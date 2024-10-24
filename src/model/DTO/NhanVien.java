package model.DTO;

import org.bson.Document;
import org.bson.types.Binary;

public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private byte[] anhDaiDien;
    private String soDienThoai;
    private String CCCD;
    private String diaChi;
    private String chucVu;
    private String tenTaiKhoan;
    private String matKhau;

    public NhanVien() {
    }

    public NhanVien(String tenNhanVien, byte[] anhDaiDien, String soDienThoai, String CCCD, String diaChi, String chucVu) {
        this.tenNhanVien = tenNhanVien;
        this.anhDaiDien = anhDaiDien;
        this.soDienThoai = soDienThoai;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
    }

    public NhanVien(int maNhanVien, String tenNhanVien, byte[] anhDaiDien, String soDienThoai, String CCCD, String diaChi, String chucVu) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.anhDaiDien = anhDaiDien;
        this.soDienThoai = soDienThoai;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.maNhanVien;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhanVien other = (NhanVien) obj;
        return this.maNhanVien == other.maNhanVien;
    }
    
    public NhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    public NhanVien(int maNhanVien, String tenNhanVien, byte[] anhDaiDien, String soDienThoai, String CCCD, String diaChi, String chucVu, String tenTaiKhoan, String matKhau) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.anhDaiDien = anhDaiDien;
        this.soDienThoai = soDienThoai;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public byte[] getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(byte[] anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
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

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
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

    public static NhanVien fromDocument(Document doc) {
        NhanVien nhanVien = new NhanVien();
        if (doc.containsKey("maNhanVien")) {
            nhanVien.setMaNhanVien(doc.getInteger("maNhanVien"));
        }
        if (doc.containsKey("tenNhanVien")) {
            nhanVien.setTenNhanVien(doc.getString("tenNhanVien"));
        }
        if (doc.containsKey("anhDaiDien")) {
            Binary binaryData = doc.get("anhDaiDien", Binary.class);
            byte[] imageData = binaryData.getData();
            nhanVien.setAnhDaiDien(imageData);
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
            nhanVien.setChucVu(doc.getString("chucVu"));
        }
        if (doc.containsKey("tenTaiKhoan")) {
            nhanVien.setTenTaiKhoan(doc.getString("tenTaiKhoan"));
        }
        if (doc.containsKey("matKhau")) {
            nhanVien.setMatKhau(doc.getString("matKhau"));
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
                '}';
    }
}
