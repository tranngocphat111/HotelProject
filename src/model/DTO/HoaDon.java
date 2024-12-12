package model.DTO;

import java.util.ArrayList;
import org.bson.Document;
import java.util.Date;
import java.util.List;

public class HoaDon {

    private int maHoaDon;
    private int tienThanhToan;
    private Date ngayTaoHoaDon;
    private NhanVienEmbed nhanVien;
    private int donDatPhong;
    private List<ThongTinThanhToan> thongTinThanhToans;

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public int getTienThanhToan() {
        return tienThanhToan;
    }

    public Date getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public NhanVienEmbed getNhanVien() {
        return nhanVien;
    }

    public int getDonDatPhong() {
        return donDatPhong;
    }

    public List<ThongTinThanhToan> getThongTinThanhToans() {
        return thongTinThanhToans;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setTienThanhToan(int tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }

    public void setNgayTaoHoaDon(Date ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public void setNhanVien(NhanVienEmbed nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setDonDatPhong(int donDatPhong) {
        this.donDatPhong = donDatPhong;
    }

    public void setThongTinThanhToans(List<ThongTinThanhToan> thongTinThanhToans) {
        this.thongTinThanhToans = thongTinThanhToans;
    }

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int tienThanhToan, Date ngayTaoHoaDon, NhanVienEmbed nhanVien, int donDatPhong, List<ThongTinThanhToan> thongTinThanhToans) {
        this.maHoaDon = maHoaDon;
        this.tienThanhToan = tienThanhToan;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.nhanVien = nhanVien;
        this.donDatPhong = donDatPhong;
        this.thongTinThanhToans = thongTinThanhToans;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", tienThanhToan=" + tienThanhToan + ", ngayTaoHoaDon=" + ngayTaoHoaDon + ", nhanVien=" + nhanVien + ", donDatPhong=" + donDatPhong + ", thongTinThanhToans=" + thongTinThanhToans + '}';
    }
    
    
    public static HoaDon fromDocument(Document doc) {
        HoaDon hoaDon = new HoaDon();

        if (doc.containsKey("maHoaDon")) {
            hoaDon.setMaHoaDon(doc.getInteger("maHoaDon"));
        }

        if (doc.containsKey("tienThanhToan")) {
            hoaDon.setTienThanhToan(doc.getInteger("tienThanhToan"));
        }

        if (doc.containsKey("ngayTaoHoaDon")) {
            hoaDon.setNgayTaoHoaDon(doc.getDate("ngayTaoHoaDon"));
        }

        if (doc.containsKey("nhanVien")) {
            Document nhanVienDoc = doc.get("nhanVien", Document.class);
            hoaDon.setNhanVien(NhanVienEmbed.fromDocument(nhanVienDoc));
        }

        if (doc.containsKey("donDatPhong")) {
            hoaDon.setDonDatPhong(doc.getInteger("donDatPhong"));
        }

        if (doc.containsKey("thongTinThanhToans")) {
            List<Document> thongTinDocs = doc.getList("thongTinThanhToans", Document.class);
            List<ThongTinThanhToan> thongTinThanhToans = new ArrayList<>();
            for (Document thongTinDoc : thongTinDocs) {
                thongTinThanhToans.add(ThongTinThanhToan.fromDocument(thongTinDoc));
            }
            hoaDon.setThongTinThanhToans(thongTinThanhToans);
        }

        return hoaDon;
    }
    
    

   

    

}
