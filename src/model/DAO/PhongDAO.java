package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.Phong;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.model.Filters;
import java.util.Date;

public class PhongDAO {

    private MongoCollection<Document> phongCollection;
    private MongoCollection<Document> donDatPhongCollection;

    public PhongDAO(MongoDatabase database) {
        phongCollection = database.getCollection("Phong");
        donDatPhongCollection = database.getCollection("DonDatPhong");
    }

    public List<Phong> getAllPhong() {
        List<Phong> Phongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = phongCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Phong phong = Phong.fromDocument(doc);
                Phongs.add(phong);
            }
        }
        return Phongs;
    }
    
    public List<Phong> getAllPhongTrongTheoNgay(Date NgayNhanPhongDuKien, Date NgayTraPhongDuKien) {
        List<Phong> phongTrong = new ArrayList<>();
        
        // 1. Lấy danh sách mã phòng đã được đặt trong khoảng ngày
        List<Integer> phongDaDat = new ArrayList<>();

        // Viết câu lệnh MongoDB với logic $or theo 2 điều kiện như bạn yêu cầu
        donDatPhongCollection.find(
                Filters.or(
                        Filters.and(
                                Filters.gte("phong.ngayNhanPhongDuKien", NgayNhanPhongDuKien),
                                Filters.lte("phong.ngayTraPhongDuKien", NgayNhanPhongDuKien)
                        ),
                        Filters.and(
                                Filters.gte("phong.ngayNhanPhongDuKien", NgayTraPhongDuKien),
                                Filters.lte("phong.ngayTraPhongDuKien", NgayTraPhongDuKien)
                        )
                )
        ).forEach((Document donDatPhong) -> {
            List<Document> phongList = (List<Document>) donDatPhong.get("phong");
            if (phongList != null) {
                for (Document phong : phongList) {
                    phongDaDat.add(phong.getInteger("maPhong"));
                }
            }
        });

        for (Integer sophong : phongDaDat) {
            System.out.println(sophong);
        }

        // 2. Lọc các phòng không nằm trong danh sách đã đặt
        phongCollection.find().forEach((Document phongDoc) -> {
            int maPhong = phongDoc.getInteger("maPhong");
            if (!phongDaDat.contains(maPhong)) {
                phongTrong.add(Phong.fromDocument(phongDoc));
            }
        });

        return phongTrong;
    }



    public Phong getPhongByMa(int maPhong) {
        Phong phong = null;
        Document query = new Document("maPhong", maPhong);
        try {
            Document doc = phongCollection.find(query).first();
            if (doc != null) {
                phong = Phong.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return phong;
    }

    public boolean createPhong(Phong phong) {
        try {
            Document doc = new Document()
                    .append("maPhong", phong.getMaPhong())
                    .append("tang", phong.getTang())
                    .append("loaiPhong", phong.getLoaiPhong())
                    .append("moTa", phong.getMoTa());

            InsertOneResult result = phongCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePhong(int maPhong) {
        try {
            // Tạo điều kiện để tìm tài liệu cần xóa
            Document query = new Document("maPhong", maPhong);

            // Thực hiện xóa
            DeleteResult result = phongCollection.deleteOne(query);

            // Kiểm tra xem có tài liệu nào đã bị xóa không
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình xóa phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePhong(Phong updatedPhong) {
        try {
            // Tạo điều kiện để tìm tài liệu cần sửa
            Document query = new Document("maPhong", updatedPhong.getMaPhong());

            // Tạo tài liệu chứa các trường cần cập nhật
            Document update = new Document("$set", new Document()
                    .append("tang", updatedPhong.getTang())
                    .append("loaiPhong", updatedPhong.getLoaiPhong())
                    .append("moTa", updatedPhong.getMoTa()));

            // Thực hiện cập nhật
            UpdateResult result = phongCollection.updateOne(query, update);

            // Kiểm tra xem có tài liệu nào đã được cập nhật không
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình sửa phòng: " + e.getMessage());
            return false;
        }
    }

    public List<Phong> getAllPhongsSortByMaPhong() {
        List<Phong> phongs = new ArrayList<>();
        try (MongoCursor<Document> cursor = phongCollection.find()
                .sort(Sorts.ascending("maPhong"))
                .iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Phong phong = Phong.fromDocument(document);
                phongs.add(phong);
            }
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình lấy danh sách phòng: " + e.getMessage());
        }
        return phongs;
    }

}
