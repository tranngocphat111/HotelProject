package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.DTO.NhanVien;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    private MongoCollection<Document> nhanVienCollection;

    public NhanVienDAO(MongoDatabase database) {
        nhanVienCollection = database.getCollection("NhanVien");
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanViens = new ArrayList<>();
        try (MongoCursor<Document> cursor = nhanVienCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                NhanVien nhanVien = NhanVien.fromDocument(doc);
                nhanViens.add(nhanVien);
            }
        }
        return nhanViens;
    }
    
    public boolean createNhanVien(NhanVien nhanVien) {
        try {
            Document doc = new Document()
                    .append("maNhanVien", nhanVien.getMaNhanVien())
                    .append("tenNhanVien", nhanVien.getTenNhanVien())
                    .append("anhDaiDien", nhanVien.getAnhDaiDien())
                    .append("SoDienThoai", nhanVien.getSoDienThoai())
                    .append("CCCD", nhanVien.getCCCD())
                    .append("diaChi", nhanVien.getDiaChi())
                    .append("chucVu", nhanVien.getChucVu())
                    .append("tenTaiKhoan", nhanVien.getTenTaiKhoan())
                    .append("matKhau", nhanVien.getMatKhau());

            InsertOneResult result = nhanVienCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo nhân viên: " + e.getMessage());
            return false;
        }
    }
    
    public NhanVien checkAccount(String tenTaiKhoan, String matKhau) {
        Document doc = nhanVienCollection.find(Filters.and(
                Filters.eq("tenTaiKhoan", tenTaiKhoan),
                Filters.eq("matKhau", matKhau)
        )).first();

        if (doc != null) {
            return NhanVien.fromDocument(doc);
        } else {
            return null; // Account not found
        }
    }
}
