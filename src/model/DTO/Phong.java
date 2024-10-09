package model.DTO;

import org.bson.Document;

public class Phong {
    private int maPhong;
    private int trangThai;
    private String soPhong;
    private int tang;
    private int loaiPhong;

    public Phong(int maPhong, int trangThai, String soPhong, int tang, int loaiPhong) {
        this.maPhong = maPhong;
        this.trangThai = trangThai;
        this.soPhong = soPhong;
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

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
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
        return "Phong{" +
                "maPhong=" + maPhong +
                ", trangThai=" + trangThai +
                ", soPhong='" + soPhong + '\'' +
                ", tang=" + tang +
                ", loaiPhong=" + loaiPhong +
                '}';
    }

    public static Phong fromDocument(Document doc) {
        Phong phong = new Phong();

        if (doc.containsKey("maPhong")) {
            phong.setMaPhong(doc.getInteger("maPhong"));
        }
        if (doc.containsKey("trangThai")) {
            phong.setTrangThai(doc.getInteger("trangThai"));
        }
        if (doc.containsKey("soPhong")) {
            phong.setSoPhong(doc.getString("soPhong"));
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
