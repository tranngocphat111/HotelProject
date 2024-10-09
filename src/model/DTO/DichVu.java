package model.DTO;

import org.bson.Document;

public class DichVu {
    private int maDV;
    private String tenDV;
    private String moTa;
    private int donGia;

    public DichVu() {
    }

    public DichVu(int maDV, String tenDV, String moTa, int donGia) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.moTa = moTa;
        this.donGia = donGia;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public static DichVu fromDocument(Document doc) {
        DichVu dichVu = new DichVu();

        if (doc.containsKey("maDV")) {
            dichVu.setMaDV(doc.getInteger("maDV"));
        }
        if (doc.containsKey("tenDV")) {
            dichVu.setTenDV(doc.getString("tenDV"));
        }
        if (doc.containsKey("moTa")) {
            dichVu.setMoTa(doc.getString("moTa"));
        }
        if (doc.containsKey("donGia")) {
            dichVu.setDonGia(doc.getInteger("donGia"));
        }

        return dichVu;
    }

    @Override
    public String toString() {
        return "DichVu{" +
                "maDV=" + maDV +
                ", tenDV='" + tenDV + '\'' +
                ", moTa='" + moTa + '\'' +
                ", donGia=" + donGia +
                '}';
    }
}
