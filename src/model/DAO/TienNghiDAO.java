package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
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
    
    public boolean createTienNghi(TienNghi tienNghi) {
        try {
            Document doc = new Document()
                    .append("maTienNghi", tienNghi.getMaTienNghi())
                    .append("tenTienNghi", tienNghi.getTenTienNghi())
                    .append("moTa", tienNghi.getMoTa());

            InsertOneResult result = tienNghiCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo tiện nghi: " + e.getMessage());
            return false;
        }
    }
}
