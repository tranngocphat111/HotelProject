package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;

public class ThongKe_BieuDoCotTheHienMucDoSuDungLoaiPhong extends JPanel {

    public ThongKe_BieuDoCotTheHienMucDoSuDungLoaiPhong(ArrayList<Document> list) {
        // Tạo dataset
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
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    private CategoryDataset createDataset(Map<String, Integer> bookingCount) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : bookingCount.entrySet()) {
            System.out.println("Loại phòng: " + entry.getKey() + ", Số lần đặt: " + entry.getValue());
            dataset.addValue(entry.getValue(), "Loại phòng", entry.getKey());
        }
        return dataset;
    }

    public static void main(String[] args) {
        try {
            MongoDBConnection.connection();
            DonDatPhongDAO ddpDAO = new DonDatPhongDAO(MongoDBConnection.getDatabase());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            Date ngayBatDau = sdf.parse("2024-10-23");
            Date ngayKetThuc = sdf.parse("2024-11-23");
            SwingUtilities.invokeLater(() -> {
                // Tạo JFrame để hiển thị JPanel
                JFrame frame = new JFrame("Biểu đồ loại phòng");
                ThongKe_BieuDoCotTheHienMucDoSuDungLoaiPhong panel = 
                        new ThongKe_BieuDoCotTheHienMucDoSuDungLoaiPhong(ddpDAO.getDonDatPhongTheoNgay(ngayBatDau, ngayKetThuc));
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        } catch (ParseException ex) {
            Logger.getLogger(ThongKe_BieuDoCotTheHienMucDoSuDungLoaiPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int getMaxValue(Map<String, Integer> map) {
        int maxValue = Integer.MIN_VALUE; // Khởi tạo với giá trị nhỏ nhất
        for (Integer value : map.values()) {
            if (value > maxValue) {
                maxValue = value; // Cập nhật giá trị tối đa
            }
        }
        return maxValue;
    }

    private Map<String, Integer> docToMap(ArrayList<Document> list) {
        Map<String, Integer> bookingCount = new HashMap<>();
        for (Document doc : list) {
            // Lấy giá trị tên loại phòng (giả sử chỉ có 1 giá trị)
            ArrayList<String> tenLoaiPhong = (ArrayList<String>) doc.get("tenLoaiPhong");

            // Kiểm tra nếu tenLoaiPhong không rỗng
            if (!tenLoaiPhong.isEmpty()) {
                String loaiPhong = tenLoaiPhong.get(0); // Lấy giá trị đầu tiên
                // Cập nhật số lần đặt phòng cho loại phòng
                bookingCount.put(loaiPhong, bookingCount.getOrDefault(loaiPhong, 0) + 1);
            }
        }
        return bookingCount;
    }
}
