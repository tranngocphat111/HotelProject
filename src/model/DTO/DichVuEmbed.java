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
public class DichVuEmbed {

    private int maDV;
    private String tenDV;
    private int donGia;
    private int soLuong;

    public DichVuEmbed(int maDV, String tenDV, int donGia, int soLuong) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public DichVuEmbed() {
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
    
    public static DichVuEmbed fromDocument(Document doc) {
        DichVuEmbed dichVu = new DichVuEmbed();

        if (doc.containsKey("maDV")) {
            dichVu.setMaDV(doc.getInteger("maDV"));
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
        return "DichVuEmbed{" + "maDV=" + maDV + ", tenDV=" + tenDV + ", donGia=" + donGia + ", soLuong=" + soLuong + '}';
    }
    
    

}
