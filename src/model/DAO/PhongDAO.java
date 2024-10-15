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
    
    public ArrayList<Phong> getAllPhongs() {
        ArrayList<Phong> phongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = phongCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Phong phong = Phong.fromDocument(document);
                phongs.add(phong);
            }
        }
        return phongs;
    }
}

