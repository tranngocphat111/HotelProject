package model.DAO;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import model.DTO.NhanVien;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import model.DTO.DichVu;
import model.DTO.DonDatPhong;
import model.DTO.KhachHang;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

public class NhanVienDAO {

    private MongoCollection<Document> nhanVienCollection;

    public NhanVienDAO(MongoDatabase database) {
        nhanVienCollection = database.getCollection("NhanVien");
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanViens = new ArrayList<>();
        try (MongoCursor<Document> cursor = nhanVienCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                NhanVien nhanVien = NhanVien.fromDocument(doc);
                nhanViens.add(nhanVien);
            }
        }
        return nhanViens;
    }
    
    public boolean createNhanVien(NhanVien nhanVien) {
        try {
            Document doc = new Document()
                    .append("maNhanVien", nhanVien.getMaNhanVien())
                    .append("tenNhanVien", nhanVien.getTenNhanVien())
                    .append("anhDaiDien", nhanVien.getAnhDaiDien())
                    .append("SoDienThoai", nhanVien.getSoDienThoai())
                    .append("CCCD", nhanVien.getCCCD())
                    .append("diaChi", nhanVien.getDiaChi())
                    .append("chucVu", nhanVien.getChucVu())
                    .append("tenTaiKhoan", nhanVien.getTenTaiKhoan())
                    .append("matKhau", nhanVien.getMatKhau());

            InsertOneResult result = nhanVienCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo nhân viên: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateNhanVien(NhanVien nhanVien) {
        try {
            

            Document updateDoc = new Document()
                    .append("maNhanVien", nhanVien.getMaNhanVien())
                    .append("tenNhanVien", nhanVien.getTenNhanVien())
                    .append("anhDaiDien", nhanVien.getAnhDaiDien())
                    .append("SoDienThoai", nhanVien.getSoDienThoai())
                    .append("CCCD", nhanVien.getCCCD())
                    .append("diaChi", nhanVien.getDiaChi())
                    .append("chucVu", nhanVien.getChucVu())
                    .append("tenTaiKhoan", nhanVien.getTenTaiKhoan())
                    .append("matKhau", nhanVien.getMatKhau());

            Document updateQuery = new Document("$set", updateDoc);
            Document filter = new Document("maNhanVien", nhanVien.getMaNhanVien());

            UpdateResult result = nhanVienCollection.updateOne(filter, updateQuery);
            return result.getMatchedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình cập nhật đơn đặt phòng: " + e.getMessage());
            return false;
        }
    }
    public NhanVien checkAccount(String tenTaiKhoan, String matKhau) {
        Document doc = nhanVienCollection.find(Filters.and(
                Filters.eq("tenTaiKhoan", tenTaiKhoan),
                Filters.eq("matKhau", matKhau)
        )).first();

        if (doc != null) {
            return NhanVien.fromDocument(doc);
        } else {
            return null; // Account not found
        }
    }
    
     public NhanVien checkAccountTheoTen(String tenTaiKhoan) {
        Document doc = nhanVienCollection.find(Filters.and(
                Filters.eq("tenTaiKhoan", tenTaiKhoan)
        )).first();

        if (doc != null) {
            return NhanVien.fromDocument(doc);
        } else {
            return null; // Account not found
        }
    }
    public NhanVien timTheoMaNhanVien(int maNhanVien) {
        
        List<NhanVien> list_DV = getAllNhanVien();
        for(NhanVien x : list_DV) {
            if(x.getMaNhanVien() == maNhanVien) return x;
        }
        return null;
    }
    
    public NhanVien timTheoTenNhanVien(String TenNhanVien) {
        
        List<NhanVien> list_DV = getAllNhanVien();
        for(NhanVien x : list_DV) {
            if(x.getTenNhanVien().equals(TenNhanVien)) return x;
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public List<NhanVien> timTheoCCCD(String CCCD) {
        
        List<Bson> pipeline = Arrays.asList(
            Aggregates.match(Filters.regex("CCCD", ".*" + CCCD + ".*")),
            Aggregates.project(Projections.fields(
                Projections.include(
                    "maNhanVien",
                    "tenNhanVien",
                    "anhDaiDien",
                    "SoDienThoai",
                    "CCCD",
                    "diaChi",
                    "chucVu",
                    "tenTaiKhoan",
                    "matKhau"
                )
            ))
        );

        AggregateIterable<Document> aggregate = nhanVienCollection.aggregate(pipeline);

        List<NhanVien> list_NhanVien = new ArrayList<>();
        for (Document x : aggregate) {
            
            
            
            NhanVien t = new NhanVien(
                x.getInteger("maNhanVien"),
                x.getString("tenNhanVien"),
                x.get("anhDaiDien", Binary.class).getData(),
                x.getString("SoDienThoai"),
                x.getString("CCCD"),
                x.getString("diaChi"),
                x.getString("chucVu"),
                x.getString("tenTaiKhoan"),
                x.getString("matKhau")
            );
            list_NhanVien.add(t);
        }
        
        return list_NhanVien;
    }
    
    public List<NhanVien> timTheoTenNV(String tenNV) {
        List<Bson> pipeline = Arrays.asList(
                Aggregates.match(Filters.regex("tenNhanVien", ".*" + tenNV + ".*" )),
                Projections.include(
                        "maNhanVien",
                        "tenNhanVien",
                        "anhDaiDien",
                        "SoDienThoai",
                        "CCCD",
                        "diaChi",
                        "chucVu",
                        "tenTaiKhoan",
                        "matKhau"
                )
        );
        List<NhanVien> list_NhanVien = new ArrayList<>();
        for(Bson x : pipeline) {
            NhanVien t = new NhanVien(
                    x.toBsonDocument().getInt32("maNhanVien").getValue(), 
                    x.toBsonDocument().getString("tenNhanVien").getValue(), 
                    x.toBsonDocument().getBinary("anhDaiDien").getData(), 
                    x.toBsonDocument().getString("SoDienThoai").getValue(), 
                    x.toBsonDocument().getString("CCCD").getValue(), 
                    x.toBsonDocument().getString("diaChi").getValue(), 
                    x.toBsonDocument().getString("chucVu").getValue(), 
                    x.toBsonDocument().getString("tenTaiKhoan").getValue(), 
                    x.toBsonDocument().getString("matKhau").getValue()
            );
            list_NhanVien.add(t);
        }
        
        return list_NhanVien;
    }
    
    public List<NhanVien> timTheoChucVu(String tenChucVu) {
        List<NhanVien> list_NhanVien = getAllNhanVien();
        List<NhanVien> list_daLoc = new ArrayList<>();
        for(NhanVien x: list_NhanVien) {
            if(x.getChucVu().equals(tenChucVu)) {
                list_daLoc.add(x);
            }
        }
        return list_daLoc;
    }
    
    
    public boolean xoaNhanVien(NhanVien nhanVien) {
        try {
            Document doc = new Document()
                    .append("maNhanVien", nhanVien.getMaNhanVien())
                    .append("tenNhanVien", nhanVien.getTenNhanVien())
                    .append("anhDaiDien", nhanVien.getAnhDaiDien())
                    .append("SoDienThoai", nhanVien.getSoDienThoai())
                    .append("CCCD", nhanVien.getCCCD())
                    .append("diaChi", nhanVien.getDiaChi())
                    .append("chucVu", nhanVien.getChucVu())
                    .append("tenTaiKhoan", nhanVien.getTenTaiKhoan())
                    .append("matKhau", nhanVien.getMatKhau());

            DeleteResult result = nhanVienCollection.deleteOne(doc);            
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa nhân viên: " + e.getMessage());
            return false;
        }
    }
    public boolean suaNhanVien(NhanVien oldNV, NhanVien newNV) {
        try {
            Document filter = new Document()
                    .append("maNhanVien", oldNV.getMaNhanVien());
            
            Document newValue = new Document(
                    "$set", 
                    new Document().append("tenNhanVien", newNV.getTenNhanVien())
                                    .append("anhDaiDien", newNV.getAnhDaiDien())
                                    .append("SoDienThoai", newNV.getSoDienThoai())
                                    .append("CCCD", newNV.getCCCD())
                                    .append("diaChi", newNV.getDiaChi())
                                    .append("chucVu", newNV.getChucVu())
                                    .append("tenTaiKhoan", newNV.getTenTaiKhoan())
                                    .append("matKhau", newNV.getMatKhau())
            );
                    
            
            UpdateResult result = nhanVienCollection.updateOne(filter, newValue);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình sửa nhân viên: " + e.getMessage());
            return false;
        }
    }

    public MongoCollection<Document> getCollection() {
        return nhanVienCollection;
    }
    
}
