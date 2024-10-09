package model.DTO;

import org.bson.Document;

public class KhachHang {
    private int maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String CCCD;
    private String quocTich;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String tenKhachHang, String soDienThoai, String CCCD, String quocTich) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.CCCD = CCCD;
        this.quocTich = quocTich;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public static KhachHang fromDocument(Document doc) {
        KhachHang khachHang = new KhachHang();

        if (doc.containsKey("maKhachHang")) {
            khachHang.setMaKhachHang(doc.getInteger("maKhachHang"));
        }
        if (doc.containsKey("tenKhachHang")) {
            khachHang.setTenKhachHang(doc.getString("tenKhachHang"));
        }
        if (doc.containsKey("soDienThoai")) {
            khachHang.setSoDienThoai(doc.getString("soDienThoai"));
        }
        if (doc.containsKey("CCCD")) {
            khachHang.setCCCD(doc.getString("CCCD"));
        }
        if (doc.containsKey("quocTich")) {
            khachHang.setQuocTich(doc.getString("quocTich"));
        }

        return khachHang;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang=" + maKhachHang +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", quocTich='" + quocTich + '\'' +
                '}';
    }
}
