package model.DTO;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import model.DAO.TienNghiDAO;
import model.MongoDBConnection;

public class LoaiPhong {
    private int maLoaiPhong;
    private String tenLoaiPhong;
    private int dienTich;
    private int donGia;
    private int soKhachToiDa;
    private List<TienNghi> tienNghis;
    private String loaiGiuong;

    public LoaiPhong() {
    }

    public LoaiPhong(int maLoaiPhong, String tenLoaiPhong, int dienTich, int donGia, int soKhachToiDa, int khuyenMai, List<TienNghi> tienNghis) {
        this.maLoaiPhong = maLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.dienTich = dienTich;
        this.donGia = donGia;
        this.soKhachToiDa = soKhachToiDa;
        this.tienNghis = tienNghis;
        this.loaiGiuong = loaiGiuong;
    }

    public String getLoaiGiuong() {
        return loaiGiuong;
    }

    public void setLoaiGiuong(String loaiGiuong) {
        this.loaiGiuong = loaiGiuong;
    }

   
    

    public int getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public int getDienTich() {
        return dienTich;
    }

    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }



    public int getSoKhachToiDa() {
        return soKhachToiDa;
    }

    public void setSoKhachToiDa(int soKhachToiDa) {
        this.soKhachToiDa = soKhachToiDa;
    }



    public List<TienNghi> getTienNghis() {
        return tienNghis;
    }

    public void setTienNghis(List<TienNghi> tienNghis) {
        this.tienNghis = tienNghis;
    }

    public static LoaiPhong fromDocument(Document doc) {
        LoaiPhong loaiPhong = new LoaiPhong();

        if (doc.containsKey("maLoaiPhong")) {
            loaiPhong.setMaLoaiPhong(doc.getInteger("maLoaiPhong"));
        }
        if (doc.containsKey("tenLoaiPhong")) {
            loaiPhong.setTenLoaiPhong(doc.getString("tenLoaiPhong"));
        }
        if (doc.containsKey("dienTich")) {
            loaiPhong.setDienTich(doc.getInteger("dienTich"));
        }
        if (doc.containsKey("donGia")) {
            loaiPhong.setDonGia(doc.getInteger("donGia"));
        }
        
        if (doc.containsKey("soKhachToiDa")) {
            loaiPhong.setSoKhachToiDa(doc.getInteger("soKhachToiDa"));
        }
        if (doc.containsKey("loaiGiuong")) {
            loaiPhong.setLoaiGiuong(doc.getString("loaiGiuong"));
        }

//         Convert TienNghi array
        if (doc.containsKey("tienNghis")) {
            List<Document> tienNghis = (List<Document>) doc.get("tienNghis");
            List<TienNghi> List_tienNghi = new ArrayList<>();
            for (Document tn : tienNghis) {
                TienNghi tienNghi = TienNghi.fromDocument(tn);
                List_tienNghi.add(tienNghi);
            }
            
            loaiPhong.setTienNghis(List_tienNghi);
        }

        return loaiPhong;
    }

    @Override
    public String toString() {
        return "LoaiPhong{" + "maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", dienTich=" + dienTich + ", donGia=" + donGia  + ", soKhachToiDa=" + soKhachToiDa  + ", tienNghis=" + tienNghis + ", loaiGiuong=" + loaiGiuong + '}';
    }

    
}
