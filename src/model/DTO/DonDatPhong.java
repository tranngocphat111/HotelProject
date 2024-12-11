package model.DTO;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonDatPhong {

    private int maDonDat;
    private Date ngayTaoDon;
    private String trangThai;
    private List<KhachHang> khachO;
    private List<PhongEmbed> phongs;
    private KhachHang nguoiDat;
    

    public DonDatPhong() {
    }

    public DonDatPhong(int maDonDat, Date ngayTaoDon, String trangThai, List<KhachHang> khachO, List<PhongEmbed> phongs, KhachHang nguoiDat) {
        this.maDonDat = maDonDat;
        this.ngayTaoDon = ngayTaoDon;
        this.trangThai = trangThai;
        this.khachO = khachO;
        this.phongs = phongs;
        this.nguoiDat = nguoiDat;
    }

    
    
    

    public static DonDatPhong fromDocument(Document doc) {
        DonDatPhong donDatPhong = new DonDatPhong();

        if (doc.containsKey("maDonDat")) {
            donDatPhong.setMaDonDat(doc.getInteger("maDonDat"));
        }
        if (doc.containsKey("ngayTaoDon")) {
            donDatPhong.setNgayTaoDon(doc.getDate("ngayTaoDon"));
        }
        if (doc.containsKey("nguoiDat")) {
            Document nguoiDatDoc = (Document) doc.get("nguoiDat");
            KhachHang nguoiDat = KhachHang.fromDocument(nguoiDatDoc);
            donDatPhong.setNguoiDat(nguoiDat);
        }
        if (doc.containsKey("nguoiO")) {
            List<Document> nguoiODocs = (List<Document>) doc.get("nguoiO");
            List<KhachHang> nguoiO = new ArrayList<>();
            for(Document nguoi : nguoiODocs){
                KhachHang kh = KhachHang.fromDocument(nguoi);
                nguoiO.add(kh);
            }
            donDatPhong.setKhachO(nguoiO);
        }
        
        if (doc.containsKey("trangThai")) {
            donDatPhong.setTrangThai(doc.getString("trangThai"));
        }


        if (doc.containsKey("phong")) {
            List<Document> phongDocs = (List<Document>) doc.get("phong");
            List<PhongEmbed> Phongs = new ArrayList<>();
            for(Document phong : phongDocs){
                PhongEmbed kh = PhongEmbed.fromDocument(phong);
                Phongs.add(kh);
            }
            donDatPhong.setPhongs(Phongs);
            
        }
        

        return donDatPhong;
    }



    public void setMaDonDat(int maDonDat) {
        this.maDonDat = maDonDat;
    }

    public void setNgayTaoDon(Date ngayTaoDon) {
        this.ngayTaoDon = ngayTaoDon;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setKhachO(List<KhachHang> khachO) {
        this.khachO = khachO;
    }

    public void setPhongs(List<PhongEmbed> phongs) {
        this.phongs = phongs;
    }

    public void setNguoiDat(KhachHang nguoiDat) {
        this.nguoiDat = nguoiDat;
    }


//    public int getSoluongNgaySuDung() {
//        long diffInMillies = getNgayTraPhong().getTime() - getNgayNhanPhong().getTime() ;
//        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24); // chuyển từ milliseconds sang ngày
//
//        return (int) diffInDays;
//    }
//
//    public int thanhTien() {
//        int tongTienDV = 0;
//        if (getDichVuSuDung().size() != 0) {
//            for (DichVuEmbed dv : getDichVuSuDung()) {
//                tongTienDV = tongTienDV + (dv.getDonGia() * dv.getSoLuong());
//            }
//            
//        }
//        return getSoluongNgaySuDung() * phong.getDonGia()  + tongTienDV;
//    }

    public int getMaDonDat() {
        return maDonDat;
    }

    public Date getNgayTaoDon() {
        return ngayTaoDon;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public List<KhachHang> getKhachO() {
        return khachO;
    }

    public List<PhongEmbed> getPhongs() {
        return phongs;
    }

    public KhachHang getNguoiDat() {
        return nguoiDat;
    }



    @Override
    public String toString() {
        return "DonDatPhong{" + "maDonDat=" + maDonDat + ", ngayDatPhong=" + ngayTaoDon + ", trangThai=" + trangThai + ", khachO=" + khachO + ", phongs=" + phongs + ", nguoiDat=" + nguoiDat  + '}';
    }

    public int getTongTien(){
        int tongTien = 0;
        for (PhongEmbed p: phongs){
            tongTien += p.getTongTien();
        }
        return tongTien;
    }
    
    public int getTienDaThanhToan(){
        int tongTien = 0;
        for (PhongEmbed p: phongs){
            tongTien += p.getTienDaThanhToan();
        }
        return tongTien;
    }
    
    public boolean isHoanThanh(){
        return getTongTien() == getTienDaThanhToan();
    }


}
