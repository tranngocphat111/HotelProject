package model.DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.TienNghi;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import model.DTO.LoaiPhong;
import model.MongoDBConnection;
import org.bson.types.Binary;
import GUI.DangNhap_GUI;
import static GUI.DangNhap_GUI.database;
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
                    .append("moTa", tienNghi.getMoTa())
                    .append("hinhAnh", tienNghi.getHinhAnh());

            InsertOneResult result = tienNghiCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo tiện nghi: " + e.getMessage());
            return false;
        }
    }

    public TienNghi getTienNghiByMa(int maTienNghi) {
        try {
            // Tạo một bộ lọc để tìm kiếm theo maTienNghi
            Document query = new Document("maTienNghi", maTienNghi);

            // Thực hiện tìm kiếm
            Document doc = tienNghiCollection.find(query).first();

            // Nếu không tìm thấy, trả về null
            if (doc == null) {
                return null;
            }

            // Tạo đối tượng TienNghi từ kết quả tìm kiếm
            TienNghi tienNghi = new TienNghi();
            tienNghi.setMaTienNghi(doc.getInteger("maTienNghi"));
            tienNghi.setTenTienNghi(doc.getString("tenTienNghi"));
            tienNghi.setMoTa(doc.getString("moTa"));

            Binary binaryData = doc.get("hinhAnh", Binary.class);
            byte[] imageData = binaryData.getData();
            tienNghi.setHinhAnh(imageData);

            return tienNghi; // Trả về đối tượng tiện nghi tìm được
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tìm tiện nghi: " + e.getMessage());
            return null;
        }
    }

    public TienNghi timTienNghi(String tenTienNghi) {
        TienNghi x = new TienNghi(tenTienNghi);
        List<TienNghi> list_TienNghi = getAllTienNghi();
        if (!list_TienNghi.contains(x)) {
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
            
            LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO(database);
            
            for(LoaiPhong x : loaiPhongDAO.getAllLoaiPhong()) {
                x.getTienNghis().remove(tienNghi);
                loaiPhongDAO.updateLoaiPhong(x);
                
            }
            
            
            
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
            
            
            LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO(database);
            
            for(LoaiPhong x : loaiPhongDAO.getAllLoaiPhong()) {
                x.getTienNghis().set(x.getTienNghis().indexOf(oldTienNghi), newTienNghi);
                loaiPhongDAO.updateLoaiPhong(x);
                
            }
            
            
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa tiện nghi: " + e.getMessage());
            return false;
        }
    }

    public List<TienNghi> SortTienNghiTheoMa() {
        List<TienNghi> danhSachTienNghi = new ArrayList<>();

        try {
            // Lấy tất cả tài liệu và sắp xếp theo mã tiện nghi
            FindIterable<Document> documents = tienNghiCollection.find()
                    .sort(Sorts.ascending("maTienNghi")); // Sắp xếp theo mã tiện nghi

            for (Document doc : documents) {
                TienNghi tienNghi = new TienNghi();
                tienNghi.setMaTienNghi(doc.getInteger("maTienNghi"));
                tienNghi.setTenTienNghi(doc.getString("tenTienNghi"));
                tienNghi.setMoTa(doc.getString("moTa"));
                Binary binaryData = doc.get("hinhAnh", Binary.class);
                byte[] imageData = binaryData.getData();
                tienNghi.setHinhAnh(imageData);

                danhSachTienNghi.add(tienNghi);
            }
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình lấy danh sách tiện nghi: " + e.getMessage());
        }

        return danhSachTienNghi;
    }

    public static void main(String[] args) {
        MongoDBConnection model = new MongoDBConnection();
        TienNghiDAO tienNghiDAO = new TienNghiDAO(model.getDatabase());

        TienNghi x = tienNghiDAO.timTienNghi("Tủ lạnh");
        System.err.println(x.toString());
    }
}
