/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author datba
 */
public class PhongEmbed {

    private int maPhong;
    private int donGia;
    private String tenLoaiPhong;
    private List<DichVuSuDungEmbed> dichVuSuDung;
    private Date ngayNhanPhongDuKien;
    private Date ngayTraPhongDuKien;
    private Date ngayNhanPhong;
    private Date ngayTraPhong;
    private String trangThaiPhong;
    private int tienDaThanhToan;

    public PhongEmbed() {
    }

    public PhongEmbed(int maPhong, int donGia, String tenLoaiPhong, ArrayList<DichVuSuDungEmbed> dichVuSuDung, Date ngayNhanPhongDuKien, Date ngayTraPhongDuKien, Date ngayNhanPhong, Date ngayTraPhong, String trangThaiPhong, int tienDaThanhToan) {
        this.maPhong = maPhong;
        this.donGia = donGia;
        this.tenLoaiPhong = tenLoaiPhong;
        this.dichVuSuDung = dichVuSuDung;
        this.ngayNhanPhongDuKien = ngayNhanPhongDuKien;
        this.ngayTraPhongDuKien = ngayTraPhongDuKien;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.trangThaiPhong = trangThaiPhong;
        this.tienDaThanhToan = tienDaThanhToan;
    }

    public List<DichVuSuDungEmbed> getDichVuSuDung() {
        return dichVuSuDung;
    }

    public Date getNgayNhanPhongDuKien() {
        return ngayNhanPhongDuKien;
    }

    public Date getNgayTraPhongDuKien() {
        return ngayTraPhongDuKien;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public String getTrangThaiPhong() {
        return trangThaiPhong;
    }

    public int getTienDaThanhToan() {
        return tienDaThanhToan;
    }

    public void setDichVuSuDung(List<DichVuSuDungEmbed> dichVuSuDung) {
        this.dichVuSuDung = dichVuSuDung;
    }

    public void setNgayNhanPhongDuKien(Date ngayNhanPhongDuKien) {
        this.ngayNhanPhongDuKien = ngayNhanPhongDuKien;
    }

    public void setNgayTraPhongDuKien(Date ngayTraPhongDuKien) {
        this.ngayTraPhongDuKien = ngayTraPhongDuKien;
    }

    public void setNgayNhanPhong(Date ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public void setNgayTraPhong(Date ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public void setTrangThaiPhong(String trangThaiPhong) {
        this.trangThaiPhong = trangThaiPhong;
    }

    public void setTienDaThanhToan(int tienDaThanhToan) {
        this.tienDaThanhToan = tienDaThanhToan;
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
        
        if (doc.containsKey("ngayNhanPhongDuKien")) {
            phong.setNgayNhanPhongDuKien(doc.getDate("ngayNhanPhongDuKien"));
        }
        
        if (doc.containsKey("ngayTraPhongDuKien")) {
            phong.setNgayTraPhongDuKien(doc.getDate("ngayTraPhongDuKien"));
        }
        
        if (doc.containsKey("ngayNhanPhong")) {
            phong.setNgayNhanPhong(doc.getDate("ngayNhanPhong"));
        }
        
        if (doc.containsKey("ngayTraPhong")) {
            phong.setNgayTraPhong(doc.getDate("ngayTraPhong"));
        }
        

        if (doc.containsKey("dichVuSuDung")) {
            List<Document> dichVuDocs = (List<Document>) doc.get("dichVuSuDung");
            List<DichVuSuDungEmbed> dichVuSuDung = new ArrayList<>();
            for (Document dichVuDoc : dichVuDocs) {
                
                DichVuSuDungEmbed dichVu = DichVuSuDungEmbed.fromDocument(dichVuDoc);
                if(dichVu != null) dichVuSuDung.add(dichVu);
            }
            phong.setDichVuSuDung(dichVuSuDung);
        }
        
        if (doc.containsKey("trangThaiPhong")) {
            phong.setTrangThaiPhong(doc.getString("trangThaiPhong"));
        }
        
        if (doc.containsKey("tienDaThanhToan")) {
            phong.setTienDaThanhToan(doc.getInteger("tienDaThanhToan"));
        }
        
        
        return phong;
    }

    @Override
    public String toString() {
        return "PhongEmbed{" + "maPhong=" + maPhong + ", donGia=" + donGia + ", tenLoaiPhong=" + tenLoaiPhong + ", dichVuSuDung=" + dichVuSuDung + ", ngayNhanPhongDuKien=" + ngayNhanPhongDuKien + ", ngayTraPhongDuKien=" + ngayTraPhongDuKien + ", ngayNhanPhong=" + ngayNhanPhong + ", ngayTraPhong=" + ngayTraPhong + ", trangThaiPhong=" + trangThaiPhong + ", tienDaThanhToan=" + tienDaThanhToan + '}';
    }

}
