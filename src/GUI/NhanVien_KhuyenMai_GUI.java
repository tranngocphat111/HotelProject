/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import static GUI.DangNhap_GUI.database;
import com.mongodb.client.MongoDatabase;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.KhuyenMaiDAO;
import model.DTO.KhuyenMai;
import model.MongoDBConnection;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import model.DTO.KhachHang;
import model.DTO.NhanVien;
import org.bson.Document;

/**
 *
 * @author Admin
 */
public class NhanVien_KhuyenMai_GUI extends javax.swing.JInternalFrame {

    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO(database);
    private List<KhuyenMai> list_km = new ArrayList<KhuyenMai>();
    private DefaultTableModel model;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String listLP = "";
    private NhanVien nhanVien_DangSuDung;
    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    // generate ID
    public String generateRandomCode(int length) {

        String characters = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }

    public boolean validateForm(Date ngayBatDau, Date ngayKetThuc, String tiLeKhuyenMai, String moTa, List<String> loaiPhong) {
        if (ngayBatDau == null) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được để trống.");
            txt_NgayBatDau.requestFocusInWindow();
            return false;
        }
        if (ngayKetThuc == null) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc không được để trống.");
            txt_NgayDi.requestFocusInWindow();
            return false;
        }

        try {
            if (tiLeKhuyenMai == null || tiLeKhuyenMai.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không được để trống tỉ lệ khuyến mãi");
                txt_TiLeKhuyenMai.requestFocus();
                return false;
            }

            int khuyenMai = Integer.parseInt(tiLeKhuyenMai);

            if (khuyenMai < 0 || khuyenMai > 100) {
                JOptionPane.showMessageDialog(null, "Tỉ lệ khuyến mãi phải nằm trong khoảng từ 0 đến 100.");
                txt_TiLeKhuyenMai.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tỉ lệ khuyến mãi phải là số, nằm trong khoảng từ 0 đến 100.");
            txt_TiLeKhuyenMai.requestFocus();
            return false;
        }

        if (moTa != null && moTa.length() > 500) {
            JOptionPane.showMessageDialog(null, "Mô tả không được dài quá 500 ký tự.");
            area_ghichu.requestFocus();
            return false;
        }

        if (loaiPhong.size() == 0) {
            JOptionPane.showMessageDialog(null, "Phải chọn ít nhất 1 loại phòng.");
            return false;
        }

        return true;
    }

    public boolean checkInvaildDate(String ngayBatDau, String ngayKetThuc) {

        Date startDate;
        try {
            startDate = dateFormat.parse(ngayBatDau);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu không hợp lệ. Định dạng phải là dd/MM/yyyy.");
            return false;
        }

        Date endDate;
        try {
            endDate = dateFormat.parse(ngayKetThuc);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc không hợp lệ. Định dạng phải là yyyy-MM-dd.");
            return false;
        }
        System.out.println(startDate);
        System.out.println(endDate);

        // Check if end date is after or same as start date
        if (endDate.before(startDate)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu.");
            return false;
        }
        return true;
    }

    public NhanVien_KhuyenMai_GUI(NhanVien nhanVien_DangSuDung) {
        this.nhanVien_DangSuDung = nhanVien_DangSuDung;
        initComponents();
        jLabel1.setText(this.nhanVien_DangSuDung.getTenNhanVien());
        ImageScale.setCircularImage(label_Avatar, new ImageScale().getScaledImage1(50, 50, new ImageIcon(nhanVien_DangSuDung.getAnhDaiDien())));
        this.model = (DefaultTableModel) jTable1.getModel();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        // make butotn hover 
        list_btn.add(btn_Them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_LamMoi);
        list_btn.add(btn_Tim);

        list_btn.forEach((element) -> {
//            if(!element.isEnabled()) {
//                element.setBackground(new java.awt.Color(173, 216, 230)); // Màu nền xanh nhạt khi bị vô hiệu hóa
//                element.setForeground(new java.awt.Color(100, 100, 100)); // Màu chữ xám đậm khi bị vô hiệu hóa
//                element.setBorder(BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100), 1));
//            }
//            
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
                    if (element.isEnabled()) {
                        element.setkStartColor(new java.awt.Color(255, 225, 27));
                        element.setkEndColor(new java.awt.Color(255, 222, 89));
                        element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (element.isEnabled()) {
                        element.setkStartColor(new java.awt.Color(225, 176, 27));
                        element.setkEndColor(new java.awt.Color(255, 222, 89));
                        element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
                    }

                }
            });
        });
        // set table header fon
        JTableHeader header = jTable1.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setPreferredSize(new Dimension(header.getPreferredSize().height, 30));

        header.setFont(new Font("Arial", Font.PLAIN, 15));

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);

        //Căn giữa các các dòng trong table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);
        for (int i = 0; i < header.getTable().getColumnCount(); i++) {
            header.getTable().getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //add values to table
        list_km = khuyenMaiDAO.getAllKhuyenMai();

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");

        list_km.forEach((km) -> {

            String beginText = outputFormat.format(km.getNgayBatDau());

            String endText = outputFormat.format(km.getNgayKetThuc());
            listLP = "";
            for (String loaip : km.getLoaiPhong()) {
                listLP += loaip + ", ";
            }
            listLP = listLP.substring(0, listLP.length() - 2);

            Object[] rowData = {
                km.getMaKhuyenMai(),
                beginText,
                endText,
                km.getMoTa(),
                km.getTiLeKhuyenMai(),
                listLP,};

            model.addRow(rowData);

        });

        // make update and remove btn disabled if not select row
        btn_Xoa.setEnabled(false);
        btn_Sua.setEnabled(false);

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow == -1) {
                    btn_Xoa.setEnabled(false);
                    btn_Sua.setEnabled(false);

                    txt_NgayBatDau.setDate(null);
                    txt_NgayDi.setDate(null);
                    txt_TiLeKhuyenMai.setText("");
                    area_ghichu.setText("");
                    checkBox_Deluxe.setSelected(false);
                    checkBox_Family.setSelected(false);
                    checkBox_Standard.setSelected(false);
                    checkBox_Suite.setSelected(false);

                } else {
                    btn_Xoa.setEnabled(true);
                    btn_Sua.setEnabled(true);

                    try {
                        Date ngayBatDau = inputFormat.parse(jTable1.getValueAt(selectedRow, 1).toString());
                        Date ngayKetThuc = inputFormat.parse(jTable1.getValueAt(selectedRow, 2).toString());

                        txt_NgayBatDau.setDate(ngayBatDau);
                        txt_NgayDi.setDate(ngayKetThuc);
                        txt_TiLeKhuyenMai.setText(jTable1.getValueAt(selectedRow, 4).toString());
                        area_ghichu.setText(jTable1.getValueAt(selectedRow, 3).toString());

                        String loaiPhong = jTable1.getValueAt(selectedRow, 5).toString();

                        if (loaiPhong != null) {
                            if (loaiPhong.contains("Standard")) {
                                checkBox_Standard.setSelected(true);
                            } else {
                                checkBox_Standard.setSelected(false);

                            }

                            if (loaiPhong.contains("Deluxe")) {
                                checkBox_Deluxe.setSelected(true);
                            } else {
                                checkBox_Deluxe.setSelected(false);
                            }
                            if (loaiPhong.contains("Suite")) {
                                checkBox_Suite.setSelected(true);
                            } else {
                                checkBox_Suite.setSelected(false);
                            }
                            if (loaiPhong.contains("Family")) {
                                checkBox_Family.setSelected(true);
                            } else {
                                checkBox_Family.setSelected(false);

                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
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

        jPanel1 = new javax.swing.JPanel();
        BangChonLoaiPhong = new javax.swing.JPanel();
        checkBox_Suite = new javax.swing.JCheckBox();
        checkBox_Deluxe = new javax.swing.JCheckBox();
        checkBox_Family = new javax.swing.JCheckBox();
        checkBox_Standard = new javax.swing.JCheckBox();
        ThongTinKhuyenMai = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_NgayBatDau = new com.toedter.calendar.JDateChooser();
        txt_NgayDi = new com.toedter.calendar.JDateChooser();
        jLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_TiLeKhuyenMai = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
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
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        circlePanel1 = new GUI.CirclePanel_Atatar();
        label_Avatar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Backgroup = new javax.swing.JLabel();

        setName("page_KhuyenMai"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        BangChonLoaiPhong.setBackground(new java.awt.Color(0, 0, 0));
        BangChonLoaiPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        BangChonLoaiPhong.setOpaque(false);

        checkBox_Suite.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_Suite.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_Suite.setText("Suite");
        checkBox_Suite.setToolTipText("");
        checkBox_Suite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_Suite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_Suite.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_Suite.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_Suite.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_Suite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_SuiteActionPerformed(evt);
            }
        });

        checkBox_Deluxe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_Deluxe.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_Deluxe.setText("Deluxe");
        checkBox_Deluxe.setToolTipText("");
        checkBox_Deluxe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_Deluxe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_Deluxe.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_Deluxe.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_Deluxe.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_Deluxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_DeluxeActionPerformed(evt);
            }
        });

        checkBox_Family.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_Family.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_Family.setText("Family");
        checkBox_Family.setToolTipText("");
        checkBox_Family.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_Family.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_Family.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_Family.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_Family.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_Family.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_FamilyActionPerformed(evt);
            }
        });

        checkBox_Standard.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_Standard.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_Standard.setText("Standard");
        checkBox_Standard.setToolTipText("");
        checkBox_Standard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_Standard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_Standard.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_Standard.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_Standard.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_Standard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_StandardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BangChonLoaiPhongLayout = new javax.swing.GroupLayout(BangChonLoaiPhong);
        BangChonLoaiPhong.setLayout(BangChonLoaiPhongLayout);
        BangChonLoaiPhongLayout.setHorizontalGroup(
            BangChonLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BangChonLoaiPhongLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(checkBox_Standard, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(checkBox_Deluxe, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(checkBox_Suite, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(checkBox_Family, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        BangChonLoaiPhongLayout.setVerticalGroup(
            BangChonLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BangChonLoaiPhongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BangChonLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBox_Suite, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox_Deluxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox_Family, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox_Standard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel1.add(BangChonLoaiPhong);
        BangChonLoaiPhong.setBounds(80, 250, 810, 60);

        ThongTinKhuyenMai.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhuyenMai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        ThongTinKhuyenMai.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày bắt đầu");

        jLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel.setForeground(new java.awt.Color(255, 255, 255));
        jLabel.setText("Ngày kết thúc");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tỉ lệ khuyến mãi");

        txt_TiLeKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TiLeKhuyenMaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinKhuyenMaiLayout = new javax.swing.GroupLayout(ThongTinKhuyenMai);
        ThongTinKhuyenMai.setLayout(ThongTinKhuyenMaiLayout);
        ThongTinKhuyenMaiLayout.setHorizontalGroup(
            ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhuyenMaiLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(99, 99, 99)
                .addGroup(ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_NgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TiLeKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(24, 24, 24))
        );
        ThongTinKhuyenMaiLayout.setVerticalGroup(
            ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhuyenMaiLayout.createSequentialGroup()
                .addGroup(ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinKhuyenMaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(ThongTinKhuyenMaiLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel))
                        .addGap(6, 6, 6)))
                .addGroup(ThongTinKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_NgayDi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TiLeKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhuyenMai);
        ThongTinKhuyenMai.setBounds(80, 90, 810, 100);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Áp dụng cho loại phòng");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel14);
        jLabel14.setBounds(90, 210, 300, 25);

        jLabel19.setBackground(new java.awt.Color(255, 209, 84));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 209, 84));
        jLabel19.setText("Thông tin khuyến mãi");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel19);
        jLabel19.setBounds(80, 50, 250, 32);

        btn_LamMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LamMoiMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Làm Mới");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(590, 330, 140, 40);

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
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Them);
        btn_Them.setBounds(80, 330, 140, 40);

        btn_Xoa.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Xoa.setkGradientFocus(250);
        btn_Xoa.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_XoaMouseClicked(evt);
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
        btn_Xoa.setBounds(420, 330, 140, 40);

        btn_Sua.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Sua.setkGradientFocus(250);
        btn_Sua.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Sua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SuaMouseClicked(evt);
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
        btn_Sua.setBounds(250, 330, 140, 40);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Mô tả", "Tỉ lệ khuyến mãi", "Loại phòng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(220);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(220);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(450);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(250);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(300);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 400, 1130, 310);

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84), 2));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setToolTipText("");
        jScrollPane2.setInheritsPopupMenu(true);
        jScrollPane2.setOpaque(false);

        area_ghichu.setBackground(new java.awt.Color(0, 0, 0));
        area_ghichu.setColumns(20);
        area_ghichu.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        area_ghichu.setForeground(new java.awt.Color(255, 255, 255));
        area_ghichu.setLineWrap(true);
        area_ghichu.setRows(3);
        area_ghichu.setWrapStyleWord(true);
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
        jScrollPane2.setBounds(930, 90, 280, 280);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_TimMouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Tìm");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_TimLayout = new javax.swing.GroupLayout(btn_Tim);
        btn_Tim.setLayout(btn_TimLayout);
        btn_TimLayout.setHorizontalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(750, 330, 140, 40);

        jPanel2.setOpaque(false);
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
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

        jPanel3.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 209, 84));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nguyễn Hoàng Sang");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(circlePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(circlePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(1000, 0, 280, 70);

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
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btn_ThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMouseClicked
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.model = (DefaultTableModel) jTable1.getModel();
        String maKM = generateRandomCode(2);
        Date ngayBatDau = txt_NgayBatDau.getDate();
        Date ngayKetThuc = txt_NgayDi.getDate();
        String tiLeKhuyenMai = txt_TiLeKhuyenMai.getText();
        String moTa = area_ghichu.getText();

        List<String> listLP = new ArrayList<String>();
            if(checkBox_Deluxe.isSelected()) {
                listLP.add("Deluxe");
            } 
             if(checkBox_Family.isSelected()) {
                listLP.add("Family");
            } 
              if(checkBox_Standard.isSelected()) {
                listLP.add("Standard");
            } 
               if(checkBox_Suite.isSelected()) {
                listLP.add("Suite");
            } 
            boolean validate = validateForm(ngayBatDau, ngayKetThuc, tiLeKhuyenMai, moTa,listLP);

        if (validate) {
            String beginText = outputFormat.format(ngayBatDau);
            String endText = outputFormat.format(ngayKetThuc);
            if (!checkInvaildDate(beginText, endText)) {
                return;
            }

            KhuyenMai km = new KhuyenMai(
                    Integer.parseInt(maKM),
                    ngayBatDau,
                    ngayKetThuc,
                    Integer.parseInt(tiLeKhuyenMai),
                    moTa,
                    listLP);

            khuyenMaiDAO.createKhuyenMai(km);

            String listP = "";
            for (String loaip : km.getLoaiPhong()) {
                listP += loaip + ", ";
            }
            listP = listP.substring(0, listP.length() - 2);
            Object[] rowData = {
                km.getMaKhuyenMai(),
                beginText,
                endText,
                km.getMoTa(),
                km.getTiLeKhuyenMai(),
                listP
            };
            this.model.addRow(rowData);

            JOptionPane.showMessageDialog(this, "Thêm thành công");

        }

    }//GEN-LAST:event_btn_ThemMouseClicked

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

    private void area_ghichuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_ghichuFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_area_ghichuFocusGained

    private void area_ghichuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_ghichuFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_area_ghichuFocusLost

    private void btn_SuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMouseClicked
        this.model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();
        String maKM = jTable1.getValueAt(selectedRow, 0).toString();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date ngayBatDau = txt_NgayBatDau.getDate();
        Date ngayKetThuc = txt_NgayDi.getDate();
        String tiLeKhuyenMai = txt_TiLeKhuyenMai.getText();
        String moTa = area_ghichu.getText();

        List<String> listLP = new ArrayList<String>();
        if (checkBox_Deluxe.isSelected()) {
            listLP.add("Deluxe");
        }
        if (checkBox_Family.isSelected()) {
            listLP.add("Family");
        }
        if (checkBox_Standard.isSelected()) {
            listLP.add("Standard");
        }
        if (checkBox_Suite.isSelected()) {
            listLP.add("Suite");
        }

        boolean validate = validateForm(ngayBatDau, ngayKetThuc, tiLeKhuyenMai, moTa, listLP);

        String beginText = outputFormat.format(ngayBatDau);
        String endText = outputFormat.format(ngayKetThuc);
        if (!checkInvaildDate(beginText, endText)) {
            return;
        }

        if (validate) {

            KhuyenMai km = new KhuyenMai(
                    Integer.parseInt(maKM),
                    ngayBatDau,
                    ngayKetThuc,
                    Integer.parseInt(txt_TiLeKhuyenMai.getText()),
                    moTa,
                    listLP);
            khuyenMaiDAO.updateKhuyenMai(Integer.parseInt(maKM), km);

            String listP = "";
            for (String loaip : km.getLoaiPhong()) {
                listP += loaip + ", ";
            }
            listP = listP.substring(0, listP.length() - 2);

            jTable1.setValueAt(beginText, selectedRow, 1);
            jTable1.setValueAt(endText, selectedRow, 2);
            jTable1.setValueAt(km.getMoTa(), selectedRow, 3);
            jTable1.setValueAt(km.getTiLeKhuyenMai(), selectedRow, 4);
            jTable1.setValueAt(listP, selectedRow, 5);
            JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công");
        }


    }//GEN-LAST:event_btn_SuaMouseClicked

    private void btn_XoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMouseClicked
        this.model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow != -1) {
            String maKH = jTable1.getValueAt(selectedRow, 0).toString();

            khuyenMaiDAO.deleteKhuyenMai(Integer.parseInt(maKH));

            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Xóa thành công");

        }
    }//GEN-LAST:event_btn_XoaMouseClicked

    private void btn_LamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMouseClicked

        txt_NgayBatDau.setDate(null);
        txt_NgayDi.setDate(null);
        txt_TiLeKhuyenMai.setText("");
        area_ghichu.setText("");
        checkBox_Deluxe.setSelected(false);
        checkBox_Family.setSelected(false);
        checkBox_Standard.setSelected(false);
        checkBox_Suite.setSelected(false);
        List<Document> list_KM = new ArrayList<Document>();
        try {
            list_KM = khuyenMaiDAO.findKhuyenMai(null, null, 0, "", new ArrayList<String>());
        } catch (ParseException ex) {
            Logger.getLogger(NhanVien_KhuyenMai_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!list_KM.isEmpty()) {
            this.model.setRowCount(0);
            for (Document km : list_KM) {
                String maKhuyenMai = km.getInteger("maKhuyenMai").toString();
                Date ngaybatdau = km.getDate("ngayBatDau");
                Date ngayketthuc = km.getDate("ngayKetThuc");
                int tile = km.getInteger("tiLeKhuyenMai");
                String mota = km.getString("moTa");
                List<String> loaiPhong = km.getList("loaiPhong", String.class);
//                
                String listLP = "";
                for (String loaip : loaiPhong) {
                    listLP += loaip + ", ";
                }
                listLP = listLP.substring(0, listLP.length() - 2);

                this.model.addRow(new Object[]{
                    maKhuyenMai,
                    dateFormat.format(ngaybatdau),
                    dateFormat.format(ngayketthuc),
                    mota,
                    tile,
                    listLP
                });

            }
        }
    }//GEN-LAST:event_btn_LamMoiMouseClicked

    private void btn_TimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMouseClicked
        this.model = (DefaultTableModel) jTable1.getModel();
        Date ngayBatDau = txt_NgayBatDau.getDate();
        Date ngayKetThuc = txt_NgayDi.getDate();
        int tiLeKhuyenMai = Integer.parseInt(!txt_TiLeKhuyenMai.getText().equals("") ? txt_TiLeKhuyenMai.getText() : "0");
        String moTa = area_ghichu.getText();

        List<String> listLP = new ArrayList<String>();
        if (checkBox_Deluxe.isSelected()) {
            listLP.add("Deluxe");
        }
        if (checkBox_Family.isSelected()) {
            listLP.add("Family");
        }
        if (checkBox_Standard.isSelected()) {
            listLP.add("Standard");
        }
        if (checkBox_Suite.isSelected()) {
            listLP.add("Suite");
        }

        List<Document> list_KM = new ArrayList<Document>();
        try {
            list_KM = khuyenMaiDAO.findKhuyenMai(ngayBatDau, ngayKetThuc, tiLeKhuyenMai, moTa, listLP);
        } catch (ParseException ex) {
            Logger.getLogger(NhanVien_KhuyenMai_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!list_KM.isEmpty()) {
            this.model.setRowCount(0);
            for (Document km : list_KM) {
                String maKhuyenMai = km.getInteger("maKhuyenMai").toString();
                Date ngaybatdau = km.getDate("ngayBatDau");
                Date ngayketthuc = km.getDate("ngayKetThuc");
                int tile = km.getInteger("tiLeKhuyenMai");
                String mota = km.getString("moTa");
                List<String> loaiPhong = km.getList("loaiPhong", String.class);
//                
                String listP = "";
                for (String loaip : loaiPhong) {
                    listP += loaip + ", ";
                }
                listP = listP.substring(0, listP.length() - 2);

                this.model.addRow(new Object[]{
                    maKhuyenMai,
                    dateFormat.format(ngaybatdau),
                    dateFormat.format(ngayketthuc),
                    mota,
                    tile,
                    listP
                });

            }
        }


    }//GEN-LAST:event_btn_TimMouseClicked

    private void txt_TiLeKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TiLeKhuyenMaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TiLeKhuyenMaiActionPerformed

    private void checkBox_SuiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_SuiteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_checkBox_SuiteActionPerformed

    private void checkBox_DeluxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_DeluxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_DeluxeActionPerformed

    private void checkBox_FamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_FamilyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_FamilyActionPerformed

    private void checkBox_StandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_StandardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_StandardActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
        new TrangCaNhan(nhanVien_DangSuDung).setVisible(true);
    }//GEN-LAST:event_jPanel2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel BangChonLoaiPhong;
    private javax.swing.JPanel ThongTinKhuyenMai;
    private javax.swing.JTextArea area_ghichu;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Them;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    public static javax.swing.JCheckBox checkBox_Deluxe;
    public static javax.swing.JCheckBox checkBox_Family;
    public static javax.swing.JCheckBox checkBox_Standard;
    public static javax.swing.JCheckBox checkBox_Suite;
    private GUI.CirclePanel_Atatar circlePanel1;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_Avatar;
    private com.toedter.calendar.JDateChooser txt_NgayBatDau;
    private com.toedter.calendar.JDateChooser txt_NgayDi;
    private javax.swing.JTextField txt_TiLeKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
