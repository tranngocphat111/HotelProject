/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import model.DTO.DichVuSuDung;
import model.DTO.DichVuSuDungEmbed;

/**
 *
 * @author datba
 */
public class DichVuSuDungDAO {

    private MongoCollection<Document> dichVuSuDungCollection;

    public MongoCollection<Document> getCollection() {
        return dichVuSuDungCollection;
    }

    public DichVuSuDungDAO(MongoDatabase database) {
        dichVuSuDungCollection = database.getCollection("DichVuSuDung");
    }

    public List<DichVuSuDung> getAllDichVu() {
        List<DichVuSuDung> dichVus = new ArrayList<>();
        try (MongoCursor<Document> cursor = dichVuSuDungCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                DichVuSuDung dichVuSuDung = DichVuSuDung.fromDocument(doc);
                dichVus.add(dichVuSuDung);
            }
        }
        return dichVus;
    }

    public boolean createDichVuSuDung(DichVuSuDung dvsd) {
        try {
            Document doc = new Document()
                    .append("maDVSD", dvsd.getMaDVSD())
                    .append("maDonDatPhong", dvsd.getMaDonDatPhong())
                    .append("maDV", dvsd.getMaDV())
                    .append("maPhong", dvsd.getMaPhong())
                    .append("soLuong", dvsd.getSoLuong())
                    .append("donGia", dvsd.getDonGia())
                    .append("tenDV", dvsd.getTenDV())
                    .append("ngaySuDung", dvsd.getNgaySuDung());

            InsertOneResult result = dichVuSuDungCollection.insertOne(doc);
            return result.wasAcknowledged(); // Kiểm tra xem insert có được xác nhận không
        } catch (Exception e) {
            System.out.println("Lỗi xảy ra trong quá trình tạo dịch vụ sử dụng: " + e.getMessage());
            return false; // Trả về false nếu có lỗi
        }
    }

    public DichVuSuDung getDichVuEmbedByMa(int maDichVu) {
        DichVuSuDung dichvu = null;
        Document query = new Document("maDVSD", maDichVu);
        try {
            Document doc = dichVuSuDungCollection.find(query).first();
            if (doc != null) {
                dichvu = DichVuSuDung.fromDocument(doc);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Bắt lỗi nếu có
        }
        return dichvu;
    }
}
