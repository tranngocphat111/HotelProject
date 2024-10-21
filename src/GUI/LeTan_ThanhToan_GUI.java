/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DonDatPhongDAO;
import model.DAO.HoaDonDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class LeTan_ThanhToan_GUI extends javax.swing.JInternalFrame {

    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    public static MongoDBConnection database = new MongoDBConnection();
    public static DefaultTableModel model;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<LoaiPhong> list_LoaiPhong = new ArrayList<LoaiPhong>();
    private LoaiPhongDAO loaiphong_dao = new LoaiPhongDAO(database.getDatabase());
    private List<Phong> list_Phong = new ArrayList<Phong>();
    private PhongDAO phong_dao = new PhongDAO(database.getDatabase());
    private List<HoaDon> list_HoaDon = new ArrayList<HoaDon>();
    private HoaDonDAO hoadon_dao = new HoaDonDAO(database.getDatabase());
    public static List<DonDatPhong> list_DonDatPhong = new ArrayList<DonDatPhong>();
    public static DonDatPhongDAO dondatphong_dao = new DonDatPhongDAO(database.getDatabase());
    public static DefaultTableCellRenderer centerRenderer;

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

        list_DonDatPhong = dondatphong_dao.getAllDonDatPhong();
        list_HoaDon = hoadon_dao.getAllHoaDon();
        model = (DefaultTableModel) Table_hoaDon.getModel();
        model.setRowCount(0);
//      Căn giữa các phần tử trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        DocDuLieuLenTable(list_HoaDon);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_DonDatPhong = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ThongTinDat1 = new javax.swing.JPanel();
        txt_NgayDi = new com.toedter.calendar.JDateChooser();
        txt_NgayDen = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ThongTinKhachHang1 = new javax.swing.JPanel();
        checkBox_DaThanhToan = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_hoaDon = new javax.swing.JTable();
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
        cb_Phong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_PhongActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Phòng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tầng");

        cb_Tang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_Tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TangActionPerformed(evt);
            }
        });

        cb_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_LoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaiPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinDatLayout = new javax.swing.GroupLayout(ThongTinDat);
        ThongTinDat.setLayout(ThongTinDatLayout);
        ThongTinDatLayout.setHorizontalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Tang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_LoaiPhong, 0, 238, Short.MAX_VALUE)
                            .addComponent(cb_Phong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, Short.MAX_VALUE))))
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
                .addGap(3, 3, 3)
                .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat);
        ThongTinDat.setBounds(340, 50, 330, 250);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Tìm");
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
        btn_Tim.setBounds(1080, 160, 160, 50);

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
        btn_LamMoi.setBounds(1080, 80, 160, 50);

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
        txt_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDienThoaiActionPerformed(evt);
            }
        });

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
        jLabel16.setBounds(30, 320, 250, 27);

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
                "Mã Hóa Đơn", "Ngày Đến", "Ngày Đi", "Phòng", "Loại Phòng", "Số Lượng", "Dịch Vụ"
            }
        ));
        Table_DonDatPhong.setRowHeight(30);
        jScrollPane1.setViewportView(Table_DonDatPhong);
        if (Table_DonDatPhong.getColumnModel().getColumnCount() > 0) {
            Table_DonDatPhong.getColumnModel().getColumn(0).setPreferredWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(0).setMaxWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(1).setPreferredWidth(180);
            Table_DonDatPhong.getColumnModel().getColumn(1).setMaxWidth(180);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 550, 1209, 155);

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
        jLabel1.setText("Ngày đến");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày đi");

        javax.swing.GroupLayout ThongTinDat1Layout = new javax.swing.GroupLayout(ThongTinDat1);
        ThongTinDat1.setLayout(ThongTinDat1Layout);
        ThongTinDat1Layout.setHorizontalGroup(
            ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDat1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        ThongTinDat1Layout.setVerticalGroup(
            ThongTinDat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDat1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        checkBox_DaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_DaThanhToanActionPerformed(evt);
            }
        });

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
        ));
        Table_hoaDon.setRowHeight(30);
        jScrollPane3.setViewportView(Table_hoaDon);
        if (Table_hoaDon.getColumnModel().getColumnCount() > 0) {
            Table_hoaDon.getColumnModel().getColumn(0).setPreferredWidth(120);
            Table_hoaDon.getColumnModel().getColumn(0).setMaxWidth(120);
            Table_hoaDon.getColumnModel().getColumn(1).setPreferredWidth(180);
            Table_hoaDon.getColumnModel().getColumn(1).setMaxWidth(180);
        }

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(30, 350, 1209, 155);

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

    private void cb_PhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_PhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_PhongActionPerformed

    private void cb_TangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_TangActionPerformed

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void cb_LoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_LoaiPhongActionPerformed

    private void checkBox_DaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_DaThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_DaThanhToanActionPerformed

    private void btn_ThanhToanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanMousePressed
        // TODO add your handling code here:
        new HoaDon_GUI().setVisible(true);
    }//GEN-LAST:event_btn_ThanhToanMousePressed

    public static String getPhongSuDung(HoaDon hoadon) {

        String danhsachPhong = "";
        for (DonDatPhong ddp : list_DonDatPhong) {
            if (ddp.getHoaDon() == hoadon.getMaHoaDon()) {
                danhsachPhong = danhsachPhong + ddp.getPhong() + ", ";
            }
        }
        danhsachPhong = danhsachPhong.substring(0, danhsachPhong.length() - 2);

        return danhsachPhong;
    }

    public static void DocDuLieuLenTable(List<HoaDon> list_HoaDon) {
        model.setRowCount(0);
        for (HoaDon hoadon : list_HoaDon) {
            model.addRow(new Object[]{
                hoadon.getMaHoaDon(),
                sdf.format(hoadon.getNgayTaoHoaDon()),
                getPhongSuDung(hoadon),
                "Dich Vu",
                "Tong Tien"});
        }

        for (int i = 0; i < Table_hoaDon.getColumnCount(); i++) {
            Table_hoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }


    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        txt_CCCD.setText("");
        txt_HoTen.setText("");
        txt_SoDienThoai.setText("");
        txt_NgayDen.setDate(null);
        txt_NgayDi.setDate(null);
        cb_LoaiPhong.setSelectedIndex(0);
        cb_Phong.setSelectedIndex(0);
        cb_Tang.setSelectedIndex(0);
    }//GEN-LAST:event_btn_LamMoiMousePressed


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
    private com.toedter.calendar.JDateChooser txt_NgayDen;
    private com.toedter.calendar.JDateChooser txt_NgayDi;
    private javax.swing.JTextField txt_SoDienThoai;
    // End of variables declaration//GEN-END:variables
}
