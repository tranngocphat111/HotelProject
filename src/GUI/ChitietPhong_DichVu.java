/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.text.DecimalFormat;
import model.DTO.DichVuSuDungEmbed;

/**
 *
 * @author Admin
 */
public class ChitietPhong_DichVu extends javax.swing.JPanel {
    public static DecimalFormat df = new DecimalFormat("#,##0");
    /**
     * Creates new form ChitietPhong_Phong
     */
    public ChitietPhong_DichVu(DichVuSuDungEmbed dv) {
        initComponents();
        ten_dich_vu.setText(dv.getTenDV());
        so_luong1.setText(String.valueOf(dv.getSoLuong()));
        thanh_tien.setText(df.format(dv.getDonGia() * dv.getSoLuong())+ " VNĐ");
        tien_dv.setText(df.format(dv.getDonGia()) + " VNĐ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        thanh_tien = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ten_dich_vu = new javax.swing.JLabel();
        ngay_phong1 = new javax.swing.JLabel();
        tien_dv = new javax.swing.JLabel();
        so_luong1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(471, 46));

        thanh_tien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        thanh_tien.setForeground(new java.awt.Color(255, 209, 84));
        thanh_tien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        thanh_tien.setText("jLabel2");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        ten_dich_vu.setBackground(new java.awt.Color(255, 209, 84));
        ten_dich_vu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ten_dich_vu.setForeground(new java.awt.Color(255, 209, 84));
        ten_dich_vu.setText("Spa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(ten_dich_vu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ten_dich_vu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ngay_phong1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ngay_phong1.setForeground(new java.awt.Color(255, 209, 84));
        ngay_phong1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ngay_phong1.setText("X");

        tien_dv.setBackground(new java.awt.Color(255, 209, 84));
        tien_dv.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tien_dv.setForeground(new java.awt.Color(255, 209, 84));
        tien_dv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tien_dv.setText("jLabel1");

        so_luong1.setBackground(new java.awt.Color(255, 209, 84));
        so_luong1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        so_luong1.setForeground(new java.awt.Color(255, 209, 84));
        so_luong1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        so_luong1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(so_luong1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(ngay_phong1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(tien_dv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thanh_tien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tien_dv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(thanh_tien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(so_luong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ngay_phong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel ngay_phong1;
    private javax.swing.JLabel so_luong1;
    private javax.swing.JLabel ten_dich_vu;
    private javax.swing.JLabel thanh_tien;
    private javax.swing.JLabel tien_dv;
    // End of variables declaration//GEN-END:variables
}
