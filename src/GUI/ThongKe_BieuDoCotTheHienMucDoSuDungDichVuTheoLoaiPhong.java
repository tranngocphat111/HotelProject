package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.DAO.DonDatPhongDAO;
import model.MongoDBConnection;
import org.bson.Document;

public class ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong extends JPanel {

    public ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong(ArrayList<Document> list) {
        // Tạo dataset từ danh sách Document
        Map<String, Integer> bookingCount = docToMap(list);
        CategoryDataset dataset = createDataset(bookingCount);

        // Tạo biểu đồ cột
        JFreeChart barChart = ChartFactory.createBarChart(
                "Số Lượng Đặt Phòng Các Loại",
                "Tên loại phòng",
                "Số lượng",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        // Thiết lập trục Y
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(true);
        yAxis.setRange(0, getMaxValue(bookingCount) + 2);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(1200, 440));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Tạo dữ liệu từ bookingCount Map
     */
    private CategoryDataset createDataset(Map<String, Integer> bookingCount) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : bookingCount.entrySet()) {
            System.out.println("Loại phòng: " + entry.getKey() + ", Số lần đặt: " + entry.getValue());
            dataset.addValue(entry.getValue(), "Loại phòng", entry.getKey());
        }
        return dataset;
    }

    /**
     * Chạy ứng dụng
     */
    public static void main(String[] args) {
        try {
            MongoDBConnection.connection();
            DonDatPhongDAO ddpDAO = new DonDatPhongDAO(MongoDBConnection.getDatabase());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date ngayBatDau = sdf.parse("2024-01-01");
            Date ngayKetThuc = sdf.parse("2024-01-05");

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Biểu đồ loại phòng");
                ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong panel =
                        new ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong(ddpDAO.getDoanhThu(ngayBatDau, ngayKetThuc));

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        } catch (ParseException ex) {
            Logger.getLogger(ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Lấy giá trị lớn nhất trong Map
     */
    private static int getMaxValue(Map<String, Integer> map) {
        return map.values().stream().max(Integer::compare).orElse(0);
    }

    /**
     * Chuyển Document sang Map<String, Integer> để tính toán số lượng đặt phòng
     */
    private Map<String, Integer> docToMap(ArrayList<Document> list) {
        Map<String, Integer> bookingCount = new HashMap<>();

        for (Document doc : list) {
            List<Document> phongs = (List<Document>) doc.get("phong");

            if (phongs != null && !phongs.isEmpty()) {
                for (Document phong : phongs) {
                    String tenLoaiPhong = phong.getString("tenLoaiPhong");
                    if (tenLoaiPhong != null && !tenLoaiPhong.isEmpty()) {
                        bookingCount.put(tenLoaiPhong, bookingCount.getOrDefault(tenLoaiPhong, 0) + 1);
                    }
                }
            }
        }
        return bookingCount;
    }
}
