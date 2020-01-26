package posidon.potassium.ui

import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.JComponent
import javax.swing.LookAndFeel
import javax.swing.plaf.basic.BasicArrowButton
import javax.swing.plaf.basic.BasicScrollBarUI

class ScrollBar : BasicScrollBarUI() {
    override fun configureScrollBarColors() {
        LookAndFeel.installColors(scrollbar, "ScrollBar.background", "ScrollBar.foreground")
        thumbColor = java.awt.Color(Color.SCROLL_FG)
        trackColor = java.awt.Color(Color.SCROLL_BG)
    }

    override fun installComponents() {
        incrButton = BasicArrowButton(0, trackColor, trackColor, trackColor, trackColor)
        decrButton = BasicArrowButton(0, trackColor, trackColor, trackColor, trackColor)
        scrollbar.add(incrButton)
        scrollbar.add(decrButton)
        scrollbar.isEnabled = scrollbar.isEnabled
    }

    override fun paintThumb(g: Graphics, c: JComponent, thumbBounds: Rectangle) {
        if (!thumbBounds.isEmpty && scrollbar.isEnabled) {
            g.translate(thumbBounds.x, thumbBounds.y)
            g.color = thumbColor
            g.fillRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1)
        }
    }
}