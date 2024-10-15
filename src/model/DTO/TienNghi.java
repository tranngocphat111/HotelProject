package model.DTO;


import org.bson.Document;

import java.util.Date;

public class TienNghi {
    private int maTienNghi;
    private String tenTienNghi;
    private String moTa;
    private String hinhAnh;
    
//    private int soLuong;
//    private Date ngayLapDat;
//    private Date ngayBaoTriGanNhat;

    public TienNghi() {
    }

    public TienNghi(int maTienNghi, String tenTienNghi, String moTa, int soLuong, Date ngayLapDat, Date ngayBaoTriGanNhat) {
        this.maTienNghi = maTienNghi;
        this.tenTienNghi = tenTienNghi;
        this.moTa = moTa;
//        this.soLuong = soLuong;
//        this.ngayLapDat = ngayLapDat;
//        this.ngayBaoTriGanNhat = ngayBaoTriGanNhat;
    }

    public int getMaTienNghi() {
        return maTienNghi;
    }

    public void setMaTienNghi(int maTienNghi) {
        this.maTienNghi = maTienNghi;
    }

    public String getTenTienNghi() {
        return tenTienNghi;
    }

    public void setTenTienNghi(String tenTienNghi) {
        this.tenTienNghi = tenTienNghi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

//    public int getSoLuong() {
//        return soLuong;
//    }
//
//    public void setSoLuong(int soLuong) {
//        this.soLuong = soLuong;
//    }
//
//    public Date getNgayLapDat() {
//        return ngayLapDat;
//    }
//
//    public void setNgayLapDat(Date ngayLapDat) {
//        this.ngayLapDat = ngayLapDat;
//    }
//
//    public Date getNgayBaoTriGanNhat() {
//        return ngayBaoTriGanNhat;
//    }
//
//    public void setNgayBaoTriGanNhat(Date ngayBaoTriGanNhat) {
//        this.ngayBaoTriGanNhat = ngayBaoTriGanNhat;
//    }

    public static TienNghi fromDocument(Document doc) {
        TienNghi tienNghi = new TienNghi();

        if (doc.containsKey("maTienNghi")) {
            tienNghi.setMaTienNghi(doc.getInteger("maTienNghi"));
        }
        if (doc.containsKey("tenTienNghi")) {
            tienNghi.setTenTienNghi(doc.getString("tenTienNghi"));
        }
        if (doc.containsKey("moTa")) {
            tienNghi.setMoTa(doc.getString("moTa"));
        }
        if (doc.containsKey("hinhAnh")) {
            tienNghi.setHinhAnh(doc.getString("soLuong"));
        }

        return tienNghi;
    }

    @Override
    public String toString() {
        return "TienNghi{" + "maTienNghi=" + maTienNghi + ", tenTienNghi=" + tenTienNghi + ", moTa=" + moTa + ", hinhAnh=" + hinhAnh + '}';
    }
}
