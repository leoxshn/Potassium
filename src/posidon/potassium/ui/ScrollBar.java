package posidon.potassium.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ScrollBar extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        LookAndFeel.installColors(this.scrollbar, "ScrollBar.background", "ScrollBar.foreground");
        this.thumbColor = new Color(color.SCROLL_FG);
        this.trackColor = new Color(color.SCROLL_BG);
    }

    protected void installComponents() {
        this.incrButton = new BasicArrowButton(0, this.trackColor, this.trackColor, this.trackColor, this.trackColor);
        this.decrButton = new BasicArrowButton(0, this.trackColor, this.trackColor, this.trackColor, this.trackColor);
        this.scrollbar.add(this.incrButton);
        this.scrollbar.add(this.decrButton);
        this.scrollbar.setEnabled(this.scrollbar.isEnabled());
    }

    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (!thumbBounds.isEmpty() && this.scrollbar.isEnabled()) {
            g.translate(thumbBounds.x, thumbBounds.y);
            g.setColor(this.thumbColor);
            g.fillRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1);
        }
    }
}
