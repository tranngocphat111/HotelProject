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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.LoaiPhongDAO;
import model.DAO.TienNghiDAO;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.DTO.TienNghi;
import model.MongoDBConnection;
import test.convertImage;

/**
 *
 * @author Admin
 */
public class NhanVien_TienNghi_GUI extends javax.swing.JInternalFrame{

    /**
     * Creates new form LeTan_DatPhong_GUI
     */

    private DefaultTableModel model;
    private TienNghiDAO tienNghiDAO = new TienNghiDAO(database);
    private LoaiPhongDAO loaiphongDAO = new LoaiPhongDAO(database);
    private byte[] hinhAnh = null;
    private DefaultTableCellRenderer centerRenderer;
    
    public NhanVien_TienNghi_GUI() {
        initComponents();


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
        jLabel2 = new javax.swing.JLabel();
        ThongTinTienNghi = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_tienNghi = new javax.swing.JTextField();
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
        txt_moTa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_TienNghi = new javax.swing.JTable();
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

        javax.swing.GroupLayout ThongTinTienNghiLayout = new javax.swing.GroupLayout(ThongTinTienNghi);
        ThongTinTienNghi.setLayout(ThongTinTienNghiLayout);
        ThongTinTienNghiLayout.setHorizontalGroup(
            ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinTienNghiLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_tienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        ThongTinTienNghiLayout.setVerticalGroup(
            ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinTienNghiLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_tienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinTienNghi);
        ThongTinTienNghi.setBounds(80, 120, 490, 160);

        btn_lammoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_lammoi.setkGradientFocus(250);
        btn_lammoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_lammoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_lammoiMouseClicked(evt);
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
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_lammoi);
        btn_lammoi.setBounds(610, 330, 140, 50);

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
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(780, 330, 140, 50);

        btn_them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_them.setkGradientFocus(250);
        btn_them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themMouseClicked(evt);
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
        btn_them.setBounds(80, 330, 140, 50);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_XoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Xoa);
        btn_Xoa.setBounds(260, 330, 140, 50);

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
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Sua);
        btn_Sua.setBounds(430, 330, 140, 50);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_Anh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(label_Anh, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(960, 120, 240, 160);

        chonAnh_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        chonAnh_btn.setkEndColor(new java.awt.Color(255, 222, 89));
        chonAnh_btn.setkGradientFocus(250);
        chonAnh_btn.setkStartColor(new java.awt.Color(225, 176, 27));
        chonAnh_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chonAnh_btnMouseClicked(evt);
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
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        chonAnh_btnLayout.setVerticalGroup(
            chonAnh_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(chonAnh_btn);
        chonAnh_btn.setBounds(960, 331, 240, 50);

        txt_moTa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_moTa.setPreferredSize(new java.awt.Dimension(80, 32));
        txt_moTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_moTaActionPerformed(evt);
            }
        });
        jPanel1.add(txt_moTa);
        txt_moTa.setBounds(610, 120, 310, 160);

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_TienNghi.setRowHeight(30);
        table_TienNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_TienNghiMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_TienNghi);
        if (table_TienNghi.getColumnModel().getColumnCount() > 0) {
            table_TienNghi.getColumnModel().getColumn(0).setPreferredWidth(300);
            table_TienNghi.getColumnModel().getColumn(0).setMaxWidth(300);
            table_TienNghi.getColumnModel().getColumn(1).setPreferredWidth(300);
            table_TienNghi.getColumnModel().getColumn(1).setMaxWidth(300);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 450, 1130, 220);

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
    
//    static DefaultTableModel duaDataVaoModel(List<TienNghi> list_TienNghi) {
//        
//        
//        Object[][] object = new Object[list_TienNghi.size()][4];
//        for(int i = 0; i < object.length; i++) {
//            TienNghi x = list_TienNghi.get(i);
//            object[i] = new Object[]{
//                x.getMaTienNghi(), x.getTenTienNghi(), x.getMoTa()
//            };
////            System.out.println(object[i][0]);
////            System.out.println(object[i][1]);
////            System.out.println(object[i][2]);
//            
//        }
//        String[] table_header = new String [] {
//                "Mã tiện nghi", "Tên tiện nghi", "Mô tả"
//        };
//        
//        return new DefaultTableModel(object, table_header);
//        
//    }
    
    private void txt_moTaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_moTaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_moTaActionPerformed

    private void chonAnh_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chonAnh_btnMouseClicked
        // TODO add your handling code here:
        JFileChooser frame_chonAnh = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File ảnh", "png", "jpg", "jpeg", "gif");
        frame_chonAnh.setFileFilter(filter);
        frame_chonAnh.setAcceptAllFileFilterUsed(false);
        
        
        int returnValue = frame_chonAnh.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION) {
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
    }//GEN-LAST:event_chonAnh_btnMouseClicked

    private void btn_themMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themMouseClicked
        // TODO add your handling code here:
        
        try {
            if(txt_tienNghi.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập tên dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Chưa nhập tên dịch vụ!");
            }
            if(txt_moTa.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa nhập mô tả dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
                throw new Exception("Chưa nhập mô tả dịch vụ!");
            }
            
            if(hinhAnh == null) {
                throw new Exception("Chưa chọn ảnh dịch vụ!");
            }
//            if(txtHinhAnh.getText().isBlank()) {
//                JOptionPane.showMessageDialog(this, "Chưa chọn hình ảnh dịch vụ!", "Trống dữ liệu", JOptionPane.ERROR_MESSAGE);
//                throw new Exception("Chưa nhập đơn giá dịch vụ!");
//            }
            String tenTienNghi = txt_tienNghi.getText();
            
            if(tienNghiDAO.timTienNghi(tenTienNghi) != null) {
//                JOptionPane.showMessageDialog(this, "Đã có dịch vụ có tên " + tenTienNghi, "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                throw new ExceptionInInitializerError("Trùng dữ liệu");
            }
            
            int maTienNghi = tienNghiDAO.getAllTienNghi().getLast().getMaTienNghi() + 1;
            String moTaTienNghi = txt_moTa.getText();
            
            byte[] img = hinhAnh;
            
            //Tạo ra một dịch vụ mới
            TienNghi x = new TienNghi(maTienNghi, tenTienNghi, moTaTienNghi, hinhAnh);
            //Đưa dữ liệu vào database
            tienNghiDAO.createTienNghi(x);
            
            
            //Reset lại dữ liệu trong table
            
            
            //Đưa data vào table 
            DocDuLieuLenTableTienNghi(tienNghiDAO.getAllTienNghi());
            
            txt_tienNghi.setText("");
            txt_moTa.setText("");
//            hinhAnh = null;
            label_Anh.setIcon(null);
            txt_tienNghi.setEnabled(true);
            
        }   
        catch (ExceptionInInitializerError e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Dữ liệu bị thiếu", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_themMouseClicked

    private void btn_SuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMouseClicked
        // TODO add your handling code here:
        try {
            
            if(table_TienNghi.getSelectedRow() != -1) {
                String tenTienNghi = table_TienNghi.getModel().getValueAt(table_TienNghi.getSelectedRow(), 1).toString();
                TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
                
                
                
                System.out.println(x.toString());
                tenTienNghi = txt_tienNghi.getText();
                int maDV = x.getMaTienNghi();
                String moTaDV = txt_moTa.getText();
                byte[] hinhAnh = this.hinhAnh;
                
                
                
                System.out.println("Trên label" + String.format("%d %s", maDV, moTaDV));
                TienNghi y = new TienNghi(maDV, tenTienNghi, moTaDV, hinhAnh);
                
                tienNghiDAO.suaTienNghi(x, y);
//                System.out.println(y.toString());
                DocDuLieuLenTableTienNghi(tienNghiDAO.getAllTienNghi());
                                
//                txt_TienNghi.setEnabled(true);
                txt_tienNghi.setText("");
                txt_moTa.setText("");
                label_Anh.setIcon(null);
                for(LoaiPhong lp : loaiphongDAO.getAllLoaiPhong()){
                    for(int i = 0; i < lp.getTienNghis().size(); i++){
                        if(maDV == lp.getTienNghis().get(i).getMaTienNghi()){
                            lp.getTienNghis().set(i, y);
                        }
                    }
                    loaiphongDAO.updateLoaiPhong(lp);
                }
                
        }
            
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_SuaMouseClicked

    private void btn_XoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMouseClicked
        // TODO add your handling code here:
        int row = table_TienNghi.getSelectedRow();
        
        if(row != -1) {
//            int maDV = Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString());
            String tenDV = table_TienNghi.getModel().getValueAt(row, 1).toString();
//            String moTa = jTable1.getModel().getValueAt(row, 2).toString();
//            int donGia = Integer.parseInt(jTable1.getModel().getValueAt(row, 3).toString());

            TienNghi x = tienNghiDAO.timTienNghi(tenDV);
            
            tienNghiDAO.xoaTienNghi(x);
            
            DocDuLieuLenTableTienNghi(tienNghiDAO.getAllTienNghi());
        }
    }//GEN-LAST:event_btn_XoaMouseClicked

    private void btn_TimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMouseClicked
        // TODO add your handling code here:
        String tenTienNghi = txt_tienNghi.getText().toString();
        
//        System.out.println(tenTienNghi);
        if(tenTienNghi.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên tiện nghi cần tìm", "Chưa nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
        }
        TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
//        System.out.println(x == null);
        int viTriCuaX = tienNghiDAO.getAllTienNghi().indexOf(x);
        hinhAnh = x.getHinhAnh();
        label_Anh.setIcon(new ImageScale().load1(new ImageIcon(hinhAnh), label_Anh.getWidth(), label_Anh.getHeight()));
        txt_moTa.setText(x.getMoTa());
        if(viTriCuaX != -1) {
            table_TienNghi.setRowSelectionInterval(viTriCuaX, viTriCuaX);
        } else {
            JOptionPane.showMessageDialog(this, "Không có tiện nghi có tên " + tenTienNghi, "Không tìm thấy dữ liệu", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_TimMouseClicked

    private void btn_lammoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lammoiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_lammoiMouseClicked

    private void table_TienNghiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_TienNghiMousePressed
        // TODO add your handling code here:
         int row = table_TienNghi.getSelectedRow();

        if(row != -1) {
            String tenTienNghi = table_TienNghi.getModel().getValueAt(row, 1).toString();

            TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
//            System.out.println(x.toString());

            //            System.out.println(x.getHinhAnh());
            txt_tienNghi.setText(x.getTenTienNghi());
            txt_moTa.setText(x.getMoTa());

            hinhAnh = x.getHinhAnh();
            System.out.println(hinhAnh == null);
            ImageIcon icon = new ImageScale().load1(new ImageIcon(hinhAnh), label_Anh.getWidth(), label_Anh.getHeight());

//            System.out.println("Không gặp lỗi");
            label_Anh.setIcon(icon);

            //            txt_DichVu.setEnabled(false);

        }
        
    }//GEN-LAST:event_table_TienNghiMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel ThongTinTienNghi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private keeptoo.KGradientPanel btn_lammoi;
    private keeptoo.KGradientPanel btn_them;
    private keeptoo.KGradientPanel chonAnh_btn;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_Anh;
    private javax.swing.JTable table_TienNghi;
    private javax.swing.JTextField txt_moTa;
    private javax.swing.JTextField txt_tienNghi;
    // End of variables declaration//GEN-END:variables
}
