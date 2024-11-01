package model.DAO;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.computed;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.DonDatPhong;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.DTO.DichVu;
import model.DTO.DichVuEmbed;
import model.DTO.KhachHang;
import org.bson.conversions.Bson;

public class DonDatPhongDAO {

    private MongoCollection<Document> donDatPhongCollection;

    public DonDatPhongDAO(MongoDatabase database) {
        donDatPhongCollection = database.getCollection("DonDatPhong");
    }

    public List<DonDatPhong> getAllDonDatPhong() {
        List<DonDatPhong> donDatPhongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = donDatPhongCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DonDatPhong donDatPhong = DonDatPhong.fromDocument(doc);
                donDatPhongs.add(donDatPhong);
            }
        }
        return donDatPhongs;
    }

    public List<DonDatPhong> getDonDatPhongTheoTrangThaiOVaCho() {
        List<DonDatPhong> donDatPhongs = new ArrayList<>();
        // Danh sách trạng thái cần tìm
        List<String> trangThaiTimKiem = Arrays.asList("Đang ở", "Đang chờ");

        try (MongoCursor<Document> cursor = donDatPhongCollection.find(new Document("trangThai", new Document("$in", trangThaiTimKiem))).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DonDatPhong donDatPhong = DonDatPhong.fromDocument(doc);
                donDatPhongs.add(donDatPhong);
            }
        }
        return donDatPhongs;
    }

    public List<DonDatPhong> getDonDatPhongTheoTrangThaiO() {
        List<DonDatPhong> donDatPhongs = new ArrayList<>();
        // Trạng thái cần tìm
        String trangThaiTimKiem = "Đang ở";

        try (MongoCursor<Document> cursor = donDatPhongCollection.find(new Document("trangThai", trangThaiTimKiem)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DonDatPhong donDatPhong = DonDatPhong.fromDocument(doc);
                donDatPhongs.add(donDatPhong);
            }
        }
        return donDatPhongs;
    }

    public DonDatPhong getDonDatPhongByMa(int maDon) {
        DonDatPhong donDatPhong = null;
        Document query = new Document("maDonDat", maDon);
        try {
            Document doc = donDatPhongCollection.find(query).first();
            if (doc != null) {
                donDatPhong = DonDatPhong.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return donDatPhong;
    }

    public boolean createDonDatPhong(DonDatPhong donDatPhong) {
        try {

            ArrayList<Document> list_KhachHang = new ArrayList<Document>();
            for (KhachHang khachHang : donDatPhong.getKhachO()) {
                list_KhachHang.add(new Document()
                        .append("maKhachHang", khachHang.getMaKhachHang())
                        .append("HoTen", khachHang.getTenKhachHang())
                        .append("SDT", khachHang.getSoDienThoai())
                        .append("CCCD", khachHang.getCCCD())
                        .append("GioiTinh", khachHang.getGioiTinh())
                        .append("Email", khachHang.getEmail())
                        .append("QuocTich", khachHang.getQuocTich())
                );
            }

            ArrayList<Document> list_DichVu = new ArrayList<Document>();
            for (DichVuEmbed dichVu : donDatPhong.getDichVuSuDung()) {
                list_DichVu.add(
                        new Document()
                                .append("maDV", dichVu.getMaDV())
                                .append("tenDV", dichVu.getTenDV())
                                .append("soLuong", dichVu.getSoLuong())
                                .append("donGia", dichVu.getDonGia())
                );
            }

            Document doc = new Document()
                    .append("maDonDat", donDatPhong.getMaDonDat())
                    .append("ngayDatPhong", donDatPhong.getNgayDatPhong())
                    .append("ngayNhanPhong", donDatPhong.getNgayNhanPhong())
                    .append("ngayTraPhong", donDatPhong.getNgayTraPhong())
                    .append("trangThai", donDatPhong.getTrangThai())
                    .append("KhachO", list_KhachHang)
                    .append("dichVuSuDung", list_DichVu)
                    .append("Phong", new Document()
                            .append("maPhong", donDatPhong.getPhong().getMaPhong())
                            .append("donGia", donDatPhong.getPhong().getDonGia())
                                .append("tenLoaiPhong", donDatPhong.getPhong().getTenLoaiPhong())
                    )
                    .append("HoaDon", donDatPhong.getHoaDon());

            InsertOneResult result = donDatPhongCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo đơn đặt phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean updateDonDatPhong(DonDatPhong donDatPhong) {
        try {
            ArrayList<Document> list_KhachHang = new ArrayList<Document>();
            for (KhachHang khachHang : donDatPhong.getKhachO()) {
                list_KhachHang.add(new Document()
                        .append("maKhachHang", khachHang.getMaKhachHang())
                        .append("HoTen", khachHang.getTenKhachHang())
                        .append("SDT", khachHang.getSoDienThoai())
                        .append("CCCD", khachHang.getCCCD())
                        .append("GioiTinh", khachHang.getGioiTinh())
                        .append("Email", khachHang.getEmail())
                        .append("QuocTich", khachHang.getQuocTich())
                );
            }

            ArrayList<Document> list_DichVu = new ArrayList<Document>();
            for (DichVuEmbed dichVu : donDatPhong.getDichVuSuDung()) {
                list_DichVu.add(
                        new Document()
                                .append("maDV", dichVu.getMaDV())
                                .append("tenDV", dichVu.getTenDV())
                                .append("soLuong", dichVu.getSoLuong())
                                .append("donGia", dichVu.getDonGia())
                );
            }


            Document phong = new Document()
                    .append("maPhong", donDatPhong.getPhong().getMaPhong())
                    .append("donGia", donDatPhong.getPhong().getDonGia())
                    .append("tenLoaiPhong", donDatPhong.getPhong().getTenLoaiPhong());

            Document updateDoc = new Document()
                    .append("maDonDat", donDatPhong.getMaDonDat())
                    .append("ngayDatPhong", donDatPhong.getNgayDatPhong())
                    .append("ngayNhanPhong", donDatPhong.getNgayNhanPhong())
                    .append("ngayTraPhong", donDatPhong.getNgayTraPhong())
                    .append("trangThai", donDatPhong.getTrangThai())
                    .append("KhachO", list_KhachHang)
                    .append("dichVuSuDung", list_DichVu)
                    .append("Phong", new Document()
                            .append("maPhong", donDatPhong.getPhong().getMaPhong())
                            .append("donGia", donDatPhong.getPhong().getDonGia())
                                .append("tenLoaiPhong", donDatPhong.getPhong().getTenLoaiPhong())
                    )
                    .append("HoaDon", donDatPhong.getHoaDon());

            Document updateQuery = new Document("$set", updateDoc);
            Document filter = new Document("maDonDat", donDatPhong.getMaDonDat());

            UpdateResult result = donDatPhongCollection.updateOne(filter, updateQuery);
            return result.getMatchedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình cập nhật đơn đặt phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean xoaDonDatPhongByMaDonDat(int maDonDat) {
        Bson filter = and(
                eq("maDonDat", maDonDat),
                eq("trangThai", "Đang chờ")
        );

        DeleteResult result = donDatPhongCollection.deleteOne(filter);
        return result.getDeletedCount() > 0;
    }

    public ArrayList<Document> getDonDatPhongTheoNgay(Date ngayBatDau, Date ngayKetThuc) {
        List<Bson> pipeline = Arrays.asList(
                lookup("Phong", "Phong", "maPhong", "Phongs"),
                lookup("LoaiPhong", "Phongs.loaiPhong", "maLoaiPhong", "LoaiPhongs"),
                project(fields(
                        excludeId(),
                        include("ngayNhanPhong", "ngayTraPhong"),
                        computed("tenLoaiPhong", "$LoaiPhongs.tenLoaiPhong")
                )),
                match(or(
                        and(gte("ngayNhanPhong", ngayBatDau), lte("ngayNhanPhong", ngayKetThuc)),
                        and(gte("ngayTraPhong", ngayBatDau), lte("ngayTraPhong", ngayKetThuc))
                ))
        );

        AggregateIterable<Document> results = donDatPhongCollection.aggregate(pipeline);
        ArrayList<Document> documents = new ArrayList<>();
        results.into(documents);
        return documents;
    }

    public ArrayList<Document> getDoanhThu(Date ngayBatDau, Date ngayKetThuc) {
        // Tạo pipeline cho aggregate
        List<Bson> pipeline = Arrays.asList(
                lookup("Phong", "Phong", "maPhong", "Phongs"),
                lookup("LoaiPhong", "Phongs.loaiPhong", "maLoaiPhong", "LoaiPhongs"),
                match(or(
                        and(
                                gte("ngayNhanPhong", ngayBatDau),
                                lte("ngayNhanPhong", ngayKetThuc)
                        ),
                        and(
                                gte("ngayTraPhong", ngayBatDau),
                                lte("ngayTraPhong", ngayKetThuc)
                        )
                )),
                project(fields(
                        include("LoaiPhongs.donGia", "ngayNhanPhong", "ngayTraPhong", "dichVuSuDung.tenDV", "dichVuSuDung.donGia")
                ))
        );

        // Thực thi aggregate
        AggregateIterable<Document> results = donDatPhongCollection.aggregate(pipeline);

        // Chuyển kết quả thành ArrayList
        ArrayList<Document> documents = new ArrayList<>();
        results.into(documents);

        return documents;
    }
}
