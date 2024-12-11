package model.DTO;

import org.bson.Document;

public class KhachHang {
    private int maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String CCCD;
    private String quocTich;
    private String gioiTinh;
    private String email;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String tenKhachHang, String soDienThoai, String CCCD, String quocTich, String gioiTinh, String email) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.CCCD = CCCD;
        this.quocTich = quocTich;
        this.gioiTinh = gioiTinh;
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (doc.containsKey("Hoten")) {
            khachHang.setTenKhachHang(doc.getString("Hoten"));
        }
        if (doc.containsKey("SDT")) {
            khachHang.setSoDienThoai(doc.getString("SDT"));
        }
        if (doc.containsKey("CCCD")) {
            khachHang.setCCCD(doc.getString("CCCD"));
        }
        if (doc.containsKey("QuocTich")) {
            khachHang.setQuocTich(doc.getString("QuocTich"));
        }
        if (doc.containsKey("GioiTinh")) {
            khachHang.setGioiTinh(doc.getString("GioiTinh"));
        }
        if (doc.containsKey("Email")) {
            khachHang.setEmail(doc.getString("Email"));
        }

        return khachHang;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", soDienThoai=" + soDienThoai + ", CCCD=" + CCCD + ", quocTich=" + quocTich + ", gioiTinh=" + gioiTinh + ", email=" + email + '}';
    }
    
}
