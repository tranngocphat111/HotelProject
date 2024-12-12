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
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
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
import static model.DTO.TienNghi.sapXepTienNghiTheoMa;

/**
 *
 * @author Admin
 */
public class NhanVien_LoaiPhong_GUI extends javax.swing.JInternalFrame {

    private List<LoaiPhong> list_LoaiPhong = new ArrayList<LoaiPhong>();
    private LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database);
    private List<Phong> list_Phong = new ArrayList<Phong>();
    private PhongDAO phong_dao = new PhongDAO(database);
    private List<DonDatPhong> list_DonDatPhong = new ArrayList<>();
    private DonDatPhongDAO dondatphong_dao = new DonDatPhongDAO(database);
    private List<TienNghi> list_TienNghi = new ArrayList<TienNghi>();
    private List<TienNghi> list_TienNghiDuocChon = new ArrayList<TienNghi>();
    private TienNghiDAO tienNghi_dao = new TienNghiDAO(database);
    private ArrayList<KGradientPanel> list_btn = new ArrayList<KGradientPanel>();
    private ArrayList<KGradientPanel> list_btnTienNghi = new ArrayList<KGradientPanel>();
    private DefaultTableModel model;
    private Map<KGradientPanel, Boolean> clickMap = new HashMap<>();
    DefaultTableCellRenderer centerRenderer;
    DecimalFormat df = new DecimalFormat("#,##0");
    private NhanVien nhanVien_DangSuDung;
    /**
     * Creates new form LeTan_DatPhong_GUI
     */
    public NhanVien_LoaiPhong_GUI(NhanVien nhanVien_DangSuDung) {
        this.nhanVien_DangSuDung = nhanVien_DangSuDung;
        initComponents();
        jLabel1.setText(this.nhanVien_DangSuDung.getTenNhanVien());
        ImageScale.setCircularImage(label_Avatar, new ImageScale().getScaledImage1(50, 50, new ImageIcon(nhanVien_DangSuDung.getAnhDaiDien())));
        jScrollPane2.setOpaque(false); // Làm JScrollPane trong suốt
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setUI(new javax.swing.plaf.basic.BasicScrollPaneUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                // Không vẽ gì cả, loại bỏ hoàn toàn nền
            }
        });
        jScrollPane2.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
        Panel_TienNghi.setBorder(new EmptyBorder(10, 15, 10, 10));
        table_LoaiPhong.setSelectionBackground(new Color(255, 222, 89));
        table_LoaiPhong.setSelectionForeground(new Color(0, 0, 0));
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(5);
        table_LoaiPhong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//      Đọc dữ liệu từ database lên
        list_LoaiPhong = loaiPhong_dao.getAllLoaiPhongSort();
        list_TienNghi = tienNghi_dao.SortTienNghiTheoMa();

//      set số dòng của tiện nghi
        Panel_TienNghi.setLayout(new java.awt.GridLayout(getRowTienNghi(list_TienNghi.size()), 2, 18, 17));

//      Load tiện nghi lên Frame
        DocDataLenFrame();

//        Xét kính thước cho header
        JTableHeader header = table_LoaiPhong.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setFont(new Font("Arial", Font.BOLD, 15));

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        // Thiết lập renderer cho header
        header.setDefaultRenderer(renderer);
        //Căn giữa các phần tử trong table
        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setVerticalAlignment(JLabel.CENTER);

//        Load dữ liệu lên table
        model = (DefaultTableModel) table_LoaiPhong.getModel();
        model.setRowCount(0);
        cb_Loaigiuong.insertItemAt("Tất cả", 0);
        cb_Loaigiuong.setSelectedIndex(0);
        DocDataLenTable(list_LoaiPhong);

        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);

        list_btn.add(btn_Them);
        list_btn.add(btn_Sua);
        list_btn.add(btn_Xoa);
        list_btn.add(btn_Lammoi);
        list_btn.add(btn_Tim);

//        Bắt sự kiện các nút chức năng
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
        boolean click;
        table_LoaiPhong.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                // Kiểm tra nếu không còn sự kiện đang thay đổi
                if (table_LoaiPhong.getSelectedRow() == -1) {
                    return;
                } else {
                    int selectedRow = table_LoaiPhong.getSelectedRow();
                    String tenloaiphong = model.getValueAt(selectedRow, 1) + "";
                    txt_TenLoaiphong.setText(tenloaiphong);
                    String dientich = model.getValueAt(selectedRow, 4) + "";
                    dientich = dientich.substring(0, dientich.length() - 3);
                    txt_Dientich.setText(dientich);
                    String loaigiuong = model.getValueAt(selectedRow, 2) + "";
                    if (loaigiuong.equals("Đơn")) {
                        cb_Loaigiuong.setSelectedIndex(1);
                    } else {
                        cb_Loaigiuong.setSelectedIndex(2);
                    }
                    String soKhachToida = model.getValueAt(selectedRow, 3) + "";
                    txt_Sokhachtoida.setText(soKhachToida);
                    String donGia = model.getValueAt(selectedRow, 6) + "";
                    donGia = donGia.substring(0, donGia.length() - 4);
                    donGia = donGia.replace(",", "");
                    txt_Dongia.setText(donGia);

                    String tiennghi = model.getValueAt(selectedRow, 5) + "";
                    String[] list_tn = tiennghi.split(", ");
                    list_TienNghiDuocChon = new ArrayList<>();
                    resetTienNghi();

                    int maLoaiPhong = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

                    LoaiPhong x = loaiPhong_dao.getLoaiPhongByMa(maLoaiPhong);

                    for (KGradientPanel btn_tiennghi : list_btnTienNghi) {
                        for (String s : list_tn) {
                            if (s.trim().equals(tienNghi_dao.getTienNghiByMa(Integer.parseInt(btn_tiennghi.getName())).getTenTienNghi())) {
                                btn_tiennghi.setkEndColor(new java.awt.Color(255, 222, 89));
                                btn_tiennghi.setkStartColor(new java.awt.Color(225, 176, 27));
                                btn_tiennghi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                                btn_tiennghi.setBorder(null);
                                clickMap.put(btn_tiennghi, true);
                                list_TienNghiDuocChon.add(tienNghi_dao.getTienNghiByMa(Integer.parseInt(btn_tiennghi.getName())));
                            }
                        }
                    }
                }
            }
        });
//        Bắt sự kiện chọn Tiện Nghi
        list_btnTienNghi.forEach((element) -> {
            clickMap.put(element, false);
            element.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    boolean click = clickMap.get(element);
                    if (!click) {
                        element.setkEndColor(new java.awt.Color(255, 222, 89));
                        element.setkStartColor(new java.awt.Color(225, 176, 27));
                        element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
                        clickMap.put(element, true);
                        list_TienNghiDuocChon.add(tienNghi_dao.getTienNghiByMa(Integer.parseInt(element.getName())));
                    } else {
                        element.setkStartColor(new java.awt.Color(255, 255, 255));
                        element.setkEndColor(new java.awt.Color(255, 255, 255));
                        element.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                        element.setBorder(null);
                        clickMap.put(element, false);
                        list_TienNghiDuocChon.remove(tienNghi_dao.getTienNghiByMa(Integer.parseInt(element.getName())));
                    }

//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        });

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
    }

    public void resetTienNghi() {
        for (KGradientPanel btn_tiennghi : list_btnTienNghi) {
            boolean click = clickMap.get(btn_tiennghi);
            if (click) {
                btn_tiennghi.setkStartColor(new java.awt.Color(255, 255, 255));
                btn_tiennghi.setkEndColor(new java.awt.Color(255, 255, 255));
                btn_tiennghi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
                btn_tiennghi.setBorder(null);
                clickMap.put(btn_tiennghi, false);
                list_TienNghiDuocChon.remove(tienNghi_dao.getTienNghiByMa(Integer.parseInt(btn_tiennghi.getName())));
            }
        }
    }

    public void DocDataLenTable(List<LoaiPhong> list_LoaiPhongs) {
        model.setRowCount(0);
        for (LoaiPhong loaiPhong : list_LoaiPhongs) {
            loaiPhong.sortTienNghis();
            model.addRow(new Object[]{
                loaiPhong.getMaLoaiPhong(),
                loaiPhong.getTenLoaiPhong(),
                loaiPhong.getLoaiGiuong(),
                loaiPhong.getSoKhachToiDa(),
                loaiPhong.getDienTich() + " m2",
                getListTienNghi(loaiPhong.getTienNghis()),
                df.format(loaiPhong.getDonGia()) + " VND"
            });
        }

        for (int i = 0; i < table_LoaiPhong.getColumnCount(); i++) {
            table_LoaiPhong.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void addRowTable(LoaiPhong loaiPhong) {
        model.addRow(new Object[]{
            loaiPhong.getMaLoaiPhong(),
            loaiPhong.getTenLoaiPhong(),
            loaiPhong.getLoaiGiuong(),
            loaiPhong.getSoKhachToiDa(),
            loaiPhong.getDienTich() + " m2",
            getListTienNghi(loaiPhong.getTienNghis()),
            df.format(loaiPhong.getDonGia()) + " VND"
        });
    }

    public String getListTienNghi(List<TienNghi> list_tienNghi) {
        String list = "";

        for (TienNghi tn : list_tienNghi) {
            list = list + tn.getTenTienNghi() + ", ";
        }
        if (list.length() == 0) {
            return "";
        }
        list = list.substring(0, list.length() - 2);
        return list;
    }

    public void DocDataLenFrame() {
        int i = 1;
        for (TienNghi tienNghi : list_TienNghi) {
            KGradientPanel btnGradientPanel = new KGradientPanel();
            btnGradientPanel.setkEndColor(new java.awt.Color(255, 255, 255));
            btnGradientPanel.setkGradientFocus(250);
            btnGradientPanel.setkStartColor(new java.awt.Color(255, 255, 255));
            btnGradientPanel.setPreferredSize(new java.awt.Dimension(125, 47));

            JLabel txt_TenTienNghi = new JLabel();
            txt_TenTienNghi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            txt_TenTienNghi.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
            txt_TenTienNghi.setText(tienNghi.getTenTienNghi());
            txt_TenTienNghi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            JLabel icon_TienNghi = new JLabel();
            ImageIcon icon = new ImageScale().load1(new ImageIcon(tienNghi.getHinhAnh()), 32, 30);
            icon_TienNghi.setIcon(icon);

            javax.swing.GroupLayout btnGradientPanelLayout = new javax.swing.GroupLayout(btnGradientPanel);
            btnGradientPanel.setLayout(btnGradientPanelLayout);
            btnGradientPanelLayout.setHorizontalGroup(
                    btnGradientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGradientPanelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(icon_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_TenTienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
            );
            btnGradientPanelLayout.setVerticalGroup(
                    btnGradientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenTienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(icon_TienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            btnGradientPanel.setName("" + tienNghi.getMaTienNghi());
            System.out.println("name: " + btnGradientPanel.getName());
            list_btnTienNghi.add(btnGradientPanel);
            Panel_TienNghi.add(btnGradientPanel);
        }
    }

    public int getRowTienNghi(int n) {

        return n / 2 + n % 2;

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
        ThongTinLoaiPhong = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_Loaigiuong = new javax.swing.JComboBox<>();
        txt_TenLoaiphong = new javax.swing.JTextField();
        txt_Dientich = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_Sokhachtoida = new javax.swing.JTextField();
        txt_Dongia = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_Tim = new keeptoo.KGradientPanel();
        jLabel19 = new javax.swing.JLabel();
        btn_Lammoi = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        btn_Them = new keeptoo.KGradientPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_Xoa = new keeptoo.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        btn_Sua = new keeptoo.KGradientPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_LoaiPhong = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        Panel_TienNghi = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        circlePanel1 = new GUI.CirclePanel_Atatar();
        label_Avatar = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Backgroup = new javax.swing.JLabel();

        setName("page_LoaiPhong"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1283, 830));

        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 803));
        jPanel1.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(255, 209, 84));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 84));
        jLabel2.setText("Tiện nghi");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(910, 90, 130, 32);

        ThongTinLoaiPhong.setBackground(new java.awt.Color(0, 0, 0));
        ThongTinLoaiPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        ThongTinLoaiPhong.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên loại phòng ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Diện tích");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Loại giường");

        cb_Loaigiuong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cb_Loaigiuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đơn", "Đôi" }));
        cb_Loaigiuong.setFocusable(false);
        cb_Loaigiuong.setPreferredSize(new java.awt.Dimension(72, 32));
        cb_Loaigiuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LoaigiuongActionPerformed(evt);
            }
        });

        txt_TenLoaiphong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_TenLoaiphong.setPreferredSize(new java.awt.Dimension(64, 35));

        txt_Dientich.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Dientich.setPreferredSize(new java.awt.Dimension(64, 35));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Số khách tối đa");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Đơn giá");

        txt_Sokhachtoida.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Sokhachtoida.setPreferredSize(new java.awt.Dimension(64, 35));
        txt_Sokhachtoida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SokhachtoidaActionPerformed(evt);
            }
        });

        txt_Dongia.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Dongia.setPreferredSize(new java.awt.Dimension(64, 35));

        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
        jLabel25.setText("*");

        jLabel26.setForeground(new java.awt.Color(255, 0, 0));
        jLabel26.setText("*");

        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setText("*");

        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("*");

        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("*");

        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("*");

        javax.swing.GroupLayout ThongTinLoaiPhongLayout = new javax.swing.GroupLayout(ThongTinLoaiPhong);
        ThongTinLoaiPhong.setLayout(ThongTinLoaiPhongLayout);
        ThongTinLoaiPhongLayout.setHorizontalGroup(
            ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_Sokhachtoida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTinLoaiPhongLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txt_TenLoaiphong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                .addGap(48, 48, 48)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_Dongia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTinLoaiPhongLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_Dientich, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addGap(47, 47, 47)
                        .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_Loaigiuong, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                    .addGap(366, 366, 366)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(414, Short.MAX_VALUE)))
        );
        ThongTinLoaiPhongLayout.setVerticalGroup(
            ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinLoaiPhongLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addGap(6, 6, 6)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_Loaigiuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_Dientich, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TenLoaiphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(6, 6, 6)
                .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Sokhachtoida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Dongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(ThongTinLoaiPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinLoaiPhongLayout.createSequentialGroup()
                    .addContainerGap(127, Short.MAX_VALUE)
                    .addComponent(jLabel30)
                    .addGap(55, 55, 55)))
        );

        jPanel1.add(ThongTinLoaiPhong);
        ThongTinLoaiPhong.setBounds(80, 140, 790, 200);

        jLabel14.setBackground(new java.awt.Color(255, 209, 84));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 84));
        jLabel14.setText("Thông tin loại phòng");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel14);
        jLabel14.setBounds(80, 90, 250, 32);

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
        btn_Tim.setBounds(730, 360, 140, 40);

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
        btn_Lammoi.setBounds(560, 360, 140, 40);

        btn_Them.setkEndColor(new java.awt.Color(255, 222, 89));
        btn_Them.setkGradientFocus(250);
        btn_Them.setkStartColor(new java.awt.Color(225, 176, 27));
        btn_Them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ThemMousePressed(evt);
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
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btn_Them);
        btn_Them.setBounds(80, 360, 140, 40);

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
        btn_Xoa.setBounds(240, 360, 140, 40);

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
        btn_Sua.setBounds(400, 360, 140, 40);

        table_LoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        table_LoaiPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Loại", "Tên Loại", "Loại giường", "Số khách tối đa", "Diện tích", "Tiện nghi", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_LoaiPhong.setFocusable(false);
        table_LoaiPhong.setRowHeight(30);
        table_LoaiPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_LoaiPhongMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_LoaiPhong);
        if (table_LoaiPhong.getColumnModel().getColumnCount() > 0) {
            table_LoaiPhong.getColumnModel().getColumn(0).setMaxWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(1).setPreferredWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(1).setMaxWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(2).setPreferredWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(2).setMaxWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(3).setPreferredWidth(150);
            table_LoaiPhong.getColumnModel().getColumn(3).setMaxWidth(150);
            table_LoaiPhong.getColumnModel().getColumn(4).setPreferredWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(4).setMaxWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(6).setPreferredWidth(130);
            table_LoaiPhong.getColumnModel().getColumn(6).setMaxWidth(130);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(80, 430, 1130, 336);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 209, 84)));
        jPanel3.setOpaque(false);

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(456, 316));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setOpaque(false);

        Panel_TienNghi.setBackground(new java.awt.Color(0, 0, 0));
        Panel_TienNghi.setOpaque(false);
        Panel_TienNghi.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_TienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_TienNghi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Panel_TienNghi.getAccessibleContext().setAccessibleParent(jPanel2);

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(910, 140, 300, 260);

        jPanel4.setOpaque(false);
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
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

        jPanel5.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 209, 84));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nguyễn Hoàng Sang");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(circlePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(circlePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(1000, 0, 280, 70);

        Backgroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Backgroup.png"))); // NOI18N
        Backgroup.setName("page_LoaiPhong"); // NOI18N
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

    private void table_LoaiPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_LoaiPhongMousePressed
        // TODO add your handling code here:

        //                for(TienNghi t : x.getTienNghis()) {
        //                    if(t.getMaTienNghi() == Integer.parseInt(btn_tiennghi.getName())) {
        //                        btn_tiennghi.setkEndColor(new java.awt.Color(255, 222, 89));
        //                        btn_tiennghi.setkStartColor(new java.awt.Color(225, 176, 27));
        //                        btn_tiennghi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 1));
        //                        btn_tiennghi.setBorder(null);
        //                        clickMap.put(btn_tiennghi, true);
        //                        list_TienNghiDuocChon.add(tienNghi_dao.getTienNghiByMa(Integer.parseInt(btn_tiennghi.getName())));
        //                    }
        //                }
    }//GEN-LAST:event_table_LoaiPhongMousePressed

    private void btn_SuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SuaMousePressed
        // TODO add your handling code here:

        if (table_LoaiPhong.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        int selectedRow = table_LoaiPhong.getSelectedRow();
        int maLoaiPhong = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        String tenloaiPhong = model.getValueAt(selectedRow, 1).toString();
        if (checkTenLoaiPhongDangSuDung(tenloaiPhong)) {
            JOptionPane.showMessageDialog(this, "Loại phòng đang được sử dụng!!! Không thể sửa");
            return;
        }

        if (txt_TenLoaiphong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập tên loại phòng");
            txt_TenLoaiphong.requestFocus();
            return;
        }

        for (LoaiPhong lp : loaiPhong_dao.getAllLoaiPhongSort()) {
            if (txt_TenLoaiphong.getText().trim().equals(lp.getTenLoaiPhong()) && lp.getMaLoaiPhong() != maLoaiPhong) {
                JOptionPane.showMessageDialog(this, "Tên loại phòng trùng");
                txt_TenLoaiphong.requestFocus();
                return;
            }
        }

        String regex = "^[a-zA-Z].*";
        if (!txt_TenLoaiphong.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Tên loại phòng bắt đầu bằng chữ");
            txt_TenLoaiphong.requestFocus();
            return;
        }

        if (txt_Dientich.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập diện tích");
            txt_Dientich.requestFocus();
            return;
        }

        regex = "\\d+";
        if (!txt_Dientich.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Diện tích phải là số");
            txt_Dientich.requestFocus();
            return;
        }

        if (cb_Loaigiuong.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn loại giường khác 'Tất cả'");
            txt_Dientich.requestFocus();
            return;
        }

        if (txt_Sokhachtoida.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập số khách tối đa");
            txt_Sokhachtoida.requestFocus();
            return;
        }

        if (!txt_Sokhachtoida.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Số khách tối đa phải là số");
            txt_Sokhachtoida.requestFocus();
            return;
        }

        if (txt_Dongia.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập đơn giá");
            txt_Dongia.requestFocus();
            return;
        }

        if (!txt_Dongia.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            txt_Dongia.requestFocus();
            return;
        }

        if (list_TienNghiDuocChon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn tiện nghi");
            txt_Dongia.requestFocus();
            return;
        }

        LoaiPhong lp = new LoaiPhong();

        String tenLoaiPhong = txt_TenLoaiphong.getText().trim();
        int dienTich = Integer.parseInt(txt_Dientich.getText().trim());
        int soLuongKhachToiDa = Integer.parseInt(txt_Sokhachtoida.getText().trim());
        int donGia = Integer.parseInt(txt_Dongia.getText().trim());
        String loaiGiuong;
        if (cb_Loaigiuong.getSelectedIndex() == 1) {
            loaiGiuong = "Đơn";
        } else {
            loaiGiuong = "Đôi";
        }
        lp.setMaLoaiPhong(maLoaiPhong);
        lp.setTenLoaiPhong(tenLoaiPhong);
        lp.setDienTich(dienTich);
        lp.setSoKhachToiDa(soLuongKhachToiDa);
        lp.setDonGia(donGia);
        lp.setLoaiGiuong(loaiGiuong);
        lp.setTienNghis(list_TienNghiDuocChon);
        sapXepTienNghiTheoMa(list_TienNghiDuocChon);

        for (LoaiPhong lphong : loaiPhong_dao.getAllLoaiPhongSort()) {
            sapXepTienNghiTheoMa(lphong.getTienNghis());
            if (lphong.getTenLoaiPhong().equals(lp.getTenLoaiPhong())
                    && lp.getDienTich() == lphong.getDienTich()
                    && lphong.getLoaiGiuong().equals(lp.getLoaiGiuong())
                    && lphong.getSoKhachToiDa() == lp.getSoKhachToiDa()
                    && lphong.getDonGia() == lp.getDonGia()
                    && getListTienNghi(list_TienNghiDuocChon).equals(getListTienNghi(lphong.getTienNghis()))) {
                JOptionPane.showMessageDialog(this, "Bạn chưa thay đổi gì");
                return;
            }
        }

        model.setValueAt(tenLoaiPhong, selectedRow, 1);
        model.setValueAt(loaiGiuong, selectedRow, 2);
        model.setValueAt(soLuongKhachToiDa, selectedRow, 3);
        model.setValueAt(dienTich + " m2", selectedRow, 4);
        model.setValueAt(getListTienNghi(list_TienNghiDuocChon), selectedRow, 5);
        model.setValueAt(df.format(donGia) + " VND", selectedRow, 6);

        loaiPhong_dao.updateLoaiPhong(lp);
        JOptionPane.showMessageDialog(this, "Sửa thành công");
    }//GEN-LAST:event_btn_SuaMousePressed

    private void btn_XoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_XoaMousePressed
        // TODO add your handling code here:
        if (table_LoaiPhong.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng cần xóa");
            return;
        }

        int maLoaiPhong = Integer.parseInt(model.getValueAt(table_LoaiPhong.getSelectedRow(), 0).toString());
        String tenLoaiPhong = model.getValueAt(table_LoaiPhong.getSelectedRow(), 1).toString();
        if (checkTenLoaiPhongDangSuDung(tenLoaiPhong)) {
            JOptionPane.showMessageDialog(this, "Loại phòng đang được sử dụng!!! Không thể xóa");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có thật sự muốn xóa loại phòng?" + "\n" + "Các phòng liên quan sẽ bị xóa?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            model.removeRow(table_LoaiPhong.getSelectedRow());
            loaiPhong_dao.deleteLoaiPhong(maLoaiPhong);
            list_Phong = phong_dao.getAllPhongsSortByMaPhong();
            list_Phong = getAllPhongByLoaiPhong(list_Phong, maLoaiPhong);
            for (Phong p : list_Phong) {
                phong_dao.deletePhong(p.getMaPhong());
            }
            txt_TenLoaiphong.setText("");
            txt_TenLoaiphong.requestFocus();
            txt_Dientich.setText("");
            cb_Loaigiuong.setSelectedIndex(0);
            txt_Sokhachtoida.setText("");
            txt_Dongia.setText("");
            resetTienNghi();
        }
    }//GEN-LAST:event_btn_XoaMousePressed

    private void btn_ThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ThemMousePressed
        // TODO add your handling code here:
        list_LoaiPhong = loaiPhong_dao.getAllLoaiPhongSort();

        if (txt_TenLoaiphong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập tên loại phòng");
            txt_TenLoaiphong.requestFocus();
            return;
        }

        for (LoaiPhong lp : list_LoaiPhong) {
            if (lp.getTenLoaiPhong().equals(txt_TenLoaiphong.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Trùng tên loại phòng");
                return;
            }
        }

        String regex = "^[a-zA-Z].*";
        if (!txt_TenLoaiphong.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Tên loại phòng bắt đầu bằng chữ");
            txt_TenLoaiphong.requestFocus();
            return;
        }

        if (txt_Dientich.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập diện tích");
            txt_Dientich.requestFocus();
            return;
        }

        regex = "\\d+";
        if (!txt_Dientich.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Diện tích phải là số");
            txt_Dientich.requestFocus();
            return;
        }

        if (cb_Loaigiuong.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn loại giường khác 'Tất cả'");
            txt_Dientich.requestFocus();
            return;
        }

        if (txt_Sokhachtoida.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập số khách tối đa");
            txt_Sokhachtoida.requestFocus();
            return;
        }

        if (!txt_Sokhachtoida.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Số khách tối đa phải là số");
            txt_Sokhachtoida.requestFocus();
            return;
        }

        if (txt_Dongia.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập đơn giá");
            txt_Dongia.requestFocus();
            return;
        }

        if (!txt_Dongia.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            txt_Dongia.requestFocus();
            return;
        }

        if (list_TienNghiDuocChon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn tiện nghi");
            txt_Dongia.requestFocus();
            return;
        }

        LoaiPhong loaiPhongMoi = new LoaiPhong();
        int tam = taoMaTuDong(getListLoaiPhong());
        loaiPhongMoi.setMaLoaiPhong(tam);
        loaiPhongMoi.setTenLoaiPhong(txt_TenLoaiphong.getText().trim());
        loaiPhongMoi.setDienTich(Integer.parseInt(txt_Dientich.getText().trim()));
        loaiPhongMoi.setDonGia(Integer.parseInt(txt_Dongia.getText().trim()));
        sapXepTienNghiTheoMa(list_TienNghiDuocChon);
        loaiPhongMoi.setTienNghis(list_TienNghiDuocChon);
        loaiPhongMoi.setLoaiGiuong(cb_Loaigiuong.getSelectedItem().toString());
        loaiPhongMoi.setSoKhachToiDa(Integer.parseInt(txt_Sokhachtoida.getText().trim()));
        loaiPhong_dao.createLoaiPhong(loaiPhongMoi);

        list_TienNghi = tienNghi_dao.getAllTienNghi();

        model.insertRow(0, new Object[]{
            tam,
            txt_TenLoaiphong.getText().trim(),
            cb_Loaigiuong.getSelectedItem().toString(),
            Integer.valueOf(txt_Sokhachtoida.getText().trim()),
            Integer.valueOf(txt_Dientich.getText().trim()) + " m2",
            getListTienNghi(list_TienNghiDuocChon),
            df.format(Integer.parseInt(txt_Dongia.getText().trim())) + " VND"});
        table_LoaiPhong.setRowSelectionInterval(1, 0);
        JOptionPane.showMessageDialog(this, "Thêm thành công");
    }//GEN-LAST:event_btn_ThemMousePressed

    private void btn_LammoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LammoiMousePressed
        // TODO add your handling code here:
        lamMoi();
        DocDataLenTable(list_LoaiPhong);
    }//GEN-LAST:event_btn_LammoiMousePressed

    private void btn_TimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_TimMousePressed
        // TODO add your handling code here:
        List<LoaiPhong> list_LP = loaiPhong_dao.getAllLoaiPhongSort();
        if (!txt_TenLoaiphong.getText().trim().isEmpty()) {
            list_LP = getLoaiPhongByTenLoaiPhong(list_LP, txt_TenLoaiphong.getText().trim());
        }
        if (!txt_Dientich.getText().trim().isEmpty()) {
            list_LP = getLoaiPhongByDienTich(list_LP, Integer.parseInt(txt_Dientich.getText().trim()));
        }

        if (cb_Loaigiuong.getSelectedIndex() != 0) {
            list_LP = getLoaiPhongByLoaiGiuong(list_LP, cb_Loaigiuong.getSelectedIndex());
        }

        if (!txt_Sokhachtoida.getText().trim().isEmpty()) {
            list_LP = getLoaiPhongBySoKhachToiDa(list_LP, Integer.parseInt(txt_Sokhachtoida.getText().trim()));
        }

        if (!txt_Dongia.getText().trim().isEmpty()) {
            list_LP = getLoaiPhongByDonGia(list_LP, Integer.parseInt(txt_Dongia.getText().trim()));
        }

        if (!list_TienNghiDuocChon.isEmpty()) {
            list_LP = getLoaiPhongByTienNghi(list_LP, list_TienNghiDuocChon);
        }

        DocDataLenTable(list_LP);
    }//GEN-LAST:event_btn_TimMousePressed

    private void txt_SokhachtoidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SokhachtoidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SokhachtoidaActionPerformed

    private void cb_LoaigiuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LoaigiuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_LoaigiuongActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        new TrangCaNhan(nhanVien_DangSuDung).setVisible(true);
    }//GEN-LAST:event_jPanel4MouseClicked

    public void checkRegex() {
        if (txt_TenLoaiphong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập tên phòng");
            txt_TenLoaiphong.requestFocus();
            return;
        }

        if (txt_Dientich.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập diện tích");
            txt_Dientich.requestFocus();
            return;
        }

        String regex = "\\d+";
        if (!txt_Dientich.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Diện tích phải là số");
            txt_Dientich.setText("");
            txt_Dientich.requestFocus();
            return;
        }

        if (cb_Loaigiuong.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn loại giường khác 'Tất cả'");
            txt_Dientich.requestFocus();
            return;
        }

        if (txt_Sokhachtoida.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập số khách tối đa");
            txt_Sokhachtoida.requestFocus();
            return;
        }

        if (!txt_Sokhachtoida.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Số khách tối đa phải là số");
            txt_Sokhachtoida.setText("");
            txt_Sokhachtoida.requestFocus();
            return;
        }

        if (txt_Dongia.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Nhập đơn giá");
            txt_Dongia.requestFocus();
            return;
        }

        if (!txt_Dongia.getText().trim().matches(regex)) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            txt_Dongia.setText("");
            txt_Dongia.requestFocus();
            return;
        }

        if (list_TienNghiDuocChon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn tiện nghi");
            txt_Dongia.requestFocus();
            return;
        }
    }

    public void lamMoi() {
        txt_TenLoaiphong.setText("");
        txt_TenLoaiphong.requestFocus();
        txt_Dientich.setText("");
        cb_Loaigiuong.setSelectedIndex(0);
        txt_Sokhachtoida.setText("");
        txt_Dongia.setText("");
        resetTienNghi();
        table_LoaiPhong.clearSelection();
        list_LoaiPhong = loaiPhong_dao.getAllLoaiPhongSort();
        DocDataLenTable(list_LoaiPhong);
    }

    public boolean checkTenLoaiPhongDangSuDung(String tenLoaiPhong) {
        return dondatphong_dao.kiemTraTenLoaiPhongDangSuDung(tenLoaiPhong);
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

    public List<LoaiPhong> getLoaiPhongByTenLoaiPhong(List<LoaiPhong> list_LoaiPhong, String loaiPhong) {
        List<LoaiPhong> list_PhongByTenLoai = new ArrayList<>();
        for (LoaiPhong lp : list_LoaiPhong) {
            if (lp.getTenLoaiPhong().equals(loaiPhong)) {
                list_PhongByTenLoai.add(lp);
            }

        }
        return list_PhongByTenLoai;
    }

    public List<LoaiPhong> getLoaiPhongByDienTich(List<LoaiPhong> list_LoaiPhong, int dientich) {
        List<LoaiPhong> list_PhongByDienTich = new ArrayList<>();
        for (LoaiPhong lp : list_LoaiPhong) {
            if (lp.getDienTich() == dientich) {
                list_PhongByDienTich.add(lp);
            }

        }
        return list_PhongByDienTich;
    }

    public List<LoaiPhong> getLoaiPhongByLoaiGiuong(List<LoaiPhong> list_LoaiPhong, int loaiGiuong) {
        List<LoaiPhong> list_PhongByLoaiGiuong = new ArrayList<>();
        String lg;
        if (loaiGiuong == 1) {
            lg = "Đơn";
        } else {
            lg = "Đôi";
        }
        for (LoaiPhong lp : list_LoaiPhong) {
            if (lp.getLoaiGiuong().equals(lg)) {
                list_PhongByLoaiGiuong.add(lp);
            }
        }
        return list_PhongByLoaiGiuong;
    }

    public List<LoaiPhong> getLoaiPhongBySoKhachToiDa(List<LoaiPhong> list_LoaiPhong, int sokhachtoida) {
        List<LoaiPhong> list_PhongBySoKhachToiDa = new ArrayList<>();
        for (LoaiPhong lp : list_LoaiPhong) {
            if (lp.getSoKhachToiDa() == sokhachtoida) {
                list_PhongBySoKhachToiDa.add(lp);
            }
        }
        return list_PhongBySoKhachToiDa;
    }

    public List<LoaiPhong> getLoaiPhongByDonGia(List<LoaiPhong> list_LoaiPhong, int donGia) {
        List<LoaiPhong> list_PhongByDonGia = new ArrayList<>();
        for (LoaiPhong lp : list_LoaiPhong) {
            if (lp.getDonGia() == donGia) {
                list_PhongByDonGia.add(lp);
            }
        }
        return list_PhongByDonGia;
    }

    public List<LoaiPhong> getLoaiPhongByTienNghi(List<LoaiPhong> list_LoaiPhong, List<TienNghi> tiennghi) {
        // Chuyển danh sách tiện nghi thành danh sách tên tiện nghi
        List<String> tienNghiTim = convertToTenTienNghiList(tiennghi);

        List<LoaiPhong> list_PhongByTienNghi = new ArrayList<>();
        for (LoaiPhong lp : list_LoaiPhong) {
            List<String> tenTienNghisPhong = convertToTenTienNghiList(lp.getTienNghis());

            // Kiểm tra xem tất cả tiện nghi trong tienNghiTim có nằm trong tenTienNghisPhong không
            if (tenTienNghisPhong.containsAll(tienNghiTim)) {
                list_PhongByTienNghi.add(lp);
            }
        }
        return list_PhongByTienNghi;
    }

    public List<String> convertToTenTienNghiList(List<TienNghi> tienNghis) {
        List<String> tenTienNghis = new ArrayList<>();
        for (TienNghi tienNghi : tienNghis) {
            tenTienNghis.add(tienNghi.getTenTienNghi());
        }
        return tenTienNghis;
    }

    public List<Integer> getListLoaiPhong() {
        List<Integer> tam = new ArrayList<>();
        for (LoaiPhong lp : loaiPhong_dao.getAllLoaiPhongSort()) {
            tam.add(lp.getMaLoaiPhong());
        }
        return tam;
    }

    public int taoMaTuDong(List<Integer> mangCoSan) {
        Collections.sort(mangCoSan);

        if (mangCoSan.size() == 0) {
            return 1;
        }

        for (int i = 1; i <= mangCoSan.getLast(); i++) {
            if (!mangCoSan.contains(i)) {
                return i;
            }
        }

        return mangCoSan.getLast() + 1;

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Backgroup;
    private javax.swing.JPanel Panel_TienNghi;
    private javax.swing.JPanel ThongTinLoaiPhong;
    private keeptoo.KGradientPanel btn_Lammoi;
    private keeptoo.KGradientPanel btn_Sua;
    private keeptoo.KGradientPanel btn_Them;
    private keeptoo.KGradientPanel btn_Tim;
    private keeptoo.KGradientPanel btn_Xoa;
    private javax.swing.JComboBox<String> cb_Loaigiuong;
    private GUI.CirclePanel_Atatar circlePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_Avatar;
    private javax.swing.JTable table_LoaiPhong;
    private javax.swing.JTextField txt_Dientich;
    private javax.swing.JTextField txt_Dongia;
    private javax.swing.JTextField txt_Sokhachtoida;
    private javax.swing.JTextField txt_TenLoaiphong;
    // End of variables declaration//GEN-END:variables
}
