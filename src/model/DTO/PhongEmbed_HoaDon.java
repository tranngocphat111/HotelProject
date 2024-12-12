/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Admin
 */
public class PhongEmbed_HoaDon {

    private int maPhong;
    private Date ngayNhan;
    private Date ngayTra;
    private int donGia;

    @Override
    public String toString() {
        return "PhongEmbed_HoaDon{" + "maPhong=" + maPhong + ", ngayNhan=" + ngayNhan + ", ngayTra=" + ngayTra + ", donGia=" + donGia + '}';
    }

    public PhongEmbed_HoaDon() {
    }

    public PhongEmbed_HoaDon(int maPhong, Date ngayNhan, Date ngayTra, int donGia) {
        this.maPhong = maPhong;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.donGia = donGia;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public int getDonGia() {
        return donGia;
    }

    public static PhongEmbed_HoaDon fromDocument(Document doc) {
        PhongEmbed_HoaDon phong = new PhongEmbed_HoaDon();

        if (doc.containsKey("maPhong")) {
            phong.setMaPhong(doc.getInteger("maPhong"));
        }

        if (doc.containsKey("ngayNhan")) {
            phong.setNgayNhan(doc.getDate("ngayNhan"));
        }

        if (doc.containsKey("ngayTra")) {
            phong.setNgayTra(doc.getDate("ngayTra"));
        }

        if (doc.containsKey("donGia")) {
            phong.setDonGia(doc.getInteger("donGia"));
        }

        return phong;
    }

}
