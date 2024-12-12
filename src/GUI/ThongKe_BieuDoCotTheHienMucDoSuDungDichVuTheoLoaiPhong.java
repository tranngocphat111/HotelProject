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

public class ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong extends JPanel {

    public ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong(ArrayList<Document> list, Date ngayBatDau, Date ngayKetThuc) {
        // Tạo dataset từ danh sách Document
        Map<String, Map<String, Integer>> serviceUsage = docToMap(list);
        CategoryDataset dataset = createDataset(serviceUsage);

        // Tạo biểu đồ cột
        JFreeChart barChart = ChartFactory.createBarChart(
                "Số Lượng Sử Dụng Dịch Vụ Theo Loại Phòng",
                "Tên dịch vụ",
                "Số lượng",
                dataset,
                PlotOrientation.VERTICAL,
                true, // Hiển thị chú thích
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

    private CategoryDataset createDataset(Map<String, Map<String, Integer>> serviceUsage) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Map<String, Integer>> entry : serviceUsage.entrySet()) {
            String roomType = entry.getKey();
            Map<String, Integer> serviceCounts = entry.getValue();

            for (Map.Entry<String, Integer> serviceEntry : serviceCounts.entrySet()) {
                String serviceName = serviceEntry.getKey();
                int count = serviceEntry.getValue();

                System.out.println("Loại phòng: " + roomType + ", Dịch vụ: " + serviceName + ", Số lần sử dụng: " + count);
                dataset.addValue(count, roomType, serviceName);
            }
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
                ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong panel =
                        new ThongKe_BieuDoCotTheHienMucDoSuDungDichVuTheoLoaiPhong(ddpDAO.getDoanhThu(ngayBatDau, ngayKetThuc), ngayBatDau, ngayKetThuc);

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
     * Chuyển Document sang Map<String, Map<String, Integer>>
     * để tính toán số lượng dịch vụ trên từng loại phòng
     */
    private Map<String, Map<String, Integer>> docToMap(ArrayList<Document> list) {
        Map<String, Map<String, Integer>> serviceUsage = new HashMap<>();

        for (Document doc : list) {
            List<Document> rooms = (List<Document>) doc.get("phong");

            if (rooms != null && !rooms.isEmpty()) {
                for (Document room : rooms) {
                    String roomType = room.getString("tenLoaiPhong");
                    List<Document> services = (List<Document>) room.get("dichVuSuDung");

                    if (services != null && !services.isEmpty()) {
                        // Lấy danh sách dịch vụ
                        for (Document service : services) {
                            String serviceName = service.getString("tenDV");
                            int serviceCount = service.getInteger("soLuong", 0);

                            // Nếu loại phòng chưa có trong Map, thêm mới
                            serviceUsage.putIfAbsent(roomType, new HashMap<>());

                            // Cộng dồn số lượng dịch vụ
                            Map<String, Integer> serviceCounts = serviceUsage.get(roomType);
                            serviceCounts.put(serviceName, serviceCounts.getOrDefault(serviceName, 0) + serviceCount);
                        }
                    }
                }
            }
        }
        return serviceUsage;
    }
}
