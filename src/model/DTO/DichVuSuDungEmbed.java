/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import org.bson.Document;
import org.bson.types.Binary;

/**
 *
 * @author datba
 */
public class DichVuSuDungEmbed {

    private int maDVSD;
    private String tenDV;
    private int donGia;
    private int soLuong;

    public DichVuSuDungEmbed(int maDVSD, String tenDV, int donGia, int soLuong) {
        this.maDVSD = maDVSD;
        this.tenDV = tenDV;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public DichVuSuDungEmbed() {
    }

    public int getMaDVSD() {
        return maDVSD;
    }

    public void setMaDVSD(int maDVSD) {
        this.maDVSD = maDVSD;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public static DichVuSuDungEmbed fromDocument(Document doc) {
        DichVuSuDungEmbed dichVu = new DichVuSuDungEmbed();

        if (doc.containsKey("maDVSD")) {
            dichVu.setMaDVSD(doc.getInteger("maDVSD"));
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
        return "DichVuEmbed{" + "maDVSD=" + maDVSD + ", tenDV=" + tenDV + ", donGia=" + donGia + ", soLuong=" + soLuong + '}';
    }
    
    

}
