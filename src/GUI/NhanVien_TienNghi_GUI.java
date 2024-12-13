/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DonDatPhongDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DAO.TienNghiDAO;
import model.DTO.DonDatPhong;
import model.DTO.LoaiPhong;
import model.DTO.NhanVien;
import model.DTO.Phong;
import model.DTO.TienNghi;
import test.convertImage;

/**
 *
 * @author Admin
 */
public class NhanVien_TienNghi_GUI extends javax.swing.JInternalFrame {

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    private DefaultTableModel model;
    private List<TienNghi> list_TienNghi = new ArrayList<>();
    private TienNghiDAO tienNghiDAO = new TienNghiDAO(database);
    private List<LoaiPhong> list_LoaiPhong = new ArrayList<>();
    private LoaiPhongDAO loaiphongDAO = new LoaiPhongDAO(database);
    private List<DonDatPhong> list_DonDatPhong = new ArrayList<>();
    private DonDatPhongDAO dondatphong_dao = new DonDatPhongDAO(database);
    private List<Phong> list_Phong = new ArrayList<Phong>();
    private PhongDAO phong_dao = new PhongDAO(database);
    private byte[] hinhAnh = null;
    private DefaultTableCellRenderer centerRenderer;

    private NhanVien nhanVien_DangSuDung;

    public NhanVien_TienNghi_GUI(NhanVien nhanVien_DangSuDung) {
        this.nhanVien_DangSuDung = nhanVien_DangSuDung;
        initComponents();
        jLabel1.setText(this.nhanVien_DangSuDung.getTenNhanVien());
        ImageScale.setCircularImage(label_Avatar, new ImageScale().getScaledImage1(50, 50, new ImageIcon(nhanVien_DangSuDung.getAnhDaiDien())));

        table_TienNghi.setSelectionBackground(new Color(255, 222, 89));
        table_TienNghi.setSelectionForeground(new Color(0, 0, 0));
        table_TienNghi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(80);

        table_TienNghi.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                // Kiểm tra nếu không còn sự kiện đang thay đổi
                int row = table_TienNghi.getSelectedRow();

                if (row != -1) {
                    String tenTienNghi = table_TienNghi.getModel().getValueAt(row, 1).toString();

                    TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
//            System.out.println(x.toString());

                    //            System.out.println(x.getHinhAnh());
                    txt_tienNghi.setText(x.getTenTienNghi());
                    area_moTa.setText(x.getMoTa());

                    hinhAnh = x.getHinhAnh();
                    System.out.println(hinhAnh == null);
                    ImageIcon icon = new ImageScale().load1(new ImageIcon(hinhAnh), label_Anh.getWidth(), label_Anh.getHeight());

//            System.out.println("Không gặp lỗi");
                    label_Anh.setIcon(icon);

                    //            txt_DichVu.setEnabled(false);
                }
            }
        });

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
//      Set font cho header_tiennghi
        JTableHeader header_tn = table_TienNghi.getTableHeader();
        header_tn.setPreferredSize(new Dimension(header_tn.getPreferredSize().width, 30));
        header_tn.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table tiện nghi
        DefaultTableCellRenderer renderer_tn = (DefaultTableCellRenderer) header_tn.getDefaultRenderer();
        renderer_tn.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header tiennghi
        header_tn.setDefaultRenderer(renderer_tn);

        List<TienNghi> list_tn = tienNghiDAO.getAllTienNghi();
        model = (DefaultTableModel) table_TienNghi.getModel();
        model.setRowCount(0);
        DocDuLieuLenTableTienNghi(list_tn);

        ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
        list_btn.add(btn_them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_lammoi);
        list_btn.add(btn_Tim);
        list_btn.add(chonAnh_btn);

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
        jLabel2 = new javax.swing.JLabel();
        ThongTinTienNghi = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_tienNghi = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btn_lammoi = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_them = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        label_Anh = new javax.swing.JLabel();
        chonAnh_btn = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_TienNghi = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        area_moTa = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        circlePanel1 = new GUI.CirclePanel_Atatar();
        label_Avatar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Backgroup = new javax.swing.JLabel();

        setName("page_TienNghi"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 209, 84));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Thông tin tiện nghi");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 60, 250, 32);

        ThongTinTienNghi.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinTienNghi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinTienNghi.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên tiện nghi");

        txt_tienNghi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tienNghi.setPreferredSize(new java.awt.Dimension(80, 32));
        txt_tienNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienNghiActionPerformed(evt);
            }
        });

        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("*");

        javax.swing.GroupLayout ThongTinTienNghiLayout = new javax.swing.GroupLayout(ThongTinTienNghi);
        ThongTinTienNghi.setLayout(ThongTinTienNghiLayout);
        ThongTinTienNghiLayout.setHorizontalGroup(
            ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinTienNghiLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinTienNghiLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_tienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        ThongTinTienNghiLayout.setVerticalGroup(
            ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinTienNghiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_tienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinTienNghi);
        ThongTinTienNghi.setBounds(80, 100, 490, 120);

        btn_lammoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_lammoi.setkGradientFocus(250);
        btn_lammoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_lammoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_lammoiMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Làm Mới");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_lammoiLayout = new javax.swing.GroupLayout(btn_lammoi);
        btn_lammoi.setLayout(btn_lammoiLayout);
        btn_lammoiLayout.setHorizontalGroup(
            btn_lammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_lammoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_lammoiLayout.setVerticalGroup(
            btn_lammoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_lammoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_lammoi);
        btn_lammoi.setBounds(620, 250, 140, 40);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TimMousePressed(evt);
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
        btn_Tim.setBounds(790, 250, 140, 40);

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
        btn_them.setBounds(80, 250, 140, 40);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_XoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Xoa);
        btn_Xoa.setBounds(260, 250, 140, 40);

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
        btn_Sua.setBounds(430, 250, 140, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(label_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(label_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(980, 100, 220, 120);

        chonAnh_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chonAnh_btn.setkEndColor(new java.awt.Color(255, 222, 89));
        chonAnh_btn.setkGradientFocus(250);
        chonAnh_btn.setkStartColor(new java.awt.Color(225, 176, 27));
        chonAnh_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chonAnh_btnMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Chọn ảnh");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout chonAnh_btnLayout = new javax.swing.GroupLayout(chonAnh_btn);
        chonAnh_btn.setLayout(chonAnh_btnLayout);
        chonAnh_btnLayout.setHorizontalGroup(
            chonAnh_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chonAnh_btnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );
        chonAnh_btnLayout.setVerticalGroup(
            chonAnh_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(chonAnh_btn);
        chonAnh_btn.setBounds(980, 250, 220, 40);

        table_TienNghi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        table_TienNghi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Tiện Nghi", "Tên Tiện Nghi", "Mô Tả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_TienNghi.setFocusable(false);
        table_TienNghi.setRowHeight(30);
        table_TienNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_TienNghiMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_TienNghi);
        if (table_TienNghi.getColumnModel().getColumnCount() > 0) {
            table_TienNghi.getColumnModel().getColumn(0).setPreferredWidth(130);
            table_TienNghi.getColumnModel().getColumn(0).setMaxWidth(130);
            table_TienNghi.getColumnModel().getColumn(1).setPreferredWidth(200);
            table_TienNghi.getColumnModel().getColumn(1).setMaxWidth(200);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 320, 1120, 400);

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

        jPanel3.setOpaque(false);
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        label_Avatar.setMaximumSize(new java.awt.Dimension(45, 45));
        label_Avatar.setMinimumSize(new java.awt.Dimension(45, 45));
        label_Avatar.setPreferredSize(new java.awt.Dimension(45, 45));

        javax.swing.GroupLayout circlePanel1Layout = new javax.swing.GroupLayout(circlePanel1);
        circlePanel1.setLayout(circlePanel1Layout);
        circlePanel1Layout.setHorizontalGroup(
            circlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(circlePanel1Layout.createSequentialGroup()
                .addComponent(label_Avatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        circlePanel1Layout.setVerticalGroup(
            circlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, circlePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_Avatar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 209, 84));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nguyễn Hoàng Sang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(circlePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(circlePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(1000, 0, 280, 70);

        Backgroup.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1283, 803);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1283, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    public void DocDuLieuLenTableTienNghi(List<TienNghi> list_tn) {
        model.setRowCount(0);

        for (TienNghi tn : list_tn) {
            model.addRow(new Object[]{
                tn.getMaTienNghi(),
                tn.getTenTienNghi(),
                tn.getMoTa()});
        }
        for (int i = 0; i < table_TienNghi.getColumnCount(); i++) {
            table_TienNghi.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    private void txt_tienNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienNghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienNghiActionPerformed

    private void table_TienNghiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_TienNghiMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_table_TienNghiMousePressed

    private void area_moTaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_moTaFocusGained
        // TODO add your handling code here:
        if (area_moTa.getText().trim().equals("Mô Tả")) {
            area_moTa.setText("");
        }
    }//GEN-LAST:event_area_moTaFocusGained

    private void area_moTaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_moTaFocusLost
        // TODO add your handling code here:
        if (area_moTa.getText().trim().equals("")) {
            area_moTa.setText("Mô Tả");
        }
    }//GEN-LAST:event_area_moTaFocusLost

    private void btn_themMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themMousePressed
        // TODO add your handling code here:

        if (txt_tienNghi.getText().trim().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập tên dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Chưa nhập tên dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập tên tiện nghi");
            txt_tienNghi.requestFocus();
            return;
        }
        if (area_moTa.getText().trim().equals("Mô Tả") || area_moTa.getText().trim().equals("")) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập mô tả dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Nhập mô tả tiện nghi");
            area_moTa.requestFocus();
            return;
        }

        if (hinhAnh == null) {
            JOptionPane.showMessageDialog(this, "Chọn hình ảnh");
            return;
        }
//            if(txtHinhAnh.getText().trim().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa chọn hình ảnh dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Chưa nhập đơn giá dịch vụ!");
//            }
        String tenTienNghi = txt_tienNghi.getText().trim();

        if (tienNghiDAO.timTienNghi(tenTienNghi) != null) {
//                JOptionPane.showMessageDialog(this, "Đã có dịch vụ có tên " + tenTienNghi, "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Trùng tên tiện nghi");
            txt_tienNghi.requestFocus();
            return;
        }

        int maTienNghi = tienNghiDAO.getAllTienNghi().getLast().getMaTienNghi() + 1;
        String moTaTienNghi = area_moTa.getText().trim();

        byte[] img = hinhAnh;

        //Tạo ra một dịch vụ mới
        TienNghi x = new TienNghi(maTienNghi, tenTienNghi, moTaTienNghi, hinhAnh);
        //Đưa dữ liệu vào database
        tienNghiDAO.createTienNghi(x);

        //Reset lại dữ liệu trong table
        //Đưa data vào table 
        JOptionPane.showMessageDialog(this, "Thêm thành công");
        DocDuLieuLenTableTienNghi(tienNghiDAO.getAllTienNghi());

        txt_tienNghi.setText("");
        area_moTa.setText("");
//            hinhAnh = null;
        label_Anh.setIcon(null);
        txt_tienNghi.setEnabled(true);

        LamMoi();
    }//GEN-LAST:event_btn_themMousePressed

    private void btn_XoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMousePressed
        // TODO add your handling code here:

        if (table_TienNghi.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa");
            return;
        }
        int row = table_TienNghi.getSelectedRow();

        int maTN = Integer.parseInt(table_TienNghi.getModel().getValueAt(row, 0).toString());

        if (row != -1) {

            if (JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa tiện nghi?" + "\n" + "Nó có thể thay đổi loại phòng? ", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                String tenTN = table_TienNghi.getModel().getValueAt(row, 1).toString();
//            String moTa = jTable1.getModel().getValueAt(row, 2).toString();
//            int donGia = Integer.parseInt(jTable1.getModel().getValueAt(row, 3).toString());

                TienNghi x = tienNghiDAO.timTienNghi(tenTN);

                tienNghiDAO.xoaTienNghi(x);

                DocDuLieuLenTableTienNghi(tienNghiDAO.getAllTienNghi());
                LamMoi();
            }
//          
        }
    }//GEN-LAST:event_btn_XoaMousePressed

    public List<Phong> getAllPhongByLoaiPhong(List<Phong> list_PhongTrong, int loaiPhong) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
        for (Phong phong : list_PhongTrong) {
            if (phong.getLoaiPhong() == loaiPhong) {
                list_PhongByLoai.add(phong);
            }

        }

        return list_PhongByLoai;
    }

    public List<LoaiPhong> getAllLoaiPhongByMaTienNghi(List<LoaiPhong> list_LoaiPhong, int maTienNghi) {
        List<LoaiPhong> list_LoaiPhongByTienNghi = new ArrayList<>();
        list_TienNghi = tienNghiDAO.getAllTienNghi();
        TienNghi tiennghi = new TienNghi();
        for (TienNghi tn : list_TienNghi) {
            if (tn.getMaTienNghi() == maTienNghi) {
                tiennghi = tn;
                break;
            }
        }
        for (LoaiPhong loaiphong : list_LoaiPhong) {
            if (loaiphong.getTienNghis().contains(tiennghi)) {
                list_LoaiPhongByTienNghi.add(loaiphong);
            }
        }
        return list_LoaiPhongByTienNghi;
    }


    private void btn_SuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMousePressed
        // TODO add your handling code here:
        if (table_TienNghi.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        int maTN = Integer.parseInt(table_TienNghi.getModel().getValueAt(table_TienNghi.getSelectedRow(), 0).toString());

        if (txt_tienNghi.getText().trim().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập tên dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Chưa nhập tên dịch vụ!");
            JOptionPane.showMessageDialog(this, "Nhập tiện nghi");
            txt_tienNghi.requestFocus();
            return;
        }
        if (area_moTa.getText().trim().equals("Mô Tả") || area_moTa.getText().trim().equals("")) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập mô tả dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(this, "Nhập mô tả");
            area_moTa.requestFocus();
            return;
        }

        if (hinhAnh == null) {
            JOptionPane.showMessageDialog(this, "Chọn hình ảnh");
            return;
        }

        if (table_TienNghi.getSelectedRow() != -1) {
            String tenTienNghi = table_TienNghi.getModel().getValueAt(table_TienNghi.getSelectedRow(), 1).toString();
            TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);

            System.out.println(x.toString());
            tenTienNghi = txt_tienNghi.getText().trim();
            int maDV = x.getMaTienNghi();
            String moTaDV = area_moTa.getText().trim();
            byte[] hinhAnh = this.hinhAnh;

            System.out.println("Trên label" + String.format("%d %s", maDV, moTaDV));
            TienNghi y = new TienNghi(maDV, tenTienNghi, moTaDV, hinhAnh);

            tienNghiDAO.suaTienNghi(x, y);
//                System.out.println(y.toString());
            DocDuLieuLenTableTienNghi(tienNghiDAO.getAllTienNghi());

            JOptionPane.showMessageDialog(this, "Sửa thành công");

//                txt_TienNghi.setEnabled(true);
            LamMoi();

        }

    }//GEN-LAST:event_btn_SuaMousePressed

    private void btn_TimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMousePressed
        // TODO add your handling code here:
//        String tenTienNghi = txt_tienNghi.getText().trim().toString();
//
////        System.out.println(tenTienNghi);
//        if (tenTienNghi.equals("")) {
//            JOptionPane.showMessageDialog(this, "Chưa nhập tên tiện nghi cần tìm", "Chưa nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
//        }
//        TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
////        System.out.println(x == null);
//        int viTriCuaX = tienNghiDAO.getAllTienNghi().indexOf(x);
//        hinhAnh = x.getHinhAnh();
//        label_Anh.setIcon(new ImageScale().load1(new ImageIcon(hinhAnh), label_Anh.getWidth(), label_Anh.getHeight()));
//        area_moTa.setText(x.getMoTa());
//        if (viTriCuaX != -1) {
//            table_TienNghi.setRowSelectionInterval(viTriCuaX, viTriCuaX);
//        } else {
//            JOptionPane.showMessageDialog(this, "Không có tiện nghi có tên " + tenTienNghi, "Không tìm thấy dữ liệu", JOptionPane.ERROR_MESSAGE);
//        }
        List<TienNghi> ListTienNghiTim = tienNghiDAO.getAllTienNghi();
        if (!txt_tienNghi.getText().trim().isEmpty()) {
            ListTienNghiTim = getListTienNghiTheoTen(ListTienNghiTim, txt_tienNghi.getText().trim());
            System.out.println(ListTienNghiTim);
            System.out.println(1);
        }

        if (!area_moTa.getText().trim().equals("Mô Tả")) {
            ListTienNghiTim = getListTienNghiTheoMoTa(ListTienNghiTim, area_moTa.getText().trim());
            System.out.println(ListTienNghiTim);
            System.out.println(2);
        }

        DocDuLieuLenTableTienNghi(ListTienNghiTim);
    }//GEN-LAST:event_btn_TimMousePressed

    public List<TienNghi> getListTienNghiTheoTen(List<TienNghi> list_tn, String TenTienNghi) {
        List<TienNghi> ListTienNghiTheoTen = new ArrayList<>();
        for (TienNghi tn : list_tn) {
            if (tn.getTenTienNghi().equals(TenTienNghi)) {
                ListTienNghiTheoTen.add(tn);
            }
        }
        return ListTienNghiTheoTen;
    }

    public List<TienNghi> getListTienNghiTheoMoTa(List<TienNghi> list_tn, String Mota) {
        List<TienNghi> ListTienNghiTheoMoTa = new ArrayList<>();
        for (TienNghi tn : list_tn) {
            if (tn.getMoTa().contains(Mota)) {
                ListTienNghiTheoMoTa.add(tn);
            }
        }
        return ListTienNghiTheoMoTa;
    }

    private void chonAnh_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chonAnh_btnMousePressed
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
                Logger.getLogger(NhanVien_TienNghi_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            label_Anh.setIcon(icon);

//            txt_TienNghi.setEnabled(false);
        }
    }//GEN-LAST:event_chonAnh_btnMousePressed

    private void btn_lammoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lammoiMousePressed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_btn_lammoiMousePressed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        new TrangCaNhan(nhanVien_DangSuDung).setVisible(true);
    }//GEN-LAST:event_jPanel3MouseClicked

    public void LamMoi() {
        txt_tienNghi.setText("");
        txt_tienNghi.requestFocus();
        DocDuLieuLenTableTienNghi(tienNghiDAO.getAllTienNghi());
        hinhAnh = null;
        area_moTa.setText("Mô Tả");
        label_Anh.setIcon(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel ThongTinTienNghi;
    public static javax.swing.JTextArea area_moTa;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private keeptoo.KGradientPanel btn_lammoi;
    private keeptoo.KGradientPanel btn_them;
    private keeptoo.KGradientPanel chonAnh_btn;
    private GUI.CirclePanel_Atatar circlePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_Anh;
    private javax.swing.JLabel label_Avatar;
    private javax.swing.JTable table_TienNghi;
    private javax.swing.JTextField txt_tienNghi;
    // End of variables declaration//GEN-END:variables
}
