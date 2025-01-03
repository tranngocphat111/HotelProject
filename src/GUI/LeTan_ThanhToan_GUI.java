/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DichVuSuDungDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.HoaDonDAO;
import model.DAO.KhachHangDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVu;
import model.DTO.DichVuSuDung;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.KhachHang;
import model.DTO.NhanVien;
import model.DTO.PhongEmbed;
import model.DTO.PhongEmbed_HoaDon;
import model.DTO.ThongTinThanhToan;

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
    private NhanVien nhanVien_DangSuDung;
    DichVuSuDungDAO dichVuSuDung_dao = new DichVuSuDungDAO(database);

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_ThanhToan_GUI(NhanVien nhanVien_DangSuDung) {
        this.nhanVien_DangSuDung = nhanVien_DangSuDung;
        initComponents();
        jLabel2.setText(this.nhanVien_DangSuDung.getTenNhanVien());
        ImageScale.setCircularImage(label_Avatar, new ImageScale().getScaledImage1(50, 50, new ImageIcon(nhanVien_DangSuDung.getAnhDaiDien())));

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
        txt_NgayKetThuc = new com.toedter.calendar.JDateChooser();
        txt_NgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_hoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        circlePanel1 = new GUI.CirclePanel_Atatar();
        label_Avatar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(1090, 90, 160, 45);

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
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(1090, 160, 160, 45);

        jLabel16.setBackground(new java.awt.Color(255, 209, 84));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 209, 84));
        jLabel16.setText("Hóa Đơn");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel16);
        jLabel16.setBounds(40, 240, 250, 30);

        jLabel15.setBackground(new java.awt.Color(255, 209, 84));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 209, 84));
        jLabel15.setText("Tìm Hóa Đơn");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel15);
        jLabel15.setBounds(40, 50, 250, 27);

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
                .addGap(17, 17, 17)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(550, 90, 510, 120);

        ThongTinDat1.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDat1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinDat1.setOpaque(false);

        txt_NgayKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayKetThucPropertyChange(evt);
            }
        });

        txt_NgayBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayBatDauPropertyChange(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ngày bắt đầu");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày kết thúc");

        javax.swing.GroupLayout ThongTinDat1Layout = new javax.swing.GroupLayout(ThongTinDat1);
        ThongTinDat1.setLayout(ThongTinDat1Layout);
        ThongTinDat1Layout.setHorizontalGroup(
            ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDat1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        ThongTinDat1Layout.setVerticalGroup(
            ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDat1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat1);
        ThongTinDat1.setBounds(40, 90, 470, 120);

        Table_hoaDon.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Người đại diện", "Ngày Tạo", "Phòng", "Dịch Vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            Table_hoaDon.getColumnModel().getColumn(2).setPreferredWidth(180);
            Table_hoaDon.getColumnModel().getColumn(2).setMaxWidth(180);
        }

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(40, 280, 1220, 470);

        jPanel2.setOpaque(false);
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        label_Avatar.setMaximumSize(new java.awt.Dimension(45, 45));
        label_Avatar.setMinimumSize(new java.awt.Dimension(45, 45));
        label_Avatar.setPreferredSize(new java.awt.Dimension(45, 45));

        javax.swing.GroupLayout circlePanel1Layout = new javax.swing.GroupLayout(circlePanel1);
        circlePanel1.setLayout(circlePanel1Layout);
        circlePanel1Layout.setHorizontalGroup(
            circlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(circlePanel1Layout.createSequentialGroup()
                .addComponent(label_Avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        circlePanel1Layout.setVerticalGroup(
            circlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_Avatar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nguyễn Hoàng Sang");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(circlePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(circlePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(1000, 0, 280, 70);

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
//    public List<HoaDon> getHoaDonTheoCCCD(List<HoaDon> list_HoaDons) {
//        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
//        for (HoaDon hoaDon : list_HoaDons) {
//            DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(hoaDon.getDonDatPhongs());
//            for (KhachHang kh : ddp.getKhachO()) {
//                if (kh.getCCCD().equals(txt_CCCD.getText().trim())) {
//                    list_hoaDonMoi.add(hoaDon);
//                }
//            }
//
//        }
//        return list_hoaDonMoi;
//    }
//
//    public List<HoaDon> getHoaDonTheoTenKhachHang(List<HoaDon> list_HoaDons) {
//        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
//        for (HoaDon hoaDon : list_HoaDons) {
//            DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(hoaDon.getDonDatPhongs());
//            for (KhachHang kh : ddp.getKhachO()) {
//                if (kh.getCCCD().equals(txt_HoTen.getText().trim())) {
//                    list_hoaDonMoi.add(hoaDon);
//                }
//            }
//
//        }
//        return list_hoaDonMoi;
//    }
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
        for (HoaDon hoaDon : list_hoadon) {

            String phong = "";

            for (PhongEmbed_HoaDon p : hoaDon.getThongTinThanhToan().getPhongs()) {
                phong += p.getMaPhong() + ", ";

            }
            String dichVuSuDung = "";

            for (int dv : hoaDon.getThongTinThanhToan().getDichVu()) {
                DichVuSuDung dvsd = dichVuSuDung_dao.getDichVuEmbedByMa(dv);
                dichVuSuDung += dvsd.getTenDV() + "(" + dvsd.getSoLuong() + "), ";
            }

            if (dichVuSuDung.length() > 0) {
                dichVuSuDung = dichVuSuDung.substring(0, dichVuSuDung.length() - 2);
            }

            phong = phong.substring(0, phong.length() - 2);
            model.addRow(new Object[]{
                hoaDon.getMaHoaDon(),
                donDatPhong_dao.getDonDatPhongByMa(hoaDon.getDonDatPhong()).getNguoiDat().getTenKhachHang(),
                sdf.format(hoaDon.getNgayTaoHoaDon()),
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
        txt_NgayBatDau.setDate(null);
        txt_NgayKetThuc.setDate(null);

        list_HoaDonTheoTieuChi = hoadon_dao.getAllHoaDon();
        DocDuLieuLenTableHoaDon(list_HoaDonTheoTieuChi);
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

    public List<HoaDon> timHoaDonTheoNgayTaoDon(List<HoaDon> danhSachHoaDon, Date ngayBatDau, Date ngayKetThuc) {
        // Danh sách chứa các hóa đơn thỏa mãn điều kiện
        List<HoaDon> ketQua = new ArrayList<>();

        // Lọc từng hóa đơn trong danh sách
        for (HoaDon hoaDon : danhSachHoaDon) {
            Date ngayTao = hoaDon.getNgayTaoHoaDon();

            // Kiểm tra nếu ngày tạo nằm trong khoảng ngày bắt đầu và ngày kết thúc
            if (ngayTao != null && !ngayTao.before(ngayBatDau) && !ngayTao.after(ngayKetThuc)) {
                ketQua.add(hoaDon);
            }
        }

        return ketQua;
    }

    public List<HoaDon> timHoaDonTheoCCCD(List<HoaDon> danhSachHoaDon, String CCCD) {
        // Danh sách chứa các hóa đơn thỏa mãn điều kiện
        List<HoaDon> ketQua = new ArrayList<>();

        // Duyệt qua từng hóa đơn
        for (HoaDon hoaDon : danhSachHoaDon) {
            DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(hoaDon.getDonDatPhong());
            if (ddp.getNguoiDat().getCCCD().equals(CCCD)) {
                ketQua.add(hoaDon);
            }
        }

        return ketQua;
    }
    
    public List<HoaDon> timHoaDonTheoHoten(List<HoaDon> danhSachHoaDon, String Hoten) {
        // Danh sách chứa các hóa đơn thỏa mãn điều kiện
        List<HoaDon> ketQua = new ArrayList<>();

        // Duyệt qua từng hóa đơn
        for (HoaDon hoaDon : danhSachHoaDon) {
            DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(hoaDon.getDonDatPhong());
            if (ddp.getNguoiDat().getTenKhachHang().equals(Hoten)) {
                ketQua.add(hoaDon);
            }
        }

        return ketQua;
    }
    private void btn_TimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMousePressed
        // TODO add your handling code here:
        if (txt_NgayBatDau.getDate() != null && txt_NgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được trống");
            return;
        }

        if (txt_NgayBatDau.getDate() == null && txt_NgayKetThuc.getDate() != null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được trống");
            return;
        }

        list_HoaDonTheoTieuChi = hoadon_dao.getAllHoaDon();

        if (txt_NgayBatDau.getDate() != null && txt_NgayKetThuc.getDate() != null) {
            list_HoaDonTheoTieuChi = timHoaDonTheoNgayTaoDon(list_HoaDonTheoTieuChi, txt_NgayBatDau.getDate(), txt_NgayKetThuc.getDate());
        }
        if (!txt_CCCD.getText().isEmpty()) {
            list_HoaDonTheoTieuChi = timHoaDonTheoCCCD(list_HoaDonTheoTieuChi, txt_CCCD.getText());
        }

        if (!txt_HoTen.getText().isEmpty()) {
            list_HoaDonTheoTieuChi = timHoaDonTheoHoten(list_HoaDonTheoTieuChi, txt_HoTen.getText());
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

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        new TrangCaNhan(nhanVien_DangSuDung).setVisible(true);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void txt_NgayBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_NgayBatDauPropertyChange
        // TODO add your handling code here:
        if (txt_NgayBatDau.getDate() == null || txt_NgayKetThuc.getDate() == null) {
            return;
        }

        if (txt_NgayBatDau.getDate().after(txt_NgayKetThuc.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
            txt_NgayBatDau.setDate(new Date(txt_NgayKetThuc.getDate().getTime() - 60 * 60 * 24 * 1000));
            return;
        }
    }//GEN-LAST:event_txt_NgayBatDauPropertyChange

    private void txt_NgayKetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_NgayKetThucPropertyChange
        // TODO add your handling code here:
        if (txt_NgayBatDau.getDate() == null || txt_NgayKetThuc.getDate() == null) {
            return;
        }

        if (txt_NgayBatDau.getDate().after(txt_NgayKetThuc.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            txt_NgayKetThuc.setDate(new Date(txt_NgayBatDau.getDate().getTime() + 60 * 60 * 24 * 1000));
            return;
        }
    }//GEN-LAST:event_txt_NgayKetThucPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    public static javax.swing.JTable Table_hoaDon;
    private javax.swing.JPanel ThongTinDat1;
    private javax.swing.JPanel ThongTinKhachHang;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_Tim;
    private GUI.CirclePanel_Atatar circlePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_Avatar;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_HoTen;
    private com.toedter.calendar.JDateChooser txt_NgayBatDau;
    private com.toedter.calendar.JDateChooser txt_NgayKetThuc;
    // End of variables declaration//GEN-END:variables
}
