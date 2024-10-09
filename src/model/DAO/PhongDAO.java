package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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

    public Document getPhongByMaPhong(int maPhong) {
        return phongCollection.find(new Document("maPhong", maPhong)).first();
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

    public void updatePhong(int maPhong, int trangThai) {
        phongCollection.updateOne(new Document("maPhong", maPhong),
                new Document("$set", new Document("trangThai", trangThai)));
        System.out.println("Updated Phong successfully");
    }

    public void deletePhong(int maPhong) {
        phongCollection.deleteOne(new Document("maPhong", maPhong));
        System.out.println("Deleted Phong successfully");
    }
}

