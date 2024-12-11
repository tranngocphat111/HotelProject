/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_DatPhong_GUI.nguoiDaiDien;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import model.DAO.KhachHangDAO;
import model.DTO.KhachHang;

/**
 *
 * @author Admin
 */
public class LeTan_DatPhong_NguoiDaiDien_GUI extends javax.swing.JDialog {

    KhachHangDAO khachHang_dao = new KhachHangDAO(database);
    DefaultTableCellRenderer centerRenderer;
    DecimalFormat df = new DecimalFormat("#,##0");
    List<KhachHang> list_KhachHang = new ArrayList<>();

    /**
     * Creates new form LeTan_
     */
    public LeTan_DatPhong_NguoiDaiDien_GUI(JFrame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);

        list_KhachHang = khachHang_dao.getAllKhachHang();
        initComponents();

        if (LeTan_DatPhong_GUI.nguoiDaiDien != null) {
            txt_CCCD.setText(LeTan_DatPhong_GUI.nguoiDaiDien.getCCCD());
            txt_HoTen.setText(LeTan_DatPhong_GUI.nguoiDaiDien.getTenKhachHang());
            txt_SDT.setText(LeTan_DatPhong_GUI.nguoiDaiDien.getSoDienThoai());
            txt_Email.setText(LeTan_DatPhong_GUI.nguoiDaiDien.getEmail());
            cb_GioiTinh.setSelectedItem(LeTan_DatPhong_GUI.nguoiDaiDien.getGioiTinh());
            cb_QuocTich.setSelectedItem(LeTan_DatPhong_GUI.nguoiDaiDien.getQuocTich());
        }

        setLocationRelativeTo(null);
    }

//    public void DocDuLieuLenTable(List<Phong> list_PhongTrong) {
//        for (Phong phong : list_PhongTrong) {
//            LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
//            String list_tienNghi = getListTienNghi(loaiPhong.getTienNghis());
//            model.addRow(new Object[]{
//                phong.getMaPhong(),
//                phong.getTang(),
//                loaiPhong.getTenLoaiPhong(),
//                loaiPhong.getLoaiGiuong(),
//                loaiPhong.getDienTich() + " m2",
//                list_tienNghi,
//                phong.getMoTa(),
//                df.format(loaiPhong.getDonGia()) + " VND",
//                loaiPhong.getSoKhachToiDa()});
//        }
//
//        for (int i = 0; i < Table_Phong.getColumnCount(); i++) {
//            Table_Phong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//        }
//
//    }
//
//    public String getListTienNghi(List<TienNghi> list_tienNghi) {
//        String list = "";
//
//        for (TienNghi tn : list_tienNghi) {
//            list = list + tn.getTenTienNghi() + ", ";
//        }
//        list = list.substring(0, list.length() - 2);
//        return list;
//    }
//
//    public List<Phong> getAllPhongByLoaiPhong(List<Phong> list_PhongTrong, int loaiPhong) {
//        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
//        for (Phong phong : list_PhongTrong) {
//            if (phong.getLoaiPhong() == loaiPhong) {
//                list_PhongByLoai.add(phong);
//            }
//
//        }
//
//        return list_PhongByLoai;
//    }
//
//    public List<Phong> getAllPhongByTang(List<Phong> list_PhongTrong, int tang) {
//        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
//        for (Phong phong : list_PhongTrong) {
//            if (phong.getTang() == tang) {
//                list_PhongByLoai.add(phong);
//            }
//
//        }
//
//        return list_PhongByLoai;
//    }
//
//    public List<Phong> getAllPhongByDonGia(List<Phong> list_PhongTrong, int KhoangDonGia) {
//        List<Phong> list_PhongByLoai = new ArrayList<Phong>();
//
//        if (KhoangDonGia == 1) {
//            for (Phong phong : list_PhongTrong) {
//                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
//                if (loaiPhong.getDonGia() >= 500000 && loaiPhong.getDonGia() <= 1000000) {
//                    list_PhongByLoai.add(phong);
//                }
//
//            }
//            return list_PhongByLoai;
//
//        }
//
//        if (KhoangDonGia == 2) {
//            for (Phong phong : list_PhongTrong) {
//                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
//                if (loaiPhong.getDonGia() >= 1000000 && loaiPhong.getDonGia() <= 2000000) {
//                    list_PhongByLoai.add(phong);
//                }
//
//            }
//            return list_PhongByLoai;
//
//        }
//
//        if (KhoangDonGia == 3) {
//            for (Phong phong : list_PhongTrong) {
//                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong.getLoaiPhong());
//                if (loaiPhong.getDonGia() >= 2000000 && loaiPhong.getDonGia() <= 5000000) {
//                    list_PhongByLoai.add(phong);
//                }
//
//            }
//            return list_PhongByLoai;
//
//        }
//
//        return list_PhongByLoai;
//    }
    public boolean validateInput(String cccd, String hoTen, String soDienThoai, String email) {

        if (cccd == null || cccd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CCCD không được để trống.");
            txt_CCCD.requestFocus();
            return false;
        } else if (cccd.length() < 12 || cccd.length() > 12) {
            JOptionPane.showMessageDialog(null, "CCCD phải có 12 số.");
            txt_CCCD.requestFocus();
            return false;
        }

        if (hoTen == null || hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ tên không được để trống.");
            txt_HoTen.requestFocus();
            return false;
        } else if (!hoTen.matches("^[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯ][a-zàáâãèéêìíòóôõùúăđĩũơưạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹý]+( [A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯ][a-zàáâãèéêìíòóôõùúăđĩũơưạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹý]+)*$")) {
            JOptionPane.showMessageDialog(null, "Họ tên phải viết hoa ở đầu mỗi chữ.");
            txt_HoTen.requestFocus();
            return false;
        }

        if (soDienThoai == null || soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống.");
            txt_SDT.requestFocus();
            return false;
        } else if (soDienThoai.length() < 10 || soDienThoai.length() > 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số.");
            txt_SDT.requestFocus();
            return false;
        }

        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email không được để trống.");
            txt_Email.requestFocus();
            return false;
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Email phải đúng cấu trúc example1@gmail.com");
            txt_Email.requestFocus();
            return false;
        }

        return true;
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
        btn_XacNhan = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Huy = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_QuocTich = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cb_GioiTinh = new javax.swing.JComboBox<>();
        txt_SDT = new javax.swing.JTextField();
        txt_CCCD = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        BackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(844, 340));
        jPanel1.setLayout(null);

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
        jLabel19.setText("Xác nhận");
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
        btn_XacNhan.setBounds(490, 260, 150, 45);

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
        jLabel20.setText("Hủy");
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
        btn_Huy.setBounds(680, 260, 140, 46);

        jLabel2.setBackground(new java.awt.Color(255, 209, 84));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Người đại diện");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 20, 250, 25);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CCCD/Passport");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Họ và tên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giới tinh");

        cb_QuocTich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Việt Nam", "Mỹ", "Hàn Quốc", "Trung Quốc", " " }));
        cb_QuocTich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_QuocTichActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số điện thoại");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Email");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Quốc tịch");

        cb_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cb_GioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_GioiTinhActionPerformed(evt);
            }
        });

        txt_SDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_SDTFocusLost(evt);
            }
        });
        txt_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTActionPerformed(evt);
            }
        });

        txt_CCCD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_CCCDFocusLost(evt);
            }
        });
        txt_CCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CCCDActionPerformed(evt);
            }
        });

        txt_Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_EmailFocusLost(evt);
            }
        });
        txt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailActionPerformed(evt);
            }
        });

        txt_HoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_HoTenFocusLost(evt);
            }
        });
        txt_HoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HoTenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(36, 36, 36)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(cb_QuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_QuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(40, 60, 780, 170);

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup_DatPhong_chonPhong.png"))); // NOI18N
        BackGround.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        BackGround.setPreferredSize(new java.awt.Dimension(1500, 450));
        jPanel1.add(BackGround);
        BackGround.setBounds(0, 0, 850, 340);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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

    private void btn_XacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XacNhanMousePressed
        // TODO add your handling code here:
        if (validateInput(txt_CCCD.getText(), txt_HoTen.getText(), txt_SDT.getText(), txt_Email.getText())) {
            //        Kiểm tra khách hàng cũ hay mới

            KhachHang kh = new KhachHang(
                    list_KhachHang.getLast().getMaKhachHang() + 1,
                    txt_HoTen.getText(),
                    txt_SDT.getText(),
                    txt_CCCD.getText(),
                    cb_QuocTich.getSelectedItem().toString(),
                    cb_GioiTinh.getSelectedItem().toString(),
                    txt_Email.getText());

            if (khachHang_dao.getKhachHangByCCCD(kh.getCCCD()) != null) {
                kh.setMaKhachHang(khachHang_dao.getKhachHangByCCCD(kh.getCCCD()).getMaKhachHang());
                khachHang_dao.updateKhachHang(kh.getMaKhachHang(), kh);
            }else{
                khachHang_dao.createKhachHang(kh);
            }
            LeTan_DatPhong_GUI.nguoiDaiDien = kh;
            LeTan_DatPhong_GUI.txt_TenNguoiDaiDien.setText(kh.getTenKhachHang());
            setVisible(false);
        }

    }//GEN-LAST:event_btn_XacNhanMousePressed

    private void btn_HuyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyMousePressed
        // TODO add your handling code here:
        setVisible(false);

    }//GEN-LAST:event_btn_HuyMousePressed

    private void cb_QuocTichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_QuocTichActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_QuocTichActionPerformed

    private void cb_GioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_GioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_GioiTinhActionPerformed

    private void txt_SDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_SDTFocusLost

    }//GEN-LAST:event_txt_SDTFocusLost

    private void txt_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SDTActionPerformed

    }//GEN-LAST:event_txt_SDTActionPerformed

    private void txt_CCCDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_CCCDFocusLost

    }//GEN-LAST:event_txt_CCCDFocusLost

    private void txt_CCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CCCDActionPerformed

    private void txt_EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EmailFocusLost

    }//GEN-LAST:event_txt_EmailFocusLost

    private void txt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailActionPerformed

    private void txt_HoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_HoTenFocusLost

    }//GEN-LAST:event_txt_HoTenFocusLost

    private void txt_HoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HoTenActionPerformed

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
            java.util.logging.Logger.getLogger(LeTan_DatPhong_NguoiDaiDien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_NguoiDaiDien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_NguoiDaiDien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeTan_DatPhong_NguoiDaiDien_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
//                LeTan_DatPhong_NguoiDaiDien_GUI dialog = new LeTan_DatPhong_NguoiDaiDien_GUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel ThongTinKhachHang;
    private keeptoo.KGradientPanel btn_Huy;
    private keeptoo.KGradientPanel btn_XacNhan;
    private javax.swing.JComboBox<String> cb_GioiTinh;
    private javax.swing.JComboBox<String> cb_QuocTich;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_SDT;
    // End of variables declaration//GEN-END:variables
}
