package model.DAO;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.elemMatch;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.computed;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import com.mongodb.client.model.UpdateOptions;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.DTO.DonDatPhong;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.DTO.DichVu;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.KhachHang;
import model.DTO.PhongEmbed;
import org.bson.conversions.Bson;

public class DonDatPhongDAO {

    private MongoCollection<Document> donDatPhongCollection;

    public DonDatPhongDAO(MongoDatabase database) {
        donDatPhongCollection = database.getCollection("DonDatPhong");
    }

    public void updateNguoiO(int maDonDat, List<KhachHang> nguoiOMoi) {

        // Tạo điều kiện tìm kiếm
        Bson filter = eq("maDonDat", maDonDat);

        // Tạo bản cập nhật
        Bson update = set("nguoiO", convertToDocumentList(nguoiOMoi));

        // Cập nhật document
        donDatPhongCollection.updateOne(filter, update);

    }

    public List<Document> convertToDocumentList(List<KhachHang> khachHangList) {
        List<Document> documentList = new ArrayList<>();
        for (KhachHang kh : khachHangList) {
            Document doc = new Document("maKhachHang", kh.getMaKhachHang())
                    .append("HoTen", kh.getTenKhachHang())
                    .append("SDT", kh.getSoDienThoai())
                    .append("CCCD", kh.getCCCD())
                    .append("GioiTinh", kh.getGioiTinh())
                    .append("Email", kh.getEmail())
                    .append("QuocTich", kh.getQuocTich());
            documentList.add(doc);
        }
        return documentList;
    }

    public boolean kiemTraTenLoaiPhongDangSuDung(String tenLoaiPhong) {

        // Tạo điều kiện tìm kiếm
        Bson filter = elemMatch("phong", and(
                eq("tenLoaiPhong", tenLoaiPhong),
                in("trangThaiPhong", "Đang ở", "Đang chờ")
        ));

        // Tìm document phù hợp
        Document result = donDatPhongCollection.find(filter).first();

        return result != null;

    }

    public boolean kiemTraPhongDangSuDung(int maPhong) {

        // Tạo điều kiện tìm kiếm
        Bson filter = elemMatch("phong", and(
                eq("maPhong", maPhong),
                in("trangThaiPhong", "Đang ở", "Đang chờ")
        ));

        // Tìm document phù hợp
        Document result = donDatPhongCollection.find(filter).first();

        return result != null;

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

    public DonDatPhong getLastDonDatPhong() {
        try (MongoCursor<Document> cursor = donDatPhongCollection
                .find()
                .sort(new Document("_id", -1)) // Sắp xếp giảm dần theo _id
                .limit(1) // Giới hạn kết quả chỉ 1 bản ghi
                .iterator()) {
            if (cursor.hasNext()) {
                Document doc = cursor.next();
                return DonDatPhong.fromDocument(doc); // Chuyển đổi Document thành đối tượng DonDatPhong
            }
        }
        return null; // Trả về null nếu không có bản ghi nào
    }

    public List<DonDatPhong> getDonDatPhongTheoTrangThaiOVaCho() {
        List<DonDatPhong> donDatPhongs = new ArrayList<>();
        // Danh sách trạng thái cần tìm
        List<String> trangThaiTimKiem = Arrays.asList("Đang ở", "Đang chờ");

        try (MongoCursor<Document> cursor = donDatPhongCollection.find(new Document("trangThai", new Document("$in", trangThaiTimKiem))).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DonDatPhong donDatPhong = DonDatPhong.fromDocument(doc);
                donDatPhongs.add(donDatPhong);
            }
        }
        return donDatPhongs;
    }

    public List<DonDatPhong> getDonDatPhongTheoTrangThaiO() {
        List<DonDatPhong> donDatPhongs = new ArrayList<>();
        // Trạng thái cần tìm
        String trangThaiTimKiem = "Đang ở";

        try (MongoCursor<Document> cursor = donDatPhongCollection.find(new Document("trangThai", trangThaiTimKiem)).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DonDatPhong donDatPhong = DonDatPhong.fromDocument(doc);
                donDatPhongs.add(donDatPhong);
            }
        }
        return donDatPhongs;
    }

    public DonDatPhong getDonDatPhongByMa(int maDon) {
        DonDatPhong donDatPhong = null;
        Document query = new Document("maDonDat", maDon);
        try {
            Document doc = donDatPhongCollection.find(query).first();
            if (doc != null) {
                donDatPhong = DonDatPhong.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return donDatPhong;
    }

    public ArrayList<Document> list_DichVuPhong(PhongEmbed phong) {
        ArrayList<Document> list_DichVu = new ArrayList<Document>();
        for (DichVuSuDungEmbed dichVu : phong.getDichVuSuDung()) {
            list_DichVu.add(
                    new Document()
                            .append("maDVSD", dichVu.getMaDVSD())
                            .append("tenDV", dichVu.getTenDV())
                            .append("soLuong", dichVu.getSoLuong())
                            .append("donGia", dichVu.getDonGia())
            );
        }
        return list_DichVu;
    }

    public boolean updatePhongTrongDonDatPhong(int maDonDat, PhongEmbed phongUpdate) {
        try {
            // Tìm kiếm đơn đặt phòng dựa trên mã đơn đặt phòng
            Document filter = new Document("maDonDat", maDonDat);

            // Định nghĩa điều kiện và hành động cập nhật
            Document updateQuery = new Document(
                    "$set", new Document("phong.$[ph].donGia", phongUpdate.getDonGia())
                            .append("phong.$[ph].tenLoaiPhong", phongUpdate.getTenLoaiPhong())
                            .append("phong.$[ph].dichVuSuDung", list_DichVuPhong(phongUpdate))
                            .append("phong.$[ph].ngayNhanPhongDuKien", phongUpdate.getNgayNhanPhongDuKien())
                            .append("phong.$[ph].ngayTraPhongDuKien", phongUpdate.getNgayTraPhongDuKien())
                            .append("phong.$[ph].ngayNhanPhong", phongUpdate.getNgayNhanPhong())
                            .append("phong.$[ph].ngayTraPhong", phongUpdate.getNgayTraPhong())
                            .append("phong.$[ph].trangThaiPhong", phongUpdate.getTrangThaiPhong())
                            .append("phong.$[ph].tienDaThanhToan", phongUpdate.getTienDaThanhToan())
            );

            // Cấu hình arrayFilters để cập nhật phần tử phù hợp trong mảng "phong"
            Document arrayFilter = new Document("ph.maPhong", phongUpdate.getMaPhong());
            UpdateOptions options = new UpdateOptions().arrayFilters(Collections.singletonList(arrayFilter));

            // Thực hiện cập nhật
            UpdateResult result = donDatPhongCollection.updateOne(filter, updateQuery, options);

            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình cập nhật phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean createDonDatPhong(DonDatPhong donDatPhong) {
        try {

            ArrayList<Document> list_KhachHang = new ArrayList<Document>();
            for (KhachHang khachHang : donDatPhong.getKhachO()) {
                list_KhachHang.add(new Document()
                        .append("maKhachHang", khachHang.getMaKhachHang())
                        .append("HoTen", khachHang.getTenKhachHang())
                        .append("SDT", khachHang.getSoDienThoai())
                        .append("CCCD", khachHang.getCCCD())
                        .append("GioiTinh", khachHang.getGioiTinh())
                        .append("Email", khachHang.getEmail())
                        .append("QuocTich", khachHang.getQuocTich())
                );
            }

            ArrayList<Document> list_phong = new ArrayList<Document>();
            for (PhongEmbed phong : donDatPhong.getPhongs()) {
                list_phong.add(
                        new Document()
                                .append("maPhong", phong.getMaPhong())
                                .append("donGia", phong.getDonGia())
                                .append("tenLoaiPhong", phong.getTenLoaiPhong())
                                .append("dichVuSuDung", list_DichVuPhong(phong))
                                .append("ngayNhanPhongDuKien", phong.getNgayNhanPhongDuKien())
                                .append("ngayTraPhongDuKien", phong.getNgayTraPhongDuKien())
                                .append("ngayNhanPhong", phong.getNgayNhanPhong())
                                .append("ngayTraPhong", phong.getNgayTraPhong())
                                .append("trangThaiPhong", phong.getTrangThaiPhong())
                                .append("tienDaThanhToan", phong.getTienDaThanhToan())
                );
            }

            Document doc = new Document()
                    .append("maDonDat", donDatPhong.getMaDonDat())
                    .append("ngayTaoDon", donDatPhong.getNgayTaoDon())
                    .append("trangThai", donDatPhong.getTrangThai())
                    .append("nguoiDat", new Document()
                            .append("maKhachHang", donDatPhong.getNguoiDat().getMaKhachHang())
                            .append("HoTen", donDatPhong.getNguoiDat().getTenKhachHang())
                            .append("SDT", donDatPhong.getNguoiDat().getSoDienThoai())
                            .append("CCCD", donDatPhong.getNguoiDat().getCCCD())
                            .append("GioiTinh", donDatPhong.getNguoiDat().getGioiTinh())
                            .append("Email", donDatPhong.getNguoiDat().getEmail())
                            .append("QuocTich", donDatPhong.getNguoiDat().getQuocTich())
                    )
                    .append("nguoiO", list_KhachHang)
                    .append("phong", list_phong
                    );

            InsertOneResult result = donDatPhongCollection.insertOne(doc);
            return result.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo đơn đặt phòng: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Document> getDoanhThu(Date ngayBatDau, Date ngayKetThuc) {
        // Tạo pipeline cho aggregate
        List<Bson> pipeline = Arrays.asList(
                // Gộp các điều kiện lọc vào một match duy nhất
                match(and(
                        eq("trangThai", "Hoàn thành"),
                        or(
                                and(
                                        gte("phong.ngayNhanPhong", ngayBatDau),
                                        lte("phong.ngayNhanPhong", ngayKetThuc)
                                ),
                                and(
                                        gte("phong.ngayTraPhong", ngayBatDau),
                                        lte("phong.ngayTraPhong", ngayKetThuc)
                                )
                        )
                ))
        );

        // Thực thi aggregate
        AggregateIterable<Document> results = donDatPhongCollection.aggregate(pipeline);

        // Chuyển kết quả thành ArrayList
        ArrayList<Document> documents = new ArrayList<>();
        results.into(documents);

        return documents;
    }

    public boolean updateTienTra(int maDonDat, int maPhong, int tienDaThanhToan) {
        try {
            UpdateResult result = donDatPhongCollection.updateOne(
                    and(
                            eq("maDonDat", maDonDat),
                            eq("phong.maPhong", maPhong)
                    ),
                    set("phong.$.tienDaThanhToan", tienDaThanhToan)
            );

            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật tiền trả: " + e.getMessage());
            return false;
        }
    }

    public PhongEmbed getPhongTheoMaPhong(int maDonDat, int maPhong) {
        try {
            // Tìm đơn đặt phòng dựa vào mã đơn đặt phòng
            Document donDatPhong = donDatPhongCollection.find(eq("maDonDat", maDonDat)).first();

            if (donDatPhong != null) {
                // Lấy danh sách phòng từ document "donDatPhong"
                List<Document> danhSachPhong = donDatPhong.getList("phong", Document.class);

                // Duyệt qua danh sách phòng để tìm phòng có mã phòng khớp
                for (Document phong : danhSachPhong) {
                    if (phong.getInteger("maPhong") == maPhong) {
                        return PhongEmbed.fromDocument(phong);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy thông tin phòng: " + e.getMessage());
        }

        // Trả về null nếu không tìm thấy phòng hoặc có lỗi xảy ra
        return null;
    }

    public boolean updateTrangThaiDon(int maDonDat, String trangThai) {
        try {
            UpdateResult result = donDatPhongCollection.updateOne(
                    eq("maDonDat", maDonDat),
                    set("trangThai", trangThai)
            );

            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái hoàn thành: " + e.getMessage());
            return false;
        }
    }

    public boolean updateTrangThaiPhongtheoDon(int maDonDat, String trangThaiPhong) {
        try {
            UpdateResult result = donDatPhongCollection.updateOne(
                    eq("maDonDat", maDonDat),
                    set("phong.$[].trangThaiPhong", trangThaiPhong) // $[] áp dụng cho tất cả các phần tử trong mảng "phong"
            );

            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean updateTrangThaiPhong(int maDonDatPhong, int maPhong, String trangThaiPhong) {
        try {
            // Cập nhật trạng thái phòng trong danh sách phòng của đơn đặt phòng
            UpdateResult result = donDatPhongCollection.updateOne(
                    and(eq("maDonDat", maDonDatPhong), elemMatch("phong", eq("maPhong", maPhong))), // Tìm đơn và phòng tương ứng
                    set("phong.$.trangThaiPhong", trangThaiPhong) // Cập nhật trạng thái của phòng đó
            );

            // Kiểm tra xem số bản ghi được chỉnh sửa có lớn hơn 0
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean updateNgayNhanPhong(int maDonDatPhong, int maPhong, Date ngayNhanPhong) {
        try {
            // Cập nhật ngày nhận phòng trong danh sách phòng của đơn đặt phòng
            UpdateResult result = donDatPhongCollection.updateOne(
                    and(eq("maDonDat", maDonDatPhong), elemMatch("phong", eq("maPhong", maPhong))), // Tìm đơn và phòng tương ứng
                    set("phong.$.ngayNhanPhong", ngayNhanPhong) // Cập nhật ngày nhận phòng của phòng đó
            );

            // Kiểm tra xem số bản ghi được chỉnh sửa có lớn hơn 0
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật ngày nhận phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean updateNgayTraPhong(int maDonDatPhong, int maPhong, Date ngayTraPhong) {
        try {
            // Cập nhật ngày nhận phòng trong danh sách phòng của đơn đặt phòng
            UpdateResult result = donDatPhongCollection.updateOne(
                    and(eq("maDonDat", maDonDatPhong), elemMatch("phong", eq("maPhong", maPhong))), // Tìm đơn và phòng tương ứng
                    set("phong.$.ngayTraPhong", ngayTraPhong) // Cập nhật ngày nhận phòng của phòng đó
            );

            // Kiểm tra xem số bản ghi được chỉnh sửa có lớn hơn 0
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật ngày nhận phòng: " + e.getMessage());
            return false;
        }
    }

    public boolean updateNgayTaoDon(int maDonDat, Date ngayTao) {
        try {
            UpdateResult result = donDatPhongCollection.updateOne(
                    eq("maDonDat", maDonDat),
                    set("ngayTaoDon", ngayTao)
            );

            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái hoàn thành: " + e.getMessage());
            return false;
        }
    }

    public List<Integer> getYearsByTrangThaiHoanThanh() {
        List<Integer> years = new ArrayList<>();

        // Sử dụng Aggregation Framework đúng cách
        List<Document> aggregationPipeline = donDatPhongCollection.aggregate(
                List.of(
                        match(eq("trangThai", "Hoàn thành")),
                        project(new Document("year", new Document("$year", "$ngayTaoDon"))),
                        group(new Document("_id", "$year"), first("year", "$year")),
                        sort(ascending("_id"))
                )
        ).into(new ArrayList<>());

        // Lấy các năm từ kết quả trả về
        for (Document doc : aggregationPipeline) {
            years.add(doc.getInteger("year"));
        }

        return years;
    }
}
