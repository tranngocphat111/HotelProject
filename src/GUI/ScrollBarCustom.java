package GUI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new Vertical_ScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
    }
}
