/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import org.bson.Document;

/**
 *
 * @author Admin
 */
public class DichVuSuDung {

    private int maDVSD;
    private int maPhong;
    private int maDonDatPhong;
    private int maDV;
    private String tenDV;
    private int soLuong;
    private int donGia;

    public int getMaDVSD() {
        return maDVSD;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public int getMaDonDatPhong() {
        return maDonDatPhong;
    }

    public int getMaDV() {
        return maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setMaDVSD(int maDVSD) {
        this.maDVSD = maDVSD;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public void setMaDonDatPhong(int maDonDatPhong) {
        this.maDonDatPhong = maDonDatPhong;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public DichVuSuDung(int maDVSD, int maPhong, int maDonDatPhong, int maDV, String tenDV, int soLuong, int donGia) {
        this.maDVSD = maDVSD;
        this.maPhong = maPhong;
        this.maDonDatPhong = maDonDatPhong;
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public DichVuSuDung() {
    }

    public static DichVuSuDung fromDocument(Document doc) {
        DichVuSuDung dichVu = new DichVuSuDung();

        if (doc.containsKey("maDVSD")) {
            dichVu.setMaDVSD(doc.getInteger("maDVSD"));
        }
        if (doc.containsKey("maDonDatPhong")) {
            dichVu.setMaDonDatPhong(doc.getInteger("maDonDatPhong"));
        }
        if (doc.containsKey("maPhong")) {
            dichVu.setMaPhong(doc.getInteger("maPhong"));
        }
        if (doc.containsKey("maDichVu")) {
            dichVu.setMaDV(doc.getInteger("maDichVu"));
        }
        if (doc.containsKey("tenDV")) {
            dichVu.setTenDV(doc.getString("tenDV"));
        }
        if (doc.containsKey("donGia")) {
            dichVu.setDonGia(doc.getInteger("donGia"));
        }
        if (doc.containsKey("soLuong")) {
            dichVu.setSoLuong(doc.getInteger("soLuong"));
        }
        return dichVu;
    }

    @Override
    public String toString() {
        return "DichVuSuDung{" + "maDVSD=" + maDVSD + ", maPhong=" + maPhong + ", maDonDatPhong=" + maDonDatPhong + ", maDV=" + maDV + ", tenDV=" + tenDV + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

}
