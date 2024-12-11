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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAO.DonDatPhongDAO;
import model.DTO.DonDatPhong;
import model.DTO.Phong;
import model.DTO.PhongEmbed;
import model.MongoDBConnection;
import org.bson.Document;
import org.jfree.chart.title.TextTitle;

public class ThongKe_BieuDoTronTheHienPhanTramDoanhThuLoaiPhong extends JPanel {

    public ThongKe_BieuDoTronTheHienPhanTramDoanhThuLoaiPhong(ArrayList<Document> list, Date ngayBatDau, Date ngayKetThuc) {
        // Tạo dataset cho biểu đồ tròn
        Map<String, Integer> doanhThu = calculateRevenue(list, ngayBatDau, ngayKetThuc);
        DefaultPieDataset dataset = createDataset(doanhThu);

        // Tạo biểu đồ tròn
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Tỷ Lệ Doanh Thu", // Tiêu đề biểu đồ
                dataset, // Dataset
                true, // Hiện thị chú thích
                true, // Thể hiện thông tin chi tiết
                false // Không cho phép xuất ra file
        );

        // Thêm ghi chú thời gian vào biểu đồ
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String ghiChu = "Khoảng thời gian: " + sdf.format(ngayBatDau) + " - " + sdf.format(ngayKetThuc);
        TextTitle subtitle = new TextTitle(ghiChu, new Font("Arial", Font.PLAIN, 12));
        pieChart.addSubtitle(subtitle);

        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(1240, 460));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    private DefaultPieDataset createDataset(Map<String, Integer> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int totalRevenue = data.values().stream().mapToInt(Integer::intValue).sum();

        // Sử dụng NumberFormat cho định dạng tiền tệ
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String key = entry.getKey();
            int revenue = entry.getValue();

            // Định dạng số tiền
            String formattedRevenue = currencyFormat.format(revenue);

            // Tính tỷ lệ phần trăm
            double percentage = (double) revenue / totalRevenue * 100;

            // Thêm vào dataset với tỷ lệ phần trăm và doanh thu đã định dạng
            dataset.setValue(
                    key + " (" + formattedRevenue + " - " + String.format("%.2f", percentage) + "%)",
                    revenue
            );
        }

        return dataset;
    }

    public static void main(String[] args) {
        // Tạo JFrame để hiển thị biểu đồ
        JFrame frame = new JFrame("Biểu đồ tròn thể hiện doanh thu");

        // Tạo format yyyy-MM-dd-HH
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");

        Date ngayBatDau = null;
        Date ngayKetThuc = null;

        try {
            ngayBatDau = sdf.parse("2024-01-01-00");  // Bắt đầu từ 00 giờ
            ngayKetThuc = sdf.parse("2024-01-05-23"); // Kết thúc lúc 23 giờ
        } catch (ParseException ex) {
            Logger.getLogger(ThongKe_BieuDoTronTheHienPhanTramDoanhThuLoaiPhong.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Kết nối MongoDB và vẽ biểu đồ
        MongoDBConnection.connection();
        DonDatPhongDAO ddp = new DonDatPhongDAO(MongoDBConnection.getDatabase());

        // Khởi tạo biểu đồ
        ThongKe_BieuDoTronTheHienPhanTramDoanhThuLoaiPhong pieChartPanel =
                new ThongKe_BieuDoTronTheHienPhanTramDoanhThuLoaiPhong(
                        ddp.getDoanhThu(ngayBatDau, ngayKetThuc), ngayBatDau, ngayKetThuc
                );

        // Hiển thị biểu đồ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pieChartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Map<String, Integer> calculateRevenue(ArrayList<Document> bookingData, Date ngayBatDau, Date ngayKetThuc) {
        Map<String, Integer> revenueMap = new HashMap<>();

        for (Document booking : bookingData) {
            DonDatPhong ddp = DonDatPhong.fromDocument(booking);
            for (PhongEmbed p : ddp.getPhongs()) {
                revenueMap.put(
                        p.getTenLoaiPhong(),
                        revenueMap.getOrDefault(p.getTenLoaiPhong(), 0) + getDoanhThuTheoKhoangThoiGian(ngayBatDau, ngayKetThuc, p)
                );
            }

        }
        return revenueMap;
    }

    public int getDoanhThuTheoKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc, PhongEmbed phong) {
        int doanhThu = 0;
        Date ngayBatDauSuDung = phong.getNgayNhanPhong().after(ngayBatDau) ? phong.getNgayNhanPhong() : ngayBatDau;
        Date ngayKetThucSuDung = phong.getNgayTraPhong().before(ngayKetThuc) ? phong.getNgayTraPhong() : ngayKetThuc;
        phong.setNgayNhanPhong(ngayBatDauSuDung);
        phong.setNgayTraPhong(ngayKetThucSuDung);
        doanhThu = phong.getTienPhong();
        System.out.println(String.format("%s %s %s %s",phong.getMaPhong(), phong.getNgayNhanPhong(), phong.getNgayTraPhong(), doanhThu));
        return doanhThu;
    }
}
