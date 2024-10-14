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
public class NhanVien_GUI extends javax.swing.JFrame {
    NhanVien_Phong_GUI phong_Gui = new NhanVien_Phong_GUI();
    NhanVien_LoaiPhong_GUI loaiphong_Gui = new NhanVien_LoaiPhong_GUI();
    NhanVien_TienNghi_GUI tiennghi_Gui = new NhanVien_TienNghi_GUI();
    NhanVien_DichVu_GUI dichvu_Gui = new NhanVien_DichVu_GUI();
    NhanVien_KhuyenMai_GUI khuyenmai_Gui = new NhanVien_KhuyenMai_GUI();
    NhanVien_KhachHang_GUI khachhang_Gui = new NhanVien_KhachHang_GUI();
    
    private ArrayList<KGradientPanel> list_page = new ArrayList<KGradientPanel>();
    private List<JInternalFrame> internalFrameList = new ArrayList<>();
    private String tam = "page_Phong";
    /**
     * Creates new form NewJFrame
     */
    public NhanVien_GUI() {
        initComponents();

        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        page_Phong.setkStartColor(new java.awt.Color(225, 176, 27));
        page_Phong.setkEndColor(new java.awt.Color(255, 222, 89));
        
        phong_Gui.setVisible(true);
        
        jDesktopPane1.add(phong_Gui);
        jDesktopPane1.add(loaiphong_Gui);
        jDesktopPane1.add(tiennghi_Gui);
        jDesktopPane1.add(dichvu_Gui);
        jDesktopPane1.add(khuyenmai_Gui);
        jDesktopPane1.add(khachhang_Gui);

        
        internalFrameList.add(phong_Gui);
        internalFrameList.add(loaiphong_Gui);
        internalFrameList.add(tiennghi_Gui);
        internalFrameList.add(dichvu_Gui);
        internalFrameList.add(khuyenmai_Gui);
        internalFrameList.add(khachhang_Gui);

        
        
        list_page.add(page_Phong);
        list_page.add(page_LoaiPhong);
        list_page.add(page_TienNghi);
        list_page.add(page_DichVu);
        list_page.add(page_KhuyenMai);
        list_page.add(page_KhachHang);
        
        
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
                                System.out.println(element.getName());
                                System.out.println(page.getName());
                                page.setkStartColor(new java.awt.Color(225, 176, 27));
                                page.setkEndColor(new java.awt.Color(255, 222, 89)); 
                                page.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1));
                                page.setBorder(null);
                                for(JInternalFrame internal : internalFrameList){
//                                    System.out.println(internal.getName());
                                    if(page.getName().equals(internal.getName())){
                                        
                                        System.out.println(page.getName());
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
        page_LoaiPhong = new keeptoo.KGradientPanel();
        label_DonDatPhong = new javax.swing.JLabel();
        icon_DonDatPhong = new javax.swing.JLabel();
        page_Phong = new keeptoo.KGradientPanel();
        label_DatPhong = new javax.swing.JLabel();
        icon_DatPhong = new javax.swing.JLabel();
        page_TienNghi = new keeptoo.KGradientPanel();
        label_ThanhToan2 = new javax.swing.JLabel();
        icon_ThanhToan1 = new javax.swing.JLabel();
        page_DichVu = new keeptoo.KGradientPanel();
        label_ThanhToan3 = new javax.swing.JLabel();
        icon_ThanhToan2 = new javax.swing.JLabel();
        page_KhuyenMai = new keeptoo.KGradientPanel();
        label_ThanhToan4 = new javax.swing.JLabel();
        icon_ThanhToan3 = new javax.swing.JLabel();
        page_KhachHang = new keeptoo.KGradientPanel();
        label_ThanhToan = new javax.swing.JLabel();
        icon_ThanhToan = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btn_DangXuat = new javax.swing.JPanel();
        label_ThanhToan1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1555, 830));

        jPanel2.setPreferredSize(new java.awt.Dimension(1645, 800));

        SideBar.setPreferredSize(new java.awt.Dimension(277, 800));
        SideBar.setLayout(null);

        logo.setBackground(new java.awt.Color(204, 255, 102));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo.png"))); // NOI18N
        SideBar.add(logo);
        logo.setBounds(0, 0, 277, 180);

        Jpanel_menu.setPreferredSize(new java.awt.Dimension(277, 536));

        page_LoaiPhong.setkEndColor(new java.awt.Color(115, 115, 115));
        page_LoaiPhong.setkGradientFocus(250);
        page_LoaiPhong.setkStartColor(new java.awt.Color(0, 0, 0));
        page_LoaiPhong.setName("page_LoaiPhong"); // NOI18N
        page_LoaiPhong.setPreferredSize(new java.awt.Dimension(277, 75));

        label_DonDatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_DonDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        label_DonDatPhong.setText("LOẠI PHÒNG");
        label_DonDatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_DonDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_LoaiPhong.png"))); // NOI18N
        icon_DonDatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_LoaiPhongLayout = new javax.swing.GroupLayout(page_LoaiPhong);
        page_LoaiPhong.setLayout(page_LoaiPhongLayout);
        page_LoaiPhongLayout.setHorizontalGroup(
            page_LoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_LoaiPhongLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_DonDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_LoaiPhongLayout.setVerticalGroup(
            page_LoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_LoaiPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(label_DonDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        page_Phong.setkEndColor(new java.awt.Color(115, 115, 115));
        page_Phong.setkGradientFocus(250);
        page_Phong.setkStartColor(new java.awt.Color(0, 0, 0));
        page_Phong.setName("page_Phong"); // NOI18N
        page_Phong.setPreferredSize(new java.awt.Dimension(277, 75));

        label_DatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_DatPhong.setForeground(new java.awt.Color(255, 255, 255));
        label_DatPhong.setText("PHÒNG");
        label_DatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_DatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DatPhong.png"))); // NOI18N
        icon_DatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_PhongLayout = new javax.swing.GroupLayout(page_Phong);
        page_Phong.setLayout(page_PhongLayout);
        page_PhongLayout.setHorizontalGroup(
            page_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_PhongLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_DatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(label_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_PhongLayout.setVerticalGroup(
            page_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_PhongLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(icon_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_PhongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_DatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        page_TienNghi.setkEndColor(new java.awt.Color(115, 115, 115));
        page_TienNghi.setkGradientFocus(250);
        page_TienNghi.setkStartColor(new java.awt.Color(0, 0, 0));
        page_TienNghi.setName("page_TienNghi"); // NOI18N
        page_TienNghi.setPreferredSize(new java.awt.Dimension(277, 75));

        label_ThanhToan2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_ThanhToan2.setForeground(new java.awt.Color(255, 255, 255));
        label_ThanhToan2.setText("TIỆN NGHI");
        label_ThanhToan2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_ThanhToan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_TienNghi.png"))); // NOI18N
        icon_ThanhToan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_TienNghiLayout = new javax.swing.GroupLayout(page_TienNghi);
        page_TienNghi.setLayout(page_TienNghiLayout);
        page_TienNghiLayout.setHorizontalGroup(
            page_TienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_TienNghiLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_ThanhToan1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_TienNghiLayout.setVerticalGroup(
            page_TienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_TienNghiLayout.createSequentialGroup()
                .addGroup(page_TienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_ThanhToan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(page_TienNghiLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(icon_ThanhToan1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(412, 412, 412))
        );

        page_DichVu.setkEndColor(new java.awt.Color(115, 115, 115));
        page_DichVu.setkGradientFocus(250);
        page_DichVu.setkStartColor(new java.awt.Color(0, 0, 0));
        page_DichVu.setName("page_DichVu"); // NOI18N
        page_DichVu.setPreferredSize(new java.awt.Dimension(277, 75));

        label_ThanhToan3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_ThanhToan3.setForeground(new java.awt.Color(255, 255, 255));
        label_ThanhToan3.setText("DỊCH VỤ");
        label_ThanhToan3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_ThanhToan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_DichVu.png"))); // NOI18N
        icon_ThanhToan2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_DichVuLayout = new javax.swing.GroupLayout(page_DichVu);
        page_DichVu.setLayout(page_DichVuLayout);
        page_DichVuLayout.setHorizontalGroup(
            page_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_DichVuLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_ThanhToan2, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ThanhToan3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_DichVuLayout.setVerticalGroup(
            page_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_DichVuLayout.createSequentialGroup()
                .addGroup(page_DichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_ThanhToan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(page_DichVuLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(icon_ThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(412, 412, 412))
        );

        page_KhuyenMai.setkEndColor(new java.awt.Color(115, 115, 115));
        page_KhuyenMai.setkGradientFocus(250);
        page_KhuyenMai.setkStartColor(new java.awt.Color(0, 0, 0));
        page_KhuyenMai.setName("page_KhuyenMai"); // NOI18N
        page_KhuyenMai.setPreferredSize(new java.awt.Dimension(277, 75));

        label_ThanhToan4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_ThanhToan4.setForeground(new java.awt.Color(255, 255, 255));
        label_ThanhToan4.setText("KHUYẾN MÃI");
        label_ThanhToan4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_ThanhToan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_KhuyenMai.png"))); // NOI18N
        icon_ThanhToan3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_KhuyenMaiLayout = new javax.swing.GroupLayout(page_KhuyenMai);
        page_KhuyenMai.setLayout(page_KhuyenMaiLayout);
        page_KhuyenMaiLayout.setHorizontalGroup(
            page_KhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_KhuyenMaiLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_ThanhToan3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ThanhToan4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_KhuyenMaiLayout.setVerticalGroup(
            page_KhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_KhuyenMaiLayout.createSequentialGroup()
                .addGroup(page_KhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_ThanhToan4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(page_KhuyenMaiLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(icon_ThanhToan3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(412, 412, 412))
        );

        page_KhachHang.setkEndColor(new java.awt.Color(115, 115, 115));
        page_KhachHang.setkGradientFocus(250);
        page_KhachHang.setkStartColor(new java.awt.Color(0, 0, 0));
        page_KhachHang.setName("page_KhachHang"); // NOI18N
        page_KhachHang.setPreferredSize(new java.awt.Dimension(277, 75));

        label_ThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_ThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        label_ThanhToan.setText("KHÁCH HÀNG");
        label_ThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        icon_ThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_KhachHang.png"))); // NOI18N
        icon_ThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout page_KhachHangLayout = new javax.swing.GroupLayout(page_KhachHang);
        page_KhachHang.setLayout(page_KhachHangLayout);
        page_KhachHangLayout.setHorizontalGroup(
            page_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, page_KhachHangLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(icon_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        page_KhachHangLayout.setVerticalGroup(
            page_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(page_KhachHangLayout.createSequentialGroup()
                .addGroup(page_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(page_KhachHangLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(icon_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(412, 412, 412))
        );

        javax.swing.GroupLayout Jpanel_menuLayout = new javax.swing.GroupLayout(Jpanel_menu);
        Jpanel_menu.setLayout(Jpanel_menuLayout);
        Jpanel_menuLayout.setHorizontalGroup(
            Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(page_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_KhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(page_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Jpanel_menuLayout.setVerticalGroup(
            Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel_menuLayout.createSequentialGroup()
                .addComponent(page_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(Jpanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel_menuLayout.createSequentialGroup()
                        .addComponent(page_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(page_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(page_KhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(page_KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Jpanel_menuLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(page_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        SideBar.add(Jpanel_menu);
        Jpanel_menu.setBounds(0, 180, 277, 440);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N
        jLabel21.setText("backgoup_menu");
        jLabel21.setPreferredSize(new java.awt.Dimension(277, 400));
        SideBar.add(jLabel21);
        jLabel21.setBounds(0, 410, 277, 320);

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
            java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new NhanVien_GUI().setVisible(true);
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
    private javax.swing.JLabel icon_ThanhToan1;
    private javax.swing.JLabel icon_ThanhToan2;
    private javax.swing.JLabel icon_ThanhToan3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_DatPhong;
    private javax.swing.JLabel label_DonDatPhong;
    private javax.swing.JLabel label_ThanhToan;
    private javax.swing.JLabel label_ThanhToan1;
    private javax.swing.JLabel label_ThanhToan2;
    private javax.swing.JLabel label_ThanhToan3;
    private javax.swing.JLabel label_ThanhToan4;
    private javax.swing.JLabel logo;
    private keeptoo.KGradientPanel page_DichVu;
    private keeptoo.KGradientPanel page_KhachHang;
    private keeptoo.KGradientPanel page_KhuyenMai;
    private keeptoo.KGradientPanel page_LoaiPhong;
    private keeptoo.KGradientPanel page_Phong;
    private keeptoo.KGradientPanel page_TienNghi;
    // End of variables declaration//GEN-END:variables
}
