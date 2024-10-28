/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAO.DonDatPhongDAO;
import model.MongoDBConnection;
import org.bson.Document;

public class ThongKe_BieuDoTronTheHienPhanTramDoanhThu extends JPanel {

    public ThongKe_BieuDoTronTheHienPhanTramDoanhThu(ArrayList<Document> list) {
        // Tạo dataset cho biểu đồ tròn
        Map<String, Integer> doanhThu = calculateRevenue(list);
        DefaultPieDataset dataset = createDataset(doanhThu);

        // Tạo biểu đồ tròn
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Tỷ Lệ Doanh Thu", // Tiêu đề biểu đồ
                dataset, // Dataset
                true, // Hiện thị chú thích
                true, // Thể hiện thông tin chi tiết
                false // Không cho phép xuất ra file
        );
        
        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(680, 400));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    private DefaultPieDataset createDataset(Map<String, Integer> data) {
//        DefaultPieDataset dataset = new DefaultPieDataset();
//        
//        for (Map.Entry<String, Integer> entry : data.entrySet()) {
//            System.out.println("Loại phòng: " + entry.getKey() + ", Số lần đặt: " + entry.getValue());
//            dataset.setValue(entry.getKey(), entry.getValue());
//        }
//        return dataset;

        DefaultPieDataset dataset = new DefaultPieDataset();
        int totalRevenue = data.values().stream().mapToInt(Integer::intValue).sum();

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String key = entry.getKey();
            int revenue = entry.getValue();

            // Tính tỷ lệ phần trăm
            double percentage = (double) revenue / totalRevenue * 100;

            // Thêm vào dataset với tỷ lệ phần trăm
            dataset.setValue(key + " (" + String.format("%.2f", percentage) + "%)", revenue);
        }

        return dataset;
    }

    public static void main(String[] args) {
        // Tạo JFrame để hiển thị biểu đồ
        JFrame frame = new JFrame("Biểu đồ tròn thể hiện doanh thu");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date ngayBatDau = null;
        try {
            ngayBatDau = sdf.parse("2024-10-23");
        } catch (ParseException ex) {
            Logger.getLogger(ThongKe_BieuDoTronTheHienPhanTramDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date ngayKetThuc = null;
        try {
            ngayKetThuc = sdf.parse("2024-11-23");
        } catch (ParseException ex) {
            Logger.getLogger(ThongKe_BieuDoTronTheHienPhanTramDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        MongoDBConnection.connection();
        DonDatPhongDAO ddp = new DonDatPhongDAO(MongoDBConnection.getDatabase());
        ThongKe_BieuDoTronTheHienPhanTramDoanhThu pieChartPanel = new ThongKe_BieuDoTronTheHienPhanTramDoanhThu(ddp.getDoanhThu(ngayBatDau, ngayKetThuc));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pieChartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Map<String, Integer> calculateRevenue(ArrayList<Document> bookingData) {
        Map<String, Integer> revenueMap = new HashMap<>();

        int totalRoomRevenue = 0;

        for (Document booking : bookingData) {
            // Calculate room revenue from `LoaiPhongs`
            ArrayList<Document> roomTypes = (ArrayList<Document>) booking.get("LoaiPhongs");
            if (roomTypes != null && !roomTypes.isEmpty()) {
                // Access the first element since donGia is in an array
                int roomPrice = roomTypes.get(0).getInteger("donGia", 0);
                totalRoomRevenue += roomPrice;
            }

            // Calculate service revenue from `dichVuSuDung`
            ArrayList<Document> services = (ArrayList<Document>) booking.get("dichVuSuDung");
            for (Document service : services) {
                String serviceName = service.getString("tenDV");
                int servicePrice = service.getInteger("donGia", 0);

                // Update the revenue for each service in the map
                revenueMap.put(serviceName, revenueMap.getOrDefault(serviceName, 0) + servicePrice);
            }
        }

        // Add total room revenue to the map
        revenueMap.put("Phòng", totalRoomRevenue);
        return revenueMap;
    }

}
