/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.NhanVienDAO;
import model.DTO.NhanVien;
import model.MongoDBConnection;
import test.convertImage;

/**
 *
 * @author Admin
 */
public class QuanLy_NhanVien_GUI extends javax.swing.JInternalFrame {
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    
    private NhanVienDAO nhanVienDAO = new NhanVienDAO(database);
    
    private byte[] hinhAnh = null;
    
    
    public QuanLy_NhanVien_GUI() {
        initComponents();
        
        
        
        
        list_btn.add(btn_Them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_Lammoi);
        list_btn.add(btn_Chonhinhanh);
        
        
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
        })
        ;
        
        
        
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
        ThongTinNhanVien = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cb_ChucVu = new javax.swing.JComboBox<>();
        txt_DC = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_Chonhinhanh = new keeptoo.KGradientPanel();
        jLabel23 = new javax.swing.JLabel();
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
        ChuaAnhNhanVien = new javax.swing.JPanel();
        anhnhanvien = new javax.swing.JLabel();
        CBox_TieuChiTimNV = new javax.swing.JComboBox<>();
        ChucVu = new javax.swing.JComboBox<>();
        txt_Ma_va_Ten = new javax.swing.JTextField();
        Backgroup = new javax.swing.JLabel();

        setName("page_NhanVien"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinNhanVien.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        ThongTinNhanVien.setOpaque(false);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CCCD/Passport");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Họ và tên");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Số điện thoại");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Địa chỉ");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Chức vụ");

        Set<String> set = new HashSet<>();
        for(NhanVien x : nhanVienDAO.getAllNhanVien()) {
            if(!set.contains(x.getChucVu())) {
                set.add(x.getChucVu());
                cb_ChucVu.addItem(x.getChucVu());
            }
        }
        cb_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ChucVuActionPerformed(evt);
            }
        });

        txt_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinNhanVienLayout = new javax.swing.GroupLayout(ThongTinNhanVien);
        ThongTinNhanVien.setLayout(ThongTinNhanVienLayout);
        ThongTinNhanVienLayout.setHorizontalGroup(
            ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                        .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txt_DC, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                        .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(159, 159, 159))
                            .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(txt_CCCD)
                                .addGap(36, 36, 36)))
                        .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156))
                            .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                                .addComponent(txt_HoTen)
                                .addGap(35, 35, 35)))
                        .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        ThongTinNhanVienLayout.setVerticalGroup(
            ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinNhanVienLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_CCCD)
                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(6, 6, 6)
                .addGroup(ThongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_DC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ChucVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinNhanVien);
        ThongTinNhanVien.setBounds(80, 90, 940, 180);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Thông tin nhân viên");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel14.setName(""); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(80, 50, 250, 32);

        btn_Chonhinhanh.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Chonhinhanh.setkGradientFocus(250);
        btn_Chonhinhanh.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Chonhinhanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ChonhinhanhMouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Chọn hình ảnh");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_ChonhinhanhLayout = new javax.swing.GroupLayout(btn_Chonhinhanh);
        btn_Chonhinhanh.setLayout(btn_ChonhinhanhLayout);
        btn_ChonhinhanhLayout.setHorizontalGroup(
            btn_ChonhinhanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ChonhinhanhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ChonhinhanhLayout.setVerticalGroup(
            btn_ChonhinhanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ChonhinhanhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Chonhinhanh);
        btn_Chonhinhanh.setBounds(1050, 300, 170, 40);

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
        btn_Lammoi.setBounds(560, 300, 140, 40);

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
        btn_Them.setBounds(80, 300, 140, 40);

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
        btn_Xoa.setBounds(400, 300, 140, 40);

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
        btn_Sua.setBounds(240, 300, 140, 40);

        JTableHeader header = jTable1.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 15));
        jTable1.setModel(duaDuLieuVaoTable(nhanVienDAO.getAllNhanVien()));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(110);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 380, 1140, 290);

        ChuaAnhNhanVien.setBackground(new java.awt.Color(0, 0, 0));
        ChuaAnhNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        ChuaAnhNhanVien.setOpaque(false);

        anhnhanvien.setForeground(new java.awt.Color(255, 255, 255));
        anhnhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/anh_nhanvien_cute.jpg"))); // NOI18N

        javax.swing.GroupLayout ChuaAnhNhanVienLayout = new javax.swing.GroupLayout(ChuaAnhNhanVien);
        ChuaAnhNhanVien.setLayout(ChuaAnhNhanVienLayout);
        ChuaAnhNhanVienLayout.setHorizontalGroup(
            ChuaAnhNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChuaAnhNhanVienLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(anhnhanvien))
        );
        ChuaAnhNhanVienLayout.setVerticalGroup(
            ChuaAnhNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(anhnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.add(ChuaAnhNhanVien);
        ChuaAnhNhanVien.setBounds(1050, 90, 170, 170);

        CBox_TieuChiTimNV.setModel(new javax.swing.DefaultComboBoxModel<>(
            new String[] {
                "Tiêu chí tìm",
                "Mã nhân viên",
                "Tên nhân viên",
                "Chức vụ"
            }

        ));
        CBox_TieuChiTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBox_TieuChiTimNVActionPerformed(evt);
            }
        });
        CBox_TieuChiTimNV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CBox_TieuChiTimNVPropertyChange(evt);
            }
        });
        jPanel1.add(CBox_TieuChiTimNV);
        CBox_TieuChiTimNV.setBounds(720, 300, 110, 40);

        ChucVu.setModel(new javax.swing.DefaultComboBoxModel<>());
        ChucVu.setVisible(false);
        ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChucVuActionPerformed(evt);
            }
        });
        jPanel1.add(ChucVu);
        ChucVu.setBounds(840, 300, 180, 40);

        jPanel1.add(txt_Ma_va_Ten);
        txt_Ma_va_Ten.setBounds(840, 300, 180, 40);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1283, 803);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1288, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 842, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_ChucVuActionPerformed
    
    
    private void btn_ThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMouseEntered
        // TODO add your handling code here:
        btn_Them.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Them.setBorder(null);

    }//GEN-LAST:event_btn_ThemMouseEntered

    private void btn_ThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMouseExited
        // TODO add your handling code here:
        btn_Them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Them.setBorder(null);
    }//GEN-LAST:event_btn_ThemMouseExited

    private void txt_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SDTActionPerformed

    private void CBox_TieuChiTimNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBox_TieuChiTimNVActionPerformed
        // TODO add your handling code here:
        String tieuChiTim = CBox_TieuChiTimNV.getSelectedItem().toString();
        System.out.println(tieuChiTim);
        if(tieuChiTim.equals("Chức vụ")) {
            ChucVu.setVisible(true);
            txt_Ma_va_Ten.setVisible(false);
        } else {
            ChucVu.setVisible(false);
            txt_Ma_va_Ten.setVisible(true);
        }
    }//GEN-LAST:event_CBox_TieuChiTimNVActionPerformed

    private void ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChucVuActionPerformed

    private void CBox_TieuChiTimNVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CBox_TieuChiTimNVPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_CBox_TieuChiTimNVPropertyChange
    static DefaultTableModel duaDuLieuVaoTable(List<NhanVien> list_NhanVien) {
        String[] header = new String [] {
        "Mã nhân viên", "CCCD/Passport", "Tên nhân viên", "Số điện thoại", "Địa chỉ", "Chức vụ"
        };
        Object[][] object = new Object[list_NhanVien.size()][];
        for(int i = 0; i < list_NhanVien.size(); i++) {
            NhanVien x = list_NhanVien.get(i);
            Object[] t = new Object[] {
                x.getMaNhanVien(),
                x.getCCCD(),
                x.getTenNhanVien(),
                x.getSoDienThoai(),
                x.getDiaChi(),
                x.getChucVu()
            };
            object[i] = t;
        }
        return new DefaultTableModel(object, header);
    }
    
    static NhanVien KiemTraThongTinNhap(String x) throws Exception{
        
        
        return new NhanVien(PROPERTIES);
    }
    
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        
        if(row != -1) {
            String CCCD = jTable1.getModel().getValueAt(row, 1).toString();
            
            NhanVien x = nhanVienDAO.timTheoCCCD(CCCD).getFirst();
            
            txt_CCCD.setText(x.getCCCD());
            txt_HoTen.setText(x.getTenNhanVien());
            System.out.println(x.getSoDienThoai());
            txt_SDT.setText(x.getSoDienThoai());
            txt_DC.setText(x.getDiaChi());
            cb_ChucVu.setSelectedItem(x.getChucVu());
            
            anhnhanvien.setIcon(new ImageScale().getScaledImage1(anhnhanvien.getWidth(), anhnhanvien.getHeight(), new ImageIcon(x.getAnhDaiDien())));
        }
        
    }//GEN-LAST:event_jTable1MouseClicked
        
    
    private void btn_ThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMouseClicked
        // TODO add your handling code here:
        try {
            if(txt_CCCD.getText().equals("")) {
                throw new Exception("Chưa nhập CCCD / Passport.");
            }
            if(txt_HoTen.getText().equals("")) {
                throw new Exception("Chưa nhập họ tên.");
            }
            if(txt_SDT.getText().equals("")) {
                throw new Exception("Chưa nhập số điện thoại.");
            }
            if(txt_DC.getText().equals("")) {
                throw new Exception("Chưa nhập địa chỉ");
            }
            if(hinhAnh == null) {
                throw new Exception("Chưa chọn hình ảnh");
            }
            
            int maNhanVien = nhanVienDAO.getAllNhanVien().getLast().getMaNhanVien()  + 1;
            String HoTen = txt_HoTen.getText();
            byte[] anhDaiDien = hinhAnh;
            String SDT = txt_SDT.getText();
            String CCCD = txt_CCCD.getText();
            String DC = txt_DC.getText();
            String ChucVu = cb_ChucVu.getSelectedItem().toString();
            
            if(!nhanVienDAO.timTheoCCCD(CCCD).isEmpty()) {
                throw new Exception("CCCD không được trùng");
            }
            
            
            
            NhanVien x = new NhanVien(maNhanVien, HoTen, anhDaiDien, SDT, CCCD, DC, ChucVu);
            
            nhanVienDAO.createNhanVien(x);
            jTable1.setModel(duaDuLieuVaoTable(nhanVienDAO.getAllNhanVien()));
            
            txt_CCCD.setText("");
            txt_HoTen.setText("");
            txt_DC.setText("");
            txt_SDT.setText("");
            hinhAnh = null;
            
            JOptionPane.showMessageDialog(this, "Đã thêm thành công!", "Thêm dữ liệu", JOptionPane.INFORMATION_MESSAGE);
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Chưa nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemMouseClicked

    private void btn_ChonhinhanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ChonhinhanhMouseClicked
        // TODO add your handling code here:
        JFileChooser frame_chonAnh = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File ảnh", "png", "jpg", "jpeg", "gif");
        frame_chonAnh.setFileFilter(filter);
        frame_chonAnh.setAcceptAllFileFilterUsed(false);
        
        int returnValue = frame_chonAnh.showOpenDialog(null);
        
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = frame_chonAnh.getSelectedFile().getPath();
            
            ImageIcon icon = new ImageScale().load(filePath, anhnhanvien.getWidth(), anhnhanvien.getHeight());
            
            try {
                hinhAnh = new convertImage().convertImageToBinary(filePath);
            } catch (IOException ex) {
                Logger.getLogger(NhanVien_TienNghi_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            anhnhanvien.setIcon(icon);
        }
    }//GEN-LAST:event_btn_ChonhinhanhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JComboBox<String> CBox_TieuChiTimNV;
    private javax.swing.JPanel ChuaAnhNhanVien;
    private javax.swing.JComboBox<String> ChucVu;
    private javax.swing.JPanel ThongTinNhanVien;
    private javax.swing.JLabel anhnhanvien;
    private keeptoo.KGradientPanel btn_Chonhinhanh;
    private keeptoo.KGradientPanel btn_Lammoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Them;
    private keeptoo.KGradientPanel btn_Xoa;
    private javax.swing.JComboBox<String> cb_ChucVu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_DC;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_Ma_va_Ten;
    private javax.swing.JTextField txt_SDT;
    // End of variables declaration//GEN-END:variables
}
