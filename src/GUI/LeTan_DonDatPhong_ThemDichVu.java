/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import keeptoo.KGradientPanel;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_ThemDichVu extends javax.swing.JFrame {
    private ArrayList<KGradientPanel> list_DichVu = new ArrayList<KGradientPanel>();
    /**
     * Creates new form LeTan_DatPhong_ChonPhong1
     */
    public LeTan_DonDatPhong_ThemDichVu() {
        setUndecorated(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        
        list_DichVu.add(btn_DichVu);
        list_DichVu.add(btn_DichVu1);
        list_DichVu.add(btn_DichVu2);
        
        
        list_DichVu.forEach((btn) -> {

            btn.addMouseListener(new MouseListener(){
                int click = 0;

                @Override
                public void mouseClicked(MouseEvent e) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if(click % 2 == 0){
                        btn.setkStartColor(new java.awt.Color(255, 225, 27));
                        btn.setkEndColor(new java.awt.Color(255, 222, 89));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                    }else{
                        btn.setkStartColor(new java.awt.Color(255, 225, 255));
                        btn.setkEndColor(new java.awt.Color(255, 255, 255));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                    }
                    click++;
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
                    if(click % 2 == 0){
                        btn.setkStartColor(new java.awt.Color(255, 255, 255));
                        btn.setkEndColor(new java.awt.Color(255, 255, 255));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                    }
                    
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

        kGradientPanel1 = new keeptoo.KGradientPanel();
        btn_XacNhan = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Huy = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_DichVu = new keeptoo.KGradientPanel();
        label_TenDichVu = new javax.swing.JLabel();
        icon_DichVu = new javax.swing.JLabel();
        label_MoTa = new javax.swing.JLabel();
        label_DonGia = new javax.swing.JLabel();
        btn_DichVu1 = new keeptoo.KGradientPanel();
        label_TenDichVu1 = new javax.swing.JLabel();
        icon_DichVu1 = new javax.swing.JLabel();
        label_MoTa1 = new javax.swing.JLabel();
        label_DonGia1 = new javax.swing.JLabel();
        btn_DichVu2 = new keeptoo.KGradientPanel();
        label_TenDichVu2 = new javax.swing.JLabel();
        icon_DichVu2 = new javax.swing.JLabel();
        label_MoTa2 = new javax.swing.JLabel();
        label_DonGia2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(777, 456));

        kGradientPanel1.setkEndColor(new java.awt.Color(115, 115, 115));
        kGradientPanel1.setkGradientFocus(250);
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 0, 0));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(777, 450));

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_HuyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_HuyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_HuyMouseExited(evt);
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

        btn_DichVu.setkEndColor(new java.awt.Color(255, 255, 255));
        btn_DichVu.setkGradientFocus(250);
        btn_DichVu.setkStartColor(new java.awt.Color(255, 255, 255));
        btn_DichVu.setName("btn_DichVu"); // NOI18N
        btn_DichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DichVuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DichVuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DichVuMouseExited(evt);
            }
        });

        label_TenDichVu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        label_TenDichVu.setText("BUFFET");
        label_TenDichVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_TenDichVu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        icon_DichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DichVu_buffet.png"))); // NOI18N

        label_MoTa.setText("<html>Cung cấp ăn uống miễn phí tại nhà hàng</html>");

        label_DonGia.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label_DonGia.setText("1.500.000 đ");

        javax.swing.GroupLayout btn_DichVuLayout = new javax.swing.GroupLayout(btn_DichVu);
        btn_DichVu.setLayout(btn_DichVuLayout);
        btn_DichVuLayout.setHorizontalGroup(
            btn_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DichVuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_DichVu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(btn_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_TenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        btn_DichVuLayout.setVerticalGroup(
            btn_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DichVuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_TenDichVu)
                .addGap(0, 0, 0)
                .addComponent(label_MoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_DonGia)
                .addGap(0, 9, Short.MAX_VALUE))
            .addComponent(icon_DichVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_DichVu1.setkEndColor(new java.awt.Color(255, 255, 255));
        btn_DichVu1.setkGradientFocus(250);
        btn_DichVu1.setkStartColor(new java.awt.Color(255, 255, 255));
        btn_DichVu1.setName("btn_DichVu1"); // NOI18N
        btn_DichVu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DichVu1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DichVu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DichVu1MouseExited(evt);
            }
        });

        label_TenDichVu1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        label_TenDichVu1.setText("SPA");
        label_TenDichVu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_TenDichVu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        icon_DichVu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DichVu_Spa.png"))); // NOI18N

        label_MoTa1.setText("Chăm sóc da, mát xa toàn thân");

        label_DonGia1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label_DonGia1.setText("1.500.000 đ");

        javax.swing.GroupLayout btn_DichVu1Layout = new javax.swing.GroupLayout(btn_DichVu1);
        btn_DichVu1.setLayout(btn_DichVu1Layout);
        btn_DichVu1Layout.setHorizontalGroup(
            btn_DichVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DichVu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_DichVu1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(btn_DichVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_TenDichVu1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_MoTa1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_DonGia1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        btn_DichVu1Layout.setVerticalGroup(
            btn_DichVu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DichVu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_TenDichVu1)
                .addGap(0, 0, 0)
                .addComponent(label_MoTa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_DonGia1)
                .addGap(0, 9, Short.MAX_VALUE))
            .addComponent(icon_DichVu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_DichVu2.setkEndColor(new java.awt.Color(255, 255, 255));
        btn_DichVu2.setkGradientFocus(250);
        btn_DichVu2.setkStartColor(new java.awt.Color(255, 255, 255));
        btn_DichVu2.setName("btn_DichVu2"); // NOI18N
        btn_DichVu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DichVu2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DichVu2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DichVu2MouseExited(evt);
            }
        });

        label_TenDichVu2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        label_TenDichVu2.setText("GIẶC ỦI");
        label_TenDichVu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_TenDichVu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        icon_DichVu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DichVu_GiacUi.png"))); // NOI18N

        label_MoTa2.setText("<html>Chăm sóc da, mát xa toàn thân</html>");

        label_DonGia2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        label_DonGia2.setText("1.500.000 đ");

        javax.swing.GroupLayout btn_DichVu2Layout = new javax.swing.GroupLayout(btn_DichVu2);
        btn_DichVu2.setLayout(btn_DichVu2Layout);
        btn_DichVu2Layout.setHorizontalGroup(
            btn_DichVu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DichVu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_DichVu2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(btn_DichVu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_TenDichVu2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_MoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_DonGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        btn_DichVu2Layout.setVerticalGroup(
            btn_DichVu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DichVu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_TenDichVu2)
                .addGap(0, 0, 0)
                .addComponent(label_MoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_DonGia2)
                .addGap(0, 9, Short.MAX_VALUE))
            .addComponent(icon_DichVu2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_DichVu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_DichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(btn_DichVu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(btn_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_DichVu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(btn_DichVu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)))
                .addGap(100, 100, 100)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btn_XacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMouseClicked
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_XacNhanMouseClicked

    private void btn_HuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseClicked
        // TODO add your handling code here:
                setVisible(false);
    }//GEN-LAST:event_btn_HuyMouseClicked

    private void btn_DichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVuMouseClicked

    private void btn_DichVuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVuMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVuMouseEntered

    private void btn_DichVuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVuMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVuMouseExited

    private void btn_DichVu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVu1MouseClicked

    private void btn_DichVu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVu1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVu1MouseEntered

    private void btn_DichVu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVu1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVu1MouseExited

    private void btn_DichVu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVu2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVu2MouseClicked

    private void btn_DichVu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVu2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVu2MouseEntered

    private void btn_DichVu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DichVu2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DichVu2MouseExited

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeTan_DonDatPhong_ThemDichVu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel btn_DichVu;
    private keeptoo.KGradientPanel btn_DichVu1;
    private keeptoo.KGradientPanel btn_DichVu2;
    private keeptoo.KGradientPanel btn_Huy;
    private keeptoo.KGradientPanel btn_XacNhan;
    private javax.swing.JLabel icon_DichVu;
    private javax.swing.JLabel icon_DichVu1;
    private javax.swing.JLabel icon_DichVu2;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel label_DonGia;
    private javax.swing.JLabel label_DonGia1;
    private javax.swing.JLabel label_DonGia2;
    private javax.swing.JLabel label_MoTa;
    private javax.swing.JLabel label_MoTa1;
    private javax.swing.JLabel label_MoTa2;
    private javax.swing.JLabel label_TenDichVu;
    private javax.swing.JLabel label_TenDichVu1;
    private javax.swing.JLabel label_TenDichVu2;
    // End of variables declaration//GEN-END:variables
}
