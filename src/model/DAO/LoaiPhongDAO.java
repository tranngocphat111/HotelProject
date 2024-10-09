package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.DTO.LoaiPhong;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAO {
    private MongoCollection<Document> loaiPhongCollection;

    public LoaiPhongDAO(MongoDatabase database) {
        loaiPhongCollection = database.getCollection("LoaiPhong");
    }

    public List<LoaiPhong> getAllLoaiPhong() {
        List<LoaiPhong> loaiPhongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = loaiPhongCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                LoaiPhong loaiPhong = LoaiPhong.fromDocument(doc);
                loaiPhongs.add(loaiPhong);
            }
        }
        return loaiPhongs;
    }
}

