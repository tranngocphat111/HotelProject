/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_DonDatPhong_GUI.Table_DonDatPhong;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DonDatPhongDAO;
import model.DAO.HoaDonDAO;
import model.DAO.KhachHangDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVu;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.KhachHang;
import model.DTO.PhongEmbed;

/**
 *
 * @author Admin
 */
public class LeTan_ThanhToan_GUI extends javax.swing.JInternalFrame {

    private ArrayList<KGradientPanel> list_btn = new ArrayList<>();
    public static DefaultTableModel model;
    public static DefaultTableModel model_DonDatPhong;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<LoaiPhong> list_LoaiPhong = new ArrayList<>();
    private LoaiPhongDAO loaiphong_dao = new LoaiPhongDAO(database);
    private List<Phong> list_Phong = new ArrayList<>();
    private PhongDAO phong_dao = new PhongDAO(database);
    private List<HoaDon> list_HoaDon = new ArrayList<>();
    private List<HoaDon> list_HoaDonTheoTrangThai = new ArrayList<>();
    private HoaDonDAO hoadon_dao = new HoaDonDAO(database);
    public List<DonDatPhong> list_DonDatPhong = new ArrayList<>();
    public static DonDatPhongDAO dondatphong_dao = new DonDatPhongDAO(database);
    public static DefaultTableCellRenderer centerRenderer;
    public static DecimalFormat df = new DecimalFormat("#,##0");
    private List<HoaDon> list_HoaDonTheoTieuChi = new ArrayList<>();
    private List<KhachHang> list_khachHang = new ArrayList<>();
    private KhachHangDAO khacHang_dao = new KhachHangDAO(database);
    DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database);

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_ThanhToan_GUI() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        list_HoaDonTheoTrangThai = hoadon_dao.getAllHoaDon();
        list_btn.add(btn_LamMoi);
        list_btn.add(btn_Tim);

        model = (DefaultTableModel) Table_hoaDon.getModel();
        model.setRowCount(0);
//      Căn giữa các phần tử trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        Table_hoaDon.setSelectionBackground(new Color(255, 222, 89));
        Table_hoaDon.setSelectionForeground(new Color(0, 0, 0));
        Table_hoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model = (DefaultTableModel) Table_hoaDon.getModel();
//      Set font cho header_phong
        JTableHeader header_phong = Table_hoaDon.getTableHeader();
        header_phong.setPreferredSize(new Dimension(header_phong.getPreferredSize().width, 30));
        header_phong.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table phòng
        DefaultTableCellRenderer renderer_phong = (DefaultTableCellRenderer) header_phong.getDefaultRenderer();
        renderer_phong.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header phòng
        header_phong.setDefaultRenderer(renderer_phong);

        DocDuLieuLenTableHoaDon(list_HoaDonTheoTrangThai);

//      Thêm các loại phòng vào combobox loại phòng
        list_LoaiPhong = loaiphong_dao.getAllLoaiPhong();

//      Thêm các phòng và tầng vào combobox phòng và tầng 
        list_Phong = phong_dao.getAllPhong();

        Set<Integer> set = new HashSet<>();

        for (Phong p : list_Phong) {
            if (!set.contains(p.getTang())) {
                set.add(p.getTang());
            }
        }

//      Set font cho header_dondatphong
        list_btn.forEach((btn) -> {
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setkStartColor(new java.awt.Color(255, 225, 27));
                    btn.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn.setBorder(null);

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setkStartColor(new java.awt.Color(225, 176, 27));
                    btn.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn.setBorder(null);
                }
            });
        });
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
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_HoTen = new javax.swing.JTextField();
        txt_CCCD = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ThongTinDat1 = new javax.swing.JPanel();
        txt_NgayTraPhong = new com.toedter.calendar.JDateChooser();
        txt_NgayNhanPhong = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_hoaDon = new javax.swing.JTable();
        Backgroup = new javax.swing.JLabel();

        setName("page_ThanhToan"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TimMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_TimMouseReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Tìm ");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_TimLayout = new javax.swing.GroupLayout(btn_Tim);
        btn_Tim.setLayout(btn_TimLayout);
        btn_TimLayout.setHorizontalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(910, 280, 160, 50);

        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LamMoiMousePressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Làm Mới");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(1090, 280, 160, 50);

        jLabel16.setBackground(new java.awt.Color(255, 209, 84));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 209, 84));
        jLabel16.setText("Hóa Đơn");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel16);
        jLabel16.setBounds(30, 310, 250, 27);

        jLabel15.setBackground(new java.awt.Color(255, 209, 84));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 209, 84));
        jLabel15.setText("Tìm Hóa Đơn");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel15);
        jLabel15.setBounds(30, 20, 250, 27);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Họ và tên");

        txt_HoTen.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_HoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HoTenActionPerformed(evt);
            }
        });

        txt_CCCD.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CCCD/Passport");

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(550, 90, 510, 130);

        ThongTinDat1.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDat1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinDat1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ngày nhận phòng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày trả phòng");

        javax.swing.GroupLayout ThongTinDat1Layout = new javax.swing.GroupLayout(ThongTinDat1);
        ThongTinDat1.setLayout(ThongTinDat1Layout);
        ThongTinDat1Layout.setHorizontalGroup(
            ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDat1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        ThongTinDat1Layout.setVerticalGroup(
            ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDat1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat1);
        ThongTinDat1.setBounds(40, 90, 470, 130);

        Table_hoaDon.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Ngày Tạo", "Phòng", "Dịch Vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_hoaDon.setFocusable(false);
        Table_hoaDon.setRowHeight(30);
        Table_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_hoaDonMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(Table_hoaDon);
        if (Table_hoaDon.getColumnModel().getColumnCount() > 0) {
            Table_hoaDon.getColumnModel().getColumn(0).setPreferredWidth(120);
            Table_hoaDon.getColumnModel().getColumn(0).setMaxWidth(120);
            Table_hoaDon.getColumnModel().getColumn(1).setPreferredWidth(180);
            Table_hoaDon.getColumnModel().getColumn(1).setMaxWidth(180);
        }

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(30, 340, 1220, 430);

        Backgroup.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1283, 803);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1288, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 842, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public List<HoaDon> getHoaDonTheoTang(List<HoaDon> list_HoaDons) {
//        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
//        for (HoaDon hoaDon : list_HoaDons) {
//            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
//                Phong phong = phong_dao.getPhongByMa(ddp.getPhong().getMaPhong());
//                if (phong.getTang() == cb_Tang.getSelectedIndex()) {
//                    list_hoaDonMoi.add(hoaDon);
//                }
//            }
//        }
//
//        return list_hoaDonMoi;
//    }
//    public List<HoaDon> getHoaDonTheoLoaiPhong(List<HoaDon> list_HoaDons) {
//        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
//        for (HoaDon hoaDon : list_HoaDons) {
//            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
//                Phong phong = phong_dao.getPhongByMa(ddp.getPhong().getMaPhong());
//                if (phong.getLoaiPhong() == cb_LoaiPhong.getSelectedIndex()) {
//                    list_hoaDonMoi.add(hoaDon);
//                }
//            }
//        }
//        return list_hoaDonMoi;
//    }
//    public List<HoaDon> getHoaDonTheoPhong(List<HoaDon> list_HoaDons) {
//        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
//        for (HoaDon hoaDon : list_HoaDons) {
//            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
//                if (ddp.getPhong().getMaPhong() == Integer.parseInt(cb_Phong.getSelectedItem().toString())) {
//                    list_hoaDonMoi.add(hoaDon);
//                }
//            }
//        }
//        return list_hoaDonMoi;
//    }
    public List<HoaDon> getHoaDonTheoCCCD(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(hoaDon.getDonDatPhongs());
            for (KhachHang kh : ddp.getKhachO()) {
                if (kh.getCCCD().equals(txt_CCCD.getText().trim())) {
                    list_hoaDonMoi.add(hoaDon);
                }
            }

        }
        return list_hoaDonMoi;
    }

    public List<HoaDon> getHoaDonTheoTenKhachHang(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(hoaDon.getDonDatPhongs());
            for (KhachHang kh : ddp.getKhachO()) {
                if (kh.getCCCD().equals(txt_HoTen.getText().trim())) {
                    list_hoaDonMoi.add(hoaDon);
                }
            }

        }
        return list_hoaDonMoi;
    }

//    public List<HoaDon> getHoaDonTheoSDT(List<HoaDon> list_HoaDons) {
//        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
//        for (HoaDon hoaDon : list_HoaDons) {
//            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
//                for (KhachHang kh : ddp.getKhachO()) {
//                    if (kh.getCCCD().equals(txt_SoDienThoai.getText().trim())) {
//                        list_hoaDonMoi.add(hoaDon);
//                    }
//                }
//            }
//
//        }
//        return list_hoaDonMoi;
//    }
//    public List<HoaDon> getHoaDonTheoTrangThai(List<HoaDon> list_HoaDons) {
//        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
//        for (HoaDon hoaDon : list_HoaDons) {
//            if (hoaDon.isTrangThai() == checkBox_DaThanhToan.isSelected()) {
//                list_hoaDonMoi.add(hoaDon);
//            }
//
//        }
//        return list_hoaDonMoi;
//    }
    public void DocDuLieuLenTableHoaDon(List<HoaDon> list_hoadon) {
        model.setRowCount(0);
        for (HoaDon ddp : list_hoadon) {
            DonDatPhong dp = donDatPhong_dao.getDonDatPhongByMa(ddp.getDonDatPhongs());
            String phong = "";
            StringBuilder dichVuSuDung = new StringBuilder();
            if (dp != null) {
                Map<String, Integer> dichVuMap = new HashMap<>(); // Lưu trữ dịch vụ và số lượng

                for (PhongEmbed p : dp.getPhongs()) {
                    phong += p.getMaPhong() + ", ";
                    if (p.getDichVuSuDung() != null) {
                        for (DichVuSuDungEmbed dv : p.getDichVuSuDung()) {
                            // Cộng dồn số lượng nếu dịch vụ đã tồn tại
                            dichVuMap.put(dv.getTenDV(), dichVuMap.getOrDefault(dv.getTenDV(), 0) + dv.getSoLuong());
                        }
                    }
                }

                // Loại bỏ dấu phẩy cuối cùng trong danh sách phòng
                if (!phong.isEmpty()) {
                    phong = phong.substring(0, phong.length() - 2);
                }

                // Xây dựng chuỗi dịch vụ từ Map
                for (Map.Entry<String, Integer> entry : dichVuMap.entrySet()) {
                    dichVuSuDung.append(entry.getKey())
                            .append(" (x")
                            .append(entry.getValue())
                            .append("), ");
                }

                // Loại bỏ dấu phẩy cuối cùng trong danh sách dịch vụ
                if (dichVuSuDung.length() > 0) {
                    dichVuSuDung.setLength(dichVuSuDung.length() - 2);
                }
            }

            model.addRow(new Object[]{
                ddp.getMaHoaDon(),
                sdf.format(ddp.getNgayTaoHoaDon()),
                phong,
                dichVuSuDung
            });
        }

        for (int i = 0; i < Table_hoaDon.getColumnCount(); i++) {
            Table_hoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }


    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        txt_CCCD.setText("");
        txt_HoTen.setText("");
        txt_NgayNhanPhong.setDate(null);
        txt_NgayTraPhong.setDate(null);
        list_HoaDonTheoTrangThai.clear();
        for (HoaDon hoaDon : list_HoaDon) {
            if (!hoaDon.isTrangThai()) {
                list_HoaDonTheoTrangThai.add(hoaDon);
            }
        }
        DocDuLieuLenTableHoaDon(list_HoaDonTheoTrangThai);
        model_DonDatPhong.setRowCount(0);
    }//GEN-LAST:event_btn_LamMoiMousePressed

    private void setThoiGianBang0(JDateChooser ngay) {
        if (ngay.getDate() != null) {
            // Sử dụng Calendar để làm sạch phần thời gian
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngay.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            // Lấy lại đối tượng Date với thời gian đã được làm sạch
            ngay.setDate(calendar.getTime());
        }
    }

    public Date getNgayHienTai() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date ngayhientai = calendar.getTime();
        return ngayhientai;
    }
    private void btn_TimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMousePressed
        // TODO add your handling code here:
//        list_HoaDonTheoTieuChi.clear();
//        list_HoaDonTheoTieuChi = list_HoaDon;
//        list_HoaDonTheoTieuChi = getHoaDonTheoTrangThai(list_HoaDonTheoTieuChi);
        if (txt_CCCD.getText().trim().isEmpty()
                && txt_HoTen.getText().trim().isEmpty()
                && txt_NgayNhanPhong.getDate() == null
                && txt_NgayTraPhong.getDate() == null) {
//            DocDuLieuLenTable(list_HoaDonTheoTieuChi);
            return;
        }

        if (txt_NgayNhanPhong.getDate() == null && txt_NgayTraPhong.getDate() == null) {
        } else {
            if (txt_NgayNhanPhong.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Không được để trống Ngày nhận phòng");
                return;
            }

            if (txt_NgayTraPhong.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Không được để trống Ngày trả phòng");
                return;
            }

            setThoiGianBang0(txt_NgayNhanPhong);
            setThoiGianBang0(txt_NgayTraPhong);
            //        Bắt regex ngày nhận phòng
            if (txt_NgayNhanPhong.getDate().equals(txt_NgayTraPhong.getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày nhận phòng không được bằng Ngày trả");
                txt_NgayNhanPhong.setDate(new Date(txt_NgayTraPhong.getDate().getTime() - (24 * 60 * 60 * 1000)));
                return;

            }

            if (txt_NgayNhanPhong.getDate().after(txt_NgayTraPhong.getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày nhận phòng phải trước Ngày trả");
                txt_NgayNhanPhong.setDate(new Date(txt_NgayTraPhong.getDate().getTime() - (24 * 60 * 60 * 1000)));
                setThoiGianBang0(txt_NgayNhanPhong);
                return;

            }

            if (getNgayHienTai().after(txt_NgayNhanPhong.getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày nhận phòng phải sau ngày hôm nay");
                txt_NgayNhanPhong.setDate(getNgayHienTai());
                setThoiGianBang0(txt_NgayNhanPhong);
            }

//      Bắt regex ngày trả phòng
            if (txt_NgayTraPhong.getDate().before(txt_NgayNhanPhong.getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày trả phải sau Ngày nhận phòng");
                txt_NgayTraPhong.setDate(new Date(txt_NgayNhanPhong.getDate().getTime() + (24 * 60 * 60 * 1000)));
                setThoiGianBang0(txt_NgayTraPhong);
                return;

            }

            if (txt_NgayTraPhong.getDate().equals(txt_NgayNhanPhong.getDate())) {
                JOptionPane.showMessageDialog(null, "Ngày trả ko được bằng Ngày nhận phòng");
                txt_NgayTraPhong.setDate(new Date(txt_NgayNhanPhong.getDate().getTime() + (24 * 60 * 60 * 1000)));
                return;
            }
            if (txt_NgayNhanPhong.getDate() != null && txt_NgayTraPhong.getDate() != null) {
                list_HoaDonTheoTieuChi = hoadon_dao.getHoaDonTheoNgay(txt_NgayNhanPhong.getDate(), txt_NgayTraPhong.getDate());
            }

        }

//        Bắt regex thông tin khách hàng
        String regex_CCCD = "[0-9]{12}";
        if (!txt_CCCD.getText().trim().matches(regex_CCCD) && !txt_CCCD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CCCD phải đủ 12 số, không phải chứa các kí tự đặc biệt và chữ");
            txt_CCCD.requestFocus();
            return;
        }

        String regex_HoTen = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹý'-]+(\\s+[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹý'-]+)+$";
        if (!txt_HoTen.getText().trim().matches(regex_HoTen) && !txt_HoTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên ít nhất 2 từ, không chứa số và các kí tự đặc biệt");
            txt_HoTen.requestFocus();
            return;
        }

        if (!txt_CCCD.getText().trim().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoCCCD(list_HoaDonTheoTieuChi);
        }

        if (!txt_HoTen.getText().trim().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoTenKhachHang(list_HoaDonTheoTieuChi);
        }

        DocDuLieuLenTableHoaDon(list_HoaDonTheoTieuChi);


    }//GEN-LAST:event_btn_TimMousePressed

    private void Table_hoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_hoaDonMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int maDon = Integer.parseInt(model.getValueAt(Table_hoaDon.getSelectedRow(), 0).toString());
            HoaDon hoadon = hoadon_dao.getHoaDonByMa(maDon);

            LeTan_ThanhToan_ChiTietHoaDon_GUI leTan_ThanhToan_ChiTietHoaDon_GUI1 = new LeTan_ThanhToan_ChiTietHoaDon_GUI(hoadon);
            leTan_ThanhToan_ChiTietHoaDon_GUI1.setVisible(true);

        }
    }//GEN-LAST:event_Table_hoaDonMousePressed

    private void btn_TimMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TimMouseReleased

    private void txt_HoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HoTenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    public static javax.swing.JTable Table_hoaDon;
    private javax.swing.JPanel ThongTinDat1;
    private javax.swing.JPanel ThongTinKhachHang;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_Tim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_HoTen;
    private com.toedter.calendar.JDateChooser txt_NgayNhanPhong;
    private com.toedter.calendar.JDateChooser txt_NgayTraPhong;
    // End of variables declaration//GEN-END:variables
}
