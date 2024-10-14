/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.text.Element;
import keeptoo.KGradientPanel;

/**
 *
 * @author Admin
 */
public class LeTan_GUI extends javax.swing.JFrame {
    private LeTan_DatPhong_GUI datPhong_Gui = new LeTan_DatPhong_GUI();
    private LeTan_ThanhToan_GUI thanhToan_Gui = new LeTan_ThanhToan_GUI();
    private LeTan_DonDatPhong_GUI donDatPhong_Gui = new LeTan_DonDatPhong_GUI();
    private ArrayList<KGradientPanel> list_page = new ArrayList<KGradientPanel>();
    private List<JInternalFrame> internalFrameList = new ArrayList<>();
    private String tam = "page_DatPhong";

    /**
     * Creates new form NewJFrame
     */
    public LeTan_GUI() {
        initComponents();

        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        page_DatPhong.setkStartColor(new java.awt.Color(225, 176, 27));
        page_DatPhong.setkEndColor(new java.awt.Color(255, 222, 89)); 
        
        datPhong_Gui.setVisible(true);
        
        jDesktopPane1.add(datPhong_Gui);
        jDesktopPane1.add(donDatPhong_Gui);
        jDesktopPane1.add(thanhToan_Gui);
        
        
        internalFrameList.add(datPhong_Gui);
        internalFrameList.add(donDatPhong_Gui);
        internalFrameList.add(thanhToan_Gui);
        
        
        list_page.add(page_DatPhong);
        list_page.add(page_DonDatPhong);
        list_page.add(page_ThanhToan);
        
    
        list_page.forEach((element) -> {
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
               
                @Override
                public void mousePressed(MouseEvent e) {
                        
                        for(KGradientPanel page : list_page){
                            
                            if(element.getName().equals(page.getName())){
                                page.setkStartColor(new java.awt.Color(225, 176, 27));
                                page.setkEndColor(new java.awt.Color(255, 222, 89)); 
                                page.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1));
                                page.setBorder(null);
                                for(JInternalFrame internal : internalFrameList){
                                    if(page.getName().equals(internal.getName())){
                                        internal.setVisible(true);
                                    }
                                    else {
                                         internal.setVisible(false);
                                    }
                                }
                                tam = page.getName();
                            }else{
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
                    if(!element.getName().equals(tam)){
                        element.setkEndColor(new java.awt.Color(115, 115, 115));
                        element.setkStartColor(new java.awt.Color(0, 0, 0)); 
                        element.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
                    }
                }
            });
        })
        ;
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
        page_ThanhToan = new keeptoo.KGradientPanel();
        label_ThanhToan = new javax.swing.JLabel();
        icon_ThanhToan = new javax.swing.JLabel();
        page_DonDatPhong = new keeptoo.KGradientPanel();
        label_DonDatPhong = new javax.swing.JLabel();
        icon_DonDatPhong = new javax.swing.JLabel();
        page_DatPhong = new keeptoo.KGradientPanel();
        label_DatPhong = new javax.swing.JLabel();
        icon_DatPhong = new javax.swing.JLabel();
        btn_DangXuat = new javax.swing.JPanel();
        label_ThanhToan1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setPreferredSize(new java.awt.Dimension(1645, 800));

        SideBar.setPreferredSize(new java.awt.Dimension(277, 800));
        SideBar.setLayout(null);

        logo.setBackground(new java.awt.Color(204, 255, 102));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo.png"))); // NOI18N
        SideBar.add(logo);
        logo.setBounds(0, 0, 277, 190);

        Jpanel_menu.setPreferredSize(new java.awt.Dimension(277, 536));

        page_ThanhToan.setkEndColor(new java.awt.Color(115, 115, 115));
        page_ThanhToan.setkGradientFocus(250);
        page_ThanhToan.setkStartColor(new java.awt.Color(0, 0, 0));
        page_ThanhToan.setName("page_ThanhToan"); // NOI18N
        page_ThanhToan.setPreferredSize(new java.awt.Dimension(277, 75));

        label_ThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_ThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        label_ThanhToan.setText("THANH TOÁN");
        label_ThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_ThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_ThanhToan.png"))); // NOI18N
        icon_ThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_ThanhToanLayout = new javax.swing.GroupLayout(page_ThanhToan);
        page_ThanhToan.setLayout(page_ThanhToanLayout);
        page_ThanhToanLayout.setHorizontalGroup(
            page_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_ThanhToanLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_ThanhToanLayout.setVerticalGroup(
            page_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_ThanhToanLayout.createSequentialGroup()
                .addGroup(page_ThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(page_ThanhToanLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(icon_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(412, 412, 412))
        );

        page_DonDatPhong.setkEndColor(new java.awt.Color(115, 115, 115));
        page_DonDatPhong.setkGradientFocus(250);
        page_DonDatPhong.setkStartColor(new java.awt.Color(0, 0, 0));
        page_DonDatPhong.setName("page_DonDatPhong"); // NOI18N
        page_DonDatPhong.setPreferredSize(new java.awt.Dimension(277, 75));

        label_DonDatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_DonDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        label_DonDatPhong.setText("ĐƠN ĐẶT PHÒNG");
        label_DonDatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_DonDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DonDatPhong.png"))); // NOI18N
        icon_DonDatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_DonDatPhongLayout = new javax.swing.GroupLayout(page_DonDatPhong);
        page_DonDatPhong.setLayout(page_DonDatPhongLayout);
        page_DonDatPhongLayout.setHorizontalGroup(
            page_DonDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_DonDatPhongLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_DonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_DonDatPhongLayout.setVerticalGroup(
            page_DonDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_DonDatPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(label_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        page_DatPhong.setkEndColor(new java.awt.Color(115, 115, 115));
        page_DatPhong.setkGradientFocus(250);
        page_DatPhong.setkStartColor(new java.awt.Color(0, 0, 0));
        page_DatPhong.setName("page_DatPhong"); // NOI18N
        page_DatPhong.setPreferredSize(new java.awt.Dimension(277, 75));

        label_DatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_DatPhong.setForeground(new java.awt.Color(255, 255, 255));
        label_DatPhong.setText("ĐẶT PHÒNG");
        label_DatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_DatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DatPhong.png"))); // NOI18N
        icon_DatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_DatPhongLayout = new javax.swing.GroupLayout(page_DatPhong);
        page_DatPhong.setLayout(page_DatPhongLayout);
        page_DatPhongLayout.setHorizontalGroup(
            page_DatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_DatPhongLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_DatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_DatPhongLayout.setVerticalGroup(
            page_DatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_DatPhongLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(icon_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_DatPhongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout Jpanel_menuLayout = new javax.swing.GroupLayout(Jpanel_menu);
        Jpanel_menu.setLayout(Jpanel_menuLayout);
        Jpanel_menuLayout.setHorizontalGroup(
            Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(page_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_DonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Jpanel_menuLayout.setVerticalGroup(
            Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel_menuLayout.createSequentialGroup()
                .addComponent(page_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(page_DonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(page_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        SideBar.add(Jpanel_menu);
        Jpanel_menu.setBounds(0, 190, 277, 220);

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

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N
        jLabel21.setText("backgoup_menu");
        jLabel21.setPreferredSize(new java.awt.Dimension(277, 400));
        SideBar.add(jLabel21);
        jLabel21.setBounds(0, 340, 277, 387);

        jDesktopPane1.setBackground(new java.awt.Color(255, 153, 153));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1276, 800));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1276, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(SideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(LeTan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeTan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeTan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeTan_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeTan_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel_menu;
    private javax.swing.JPanel SideBar;
    private javax.swing.JPanel btn_DangXuat;
    private javax.swing.JLabel icon_DatPhong;
    private javax.swing.JLabel icon_DonDatPhong;
    private javax.swing.JLabel icon_ThanhToan;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_DatPhong;
    private javax.swing.JLabel label_DonDatPhong;
    private javax.swing.JLabel label_ThanhToan;
    private javax.swing.JLabel label_ThanhToan1;
    private javax.swing.JLabel logo;
    private keeptoo.KGradientPanel page_DatPhong;
    private keeptoo.KGradientPanel page_DonDatPhong;
    private keeptoo.KGradientPanel page_ThanhToan;
    // End of variables declaration//GEN-END:variables
}
