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
import model.DAO.KhachHangDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.KhachHang;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.PhongEmbed;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_NhanDon_GUI extends javax.swing.JInternalFrame {

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
    List<KhachHang> list_KhachHang_db = new ArrayList<>();
    List<KhachHang> list_KhachHang = new ArrayList<>();
    KhachHangDAO khachHang_dao = new KhachHangDAO(database);

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_DonDatPhong_NhanDon_GUI(DonDatPhong ddp) {
        this.ddp = ddp;
        list_Phong_filter = ddp.getPhongs();
        list_KhachHang_db = khachHang_dao.getAllKhachHang();
        initComponents();
        list_KhachHang = ddp.getKhachO();
        Table_KhachHang.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                // Kiểm tra nếu không còn sự kiện đang thay đổi
                if (Table_KhachHang.getSelectedRow() == -1) {
                    return;
                } else {
                    int selectedRow = Table_KhachHang.getSelectedRow();
                    txt_CCCD.setText(model_KhachHang.getValueAt(selectedRow, 1).toString());
                    txt_Hoten.setText(model_KhachHang.getValueAt(selectedRow, 2).toString());
                    txt_SDT.setText(model_KhachHang.getValueAt(selectedRow, 3).toString());
                    txt_Email.setText(model_KhachHang.getValueAt(selectedRow, 4).toString());
                    cb_GioiTinh.setSelectedItem(model_KhachHang.getValueAt(selectedRow, 5).toString());
                    cb_QuocTich.setSelectedItem(model_KhachHang.getValueAt(selectedRow, 6).toString());
                }
            }
        });

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
        model_KhachHang = (DefaultTableModel) Table_KhachHang.getModel();
//      Set font cho header_phong
        JTableHeader header_khachHang = Table_KhachHang.getTableHeader();
        header_khachHang.setPreferredSize(new Dimension(header_phong.getPreferredSize().width, 30));
        header_khachHang.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table phòng
        DefaultTableCellRenderer renderer_KhachHang = (DefaultTableCellRenderer) header_khachHang.getDefaultRenderer();
        renderer_KhachHang.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header phòng
        header_khachHang.setDefaultRenderer(renderer_KhachHang);

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();

        DocDuLieuLenTablePhong(ddp.getPhongs());
        if (ddp.getKhachO().size() != 0) {
            DocDuLieuLenTableKhachHang(ddp.getKhachO());
        }

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

    public void DocDuLieuLenTableKhachHang(List<KhachHang> list_KH) {
        model_KhachHang.setRowCount(0);

        for (KhachHang kh : list_KH) {

            model_KhachHang.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getCCCD(),
                kh.getTenKhachHang(),
                kh.getSoDienThoai(),
                kh.getEmail(),
                kh.getGioiTinh(),
                kh.getQuocTich()
            });
        }

        // Định dạng cột trong bảng (nếu cần)
        for (int i = 0; i < Table_KhachHang.getColumnCount(); i++) {
            Table_KhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer_KhachHang);
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
        Scroll_KhachHang = new javax.swing.JScrollPane();
        Table_KhachHang = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        line = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        ThongTinKhachHang1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cb_GioiTinh = new javax.swing.JComboBox<>();
        txt_CCCD = new javax.swing.JTextField();
        txt_Hoten = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cb_QuocTich = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txt_SDT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel23 = new javax.swing.JLabel();
        btn_Them = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel24 = new javax.swing.JLabel();
        Scroll_Phong = new javax.swing.JScrollPane();
        Table_Phong = new javax.swing.JTable();
        btn_Thoat = new keeptoo.KGradientPanel();
        jLabel26 = new javax.swing.JLabel();
        btn_XacNhan = new keeptoo.KGradientPanel();
        jLabel28 = new javax.swing.JLabel();
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
                "Mã khách hàng", "CCCD", "Họ và tên", "Số điện thoại", "Email", "Giới tính", "Quốc tịch"
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

        cb_GioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        txt_CCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CCCDActionPerformed(evt);
            }
        });

        txt_Hoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HotenActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Họ và tên");

        cb_QuocTich.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_QuocTich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Việt Nam", "Mỹ", "Pháp", "Đức" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Quốc tịch");

        txt_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Số điện thoại");

        txt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailActionPerformed(evt);
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
                .addGap(39, 39, 39)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(txt_Email))
                .addGap(67, 67, 67)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_Hoten)
                    .addComponent(cb_GioiTinh, 0, 217, Short.MAX_VALUE))
                .addGap(18, 63, Short.MAX_VALUE)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cb_QuocTich, 0, 215, Short.MAX_VALUE)
                    .addComponent(txt_SDT))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        ThongTinKhachHang1Layout.setVerticalGroup(
            ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_QuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                        .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang1);
        ThongTinKhachHang1.setBounds(40, 370, 860, 160);

        btn_LamMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LamMoiMousePressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Làm Mới");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(1100, 460, 140, 45);

        btn_Them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setkGradientFocus(250);
        btn_Them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Them.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Thêm");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThemLayout = new javax.swing.GroupLayout(btn_Them);
        btn_Them.setLayout(btn_ThemLayout);
        btn_ThemLayout.setHorizontalGroup(
            btn_ThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThemLayout.setVerticalGroup(
            btn_ThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Them);
        btn_Them.setBounds(940, 390, 140, 45);

        btn_Xoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Xoa.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Xoa.setkGradientFocus(250);
        btn_Xoa.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Xoa.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_XoaMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Xóa");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_XoaLayout = new javax.swing.GroupLayout(btn_Xoa);
        btn_Xoa.setLayout(btn_XoaLayout);
        btn_XoaLayout.setHorizontalGroup(
            btn_XoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_XoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_XoaLayout.setVerticalGroup(
            btn_XoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel1.add(btn_Xoa);
        btn_Xoa.setBounds(1100, 390, 140, 45);

        btn_Sua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Sua.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Sua.setkGradientFocus(250);
        btn_Sua.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Sua.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Sua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_SuaMousePressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Sửa");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_SuaLayout = new javax.swing.GroupLayout(btn_Sua);
        btn_Sua.setLayout(btn_SuaLayout);
        btn_SuaLayout.setHorizontalGroup(
            btn_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_SuaLayout.setVerticalGroup(
            btn_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Sua);
        btn_Sua.setBounds(940, 460, 140, 45);

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
        jLabel26.setText("Hủy");
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
        btn_Thoat.setBounds(940, 730, 140, 45);

        btn_XacNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_XacNhan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_XacNhan.setkGradientFocus(250);
        btn_XacNhan.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_XacNhan.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_XacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_XacNhanMousePressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Nhận Đơn");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_XacNhanLayout = new javax.swing.GroupLayout(btn_XacNhan);
        btn_XacNhan.setLayout(btn_XacNhanLayout);
        btn_XacNhanLayout.setHorizontalGroup(
            btn_XacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_XacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_XacNhanLayout.setVerticalGroup(
            btn_XacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_XacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_XacNhan);
        btn_XacNhan.setBounds(1100, 730, 140, 45);

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

    public boolean validateInput(String cccd, String hoTen, String soDienThoai, String email) {

        if (cccd == null || cccd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CCCD không được để trống.");
            txt_CCCD.requestFocus();
            return false;
        } else if (cccd.length() < 12 || cccd.length() > 12) {
            JOptionPane.showMessageDialog(null, "CCCD phải có 12 số.");
            txt_CCCD.requestFocus();
            return false;
        }

        if (hoTen == null || hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ tên không được để trống.");
            txt_Hoten.requestFocus();
            return false;
        } else if (!hoTen.matches("^[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯ][a-zàáâãèéêìíòóôõùúăđĩũơưạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹý]+( [A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯ][a-zàáâãèéêìíòóôõùúăđĩũơưạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹý]+)*$")) {
            JOptionPane.showMessageDialog(null, "Họ tên phải viết hoa ở đầu mỗi chữ.");
            txt_Hoten.requestFocus();
            return false;
        }

        if (soDienThoai == null || soDienThoai.isEmpty()) {

        } else if (soDienThoai.length() < 10 || soDienThoai.length() > 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số.");
            txt_SDT.requestFocus();
            return false;
        }

        if (email == null || email.isEmpty()) {

        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Email phải đúng cấu trúc example1@gmail.com");
            txt_Email.requestFocus();
            return false;
        }

        return true;
    }

    public boolean ktraAllChuyenTrangThaicuaDon(DonDatPhong don, String trangThai) {
        for (PhongEmbed p : don.getPhongs()) {
            if (!p.getTrangThaiPhong().equals(trangThai)) {
                return false;
            }
        }

        return true;
    }


    private void Table_KhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_KhachHangMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_Table_KhachHangMousePressed

    private void txt_CCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CCCDActionPerformed

    private void txt_HotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HotenActionPerformed

    private void txt_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SDTActionPerformed

    private void txt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailActionPerformed

    private void btn_ThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMousePressed
        // TODO add your handling code here:

        if (validateInput(txt_CCCD.getText(), txt_Hoten.getText(), txt_SDT.getText(), txt_Email.getText())) {
            //        Kiểm tra khách hàng cũ hay mới
//            for (KhachHang kh : list_KhachHang_db){
//                if(txt_CCCD.getText().equals(kh.getCCCD())){
//                    tam = kh.getMaKhachHang();
//                }
//            }

            KhachHang kh = new KhachHang(
                    list_KhachHang_db.getLast().getMaKhachHang() + 1,
                    txt_Hoten.getText(),
                    txt_SDT.getText(),
                    txt_CCCD.getText(),
                    cb_QuocTich.getSelectedItem().toString(),
                    cb_GioiTinh.getSelectedItem().toString(),
                    txt_Email.getText());

            if (khachHang_dao.getKhachHangByCCCD(kh.getCCCD()) != null) {
                kh.setMaKhachHang(khachHang_dao.getKhachHangByCCCD(kh.getCCCD()).getMaKhachHang());
                khachHang_dao.updateKhachHang(kh.getMaKhachHang(), kh);

            } else {
                khachHang_dao.createKhachHang(kh);
            }
            boolean kt = false;
            if (list_KhachHang.size() != 0) {
                for (KhachHang khh : list_KhachHang) {
                    if (khh.getCCCD().equals(txt_CCCD.getText())) {
                        kt = true;
                    }
                }
            }
            if (kt == false) {
                list_KhachHang.add(kh);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Trùng CCCD");
                txt_CCCD.setText("");
                txt_Hoten.setText("");
                txt_SDT.setText("");
                txt_Email.setText("");
                cb_GioiTinh.setSelectedItem("Nam");
                cb_QuocTich.setSelectedItem("Việt Nam");
                Table_KhachHang.clearSelection();
                return;
            }

            DocDuLieuLenTableKhachHang(list_KhachHang);

            txt_CCCD.setText("");
            txt_Hoten.setText("");
            txt_SDT.setText("");
            txt_Email.setText("");
            cb_GioiTinh.setSelectedItem("Nam");
            cb_QuocTich.setSelectedItem("Việt Nam");

        }


    }//GEN-LAST:event_btn_ThemMousePressed

    private void btn_XoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMousePressed
        // TODO add your handling code here:
        if (Table_KhachHang.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            KhachHang KhachHangTam = new KhachHang();
            for (KhachHang kh : list_KhachHang) {
                if (kh.getMaKhachHang() == Integer.parseInt(model_KhachHang.getValueAt(Table_KhachHang.getSelectedRow(), 0).toString())) {
                    KhachHangTam = kh;
                }
            }

            list_KhachHang.remove(KhachHangTam);
            DocDuLieuLenTableKhachHang(list_KhachHang);
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            txt_CCCD.setText("");
            txt_Hoten.setText("");
            txt_SDT.setText("");
            txt_Email.setText("");
            cb_GioiTinh.setSelectedItem("Nam");
            cb_QuocTich.setSelectedItem("Việt Nam");

        }

    }//GEN-LAST:event_btn_XoaMousePressed

    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        txt_CCCD.setText("");
        txt_Hoten.setText("");
        txt_SDT.setText("");
        txt_Email.setText("");
        cb_GioiTinh.setSelectedItem("Nam");
        cb_QuocTich.setSelectedItem("Việt Nam");
        Table_KhachHang.clearSelection();
    }//GEN-LAST:event_btn_LamMoiMousePressed

    private void btn_SuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMousePressed
        // TODO add your handling code here:
        if (Table_KhachHang.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        if (validateInput(txt_CCCD.getText(), txt_Hoten.getText(), txt_SDT.getText(), txt_Email.getText())) {
            for (int i = 0; i < list_KhachHang.size(); i++) {
                if (list_KhachHang.get(i).getMaKhachHang() == Integer.parseInt(model_KhachHang.getValueAt(Table_KhachHang.getSelectedRow(), 0).toString())) {
                    list_KhachHang.get(i).setCCCD(txt_CCCD.getText());
                    list_KhachHang.get(i).setTenKhachHang(txt_Hoten.getText());
                    list_KhachHang.get(i).setSoDienThoai(txt_SDT.getText());
                    list_KhachHang.get(i).setEmail(txt_Email.getText());
                    list_KhachHang.get(i).setGioiTinh(cb_GioiTinh.getSelectedItem().toString());
                    list_KhachHang.get(i).setQuocTich(cb_QuocTich.getSelectedItem().toString());
                }
            }

            DocDuLieuLenTableKhachHang(list_KhachHang);
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        }

    }//GEN-LAST:event_btn_SuaMousePressed

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

    private void btn_XacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMousePressed
        // TODO add your handling code here:
        
        if(list_KhachHang.size() == 0){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập khách hàng");
            return;
        }
        
        ddp.setKhachO(list_KhachHang);
        donDatPhong_dao.updateNguoiO(ddp.getMaDonDat(), list_KhachHang);
        donDatPhong_dao.updateTrangThaiDon(ddp.getMaDonDat(), "Xử lý");
        for(PhongEmbed phong : ddp.getPhongs()){
            if (phong.getTrangThaiPhong().equals("Đang chờ")) {
                donDatPhong_dao.updateTrangThaiPhong(ddp.getMaDonDat(), phong.getMaPhong(), "Đang ở");
                donDatPhong_dao.updateNgayNhanPhong(ddp.getMaDonDat(), phong.getMaPhong(), setThoiGian0(new Date()));
            }
        }
        
        LeTan_DonDatPhong_GUI.list_DonDatPhong = donDatPhong_dao.getAllDonDatPhong();
        if (cb_trangthaidon.getSelectedItem().toString().equals("Tất cả")) {
            DocDuLieuLenTableDonDatPhong(list_DonDatPhong);
        } else {
            DocDuLieuLenTableDonDatPhong(timDonDatPhongTheoTrangThai(cb_trangthaidon.getSelectedItem() + "", list_DonDatPhong));

        }
        jDesktopPane1.remove(this);
        LeTan_GUI.donDatPhong_Gui.setVisible(true);
    }//GEN-LAST:event_btn_XacNhanMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JScrollPane Scroll_KhachHang;
    private javax.swing.JScrollPane Scroll_Phong;
    public static javax.swing.JTable Table_KhachHang;
    public static javax.swing.JTable Table_Phong;
    private javax.swing.JPanel ThongTinKhachHang;
    private javax.swing.JPanel ThongTinKhachHang1;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Them;
    private keeptoo.KGradientPanel btn_Thoat;
    private keeptoo.KGradientPanel btn_XacNhan;
    private keeptoo.KGradientPanel btn_Xoa;
    private javax.swing.JComboBox<String> cb_GioiTinh;
    private javax.swing.JComboBox<String> cb_LoaiPhong;
    private javax.swing.JComboBox<String> cb_Phong;
    private javax.swing.JComboBox<String> cb_QuocTich;
    private javax.swing.JComboBox<String> cb_Tang;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel line;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_Hoten;
    private javax.swing.JTextField txt_SDT;
    // End of variables declaration//GEN-END:variables
}
