/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_DonDatPhong_GUI.DocDuLieuLenTableDonDatPhong;
import static GUI.LeTan_DonDatPhong_GUI.Table_DonDatPhong;
import static GUI.LeTan_DonDatPhong_GUI.cb_trangthaidon;
import static GUI.LeTan_DonDatPhong_GUI.list_DonDatPhong;
import static GUI.LeTan_DonDatPhong_GUI.timDonDatPhongTheoTrangThai;
import static GUI.LeTan_GUI.jDesktopPane1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.DAO.DonDatPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.PhongEmbed;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_PhongCuaDon_GUI extends javax.swing.JInternalFrame {

    private DefaultTableCellRenderer centerRenderer_Phong;
    private DefaultTableModel model_Phong = new DefaultTableModel();
    private DefaultTableCellRenderer centerRenderer_KhachHang;
    private DefaultTableModel model_KhachHang = new DefaultTableModel();
    public static DecimalFormat df = new DecimalFormat("#,##0");
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database);
    DonDatPhong ddp;
    PhongDAO phong_Dao = new PhongDAO(database);
    List<PhongEmbed> list_Phong_filter = new ArrayList<>();

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_DonDatPhong_PhongCuaDon_GUI(DonDatPhong ddp) {
        this.ddp = ddp;
        list_Phong_filter = ddp.getPhongs();
        initComponents();

        Set<String> list_LoaiPhong = new HashSet<>();
        for (PhongEmbed phong : ddp.getPhongs()) {
            if (!list_LoaiPhong.contains(phong.getTenLoaiPhong())) {
                list_LoaiPhong.add(phong.getTenLoaiPhong());
            }
        }
        cb_LoaiPhong.addItem("Tất cả");
        for (String loaiPhong : list_LoaiPhong) {
            cb_LoaiPhong.addItem(loaiPhong);
        }

        Set<Integer> list_Tang = new HashSet<>();
        for (PhongEmbed phong : ddp.getPhongs()) {
            if (!list_Tang.contains(phong_Dao.getPhongByMa(phong.getMaPhong()).getTang())) {
                list_Tang.add(phong_Dao.getPhongByMa(phong.getMaPhong()).getTang());
            }
        }
        cb_Tang.addItem("Tất cả");
        for (Integer tang : list_Tang) {
            cb_Tang.addItem(tang + "");
        }

        Set<Integer> list_Phong = new HashSet<>();
        for (PhongEmbed phong : ddp.getPhongs()) {
            if (!list_Phong.contains(phong.getMaPhong())) {
                list_Phong.add(phong.getMaPhong());
            }
        }

        cb_Phong.addItem("Tất cả");
        for (Integer phong : list_Phong) {
            cb_Phong.addItem(phong + "");
        }

//        Customer table_Phong
        Scroll_Phong.setVerticalScrollBar(new ScrollBarCustom());
        Scroll_Phong.getVerticalScrollBar().setUnitIncrement(80);
        centerRenderer_Phong = new DefaultTableCellRenderer();
        centerRenderer_Phong.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer_Phong.setVerticalAlignment(JLabel.CENTER);
        Table_Phong.setSelectionBackground(new Color(255, 222, 89));
        Table_Phong.setSelectionForeground(new Color(0, 0, 0));
        model_Phong = (DefaultTableModel) Table_Phong.getModel();
//      Set font cho header_phong
        JTableHeader header_phong = Table_Phong.getTableHeader();
        header_phong.setPreferredSize(new Dimension(header_phong.getPreferredSize().width, 25));
        header_phong.setFont(new Font("Arial", Font.BOLD, 12));

//      Căn giữa cho header table phòng
        DefaultTableCellRenderer renderer_phong = (DefaultTableCellRenderer) header_phong.getDefaultRenderer();
        renderer_phong.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header phòng
        header_phong.setDefaultRenderer(renderer_phong);

        //        Customer table_KhachHang
        Scroll_KhachHang.setVerticalScrollBar(new ScrollBarCustom());
        Scroll_KhachHang.getVerticalScrollBar().setUnitIncrement(80);
        centerRenderer_KhachHang = new DefaultTableCellRenderer();
        centerRenderer_KhachHang.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer_KhachHang.setVerticalAlignment(JLabel.CENTER);
        Table_KhachHang.setSelectionBackground(new Color(255, 222, 89));
        Table_KhachHang.setSelectionForeground(new Color(0, 0, 0));
        Table_KhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model_KhachHang = (DefaultTableModel) Table_Phong.getModel();
//      Set font cho header_phong
        JTableHeader header_khachHang = Table_KhachHang.getTableHeader();
        header_phong.setPreferredSize(new Dimension(header_phong.getPreferredSize().width, 25));
        header_phong.setFont(new Font("Arial", Font.BOLD, 10));

//      Căn giữa cho header table phòng
        DefaultTableCellRenderer renderer_KhachHang = (DefaultTableCellRenderer) header_phong.getDefaultRenderer();
        renderer_phong.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header phòng
        header_phong.setDefaultRenderer(renderer_phong);

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();

        DocDuLieuLenTablePhong(ddp.getPhongs());
        ui.setNorthPane(null);
    }

    public void DocDuLieuLenTablePhong(List<PhongEmbed> list_Phong) {
        model_Phong.setRowCount(0);

        for (PhongEmbed p : list_Phong) {

            // Tổng hợp dịch vụ sử dụng
            Map<String, Integer> dichVuMap = new HashMap<>(); // Lưu trữ dịch vụ và số lượng

            String dichVuSuDungs = "";

            for (DichVuSuDungEmbed dv : p.getDichVuSuDung()) {
                dichVuSuDungs += dv.getTenDV() + "(" + dv.getSoLuong() + "), ";
            }
            if (dichVuSuDungs.length() > 2) {
                dichVuSuDungs = dichVuSuDungs.substring(0, dichVuSuDungs.length() - 2);
            }

            LocalDate localDateFrom;
            LocalDate localDateTo;
            if (p.getNgayNhanPhong() != null && p.getNgayTraPhong() != null) {
                localDateFrom = p.getNgayNhanPhong().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localDateTo = p.getNgayTraPhong().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } else {
                localDateFrom = p.getNgayNhanPhongDuKien().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                localDateTo = p.getNgayTraPhongDuKien().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            long daysBetween = ChronoUnit.DAYS.between(localDateFrom, localDateTo);

            // Thêm dữ liệu vào bảng
            model_Phong.addRow(new Object[]{
                p.getMaPhong(),
                p.getTrangThaiPhong(),
                p.getTenLoaiPhong(),
                sdf.format(p.getNgayNhanPhongDuKien()),
                sdf.format(p.getNgayTraPhongDuKien()),
                p.getNgayNhanPhong() == null ? "" : sdf.format(p.getNgayNhanPhong()),
                p.getNgayTraPhong() == null ? "" : sdf.format(p.getNgayTraPhong()),
                dichVuSuDungs,
                df.format(p.getDonGia() * daysBetween),
                df.format(p.getTienDaThanhToan())
            });
        }

        // Định dạng cột trong bảng (nếu cần)
        for (int i = 0; i < Table_Phong.getColumnCount(); i++) {
            Table_Phong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer_Phong);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_Tang = new javax.swing.JComboBox<>();
        cb_LoaiPhong = new javax.swing.JComboBox<>();
        cb_Phong = new javax.swing.JComboBox<>();
        btn_HuyDon = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_NhanPhong = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_ThanhToanDon = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_HuyPhong = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_NhanDon = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        Scroll_KhachHang = new javax.swing.JScrollPane();
        Table_KhachHang = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        line = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        ThongTinKhachHang1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_HuyDon2 = new keeptoo.KGradientPanel();
        jLabel23 = new javax.swing.JLabel();
        btn_HuyDon1 = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_ThanhToanDon1 = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_HuyDon4 = new keeptoo.KGradientPanel();
        jLabel25 = new javax.swing.JLabel();
        btn_HuyDon3 = new keeptoo.KGradientPanel();
        jLabel24 = new javax.swing.JLabel();
        Scroll_Phong = new javax.swing.JScrollPane();
        Table_Phong = new javax.swing.JTable();
        btn_Thoat = new keeptoo.KGradientPanel();
        jLabel26 = new javax.swing.JLabel();
        Backgroup = new javax.swing.JLabel();

        setName("page_DonDatPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Loại Phòng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Phòng");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tầng");

        cb_Tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TangActionPerformed(evt);
            }
        });

        cb_LoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaiPhongActionPerformed(evt);
            }
        });

        cb_Phong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_PhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Tang, 0, 166, Short.MAX_VALUE)
                    .addComponent(cb_LoaiPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_Phong, 0, 166, Short.MAX_VALUE))
                .addGap(64, 64, Short.MAX_VALUE))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(40, 60, 200, 210);

        btn_HuyDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon.setkGradientFocus(250);
        btn_HuyDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyDonMousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Thanh Toán");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyDonLayout = new javax.swing.GroupLayout(btn_HuyDon);
        btn_HuyDon.setLayout(btn_HuyDonLayout);
        btn_HuyDonLayout.setHorizontalGroup(
            btn_HuyDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HuyDonLayout.setVerticalGroup(
            btn_HuyDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon);
        btn_HuyDon.setBounds(940, 280, 140, 45);

        btn_NhanPhong.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_NhanPhong.setkGradientFocus(250);
        btn_NhanPhong.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_NhanPhong.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_NhanPhong.setPreferredSize(new java.awt.Dimension(140, 35));
        btn_NhanPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_NhanPhongMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Nhận Phòng");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_NhanPhongLayout = new javax.swing.GroupLayout(btn_NhanPhong);
        btn_NhanPhong.setLayout(btn_NhanPhongLayout);
        btn_NhanPhongLayout.setHorizontalGroup(
            btn_NhanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_NhanPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_NhanPhongLayout.setVerticalGroup(
            btn_NhanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_NhanPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_NhanPhong);
        btn_NhanPhong.setBounds(440, 280, 140, 45);

        btn_ThanhToanDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_ThanhToanDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThanhToanDon.setkGradientFocus(250);
        btn_ThanhToanDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThanhToanDon.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_ThanhToanDon.setPreferredSize(new java.awt.Dimension(140, 45));
        btn_ThanhToanDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThanhToanDonMousePressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Trả Phòng");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_ThanhToanDonLayout = new javax.swing.GroupLayout(btn_ThanhToanDon);
        btn_ThanhToanDon.setLayout(btn_ThanhToanDonLayout);
        btn_ThanhToanDonLayout.setHorizontalGroup(
            btn_ThanhToanDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThanhToanDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThanhToanDonLayout.setVerticalGroup(
            btn_ThanhToanDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel1.add(btn_ThanhToanDon);
        btn_ThanhToanDon.setBounds(1100, 280, 140, 45);

        btn_HuyPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyPhong.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyPhong.setkGradientFocus(250);
        btn_HuyPhong.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyPhong.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyPhong.setPreferredSize(new java.awt.Dimension(140, 45));
        btn_HuyPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyPhongMousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Hủy Phòng");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyPhongLayout = new javax.swing.GroupLayout(btn_HuyPhong);
        btn_HuyPhong.setLayout(btn_HuyPhongLayout);
        btn_HuyPhongLayout.setHorizontalGroup(
            btn_HuyPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btn_HuyPhongLayout.setVerticalGroup(
            btn_HuyPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_HuyPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyPhong);
        btn_HuyPhong.setBounds(610, 280, 140, 45);

        btn_NhanDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_NhanDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_NhanDon.setkGradientFocus(250);
        btn_NhanDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_NhanDon.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_NhanDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_NhanDonMousePressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Thêm dịch vụ");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_NhanDonLayout = new javax.swing.GroupLayout(btn_NhanDon);
        btn_NhanDon.setLayout(btn_NhanDonLayout);
        btn_NhanDonLayout.setHorizontalGroup(
            btn_NhanDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_NhanDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_NhanDonLayout.setVerticalGroup(
            btn_NhanDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_NhanDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_NhanDon);
        btn_NhanDon.setBounds(780, 280, 140, 45);

        Table_KhachHang.setAutoCreateRowSorter(true);
        Table_KhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn", "Trạng Thái", "Ngày Tạo Đơn", "Người Đặt", "Số Lượng Phòng", "Số Lượng Khách", "Dịch Vụ Sử Dụng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_KhachHang.setFocusable(false);
        Table_KhachHang.setRowHeight(30);
        Table_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_KhachHangMousePressed(evt);
            }
        });
        Scroll_KhachHang.setViewportView(Table_KhachHang);
        if (Table_KhachHang.getColumnModel().getColumnCount() > 0) {
            Table_KhachHang.getColumnModel().getColumn(3).setPreferredWidth(110);
            Table_KhachHang.getColumnModel().getColumn(4).setPreferredWidth(110);
        }

        jPanel1.add(Scroll_KhachHang);
        Scroll_KhachHang.setBounds(40, 550, 1200, 160);

        jLabel21.setBackground(new java.awt.Color(255, 209, 84));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 209, 84));
        jLabel21.setText("Tìm phòng");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel21);
        jLabel21.setBounds(20, 20, 150, 27);

        line.setBackground(new java.awt.Color(255, 209, 84));

        javax.swing.GroupLayout lineLayout = new javax.swing.GroupLayout(line);
        line.setLayout(lineLayout);
        lineLayout.setHorizontalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        lineLayout.setVerticalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel1.add(line);
        line.setBounds(200, 345, 1040, 2);

        jLabel27.setBackground(new java.awt.Color(255, 209, 84));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 209, 84));
        jLabel27.setText("Khách lưu trữ");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel27);
        jLabel27.setBounds(40, 330, 160, 27);

        ThongTinKhachHang1.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang1.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CCCD/Passport");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Giới tính");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Họ và tên");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Quốc tịch");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Số điện thoại");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Email");

        javax.swing.GroupLayout ThongTinKhachHang1Layout = new javax.swing.GroupLayout(ThongTinKhachHang1);
        ThongTinKhachHang1.setLayout(ThongTinKhachHang1Layout);
        ThongTinKhachHang1Layout.setHorizontalGroup(
            ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 45, Short.MAX_VALUE)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField3)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        ThongTinKhachHang1Layout.setVerticalGroup(
            ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                        .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang1);
        ThongTinKhachHang1.setBounds(40, 370, 720, 160);

        btn_HuyDon2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyDon2.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon2.setkGradientFocus(250);
        btn_HuyDon2.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon2.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyDon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyDon2MousePressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Làm Mới");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyDon2Layout = new javax.swing.GroupLayout(btn_HuyDon2);
        btn_HuyDon2.setLayout(btn_HuyDon2Layout);
        btn_HuyDon2Layout.setHorizontalGroup(
            btn_HuyDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HuyDon2Layout.setVerticalGroup(
            btn_HuyDon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon2);
        btn_HuyDon2.setBounds(780, 390, 140, 45);

        btn_HuyDon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyDon1.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon1.setkGradientFocus(250);
        btn_HuyDon1.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon1.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyDon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyDon1MousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Thêm");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyDon1Layout = new javax.swing.GroupLayout(btn_HuyDon1);
        btn_HuyDon1.setLayout(btn_HuyDon1Layout);
        btn_HuyDon1Layout.setHorizontalGroup(
            btn_HuyDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HuyDon1Layout.setVerticalGroup(
            btn_HuyDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon1);
        btn_HuyDon1.setBounds(940, 390, 140, 45);

        btn_ThanhToanDon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_ThanhToanDon1.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThanhToanDon1.setkGradientFocus(250);
        btn_ThanhToanDon1.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThanhToanDon1.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_ThanhToanDon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThanhToanDon1MousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Xóa");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_ThanhToanDon1Layout = new javax.swing.GroupLayout(btn_ThanhToanDon1);
        btn_ThanhToanDon1.setLayout(btn_ThanhToanDon1Layout);
        btn_ThanhToanDon1Layout.setHorizontalGroup(
            btn_ThanhToanDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThanhToanDon1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThanhToanDon1Layout.setVerticalGroup(
            btn_ThanhToanDon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel1.add(btn_ThanhToanDon1);
        btn_ThanhToanDon1.setBounds(1100, 390, 140, 45);

        btn_HuyDon4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyDon4.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon4.setkGradientFocus(250);
        btn_HuyDon4.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon4.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyDon4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyDon4MousePressed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Thêm Excel");
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyDon4Layout = new javax.swing.GroupLayout(btn_HuyDon4);
        btn_HuyDon4.setLayout(btn_HuyDon4Layout);
        btn_HuyDon4Layout.setHorizontalGroup(
            btn_HuyDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HuyDon4Layout.setVerticalGroup(
            btn_HuyDon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon4);
        btn_HuyDon4.setBounds(1100, 460, 140, 45);

        btn_HuyDon3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyDon3.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon3.setkGradientFocus(250);
        btn_HuyDon3.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon3.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyDon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyDon3MousePressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Sửa");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyDon3Layout = new javax.swing.GroupLayout(btn_HuyDon3);
        btn_HuyDon3.setLayout(btn_HuyDon3Layout);
        btn_HuyDon3Layout.setHorizontalGroup(
            btn_HuyDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HuyDon3Layout.setVerticalGroup(
            btn_HuyDon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDon3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon3);
        btn_HuyDon3.setBounds(940, 460, 140, 45);

        Table_Phong.setAutoCreateRowSorter(true);
        Table_Phong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phòng", "Trạng Thái ", "Loại Phòng", "Nhận Phòng Dự Kiến", "Trả Phòng Dự Kiến", "Nhận Phòng", "Trả Phòng", "Dịch Vụ ", "Cần Thanh Toán", "Đã Thanh Toán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_Phong.setFocusable(false);
        Table_Phong.setRowHeight(25);
        Table_Phong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_PhongMousePressed(evt);
            }
        });
        Scroll_Phong.setViewportView(Table_Phong);
        if (Table_Phong.getColumnModel().getColumnCount() > 0) {
            Table_Phong.getColumnModel().getColumn(3).setPreferredWidth(110);
            Table_Phong.getColumnModel().getColumn(4).setPreferredWidth(110);
            Table_Phong.getColumnModel().getColumn(8).setPreferredWidth(90);
            Table_Phong.getColumnModel().getColumn(9).setPreferredWidth(90);
        }

        jPanel1.add(Scroll_Phong);
        Scroll_Phong.setBounds(260, 60, 980, 210);

        btn_Thoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Thoat.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Thoat.setkGradientFocus(250);
        btn_Thoat.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Thoat.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Thoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThoatMousePressed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Thoát");
        jLabel26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThoatLayout = new javax.swing.GroupLayout(btn_Thoat);
        btn_Thoat.setLayout(btn_ThoatLayout);
        btn_ThoatLayout.setHorizontalGroup(
            btn_ThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThoatLayout.setVerticalGroup(
            btn_ThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Thoat);
        btn_Thoat.setBounds(1100, 720, 140, 45);

        Backgroup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_DonDatPhong"); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1280, 803);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Date setThoiGian0(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // Đặt ngày hiện tại
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();

    }
    private void btn_NhanPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_NhanPhongMousePressed
        // TODO add your handling code here:
        int row[] = Table_Phong.getSelectedRows();
        if (row.length == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng cần nhận");
            return;
        }
        for (int i = 0; i < row.length; i++) {

            int maPhong = Integer.parseInt(model_Phong.getValueAt(row[i], 0).toString());
            PhongEmbed phong = donDatPhong_dao.getPhongTheoMaPhong(ddp.getMaDonDat(), maPhong);
            if (!phong.getTrangThaiPhong().equals("Đang chờ")) {
                JOptionPane.showMessageDialog(this, "Chỉ có thể nhận phòng có trạng thái Đang chờ");
                return;
            }

        }

        for (int i = 0; i < row.length; i++) {

            int maPhong = Integer.parseInt(model_Phong.getValueAt(row[i], 0).toString());

            donDatPhong_dao.updateNgayNhanPhong(ddp.getMaDonDat(), maPhong, setThoiGian0(new Date()));
            donDatPhong_dao.updateTrangThaiPhong(ddp.getMaDonDat(), maPhong, "Đang ở");

            if (ktraAllChuyenTrangThaicuaDon(donDatPhong_dao.getDonDatPhongByMa(ddp.getMaDonDat()), "Đang ở")) {
                donDatPhong_dao.updateTrangThaiDon(ddp.getMaDonDat(), "Xử lý");
            }

        }

        DocDuLieuLenTablePhong(donDatPhong_dao.getDonDatPhongByMa(ddp.getMaDonDat()).getPhongs());
        JOptionPane.showMessageDialog(this, "Nhận phòng thành công");


    }//GEN-LAST:event_btn_NhanPhongMousePressed

    public boolean ktraAllChuyenTrangThaicuaDon(DonDatPhong don, String trangThai) {
        for (PhongEmbed p : don.getPhongs()) {
            if (!p.getTrangThaiPhong().equals(trangThai)) {
                return false;
            }
        }

        return true;
    }

    private void btn_HuyPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyPhongMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_HuyPhongMousePressed


    private void Table_KhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_KhachHangMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_Table_KhachHangMousePressed

    private void btn_ThanhToanDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanDonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThanhToanDonMousePressed

    private void btn_NhanDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_NhanDonMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_NhanDonMousePressed

    private void btn_HuyDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyDonMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_HuyDonMousePressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btn_HuyDon1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyDon1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HuyDon1MousePressed

    private void btn_ThanhToanDon1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanDon1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThanhToanDon1MousePressed

    private void btn_HuyDon2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyDon2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HuyDon2MousePressed

    private void btn_HuyDon3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyDon3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HuyDon3MousePressed

    private void btn_HuyDon4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyDon4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HuyDon4MousePressed

    private void Table_PhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_PhongMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_Table_PhongMousePressed

    private void btn_ThoatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMousePressed
        // TODO add your handling code here:

        LeTan_DonDatPhong_GUI.list_DonDatPhong = donDatPhong_dao.getAllDonDatPhong();
        if (cb_trangthaidon.getSelectedItem().toString().equals("Tất cả")) {
            DocDuLieuLenTableDonDatPhong(list_DonDatPhong);
        } else {
            DocDuLieuLenTableDonDatPhong(timDonDatPhongTheoTrangThai(cb_trangthaidon.getSelectedItem() + "", list_DonDatPhong));

        }
        jDesktopPane1.remove(this);
        LeTan_GUI.donDatPhong_Gui.setVisible(true);
    }//GEN-LAST:event_btn_ThoatMousePressed

    public List<PhongEmbed> getAllPhongTheoLoaiPhong(List<PhongEmbed> list_phong) {
        List<PhongEmbed> list_PhongMoi = new ArrayList<>();
        for (PhongEmbed phong : list_phong) {
            if (phong.getTenLoaiPhong().equals(cb_LoaiPhong.getSelectedItem().toString())) {
                list_PhongMoi.add(phong);
            }
        }

        return list_PhongMoi;
    }

    public List<PhongEmbed> getAllPhongTheoTang(List<PhongEmbed> list_phong) {
        List<PhongEmbed> list_PhongMoi = new ArrayList<>();
        for (PhongEmbed phong : list_phong) {
            if (phong_Dao.getPhongByMa(phong.getMaPhong()).getTang() == Integer.parseInt(cb_Tang.getSelectedItem().toString())) {
                list_PhongMoi.add(phong);
            }
        }

        return list_PhongMoi;
    }

    public List<PhongEmbed> getAllPhongTheoPhong(List<PhongEmbed> list_phong) {
        List<PhongEmbed> list_PhongMoi = new ArrayList<>();
        for (PhongEmbed phong : list_phong) {
            if (phong.getMaPhong() == Integer.parseInt(cb_Phong.getSelectedItem().toString())) {
                list_PhongMoi.add(phong);
            }
        }

        return list_PhongMoi;
    }

    private void cb_LoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaiPhongActionPerformed
        // TODO add your handling code here:
        if (cb_LoaiPhong.getSelectedItem() == null || cb_Tang.getSelectedItem() == null || cb_Phong.getSelectedItem() == null) {
            return;
        }

        list_Phong_filter = ddp.getPhongs();
        if (!cb_LoaiPhong.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoLoaiPhong(list_Phong_filter);
        }

        if (!cb_Tang.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoTang(list_Phong_filter);
        }

        if (!cb_Phong.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoPhong(list_Phong_filter);
        }

        DocDuLieuLenTablePhong(list_Phong_filter);


    }//GEN-LAST:event_cb_LoaiPhongActionPerformed

    private void cb_TangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TangActionPerformed
        // TODO add your handling code here:
        if (cb_LoaiPhong.getSelectedItem() == null || cb_Tang.getSelectedItem() == null || cb_Phong.getSelectedItem() == null) {
            return;
        }

        list_Phong_filter = ddp.getPhongs();
        if (!cb_LoaiPhong.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoLoaiPhong(list_Phong_filter);
        }

        if (!cb_Tang.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoTang(list_Phong_filter);
        }

        if (!cb_Phong.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoPhong(list_Phong_filter);
        }

        DocDuLieuLenTablePhong(list_Phong_filter);
    }//GEN-LAST:event_cb_TangActionPerformed

    private void cb_PhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_PhongActionPerformed
        // TODO add your handling code here:
        if (cb_LoaiPhong.getSelectedItem() == null || cb_Tang.getSelectedItem() == null || cb_Phong.getSelectedItem() == null) {
            return;
        }

        list_Phong_filter = ddp.getPhongs();
        if (!cb_LoaiPhong.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoLoaiPhong(list_Phong_filter);
        }

        if (!cb_Tang.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoTang(list_Phong_filter);
        }

        if (!cb_Phong.getSelectedItem().toString().equals("Tất cả")) {
            list_Phong_filter = getAllPhongTheoPhong(list_Phong_filter);
        }

        DocDuLieuLenTablePhong(list_Phong_filter);
    }//GEN-LAST:event_cb_PhongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JScrollPane Scroll_KhachHang;
    private javax.swing.JScrollPane Scroll_Phong;
    public static javax.swing.JTable Table_KhachHang;
    public static javax.swing.JTable Table_Phong;
    private javax.swing.JPanel ThongTinKhachHang;
    private javax.swing.JPanel ThongTinKhachHang1;
    private keeptoo.KGradientPanel btn_HuyDon;
    private keeptoo.KGradientPanel btn_HuyDon1;
    private keeptoo.KGradientPanel btn_HuyDon2;
    private keeptoo.KGradientPanel btn_HuyDon3;
    private keeptoo.KGradientPanel btn_HuyDon4;
    private keeptoo.KGradientPanel btn_HuyPhong;
    private keeptoo.KGradientPanel btn_NhanDon;
    private keeptoo.KGradientPanel btn_NhanPhong;
    private keeptoo.KGradientPanel btn_ThanhToanDon;
    private keeptoo.KGradientPanel btn_ThanhToanDon1;
    private keeptoo.KGradientPanel btn_Thoat;
    private javax.swing.JComboBox<String> cb_LoaiPhong;
    private javax.swing.JComboBox<String> cb_Phong;
    private javax.swing.JComboBox<String> cb_Tang;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JPanel line;
    // End of variables declaration//GEN-END:variables
}
