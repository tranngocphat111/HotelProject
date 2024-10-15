package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.KhuyenMai;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO {
    private MongoCollection<Document> khuyenMaiCollection;

    public KhuyenMaiDAO(MongoDatabase database) {
        khuyenMaiCollection = database.getCollection("KhuyenMai");
    }

    public boolean createKhuyenMai(KhuyenMai khuyenMai) {
        try {
            Document doc = new Document()
                    .append("maKhuyenMai", khuyenMai.getMaKhuyenMai())
                    .append("ngayBatDau", khuyenMai.getNgayBatDau())
                    .append("ngayKetThuc", khuyenMai.getNgayKetThuc())
                    .append("tiLeKhuyenMai", khuyenMai.getTiLeKhuyenMai())
                    .append("moTa", khuyenMai.getMoTa());

            InsertOneResult result = khuyenMaiCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo khuyến mãi: " + e.getMessage());
            return false;
        }
    }
}

