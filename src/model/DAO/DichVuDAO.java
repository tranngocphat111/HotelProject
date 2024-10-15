package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.DichVu;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    private MongoCollection<Document> dichVuCollection;

    public DichVuDAO(MongoDatabase database) {
        dichVuCollection = database.getCollection("DichVu");
    }

    public List<DichVu> getAllDichVu() {
        List<DichVu> dichVus = new ArrayList<>();
        try (MongoCursor<Document> cursor = dichVuCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DichVu dichVu = DichVu.fromDocument(doc);
                dichVus.add(dichVu);
            }
        }
        return dichVus;
    }
    
    public boolean createDichVu(DichVu dichVu) {
        try {
            Document doc = new Document()
                    .append("maDV", dichVu.getMaDV())
                    .append("tenDV", dichVu.getTenDV())
                    .append("moTa", dichVu.getMoTa())
                    .append("donGia", dichVu.getDonGia());

            InsertOneResult result = dichVuCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo dịch vụ: " + e.getMessage());
            return false;
        }
    }
}
