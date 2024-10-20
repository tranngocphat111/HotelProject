/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Functions.ImageScale;
import com.mongodb.client.MongoDatabase;
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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.TienNghiDAO;
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
    private MongoDBConnection connection = new MongoDBConnection();
    private MongoDatabase database = connection.getDatabase();
    
    private TienNghiDAO tienNghiDAO = new TienNghiDAO(database);
    
    private byte[] hinhAnh = null;
    
    public NhanVien_TienNghi_GUI() {
        initComponents();
        ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
        list_btn.add(btn_them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
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
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_tienNghi = new javax.swing.JTextField();
        txt_moTa = new javax.swing.JTextField();
        chonAnh_btn = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_them = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        label_Anh = new javax.swing.JLabel();
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
        jLabel2.setBounds(100, 130, 250, 32);

        ThongTinTienNghi.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinTienNghi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinTienNghi.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên tiện nghi");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Mô tả");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Hình ảnh");

        txt_tienNghi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_tienNghi.setPreferredSize(new java.awt.Dimension(80, 32));
        txt_tienNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienNghiActionPerformed(evt);
            }
        });

        txt_moTa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_moTa.setPreferredSize(new java.awt.Dimension(80, 32));
        txt_moTa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_moTaActionPerformed(evt);
            }
        });

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
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );
        chonAnh_btnLayout.setVerticalGroup(
            chonAnh_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ThongTinTienNghiLayout = new javax.swing.GroupLayout(ThongTinTienNghi);
        ThongTinTienNghi.setLayout(ThongTinTienNghiLayout);
        ThongTinTienNghiLayout.setHorizontalGroup(
            ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinTienNghiLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txt_tienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_moTa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(chonAnh_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        ThongTinTienNghiLayout.setVerticalGroup(
            ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinTienNghiLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinTienNghiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tienNghi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(chonAnh_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_moTa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinTienNghi);
        ThongTinTienNghi.setBounds(100, 180, 810, 140);

        btn_Tim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(640, 380, 140, 40);

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
        btn_them.setBounds(100, 380, 140, 40);

        btn_Xoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        btn_Xoa.setBounds(460, 380, 140, 40);

        btn_Sua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        btn_Sua.setBounds(280, 380, 140, 40);

        JTableHeader header = jTable1.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable1.setModel(duaDataVaoModel(tienNghiDAO.getAllTienNghi()));
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(90, 450, 1120, 290);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_Anh, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_Anh, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(940, 180, 270, 240);

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

    private void txt_tienNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienNghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienNghiActionPerformed
    
    static DefaultTableModel duaDataVaoModel(List<TienNghi> list_TienNghi) {
        
        
        Object[][] object = new Object[list_TienNghi.size()][4];
        for(int i = 0; i < object.length; i++) {
            TienNghi x = list_TienNghi.get(i);
            object[i] = new Object[]{
                x.getMaTienNghi(), x.getTenTienNghi(), x.getMoTa()
            };
//            System.out.println(object[i][0]);
//            System.out.println(object[i][1]);
//            System.out.println(object[i][2]);
            
        }
        String[] table_header = new String [] {
                "Mã tiện nghi", "Tên tiện nghi", "Mô tả"
        };
        
        return new DefaultTableModel(object, table_header);
        
    }
    
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
            jTable1.setModel(duaDataVaoModel(tienNghiDAO.getAllTienNghi()));
            
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
            
            if(jTable1.getSelectedRow() != -1) {
                String tenTienNghi = txt_tienNghi.getText();
                TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
                System.out.println(x.toString());
                int maDV = x.getMaTienNghi();
                String moTaDV = txt_moTa.getText();
                byte[] hinhAnh = this.hinhAnh;
                System.out.println("Trên label" + String.format("%d %s", maDV, moTaDV));
                TienNghi y = new TienNghi(maDV, moTaDV, moTaDV, hinhAnh);
                
                tienNghiDAO.suaTienNghi(x, y);
//                System.out.println(y.toString());
                jTable1.setModel(duaDataVaoModel(tienNghiDAO.getAllTienNghi()));
                                
//                txt_TienNghi.setEnabled(true);
                txt_tienNghi.setText("");
                txt_moTa.setText("");
                label_Anh.setIcon(null);
                
            } else {
                throw new Exception("Chưa chọn dịch vụ để sửa đổi");
            }
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi dữ liệu", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_SuaMouseClicked

    private void btn_XoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        
        if(row != -1) {
//            int maDV = Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString());
            String tenDV = jTable1.getModel().getValueAt(row, 1).toString();
//            String moTa = jTable1.getModel().getValueAt(row, 2).toString();
//            int donGia = Integer.parseInt(jTable1.getModel().getValueAt(row, 3).toString());

            TienNghi x = tienNghiDAO.timTienNghi(tenDV);
            
            tienNghiDAO.xoaTienNghi(x);
            
            jTable1.setModel(duaDataVaoModel(tienNghiDAO.getAllTienNghi()));
        }
    }//GEN-LAST:event_btn_XoaMouseClicked

    private void btn_TimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMouseClicked
        // TODO add your handling code here:
        String tenTienNghi = txt_tienNghi.getText();
        if(tenTienNghi.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên tiện nghi cần tìm", "Chưa nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
        }
        TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
        System.out.println(x.equals(null));
        int viTriCuaX = tienNghiDAO.getAllTienNghi().indexOf(x);
        hinhAnh = x.getHinhAnh();
        if(viTriCuaX != -1) {
            jTable1.setRowSelectionInterval(viTriCuaX, viTriCuaX);
        } else {
            JOptionPane.showMessageDialog(this, "Không có tiện nghi có tên " + tenTienNghi, "Không tìm thấy dữ liệu", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_TimMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        
        if(row != -1) {
            String tenTienNghi = jTable1.getModel().getValueAt(row, 1).toString();
            
            TienNghi x = tienNghiDAO.timTienNghi(tenTienNghi);
//            System.out.println(x.toString());
//            System.out.println(x.getHinhAnh());
            txt_tienNghi.setText(x.getTenTienNghi());
            txt_moTa.setText(x.getMoTa());
            
            hinhAnh = x.getHinhAnh();
            System.out.println(hinhAnh == null);
//          
            ImageIcon icon = new ImageScale().load1(new ImageIcon(hinhAnh), label_Anh.getWidth(), label_Anh.getHeight());

            System.out.println("Không gặp lỗi");
            label_Anh.setIcon(icon);
            
//            txt_DichVu.setEnabled(false);
            
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel ThongTinTienNghi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private keeptoo.KGradientPanel btn_them;
    private keeptoo.KGradientPanel chonAnh_btn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_Anh;
    private javax.swing.JTextField txt_moTa;
    private javax.swing.JTextField txt_tienNghi;
    // End of variables declaration//GEN-END:variables
}
