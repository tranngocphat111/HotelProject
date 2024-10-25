/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import static GUI.DangNhap_GUI.database;
import static GUI.LeTan_ThanhToan_GUI.list_DonDatPhong;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVu;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.LoaiPhong;
import model.DTO.Phong;

/**
 *
 * @author Admin
 */
public class LeTan_ThanhToan_HoaDon_GUI extends javax.swing.JDialog {

    private LoaiPhongDAO loaiPhong_Dao = new LoaiPhongDAO(database);
    private PhongDAO phong_dao = new PhongDAO(database);
    DecimalFormat df = new DecimalFormat("#,##0");

    /**
     * Creates new form LeTan_ThanhToan_HoaDon
     */
    public LeTan_ThanhToan_HoaDon_GUI(HoaDon hoaDon, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();

        Panel_DSPhong.setLayout(new java.awt.GridLayout(getRow(getAllDonDatPhongTheoHoaDon(hoaDon).size()), 1));
        panel_DSDichVu.setLayout(new java.awt.GridLayout(1, 1));
        for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
            LoaiPhong loaiphong = loaiPhong_Dao.getLoaiPhongByMa(phong_dao.getPhongByMa(ddp.getPhong()).getLoaiPhong());
            JPanel Panel_Phong = new JPanel();
            Panel_Phong.setBackground(new java.awt.Color(255, 255, 255));

            JLabel lable_phong = new JLabel();
            lable_phong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            
            lable_phong.setText(ddp.getPhong()+"");

            JLabel lable_loaiphong = new JLabel();
            lable_loaiphong.setText(loaiphong.getTenLoaiPhong());

            JLabel lable_songay = new JLabel();
            lable_songay.setText(getSoluongNgaySuDung(ddp.getNgayNhanPhong(),ddp.getNgayDatPhong()) + "");

            JLabel lable_X = new JLabel();
            lable_X.setText("x");

            JLabel lable_donGia = new JLabel();
            lable_donGia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            lable_donGia.setText(loaiphong.getDonGia() + " VND");

            
            JLabel label_thanhTien = new JLabel();
            label_thanhTien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
            label_thanhTien.setText(ddp.thanhTien() + " VND");

            javax.swing.GroupLayout Panel_PhongLayout = new javax.swing.GroupLayout(Panel_Phong);
            Panel_Phong.setLayout(Panel_PhongLayout);
            Panel_PhongLayout.setHorizontalGroup(
                    Panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_PhongLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(lable_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(lable_loaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(101, 101, 101)
                                    .addComponent(lable_songay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(46, 46, 46)
                                    .addComponent(lable_X, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lable_donGia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(label_thanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(32, Short.MAX_VALUE))
            );
            Panel_PhongLayout.setVerticalGroup(
                    Panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_PhongLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(Panel_PhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lable_phong)
                                            .addComponent(lable_loaiphong)
                                            .addComponent(lable_songay)
                                            .addComponent(lable_X)
                                            .addComponent(lable_donGia)
                                            .addComponent(label_thanhTien))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            Panel_DSPhong.add(Panel_Phong);
        }

//        for (DichVu dichvu : hoaDon) {
//                    panel_dichVu.setBackground(new java.awt.Color(255, 255, 255));
//
//        lable_dichvu_sudung.setText("Buffet");
//
//        lable_dichvu_soluong.setText("3");
//
//        label_x.setText("x");
//
//        lable_dichvu_dongia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
//        lable_dichvu_dongia.setText("500.000đ");
//
//        lable_dichvu_thanhtien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
//        lable_dichvu_thanhtien.setText("1.500.000đ");
//
//        javax.swing.GroupLayout panel_dichVuLayout = new javax.swing.GroupLayout(panel_dichVu);
//        panel_dichVu.setLayout(panel_dichVuLayout);
//        panel_dichVuLayout.setHorizontalGroup(
//            panel_dichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(panel_dichVuLayout.createSequentialGroup()
//                .addGap(17, 17, 17)
//                .addComponent(lable_dichvu_sudung, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(178, 178, 178)
//                .addComponent(lable_dichvu_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(57, 57, 57)
//                .addComponent(label_x, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(lable_dichvu_dongia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
//                .addComponent(lable_dichvu_thanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(31, 31, 31))
//        );
//        panel_dichVuLayout.setVerticalGroup(
//            panel_dichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_dichVuLayout.createSequentialGroup()
//                .addContainerGap(19, Short.MAX_VALUE)
//                .addGroup(panel_dichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(lable_dichvu_sudung)
//                    .addComponent(lable_dichvu_soluong)
//                    .addComponent(label_x)
//                    .addComponent(lable_dichvu_dongia)
//                    .addComponent(lable_dichvu_thanhtien))
//                .addGap(20, 20, 20))
//        );
//
//        panel_DSDichVu.add(panel_dichVu);
//        }
        lable_tongtien.setText(df.format(hoaDon.getTongTien()) + " VND");

        setLocationRelativeTo(null);
    }

    public int getRow(int n) {

        return n / 2 + n % 2;

    }

    public List<DonDatPhong> getAllDonDatPhongTheoHoaDon(HoaDon hoaDon) {
        List<DonDatPhong> list_DDP = new ArrayList<>();
        for (DonDatPhong ddp : list_DonDatPhong) {
            if (ddp.getHoaDon() == hoaDon.getMaHoaDon()) {
                list_DDP.add(ddp);
            }
        }
        return list_DDP;
    }

    public int getSoluongNgaySuDung(Date ngayTra, Date ngayNhan) {
        long diffInMillies = ngayTra.getTime() - ngayNhan.getTime();
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24); // chuyển từ milliseconds sang ngày

        return (int) diffInDays;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lable_mahoadon = new javax.swing.JLabel();
        lable_ngaytao = new javax.swing.JLabel();
        panel_Column1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panel_Column2 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        panel_DSDichVu = new javax.swing.JPanel();
        panel_dichVu = new javax.swing.JPanel();
        lable_dichvu_sudung = new javax.swing.JLabel();
        lable_dichvu_soluong = new javax.swing.JLabel();
        label_x = new javax.swing.JLabel();
        lable_dichvu_dongia = new javax.swing.JLabel();
        lable_dichvu_thanhtien = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        lable_tongtien = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Panel_DSPhong = new javax.swing.JPanel();
        btn_Thoat = new keeptoo.KGradientPanel();
        jLabel46 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(730, 800));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(730, 800));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon _moonhotel.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("MOON HOTEL");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setText("HÓA ĐƠN");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã hóa đơn:");

        lable_mahoadon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_mahoadon.setText("HD001");

        lable_ngaytao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_ngaytao.setText("15/10/2024");

        panel_Column1.setBackground(new java.awt.Color(255, 255, 255));
        panel_Column1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Phòng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Loại phòng ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Số ngày sử dụng");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Đơn giá");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Thành tiền");

        javax.swing.GroupLayout panel_Column1Layout = new javax.swing.GroupLayout(panel_Column1);
        panel_Column1.setLayout(panel_Column1Layout);
        panel_Column1Layout.setHorizontalGroup(
            panel_Column1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Column1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel8)
                .addGap(54, 54, 54)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(20, 20, 20))
        );
        panel_Column1Layout.setVerticalGroup(
            panel_Column1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_Column1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panel_Column1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(21, 21, 21))
        );

        panel_Column2.setBackground(new java.awt.Color(255, 255, 255));
        panel_Column2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,new java.awt.Color(0, 0, 0)));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Dịch vụ sử dụng");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Số lượng");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Đơn giá");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Thành tiền");

        javax.swing.GroupLayout panel_Column2Layout = new javax.swing.GroupLayout(panel_Column2);
        panel_Column2.setLayout(panel_Column2Layout);
        panel_Column2Layout.setHorizontalGroup(
            panel_Column2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Column2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        panel_Column2Layout.setVerticalGroup(
            panel_Column2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_Column2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panel_Column2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panel_DSDichVu.setBackground(new java.awt.Color(255, 255, 255));
        panel_DSDichVu.setPreferredSize(new java.awt.Dimension(604, 100));
        panel_DSDichVu.setLayout(new java.awt.GridLayout(1, 1));

        panel_dichVu.setBackground(new java.awt.Color(255, 255, 255));

        lable_dichvu_sudung.setText("Buffet");

        lable_dichvu_soluong.setText("3");

        label_x.setText("x");

        lable_dichvu_dongia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lable_dichvu_dongia.setText("500.000đ");

        lable_dichvu_thanhtien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lable_dichvu_thanhtien.setText("1.500.000đ");

        javax.swing.GroupLayout panel_dichVuLayout = new javax.swing.GroupLayout(panel_dichVu);
        panel_dichVu.setLayout(panel_dichVuLayout);
        panel_dichVuLayout.setHorizontalGroup(
            panel_dichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_dichVuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lable_dichvu_sudung, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178)
                .addComponent(lable_dichvu_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(label_x, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lable_dichvu_dongia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(lable_dichvu_thanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panel_dichVuLayout.setVerticalGroup(
            panel_dichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_dichVuLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(panel_dichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_dichvu_sudung)
                    .addComponent(lable_dichvu_soluong)
                    .addComponent(label_x)
                    .addComponent(lable_dichvu_dongia)
                    .addComponent(lable_dichvu_thanhtien))
                .addGap(20, 20, 20))
        );

        panel_DSDichVu.add(panel_dichVu);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0,new java.awt.Color(0, 0, 0)));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel44.setText("Tổng tiền");

        lable_tongtien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lable_tongtien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lable_tongtien.setText("19.600.000đ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lable_tongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lable_tongtien))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(684, 150));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        Panel_DSPhong.setBackground(new java.awt.Color(255, 255, 255));
        Panel_DSPhong.setPreferredSize(new java.awt.Dimension(684, 100));
        Panel_DSPhong.setLayout(new java.awt.GridLayout(1, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Panel_DSPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Panel_DSPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lable_ngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(286, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(300, 300, 300))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lable_mahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(panel_Column2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_Column1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_DSDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lable_mahoadon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lable_ngaytao)
                .addGap(54, 54, 54)
                .addComponent(panel_Column1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_Column2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_DSDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        btn_Thoat.setkEndColor(new java.awt.Color(115, 115, 115));
        btn_Thoat.setkGradientFocus(250);
        btn_Thoat.setkStartColor(new java.awt.Color(0, 0, 0));
        btn_Thoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThoatMousePressed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Thoát");
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThoatLayout = new javax.swing.GroupLayout(btn_Thoat);
        btn_Thoat.setLayout(btn_ThoatLayout);
        btn_ThoatLayout.setHorizontalGroup(
            btn_ThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_ThoatLayout.setVerticalGroup(
            btn_ThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(551, Short.MAX_VALUE)
                .addComponent(btn_Thoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(705, Short.MAX_VALUE)
                .addComponent(btn_Thoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMouseEntered
        // TODO add your handling code here:
        btn_Thoat.setkStartColor(new java.awt.Color(115, 115, 115));
        btn_Thoat.setkEndColor(new java.awt.Color(0, 0, 0));
        btn_Thoat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 209, 84), 1, true));
        btn_Thoat.setBorder(null);
    }//GEN-LAST:event_btn_ThoatMouseEntered

    private void btn_ThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMouseExited
        btn_Thoat.setkEndColor(new java.awt.Color(115, 115, 115));
        btn_Thoat.setkStartColor(new java.awt.Color(0, 0, 0));
        btn_Thoat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 209, 84), 1, true));
        btn_Thoat.setBorder(null);
    }//GEN-LAST:event_btn_ThoatMouseExited

    private void btn_ThoatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThoatMousePressed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_ThoatMousePressed

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
            java.util.logging.Logger.getLogger(LeTan_ThanhToan_HoaDon_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeTan_ThanhToan_HoaDon_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeTan_ThanhToan_HoaDon_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeTan_ThanhToan_HoaDon_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LeTan_ThanhToan_HoaDon dialog = new LeTan_ThanhToan_HoaDon(new javax.swing.JFrame(), true);
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
//                LeTan_ThanhToan_HoaDon_GUI dialog = new LeTan_ThanhToan_HoaDon_GUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel Panel_DSPhong;
    private keeptoo.KGradientPanel btn_Thoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel label_x;
    private javax.swing.JLabel lable_dichvu_dongia;
    private javax.swing.JLabel lable_dichvu_soluong;
    private javax.swing.JLabel lable_dichvu_sudung;
    private javax.swing.JLabel lable_dichvu_thanhtien;
    private javax.swing.JLabel lable_mahoadon;
    private javax.swing.JLabel lable_ngaytao;
    private javax.swing.JLabel lable_tongtien;
    private javax.swing.JPanel panel_Column1;
    private javax.swing.JPanel panel_Column2;
    private javax.swing.JPanel panel_DSDichVu;
    private javax.swing.JPanel panel_dichVu;
    // End of variables declaration//GEN-END:variables
}
