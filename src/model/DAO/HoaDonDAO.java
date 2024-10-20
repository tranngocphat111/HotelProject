package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.HoaDon;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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
    
    
}

