/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.DAO.NhanVienDAO;
import model.DTO.NhanVien;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class DangNhap_GUI extends javax.swing.JFrame {

    private MongoDBConnection database = new MongoDBConnection();
    private List<NhanVien> list_NhanVien = new ArrayList<NhanVien>();
    private NhanVienDAO nhanVien_Dao = new NhanVienDAO(database.getDatabase());
    public static NhanVien nhanVien_DangSuDung;

    /**
     * Creates new form DangNhap
     */
    public DangNhap_GUI() {
        initComponents();
        setLocationRelativeTo(null);

        list_NhanVien = nhanVien_Dao.getAllNhanVien();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_DangNhap = new keeptoo.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_TaiKhoan = new javax.swing.JTextField();
        txt_matKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setPreferredSize(new java.awt.Dimension(387, 500));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DangNhap.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(387, 500));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("ĐĂNG NHẬP");

        jLabel3.setText("Tài khoản");

        jLabel4.setText("Mật khẩu");

        btn_DangNhap.setkEndColor(new java.awt.Color(115, 115, 115));
        btn_DangNhap.setkGradientFocus(250);
        btn_DangNhap.setkStartColor(new java.awt.Color(0, 0, 0));
        btn_DangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DangNhapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DangNhapMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_DangNhapMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Đăng nhập");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_DangNhapLayout = new javax.swing.GroupLayout(btn_DangNhap);
        btn_DangNhap.setLayout(btn_DangNhapLayout);
        btn_DangNhapLayout.setHorizontalGroup(
            btn_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DangNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_DangNhapLayout.setVerticalGroup(
            btn_DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DangNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_TaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_TaiKhoanKeyPressed(evt);
            }
        });

        txt_matKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_matKhauActionPerformed(evt);
            }
        });
        txt_matKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_matKhauKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_matKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TaiKhoan))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(77, 77, 77)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btn_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DangNhapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DangNhapMouseEntered
        // TODO add your handling code here:
        btn_DangNhap.setkStartColor(new java.awt.Color(115, 115, 115));
        btn_DangNhap.setkEndColor(new java.awt.Color(0, 0, 0));
        btn_DangNhap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 209, 84), 1, true));
        btn_DangNhap.setBorder(null);


    }//GEN-LAST:event_btn_DangNhapMouseEntered

    private void btn_DangNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DangNhapMouseExited
        btn_DangNhap.setkEndColor(new java.awt.Color(115, 115, 115));
        btn_DangNhap.setkStartColor(new java.awt.Color(0, 0, 0));
        btn_DangNhap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 209, 84), 1, true));
        btn_DangNhap.setBorder(null);


    }//GEN-LAST:event_btn_DangNhapMouseExited

    private void txt_matKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_matKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_matKhauActionPerformed

    private void btn_DangNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DangNhapMousePressed
        // TODO add your handling code here:

        if (txt_TaiKhoan.getText().equals("") || txt_matKhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống thông tin");
            return;
        }
        NhanVien nv = nhanVien_Dao.checkAccount(txt_TaiKhoan.getText(), txt_matKhau.getText());

        if (nv == null) {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
            return;
        }

        nhanVien_DangSuDung = nv;

        if (nv.getChucVu().equals("Lễ tân")) {
            new LeTan_GUI().setVisible(true);
            setVisible(false);
            return;
        }

        if (nv.getChucVu().equals("Nhân viên")) {
            new NhanVien_GUI().setVisible(true);
            setVisible(false);
        } else {
            new QuanLy_GUI().setVisible(true);
            setVisible(false);
        }


    }//GEN-LAST:event_btn_DangNhapMousePressed

    private void txt_matKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_matKhauKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_TaiKhoan.getText().equals("") || txt_matKhau.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Không được bỏ trống thông tin");
                return;
            }
            NhanVien nv = nhanVien_Dao.checkAccount(txt_TaiKhoan.getText(), txt_matKhau.getText());

            if (nv == null) {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
                return;
            }
            nhanVien_DangSuDung = nv;
            if (nv.getChucVu().equals("Lễ tân")) {
                new LeTan_GUI().setVisible(true);
                setVisible(false);
                return;
            }

            if (nv.getChucVu().equals("Nhân viên")) {
                new NhanVien_GUI().setVisible(true);
                setVisible(false);
            } else {
                new QuanLy_GUI().setVisible(true);
                setVisible(false);
            }
        }
    }//GEN-LAST:event_txt_matKhauKeyPressed

    private void txt_TaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TaiKhoanKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_TaiKhoan.getText().equals("") || txt_matKhau.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Không được bỏ trống thông tin");
                return;
            }
            NhanVien nv = nhanVien_Dao.checkAccount(txt_TaiKhoan.getText(), txt_matKhau.getText());

            if (nv == null) {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
                return;
            }

            if (nv.getChucVu().equals("Lễ tân")) {
                new LeTan_GUI().setVisible(true);
                setVisible(false);
                return;
            }

            if (nv.getChucVu().equals("Nhân viên")) {
                new NhanVien_GUI().setVisible(true);
                setVisible(false);
            } else {
                new QuanLy_GUI().setVisible(true);
                setVisible(false);
            }
        }
    }//GEN-LAST:event_txt_TaiKhoanKeyPressed

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
            java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KGradientPanel btn_DangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txt_TaiKhoan;
    private javax.swing.JPasswordField txt_matKhau;
    // End of variables declaration//GEN-END:variables
}
