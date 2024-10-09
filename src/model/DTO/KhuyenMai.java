package model.DTO;

import org.bson.Document;

import java.util.Date;

public class KhuyenMai {
    private int maKhuyenMai;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private int tiLeKhuyenMai;
    private String moTa;

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
                '}';
    }
}
