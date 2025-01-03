package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.DTO.KhuyenMai;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhuyenMaiDAO {
    private MongoCollection<Document> khuyenMaiCollection;

    public KhuyenMaiDAO(MongoDatabase database) {
        khuyenMaiCollection = database.getCollection("KhuyenMai");
    }

    public boolean createKhuyenMai(KhuyenMai khuyenMai) {
        try {
            Document doc = new Document()
                    .append("maKhuyenMai", khuyenMai.getMaKhuyenMai())
                    .append("ngayBatDau", khuyenMai.getNgayBatDau())
                    .append("ngayKetThuc", khuyenMai.getNgayKetThuc())
                    .append("tiLeKhuyenMai", khuyenMai.getTiLeKhuyenMai())
                    .append("moTa", khuyenMai.getMoTa())
                    .append("loaiPhong",khuyenMai.getLoaiPhong());

            InsertOneResult result = khuyenMaiCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo khuyến mãi: " + e.getMessage());
            return false;
        }
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
    
    
    public boolean updateKhuyenMai(int maKM,KhuyenMai khuyenMai) {
        try {
            Document id = new Document("maKhuyenMai",maKM);
            Document update = new Document("$set",
                    new Document(
                    "ngayBatDau", khuyenMai.getNgayBatDau())
                    .append("ngayKetThuc", khuyenMai.getNgayKetThuc())
                    .append("tiLeKhuyenMai", khuyenMai.getTiLeKhuyenMai())
                    .append("moTa", khuyenMai.getMoTa())
                    .append("loaiPhong", khuyenMai.getLoaiPhong())
            );
            
            UpdateResult result = khuyenMaiCollection.updateOne(id, update);

            
            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo khách hàng: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }
    
     public boolean deleteKhuyenMai(int maKM) {
        try {
            Document id = new Document("maKhuyenMai",maKM);
            
            DeleteResult result = khuyenMaiCollection.deleteOne(id);

            
            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo khách hàng: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }
    
    public List<Document> findKhuyenMai(Date ngayBatDau,Date ngayKetThuc,int tiLeKhuyenMai, String moTa,List<String> loaiPhong) throws ParseException {
        List<Document> filters = new ArrayList<>();
        
        
     
        if (tiLeKhuyenMai >0) {
            filters.add(new Document("tiLeKhuyenMai", tiLeKhuyenMai));
        }
       
        if (moTa != null && !moTa.isEmpty()) {
            filters.add(new Document("moTa", moTa));
        }
        
        if (ngayBatDau != null ) {           
            filters.add(new Document("ngayBatDau", ngayBatDau));
        }
        if (ngayKetThuc != null) {
            filters.add(new Document("ngayKetThuc", ngayKetThuc));
        }
        if(loaiPhong.size() != 0) {
            filters.add(new Document("loaiPhong", loaiPhong));

        }

      
        if (filters.isEmpty()) {
            
            return khuyenMaiCollection.find().into(new ArrayList<>());
        } else {
            Document query = new Document("$and", filters);
            return khuyenMaiCollection.find(query).into(new ArrayList<>());
        }
    }
}

