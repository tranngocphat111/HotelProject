package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.DTO.TienNghi;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TienNghiDAO {
    private MongoCollection<Document> tienNghiCollection;

    public TienNghiDAO(MongoDatabase database) {
        tienNghiCollection = database.getCollection("TienNghi");
    }

    public List<TienNghi> getAllTienNghi() {
        List<TienNghi> tienNghis = new ArrayList<>();
        try (MongoCursor<Document> cursor = tienNghiCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                TienNghi tienNghi = TienNghi.fromDocument(doc);
                tienNghis.add(tienNghi);
            }
        }
        return tienNghis;
    }
}
