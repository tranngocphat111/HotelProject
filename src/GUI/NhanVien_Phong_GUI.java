/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Admin
 */
public class NhanVien_Phong_GUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public NhanVien_Phong_GUI() {
        initComponents();
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
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
        ThongTinPhong = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_tang = new javax.swing.JComboBox<>();
        cb_loaiphong = new javax.swing.JComboBox<>();
        txt_gia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Lammoi = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_Them = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        area_ghichu = new javax.swing.JTextArea();
        Backgroup = new javax.swing.JLabel();

        setName("page_Phong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1276, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1276, 800));
        jPanel1.setLayout(null);

        ThongTinPhong.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        ThongTinPhong.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Loại Phòng");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tầng");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giá");

        cb_tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_tangActionPerformed(evt);
            }
        });

        cb_loaiphong.setPreferredSize(new java.awt.Dimension(108, 22));
        cb_loaiphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_loaiphongActionPerformed(evt);
            }
        });

        txt_gia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinPhongLayout = new javax.swing.GroupLayout(ThongTinPhong);
        ThongTinPhong.setLayout(ThongTinPhongLayout);
        ThongTinPhongLayout.setHorizontalGroup(
            ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinPhongLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinPhongLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(158, 158, 158)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinPhongLayout.createSequentialGroup()
                        .addComponent(cb_loaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cb_tang, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_gia, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        ThongTinPhongLayout.setVerticalGroup(
            ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinPhongLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(cb_loaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_tang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinPhong);
        ThongTinPhong.setBounds(80, 90, 660, 100);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Thông tin phòng");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel14);
        jLabel14.setBounds(80, 50, 210, 32);

        btn_Tim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Tìm");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_TimLayout = new javax.swing.GroupLayout(btn_Tim);
        btn_Tim.setLayout(btn_TimLayout);
        btn_TimLayout.setHorizontalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(730, 230, 140, 40);

        btn_Lammoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Lammoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Lammoi.setkGradientFocus(250);
        btn_Lammoi.setkStartColor(new java.awt.Color(225, 176, 27));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Làm mới");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_LammoiLayout = new javax.swing.GroupLayout(btn_Lammoi);
        btn_Lammoi.setLayout(btn_LammoiLayout);
        btn_LammoiLayout.setHorizontalGroup(
            btn_LammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LammoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_LammoiLayout.setVerticalGroup(
            btn_LammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LammoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Lammoi);
        btn_Lammoi.setBounds(560, 230, 140, 40);

        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setkGradientFocus(250);
        btn_Them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ThemMouseExited(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Thêm");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThemLayout = new javax.swing.GroupLayout(btn_Them);
        btn_Them.setLayout(btn_ThemLayout);
        btn_ThemLayout.setHorizontalGroup(
            btn_ThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ThemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThemLayout.setVerticalGroup(
            btn_ThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Them);
        btn_Them.setBounds(80, 230, 140, 40);

        btn_Xoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Xoa.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Xoa.setkGradientFocus(250);
        btn_Xoa.setkStartColor(new java.awt.Color(225, 176, 27));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Xóa");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_XoaLayout = new javax.swing.GroupLayout(btn_Xoa);
        btn_Xoa.setLayout(btn_XoaLayout);
        btn_XoaLayout.setHorizontalGroup(
            btn_XoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btn_XoaLayout.setVerticalGroup(
            btn_XoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_XoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Xoa);
        btn_Xoa.setBounds(400, 230, 140, 40);

        btn_Sua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Sua.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Sua.setkGradientFocus(250);
        btn_Sua.setkStartColor(new java.awt.Color(225, 176, 27));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Sửa");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_SuaLayout = new javax.swing.GroupLayout(btn_Sua);
        btn_Sua.setLayout(btn_SuaLayout);
        btn_SuaLayout.setHorizontalGroup(
            btn_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btn_SuaLayout.setVerticalGroup(
            btn_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_SuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Sua);
        btn_Sua.setBounds(240, 230, 140, 40);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phòng", "Số tầng", "Loại phòng", "Loại giường", "Diện tích", "Tiện nghi", "Trạng thái", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 310, 1120, 400);

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setToolTipText("");
        jScrollPane2.setInheritsPopupMenu(true);
        jScrollPane2.setOpaque(false);

        area_ghichu.setBackground(new java.awt.Color(0, 0, 0));
        area_ghichu.setColumns(20);
        area_ghichu.setForeground(new java.awt.Color(255, 255, 255));
        area_ghichu.setRows(3);
        area_ghichu.setText("Ghi Chú");
        area_ghichu.setCaretColor(new java.awt.Color(255, 255, 255));
        area_ghichu.setMargin(new java.awt.Insets(4, 6, 2, 6));
        area_ghichu.setOpaque(false);
        area_ghichu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                area_ghichuFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                area_ghichuFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(area_ghichu);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(850, 90, 280, 100);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1276, 800));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1276, 796);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1282, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMouseExited
        // TODO add your handling code here:
        btn_Them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Them.setBorder(null);
    }//GEN-LAST:event_btn_ThemMouseExited

    private void btn_ThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMouseEntered
        // TODO add your handling code here:
        btn_Them.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Them.setBorder(null);
    }//GEN-LAST:event_btn_ThemMouseEntered

    private void btn_ThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMouseClicked
        // TODO add your handling code here:

        System.out.println("đâs");
    }//GEN-LAST:event_btn_ThemMouseClicked

    private void txt_giaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giaActionPerformed

    private void cb_loaiphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_loaiphongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_loaiphongActionPerformed

    private void cb_tangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_tangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_tangActionPerformed

    private void area_ghichuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_ghichuFocusGained
        // TODO add your handling code here:
        if(area_ghichu.getText().equals("Ghi Chú")){
            area_ghichu.setText("");         
        }
    }//GEN-LAST:event_area_ghichuFocusGained

    private void area_ghichuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_ghichuFocusLost
        // TODO add your handling code here:
        if(area_ghichu.getText().equals("")){
            area_ghichu.setText("Ghi Chú");         
        }
    }//GEN-LAST:event_area_ghichuFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel ThongTinPhong;
    private javax.swing.JTextArea area_ghichu;
    private keeptoo.KGradientPanel btn_Lammoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Them;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private javax.swing.JComboBox<String> cb_loaiphong;
    private javax.swing.JComboBox<String> cb_tang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_gia;
    // End of variables declaration//GEN-END:variables
}
