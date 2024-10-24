package model.DTO;

import org.bson.Document;

import java.util.Date;

public class KhuyenMai {
    private int maKhuyenMai;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int tiLeKhuyenMai;
    private String moTa;
    private String loaiPhong;

    public KhuyenMai() {
    }
     public KhuyenMai(int maKhuyenMai, Date ngayBatDau, Date ngayKetThuc,int tiLeKhuyenMai, String moTa,String loaiPhong) {
        this.maKhuyenMai = maKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tiLeKhuyenMai = tiLeKhuyenMai;
        this.moTa = moTa;
        this.loaiPhong = loaiPhong;
    }
    
    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getTiLeKhuyenMai() {
        return tiLeKhuyenMai;
    }

    public void setTiLeKhuyenMai(int tiLeKhuyenMai) {
        this.tiLeKhuyenMai = tiLeKhuyenMai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
     public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public static KhuyenMai fromDocument(Document doc) {
        KhuyenMai khuyenMai = new KhuyenMai();

        if (doc.containsKey("maKhuyenMai")) {
            khuyenMai.setMaKhuyenMai(doc.getInteger("maKhuyenMai"));
        }
        if (doc.containsKey("ngayBatDau")) {
            khuyenMai.setNgayBatDau(doc.getDate("ngayBatDau"));
        }
        if (doc.containsKey("ngayKetThuc")) {
            khuyenMai.setNgayKetThuc(doc.getDate("ngayKetThuc"));
        }
        if (doc.containsKey("tiLeKhuyenMai")) {
            khuyenMai.setTiLeKhuyenMai(doc.getInteger("tiLeKhuyenMai"));
        }
        if (doc.containsKey("moTa")) {
            khuyenMai.setMoTa(doc.getString("moTa"));
        }
         if (doc.containsKey("loaiPhong")) {
            khuyenMai.setMoTa(doc.getString("loaiPhong"));
        }

        return khuyenMai;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" +
                "maKhuyenMai=" + maKhuyenMai +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", tiLeKhuyenMai=" + tiLeKhuyenMai +
                ", moTa='" + moTa + '\'' +
                ", loaiPhong='" + loaiPhong + '\'' +
                '}';
    }
}
