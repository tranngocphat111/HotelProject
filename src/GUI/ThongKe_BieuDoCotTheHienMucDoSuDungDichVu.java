package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import org.jfree.chart.title.TextTitle;

public class ThongKe_BieuDoCotTheHienMucDoSuDungDichVu extends JPanel {

    public ThongKe_BieuDoCotTheHienMucDoSuDungDichVu(ArrayList<Document> list, Date ngayBatDau, Date ngayKetThuc) {
        // Tạo dataset từ danh sách Document
        Map<String, Integer> serviceUsage = docToMap(list);
        CategoryDataset dataset = createDataset(serviceUsage);

        // Tạo biểu đồ cột
        JFreeChart barChart = ChartFactory.createBarChart(
                "Số Lượng Sử Dụng Dịch Vụ",
                "Tên dịch vụ",
                "Số lượng",
                dataset,
                PlotOrientation.VERTICAL,
                false, // Không hiển thị chú thích
                true, // Hiển thị thông tin chi tiết
                false // Không xuất URL
        );
        
        // Thêm ghi chú thời gian vào biểu đồ
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String ghiChu = "Khoảng thời gian: " + sdf.format(ngayBatDau) + " - " + sdf.format(ngayKetThuc);
        TextTitle subtitle = new TextTitle(ghiChu, new Font("Arial", Font.PLAIN, 12));
        barChart.addSubtitle(subtitle);

        // Thiết lập trục Y
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(true);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(1200, 440));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    private CategoryDataset createDataset(Map<String, Integer> serviceUsage) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : serviceUsage.entrySet()) {
            String serviceName = entry.getKey();
            int count = entry.getValue();

            System.out.println("Dịch vụ: " + serviceName + ", Số lượng: " + count);
            dataset.addValue(count, "Dịch vụ", serviceName);
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
            Date ngayKetThuc = sdf.parse("2024-02-01");

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Biểu đồ sử dụng dịch vụ");
                ThongKe_BieuDoCotTheHienMucDoSuDungDichVu panel
                        = new ThongKe_BieuDoCotTheHienMucDoSuDungDichVu(ddpDAO.getDoanhThu(ngayBatDau, ngayKetThuc), ngayBatDau, ngayKetThuc);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
        } catch (ParseException ex) {
            Logger.getLogger(ThongKe_BieuDoCotTheHienMucDoSuDungDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Chuyển Document sang Map<String, Map<String, Integer>> để tính toán số
     * lượng dịch vụ trên từng loại phòng
     */
    private Map<String, Integer> docToMap(ArrayList<Document> list) {
        Map<String, Integer> serviceUsage = new HashMap<>();

        for (Document doc : list) {
            List<Document> rooms = (List<Document>) doc.get("phong");

            if (rooms != null && !rooms.isEmpty()) {
                for (Document room : rooms) {
                    List<Document> services = (List<Document>) room.get("dichVuSuDung");

                    if (services != null && !services.isEmpty()) {
                        for (Document service : services) {
                            String serviceName = service.getString("tenDV");
                            int serviceCount = service.getInteger("soLuong", 0);

                            // Cộng dồn số lượng dịch vụ
                            serviceUsage.put(serviceName, serviceUsage.getOrDefault(serviceName, 0) + serviceCount);
                        }
                    }
                }
            }
        }
        return serviceUsage;
    }

}
