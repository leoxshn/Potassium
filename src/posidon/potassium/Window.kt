package posidon.potassium

import posidon.potassium.ui.ScrollBar
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.text.StyleConstants
import javax.swing.text.StyledDocument

class Window {
    private fun scrollTop() {
        console!!.caretPosition = 0
    }

    companion object {
        private val window = JFrame()
        private var console: JTextPane? = null
        private var input: JTextField? = null
        private var scroll: JScrollPane? = null
        private var document: StyledDocument? = null
        private val commands = Commands()
        
        fun create() {
            val monospace = Font(Font.MONOSPACED, Font.PLAIN, 15)
            window.title = "Potassium"
            window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            try {
                window.iconImage = ImageIO.read(Window::class.java.getResource("/ic.png"))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            console = JTextPane()
            console!!.isEditable = false
            console!!.font = monospace
            console!!.isOpaque = false
            console!!.foreground = Color(posidon.potassium.ui.Color.WHITE)
            console!!.border = BorderFactory.createEmptyBorder(12, 12, 12, 12)
            document = console!!.styledDocument
            input = JTextField()
            input!!.isEditable = true
            input!!.font = monospace
            input!!.border = null
            input!!.border = BorderFactory.createEmptyBorder(12, 12, 12, 12)
            input!!.caretColor = Color(posidon.potassium.ui.Color.CARET)
            input!!.foreground = Color(posidon.potassium.ui.Color.INPUT_TEXT_COLOR)
            input!!.background = Color(posidon.potassium.ui.Color.INPUT_BG)
            input!!.addActionListener { actionEvent: ActionEvent? ->
                val txt = input!!.text.split(" ".toRegex()).toTypedArray()
                input!!.text = ""
                commands.onCommand(txt)
                scrollBottom()
            }
            input!!.addKeyListener(object : KeyListener {
                override fun keyTyped(keyEvent: KeyEvent) {}
                override fun keyPressed(keyEvent: KeyEvent) {}
                override fun keyReleased(keyEvent: KeyEvent) {}
            })
            scroll = JScrollPane(console)
            scroll!!.border = null
            scroll!!.isOpaque = false
            scroll!!.viewport.isOpaque = false
            scroll!!.verticalScrollBar.setUI(ScrollBar())
            window.add(input, BorderLayout.SOUTH)
            window.add(scroll, BorderLayout.CENTER)
            window.background = Color(posidon.potassium.ui.Color.BG)
            window.contentPane.background = Color(posidon.potassium.ui.Color.BG)
            window.setSize(800, 600)
            window.setLocationRelativeTo(null)
            window.isResizable = true
            window.isVisible = true
        }

        private fun scrollBottom() {
            console!!.caretPosition = console!!.document.length
        }

        
        fun println(string: String) {
            print(string + "\n")
            scrollBottom()
        }

        
        fun println(string: String, color: Int) {
            print(string + "\n", color)
            scrollBottom()
        }

        
        @JvmOverloads
        fun print(string: String?, color: Int = posidon.potassium.ui.Color.WHITE) {
            val style = console!!.addStyle("Style", null)
            StyleConstants.setForeground(style, Color(color))
            try {
                document!!.insertString(document!!.length, string, style)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun clear() {
            try {
                document!!.remove(0, document!!.length)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}