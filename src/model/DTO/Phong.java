package model.DTO;

import org.bson.Document;

public class Phong {
    private int maPhong;
    private int tang;
    private int loaiPhong;
    private String moTa;

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }

    public Phong(int maPhong, int trangThai, int tang, int loaiPhong) {
        this.maPhong = maPhong;
        this.tang = tang;
        this.loaiPhong = loaiPhong;
    }

    public Phong() {
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }


    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public int getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(int loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    @Override
    public String toString() {
        return "Phong{" + "maPhong=" + maPhong + ", tang=" + tang + ", loaiPhong=" + loaiPhong + ", moTa=" + moTa + '}';
    }

   

    public static Phong fromDocument(Document doc) {
        Phong phong = new Phong();

        if (doc.containsKey("maPhong")) {
            phong.setMaPhong(doc.getInteger("maPhong"));
        }

        if (doc.containsKey("moTa")) {
            phong.setMoTa(doc.getString("moTa"));
        }
        if (doc.containsKey("tang")) {
            phong.setTang(doc.getInteger("tang"));
        }
        if (doc.containsKey("loaiPhong")) {
            phong.setLoaiPhong(doc.getInteger("loaiPhong"));
        }

        return phong;
    }
}
