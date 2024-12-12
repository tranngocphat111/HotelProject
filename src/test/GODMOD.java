/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.mongodb.client.MongoDatabase;
import com.sun.jdi.connect.Connector;
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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import model.DAO.*;

import model.DTO.*;

/**
 *
 * @author datba
 */
public class GODMOD {

    private MongoDatabase database;
    private DichVuDAO dichVuDAO;
    private DichVuSuDungDAO dichVuSuDungDAO;
    private DonDatPhongDAO donDatPhongDAO;
    private HoaDonDAO hoaDonDAO;
    private KhachHangDAO khachHangDAO;
    private KhuyenMaiDAO khuyenMaiDAO;
    private LoaiPhongDAO loaiPhongDAO;
    private NhanVienDAO nhanVienDAO;
    private PhongDAO phongDAO;
    private TienNghiDAO tienNghiDAO;
    private Random random;

    public GODMOD() {
        MongoDBConnection.connection();
        database = MongoDBConnection.getDatabase();
        dichVuDAO = new DichVuDAO(database);
        dichVuSuDungDAO = new DichVuSuDungDAO(database);
        donDatPhongDAO = new DonDatPhongDAO(database);
        hoaDonDAO = new HoaDonDAO(database);
        khachHangDAO = new KhachHangDAO(database);
        khuyenMaiDAO = new KhuyenMaiDAO(database);
        loaiPhongDAO = new LoaiPhongDAO(database);
        nhanVienDAO = new NhanVienDAO(database);
        phongDAO = new PhongDAO(database);
        tienNghiDAO = new TienNghiDAO(database);
        random = new Random();
    }

    public static void main(String[] args) {
        GODMOD gm = new GODMOD();
        int ma_ddp_last = 2085;
        int maDVSD = 6253;
        Date ngayBatDau = new Date(124, 10, 1, 0, 0, 0);
        

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayBatDau);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date ngayCuoiThang = calendar.getTime();

        while (ngayBatDau.before(ngayCuoiThang)) {
            int startFloor = ThreadLocalRandom.current().nextInt(1, 3);
            int endFloor = ThreadLocalRandom.current().nextInt(6, 9);

            for (int floor = startFloor; floor <= endFloor; floor++) {
                for (int room = ThreadLocalRandom.current().nextInt(1, 3); room <= ThreadLocalRandom.current().nextInt(6, 9); room++) {
                    int roomNumber = generateRoomNumber(floor, room);
                    System.out.println("phòng: " + roomNumber + " Ma Don: " + ma_ddp_last + " Ma DVSD: " + maDVSD);
                    gm.donDatPhongDAO.createDonDatPhong(gm.taoDonDatPhong(ma_ddp_last, ngayBatDau, roomNumber, maDVSD));
                    ma_ddp_last += 1;
                    maDVSD += 3;
                }
            }
            // Cập nhật ngày bắt đầu lên ngày tiếp theo
            calendar.setTime(ngayBatDau);
            calendar.add(Calendar.DAY_OF_MONTH, 6);
            ngayBatDau = calendar.getTime();
            System.out.println(ngayBatDau);

        }

    }

    private DonDatPhong taoDonDatPhong(int maDonDat, Date ngayTaoDon, int maPhong, int maDVSD) {
        DonDatPhong ddp = new DonDatPhong();
        List<KhachHang> khachHangs = new ArrayList<>();
        List<PhongEmbed> phongs = new ArrayList<>();

        // 1 phòng, 2 khách ở, xài đủ 3 dịch vụ, số lượng sử dụng mỗi dịch vụ random
        int slKhachO = random.nextInt(2) + 1;

        for (int i = 0; i < slKhachO; ++i) {
            int maKH = random.nextInt(99) + 1;
            KhachHang khachHang = khachHangDAO.getKhachHangByMa(maKH);
            khachHangs.add(khachHang);
        }

        PhongEmbed phongEmbed = new PhongEmbed();

        List<Date> lDate = generateRandomDates(ngayTaoDon);

        phongEmbed.setMaPhong(maPhong);
        phongEmbed.setNgayNhanPhongDuKien(lDate.getFirst());
        phongEmbed.setNgayNhanPhong(lDate.getFirst());
        phongEmbed.setNgayTraPhongDuKien(lDate.getLast());
        phongEmbed.setNgayTraPhong(lDate.getLast());
        phongEmbed.setTrangThaiPhong("Đã trả");
        phongEmbed.setDonGia(loaiPhongDAO.getLoaiPhongByMa(phongDAO.getPhongByMa(maPhong).getLoaiPhong()).getDonGia());
        phongEmbed.setTenLoaiPhong(loaiPhongDAO.getLoaiPhongByMa(phongDAO.getPhongByMa(maPhong).getLoaiPhong()).getTenLoaiPhong());

        List<DichVuSuDung> lDVSD = new ArrayList<>();
        List<DichVuSuDungEmbed> lDVSDEmbed = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            DichVu dvInfo = dichVuDAO.getDichVuByMa(i + 1);

            DichVuSuDung dvsdTemp = new DichVuSuDung();
            dvsdTemp.setDonGia(dvInfo.getDonGia());
            dvsdTemp.setMaDV(i + 1);
            dvsdTemp.setMaDVSD(maDVSD++);
            dvsdTemp.setMaDonDatPhong(maDonDat);
            dvsdTemp.setMaPhong(maPhong);
            dvsdTemp.setNgaySuDung(lDate.getLast());
            dvsdTemp.setTenDV(dvInfo.getTenDV());
            dvsdTemp.setSoLuong(random.nextInt(2, 5));
            System.out.println(dvsdTemp);

            dichVuSuDungDAO.createDichVuSuDung(dvsdTemp);
            lDVSDEmbed.add(new DichVuSuDungEmbed(dvsdTemp.getMaDVSD(), dvsdTemp.getTenDV(), dvsdTemp.getDonGia(), dvsdTemp.getSoLuong()));
        }

        phongEmbed.setDichVuSuDung(lDVSDEmbed);
        phongEmbed.setTienDaThanhToan(phongEmbed.getTongTien());

        phongs.add(phongEmbed);

        ddp.setMaDonDat(maDonDat);
        ddp.setKhachO(khachHangs);
        ddp.setNguoiDat(khachHangs.get(0));
        ddp.setNgayTaoDon(ngayTaoDon);
        ddp.setTrangThai("Hoàn thành");
        ddp.setPhongs(phongs);
        System.out.println(ddp);
        
        

        return ddp;
    }

    private static List<Date> generateRandomDates(Date date) {
        List<Date> result = new ArrayList<>();

        // Tạo ngày ngẫu nhiên từ 1-2 ngày
        int randomDays1 = ThreadLocalRandom.current().nextInt(1, 3);
        Date firstDate = addDays(date, randomDays1);
        result.add(firstDate);

        // Tạo ngày ngẫu nhiên từ 2-4 ngày
        int randomDays2 = ThreadLocalRandom.current().nextInt(2, 5);
        Date secondDate = addDays(date, randomDays2);
        result.add(secondDate);

        return result;
    }

    private static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    private static int generateRoomNumber(int floor, int room) {
        if (floor < 1 || floor > 8 || room < 1 || room > 8) {
            throw new IllegalArgumentException("Tầng và số phòng phải nằm trong khoảng từ 1 đến 8.");
        }
        return floor * 100 + room;
    }
    
//    private HoaDon taoHoaDon(int maHoaDon, int tongTien, Date ngayTaoHoaDon, NhanVienEmbed nv, boolean trangThai, int maDonDatPhong){
//        
//    }

}
