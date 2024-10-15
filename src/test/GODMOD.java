/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.mongodb.client.MongoDatabase;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.DAO.DichVuDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.KhachHangDAO;
import model.DAO.KhuyenMaiDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.NhanVienDAO;
import model.DAO.PhongDAO;
import model.DAO.TienNghiDAO;
import model.MongoDBConnection;

import model.DAO.DichVuDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.KhachHangDAO;
import model.DAO.KhuyenMaiDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.NhanVienDAO;
import model.DAO.PhongDAO;
import model.DAO.TienNghiDAO;
import model.DTO.DichVu;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.KhachHang;

/**
 *
 * @author datba
 */
public class GODMOD {
    private MongoDBConnection connection = new MongoDBConnection();
    private MongoDatabase database = database = connection.getDatabase();
    private DichVuDAO dichVuDAO = new DichVuDAO(database);
    private DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO(database);
    private KhachHangDAO khachHangDAO = new KhachHangDAO(database);
    private KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO(database);
    private LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO(database);
    private NhanVienDAO nhanVienDAO = new NhanVienDAO(database);
    private PhongDAO phongDAO = new PhongDAO(database);
    private TienNghiDAO tienNghiDAO = new TienNghiDAO(database);
    
    
    
    public static void main(String[] args) {

    }
}

