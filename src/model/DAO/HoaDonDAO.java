package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.HoaDon;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.DTO.PhongEmbed_HoaDon;
import org.bson.conversions.Bson;

public class HoaDonDAO {

    private MongoCollection<Document> hoaDonCollection;
    private MongoCollection<Document> donDatPhongCollection;

    public HoaDonDAO(MongoDatabase database) {
        hoaDonCollection = database.getCollection("HoaDon");
        donDatPhongCollection = database.getCollection("DonDatPhong");
    }

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDons = new ArrayList<>();
        try (MongoCursor<Document> cursor = hoaDonCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                HoaDon hoaDon = HoaDon.fromDocument(doc);
                hoaDons.add(hoaDon);
            }
        }
        return hoaDons;
    }

    public HoaDon getHoaDonByMa(int maHoaDon) {
        HoaDon hoaDon = null;
        Document query = new Document("maHoaDon", maHoaDon);
        try {
            Document doc = hoaDonCollection.find(query).first();
            if (doc != null) {
                hoaDon = HoaDon.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return hoaDon;
    }

    public boolean createHoaDon(HoaDon hoaDon) {
        try {
            // Kiểm tra hoaDon và các trường con không null
            if (hoaDon == null || hoaDon.getNhanVien() == null || hoaDon.getThongTinThanhToan() == null) {
                System.out.println("Dữ liệu hóa đơn không hợp lệ.");
                return false;
            }

            // Tạo sub-document cho nhân viên
            Document nhanVienDoc = new Document()
                    .append("maNhanVien", hoaDon.getNhanVien().getMaNhanVien())
                    .append("tenNhanVien", hoaDon.getNhanVien().getTenNhanVien());

            // Tạo sub-document cho thông tin thanh toán
            List<Document> phongsDoc = new ArrayList<>();
            if (hoaDon.getThongTinThanhToan().getPhongs() != null) {
                for (PhongEmbed_HoaDon phong : hoaDon.getThongTinThanhToan().getPhongs()) {
                    if (phong != null) {
                        Document phongDoc = new Document()
                                .append("maPhong", phong.getMaPhong())
                                .append("ngayNhan", phong.getNgayNhan())
                                .append("ngayTra", phong.getNgayTra())
                                .append("donGia", phong.getDonGia());
                        phongsDoc.add(phongDoc);
                    }
                }
            }

            // Danh sách dịch vụ
            int[] dichVuList = hoaDon.getThongTinThanhToan().getDichVu();
            List<Integer> dichVuAsList = Arrays.stream(dichVuList).boxed().toList();

            Document thongTinThanhToanDoc = new Document()
                    .append("phongs", phongsDoc)
                    .append("dichVu", dichVuAsList);

            Document doc = new Document()
                    .append("maHoaDon", hoaDon.getMaHoaDon())
                    .append("tienThanhToan", hoaDon.getTienThanhToan())
                    .append("ngayTaoHoaDon", hoaDon.getNgayTaoHoaDon())
                    .append("nhanVien", nhanVienDoc)
                    .append("donDatPhong", hoaDon.getDonDatPhong())
                    .append("thongTinThanhToan", thongTinThanhToanDoc);

            InsertOneResult result = hoaDonCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            e.printStackTrace(); // In đầy đủ stack trace để dễ dàng debug
            return false;
        }
    }

    public boolean deleteHoaDonByMaHoaDon(int maHoaDon) {
        Bson filter = eq("maHoaDon", maHoaDon);
        DeleteResult result = hoaDonCollection.deleteOne(filter);
        return result.getDeletedCount() > 0;
    }

//    public boolean updateHoaDon(HoaDon hoaDon) {
//        try {
//            Document subdoc = new Document()
//                    .append("maNhanVien", hoaDon.getNhanVien().getMaNhanVien())
//                    .append("tenNhanVien", hoaDon.getNhanVien().getTenNhanVien());
//
//            Document updateDoc = new Document()
//                    .append("maHoaDon", hoaDon.getMaHoaDon())
//                    .append("tongTien", hoaDon.getTongTien())
//                    .append("ngayTaoHoaDon", hoaDon.getNgayTaoHoaDon())
//                    .append("NhanVien", subdoc)
//                    .append("trangThai", hoaDon.isTrangThai());
//
//            Document updateQuery = new Document("$set", updateDoc);
//            Document filter = new Document("maHoaDon", hoaDon.getMaHoaDon());
//
//            UpdateResult result = hoaDonCollection.updateOne(filter, updateQuery);
//            return result.getMatchedCount() > 0;
//        } catch (Exception e) {
//            System.out.println("Lỗi xảy ra trong quá trình cập nhật hóa đơn: " + e.getMessage());
//            return false;
//        }
//
//    }
    public List<HoaDon> getHoaDonTheoNgay(Date ngayBatDau, Date ngayKetThuc) {
        // Thiết lập thời gian bắt đầu trong ngày cho ngayBatDau
        if (ngayBatDau != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngayBatDau);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            ngayBatDau = calendar.getTime();
        }

        // Thiết lập thời gian kết thúc trong ngày cho ngayKetThuc
        if (ngayKetThuc != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngayKetThuc);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            ngayKetThuc = calendar.getTime();
        }

        // Bước 1: Lấy danh sách mã hóa đơn từ collection "Đơn đặt phòng"
        List<Integer> maHoaDonList = new ArrayList<>();
        for (Document donDatPhong : donDatPhongCollection.find(
                and(
                        gte("ngayNhanPhong", ngayBatDau),
                        lte("ngayTraPhong", ngayKetThuc)
                ))) {
            Integer maHoaDon = donDatPhong.getInteger("HoaDon");
            if (maHoaDon != null) {
                maHoaDonList.add(maHoaDon);
            }
        }

        // Bước 2: Truy vấn collection "Hóa đơn" để lấy danh sách hóa đơn
        List<HoaDon> hoaDonList = new ArrayList<>();
        if (!maHoaDonList.isEmpty()) {
            for (Document doc : hoaDonCollection.find(in("maHoaDon", maHoaDonList))) {
                HoaDon hoaDon = HoaDon.fromDocument(doc);
                hoaDonList.add(hoaDon);
            }
        }
        System.out.println(hoaDonList);
        return hoaDonList;
    }

    public List<HoaDon> getHoaDonTheoNgayTraPhongHomNay() {
        // Thiết lập thời gian bắt đầu và kết thúc của ngày hôm nay
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date todayStart = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date todayEnd = calendar.getTime();

        // Bước 1: Lấy danh sách mã hóa đơn từ collection "Đơn đặt phòng" có ngày trả phòng là hôm nay
        List<Integer> maHoaDonList = new ArrayList<>();
        for (Document donDatPhong : donDatPhongCollection.find(
                and(
                        gte("ngayTraPhong", todayStart),
                        lte("ngayTraPhong", todayEnd)
                ))) {
            Integer maHoaDon = donDatPhong.getInteger("HoaDon"); // Lấy mã hóa đơn kiểu Integer
            if (maHoaDon != null) {
                maHoaDonList.add(maHoaDon);
            }
        }

        // Bước 2: Truy vấn collection "Hóa đơn" để lấy danh sách hóa đơn
        List<HoaDon> hoaDonList = new ArrayList<>();
        if (!maHoaDonList.isEmpty()) {
            for (Document doc : hoaDonCollection.find(in("maHoaDon", maHoaDonList))) {
                HoaDon hoaDon = HoaDon.fromDocument(doc); // Tạo đối tượng HoaDon từ Document
                hoaDonList.add(hoaDon);
            }
        }

        // Bước 3: Sắp xếp danh sách hóa đơn theo ngày trả phòng (dựa vào Đơn đặt phòng) giảm dần
        hoaDonList.sort((hd1, hd2) -> {
            Date ngayTraPhong1 = donDatPhongCollection.find(eq("HoaDon", hd1.getMaHoaDon())).first().getDate("ngayTraPhong");
            Date ngayTraPhong2 = donDatPhongCollection.find(eq("HoaDon", hd2.getMaHoaDon())).first().getDate("ngayTraPhong");
            return ngayTraPhong2.compareTo(ngayTraPhong1); // Giảm dần
        });

        return hoaDonList;
    }

    public boolean deleteHoaDon(int maHoaDon) {
        try {
            Document filter = new Document("maHoaDon", maHoaDon);

            DeleteResult result = hoaDonCollection.deleteOne(filter);
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa hóa đơn: " + e.getMessage());
            return false;
        }
    }
}
