package model.DTO;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.DAO.DichVuDAO;
import model.DAO.KhachHangDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.MongoDBConnection;

public class DonDatPhong {

    private int maDonDat;
    private Date ngayDatPhong;
    private Date ngayNhanPhong;
    private Date ngayTraPhong;
    private String trangThai;
    private List<KhachHang> khachO;
    private List<DichVuEmbed> dichVuSuDung;
    private PhongEmbed phong;
    private int hoaDon;
    
    private LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(MongoDBConnection.getDatabase());
    private PhongDAO phong_dao = new PhongDAO(MongoDBConnection.getDatabase());


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

    public List<DichVuEmbed> getDichVuSuDung() {
        return dichVuSuDung;
    }

    public void setDichVuSuDung(List<DichVuEmbed> dichVuSuDung) {
        this.dichVuSuDung = dichVuSuDung;
    }

    public PhongEmbed getPhong() {
        return phong;
    }

    public void setPhong(PhongEmbed phong) {
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
                if(khachHang != null) khachO.add(khachHang);
            }
            donDatPhong.setKhachO(khachO);
        }

        // Convert DichVuSuDung array
        if (doc.containsKey("dichVuSuDung")) {
            List<Document> dichVuDocs = (List<Document>) doc.get("dichVuSuDung");
            List<DichVuEmbed> dichVuSuDung = new ArrayList<>();
            for (Document dichVuDoc : dichVuDocs) {
                
                DichVuEmbed dichVu = DichVuEmbed.fromDocument(dichVuDoc);
                if(dichVu != null) dichVuSuDung.add(dichVu);
            }
            donDatPhong.setDichVuSuDung(dichVuSuDung);
        }

        if (doc.containsKey("Phong")) {
            PhongEmbed phong = PhongEmbed.fromDocument((Document)doc.get("Phong"));
            if (phong != null) {
                donDatPhong.setPhong(phong);
            }
        }
        if (doc.containsKey("HoaDon")) {
            donDatPhong.setHoaDon(doc.getInteger("HoaDon"));
        }

        return donDatPhong;
    }

    public int getSoluongNgaySuDung() {
        long diffInMillies = getNgayTraPhong().getTime() - getNgayNhanPhong().getTime() ;
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24); // chuyển từ milliseconds sang ngày

        return (int) diffInDays;
    }

    public int thanhTien() {
        int tongTienDV = 0;
        if (getDichVuSuDung().size() != 0) {
            for (DichVuEmbed dv : getDichVuSuDung()) {
                tongTienDV = tongTienDV + (dv.getDonGia() * dv.getSoLuong());
            }
            
        }
        return getSoluongNgaySuDung() * phong.getDonGia()  + tongTienDV;
    }

    @Override
    public String toString() {
        return "DonDatPhong{" + "maDonDat=" + maDonDat + ", ngayDatPhong=" + ngayDatPhong + ", ngayNhanPhong=" + ngayNhanPhong + ", ngayTraPhong=" + ngayTraPhong + ", trangThai=" + trangThai + ", khachO=" + khachO + ", dichVuSuDung=" + dichVuSuDung + ", phong=" + phong + ", hoaDon=" + hoaDon + '}';
    }

    


}
