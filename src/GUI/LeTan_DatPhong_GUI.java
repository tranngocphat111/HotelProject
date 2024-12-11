/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_GUI.jDesktopPane1;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import keeptoo.KGradientPanel;
import model.DAO.DonDatPhongDAO;
import model.DAO.KhachHangDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DAO.TienNghiDAO;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.KhachHang;
import model.DTO.LoaiPhong;
import model.DTO.NhanVien;
import model.DTO.Phong;
import model.DTO.PhongEmbed;
import model.DTO.TienNghi;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class LeTan_DatPhong_GUI extends javax.swing.JInternalFrame {

    DefaultTableCellRenderer centerRenderer;
    public static DecimalFormat df = new DecimalFormat("#,##0");
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
    public static PhongDAO phong_dao = new PhongDAO(database);
    public static LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database);
    TienNghiDAO tienNghi_dao = new TienNghiDAO(database);
    List<TienNghi> list_TienNghi = new ArrayList<>();
    public static List<Phong> list_Phong = new ArrayList<>();
    public static List<JPanel> list_Panel_Phong = new ArrayList<>();
    public static List<JPanel> list_Panel_PhongDuocChon = new ArrayList<>();
    public static List<JPanel> list_PanelTienNghi_Phong = new ArrayList<>();
    public static Map<String, Boolean> clickMap_Phong = new HashMap<>();
    public static Map<JPanel, Boolean> clickMap_PhongDuocChon = new HashMap<>();
    public static List<Phong> list_PhongDuocChonTheo1thoiGian = new ArrayList<>();
    public static List<PhongEmbed> list_PhongDuocChon = new ArrayList<>();
    public static List<Phong> list_PhongSauKhiLocHoacChon = new ArrayList<>();
    public static List<CirclePanel> list_Close = new ArrayList<>();
    public static KhachHang nguoiDaiDien;
    public static List<Integer> list_IndexPhongDuocChonDuocSelected = new ArrayList<>();
    public static DonDatPhongDAO DonDatPhong_Dao = new DonDatPhongDAO(database);
    KhachHangDAO khachHang_Dao = new KhachHangDAO(database);

//  Tạo biến cho bộ lọc 
    public static String boloc_loaiPhong;
    public static String boloc_loaiGiuong;
    public static String boloc_SokhachtoiDa;
    public static int boloc_DonGia;
    public static String boloc_Mota;
    
    private static NhanVien nhanVien_DangSuDung;

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_DatPhong_GUI(NhanVien nhanVien_DangSuDung) {
        
        list_PhongDuocChon.clear();
        boloc_loaiPhong = "Tất cả";
        boloc_loaiGiuong = "Tất cả";
        boloc_SokhachtoiDa = "Tất cả";
        boloc_DonGia = 0;
        boloc_Mota = "Tất cả";
        
        initComponents();
        this.nhanVien_DangSuDung = nhanVien_DangSuDung;
        jLabel1.setText(this.nhanVien_DangSuDung.getTenNhanVien());
        
        txt_NgayNhanPhong.setDate(setThoiGian0(new Date()));
        txt_NgayTraPhong.setDate(setThoiGian0(new Date(new Date().getTime() + 60 * 60 * 24 * 1000)));

        list_TienNghi = tienNghi_dao.getAllTienNghi();
        list_Phong = phong_dao.getAllPhongTrongTheoNgay(txt_NgayNhanPhong.getDate(), txt_NgayTraPhong.getDate());
        list_PhongSauKhiLocHoacChon = list_Phong;
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        Scroll_ChuThich.setOpaque(false); // Làm JScrollPane trong suốt
        Scroll_ChuThich.getViewport().setOpaque(false);
        Scroll_ChuThich.setUI(new javax.swing.plaf.basic.BasicScrollPaneUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Không vẽ gì cả, loại bỏ hoàn toàn nền
            }
        });

        Scroll_PhongDaChon.setOpaque(false); // Làm JScrollPane trong suốt
        Scroll_PhongDaChon.getViewport().setOpaque(false);
        Scroll_PhongDaChon.setUI(new javax.swing.plaf.basic.BasicScrollPaneUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Không vẽ gì cả, loại bỏ hoàn toàn nền
            }
        });

        Scroll_Phong.setUI(new javax.swing.plaf.basic.BasicScrollPaneUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Không vẽ gì cả, loại bỏ hoàn toàn nền
            }
        });
        Scroll_Phong.setOpaque(false); // Làm JScrollPane trong suốt
        Scroll_Phong.getViewport().setOpaque(false);

//        Set avatar
        jPanel3.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(255, 209, 84)));
        System.out.println(String.format("%d %d",label_Avatar.getWidth(), label_Avatar.getHeight()));
        ImageScale.setCircularImage(label_Avatar, new ImageScale().getScaledImage1(50, 50, new ImageIcon(nhanVien_DangSuDung.getAnhDaiDien())));
        SideBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(255, 255, 255)));

//      Set thanh Scroll
        Scroll_Phong.setVerticalScrollBar(new ScrollBarCustom());
        Scroll_Phong.getVerticalScrollBar().setUnitIncrement(80);

        Scroll_PhongDaChon.setHorizontalScrollBar(new Horizontal_ScrollBarUI());
        Scroll_PhongDaChon.getHorizontalScrollBar().setUnitIncrement(20);

        Scroll_ChuThich.setVerticalScrollBar(new ScrollBarCustom());
        Scroll_ChuThich.getVerticalScrollBar().setUnitIncrement(20);

//        Load Chú thích tiện nghi
        Panel_TienNghis.setLayout(new java.awt.GridLayout(getRowTienNghi(list_TienNghi.size()), 2));
        for (TienNghi tienNghi : list_TienNghi) {
            JPanel Panel_TienNghi_ChuThich = new JPanel();
            Panel_TienNghi_ChuThich.setOpaque(false);

            JLabel label_TenTienNghi = new JLabel();
            label_TenTienNghi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            label_TenTienNghi.setForeground(new java.awt.Color(255, 255, 255));
            label_TenTienNghi.setText(tienNghi.getTenTienNghi());

            JLabel icon_TienNghi = new JLabel();
            ImageIcon icon = new ImageScale().load1(new ImageIcon(tienNghi.getHinhAnh()), 27, 27);
            icon_TienNghi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            icon_TienNghi.setIcon(changeIconColor(icon));

            javax.swing.GroupLayout Panel_TienNghi_ChuThichLayout = new javax.swing.GroupLayout(Panel_TienNghi_ChuThich);
            Panel_TienNghi_ChuThich.setLayout(Panel_TienNghi_ChuThichLayout);
            Panel_TienNghi_ChuThichLayout.setHorizontalGroup(
                    Panel_TienNghi_ChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_TienNghi_ChuThichLayout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addComponent(icon_TienNghi)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(label_TenTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            Panel_TienNghi_ChuThichLayout.setVerticalGroup(
                    Panel_TienNghi_ChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_TienNghi_ChuThichLayout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addGroup(Panel_TienNghi_ChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(label_TenTienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(icon_TienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                                    .addGap(0, 0, 0))
            );

            Panel_TienNghis.add(Panel_TienNghi_ChuThich);
        }

        //        Load dữ liệu phòng
//        LoadPhong(list_PhongSauKhiLocHoacChon);
    }

    public static List<Phong> filter(List<Phong> list_canLoc) {
        List<Phong> list_Tam = list_canLoc;

        if (!boloc_loaiPhong.equals("Tất cả")) {
            list_Tam = getAllPhongByLoaiPhong(list_Tam);
        }

        if (!boloc_loaiGiuong.equals("Tất cả")) {
            list_Tam = getAllPhongByLoaiGiuong(list_Tam);
        }

        if (!boloc_SokhachtoiDa.equals("Tất cả")) {
            list_Tam = getAllPhongBySoKhachToiDa(list_Tam);
        }

        if (!boloc_Mota.equals("Tất cả")) {
            list_Tam = getAllPhongByMoTa(list_Tam);
        }

        if (boloc_DonGia != 0) {
            list_Tam = getAllPhongByDonGia(list_Tam);
        }

        return list_Tam;
    }

    public static List<Phong> getAllPhongByLoaiPhong(List<Phong> list_PhongTrong) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
        for (Phong phong : list_PhongTrong) {
            LoaiPhong loaiphong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
            if (loaiphong.getTenLoaiPhong().equals(boloc_loaiPhong)) {
                list_PhongByLoai.add(phong);
            }
        }
        return list_PhongByLoai;
    }

    public static List<Phong> getAllPhongByLoaiGiuong(List<Phong> list_PhongTrong) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
        for (Phong phong : list_PhongTrong) {
            LoaiPhong loaiphong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
            if (loaiphong.getLoaiGiuong().equals(boloc_loaiGiuong)) {
                list_PhongByLoai.add(phong);
            }
        }
        return list_PhongByLoai;
    }

    public static List<Phong> getAllPhongBySoKhachToiDa(List<Phong> list_PhongTrong) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
        for (Phong phong : list_PhongTrong) {
            LoaiPhong loaiphong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
            if (loaiphong.getSoKhachToiDa() == Integer.parseInt(boloc_SokhachtoiDa)) {
                list_PhongByLoai.add(phong);
            }
        }
        return list_PhongByLoai;
    }

    public static List<Phong> getAllPhongByMoTa(List<Phong> list_PhongTrong) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
        for (Phong phong : list_PhongTrong) {
            if (phong.getMoTa().equals(boloc_Mota)) {
                list_PhongByLoai.add(phong);
            }
        }
        return list_PhongByLoai;
    }

    public static List<Phong> getAllPhongByDonGia(List<Phong> list_PhongTrong) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();

        if (boloc_DonGia == 1) {
            for (Phong phong : list_PhongTrong) {
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
                if (loaiPhong.getDonGia() >= 500000 && loaiPhong.getDonGia() <= 1000000) {
                    list_PhongByLoai.add(phong);
                }

            }
            return list_PhongByLoai;

        }

        if (boloc_DonGia == 2) {
            for (Phong phong : list_PhongTrong) {
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
                if (loaiPhong.getDonGia() >= 1000000 && loaiPhong.getDonGia() <= 2000000) {
                    list_PhongByLoai.add(phong);
                }

            }
            return list_PhongByLoai;

        }

        if (boloc_DonGia == 3) {
            for (Phong phong : list_PhongTrong) {
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
                if (loaiPhong.getDonGia() >= 2000000 && loaiPhong.getDonGia() <= 5000000) {
                    list_PhongByLoai.add(phong);
                }

            }
            return list_PhongByLoai;

        }

        return list_PhongByLoai;
    }

    public Date setThoiGian0(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // Đặt ngày hiện tại
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();

    }

    public boolean checkDateNgayNhan() {
        if (txt_NgayTraPhong.getDate() == null) {
            return false;
        }

        if (txt_NgayNhanPhong.getDate().after(txt_NgayTraPhong.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày nhận phải trước ngày trả");
            txt_NgayNhanPhong.setDate(setThoiGian0(new Date()));
            return false;
        }

        if (txt_NgayNhanPhong.getDate().equals(txt_NgayTraPhong.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày nhận phải khác ngày trả");
            txt_NgayNhanPhong.setDate(setThoiGian0(new Date()));
            return false;
        }

        if (txt_NgayNhanPhong.getDate().before(setThoiGian0(new Date()))) {
            JOptionPane.showMessageDialog(this, "Ngày nhận không được trước ngày hôm nay");
            txt_NgayNhanPhong.setDate(setThoiGian0(new Date()));
            return false;
        }

        return true;

    }

    public boolean checkDateNgayTra() {
        if (txt_NgayNhanPhong.getDate() == null) {
            return false;
        }

        if (txt_NgayTraPhong.getDate().before(txt_NgayNhanPhong.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày trả phải sau ngày nhận");
            txt_NgayTraPhong.setDate(setThoiGian0(new Date(txt_NgayNhanPhong.getDate().getTime() + 60 * 60 * 24 * 1000)));
            return false;
        }

        if (txt_NgayTraPhong.getDate().equals(txt_NgayNhanPhong.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày trả phải khác ngày nhận");
            txt_NgayTraPhong.setDate(setThoiGian0(new Date(txt_NgayNhanPhong.getDate().getTime() + 60 * 60 * 24 * 1000)));
            return false;
        }

        return true;

    }

    public static int getRowTienNghi(int n) {

        return n / 2 + n % 2;

    }

    public static List<Phong> getAllPhongTheoTang(List<Phong> list_phong, int soTang) {
        List<Phong> Phongs = new ArrayList<>();
        for (Phong p : list_phong) {
            if (p.getTang() == soTang) {
                Phongs.add(p);

            }
        }
        return Phongs;
    }

    public static ImageIcon changeIconColor(ImageIcon icon) {
        // Chuyển ImageIcon thành BufferedImage
        BufferedImage originalImage = iconToBufferedImage(icon);

        // Tạo ảnh mới với cùng kích thước
        BufferedImage newImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        // Thay đổi màu sắc của từng pixel
        for (int x = 0; x < originalImage.getWidth(); x++) {
            for (int y = 0; y < originalImage.getHeight(); y++) {
                int pixel = originalImage.getRGB(x, y);

                // Kiểm tra xem pixel có phải màu đen không
                if (isBlack(pixel)) {
                    newImage.setRGB(x, y, Color.WHITE.getRGB()); // Thay đen thành trắng
                } else {
                    newImage.setRGB(x, y, pixel); // Giữ nguyên pixel khác
                }
            }
        }

        // Trả về ImageIcon từ BufferedImage
        return new ImageIcon(newImage);
    }

    // Hàm kiểm tra xem pixel có phải màu đen không
    private static boolean isBlack(int pixel) {
        Color color = new Color(pixel, true); // Bao gồm alpha
        return color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0 && color.getAlpha() > 0;
    }

    // Hàm chuyển đổi từ ImageIcon sang BufferedImage
    private static BufferedImage iconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        // Vẽ ImageIcon lên BufferedImage
        java.awt.Graphics g = bufferedImage.getGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        return bufferedImage;
    }

    public static void LoadPhong(List<Phong> list_phong) {

        list_Panel_Phong.clear();
        list_PanelTienNghi_Phong.clear();
        Panel_ListPhong.removeAll();
        Panel_ListPhong.revalidate();
        Panel_ListPhong.repaint();
        Set<Integer> list_Tang = new HashSet<>();
        for (Phong p : list_phong) {
            if (!list_Tang.contains(p.getTang())) {
                list_Tang.add(p.getTang());
            }
        }

        for (Integer SoTang : list_Tang) {
            java.awt.GridBagLayout Panel_PhongsLayout = new java.awt.GridBagLayout();
            Panel_PhongsLayout.columnWidths = new int[]{};
            Panel_PhongsLayout.rowHeights = new int[]{};
            JPanel Panel_Phongs = new JPanel();
            Panel_Phongs.setLayout(Panel_PhongsLayout);

            JPanel panel_Tang = new JPanel();
            panel_Tang.setOpaque(false);

            KGradientPanel btn_Tang = new KGradientPanel();
            btn_Tang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            btn_Tang.setkEndColor(new java.awt.Color(255, 222, 89));
            btn_Tang.setkGradientFocus(250);
            btn_Tang.setkStartColor(new java.awt.Color(225, 176, 27));
            btn_Tang.setMinimumSize(new java.awt.Dimension(140, 45));
            btn_Tang.setPreferredSize(new java.awt.Dimension(110, 47));

            JLabel jLabel41 = new JLabel();
            jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel41.setText("Tầng " + SoTang);
            jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout btn_TangLayout = new javax.swing.GroupLayout(btn_Tang);
            btn_Tang.setLayout(btn_TangLayout);
            btn_TangLayout.setHorizontalGroup(
                    btn_TangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btn_TangLayout.createSequentialGroup()
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
            );
            btn_TangLayout.setVerticalGroup(
                    btn_TangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btn_TangLayout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel41)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            Panel_Phongs.setOpaque(false);
            javax.swing.GroupLayout panel_TangLayout = new javax.swing.GroupLayout(panel_Tang);
            panel_Tang.setLayout(panel_TangLayout);
            panel_TangLayout.setHorizontalGroup(
                    panel_TangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_TangLayout.createSequentialGroup()
                                    .addComponent(btn_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_TangLayout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(Panel_Phongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            panel_TangLayout.setVerticalGroup(
                    panel_TangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_TangLayout.createSequentialGroup()
                                    .addComponent(btn_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Panel_Phongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10))
            );
            panel_Tang.setAlignmentX(Component.LEFT_ALIGNMENT);
            Panel_ListPhong.add(panel_Tang);

            List<Phong> list_PhongTheoTang = getAllPhongTheoTang(list_phong, SoTang);
            for (int i = 0; i < list_PhongTheoTang.size(); i++) {

                Phong phong = list_PhongTheoTang.get(i);
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());

                JPanel panel_Phong = new JPanel();
                panel_Phong.setBackground(new java.awt.Color(255, 255, 255));

                JPanel jPanel16 = new JPanel();
                jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jPanel16.setOpaque(false);

                JLabel label_SoPhong = new JLabel();
                label_SoPhong.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
                label_SoPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label_SoPhong.setText(phong.getMaPhong() + "");
                label_SoPhong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label_SoPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                jPanel16Layout.setVerticalGroup(
                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(label_SoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                                        .addGap(0, 0, 0))
                );

                JPanel jPanel17 = new JPanel();
                jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jPanel17.setOpaque(false);
                jPanel17.setPreferredSize(new java.awt.Dimension(77, 72));

                JLabel label_SoLuongKhach = new JLabel();
                label_SoLuongKhach.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                label_SoLuongKhach.setText(loaiPhong.getSoKhachToiDa() + "");

                JLabel jLabel65 = new JLabel();
                jLabel65.setIcon(new ImageIcon("src/images/icon_SoLuongKhach_mini.png"));

                JLabel jLabel66 = new JLabel();
                jLabel66.setIcon(new ImageIcon("src/images/icon_LoaiGiuong_mini.png")); // NOI18N

                JLabel label_LoaiGiuong = new JLabel();
                label_LoaiGiuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                label_LoaiGiuong.setText(loaiPhong.getLoaiGiuong());

                JLabel jLabel67 = new JLabel();
                jLabel67.setIcon(new ImageIcon("src/images/icon_LoaiPhong_mini.png")); // NOI18N

                JLabel label_LoaiPhong = new JLabel();
                label_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                label_LoaiPhong.setText(loaiPhong.getTenLoaiPhong());

                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                jPanel17.setLayout(jPanel17Layout);
                jPanel17Layout.setHorizontalGroup(
                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                        .addComponent(jLabel65)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label_SoLuongKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                        .addComponent(jLabel66)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label_LoaiGiuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                        .addComponent(jLabel67)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label_LoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
                );
                jPanel17Layout.setVerticalGroup(
                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(label_SoLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel65))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(label_LoaiGiuong, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel66))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(label_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel67)))
                );

                JPanel jPanel18 = new JPanel();
                jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jPanel18.setOpaque(false);

                JLabel label_DonGia = new JLabel();
                label_DonGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                label_DonGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label_DonGia.setText(df.format(loaiPhong.getDonGia()));

                javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                jPanel18.setLayout(jPanel18Layout);
                jPanel18Layout.setHorizontalGroup(
                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label_DonGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                jPanel18Layout.setVerticalGroup(
                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(label_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                );

                JPanel jPanel20 = new JPanel();
                jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jPanel20.setOpaque(false);

                JLabel label_View = new JLabel();
                label_View.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                label_View.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label_View.setText(phong.getMoTa());

                javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                jPanel20.setLayout(jPanel20Layout);
                jPanel20Layout.setHorizontalGroup(
                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label_View, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                jPanel20Layout.setVerticalGroup(
                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(label_View, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                );

                JPanel jPanel4 = new JPanel();
                jPanel4.setBackground(new java.awt.Color(255, 255, 255));
                jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jPanel4.setOpaque(false);

                JScrollPane jScrollPane1 = new JScrollPane();
                jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
                jScrollPane1.setBorder(null);
                jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                jScrollPane1.setMinimumSize(new java.awt.Dimension(60, 85));
                jScrollPane1.setOpaque(false);
                jScrollPane1.setPreferredSize(new java.awt.Dimension(60, 85));

                JPanel panel_TienNghiCuaPhong = new JPanel();
                panel_TienNghiCuaPhong.setBackground(new java.awt.Color(255, 255, 255));
                panel_TienNghiCuaPhong.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 4, 2));
                panel_TienNghiCuaPhong.setLayout(new java.awt.GridLayout(3, 2, 6, 0));

                panel_TienNghiCuaPhong.setName(phong.getMaPhong() + "");
                list_PanelTienNghi_Phong.add(panel_TienNghiCuaPhong);
                for (TienNghi tiennghi : loaiPhong.getTienNghis()) {
                    JLabel icon_TienNghi = new JLabel();
                    ImageIcon icon = new ImageScale().load1(new ImageIcon(tiennghi.getHinhAnh()), 20, 20);
                    icon_TienNghi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    icon_TienNghi.setIcon(icon);
                    panel_TienNghiCuaPhong.add(icon_TienNghi);
                }

                jScrollPane1.setViewportView(panel_TienNghiCuaPhong);

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 60, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                );
                jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 85, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                );

                javax.swing.GroupLayout panel_PhongLayout = new javax.swing.GroupLayout(panel_Phong);
                panel_Phong.setLayout(panel_PhongLayout);
                panel_PhongLayout.setHorizontalGroup(
                        panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_PhongLayout.createSequentialGroup()
                                        .addGroup(panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, 0)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                panel_PhongLayout.setVerticalGroup(
                        panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel_PhongLayout.createSequentialGroup()
                                        .addGroup(panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel_PhongLayout.createSequentialGroup()
                                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0))
                );

                GridBagConstraints gridBagConstraints = new GridBagConstraints();

                if (i >= 6) {
                    gridBagConstraints.gridx = i % 6;
                } else {
                    gridBagConstraints.gridx = i;
                }

                gridBagConstraints.gridy = (int) i / 6;

                gridBagConstraints.insets = new Insets(0, 0, 10, 10);
                Panel_Phongs.setLayout(Panel_PhongsLayout);
                Panel_Phongs.add(panel_Phong, gridBagConstraints);
                panel_Phong.setName(phong.getMaPhong() + "");
                list_Panel_Phong.add(panel_Phong);

            }
        }
        EventChooseRoom();

    }

    public static void LoadPhongDuocChon() {
        int index_PhongDuocChon = 0;
        list_IndexPhongDuocChonDuocSelected.clear();
        clickMap_PhongDuocChon.clear();
        list_Panel_PhongDuocChon.clear();
        list_Close.clear();
        Panel_PhongDuocChons.removeAll();
        Panel_PhongDuocChons.revalidate();
        Panel_PhongDuocChons.repaint();

        Panel_PhongDuocChons.setLayout(new java.awt.GridLayout(1, list_PhongDuocChon.size(), 15, 0));
        for (PhongEmbed phong : list_PhongDuocChon) {
            LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByTen(phong.getTenLoaiPhong());
            JPanel panel_PhongDuocChon = new JPanel();
            panel_PhongDuocChon.setBackground(new java.awt.Color(255, 255, 153));
            panel_PhongDuocChon.setOpaque(false);
            panel_PhongDuocChon.setPreferredSize(new java.awt.Dimension(150, 150));
            panel_PhongDuocChon.setLayout(null);

            JLabel jLabel29 = new JLabel();
            jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel29.setIcon(new javax.swing.ImageIcon("src/images/icon_close.png")); // NOI18N
            jLabel29.setPreferredSize(new java.awt.Dimension(25, 25));

            CirclePanel icon_remove = new GUI.CirclePanel();
            icon_remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            javax.swing.GroupLayout icon_removeLayout = new javax.swing.GroupLayout(icon_remove);
            icon_remove.setLayout(icon_removeLayout);
            icon_removeLayout.setHorizontalGroup(
                    icon_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, icon_removeLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            icon_removeLayout.setVerticalGroup(
                    icon_removeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, icon_removeLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            icon_remove.setName(index_PhongDuocChon + "");
            list_Close.add(icon_remove);
            panel_PhongDuocChon.add(icon_remove);
            icon_remove.setBounds(122, 7, 25, 25);

            JPanel panel_Phong = new JPanel();
            panel_Phong.setBackground(new java.awt.Color(255, 255, 255));

            JPanel jPanel131 = new JPanel();
            jPanel131.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel131.setOpaque(false);

            JLabel label_SoPhong = new JLabel();
            label_SoPhong.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
            label_SoPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_SoPhong.setText(phong.getMaPhong() + "");
            label_SoPhong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel131Layout = new javax.swing.GroupLayout(jPanel131);
            jPanel131.setLayout(jPanel131Layout);
            jPanel131Layout.setHorizontalGroup(
                    jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel131Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label_SoPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            jPanel131Layout.setVerticalGroup(
                    jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel131Layout.createSequentialGroup()
                                    .addComponent(label_SoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                                    .addGap(0, 0, 0))
            );

            JPanel jPanel132 = new JPanel();
            jPanel132.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel132.setOpaque(false);
            jPanel132.setPreferredSize(new java.awt.Dimension(77, 72));

            JLabel label_SoLuongKhach = new JLabel();
            label_SoLuongKhach.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            label_SoLuongKhach.setText(loaiPhong.getSoKhachToiDa() + "");

            JLabel label = new JLabel();
            label.setIcon(new javax.swing.ImageIcon("src/images/icon_SoLuongKhach_mini.png")); // NOI18N

            JLabel jLabel319 = new JLabel();
            jLabel319.setIcon(new javax.swing.ImageIcon("src/images/icon_LoaiGiuong_mini.png")); // NOI18N

            JLabel label_LoaiGiuong = new JLabel();
            label_LoaiGiuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            label_LoaiGiuong.setText(loaiPhong.getLoaiGiuong());

            JLabel jLabel320 = new JLabel();
            jLabel320.setIcon(new javax.swing.ImageIcon("src/images/icon_LoaiPhong_mini.png")); // NOI18N

            JLabel label_LoaiPhong = new JLabel();
            label_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            label_LoaiPhong.setText(loaiPhong.getTenLoaiPhong());

            javax.swing.GroupLayout jPanel132Layout = new javax.swing.GroupLayout(jPanel132);
            jPanel132.setLayout(jPanel132Layout);
            jPanel132Layout.setHorizontalGroup(
                    jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel132Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel132Layout.createSequentialGroup()
                                                    .addComponent(label)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(label_SoLuongKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel132Layout.createSequentialGroup()
                                                    .addComponent(jLabel319)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(label_LoaiGiuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel132Layout.createSequentialGroup()
                                                    .addComponent(jLabel320)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(label_LoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))))
            );
            jPanel132Layout.setVerticalGroup(
                    jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel132Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(label_SoLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label))
                                    .addGap(0, 0, 0)
                                    .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(label_LoaiGiuong, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel319))
                                    .addGap(0, 0, 0)
                                    .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(label_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel320)))
            );

            JPanel jPanel133 = new JPanel();
            jPanel133.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel133.setOpaque(false);

            JLabel label_DonGia = new JLabel();
            label_DonGia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
            label_DonGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_DonGia.setText(df.format(loaiPhong.getDonGia()));

            javax.swing.GroupLayout jPanel133Layout = new javax.swing.GroupLayout(jPanel133);
            jPanel133.setLayout(jPanel133Layout);
            jPanel133Layout.setHorizontalGroup(
                    jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel133Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label_DonGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            jPanel133Layout.setVerticalGroup(
                    jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel133Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addComponent(label_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
            );

            JPanel jPanel135 = new JPanel();
            jPanel135.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel135.setOpaque(false);

            JLabel jLabel328 = new JLabel();
            jLabel328.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel328.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel328.setText("-");

            JLabel txt_NgayTra = new JLabel();
            txt_NgayTra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txt_NgayTra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            txt_NgayTra.setText(sdf.format(phong.getNgayTraPhongDuKien()));

            JLabel txt_NgayNhan = new JLabel();
            txt_NgayNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txt_NgayNhan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            txt_NgayNhan.setText(sdf.format(phong.getNgayNhanPhongDuKien()));

            javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
            jPanel135.setLayout(jPanel135Layout);
            jPanel135Layout.setHorizontalGroup(
                    jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel135Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addComponent(txt_NgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jLabel328, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(txt_NgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel135Layout.setVerticalGroup(
                    jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_NgayTra)
                                    .addComponent(jLabel328, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_NgayNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            Panel_PhongDuocChons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            Panel_PhongDuocChons.setBackground(new java.awt.Color(0, 0, 0));

            JPanel panel_TienNghiCuaPhong = new JPanel();
            panel_TienNghiCuaPhong.setOpaque(false);
            panel_TienNghiCuaPhong.setLayout(new java.awt.GridLayout(getRowTienNghi(loaiPhong.getTienNghis().size()), 2, 8, 6));

            for (TienNghi tiennghi : loaiPhong.getTienNghis()) {
                JLabel icon_TienNghi = new JLabel();
                ImageIcon icon = new ImageScale().load1(new ImageIcon(tiennghi.getHinhAnh()), 18, 18);
                icon_TienNghi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                icon_TienNghi.setIcon(icon);
                panel_TienNghiCuaPhong.add(icon_TienNghi);
            }

            JPanel jPanel4 = new JPanel();
            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            jPanel4.setOpaque(false);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(panel_TienNghiCuaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(panel_TienNghiCuaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                            .addContainerGap(10, Short.MAX_VALUE)))
            );

            javax.swing.GroupLayout panel_PhongLayout = new javax.swing.GroupLayout(panel_Phong);
            panel_Phong.setLayout(panel_PhongLayout);
            panel_PhongLayout.setHorizontalGroup(
                    panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_PhongLayout.createSequentialGroup()
                                    .addGroup(panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel132, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                            .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(0, 0, 0)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel133, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel135, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            panel_PhongLayout.setVerticalGroup(
                    panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_PhongLayout.createSequentialGroup()
                                    .addGroup(panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panel_PhongLayout.createSequentialGroup()
                                                    .addComponent(jPanel131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, 0)
                                                    .addComponent(jPanel132, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(0, 0, 0)
                                    .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jPanel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            panel_PhongDuocChon.add(panel_Phong);
            panel_Phong.setBounds(0, 16, 135, 134);
            Panel_PhongDuocChons.add(panel_PhongDuocChon);
            panel_Phong.setName(index_PhongDuocChon + "");
            list_Panel_PhongDuocChon.add(panel_Phong);
            index_PhongDuocChon++;
            clickMap_Phong.put(phong.getMaPhong() + "", false);

        }
        EventCloseRoom();
        EventChooseRoomSelected();
    }

    public static boolean removePhongDuocChon(int index) {
        for (Integer i : list_IndexPhongDuocChonDuocSelected) {
            if (i == index) {
                list_IndexPhongDuocChonDuocSelected.remove(i);
                return true;
            }
        }

        return false;
    }

    public static void EventChooseRoomSelected() {
        list_Panel_PhongDuocChon.forEach((element) -> {
            if (clickMap_PhongDuocChon.get(element) == null) {
                clickMap_PhongDuocChon.put(element, false);
            }
            element.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                    if (clickMap_PhongDuocChon.get(element)) {
                        element.setBackground(new java.awt.Color(255, 255, 255));
                        for (JPanel close : list_Close) {
                            if (close.getName().equals(element.getName())) {
                                element.getParent().setComponentZOrder(close, 0);

                            }
                        }
                        removePhongDuocChon(Integer.parseInt(element.getName()));

                        // Gọi repaint() để vẽ lại giao diện
                        element.getParent().repaint();

                        clickMap_PhongDuocChon.put(element, false);

                    } else {
                        element.setBackground(new java.awt.Color(255, 209, 84));
                        for (JPanel close : list_Close) {
                            if (close.getName().equals(element.getName())) {
                                element.getParent().setComponentZOrder(close, 0);

                            }
                        }

                        // Gọi repaint() để vẽ lại giao diện
                        element.getParent().repaint();
                        list_IndexPhongDuocChonDuocSelected.add(Integer.parseInt(element.getName()));
                        clickMap_PhongDuocChon.put(element, true);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });
        });
    }

    public static void EventCloseRoom() {
        list_Close.forEach((element) -> {
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    System.out.println("Index muốn xóa: " + Integer.parseInt(element.getName()));
//                    System.out.println("Số lượng Phòng đã chọn: " + list_PhongDuocChon.size());
//                    System.out.println("Phòng bị xóa: " + list_PhongDuocChon.get(Integer.parseInt(element.getName())));
                    list_PhongDuocChon.remove(Integer.parseInt(element.getName()));

                    LoadPhongDuocChon();

                    list_PhongSauKhiLocHoacChon = getAllPhongSauKhiChon();
                    LoadPhong(list_PhongSauKhiLocHoacChon);

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });
        });
    }

    public static void EventChooseRoom() {
        list_Panel_Phong.forEach((element) -> {
            if (clickMap_Phong.get(element.getName()) == null) {
                clickMap_Phong.put(element.getName(), false);
            }

            element.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (clickMap_Phong.get(element.getName())) {
                        element.setBackground(new java.awt.Color(255, 255, 255));
                        for (JPanel p : list_PanelTienNghi_Phong) {
                            if (p.getName().equals(element.getName())) {
                                p.setBackground(new java.awt.Color(255, 255, 255));
                                break;
                            }
                        }

                        removePhong(phong_dao.getPhongByMa(Integer.parseInt(element.getName())));
                        clickMap_Phong.put(element.getName(), false);

                    } else {
                        element.setBackground(new java.awt.Color(255, 209, 84));
                        for (JPanel p : list_PanelTienNghi_Phong) {
                            if (p.getName().equals(element.getName())) {
                                p.setBackground(new java.awt.Color(255, 209, 84));
                                break;
                            }
                        }
                        list_PhongDuocChonTheo1thoiGian.add(phong_dao.getPhongByMa(Integer.parseInt(element.getName())));
                        clickMap_Phong.put(element.getName(), true);

                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });
        });

        list_PanelTienNghi_Phong.forEach((element) -> {
            if (clickMap_Phong.get(element.getName()) == null) {
                clickMap_Phong.put(element.getName(), false);
            }

            element.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (clickMap_Phong.get(element.getName())) {
                        element.setBackground(new java.awt.Color(255, 255, 255));
                        for (JPanel p : list_Panel_Phong) {
                            if (p.getName().equals(element.getName())) {
                                p.setBackground(new java.awt.Color(255, 255, 255));
                                break;
                            }
                        }
                        removePhong(phong_dao.getPhongByMa(Integer.parseInt(element.getName())));
                        clickMap_Phong.put(element.getName(), false);

                    } else {
                        element.setBackground(new java.awt.Color(255, 209, 84));
                        for (JPanel p : list_Panel_Phong) {
                            if (p.getName().equals(element.getName())) {
                                p.setBackground(new java.awt.Color(255, 209, 84));
                                break;
                            }
                        }
                        list_PhongDuocChonTheo1thoiGian.add(phong_dao.getPhongByMa(Integer.parseInt(element.getName())));
                        clickMap_Phong.put(element.getName(), true);

                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });
        });

    }

    public static boolean removePhong(Phong p) {
        for (Phong phong : list_PhongDuocChonTheo1thoiGian) {
            if (phong.getMaPhong() == p.getMaPhong()) {
                list_PhongDuocChonTheo1thoiGian.remove(phong);
                return true;
            }
        }
        return false;
    }

    public static List<Phong> getAllPhongSauKhiChon() {
        list_Phong = phong_dao.getAllPhongTrongTheoNgay(txt_NgayNhanPhong.getDate(), txt_NgayTraPhong.getDate());
        List<Phong> list_Tam = list_Phong;

        if (!boloc_loaiPhong.equals("Tất cả")) {
            list_Tam = getAllPhongByLoaiPhong(list_Tam);
        }

        if (!boloc_loaiGiuong.equals("Tất cả")) {
            list_Tam = getAllPhongByLoaiGiuong(list_Tam);
        }

        if (!boloc_SokhachtoiDa.equals("Tất cả")) {
            list_Tam = getAllPhongBySoKhachToiDa(list_Tam);
        }

        if (!boloc_Mota.equals("Tất cả")) {
            list_Tam = getAllPhongByMoTa(list_Tam);
        }

        if (boloc_DonGia != 0) {
            list_Tam = getAllPhongByDonGia(list_Tam);
        }

        List<Phong> list_Phong_Loc = new ArrayList<>();
        for (Phong p : list_Tam) {
            boolean ktra = true;
            for (PhongEmbed pDuocChon : list_PhongDuocChon) {
                if (p.getMaPhong() == pDuocChon.getMaPhong()) {
                    if (pDuocChon.getNgayNhanPhongDuKien().equals(txt_NgayNhanPhong.getDate())
                            && pDuocChon.getNgayTraPhongDuKien().equals(txt_NgayTraPhong.getDate())) {
                        ktra = false;
                        break;
                    }
                    if ((pDuocChon.getNgayNhanPhongDuKien().after(txt_NgayNhanPhong.getDate()) || 
                            pDuocChon.getNgayNhanPhongDuKien().equals(txt_NgayNhanPhong.getDate()))
                            && (pDuocChon.getNgayTraPhongDuKien().before(txt_NgayTraPhong.getDate())
                            || pDuocChon.getNgayTraPhongDuKien().equals(txt_NgayTraPhong.getDate()))) {
                        ktra = false;
                        break;
                    }

                }

            }

            if (ktra) {
                list_Phong_Loc.add(p);
            }
        }
        return list_Phong_Loc;
    }

    public void ThemPhongDaChon(List<Phong> list_phong) {
        list_phong.sort((p1, p2) -> Integer.compare(p1.getMaPhong(), p2.getMaPhong()));
        for (Phong p : list_phong) {
            LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(p.getLoaiPhong());
            PhongEmbed phong = new PhongEmbed();
            phong.setMaPhong(p.getMaPhong());
            phong.setTenLoaiPhong(loaiPhong_dao.getLoaiPhongByMa(p.getLoaiPhong()).getTenLoaiPhong());
            phong.setNgayNhanPhongDuKien(txt_NgayNhanPhong.getDate());
            phong.setNgayTraPhongDuKien(txt_NgayTraPhong.getDate());
            List<DichVuSuDungEmbed> dichVuSuDung = new ArrayList<>();
            phong.setDichVuSuDung(dichVuSuDung);
            phong.setDonGia(loaiPhong.getDonGia());
            phong.setTenLoaiPhong(loaiPhong.getTenLoaiPhong());
            phong.setTienDaThanhToan(0);
            phong.setTrangThaiPhong("Đang chờ");
            phong.setNgayNhanPhong(null);
            phong.setNgayTraPhong(null);
            list_PhongDuocChon.add(phong);
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
        jPanel2 = new javax.swing.JPanel();
        circlePanel1 = new GUI.CirclePanel_Atatar();
        label_Avatar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        button_ThongBao = new javax.swing.JLabel();
        SideBar = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txt_NgayNhanPhong = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txt_NgayTraPhong = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        btn_Loc = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_ThemPhong = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Jpanel_NguoiDaiDien = new javax.swing.JPanel();
        txt_TenNguoiDaiDien = new javax.swing.JLabel();
        icon_Them = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Panel_ChuThich = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Panel_ChuThich1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Panel_ChuThich2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Panel_ChuThich3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Panel_ChuThich4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        Scroll_ChuThich = new javax.swing.JScrollPane();
        Panel_TienNghis = new javax.swing.JPanel();
        btn_SuaThoiGianDat = new keeptoo.KGradientPanel();
        jLabel28 = new javax.swing.JLabel();
        btn_HoanTat = new keeptoo.KGradientPanel();
        jLabel43 = new javax.swing.JLabel();
        Scroll_Phong = new javax.swing.JScrollPane();
        Panel_ListPhong = new javax.swing.JPanel();
        line = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        Scroll_PhongDaChon = new javax.swing.JScrollPane();
        panel_GrPhong = new javax.swing.JPanel();
        Panel_PhongDuocChons = new javax.swing.JPanel();
        Backgroup = new javax.swing.JLabel();

        setName("page_DatPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 833));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 209, 84));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nguyễn Hoàng Sang");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
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

        button_ThongBao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_ThongBao.png"))); // NOI18N
        jPanel1.add(button_ThongBao);
        button_ThongBao.setBounds(930, 10, 40, 40);

        SideBar.setOpaque(false);

        jLabel21.setBackground(new java.awt.Color(255, 209, 84));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 209, 84));
        jLabel21.setText("Đặt phòng");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt_NgayNhanPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayNhanPhong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayNhanPhongPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày nhận phòng");

        txt_NgayTraPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayTraPhong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayTraPhongPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày trả phòng");

        btn_Loc.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btn_Loc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Loc.setOpaque(false);
        btn_Loc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LocMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lọc theo các tiêu chí");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DatPhong_Loc.png"))); // NOI18N

        javax.swing.GroupLayout btn_LocLayout = new javax.swing.GroupLayout(btn_Loc);
        btn_Loc.setLayout(btn_LocLayout);
        btn_LocLayout.setHorizontalGroup(
            btn_LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LocLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(17, 17, 17))
        );
        btn_LocLayout.setVerticalGroup(
            btn_LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_LocLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(btn_LocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btn_ThemPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_ThemPhong.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThemPhong.setkGradientFocus(250);
        btn_ThemPhong.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThemPhong.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_ThemPhong.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_ThemPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemPhongMousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Thêm phòng");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThemPhongLayout = new javax.swing.GroupLayout(btn_ThemPhong);
        btn_ThemPhong.setLayout(btn_ThemPhongLayout);
        btn_ThemPhongLayout.setHorizontalGroup(
            btn_ThemPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemPhongLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_ThemPhongLayout.setVerticalGroup(
            btn_ThemPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemPhongLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_LamMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_LamMoi.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LamMoiMousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Làm mới");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Người đại diện");

        Jpanel_NguoiDaiDien.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel_NguoiDaiDien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Jpanel_NguoiDaiDien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Jpanel_NguoiDaiDien.setName("btn_Them"); // NOI18N
        Jpanel_NguoiDaiDien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jpanel_NguoiDaiDienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Jpanel_NguoiDaiDienMousePressed(evt);
            }
        });

        txt_TenNguoiDaiDien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        icon_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Them.png"))); // NOI18N
        icon_Them.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout Jpanel_NguoiDaiDienLayout = new javax.swing.GroupLayout(Jpanel_NguoiDaiDien);
        Jpanel_NguoiDaiDien.setLayout(Jpanel_NguoiDaiDienLayout);
        Jpanel_NguoiDaiDienLayout.setHorizontalGroup(
            Jpanel_NguoiDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel_NguoiDaiDienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_TenNguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(icon_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Jpanel_NguoiDaiDienLayout.setVerticalGroup(
            Jpanel_NguoiDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel_NguoiDaiDienLayout.createSequentialGroup()
                .addComponent(txt_TenNguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Jpanel_NguoiDaiDienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_Them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel22.setBackground(new java.awt.Color(255, 209, 84));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 209, 84));
        jLabel22.setText("Chú thích");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Panel_ChuThich.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Số lượng khách");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThichLayout = new javax.swing.GroupLayout(Panel_ChuThich);
        Panel_ChuThich.setLayout(Panel_ChuThichLayout);
        Panel_ChuThichLayout.setHorizontalGroup(
            Panel_ChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThichLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThichLayout.setVerticalGroup(
            Panel_ChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThichLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_ChuThich1.setOpaque(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Loại giường");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiGiuong.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich1Layout = new javax.swing.GroupLayout(Panel_ChuThich1);
        Panel_ChuThich1.setLayout(Panel_ChuThich1Layout);
        Panel_ChuThich1Layout.setHorizontalGroup(
            Panel_ChuThich1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich1Layout.setVerticalGroup(
            Panel_ChuThich1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Panel_ChuThich2.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Loại phòng");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich2Layout = new javax.swing.GroupLayout(Panel_ChuThich2);
        Panel_ChuThich2.setLayout(Panel_ChuThich2Layout);
        Panel_ChuThich2Layout.setHorizontalGroup(
            Panel_ChuThich2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        Panel_ChuThich2Layout.setVerticalGroup(
            Panel_ChuThich2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_ChuThich3.setOpaque(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Phòng trống");

        javax.swing.GroupLayout Panel_ChuThich3Layout = new javax.swing.GroupLayout(Panel_ChuThich3);
        Panel_ChuThich3.setLayout(Panel_ChuThich3Layout);
        Panel_ChuThich3Layout.setHorizontalGroup(
            Panel_ChuThich3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        Panel_ChuThich3Layout.setVerticalGroup(
            Panel_ChuThich3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        Panel_ChuThich4.setOpaque(false);

        jPanel6.setBackground(new java.awt.Color(255, 209, 84));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Phòng được chọn");

        javax.swing.GroupLayout Panel_ChuThich4Layout = new javax.swing.GroupLayout(Panel_ChuThich4);
        Panel_ChuThich4.setLayout(Panel_ChuThich4Layout);
        Panel_ChuThich4Layout.setHorizontalGroup(
            Panel_ChuThich4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        Panel_ChuThich4Layout.setVerticalGroup(
            Panel_ChuThich4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        Scroll_ChuThich.setBackground(new java.awt.Color(0, 0, 0));
        Scroll_ChuThich.setBorder(null);
        Scroll_ChuThich.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Panel_TienNghis.setBackground(new java.awt.Color(0, 0, 0));
        Panel_TienNghis.setOpaque(false);
        Panel_TienNghis.setLayout(new java.awt.GridLayout(4, 2));
        Scroll_ChuThich.setViewportView(Panel_TienNghis);

        javax.swing.GroupLayout SideBarLayout = new javax.swing.GroupLayout(SideBar);
        SideBar.setLayout(SideBarLayout);
        SideBarLayout.setHorizontalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SideBarLayout.createSequentialGroup()
                        .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SideBarLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(Jpanel_NguoiDaiDien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SideBarLayout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(Panel_ChuThich2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Panel_ChuThich1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Panel_ChuThich, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(SideBarLayout.createSequentialGroup()
                                            .addComponent(btn_ThemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txt_NgayNhanPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_NgayTraPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_Loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(SideBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Panel_ChuThich4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Panel_ChuThich3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(SideBarLayout.createSequentialGroup()
                        .addContainerGap(274, Short.MAX_VALUE)
                        .addComponent(Scroll_ChuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        SideBarLayout.setVerticalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ThemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(4, 4, 4)
                .addComponent(Jpanel_NguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_ChuThich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Panel_ChuThich1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Panel_ChuThich2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_ChuThich3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Panel_ChuThich4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scroll_ChuThich, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
        );

        jPanel1.add(SideBar);
        SideBar.setBounds(10, 20, 280, 760);

        btn_SuaThoiGianDat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_SuaThoiGianDat.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_SuaThoiGianDat.setkGradientFocus(250);
        btn_SuaThoiGianDat.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_SuaThoiGianDat.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_SuaThoiGianDat.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_SuaThoiGianDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_SuaThoiGianDatMousePressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Sửa thời gian đặt");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_SuaThoiGianDatLayout = new javax.swing.GroupLayout(btn_SuaThoiGianDat);
        btn_SuaThoiGianDat.setLayout(btn_SuaThoiGianDatLayout);
        btn_SuaThoiGianDatLayout.setHorizontalGroup(
            btn_SuaThoiGianDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SuaThoiGianDatLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        btn_SuaThoiGianDatLayout.setVerticalGroup(
            btn_SuaThoiGianDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SuaThoiGianDatLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(btn_SuaThoiGianDat);
        btn_SuaThoiGianDat.setBounds(880, 740, 160, 47);

        btn_HoanTat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HoanTat.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HoanTat.setkGradientFocus(250);
        btn_HoanTat.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HoanTat.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HoanTat.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_HoanTat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HoanTatMousePressed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Hoàn tất");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HoanTatLayout = new javax.swing.GroupLayout(btn_HoanTat);
        btn_HoanTat.setLayout(btn_HoanTatLayout);
        btn_HoanTatLayout.setHorizontalGroup(
            btn_HoanTatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HoanTatLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        btn_HoanTatLayout.setVerticalGroup(
            btn_HoanTatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HoanTatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HoanTat);
        btn_HoanTat.setBounds(1080, 740, 150, 47);

        Scroll_Phong.setBackground(new java.awt.Color(0, 0, 0));
        Scroll_Phong.setBorder(null);
        Scroll_Phong.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll_Phong.setMaximumSize(new java.awt.Dimension(940, 200));
        Scroll_Phong.setPreferredSize(new java.awt.Dimension(940, 520));

        Panel_ListPhong.setBackground(new java.awt.Color(0, 0, 0));
        Panel_ListPhong.setOpaque(false);
        Panel_ListPhong.setLayout(new javax.swing.BoxLayout(Panel_ListPhong, javax.swing.BoxLayout.Y_AXIS));
        Scroll_Phong.setViewportView(Panel_ListPhong);

        jPanel1.add(Scroll_Phong);
        Scroll_Phong.setBounds(310, 70, 940, 470);

        line.setBackground(new java.awt.Color(255, 209, 84));

        javax.swing.GroupLayout lineLayout = new javax.swing.GroupLayout(line);
        line.setLayout(lineLayout);
        lineLayout.setHorizontalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        lineLayout.setVerticalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel1.add(line);
        line.setBounds(450, 563, 780, 2);

        jLabel27.setBackground(new java.awt.Color(255, 209, 84));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 209, 84));
        jLabel27.setText("Phòng đã chọn");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel27);
        jLabel27.setBounds(300, 550, 160, 27);

        Scroll_PhongDaChon.setBackground(new java.awt.Color(0, 0, 0));
        Scroll_PhongDaChon.setBorder(null);
        Scroll_PhongDaChon.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        Scroll_PhongDaChon.setOpaque(false);
        Scroll_PhongDaChon.setPreferredSize(new java.awt.Dimension(150, 150));

        panel_GrPhong.setBackground(new java.awt.Color(0, 0, 0));
        panel_GrPhong.setAutoscrolls(true);
        panel_GrPhong.setOpaque(false);

        Panel_PhongDuocChons.setBackground(new java.awt.Color(0, 0, 0));
        Panel_PhongDuocChons.setAutoscrolls(true);
        Panel_PhongDuocChons.setOpaque(false);
        Panel_PhongDuocChons.setLayout(new java.awt.GridLayout(1, 6, 15, 15));

        javax.swing.GroupLayout panel_GrPhongLayout = new javax.swing.GroupLayout(panel_GrPhong);
        panel_GrPhong.setLayout(panel_GrPhongLayout);
        panel_GrPhongLayout.setHorizontalGroup(
            panel_GrPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_GrPhongLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Panel_PhongDuocChons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        panel_GrPhongLayout.setVerticalGroup(
            panel_GrPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_PhongDuocChons, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );

        Panel_PhongDuocChons.getAccessibleContext().setAccessibleParent(panel_GrPhong);

        Scroll_PhongDaChon.setViewportView(panel_GrPhong);

        jPanel1.add(Scroll_PhongDaChon);
        Scroll_PhongDaChon.setBounds(348, 570, 880, 170);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_DatPhong"); // NOI18N
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

    private void txt_NgayNhanPhongPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_NgayNhanPhongPropertyChange
        // TODO add your handling code here:
        if (checkDateNgayNhan()) {
            list_PhongSauKhiLocHoacChon = phong_dao.getAllPhongTrongTheoNgay(txt_NgayNhanPhong.getDate(), txt_NgayTraPhong.getDate());
//            System.out.println("GUI.LeTan_DatPhong_GUI.txt_NgayNhanPhongPropertyChange()");
            list_PhongDuocChonTheo1thoiGian.clear();
            clickMap_Phong.clear();
            list_PhongSauKhiLocHoacChon = getAllPhongSauKhiChon();
            LoadPhong(list_PhongSauKhiLocHoacChon);

        }

    }//GEN-LAST:event_txt_NgayNhanPhongPropertyChange

    private void txt_NgayTraPhongPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_NgayTraPhongPropertyChange
        // TODO add your handling code here:
        if (checkDateNgayTra()) {
            list_PhongSauKhiLocHoacChon = phong_dao.getAllPhongTrongTheoNgay(txt_NgayNhanPhong.getDate(), txt_NgayTraPhong.getDate());
//            System.out.println("GUI.LeTan_DatPhong_GUI.txt_NgayTraPhongPropertyChange()");
            list_PhongDuocChonTheo1thoiGian.clear();
            clickMap_Phong.clear();
            list_PhongSauKhiLocHoacChon = getAllPhongSauKhiChon();
            LoadPhong(list_PhongSauKhiLocHoacChon);
        }

    }//GEN-LAST:event_txt_NgayTraPhongPropertyChange

    private void btn_ThemPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemPhongMousePressed
        // TODO add your handling code here:
        if (list_PhongDuocChonTheo1thoiGian.size() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng muốn đặt");
            return;
        }
        ThemPhongDaChon(list_PhongDuocChonTheo1thoiGian);
        LoadPhongDuocChon();
        list_PhongSauKhiLocHoacChon = getAllPhongSauKhiChon();
        LoadPhong(list_PhongSauKhiLocHoacChon);
        list_PhongDuocChonTheo1thoiGian.clear();


    }//GEN-LAST:event_btn_ThemPhongMousePressed


    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        boloc_loaiPhong = "Tất cả";
        boloc_loaiGiuong = "Tất cả";
        boloc_SokhachtoiDa = "Tất cả";
        boloc_DonGia = 0;
        boloc_Mota = "Tất cả";

        txt_NgayNhanPhong.setDate(setThoiGian0(new Date()));
        txt_NgayTraPhong.setDate(setThoiGian0(new Date(new Date().getTime() + 60 * 60 * 24 * 1000)));

        list_PhongSauKhiLocHoacChon = getAllPhongSauKhiChon();
        LoadPhong(list_PhongSauKhiLocHoacChon);


    }//GEN-LAST:event_btn_LamMoiMousePressed

    private void Jpanel_NguoiDaiDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jpanel_NguoiDaiDienMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_Jpanel_NguoiDaiDienMouseClicked

    private void Jpanel_NguoiDaiDienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jpanel_NguoiDaiDienMousePressed
        // TODO add your handling code here:
        new LeTan_DatPhong_NguoiDaiDien_GUI((JFrame) this.getParent().getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);


    }//GEN-LAST:event_Jpanel_NguoiDaiDienMousePressed

    public boolean ktraTrungChoose() {
        // Sử dụng Set để kiểm tra các mã phòng
        Set<Integer> uniqueMaPhongSet = new HashSet<>();
        for (Integer index : list_IndexPhongDuocChonDuocSelected) {
            // Lấy mã phòng từ danh sách list_PhongDuocChon
            int maPhong = list_PhongDuocChon.get(index).getMaPhong();
            // Kiểm tra mã phòng đã tồn tại trong Set chưa
            if (uniqueMaPhongSet.contains(maPhong)) {
                // Nếu mã phòng đã tồn tại, trả về false
                return false;
            }

            uniqueMaPhongSet.add(maPhong);

        }

        // Nếu duyệt hết danh sách mà không có mã phòng trùng, trả về true
        return true;

    }
    private void btn_SuaThoiGianDatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaThoiGianDatMousePressed
        // TODO add your handling code here:
        if (list_IndexPhongDuocChonDuocSelected.size() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng cần chỉnh sửa thời gian đặt");
            return;
        }

        if (!ktraTrungChoose()) {
            JOptionPane.showMessageDialog(this, "Không được chọn 2 phòng cùng mã");
            return;
        }

        new LeTan_DatPhong_SuaThoiGianDat_GUI((JFrame) this.getParent().getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);

    }//GEN-LAST:event_btn_SuaThoiGianDatMousePressed

    private void btn_HoanTatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HoanTatMousePressed
        // TODO add your handling code here:
        if (list_PhongDuocChon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa có phòng, vui lòng chọn phòng muốn đặt");
            return;
        }

        if (nguoiDaiDien == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập người đại diện");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đặt?", "Thông báo", JOptionPane.YES_NO_OPTION)
                == JOptionPane.NO_OPTION) {
            return;
        }
//      Tạo đơn đặt phòng

        DonDatPhong ddp = new DonDatPhong();
        ddp.setMaDonDat(DonDatPhong_Dao.getLastDonDatPhong().getMaDonDat() + 1);
        ddp.setNgayTaoDon(setThoiGian0(new Date()));
        ddp.setNguoiDat(nguoiDaiDien);

        ddp.setTrangThai("Đang chờ");
        ddp.setPhongs(list_PhongDuocChon);
        List<KhachHang> khachO = new ArrayList<>();
        ddp.setKhachO(khachO);

        if (DonDatPhong_Dao.createDonDatPhong(ddp)) {
            JOptionPane.showMessageDialog(this, "Đặt thành công");
            jDesktopPane1.removeAll();
            LeTan_DatPhong_GUI datPhong_Gui = new LeTan_DatPhong_GUI(nhanVien_DangSuDung);
            jDesktopPane1.add(datPhong_Gui);
            datPhong_Gui.setVisible(true);
        }


    }//GEN-LAST:event_btn_HoanTatMousePressed

    private void btn_LocMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LocMousePressed
        // TODO add your handling code here:
        new LeTan_DatPhong_BoLoc_GUI((JFrame) this.getParent().getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);

    }//GEN-LAST:event_btn_LocMousePressed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        new TrangCaNhan(nhanVien_DangSuDung).setVisible(true);
    }//GEN-LAST:event_jPanel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel Jpanel_NguoiDaiDien;
    private javax.swing.JPanel Panel_ChuThich;
    private javax.swing.JPanel Panel_ChuThich1;
    private javax.swing.JPanel Panel_ChuThich2;
    private javax.swing.JPanel Panel_ChuThich3;
    private javax.swing.JPanel Panel_ChuThich4;
    public static javax.swing.JPanel Panel_ListPhong;
    public static javax.swing.JPanel Panel_PhongDuocChons;
    private javax.swing.JPanel Panel_TienNghis;
    private javax.swing.JScrollPane Scroll_ChuThich;
    private javax.swing.JScrollPane Scroll_Phong;
    private javax.swing.JScrollPane Scroll_PhongDaChon;
    private javax.swing.JPanel SideBar;
    private keeptoo.KGradientPanel btn_HoanTat;
    private keeptoo.KGradientPanel btn_LamMoi;
    private javax.swing.JPanel btn_Loc;
    private keeptoo.KGradientPanel btn_SuaThoiGianDat;
    private keeptoo.KGradientPanel btn_ThemPhong;
    private javax.swing.JLabel button_ThongBao;
    private GUI.CirclePanel_Atatar circlePanel1;
    private javax.swing.JLabel icon_Them;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel label_Avatar;
    private javax.swing.JPanel line;
    private javax.swing.JPanel panel_GrPhong;
    public static com.toedter.calendar.JDateChooser txt_NgayNhanPhong;
    public static com.toedter.calendar.JDateChooser txt_NgayTraPhong;
    public static javax.swing.JLabel txt_TenNguoiDaiDien;
    // End of variables declaration//GEN-END:variables
}
