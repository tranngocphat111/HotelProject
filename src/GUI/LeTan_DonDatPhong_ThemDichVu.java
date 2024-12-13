/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_DonDatPhong_GUI.list_DonDatPhong;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.DocDuLieuLenTablePhong;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.cb_LoaiPhong;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.cb_Phong;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.cb_Tang;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.getAllPhongTheoLoaiPhong;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.getAllPhongTheoPhong;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.getAllPhongTheoTang;
import static GUI.LeTan_DonDatPhong_PhongCuaDon_GUI.list_Phong_filter;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import keeptoo.KGradientPanel;
import model.DAO.DichVuDAO;
import model.DAO.DichVuSuDungDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.HoaDonDAO;
import model.DTO.DichVu;
import model.DTO.DichVuSuDung;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.PhongEmbed;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_ThemDichVu extends javax.swing.JDialog {

    private List<DichVuSuDungEmbed> list_DichVu = new ArrayList<>();
    private DichVuDAO dichVu_dao = new DichVuDAO(database);
    DecimalFormat df = new DecimalFormat("#,##0");
    private List<DichVuSuDungEmbed> list_DichVuDuocChon = new ArrayList<>();
    private ArrayList<KGradientPanel> list_DichVuCoSan = new ArrayList<>();
    private ArrayList<KGradientPanel> list_btn = new ArrayList<>();
    private DonDatPhong donDatPhong = new DonDatPhong();
    private DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database);
    private HoaDonDAO hoaDon_Dao = new HoaDonDAO(database);
    private List<JLabel> list_Cong = new ArrayList<>();
    private List<JLabel> list_Tru = new ArrayList<>();
    private List<JLabel> list_SoLuong = new ArrayList<>();
    DichVuSuDungDAO dichVuSuDungDAO = new DichVuSuDungDAO(database);
    List<DichVuSuDung> list_dvsd = new ArrayList<>();
    private int maDDp;
PhongEmbed phongE;
    /**
     * Creates new form LeTan_DonDatPhong_ThemDichVui
     */
    public LeTan_DonDatPhong_ThemDichVu(int maDDP, PhongEmbed phongE, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        this.phongE = phongE;
        this.maDDp = maDDP;

//        Đọc dữ liệu lên frame
        list_dvsd = dichVuSuDungDAO.getAllDichVu();
        list_DichVu = phongE.getDichVuSuDung();
        DocDuLieuLenFrame();
//        Load những dịch vụ mà đơn đặt phòng đã có
        for (DichVuSuDungEmbed dv : list_DichVu) {
            for (KGradientPanel btn : list_btn) {
                if (dichVuSuDungDAO.getDichVuEmbedByMa(dv.getMaDVSD()).getMaDV() == Integer.parseInt(btn.getName())) {
                    list_DichVuCoSan.add(btn);
                    btn.setkEndColor(new java.awt.Color(61, 214, 89));
                    btn.setkStartColor(new java.awt.Color(13, 195, 6));
                    btn.setFocusable(false);
                    break;

                }
            }
        }

//        Bắt sự kiện các Dịch vụ
        list_btn.forEach((btn) -> {
            btn.addMouseListener(new MouseListener() {
                boolean click = false;

                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    for (KGradientPanel btn_DaChon : list_DichVuCoSan) {
                        if (btn.getName().equals(btn_DaChon.getName())) {
                            return;
                        }
                    }
                    if (click) {

                        btn.setkStartColor(new java.awt.Color(255, 255, 255));
                        btn.setkEndColor(new java.awt.Color(255, 255, 255));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                        click = false;
                    } else {
                        btn.setkStartColor(new java.awt.Color(255, 225, 27));
                        btn.setkEndColor(new java.awt.Color(255, 222, 89));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                        click = true;
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
//        Bắt sự kiện dấu trừ
        list_Tru.forEach((element) -> {
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    for (JLabel soluong : list_SoLuong) {
                        if (element.getName().equals(soluong.getName())) {
                            int TruDi = Integer.parseInt(soluong.getText()) - 1;
                            if (TruDi >= getSoLuongBanDau(Integer.parseInt(element.getName()))) {
                                soluong.setText(TruDi + "");
                                if (TruDi == 0) {
                                    list_btn.forEach((btn) -> {
                                        if (element.getName().equals(btn.getName())) {
                                            btn.setkStartColor(new java.awt.Color(255, 255, 255));
                                            btn.setkEndColor(new java.awt.Color(255, 255, 255));
                                            btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                                            btn.setBorder(null);

                                        }
                                    });
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Không thể giảm được nữa !!!");
                                return;
                            }

                        }
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
        //        Bắt sự kiện dấu cộng
        list_Cong.forEach((element) -> {
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    for (JLabel soluong : list_SoLuong) {
                        if (element.getName().equals(soluong.getName())) {
                            int congThem = Integer.parseInt(soluong.getText()) + 1;
                            soluong.setText(congThem + "");
                            list_btn.forEach((btn) -> {
                                if (element.getName().equals(btn.getName())) {
                                    btn.setkStartColor(new java.awt.Color(255, 225, 27));
                                    btn.setkEndColor(new java.awt.Color(255, 222, 89));
                                    btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                                    btn.setBorder(null);
                                }
                            });

                        }
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
        setLocationRelativeTo(null);
    }

    public int getSoLuongBanDau(int maDV) {
        for (DichVuSuDungEmbed dv : list_DichVu) {
            if (dichVu_dao.getDichVuByTen(dv.getTenDV()).getMaDV() == maDV) {
                return dv.getSoLuong();
            }
        }
        return 0;
    }

    public void DocDuLieuLenFrame() {
        int i = 1;
        Panel_DichVus.setLayout(new java.awt.GridLayout(list_DichVu.size(), 1, 0, 30));
        for (DichVuSuDungEmbed dichVu : list_DichVu) {
            KGradientPanel btn_DichVu = new KGradientPanel();
            btn_DichVu.setkEndColor(new java.awt.Color(255, 255, 255));
            btn_DichVu.setkStartColor(new java.awt.Color(255, 255, 255));
            btn_DichVu.setPreferredSize(new java.awt.Dimension(687, 93));
            btn_DichVu.setLayout(null);

            JLabel icon_DichVu = new JLabel();
            icon_DichVu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            DichVu dv = dichVu_dao.getDichVuByTen(dichVu.getTenDV());
            System.out.println(dv);
            ImageIcon icon = new ImageScale().load1(new ImageIcon(dv.getHinhAnh()), 77, 60);
            icon_DichVu.setIcon(icon);
            btn_DichVu.add(icon_DichVu);
            icon_DichVu.setBounds(9, 6, 72, 86);

            JLabel label_MoTa = new JLabel();
            label_MoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            label_MoTa.setText(dv.getMoTa());
            btn_DichVu.add(label_MoTa);
            label_MoTa.setBounds(90, 50, 280, 20);

            JLabel label_TenDichVu = new JLabel();
            label_TenDichVu.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
            label_TenDichVu.setText(dv.getTenDV());
            btn_DichVu.add(label_TenDichVu);
            label_TenDichVu.setBounds(90, 10, 161, 50);

            JLabel label_DonGia = new JLabel();
            label_DonGia.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
            label_DonGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label_DonGia.setText(df.format(dv.getDonGia()) + " VND");
            btn_DichVu.add(label_DonGia);
            label_DonGia.setBounds(370, 10, 230, 70);

            JPanel panel_soLuongSuDung = new JPanel();
            panel_soLuongSuDung.setOpaque(false);

            JLabel icon_Cong = new JLabel();
            icon_Cong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            icon_Cong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_Cong.png"))); // NOI18N

            JLabel icon_Tru = new JLabel();
            icon_Tru.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            icon_Tru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_Tru.png"))); // NOI18N

            JLabel label_SoLuong = new JLabel();
            label_SoLuong.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
            label_SoLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            if (list_DichVu.size() != 0) {
                for (DichVuSuDungEmbed dichVU : list_DichVu) {
                    if (dichVu_dao.getDichVuByTen(dichVU.getTenDV()).getMaDV() == dv.getMaDV()) {
                        label_SoLuong.setText(dichVuSuDungDAO.getDichVuEmbedByMa(dichVu.getMaDVSD()).getSoLuong() + "");
                        break;
                    } else {
                        label_SoLuong.setText("0");
                    }
                }
            } else {
                label_SoLuong.setText("0");
            }

            label_SoLuong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout panel_soLuongSuDungLayout = new javax.swing.GroupLayout(panel_soLuongSuDung);
            panel_soLuongSuDung.setLayout(panel_soLuongSuDungLayout);
            panel_soLuongSuDungLayout.setHorizontalGroup(
                    panel_soLuongSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_soLuongSuDungLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel_soLuongSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(icon_Cong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(icon_Tru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label_SoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            panel_soLuongSuDungLayout.setVerticalGroup(
                    panel_soLuongSuDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_soLuongSuDungLayout.createSequentialGroup()
                                    .addComponent(icon_Cong, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3)
                                    .addComponent(label_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
                                    .addGap(3, 3, 3)
                                    .addComponent(icon_Tru, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );

            btn_DichVu.add(panel_soLuongSuDung);
            panel_soLuongSuDung.setBounds(598, 6, 90, 80);

            Panel_DichVus.add(btn_DichVu);
            btn_DichVu.setName(i + "");
            icon_Cong.setName(i + "");
            icon_Tru.setName(i + "");
            label_SoLuong.setName(i + "");
            i++;
            list_btn.add(btn_DichVu);
            list_Cong.add(icon_Cong);
            list_Tru.add(icon_Tru);
            icon_Cong.setCursor(new Cursor(Cursor.HAND_CURSOR));
            icon_Tru.setCursor(new Cursor(Cursor.HAND_CURSOR));
            list_SoLuong.add(label_SoLuong);
            Panel_DichVus.add(btn_DichVu);
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

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        Panel_DichVus = new javax.swing.JPanel();
        btn_XacNhan = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Huy = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        kGradientPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        kGradientPanel1.setkEndColor(new java.awt.Color(0, 0, 0));
        kGradientPanel1.setkGradientFocus(250);
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 0, 0));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(783, 450));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(725, 384));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(783, 384));

        Panel_DichVus.setBackground(new java.awt.Color(0, 0, 0));
        Panel_DichVus.setLayout(new java.awt.GridLayout(1, 1, 0, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(Panel_DichVus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(740, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(Panel_DichVus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(355, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel3);

        btn_XacNhan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_XacNhan.setkGradientFocus(250);
        btn_XacNhan.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_XacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_XacNhanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_XacNhanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_XacNhanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_XacNhanMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("XÁC NHẬN");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_XacNhanLayout = new javax.swing.GroupLayout(btn_XacNhan);
        btn_XacNhan.setLayout(btn_XacNhanLayout);
        btn_XacNhanLayout.setHorizontalGroup(
            btn_XacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_XacNhanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        btn_XacNhanLayout.setVerticalGroup(
            btn_XacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_XacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setkGradientFocus(250);
        btn_Huy.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Huy.addMouseListener(new java.awt.event.MouseAdapter() {
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
        jLabel20.setText("HỦY");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyLayout = new javax.swing.GroupLayout(btn_Huy);
        btn_Huy.setLayout(btn_HuyLayout);
        btn_HuyLayout.setHorizontalGroup(
            btn_HuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_HuyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        btn_HuyLayout.setVerticalGroup(
            btn_HuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_HuyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_XacNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_XacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XacNhanMouseClicked

    private void btn_XacNhanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMouseEntered
        // TODO add your handling code here:
        btn_XacNhan.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_XacNhan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_XacNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_XacNhan.setBorder(null);
    }//GEN-LAST:event_btn_XacNhanMouseEntered

    private void btn_XacNhanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMouseExited
        // TODO add your handling code here:
        btn_XacNhan.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_XacNhan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_XacNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_XacNhan.setBorder(null);
    }//GEN-LAST:event_btn_XacNhanMouseExited

    private void btn_HuyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseEntered
        // TODO add your handling code here:
        btn_Huy.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Huy.setBorder(null);
    }//GEN-LAST:event_btn_HuyMouseEntered

    private void btn_HuyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseExited
        // TODO add your handling code here:
        btn_Huy.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Huy.setBorder(null);
    }//GEN-LAST:event_btn_HuyMouseExited

    public DichVuSuDungEmbed ktraDaSuDungDV(int maDV){
        for(DichVuSuDungEmbed dv : list_DichVu){
            if(dichVuSuDungDAO.getDichVuEmbedByMa(dv.getMaDVSD()).getMaDV() == maDV){
                return dv;
            }
        }
        
        return null;
        
    }
    
    public DichVuSuDungEmbed chuyenDoiDV(DichVuSuDung dv ){
        DichVuSuDungEmbed dvmoi = new DichVuSuDungEmbed();
        dvmoi.setMaDVSD(dv.getMaDVSD());
        dvmoi.setTenDV(dv.getTenDV());
        dvmoi.setSoLuong(dv.getSoLuong());
        dvmoi.setDonGia(dv.getDonGia());
        return dvmoi;
    }
    private void btn_XacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMousePressed
        // TODO add your handling code here:
        for (JLabel soluong : list_SoLuong) {
            if (Integer.parseInt(soluong.getText()) != 0) {
                DichVuSuDungEmbed dv = ktraDaSuDungDV(Integer.parseInt(soluong.getName()));
                if(dv != null){
                    dichVuSuDungDAO.updateSoLuong(dv.getMaDVSD(), Integer.parseInt(soluong.getText()));
                }else{
                    DichVuSuDung dvsd_moi = new DichVuSuDung(list_dvsd.getLast().getMaDVSD()+1,phongE.getMaPhong(),maDDp, Integer.parseInt(soluong.getName()), dv.getTenDV(), Integer.parseInt(soluong.getText()), dichVu_dao.getDichVuByTen(dv.getTenDV()).getDonGia(), new Date() );
                    dichVuSuDungDAO.createDichVuSuDung(dvsd_moi);
                    
                    list_DichVu.add(chuyenDoiDV(dvsd_moi));
                    phongE.setDichVuSuDung(list_DichVu);
                    donDatPhong_dao.updatePhongTrongDonDatPhong(maDDp, phongE);
                    
                }
                
                
              
            }
        }
        
        list_Phong_filter = donDatPhong_dao.getDonDatPhongByMa(maDDp).getPhongs();
        if (cb_LoaiPhong.getSelectedItem() == null || cb_Tang.getSelectedItem() == null || cb_Phong.getSelectedItem() == null) {
            return;
        }

        list_Phong_filter = donDatPhong_dao.getDonDatPhongByMa(maDDp).getPhongs();
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
//        

        setVisible(false);
    }//GEN-LAST:event_btn_XacNhanMousePressed

    private void btn_HuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMousePressed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_HuyMousePressed
//    public int getTongtien(HoaDon hoadon) {
//        int tongtien = 0;
//        for (DonDatPhong ddp : list_DonDatPhong) {
//            if (ddp.getHoaDon() == hoadon.getMaHoaDon()) {
//                tongtien = tongtien + ddp.thanhTien();
//            }
//        }
//        return tongtien;
//    }

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
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThemDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThemDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThemDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeTan_DonDatPhong_ThemDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DonDatPhong_ThemDichVui dialog = new LeTan_DonDatPhong_ThemDichVui(new javax.swing.JFrame(), true);
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
//                LeTan_DonDatPhong_ThemDichVu dialog = new LeTan_DonDatPhong_ThemDichVu(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel Panel_DichVus;
    private keeptoo.KGradientPanel btn_Huy;
    private keeptoo.KGradientPanel btn_XacNhan;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
