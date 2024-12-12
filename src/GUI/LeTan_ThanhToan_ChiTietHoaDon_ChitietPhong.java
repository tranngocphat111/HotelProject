/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import model.DTO.DichVuSuDungEmbed;
import model.DTO.PhongEmbed;

/**
 *
 * @author Admin
 */
public class LeTan_ThanhToan_ChiTietHoaDon_ChitietPhong extends javax.swing.JPanel {

    public static DecimalFormat df = new DecimalFormat("#,##0");

    /**
     * Creates new form LeTan_ThanhToan_ChiTietHoaDon_ChitietPhong
     *
     * @param phong
     */
    public LeTan_ThanhToan_ChiTietHoaDon_ChitietPhong(PhongEmbed phong) {
        if (phong == null) {
            throw new IllegalArgumentException("PhongEmbed không được null");
        }
        initComponents();
        TienPhong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 209, 84)));
        DichVu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(255, 209, 84)));
        chi_tiet_dich_vu.setVisible(false);
        chi_tiet_phong.setVisible(false);
        main.setSize(main.getWidth() + 500, (main.getHeight() - chi_tiet_phong.getHeight() - chi_tiet_dich_vu.getHeight()));
        List<String> songayo = splitDateRange(phong.getNgayNhanPhong(), phong.getNgayTraPhong());

        ma_phong.setText("Phòng " + String.valueOf(phong.getMaPhong()));

        phong_list.setLayout(new BoxLayout(phong_list, BoxLayout.Y_AXIS));
        dv_list.setLayout(new BoxLayout(dv_list, BoxLayout.Y_AXIS));

        ChitietPhong_Phong ct = new ChitietPhong_Phong(phong);
        ct.setVisible(true);
        phong_list.add(ct);

        for (DichVuSuDungEmbed dv : phong.getDichVuSuDung()) {
            ChitietPhong_DichVu dvsd = new ChitietPhong_DichVu(dv);
            dvsd.setVisible(true);
            dv_list.add(dvsd);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        phong_header = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ma_phong = new javax.swing.JLabel();
        xem_chi_tiet_btn = new java.awt.Button();
        chi_tiet_phong = new javax.swing.JPanel();
        TienPhong = new javax.swing.JLabel();
        phong_list = new javax.swing.JPanel();
        chi_tiet_dich_vu = new javax.swing.JPanel();
        DichVu = new javax.swing.JLabel();
        dv_list = new javax.swing.JPanel();
        line = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 209, 84));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        main.setBackground(new java.awt.Color(0, 0, 0));
        main.setName(""); // NOI18N

        phong_header.setBackground(new java.awt.Color(255, 209, 84));
        phong_header.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanel1.setBackground(new java.awt.Color(255, 209, 84));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 209, 84));

        ma_phong.setBackground(new java.awt.Color(255, 209, 84));
        ma_phong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ma_phong.setText("Standard 202");

        xem_chi_tiet_btn.setActionCommand("+");
        xem_chi_tiet_btn.setBackground(new java.awt.Color(0, 0, 0));
        xem_chi_tiet_btn.setForeground(new java.awt.Color(255, 209, 84));
        xem_chi_tiet_btn.setLabel("+");
        xem_chi_tiet_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xem_chi_tiet_btnMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xem_chi_tiet_btnMousePressed(evt);
            }
        });
        xem_chi_tiet_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xem_chi_tiet_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ma_phong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xem_chi_tiet_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(xem_chi_tiet_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ma_phong))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout phong_headerLayout = new javax.swing.GroupLayout(phong_header);
        phong_header.setLayout(phong_headerLayout);
        phong_headerLayout.setHorizontalGroup(
            phong_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phong_headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        phong_headerLayout.setVerticalGroup(
            phong_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phong_headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(phong_headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        chi_tiet_phong.setBackground(new java.awt.Color(0, 0, 0));
        chi_tiet_phong.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TienPhong.setBackground(new java.awt.Color(255, 209, 84));
        TienPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        TienPhong.setForeground(new java.awt.Color(255, 209, 84));
        TienPhong.setText("Tiền phòng");

        phong_list.setBackground(new java.awt.Color(0, 0, 0));
        phong_list.setPreferredSize(new java.awt.Dimension(678, 96));

        javax.swing.GroupLayout phong_listLayout = new javax.swing.GroupLayout(phong_list);
        phong_list.setLayout(phong_listLayout);
        phong_listLayout.setHorizontalGroup(
            phong_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 651, Short.MAX_VALUE)
        );
        phong_listLayout.setVerticalGroup(
            phong_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout chi_tiet_phongLayout = new javax.swing.GroupLayout(chi_tiet_phong);
        chi_tiet_phong.setLayout(chi_tiet_phongLayout);
        chi_tiet_phongLayout.setHorizontalGroup(
            chi_tiet_phongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chi_tiet_phongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phong_list, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        chi_tiet_phongLayout.setVerticalGroup(
            chi_tiet_phongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chi_tiet_phongLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(phong_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(chi_tiet_phongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        chi_tiet_dich_vu.setBackground(new java.awt.Color(0, 0, 0));
        chi_tiet_dich_vu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        DichVu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        DichVu.setForeground(new java.awt.Color(255, 209, 84));
        DichVu.setText("Dịch Vụ");

        dv_list.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout dv_listLayout = new javax.swing.GroupLayout(dv_list);
        dv_list.setLayout(dv_listLayout);
        dv_listLayout.setHorizontalGroup(
            dv_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );
        dv_listLayout.setVerticalGroup(
            dv_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout chi_tiet_dich_vuLayout = new javax.swing.GroupLayout(chi_tiet_dich_vu);
        chi_tiet_dich_vu.setLayout(chi_tiet_dich_vuLayout);
        chi_tiet_dich_vuLayout.setHorizontalGroup(
            chi_tiet_dich_vuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chi_tiet_dich_vuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(dv_list, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        chi_tiet_dich_vuLayout.setVerticalGroup(
            chi_tiet_dich_vuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chi_tiet_dich_vuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chi_tiet_dich_vuLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(dv_list, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        line.setBackground(new java.awt.Color(255, 209, 84));
        line.setPreferredSize(new java.awt.Dimension(782, 2));

        javax.swing.GroupLayout lineLayout = new javax.swing.GroupLayout(line);
        line.setLayout(lineLayout);
        lineLayout.setHorizontalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        lineLayout.setVerticalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(phong_header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(chi_tiet_dich_vu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(chi_tiet_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addComponent(phong_header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(chi_tiet_phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chi_tiet_dich_vu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorAdded

    private void xem_chi_tiet_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xem_chi_tiet_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_xem_chi_tiet_btnActionPerformed

    private void xem_chi_tiet_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xem_chi_tiet_btnMouseClicked

    }//GEN-LAST:event_xem_chi_tiet_btnMouseClicked

    private void xem_chi_tiet_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xem_chi_tiet_btnMousePressed
        // TODO add your handling code here:
        if (!chi_tiet_dich_vu.isVisible() && !chi_tiet_phong.isVisible()) {
            main.setSize(main.getWidth() + 500, (main.getHeight() + chi_tiet_phong.getHeight() + chi_tiet_dich_vu.getHeight()));
            xem_chi_tiet_btn.setLabel("-");
            chi_tiet_dich_vu.setVisible(true);
            chi_tiet_phong.setVisible(true);

        } else {
            xem_chi_tiet_btn.setLabel("+");
            chi_tiet_dich_vu.setVisible(false);
            chi_tiet_phong.setVisible(false);
            main.setSize(main.getWidth() + 500, (main.getHeight() - chi_tiet_phong.getHeight() - chi_tiet_dich_vu.getHeight()));
        }
    }//GEN-LAST:event_xem_chi_tiet_btnMousePressed
    private void adjustMainPanelSize() {
        revalidate();
        repaint();
    }

    public List<String> splitDateRange(Date startDate, Date endDate) {
        List<String> ranges = new ArrayList<>();

        // Kiểm tra nếu ngày bắt đầu và ngày kết thúc hợp lệ
        if (startDate.after(endDate)) {
            return ranges;
        }

        // Tạo Calendar và thiết lập thời gian cho ngày bắt đầu
        Calendar currentStart = Calendar.getInstance();
        currentStart.setTime(startDate);

        // Tạo Calendar cho ngày kết thúc
        Calendar currentEnd = Calendar.getInstance();
        currentEnd.setTime(endDate);

        // Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Tách khoảng thành các khoảng 1 ngày
        while (currentStart.before(currentEnd)) {
            // Tạo một bản sao của currentStart để làm currentEnd
            Calendar tempEnd = (Calendar) currentStart.clone();
            tempEnd.add(Calendar.DAY_OF_MONTH, 1); // Tăng ngày kết thúc lên 1

            // Thêm khoảng thời gian vào danh sách với định dạng
            ranges.add(dateFormat.format(currentStart.getTime()) + " đến " + dateFormat.format(tempEnd.getTime()));

            // Tăng currentStart lên 1 ngày
            currentStart.add(Calendar.DAY_OF_MONTH, 1);
        }
        return ranges;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DichVu;
    private javax.swing.JLabel TienPhong;
    private javax.swing.JPanel chi_tiet_dich_vu;
    private javax.swing.JPanel chi_tiet_phong;
    private javax.swing.JPanel dv_list;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel line;
    private javax.swing.JLabel ma_phong;
    private javax.swing.JPanel main;
    private javax.swing.JPanel phong_header;
    private javax.swing.JPanel phong_list;
    private java.awt.Button xem_chi_tiet_btn;
    // End of variables declaration//GEN-END:variables
}