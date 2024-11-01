package model.DTO;

import java.util.Objects;
import org.bson.Document;
import org.bson.types.Binary;

public class DichVu {
    private int maDV;
    private String tenDV;
    private String moTa;
    private int donGia;
//    private int soLuong;
    private byte[] hinhAnh;

    public DichVu() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.tenDV);
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
        final DichVu other = (DichVu) obj;
        return Objects.equals(this.tenDV, other.tenDV);
    }

    public DichVu(String tenDV) {
        this.tenDV = tenDV;
    }

//    public int getSoLuong() {
//        return soLuong;
//    }
//
//    public void setSoLuong(int soLuong) {
//        this.soLuong = soLuong;
//    }

    public DichVu(int maDV, String tenDV, String moTa, int donGia,  byte[] hinhAnh) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.moTa = moTa;
        this.donGia = donGia;
        this.hinhAnh = hinhAnh;
    }
    



    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public static DichVu fromDocument(Document doc) {
        DichVu dichVu = new DichVu();

        if (doc.containsKey("maDV")) {
            dichVu.setMaDV(doc.getInteger("maDV"));
        }
        if (doc.containsKey("tenDV")) {
            dichVu.setTenDV(doc.getString("tenDV"));
        }
        if (doc.containsKey("moTa")) {
            dichVu.setMoTa(doc.getString("moTa"));
        }
        if (doc.containsKey("donGia")) {
            dichVu.setDonGia(doc.getInteger("donGia"));
        }
//        if (doc.containsKey("soLuong")) {
//            dichVu.setDonGia(doc.getInteger("soLuong"));
//        }
        if (doc.containsKey("hinhAnh")) {
            Binary binaryData = doc.get("hinhAnh", Binary.class);
            byte[] imageData = binaryData.getData();
            dichVu.setHinhAnh(imageData);
        }

        return dichVu;
    }



}
