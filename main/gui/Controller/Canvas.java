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

// This class represents the main displayer in the middle of the program
// This class utilize Java's Graphics be the canvas to draw grid of colours
// represent the `main.algo.Image` object
public class Canvas extends JPanel {
    // ==== constants
    // Length of a tile in the color grid
    public static final int TILE_EDGE = 50;

    // Offset of a tile in the color grid
    public static final int TILE_OFFSET = 2;
    
    // ==== Constructor
    // A ref object `main.gui.Frame` and a constructor takes `main.gui.Frame` as arg
    Frame frame = null;
    public Canvas(Frame source) {
        this.frame = source;
    }

    // ==== overriden `paintComponent` method
    @Override
    public void paintComponent(Graphics g) {
        // Reset the canvas by paint it all black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Check whether the ref `main.gui.Frame` object is set
        if (frame.img == null) {
            // -- If no (when just start-up), it shall draw a default image, here it draws `img/Cover.jpg`
            // Get `java.awt.Image` object
            ImageIcon icon = new ImageIcon(getClass().getResource("../../img/Cover.jpg"));
            Image img = icon.getImage();

            // Do some math to scale that image
            int edge = Math.min(this.getHeight(), this.getWidth());
            int startW = (this.getWidth() - edge)/2;
            int startH = (this.getHeight() - edge)/2;

            // Draw that image to this canvas
            g.drawImage(img, startW, startH, edge, edge, null);

        } else {
            // -- If yes (a valid test has been run), it tries to draw the colors grid represent that input
            // Calculate the actual size of the buffered image
            int buffImgW = frame.img.getWidth() * TILE_EDGE; 
            int buffImgH = frame.img.getHeight() * TILE_EDGE;

            // Get a `Graphics` object to draw on
            BufferedImage buffImg = new BufferedImage(buffImgW, buffImgH, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics buffImgGraphics = buffImg.getGraphics();
            
            // Get color mapping via `main.algo.drawing.IntegerRangeToColorRange`
            HashMap<Integer, Color> colorMapping = IntegerRangeToColorRange.getColorMapping(frame.img.getPixels());

            // Calculate some Offset so the image looks nice
            int tileEdgeAfterOffset = TILE_EDGE - TILE_OFFSET*2;

            // Clear the `Graphics` object by painting it black 
            buffImgGraphics.setColor(Color.BLACK);
            buffImgGraphics.fillRect(0, 0, buffImgW, buffImgH);

            // Tried to draw the grid, the color and its depth on the `Graphics` object
            for (int ih = 0; ih < frame.img.getHeight(); ih++) {
                for (int iw = 0; iw < frame.img.getWidth(); iw++) {
                    int index = frame.img.pairToImageIndex(ih, iw);
                    
                    // -- draw Color tile
                    buffImgGraphics.setColor(colorMapping.get(frame.img.getPixels()[index]));
                    int startX = iw * TILE_EDGE + TILE_OFFSET;
                    int startY = ih * TILE_EDGE + TILE_OFFSET;
                    buffImgGraphics.fillRect(startX, startY, tileEdgeAfterOffset, tileEdgeAfterOffset); 
   
                    // -- Draw text
                    buffImgGraphics.setColor(Color.BLACK);
                    Font usedFont = buffImgGraphics.getFont();
                    Font newFont  = new Font(usedFont.getFamily(), Font.PLAIN, 24);
                    buffImgGraphics.setFont(newFont);

                    int midX = startX + tileEdgeAfterOffset/2;
                    int midY = startY + tileEdgeAfterOffset/2;                    

                    String text = "" + frame.img.getDepthOf()[index];
                    FontMetrics metrics = buffImgGraphics.getFontMetrics();
                    int stringWidth = metrics.stringWidth(text);
                    int fontAscent = metrics.getAscent();
                    int fontDescent = metrics.getDescent();

                    buffImgGraphics.drawString(text,
                        midX - stringWidth/2,
                        midY + (fontAscent - fontDescent)/2
                    );
                }
            }

            // Do some math to scale BufferredImage on to canvas
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

            // Finally draw it on the canvas
            g.drawImage(buffImg, startX, startY, drawW, drawH, null);
        }
    }
}