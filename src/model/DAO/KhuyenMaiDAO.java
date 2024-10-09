package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.DTO.KhuyenMai;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO {
    private MongoCollection<Document> khuyenMaiCollection;

    public KhuyenMaiDAO(MongoDatabase database) {
        khuyenMaiCollection = database.getCollection("KhuyenMai");
    }

    public List<KhuyenMai> getAllKhuyenMai() {
        List<KhuyenMai> khuyenMais = new ArrayList<>();
        try (MongoCursor<Document> cursor = khuyenMaiCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                KhuyenMai khuyenMai = KhuyenMai.fromDocument(doc);
                khuyenMais.add(khuyenMai);
            }
        }
        return khuyenMais;
    }
}

