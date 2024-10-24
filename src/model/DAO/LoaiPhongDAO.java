package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.LoaiPhong;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import model.DTO.TienNghi;

public class LoaiPhongDAO {

    private MongoCollection<Document> loaiPhongCollection;

    public LoaiPhongDAO(MongoDatabase database) {
        loaiPhongCollection = database.getCollection("LoaiPhong");
    }

    public List<LoaiPhong> getAllLoaiPhong() {
        List<LoaiPhong> loaiPhongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = loaiPhongCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                LoaiPhong loaiPhong = LoaiPhong.fromDocument(doc);
                loaiPhongs.add(loaiPhong);
            }
        }
        return loaiPhongs;
    }

    public LoaiPhong getLoaiPhongByMa(int maLoaiPhong) {
        LoaiPhong loaiPhong = null;
        Document query = new Document("maLoaiPhong", maLoaiPhong);
        try {
            Document doc = loaiPhongCollection.find(query).first();
            if (doc != null) {
                loaiPhong = LoaiPhong.fromDocument(doc); // Chuyển đổi document thành đối tượng LoaiPhong
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return loaiPhong;
    }

    public boolean createLoaiPhong(LoaiPhong loaiPhong) {
        try {
            List<Document> List_TienNghi = new ArrayList<>();
            for (TienNghi tienNghi : loaiPhong.getTienNghis()) {
                List_TienNghi.add(new Document()
                        .append("maTienNghi", tienNghi.getMaTienNghi())
                        .append("tenTienNghi", tienNghi.getTenTienNghi())
                        .append("moTa", tienNghi.getMoTa())
                        .append("hinhAnh", tienNghi.getHinhAnh())
                );
            }
            Document doc = new Document()
                    .append("maLoaiPhong", loaiPhong.getMaLoaiPhong())
                    .append("tenLoaiPhong", loaiPhong.getTenLoaiPhong())
                    .append("dienTich", loaiPhong.getDienTich())
                    .append("donGia", loaiPhong.getDonGia())
                    .append("soKhachToiDa", loaiPhong.getSoKhachToiDa())
                    .append("tienNghis", List_TienNghi)
                    .append("loaiGiuong", loaiPhong.getLoaiGiuong());

            InsertOneResult result = loaiPhongCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo loại phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean updateLoaiPhong(LoaiPhong updatedLoaiPhong) {
        try {
            // Tạo điều kiện để tìm tài liệu cần sửa
            Document query = new Document("maLoaiPhong", updatedLoaiPhong.getMaLoaiPhong());

            // Tạo tài liệu chứa các trường cần cập nhật
            Document update = new Document("$set", new Document()
                    .append("tenLoaiPhong", updatedLoaiPhong.getTenLoaiPhong())
                    .append("dienTich", updatedLoaiPhong.getDienTich())
                    .append("donGia", updatedLoaiPhong.getDonGia())
                    .append("soKhachToiDa", updatedLoaiPhong.getSoKhachToiDa())
                    .append("loaiGiuong", updatedLoaiPhong.getLoaiGiuong())
                    .append("tienNghis", getTienNghisDocuments(updatedLoaiPhong.getTienNghis())));

            // Thực hiện cập nhật
            UpdateResult result = loaiPhongCollection.updateOne(query, update);

            // Kiểm tra xem có tài liệu nào đã được cập nhật không
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình sửa loại phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteLoaiPhong(int maLoaiPhong) {
        try {
            // Tạo điều kiện để tìm tài liệu cần xóa
            Document query = new Document("maLoaiPhong", maLoaiPhong);

            // Thực hiện xóa
            DeleteResult result = loaiPhongCollection.deleteOne(query);

            // Kiểm tra xem có tài liệu nào đã bị xóa không
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa loại phòng: " + e.getMessage());
            return false;
        }
    }

    // Hàm để chuyển đổi danh sách TienNghi thành List<Document>
    private List<Document> getTienNghisDocuments(List<TienNghi> tienNghis) {
        List<Document> listTienNghi = new ArrayList<>();
        for (TienNghi tienNghi : tienNghis) {
            listTienNghi.add(new Document()
                    .append("maTienNghi", tienNghi.getMaTienNghi())
                    .append("tenTienNghi", tienNghi.getTenTienNghi())
                    .append("moTa", tienNghi.getMoTa())
                    .append("hinhAnh", tienNghi.getHinhAnh()));
        }
        return listTienNghi;
    }

    
    
}
