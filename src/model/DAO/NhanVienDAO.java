package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import model.DTO.NhanVien;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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
    public NhanVien timTheoMaNhanVien(int maNhanVien) {
        NhanVien x = new NhanVien(maNhanVien);
        List<NhanVien> list_DV = getAllNhanVien();
        if(!list_DV.contains(x)) {
            return null;
        }
        return list_DV.get(list_DV.indexOf(x));
    }
    
    public List<NhanVien> timTheoTenNV(String tenNV) {
        List<NhanVien> list_NhanVien = getAllNhanVien();
        List<NhanVien> list_daLoc = new ArrayList<>();
        for(NhanVien x: list_NhanVien) {
            if(x.getTenNhanVien().equals(tenNV)) {
                list_daLoc.add(x);
            }
        }
        return list_daLoc;
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
    
}
