/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DTO;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Admin
 */
public class ThongTinThanhToan {
    private List<PhongEmbed_HoaDon> phongs;
    private int[] dichVu;

    public List<PhongEmbed_HoaDon> getPhongs() {
        return phongs;
    }

    public int[] getDichVu() {
        return dichVu;
    }

    public void setPhongs(List<PhongEmbed_HoaDon> phongs) {
        this.phongs = phongs;
    }

    public void setDichVu(int[] dichVu) {
        this.dichVu = dichVu;
    }

    public ThongTinThanhToan(List<PhongEmbed_HoaDon> phongs, int[] dichVu) {
        this.phongs = phongs;
        this.dichVu = dichVu;
    }

    public ThongTinThanhToan() {
    }

    @Override
    public String toString() {
        return "ThongTinThanhToan{" + "phongs=" + phongs + ", dichVu=" + dichVu + '}';
    }
    
    public static ThongTinThanhToan fromDocument(Document doc) {
    ThongTinThanhToan thongTin = new ThongTinThanhToan();

    if (doc.containsKey("phongs")) {
        List<Document> phongDocs = doc.getList("phongs", Document.class);
        List<PhongEmbed_HoaDon> phongs = new ArrayList<>();
        for (Document phongDoc : phongDocs) {
            phongs.add(PhongEmbed_HoaDon.fromDocument(phongDoc));
        }
        thongTin.setPhongs(phongs);
    }

    if (doc.containsKey("dichVu")) {
        List<Integer> dichVuList = doc.getList("dichVu", Integer.class);
        int[] dichVuArray = dichVuList.stream().mapToInt(i -> i).toArray();
        thongTin.setDichVu(dichVuArray);
    }

    return thongTin;
}
    
    
    
    
}
