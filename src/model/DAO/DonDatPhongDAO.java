package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import model.DTO.DonDatPhong;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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
            Document doc = new Document()
                    .append("maDonDat", donDatPhong.getMaDonDat())
                    .append("ngayDatPhong", donDatPhong.getNgayDatPhong())
                    .append("ngayNhanPhong", donDatPhong.getNgayNhanPhong())
                    .append("ngayTraPhong", donDatPhong.getNgayTraPhong())
                    .append("trangThai", donDatPhong.getTrangThai())
                    .append("KhachO", donDatPhong.getKhachO())
                    .append("dichVuSuDung", donDatPhong.getDichVuSuDung())
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