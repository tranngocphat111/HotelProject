/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.LeTan_DonDatPhong_GUI.GetAllDonDatPhong;
import static GUI.LeTan_DonDatPhong_GUI.donDatPhong_dao;
import static GUI.LeTan_DonDatPhong_GUI.list_DonDatPhong;
import static GUI.LeTan_DonDatPhong_GUI.list_DonDatPhongTheoTieuChi;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import keeptoo.KGradientPanel;
import model.DAO.DichVuDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.TienNghiDAO;
import model.DTO.DichVu;
import model.DTO.DonDatPhong;
import model.DTO.TienNghi;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_ThemDichVu extends javax.swing.JDialog {

    private MongoDBConnection database = new MongoDBConnection();
    private List<DichVu> list_DichVu = new ArrayList<>();
    private DichVuDAO dichVu_dao = new DichVuDAO(database.getDatabase());
    DecimalFormat df = new DecimalFormat("#,##0");
    private List<DichVu> list_DichVuDuocChon = new ArrayList<>();
    private ArrayList<KGradientPanel> list_DichVuCoSan = new ArrayList<>();
    private ArrayList<KGradientPanel> list_btn = new ArrayList<>();
    private DonDatPhong donDatPhong = new DonDatPhong();
    private DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database.getDatabase());

    /**
     * Creates new form LeTan_DonDatPhong_ThemDichVui
     */
    public LeTan_DonDatPhong_ThemDichVu(DonDatPhong ddp, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        donDatPhong = ddp;

//        Đọc dữ liệu lên frame
        list_DichVu = dichVu_dao.getAllDichVu();
        DocDuLieuLenFrame();
//        Load những dịch vụ mà đơn đặt phòng đã có
        for (DichVu dv : donDatPhong.getDichVuSuDung()) {
            for (KGradientPanel btn : list_btn) {
                if (dv.getMaDV() == Integer.parseInt(btn.getName())) {
                    list_DichVuCoSan.add(btn);
                    list_DichVuDuocChon.add(dichVu_dao.getDichVuByMa(Integer.parseInt(btn.getName())));
                    btn.setkEndColor(new java.awt.Color(242, 92, 92));
                    btn.setkStartColor(new java.awt.Color(228, 30, 30));
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
                    for(KGradientPanel btn_DaChon : list_DichVuCoSan){
                        if(btn.getName().equals(btn_DaChon.getName())){
                            JOptionPane.showMessageDialog(null, "Dịch vụ đang được sử dụng, không được chọn");
                            return;
                        }    
                    }
                    if (click) {
                        btn.setkStartColor(new java.awt.Color(255, 255, 255));
                        btn.setkEndColor(new java.awt.Color(255, 255, 255));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                        click = false;
                        list_DichVuDuocChon.remove(dichVu_dao.getDichVuByMa(Integer.parseInt(btn.getName())));
                    } else {
                        btn.setkStartColor(new java.awt.Color(255, 225, 27));
                        btn.setkEndColor(new java.awt.Color(255, 222, 89));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                        click = true;
                        list_DichVuDuocChon.add(dichVu_dao.getDichVuByMa(Integer.parseInt(btn.getName())));
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

    public void DocDuLieuLenFrame() {
        int i = 1;
        Panel_DichVu.setLayout(new java.awt.GridLayout(getRowDichVu(list_DichVu.size()), 2, 18, 17));
        for (DichVu dichVu : list_DichVu) {
            KGradientPanel btn_DichVu = new KGradientPanel();
            btn_DichVu.setkEndColor(new java.awt.Color(255, 255, 255));
            btn_DichVu.setkGradientFocus(250);
            btn_DichVu.setkStartColor(new java.awt.Color(255, 255, 255));

            JLabel label_TenDichVu = new JLabel();
            label_TenDichVu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
            label_TenDichVu.setText(dichVu.getTenDV());
            label_TenDichVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            label_TenDichVu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            JLabel icon_DichVu = new JLabel();
            ImageIcon icon = new ImageScale().load1(new ImageIcon(dichVu.getHinhAnh()), 77, 60);
            icon_DichVu.setIcon(icon);

            JLabel label_MoTa = new JLabel();
            label_MoTa.setText(dichVu.getMoTa());

            JLabel label_DonGia = new JLabel();
            label_DonGia.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
            label_DonGia.setText(df.format(dichVu.getDonGia()) + " VND");

            javax.swing.GroupLayout btn_DichVuLayout = new javax.swing.GroupLayout(btn_DichVu);
            btn_DichVu.setLayout(btn_DichVuLayout);
            btn_DichVuLayout.setHorizontalGroup(
                    btn_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(btn_DichVuLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(icon_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(btn_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_TenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(14, Short.MAX_VALUE))
            );
            btn_DichVuLayout.setVerticalGroup(
                    btn_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DichVuLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label_TenDichVu)
                                    .addGap(0, 0, 0)
                                    .addComponent(label_MoTa)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label_DonGia)
                                    .addGap(0, 6, Short.MAX_VALUE))
                            .addComponent(icon_DichVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            btn_DichVu.setName(i++ + "");
            list_btn.add(btn_DichVu);
            Panel_DichVu.add(btn_DichVu);
        }

    }

    public int getRowDichVu(int n) {

        return n / 2 + n % 2;

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
        Panel_DichVu = new javax.swing.JPanel();
        btn_XacNhan = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Huy = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(783, 550));

        kGradientPanel1.setkEndColor(new java.awt.Color(0, 0, 0));
        kGradientPanel1.setkGradientFocus(250);
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 0, 0));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(783, 513));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(783, 513));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(725, 384));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(783, 384));

        Panel_DichVu.setBackground(new java.awt.Color(0, 0, 0));
        Panel_DichVu.setLayout(new java.awt.GridLayout(2, 2, 35, 35));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Panel_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(796, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Panel_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(418, 418, 418)
                .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_XacNhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btn_XacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMousePressed
        // TODO add your handling code here:
        donDatPhong.setDichVuSuDung(list_DichVuDuocChon);
        donDatPhong_dao.updateDonDatPhong(donDatPhong);
        
//        Đọc dữ liệu lên table_DonDatPhong
        LeTan_DonDatPhong_GUI.list_DonDatPhong = GetAllDonDatPhong(donDatPhong_dao.getAllDonDatPhong());
        LeTan_DonDatPhong_GUI.checkBox_DangO.setSelected(true);
        LeTan_DonDatPhong_GUI.checkBox_DangCho.setSelected(true);
        LeTan_DonDatPhong_GUI.DocDuLieuLenTable(list_DonDatPhong);
        setVisible(false);
    }//GEN-LAST:event_btn_XacNhanMousePressed

    private void btn_HuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMousePressed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_HuyMousePressed

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
    private javax.swing.JPanel Panel_DichVu;
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
