/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import model.DTO.DichVuSuDung;

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
}
