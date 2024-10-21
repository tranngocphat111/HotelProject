/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import keeptoo.KGradientPanel;
import model.DAO.TienNghiDAO;
import model.DTO.TienNghi;
import model.MongoDBConnection;
import test.convertImage;

/**
 *
 * @author Admin
 */
public class NhanVien_LoaiPhong_GUI extends javax.swing.JInternalFrame {

    private MongoDBConnection database = new MongoDBConnection();
    private List<TienNghi> list_TienNghi = new ArrayList<TienNghi>();
    private List<TienNghi> list_TienNghiDuocChon = new ArrayList<TienNghi>();

    private TienNghiDAO tienNghi_dao = new TienNghiDAO(database.getDatabase());
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private ArrayList<KGradientPanel> list_btnTienNghi = new ArrayList<KGradientPanel>();

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public NhanVien_LoaiPhong_GUI() {

        initComponents();

        list_TienNghi = tienNghi_dao.getAllTienNghi();
        Panel_TienNghi.setLayout(new java.awt.GridLayout(getRowTienNghi(list_TienNghi.size()), 2, 18, 17));
//      Load tiện nghi lên Frame
        DocDataLenFrame();

        list_btn.add(btn_Them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_Lammoi);
        list_btn.add(btn_Tim);

        list_btn.forEach((element) -> {
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mousePressed(MouseEvent e) {

//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    element.setkStartColor(new java.awt.Color(255, 225, 27));
                    element.setkEndColor(new java.awt.Color(255, 222, 89));
                    element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    element.setBorder(null);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    element.setkStartColor(new java.awt.Color(225, 176, 27));
                    element.setkEndColor(new java.awt.Color(255, 222, 89));
                    element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    element.setBorder(null);

                }
            });
        });

//        Bắt sự kiện chọn Tiện Nghi
        list_btnTienNghi.forEach((element) -> {

            element.addMouseListener(new MouseListener() {
                boolean click = false;

                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (!click) {
                        element.setkEndColor(new java.awt.Color(255, 222, 89));
                        element.setkStartColor(new java.awt.Color(225, 176, 27));
                        element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
                        click = true;
                        list_TienNghiDuocChon.add(tienNghi_dao.getTienNghiByMa(Integer.parseInt(element.getName())));
                    } else {
                        element.setkStartColor(new java.awt.Color(255, 255, 255));
                        element.setkEndColor(new java.awt.Color(255, 255, 255));
                        element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
                        click = false;
                        list_TienNghiDuocChon.remove(tienNghi_dao.getTienNghiByMa(Integer.parseInt(element.getName())));

                    }

//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        });

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    public void DocDataLenFrame() {
        int i = 1;
        for (TienNghi tienNghi : list_TienNghi) {
            KGradientPanel btnGradientPanel = new KGradientPanel();
            btnGradientPanel.setkEndColor(new java.awt.Color(255, 255, 255));
            btnGradientPanel.setkGradientFocus(250);
            btnGradientPanel.setkStartColor(new java.awt.Color(255, 255, 255));
            btnGradientPanel.setPreferredSize(new java.awt.Dimension(125, 47));

            JLabel txt_TenTienNghi = new JLabel();
            txt_TenTienNghi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            txt_TenTienNghi.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            txt_TenTienNghi.setText(tienNghi.getTenTienNghi());
            txt_TenTienNghi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            JLabel icon_TienNghi = new JLabel();
//            System.out.println(tienNghi.getHinhAnh());
            ImageIcon icon = new ImageScale().load1(new ImageIcon(tienNghi.getHinhAnh()), 32, 30);
            icon_TienNghi.setIcon(icon);

            javax.swing.GroupLayout btnGradientPanelLayout = new javax.swing.GroupLayout(btnGradientPanel);
            btnGradientPanel.setLayout(btnGradientPanelLayout);
            btnGradientPanelLayout.setHorizontalGroup(
                    btnGradientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGradientPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(icon_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_TenTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
            btnGradientPanelLayout.setVerticalGroup(
                    btnGradientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenTienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(icon_TienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            btnGradientPanel.setName("" + i++);
            list_btnTienNghi.add(btnGradientPanel);
            Panel_TienNghi.add(btnGradientPanel);
        }
    }

    public int getRowTienNghi(int n) {

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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ThongTinLoaiPhong = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_Loaigiuong = new javax.swing.JComboBox<>();
        txt_Loaiphong = new javax.swing.JTextField();
        txt_Dientich = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cb_Khuyenmai = new javax.swing.JComboBox<>();
        txt_Sokhachtoida = new javax.swing.JTextField();
        txt_Dongia = new javax.swing.JTextField();
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
        TienNghi = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        Panel_TienNghi = new javax.swing.JPanel();
        Backgroup = new javax.swing.JLabel();

        setName("page_LoaiPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 209, 84));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Tiện nghi");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(910, 50, 130, 32);

        ThongTinLoaiPhong.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinLoaiPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        ThongTinLoaiPhong.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên loại phòng ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Diện tích");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Loại giường");

        cb_Loaigiuong.setPreferredSize(new java.awt.Dimension(72, 32));
        cb_Loaigiuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaigiuongActionPerformed(evt);
            }
        });

        txt_Loaiphong.setPreferredSize(new java.awt.Dimension(64, 35));

        txt_Dientich.setPreferredSize(new java.awt.Dimension(64, 35));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Số khách tối đa");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Khuyến mãi");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Đơn giá");

        cb_Khuyenmai.setPreferredSize(new java.awt.Dimension(72, 35));
        cb_Khuyenmai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_KhuyenmaiActionPerformed(evt);
            }
        });

        txt_Sokhachtoida.setPreferredSize(new java.awt.Dimension(64, 35));
        txt_Sokhachtoida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SokhachtoidaActionPerformed(evt);
            }
        });

        txt_Dongia.setPreferredSize(new java.awt.Dimension(64, 35));

        javax.swing.GroupLayout ThongTinLoaiPhongLayout = new javax.swing.GroupLayout(ThongTinLoaiPhong);
        ThongTinLoaiPhong.setLayout(ThongTinLoaiPhongLayout);
        ThongTinLoaiPhongLayout.setHorizontalGroup(
            ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(142, 142, 142))
                            .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                                .addComponent(txt_Sokhachtoida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(46, 46, 46)))
                        .addGap(9, 9, 9)
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Khuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txt_Dongia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txt_Loaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Dientich, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(cb_Loaigiuong, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        ThongTinLoaiPhongLayout.setVerticalGroup(
            ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_Loaigiuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_Loaiphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_Dientich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(6, 6, 6)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Sokhachtoida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Dongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Khuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinLoaiPhong);
        ThongTinLoaiPhong.setBounds(80, 100, 790, 220);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Thông tin loại phòng");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel14);
        jLabel14.setBounds(80, 50, 250, 32);

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
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(730, 380, 140, 40);

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
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_LammoiLayout.setVerticalGroup(
            btn_LammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LammoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Lammoi);
        btn_Lammoi.setBounds(560, 380, 140, 40);

        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setkGradientFocus(250);
        btn_Them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemMousePressed(evt);
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
        btn_Them.setBounds(80, 380, 140, 40);

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
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        btn_XoaLayout.setVerticalGroup(
            btn_XoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_XoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Xoa);
        btn_Xoa.setBounds(400, 380, 140, 40);

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
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        btn_SuaLayout.setVerticalGroup(
            btn_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_SuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Sua);
        btn_Sua.setBounds(240, 380, 140, 40);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
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
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phòng", "Số tầng", "Loại phòng", "Loại giường", "Diện tích", "Tiện nghi", "Trạng thái", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(90, 470, 1130, 220);

        TienNghi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        TienNghi.setPreferredSize(new java.awt.Dimension(300, 316));

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(456, 316));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        Panel_TienNghi.setOpaque(false);
        Panel_TienNghi.setLayout(new java.awt.GridLayout(3, 2, 18, 17));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Panel_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Panel_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(300, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout TienNghiLayout = new javax.swing.GroupLayout(TienNghi);
        TienNghi.setLayout(TienNghiLayout);
        TienNghiLayout.setHorizontalGroup(
            TienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TienNghiLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        TienNghiLayout.setVerticalGroup(
            TienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        jPanel1.add(TienNghi);
        TienNghi.setBounds(910, 100, 300, 316);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_LoaiPhong"); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1283, 803);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_LoaigiuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaigiuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_LoaigiuongActionPerformed

    private void cb_KhuyenmaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_KhuyenmaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_KhuyenmaiActionPerformed

    private void txt_SokhachtoidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SokhachtoidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SokhachtoidaActionPerformed

    private void btn_ThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMousePressed
        // TODO add your handling code here:
        for (TienNghi tienNghi : list_TienNghiDuocChon) {
            System.out.println(tienNghi.getTenTienNghi());
        }
    }//GEN-LAST:event_btn_ThemMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel Panel_TienNghi;
    private javax.swing.JPanel ThongTinLoaiPhong;
    private javax.swing.JPanel TienNghi;
    private keeptoo.KGradientPanel btn_Lammoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Them;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private javax.swing.JComboBox<String> cb_Khuyenmai;
    private javax.swing.JComboBox<String> cb_Loaigiuong;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_Dientich;
    private javax.swing.JTextField txt_Dongia;
    private javax.swing.JTextField txt_Loaiphong;
    private javax.swing.JTextField txt_Sokhachtoida;
    // End of variables declaration//GEN-END:variables
}
