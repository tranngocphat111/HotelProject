/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import org.bson.Document;

/**
 *
 * @author datba
 */
public class NhanVienEmbed {
    private int maNhanVien;
    private String tenNhanVien;

    public NhanVienEmbed(int maNhanVien, String tenNhanVien) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
    }

    public NhanVienEmbed() {
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
    
    public static NhanVienEmbed fromDocument(Document doc) {
        NhanVienEmbed nhanVien = new NhanVienEmbed();
        if (doc.containsKey("maNhanVien")) {
            nhanVien.setMaNhanVien(doc.getInteger("maNhanVien"));
        }
        if (doc.containsKey("tenNhanVien")) {
            nhanVien.setTenNhanVien(doc.getString("tenNhanVien"));
        }
        return nhanVien;
    }
    @Override
    public String toString() {
        return "NhanVienEmbed{" + "maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + '}';
    }
    
}
