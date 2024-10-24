package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.DonDatPhong;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.DTO.DichVu;
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
                        .append("tenKhachHang", khachHang.getTenKhachHang())
                        .append("soDienThoai", khachHang.getSoDienThoai())
                        .append("CCCD", khachHang.getCCCD())
                        .append("gioiTinh", khachHang.getGioiTinh())
                        .append("email", khachHang.getEmail())
                        .append("quocTich", khachHang.getQuocTich())
                );
            }

            ArrayList<Document> list_DichVu = new ArrayList<Document>();
            for (DichVu dichVu : donDatPhong.getDichVuSuDung()) {
                list_DichVu.add(
                        new Document()
                                .append("maDV", dichVu.getMaDV())
                                .append("tenDV", dichVu.getTenDV())
                                .append("moTa", dichVu.getMoTa())
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
                    .append("Phong", donDatPhong.getPhong())
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
            ArrayList<Document> list_KhachHang = new ArrayList<>();
            for (KhachHang khachHang : donDatPhong.getKhachO()) {
                list_KhachHang.add(new Document()
                        .append("maKhachHang", khachHang.getMaKhachHang())
                        .append("tenKhachHang", khachHang.getTenKhachHang())
                        .append("soDienThoai", khachHang.getSoDienThoai())
                        .append("CCCD", khachHang.getCCCD())
                        .append("gioiTinh", khachHang.getGioiTinh())
                        .append("email", khachHang.getEmail())
                        .append("quocTich", khachHang.getQuocTich())
                );
            }

            ArrayList<Document> list_DichVu = new ArrayList<>();
            for (DichVu dichVu : donDatPhong.getDichVuSuDung()) {
                list_DichVu.add(new Document()
                        .append("maDV", dichVu.getMaDV())
                        .append("tenDV", dichVu.getTenDV())
                        .append("moTa", dichVu.getMoTa())
                        .append("donGia", dichVu.getDonGia())
                );
            }

            Document updateDoc = new Document()
                    .append("ngayDatPhong", donDatPhong.getNgayDatPhong())
                    .append("ngayNhanPhong", donDatPhong.getNgayNhanPhong())
                    .append("ngayTraPhong", donDatPhong.getNgayTraPhong())
                    .append("trangThai", donDatPhong.getTrangThai())
                    .append("KhachO", list_KhachHang)
                    .append("dichVuSuDung", list_DichVu)
                    .append("Phong", donDatPhong.getPhong())
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
}
