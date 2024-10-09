package model.DTO;

import org.bson.Document;

public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private String anhDaiDien;
    private String soDienThoai;
    private String CCCD;
    private String diaChi;
    private int chucVu;
    private String tenTaiKhoan;
    private String matKhau;
    private int trangThai;

    public NhanVien() {
    }

    public NhanVien(int trangThai, String matKhau, String tenTaiKhoan, int chucVu, String diaChi, String CCCD, String soDienThoai, String anhDaiDien, String tenNhanVien, int maNhanVien) {
        this.trangThai = trangThai;
        this.matKhau = matKhau;
        this.tenTaiKhoan = tenTaiKhoan;
        this.chucVu = chucVu;
        this.diaChi = diaChi;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
        this.anhDaiDien = anhDaiDien;
        this.tenNhanVien = tenNhanVien;
        this.maNhanVien = maNhanVien;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    public static NhanVien fromDocument(Document doc) {
        NhanVien nhanVien = new NhanVien();
        if (doc.containsKey("maNhanVien")) {
            nhanVien.setMaNhanVien(doc.getInteger("maNhanVien"));
        }
        if (doc.containsKey("tenNhanVien")) {
            nhanVien.setTenNhanVien(doc.getString("tenNhanVien"));
        }
        if (doc.containsKey("anhDaiDien")) {
            nhanVien.setAnhDaiDien(doc.getString("anhDaiDien"));
        }
        if (doc.containsKey("SoDienThoai")) {
            nhanVien.setSoDienThoai(doc.getString("SoDienThoai"));
        }
        if (doc.containsKey("CCCD")) {
            nhanVien.setCCCD(doc.getString("CCCD"));
        }
        if (doc.containsKey("diaChi")) {
            nhanVien.setDiaChi(doc.getString("diaChi"));
        }
        if (doc.containsKey("chucVu")) {
            nhanVien.setChucVu(doc.getInteger("chucVu"));
        }
        if (doc.containsKey("tenTaiKhoan")) {
            nhanVien.setTenTaiKhoan(doc.getString("tenTaiKhoan"));
        }
        if (doc.containsKey("matKhau")) {
            nhanVien.setMatKhau(doc.getString("matKhau"));
        }
        if (doc.containsKey("trangThai")) {
            nhanVien.setTrangThai(doc.getInteger("trangThai"));
        }
        return nhanVien;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien=" + maNhanVien +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", anhDaiDien='" + anhDaiDien + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", chucVu=" + chucVu +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
