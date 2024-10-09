package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.DTO.NhanVien;
import com.mongodb.client.model.Filters;
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

    public void addNhanVien(int maNhanVien, String tenNhanVien, String anhDaiDien, String soDienThoai, String cccd, String diaChi, int chucVu, int maTaiKhoan, String tenTaiKhoan, String matKhau) {
        Document nhanVien = new Document("maNhanVien", maNhanVien)
                .append("tenNhanVien", tenNhanVien)
                .append("anhDaiDien", anhDaiDien)
                .append("SoDienThoai", soDienThoai)
                .append("CCCD", cccd)
                .append("diaChi", diaChi)
                .append("chucVu", chucVu)
                .append("maTaiKHoan", maTaiKhoan)
                .append("tenTaiKhoan", tenTaiKhoan)
                .append("matKhau", matKhau);

        nhanVienCollection.insertOne(nhanVien);
        System.out.println("Added NhanVien successfully");
    }

    public Document getNhanVienByMaNhanVien(int maNhanVien) {
        return nhanVienCollection.find(new Document("maNhanVien", maNhanVien)).first();
    }

    public void updateNhanVien(int maNhanVien, String tenNhanVien) {
        nhanVienCollection.updateOne(new Document("maNhanVien", maNhanVien),
                new Document("$set", new Document("tenNhanVien", tenNhanVien)));
        System.out.println("Updated NhanVien successfully");
    }

    public void deleteNhanVien(int maNhanVien) {
        nhanVienCollection.deleteOne(new Document("maNhanVien", maNhanVien));
        System.out.println("Deleted NhanVien successfully");
    }
}
