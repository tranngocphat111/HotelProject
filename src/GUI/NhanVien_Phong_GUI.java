/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DonDatPhongDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DonDatPhong;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.TienNghi;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public final class NhanVien_Phong_GUI extends javax.swing.JInternalFrame {

    private DefaultTableModel model;
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private List<LoaiPhong> list_LoaiPhong = new ArrayList<LoaiPhong>();
    private LoaiPhongDAO loaiphong_dao = new LoaiPhongDAO(database);
    private List<Phong> list_Phong = new ArrayList<Phong>();
    private PhongDAO phong_dao = new PhongDAO(database);
    private List<DonDatPhong> list_DonDatPhong = new ArrayList<>();
    private DonDatPhongDAO dondatphong_dao = new DonDatPhongDAO(database);
    private final DefaultTableCellRenderer centerRenderer;
    private DecimalFormat df = new DecimalFormat("#,##0");
    List<Integer> a = new ArrayList<>();

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public NhanVien_Phong_GUI() {
        initComponents();
        txt_tang.requestFocus();
//      Chặn edit table
        String columnNames[] = {"Số Phòng", "Loại Phòng", "Số Tầng", "Loại Giường", "Diện Tích", "Tiện Nghi", "Mô Tả", "Đơn Giá"};
        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về false để chặn mọi ô không thể chỉnh sửa
                return false;
            }
        };
        model.setRowCount(0);

        Table_Phong.setModel(model);
        Table_Phong.setRowHeight(30);
        jScrollPane3.setViewportView(Table_Phong);
        if (Table_Phong.getColumnModel().getColumnCount() > 0) {
            Table_Phong.getColumnModel().getColumn(0).setPreferredWidth(100);
            Table_Phong.getColumnModel().getColumn(0).setMaxWidth(100);
            Table_Phong.getColumnModel().getColumn(1).setPreferredWidth(120);
            Table_Phong.getColumnModel().getColumn(1).setMaxWidth(120);
            Table_Phong.getColumnModel().getColumn(2).setPreferredWidth(90);
            Table_Phong.getColumnModel().getColumn(2).setMaxWidth(90);
            Table_Phong.getColumnModel().getColumn(3).setPreferredWidth(120);
            Table_Phong.getColumnModel().getColumn(3).setMaxWidth(120);
            Table_Phong.getColumnModel().getColumn(4).setPreferredWidth(100);
            Table_Phong.getColumnModel().getColumn(4).setMaxWidth(100);
            Table_Phong.getColumnModel().getColumn(6).setPreferredWidth(150);
            Table_Phong.getColumnModel().getColumn(6).setMaxWidth(150);
            Table_Phong.getColumnModel().getColumn(7).setPreferredWidth(130);
            Table_Phong.getColumnModel().getColumn(7).setMaxWidth(130);
        }

//      Căn giữa các phần tử trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

        list_Phong = phong_dao.getAllPhongsSortByMaPhong();
        model = (DefaultTableModel) Table_Phong.getModel();
        model.setRowCount(0);
        DocDuLieuLenTablePhong(list_Phong);

//      Set font cho header_phong
        JTableHeader header_phong = Table_Phong.getTableHeader();
        header_phong.setPreferredSize(new Dimension(header_phong.getPreferredSize().width, 30));
        header_phong.setFont(new Font("Arial", Font.BOLD, 15));

//      Căn giữa cho header table phòng
        DefaultTableCellRenderer renderer_phong = (DefaultTableCellRenderer) header_phong.getDefaultRenderer();
        renderer_phong.setHorizontalAlignment(JLabel.CENTER);

//      Thiết lập renderer cho header phòng
        header_phong.setDefaultRenderer(renderer_phong);

//      bắt sự kiện hover cho nút 
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

//      sự kiện chọn loại phòng setDonGia
        cb_loaiphong.addActionListener((ActionEvent e) -> {
            list_LoaiPhong = loaiphong_dao.getAllLoaiPhong();
            if (cb_loaiphong.getSelectedIndex() == 0) {
                txt_gia.setText("");
            } else {
                for (LoaiPhong loaiPhong : list_LoaiPhong) {
                    if (cb_loaiphong.getSelectedItem().equals(loaiPhong.getTenLoaiPhong())) {
                        txt_gia.setText(df.format(loaiPhong.getDonGia()));
                    }
                }

            }
        });

        capnhatComboxLoaiPhong();

        txt_gia.setFocusable(false);
        txt_gia.setEditable(false);
        txt_gia.setBackground(new java.awt.Color(225, 225, 225));

        txt_phong.setFocusable(false);
        txt_phong.setEditable(false);
        txt_phong.setBackground(new java.awt.Color(225, 225, 225));

        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UI = (BasicInternalFrameUI) this.getUI();
        UI.setNorthPane(null);

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
        cb_loaiphong = new javax.swing.JComboBox<>();
        txt_gia = new javax.swing.JTextField();
        txt_tang = new javax.swing.JTextField();
        txt_phong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Lammoi = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Them = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        area_mota = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table_Phong = new javax.swing.JTable();
        Backgroup = new javax.swing.JLabel();

        setName("page_Phong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinPhong.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinPhong.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Loại Phòng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tầng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giá");

        cb_loaiphong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cb_loaiphong.setFocusable(false);
        cb_loaiphong.setPreferredSize(new java.awt.Dimension(108, 22));

        txt_gia.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txt_tang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txt_phong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số Phòng");

        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
        jLabel25.setText("*");

        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("*");

        javax.swing.GroupLayout ThongTinPhongLayout = new javax.swing.GroupLayout(ThongTinPhong);
        ThongTinPhong.setLayout(ThongTinPhongLayout);
        ThongTinPhongLayout.setHorizontalGroup(
            ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinPhongLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinPhongLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_loaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinPhongLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_tang, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_gia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        ThongTinPhongLayout.setVerticalGroup(
            ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinPhongLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinPhongLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinPhongLayout.createSequentialGroup()
                        .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinPhongLayout.createSequentialGroup()
                                .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel25))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel26)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(ThongTinPhongLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(4, 4, 4)))
                        .addGroup(ThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_gia, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(txt_tang)
                            .addComponent(cb_loaiphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinPhong);
        ThongTinPhong.setBounds(80, 90, 830, 120);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Thông tin phòng");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel14);
        jLabel14.setBounds(80, 50, 210, 32);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TimMousePressed(evt);
            }
        });

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
        btn_Tim.setBounds(770, 240, 140, 40);

        btn_Lammoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Lammoi.setkGradientFocus(250);
        btn_Lammoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Lammoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LammoiMousePressed(evt);
            }
        });

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
        btn_Lammoi.setBounds(600, 240, 140, 40);

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
        btn_Xoa.setBounds(250, 240, 140, 40);

        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setkGradientFocus(250);
        btn_Them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Thêm ");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThemLayout = new javax.swing.GroupLayout(btn_Them);
        btn_Them.setLayout(btn_ThemLayout);
        btn_ThemLayout.setHorizontalGroup(
            btn_ThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        btn_ThemLayout.setVerticalGroup(
            btn_ThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ThemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Them);
        btn_Them.setBounds(80, 240, 140, 40);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_SuaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Sua);
        btn_Sua.setBounds(420, 240, 140, 40);

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setToolTipText("");
        jScrollPane2.setInheritsPopupMenu(true);
        jScrollPane2.setOpaque(false);

        area_mota.setBackground(new java.awt.Color(0, 0, 0));
        area_mota.setColumns(20);
        area_mota.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        area_mota.setForeground(new java.awt.Color(255, 255, 255));
        area_mota.setLineWrap(true);
        area_mota.setRows(3);
        area_mota.setTabSize(4);
        area_mota.setText("Mô Tả");
        area_mota.setWrapStyleWord(true);
        area_mota.setCaretColor(new java.awt.Color(255, 255, 255));
        area_mota.setMargin(new java.awt.Insets(4, 6, 2, 6));
        area_mota.setOpaque(false);
        area_mota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                area_motaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                area_motaFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(area_mota);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(950, 90, 250, 190);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 300));

        Table_Phong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_Phong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Số Phòng", "Loại Phòng", "Số Tầng", "Loại Giường", "Diện Tích", "Tiện Nghi", "Mô Tả", "Đơn Giá"
            }
        ));
        Table_Phong.setRowHeight(30);
        Table_Phong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_PhongMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(Table_Phong);
        if (Table_Phong.getColumnModel().getColumnCount() > 0) {
            Table_Phong.getColumnModel().getColumn(0).setPreferredWidth(100);
            Table_Phong.getColumnModel().getColumn(0).setMaxWidth(100);
            Table_Phong.getColumnModel().getColumn(1).setPreferredWidth(120);
            Table_Phong.getColumnModel().getColumn(1).setMaxWidth(120);
            Table_Phong.getColumnModel().getColumn(2).setPreferredWidth(90);
            Table_Phong.getColumnModel().getColumn(2).setMaxWidth(90);
            Table_Phong.getColumnModel().getColumn(3).setPreferredWidth(120);
            Table_Phong.getColumnModel().getColumn(3).setMaxWidth(120);
            Table_Phong.getColumnModel().getColumn(4).setPreferredWidth(100);
            Table_Phong.getColumnModel().getColumn(4).setMaxWidth(100);
            Table_Phong.getColumnModel().getColumn(6).setPreferredWidth(150);
            Table_Phong.getColumnModel().getColumn(6).setMaxWidth(150);
            Table_Phong.getColumnModel().getColumn(7).setPreferredWidth(130);
            Table_Phong.getColumnModel().getColumn(7).setMaxWidth(130);
        }

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(80, 310, 1120, 396);

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

    private void area_motaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_motaFocusGained
        // TODO add your handling code here:
        if (area_mota.getText().trim().equals("Mô Tả")) {
            area_mota.setText("");
        }
    }//GEN-LAST:event_area_motaFocusGained

    private void area_motaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_motaFocusLost
        // TODO add your handling code here:
        if (area_mota.getText().trim().equals("")) {
            area_mota.setText("Mô Tả");
        }
    }//GEN-LAST:event_area_motaFocusLost

    private void btn_ThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMousePressed
        // TODO add your handling code here:
        if (cb_loaiphong.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn loại phòng");
            return;
        }
        if (txt_tang.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời nhập số tầng");
            txt_tang.requestFocus();
            return;
        }

        String regex = "\\d+";
        if (!txt_tang.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Số Tầng phải là số");
            txt_tang.setText("");
            txt_tang.requestFocus();
            return;
        }

        if (area_mota.getText().trim().equals("Mô Tả")) {
            JOptionPane.showMessageDialog(this, "Mời nhập mô tả");
            area_mota.requestFocus();
            return;
        }

        if (!txt_phong.getText().trim().equals("")) {
            for (Phong phong : list_Phong) {
                if (Integer.parseInt(txt_phong.getText().trim()) == (phong.getMaPhong())) {
                    JOptionPane.showMessageDialog(this, "Trùng mã phòng");
                    return;
                }
            }
        }

        Phong p = new Phong();
        list_Phong = phong_dao.getAllPhongsSortByMaPhong();
        p.setMaPhong(taoMaTuDong(getListMaPhong(Integer.parseInt(txt_tang.getText().trim())), Integer.parseInt(txt_tang.getText().trim())));
        p.setTang(Integer.parseInt(txt_tang.getText().trim()));
        p.setLoaiPhong(loaiphong_dao.getLoaiPhongByTen(cb_loaiphong.getSelectedItem().toString()).getMaLoaiPhong());
        p.setMoTa(area_mota.getText().trim());
        phong_dao.createPhong(p);
        JOptionPane.showMessageDialog(null, "Thêm phòng thành công");
        lamMoi();
    }//GEN-LAST:event_btn_ThemMousePressed

    private void btn_LammoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LammoiMousePressed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btn_LammoiMousePressed

    private void btn_TimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMousePressed
        // TODO add your handling code here:
        List<Phong> list_P = phong_dao.getAllPhongsSortByMaPhong();
        System.out.println(list_P.size());
        if (!txt_phong.getText().trim().isEmpty()) {
            list_P = getPhongByMaPhong(list_P, Integer.parseInt(txt_phong.getText().trim()));
        }

        String regex = "\\d+";
        if (!txt_tang.getText().trim().matches(regex) && !txt_tang.getText().trim().isEmpty()) {
            list_P = new ArrayList<>();
        } else {
            if (!txt_tang.getText().trim().isEmpty()) {
                list_P = getPhongByTang(list_P, Integer.parseInt(txt_tang.getText().trim()));
            }
        }

        if (cb_loaiphong.getSelectedIndex() != 0) {
            list_P = getPhongByTenLoaiPhong(list_P, cb_loaiphong.getSelectedItem().toString());

        }

        if (!area_mota.getText().trim().equals("Mô Tả")) {
            list_P = getPhongByMoTa(list_P, area_mota.getText().trim());
        }

        DocDuLieuLenTablePhong(list_P);
    }//GEN-LAST:event_btn_TimMousePressed

    private void Table_PhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_PhongMousePressed
        // TODO add your handling code here:
        if (Table_Phong.getSelectedRow() == -1) {
            return;
        } else {
            int selectedRow = Table_Phong.getSelectedRow();
            String phong = model.getValueAt(selectedRow, 0) + "";
            txt_phong.setText(phong);
            cb_loaiphong.setSelectedItem(model.getValueAt(selectedRow, 1));
            String tang = model.getValueAt(selectedRow, 2) + "";
            txt_tang.setText(tang);
            String modified = (String) model.getValueAt(selectedRow, 7);
            modified = modified.substring(0, modified.length() - 4);
            txt_gia.setText(modified);
            area_mota.setText((String) model.getValueAt(selectedRow, 6));
        }
    }//GEN-LAST:event_Table_PhongMousePressed

    private void btn_XoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMousePressed
        // TODO add your handling code here:
        if (Table_Phong.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng cần xóa");
            return;
        }

        int maPhong = Integer.parseInt(model.getValueAt(Table_Phong.getSelectedRow(), 0).toString());
        if (checkPhongDangSuDung(maPhong)) {
            JOptionPane.showMessageDialog(this, "Phòng đang được sử dụng!!! Không thể xóa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            model.removeRow(Table_Phong.getSelectedRow());
            phong_dao.deletePhong(maPhong);
        }
        lamMoi();
    }//GEN-LAST:event_btn_XoaMousePressed

    private void btn_SuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMousePressed
        // TODO add your handling code here:
        if (Table_Phong.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng cần sửa");
            return;
        }
        int selectedRow = Table_Phong.getSelectedRow();
        int maPhong = Integer.parseInt(txt_phong.getText().trim());
        if (checkPhongDangSuDung(maPhong)) {
            JOptionPane.showMessageDialog(this, "Phòng đang được sử dụng!!! Không thể sửa");
            return;
        }

        if (txt_tang.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tầng");
            txt_tang.requestFocus();
            return;
        }

        String regex = "\\d+";
        if (!txt_tang.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Số Tầng phải là số");
            txt_tang.setText("");
            txt_tang.requestFocus();
            return;
        }

        String tang = model.getValueAt(Table_Phong.getSelectedRow(), 2) + "";
        if (!txt_tang.getText().trim().equals(tang)) {
            JOptionPane.showMessageDialog(this, "Không được sửa số tầng");
            txt_tang.requestFocus();
            return;
        }

        if (area_mota.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả");
            area_mota.requestFocus();
            return;
        }

        Phong p = new Phong();

        int maTang = Integer.parseInt(txt_tang.getText().trim());
        String mota = area_mota.getText().trim();
        p.setMaPhong(maPhong);
        p.setTang(maTang);
        p.setLoaiPhong(loaiphong_dao.getLoaiPhongByTen(cb_loaiphong.getSelectedItem().toString()).getMaLoaiPhong());
        p.setMoTa(mota);

        phong_dao.updatePhong(p);
        JOptionPane.showMessageDialog(this, "Sửa thành công");
        lamMoi();


    }//GEN-LAST:event_btn_SuaMousePressed

    public boolean checkPhongDangSuDung(int maPhong) {
        list_DonDatPhong = dondatphong_dao.getDonDatPhongTheoTrangThaiOVaCho();
        for (DonDatPhong ddp : list_DonDatPhong) {
            if (maPhong == ddp.getPhong().getMaPhong()) {
                return true;
            }
        }
        return false;
    }

    public void lamMoi() {
        list_Phong = phong_dao.getAllPhongsSortByMaPhong();
        DocDuLieuLenTablePhong(list_Phong);
        txt_phong.setText("");
        txt_gia.setText("");
        cb_loaiphong.setSelectedIndex(0);
        txt_tang.setText("");
        txt_gia.setText("");
        area_mota.setText("Mô Tả");
        txt_tang.requestFocus();
    }

    public List<Phong> getPhongByMaPhong(List<Phong> list_Phong, int MaPhong) {
        List<Phong> list_PhongByMaLoai = new ArrayList<>();
        for (Phong p : list_Phong) {
            if (p.getMaPhong() == MaPhong) {
                list_PhongByMaLoai.add(p);
            }
        }
        return list_PhongByMaLoai;
    }

    public List<Phong> getPhongByTang(List<Phong> list_Phong, int getTang) {
        List<Phong> list_PhongByMaTang = new ArrayList<>();
        for (Phong p : list_Phong) {
            if (p.getTang() == getTang) {
                list_PhongByMaTang.add(p);
            }
        }
        return list_PhongByMaTang;
    }

    public List<Phong> getPhongByTenLoaiPhong(List<Phong> list_Phong, String TenLoai) {
        List<Phong> list_PhongByTenLoaiPhong = new ArrayList<>();
        for (Phong p : list_Phong) {
            if (loaiphong_dao.getLoaiPhongByMa(p.getLoaiPhong()).getTenLoaiPhong().equals(TenLoai)) {
                list_PhongByTenLoaiPhong.add(p);
            }
        }
        return list_PhongByTenLoaiPhong;
    }

    public List<Phong> getPhongByMoTa(List<Phong> list_Phong, String MoTa) {
        List<Phong> list_PhongByMoTa = new ArrayList<>();
        for (Phong p : list_Phong) {
            if (p.getMoTa().contains(MoTa)) {
                list_PhongByMoTa.add(p);
            }
        }
        return list_PhongByMoTa;
    }

    public int getMaLoaiPhongBangTenLoaiPhong(String tenLoaiPhong) {
        List<LoaiPhong> list_lp = new ArrayList();
        list_lp = loaiphong_dao.getAllLoaiPhong();
        for (LoaiPhong loaiphong : list_lp) {
            if (loaiphong.getTenLoaiPhong().equals(tenLoaiPhong)) {
                return loaiphong.getMaLoaiPhong();
            }
        }
        return -1;
    }

    public boolean checkTang(int soTang) {
        for (Phong phong : list_Phong) {
            if (phong.getTang() == soTang) {
                return false;
            }
        }
        return true;
    }

    public void DocDuLieuLenTablePhong(List<Phong> list_phong) {
        model.setRowCount(0);

        for (Phong phong : list_phong) {
            LoaiPhong lp = loaiphong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
            model.addRow(new Object[]{
                phong.getMaPhong(),
                lp.getTenLoaiPhong(),
                phong.getTang(),
                lp.getLoaiGiuong(),
                lp.getDienTich() + " m2",
                getListTienNghi(lp.getTienNghis()),
                phong.getMoTa(),
                df.format(lp.getDonGia()) + " VND"});
        }

        for (int i = 0; i < Table_Phong.getColumnCount(); i++) {
            Table_Phong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    public String getListTienNghi(List<TienNghi> list_tienNghi) {
        String list = "";
        for (TienNghi tn : list_tienNghi) {
            list = list + tn.getTenTienNghi() + ", ";
        }
        list = list.substring(0, list.length() - 2);
        return list;
    }

    public void capnhatComboxLoaiPhong() {
//      Thêm các loại phòng vào combobox loại phòng
        list_LoaiPhong = loaiphong_dao.getAllLoaiPhong();
        cb_loaiphong.removeAllItems();
        cb_loaiphong.addItem("");
        for (LoaiPhong lp : list_LoaiPhong) {
            cb_loaiphong.addItem(lp.getTenLoaiPhong());
        }
    }

    public List<Integer> getListMaPhong(int tang) {
        List<Integer> tam = new ArrayList<>();
        for (Phong p : getPhongByTang(phong_dao.getAllPhongsSortByMaPhong(), tang)) {
            tam.add(p.getMaPhong() % 100);
        }
        return tam;
    }

    public int taoMaTuDong(List<Integer> mangCoSan, int Tang) {
        Collections.sort(mangCoSan);

        if (mangCoSan.size() == 0) {
            return Tang * 100 + 1;
        }

        for (int i = 1; i <= mangCoSan.getLast(); i++) {
            if (!mangCoSan.contains(i)) {
                return Tang * 100 + i;
            }
        }

        return Tang * 100 + mangCoSan.getLast() + 1;

//          int length = mangCoSan.getLast() - mangCoSan.getFirst() + 1;
//          int[] count_sort = new int[length];
//          
//          for(int i = 0; i < mangCoSan.size(); i++) {
//              count_sort[i] = mangCoSan.get(i);
//          }
//          if(count_sort[0] == 0) {
//              return mangCoSan.getFirst();
//          }
//          for(int i = 0; i < count_sort.length; i++) {
//              
//              if(count_sort[i] == 0) {
//                  return count_sort[i-1] + 1 + mangCoSan.getFirst();
//              }
//          }
//          
//          return mangCoSan.getLast() + 1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JTable Table_Phong;
    private javax.swing.JPanel ThongTinPhong;
    private javax.swing.JTextArea area_mota;
    private keeptoo.KGradientPanel btn_Lammoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Them;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private javax.swing.JComboBox<String> cb_loaiphong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_gia;
    private javax.swing.JTextField txt_phong;
    private javax.swing.JTextField txt_tang;
    // End of variables declaration//GEN-END:variables
}
