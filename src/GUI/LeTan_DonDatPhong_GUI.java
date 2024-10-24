/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import static GUI.LeTan_DatPhong_GUI.label_MaDonDatPhong;
import static GUI.LeTan_DatPhong_GUI.label_MaHoaDon;
import com.sun.management.HotSpotDiagnosticMXBean;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
import model.DAO.DichVuDAO;
import model.DAO.DonDatPhongDAO;
import model.DAO.HoaDonDAO;
import model.DAO.KhachHangDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVu;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import model.DTO.KhachHang;
import model.DTO.LoaiPhong;
import model.DTO.Phong;
import model.MongoDBConnection;

/**
 *
 * @author Admin
 */
public class LeTan_DonDatPhong_GUI extends javax.swing.JInternalFrame {

    public static MongoDBConnection database = new MongoDBConnection();
    public static LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database.getDatabase());
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    public static List<DonDatPhong> list_DonDatPhong = new ArrayList<DonDatPhong>();
    public static DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database.getDatabase());
    private List<Phong> list_phong = new ArrayList<Phong>();
    public static PhongDAO phong_dao = new PhongDAO(database.getDatabase());
    private LoaiPhongDAO LoaiPhong_dao = new LoaiPhongDAO(database.getDatabase());
    private List<DichVu> list_DichVu = new ArrayList<DichVu>();
    private DichVuDAO dichVu_dao = new DichVuDAO(database.getDatabase());
    public static DefaultTableModel model;
    public static DefaultTableCellRenderer centerRenderer;
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static List<DonDatPhong> list_DonDatPhongTheoTieuChi = new ArrayList<DonDatPhong>();
    public static List<LoaiPhong> list_LoaiPhong = new ArrayList<LoaiPhong>();
    private List<KhachHang> list_khachHang = new ArrayList<KhachHang>();
    private KhachHangDAO khachHang_dao = new KhachHangDAO(database.getDatabase());
    private List<HoaDon> list_hoaDon = new ArrayList<>();
    private HoaDonDAO hoaDon_Dao = new HoaDonDAO(database.getDatabase());

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_DonDatPhong_GUI() {
        initComponents();
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        JTableHeader header = Table_DonDatPhong.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));

//        txt_NgayNhanPhong_BatDau.setDate(getNgayHienTai());
//      căn giữa cho header 
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);

        //Căn giữa các các dòng trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

//        Đọc dữ liệu từ database lên
        list_DonDatPhong = GetAllDonDatPhong(donDatPhong_dao.getAllDonDatPhong());
        list_DichVu = dichVu_dao.getAllDichVu();
        list_LoaiPhong = loaiPhong_dao.getAllLoaiPhong();
        list_phong = phong_dao.getAllPhong();
        list_khachHang = khachHang_dao.getAllKhachHang();

//        Tải dữ liệu tầng lên cb_Tang
        Set<Integer> set = new HashSet<>();
        for (Phong p : list_phong) {
            if (!set.contains(p.getTang())) {
                set.add(p.getTang());
            }
        }
        set.forEach(t -> {
            cb_Tang.addItem(t.toString());
        });

//        Tải dữ liệu tầng lên cb_Phong
        for (Phong phong : list_phong) {
            cb_Phong.addItem(phong.getMaPhong() + "");
        }

//        Xét dữ liệu cho cb_LoaiPhong
        for (LoaiPhong lp : list_LoaiPhong) {
            cb_LoaiPhong.addItem(lp.getTenLoaiPhong());
        }

//        model = (DefaultTableModel) Table_KhachHang.getModel();
        // Tạo một DefaultTableModel tùy chỉnh với isCellEditable được ghi đè
        String columnNames[] = {"Mã Đơn", "Trạng Thái", "Ngày Đến", "Ngày Đi", "Phòng", "Loại Phòng", "SL Khách", "Dịch vụ sử dụng"};
        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về false để chặn mọi ô không thể chỉnh sửa
                return false;
            }
        };
        model.setRowCount(0);

        Table_DonDatPhong.setModel(model);
        Table_DonDatPhong.setRowHeight(30);
        jScrollPane1.setViewportView(Table_DonDatPhong);
        if (Table_DonDatPhong.getColumnModel().getColumnCount() > 0) {
            Table_DonDatPhong.getColumnModel().getColumn(0).setMaxWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(1).setMaxWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(2).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(3).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(4).setMaxWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(5).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(6).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(7).setMaxWidth(300);
        }

//        Đọc dữ liệu theo trạng thái 
        checkBox_DangO.setSelected(true);
        checkBox_DangCho.setSelected(true);
        list_DonDatPhongTheoTieuChi = list_DonDatPhong;
        
        list_DonDatPhongTheoTieuChi = list_DonDatPhong;



        list_DonDatPhongTheoTieuChi = list_DonDatPhong;

        

        list_DonDatPhongTheoTieuChi = list_DonDatPhong;


        DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);

        list_btn.add(btn_Tim);
        list_btn.add(btn_ThemDichVu);
        list_btn.add(btn_NhanPhong);
        list_btn.add(btn_LamMoi);
        list_btn.add(btn_HuyDon);

        list_btn.forEach((btn) -> {
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setkStartColor(new java.awt.Color(255, 225, 27));
                    btn.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn.setBorder(null);

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setkStartColor(new java.awt.Color(225, 176, 27));
                    btn.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn.setBorder(null);
                }
            });
        });

        //        Bắt sự kiện thay đổi kí tự trong txt_CCCD
        txt_CCCD.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                KhachHang khachHang = null;
                for (KhachHang kh : list_khachHang) {
                    if (kh.getCCCD().equals(txt_CCCD.getText())) {
                        khachHang = kh;

                    }
                }

                if (khachHang == null) {
                    txt_HoTen.setText("");
                    txt_SoDienThoai.setText("");
                    txt_SoDienThoai.setText("");

                } else {
                    txt_HoTen.setText(khachHang.getTenKhachHang());
                    txt_SoDienThoai.setText(khachHang.getSoDienThoai());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                KhachHang khachHang = null;
                for (KhachHang kh : list_khachHang) {
                    if (kh.getCCCD().equals(txt_CCCD.getText())) {
                        khachHang = kh;

                    }
                }

                if (khachHang == null) {
                    txt_HoTen.setText("");
                    txt_SoDienThoai.setText("");
                    txt_SoDienThoai.setText("");

                } else {
                    txt_HoTen.setText(khachHang.getTenKhachHang());
                    txt_SoDienThoai.setText(khachHang.getSoDienThoai());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

    }

    public static List<DonDatPhong> GetAllDonDatPhong(List<DonDatPhong> list_DonDatPhongs) {
        List<DonDatPhong> List_DonDatPhongTheoTrangThai = new ArrayList<>();
        for (DonDatPhong ddp : donDatPhong_dao.getAllDonDatPhong()) {
            if (ddp.getTrangThai().equals("Đang ở") || ddp.getTrangThai().equals("Đang chờ")) {
                List_DonDatPhongTheoTrangThai.add(ddp);
            }
        }
        return List_DonDatPhongTheoTrangThai;
    }

    public static void DocDuLieuLenTable(List<DonDatPhong> list_DonDatPhongs) {
        model.setRowCount(0);
        for (DonDatPhong ddp : list_DonDatPhongs) {
            model.addRow(new Object[]{
                ddp.getMaDonDat(),
                ddp.getTrangThai(),
                sdf.format(ddp.getNgayNhanPhong()),
                sdf.format(ddp.getNgayTraPhong()),
                ddp.getPhong(),
                loaiPhong_dao.getLoaiPhongByMa(phong_dao.getPhongByMa(ddp.getPhong()).getLoaiPhong()).getTenLoaiPhong(),
                ddp.getKhachO().size(),
                getDSDichVu(ddp.getDichVuSuDung())
            });
        }

        for (int i = 0; i < Table_DonDatPhong.getColumnCount(); i++) {
            Table_DonDatPhong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

//      
    }

    public List<DonDatPhong> getDonDatPhongTheoLoaiPhong(List<DonDatPhong> list_DDP) {
        List<DonDatPhong> list_DDPMoi = new ArrayList<>();
        for (DonDatPhong ddp : list_DDP) {
            Phong phong = phong_dao.getPhongByMa(ddp.getPhong());
            if (phong.getLoaiPhong() == cb_LoaiPhong.getSelectedIndex()) {
                list_DDPMoi.add(ddp);
            }
        }
        return list_DDPMoi;
    }

    public List<DonDatPhong> getDonDatPhongTheoTang(List<DonDatPhong> list_DDP) {
        List<DonDatPhong> list_DDPMoi = new ArrayList<>();
        for (DonDatPhong ddp : list_DDP) {
            Phong phong = phong_dao.getPhongByMa(ddp.getPhong());
            if (phong.getTang() == cb_Tang.getSelectedIndex()) {
                list_DDPMoi.add(ddp);
            }
        }
        return list_DDPMoi;
    }

    public List<DonDatPhong> getDonDatPhongTheoPhong(List<DonDatPhong> list_DDP) {
        List<DonDatPhong> list_DDPMoi = new ArrayList<>();
        for (DonDatPhong ddp : list_DDP) {
            if (ddp.getPhong() == Integer.parseInt(cb_Phong.getSelectedItem().toString())) {
                list_DDPMoi.add(ddp);
            }
        }
        return list_DDPMoi;
    }

    public void getPhongTheoTang(int tang) {
        cb_Phong.removeAllItems();
        if (tang == 0) {
            cb_Phong.addItem("");
            for (Phong phong : list_phong) {
                cb_Phong.addItem(phong.getMaPhong() + "");
            }
            return;
        }
        cb_Phong.addItem("");
        for (Phong phong : list_phong) {
            if (phong.getTang() == tang) {
                cb_Phong.addItem(phong.getMaPhong() + "");
            }
        }
    }

    public Date getNgayHienTai() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date ngayhientai = calendar.getTime();
        return ngayhientai;
    }

    public static String getDSDichVu(List<DichVu> list_DichVu) {
        if (list_DichVu.size() == 0) {
            return "";
        }
        String dsDichVu = "";
        for (DichVu dv : list_DichVu) {
            dsDichVu = dsDichVu + dv.getTenDV() + ", ";
        }
        dsDichVu = dsDichVu.substring(0, dsDichVu.length() - 2);
        return dsDichVu;
    }

    public List<DonDatPhong> getDonDatPhongTheoThoiGian(List<DonDatPhong> list_DonDatPhongs, Date NgayBatDau, Date NgayKetThuc) {
        NgayBatDau = getNgayHienTai();
        List<DonDatPhong> list_DonDatPhongTheoThoiGian = new ArrayList<DonDatPhong>();
        if (NgayKetThuc == null) {
            for (DonDatPhong ddp : list_DonDatPhongs) {
                if (ddp.getNgayNhanPhong().after(NgayBatDau)) {
                    list_DonDatPhongTheoThoiGian.add(ddp);
                }
            }

            return list_DonDatPhongTheoThoiGian;
        }

        for (DonDatPhong ddp : list_DonDatPhongs) {
            if (ddp.getNgayNhanPhong().after(NgayBatDau) && ddp.getNgayNhanPhong().before(NgayKetThuc)) {
                list_DonDatPhongTheoThoiGian.add(ddp);
            }
        }
        return list_DonDatPhongTheoThoiGian;

    }

    public void LamMoi() {
        txt_CCCD.setText("");
        txt_HoTen.setText("");
        txt_SoDienThoai.setText("");
        cb_LoaiPhong.setSelectedIndex(0);
        cb_Tang.setSelectedIndex(0);
        cb_Phong.setSelectedIndex(0);
        txt_NgayNhanPhong_BatDau.setDate(null);
        txt_NgayNhanPhong_KetThuc.setDate(null);
        txt_CCCD.requestFocus();

        checkBox_DangCho.setSelected(true);
        checkBox_DangO.setSelected(true);

        checkBox_DangCho.setSelected(true);
        checkBox_DangO.setSelected(true);

        checkBox_DangCho.setSelected(true);
        checkBox_DangO.setSelected(true);

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
        txt_NgayNhanPhong_BatDau = new com.toedter.calendar.JDateChooser();
        txt_NgayNhanPhong_KetThuc = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        txt_SoDienThoai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cb_LoaiPhong = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cb_Tang = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cb_Phong = new javax.swing.JComboBox<>();
        btn_HuyDon = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_ThemDichVu = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        btn_NhanPhong = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_DonDatPhong = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        checkBox_DangO = new javax.swing.JCheckBox();
        checkBox_DangCho = new javax.swing.JCheckBox();
        Backgroup = new javax.swing.JLabel();

        setName("page_DonDatPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        ThongTinDat.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinDat.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ngày nhận phòng");

        txt_NgayNhanPhong_BatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayNhanPhong_BatDauPropertyChange(evt);
            }
        });

        txt_NgayNhanPhong_KetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_NgayNhanPhong_KetThucPropertyChange(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_MuiTen.png"))); // NOI18N

        javax.swing.GroupLayout ThongTinDatLayout = new javax.swing.GroupLayout(ThongTinDat);
        ThongTinDat.setLayout(ThongTinDatLayout);
        ThongTinDatLayout.setHorizontalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addComponent(txt_NgayNhanPhong_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(txt_NgayNhanPhong_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        ThongTinDatLayout.setVerticalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_NgayNhanPhong_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_NgayNhanPhong_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat);
        ThongTinDat.setBounds(70, 90, 580, 120);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CCCD/Passport");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Họ và tên");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Số điện thoại");

        txt_CCCD.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_HoTen.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        txt_SoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoDienThoaiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Loại phòng");

        cb_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_LoaiPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cb_LoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaiPhongActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tầng");

        cb_Tang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_Tang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cb_Tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TangActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Phòng");

        cb_Phong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_Phong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cb_Phong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_PhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txt_CCCD)
                    .addComponent(cb_LoaiPhong, 0, 210, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cb_Tang, 0, 214, Short.MAX_VALUE)
                    .addComponent(txt_HoTen))
                .addGap(111, 111, 111)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addGap(39, 39, 39))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6))
                        .addGap(6, 6, 6)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(cb_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(70, 230, 920, 220);

        btn_HuyDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_HuyDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HuyDon.setkGradientFocus(250);
        btn_HuyDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HuyDon.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_HuyDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HuyDonMousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Hủy Đơn");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HuyDonLayout = new javax.swing.GroupLayout(btn_HuyDon);
        btn_HuyDon.setLayout(btn_HuyDonLayout);
        btn_HuyDonLayout.setHorizontalGroup(
            btn_HuyDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HuyDonLayout.setVerticalGroup(
            btn_HuyDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HuyDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HuyDon);
        btn_HuyDon.setBounds(1050, 390, 140, 40);

        btn_Tim.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Tim.setkGradientFocus(250);
        btn_Tim.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Tim.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_TimMousePressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Tìm theo KH");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_TimLayout = new javax.swing.GroupLayout(btn_Tim);
        btn_Tim.setLayout(btn_TimLayout);
        btn_TimLayout.setHorizontalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_TimLayout.setVerticalGroup(
            btn_TimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_TimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Tim);
        btn_Tim.setBounds(1050, 100, 140, 40);

        btn_ThemDichVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_ThemDichVu.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThemDichVu.setkGradientFocus(250);
        btn_ThemDichVu.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThemDichVu.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_ThemDichVu.setPreferredSize(new java.awt.Dimension(140, 45));
        btn_ThemDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemDichVuMousePressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Thêm Dịch Vụ");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_ThemDichVuLayout = new javax.swing.GroupLayout(btn_ThemDichVu);
        btn_ThemDichVu.setLayout(btn_ThemDichVuLayout);
        btn_ThemDichVuLayout.setHorizontalGroup(
            btn_ThemDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemDichVuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThemDichVuLayout.setVerticalGroup(
            btn_ThemDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel1.add(btn_ThemDichVu);
        btn_ThemDichVu.setBounds(1050, 260, 140, 45);

        btn_LamMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_LamMoi.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_LamMoiMousePressed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Làm Mới");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(1050, 160, 140, 40);

        btn_NhanPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_NhanPhong.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_NhanPhong.setkGradientFocus(250);
        btn_NhanPhong.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_NhanPhong.setMinimumSize(new java.awt.Dimension(140, 45));
        btn_NhanPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_NhanPhongMousePressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Nhận Phòng");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btn_NhanPhongLayout = new javax.swing.GroupLayout(btn_NhanPhong);
        btn_NhanPhong.setLayout(btn_NhanPhongLayout);
        btn_NhanPhongLayout.setHorizontalGroup(
            btn_NhanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_NhanPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_NhanPhongLayout.setVerticalGroup(
            btn_NhanPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_NhanPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_NhanPhong);
        btn_NhanPhong.setBounds(1050, 330, 140, 40);

        Table_DonDatPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Table_DonDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đơn", "Trạng Thái", "Ngày Đến", "Ngày Đi", "Phòng", "Loại Phòng", "SL Khách", "Dịch Vụ Sử Dụng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_DonDatPhong.setRowHeight(30);
        Table_DonDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Table_DonDatPhongMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Table_DonDatPhong);
        if (Table_DonDatPhong.getColumnModel().getColumnCount() > 0) {
            Table_DonDatPhong.getColumnModel().getColumn(0).setMaxWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(1).setMaxWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(2).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(3).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(4).setMaxWidth(120);
            Table_DonDatPhong.getColumnModel().getColumn(5).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(6).setMaxWidth(150);
            Table_DonDatPhong.getColumnModel().getColumn(7).setMaxWidth(300);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(70, 490, 1120, 250);

        jLabel21.setBackground(new java.awt.Color(255, 209, 84));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 209, 84));
        jLabel21.setText("Tìm kiếm đơn đặt phòng");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel21);
        jLabel21.setBounds(70, 50, 380, 32);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        jPanel2.setOpaque(false);

        checkBox_DangO.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_DangO.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_DangO.setText("Đang ở");
        checkBox_DangO.setToolTipText("");
        checkBox_DangO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_DangO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_DangO.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_DangO.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_DangO.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_DangO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_DangOActionPerformed(evt);
            }
        });

        checkBox_DangCho.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        checkBox_DangCho.setForeground(new java.awt.Color(255, 255, 255));
        checkBox_DangCho.setText("Đang chờ");
        checkBox_DangCho.setToolTipText("");
        checkBox_DangCho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkBox_DangCho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox.png"))); // NOI18N
        checkBox_DangCho.setMinimumSize(new java.awt.Dimension(100, 50));
        checkBox_DangCho.setPreferredSize(new java.awt.Dimension(100, 50));
        checkBox_DangCho.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_CheckBox_active.png"))); // NOI18N
        checkBox_DangCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_DangChoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(checkBox_DangO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(checkBox_DangCho, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBox_DangO, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox_DangCho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(690, 90, 300, 120);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_DonDatPhong"); // NOI18N
        Backgroup.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.add(Backgroup);
        Backgroup.setBounds(0, 0, 1283, 803);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoDienThoaiActionPerformed

    private void checkBox_DangOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_DangOActionPerformed
        // TODO add your handling code here:
        list_DonDatPhong = GetAllDonDatPhong(donDatPhong_dao.getAllDonDatPhong());
        list_DonDatPhongTheoTieuChi = new ArrayList<DonDatPhong>();
        if (checkBox_DangO.isSelected() && checkBox_DangCho.isSelected()) {
            for (DonDatPhong ddp : list_DonDatPhong) {
                list_DonDatPhongTheoTieuChi.add(ddp);
            }

            DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
            return;
        }

        if (!checkBox_DangO.isSelected() && !checkBox_DangCho.isSelected()) {
            DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
            return;
        }

        if (checkBox_DangO.isSelected()) {
            for (DonDatPhong ddp : list_DonDatPhong) {
                if (ddp.getTrangThai().equals("Đang ở")) {
                    list_DonDatPhongTheoTieuChi.add(ddp);
                }
            }
        } else {
            if (checkBox_DangCho.isSelected()) {
                for (DonDatPhong ddp : list_DonDatPhong) {
                    if (ddp.getTrangThai().equals("Đang chờ")) {
                        list_DonDatPhongTheoTieuChi.add(ddp);
                    }
                }
            } else {
                DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
                return;
            }
        }
        DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
    }//GEN-LAST:event_checkBox_DangOActionPerformed

    private void checkBox_DangChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_DangChoActionPerformed
        // TODO add your handling code here:
        list_DonDatPhong = GetAllDonDatPhong(donDatPhong_dao.getAllDonDatPhong());
        list_DonDatPhongTheoTieuChi = new ArrayList<DonDatPhong>();
        if (checkBox_DangO.isSelected() && checkBox_DangCho.isSelected()) {
            for (DonDatPhong ddp : list_DonDatPhong) {
                list_DonDatPhongTheoTieuChi.add(ddp);
            }

            DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
            return;
        }

        if (!checkBox_DangO.isSelected() && !checkBox_DangCho.isSelected()) {
            DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
            return;
        }

        if (checkBox_DangCho.isSelected()) {
            for (DonDatPhong ddp : list_DonDatPhong) {
                if (ddp.getTrangThai().equals("Đang chờ")) {
                    list_DonDatPhongTheoTieuChi.add(ddp);
                }
            }
        } else {
            if (checkBox_DangO.isSelected()) {
                for (DonDatPhong ddp : list_DonDatPhong) {
                    if (ddp.getTrangThai().equals("Đang ở")) {
                        list_DonDatPhongTheoTieuChi.add(ddp);
                    }
                }
            } else {
                DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
                return;
            }
        }

        DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
    }//GEN-LAST:event_checkBox_DangChoActionPerformed

    private void txt_NgayNhanPhong_BatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_NgayNhanPhong_BatDauPropertyChange
        // TODO add your handling code here:

        if (txt_NgayNhanPhong_BatDau.getDate() == null && txt_NgayNhanPhong_KetThuc.getDate() == null) {
            DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
            return;
        }

        if (txt_NgayNhanPhong_KetThuc.getDate() != null) {
            if (txt_NgayNhanPhong_BatDau.getDate().after(txt_NgayNhanPhong_KetThuc.getDate())) {
                JOptionPane.showMessageDialog(this, "Ngày không hợp lý !!! vui lòng chọn lại");
                txt_NgayNhanPhong_BatDau.setDate(null);
                return;
            }
        } 

        list_DonDatPhongTheoTieuChi = getDonDatPhongTheoThoiGian(list_DonDatPhongTheoTieuChi, txt_NgayNhanPhong_BatDau.getDate(), txt_NgayNhanPhong_KetThuc.getDate());
        DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
    }//GEN-LAST:event_txt_NgayNhanPhong_BatDauPropertyChange

    private void txt_NgayNhanPhong_KetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_NgayNhanPhong_KetThucPropertyChange
        // TODO add your handling code here:
        if (txt_NgayNhanPhong_BatDau.getDate() == null && txt_NgayNhanPhong_KetThuc.getDate() == null) {
            DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
            return;
        }

        if (txt_NgayNhanPhong_BatDau.getDate() == null || txt_NgayNhanPhong_KetThuc.getDate() == null) {
            return;

        }
        if (txt_NgayNhanPhong_KetThuc.getDate().before(txt_NgayNhanPhong_BatDau.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lý !!! vui lòng chọn lại");
            txt_NgayNhanPhong_KetThuc.setDate(null);
            return;
        }
        list_DonDatPhongTheoTieuChi = getDonDatPhongTheoThoiGian(list_DonDatPhongTheoTieuChi, txt_NgayNhanPhong_BatDau.getDate(), txt_NgayNhanPhong_KetThuc.getDate());
        DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);

    }//GEN-LAST:event_txt_NgayNhanPhong_KetThucPropertyChange

    private void btn_TimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMousePressed
        // TODO add your handling code here:
        List<DonDatPhong> list_DDP = new ArrayList<>();

// Nếu tất cả các trường đều trống, load toàn bộ dữ liệu
        if (txt_CCCD.getText().equals("") && txt_HoTen.getText().equals("") && txt_SoDienThoai.getText().equals("")) {
            DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
            return;
        }

// Duyệt qua tất cả các đơn đặt phòng
        for (DonDatPhong ddp : list_DonDatPhongTheoTieuChi) {
            boolean match = false; // Biến kiểm tra xem đơn đặt phòng có thỏa mãn điều kiện nào không
            List<KhachHang> list_KhachHangtheoDon = ddp.getKhachO();

            for (KhachHang kh : list_KhachHangtheoDon) {
                // Kiểm tra CCCD
                if (!txt_CCCD.getText().equals("") && kh.getCCCD().equals(txt_CCCD.getText())) {
                    match = true;
                }

                // Kiểm tra họ tên nếu đã có khớp CCCD hoặc chưa có CCCD
                if (!txt_HoTen.getText().equals("") && kh.getTenKhachHang().equals(txt_HoTen.getText())) {
                    match = true;
                }

                // Kiểm tra số điện thoại nếu đã có khớp CCCD/họ tên hoặc chưa có các điều kiện trước
                if (!txt_SoDienThoai.getText().equals("") && kh.getSoDienThoai().equals(txt_SoDienThoai.getText())) {
                    match = true;
                }

                // Nếu có khớp ở bất kỳ điều kiện nào, thêm vào danh sách
                if (match) {
                    list_DDP.add(ddp);
                    break; // Không cần kiểm tra thêm khách hàng khác trong đơn
                }
            }
        }

//         Hiển thị kết quả sau khi lọc
        DocDuLieuLenTable(list_DDP);


    }//GEN-LAST:event_btn_TimMousePressed


    private void btn_LamMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LamMoiMousePressed
        // TODO add your handling code here:
        LamMoi();
        DocDuLieuLenTable(list_DonDatPhong);
    }//GEN-LAST:event_btn_LamMoiMousePressed


    private void cb_LoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaiPhongActionPerformed
        // TODO add your handling code here:
        if (cb_LoaiPhong.getSelectedItem() == null || cb_Tang.getSelectedItem() == null || cb_Phong.getSelectedItem() == null) {
            return;
        }
        List<DonDatPhong> list_DDP = list_DonDatPhongTheoTieuChi;

        if (cb_LoaiPhong.getSelectedItem().toString().equals("")
                && cb_Tang.getSelectedItem().toString().equals("")
                && cb_Phong.getSelectedItem().toString().equals("")) {
            DocDuLieuLenTable(list_DDP);
            return;
        }

        if (!cb_LoaiPhong.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoLoaiPhong(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }

        if (!cb_Tang.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoTang(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }

        if (!cb_Phong.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoPhong(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }
    }//GEN-LAST:event_cb_LoaiPhongActionPerformed

    private void cb_TangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TangActionPerformed
        // TODO add your handling code here:
        if (cb_LoaiPhong.getSelectedItem() == null || cb_Tang.getSelectedItem() == null || cb_Phong.getSelectedItem() == null) {
            return;
        }
        getPhongTheoTang(cb_Tang.getSelectedIndex());

        List<DonDatPhong> list_DDP = list_DonDatPhongTheoTieuChi;

        if (cb_LoaiPhong.getSelectedItem().toString().equals("")
                && cb_Tang.getSelectedItem().toString().equals("")
                && cb_Phong.getSelectedItem().toString().equals("")) {
            DocDuLieuLenTable(list_DDP);
            return;
        }

        if (!cb_LoaiPhong.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoLoaiPhong(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }

        if (!cb_Tang.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoTang(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }

        if (!cb_Phong.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoPhong(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }
    }//GEN-LAST:event_cb_TangActionPerformed

    private void cb_PhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_PhongActionPerformed
        // TODO add your handling code here:
        List<DonDatPhong> list_DDP = list_DonDatPhongTheoTieuChi;
        if (cb_LoaiPhong.getSelectedItem() == null || cb_Tang.getSelectedItem() == null || cb_Phong.getSelectedItem() == null) {
            return;
        }
        if (cb_LoaiPhong.getSelectedItem().toString().equals("")
                && cb_Tang.getSelectedItem().toString().equals("")
                && cb_Phong.getSelectedItem().toString().equals("")) {
            DocDuLieuLenTable(list_DDP);
            return;
        }

        if (!cb_LoaiPhong.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoLoaiPhong(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }

        if (!cb_Tang.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoTang(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }

        if (!cb_Phong.getSelectedItem().toString().equals("")) {
            list_DDP = getDonDatPhongTheoPhong(list_DDP);
            DocDuLieuLenTable(list_DDP);
        }
    }//GEN-LAST:event_cb_PhongActionPerformed

    private void Table_DonDatPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_DonDatPhongMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int maDon = Integer.parseInt(model.getValueAt(Table_DonDatPhong.getSelectedRow(), 0).toString());
            DonDatPhong donDatPhong = donDatPhong_dao.getDonDatPhongByMa(maDon);
            new LeTan_DonDatPhong_ChiTietDonDatPhong_GUI(donDatPhong, (JFrame) this.getParent().getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);
        }
    }//GEN-LAST:event_Table_DonDatPhongMousePressed

    private void btn_ThemDichVuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemDichVuMousePressed
        // TODO add your handling code here:
        int row = Table_DonDatPhong.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Đơn cần thêm dịch vụ");
            return;
        }

        int maDon = Integer.parseInt(model.getValueAt(row, 0).toString());
        DonDatPhong donDatPhong_CanThem = donDatPhong_dao.getDonDatPhongByMa(maDon);

        if (donDatPhong_CanThem.getTrangThai().equals("Đang chờ")) {
            JOptionPane.showMessageDialog(this, "Phòng hiện tại chưa ở nên chưa thể thêm dịch vụ, vui lòng chọn đơn đặt phòng khác");
            return;
        }
        new LeTan_DonDatPhong_ThemDichVu(donDatPhong_CanThem, (JFrame) this.getParent().getParent().getParent().getParent().getParent().getParent(), true).setVisible(true);
    }//GEN-LAST:event_btn_ThemDichVuMousePressed

    private void btn_NhanPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_NhanPhongMousePressed
        // TODO add your handling code here:
        int row = Table_DonDatPhong.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Đơn đặt phòng cần nhận");
            return;
        }

        int maDon = Integer.parseInt(model.getValueAt(row, 0).toString());
        DonDatPhong donDatPhong_CanNhan = donDatPhong_dao.getDonDatPhongByMa(maDon);
        if (!donDatPhong_CanNhan.getTrangThai().equals("Đang chờ")) {
            JOptionPane.showMessageDialog(this, "Phòng hiện tại đang ở, vui lòng chọn đơn đặt phòng khác");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn nhận phòng", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        donDatPhong_CanNhan.setTrangThai("Đang ở");
        donDatPhong_dao.updateDonDatPhong(donDatPhong_CanNhan);
        list_DonDatPhongTheoTieuChi = new ArrayList<>();
        list_DonDatPhongTheoTieuChi = GetAllDonDatPhong(donDatPhong_dao.getAllDonDatPhong());
        DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);


    }//GEN-LAST:event_btn_NhanPhongMousePressed

    private void btn_HuyDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HuyDonMousePressed
        // TODO add your handling code here:
        int[] selRows = Table_DonDatPhong.getSelectedRows();
        if (selRows.length == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn đơn muốn hủy");
            return;
        }

//        Kiểm tra có chứa  phòng đang ở hay không
        boolean ktra = true;
        for (int i = 0; i < selRows.length; ++i) {
            int maDon = Integer.parseInt(Table_DonDatPhong.getValueAt(selRows[i], 0).toString());
            if (!Table_DonDatPhong.getValueAt(selRows[i], 1).toString().equals("Đang chờ")) {
                ktra = false;
                break;
            }
        }

        if (!ktra) {
            JOptionPane.showMessageDialog(null, "Chứa Phòng hiện tại đang ở, không thể hủy, Vui lòng chọn phòng khác");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy các phòng này", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        for (int i = 0; i < selRows.length; ++i) {
            int maDon = Integer.parseInt(Table_DonDatPhong.getValueAt(selRows[i], 0).toString());
            DonDatPhong ddp = donDatPhong_dao.getDonDatPhongByMa(maDon);
            donDatPhong_dao.xoaDonDatPhongByMaDonDat(maDon);
            if (!ktraDonDatPhongCuoiCung(ddp)) {
                hoaDon_Dao.deleteHoaDon(ddp.getHoaDon());
            }
        }

        list_DonDatPhongTheoTieuChi.clear();
        list_DonDatPhongTheoTieuChi = GetAllDonDatPhong(donDatPhong_dao.getAllDonDatPhong());
        DocDuLieuLenTable(list_DonDatPhongTheoTieuChi);
        JOptionPane.showMessageDialog(this, "Hủy thành công");
        list_hoaDon = new ArrayList<HoaDon>();
        list_hoaDon = hoaDon_Dao.getAllHoaDon();
        LeTan_ThanhToan_GUI.list_DonDatPhong.clear();
        LeTan_ThanhToan_GUI.list_DonDatPhong = donDatPhong_dao.getAllDonDatPhong();
        LeTan_ThanhToan_GUI.DocDuLieuLenTable(list_hoaDon);
        LeTan_DatPhong_GUI.list_DonDatPhong = list_DonDatPhongTheoTieuChi;
        LeTan_DatPhong_GUI.list_HoaDon = list_hoaDon;
        label_MaDonDatPhong.setText("Mã đơn đặt phòng: " + (list_DonDatPhongTheoTieuChi.size() + 1));
        label_MaHoaDon.setText("Mã hóa đơn: " + (list_hoaDon.size() + 1));
    }//GEN-LAST:event_btn_HuyDonMousePressed
    public boolean ktraDonDatPhongCuoiCung(DonDatPhong ddpDaXoa) {
        list_DonDatPhongTheoTieuChi.clear();
        list_DonDatPhongTheoTieuChi = GetAllDonDatPhong(donDatPhong_dao.getAllDonDatPhong());
        for (DonDatPhong ddp : list_DonDatPhongTheoTieuChi) {
            if (ddpDaXoa.getHoaDon() == ddp.getHoaDon()) {
                return true;
            }
        }
        return false;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    public static javax.swing.JTable Table_DonDatPhong;
    private javax.swing.JPanel ThongTinDat;
    private javax.swing.JPanel ThongTinKhachHang;
    private keeptoo.KGradientPanel btn_HuyDon;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_NhanPhong;
    private keeptoo.KGradientPanel btn_ThemDichVu;
    private keeptoo.KGradientPanel btn_Tim;
    private javax.swing.JComboBox<String> cb_LoaiPhong;
    private javax.swing.JComboBox<String> cb_Phong;
    private javax.swing.JComboBox<String> cb_Tang;
    public static javax.swing.JCheckBox checkBox_DangCho;
    public static javax.swing.JCheckBox checkBox_DangO;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_HoTen;
    private com.toedter.calendar.JDateChooser txt_NgayNhanPhong_BatDau;
    private com.toedter.calendar.JDateChooser txt_NgayNhanPhong_KetThuc;
    private javax.swing.JTextField txt_SoDienThoai;
    // End of variables declaration//GEN-END:variables
}
