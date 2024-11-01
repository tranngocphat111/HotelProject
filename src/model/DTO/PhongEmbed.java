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
public class PhongEmbed {
    private int maPhong;
    private int donGia;
    private String tenLoaiPhong;

    public PhongEmbed(int maPhong, int donGia, String tenLoaiPhong) {
        this.maPhong = maPhong;
        this.donGia = donGia;
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public PhongEmbed() {
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }
    
    public static PhongEmbed fromDocument(Document doc) {
        PhongEmbed phong = new PhongEmbed();

        if (doc.containsKey("maPhong")) {
            phong.setMaPhong(doc.getInteger("maPhong"));
        }

        if (doc.containsKey("donGia")) {
            phong.setDonGia(doc.getInteger("donGia"));
        }
        if (doc.containsKey("tenLoaiPhong")) {
            phong.setTenLoaiPhong(doc.getString("tenLoaiPhong"));
        }
        return phong;
    }
    
           
}
