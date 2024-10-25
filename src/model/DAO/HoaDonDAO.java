package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.HoaDon;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.conversions.Bson;

public class HoaDonDAO {

    private MongoCollection<Document> hoaDonCollection;

    public HoaDonDAO(MongoDatabase database) {
        hoaDonCollection = database.getCollection("HoaDon");
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
            Document subdoc = new Document()
                    .append("maNhanVien", hoaDon.getNhanVien().getMaNhanVien())
                    .append("tenNhanVien", hoaDon.getNhanVien().getTenNhanVien())
                    .append("anhDaiDien", hoaDon.getNhanVien().getAnhDaiDien())
                    .append("SoDienThoai", hoaDon.getNhanVien().getSoDienThoai())
                    .append("CCCD", hoaDon.getNhanVien().getCCCD())
                    .append("diaChi", hoaDon.getNhanVien().getDiaChi())
                    .append("chucVu", hoaDon.getNhanVien().getChucVu())
                    .append("tenTaiKhoan", hoaDon.getNhanVien().getTenTaiKhoan())
                    .append("matKhau", hoaDon.getNhanVien().getMatKhau());
            Document doc = new Document()
                    .append("maHoaDon", hoaDon.getMaHoaDon())
                    .append("tongTien", hoaDon.getTongTien())
                    .append("ngayTaoHoaDon", hoaDon.getNgayTaoHoaDon())
                    .append("NhanVien", subdoc)
                    .append("trangThai", hoaDon.isTrangThai());

            InsertOneResult result = hoaDonCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo hóa đơn: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteHoaDonByMaHoaDon(int maHoaDon) {
        Bson filter = eq("maHoaDon", maHoaDon);
        DeleteResult result = hoaDonCollection.deleteOne(filter);
        return result.getDeletedCount() > 0;
    }

    public boolean updateHoaDon(HoaDon hoaDon) {
        try {
            Document subdoc = new Document()
                    .append("maNhanVien", hoaDon.getNhanVien().getMaNhanVien())
                    .append("tenNhanVien", hoaDon.getNhanVien().getTenNhanVien())
                    .append("anhDaiDien", hoaDon.getNhanVien().getAnhDaiDien())
                    .append("SoDienThoai", hoaDon.getNhanVien().getSoDienThoai())
                    .append("CCCD", hoaDon.getNhanVien().getCCCD())
                    .append("diaChi", hoaDon.getNhanVien().getDiaChi())
                    .append("chucVu", hoaDon.getNhanVien().getChucVu())
                    .append("tenTaiKhoan", hoaDon.getNhanVien().getTenTaiKhoan())
                    .append("matKhau", hoaDon.getNhanVien().getMatKhau());

            Document updateDoc = new Document()
                    .append("maHoaDon", hoaDon.getMaHoaDon())
                    .append("tongTien", hoaDon.getTongTien())
                    .append("ngayTaoHoaDon", hoaDon.getNgayTaoHoaDon())
                    .append("NhanVien", subdoc)
                    .append("trangThai", hoaDon.isTrangThai());

            Document updateQuery = new Document("$set", updateDoc);
            Document filter = new Document("maHoaDon", hoaDon.getMaHoaDon());

            UpdateResult result = hoaDonCollection.updateOne(filter, updateQuery);
            return result.getMatchedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình cập nhật hóa đơn: " + e.getMessage());
            return false;
        }

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
