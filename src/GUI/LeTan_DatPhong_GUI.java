/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import keeptoo.KGradientPanel;
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
import model.DTO.NhanVien;
import model.DTO.Phong;
import model.MongoDBConnection;
import GUI.LeTan_GUI;

/**
 *
 * @author Admin
 */
public class LeTan_DatPhong_GUI extends javax.swing.JInternalFrame {

    private MongoDBConnection database = new MongoDBConnection();
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private List<Phong> list_Phong = new ArrayList<Phong>();
    private PhongDAO phong_dao = new PhongDAO(database.getDatabase());
    private List<DonDatPhong> list_DonDatPhong = new ArrayList<DonDatPhong>();
    private DonDatPhongDAO DonDatphong_dao = new DonDatPhongDAO(database.getDatabase());
    private List<LoaiPhong> list_LoaiPhong = new ArrayList<LoaiPhong>();
    private LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database.getDatabase());
    private List<KhachHang> list_KhachHang = new ArrayList<KhachHang>();
    private KhachHangDAO khachHang_dao = new KhachHangDAO(database.getDatabase());
    private DefaultTableModel model;
    private List<HoaDon> list_HoaDon = new ArrayList<HoaDon>();
    private HoaDonDAO hoaDon_dao = new HoaDonDAO(database.getDatabase());
    private List<KhachHang> list_KhachHang_TheoDon = new ArrayList<KhachHang>();
    private List<KhachHang> list_KhachHangMoi = new ArrayList<KhachHang>();

    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public LeTan_DatPhong_GUI() {
        initComponents();

        txt_CCCD.requestFocus();
        JTableHeader header = table_KhachHang.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        txt_NgayDen.setDate(new Date());
        setThoiGianBang0(txt_NgayDen);
        txt_NgayDi.setDate(new Date());
        setThoiGianBang0(txt_NgayDi);

        list_Phong = phong_dao.getAllPhong();
        list_DonDatPhong = DonDatphong_dao.getAllDonDatPhong();
        list_LoaiPhong = loaiPhong_dao.getAllLoaiPhong();
        list_KhachHang = khachHang_dao.getAllKhachHang();
        list_HoaDon = hoaDon_dao.getAllHoaDon();

        label_MaDonDatPhong.setText("Mã đơn đặt phòng: " + (list_DonDatPhong.size() + 1));
        label_MaHoaDon.setText("Mã hóa đơn: " + (list_HoaDon.size() + 1));

        model = (DefaultTableModel) table_KhachHang.getModel();
        model.setRowCount(0);

        list_btn.add(btn_LamMoi);
        list_btn.add(btn_them);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_Sua);
        list_btn.add(btn_ThemDon);
        list_btn.add(btn_HoanTat);
//         Xử lí sự kiện txt_NgayDen
        txt_NgayDen.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setThoiGianBang0(txt_NgayDen);
                setThoiGianBang0(txt_NgayDi);
                if (txt_NgayDen.getDate().after(txt_NgayDi.getDate())) {
                    JOptionPane.showMessageDialog(null, "Ngày đến phải trước ngày đi");
                    txt_NgayDen.setDate(new Date());
                    setThoiGianBang0(txt_NgayDen);
                    return;

                }

                Date ngayHientai = new Date();
                if (ngayHientai != null) {
                    // Sử dụng Calendar để làm sạch phần thời gian
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(ngayHientai);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    // Lấy lại đối tượng Date với thời gian đã được làm sạch
                    ngayHientai = calendar.getTime();
                }
                setThoiGianBang0(txt_NgayDen);
                if (ngayHientai.after(txt_NgayDen.getDate())) {
                    JOptionPane.showMessageDialog(null, "Ngày đến phải sau ngày hôm nay");
                    txt_NgayDen.setDate(new Date());
                    setThoiGianBang0(txt_NgayDen);
                }

            }
        });

//        Xử lý sự kiện txt_NgayDi
        txt_NgayDi.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setThoiGianBang0(txt_NgayDen);
                setThoiGianBang0(txt_NgayDi);

                if (txt_NgayDi.getDate().before(txt_NgayDen.getDate())) {
                    JOptionPane.showMessageDialog(null, "Ngày đi phải sau ngày đến");
                    txt_NgayDi.setDate(new Date());
                    setThoiGianBang0(txt_NgayDi);
                    return;

                }
                List<Phong> list_PhongDay = new ArrayList<Phong>();
                for (DonDatPhong ddp : list_DonDatPhong) {
                    if (!(ddp.getNgayTraPhong().before(txt_NgayDen.getDate()) || ddp.getNgayNhanPhong().after(txt_NgayDi.getDate()))) {
                        list_PhongDay.add(phong_dao.getPhongByMa(ddp.getPhong()));
                    }
                }
                if (txt_Phong.getText().equals("")) {
                    return;
                }
                boolean ktra = true;
                for (Phong phong : list_PhongDay) {
                    if (phong.getMaPhong() == Integer.parseInt(txt_Phong.getText())) {
                        ktra = false;
                    }
                }

                if (!ktra) {
                    txt_Phong.setText("");
                    txt_LoaiPhong.setText("");
                    txt_DonGia.setText("");
                    txt_Tang.setText("");
                    JOptionPane.showMessageDialog(null, "Phòng đã được đặt, vui lòng chọn phòng khác");
                }

            }
        });
//        Bắt sự kiện hover các nút chức năng
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
                    if (BlockEvent == true) {
                        btn.setkStartColor(new java.awt.Color(255, 225, 27));
                        btn.setkEndColor(new java.awt.Color(255, 222, 89));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (BlockEvent == true) {
                        btn.setkStartColor(new java.awt.Color(225, 176, 27));
                        btn.setkEndColor(new java.awt.Color(255, 222, 89));
                        btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        btn.setBorder(null);
                    }

                }
            });
        });

//        Bắt sự kiện thay đổi kí tự trong txt_CCCD
        txt_CCCD.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                KhachHang khachHang = null;
                for (KhachHang kh : list_KhachHang) {
                    if (kh.getCCCD().equals(txt_CCCD.getText())) {
                        khachHang = kh;

                    }
                }

                if (khachHang == null) {
                    txt_HoTen.setText("");
                    txt_SDT.setText("");
                    txt_Email.setText("");
                    cb_GioiTinh.setSelectedIndex(0);
                    cb_QuocTich.setSelectedIndex(0);
                    btn_them.setkEndColor(new java.awt.Color(177, 177, 209));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(84, 84, 84));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = false;
                } else {
                    txt_HoTen.setText(khachHang.getTenKhachHang());
                    txt_SDT.setText(khachHang.getSoDienThoai());
                    txt_Email.setText(khachHang.getEmail());
                    if (khachHang.getGioiTinh() == 0) {
                        cb_GioiTinh.setSelectedIndex(1);
                    }
                    cb_QuocTich.setSelectedItem(khachHang.getQuocTich());
                    btn_them.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(225, 176, 27));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = true;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                KhachHang khachHang = null;
                for (KhachHang kh : list_KhachHang) {
                    if (kh.getCCCD().equals(txt_CCCD.getText())) {
                        khachHang = kh;

                    }
                }

                if (khachHang == null) {
                    txt_HoTen.setText("");
                    txt_SDT.setText("");
                    txt_Email.setText("");
                    cb_GioiTinh.setSelectedIndex(0);
                    cb_QuocTich.setSelectedIndex(0);
                    btn_them.setkEndColor(new java.awt.Color(177, 177, 209));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(84, 84, 84));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = false;
                } else {
                    txt_HoTen.setText(khachHang.getTenKhachHang());
                    txt_SDT.setText(khachHang.getSoDienThoai());
                    txt_Email.setText(khachHang.getEmail());
                    if (khachHang.getGioiTinh() == 0) {
                        cb_GioiTinh.setSelectedIndex(1);
                    }
                    cb_QuocTich.setSelectedItem(khachHang.getQuocTich());
                    cb_QuocTich.setSelectedItem(khachHang.getQuocTich());
                    btn_them.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(225, 176, 27));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = true;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

//        Bắt sự kiện thay đổi của txt_HoTen
        txt_HoTen.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txt_HoTen.getText().equals("")) {
                    return;
                }
                String regex = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+(\\s+[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+)+$";

                if (txt_HoTen.getText().matches(regex)) {
                    btn_them.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(225, 176, 27));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = true;
                } else {
                    btn_them.setkEndColor(new java.awt.Color(177, 177, 209));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(84, 84, 84));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = false;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txt_HoTen.getText().equals("")) {
                    return;
                }
                String regex = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+(\\s+[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+)+$";

                if (txt_HoTen.getText().matches(regex)) {
                    btn_them.setkEndColor(new java.awt.Color(255, 222, 89));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(225, 176, 27));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = true;
                } else {
                    btn_them.setkEndColor(new java.awt.Color(177, 177, 209));
                    btn_them.setkGradientFocus(250);
                    btn_them.setkStartColor(new java.awt.Color(84, 84, 84));
                    btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                    btn_them.setBorder(null);
                    BlockEvent = false;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

    }

    private void setThoiGianBang0(JDateChooser ngay) {
        if (ngay.getDate() != null) {
            // Sử dụng Calendar để làm sạch phần thời gian
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngay.getDate());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            // Lấy lại đối tượng Date với thời gian đã được làm sạch
            ngay.setDate(calendar.getTime());
        }
    }

    public List<Phong> getAllPhongTrong(Date ngayDen, Date ngayDi) {
        List<Phong> list_PhongDay = new ArrayList<Phong>();
        List<Phong> list_PhongTrong = new ArrayList<Phong>();
        for (DonDatPhong ddp : list_DonDatPhong) {
            if (!(ddp.getNgayTraPhong().before(ngayDen) || ddp.getNgayNhanPhong().after(ngayDi))) {
                list_PhongDay.add(phong_dao.getPhongByMa(ddp.getPhong()));
            }
        }

        for (Phong phong1 : list_Phong) {
            boolean ktra = false;
            for (Phong phong2 : list_PhongDay) {
                if (phong1.getMaPhong() == phong2.getMaPhong()) {
                    ktra = true;
                }
            }

            if (!ktra) {
                list_PhongTrong.add(phong1);
            }

        }

        return list_PhongTrong;
    }

    public void ThemKhachHangVaoTable(KhachHang kh) {
        model.addRow(new Object[]{
            kh.getMaKhachHang(),
            kh.getCCCD(),
            kh.getTenKhachHang(),
            kh.getGioiTinh() == 1 ? "Nam" : "Nữ",
            kh.getSoDienThoai(),
            kh.getEmail(),
            kh.getQuocTich()
        });
    }

    public boolean ktraTrungKH(String CCCD) {
        for (KhachHang kh : list_KhachHang_TheoDon) {
            if (kh.getCCCD().equals(CCCD)) {
                return false;
            }
        }

        return true;
    }

    public void LamMoi() {
        txt_CCCD.setText("");
        txt_HoTen.setText("");
        txt_SDT.setText("");
        txt_Email.setText("");
        cb_GioiTinh.setSelectedIndex(0);
        cb_QuocTich.setSelectedIndex(0);

        table_KhachHang.clearSelection();
        txt_CCCD.setEditable(true);
        txt_CCCD.setFocusable(true);
        txt_CCCD.requestFocus();

    }

    public void LamMoiThongTinPhong() {
        txt_Phong.setText("");
        txt_LoaiPhong.setText("");
        txt_Tang.setText("");
        txt_DonGia.setText("");
        area_moTa.setText("Mô tả");
        model.setRowCount(0);

    }

    public KhachHang getKhachHangTheoMa(int ma) {
        for (KhachHang kh : list_KhachHang_TheoDon) {
            if (kh.getMaKhachHang() == ma) {
                return kh;
            }
        }
        return null;
    }

    public boolean removeKhachHang(int ma) {
        for (KhachHang kh : list_KhachHang_TheoDon) {
            if (kh.getMaKhachHang() == ma) {
                list_KhachHang_TheoDon.remove(kh);
                return true;
            }
        }
        return false;
    }

    public boolean updateKhachHang(KhachHang khachHang) {
        for (KhachHang kh : list_KhachHang_TheoDon) {
            if (kh.getMaKhachHang() == khachHang.getMaKhachHang()) {
                kh = khachHang;
                return true;
            }
        }
        return false;
    }

    public boolean updateKhachHangMoi(KhachHang khachHang) {
        for (KhachHang kh : list_KhachHangMoi) {
            if (kh.getMaKhachHang() == khachHang.getMaKhachHang()) {
                kh = khachHang;
                return true;
            }
        }
        return false;
    }

    public int maKHMoi() {

        for (int i = list_KhachHang.size() + 1; i <= list_KhachHangMoi.getLast().getMaKhachHang(); i++) {
            int dem = 0;
            for (KhachHang kh : list_KhachHangMoi) {
                if (i == kh.getMaKhachHang()) {
                    dem++;
                }

            }
            if (dem == 0) {
                count--;
                return i;
            }

        }
        return -1;
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
        ThongTinDat = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_NgayDen = new com.toedter.calendar.JDateChooser();
        txt_NgayDi = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_DonGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Jpanel_ThemPhong = new javax.swing.JPanel();
        txt_Phong = new javax.swing.JLabel();
        icon_Them = new javax.swing.JLabel();
        txt_LoaiPhong = new javax.swing.JTextField();
        txt_Tang = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        ThongTinKhachHang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_GioiTinh = new javax.swing.JComboBox<>();
        txt_CCCD = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cb_QuocTich = new javax.swing.JComboBox<>();
        txt_SDT = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        label_MaDonDatPhong = new javax.swing.JLabel();
        btn_LamMoi = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_them = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_KhachHang = new javax.swing.JTable();
        btn_HoanTat = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_ThemDon = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        label_MaHoaDon = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        area_moTa = new javax.swing.JTextArea();
        Backgroup = new javax.swing.JLabel();

        setName("page_DatPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 833));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 209, 84));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Thông tin khách hàng");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 290, 250, 32);

        ThongTinDat.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinDat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinDat.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ngày đến");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày đi");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Loại phòng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tầng");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Đơn giá");

        txt_DonGia.setEditable(false);
        txt_DonGia.setBackground(new java.awt.Color(225, 225, 225));
        txt_DonGia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_DonGia.setFocusable(false);
        txt_DonGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DonGiaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Phòng");

        Jpanel_ThemPhong.setBackground(new java.awt.Color(255, 255, 255));
        Jpanel_ThemPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Jpanel_ThemPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Jpanel_ThemPhong.setName("btn_Them"); // NOI18N
        Jpanel_ThemPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jpanel_ThemPhongMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Jpanel_ThemPhongMousePressed(evt);
            }
        });

        txt_Phong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        icon_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Them.png"))); // NOI18N
        icon_Them.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout Jpanel_ThemPhongLayout = new javax.swing.GroupLayout(Jpanel_ThemPhong);
        Jpanel_ThemPhong.setLayout(Jpanel_ThemPhongLayout);
        Jpanel_ThemPhongLayout.setHorizontalGroup(
            Jpanel_ThemPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel_ThemPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(icon_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Jpanel_ThemPhongLayout.setVerticalGroup(
            Jpanel_ThemPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel_ThemPhongLayout.createSequentialGroup()
                .addComponent(txt_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Jpanel_ThemPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_Them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_LoaiPhong.setEditable(false);
        txt_LoaiPhong.setBackground(new java.awt.Color(225, 225, 225));
        txt_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_LoaiPhong.setFocusable(false);
        txt_LoaiPhong.setPreferredSize(new java.awt.Dimension(64, 25));
        txt_LoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LoaiPhongActionPerformed(evt);
            }
        });

        txt_Tang.setEditable(false);
        txt_Tang.setBackground(new java.awt.Color(225, 225, 225));
        txt_Tang.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Tang.setFocusable(false);
        txt_Tang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TangActionPerformed(evt);
            }
        });

        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("*");

        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("*");

        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("*");

        javax.swing.GroupLayout ThongTinDatLayout = new javax.swing.GroupLayout(ThongTinDat);
        ThongTinDat.setLayout(ThongTinDatLayout);
        ThongTinDatLayout.setHorizontalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayDen, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(txt_LoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinDatLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThongTinDatLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_NgayDi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_Tang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 57, Short.MAX_VALUE)))
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Jpanel_ThemPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_DonGia)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        ThongTinDatLayout.setVerticalGroup(
            ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinDatLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel22))
                        .addGap(6, 6, 6)
                        .addComponent(txt_NgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel23))
                        .addGap(6, 6, 6)
                        .addComponent(txt_NgayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinDatLayout.createSequentialGroup()
                        .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel24))
                        .addGap(4, 4, 4)
                        .addComponent(Jpanel_ThemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ThongTinDatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ThongTinDatLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ThongTinDatLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_DonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTinDatLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_LoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinDat);
        ThongTinDat.setBounds(80, 90, 750, 180);

        ThongTinKhachHang.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinKhachHang.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CCCD");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Họ và tên");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Giới tính");

        cb_GioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cb_GioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_GioiTinhActionPerformed(evt);
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

        txt_HoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_HoTenFocusLost(evt);
            }
        });
        txt_HoTen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_HoTenMousePressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Số điện thoại");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Email");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Quốc tịch");

        cb_QuocTich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Việt Nam", "Hàn Quốc", "Trung Quốc" }));
        cb_QuocTich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_QuocTichActionPerformed(evt);
            }
        });

        txt_SDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_SDTFocusLost(evt);
            }
        });
        txt_SDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_SDTMousePressed(evt);
            }
        });

        txt_Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_EmailFocusLost(evt);
            }
        });
        txt_Email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_EmailMousePressed(evt);
            }
        });

        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
        jLabel25.setText("*");

        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("*");

        javax.swing.GroupLayout ThongTinKhachHangLayout = new javax.swing.GroupLayout(ThongTinKhachHang);
        ThongTinKhachHang.setLayout(ThongTinKhachHangLayout);
        ThongTinKhachHangLayout.setHorizontalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_QuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_GioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        ThongTinKhachHangLayout.setVerticalGroup(
            ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinKhachHangLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(6, 6, 6)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_GioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txt_HoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(6, 6, 6)
                .addGroup(ThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_QuocTich)
                    .addComponent(txt_SDT)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(ThongTinKhachHang);
        ThongTinKhachHang.setBounds(80, 330, 750, 180);

        label_MaDonDatPhong.setBackground(new java.awt.Color(255, 209, 84));
        label_MaDonDatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_MaDonDatPhong.setForeground(new java.awt.Color(255, 255, 255));
        label_MaDonDatPhong.setText("Mã đơn đặt phòng: ");
        label_MaDonDatPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(label_MaDonDatPhong);
        label_MaDonDatPhong.setBounds(80, 760, 240, 25);

        btn_LamMoi.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_LamMoi.setkGradientFocus(250);
        btn_LamMoi.setkStartColor(new java.awt.Color(225, 176, 27));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Làm Mới");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel18MousePressed(evt);
            }
        });

        javax.swing.GroupLayout btn_LamMoiLayout = new javax.swing.GroupLayout(btn_LamMoi);
        btn_LamMoi.setLayout(btn_LamMoiLayout);
        btn_LamMoiLayout.setHorizontalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_LamMoiLayout.setVerticalGroup(
            btn_LamMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_LamMoiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_LamMoi);
        btn_LamMoi.setBounds(1060, 440, 140, 40);

        btn_them.setkEndColor(new java.awt.Color(177, 177, 209));
        btn_them.setkGradientFocus(250);
        btn_them.setkStartColor(new java.awt.Color(84, 84, 84));
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
            .addGroup(btn_themLayout.createSequentialGroup()
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
        btn_them.setBounds(870, 370, 140, 40);

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
        btn_Xoa.setBounds(870, 440, 140, 40);

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
        btn_Sua.setBounds(1060, 370, 140, 40);

        table_KhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        table_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "CCCD/Passport", "Tên khách hàng", "Giới tính", "Số điện thoại", "Email", "Quốc tịch"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_KhachHang.setRowHeight(30);
        table_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_KhachHangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_KhachHang);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 540, 1130, 160);

        btn_HoanTat.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_HoanTat.setkGradientFocus(250);
        btn_HoanTat.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_HoanTat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_HoanTatMousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Hoàn tất");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_HoanTatLayout = new javax.swing.GroupLayout(btn_HoanTat);
        btn_HoanTat.setLayout(btn_HoanTatLayout);
        btn_HoanTatLayout.setHorizontalGroup(
            btn_HoanTatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HoanTatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_HoanTatLayout.setVerticalGroup(
            btn_HoanTatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HoanTatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_HoanTat);
        btn_HoanTat.setBounds(1070, 730, 140, 50);

        btn_ThemDon.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_ThemDon.setkGradientFocus(250);
        btn_ThemDon.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_ThemDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemDonMousePressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Tạo thêm đơn");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout btn_ThemDonLayout = new javax.swing.GroupLayout(btn_ThemDon);
        btn_ThemDon.setLayout(btn_ThemDonLayout);
        btn_ThemDonLayout.setHorizontalGroup(
            btn_ThemDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ThemDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_ThemDonLayout.setVerticalGroup(
            btn_ThemDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ThemDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_ThemDon);
        btn_ThemDon.setBounds(860, 730, 160, 50);

        jLabel21.setBackground(new java.awt.Color(255, 209, 84));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 209, 84));
        jLabel21.setText("Thông tin đặt phòng");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel21);
        jLabel21.setBounds(80, 50, 250, 32);

        label_MaHoaDon.setBackground(new java.awt.Color(255, 209, 84));
        label_MaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_MaHoaDon.setForeground(new java.awt.Color(255, 209, 84));
        label_MaHoaDon.setText("Mã hóa đơn: ");
        label_MaHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(label_MaHoaDon);
        label_MaHoaDon.setBounds(80, 730, 210, 25);

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setInheritsPopupMenu(true);
        jScrollPane2.setOpaque(false);

        area_moTa.setEditable(false);
        area_moTa.setBackground(new java.awt.Color(0, 0, 0));
        area_moTa.setColumns(1);
        area_moTa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        area_moTa.setForeground(new java.awt.Color(255, 255, 255));
        area_moTa.setLineWrap(true);
        area_moTa.setRows(3);
        area_moTa.setText("Mô tả");
        area_moTa.setCaretColor(new java.awt.Color(255, 255, 255));
        area_moTa.setFocusable(false);
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
        jScrollPane2.setBounds(870, 90, 330, 180);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_DatPhong"); // NOI18N
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
            .addGap(0, 803, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_GioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_GioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_GioiTinhActionPerformed

    private void cb_QuocTichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_QuocTichActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_QuocTichActionPerformed

    private void Jpanel_ThemPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jpanel_ThemPhongMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_Jpanel_ThemPhongMouseClicked

    private void Jpanel_ThemPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jpanel_ThemPhongMousePressed
        // TODO add your handling code here:
        List<Phong> list_PhongTrong = new ArrayList<Phong>();
        if (txt_NgayDen.getDate().equals(txt_NgayDi.getDate())) {
            list_PhongTrong = getAllPhongTrong(txt_NgayDen.getDate(), new Date(txt_NgayDen.getDate().getTime() + (24 * 60 * 60 * 1000)));
            System.out.println(list_PhongTrong);
                new LeTan_(list_PhongTrong, new LeTan_GUI(), true).setVisible(true);
//            new LeTan_DatPhong_ChonPhong_GUI(list_PhongTrong).setVisible(true);
            return;
        }

        
        list_PhongTrong = getAllPhongTrong(txt_NgayDen.getDate(), txt_NgayDi.getDate());
        new LeTan_(list_PhongTrong, new LeTan_GUI(), true).setVisible(true);
//        new LeTan_DatPhong_ChonPhong_GUI(list_PhongTrong).setVisible(true);


    }//GEN-LAST:event_Jpanel_ThemPhongMousePressed
    int count = 0;
    boolean BlockEvent = false;
    private void btn_themMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themMousePressed
        // TODO add your handling code here:

        if (BlockEvent == false) {
            return;
        }

        KhachHang khachHang = null;
        list_KhachHang = khachHang_dao.getAllKhachHang();
        for (KhachHang kh : list_KhachHang) {
            if (kh.getCCCD().equals(txt_CCCD.getText())) {
                khachHang = kh;
            }
        }

        if (khachHang == null) {

            khachHang = new KhachHang();
            if (count == 0) {
                khachHang.setMaKhachHang(list_KhachHang.size() + list_KhachHangMoi.size() + 1);

            } else {
                if (maKHMoi() == -1) {
                    khachHang.setMaKhachHang(list_KhachHang.size() + list_KhachHangMoi.size() + 1);
                } else {
                    khachHang.setMaKhachHang(maKHMoi());
                }

            }

            khachHang.setCCCD(txt_CCCD.getText());
            khachHang.setTenKhachHang(txt_HoTen.getText());
            khachHang.setSoDienThoai(txt_SDT.getText());
            khachHang.setGioiTinh(cb_GioiTinh.getSelectedItem().toString().equals("Nam") ? 1 : 0);
            khachHang.setQuocTich(cb_QuocTich.getSelectedItem().toString());
            khachHang.setEmail(txt_Email.getText());
            if (ktraTrungKH(txt_CCCD.getText())) {

                list_KhachHang_TheoDon.add(khachHang);
                list_KhachHangMoi.add(khachHang);
                Collections.sort(list_KhachHangMoi, new Comparator<KhachHang>() {
                    @Override
                    public int compare(KhachHang kh1, KhachHang kh2) {
                        return Integer.compare(kh1.getMaKhachHang(), kh2.getMaKhachHang());
                    }
                });
                ThemKhachHangVaoTable(khachHang);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                LamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại, khách hàng đã tồn tại trong phòng");
            }

        } else {
            if (ktraTrungKH(khachHang.getCCCD())) {
                list_KhachHang_TheoDon.add(khachHang);
                ThemKhachHangVaoTable(khachHang);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                LamMoi();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại, khách hàng đã tồn tại trong phòng");
            }

        }


    }//GEN-LAST:event_btn_themMousePressed

    private void txt_DonGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DonGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DonGiaActionPerformed

    int dem = 0;
    HoaDon hoadon_hientai;
    private void btn_ThemDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemDonMousePressed
        // TODO add your handling code here:  
        if (txt_Phong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng muốn đặt");
            return;
        }

        if (table_KhachHang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa có khách hàng !!! Vui phòng thêm khách hàng");
            return;
        }

        list_HoaDon = hoaDon_dao.getAllHoaDon();
        list_DonDatPhong = DonDatphong_dao.getAllDonDatPhong();
        if (dem == 0) {
            hoadon_hientai = new HoaDon();
            hoadon_hientai.setMaHoaDon(list_HoaDon.size() + 1);
            hoadon_hientai.setNgayTaoHoaDon(new Date());
            hoadon_hientai.setTongTien(0);
            hoadon_hientai.setNhanVien(DangNhap_GUI.nhanVien_DangSuDung);
            hoaDon_dao.createHoaDon(hoadon_hientai);

            dem++;
        }

        DonDatPhong ddp = new DonDatPhong();
        ddp.setMaDonDat(list_DonDatPhong.size() + 1);
        ddp.setNgayDatPhong(new Date());
        ddp.setNgayNhanPhong(txt_NgayDen.getDate());
        ddp.setNgayTraPhong(txt_NgayDi.getDate());
        ddp.setPhong(Integer.parseInt(txt_Phong.getText()));
        ddp.setKhachO(list_KhachHang_TheoDon);
        ddp.setDichVuSuDung(new ArrayList<DichVu>());
        ddp.setTrangThai(1);
        ddp.setHoaDon(hoadon_hientai.getMaHoaDon());

//       Lưu database
        DonDatphong_dao.createDonDatPhong(ddp);
        for (KhachHang kh : list_KhachHangMoi) {
            khachHang_dao.createKhachHang(kh);
        }
        list_HoaDon = hoaDon_dao.getAllHoaDon();
        list_DonDatPhong = DonDatphong_dao.getAllDonDatPhong();
        list_KhachHang = khachHang_dao.getAllKhachHang();

        label_MaDonDatPhong.setText("Mã đơn đặt phòng: " + (list_DonDatPhong.size() + 1));
        JOptionPane.showMessageDialog(this, "Tạo đơn đặt phòng thành công");
        LamMoi();
        LamMoiThongTinPhong();
        list_KhachHang_TheoDon = new ArrayList<KhachHang>();
        list_KhachHangMoi = new ArrayList<KhachHang>();
    }//GEN-LAST:event_btn_ThemDonMousePressed

    private void btn_HoanTatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HoanTatMousePressed
        // TODO add your handling code here:
        if (txt_Phong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng muốn đặt");
            return;
        }

        if (table_KhachHang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa có khách hàng !!! Vui phòng thêm khách hàng");
            return;
        }

        if (dem == 0) {
            hoadon_hientai = new HoaDon();
            hoadon_hientai.setMaHoaDon(list_HoaDon.size() + 1);
            hoadon_hientai.setNgayTaoHoaDon(new Date());
            hoadon_hientai.setTongTien(0);
            hoadon_hientai.setNhanVien(DangNhap_GUI.nhanVien_DangSuDung);
            hoaDon_dao.createHoaDon(hoadon_hientai);
        }

        DonDatPhong ddp = new DonDatPhong();
        ddp.setMaDonDat(list_DonDatPhong.size() + 1);
        ddp.setNgayDatPhong(new Date());
        ddp.setNgayNhanPhong(txt_NgayDen.getDate());
        ddp.setNgayTraPhong(txt_NgayDi.getDate());
        ddp.setPhong(Integer.parseInt(txt_Phong.getText()));
        ddp.setKhachO(list_KhachHang_TheoDon);
        ddp.setDichVuSuDung(new ArrayList<DichVu>());
        ddp.setTrangThai(1);
        ddp.setHoaDon(hoadon_hientai.getMaHoaDon());

//       Lưu database
        DonDatphong_dao.createDonDatPhong(ddp);
        for (KhachHang kh : list_KhachHangMoi) {
            khachHang_dao.createKhachHang(kh);
        }
        dem = 0;
        hoadon_hientai = null;

        list_HoaDon = hoaDon_dao.getAllHoaDon();
        list_DonDatPhong = DonDatphong_dao.getAllDonDatPhong();
        list_KhachHang = khachHang_dao.getAllKhachHang();
        label_MaDonDatPhong.setText("Mã đơn đặt phòng: " + (list_DonDatPhong.size() + 1));
        label_MaHoaDon.setText("Mã hóa đơn: " + (list_HoaDon.size() + 1));
        JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
        LamMoi();
        LamMoiThongTinPhong();
        list_KhachHang_TheoDon = new ArrayList<KhachHang>();
        list_KhachHangMoi = new ArrayList<KhachHang>();


    }//GEN-LAST:event_btn_HoanTatMousePressed

    private void area_moTaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_moTaFocusGained
        // TODO add your handling code here:
        if (area_moTa.getText().equals("Mô tả")) {
            area_moTa.setText("");
        }
    }//GEN-LAST:event_area_moTaFocusGained

    private void area_moTaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_area_moTaFocusLost
        // TODO add your handling code here:
        if (area_moTa.getText().equals("")) {
            area_moTa.setText("Mô tả");
        }
    }//GEN-LAST:event_area_moTaFocusLost

    private void txt_LoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LoaiPhongActionPerformed

    private void txt_TangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TangActionPerformed

    private void jLabel18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MousePressed
        // TODO add your handling code here:
        LamMoi();
    }//GEN-LAST:event_jLabel18MousePressed

    private void table_KhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_KhachHangMousePressed
        // TODO add your handling code here:
        if (table_KhachHang.getSelectedRow() == -1) {
            return;
        }

        KhachHang kh = getKhachHangTheoMa(Integer.parseInt(model.getValueAt(table_KhachHang.getSelectedRow(), 0).toString()));
        txt_CCCD.setText(kh.getCCCD());
        txt_HoTen.setText(kh.getTenKhachHang());
        txt_SDT.setText(kh.getSoDienThoai());
        txt_Email.setText(kh.getEmail());
        cb_GioiTinh.setSelectedIndex(kh.getGioiTinh() == 1 ? 0 : 1);
        cb_QuocTich.setSelectedItem(kh.getQuocTich());
        txt_CCCD.setFocusable(false);
        txt_CCCD.setEditable(false);
    }//GEN-LAST:event_table_KhachHangMousePressed

    private void txt_CCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CCCDActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_CCCDActionPerformed

    private void btn_SuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMousePressed
        // TODO add your handling code here:
        int row = table_KhachHang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa");
            return;
        }

        KhachHang khachHang = new KhachHang();
        khachHang.setMaKhachHang(Integer.parseInt(model.getValueAt(row, 0).toString()));
        khachHang.setCCCD(txt_CCCD.getText());
        khachHang.setTenKhachHang(txt_HoTen.getText());
        khachHang.setSoDienThoai(txt_SDT.getText());
        khachHang.setEmail(txt_Email.getText());
        khachHang.setGioiTinh(cb_GioiTinh.getSelectedIndex() == 0 ? 1 : 0);
        khachHang.setQuocTich(cb_QuocTich.getSelectedItem().toString());

        updateKhachHang(khachHang);
        updateKhachHangMoi(khachHang);

        model.setValueAt(model.getValueAt(row, 0).toString(), row, 0);
        model.setValueAt(khachHang.getCCCD(), row, 1);
        model.setValueAt(khachHang.getTenKhachHang(), row, 2);
        model.setValueAt(khachHang.getGioiTinh() == 1 ? "Nam" : "Nữ", row, 3);
        model.setValueAt(khachHang.getSoDienThoai(), row, 4);
        model.setValueAt(khachHang.getEmail(), row, 5);
        model.setValueAt(khachHang.getQuocTich(), row, 6);

        JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công");
        LamMoi();


    }//GEN-LAST:event_btn_SuaMousePressed

    private void btn_XoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMousePressed
        // TODO add your handling code here:
        if (table_KhachHang.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa");
            return;
        }

        int maKH = Integer.parseInt(model.getValueAt(table_KhachHang.getSelectedRow(), 0).toString());
        if (JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            removeKhachHang(maKH);
            model.removeRow(table_KhachHang.getSelectedRow());
            if (list_KhachHang_TheoDon.size() != 0) {
                for (KhachHang kh : list_KhachHang_TheoDon) {
                    if (maKH == kh.getMaKhachHang()) {
                        list_KhachHang_TheoDon.remove(kh);
                    }
                }
            }

            if (list_KhachHangMoi.size() != 0) {
                for (Iterator<KhachHang> iterator = list_KhachHangMoi.iterator(); iterator.hasNext();) {
                    KhachHang khachHang = iterator.next();
                    if (maKH == khachHang.getMaKhachHang()) {
                        iterator.remove();
                        count++;// Xóa phần tử an toàn qua Iterator
                    }
                }

            }

            JOptionPane.showMessageDialog(this, "Xóa thành công");
            LamMoi();

        }

    }//GEN-LAST:event_btn_XoaMousePressed


    private void txt_CCCDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_CCCDFocusLost
        // TODO add your handling code here:
        if (txt_CCCD.getText().equals("")) {
            return;
        }

        String regex = "[0-9]{12}";
        if (!txt_CCCD.getText().matches(regex)) {
            JOptionPane.showMessageDialog(this, "CCCD phải đủ 12 số, không phải là chữ");
            txt_CCCD.requestFocus();
            return;
        }

        String regex_HoTen = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+(\\s+[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+)+$";

        if (!txt_HoTen.getText().matches(regex_HoTen)) {
            txt_HoTen.requestFocus();
            return;
        }
        btn_them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_them.setkGradientFocus(250);
        btn_them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_them.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        btn_them.setBorder(null);
        BlockEvent = true;

    }//GEN-LAST:event_txt_CCCDFocusLost

    private void txt_HoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_HoTenFocusLost
        // TODO add your handling code here:

        if (txt_HoTen.getText().equals("")) {
            return;
        }
        String regex = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+(\\s+[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểễỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵỷỹ'-]+)+$";

        if (!txt_HoTen.getText().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Họ tên ít nhất 2 từ, không có số");
            txt_HoTen.requestFocus();
        }


    }//GEN-LAST:event_txt_HoTenFocusLost

    private void txt_SDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_SDTFocusLost
        // TODO add your handling code here:
        if (txt_SDT.getText().equals("")) {
            return;
        }
        String regex = "^(0[3|5|7|8|9])[0-9]{8}$";
        if (!txt_SDT.getText().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại ko chứa chữ, Số điện thoại không hợp lệ");
            txt_SDT.requestFocus();
        }


    }//GEN-LAST:event_txt_SDTFocusLost

    private void txt_EmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_EmailFocusLost
        // TODO add your handling code here:
        if (txt_Email.getText().equals("")) {
            return;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        String email = txt_Email.getText();
        if (!email.matches(regex)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ, ví dụ abc@example.com.");
            txt_Email.requestFocus();
        }
    }//GEN-LAST:event_txt_EmailFocusLost

    private void txt_HoTenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_HoTenMousePressed
        // TODO add your handling code here:
        if (txt_CCCD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập CCCD trước");
            txt_CCCD.requestFocus();
        }
    }//GEN-LAST:event_txt_HoTenMousePressed

    private void txt_SDTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_SDTMousePressed
        // TODO add your handling code here:
        if (txt_CCCD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập CCCD trước");
            txt_CCCD.requestFocus();
        }
    }//GEN-LAST:event_txt_SDTMousePressed

    private void txt_EmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_EmailMousePressed
        // TODO add your handling code here:
        if (txt_CCCD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập CCCD trước");
            txt_CCCD.requestFocus();
        }
    }//GEN-LAST:event_txt_EmailMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel Jpanel_ThemPhong;
    private javax.swing.JPanel ThongTinDat;
    private javax.swing.JPanel ThongTinKhachHang;
    public static javax.swing.JTextArea area_moTa;
    private keeptoo.KGradientPanel btn_HoanTat;
    private keeptoo.KGradientPanel btn_LamMoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_ThemDon;
    private keeptoo.KGradientPanel btn_Xoa;
    private keeptoo.KGradientPanel btn_them;
    private javax.swing.JComboBox<String> cb_GioiTinh;
    private javax.swing.JComboBox<String> cb_QuocTich;
    private javax.swing.JLabel icon_Them;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_MaDonDatPhong;
    private javax.swing.JLabel label_MaHoaDon;
    private javax.swing.JTable table_KhachHang;
    private javax.swing.JTextField txt_CCCD;
    public static javax.swing.JTextField txt_DonGia;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoTen;
    public static javax.swing.JTextField txt_LoaiPhong;
    private com.toedter.calendar.JDateChooser txt_NgayDen;
    private com.toedter.calendar.JDateChooser txt_NgayDi;
    public static javax.swing.JLabel txt_Phong;
    private javax.swing.JTextField txt_SDT;
    public static javax.swing.JTextField txt_Tang;
    // End of variables declaration//GEN-END:variables
}
