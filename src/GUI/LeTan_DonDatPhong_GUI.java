/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DichVuDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVu;
import model.DTO.DonDatPhong;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_GUI extends javax.swing.JInternalFrame {
    private MongoDBConnection database = new MongoDBConnection();
    private LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database.getDatabase());
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private List<DonDatPhong> list_DonDatPhong = new ArrayList<DonDatPhong>();
    private DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database.getDatabase());
    private PhongDAO phong_dao = new PhongDAO(database.getDatabase());
    private LoaiPhongDAO LoaiPhong_dao = new LoaiPhongDAO(database.getDatabase());
    private List<DichVu> list_DichVu = new ArrayList<DichVu>();
    private DichVuDAO dichVu_dao = new DichVuDAO(database.getDatabase());
    private DefaultTableModel model; 
    DefaultTableCellRenderer centerRenderer;
    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_DonDatPhong_GUI() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        
        JTableHeader header = Table_KhachHang.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));
        
//      căn giữa cho headẻ  
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);
        
        
        //Căn giữa các phần tử trong table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        
//        Đọc dữ liệu từ database lên
        list_DonDatPhong = donDatPhong_dao.getAllDonDatPhong();
        list_DichVu = dichVu_dao.getAllDichVu();
        
        model = (DefaultTableModel)Table_KhachHang.getModel();
        model.setRowCount(0);
        
        DocDuLieuLenTable(list_DonDatPhong);
        
        list_btn.add(btn_Tim);
        list_btn.add(btn_ThemDichVu);
        list_btn.add(btn_NhanPhong);
        list_btn.add(btn_LamMoi);
        list_btn.add(btn_HuyDon);
        
        checkBox_DangO.setSelected(true);
        list_btn.forEach((btn) -> {
            btn.addMouseListener(new MouseListener(){
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
    
    public void DocDuLieuLenTable(List<DonDatPhong> list_DonDatPhongs) {
        for(DonDatPhong ddp : list_DonDatPhongs){
            model.addRow(new Object [] {
                ddp.getMaDonDat(),
                ddp.getTrangThai(),
                ddp.getNgayNhanPhong(),
                ddp.getNgayTraPhong(),
                ddp.getPhong(),
                loaiPhong_dao.getLoaiPhongByMa(phong_dao.getPhongByMa(ddp.getPhong()).getLoaiPhong()),
                ddp.getKhachO().size(),
                getDSDichVu(ddp.getDichVuSuDung())
            });
        }
        
        for (int i = 0; i < Table_KhachHang.getColumnCount(); i++) {
            Table_KhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
         
//      
    }
    
    public String getDSDichVu(List<DichVu> list_DichVu){
        String dsDichVu = "";
        for(DichVu dv : list_DichVu){
            dsDichVu = dsDichVu + dv.getTenDV() + ", ";
        }
        return dsDichVu;
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
        jLabel1 = new javax.swing.JLabel();
        txt_NgayDatPhong_BatDau = new com.toedter.calendar.JDateChooser();
        txt_NgayDatPhong_KetThuc = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        txt_SoDienThoai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cb_LoaiPhong = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cb_Tang = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cb_Phong = new javax.swing.JComboBox<>();
        btn_HuyDon = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_ThemDichVu = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_NhanPhong = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_KhachHang = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        checkBox_DangO = new javax.swing.JCheckBox();
        checkBox_DangCho = new javax.swing.JCheckBox();
        Backgroup = new javax.swing.JLabel();

        setName("page_DonDatPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinDat.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinDat.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ngày đặt phòng");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_MuiTen.png"))); // NOI18N

        javax.swing.GroupLayout ThongTinDatLayout = new javax.swing.GroupLayout(ThongTinDat);
        ThongTinDat.setLayout(ThongTinDatLayout);
        ThongTinDatLayout.setHorizontalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addComponent(txt_NgayDatPhong_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(txt_NgayDatPhong_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        ThongTinDatLayout.setVerticalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_NgayDatPhong_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_NgayDatPhong_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat);
        ThongTinDat.setBounds(70, 90, 580, 120);

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Loại phòng");

        cb_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_LoaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng Deluxe", "Item 2", "Item 3", "Item 4" }));
        cb_LoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaiPhongActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tầng");

        cb_Tang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_Tang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cb_Tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TangActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Phòng");

        cb_Phong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_Phong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "P103", "P104", " " }));
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
                .addGap(33, 33, 33)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txt_CCCD)
                    .addComponent(cb_LoaiPhong, 0, 210, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cb_Tang, 0, 214, Short.MAX_VALUE)
                    .addComponent(txt_HoTen))
                .addGap(111, 111, 111)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addGap(39, 39, 39))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(6, 6, 6)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(cb_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(70, 230, 920, 220);

        btn_HuyDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon.setkGradientFocus(250);
        btn_HuyDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon.setMinimumSize(new java.awt.Dimension(140, 45));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Hủy Đơn");
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
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon);
        btn_HuyDon.setBounds(1050, 390, 140, 40);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.setMinimumSize(new java.awt.Dimension(140, 45));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Tìm");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_TimLayout = new javax.swing.GroupLayout(btn_Tim);
        btn_Tim.setLayout(btn_TimLayout);
        btn_TimLayout.setHorizontalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(1050, 100, 140, 40);

        btn_ThemDichVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_ThemDichVu.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThemDichVu.setkGradientFocus(250);
        btn_ThemDichVu.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThemDichVu.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_ThemDichVu.setPreferredSize(new java.awt.Dimension(140, 45));
        btn_ThemDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThemDichVuMouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Thêm Dịch Vụ");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_ThemDichVuLayout = new javax.swing.GroupLayout(btn_ThemDichVu);
        btn_ThemDichVu.setLayout(btn_ThemDichVuLayout);
        btn_ThemDichVuLayout.setHorizontalGroup(
            btn_ThemDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemDichVuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThemDichVuLayout.setVerticalGroup(
            btn_ThemDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel1.add(btn_ThemDichVu);
        btn_ThemDichVu.setBounds(1050, 260, 140, 45);

        btn_LamMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.setMinimumSize(new java.awt.Dimension(140, 45));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Làm Mới");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(1050, 160, 140, 40);

        btn_NhanPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_NhanPhong.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_NhanPhong.setkGradientFocus(250);
        btn_NhanPhong.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_NhanPhong.setMinimumSize(new java.awt.Dimension(140, 45));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Nhận Phòng");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_NhanPhongLayout = new javax.swing.GroupLayout(btn_NhanPhong);
        btn_NhanPhong.setLayout(btn_NhanPhongLayout);
        btn_NhanPhongLayout.setHorizontalGroup(
            btn_NhanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_NhanPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_NhanPhongLayout.setVerticalGroup(
            btn_NhanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_NhanPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_NhanPhong);
        btn_NhanPhong.setBounds(1050, 330, 140, 40);

        Table_KhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn", "Trạng Thái", "Ngày Đến", "Ngày Đi", "Phòng", "Loại Phòng", "Số Lượng Khách", "Dịch Vụ Sử Dụng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_KhachHang.setRowHeight(30);
        jScrollPane1.setViewportView(Table_KhachHang);
        if (Table_KhachHang.getColumnModel().getColumnCount() > 0) {
            Table_KhachHang.getColumnModel().getColumn(0).setMaxWidth(120);
            Table_KhachHang.getColumnModel().getColumn(1).setMaxWidth(120);
            Table_KhachHang.getColumnModel().getColumn(2).setMaxWidth(150);
            Table_KhachHang.getColumnModel().getColumn(3).setMaxWidth(150);
            Table_KhachHang.getColumnModel().getColumn(4).setMaxWidth(120);
            Table_KhachHang.getColumnModel().getColumn(5).setMaxWidth(150);
            Table_KhachHang.getColumnModel().getColumn(6).setMaxWidth(200);
            Table_KhachHang.getColumnModel().getColumn(7).setMaxWidth(300);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(70, 490, 1120, 250);

        jLabel21.setBackground(new java.awt.Color(255, 209, 84));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 209, 84));
        jLabel21.setText("Tìm kiếm đơn đặt phòng");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel21);
        jLabel21.setBounds(70, 50, 380, 32);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        jPanel2.setOpaque(false);

        checkBox_DangO.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_DangO.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_DangO.setText("Đang ở");
        checkBox_DangO.setToolTipText("");
        checkBox_DangO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_DangO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_DangO.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_DangO.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_DangO.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_DangO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_DangOActionPerformed(evt);
            }
        });

        checkBox_DangCho.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_DangCho.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_DangCho.setText("Đang chờ");
        checkBox_DangCho.setToolTipText("");
        checkBox_DangCho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_DangCho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_DangCho.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_DangCho.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_DangCho.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_DangCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_DangChoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(checkBox_DangO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(checkBox_DangCho, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBox_DangO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox_DangCho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(690, 90, 300, 120);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_DonDatPhong"); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1283, 803);

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

    private void cb_LoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_LoaiPhongActionPerformed

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void cb_TangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_TangActionPerformed

    private void cb_PhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_PhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_PhongActionPerformed

    private void checkBox_DangOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_DangOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_DangOActionPerformed

    private void checkBox_DangChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_DangChoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_DangChoActionPerformed

    private void btn_ThemDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemDichVuMouseClicked
        // TODO add your handling code here:
        LeTan_DonDatPhong_ThemDichVu themDichVu_gui = new LeTan_DonDatPhong_ThemDichVu();
        themDichVu_gui.setVisible(true);
    }//GEN-LAST:event_btn_ThemDichVuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JTable Table_KhachHang;
    private javax.swing.JPanel ThongTinDat;
    private javax.swing.JPanel ThongTinKhachHang;
    private keeptoo.KGradientPanel btn_HuyDon;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_NhanPhong;
    private keeptoo.KGradientPanel btn_ThemDichVu;
    private keeptoo.KGradientPanel btn_Tim;
    private javax.swing.JComboBox<String> cb_LoaiPhong;
    private javax.swing.JComboBox<String> cb_Phong;
    private javax.swing.JComboBox<String> cb_Tang;
    private javax.swing.JCheckBox checkBox_DangCho;
    private javax.swing.JCheckBox checkBox_DangO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_HoTen;
    private com.toedter.calendar.JDateChooser txt_NgayDatPhong_BatDau;
    private com.toedter.calendar.JDateChooser txt_NgayDatPhong_KetThuc;
    private javax.swing.JTextField txt_SoDienThoai;
    // End of variables declaration//GEN-END:variables
}
