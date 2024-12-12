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
    private ThongTinThanhToan thongTinThanhToan;

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

    public ThongTinThanhToan getThongTinThanhToan() {
        return thongTinThanhToan;
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

    public void setThongTinThanhToan(ThongTinThanhToan thongTinThanhToan) {
        this.thongTinThanhToan = thongTinThanhToan;
    }

    public HoaDon() {
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", tienThanhToan=" + tienThanhToan + ", ngayTaoHoaDon=" + ngayTaoHoaDon + ", nhanVien=" + nhanVien + ", donDatPhong=" + donDatPhong + ", thongTinThanhToan=" + thongTinThanhToan + '}';
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

        if (doc.containsKey("thongTinThanhToan")) {
            Document thongTinDoc = doc.get("thongTinThanhToan", Document.class);
            ThongTinThanhToan thongTinThanhToan = ThongTinThanhToan.fromDocument(thongTinDoc);
            hoaDon.setThongTinThanhToan(thongTinThanhToan);
        }

        return hoaDon;
    }

}
