/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import static GUI.NhanVien_TienNghi_GUI.area_moTa;
import com.mongodb.client.MongoDatabase;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DichVuDAO;
import model.DAO.TienNghiDAO;
import model.DTO.DichVu;
import model.DTO.TienNghi;
import model.MongoDBConnection;
import test.convertImage;

/**
 *
 * @author Admin
 */
public class NhanVien_DichVu_GUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    DecimalFormat df = new DecimalFormat("#,##0");
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private DefaultTableModel model;
    private DefaultTableCellRenderer centerRenderer;
    private DichVuDAO dichVuDAO = new DichVuDAO(database);
    List<DichVu> list_dv = dichVuDAO.getAllDichVu();

    private byte[] hinhAnh = null;
//    private String filePath = "";

    public NhanVien_DichVu_GUI() {
        initComponents();

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
//      Set font cho header_tiennghi
        JTableHeader header_tn = table_DichVu.getTableHeader();
        header_tn.setPreferredSize(new Dimension(header_tn.getPreferredSize().width, 30));
        header_tn.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table tiện nghi
        DefaultTableCellRenderer renderer_tn = (DefaultTableCellRenderer) header_tn.getDefaultRenderer();
        renderer_tn.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header tiennghi
        header_tn.setDefaultRenderer(renderer_tn);

        model = (DefaultTableModel) table_DichVu.getModel();
        model.setRowCount(0);
        list_dv = dichVuDAO.getAllDichVu();
        DocDuLieuLenTableDichVu(list_dv);

        list_btn.add(btn_them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_lammoi);
        list_btn.add(btn_Tim);
        list_btn.add(btn_chonAnh);

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

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
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
        ThongTinDichVu = new javax.swing.JPanel();
        tenDichVu = new javax.swing.JLabel();
        txt_DichVu = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        donGia = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_lammoi = new keeptoo.KGradientPanel();
        jLabel21 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_them = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_chonAnh = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        label_Anh = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        area_moTa = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_DichVu = new javax.swing.JTable();
        Backgroup = new javax.swing.JLabel();

        setName("page_DichVu"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));
        getContentPane().setLayout(null);

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinDichVu.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDichVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinDichVu.setOpaque(false);

        tenDichVu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tenDichVu.setForeground(new java.awt.Color(255, 255, 255));
        tenDichVu.setText("Tên dịch vụ");

        txt_DichVu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_DichVu.setPreferredSize(new java.awt.Dimension(64, 32));

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtDonGia.setPreferredSize(new java.awt.Dimension(64, 32));
        txtDonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDonGiaActionPerformed(evt);
            }
        });

        donGia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        donGia.setForeground(new java.awt.Color(255, 255, 255));
        donGia.setText("Đơn giá");

        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("*");

        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setText("*");

        javax.swing.GroupLayout ThongTinDichVuLayout = new javax.swing.GroupLayout(ThongTinDichVu);
        ThongTinDichVu.setLayout(ThongTinDichVuLayout);
        ThongTinDichVuLayout.setHorizontalGroup(
            ThongTinDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDichVuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(ThongTinDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDichVuLayout.createSequentialGroup()
                        .addComponent(tenDichVu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(ThongTinDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDichVuLayout.createSequentialGroup()
                        .addComponent(donGia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(204, 204, 204))
        );
        ThongTinDichVuLayout.setVerticalGroup(
            ThongTinDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinDichVuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ThongTinDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ThongTinDichVuLayout.createSequentialGroup()
                        .addGroup(ThongTinDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(donGia)
                            .addComponent(jLabel27))
                        .addGap(5, 5, 5)
                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinDichVuLayout.createSequentialGroup()
                        .addGroup(ThongTinDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tenDichVu)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );

        jPanel1.add(ThongTinDichVu);
        ThongTinDichVu.setBounds(70, 100, 510, 120);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Thông tin dịch vụ");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel14);
        jLabel14.setBounds(70, 50, 250, 32);

        btn_lammoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_lammoi.setkGradientFocus(250);
        btn_lammoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_lammoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_lammoiMousePressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Làm Mới");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_lammoiLayout = new javax.swing.GroupLayout(btn_lammoi);
        btn_lammoi.setLayout(btn_lammoiLayout);
        btn_lammoiLayout.setHorizontalGroup(
            btn_lammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_lammoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_lammoiLayout.setVerticalGroup(
            btn_lammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_lammoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_lammoi);
        btn_lammoi.setBounds(620, 260, 140, 40);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_TimMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Tìm");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_TimLayout = new javax.swing.GroupLayout(btn_Tim);
        btn_Tim.setLayout(btn_TimLayout);
        btn_TimLayout.setHorizontalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(790, 260, 140, 40);

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
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        btn_themLayout.setVerticalGroup(
            btn_themLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_themLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_them);
        btn_them.setBounds(70, 260, 140, 40);

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
        btn_Xoa.setBounds(250, 260, 140, 40);

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
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        btn_SuaLayout.setVerticalGroup(
            btn_SuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Sua);
        btn_Sua.setBounds(440, 260, 140, 40);

        btn_chonAnh.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_chonAnh.setkGradientFocus(250);
        btn_chonAnh.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_chonAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_chonAnhMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Chọn ảnh");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_chonAnhLayout = new javax.swing.GroupLayout(btn_chonAnh);
        btn_chonAnh.setLayout(btn_chonAnhLayout);
        btn_chonAnhLayout.setHorizontalGroup(
            btn_chonAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_chonAnhLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        btn_chonAnhLayout.setVerticalGroup(
            btn_chonAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.add(btn_chonAnh);
        btn_chonAnh.setBounds(980, 260, 210, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));

        label_Anh.setBackground(new java.awt.Color(255, 255, 51));
        label_Anh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_AnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(label_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(label_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(980, 100, 210, 120);

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setInheritsPopupMenu(true);
        jScrollPane2.setOpaque(false);

        area_moTa.setBackground(new java.awt.Color(0, 0, 0));
        area_moTa.setColumns(1);
        area_moTa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        area_moTa.setForeground(new java.awt.Color(255, 255, 255));
        area_moTa.setLineWrap(true);
        area_moTa.setRows(3);
        area_moTa.setText("Mô Tả");
        area_moTa.setWrapStyleWord(true);
        area_moTa.setCaretColor(new java.awt.Color(255, 255, 255));
        area_moTa.setMargin(new java.awt.Insets(4, 6, 2, 6));
        area_moTa.setMaximumSize(new java.awt.Dimension(69, 72));
        area_moTa.setOpaque(false);
        area_moTa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                area_moTaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                area_moTaFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(area_moTa);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(620, 100, 310, 120);

        table_DichVu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        table_DichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Dịch Vụ", "Tên Dịch Vụ", "Mô Tả", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_DichVu.setRowHeight(30);
        table_DichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_DichVuMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_DichVu);
        if (table_DichVu.getColumnModel().getColumnCount() > 0) {
            table_DichVu.getColumnModel().getColumn(0).setPreferredWidth(130);
            table_DichVu.getColumnModel().getColumn(0).setMaxWidth(130);
            table_DichVu.getColumnModel().getColumn(1).setPreferredWidth(170);
            table_DichVu.getColumnModel().getColumn(1).setMaxWidth(170);
            table_DichVu.getColumnModel().getColumn(3).setPreferredWidth(200);
            table_DichVu.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(70, 330, 1120, 396);

        Backgroup.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(-7, 0, 1300, 803);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1283, 803);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    static DefaultTableModel duaDataVaoModel(List<DichVu> list_DichVu) {
//        
//        
//        Object[][] object = new Object[list_DichVu.size()][4];
//        for(int i = 0; i < object.length; i++) {
//            DichVu x = list_DichVu.get(i);
//            object[i] = new Object[]{
//                x.getMaDV(), x.getTenDV(), x.getMoTa(), x.getDonGia()
//            };
////            System.out.println(object[i][0]);
////            System.out.println(object[i][1]);
////            System.out.println(object[i][2]);
////            System.out.println(object[i][3]);
//        }
//        String[] table_header = new String [] {
//                "Mã dịch vụ", "Tên dịch vụ", "Mô tả", "Đơn giá"
//        };
//        
//        return new DefaultTableModel(object, table_header);
//        
//    }
    public void DocDuLieuLenTableDichVu(List<DichVu> list_dv) {
        model.setRowCount(0);

        for (DichVu dv : list_dv) {
            model.addRow(new Object[]{
                dv.getMaDV(),
                dv.getTenDV(),
                dv.getMoTa(),
                df.format(dv.getDonGia()) + " VND"});
        }
        for (int i = 0; i < table_DichVu.getColumnCount(); i++) {
            table_DichVu.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }


    private void txtDonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDonGiaActionPerformed

    private void btn_TimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMouseClicked
        // TODO add your handling code here:
//        String tenDV = txt_DichVu.getText();
//        DichVu x = dichVuDAO.timDichVu(tenDV);
//        jTable1.setRowSelectionInterval(WIDTH, WIDTH);
        String tenDV = txt_DichVu.getText();
        if (tenDV.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên dịch vụ cần tìm", "Chưa nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
        }

        DichVu x = dichVuDAO.timDichVu(tenDV);

        int viTriCuaX = dichVuDAO.getAllDichVu().indexOf(x);

        if (viTriCuaX != -1) {
            table_DichVu.setRowSelectionInterval(viTriCuaX, viTriCuaX);

            hinhAnh = x.getHinhAnh();
            area_moTa.setText(x.getMoTa());
            txtDonGia.setText(String.format("%d", x.getDonGia()));
            label_Anh.setIcon(new ImageScale().load1(new ImageIcon(hinhAnh), label_Anh.getWidth(), label_Anh.getHeight()));
        } else {
            JOptionPane.showMessageDialog(this, "Không có dịch vụ có tên " + tenDV, "Không tìm thấy dữ liệu", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btn_TimMouseClicked

    private void label_AnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_AnhMouseClicked
        // TODO add your handling code here:
        System.out.println(label_Anh.getIcon());
    }//GEN-LAST:event_label_AnhMouseClicked

    private void area_moTaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_moTaFocusGained
        // TODO add your handling code here:
        if (area_moTa.getText().equals("Mô Tả")) {
            area_moTa.setText("");
        }
    }//GEN-LAST:event_area_moTaFocusGained

    private void area_moTaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_moTaFocusLost
        // TODO add your handling code here:
        if (area_moTa.getText().equals("")) {
            area_moTa.setText("Mô Tả");
        }
    }//GEN-LAST:event_area_moTaFocusLost

    private void btn_chonAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_chonAnhMousePressed
        // TODO add your handling code here:
        JFileChooser frame_chonAnh = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File ảnh", "png", "jpg", "jpeg", "gif");
        frame_chonAnh.setFileFilter(filter);
        frame_chonAnh.setAcceptAllFileFilterUsed(false);

        int returnValue = frame_chonAnh.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            //Đường dẫn của file
            String filePath = frame_chonAnh.getSelectedFile().getPath();
            ImageIcon icon = new ImageScale().load(filePath, label_Anh.getWidth(), label_Anh.getHeight());

            try {
                hinhAnh = new convertImage().convertImageToBinary(filePath);
            } catch (IOException ex) {
                Logger.getLogger(NhanVien_DichVu_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            label_Anh.setIcon(icon);

//            txt_DichVu.setEnabled(false);
        }
    }//GEN-LAST:event_btn_chonAnhMousePressed

    private void table_DichVuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_DichVuMousePressed
        // TODO add your handling code here:
        int row = table_DichVu.getSelectedRow();

        if (row != -1) {
            String tenDV = model.getValueAt(row, 1).toString();

            DichVu x = dichVuDAO.timDichVu(tenDV);
//            System.out.println(x.toString());
//            System.out.println(x.getHinhAnh());
            txt_DichVu.setText(x.getTenDV());
            area_moTa.setText(x.getMoTa());
            txtDonGia.setText(Integer.toString(x.getDonGia()));
            hinhAnh = x.getHinhAnh();
            ImageIcon icon = new ImageScale().load1(new ImageIcon(hinhAnh), label_Anh.getWidth(), label_Anh.getHeight());

            System.out.println("Không gặp lỗi");
            label_Anh.setIcon(icon);

//            txt_DichVu.setEnabled(false);
        }
    }//GEN-LAST:event_table_DichVuMousePressed

    private void btn_themMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themMousePressed
        // TODO add your handling code here:
        if (txt_DichVu.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập tên dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Nhập tên dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập tên dịch vụ");
            txt_DichVu.requestFocus();
            return;
        }

        if (txtDonGia.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Chưa nhập đơn giá dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập đơn giá dịch vụ");
            txtDonGia.requestFocus();
            return;
        }

        String regex = "\\d+";
        if (!txtDonGia.getText().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            txtDonGia.setText("");
            txtDonGia.requestFocus();
            return;
        }

        if (area_moTa.getText().equals("Mô Tả")) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập mô tả dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Nhập mô tả dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập mô tả dịch vụ");
            area_moTa.requestFocus();
            return;
        }

        if (hinhAnh == null) {
//                throw new Exception("Chưa chọn ảnh dịch vụ!");
            JOptionPane.showMessageDialog(this, "Chọn hình ảnh dịch vụ");
            return;
        }
//            if(txtHinhAnh.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa chọn hình ảnh dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Chưa nhập đơn giá dịch vụ!");
//            }
        String tenDichVu = txt_DichVu.getText();

        if (dichVuDAO.timDichVu(tenDichVu) != null) {
//                JOptionPane.showMessageDialog(this, "Đã có dịch vụ có tên " + tenDichVu, "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
//            throw new ExceptionInInitializerError("Trùng dữ liệu");
            JOptionPane.showMessageDialog(this, "Trùng tên dịch vụ");
            return;
        }

        int maDichVu = dichVuDAO.getAllDichVu().getLast().getMaDV() + 1;
        String moTaDichVu = area_moTa.getText();
        int donGia = Integer.parseInt(txtDonGia.getText());

        byte[] img = hinhAnh;
        for (byte x : hinhAnh) {
            System.out.print(x);
        }
        //Tạo ra một dịch vụ mới
        DichVu x = new DichVu(maDichVu, tenDichVu, moTaDichVu, donGia, hinhAnh);
        //Đưa dữ liệu vào database
        dichVuDAO.createDichVu(x);

        txt_DichVu.setText("");
        area_moTa.setText("");
//            hinhAnh = null;
        label_Anh.setIcon(null);
        txt_DichVu.setEnabled(true);
        JOptionPane.showMessageDialog(this, "Thêm thành công");
        list_dv = dichVuDAO.getAllDichVu();
        DocDuLieuLenTableDichVu(list_dv);
        LamMoi();
    }//GEN-LAST:event_btn_themMousePressed

    private void btn_XoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMousePressed
        // TODO add your handling code here:
        int row = table_DichVu.getSelectedRow();

        if (row != -1) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa dịch vụ?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//            int maDV = Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString());
                String tenDV = model.getValueAt(row, 1).toString();
//            String moTa = jTable1.getModel().getValueAt(row, 2).toString();
//            int donGia = Integer.parseInt(jTable1.getModel().getValueAt(row, 3).toString());

                DichVu x = dichVuDAO.timDichVu(tenDV);

                dichVuDAO.xoaDichVu(x);
                list_dv = dichVuDAO.getAllDichVu();
                DocDuLieuLenTableDichVu(list_dv);
                LamMoi();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa");
            return;
        }
    }//GEN-LAST:event_btn_XoaMousePressed

    private void btn_lammoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lammoiMousePressed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_btn_lammoiMousePressed

    private void btn_SuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMousePressed
        // TODO add your handling code here:
        if (table_DichVu.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        if (txt_DichVu.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập tên dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Nhập tên dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập tên dịch vụ");
            txt_DichVu.requestFocus();
            return;
        }

        if (txtDonGia.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Chưa nhập đơn giá dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập đơn giá dịch vụ");
            txtDonGia.requestFocus();
            return;
        }

        String regex = "\\d+";
        if (!txtDonGia.getText().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            txtDonGia.setText("");
            txtDonGia.requestFocus();
            return;
        }

        if (area_moTa.getText().equals("Mô Tả")) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập mô tả dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Nhập mô tả dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập mô tả dịch vụ");
            area_moTa.requestFocus();
            return;
        }

        if (hinhAnh == null) {
//                throw new Exception("Chưa chọn ảnh dịch vụ!");
            JOptionPane.showMessageDialog(this, "Chọn hình ảnh dịch vụ");
            return;
        }
        String tenDichVu = model.getValueAt(table_DichVu.getSelectedRow(), 1).toString();
        DichVu x = dichVuDAO.timDichVu(tenDichVu);

        System.out.println(x.toString());
        tenDichVu = txt_DichVu.getText();
        int maDV = x.getMaDV();
        String moTaDV = area_moTa.getText();
        int donGia = Integer.parseInt(txtDonGia.getText());
        byte[] hinhAnh = this.hinhAnh;

        System.out.println("Trên label" + String.format("%d %s %d", maDV, moTaDV, donGia));
        DichVu y = new DichVu(maDV, tenDichVu, moTaDV, donGia, hinhAnh);

        dichVuDAO.suaDichVu(x, y);
//                System.out.println(y.toString());

//                txt_DichVu.setEnabled(true);
        JOptionPane.showMessageDialog(this, "Sửa thành công");
        list_dv = dichVuDAO.getAllDichVu();
        DocDuLieuLenTableDichVu(list_dv);
        LamMoi();


    }//GEN-LAST:event_btn_SuaMousePressed

    public void LamMoi() {
        txt_DichVu.setText("");
        txt_DichVu.requestFocus();
        txtDonGia.setText("");
        hinhAnh = null;
        area_moTa.setText("Mô Tả");
        label_Anh.setIcon(null);
        DocDuLieuLenTableDichVu(list_dv);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel ThongTinDichVu;
    public static javax.swing.JTextArea area_moTa;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private keeptoo.KGradientPanel btn_chonAnh;
    private keeptoo.KGradientPanel btn_lammoi;
    private keeptoo.KGradientPanel btn_them;
    private javax.swing.JLabel donGia;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_Anh;
    private javax.swing.JTable table_DichVu;
    private javax.swing.JLabel tenDichVu;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txt_DichVu;
    // End of variables declaration//GEN-END:variables
}
