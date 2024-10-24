/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.TienNghi;
/**
 *
 * @author Admin
 */
public class LeTan_DatPhong_ChonPhong_GUI extends javax.swing.JDialog {

    private LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database);
    private DefaultTableModel model;
    private List<Phong> list_PhongTrong;
    private List<LoaiPhong> list_LoaiPhong = new ArrayList<LoaiPhong>();
    private PhongDAO phong_dao = new PhongDAO(database);
    DefaultTableCellRenderer centerRenderer;
    DecimalFormat df = new DecimalFormat("#,##0");

    /**
     * Creates new form LeTan_
     */
    public LeTan_DatPhong_ChonPhong_GUI(List<Phong> list_Phong, JFrame parent, boolean modal) {
        super(parent, modal);

        list_PhongTrong = list_Phong;
        initComponents();

        JTableHeader header = Table_Phong.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));

        //      căn giữa cho header table  
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);
        //Căn giữa các phần tử trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

        list_LoaiPhong = loaiPhong_dao.getAllLoaiPhong();

        cb_LoaiPhong.addItem("");
        for (LoaiPhong lp : list_LoaiPhong) {
            cb_LoaiPhong.addItem(lp.getTenLoaiPhong());
        }

        model = (DefaultTableModel) Table_Phong.getModel();
        model.setRowCount(0);

        DocDuLieuLenTable(list_PhongTrong);

        cb_LoaiPhong.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Phong> list_Phong = list_PhongTrong;
                if (cb_LoaiPhong.getSelectedItem().toString().equals("")
                        && cb_Tang.getSelectedItem().toString().equals("")
                        && cb_GiaPhong.getSelectedItem().toString().equals("")) {
                    DocDuLieuLenTable(list_Phong);
                    return;
                }
                if (!cb_LoaiPhong.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByLoaiPhong(list_Phong, cb_LoaiPhong.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

                if (!cb_Tang.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByTang(list_Phong, cb_Tang.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

                if (!cb_GiaPhong.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByDonGia(list_Phong, cb_GiaPhong.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

            }
        });

        cb_Tang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Phong> list_Phong = list_PhongTrong;
                if (cb_LoaiPhong.getSelectedItem().toString().equals("")
                        && cb_Tang.getSelectedItem().toString().equals("")
                        && cb_GiaPhong.getSelectedItem().toString().equals("")) {
                    DocDuLieuLenTable(list_Phong);
                    return;
                }
                if (!cb_LoaiPhong.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByLoaiPhong(list_Phong, cb_LoaiPhong.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

                if (!cb_Tang.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByTang(list_Phong, cb_Tang.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

                if (!cb_GiaPhong.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByDonGia(list_Phong, cb_GiaPhong.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

            }
        });

        cb_GiaPhong.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Phong> list_Phong = list_PhongTrong;
                if (cb_LoaiPhong.getSelectedItem().toString().equals("")
                        && cb_Tang.getSelectedItem().toString().equals("")
                        && cb_GiaPhong.getSelectedItem().toString().equals("")) {
                    DocDuLieuLenTable(list_Phong);
                    return;
                }
                if (!cb_LoaiPhong.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByLoaiPhong(list_Phong, cb_LoaiPhong.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

                if (!cb_Tang.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByTang(list_Phong, cb_Tang.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

                if (!cb_GiaPhong.getSelectedItem().toString().equals("")) {
                    list_Phong = getAllPhongByDonGia(list_Phong, cb_GiaPhong.getSelectedIndex());
                    model.setRowCount(0);
                    DocDuLieuLenTable(list_Phong);
                }

            }
        });

        setLocationRelativeTo(null);
    }

    public void DocDuLieuLenTable(List<Phong> list_PhongTrong) {
        for (Phong phong : list_PhongTrong) {
            LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
            String list_tienNghi = getListTienNghi(loaiPhong.getTienNghis());
            model.addRow(new Object[]{
                phong.getMaPhong(),
                phong.getTang(),
                loaiPhong.getTenLoaiPhong(),
                loaiPhong.getLoaiGiuong(),
                loaiPhong.getDienTich() + " m2",
                list_tienNghi,
                phong.getMoTa(),
                df.format(loaiPhong.getDonGia()) + " VND",
                loaiPhong.getSoKhachToiDa()});
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

    public List<Phong> getAllPhongByLoaiPhong(List<Phong> list_PhongTrong, int loaiPhong) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
        for (Phong phong : list_PhongTrong) {
            if (phong.getLoaiPhong() == loaiPhong) {
                list_PhongByLoai.add(phong);
            }

        }

        return list_PhongByLoai;
    }

    public List<Phong> getAllPhongByTang(List<Phong> list_PhongTrong, int tang) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
        for (Phong phong : list_PhongTrong) {
            if (phong.getTang() == tang) {
                list_PhongByLoai.add(phong);
            }

        }

        return list_PhongByLoai;
    }

    public List<Phong> getAllPhongByDonGia(List<Phong> list_PhongTrong, int KhoangDonGia) {
        List<Phong> list_PhongByLoai = new ArrayList<Phong>();

        if (KhoangDonGia == 1) {
            for (Phong phong : list_PhongTrong) {
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
                if (loaiPhong.getDonGia() >= 500000 && loaiPhong.getDonGia() <= 1000000) {
                    list_PhongByLoai.add(phong);
                }

            }
            return list_PhongByLoai;

        }

        if (KhoangDonGia == 2) {
            for (Phong phong : list_PhongTrong) {
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
                if (loaiPhong.getDonGia() >= 1000000 && loaiPhong.getDonGia() <= 2000000) {
                    list_PhongByLoai.add(phong);
                }

            }
            return list_PhongByLoai;

        }

        if (KhoangDonGia == 3) {
            for (Phong phong : list_PhongTrong) {
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
                if (loaiPhong.getDonGia() >= 2000000 && loaiPhong.getDonGia() <= 5000000) {
                    list_PhongByLoai.add(phong);
                }

            }
            return list_PhongByLoai;

        }

        return list_PhongByLoai;
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
        scroll = new javax.swing.JScrollPane();
        Table_Phong = new javax.swing.JTable();
        btn_XacNhan = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Huy = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_LoaiPhong = new javax.swing.JComboBox<>();
        cb_Tang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cb_GiaPhong = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 450));
        jPanel1.setLayout(null);

        Table_Phong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_Phong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phòng", "Số Tầng", "Loại Phòng", "Loại Giường", "Diện Tích", "Tiện Nghi", "Mô tả", "Đơn giá", "SL Khách Tối Đa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_Phong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Table_Phong.setRowHeight(30);
        scroll.setViewportView(Table_Phong);
        if (Table_Phong.getColumnModel().getColumnCount() > 0) {
            Table_Phong.getColumnModel().getColumn(0).setMaxWidth(120);
            Table_Phong.getColumnModel().getColumn(1).setMaxWidth(120);
            Table_Phong.getColumnModel().getColumn(2).setMaxWidth(150);
            Table_Phong.getColumnModel().getColumn(3).setMaxWidth(150);
            Table_Phong.getColumnModel().getColumn(4).setMaxWidth(120);
            Table_Phong.getColumnModel().getColumn(5).setMaxWidth(400);
            Table_Phong.getColumnModel().getColumn(6).setMaxWidth(200);
            Table_Phong.getColumnModel().getColumn(7).setMaxWidth(400);
            Table_Phong.getColumnModel().getColumn(8).setMaxWidth(150);
        }

        jPanel1.add(scroll);
        scroll.setBounds(250, 60, 1220, 290);

        btn_XacNhan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_XacNhan.setkGradientFocus(250);
        btn_XacNhan.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_XacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_XacNhanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_XacNhanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_XacNhanMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_XacNhanMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("XÁC NHẬN");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_XacNhanLayout = new javax.swing.GroupLayout(btn_XacNhan);
        btn_XacNhan.setLayout(btn_XacNhanLayout);
        btn_XacNhanLayout.setHorizontalGroup(
            btn_XacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_XacNhanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        btn_XacNhanLayout.setVerticalGroup(
            btn_XacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_XacNhanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_XacNhan);
        btn_XacNhan.setBounds(1140, 370, 150, 45);

        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setkGradientFocus(250);
        btn_Huy.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Huy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_HuyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_HuyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_HuyMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("HỦY");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyLayout = new javax.swing.GroupLayout(btn_Huy);
        btn_Huy.setLayout(btn_HuyLayout);
        btn_HuyLayout.setHorizontalGroup(
            btn_HuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_HuyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        btn_HuyLayout.setVerticalGroup(
            btn_HuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_HuyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Huy);
        btn_Huy.setBounds(1330, 370, 140, 46);

        jLabel2.setBackground(new java.awt.Color(255, 209, 84));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Tìm phòng");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 20, 250, 25);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Loại phòng");

        cb_LoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaiPhongActionPerformed(evt);
            }
        });

        cb_Tang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3" }));
        cb_Tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TangActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tầng");

        cb_GiaPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "500.000 - 1.000.000 VND", "1.000.000 - 2.000.000 VND", "2.000.000 - 5.000.000 VND" }));
        cb_GiaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_GiaPhongActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giá phòng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_GiaPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Tang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_LoaiPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_GiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(40, 60, 200, 290);

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup_DatPhong_chonPhong.png"))); // NOI18N
        BackGround.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        BackGround.setPreferredSize(new java.awt.Dimension(1500, 450));
        jPanel1.add(BackGround);
        BackGround.setBounds(0, 0, 1500, 450);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_XacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_XacNhanMouseClicked

    private void btn_XacNhanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMouseEntered
        // TODO add your handling code here:
        btn_XacNhan.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_XacNhan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_XacNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_XacNhan.setBorder(null);
    }//GEN-LAST:event_btn_XacNhanMouseEntered

    private void btn_XacNhanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMouseExited
        // TODO add your handling code here:
        btn_XacNhan.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_XacNhan.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_XacNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_XacNhan.setBorder(null);
    }//GEN-LAST:event_btn_XacNhanMouseExited

    private void btn_HuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HuyMouseClicked

    private void btn_HuyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseEntered
        // TODO add your handling code here:
        btn_Huy.setkStartColor(new java.awt.Color(255, 225, 27));
        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Huy.setBorder(null);
    }//GEN-LAST:event_btn_HuyMouseEntered

    private void btn_HuyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMouseExited
        // TODO add your handling code here:
        btn_Huy.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Huy.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Huy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_Huy.setBorder(null);
    }//GEN-LAST:event_btn_HuyMouseExited

    private void cb_LoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_LoaiPhongActionPerformed

    private void cb_TangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_TangActionPerformed

    private void cb_GiaPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_GiaPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_GiaPhongActionPerformed

    private void btn_XacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMousePressed
        // TODO add your handling code here:
        if (Table_Phong.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng");
            return;
        }
        Phong phong = phong_dao.getPhongByMa(Integer.parseInt(model.getValueAt(Table_Phong.getSelectedRow(), 0).toString()));
        int soLuongKhachToiDa = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong()).getSoKhachToiDa();
        if (LeTan_DatPhong_GUI.table_KhachHang.getRowCount() > soLuongKhachToiDa) {
            JOptionPane.showMessageDialog(this, "Số lượng khách đã vượt quá số lượng khách tối đa, vui lòng chọn loại khác");
            return;
        }
        LeTan_DatPhong_GUI.txt_DonGia.setText(df.format(loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong()).getDonGia()) + " VND");
        LeTan_DatPhong_GUI.txt_Phong.setText(phong.getMaPhong() + "");
        LeTan_DatPhong_GUI.txt_Tang.setText(phong.getTang() + "");
        LeTan_DatPhong_GUI.txt_LoaiPhong.setText(loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong()).getTenLoaiPhong());
        LeTan_DatPhong_GUI.area_moTa.setText(phong.getMoTa());
        LeTan_DatPhong_GUI.label_KhachToiDa.setText("Số lượng khách tối đa: " + loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong()).getSoKhachToiDa());

        setVisible(false);
    }//GEN-LAST:event_btn_XacNhanMousePressed

    private void btn_HuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMousePressed
        // TODO add your handling code here:
        setVisible(false);

    }//GEN-LAST:event_btn_HuyMousePressed

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
            java.util.logging.Logger.getLogger(LeTan_DatPhong_ChonPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_ChonPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_ChonPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_ChonPhong_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ dialog = new LeTan_(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_DatPhong_ChonPhong_GUI dialog = new LeTan_DatPhong_ChonPhong_GUI(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackGround;
    private javax.swing.JTable Table_Phong;
    private keeptoo.KGradientPanel btn_Huy;
    private keeptoo.KGradientPanel btn_XacNhan;
    private javax.swing.JComboBox<String> cb_GiaPhong;
    private javax.swing.JComboBox<String> cb_LoaiPhong;
    private javax.swing.JComboBox<String> cb_Tang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
