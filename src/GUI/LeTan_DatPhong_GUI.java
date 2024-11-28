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
        jScrollPane3 = new javax.swing.JScrollPane();
        Panel_PhongDuocChons = new javax.swing.JPanel();
        panel_PhongDuocChon = new javax.swing.JPanel();
        circlePanel2 = new GUI.CirclePanel();
        jLabel29 = new javax.swing.JLabel();
        panel_Phong23 = new javax.swing.JPanel();
        jPanel131 = new javax.swing.JPanel();
        label_SoPhong25 = new javax.swing.JLabel();
        jPanel132 = new javax.swing.JPanel();
        label_SoLuongKhach25 = new javax.swing.JLabel();
        jLabel318 = new javax.swing.JLabel();
        jLabel319 = new javax.swing.JLabel();
        label_LoaiGiuong25 = new javax.swing.JLabel();
        jLabel320 = new javax.swing.JLabel();
        label_LoaiPhong25 = new javax.swing.JLabel();
        jPanel133 = new javax.swing.JPanel();
        jLabel321 = new javax.swing.JLabel();
        jPanel134 = new javax.swing.JPanel();
        jLabel322 = new javax.swing.JLabel();
        jLabel323 = new javax.swing.JLabel();
        jLabel324 = new javax.swing.JLabel();
        jLabel325 = new javax.swing.JLabel();
        jLabel326 = new javax.swing.JLabel();
        jLabel327 = new javax.swing.JLabel();
        jPanel135 = new javax.swing.JPanel();
        jLabel328 = new javax.swing.JLabel();
        txt_NgayTra1 = new javax.swing.JLabel();
        txt_NgayNhan1 = new javax.swing.JLabel();
        panel_PhongDuocChon1 = new javax.swing.JPanel();
        circlePanel3 = new GUI.CirclePanel();
        jLabel30 = new javax.swing.JLabel();
        panel_Phong24 = new javax.swing.JPanel();
        jPanel136 = new javax.swing.JPanel();
        label_SoPhong26 = new javax.swing.JLabel();
        jPanel137 = new javax.swing.JPanel();
        label_SoLuongKhach26 = new javax.swing.JLabel();
        jLabel329 = new javax.swing.JLabel();
        jLabel330 = new javax.swing.JLabel();
        label_LoaiGiuong26 = new javax.swing.JLabel();
        jLabel331 = new javax.swing.JLabel();
        label_LoaiPhong26 = new javax.swing.JLabel();
        jPanel138 = new javax.swing.JPanel();
        jLabel332 = new javax.swing.JLabel();
        jPanel139 = new javax.swing.JPanel();
        jLabel333 = new javax.swing.JLabel();
        jLabel334 = new javax.swing.JLabel();
        jLabel335 = new javax.swing.JLabel();
        jLabel336 = new javax.swing.JLabel();
        jLabel337 = new javax.swing.JLabel();
        jLabel338 = new javax.swing.JLabel();
        jPanel140 = new javax.swing.JPanel();
        jLabel339 = new javax.swing.JLabel();
        txt_NgayTra2 = new javax.swing.JLabel();
        txt_NgayNhan2 = new javax.swing.JLabel();
        panel_PhongDuocChon2 = new javax.swing.JPanel();
        circlePanel4 = new GUI.CirclePanel();
        jLabel31 = new javax.swing.JLabel();
        panel_Phong25 = new javax.swing.JPanel();
        jPanel141 = new javax.swing.JPanel();
        label_SoPhong27 = new javax.swing.JLabel();
        jPanel142 = new javax.swing.JPanel();
        label_SoLuongKhach27 = new javax.swing.JLabel();
        jLabel340 = new javax.swing.JLabel();
        jLabel341 = new javax.swing.JLabel();
        label_LoaiGiuong27 = new javax.swing.JLabel();
        jLabel342 = new javax.swing.JLabel();
        label_LoaiPhong27 = new javax.swing.JLabel();
        jPanel143 = new javax.swing.JPanel();
        jLabel343 = new javax.swing.JLabel();
        jPanel144 = new javax.swing.JPanel();
        jLabel344 = new javax.swing.JLabel();
        jLabel345 = new javax.swing.JLabel();
        jLabel346 = new javax.swing.JLabel();
        jLabel347 = new javax.swing.JLabel();
        jLabel348 = new javax.swing.JLabel();
        jLabel349 = new javax.swing.JLabel();
        jPanel145 = new javax.swing.JPanel();
        jLabel350 = new javax.swing.JLabel();
        txt_NgayTra3 = new javax.swing.JLabel();
        txt_NgayNhan3 = new javax.swing.JLabel();
        panel_PhongDuocChon3 = new javax.swing.JPanel();
        circlePanel5 = new GUI.CirclePanel();
        jLabel32 = new javax.swing.JLabel();
        panel_Phong26 = new javax.swing.JPanel();
        jPanel146 = new javax.swing.JPanel();
        label_SoPhong28 = new javax.swing.JLabel();
        jPanel147 = new javax.swing.JPanel();
        label_SoLuongKhach28 = new javax.swing.JLabel();
        jLabel351 = new javax.swing.JLabel();
        jLabel352 = new javax.swing.JLabel();
        label_LoaiGiuong28 = new javax.swing.JLabel();
        jLabel353 = new javax.swing.JLabel();
        label_LoaiPhong28 = new javax.swing.JLabel();
        jPanel148 = new javax.swing.JPanel();
        jLabel354 = new javax.swing.JLabel();
        jPanel149 = new javax.swing.JPanel();
        jLabel355 = new javax.swing.JLabel();
        jLabel356 = new javax.swing.JLabel();
        jLabel357 = new javax.swing.JLabel();
        jLabel358 = new javax.swing.JLabel();
        jLabel359 = new javax.swing.JLabel();
        jLabel360 = new javax.swing.JLabel();
        jPanel150 = new javax.swing.JPanel();
        jLabel361 = new javax.swing.JLabel();
        txt_NgayTra4 = new javax.swing.JLabel();
        txt_NgayNhan4 = new javax.swing.JLabel();
        panel_PhongDuocChon4 = new javax.swing.JPanel();
        circlePanel6 = new GUI.CirclePanel();
        jLabel42 = new javax.swing.JLabel();
        panel_Phong27 = new javax.swing.JPanel();
        jPanel151 = new javax.swing.JPanel();
        label_SoPhong29 = new javax.swing.JLabel();
        jPanel152 = new javax.swing.JPanel();
        label_SoLuongKhach29 = new javax.swing.JLabel();
        jLabel362 = new javax.swing.JLabel();
        jLabel363 = new javax.swing.JLabel();
        label_LoaiGiuong29 = new javax.swing.JLabel();
        jLabel364 = new javax.swing.JLabel();
        label_LoaiPhong29 = new javax.swing.JLabel();
        jPanel153 = new javax.swing.JPanel();
        jLabel365 = new javax.swing.JLabel();
        jPanel154 = new javax.swing.JPanel();
        jLabel366 = new javax.swing.JLabel();
        jLabel367 = new javax.swing.JLabel();
        jLabel368 = new javax.swing.JLabel();
        jLabel369 = new javax.swing.JLabel();
        jLabel370 = new javax.swing.JLabel();
        jLabel371 = new javax.swing.JLabel();
        jPanel155 = new javax.swing.JPanel();
        jLabel372 = new javax.swing.JLabel();
        txt_NgayTra5 = new javax.swing.JLabel();
        txt_NgayNhan5 = new javax.swing.JLabel();
        panel_PhongDuocChon5 = new javax.swing.JPanel();
        circlePanel7 = new GUI.CirclePanel();
        jLabel47 = new javax.swing.JLabel();
        panel_Phong28 = new javax.swing.JPanel();
        jPanel156 = new javax.swing.JPanel();
        label_SoPhong30 = new javax.swing.JLabel();
        jPanel157 = new javax.swing.JPanel();
        label_SoLuongKhach30 = new javax.swing.JLabel();
        jLabel373 = new javax.swing.JLabel();
        jLabel374 = new javax.swing.JLabel();
        label_LoaiGiuong30 = new javax.swing.JLabel();
        jLabel375 = new javax.swing.JLabel();
        label_LoaiPhong30 = new javax.swing.JLabel();
        jPanel158 = new javax.swing.JPanel();
        jLabel376 = new javax.swing.JLabel();
        jPanel159 = new javax.swing.JPanel();
        jLabel377 = new javax.swing.JLabel();
        jLabel378 = new javax.swing.JLabel();
        jLabel379 = new javax.swing.JLabel();
        jLabel380 = new javax.swing.JLabel();
        jLabel381 = new javax.swing.JLabel();
        jLabel382 = new javax.swing.JLabel();
        jPanel160 = new javax.swing.JPanel();
        jLabel383 = new javax.swing.JLabel();
        txt_NgayTra6 = new javax.swing.JLabel();
        txt_NgayNhan6 = new javax.swing.JLabel();
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

        jScrollPane3.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setBorder(null);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        Panel_PhongDuocChons.setBackground(new java.awt.Color(0, 0, 0));
        Panel_PhongDuocChons.setAutoscrolls(true);
        Panel_PhongDuocChons.setLayout(new java.awt.GridLayout(1, 5, 15, 0));

        panel_PhongDuocChon.setBackground(new java.awt.Color(255, 255, 153));
        panel_PhongDuocChon.setOpaque(false);
        panel_PhongDuocChon.setPreferredSize(new java.awt.Dimension(150, 150));
        panel_PhongDuocChon.setLayout(null);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_close.png"))); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout circlePanel2Layout = new javax.swing.GroupLayout(circlePanel2);
        circlePanel2.setLayout(circlePanel2Layout);
        circlePanel2Layout.setHorizontalGroup(
            circlePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        circlePanel2Layout.setVerticalGroup(
            circlePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_PhongDuocChon.add(circlePanel2);
        circlePanel2.setBounds(122, 7, 25, 25);

        panel_Phong23.setBackground(new java.awt.Color(255, 255, 255));

        jPanel131.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel131.setOpaque(false);

        label_SoPhong25.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        label_SoPhong25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SoPhong25.setText("101");
        label_SoPhong25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel131Layout = new javax.swing.GroupLayout(jPanel131);
        jPanel131.setLayout(jPanel131Layout);
        jPanel131Layout.setHorizontalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel131Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_SoPhong25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel131Layout.setVerticalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel131Layout.createSequentialGroup()
                .addComponent(label_SoPhong25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel132.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel132.setOpaque(false);
        jPanel132.setPreferredSize(new java.awt.Dimension(77, 72));

        label_SoLuongKhach25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_SoLuongKhach25.setText("3");

        jLabel318.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel319.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiGiuong25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiGiuong25.setText("Đôi");

        jLabel320.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiPhong25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiPhong25.setText("Family");

        javax.swing.GroupLayout jPanel132Layout = new javax.swing.GroupLayout(jPanel132);
        jPanel132.setLayout(jPanel132Layout);
        jPanel132Layout.setHorizontalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel132Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel132Layout.createSequentialGroup()
                        .addComponent(jLabel318)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_SoLuongKhach25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel132Layout.createSequentialGroup()
                        .addComponent(jLabel319)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiGiuong25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel132Layout.createSequentialGroup()
                        .addComponent(jLabel320)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiPhong25, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
        );
        jPanel132Layout.setVerticalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel132Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_SoLuongKhach25, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel318))
                .addGap(0, 0, 0)
                .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiGiuong25, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel319))
                .addGap(0, 0, 0)
                .addGroup(jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiPhong25, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel320)))
        );

        jPanel133.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel133.setOpaque(false);

        jLabel321.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel321.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel321.setText("1.000.000");

        javax.swing.GroupLayout jPanel133Layout = new javax.swing.GroupLayout(jPanel133);
        jPanel133.setLayout(jPanel133Layout);
        jPanel133Layout.setHorizontalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel133Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel321, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel133Layout.setVerticalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel133Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel321, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel134.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel134.setOpaque(false);

        jLabel322.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel323.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel324.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel325.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel326.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel327.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel134Layout = new javax.swing.GroupLayout(jPanel134);
        jPanel134.setLayout(jPanel134Layout);
        jPanel134Layout.setHorizontalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel134Layout.createSequentialGroup()
                        .addComponent(jLabel322)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel323))
                    .addGroup(jPanel134Layout.createSequentialGroup()
                        .addComponent(jLabel324)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel325))
                    .addGroup(jPanel134Layout.createSequentialGroup()
                        .addComponent(jLabel326)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel327)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel134Layout.setVerticalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel323)
                    .addComponent(jLabel322))
                .addGap(4, 4, 4)
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel325)
                    .addComponent(jLabel324))
                .addGap(4, 4, 4)
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel327)
                    .addComponent(jLabel326))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel135.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel135.setOpaque(false);

        jLabel328.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel328.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel328.setText("-");

        txt_NgayTra1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayTra1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayTra1.setText("27/09");

        txt_NgayNhan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayNhan1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayNhan1.setText("12/09");

        javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
        jPanel135.setLayout(jPanel135Layout);
        jPanel135Layout.setHorizontalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel135Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_NgayNhan1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel328, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_NgayTra1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel135Layout.setVerticalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_NgayTra1)
                .addComponent(jLabel328, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_NgayNhan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_Phong23Layout = new javax.swing.GroupLayout(panel_Phong23);
        panel_Phong23.setLayout(panel_Phong23Layout);
        panel_Phong23Layout.setHorizontalGroup(
            panel_Phong23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong23Layout.createSequentialGroup()
                .addGroup(panel_Phong23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel132, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel133, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel135, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Phong23Layout.setVerticalGroup(
            panel_Phong23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong23Layout.createSequentialGroup()
                .addGroup(panel_Phong23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Phong23Layout.createSequentialGroup()
                        .addComponent(jPanel131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel132, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_PhongDuocChon.add(panel_Phong23);
        panel_Phong23.setBounds(0, 16, 137, 134);

        Panel_PhongDuocChons.add(panel_PhongDuocChon);

        panel_PhongDuocChon1.setBackground(new java.awt.Color(255, 255, 153));
        panel_PhongDuocChon1.setOpaque(false);
        panel_PhongDuocChon1.setPreferredSize(new java.awt.Dimension(150, 150));
        panel_PhongDuocChon1.setLayout(null);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_close.png"))); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout circlePanel3Layout = new javax.swing.GroupLayout(circlePanel3);
        circlePanel3.setLayout(circlePanel3Layout);
        circlePanel3Layout.setHorizontalGroup(
            circlePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        circlePanel3Layout.setVerticalGroup(
            circlePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_PhongDuocChon1.add(circlePanel3);
        circlePanel3.setBounds(122, 7, 25, 25);

        panel_Phong24.setBackground(new java.awt.Color(255, 255, 255));

        jPanel136.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel136.setOpaque(false);

        label_SoPhong26.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        label_SoPhong26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SoPhong26.setText("101");
        label_SoPhong26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel136Layout = new javax.swing.GroupLayout(jPanel136);
        jPanel136.setLayout(jPanel136Layout);
        jPanel136Layout.setHorizontalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel136Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_SoPhong26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel136Layout.setVerticalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel136Layout.createSequentialGroup()
                .addComponent(label_SoPhong26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel137.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel137.setOpaque(false);
        jPanel137.setPreferredSize(new java.awt.Dimension(77, 72));

        label_SoLuongKhach26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_SoLuongKhach26.setText("3");

        jLabel329.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel330.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiGiuong26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiGiuong26.setText("Đôi");

        jLabel331.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiPhong26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiPhong26.setText("Family");

        javax.swing.GroupLayout jPanel137Layout = new javax.swing.GroupLayout(jPanel137);
        jPanel137.setLayout(jPanel137Layout);
        jPanel137Layout.setHorizontalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel137Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel137Layout.createSequentialGroup()
                        .addComponent(jLabel329)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_SoLuongKhach26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel137Layout.createSequentialGroup()
                        .addComponent(jLabel330)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiGiuong26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel137Layout.createSequentialGroup()
                        .addComponent(jLabel331)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiPhong26, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
        );
        jPanel137Layout.setVerticalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel137Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_SoLuongKhach26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel329))
                .addGap(0, 0, 0)
                .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiGiuong26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel330))
                .addGap(0, 0, 0)
                .addGroup(jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiPhong26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel331)))
        );

        jPanel138.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel138.setOpaque(false);

        jLabel332.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel332.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel332.setText("1.000.000");

        javax.swing.GroupLayout jPanel138Layout = new javax.swing.GroupLayout(jPanel138);
        jPanel138.setLayout(jPanel138Layout);
        jPanel138Layout.setHorizontalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel138Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel332, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel138Layout.setVerticalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel138Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel332, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel139.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel139.setOpaque(false);

        jLabel333.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel334.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel335.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel336.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel337.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel338.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel139Layout = new javax.swing.GroupLayout(jPanel139);
        jPanel139.setLayout(jPanel139Layout);
        jPanel139Layout.setHorizontalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel139Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel139Layout.createSequentialGroup()
                        .addComponent(jLabel333)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel334))
                    .addGroup(jPanel139Layout.createSequentialGroup()
                        .addComponent(jLabel335)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel336))
                    .addGroup(jPanel139Layout.createSequentialGroup()
                        .addComponent(jLabel337)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel338)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel139Layout.setVerticalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel139Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel334)
                    .addComponent(jLabel333))
                .addGap(4, 4, 4)
                .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel336)
                    .addComponent(jLabel335))
                .addGap(4, 4, 4)
                .addGroup(jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel338)
                    .addComponent(jLabel337))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel140.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel140.setOpaque(false);

        jLabel339.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel339.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel339.setText("-");

        txt_NgayTra2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayTra2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayTra2.setText("27/09");

        txt_NgayNhan2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayNhan2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayNhan2.setText("12/09");

        javax.swing.GroupLayout jPanel140Layout = new javax.swing.GroupLayout(jPanel140);
        jPanel140.setLayout(jPanel140Layout);
        jPanel140Layout.setHorizontalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel140Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_NgayNhan2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel339, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_NgayTra2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel140Layout.setVerticalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_NgayTra2)
                .addComponent(jLabel339, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_NgayNhan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_Phong24Layout = new javax.swing.GroupLayout(panel_Phong24);
        panel_Phong24.setLayout(panel_Phong24Layout);
        panel_Phong24Layout.setHorizontalGroup(
            panel_Phong24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong24Layout.createSequentialGroup()
                .addGroup(panel_Phong24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel140, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Phong24Layout.setVerticalGroup(
            panel_Phong24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong24Layout.createSequentialGroup()
                .addGroup(panel_Phong24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Phong24Layout.createSequentialGroup()
                        .addComponent(jPanel136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel137, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_PhongDuocChon1.add(panel_Phong24);
        panel_Phong24.setBounds(0, 16, 137, 134);

        Panel_PhongDuocChons.add(panel_PhongDuocChon1);

        panel_PhongDuocChon2.setBackground(new java.awt.Color(255, 255, 153));
        panel_PhongDuocChon2.setOpaque(false);
        panel_PhongDuocChon2.setPreferredSize(new java.awt.Dimension(150, 150));
        panel_PhongDuocChon2.setLayout(null);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_close.png"))); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout circlePanel4Layout = new javax.swing.GroupLayout(circlePanel4);
        circlePanel4.setLayout(circlePanel4Layout);
        circlePanel4Layout.setHorizontalGroup(
            circlePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        circlePanel4Layout.setVerticalGroup(
            circlePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_PhongDuocChon2.add(circlePanel4);
        circlePanel4.setBounds(122, 7, 25, 25);

        panel_Phong25.setBackground(new java.awt.Color(255, 255, 255));

        jPanel141.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel141.setOpaque(false);

        label_SoPhong27.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        label_SoPhong27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SoPhong27.setText("101");
        label_SoPhong27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel141Layout = new javax.swing.GroupLayout(jPanel141);
        jPanel141.setLayout(jPanel141Layout);
        jPanel141Layout.setHorizontalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel141Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_SoPhong27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel141Layout.setVerticalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel141Layout.createSequentialGroup()
                .addComponent(label_SoPhong27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel142.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel142.setOpaque(false);
        jPanel142.setPreferredSize(new java.awt.Dimension(77, 72));

        label_SoLuongKhach27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_SoLuongKhach27.setText("3");

        jLabel340.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel341.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiGiuong27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiGiuong27.setText("Đôi");

        jLabel342.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiPhong27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiPhong27.setText("Family");

        javax.swing.GroupLayout jPanel142Layout = new javax.swing.GroupLayout(jPanel142);
        jPanel142.setLayout(jPanel142Layout);
        jPanel142Layout.setHorizontalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel142Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel142Layout.createSequentialGroup()
                        .addComponent(jLabel340)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_SoLuongKhach27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel142Layout.createSequentialGroup()
                        .addComponent(jLabel341)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiGiuong27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel142Layout.createSequentialGroup()
                        .addComponent(jLabel342)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiPhong27, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
        );
        jPanel142Layout.setVerticalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel142Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_SoLuongKhach27, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel340))
                .addGap(0, 0, 0)
                .addGroup(jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiGiuong27, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel341))
                .addGap(0, 0, 0)
                .addGroup(jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiPhong27, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel342)))
        );

        jPanel143.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel143.setOpaque(false);

        jLabel343.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel343.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel343.setText("1.000.000");

        javax.swing.GroupLayout jPanel143Layout = new javax.swing.GroupLayout(jPanel143);
        jPanel143.setLayout(jPanel143Layout);
        jPanel143Layout.setHorizontalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel143Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel343, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel143Layout.setVerticalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel143Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel343, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel144.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel144.setOpaque(false);

        jLabel344.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel345.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel346.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel347.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel348.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel349.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel144Layout = new javax.swing.GroupLayout(jPanel144);
        jPanel144.setLayout(jPanel144Layout);
        jPanel144Layout.setHorizontalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel144Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel144Layout.createSequentialGroup()
                        .addComponent(jLabel344)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel345))
                    .addGroup(jPanel144Layout.createSequentialGroup()
                        .addComponent(jLabel346)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel347))
                    .addGroup(jPanel144Layout.createSequentialGroup()
                        .addComponent(jLabel348)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel349)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel144Layout.setVerticalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel144Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel345)
                    .addComponent(jLabel344))
                .addGap(4, 4, 4)
                .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel347)
                    .addComponent(jLabel346))
                .addGap(4, 4, 4)
                .addGroup(jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel349)
                    .addComponent(jLabel348))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel145.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel145.setOpaque(false);

        jLabel350.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel350.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel350.setText("-");

        txt_NgayTra3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayTra3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayTra3.setText("27/09");

        txt_NgayNhan3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayNhan3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayNhan3.setText("12/09");

        javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
        jPanel145.setLayout(jPanel145Layout);
        jPanel145Layout.setHorizontalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel145Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_NgayNhan3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel350, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_NgayTra3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel145Layout.setVerticalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_NgayTra3)
                .addComponent(jLabel350, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_NgayNhan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_Phong25Layout = new javax.swing.GroupLayout(panel_Phong25);
        panel_Phong25.setLayout(panel_Phong25Layout);
        panel_Phong25Layout.setHorizontalGroup(
            panel_Phong25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong25Layout.createSequentialGroup()
                .addGroup(panel_Phong25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel144, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel145, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Phong25Layout.setVerticalGroup(
            panel_Phong25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong25Layout.createSequentialGroup()
                .addGroup(panel_Phong25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Phong25Layout.createSequentialGroup()
                        .addComponent(jPanel141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel142, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_PhongDuocChon2.add(panel_Phong25);
        panel_Phong25.setBounds(0, 16, 137, 134);

        Panel_PhongDuocChons.add(panel_PhongDuocChon2);

        panel_PhongDuocChon3.setBackground(new java.awt.Color(255, 255, 153));
        panel_PhongDuocChon3.setOpaque(false);
        panel_PhongDuocChon3.setPreferredSize(new java.awt.Dimension(150, 150));
        panel_PhongDuocChon3.setLayout(null);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_close.png"))); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout circlePanel5Layout = new javax.swing.GroupLayout(circlePanel5);
        circlePanel5.setLayout(circlePanel5Layout);
        circlePanel5Layout.setHorizontalGroup(
            circlePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        circlePanel5Layout.setVerticalGroup(
            circlePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_PhongDuocChon3.add(circlePanel5);
        circlePanel5.setBounds(122, 7, 25, 25);

        panel_Phong26.setBackground(new java.awt.Color(255, 255, 255));

        jPanel146.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel146.setOpaque(false);

        label_SoPhong28.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        label_SoPhong28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SoPhong28.setText("101");
        label_SoPhong28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel146Layout = new javax.swing.GroupLayout(jPanel146);
        jPanel146.setLayout(jPanel146Layout);
        jPanel146Layout.setHorizontalGroup(
            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel146Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_SoPhong28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel146Layout.setVerticalGroup(
            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel146Layout.createSequentialGroup()
                .addComponent(label_SoPhong28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel147.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel147.setOpaque(false);
        jPanel147.setPreferredSize(new java.awt.Dimension(77, 72));

        label_SoLuongKhach28.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_SoLuongKhach28.setText("3");

        jLabel351.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel352.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiGiuong28.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiGiuong28.setText("Đôi");

        jLabel353.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiPhong28.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiPhong28.setText("Family");

        javax.swing.GroupLayout jPanel147Layout = new javax.swing.GroupLayout(jPanel147);
        jPanel147.setLayout(jPanel147Layout);
        jPanel147Layout.setHorizontalGroup(
            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel147Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel147Layout.createSequentialGroup()
                        .addComponent(jLabel351)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_SoLuongKhach28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel147Layout.createSequentialGroup()
                        .addComponent(jLabel352)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiGiuong28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel147Layout.createSequentialGroup()
                        .addComponent(jLabel353)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiPhong28, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
        );
        jPanel147Layout.setVerticalGroup(
            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel147Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_SoLuongKhach28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel351))
                .addGap(0, 0, 0)
                .addGroup(jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiGiuong28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel352))
                .addGap(0, 0, 0)
                .addGroup(jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiPhong28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel353)))
        );

        jPanel148.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel148.setOpaque(false);

        jLabel354.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel354.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel354.setText("1.000.000");

        javax.swing.GroupLayout jPanel148Layout = new javax.swing.GroupLayout(jPanel148);
        jPanel148.setLayout(jPanel148Layout);
        jPanel148Layout.setHorizontalGroup(
            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel148Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel354, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel148Layout.setVerticalGroup(
            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel148Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel354, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel149.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel149.setOpaque(false);

        jLabel355.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel356.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel357.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel358.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel359.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel360.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel149Layout = new javax.swing.GroupLayout(jPanel149);
        jPanel149.setLayout(jPanel149Layout);
        jPanel149Layout.setHorizontalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel149Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel149Layout.createSequentialGroup()
                        .addComponent(jLabel355)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel356))
                    .addGroup(jPanel149Layout.createSequentialGroup()
                        .addComponent(jLabel357)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel358))
                    .addGroup(jPanel149Layout.createSequentialGroup()
                        .addComponent(jLabel359)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel360)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel149Layout.setVerticalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel149Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel356)
                    .addComponent(jLabel355))
                .addGap(4, 4, 4)
                .addGroup(jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel358)
                    .addComponent(jLabel357))
                .addGap(4, 4, 4)
                .addGroup(jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel360)
                    .addComponent(jLabel359))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel150.setOpaque(false);

        jLabel361.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel361.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel361.setText("-");

        txt_NgayTra4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayTra4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayTra4.setText("27/09");

        txt_NgayNhan4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayNhan4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayNhan4.setText("12/09");

        javax.swing.GroupLayout jPanel150Layout = new javax.swing.GroupLayout(jPanel150);
        jPanel150.setLayout(jPanel150Layout);
        jPanel150Layout.setHorizontalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel150Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_NgayNhan4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel361, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_NgayTra4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel150Layout.setVerticalGroup(
            jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_NgayTra4)
                .addComponent(jLabel361, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_NgayNhan4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_Phong26Layout = new javax.swing.GroupLayout(panel_Phong26);
        panel_Phong26.setLayout(panel_Phong26Layout);
        panel_Phong26Layout.setHorizontalGroup(
            panel_Phong26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong26Layout.createSequentialGroup()
                .addGroup(panel_Phong26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel147, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel146, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel148, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel150, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Phong26Layout.setVerticalGroup(
            panel_Phong26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong26Layout.createSequentialGroup()
                .addGroup(panel_Phong26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Phong26Layout.createSequentialGroup()
                        .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel147, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel149, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel148, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel150, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_PhongDuocChon3.add(panel_Phong26);
        panel_Phong26.setBounds(0, 16, 137, 134);

        Panel_PhongDuocChons.add(panel_PhongDuocChon3);

        panel_PhongDuocChon4.setBackground(new java.awt.Color(255, 255, 153));
        panel_PhongDuocChon4.setOpaque(false);
        panel_PhongDuocChon4.setPreferredSize(new java.awt.Dimension(150, 150));
        panel_PhongDuocChon4.setLayout(null);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_close.png"))); // NOI18N
        jLabel42.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout circlePanel6Layout = new javax.swing.GroupLayout(circlePanel6);
        circlePanel6.setLayout(circlePanel6Layout);
        circlePanel6Layout.setHorizontalGroup(
            circlePanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        circlePanel6Layout.setVerticalGroup(
            circlePanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_PhongDuocChon4.add(circlePanel6);
        circlePanel6.setBounds(122, 7, 25, 25);

        panel_Phong27.setBackground(new java.awt.Color(255, 255, 255));

        jPanel151.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel151.setOpaque(false);

        label_SoPhong29.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        label_SoPhong29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SoPhong29.setText("101");
        label_SoPhong29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel151Layout = new javax.swing.GroupLayout(jPanel151);
        jPanel151.setLayout(jPanel151Layout);
        jPanel151Layout.setHorizontalGroup(
            jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel151Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_SoPhong29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel151Layout.setVerticalGroup(
            jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel151Layout.createSequentialGroup()
                .addComponent(label_SoPhong29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel152.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel152.setOpaque(false);
        jPanel152.setPreferredSize(new java.awt.Dimension(77, 72));

        label_SoLuongKhach29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_SoLuongKhach29.setText("3");

        jLabel362.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel363.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiGiuong29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiGiuong29.setText("Đôi");

        jLabel364.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiPhong29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiPhong29.setText("Family");

        javax.swing.GroupLayout jPanel152Layout = new javax.swing.GroupLayout(jPanel152);
        jPanel152.setLayout(jPanel152Layout);
        jPanel152Layout.setHorizontalGroup(
            jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel152Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel152Layout.createSequentialGroup()
                        .addComponent(jLabel362)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_SoLuongKhach29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel152Layout.createSequentialGroup()
                        .addComponent(jLabel363)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiGiuong29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel152Layout.createSequentialGroup()
                        .addComponent(jLabel364)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiPhong29, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
        );
        jPanel152Layout.setVerticalGroup(
            jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel152Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_SoLuongKhach29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel362))
                .addGap(0, 0, 0)
                .addGroup(jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiGiuong29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel363))
                .addGap(0, 0, 0)
                .addGroup(jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiPhong29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel364)))
        );

        jPanel153.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel153.setOpaque(false);

        jLabel365.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel365.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel365.setText("1.000.000");

        javax.swing.GroupLayout jPanel153Layout = new javax.swing.GroupLayout(jPanel153);
        jPanel153.setLayout(jPanel153Layout);
        jPanel153Layout.setHorizontalGroup(
            jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel153Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel365, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel153Layout.setVerticalGroup(
            jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel153Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel365, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel154.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel154.setOpaque(false);

        jLabel366.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel367.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel368.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel369.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel370.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel371.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel154Layout = new javax.swing.GroupLayout(jPanel154);
        jPanel154.setLayout(jPanel154Layout);
        jPanel154Layout.setHorizontalGroup(
            jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel154Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel154Layout.createSequentialGroup()
                        .addComponent(jLabel366)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel367))
                    .addGroup(jPanel154Layout.createSequentialGroup()
                        .addComponent(jLabel368)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel369))
                    .addGroup(jPanel154Layout.createSequentialGroup()
                        .addComponent(jLabel370)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel371)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel154Layout.setVerticalGroup(
            jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel154Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel367)
                    .addComponent(jLabel366))
                .addGap(4, 4, 4)
                .addGroup(jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel369)
                    .addComponent(jLabel368))
                .addGap(4, 4, 4)
                .addGroup(jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel371)
                    .addComponent(jLabel370))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel155.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel155.setOpaque(false);

        jLabel372.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel372.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel372.setText("-");

        txt_NgayTra5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayTra5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayTra5.setText("27/09");

        txt_NgayNhan5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayNhan5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayNhan5.setText("12/09");

        javax.swing.GroupLayout jPanel155Layout = new javax.swing.GroupLayout(jPanel155);
        jPanel155.setLayout(jPanel155Layout);
        jPanel155Layout.setHorizontalGroup(
            jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel155Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_NgayNhan5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel372, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_NgayTra5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel155Layout.setVerticalGroup(
            jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_NgayTra5)
                .addComponent(jLabel372, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_NgayNhan5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_Phong27Layout = new javax.swing.GroupLayout(panel_Phong27);
        panel_Phong27.setLayout(panel_Phong27Layout);
        panel_Phong27Layout.setHorizontalGroup(
            panel_Phong27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong27Layout.createSequentialGroup()
                .addGroup(panel_Phong27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel152, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel155, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Phong27Layout.setVerticalGroup(
            panel_Phong27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong27Layout.createSequentialGroup()
                .addGroup(panel_Phong27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Phong27Layout.createSequentialGroup()
                        .addComponent(jPanel151, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel152, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel154, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel153, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_PhongDuocChon4.add(panel_Phong27);
        panel_Phong27.setBounds(0, 16, 137, 134);

        Panel_PhongDuocChons.add(panel_PhongDuocChon4);

        panel_PhongDuocChon5.setBackground(new java.awt.Color(255, 255, 153));
        panel_PhongDuocChon5.setOpaque(false);
        panel_PhongDuocChon5.setPreferredSize(new java.awt.Dimension(150, 150));
        panel_PhongDuocChon5.setLayout(null);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_close.png"))); // NOI18N
        jLabel47.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout circlePanel7Layout = new javax.swing.GroupLayout(circlePanel7);
        circlePanel7.setLayout(circlePanel7Layout);
        circlePanel7Layout.setHorizontalGroup(
            circlePanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        circlePanel7Layout.setVerticalGroup(
            circlePanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_PhongDuocChon5.add(circlePanel7);
        circlePanel7.setBounds(122, 7, 25, 25);

        panel_Phong28.setBackground(new java.awt.Color(255, 255, 255));

        jPanel156.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel156.setOpaque(false);

        label_SoPhong30.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        label_SoPhong30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SoPhong30.setText("101");
        label_SoPhong30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel156Layout = new javax.swing.GroupLayout(jPanel156);
        jPanel156.setLayout(jPanel156Layout);
        jPanel156Layout.setHorizontalGroup(
            jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel156Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_SoPhong30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel156Layout.setVerticalGroup(
            jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel156Layout.createSequentialGroup()
                .addComponent(label_SoPhong30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel157.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel157.setOpaque(false);
        jPanel157.setPreferredSize(new java.awt.Dimension(77, 72));

        label_SoLuongKhach30.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_SoLuongKhach30.setText("3");

        jLabel373.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel374.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiGiuong30.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiGiuong30.setText("Đôi");

        jLabel375.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        label_LoaiPhong30.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        label_LoaiPhong30.setText("Family");

        javax.swing.GroupLayout jPanel157Layout = new javax.swing.GroupLayout(jPanel157);
        jPanel157.setLayout(jPanel157Layout);
        jPanel157Layout.setHorizontalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel157Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel157Layout.createSequentialGroup()
                        .addComponent(jLabel373)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_SoLuongKhach30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel157Layout.createSequentialGroup()
                        .addComponent(jLabel374)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiGiuong30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel157Layout.createSequentialGroup()
                        .addComponent(jLabel375)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_LoaiPhong30, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))))
        );
        jPanel157Layout.setVerticalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel157Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_SoLuongKhach30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel373))
                .addGap(0, 0, 0)
                .addGroup(jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiGiuong30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel374))
                .addGap(0, 0, 0)
                .addGroup(jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_LoaiPhong30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel375)))
        );

        jPanel158.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel158.setOpaque(false);

        jLabel376.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel376.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel376.setText("1.000.000");

        javax.swing.GroupLayout jPanel158Layout = new javax.swing.GroupLayout(jPanel158);
        jPanel158.setLayout(jPanel158Layout);
        jPanel158Layout.setHorizontalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel158Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel376, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel158Layout.setVerticalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel158Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel376, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel159.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel159.setOpaque(false);

        jLabel377.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel378.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel379.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel380.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel381.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        jLabel382.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_SoLuongKhach_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel159Layout = new javax.swing.GroupLayout(jPanel159);
        jPanel159.setLayout(jPanel159Layout);
        jPanel159Layout.setHorizontalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel159Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel159Layout.createSequentialGroup()
                        .addComponent(jLabel377)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel378))
                    .addGroup(jPanel159Layout.createSequentialGroup()
                        .addComponent(jLabel379)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel380))
                    .addGroup(jPanel159Layout.createSequentialGroup()
                        .addComponent(jLabel381)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel382)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel159Layout.setVerticalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel159Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel378)
                    .addComponent(jLabel377))
                .addGap(4, 4, 4)
                .addGroup(jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel380)
                    .addComponent(jLabel379))
                .addGap(4, 4, 4)
                .addGroup(jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel382)
                    .addComponent(jLabel381))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel160.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel160.setOpaque(false);

        jLabel383.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel383.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel383.setText("-");

        txt_NgayTra6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayTra6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayTra6.setText("27/09");

        txt_NgayNhan6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_NgayNhan6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_NgayNhan6.setText("12/09");

        javax.swing.GroupLayout jPanel160Layout = new javax.swing.GroupLayout(jPanel160);
        jPanel160.setLayout(jPanel160Layout);
        jPanel160Layout.setHorizontalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel160Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt_NgayNhan6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel383, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_NgayTra6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel160Layout.setVerticalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txt_NgayTra6)
                .addComponent(jLabel383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_NgayNhan6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_Phong28Layout = new javax.swing.GroupLayout(panel_Phong28);
        panel_Phong28.setLayout(panel_Phong28Layout);
        panel_Phong28Layout.setHorizontalGroup(
            panel_Phong28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong28Layout.createSequentialGroup()
                .addGroup(panel_Phong28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel157, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel159, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel158, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel160, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_Phong28Layout.setVerticalGroup(
            panel_Phong28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Phong28Layout.createSequentialGroup()
                .addGroup(panel_Phong28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_Phong28Layout.createSequentialGroup()
                        .addComponent(jPanel156, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel157, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel159, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel158, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_PhongDuocChon5.add(panel_Phong28);
        panel_Phong28.setBounds(0, 16, 137, 134);

        Panel_PhongDuocChons.add(panel_PhongDuocChon5);

        jScrollPane3.setViewportView(Panel_PhongDuocChons);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(325, 570, 910, 170);

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
    private javax.swing.JPanel Panel_PhongDuocChons;
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
    private GUI.CirclePanel circlePanel2;
    private GUI.CirclePanel circlePanel3;
    private GUI.CirclePanel circlePanel4;
    private GUI.CirclePanel circlePanel5;
    private GUI.CirclePanel circlePanel6;
    private GUI.CirclePanel circlePanel7;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel318;
    private javax.swing.JLabel jLabel319;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel320;
    private javax.swing.JLabel jLabel321;
    private javax.swing.JLabel jLabel322;
    private javax.swing.JLabel jLabel323;
    private javax.swing.JLabel jLabel324;
    private javax.swing.JLabel jLabel325;
    private javax.swing.JLabel jLabel326;
    private javax.swing.JLabel jLabel327;
    private javax.swing.JLabel jLabel328;
    private javax.swing.JLabel jLabel329;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel330;
    private javax.swing.JLabel jLabel331;
    private javax.swing.JLabel jLabel332;
    private javax.swing.JLabel jLabel333;
    private javax.swing.JLabel jLabel334;
    private javax.swing.JLabel jLabel335;
    private javax.swing.JLabel jLabel336;
    private javax.swing.JLabel jLabel337;
    private javax.swing.JLabel jLabel338;
    private javax.swing.JLabel jLabel339;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel340;
    private javax.swing.JLabel jLabel341;
    private javax.swing.JLabel jLabel342;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel344;
    private javax.swing.JLabel jLabel345;
    private javax.swing.JLabel jLabel346;
    private javax.swing.JLabel jLabel347;
    private javax.swing.JLabel jLabel348;
    private javax.swing.JLabel jLabel349;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel350;
    private javax.swing.JLabel jLabel351;
    private javax.swing.JLabel jLabel352;
    private javax.swing.JLabel jLabel353;
    private javax.swing.JLabel jLabel354;
    private javax.swing.JLabel jLabel355;
    private javax.swing.JLabel jLabel356;
    private javax.swing.JLabel jLabel357;
    private javax.swing.JLabel jLabel358;
    private javax.swing.JLabel jLabel359;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel360;
    private javax.swing.JLabel jLabel361;
    private javax.swing.JLabel jLabel362;
    private javax.swing.JLabel jLabel363;
    private javax.swing.JLabel jLabel364;
    private javax.swing.JLabel jLabel365;
    private javax.swing.JLabel jLabel366;
    private javax.swing.JLabel jLabel367;
    private javax.swing.JLabel jLabel368;
    private javax.swing.JLabel jLabel369;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel370;
    private javax.swing.JLabel jLabel371;
    private javax.swing.JLabel jLabel372;
    private javax.swing.JLabel jLabel373;
    private javax.swing.JLabel jLabel374;
    private javax.swing.JLabel jLabel375;
    private javax.swing.JLabel jLabel376;
    private javax.swing.JLabel jLabel377;
    private javax.swing.JLabel jLabel378;
    private javax.swing.JLabel jLabel379;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel380;
    private javax.swing.JLabel jLabel381;
    private javax.swing.JLabel jLabel382;
    private javax.swing.JLabel jLabel383;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel131;
    private javax.swing.JPanel jPanel132;
    private javax.swing.JPanel jPanel133;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
    private javax.swing.JPanel jPanel150;
    private javax.swing.JPanel jPanel151;
    private javax.swing.JPanel jPanel152;
    private javax.swing.JPanel jPanel153;
    private javax.swing.JPanel jPanel154;
    private javax.swing.JPanel jPanel155;
    private javax.swing.JPanel jPanel156;
    private javax.swing.JPanel jPanel157;
    private javax.swing.JPanel jPanel158;
    private javax.swing.JPanel jPanel159;
    private javax.swing.JPanel jPanel160;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_Avatar;
    private javax.swing.JLabel label_LoaiGiuong25;
    private javax.swing.JLabel label_LoaiGiuong26;
    private javax.swing.JLabel label_LoaiGiuong27;
    private javax.swing.JLabel label_LoaiGiuong28;
    private javax.swing.JLabel label_LoaiGiuong29;
    private javax.swing.JLabel label_LoaiGiuong30;
    private javax.swing.JLabel label_LoaiPhong25;
    private javax.swing.JLabel label_LoaiPhong26;
    private javax.swing.JLabel label_LoaiPhong27;
    private javax.swing.JLabel label_LoaiPhong28;
    private javax.swing.JLabel label_LoaiPhong29;
    private javax.swing.JLabel label_LoaiPhong30;
    private javax.swing.JLabel label_SoLuongKhach25;
    private javax.swing.JLabel label_SoLuongKhach26;
    private javax.swing.JLabel label_SoLuongKhach27;
    private javax.swing.JLabel label_SoLuongKhach28;
    private javax.swing.JLabel label_SoLuongKhach29;
    private javax.swing.JLabel label_SoLuongKhach30;
    private javax.swing.JLabel label_SoPhong25;
    private javax.swing.JLabel label_SoPhong26;
    private javax.swing.JLabel label_SoPhong27;
    private javax.swing.JLabel label_SoPhong28;
    private javax.swing.JLabel label_SoPhong29;
    private javax.swing.JLabel label_SoPhong30;
    private javax.swing.JPanel line;
    private javax.swing.JPanel panel_Phong23;
    private javax.swing.JPanel panel_Phong24;
    private javax.swing.JPanel panel_Phong25;
    private javax.swing.JPanel panel_Phong26;
    private javax.swing.JPanel panel_Phong27;
    private javax.swing.JPanel panel_Phong28;
    private javax.swing.JPanel panel_PhongDuocChon;
    private javax.swing.JPanel panel_PhongDuocChon1;
    private javax.swing.JPanel panel_PhongDuocChon2;
    private javax.swing.JPanel panel_PhongDuocChon3;
    private javax.swing.JPanel panel_PhongDuocChon4;
    private javax.swing.JPanel panel_PhongDuocChon5;
    private javax.swing.JPanel panel_Tang;
    private javax.swing.JPanel panel_Tang2;
    private javax.swing.JPanel panel_Tang3;
    private javax.swing.JPanel panel_Tang4;
    private javax.swing.JLabel txt_NgayNhan1;
    private javax.swing.JLabel txt_NgayNhan2;
    private javax.swing.JLabel txt_NgayNhan3;
    private javax.swing.JLabel txt_NgayNhan4;
    private javax.swing.JLabel txt_NgayNhan5;
    private javax.swing.JLabel txt_NgayNhan6;
    private com.toedter.calendar.JDateChooser txt_NgayNhanPhong;
    private javax.swing.JLabel txt_NgayTra1;
    private javax.swing.JLabel txt_NgayTra2;
    private javax.swing.JLabel txt_NgayTra3;
    private javax.swing.JLabel txt_NgayTra4;
    private javax.swing.JLabel txt_NgayTra5;
    private javax.swing.JLabel txt_NgayTra6;
    private com.toedter.calendar.JDateChooser txt_NgayTraPhong;
    public static javax.swing.JLabel txt_TenNguoiDaiDien;
    // End of variables declaration//GEN-END:variables
}
