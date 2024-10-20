package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.TienNghi;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TienNghiDAO {
    private MongoCollection<Document> tienNghiCollection;

    public TienNghiDAO(MongoDatabase database) {
        tienNghiCollection = database.getCollection("TienNghi");
    }

    public MongoCollection<Document> getCollection() {
        return tienNghiCollection;
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
    
    public TienNghi timTienNghi(String tenTienNghi) {
        TienNghi x = new TienNghi(tenTienNghi);
        List<TienNghi> list_TienNghi = getAllTienNghi();
        if(!list_TienNghi.contains(x)) {
            return null;
        }
        return list_TienNghi.get(list_TienNghi.indexOf(x));
    }
    public boolean xoaTienNghi(TienNghi tienNghi) {
        try {
            Document doc = new Document()
                    .append("maTienNghi", tienNghi.getMaTienNghi())
                    .append("tenTienNghi", tienNghi.getTenTienNghi())
                    .append("moTa", tienNghi.getMoTa())
                    .append("hinhAnh", tienNghi.getHinhAnh());

            DeleteResult result = tienNghiCollection.deleteOne(doc);            
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa tiện nghi: " + e.getMessage());
            return false;
        }
    }
    
    public boolean suaTienNghi(TienNghi oldTienNghi, TienNghi newTienNghi) {
        try {
            Document filter = new Document()
                    .append("maTienNghi", oldTienNghi.getMaTienNghi());
            
            Document newValue = new Document(
                    "$set", 
                    new Document().append("tenTienNghi", newTienNghi.getTenTienNghi())
                                  .append("moTa", newTienNghi.getMoTa())
                                  .append("hinhAnh", newTienNghi.getHinhAnh())
            );
                    
            
            UpdateResult result = tienNghiCollection.updateOne(filter, newValue);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa tiện nghi: " + e.getMessage());
            return false;
        }
    }
    
}
