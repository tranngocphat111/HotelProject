    package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.*;
import model.DTO.DichVu;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import model.DTO.DonDatPhong;
import model.DTO.LoaiPhong;
import model.DTO.TienNghi;
import model.MongoDBConnection;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

public class DichVuDAO {
    private MongoCollection<Document> dichVuCollection;
    
    public MongoCollection<Document> getCollection() {
        return dichVuCollection;
    }
    
    
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
                    .append("donGia", dichVu.getDonGia())
                    .append("hinhAnh", dichVu.getHinhAnh());

            InsertOneResult result = dichVuCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo dịch vụ: " + e.getMessage());
            return false;
        }
    }
    
   public DichVu getDichVuByMa(int maDichVu) {
        DichVu dichvu = null;
        Document query = new Document("maDV", maDichVu);
        try {
            Document doc = dichVuCollection.find(query).first();
            if (doc != null) {
                dichvu = DichVu.fromDocument(doc); 
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return dichvu;
    }

    
    public DichVu timDichVu(String tenDV) {
        DichVu x = new DichVu(tenDV);
        List<DichVu> list_DV = getAllDichVu();
        if(!list_DV.contains(x)) {
            return null;
        }
        return list_DV.get(list_DV.indexOf(x));
    }
    
    public boolean xoaDichVu(DichVu dichVu) {
        try {
            Document doc = new Document()
                    .append("maDV", dichVu.getMaDV())
                    .append("tenDV", dichVu.getTenDV())
                    .append("moTa", dichVu.getMoTa())
                    .append("donGia", dichVu.getDonGia())
                    .append("hinhAnh", dichVu.getHinhAnh());

            DeleteResult result = dichVuCollection.deleteOne(doc);  
            
            DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO(new MongoDBConnection().getDatabase());
            
            for(DonDatPhong x : donDatPhongDAO.getAllDonDatPhong()) {
                x.getDichVuSuDung().remove(dichVu);
                donDatPhongDAO.updateDonDatPhong(x);
                
            }
            
            
            
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa dịch vụ: " + e.getMessage());
            return false;
        }
    }
    
    public boolean suaDichVu(DichVu oldDVu, DichVu newDVu) {
        try {
            Document filter = new Document()
                    .append("maDV", oldDVu.getMaDV());
            
            Document newValue = new Document(
                    "$set", 
                    new Document().append("tenDV", newDVu.getTenDV())
                                  .append("moTa", newDVu.getMoTa())
                                  .append("donGia", newDVu.getDonGia())
                                  .append("hinhAnh", newDVu.getHinhAnh())
            );
                    
            
            UpdateResult result = dichVuCollection.updateOne(filter, newValue);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa dịch vụ: " + e.getMessage());
            return false;
        }
    }
    
}
