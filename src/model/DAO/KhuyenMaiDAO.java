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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
                    .append("loaiPhong", khuyenMai.getLoaiPhong());
            

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
            System.out.println("Lỗi xảy ra trong quá trình chỉnh khuyến mãi: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }
    
     public boolean deleteKhuyenMai(int maKM) {
        try {
            Document id = new Document("maKhuyenMai",maKM);
            
            DeleteResult result = khuyenMaiCollection.deleteOne(id);

            
            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa khuyến mãi: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }
    
    public List<Document> findKhuyenMai(Date ngayBatDau, Date ngayKetThuc, int tiLeKhuyenMai, String moTa) throws ParseException {
        List<Document> filters = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+1"));


        if (tiLeKhuyenMai > 0) {
            filters.add(new Document("tiLeKhuyenMai", tiLeKhuyenMai));
        }

        // Thêm điều kiện cho mô tả
        if (moTa != null && !moTa.isEmpty()) {
            filters.add(new Document("moTa", moTa));
        }

        if(ngayBatDau !=null) {
//        Calendar startCalendar = Calendar.getInstance();
//        startCalendar.setTime(ngayBatDau);
//            
//        String startDate_Str = dateFormat.format(startCalendar.getTime());
        filters.add(new Document("ngayBatDau", new Document("$lte", ngayBatDau)));
        }
        
        if(ngayKetThuc != null) {
//        Calendar endCalendar = Calendar.getInstance();
//        endCalendar.setTime(ngayKetThuc);
//        endCalendar.add(Calendar.HOUR_OF_DAY, -1); 
//
//     
//        String endDate_Str = dateFormat.format(endCalendar.getTime());

   
//         filters.add(new Document("ngayKetThuc", new Document("$lte", new Date(2024-10-08T17:00:00.000+00:00))));
            
        }
      
        System.out.println(filters);

   
        if (!filters.isEmpty()) {
            Document query = new Document("$and", filters);
            return khuyenMaiCollection.find(query).into(new ArrayList<>());
        } else {
            return khuyenMaiCollection.find().into(new ArrayList<>());
        }
    }
}

