package model.DTO;

import org.bson.Document;
import java.util.Date;


public class HoaDon {
    private int maHoaDon;
    private int tongTien;
    private Date ngayTaoHoaDon;
    private NhanVien nhanVien;
    private boolean trangThai;

   
    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int tongTien, Date ngayTaoHoaDon, NhanVien nhanVien, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.ngayTaoHoaDon = ngayTaoHoaDon;
        this.nhanVien = nhanVien;
        this.trangThai = trangThai;
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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
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
            NhanVien nhanVien = NhanVien.fromDocument(nhanVienDoc);
            hoaDon.setNhanVien(nhanVien);
        }
        
        if(doc.containsKey("trangThai")){
             hoaDon.setTrangThai(doc.getBoolean("trangThai"));
        }

        return hoaDon;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", tongTien=" + tongTien + ", ngayTaoHoaDon=" + ngayTaoHoaDon + ", nhanVien=" + nhanVien + ", trangThai=" + trangThai + '}';
    }

 
}
