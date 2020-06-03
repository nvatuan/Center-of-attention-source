package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class My_Image {
     static private ImageIcon getScaledImage(Image srcImg, int w, int h){
          BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g2 = resizedImg.createGraphics();

          g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
          g2.drawImage(srcImg, 0, 0, w, h, null);
          g2.dispose();

          ImageIcon ii = new ImageIcon();
          ii.setImage(resizedImg);
          return ii;
     }
     static ImageIcon img_button_start_orig = new ImageIcon("D:\\18TCLC_NHAT\\nam_2\\do_an\\code2\\image");
     static ImageIcon img_button_start = getScaledImage(img_button_start_orig.getImage(), 100, 100);
}
