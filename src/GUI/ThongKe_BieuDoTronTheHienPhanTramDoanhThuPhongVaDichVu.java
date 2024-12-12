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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAO.DonDatPhongDAO;
import model.DTO.DonDatPhong;
import model.DTO.PhongEmbed;
import model.MongoDBConnection;
import org.bson.Document;
import org.jfree.chart.title.TextTitle;

public class ThongKe_BieuDoTronTheHienPhanTramDoanhThuPhongVaDichVu extends JPanel {
    private String tongDoanhThu;

    public ThongKe_BieuDoTronTheHienPhanTramDoanhThuPhongVaDichVu(ArrayList<Document> list, Date ngayBatDau, Date ngayKetThuc) {
        // Tạo dataset cho biểu đồ tròn
        Map<String, Long> doanhThu = calculateRevenue(list, ngayBatDau, ngayKetThuc);
        DefaultPieDataset dataset = createDataset(doanhThu);

        // Tạo biểu đồ tròn
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Tỷ Lệ Doanh Thu Phòng và Dịch Vụ", // Tiêu đề biểu đồ
                dataset, // Dataset
                true, // Hiển thị chú thích
                true, // Hiển thị thông tin chi tiết
                false // Không cho phép xuất ra file
        );

        // Thêm ghi chú thời gian vào biểu đồ
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String ghiChu = "Khoảng thời gian: " + sdf.format(ngayBatDau) + " - " + sdf.format(ngayKetThuc);
        TextTitle subtitle = new TextTitle(ghiChu, new Font("Arial", Font.PLAIN, 12));
        pieChart.addSubtitle(subtitle);

        ghiChu = "Tổng doanh thu: " + tongDoanhThu;
        subtitle = new TextTitle(ghiChu, new Font("Arial", Font.PLAIN, 12));
        pieChart.addSubtitle(subtitle);

        // Tạo ChartPanel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(1240, 460));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    private DefaultPieDataset createDataset(Map<String, Long> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        long totalRevenue = data.values().stream().mapToLong(Long::longValue).sum();

        // Sử dụng NumberFormat cho định dạng tiền tệ
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        tongDoanhThu = currencyFormat.format(totalRevenue);

        for (Map.Entry<String, Long> entry : data.entrySet()) {
            String key = entry.getKey();
            long revenue = entry.getValue();

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
            ngayKetThuc = sdf.parse("2024-02-01-00"); // Kết thúc lúc 23 giờ
        } catch (ParseException ex) {
            Logger.getLogger(ThongKe_BieuDoTronTheHienPhanTramDoanhThuPhongVaDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Kết nối MongoDB và vẽ biểu đồ
        MongoDBConnection.connection();
        DonDatPhongDAO ddp = new DonDatPhongDAO(MongoDBConnection.getDatabase());

        // Khởi tạo biểu đồ
        ThongKe_BieuDoTronTheHienPhanTramDoanhThuPhongVaDichVu pieChartPanel =
                new ThongKe_BieuDoTronTheHienPhanTramDoanhThuPhongVaDichVu(
                        ddp.getDoanhThu(ngayBatDau, ngayKetThuc), ngayBatDau, ngayKetThuc
                );

        // Hiển thị biểu đồ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pieChartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Map<String, Long> calculateRevenue(ArrayList<Document> bookingData, Date ngayBatDau, Date ngayKetThuc) {
        long totalRoomRevenue = 0;
        long totalServiceRevenue = 0;

        for (Document booking : bookingData) {
            DonDatPhong ddp = DonDatPhong.fromDocument(booking);

            for (PhongEmbed p : ddp.getPhongs()) {
                // Tính doanh thu từ phòng
                totalRoomRevenue += getDoanhThuTheoKhoangThoiGian(ngayBatDau, ngayKetThuc, p);

                // Tính doanh thu từ dịch vụ
                if (p.getDichVuSuDung() != null) {
                    totalServiceRevenue += p.getDichVuSuDung().stream()
                            .mapToLong(dv -> dv.getDonGia() * dv.getSoLuong())
                            .sum();
                }
            }
        }

        // Lưu trữ doanh thu vào map
        Map<String, Long> revenueMap = new HashMap<>();
        revenueMap.put("Doanh Thu Phòng", totalRoomRevenue);
        revenueMap.put("Doanh Thu Dịch Vụ", totalServiceRevenue);

        return revenueMap;
    }

    public long getDoanhThuTheoKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc, PhongEmbed phong) {
        long doanhThu = 0;
        Date ngayBatDauSuDung = phong.getNgayNhanPhong().after(ngayBatDau) ? phong.getNgayNhanPhong() : ngayBatDau;
        Date ngayKetThucSuDung = phong.getNgayTraPhong().before(ngayKetThuc) ? phong.getNgayTraPhong() : ngayKetThuc;
        phong.setNgayNhanPhong(ngayBatDauSuDung);
        phong.setNgayTraPhong(ngayKetThucSuDung);
        doanhThu = phong.getTienPhong();
        System.out.println(String.format("%s %s %s %s",phong.getMaPhong(), phong.getNgayNhanPhong(), phong.getNgayTraPhong(), doanhThu));
        return doanhThu;
    }
}
