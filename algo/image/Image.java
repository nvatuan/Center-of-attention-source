package algo;

import algo.PairRowColumn;
import java.util.Arrays;

public class Image {
    int[] pixels;
    int width, height;
    
    // -- getters
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public int[] getPixels() { return this.pixels; }

    // -- constructor
    public Image() {
        width = height = 0;
        pixels = null;
    }

    public Image(int w, int h) {
        width = w; height = h;
        pixels = new int[w*h];
        Arrays.fill(pixels, 0);
    }

    public Image(int w, int h, int[] p) {
        this.width = w;
        this.height = h;
        this.pixels = Arrays.copyOf(p, p.length);
    }

    public Image(Image img) {
        this.width = img.getWidth();
        this.height = img.getHeight();

        int[] ref = img.getPixels();
        this.pixels = Arrays.copyOf(ref, ref.length);
    }

    // -- functions
    public int pairToImageIndex(int ih, int iw) { return ih * this.width + iw; }
    public int pairToImageIndex(PairRowColumn p) { return p.getRow() * this.width + p.getCol(); }
    public PairRowColumn imageIndexToPair(int idx) {
        return new PairRowColumn(idx / this.width, idx % this.width);
    }

    // -- toString()
    @Override
    public String toString() {
        String rep = "Width = " + this.width + " | Height = " + this.height + "\n";
        rep += "Image = {\n";
        for (int i = 0; i < this.width*this.height; i++) {
            rep += this.pixels[i];
            if ((i + 1) % width == 0) rep += "\n";
            else rep += " ";
        }
        rep += "}\n";
        return rep;
    }
}