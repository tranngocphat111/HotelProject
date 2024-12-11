package model.DAO;

import static GUI.DangNhap_GUI.database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.KhachHang;
import model.MongoDBConnection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import model.DTO.DonDatPhong;
import model.DTO.LoaiPhong;

public class KhachHangDAO {

    private MongoCollection<Document> khachHangCollection;

    public KhachHangDAO(MongoDatabase database) {
        khachHangCollection = database.getCollection("KhachHang");
    }

    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> khachHangs = new ArrayList<>();
        try (MongoCursor<Document> cursor = khachHangCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                KhachHang khachHang = KhachHang.fromDocument(doc);
                khachHangs.add(khachHang);
            }
        }
        return khachHangs;
    }

    public KhachHang getKhachHangByMa(int makhachHang) {
        KhachHang khachHang = null;
        Document query = new Document("maKhachHang", makhachHang);
        try {
            Document doc = khachHangCollection.find(query).first();
            if (doc != null) {
                khachHang = KhachHang.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return khachHang;
    }

    public boolean updateKhachHang(int maKH, KhachHang khachHang) {
        try {
            Document id = new Document("maKhachHang", maKH);
            Document update = new Document("$set",
                    new Document(
                            "Hoten", khachHang.getTenKhachHang())
                            .append("SDT", khachHang.getSoDienThoai())
                            .append("CCCD", khachHang.getCCCD())
                            .append("GioiTinh", khachHang.getGioiTinh())
                            .append("Email", khachHang.getEmail())
                            .append("QuocTich", khachHang.getQuocTich())
            );

            UpdateResult result = khachHangCollection.updateOne(id, update);

//            DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO(database);
//            
//            for(DonDatPhong x : donDatPhongDAO.getAllDonDatPhong()) {
//                
//                for(int  i = 0; i < x.getKhachO().size(); i++) {
//                    if(x.getKhachO().get(i).getMaKhachHang() == maKH) {
//                        x.getKhachO().set(i, khachHang);
//                    }
//                }
//                
//                donDatPhongDAO.updateDonDatPhong(x);
//            }
            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo khách hàng: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }

//     public boolean deleteKhachHang(int maKH) {
//        try {
//            Document id = new Document("maKhachHang",maKH);
//            
//            DeleteResult result = khachHangCollection.deleteOne(id);
//
//            DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO(new MongoDBConnection().getDatabase());
//            KhachHang khachHang = new KhachHangDAO(new MongoDBConnection().getDatabase()).getKhachHangByMa(maKH);
//            for(DonDatPhong x : donDatPhongDAO.getAllDonDatPhong()) {
//                x.getKhachO().remove(khachHang);
//                donDatPhongDAO.updateDonDatPhong(x);
//                
//            }
//            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
//        } catch (Exception e) {
//            System.out.println("Lỗi xảy ra trong quá trình tạo khách hàng: " + e.getMessage());
//            return false; // Trả về false nếu có lỗi
//        }
//    }
    public List<Document> findKhachHang(String cccd, String email, String tenKhachHang, String soDienThoai) {
        List<Document> filters = new ArrayList<>();

        if (tenKhachHang != null && !tenKhachHang.isEmpty()) {
            filters.add(new Document("HoTen", tenKhachHang));
        }
        if (cccd != null && !cccd.isEmpty()) {
            filters.add(new Document("CCCD", cccd));
        }
        if (email != null && !email.isEmpty()) {
            filters.add(new Document("Email", email));
        }

        if (soDienThoai != null && !soDienThoai.isEmpty()) {
            filters.add(new Document("SDT", soDienThoai));
        }

        if (filters.isEmpty()) {

            return khachHangCollection.find().into(new ArrayList<>());
        } else {
            Document query = new Document("$and", filters);
            return khachHangCollection.find(query).into(new ArrayList<>());
        }
    }
  public KhachHang getKhachHangByCCCD(String CCCD) {
        KhachHang khachHang = null;
        Document query = new Document("CCCD", CCCD);
        try {
            Document doc = khachHangCollection.find(query).first();
            if (doc != null) {
                khachHang = KhachHang.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return khachHang;
    }
  public boolean createKhachHang(KhachHang khachHang) {
        try {
            Document doc = new Document()
                    .append("maKhachHang", khachHang.getMaKhachHang())
                    .append("Hoten", khachHang.getTenKhachHang())
                    .append("SDT", khachHang.getSoDienThoai())
                    .append("CCCD", khachHang.getCCCD())
                    .append("GioiTinh", khachHang.getGioiTinh())
                    .append("Email", khachHang.getEmail())
                    .append("QuocTich", khachHang.getQuocTich());

            InsertOneResult result = khachHangCollection.insertOne(doc);
            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo khách hàng: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }
  
}
