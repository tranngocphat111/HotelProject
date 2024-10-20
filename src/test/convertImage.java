/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.DAO.DichVuDAO;
import model.DTO.DichVu;
import model.MongoDBConnection;
import org.bson.Document;
import org.bson.types.Binary;

/**
 *
 * @author ASUS
 */
public class convertImage {
    
    public static byte[] convertImageToBinary(String filePath) throws IOException {
        File f = new File(filePath);
        BufferedImage o = ImageIO.read(f);
        
        
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write(o, "png", b);
        byte[] img = b.toByteArray();
//        for(byte x: img) {
//            System.out.print(x);
//        }
        
        return img;
    }
    public static byte[] convertIconToBinary_1(ImageIcon icon) {
        byte[] bytes = null;
        try{
            BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.setColor(Color.WHITE);
            g.drawString("", 10, 20);
            g.dispose();
            
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", os);
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            
            bytes = bos.toByteArray();
            
        } catch (IOException d) {
            d.printStackTrace();
        }
        return bytes;
    }
    public static byte[] convertIconToBinary_2(ImageIcon icon, int w, int h) {
        byte[] bytes = null;
        try{
            
            int x = w;
            int y = h;
            int ix = icon.getIconWidth();
            int iy = icon.getIconHeight();
            int dx = 0, dy = 0;

            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }
            
            BufferedImage bi = new BufferedImage(dx, dy, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.setColor(Color.WHITE);
            g.drawString("", 10, 20);
            g.dispose();
            
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", os);
            InputStream fis = new ByteArrayInputStream(os.toByteArray());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            
            bytes = bos.toByteArray();
            
        } catch (IOException d) {
            d.printStackTrace();
        }
        return bytes;
    }
    
    
    
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\ASUS\\Projects\\HotelProject\\src\\images\\icon_DichVu_Spa.png");
        BufferedImage o = ImageIO.read(f);
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write(o, "png", b);
        byte[] img = b.toByteArray();
        for(byte x: img) {
            System.out.print(x);
        }
        
        MongoDBConnection model = new MongoDBConnection();
        DichVuDAO dichVuDAO = new DichVuDAO(model.getDatabase());
        
        Document filter = new Document("maDV", 3);
        
        
        Document newValue = new 
                Document("$set", new Document(
                        "hinhAnh", img
                ));
                
        dichVuDAO.getCollection().updateOne(filter, newValue);
        
        
        
        
    }
}
