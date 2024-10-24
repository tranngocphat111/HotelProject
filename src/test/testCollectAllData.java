/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author datba
 */
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import model.DAO.*;
import model.DTO.*;
import model.MongoDBConnection;

import java.util.ArrayList;
import java.util.List;
//import GUI.DangNhap_GUI;

public class testCollectAllData {
    public static void main(String[] args) {
       
        
        
        // Initialize MongoDB connection

        MongoDBConnection.connection();
        MongoDatabase database =  MongoDBConnection.getDatabase();


//        MongoDBConnection connection = new MongoDBConnection();
//        MongoDatabase database = connection.getDatabase();
        

        // Test PhongDAO
        PhongDAO phongDAO = new PhongDAO(database);
        ArrayList<Phong> phongList = phongDAO.getAllPhongs();
        System.out.println("Phong:");
        for (Phong phong : phongList) {
            System.out.println(phong);
        }

        // Test NhanVienDAO
        NhanVienDAO nhanVienDAO = new NhanVienDAO(database);
        List<NhanVien> nhanVienList = nhanVienDAO.getAllNhanVien();
        System.out.println("\nNhanVien:");
        for (NhanVien nhanVien : nhanVienList) {
            System.out.println(nhanVien);
        }

        // Test KhachHangDAO
        KhachHangDAO khachHangDAO = new KhachHangDAO(database);
        List<KhachHang> khachHangList = khachHangDAO.getAllKhachHang();
        System.out.println("\nKhachHang:");
        for (KhachHang khachHang : khachHangList) {
            System.out.println(khachHang);
        }

        // Test LoaiPhongDAO
        LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO(database);
        List<LoaiPhong> loaiPhongList = loaiPhongDAO.getAllLoaiPhong();
        System.out.println("\nLoaiPhong:");
        for (LoaiPhong loaiPhong : loaiPhongList) {
            System.out.println(loaiPhong);
        }

        // Test TienNghiDAO
        TienNghiDAO tienNghiDAO = new TienNghiDAO(database);
        List<TienNghi> tienNghiList = tienNghiDAO.getAllTienNghi();
        System.out.println("\nTienNghi:");
        for (TienNghi tienNghi : tienNghiList) {
            System.out.println(tienNghi);
        }

        // Test KhuyenMaiDAO
        KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO(database);
        List<KhuyenMai> khuyenMaiList = khuyenMaiDAO.getAllKhuyenMai();
        System.out.println("\nKhuyenMai:");
        for (KhuyenMai khuyenMai : khuyenMaiList) {
            System.out.println(khuyenMai);
        }

        // Test DichVuDAO
        DichVuDAO dichVuDAO = new DichVuDAO(database);
        List<DichVu> dichVuList = dichVuDAO.getAllDichVu();
        System.out.println("\nDichVu:");
        for (DichVu dichVu : dichVuList) {
            System.out.println(dichVu);
        }

        // Test DonDatPhongDAO
        DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO(database);
        List<DonDatPhong> donDatPhongList = donDatPhongDAO.getAllDonDatPhong();
        System.out.println("\nDonDatPhong:");
        for (DonDatPhong donDatPhong : donDatPhongList) {
            System.out.println(donDatPhong);
        }

        // Test HoaDonDAO
        HoaDonDAO hoaDonDAO = new HoaDonDAO(database);
        List<HoaDon> hoaDonList = hoaDonDAO.getAllHoaDon();
        System.out.println("\nHoaDon:");
        for (HoaDon hoaDon : hoaDonList) {
            System.out.println(hoaDon);
        }

    }
}
