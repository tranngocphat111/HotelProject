/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.text.DecimalFormat;
import model.DTO.DichVuSuDung;
import model.DTO.DichVuSuDungEmbed;

/**
 *
 * @author Admin
 */
public class ChitietPhong_DichVu extends javax.swing.JPanel {
    public static DecimalFormat df = new DecimalFormat("#,##0");
    /**
     * Creates new form ChitietPhong
     */
    public ChitietPhong_DichVu(DichVuSuDung dv) {
        initComponents();
        ten_dich_vu1.setText(dv.getTenDV());
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

        ten_dich_vu = new javax.swing.JLabel();
        thanh_tien = new javax.swing.JLabel();
        ngay_phong1 = new javax.swing.JLabel();
        tien_dv = new javax.swing.JLabel();
        so_luong1 = new javax.swing.JLabel();
        ten_dich_vu1 = new javax.swing.JLabel();

        ten_dich_vu.setBackground(new java.awt.Color(255, 209, 84));
        ten_dich_vu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ten_dich_vu.setForeground(new java.awt.Color(255, 209, 84));
        ten_dich_vu.setText("Spa");

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(678, 45));

        thanh_tien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        thanh_tien.setForeground(new java.awt.Color(255, 209, 84));
        thanh_tien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        thanh_tien.setText("jLabel2");

        ngay_phong1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ngay_phong1.setForeground(new java.awt.Color(255, 209, 84));
        ngay_phong1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ngay_phong1.setText("X");

        tien_dv.setBackground(new java.awt.Color(255, 209, 84));
        tien_dv.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        tien_dv.setForeground(new java.awt.Color(255, 209, 84));
        tien_dv.setText("jLabel1");

        so_luong1.setBackground(new java.awt.Color(255, 209, 84));
        so_luong1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        so_luong1.setForeground(new java.awt.Color(255, 209, 84));
        so_luong1.setText("jLabel1");

        ten_dich_vu1.setBackground(new java.awt.Color(255, 209, 84));
        ten_dich_vu1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ten_dich_vu1.setForeground(new java.awt.Color(255, 209, 84));
        ten_dich_vu1.setText("Spa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(ten_dich_vu1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(so_luong1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ngay_phong1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tien_dv, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(thanh_tien, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tien_dv, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(thanh_tien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngay_phong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ten_dich_vu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(so_luong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ngay_phong1;
    private javax.swing.JLabel so_luong1;
    private javax.swing.JLabel ten_dich_vu;
    private javax.swing.JLabel ten_dich_vu1;
    private javax.swing.JLabel thanh_tien;
    private javax.swing.JLabel tien_dv;
    // End of variables declaration//GEN-END:variables
}
