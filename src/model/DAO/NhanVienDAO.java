package model.DAO;

import GUI.DangNhap_GUI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
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
import java.util.regex.Pattern;
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

            MongoCollection<Document> HoaDonCollection = DangNhap_GUI.database.getCollection("HoaDon");

            Document filter_hd = new Document("NhanVien.maNhanVien", nhanVien.getMaNhanVien());

            Document updateDoc_hd = new Document()
                    .append("NhanVien.maNhanVien", nhanVien.getMaNhanVien())
                    .append("NhanVien.tenNhanVien", nhanVien.getTenNhanVien())
                    .append("NhanVien.anhDaiDien", nhanVien.getAnhDaiDien())
                    .append("NhanVien.SoDienThoai", nhanVien.getSoDienThoai())
                    .append("NhanVien.CCCD", nhanVien.getCCCD())
                    .append("NhanVien.diaChi", nhanVien.getDiaChi())
                    .append("NhanVien.chucVu", nhanVien.getChucVu())
                    .append("NhanVien.tenTaiKhoan", nhanVien.getTenTaiKhoan())
                    .append("NhanVien.matKhau", nhanVien.getMatKhau());

            updateQuery = new Document("$set", updateDoc_hd);
            HoaDonCollection.updateOne(filter_hd, updateQuery);

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
        for (NhanVien x : list_DV) {
            if (x.getMaNhanVien() == maNhanVien) {
                return x;
            }
        }
        return null;
    }

    public NhanVien getNhanVienTheoCCCD(String CCCD) {

        List<NhanVien> list_DV = getAllNhanVien();
        for (NhanVien x : list_DV) {
            if (x.getCCCD().equals(CCCD)) {
                return x;
            }
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
                Aggregates.match(Filters.regex("tenNhanVien", ".*" + tenNV + ".*")),
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
        for (Bson x : pipeline) {
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
        for (NhanVien x : list_NhanVien) {
            if (x.getChucVu().equals(tenChucVu)) {
                list_daLoc.add(x);
            }
        }
        return list_daLoc;
    }

    public List<NhanVien> timNhanVien(String CCCD, String hoTen, String SDT, String diaChi, String chucVu) {

        Document filter = new Document();
        if (!CCCD.equals("")) {
            filter.append("CCCD", CCCD);
        }
        if (!hoTen.equals("")) {
            filter.append("tenNhanVien", hoTen);
        }
        if (!SDT.equals("")) {
            filter.append("SoDienThoai", SDT);
        }
        if (!diaChi.equals("")) {
            filter.append("diaChi", diaChi);
        }
        if (!chucVu.equals("")) {
            filter.append("chucVu", chucVu);
        }

        FindIterable<Document> list_Document = nhanVienCollection.find(filter);

        List<NhanVien> list_NhanVien = new ArrayList<>();
        list_Document.forEach(doc -> {
            list_NhanVien.add(NhanVien.fromDocument(doc));
        });

        return list_NhanVien;
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

    public MongoCollection<Document> getCollection() {
        return nhanVienCollection;
    }

    public boolean doiMatKhau(String tenTaiKhoan, String matKhauCu, String matKhauMoi) {

        // Điều kiện tìm kiếm
        Document filter = new Document("tenTaiKhoan", tenTaiKhoan)
                .append("matKhau", matKhauCu);

        // Dữ liệu cần cập nhật
        Document update = new Document("$set", new Document("matKhau", matKhauMoi));

        // Thực hiện cập nhật
        var result = nhanVienCollection.updateOne(filter, update);

        if (result.getModifiedCount() > 0) {
            System.out.println("Đổi mật khẩu thành công!");
            return true;
        } else {
            System.out.println("Không tìm thấy tài khoản hoặc mật khẩu không đúng.");
            return false;
        }
    }

    public boolean capNhapNhanVien(NhanVien nv) {
        try {
            // In dữ liệu để kiểm tra
            System.out.println("Thông tin nhân viên cần cập nhật: " + nv);

            // Tạo document chứa dữ liệu cập nhật
            Document updatedData = new Document()
                    .append("tenNhanVien", nv.getTenNhanVien())
                    .append("anhDaiDien", nv.getAnhDaiDien())
                    .append("SoDienThoai", nv.getSoDienThoai())
                    .append("CCCD", nv.getCCCD())
                    .append("diaChi", nv.getDiaChi())
                    .append("chucVu", nv.getChucVu());

            // Điều kiện tìm kiếm
            Document filter = new Document("maNhanVien", nv.getMaNhanVien());

            // In bộ lọc kiểm tra
            System.out.println("Bộ lọc tìm kiếm: " + filter.toJson());

            // Kiểm tra nếu tìm thấy tài liệu
            Document foundDoc = nhanVienCollection.find(filter).first();
            System.out.println("Tài liệu tìm thấy: " + foundDoc);

            if (foundDoc == null) {
                System.out.println("Không tìm thấy nhân viên với maNhanVien: " + nv.getMaNhanVien());
                return false;
            }

            // Cập nhật dữ liệu
            UpdateResult result = nhanVienCollection.updateOne(filter, new Document("$set", updatedData));

            if (result.getModifiedCount() > 0) {
                System.out.println("Cập nhật thông tin nhân viên thành công!");
                return true;
            } else {
                System.out.println("Không có dữ liệu nào được thay đổi.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật thông tin nhân viên: " + e.getMessage());
            return false;
        }
    }

}
