package model.DTO;

import java.util.ArrayList;
import org.bson.Document;
import java.util.Date;
import java.util.List;

public class HoaDon {

    private int maHoaDon;
    private int tongTien;
    private Date ngayTaoHoaDon;
    private NhanVienEmbed nhanVien;
    private boolean trangThai;
    private int donDatPhongs;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int tongTien, Date ngayTaoHoaDon, NhanVienEmbed nhanVien, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.nhanVien = nhanVien;
        this.trangThai = trangThai;
    }

    public int getDonDatPhongs() {
        return donDatPhongs;
    }

    public void setDonDatPhongs(int donDatPhongs) {
        this.donDatPhongs = donDatPhongs;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTaoHoaDon() {
        return ngayTaoHoaDon;
    }

    public void setNgayTaoHoaDon(Date ngayTaoHoaDon) {
        this.ngayTaoHoaDon = ngayTaoHoaDon;
    }

    public NhanVienEmbed getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVienEmbed nhanVien) {
        this.nhanVien = nhanVien;
    }

    public static HoaDon fromDocument(Document doc) {
        HoaDon hoaDon = new HoaDon();

        if (doc.containsKey("maHoaDon")) {
            hoaDon.setMaHoaDon(doc.getInteger("maHoaDon"));
        }
        if (doc.containsKey("tongTien")) {
            hoaDon.setTongTien(doc.getInteger("tongTien"));
        }
        if (doc.containsKey("ngayTaoHoaDon")) {
            hoaDon.setNgayTaoHoaDon(doc.getDate("ngayTaoHoaDon"));
        }

        // Convert NhanVien subdocument
        if (doc.containsKey("NhanVien")) {
            Document nhanVienDoc = (Document) doc.get("NhanVien");
            NhanVienEmbed nhanVien = NhanVienEmbed.fromDocument(nhanVienDoc);
            hoaDon.setNhanVien(nhanVien);
        }

        if (doc.containsKey("DonDatPhong")) {
            int DonDatPhongsdoc = (int) doc.get("DonDatPhong");
            hoaDon.setDonDatPhongs(DonDatPhongsdoc); 
        }

        if (doc.containsKey("trangThai")) {
            hoaDon.setTrangThai(doc.getBoolean("trangThai"));
        }

        return hoaDon;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", tongTien=" + tongTien + ", ngayTaoHoaDon=" + ngayTaoHoaDon + ", nhanVien=" + nhanVien + ", trangThai=" + trangThai + '}';
    }

}
