/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import java.awt.Color;
import javax.swing.ImageIcon;
import model.DTO.NhanVien;

/**
 *
 * @author ASUS
 */
public class TrangCaNhan_ThongTinCaNhan extends javax.swing.JFrame {

    /**
     * Creates new form TrangCaNhan
     */
    private static NhanVien nhanVien_DangSuDung;
    public TrangCaNhan_ThongTinCaNhan(NhanVien nhanVien_DangSuDung) {
        this.nhanVien_DangSuDung = nhanVien_DangSuDung;
        initComponents();
        jLabel2.setIcon(new ImageScale().getScaledImage1(jLabel2.getWidth(), jLabel2.getHeight(), new ImageIcon(this.nhanVien_DangSuDung.getAnhDaiDien())));
        CCCDText.setText(this.nhanVien_DangSuDung.getCCCD());
        thongTinText1.setText(this.nhanVien_DangSuDung.getTenNhanVien());
        chucVuText.setText(this.nhanVien_DangSuDung.getChucVu());
        SDTText.setText(this.nhanVien_DangSuDung.getSoDienThoai());
        DCText.setText(this.nhanVien_DangSuDung.getDiaChi());
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
        CCCDLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        thongTinPanel = new javax.swing.JPanel();
        thongTinText = new javax.swing.JLabel();
        thongTinText1 = new javax.swing.JLabel();
        CCCDPanel = new javax.swing.JPanel();
        CCCDText = new javax.swing.JLabel();
        CCCDLabel1 = new javax.swing.JLabel();
        ChucVuPanel = new javax.swing.JPanel();
        chucVuLabel = new javax.swing.JLabel();
        chucVuText = new javax.swing.JLabel();
        SDTPanel = new javax.swing.JPanel();
        SDTLabel = new javax.swing.JLabel();
        SDTText = new javax.swing.JLabel();
        DCPanel = new javax.swing.JPanel();
        DCLabel = new javax.swing.JLabel();
        DCText = new javax.swing.JLabel();
        btn_Thoat = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(639, 786));
        setPreferredSize(new java.awt.Dimension(639, 786));
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);

        CCCDLabel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        CCCDLabel.setForeground(new java.awt.Color(255, 255, 255));
        CCCDLabel.setText("Ảnh đại diện");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(CCCDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CCCDLabel)
                .addGap(73, 73, 73))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 636, 160);

        jPanel2.setOpaque(false);

        thongTinPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.yellow, null));
        thongTinPanel.setOpaque(false);
        thongTinPanel.setPreferredSize(new java.awt.Dimension(235, 91));
        thongTinPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thongTinPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                thongTinPanelMouseExited(evt);
            }
        });

        thongTinText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        thongTinText.setForeground(new java.awt.Color(255, 255, 255));
        thongTinText.setText("Họ tên");

        thongTinText1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        thongTinText1.setForeground(new java.awt.Color(255, 255, 255));
        thongTinText1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        thongTinText1.setText("Nguyễn Hoài Nhân");

        javax.swing.GroupLayout thongTinPanelLayout = new javax.swing.GroupLayout(thongTinPanel);
        thongTinPanel.setLayout(thongTinPanelLayout);
        thongTinPanelLayout.setHorizontalGroup(
            thongTinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongTinPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(thongTinText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(thongTinText1)
                .addGap(76, 76, 76))
        );
        thongTinPanelLayout.setVerticalGroup(
            thongTinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongTinPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(thongTinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thongTinText)
                    .addComponent(thongTinText1))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        CCCDPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.yellow, null));
        CCCDPanel.setOpaque(false);
        CCCDPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CCCDPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CCCDPanelMouseExited(evt);
            }
        });

        CCCDText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        CCCDText.setForeground(new java.awt.Color(255, 255, 255));
        CCCDText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        CCCDText.setText("0123456789");

        CCCDLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        CCCDLabel1.setForeground(new java.awt.Color(255, 255, 255));
        CCCDLabel1.setText("CCCD");

        javax.swing.GroupLayout CCCDPanelLayout = new javax.swing.GroupLayout(CCCDPanel);
        CCCDPanel.setLayout(CCCDPanelLayout);
        CCCDPanelLayout.setHorizontalGroup(
            CCCDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCCDPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(CCCDLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CCCDText)
                .addGap(75, 75, 75))
        );
        CCCDPanelLayout.setVerticalGroup(
            CCCDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CCCDPanelLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(CCCDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CCCDText)
                    .addComponent(CCCDLabel1))
                .addGap(30, 30, 30))
        );

        ChucVuPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.yellow, null));
        ChucVuPanel.setOpaque(false);
        ChucVuPanel.setPreferredSize(new java.awt.Dimension(235, 91));
        ChucVuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ChucVuPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ChucVuPanelMouseExited(evt);
            }
        });

        chucVuLabel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        chucVuLabel.setForeground(new java.awt.Color(255, 255, 255));
        chucVuLabel.setText("Chức vụ");

        chucVuText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        chucVuText.setForeground(new java.awt.Color(255, 255, 255));
        chucVuText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chucVuText.setText("Lễ tân");

        javax.swing.GroupLayout ChucVuPanelLayout = new javax.swing.GroupLayout(ChucVuPanel);
        ChucVuPanel.setLayout(ChucVuPanelLayout);
        ChucVuPanelLayout.setHorizontalGroup(
            ChucVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucVuPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(chucVuLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chucVuText)
                .addGap(79, 79, 79))
        );
        ChucVuPanelLayout.setVerticalGroup(
            ChucVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChucVuPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(ChucVuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chucVuLabel)
                    .addComponent(chucVuText))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        SDTPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.yellow, null));
        SDTPanel.setOpaque(false);
        SDTPanel.setPreferredSize(new java.awt.Dimension(235, 91));
        SDTPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SDTPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SDTPanelMouseExited(evt);
            }
        });

        SDTLabel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        SDTLabel.setForeground(new java.awt.Color(255, 255, 255));
        SDTLabel.setText("Số điện thoại");

        SDTText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        SDTText.setForeground(new java.awt.Color(255, 255, 255));
        SDTText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SDTText.setText("0123456789");

        javax.swing.GroupLayout SDTPanelLayout = new javax.swing.GroupLayout(SDTPanel);
        SDTPanel.setLayout(SDTPanelLayout);
        SDTPanelLayout.setHorizontalGroup(
            SDTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SDTPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(SDTLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SDTText)
                .addGap(77, 77, 77))
        );
        SDTPanelLayout.setVerticalGroup(
            SDTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SDTPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(SDTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SDTLabel)
                    .addComponent(SDTText))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        DCPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.yellow, null));
        DCPanel.setOpaque(false);
        DCPanel.setPreferredSize(new java.awt.Dimension(235, 91));
        DCPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DCPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DCPanelMouseExited(evt);
            }
        });

        DCLabel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        DCLabel.setForeground(new java.awt.Color(255, 255, 255));
        DCLabel.setText("Địa chỉ");

        DCText.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        DCText.setForeground(new java.awt.Color(255, 255, 255));
        DCText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DCText.setText("Quang Trung, Gò Vấp");

        javax.swing.GroupLayout DCPanelLayout = new javax.swing.GroupLayout(DCPanel);
        DCPanel.setLayout(DCPanelLayout);
        DCPanelLayout.setHorizontalGroup(
            DCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DCPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(DCLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addComponent(DCText)
                .addGap(76, 76, 76))
        );
        DCPanelLayout.setVerticalGroup(
            DCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DCPanelLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(DCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DCLabel)
                    .addComponent(DCText))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SDTPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addComponent(thongTinPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addComponent(ChucVuPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addComponent(DCPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addComponent(CCCDPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CCCDPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(thongTinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ChucVuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(SDTPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(DCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 155, 640, 455);

        btn_Thoat.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Thoat.setkGradientFocus(250);
        btn_Thoat.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Thoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThoatMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Thoát");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThoatLayout = new javax.swing.GroupLayout(btn_Thoat);
        btn_Thoat.setLayout(btn_ThoatLayout);
        btn_ThoatLayout.setHorizontalGroup(
            btn_ThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ThoatLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        btn_ThoatLayout.setVerticalGroup(
            btn_ThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(btn_Thoat);
        btn_Thoat.setBounds(460, 640, 150, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        jLabel1.setIconTextGap(0);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(1, 2, 640, 780);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CCCDPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CCCDPanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_CCCDPanelMouseEntered

    private void CCCDPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CCCDPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_CCCDPanelMouseExited

    private void thongTinPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongTinPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_thongTinPanelMouseExited

    private void thongTinPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongTinPanelMouseEntered


    }//GEN-LAST:event_thongTinPanelMouseEntered

    private void ChucVuPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChucVuPanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ChucVuPanelMouseEntered

    private void ChucVuPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChucVuPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ChucVuPanelMouseExited

    private void SDTPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SDTPanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SDTPanelMouseEntered

    private void SDTPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SDTPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SDTPanelMouseExited

    private void DCPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DCPanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DCPanelMouseEntered

    private void DCPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DCPanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DCPanelMouseExited

    private void btn_ThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMouseExited
        // TODO add your handling code here:
        // TODO add your handling code here:
        btn_Thoat.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Thoat.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Thoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Thoat.setBorder(null);
    }//GEN-LAST:event_btn_ThoatMouseExited

    private void btn_ThoatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_ThoatMousePressed

    private void btn_ThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMouseEntered
        // TODO add your handling code here:
        btn_Thoat.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_Thoat.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Thoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Thoat.setBorder(null);
    }//GEN-LAST:event_btn_ThoatMouseEntered

    private void btn_ThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMouseClicked
        // TODO add your handling code here:
        btn_Thoat.getParent().getParent().getParent().getParent().setVisible(false);
    }//GEN-LAST:event_btn_ThoatMouseClicked

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
            java.util.logging.Logger.getLogger(TrangCaNhan_ThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangCaNhan_ThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangCaNhan_ThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangCaNhan_ThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangCaNhan_ThongTinCaNhan(nhanVien_DangSuDung).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CCCDLabel;
    private javax.swing.JLabel CCCDLabel1;
    private javax.swing.JPanel CCCDPanel;
    private javax.swing.JLabel CCCDText;
    private javax.swing.JPanel ChucVuPanel;
    private javax.swing.JLabel DCLabel;
    private javax.swing.JPanel DCPanel;
    private javax.swing.JLabel DCText;
    private javax.swing.JLabel SDTLabel;
    private javax.swing.JPanel SDTPanel;
    private javax.swing.JLabel SDTText;
    private keeptoo.KGradientPanel btn_Thoat;
    private javax.swing.JLabel chucVuLabel;
    private javax.swing.JLabel chucVuText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel thongTinPanel;
    private javax.swing.JLabel thongTinText;
    private javax.swing.JLabel thongTinText1;
    // End of variables declaration//GEN-END:variables
}
