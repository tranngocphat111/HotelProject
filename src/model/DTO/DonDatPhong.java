package model.DTO;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonDatPhong {
    private int maDonDat;
    private Date ngayDatPhong;
    private Date ngayNhanPhong;
    private Date ngayTraPhong;
    private String trangThai;
    private List<KhachHang> khachO;
    private List<DichVu> dichVuSuDung;
    private int phong;
    private int hoaDon;

    public DonDatPhong(int maDonDat, Date ngayDatPhong, Date ngayNhanPhong, Date ngayTraPhong, String trangThai, List<KhachHang> khachO, List<DichVu> dichVuSuDung, int phong, int hoaDon) {
        this.maDonDat = maDonDat;
        this.ngayDatPhong = ngayDatPhong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.trangThai = trangThai;
        this.khachO = khachO;
        this.dichVuSuDung = dichVuSuDung;
        this.phong = phong;
        this.hoaDon = hoaDon;
    }

    public DonDatPhong() {
    }

    public int getMaDonDat() {
        return maDonDat;
    }

    public void setMaDonDat(int maDonDat) {
        this.maDonDat = maDonDat;
    }

    public Date getNgayDatPhong() {
        return ngayDatPhong;
    }

    public void setNgayDatPhong(Date ngayDatPhong) {
        this.ngayDatPhong = ngayDatPhong;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public void setNgayNhanPhong(Date ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(Date ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<KhachHang> getKhachO() {
        return khachO;
    }

    public void setKhachO(List<KhachHang> khachO) {
        this.khachO = khachO;
    }

    public List<DichVu> getDichVuSuDung() {
        return dichVuSuDung;
    }

    public void setDichVuSuDung(List<DichVu> dichVuSuDung) {
        this.dichVuSuDung = dichVuSuDung;
    }

    public int getPhong() {
        return phong;
    }

    public void setPhong(int phong) {
        this.phong = phong;
    }

    public int getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(int hoaDon) {
        this.hoaDon = hoaDon;
    }

    public static DonDatPhong fromDocument(Document doc) {
        DonDatPhong donDatPhong = new DonDatPhong();

        if (doc.containsKey("maDonDat")) {
            donDatPhong.setMaDonDat(doc.getInteger("maDonDat"));
        }
        if (doc.containsKey("ngayDatPhong")) {
            donDatPhong.setNgayDatPhong(doc.getDate("ngayDatPhong"));
        }
        if (doc.containsKey("ngayNhanPhong")) {
            donDatPhong.setNgayNhanPhong(doc.getDate("ngayNhanPhong"));
        }
        if (doc.containsKey("ngayTraPhong")) {
            donDatPhong.setNgayTraPhong(doc.getDate("ngayTraPhong"));
        }
        if (doc.containsKey("trangThai")) {
            donDatPhong.setTrangThai(doc.getString("trangThai"));
        }

        // Convert KhachO array
        if (doc.containsKey("KhachO")) {
            List<Document> khachODocs = (List<Document>) doc.get("KhachO");
            List<KhachHang> khachO = new ArrayList<>();
            for (Document khachODoc : khachODocs) {
                KhachHang khachHang = KhachHang.fromDocument(khachODoc);
                khachO.add(khachHang);
            }
            donDatPhong.setKhachO(khachO);
        }

        // Convert DichVuSuDung array
        if (doc.containsKey("dichVuSuDung")) {
            List<Document> dichVuDocs = (List<Document>) doc.get("dichVuSuDung");
            List<DichVu> dichVuSuDung = new ArrayList<>();
            for (Document dichVuDoc : dichVuDocs) {
                DichVu dichVu = DichVu.fromDocument(dichVuDoc);
                dichVuSuDung.add(dichVu);
            }
            donDatPhong.setDichVuSuDung(dichVuSuDung);
        }

        if (doc.containsKey("Phong")) {
            donDatPhong.setPhong(doc.getInteger("Phong"));
        }
        if (doc.containsKey("HoaDon")) {
            donDatPhong.setHoaDon(doc.getInteger("HoaDon"));
        }

        return donDatPhong;
    }

    @Override
    public String toString() {
        return "DonDatPhong{" +
                "maDonDat=" + maDonDat +
                ", ngayDatPhong=" + ngayDatPhong +
                ", ngayNhanPhong=" + ngayNhanPhong +
                ", ngayTraPhong=" + ngayTraPhong +
                ", trangThai=" + trangThai +
                ", khachO=" + khachO +
                ", dichVuSuDung=" + dichVuSuDung +
                ", phong=" + phong +
                ", hoaDon=" + hoaDon +
                '}';
    }
}
