/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import com.toedter.calendar.JDateChooser;
 
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import model.DTO.DichVuEmbed;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.KhachHang;

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

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_ThanhToan_GUI() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        list_btn.add(btn_LamMoi);
        list_btn.add(btn_Tim);
        list_btn.add(btn_ThanhToan);

        model_DonDatPhong = (DefaultTableModel) Table_DonDatPhong.getModel();
        model = (DefaultTableModel) Table_hoaDon.getModel();
        model.setRowCount(0);
        model_DonDatPhong.setRowCount(0);
//      Căn giữa các phần tử trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        list_DonDatPhong = dondatphong_dao.getAllDonDatPhong();

        list_HoaDon = hoadon_dao.getAllHoaDon();
        list_khachHang = khacHang_dao.getAllKhachHang();

        for (HoaDon hoaDon : list_HoaDon) {
            if (!hoaDon.isTrangThai()) {
                list_HoaDonTheoTrangThai.add(hoaDon);
            }
        }

        checkBox_DaThanhToan.setSelected(false);
        DocDuLieuLenTable(list_HoaDonTheoTrangThai);

//      Thêm các loại phòng vào combobox loại phòng
        list_LoaiPhong = loaiphong_dao.getAllLoaiPhong();

        cb_LoaiPhong.addItem("");
        for (LoaiPhong lp : list_LoaiPhong) {
            cb_LoaiPhong.addItem(lp.getTenLoaiPhong());
        }

//      Thêm các phòng và tầng vào combobox phòng và tầng 
        list_Phong = phong_dao.getAllPhong();

        cb_Phong.addItem("");
        for (Phong p : list_Phong) {
            cb_Phong.addItem(p.getMaPhong() + "");
        }

        Set<Integer> set = new HashSet<>();

        for (Phong p : list_Phong) {
            if (!set.contains(p.getTang())) {
                set.add(p.getTang());
            }
        }
        cb_Tang.addItem("");
        set.forEach(t -> {
            cb_Tang.addItem(t.toString());
        });

//      Set font cho header_hoadon
        JTableHeader header_hoadon = Table_hoaDon.getTableHeader();
        header_hoadon.setPreferredSize(new Dimension(header_hoadon.getPreferredSize().width, 30));
        header_hoadon.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table hóa đơn
        DefaultTableCellRenderer renderer_hoadon = (DefaultTableCellRenderer) header_hoadon.getDefaultRenderer();
        renderer_hoadon.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header hóa đơn
        header_hoadon.setDefaultRenderer(renderer_hoadon);

//      Set font cho header_dondatphong
        JTableHeader header_dondatphong = Table_DonDatPhong.getTableHeader();
        header_dondatphong.setPreferredSize(new Dimension(header_dondatphong.getPreferredSize().width, 30));
        header_dondatphong.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table đơn đặt phòng
        DefaultTableCellRenderer renderer_dondatphong = (DefaultTableCellRenderer) header_dondatphong.getDefaultRenderer();
        renderer_dondatphong.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header đơn đặt phòng
        header_dondatphong.setDefaultRenderer(renderer_dondatphong);

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
        ThongTinDat = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cb_Phong = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cb_Tang = new javax.swing.JComboBox<>();
        cb_LoaiPhong = new javax.swing.JComboBox<>();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_ThanhToan = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        txt_SoDienThoai = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ThongTinDat1 = new javax.swing.JPanel();
        txt_NgayTraPhong = new com.toedter.calendar.JDateChooser();
        txt_NgayNhanPhong = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ThongTinKhachHang1 = new javax.swing.JPanel();
        checkBox_DaThanhToan = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_hoaDon = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_DonDatPhong = new javax.swing.JTable();
        Backgroup = new javax.swing.JLabel();

        setName("page_ThanhToan"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinDat.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinDat.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Loại phòng");

        cb_Phong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Phòng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tầng");

        cb_Tang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        cb_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout ThongTinDatLayout = new javax.swing.GroupLayout(ThongTinDat);
        ThongTinDat.setLayout(ThongTinDatLayout);
        ThongTinDatLayout.setHorizontalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Tang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_LoaiPhong, 0, 238, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, Short.MAX_VALUE))
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        ThongTinDatLayout.setVerticalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat);
        ThongTinDat.setBounds(340, 50, 330, 250);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TimMousePressed(evt);
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
        btn_Tim.setBounds(1080, 180, 160, 50);

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
        btn_LamMoi.setBounds(1080, 100, 160, 50);

        btn_ThanhToan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThanhToan.setkGradientFocus(250);
        btn_ThanhToan.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThanhToanMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Thanh Toán");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThanhToanLayout = new javax.swing.GroupLayout(btn_ThanhToan);
        btn_ThanhToan.setLayout(btn_ThanhToanLayout);
        btn_ThanhToanLayout.setHorizontalGroup(
            btn_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThanhToanLayout.setVerticalGroup(
            btn_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_ThanhToan);
        btn_ThanhToan.setBounds(1080, 730, 160, 50);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CCCD/Passport");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Họ và tên");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Số điện thoại");

        txt_CCCD.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_HoTen.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_SoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(txt_CCCD)
                    .addComponent(txt_SoDienThoai))
                .addGap(0, 53, Short.MAX_VALUE))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(710, 50, 320, 250);

        jLabel16.setBackground(new java.awt.Color(255, 209, 84));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 209, 84));
        jLabel16.setText("Hóa Đơn");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel16);
        jLabel16.setBounds(30, 310, 250, 27);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Danh Sách Đơn Đặt Phòng");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel14);
        jLabel14.setBounds(30, 520, 490, 27);

        jLabel15.setBackground(new java.awt.Color(255, 209, 84));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 209, 84));
        jLabel15.setText("Tìm Hóa Đơn");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel15);
        jLabel15.setBounds(30, 20, 250, 27);

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
                    .addComponent(txt_NgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        ThongTinDat1Layout.setVerticalGroup(
            ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDat1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat1);
        ThongTinDat1.setBounds(30, 50, 260, 160);

        ThongTinKhachHang1.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang1.setOpaque(false);

        checkBox_DaThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_DaThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_DaThanhToan.setText("     Đã Thanh Toán");
        checkBox_DaThanhToan.setToolTipText("");
        checkBox_DaThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_DaThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_DaThanhToan.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_DaThanhToan.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_DaThanhToan.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N

        javax.swing.GroupLayout ThongTinKhachHang1Layout = new javax.swing.GroupLayout(ThongTinKhachHang1);
        ThongTinKhachHang1.setLayout(ThongTinKhachHang1Layout);
        ThongTinKhachHang1Layout.setHorizontalGroup(
            ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHang1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(checkBox_DaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        ThongTinKhachHang1Layout.setVerticalGroup(
            ThongTinKhachHang1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(checkBox_DaThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(ThongTinKhachHang1);
        ThongTinKhachHang1.setBounds(30, 240, 260, 60);

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
                "Mã Hóa Đơn", "Ngày Tạo", "Phòng", "Dịch Vụ", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jScrollPane3.setBounds(30, 340, 1209, 155);

        Table_DonDatPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_DonDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn Đặt Phòng", "Ngày Nhận", "Ngày Trả", "Phòng", "Loại Phòng", "SL Khách", "Dịch Vụ Sử Dụng"
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
        Table_DonDatPhong.setRowHeight(30);
        Table_DonDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_DonDatPhongMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Table_DonDatPhong);
        if (Table_DonDatPhong.getColumnModel().getColumnCount() > 0) {
            Table_DonDatPhong.getColumnModel().getColumn(0).setMaxWidth(180);
            Table_DonDatPhong.getColumnModel().getColumn(1).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(2).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(3).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(4).setMaxWidth(170);
            Table_DonDatPhong.getColumnModel().getColumn(5).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(6).setMaxWidth(300);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 550, 1210, 160);

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

    public List<DonDatPhong> getAllDonDatPhongTheoHoaDon(HoaDon hoaDon) {
        List<DonDatPhong> list_DDP = new ArrayList<>();
        for (DonDatPhong ddp : list_DonDatPhong) {
            if (ddp.getHoaDon() == hoaDon.getMaHoaDon()) {
                list_DDP.add(ddp);
            }
        }
        return list_DDP;
    }

    public List<HoaDon> getHoaDonTheoTang(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
                Phong phong = phong_dao.getPhongByMa(ddp.getPhong().getMaPhong());
                if (phong.getTang() == cb_Tang.getSelectedIndex()) {
                    list_hoaDonMoi.add(hoaDon);
                }
            }
        }

        return list_hoaDonMoi;
    }

    public List<HoaDon> getHoaDonTheoLoaiPhong(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
                Phong phong = phong_dao.getPhongByMa(ddp.getPhong().getMaPhong());
                if (phong.getLoaiPhong() == cb_LoaiPhong.getSelectedIndex()) {
                    list_hoaDonMoi.add(hoaDon);
                }
            }
        }
        return list_hoaDonMoi;
    }

    public List<HoaDon> getHoaDonTheoPhong(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
                if (ddp.getPhong().getMaPhong() == Integer.parseInt(cb_Phong.getSelectedItem().toString())) {
                    list_hoaDonMoi.add(hoaDon);
                }
            }
        }
        return list_hoaDonMoi;
    }

    public List<HoaDon> getHoaDonTheoCCCD(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
                for (KhachHang kh : ddp.getKhachO()) {
                    if (kh.getCCCD().equals(txt_CCCD.getText().trim())) {
                        list_hoaDonMoi.add(hoaDon);
                    }
                }
            }

        }
        return list_hoaDonMoi;
    }

    public List<HoaDon> getHoaDonTheoTenKhachHang(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
                for (KhachHang kh : ddp.getKhachO()) {
                    if (kh.getTenKhachHang().equals(txt_HoTen.getText().trim())) {
                        list_hoaDonMoi.add(hoaDon);
                    }
                }
            }

        }
        return list_hoaDonMoi;
    }

    public List<HoaDon> getHoaDonTheoSDT(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
                for (KhachHang kh : ddp.getKhachO()) {
                    if (kh.getCCCD().equals(txt_SoDienThoai.getText().trim())) {
                        list_hoaDonMoi.add(hoaDon);
                    }
                }
            }

        }
        return list_hoaDonMoi;
    }

    public List<HoaDon> getHoaDonTheoTrangThai(List<HoaDon> list_HoaDons) {
        List<HoaDon> list_hoaDonMoi = new ArrayList<>();
        for (HoaDon hoaDon : list_HoaDons) {
            if (hoaDon.isTrangThai() == checkBox_DaThanhToan.isSelected()) {
                list_hoaDonMoi.add(hoaDon);
            }

        }
        return list_hoaDonMoi;
    }


    private void btn_ThanhToanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanMousePressed

        // TODO add your handling code here:
        int row = Table_hoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần thanh toán");
            return;
        }

        HoaDon hoaDon_update = hoadon_dao.getHoaDonByMa(Integer.parseInt(model.getValueAt(row, 0).toString()));
        if (hoaDon_update.isTrangThai()) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đã thanh toán vui lòng chọn hóa đơn chưa thanh toán");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thanh toán?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        hoaDon_update.setTrangThai(true);
        capNhatTrangThaiDonDatPhong(hoaDon_update);
        hoadon_dao.updateHoaDon(hoaDon_update);
        list_HoaDon = hoadon_dao.getAllHoaDon();

        list_HoaDonTheoTrangThai.clear();
        for (HoaDon hoaDon : list_HoaDon) {
            if (!hoaDon.isTrangThai()) {
                list_HoaDonTheoTrangThai.add(hoaDon);
            }
        }
        model_DonDatPhong.setRowCount(0);
        DocDuLieuLenTable(list_HoaDonTheoTrangThai);
        new LeTan_ThanhToan_HoaDon_GUI(hoaDon_update, (JFrame) this.getParent().getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);
    }//GEN-LAST:event_btn_ThanhToanMousePressed

    public void capNhatTrangThaiDonDatPhong(HoaDon hoaDon) {
        for (DonDatPhong ddp : list_DonDatPhong) {
            if (ddp.getHoaDon() == hoaDon.getMaHoaDon()) {
                ddp.setTrangThai("Đã trả phòng");
                dondatphong_dao.updateDonDatPhong(ddp);
            }
        }
    }

    public String getPhongSuDung(HoaDon hoadon) {
        String danhsachPhong = "";
        for (Integer maDDp : hoadon.getDonDatPhongs()) {
            DonDatPhong ddp = dondatphong_dao.getDonDatPhongByMa(maDDp);
                danhsachPhong = danhsachPhong + ddp.getPhong().getMaPhong() + ", ";
        }
        if (danhsachPhong.length() < 2) {
            return danhsachPhong;
        }
        danhsachPhong = danhsachPhong.substring(0, danhsachPhong.length() - 2);
        return danhsachPhong;

    }

    public List<DichVuEmbed> getAllDichVutheoHoaDon(HoaDon hoaDon) {
        Set<DichVuEmbed> list_dichvu = new HashSet<>();

        for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
            for (DichVuEmbed dv : ddp.getDichVuSuDung()) {
                if (!list_dichvu.contains(dv)) {
                    list_dichvu.add(dv);
                }
            }
        }

        List<DichVuEmbed> list_DICHVU = new ArrayList<>();
        for (DichVuEmbed dv : list_dichvu) {
            list_DICHVU.add(dv);
        }
        return list_DICHVU;
    }

    public void DocDuLieuLenTable(List<HoaDon> list_HoaDon) {
        model.setRowCount(0);
        for (HoaDon hoadon : list_HoaDon) {
            model.addRow(new Object[]{
                hoadon.getMaHoaDon(),
                sdf.format(hoadon.getNgayTaoHoaDon()),
                getPhongSuDung(hoadon),
                getDSDichVu(getAllDichVutheoHoaDon(hoadon)),
                df.format(hoadon.getTongTien()) + " VND"
            });
        }

        for (int i = 0; i < Table_hoaDon.getColumnCount(); i++) {
            Table_hoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    public String getDSDichVu(List<DichVuEmbed> list_DichVu) {
        if (list_DichVu.size() == 0) {
            return "";
        }
        String dsDichVu = "";
        for (DichVuEmbed dv : list_DichVu) {
            dsDichVu = dsDichVu + dv.getTenDV() + ", ";
        }
        dsDichVu = dsDichVu.substring(0, dsDichVu.length() - 2);
        return dsDichVu;
    }

    public void DocDuLieuLenTableDDP(List<DonDatPhong> list_DonDatPhongs) {
        model_DonDatPhong.setRowCount(0);
        for (DonDatPhong ddp : list_DonDatPhongs) {
            model_DonDatPhong.addRow(new Object[]{
                ddp.getMaDonDat(),
                sdf.format(ddp.getNgayNhanPhong()),
                sdf.format(ddp.getNgayTraPhong()),
                ddp.getPhong().getMaPhong(),
                ddp.getPhong().getTenLoaiPhong(),
                ddp.getKhachO().size(),
                getDSDichVu(ddp.getDichVuSuDung())
            });
        }

        for (int i = 0; i < Table_DonDatPhong.getColumnCount(); i++) {
            Table_DonDatPhong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }


    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        txt_CCCD.setText("");
        txt_HoTen.setText("");
        txt_SoDienThoai.setText("");
        txt_NgayNhanPhong.setDate(null);
        txt_NgayTraPhong.setDate(null);
        cb_LoaiPhong.setSelectedIndex(0);
        cb_Phong.setSelectedIndex(0);
        cb_Tang.setSelectedIndex(0);
        list_HoaDonTheoTrangThai.clear();
        for (HoaDon hoaDon : list_HoaDon) {
            if (!hoaDon.isTrangThai()) {
                list_HoaDonTheoTrangThai.add(hoaDon);
            }
        }
        DocDuLieuLenTable(list_HoaDonTheoTrangThai);
        checkBox_DaThanhToan.setSelected(false);
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
        list_HoaDonTheoTieuChi.clear();
        list_HoaDonTheoTieuChi = list_HoaDon;
        list_HoaDonTheoTieuChi = getHoaDonTheoTrangThai(list_HoaDonTheoTieuChi);
        if (cb_LoaiPhong.getSelectedItem().toString().isEmpty()
                && cb_Tang.getSelectedItem().toString().isEmpty()
                && cb_Phong.getSelectedItem().toString().isEmpty()
                && txt_CCCD.getText().trim().isEmpty()
                && txt_HoTen.getText().trim().isEmpty()
                && txt_SoDienThoai.getText().trim().isEmpty()
                && txt_NgayNhanPhong.getDate() == null
                && txt_NgayTraPhong.getDate() == null) {
            DocDuLieuLenTable(list_HoaDonTheoTieuChi);
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

        if (!txt_SoDienThoai.getText().trim().isEmpty() && !txt_SoDienThoai.getText().trim().isEmpty()) {
            String regex_SDT = "(0[3|5|7|8|9])+([0-9]{8})";
            String SDT = txt_SoDienThoai.getText().trim();
            if (!SDT.matches(regex_SDT)) {
                JOptionPane.showMessageDialog(this, "Bắt đầu bằng 09 hoặc 03, 07, 08, 10 chữ số");
                txt_SoDienThoai.requestFocus();
                return;
            }
        }

        if (!cb_LoaiPhong.getSelectedItem().toString().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoLoaiPhong(list_HoaDonTheoTieuChi);
        }

        if (!cb_Tang.getSelectedItem().toString().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoTang(list_HoaDonTheoTieuChi);
        }

        if (!cb_Phong.getSelectedItem().toString().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoPhong(list_HoaDonTheoTieuChi);
        }

        if (!txt_CCCD.getText().trim().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoCCCD(list_HoaDonTheoTieuChi);
        }

        if (!txt_HoTen.getText().trim().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoTenKhachHang(list_HoaDonTheoTieuChi);
        }

        if (!txt_SoDienThoai.getText().trim().isEmpty()) {
            list_HoaDonTheoTieuChi = getHoaDonTheoSDT(list_HoaDonTheoTieuChi);
        }
        DocDuLieuLenTable(list_HoaDonTheoTieuChi);


    }//GEN-LAST:event_btn_TimMousePressed

    private void Table_hoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_hoaDonMousePressed
        // TODO add your handling code here:
        int row = Table_hoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        HoaDon hoaDon = hoadon_dao.getHoaDonByMa(Integer.parseInt(model.getValueAt(row, 0).toString()));
        DocDuLieuLenTableDDP(getAllDonDatPhongTheoHoaDon(hoaDon));
    }//GEN-LAST:event_Table_hoaDonMousePressed

    private void Table_DonDatPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_DonDatPhongMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int maDon = Integer.parseInt(model_DonDatPhong.getValueAt(Table_DonDatPhong.getSelectedRow(), 0).toString());
            DonDatPhong donDatPhong = dondatphong_dao.getDonDatPhongByMa(maDon);
            new LeTan_DonDatPhong_ChiTietDonDatPhong_GUI(donDatPhong, (JFrame) this.getParent().getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);
        }
    }//GEN-LAST:event_Table_DonDatPhongMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    public static javax.swing.JTable Table_DonDatPhong;
    public static javax.swing.JTable Table_hoaDon;
    private javax.swing.JPanel ThongTinDat;
    private javax.swing.JPanel ThongTinDat1;
    private javax.swing.JPanel ThongTinKhachHang;
    private javax.swing.JPanel ThongTinKhachHang1;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_ThanhToan;
    private keeptoo.KGradientPanel btn_Tim;
    private javax.swing.JComboBox<String> cb_LoaiPhong;
    private javax.swing.JComboBox<String> cb_Phong;
    private javax.swing.JComboBox<String> cb_Tang;
    private javax.swing.JCheckBox checkBox_DaThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_HoTen;
    private com.toedter.calendar.JDateChooser txt_NgayNhanPhong;
    private com.toedter.calendar.JDateChooser txt_NgayTraPhong;
    private javax.swing.JTextField txt_SoDienThoai;
    // End of variables declaration//GEN-END:variables
}
