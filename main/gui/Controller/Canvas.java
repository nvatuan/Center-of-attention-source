package main.gui.Controller;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Graphics; 
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;

import main.algo.drawing.IntegerRangeToColorRange;

public class Canvas extends JPanel {
    // -- constants
    public static final int TILE_EDGE = 50;
    public static final int TILE_OFFSET = 2;
    // --
    Frame frame = null;
    public Canvas(Frame source) {
        this.frame = source;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (frame.img == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("../../img/Cover.jpg"));
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
            Graphics buffImgGraphics = buffImg.getGraphics();
            //
            HashMap<Integer, Color> colorMapping = IntegerRangeToColorRange.getColorMapping(frame.img.getPixels());

            int tileEdgeAfterOffset = TILE_EDGE - TILE_OFFSET*2;

            buffImgGraphics.clearRect(0, 0, buffImgW, buffImgH);
            buffImgGraphics.setColor(Color.BLACK);
            buffImgGraphics.fillRect(0, 0, buffImgW, buffImgH);
            for (int ih = 0; ih < frame.img.getHeight(); ih++) {
                for (int iw = 0; iw < frame.img.getWidth(); iw++) {
                    int index = frame.img.pairToImageIndex(ih, iw);
                    
                    // -- draw Color tile
                    buffImgGraphics.setColor(colorMapping.get(frame.img.getPixels()[index]));
                    int startX = iw * TILE_EDGE + TILE_OFFSET;
                    int startY = ih * TILE_EDGE + TILE_OFFSET;
                    buffImgGraphics.fillRect(startX, startY, tileEdgeAfterOffset, tileEdgeAfterOffset); 
                    buffImgGraphics.setColor(Color.BLACK);
                    
                    Font usedFont = buffImgGraphics.getFont();
                    Font newFont  = new Font(usedFont.getFamily(), Font.PLAIN, 24);
                    buffImgGraphics.setFont(newFont);
   
                    // -- Draw text
                    int midX = startX + tileEdgeAfterOffset/2;
                    int midY = startY + tileEdgeAfterOffset/2;                    
                    String text = "" + frame.img.getDepthOf()[index];
                    FontMetrics metrics = buffImgGraphics.getFontMetrics();
                    int stringWidth = metrics.stringWidth(text);
                    //int fontHeight = metrics.getHeight(); 
                    int fontAscent = metrics.getAscent();
                    int fontDescent = metrics.getDescent();

                    buffImgGraphics.drawString(text,
                        midX - stringWidth/2,
                        midY + (fontAscent - fontDescent)/2
                    );
                }
            }
            //
            int drawW, drawH;
            if (buffImgW*this.getHeight() > this.getWidth()*buffImgH) {
                drawW = this.getWidth();
                drawH = ((int) ((float) buffImgH * this.getWidth() / buffImgW));
            } else {
                drawH = this.getHeight();
                drawW = ((int) ((float) buffImgW * this.getHeight() / buffImgH));
            }
            int startX, startY;
            startX = (this.getWidth() - drawW)/2;
            startY = (this.getHeight() - drawH)/2;
            g.drawImage(buffImg, startX, startY, drawW, drawH, null);
        }
    }
}