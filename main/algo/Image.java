package main.algo;

import java.io.Serializable;
import java.util.Arrays;

import main.algo.*;

// This class contains two integer `width`, `height` and an array of integer `pixels`
// with `width*height` elements, represents an image with that much pixels.
// This class is base class without algorithm implementation
public class Image implements Serializable {
    // ==== fields
    int[] pixels;
    int width, height;
    // ==== getters
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public int[] getPixels() { return this.pixels; }

    // ==== constructor
    // Default no args constructor
    public Image() {
        width = height = 0;
        pixels = null;
    }
    
    // Constructor takes `width`, `heigth` as args, will initialize `pixels[]` to be all 0
    public Image(int w, int h) {
        width = w; height = h;
        pixels = new int[w*h];
        Arrays.fill(pixels, 0);
    }

    // Constructor takes `width`, `height`, `pixels[]` as args, will initialize instance data with them
    public Image(int w, int h, int[] p) {
        this.width = w;
        this.height = h;
        this.pixels = Arrays.copyOf(p, p.length);
    }

    // A copy constructor, will copy data of an `main.algo.Image` type
    public Image(Image img) {
        this.width = img.getWidth();
        this.height = img.getHeight();

        int[] ref = img.getPixels();
        this.pixels = Arrays.copyOf(ref, ref.length);
    }

    // ==== methods
    // Convert a pair of integers represents coordinates in a 2D array
    // to the corresponding index number as if that 2D array is laid out
    // to be a 1D array from left to right, top to bottom
    public int pairToImageIndex(int ih, int iw) { return ih * this.width + iw; }
    public int pairToImageIndex(PairRowColumn p) { return p.getRow() * this.width + p.getCol(); }
    // Convert index of 1D array to be a pair of integers that represents
    // coordinates in a 2D array as if that 2D array is made of that 1D
    // array splits every `width` elements from left to right and arranges
    // them from top to bottom.
    public PairRowColumn imageIndexToPair(int idx) {
        return new PairRowColumn(idx / this.width, idx % this.width);
    }

    // Ovrride `toString()` method
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