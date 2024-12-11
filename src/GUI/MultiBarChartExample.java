/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class MultiBarChartExample extends JFrame {

    public MultiBarChartExample() {
        setTitle("Biểu Đồ Cột Nhiều Dữ Liệu");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Tạo dataset
        DefaultCategoryDataset dataset = createDataset();

        // Tạo biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh Thu Loại Phòng",
                "Loại Phòng",
                "Doanh Thu (VND)",
                dataset,
                PlotOrientation.VERTICAL,
                true,  // Hiển thị chú thích
                true,  // Tooltips
                false  // URLs
        );

        // Tùy chỉnh biểu đồ
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setDomainGridlinesVisible(true);
        

        
        // Tạo biểu đồ Panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    /**
     * Tạo Dataset
     */
    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Thêm dữ liệu
        dataset.addValue(3000000, "Doanh Thu Dịch Vụ", "Suite");
        dataset.addValue(5000000, "Doanh Thu Phòng", "Suite");
        dataset.addValue(2000000, "Tổng Doanh Thu", "Suite");

        dataset.addValue(1500000, "Doanh Thu Dịch Vụ", "Deluxe");
        dataset.addValue(4000000, "Doanh Thu Phòng", "Deluxe");
        dataset.addValue(5500000, "Tổng Doanh Thu", "Deluxe");

        dataset.addValue(2500000, "Doanh Thu Dịch Vụ", "Standard");
        dataset.addValue(3000000, "Doanh Thu Phòng", "Standard");
        dataset.addValue(5500000, "Tổng Doanh Thu", "Standard");

        return dataset;
    }

    /**
     * Chạy Chương Trình
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultiBarChartExample example = new MultiBarChartExample();
            example.setVisible(true);
        });
    }
}
