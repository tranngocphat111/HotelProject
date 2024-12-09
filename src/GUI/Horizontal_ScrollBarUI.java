package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Horizontal_ScrollBarUI extends JScrollBar {

    public Horizontal_ScrollBarUI() {
        super(JScrollBar.HORIZONTAL); // Đặt mặc định là thanh cuộn ngang
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(0, 0, 0));
        setBackground(new Color(240, 240, 240));
    }

    // Lớp UI tùy chỉnh
    private static class ModernScrollBarUI extends BasicScrollBarUI {
        private final int THUMB_SIZE = 80;

        @Override
        protected Dimension getMaximumThumbSize() {
            return new Dimension(THUMB_SIZE, 0); // Kích thước tối đa cho thanh ngang
        }

        @Override
        protected Dimension getMinimumThumbSize() {
            return new Dimension(THUMB_SIZE, 0); // Kích thước tối thiểu cho thanh ngang
        }

        @Override
        protected JButton createIncreaseButton(int i) {
            return new ScrollBarButton();
        }

        @Override
        protected JButton createDecreaseButton(int i) {
            return new ScrollBarButton();
        }

        @Override
        protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int size = rctngl.height / 2;
            int y = rctngl.y + ((rctngl.height - size) / 2);
            g2.setColor(new Color(240, 240, 240));
            g2.fillRect(rctngl.x, y, rctngl.width, size);
        }

        @Override
        protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int x = rctngl.x + 8;
            int width = rctngl.width - 16;
            g2.setColor(scrollbar.getForeground());
            g2.fillRoundRect(x, rctngl.y, width, rctngl.height, 10, 10);
        }

        private static class ScrollBarButton extends JButton {
            public ScrollBarButton() {
                setBorder(BorderFactory.createEmptyBorder());
            }

            @Override
            public void paint(Graphics grphcs) {
                // Không vẽ nút
            }
        }
    }
}
