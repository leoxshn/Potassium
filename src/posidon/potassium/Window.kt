package posidon.potassium;

import posidon.potassium.ui.ScrollBar;
import posidon.potassium.ui.color;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Window {

    private static JFrame window = new JFrame();;
    private static JTextPane console;
    private static JTextField input;
    private static JScrollPane scroll;
    private static StyledDocument document;
    private static Commands commands = new Commands();

    public static void create() {
        Font monospace = new Font(Font.MONOSPACED, Font.PLAIN, 15);
        window.setTitle("Potassium");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try { window.setIconImage(ImageIO.read(Window.class.getResource("/ic.png"))); }
        catch (IOException e) { e.printStackTrace(); }

        console = new JTextPane();
        console.setEditable(false);
        console.setFont(monospace);
        console.setOpaque(false);
        console.setForeground(new Color(color.WHITE));
        console.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        document = console.getStyledDocument();

        input = new JTextField();
        input.setEditable(true);
        input.setFont(monospace);
        input.setBorder(null);
        input.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        input.setCaretColor(new Color(color.CARET));
        input.setForeground(new Color(color.INPUT_TEXT_COLOR));
        input.setBackground(new Color(color.INPUT_BG));
        input.addActionListener(actionEvent -> {
            String[] txt = input.getText().split(" ");
            input.setText("");
            commands.onCommand(txt);
            scrollBottom();
        });
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }
            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        scroll = new JScrollPane(console);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUI(new ScrollBar());

        window.add(input, BorderLayout.SOUTH);
        window.add(scroll, BorderLayout.CENTER);
        window.setBackground(new Color(color.BG));
        window.getContentPane().setBackground(new Color(color.BG));
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setResizable(true);
        window.setVisible(true);
    }

    private void scrollTop() { console.setCaretPosition(0); }
    private static void scrollBottom() { console.setCaretPosition(console.getDocument().getLength()); }
    public static void println(String string) { print(string + "\n"); scrollBottom(); }
    public static void println(String string, int color) { print(string + "\n", color); scrollBottom(); }
    public static void print(String string) { print(string, color.WHITE); }
    public static void print(String string, int color) {
        Style style = console.addStyle("Style", null);
        StyleConstants.setForeground(style, new Color(color));
        try { document.insertString(document.getLength(), string, style); }
        catch (Exception e) { e.printStackTrace(); }
    }
    public static void clear() {
        try { document.remove(0, document.getLength()); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
