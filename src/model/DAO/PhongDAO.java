package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.Phong;
import model.MongoDBConnection;
import org.bson.Document;

import java.util.ArrayList;

public class PhongDAO {

    private MongoCollection<Document> phongCollection;

    public PhongDAO(MongoDatabase database) {
        phongCollection = database.getCollection("Phong");
    }

    public void addPhong(int maPhong, int trangThai, String soPhong, int tang, int loaiPhong) {
        Document phong = new Document("maPhong", maPhong)
                .append("trangThai", trangThai)
                .append("soPhong", soPhong)
                .append("tang", tang)
                .append("loaiPhong", loaiPhong);

        phongCollection.insertOne(phong);
        System.out.println("Added Phong successfully");
    }

    public boolean createPhong(Phong phong) {
        try {
            Document doc = new Document()
                    .append("maPhong", phong.getMaPhong())
                    .append("trangThai", phong.getTrangThai())
                    .append("soPhong", phong.getSoPhong())
                    .append("tang", phong.getTang())
                    .append("loaiPhong", phong.getLoaiPhong());

            InsertOneResult result = phongCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo phòng: " + e.getMessage());
            return false;
        }
    }
}

