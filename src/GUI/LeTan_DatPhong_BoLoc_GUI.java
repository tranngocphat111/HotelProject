/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_DatPhong_GUI.LoadPhong;
import static GUI.LeTan_DatPhong_GUI.getAllPhongSauKhiChon;
import static GUI.LeTan_DatPhong_GUI.list_PhongDuocChonTheo1thoiGian;
import static GUI.LeTan_DatPhong_GUI.list_PhongSauKhiLocHoacChon;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import model.DAO.KhachHangDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.KhachHang;
import model.DTO.LoaiPhong;
import model.DTO.Phong;

/**
 *
 * @author Admin
 */
public class LeTan_DatPhong_BoLoc_GUI extends javax.swing.JDialog {

    DefaultTableCellRenderer centerRenderer;
    DecimalFormat df = new DecimalFormat("#,##0");
    LoaiPhongDAO loaiPhong_Dao = new LoaiPhongDAO(database);
    List<LoaiPhong> list_LoaiPhong_db = new ArrayList<>();
    PhongDAO phong_dao = new PhongDAO(database);

    /**
     * Creates new form LeTan_
     */
    public LeTan_DatPhong_BoLoc_GUI(JFrame parent, boolean modal) {
        super(parent, modal);

        setUndecorated(true);
        list_LoaiPhong_db = loaiPhong_Dao.getAllLoaiPhong();

        initComponents();

//        Load dữ liệu lên các Combobox
        Set<String> list_LoaiPhong = new HashSet<>();
        for (LoaiPhong lp : list_LoaiPhong_db) {
            if (!list_LoaiPhong.contains(lp.getTenLoaiPhong())) {
                list_LoaiPhong.add(lp.getTenLoaiPhong());
            }
        }
        cb_LoaiPhong.addItem("Tất cả");
        for (String loaiPhong : list_LoaiPhong) {
            cb_LoaiPhong.addItem(loaiPhong);
        }

        Set<String> list_LoaiGiuong = new HashSet<>();
        for (LoaiPhong lp : list_LoaiPhong_db) {
            if (!list_LoaiGiuong.contains(lp.getLoaiGiuong())) {
                list_LoaiGiuong.add(lp.getLoaiGiuong());
            }
        }
        cb_LoaiGiuong.addItem("Tất cả");
        for (String loaiGiuong : list_LoaiGiuong) {
            cb_LoaiGiuong.addItem(loaiGiuong);
        }

        Set<Integer> list_SoKhachToiDa = new HashSet<>();
        for (LoaiPhong lp : list_LoaiPhong_db) {
            if (!list_SoKhachToiDa.contains(lp.getSoKhachToiDa())) {
                list_SoKhachToiDa.add(lp.getSoKhachToiDa());
            }
        }
        cb_SoKhachToiDa.addItem("Tất cả");
        for (Integer SoKhachToiDa : list_SoKhachToiDa) {
            cb_SoKhachToiDa.addItem(SoKhachToiDa + "");
        }

        List<Phong> list_Phong = phong_dao.getAllPhong();
        Set<String> list_Mota = new HashSet<>();
        for (Phong p : list_Phong) {
            p.getMoTa();
            if (!list_Mota.contains(p.getMoTa())) {
                list_Mota.add(p.getMoTa());
            }
        }
        cb_moTa.addItem("Tất cả");
        for (String moTa : list_Mota) {
            cb_moTa.addItem(moTa);
        }

        cb_LoaiPhong.setSelectedItem(LeTan_DatPhong_GUI.boloc_loaiPhong);
        cb_LoaiGiuong.setSelectedItem(LeTan_DatPhong_GUI.boloc_loaiGiuong);
        cb_SoKhachToiDa.setSelectedItem(LeTan_DatPhong_GUI.boloc_SokhachtoiDa);
        cb_moTa.setSelectedItem(LeTan_DatPhong_GUI.boloc_Mota);
        cb_DonGia.setSelectedIndex(LeTan_DatPhong_GUI.boloc_DonGia);

        setLocationRelativeTo(null);
    }

//    public void DocDuLieuLenTable(List<Phong> list_PhongTrong) {
//        for (Phong phong : list_PhongTrong) {
//            LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
//            String list_tienNghi = getListTienNghi(loaiPhong.getTienNghis());
//            model.addRow(new Object[]{
//                phong.getMaPhong(),
//                phong.getTang(),
//                loaiPhong.getTenLoaiPhong(),
//                loaiPhong.getLoaiGiuong(),
//                loaiPhong.getDienTich() + " m2",
//                list_tienNghi,
//                phong.getMoTa(),
//                df.format(loaiPhong.getDonGia()) + " VND",
//                loaiPhong.getSoKhachToiDa()});
//        }
//
//        for (int i = 0; i < Table_Phong.getColumnCount(); i++) {
//            Table_Phong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//        }
//
//    }
//
//    public String getListTienNghi(List<TienNghi> list_tienNghi) {
//        String list =;
//
//        for (TienNghi tn : list_tienNghi) {
//            list = list + tn.getTenTienNghi() + ", ";
//        }
//        list = list.substring(0, list.length() - 2);
//        return list;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_XacNhan = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Huy = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cb_DonGia = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cb_LoaiGiuong = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cb_LoaiPhong = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cb_SoKhachToiDa = new javax.swing.JComboBox<>();
        cb_moTa = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(580, 390));
        jPanel1.setLayout(null);

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
        jLabel19.setText("Xác nhận");
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

        jPanel1.add(btn_XacNhan);
        btn_XacNhan.setBounds(200, 330, 150, 45);

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
        jLabel20.setText("Hủy");
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

        jPanel1.add(btn_Huy);
        btn_Huy.setBounds(390, 330, 140, 46);

        jLabel2.setBackground(new java.awt.Color(255, 209, 84));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Người đại diện");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 20, 250, 25);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Loại giường");

        cb_DonGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_DonGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "500.000 - 1.000.000", "1.000.000 - 2.000.000", "2.000.000 - 5.000.000" }));
        cb_DonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_DonGiaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Đơn giá");

        cb_LoaiGiuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_LoaiGiuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaiGiuongActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Loại phòng");

        cb_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_LoaiPhong.setPreferredSize(new java.awt.Dimension(72, 30));
        cb_LoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaiPhongActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Số khách tối đa");

        cb_SoKhachToiDa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_SoKhachToiDa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_SoKhachToiDaActionPerformed(evt);
            }
        });

        cb_moTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cb_moTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_moTaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mô tả");

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_moTa, javax.swing.GroupLayout.Alignment.LEADING, 0, 183, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_LoaiPhong, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_SoKhachToiDa, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(cb_LoaiGiuong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_DonGia, 0, 186, Short.MAX_VALUE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(cb_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(6, 6, 6)
                        .addComponent(cb_SoKhachToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(cb_LoaiGiuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addComponent(cb_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(6, 6, 6)
                .addComponent(cb_moTa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(40, 60, 490, 250);

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup_DatPhong_chonPhong.png"))); // NOI18N
        BackGround.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        BackGround.setPreferredSize(new java.awt.Dimension(1500, 450));
        jPanel1.add(BackGround);
        BackGround.setBounds(0, 0, 580, 390);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void btn_HuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HuyMouseClicked

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

    private void btn_XacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMousePressed
        // TODO add your handling code here:
        LeTan_DatPhong_GUI.boloc_loaiPhong = cb_LoaiPhong.getSelectedItem().toString();
        LeTan_DatPhong_GUI.boloc_loaiGiuong = cb_LoaiGiuong.getSelectedItem().toString();
        LeTan_DatPhong_GUI.boloc_SokhachtoiDa = cb_SoKhachToiDa.getSelectedItem().toString();
        LeTan_DatPhong_GUI.boloc_Mota = cb_moTa.getSelectedItem().toString();
        LeTan_DatPhong_GUI.boloc_DonGia = cb_DonGia.getSelectedIndex();

        list_PhongSauKhiLocHoacChon = getAllPhongSauKhiChon();
        LoadPhong(list_PhongSauKhiLocHoacChon);
        setVisible(false);


    }//GEN-LAST:event_btn_XacNhanMousePressed

    private void btn_HuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMousePressed
        // TODO add your handling code here:
        setVisible(false);

    }//GEN-LAST:event_btn_HuyMousePressed

    private void cb_LoaiGiuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaiGiuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_LoaiGiuongActionPerformed

    private void cb_DonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_DonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_DonGiaActionPerformed

    private void cb_LoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_LoaiPhongActionPerformed

    private void cb_SoKhachToiDaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_SoKhachToiDaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_SoKhachToiDaActionPerformed

    private void cb_moTaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_moTaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_moTaActionPerformed

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
            java.util.logging.Logger.getLogger(LeTan_DatPhong_BoLoc_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_BoLoc_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_BoLoc_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_BoLoc_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
//                LeTan_DatPhong_NguoiDaiDien_GUI dialog = new LeTan_DatPhong_NguoiDaiDien_GUI(new javax.swing.JFrame(), true);
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
//                LeTan_DatPhong_NguoiDaiDien_GUI dialog = new LeTan_DatPhong_NguoiDaiDien_GUI(new javax.swing.JFrame(), true);
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
//                LeTan_DatPhong_NguoiDaiDien_GUI dialog = new LeTan_DatPhong_NguoiDaiDien_GUI(new javax.swing.JFrame(), true);
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
//                LeTan_DatPhong_NguoiDaiDien_GUI dialog = new LeTan_DatPhong_NguoiDaiDien_GUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel BackGround;
    private javax.swing.JPanel ThongTinKhachHang;
    private keeptoo.KGradientPanel btn_Huy;
    private keeptoo.KGradientPanel btn_XacNhan;
    private javax.swing.JComboBox<String> cb_DonGia;
    private javax.swing.JComboBox<String> cb_LoaiGiuong;
    private javax.swing.JComboBox<String> cb_LoaiPhong;
    private javax.swing.JComboBox<String> cb_SoKhachToiDa;
    private javax.swing.JComboBox<String> cb_moTa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
