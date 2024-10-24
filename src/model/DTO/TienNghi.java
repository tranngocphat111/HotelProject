package model.DTO;

import java.util.Collections;
import java.util.Comparator;
import org.bson.Document;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.bson.types.Binary;

public class TienNghi {

    private int maTienNghi;
    private String tenTienNghi;
    private String moTa;
    private byte[] hinhAnh;

//    private int soLuong;
//    private Date ngayLapDat;
//    private Date ngayBaoTriGanNhat;
    public TienNghi() {
    }

    public TienNghi(int maTienNghi, String tenTienNghi, String moTa, byte[] hinhAnh) {
        this.maTienNghi = maTienNghi;
        this.tenTienNghi = tenTienNghi;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public TienNghi(String tenTienNghi) {
        this.tenTienNghi = tenTienNghi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tenTienNghi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TienNghi other = (TienNghi) obj;
        return Objects.equals(this.tenTienNghi, other.tenTienNghi);
    }

    public int getMaTienNghi() {
        return maTienNghi;
    }

    public void setMaTienNghi(int maTienNghi) {
        this.maTienNghi = maTienNghi;
    }

    public String getTenTienNghi() {
        return tenTienNghi;
    }

    public void setTenTienNghi(String tenTienNghi) {
        this.tenTienNghi = tenTienNghi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public static TienNghi fromDocument(Document doc) {
        TienNghi tienNghi = new TienNghi();

        if (doc.containsKey("maTienNghi")) {
            tienNghi.setMaTienNghi(doc.getInteger("maTienNghi"));
        }
        if (doc.containsKey("tenTienNghi")) {
            tienNghi.setTenTienNghi(doc.getString("tenTienNghi"));
        }
        if (doc.containsKey("moTa")) {
            tienNghi.setMoTa(doc.getString("moTa"));
        }
        if (doc.containsKey("hinhAnh")) {
            Binary binaryData = doc.get("hinhAnh", Binary.class);
            byte[] imageData = binaryData.getData();
            tienNghi.setHinhAnh(imageData);
        }

        return tienNghi;
    }

    public static void sapXepTienNghiTheoMa(List<TienNghi> danhSachTienNghi) {
        Collections.sort(danhSachTienNghi, Comparator.comparingInt(TienNghi::getMaTienNghi));
    }

    @Override
    public String toString() {
        return "TienNghi{" + "maTienNghi=" + maTienNghi + ", tenTienNghi=" + tenTienNghi + ", moTa=" + moTa + ", hinhAnh=" + hinhAnh + '}';
    }

}
