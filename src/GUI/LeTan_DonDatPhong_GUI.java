/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_GUI.jDesktopPane1;
import static GUI.LeTan_ThanhToan_GUI.Table_hoaDon;
import static GUI.LeTan_ThanhToan_GUI.model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DonDatPhongDAO;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.LoaiPhong;
import model.DTO.NhanVien;
import model.DTO.Phong;
import model.DTO.PhongEmbed;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_GUI extends javax.swing.JInternalFrame {

    private final DefaultTableCellRenderer centerRenderer;
    private DefaultTableModel model;
    private List<DonDatPhong> list_DonDatPhong = new ArrayList<>();
    private DonDatPhongDAO dondatphong_dao = new DonDatPhongDAO(database);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private List<DonDatPhong> listddp = new ArrayList<>();

    private NhanVien nhanVien_DangSuDung;

    /**
     * Creates new form LeTan_DatPhong_GUI
     *
     * @param nhanVien_DangSuDung
     */
    public LeTan_DonDatPhong_GUI(NhanVien nhanVien_DangSuDung) {
        this.nhanVien_DangSuDung = nhanVien_DangSuDung;
        initComponents();
        jLabel4.setText(this.nhanVien_DangSuDung.getTenNhanVien());
        ImageScale.setCircularImage(label_Avatar, new ImageScale().getScaledImage1(50, 50, new ImageIcon(nhanVien_DangSuDung.getAnhDaiDien())));

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(80);
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        Table_DonDatPhong.setSelectionBackground(new Color(255, 222, 89));
        Table_DonDatPhong.setSelectionForeground(new Color(0, 0, 0));
        Table_DonDatPhong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_DonDatPhong = dondatphong_dao.getAllDonDatPhong();
        model = (DefaultTableModel) Table_DonDatPhong.getModel();
        DocDuLieuLenTableDonDatPhong(list_DonDatPhong);
        capnhatcombobox();
//      Set font cho header_phong
        JTableHeader header_phong = Table_DonDatPhong.getTableHeader();
        header_phong.setPreferredSize(new Dimension(header_phong.getPreferredSize().width, 30));
        header_phong.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table phòng
        DefaultTableCellRenderer renderer_phong = (DefaultTableCellRenderer) header_phong.getDefaultRenderer();
        renderer_phong.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header phòng
        header_phong.setDefaultRenderer(renderer_phong);

        //      bắt sự kiện hover cho nút 
        list_btn.add(btn_Tim);
        list_btn.add(btn_HuyDon);
        list_btn.add(btn_LamMoi);
        list_btn.add(btn_NhanDon);
        list_btn.add(btn_ThanhToanDon);

        list_btn.forEach((element) -> {
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mousePressed(MouseEvent e) {

//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    element.setkStartColor(new java.awt.Color(255, 225, 27));
                    element.setkEndColor(new java.awt.Color(255, 222, 89));
                    element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    element.setBorder(null);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    element.setkStartColor(new java.awt.Color(225, 176, 27));
                    element.setkEndColor(new java.awt.Color(255, 222, 89));
                    element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    element.setBorder(null);

                }
            });
        });

    }

    public List<DonDatPhong> timDonDatPhongTheoTrangThai(String trangThai, List<DonDatPhong> list_DDP) {
        return list_DDP.stream()
                .filter(don -> trangThai.equals(don.getTrangThai()))
                .collect(Collectors.toList());
    }

    public List<DonDatPhong> timDonDatPhongTheoNguoiDat(List<DonDatPhong> danhSachDonDatPhong, String tenKhachHang) {
        return danhSachDonDatPhong.stream()
                .filter(don -> tenKhachHang.equals(don.getNguoiDat().getTenKhachHang()))
                .collect(Collectors.toList());
    }

    public List<DonDatPhong> timDonDatPhongTheoCCCD(List<DonDatPhong> danhSachDonDatPhong, String CCCD) {
        return danhSachDonDatPhong.stream()
                .filter(don -> CCCD.equals(don.getNguoiDat().getCCCD()))
                .collect(Collectors.toList());
    }

    public List<DonDatPhong> timDonDatPhongTheoMaPhong(List<DonDatPhong> danhSachDonDatPhong, int maPhong) {
        return danhSachDonDatPhong.stream()
                .filter(donDat -> donDat.getPhongs().stream().anyMatch(phong -> phong.getMaPhong() == maPhong))
                .collect(Collectors.toList());
    }

    public static List<DonDatPhong> timDonDatPhongTheoNgayTaoDon(List<DonDatPhong> danhSachDonDatPhong, Date startDate, Date endDate) {
        return danhSachDonDatPhong.stream()
                .filter(donDat -> !donDat.getNgayTaoDon().before(startDate) && !donDat.getNgayTaoDon().after(endDate))
                .collect(Collectors.toList());
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
        ThongTinNgay = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_NgayBatDau = new com.toedter.calendar.JDateChooser();
        txt_NgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        txt_TenKhachHang = new javax.swing.JTextField();
        btn_HuyDon = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_ThanhToanDon = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_NhanDon = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_DonDatPhong = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cb_trangthaidon = new javax.swing.JComboBox<>();
        ThongTinPhong = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_Phong = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        circlePanel1 = new GUI.CirclePanel_Atatar();
        label_Avatar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Backgroup = new javax.swing.JLabel();

        setName("page_DonDatPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinNgay.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinNgay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinNgay.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đến ngày ");

        txt_NgayBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayBatDauPropertyChange(evt);
            }
        });

        txt_NgayKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayKetThucPropertyChange(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_MuiTen.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Từ ngày ");

        javax.swing.GroupLayout ThongTinNgayLayout = new javax.swing.GroupLayout(ThongTinNgay);
        ThongTinNgay.setLayout(ThongTinNgayLayout);
        ThongTinNgayLayout.setHorizontalGroup(
            ThongTinNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinNgayLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ThongTinNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(ThongTinNgayLayout.createSequentialGroup()
                        .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(ThongTinNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        ThongTinNgayLayout.setVerticalGroup(
            ThongTinNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinNgayLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ThongTinNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinNgay);
        ThongTinNgay.setBounds(20, 70, 480, 110);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CCCD/Passport");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên khách hàng");

        txt_CCCD.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_TenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(6, 6, 6)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(540, 70, 440, 110);

        btn_HuyDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon.setkGradientFocus(250);
        btn_HuyDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyDonMousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
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
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HuyDonLayout.setVerticalGroup(
            btn_HuyDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon);
        btn_HuyDon.setBounds(900, 230, 150, 45);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Tim.setPreferredSize(new java.awt.Dimension(140, 35));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TimMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Tìm ");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_TimLayout = new javax.swing.GroupLayout(btn_Tim);
        btn_Tim.setLayout(btn_TimLayout);
        btn_TimLayout.setHorizontalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
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
        btn_Tim.setBounds(330, 230, 150, 45);

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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Thanh Toán Đơn");
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
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel1.add(btn_ThanhToanDon);
        btn_ThanhToanDon.setBounds(1080, 230, 150, 45);

        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_LamMoi.setPreferredSize(new java.awt.Dimension(140, 45));
        btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LamMoiMousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Làm Mới");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(520, 230, 150, 45);

        btn_NhanDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_NhanDon.setkGradientFocus(250);
        btn_NhanDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_NhanDon.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_NhanDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_NhanDonMousePressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Nhận Đơn");
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
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_NhanDon);
        btn_NhanDon.setBounds(710, 230, 150, 45);

        Table_DonDatPhong.setAutoCreateRowSorter(true);
        Table_DonDatPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_DonDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn", "Trạng Thái", "Ngày Tạo Đơn", "Người Đặt", "CCCD Người Đặt", "Số Lượng Phòng", "Số Lượng Khách", "Dịch Vụ Sử Dụng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_DonDatPhong.setFocusable(false);
        Table_DonDatPhong.setRowHeight(30);
        Table_DonDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_DonDatPhongMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Table_DonDatPhong);
        if (Table_DonDatPhong.getColumnModel().getColumnCount() > 0) {
            Table_DonDatPhong.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 290, 1210, 460);

        jLabel21.setBackground(new java.awt.Color(255, 209, 84));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 209, 84));
        jLabel21.setText("Tìm đơn đặt phòng");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel21);
        jLabel21.setBounds(20, 20, 320, 27);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Trạng thái đơn");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 190, 170, 40);

        cb_trangthaidon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_trangthaidon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_trangthaidonActionPerformed(evt);
            }
        });
        jPanel1.add(cb_trangthaidon);
        cb_trangthaidon.setBounds(20, 230, 250, 42);

        ThongTinPhong.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinPhong.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phòng");

        txt_Phong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Phong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinPhongLayout = new javax.swing.GroupLayout(ThongTinPhong);
        ThongTinPhong.setLayout(ThongTinPhongLayout);
        ThongTinPhongLayout.setHorizontalGroup(
            ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinPhongLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txt_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        ThongTinPhongLayout.setVerticalGroup(
            ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinPhongLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(txt_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinPhong);
        ThongTinPhong.setBounds(1020, 70, 210, 110);

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
                .addComponent(label_Avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 209, 84));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nguyễn Hoàng Sang");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
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
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(circlePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(1000, 0, 280, 70);

        Backgroup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_DonDatPhong"); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, -27, 1280, 830);

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
    public void DocDuLieuLenTableDonDatPhong(List<DonDatPhong> list_dondatphong) {
        model.setRowCount(0);

        if (list_dondatphong == null || list_dondatphong.isEmpty()) {
            return;
        }

        for (DonDatPhong ddp : list_dondatphong) {
            // Lấy thông tin chính từ đối tượng DonDatPhong
            String maDonDat = String.valueOf(ddp.getMaDonDat());
            String trangThai = ddp.getTrangThai();
            String ngayTaoDon = sdf.format(ddp.getNgayTaoDon() != null ? ddp.getNgayTaoDon() : "");

            // Lấy thông tin người đặt
            String nguoiDat = ddp.getNguoiDat().getTenKhachHang();
            String cccdNguoiDat = ddp.getNguoiDat().getCCCD();

            // Tính số lượng phòng
            int soLuongPhong = ddp.getPhongs().size();

            // Tính số lượng khách
            int soLuongKhach = ddp.getKhachO().size();

            // Tổng hợp dịch vụ sử dụng
            StringBuilder dichVuSuDung = new StringBuilder();

            Map<String, Integer> dichVuMap = new HashMap<>(); // Lưu trữ dịch vụ và số lượng

            for (PhongEmbed p : ddp.getPhongs()) {
                if (p.getDichVuSuDung() != null) {
                    for (DichVuSuDungEmbed dv : p.getDichVuSuDung()) {
                        // Cộng dồn số lượng nếu dịch vụ đã tồn tại
                        dichVuMap.put(dv.getTenDV(), dichVuMap.getOrDefault(dv.getTenDV(), 0) + dv.getSoLuong());
                    }
                }
            }

            // Loại bỏ dấu phẩy cuối cùng trong danh sách phòng
            // Xây dựng chuỗi dịch vụ từ Map
            for (Map.Entry<String, Integer> entry : dichVuMap.entrySet()) {
                dichVuSuDung.append(entry.getKey())
                        .append(" (x")
                        .append(entry.getValue())
                        .append("), ");
            }

            // Xóa dấu phẩy cuối cùng nếu có
            if (dichVuSuDung.length() > 0) {
                dichVuSuDung.setLength(dichVuSuDung.length() - 2);
            }

            // Thêm dữ liệu vào bảng
            model.addRow(new Object[]{
                maDonDat,
                trangThai,
                ngayTaoDon,
                nguoiDat,
                cccdNguoiDat,
                soLuongPhong,
                soLuongKhach,
                dichVuSuDung.toString()
            });
        }

        // Định dạng cột trong bảng (nếu cần)
        for (int i = 0; i < Table_DonDatPhong.getColumnCount(); i++) {
            Table_DonDatPhong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void capnhatcombobox() {

        // Thêm các trạng thái không trùng lặp vào ComboBox
        cb_trangthaidon.addItem("Tất cả");
        cb_trangthaidon.addItem("Hoàn thành");
        cb_trangthaidon.addItem("Đang chờ");
        cb_trangthaidon.addItem("Xử lý");
        cb_trangthaidon.addItem("Đã hủy");

        cb_trangthaidon.setSelectedItem("Đang chờ");

    }

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

        if (cb_trangthaidon.getSelectedItem().toString().equals("Tất cả")) {
            listddp = list_DonDatPhong;
        } else {
            listddp = timDonDatPhongTheoTrangThai(cb_trangthaidon.getSelectedItem() + "", list_DonDatPhong);

        }

        if (txt_NgayBatDau.getDate() != null && txt_NgayKetThuc.getDate() != null) {
            listddp = timDonDatPhongTheoNgayTaoDon(listddp, txt_NgayBatDau.getDate(), txt_NgayKetThuc.getDate());
        }
        if (!txt_CCCD.getText().isEmpty()) {
            listddp = timDonDatPhongTheoCCCD(listddp, txt_CCCD.getText());
        }

        if (!txt_TenKhachHang.getText().isEmpty()) {
            listddp = timDonDatPhongTheoNguoiDat(listddp, txt_TenKhachHang.getText());
        }

        if (!txt_Phong.getText().isEmpty()) {
            listddp = timDonDatPhongTheoMaPhong(listddp, Integer.parseInt(txt_Phong.getText()));
        }

        model.setRowCount(0);
        DocDuLieuLenTableDonDatPhong(listddp);

    }//GEN-LAST:event_btn_TimMousePressed


    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        cb_trangthaidon.setSelectedItem("Đang chờ");
    }//GEN-LAST:event_btn_LamMoiMousePressed


    private void Table_DonDatPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_DonDatPhongMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int maDDp = Integer.parseInt(model.getValueAt(Table_DonDatPhong.getSelectedRow(), 0).toString());
            DonDatPhong ddp = dondatphong_dao.getDonDatPhongByMa(maDDp);
            LeTan_DonDatPhong_PhongCuaDon_GUI phongcuatui = new LeTan_DonDatPhong_PhongCuaDon_GUI(ddp);
            setVisible(false);
            jDesktopPane1.add(phongcuatui);
            phongcuatui.setVisible(true);

        }

    }//GEN-LAST:event_Table_DonDatPhongMousePressed

    private void btn_ThanhToanDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanDonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThanhToanDonMousePressed

    private void btn_NhanDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_NhanDonMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_NhanDonMousePressed

    private void btn_HuyDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyDonMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_HuyDonMousePressed

    private void txt_PhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PhongActionPerformed

    private void cb_trangthaidonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_trangthaidonActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0);
        if (cb_trangthaidon.getSelectedItem().toString().equals("Tất cả")) {
            DocDuLieuLenTableDonDatPhong(list_DonDatPhong);
        } else {
            DocDuLieuLenTableDonDatPhong(timDonDatPhongTheoTrangThai(cb_trangthaidon.getSelectedItem() + "", list_DonDatPhong));

        }

        txt_NgayBatDau.setDate(null);
        txt_NgayKetThuc.setDate(null);
        txt_CCCD.setText("");
        txt_TenKhachHang.setText("");
        txt_Phong.setText("");
    }//GEN-LAST:event_cb_trangthaidonActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        new TrangCaNhan(nhanVien_DangSuDung).setVisible(true);
    }//GEN-LAST:event_jPanel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    public static javax.swing.JTable Table_DonDatPhong;
    private javax.swing.JPanel ThongTinKhachHang;
    private javax.swing.JPanel ThongTinNgay;
    private javax.swing.JPanel ThongTinPhong;
    private keeptoo.KGradientPanel btn_HuyDon;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_NhanDon;
    private keeptoo.KGradientPanel btn_ThanhToanDon;
    private keeptoo.KGradientPanel btn_Tim;
    private javax.swing.JComboBox<String> cb_trangthaidon;
    private GUI.CirclePanel_Atatar circlePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_Avatar;
    private javax.swing.JTextField txt_CCCD;
    private com.toedter.calendar.JDateChooser txt_NgayBatDau;
    private com.toedter.calendar.JDateChooser txt_NgayKetThuc;
    private javax.swing.JTextField txt_Phong;
    private javax.swing.JTextField txt_TenKhachHang;
    // End of variables declaration//GEN-END:variables
}
