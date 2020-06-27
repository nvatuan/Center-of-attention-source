package main.gui.Controller;

import javax.swing.JTextArea;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Graphics; 
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Canvas extends JPanel {
    // -- constants
    public static final int TILE_EDGE = 50;
    // --
    Frame frame = null;
    public Canvas(Frame source) {
        this.frame = source;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        if (frame.img == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("../../img/Winter.jpg"));
            Image img = icon.getImage();

            int edge = Math.min(this.getHeight(), this.getWidth());
            int startW = (this.getWidth() - edge)/2;
            int startH = (this.getHeight() - edge)/2;
            g.drawImage(img, startW, startH, edge, edge, null);

            // --
            // String text = textInput.getText();
            // int midW = this.getWidth()/2;
            // int midH = this.getHeight()/2;

            // FontMetrics metrics = g.getFontMetrics(textInput.getFont());
            // midW -= metrics.stringWidth(text)/2;
            // midH -= metrics.getHeight()/2; midH += + metrics.getAscent();
            // g.setFont(textInput.getFont());
            // g.drawString(text, midW, midH);
        } else {
            int buffImgW = frame.img.getWidth() * TILE_EDGE; 
            int buffImgH = frame.img.getHeight() * TILE_EDGE;
            BufferedImage buffImg = new BufferedImage(buffImgW, buffImgH, BufferedImage.TYPE_4BYTE_ABGR);
        }
    }
}