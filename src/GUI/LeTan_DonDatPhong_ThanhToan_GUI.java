/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_DonDatPhong_GUI.DocDuLieuLenTableDonDatPhong;
import static GUI.LeTan_DonDatPhong_GUI.list_DonDatPhong;
import GUI.LeTan_ThanhToan_ChiTietHoaDon_ChitietPhong;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import keeptoo.KGradientPanel;
import model.DAO.DichVuSuDungDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.HoaDonDAO;
import model.DAO.KhachHangDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVuSuDung;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.KhachHang;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.PhongEmbed;
import model.DTO.PhongEmbed_HoaDon;
import model.DTO.ThongTinThanhToan;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_ThanhToan_GUI extends javax.swing.JFrame {

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
    private HoaDon hoadon = null;
    DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database);
    DichVuSuDungDAO dichVuSuDungDAO = new DichVuSuDungDAO(database);

    /**
     * Creates new form LeTan_
     *
     * @param hoadon
     */
    public List<DichVuSuDung> getListDV(PhongEmbed_HoaDon p, ThongTinThanhToan tt) {
        List<DichVuSuDung> list_dv = new ArrayList<>();
        for (int ma : tt.getDichVu()) {
            if (dichVuSuDungDAO.getDichVuEmbedByMa(ma).getMaPhong() == p.getMaPhong()) {
                list_dv.add(dichVuSuDungDAO.getDichVuEmbedByMa(ma));
            }
        }

        System.out.println();

        return list_dv;
    }

    public List<DichVuSuDung> getListDV(ThongTinThanhToan tt) {
        List<DichVuSuDung> list_dv = new ArrayList<>();
        for (int ma : tt.getDichVu()) {
            list_dv.add(dichVuSuDungDAO.getDichVuEmbedByMa(ma));
        }
        System.out.println();

        return list_dv;
    }

    public int TongTien___ = 0;
    public LeTan_DonDatPhong_ThanhToan_GUI(HoaDon hoadon) {
        setUndecorated(true);
        initComponents();
        phong_holder.setVerticalScrollBar(new ScrollBarCustom());
        phong_holder.getVerticalScrollBar().setUnitIncrement(80);
        this.hoadon = hoadon;
        phong_list.setLayout(new BoxLayout(phong_list, BoxLayout.Y_AXIS));
        int tongtien = 0;
        // thêm phòng vào danh sách phong

        for (PhongEmbed_HoaDon p : hoadon.getThongTinThanhToan().getPhongs()) {
            LeTan_ThanhToan_ChiTietHoaDon_ChitietPhong chitietphong = new LeTan_ThanhToan_ChiTietHoaDon_ChitietPhong(p, getListDV(p, hoadon.getThongTinThanhToan()));
            chitietphong.setVisible(true);
            phong_list.add(chitietphong);
        }

        // thêm mục đã thanh toán vào danh sách thanh toán
        paycheck_list.setLayout(new BoxLayout(paycheck_list, BoxLayout.Y_AXIS));

        for (PhongEmbed_HoaDon p : hoadon.getThongTinThanhToan().getPhongs()) {

            ChiTietHoaDon_DanhSachThanhToan ds1 = new ChiTietHoaDon_DanhSachThanhToan(p, getListDV(p, hoadon.getThongTinThanhToan()));
            ds1.setVisible(true);
            paycheck_list.add(ds1);
        }
        int tienDv = 0;
        for (DichVuSuDung dv : getListDV(hoadon.getThongTinThanhToan())) {
            tienDv += dv.getDonGia() * dv.getSoLuong();
        }
        int tienPhong = 0;
        for (PhongEmbed_HoaDon p : hoadon.getThongTinThanhToan().getPhongs()) {
            LocalDate localDateFrom = p.getNgayNhan().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localDateTo = setThoiGian0(new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysBetween = ChronoUnit.DAYS.between(localDateFrom, localDateTo);
            int tien = (int) daysBetween * p.getDonGia();
            tienPhong += tien;
        }

        int tongTien = tienPhong + tienDv;
        int tienDaTra = 0;
        DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(hoadon.getDonDatPhong());
        for (PhongEmbed phong : ddp.getPhongs()) {
            tienDaTra += phong.getTienDaThanhToan();
        }

        tien_can_Thanh_toan.setText(df.format(tongTien) + " VNĐ");
        tien_da_Thanh_Toan.setText(df.format(tienDaTra) + " VNĐ");
        tien_conlai.setText(df.format(tongTien - tienDaTra) + " VNĐ");

        TongTien___ = tongTien;
        paycheck_list.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 209, 84)));
//        tien_da_thanh_toan.setText(df.format(tong_tien_da_tra) + " VNĐ");
//        tien_conlai.setText(df.format(tienconlai) + " VNĐ");
        setLocationRelativeTo(null);
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
        phong_section = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        phong_group = new javax.swing.JPanel();
        phong_holder = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        phong_list = new javax.swing.JPanel();
        paycheck_group = new javax.swing.JPanel();
        btn_Huy = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        paycheck_holder = new javax.swing.JPanel();
        paycheck_list = new javax.swing.JPanel();
        payment = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tien_da_Thanh_Toan = new javax.swing.JLabel();
        tien_can_Thanh_toan = new javax.swing.JLabel();
        tien_conlai = new javax.swing.JLabel();
        btn_ThanhToan = new keeptoo.KGradientPanel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1300, 700));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 400));

        phong_section.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Danh Sách Phòng");

        phong_group.setBackground(new java.awt.Color(0, 0, 0));

        phong_holder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        phong_holder.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        phong_list.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout phong_listLayout = new javax.swing.GroupLayout(phong_list);
        phong_list.setLayout(phong_listLayout);
        phong_listLayout.setHorizontalGroup(
            phong_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        phong_listLayout.setVerticalGroup(
            phong_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(phong_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(phong_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        phong_holder.setViewportView(jPanel2);

        javax.swing.GroupLayout phong_groupLayout = new javax.swing.GroupLayout(phong_group);
        phong_group.setLayout(phong_groupLayout);
        phong_groupLayout.setHorizontalGroup(
            phong_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phong_groupLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(phong_holder, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        phong_groupLayout.setVerticalGroup(
            phong_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phong_groupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phong_holder, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout phong_sectionLayout = new javax.swing.GroupLayout(phong_section);
        phong_section.setLayout(phong_sectionLayout);
        phong_sectionLayout.setHorizontalGroup(
            phong_sectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phong_sectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(phong_group, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        phong_sectionLayout.setVerticalGroup(
            phong_sectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phong_sectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phong_group, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        paycheck_group.setBackground(new java.awt.Color(0, 0, 0));

        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setkGradientFocus(250);
        btn_Huy.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Huy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_HuyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_HuyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_HuyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Thoát");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyLayout = new javax.swing.GroupLayout(btn_Huy);
        btn_Huy.setLayout(btn_HuyLayout);
        btn_HuyLayout.setHorizontalGroup(
            btn_HuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_HuyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        btn_HuyLayout.setVerticalGroup(
            btn_HuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_HuyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 209, 84));
        jLabel1.setText("Danh sách phòng cần thanh toán");

        paycheck_holder.setBackground(new java.awt.Color(0, 0, 0));

        paycheck_list.setBackground(new java.awt.Color(0, 0, 0));
        paycheck_list.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));

        javax.swing.GroupLayout paycheck_listLayout = new javax.swing.GroupLayout(paycheck_list);
        paycheck_list.setLayout(paycheck_listLayout);
        paycheck_listLayout.setHorizontalGroup(
            paycheck_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );
        paycheck_listLayout.setVerticalGroup(
            paycheck_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout paycheck_holderLayout = new javax.swing.GroupLayout(paycheck_holder);
        paycheck_holder.setLayout(paycheck_holderLayout);
        paycheck_holderLayout.setHorizontalGroup(
            paycheck_holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paycheck_holderLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(paycheck_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        paycheck_holderLayout.setVerticalGroup(
            paycheck_holderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paycheck_holderLayout.createSequentialGroup()
                .addComponent(paycheck_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 219, Short.MAX_VALUE))
        );

        payment.setBackground(new java.awt.Color(0, 0, 0));
        payment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));

        jLabel3.setBackground(new java.awt.Color(255, 209, 84));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 209, 84));
        jLabel3.setText("Cần thanh toán");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 209, 84));
        jLabel4.setText("Đã thanh toán");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 209, 84));
        jLabel5.setText("Còn lại");

        tien_da_Thanh_Toan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tien_da_Thanh_Toan.setForeground(new java.awt.Color(255, 209, 84));
        tien_da_Thanh_Toan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tien_da_Thanh_Toan.setText("860,000");

        tien_can_Thanh_toan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tien_can_Thanh_toan.setForeground(new java.awt.Color(255, 209, 84));
        tien_can_Thanh_toan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tien_can_Thanh_toan.setText("860,000");

        tien_conlai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tien_conlai.setForeground(new java.awt.Color(255, 209, 84));
        tien_conlai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tien_conlai.setText("860,000");

        javax.swing.GroupLayout paymentLayout = new javax.swing.GroupLayout(payment);
        payment.setLayout(paymentLayout);
        paymentLayout.setHorizontalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tien_can_Thanh_toan, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(tien_da_Thanh_Toan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tien_conlai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paymentLayout.setVerticalGroup(
            paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tien_can_Thanh_toan))
                .addGap(18, 18, 18)
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tien_da_Thanh_Toan))
                .addGap(18, 18, 18)
                .addGroup(paymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tien_conlai))
                .addGap(15, 15, 15))
        );

        btn_ThanhToan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThanhToan.setkGradientFocus(250);
        btn_ThanhToan.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThanhToanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ThanhToanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ThanhToanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThanhToanMousePressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Thanh Toán & Trả Phòng");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThanhToanLayout = new javax.swing.GroupLayout(btn_ThanhToan);
        btn_ThanhToan.setLayout(btn_ThanhToanLayout);
        btn_ThanhToanLayout.setHorizontalGroup(
            btn_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThanhToanLayout.setVerticalGroup(
            btn_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout paycheck_groupLayout = new javax.swing.GroupLayout(paycheck_group);
        paycheck_group.setLayout(paycheck_groupLayout);
        paycheck_groupLayout.setHorizontalGroup(
            paycheck_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paycheck_groupLayout.createSequentialGroup()
                .addGroup(paycheck_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paycheck_groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(paycheck_groupLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(paycheck_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(paycheck_groupLayout.createSequentialGroup()
                                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(paycheck_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paycheck_groupLayout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(paycheck_holder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(9, 9, 9)))
        );
        paycheck_groupLayout.setVerticalGroup(
            paycheck_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paycheck_groupLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
                .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addGroup(paycheck_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(paycheck_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(paycheck_groupLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(paycheck_holder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(161, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(phong_section, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(paycheck_group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(phong_section, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(paycheck_group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_HuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMousePressed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_HuyMousePressed

    private void btn_HuyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseExited
        // TODO add your handling code here:
        btn_Huy.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Huy.setBorder(null);
    }//GEN-LAST:event_btn_HuyMouseExited

    private void btn_HuyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseEntered
        // TODO add your handling code here:
        btn_Huy.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Huy.setBorder(null);
    }//GEN-LAST:event_btn_HuyMouseEntered

    private void btn_HuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HuyMouseClicked

    private void btn_ThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThanhToanMouseClicked

    private void btn_ThanhToanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThanhToanMouseEntered

    private void btn_ThanhToanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThanhToanMouseExited

    public Date setThoiGian0(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // Đặt ngày hiện tại
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();

    }
    private void btn_ThanhToanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThanhToanMousePressed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thánh toán và trả phòng không?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;

        }
        dondatphong_dao.updateTrangThaiDon(hoadon.getDonDatPhong(), "Hoàn thành");

        DonDatPhong ddp = dondatphong_dao.getDonDatPhongByMa(hoadon.getDonDatPhong());
        for (PhongEmbed phong : ddp.getPhongs()) {
            if (phong.getTrangThaiPhong().equals("Đang ở")) {
                donDatPhong_dao.updateTrangThaiPhong(ddp.getMaDonDat(), phong.getMaPhong(), "Đã trả");
                dondatphong_dao.updateNgayTraPhong(ddp.getMaDonDat(), phong.getMaPhong(), setThoiGian0(new Date()));
                LocalDate localDateFrom = phong.getNgayNhanPhong().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateTo = setThoiGian0(new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long daysBetween = ChronoUnit.DAYS.between(localDateFrom, localDateTo);
                int tienPhong = (int) daysBetween * phong.getDonGia();
                dondatphong_dao.updateTienTra(ddp.getMaDonDat(), phong.getMaPhong(),  tienPhong);
            }

        }
        
        hoadon.setTienThanhToan(TongTien___);
        hoadon_dao.createHoaDon(hoadon);
        
        LeTan_DonDatPhong_GUI.list_DonDatPhong = dondatphong_dao.getAllDonDatPhong();
        DocDuLieuLenTableDonDatPhong(list_DonDatPhong);
        
        setVisible(false);

        
    }//GEN-LAST:event_btn_ThanhToanMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThanhToan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThanhToan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThanhToan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThanhToan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel btn_Huy;
    private keeptoo.KGradientPanel btn_ThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel paycheck_group;
    private javax.swing.JPanel paycheck_holder;
    private javax.swing.JPanel paycheck_list;
    private javax.swing.JPanel payment;
    private javax.swing.JPanel phong_group;
    private javax.swing.JScrollPane phong_holder;
    private javax.swing.JPanel phong_list;
    private javax.swing.JPanel phong_section;
    private javax.swing.JLabel tien_can_Thanh_toan;
    private javax.swing.JLabel tien_conlai;
    private javax.swing.JLabel tien_da_Thanh_Toan;
    // End of variables declaration//GEN-END:variables
}
