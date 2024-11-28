/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DAO.TienNghiDAO;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.TienNghi;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class LeTan_DatPhong_GUI extends javax.swing.JInternalFrame {

    DefaultTableCellRenderer centerRenderer;
    DecimalFormat df = new DecimalFormat("#,##0");

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_DatPhong_GUI() {
        MongoDBConnection.connection();
        database = MongoDBConnection.getDatabase();
        PhongDAO phong_dao = new PhongDAO(database);
        LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database);
        initComponents();
        Phong phong = phong_dao.getPhongByMa(102);
        LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        jPanel3.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(255, 209, 84)));
        ImageScale.setCircularImage(label_Avatar, new javax.swing.ImageIcon(getClass().getResource("/images/Avatar_NguyenHoangSang.png")));
        SideBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(255, 255, 255)));

        java.awt.GridBagLayout Panel_PhongsLayout = new java.awt.GridBagLayout();
        Panel_PhongsLayout.columnWidths = new int[]{};
        Panel_PhongsLayout.rowHeights = new int[]{};
        Panel_Phongs.setLayout(Panel_PhongsLayout);
//      Load phòng lên giao diện
        for (int i = 0; i < 30; i++) {
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
            jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

            JLabel jLabel66 = new JLabel();
            jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

            JLabel label_LoaiGiuong = new JLabel();
            label_LoaiGiuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            label_LoaiGiuong.setText(loaiPhong.getLoaiGiuong());

            JLabel jLabel67 = new JLabel();
            jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

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
            
            gridBagConstraints.insets = new Insets(0, 0, 15, 15);
            Panel_Phongs.setLayout(Panel_PhongsLayout);
            Panel_Phongs.add(panel_Phong, gridBagConstraints);
            

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
        jScrollPane2 = new javax.swing.JScrollPane();
        Panel_TienNghis = new javax.swing.JPanel();
        Panel_ChuThich6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Panel_ChuThich5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Panel_ChuThich7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Panel_ChuThich8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        Panel_ChuThich12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        Panel_ChuThich13 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        Panel_ChuThich14 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        Panel_ChuThich15 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        line = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        btn_ThemPhong3 = new keeptoo.KGradientPanel();
        jLabel28 = new javax.swing.JLabel();
        btn_LamMoi1 = new keeptoo.KGradientPanel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Panel_ListPhong = new javax.swing.JPanel();
        panel_Tang = new javax.swing.JPanel();
        btn_Tang = new keeptoo.KGradientPanel();
        jLabel41 = new javax.swing.JLabel();
        Panel_Phongs = new javax.swing.JPanel();
        panel_Tang2 = new javax.swing.JPanel();
        btn_Tang2 = new keeptoo.KGradientPanel();
        jLabel44 = new javax.swing.JLabel();
        Panel_Phongs2 = new javax.swing.JPanel();
        panel_Tang3 = new javax.swing.JPanel();
        btn_Tang3 = new keeptoo.KGradientPanel();
        jLabel45 = new javax.swing.JLabel();
        Panel_Phongs3 = new javax.swing.JPanel();
        panel_Tang4 = new javax.swing.JPanel();
        btn_Tang4 = new keeptoo.KGradientPanel();
        jLabel46 = new javax.swing.JLabel();
        Panel_Phongs4 = new javax.swing.JPanel();
        Backgroup = new javax.swing.JLabel();

        setName("page_DatPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 833));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        jPanel2.setOpaque(false);

        label_Avatar.setPreferredSize(new java.awt.Dimension(45, 45));

        javax.swing.GroupLayout circlePanel1Layout = new javax.swing.GroupLayout(circlePanel1);
        circlePanel1.setLayout(circlePanel1Layout);
        circlePanel1Layout.setHorizontalGroup(
            circlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(circlePanel1Layout.createSequentialGroup()
                .addComponent(label_Avatar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
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
        btn_Loc.setOpaque(false);

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

        txt_TenNguoiDaiDien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        icon_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Them.png"))); // NOI18N
        icon_Them.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout Jpanel_NguoiDaiDienLayout = new javax.swing.GroupLayout(Jpanel_NguoiDaiDien);
        Jpanel_NguoiDaiDien.setLayout(Jpanel_NguoiDaiDienLayout);
        Jpanel_NguoiDaiDienLayout.setHorizontalGroup(
            Jpanel_NguoiDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel_NguoiDaiDienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_TenNguoiDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Panel_TienNghis.setBackground(new java.awt.Color(0, 0, 0));
        Panel_TienNghis.setPreferredSize(new java.awt.Dimension(270, 210));
        Panel_TienNghis.setLayout(new java.awt.GridLayout(4, 2));

        Panel_ChuThich6.setOpaque(false);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Loại phòng");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich6Layout = new javax.swing.GroupLayout(Panel_ChuThich6);
        Panel_ChuThich6.setLayout(Panel_ChuThich6Layout);
        Panel_ChuThich6Layout.setHorizontalGroup(
            Panel_ChuThich6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich6Layout.setVerticalGroup(
            Panel_ChuThich6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich6);

        Panel_ChuThich5.setOpaque(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Loại phòng");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich5Layout = new javax.swing.GroupLayout(Panel_ChuThich5);
        Panel_ChuThich5.setLayout(Panel_ChuThich5Layout);
        Panel_ChuThich5Layout.setHorizontalGroup(
            Panel_ChuThich5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich5Layout.setVerticalGroup(
            Panel_ChuThich5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich5);

        Panel_ChuThich7.setOpaque(false);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Loại phòng");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich7Layout = new javax.swing.GroupLayout(Panel_ChuThich7);
        Panel_ChuThich7.setLayout(Panel_ChuThich7Layout);
        Panel_ChuThich7Layout.setHorizontalGroup(
            Panel_ChuThich7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich7Layout.setVerticalGroup(
            Panel_ChuThich7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich7);

        Panel_ChuThich8.setOpaque(false);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Loại phòng");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich8Layout = new javax.swing.GroupLayout(Panel_ChuThich8);
        Panel_ChuThich8.setLayout(Panel_ChuThich8Layout);
        Panel_ChuThich8Layout.setHorizontalGroup(
            Panel_ChuThich8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich8Layout.setVerticalGroup(
            Panel_ChuThich8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich8);

        Panel_ChuThich12.setOpaque(false);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Loại phòng");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich12Layout = new javax.swing.GroupLayout(Panel_ChuThich12);
        Panel_ChuThich12.setLayout(Panel_ChuThich12Layout);
        Panel_ChuThich12Layout.setHorizontalGroup(
            Panel_ChuThich12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich12Layout.setVerticalGroup(
            Panel_ChuThich12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich12);

        Panel_ChuThich13.setOpaque(false);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Loại phòng");

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich13Layout = new javax.swing.GroupLayout(Panel_ChuThich13);
        Panel_ChuThich13.setLayout(Panel_ChuThich13Layout);
        Panel_ChuThich13Layout.setHorizontalGroup(
            Panel_ChuThich13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich13Layout.setVerticalGroup(
            Panel_ChuThich13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich13);

        Panel_ChuThich14.setOpaque(false);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Loại phòng");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich14Layout = new javax.swing.GroupLayout(Panel_ChuThich14);
        Panel_ChuThich14.setLayout(Panel_ChuThich14Layout);
        Panel_ChuThich14Layout.setHorizontalGroup(
            Panel_ChuThich14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich14Layout.setVerticalGroup(
            Panel_ChuThich14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich14);

        Panel_ChuThich15.setOpaque(false);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Loại phòng");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong_chuThich.png"))); // NOI18N

        javax.swing.GroupLayout Panel_ChuThich15Layout = new javax.swing.GroupLayout(Panel_ChuThich15);
        Panel_ChuThich15.setLayout(Panel_ChuThich15Layout);
        Panel_ChuThich15Layout.setHorizontalGroup(
            Panel_ChuThich15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChuThich15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ChuThich15Layout.setVerticalGroup(
            Panel_ChuThich15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChuThich15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_ChuThich15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Panel_TienNghis.add(Panel_ChuThich15);

        jScrollPane2.setViewportView(Panel_TienNghis);

        javax.swing.GroupLayout SideBarLayout = new javax.swing.GroupLayout(SideBar);
        SideBar.setLayout(SideBarLayout);
        SideBarLayout.setHorizontalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGroup(SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SideBarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
        );

        jPanel1.add(SideBar);
        SideBar.setBounds(10, 20, 280, 760);

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

        btn_ThemPhong3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_ThemPhong3.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThemPhong3.setkGradientFocus(250);
        btn_ThemPhong3.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThemPhong3.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_ThemPhong3.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_ThemPhong3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemPhong3MousePressed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Sửa thời gian đặt");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThemPhong3Layout = new javax.swing.GroupLayout(btn_ThemPhong3);
        btn_ThemPhong3.setLayout(btn_ThemPhong3Layout);
        btn_ThemPhong3Layout.setHorizontalGroup(
            btn_ThemPhong3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemPhong3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        btn_ThemPhong3Layout.setVerticalGroup(
            btn_ThemPhong3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemPhong3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(btn_ThemPhong3);
        btn_ThemPhong3.setBounds(880, 740, 160, 47);

        btn_LamMoi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_LamMoi1.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi1.setkGradientFocus(250);
        btn_LamMoi1.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi1.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_LamMoi1.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_LamMoi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LamMoi1MousePressed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Hoàn tất");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_LamMoi1Layout = new javax.swing.GroupLayout(btn_LamMoi1);
        btn_LamMoi1.setLayout(btn_LamMoi1Layout);
        btn_LamMoi1Layout.setHorizontalGroup(
            btn_LamMoi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoi1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        btn_LamMoi1Layout.setVerticalGroup(
            btn_LamMoi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoi1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi1);
        btn_LamMoi1.setBounds(1080, 740, 150, 47);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(940, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(940, 520));

        Panel_ListPhong.setBackground(new java.awt.Color(0, 0, 0));
        Panel_ListPhong.setLayout(new javax.swing.BoxLayout(Panel_ListPhong, javax.swing.BoxLayout.Y_AXIS));

        panel_Tang.setOpaque(false);

        btn_Tang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Tang.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tang.setkGradientFocus(250);
        btn_Tang.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tang.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Tang.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_Tang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TangMousePressed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Tầng 1");
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

        javax.swing.GroupLayout Panel_PhongsLayout = new javax.swing.GroupLayout(Panel_Phongs);
        Panel_Phongs.setLayout(Panel_PhongsLayout);
        Panel_PhongsLayout.setHorizontalGroup(
            Panel_PhongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Panel_PhongsLayout.setVerticalGroup(
            Panel_PhongsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_TangLayout = new javax.swing.GroupLayout(panel_Tang);
        panel_Tang.setLayout(panel_TangLayout);
        panel_TangLayout.setHorizontalGroup(
            panel_TangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_TangLayout.createSequentialGroup()
                .addComponent(btn_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 858, Short.MAX_VALUE))
            .addGroup(panel_TangLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Panel_Phongs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_TangLayout.setVerticalGroup(
            panel_TangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_TangLayout.createSequentialGroup()
                .addComponent(btn_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_Phongs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        Panel_ListPhong.add(panel_Tang);

        panel_Tang2.setOpaque(false);

        btn_Tang2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Tang2.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tang2.setkGradientFocus(250);
        btn_Tang2.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tang2.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Tang2.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_Tang2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_Tang2MousePressed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Tầng 1");
        jLabel44.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_Tang2Layout = new javax.swing.GroupLayout(btn_Tang2);
        btn_Tang2.setLayout(btn_Tang2Layout);
        btn_Tang2Layout.setHorizontalGroup(
            btn_Tang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Tang2Layout.createSequentialGroup()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btn_Tang2Layout.setVerticalGroup(
            btn_Tang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Tang2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel44)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panel_Phongs2.setOpaque(false);

        javax.swing.GroupLayout Panel_Phongs2Layout = new javax.swing.GroupLayout(Panel_Phongs2);
        Panel_Phongs2.setLayout(Panel_Phongs2Layout);
        Panel_Phongs2Layout.setHorizontalGroup(
            Panel_Phongs2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Panel_Phongs2Layout.setVerticalGroup(
            Panel_Phongs2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_Tang2Layout = new javax.swing.GroupLayout(panel_Tang2);
        panel_Tang2.setLayout(panel_Tang2Layout);
        panel_Tang2Layout.setHorizontalGroup(
            panel_Tang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Tang2Layout.createSequentialGroup()
                .addComponent(btn_Tang2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 858, Short.MAX_VALUE))
            .addComponent(Panel_Phongs2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Tang2Layout.setVerticalGroup(
            panel_Tang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Tang2Layout.createSequentialGroup()
                .addComponent(btn_Tang2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Panel_Phongs2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Panel_ListPhong.add(panel_Tang2);

        panel_Tang3.setOpaque(false);

        btn_Tang3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Tang3.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tang3.setkGradientFocus(250);
        btn_Tang3.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tang3.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Tang3.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_Tang3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_Tang3MousePressed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Tầng 1");
        jLabel45.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_Tang3Layout = new javax.swing.GroupLayout(btn_Tang3);
        btn_Tang3.setLayout(btn_Tang3Layout);
        btn_Tang3Layout.setHorizontalGroup(
            btn_Tang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Tang3Layout.createSequentialGroup()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btn_Tang3Layout.setVerticalGroup(
            btn_Tang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Tang3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel45)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panel_Phongs3.setOpaque(false);

        javax.swing.GroupLayout Panel_Phongs3Layout = new javax.swing.GroupLayout(Panel_Phongs3);
        Panel_Phongs3.setLayout(Panel_Phongs3Layout);
        Panel_Phongs3Layout.setHorizontalGroup(
            Panel_Phongs3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Panel_Phongs3Layout.setVerticalGroup(
            Panel_Phongs3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_Tang3Layout = new javax.swing.GroupLayout(panel_Tang3);
        panel_Tang3.setLayout(panel_Tang3Layout);
        panel_Tang3Layout.setHorizontalGroup(
            panel_Tang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Tang3Layout.createSequentialGroup()
                .addComponent(btn_Tang3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 858, Short.MAX_VALUE))
            .addComponent(Panel_Phongs3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Tang3Layout.setVerticalGroup(
            panel_Tang3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Tang3Layout.createSequentialGroup()
                .addComponent(btn_Tang3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Panel_Phongs3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Panel_ListPhong.add(panel_Tang3);

        panel_Tang4.setOpaque(false);

        btn_Tang4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Tang4.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tang4.setkGradientFocus(250);
        btn_Tang4.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tang4.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Tang4.setPreferredSize(new java.awt.Dimension(110, 47));
        btn_Tang4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_Tang4MousePressed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Tầng 1");
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_Tang4Layout = new javax.swing.GroupLayout(btn_Tang4);
        btn_Tang4.setLayout(btn_Tang4Layout);
        btn_Tang4Layout.setHorizontalGroup(
            btn_Tang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Tang4Layout.createSequentialGroup()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btn_Tang4Layout.setVerticalGroup(
            btn_Tang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Tang4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel46)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Panel_Phongs4.setOpaque(false);

        javax.swing.GroupLayout Panel_Phongs4Layout = new javax.swing.GroupLayout(Panel_Phongs4);
        Panel_Phongs4.setLayout(Panel_Phongs4Layout);
        Panel_Phongs4Layout.setHorizontalGroup(
            Panel_Phongs4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Panel_Phongs4Layout.setVerticalGroup(
            Panel_Phongs4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_Tang4Layout = new javax.swing.GroupLayout(panel_Tang4);
        panel_Tang4.setLayout(panel_Tang4Layout);
        panel_Tang4Layout.setHorizontalGroup(
            panel_Tang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Tang4Layout.createSequentialGroup()
                .addComponent(btn_Tang4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 858, Short.MAX_VALUE))
            .addComponent(Panel_Phongs4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Tang4Layout.setVerticalGroup(
            panel_Tang4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Tang4Layout.createSequentialGroup()
                .addComponent(btn_Tang4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Panel_Phongs4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Panel_ListPhong.add(panel_Tang4);

        jScrollPane1.setViewportView(Panel_ListPhong);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(310, 70, 940, 470);

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

    }//GEN-LAST:event_txt_NgayNhanPhongPropertyChange

    private void txt_NgayTraPhongPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_NgayTraPhongPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayTraPhongPropertyChange

    private void btn_ThemPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemPhongMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_ThemPhongMousePressed

    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoiMousePressed

    private void Jpanel_NguoiDaiDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jpanel_NguoiDaiDienMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_Jpanel_NguoiDaiDienMouseClicked

    private void Jpanel_NguoiDaiDienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jpanel_NguoiDaiDienMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_Jpanel_NguoiDaiDienMousePressed

    private void btn_TangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TangMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TangMousePressed

    private void btn_ThemPhong3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemPhong3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemPhong3MousePressed

    private void btn_LamMoi1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoi1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoi1MousePressed

    private void btn_Tang2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Tang2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Tang2MousePressed

    private void btn_Tang3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Tang3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Tang3MousePressed

    private void btn_Tang4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Tang4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Tang4MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel Jpanel_NguoiDaiDien;
    private javax.swing.JPanel Panel_ChuThich;
    private javax.swing.JPanel Panel_ChuThich1;
    private javax.swing.JPanel Panel_ChuThich12;
    private javax.swing.JPanel Panel_ChuThich13;
    private javax.swing.JPanel Panel_ChuThich14;
    private javax.swing.JPanel Panel_ChuThich15;
    private javax.swing.JPanel Panel_ChuThich2;
    private javax.swing.JPanel Panel_ChuThich3;
    private javax.swing.JPanel Panel_ChuThich4;
    private javax.swing.JPanel Panel_ChuThich5;
    private javax.swing.JPanel Panel_ChuThich6;
    private javax.swing.JPanel Panel_ChuThich7;
    private javax.swing.JPanel Panel_ChuThich8;
    private javax.swing.JPanel Panel_ListPhong;
    private javax.swing.JPanel Panel_Phongs;
    private javax.swing.JPanel Panel_Phongs2;
    private javax.swing.JPanel Panel_Phongs3;
    private javax.swing.JPanel Panel_Phongs4;
    private javax.swing.JPanel Panel_TienNghis;
    private javax.swing.JPanel SideBar;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_LamMoi1;
    private javax.swing.JPanel btn_Loc;
    private keeptoo.KGradientPanel btn_Tang;
    private keeptoo.KGradientPanel btn_Tang2;
    private keeptoo.KGradientPanel btn_Tang3;
    private keeptoo.KGradientPanel btn_Tang4;
    private keeptoo.KGradientPanel btn_ThemPhong;
    private keeptoo.KGradientPanel btn_ThemPhong3;
    private javax.swing.JLabel button_ThongBao;
    private GUI.CirclePanel_Atatar circlePanel1;
    private javax.swing.JLabel icon_Them;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_Avatar;
    private javax.swing.JPanel line;
    private javax.swing.JPanel panel_Tang;
    private javax.swing.JPanel panel_Tang2;
    private javax.swing.JPanel panel_Tang3;
    private javax.swing.JPanel panel_Tang4;
    private com.toedter.calendar.JDateChooser txt_NgayNhanPhong;
    private com.toedter.calendar.JDateChooser txt_NgayTraPhong;
    public static javax.swing.JLabel txt_TenNguoiDaiDien;
    // End of variables declaration//GEN-END:variables
}
