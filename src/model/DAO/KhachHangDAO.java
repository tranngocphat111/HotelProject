package model.DAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.KhachHang;
import model.MongoDBConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    private MongoCollection<Document> khachHangCollection;

    public KhachHangDAO(MongoDatabase database) {
        khachHangCollection = database.getCollection("KhachHang");
    }

    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> khachHangs = new ArrayList<>();
        try (MongoCursor<Document> cursor = khachHangCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                KhachHang khachHang = KhachHang.fromDocument(doc);
                khachHangs.add(khachHang);
            }
        }
        return khachHangs;
    }
    
    public boolean createKhachHang(KhachHang khachHang) {
        try {
            Document doc = new Document()
                    .append("maKhachHang", khachHang.getMaKhachHang())
                    .append("tenKhachHang", khachHang.getTenKhachHang())
                    .append("soDienThoai", khachHang.getSoDienThoai())
                    .append("CCCD", khachHang.getCCCD())
                    .append("gioiTinh", khachHang.getGioiTinh())
                    .append("email", khachHang.getEmail())
                    .append("quocTich", khachHang.getQuocTich());

            InsertOneResult result = khachHangCollection.insertOne(doc);
            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo khách hàng: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }
}
