/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.NhanVienDAO;
import model.DTO.NhanVien;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class QuanLy_TaiKhoan_GUI extends javax.swing.JInternalFrame {

    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private MongoDBConnection database = new MongoDBConnection();
    private List<NhanVien> list_NhanVien = new ArrayList<>();
    private NhanVienDAO nhanVien_dao = new NhanVienDAO(database.getDatabase());
    DefaultTableCellRenderer centerRenderer;
    DefaultTableModel model;

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public QuanLy_TaiKhoan_GUI() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

//        Căn giữa table
        JTableHeader header = Table_TaiKhoan.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);
        //Căn giữa các phần tử trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

        model = (DefaultTableModel) Table_TaiKhoan.getModel();
        model.setRowCount(0);

//        Đọc dữ liệu từ database lên
        list_NhanVien = nhanVien_dao.getAllNhanVien();
        DocDataLenTable(list_NhanVien);

        cb_NhanVien.addItem("");
        for (NhanVien nv : list_NhanVien) {
            cb_NhanVien.addItem(nv.getTenNhanVien());
        }

        list_btn.add(btn_them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_LamMoi);

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
    }

    public void LamMoi() {
        cb_ChucVu.setSelectedItem("");
        cb_NhanVien.setSelectedItem("");
        txt_TenDangNhap.setText("");
        txt_MatKhau.setText("");
        Table_TaiKhoan.clearSelection();
        cb_ChucVu.requestFocus();
        cb_NhanVien.setEnabled(true);
    }

    public void DocDataLenTable(List<NhanVien> list_NhanViens) {
        model.setRowCount(0);
        for (NhanVien nv : list_NhanViens) {
            model.addRow(new Object[]{
                nv.getMaNhanVien(),
                nv.getTenNhanVien(),
                nv.getTenTaiKhoan(),
                nv.getMatKhau(),
                nv.getChucVu()
            });
        }

        for (int i = 0; i < Table_TaiKhoan.getColumnCount(); i++) {
            Table_TaiKhoan.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
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
        ThongTinDat = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_ChucVu = new javax.swing.JComboBox<>();
        cb_NhanVien = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txt_TenDangNhap = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_MatKhau = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_them = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_TaiKhoan = new javax.swing.JTable();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel23 = new javax.swing.JLabel();
        Backgroup = new javax.swing.JLabel();

        setName("page_TaiKhoan"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinDat.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        ThongTinDat.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chức vụ");

        cb_ChucVu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_ChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Nhân viên", "Lễ tân", "Quản lý" }));
        cb_ChucVu.setPreferredSize(new java.awt.Dimension(72, 35));

        cb_NhanVien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_NhanVien.setPreferredSize(new java.awt.Dimension(72, 35));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Nhân Viên");

        txt_TenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_TenDangNhap.setPreferredSize(new java.awt.Dimension(64, 35));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên đăng nhập");

        txt_MatKhau.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_MatKhau.setPreferredSize(new java.awt.Dimension(64, 35));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Mật khẩu");

        javax.swing.GroupLayout ThongTinDatLayout = new javax.swing.GroupLayout(ThongTinDat);
        ThongTinDat.setLayout(ThongTinDatLayout);
        ThongTinDatLayout.setHorizontalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(cb_ChucVu, 0, 240, Short.MAX_VALUE)
                    .addComponent(txt_TenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        ThongTinDatLayout.setVerticalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_TenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_MatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat);
        ThongTinDat.setBounds(80, 90, 700, 210);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Thông tin khách hàng");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel14.setName(""); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(80, 50, 250, 32);

        btn_them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_them.setkGradientFocus(250);
        btn_them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_themMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Thêm");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_themLayout = new javax.swing.GroupLayout(btn_them);
        btn_them.setLayout(btn_themLayout);
        btn_themLayout.setHorizontalGroup(
            btn_themLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_themLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_themLayout.setVerticalGroup(
            btn_themLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_themLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_them);
        btn_them.setBounds(860, 220, 140, 40);

        btn_Xoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Xoa.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Xoa.setkGradientFocus(250);
        btn_Xoa.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_XoaMousePressed(evt);
            }
        });

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
        btn_Xoa.setBounds(1060, 220, 140, 40);

        btn_Sua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Sua.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Sua.setkGradientFocus(250);
        btn_Sua.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Sua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_SuaMousePressed(evt);
            }
        });

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
        btn_Sua.setBounds(860, 140, 140, 40);

        Table_TaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_TaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Tên tài khoản", "Mật khẩu", "Chức vụ"
            }
        ));
        Table_TaiKhoan.setRowHeight(30);
        Table_TaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_TaiKhoanMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Table_TaiKhoan);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 330, 1120, 360);

        btn_LamMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LamMoiMousePressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Làm mới");
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(1060, 140, 140, 40);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1283, 803);

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
            .addGap(0, 803, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themMousePressed
        // TODO add your handling code here:
        NhanVien nv = nhanVien_dao.timTheoTenNhanVien(cb_NhanVien.getSelectedItem().toString());
        if (nv.getTenTaiKhoan() != null) {
            JOptionPane.showMessageDialog(this, "Nhân viên đã có tài khoản, vui lòng chọn nhân viên khác");
            return;
        }

        if (cb_ChucVu.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống Chức vụ");
            cb_ChucVu.requestFocus();
            return;
        }
        if (cb_NhanVien.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống Nhân viên");
            cb_NhanVien.requestFocus();
            return;
        }

        if (txt_TenDangNhap.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên đăng nhập");
            txt_TenDangNhap.requestFocus();
            return;
        }
        
        if(nhanVien_dao.checkAccountTheoTen(txt_TenDangNhap.getText()) != null){
            JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
            txt_TenDangNhap.requestFocus();
            return;
        }
        if (txt_MatKhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống mật khẩu");
            txt_TenDangNhap.requestFocus();
            return;
        }

        nv.setTenTaiKhoan(txt_TenDangNhap.getText());
        nv.setMatKhau(txt_MatKhau.getText());

        if (nhanVien_dao.updateNhanVien(nv)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            LamMoi();
        }

        list_NhanVien.clear();
        list_NhanVien = nhanVien_dao.getAllNhanVien();
        DocDataLenTable(list_NhanVien);


    }//GEN-LAST:event_btn_themMousePressed

    private void Table_TaiKhoanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_TaiKhoanMousePressed
        // TODO add your handling code here:
        int row = Table_TaiKhoan.getSelectedRow();
        NhanVien nv = nhanVien_dao.timTheoMaNhanVien(Integer.parseInt(model.getValueAt(row, 0).toString()));
        cb_ChucVu.setSelectedItem(nv.getChucVu());
        cb_NhanVien.setSelectedItem(nv.getTenNhanVien());
        txt_TenDangNhap.setText(nv.getTenTaiKhoan());
        txt_MatKhau.setText(nv.getMatKhau());
        cb_NhanVien.setEnabled(false);

    }//GEN-LAST:event_Table_TaiKhoanMousePressed

    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_btn_LamMoiMousePressed

    private void btn_SuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMousePressed
        // TODO add your handling code here:
        int row = Table_TaiKhoan.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa");
            return;
        }

        if (cb_ChucVu.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống Chức vụ");
            cb_ChucVu.requestFocus();
            return;
        }

        if (txt_TenDangNhap.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên đăng nhập");
            txt_TenDangNhap.requestFocus();
            return;
        }
        if(nhanVien_dao.checkAccountTheoTen(txt_TenDangNhap.getText()) != null){
            JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
            txt_TenDangNhap.requestFocus();
            return;
        }
        if (txt_MatKhau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống mật khẩu");
            txt_TenDangNhap.requestFocus();
            return;
        }

        NhanVien nv = nhanVien_dao.timTheoMaNhanVien(Integer.parseInt(model.getValueAt(row, 0).toString()));
        nv.setChucVu(cb_ChucVu.getSelectedItem().toString());
        nv.setTenTaiKhoan(txt_TenDangNhap.getText());
        nv.setMatKhau(txt_MatKhau.getText());
        if (nhanVien_dao.updateNhanVien(nv)) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            LamMoi();
        }
        list_NhanVien.clear();
        list_NhanVien = nhanVien_dao.getAllNhanVien();
        DocDataLenTable(list_NhanVien);
    }//GEN-LAST:event_btn_SuaMousePressed

    private void btn_XoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMousePressed
        // TODO add your handling code here:
        int row = Table_TaiKhoan.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa");
            return;
        }
        
        if(JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?","Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION){
            return;
        }
        NhanVien nv = nhanVien_dao.timTheoMaNhanVien(Integer.parseInt(model.getValueAt(row, 0).toString()));
        nv.setTenTaiKhoan(null);
        nv.setMatKhau(null);
        nhanVien_dao.updateNhanVien(nv);
        list_NhanVien.clear();
        list_NhanVien = nhanVien_dao.getAllNhanVien();
        DocDataLenTable(list_NhanVien);
    }//GEN-LAST:event_btn_XoaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JTable Table_TaiKhoan;
    private javax.swing.JPanel ThongTinDat;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Xoa;
    private keeptoo.KGradientPanel btn_them;
    private javax.swing.JComboBox<String> cb_ChucVu;
    private javax.swing.JComboBox<String> cb_NhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_MatKhau;
    private javax.swing.JTextField txt_TenDangNhap;
    // End of variables declaration//GEN-END:variables
}
