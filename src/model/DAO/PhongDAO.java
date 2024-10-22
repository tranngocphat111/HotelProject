package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.Phong;
import model.MongoDBConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import model.DTO.NhanVien;

public class PhongDAO {

    private MongoCollection<Document> phongCollection;

    public PhongDAO(MongoDatabase database) {
        phongCollection = database.getCollection("Phong");
    }


    public List<Phong> getAllPhong() {
        List<Phong> Phongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = phongCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Phong phong = Phong.fromDocument(doc);
                Phongs.add(phong);
            }
        }
        return Phongs;
    }

    public Phong getPhongByMa(int maPhong) {
        Phong phong = null;
        Document query = new Document("maPhong", maPhong);
        try {
            Document doc = phongCollection.find(query).first();
            if (doc != null) {
                phong = Phong.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return phong;
    }




    public boolean createPhong(Phong phong) {
        try {
            Document doc = new Document()
                    .append("maPhong", phong.getMaPhong())
                    .append("tang", phong.getTang())
                    .append("loaiPhong", phong.getLoaiPhong())
                    .append("moTa", phong.getMoTa());

            InsertOneResult result = phongCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo phòng: " + e.getMessage());
            return false;
        }
    }
    
    
    
    public List<Phong> getAllPhongsSortByMaPhong() {
        List<Phong> phongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = phongCollection.find()
                .sort(Sorts.ascending("maPhong"))
                .iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Phong phong = Phong.fromDocument(document);
                phongs.add(phong);
            }
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình lấy danh sách phòng: " + e.getMessage());
        }
        return phongs;
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
