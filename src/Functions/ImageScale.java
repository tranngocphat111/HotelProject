package Functions;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageScale {

    public ImageIcon getScaledImage(int width, int height, String path) {
        ImageIcon myImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(path)));
        Image img1 = myImage.getImage();

        Image img2 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        return i;
    }

    public ImageIcon getScaledImage1(int width, int height, ImageIcon myImage) {
        Image img1 = myImage.getImage();

        Image img2 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        return i;
    }

    // can chinh image
    public ImageIcon load(String linkImage, int k, int m) {/*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/
        try {
            BufferedImage image = ImageIO.read(new File(linkImage));//đọc ảnh dùng BufferedImage

            int x = k;
            int y = m;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0, dy = 0;

            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            return new ImageIcon(image.getScaledInstance(dx, dy,
                    image.SCALE_SMOOTH));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }
//    public ImageIcon load1(ImageIcon image, int k, int m) { /*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/ int x = k;
//    int y = m;
//    int ix = image.getIconWidth();
//    int iy = image.getIconHeight();
//    int dx = 0, dy = 0;
//    if (x / y > ix / iy) {
//        dy = y;
//        dx = dy * ix / iy;
//    } else {
//        dx = x;
//        dy = dx * iy / ix;
//    }
//    return new ImageIcon(image.getImage().getScaledInstance(dx, dy,Image.SCALE_SMOOTH));
//    }

    public ImageIcon load1(ImageIcon image, int k, int m) {
        // Kiểm tra xem image có phải là null không
        if (image == null || k <= 0 || m <= 0) {
            return null; // Hoặc trả về một icon mặc định nếu cần
        }

        int x = k;
        int y = m;
        int ix = image.getIconWidth();
        int iy = image.getIconHeight();

        // Kiểm tra kích thước của ảnh gốc
        if (ix == 0 || iy == 0) {
            return null; // Hoặc trả về một icon mặc định nếu cần
        }

        int dx = 0, dy = 0;

        // Tính toán kích thước mới dựa trên tỉ lệ
        if (x / (double) y > ix / (double) iy) {
            dy = y;
            dx = dy * ix / iy;
        } else {
            dx = x;
            dy = dx * iy / ix;
        }

        // Đảm bảo kích thước không âm
        if (dx < 0 || dy < 0) {
            return null; // Hoặc trả về một icon mặc định nếu cần
        }

        // Trả về ImageIcon đã được thay đổi kích thước
        return new ImageIcon(image.getImage().getScaledInstance(dx, dy, Image.SCALE_SMOOTH));
    }

        public static void setCircularImage(JLabel label, ImageIcon imageIcon) {
        // Lấy ảnh từ ImageIcon
        Image image = imageIcon.getImage();
        int diameter = Math.min(image.getWidth(null), image.getHeight(null));

        // Tạo BufferedImage mới với nền trong suốt và hình tròn
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circularImage.createGraphics();

        // Bật khử răng cưa
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cắt hình tròn
        g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, diameter, diameter));
        g2d.drawImage(image, 0, 0, diameter, diameter, null);
        g2d.dispose();

        // Chuyển BufferedImage thành ImageIcon
        ImageIcon circularIcon = new ImageIcon(circularImage);

        // Áp dụng icon hình tròn lên JLabel
        label.setIcon(circularIcon);
    }
}
