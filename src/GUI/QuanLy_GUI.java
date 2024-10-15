/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.util.Elements;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import keeptoo.KGradientPanel;

/**
 *
 * @author Admin
 */
public class QuanLy_GUI extends javax.swing.JFrame {
    QuanLy_NhanVien_GUI nhanvien_Gui = new QuanLy_NhanVien_GUI();
    QuanLy_DoanhThu_GUI taikhoan_Gui = new QuanLy_DoanhThu_GUI();
    QuanLy_TaiKhoan_GUI doanhthu_Gui = new QuanLy_TaiKhoan_GUI();


    private ArrayList<KGradientPanel> list_page = new ArrayList<KGradientPanel>();
    private List<JInternalFrame> internalFrameList = new ArrayList<>();
    private String tam = "page_NhanVien";
   

    /**
     * Creates new form NewJFrame
     */
    public QuanLy_GUI() {
        initComponents();

        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        page_NhanVien.setkStartColor(new java.awt.Color(225, 176, 27));
        page_NhanVien.setkEndColor(new java.awt.Color(255, 222, 89));

        nhanvien_Gui.setVisible(true);

        jDesktopPane1.add(nhanvien_Gui);
        jDesktopPane1.add(taikhoan_Gui);
        jDesktopPane1.add(doanhthu_Gui);
    
        
        
        internalFrameList.add(nhanvien_Gui);
        internalFrameList.add(taikhoan_Gui);
        internalFrameList.add(doanhthu_Gui);
       

        list_page.add(page_NhanVien);
        list_page.add(page_TaiKhoan);
        list_page.add(page_DoanhThu);
        

        list_page.forEach((element) -> {
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mousePressed(MouseEvent e) {

                    for (KGradientPanel page : list_page) {

                        if (element.getName().equals(page.getName())) {
                            page.setkStartColor(new java.awt.Color(225, 176, 27));
                            page.setkEndColor(new java.awt.Color(255, 222, 89));
                            page.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1));
                            page.setBorder(null);
                            for (JInternalFrame internal : internalFrameList) {
                                if (page.getName().equals(internal.getName())) {
                                    internal.setVisible(true);
                                } else {
                                    internal.setVisible(false);
                                }
                            }
                            tam = page.getName();
                        } else {
                            page.setkEndColor(new java.awt.Color(115, 115, 115));
                            page.setkStartColor(new java.awt.Color(0, 0, 0));
                            page.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1));
                            page.setBorder(null);
                        }
                    }
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    element.setkStartColor(new java.awt.Color(225, 176, 27));
                    element.setkEndColor(new java.awt.Color(255, 222, 89));
                    element.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1));
                    element.setBorder(null);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!element.getName().equals(tam)) {
                        element.setkEndColor(new java.awt.Color(115, 115, 115));
                        element.setkStartColor(new java.awt.Color(0, 0, 0));
                        element.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
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

        jPanel2 = new javax.swing.JPanel();
        SideBar = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        Jpanel_menu = new javax.swing.JPanel();
        page_TaiKhoan = new keeptoo.KGradientPanel();
        label_DonDatPhong = new javax.swing.JLabel();
        icon_DonDatPhong = new javax.swing.JLabel();
        page_NhanVien = new keeptoo.KGradientPanel();
        label_DatPhong = new javax.swing.JLabel();
        icon_DatPhong = new javax.swing.JLabel();
        page_DoanhThu = new keeptoo.KGradientPanel();
        label_ThanhToan2 = new javax.swing.JLabel();
        icon_ThanhToan1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btn_DangXuat = new javax.swing.JPanel();
        label_ThanhToan1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setPreferredSize(new java.awt.Dimension(1645, 800));

        SideBar.setPreferredSize(new java.awt.Dimension(277, 800));
        SideBar.setLayout(null);

        logo.setBackground(new java.awt.Color(204, 255, 102));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo.png"))); // NOI18N
        SideBar.add(logo);
        logo.setBounds(0, 0, 277, 180);

        Jpanel_menu.setPreferredSize(new java.awt.Dimension(277, 536));

        page_TaiKhoan.setkEndColor(new java.awt.Color(115, 115, 115));
        page_TaiKhoan.setkGradientFocus(250);
        page_TaiKhoan.setkStartColor(new java.awt.Color(0, 0, 0));
        page_TaiKhoan.setName("page_TaiKhoan"); // NOI18N
        page_TaiKhoan.setPreferredSize(new java.awt.Dimension(277, 75));

        label_DonDatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_DonDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        label_DonDatPhong.setText("TÀI KHOẢN");
        label_DonDatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_DonDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_TaiKhoan.png"))); // NOI18N
        icon_DonDatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_TaiKhoanLayout = new javax.swing.GroupLayout(page_TaiKhoan);
        page_TaiKhoan.setLayout(page_TaiKhoanLayout);
        page_TaiKhoanLayout.setHorizontalGroup(
            page_TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_TaiKhoanLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_DonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_TaiKhoanLayout.setVerticalGroup(
            page_TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_TaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(label_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        page_NhanVien.setkEndColor(new java.awt.Color(115, 115, 115));
        page_NhanVien.setkGradientFocus(250);
        page_NhanVien.setkStartColor(new java.awt.Color(0, 0, 0));
        page_NhanVien.setName("page_NhanVien"); // NOI18N
        page_NhanVien.setPreferredSize(new java.awt.Dimension(277, 75));

        label_DatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_DatPhong.setForeground(new java.awt.Color(255, 255, 255));
        label_DatPhong.setText("NHÂN VIÊN");
        label_DatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_DatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_NhanVien.png"))); // NOI18N
        icon_DatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_NhanVienLayout = new javax.swing.GroupLayout(page_NhanVien);
        page_NhanVien.setLayout(page_NhanVienLayout);
        page_NhanVienLayout.setHorizontalGroup(
            page_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_NhanVienLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_DatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(label_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_NhanVienLayout.setVerticalGroup(
            page_NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_NhanVienLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(icon_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_NhanVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        page_DoanhThu.setkEndColor(new java.awt.Color(115, 115, 115));
        page_DoanhThu.setkGradientFocus(250);
        page_DoanhThu.setkStartColor(new java.awt.Color(0, 0, 0));
        page_DoanhThu.setName("page_DoanhThu"); // NOI18N
        page_DoanhThu.setPreferredSize(new java.awt.Dimension(277, 75));

        label_ThanhToan2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_ThanhToan2.setForeground(new java.awt.Color(255, 255, 255));
        label_ThanhToan2.setText("DOANH THU");
        label_ThanhToan2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_ThanhToan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DoanhThu.png"))); // NOI18N
        icon_ThanhToan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_DoanhThuLayout = new javax.swing.GroupLayout(page_DoanhThu);
        page_DoanhThu.setLayout(page_DoanhThuLayout);
        page_DoanhThuLayout.setHorizontalGroup(
            page_DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_DoanhThuLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(icon_ThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_DoanhThuLayout.setVerticalGroup(
            page_DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_DoanhThuLayout.createSequentialGroup()
                .addGroup(page_DoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_ThanhToan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(page_DoanhThuLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(icon_ThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(412, 412, 412))
        );

        javax.swing.GroupLayout Jpanel_menuLayout = new javax.swing.GroupLayout(Jpanel_menu);
        Jpanel_menu.setLayout(Jpanel_menuLayout);
        Jpanel_menuLayout.setHorizontalGroup(
            Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(page_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Jpanel_menuLayout.setVerticalGroup(
            Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel_menuLayout.createSequentialGroup()
                .addComponent(page_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(page_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Jpanel_menuLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(page_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        SideBar.add(Jpanel_menu);
        Jpanel_menu.setBounds(0, 180, 277, 220);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N
        jLabel21.setText("backgoup_menu");
        jLabel21.setPreferredSize(new java.awt.Dimension(277, 400));
        SideBar.add(jLabel21);
        jLabel21.setBounds(0, 400, 277, 330);

        btn_DangXuat.setBackground(new java.awt.Color(214, 50, 50));
        btn_DangXuat.setPreferredSize(new java.awt.Dimension(277, 100));
        btn_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DangXuatMouseExited(evt);
            }
        });

        label_ThanhToan1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_ThanhToan1.setForeground(new java.awt.Color(255, 255, 255));
        label_ThanhToan1.setText("ĐĂNG XUẤT");
        label_ThanhToan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DangXuat_.png"))); // NOI18N
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_DangXuatLayout = new javax.swing.GroupLayout(btn_DangXuat);
        btn_DangXuat.setLayout(btn_DangXuatLayout);
        btn_DangXuatLayout.setHorizontalGroup(
            btn_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_DangXuatLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        btn_DangXuatLayout.setVerticalGroup(
            btn_DangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_DangXuatLayout.createSequentialGroup()
                .addComponent(label_ThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(btn_DangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        SideBar.add(btn_DangXuat);
        btn_DangXuat.setBounds(0, 727, 277, 73);

        jDesktopPane1.setBackground(new java.awt.Color(255, 153, 153));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1283, 803));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1283, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 803, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(SideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1560, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btn_DangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DangXuatMouseEntered
        // TODO add your handling code here:
        btn_DangXuat.setBackground(new java.awt.Color(142, 52, 52));


    }//GEN-LAST:event_btn_DangXuatMouseEntered

    private void btn_DangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DangXuatMouseExited
        // TODO add your handling code here:
        btn_DangXuat.setBackground(new java.awt.Color(214, 50, 50));
    }//GEN-LAST:event_btn_DangXuatMouseExited

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
            java.util.logging.Logger.getLogger(QuanLy_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLy_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLy_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLy_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new QuanLy_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel_menu;
    private javax.swing.JPanel SideBar;
    private javax.swing.JPanel btn_DangXuat;
    private javax.swing.JLabel icon_DatPhong;
    private javax.swing.JLabel icon_DonDatPhong;
    private javax.swing.JLabel icon_ThanhToan1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_DatPhong;
    private javax.swing.JLabel label_DonDatPhong;
    private javax.swing.JLabel label_ThanhToan1;
    private javax.swing.JLabel label_ThanhToan2;
    private javax.swing.JLabel logo;
    private keeptoo.KGradientPanel page_DoanhThu;
    private keeptoo.KGradientPanel page_NhanVien;
    private keeptoo.KGradientPanel page_TaiKhoan;
    // End of variables declaration//GEN-END:variables
}