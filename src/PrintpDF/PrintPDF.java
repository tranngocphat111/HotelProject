package PrintpDF;

import static GUI.DangNhap_GUI.database;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import model.DTO.DonDatPhong;
import model.DTO.HoaDon;
import java.util.List;
import java.util.Set;
import model.DAO.DonDatPhongDAO;
import model.DAO.LoaiPhongDAO;
import model.DAO.PhongDAO;
import model.DTO.DichVu;
import model.DTO.LoaiPhong;

public final class PrintPDF {

    public static InputStream imageStream = PrintPDF.class.getResourceAsStream("/images/icon_moonhotel.png");
    public static InputStream QRcode = PrintPDF.class.getResourceAsStream("/images/qrcode.png");
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat df = new DecimalFormat("#,##0");
    private List<DonDatPhong> list_donDatPhong = new ArrayList<DonDatPhong>();
    private DonDatPhongDAO donDatPhong_dao = new DonDatPhongDAO(database);
    private PhongDAO phong_dao = new PhongDAO(database);
    private LoaiPhongDAO loaiPhong_dao = new LoaiPhongDAO(database);

    public PrintPDF(HoaDon hoaDon) {
        list_donDatPhong = donDatPhong_dao.getAllDonDatPhong();
        try {
            String dest = "hoaDon.pdf";
            PageSize customPageSize = new PageSize(PageSize.A4.getWidth(), 1400);

// Khởi tạo tài liệu với kích thước mới
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, customPageSize);
            document.setMargins(20, 20, 20, 20);

            // Font setup with DejaVu Sans
            PdfFont boldFont = PdfFontFactory.createFont("/Font/dejavu-sans/DejaVuSans-Bold.ttf", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
            PdfFont regularFont = PdfFontFactory.createFont("/Font/dejavu-sans/DejaVuSans.ttf", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
            
            // Logo
            ImageData imageData = ImageDataFactory.create(imageStream.readAllBytes());
            Image logo = new Image(imageData).setHorizontalAlignment(HorizontalAlignment.LEFT).setHeight(40);

            Table logoTable = new Table(UnitValue.createPercentArray(new float[]{1, 10})); // 1 cột cho logo, 4 cột cho tên
            logoTable.setWidth(UnitValue.createPercentValue(100)); // Chiếm toàn bộ chiều rộng

// Thêm logo vào cột đầu tiên
            logoTable.addCell(new Cell().add(logo).setBorder(Border.NO_BORDER));

// Thêm tên khách sạn vào cột thứ hai với khoảng cách trái
            Paragraph hotelName = new Paragraph("MOON HOTEL")
                    .setFont(boldFont)
                    .setFontSize(20)
                    .setMarginTop(10); // Dịch chữ lên trên 10px

// Điều chỉnh khoảng cách giữa logo và tên
            Cell nameCell = new Cell()
                    .add(hotelName)
                    .setBorder(Border.NO_BORDER)
                    .setVerticalAlignment(VerticalAlignment.BOTTOM)
                    .setPaddingLeft(0) // Giảm khoảng cách trái
                    .setPaddingRight(0); // Giảm khoảng cách phải; // Thay đổi giá trị này để điều chỉnh khoảng cách

            logoTable.addCell(nameCell);

// Thêm Table vào tài liệu
            document.add(logoTable);

            document.add(new Paragraph("\n\n")); // Thêm khoảng trắng
            // Title
            document.add(new Paragraph("HÓA ĐƠN").setFont(boldFont).setFontSize(18).setTextAlignment(TextAlignment.CENTER));

            // Invoice information
            document.add(new Paragraph("Mã hóa đơn: HD00" + hoaDon.getMaHoaDon() + " \n" + sdf.format(hoaDon.getNgayTaoHoaDon())).setFont(regularFont).setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
            // Room Information Table
            // Room Information Table
            document.add(new Paragraph("\n\n")); // Thêm khoảng trắng

            StringBuilder roomInfo = new StringBuilder();
            roomInfo.append(String.format("%-12s %-15s %25s %22s %27s\n", "Phòng", "Loại phòng", "Số ngày sử dụng", "Đơn giá", "Thành tiền")); // Tăng khoảng cách giữa các cột
            roomInfo.append("----------------------------------------------------------------------------------------------------------------------------\n");
            document.add(new Paragraph(roomInfo.toString())
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMultipliedLeading(1.5f) // Giãn cách dòng
                    .setWidth(UnitValue.createPercentValue(97))); // Đảm bảo chiếm toàn bộ chiều ngang

            Table invoiceTable = new Table(UnitValue.createPercentArray(new float[]{1, 2, 1, 2, 2})); // Tỷ lệ các cột
            invoiceTable.setWidth(UnitValue.createPercentValue(97)); // Đặt chiều rộng bảng

            System.out.println(getAllDonDatPhongTheoHoaDon(hoaDon).size());
            for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
                LoaiPhong loaiPhong = loaiPhong_dao.getLoaiPhongByMa(phong_dao.getPhongByMa(ddp.getPhong()).getLoaiPhong());
                invoiceTable.addCell(new Cell().add(new Paragraph(ddp.getPhong() + "").setFont(regularFont)).setBorder(Border.NO_BORDER));
                invoiceTable.addCell(new Cell().add(new Paragraph(loaiPhong.getTenLoaiPhong()).setFont(regularFont)).setBorder(Border.NO_BORDER));
                invoiceTable.addCell(new Cell().add(new Paragraph(getSoluongNgaySuDung(ddp.getNgayNhanPhong(), ddp.getNgayTraPhong()) + "").setFont(regularFont)).setBorder(Border.NO_BORDER));
                invoiceTable.addCell(new Cell().add(new Paragraph(df.format(loaiPhong.getDonGia()) + " VND").setFont(regularFont)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                invoiceTable.addCell(new Cell().add(new Paragraph(df.format(loaiPhong.getDonGia() * getSoluongNgaySuDung(ddp.getNgayNhanPhong(), ddp.getNgayDatPhong())) + " VND").setFont(regularFont)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                document.add(invoiceTable);
            }

            document.add(new Paragraph("\n\n")); // Thêm khoảng trắng

            System.out.println(getAllDichVutheoHoaDon(hoaDon).size());
            if (getAllDichVutheoHoaDon(hoaDon).size() != 0) {
                StringBuilder serviceInfo = new StringBuilder();
                serviceInfo.append(String.format("%-25s %20s %37s %27s\n", "Dịch vụ sử dụng", "Số lượng", "Đơn giá", "Thành tiền")); // Tăng khoảng cách giữa các cột
                serviceInfo.append("-----------------------------------------------------------------------------------------------------------------------------\n");
                document.add(new Paragraph(serviceInfo.toString())
                        .setFont(regularFont)
                        .setFontSize(12)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setMultipliedLeading(1.5f) // Giãn cách dòng
                        .setWidth(UnitValue.createPercentValue(100))); // Đảm bảo chiếm toàn bộ chiều ngang
                Table serviceTable = new Table(UnitValue.createPercentArray(new float[]{3, 1, 2, 2})); // Tỷ lệ các cột
                serviceTable.setWidth(UnitValue.createPercentValue(97)); // Đặt chiều rộng bảng
                
                for (DichVu dv : getAllDichVutheoHoaDon(hoaDon)) {
                    System.out.println(dv);
                    serviceTable.addCell(new Cell().add(new Paragraph(dv.getTenDV()).setFont(regularFont)).setBorder(Border.NO_BORDER));
                    serviceTable.addCell(new Cell().add(new Paragraph(DemSoDichVuSuDung(dv, hoaDon) + "").setFont(regularFont)).setBorder(Border.NO_BORDER));
                    serviceTable.addCell(new Cell().add(new Paragraph(df.format(dv.getDonGia()) + " VND").setFont(regularFont)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                    serviceTable.addCell(new Cell().add(new Paragraph(df.format(dv.getDonGia() * DemSoDichVuSuDung(dv, hoaDon)) + " VND").setFont(regularFont)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
                    document.add(serviceTable);
                }

            }
// Thêm thông tin dịch vụ mà không có đường viền

// Thêm đoạn văn bản vào tài liệu
// Tổng tiền
            Table totalTable = new Table(UnitValue.createPercentArray(new float[]{1, 1})); // Tạo bảng với 2 cột có độ rộng bằng nhau
            totalTable.setWidth(UnitValue.createPercentValue(97)); // Đảm bảo chiếm toàn bộ chiều ngang

// Thêm ô cho "Tổng tiền" (cột trái)
            document.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------------------------\n"));

            totalTable.addCell(new Cell().add(new Paragraph("Tổng tiền:").setFont(boldFont).setFontSize(14)).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));

// Thêm ô cho số tiền (cột phải)
            totalTable.addCell(new Cell().add(new Paragraph(df.format(hoaDon.getTongTien()) + " VND").setFont(boldFont).setFontSize(14)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));

// Thêm bảng vào tài liệu
            document.add(totalTable);

            new VietQRGen(hoaDon);
            document.add(new Paragraph("\n")); // Thêm khoảng trắng
            ImageData imageQR = ImageDataFactory.create(QRcode.readAllBytes());
            Image QRcode = new Image(imageQR).setHorizontalAlignment(HorizontalAlignment.CENTER).setHeight(350);
            document.add(QRcode);

            
            // Close the document
            document.close();
            System.out.println("PDF Created: " + dest);
            Desktop.getDesktop().open(new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DonDatPhong> getAllDonDatPhongTheoHoaDon(HoaDon hoaDon) {
        java.util.List<DonDatPhong> list_DDP = new ArrayList<>();
        for (DonDatPhong ddp : list_donDatPhong) {
            if (ddp.getHoaDon() == hoaDon.getMaHoaDon()) {
                list_DDP.add(ddp);
            }
        }
        return list_DDP;
    }

    public Set<DichVu> getAllDichVutheoHoaDon(HoaDon hoaDon) {
        Set<DichVu> list_dichvu = new HashSet<>();
        for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
            for (DichVu dv : ddp.getDichVuSuDung()) {
                if (!list_dichvu.contains(dv)) {
                    list_dichvu.add(dv);
                }
            }
        }
        return list_dichvu;
    }

    public int DemSoDichVuSuDung(DichVu dv, HoaDon hoaDon) {
        int dem = 0;
        for (DonDatPhong ddp : getAllDonDatPhongTheoHoaDon(hoaDon)) {
            for (DichVu dichvu : ddp.getDichVuSuDung()) {
                if (dv.getMaDV() == dichvu.getMaDV()) {
                    dem++;
                }
            }
        }

        return dem;
    }

    public int getSoluongNgaySuDung(Date ngayTra, Date ngayNhan) {
        long diffInMillies = ngayTra.getTime() - ngayNhan.getTime();
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24); // chuyển từ milliseconds sang ngày

        return (int) diffInDays + 1;
    }

    public static void main(String[] args) throws IOException {

    }
}
