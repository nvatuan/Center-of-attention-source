package main.gui.Controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics; 
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Canvas extends JPanel {
    public Canvas() {
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        //ImageIcon icon = new ImageIcon("D:\\Data\\DAECEQMVYAAxVu4.jpg");
        ImageIcon icon = new ImageIcon(getClass().getResource("../../img/Winter.jpg"));
        Image img = icon.getImage();

        int edge = Math.min(this.getHeight(), this.getWidth());
        int startW = (this.getWidth() - edge)/2;
        int startH = (this.getHeight() - edge)/2;
        g.drawImage(img, startW, startH, edge, edge, null);
    }
}