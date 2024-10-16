package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.DonDatPhong;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.DTO.DichVu;
import model.DTO.KhachHang;

public class DonDatPhongDAO {
    private MongoCollection<Document> donDatPhongCollection;

    public DonDatPhongDAO(MongoDatabase database) {
        donDatPhongCollection = database.getCollection("DonDatPhong");
    }

    public List<DonDatPhong> getAllDonDatPhong() {
        List<DonDatPhong> donDatPhongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = donDatPhongCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DonDatPhong donDatPhong = DonDatPhong.fromDocument(doc);
                donDatPhongs.add(donDatPhong);
            }
        }
        return donDatPhongs;
    }
    
    public boolean createDonDatPhong(DonDatPhong donDatPhong) {
        try {
            
            ArrayList<Document>  list_KhachHang = new ArrayList<Document>();
            for(KhachHang khachHang : donDatPhong.getKhachO()){
                list_KhachHang.add(new Document()
                    .append("maKhachHang", khachHang.getMaKhachHang())
                    .append("tenKhachHang", khachHang.getTenKhachHang())
                    .append("soDienThoai", khachHang.getSoDienThoai())
                    .append("CCCD", khachHang.getCCCD())
                    .append("gioiTinh", khachHang.getGioiTinh())
                    .append("email", khachHang.getEmail())
                    .append("quocTich", khachHang.getQuocTich())
                );
            }
            
            ArrayList<Document>  list_DichVu = new ArrayList<Document>();
            for(DichVu dichVu : donDatPhong.getDichVuSuDung()){
                list_DichVu.add(
                        new Document()
                    .append("maDV", dichVu.getMaDV())
                    .append("tenDV", dichVu.getTenDV())
                    .append("moTa", dichVu.getMoTa())
                    .append("donGia", dichVu.getDonGia())
                );
            }    
                
            
            Document doc = new Document()
                    .append("maDonDat", donDatPhong.getMaDonDat())
                    .append("ngayDatPhong", donDatPhong.getNgayDatPhong())
                    .append("ngayNhanPhong", donDatPhong.getNgayNhanPhong())
                    .append("ngayTraPhong", donDatPhong.getNgayTraPhong())
                    .append("trangThai", donDatPhong.getTrangThai())
                    .append("KhachO", list_KhachHang)
                    .append("dichVuSuDung", list_DichVu)
                    .append("Phong", donDatPhong.getPhong())
                    .append("HoaDon", donDatPhong.getHoaDon());

            InsertOneResult result = donDatPhongCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo đơn đặt phòng: " + e.getMessage());
            return false;
        }
    }
}