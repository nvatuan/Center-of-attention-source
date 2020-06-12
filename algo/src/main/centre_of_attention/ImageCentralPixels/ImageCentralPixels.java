import main.centre_of_attention.Image;

public class ImageCentralPixels extends Image {
    // -- constructors
    public ImageCentralPixels() { super(); }
    public ImageCentralPixels(int w, int h) { super(w, h); }
    public ImageCentralPixels(int w, int h, int[] p) { super(w, h, p); }

    // --
    public int[] centralPixels(int colour) {
        System.out.println("Image.toString() = ");
        System.out.println(this.toString());
        System.out.println("Colour looked for = " + colour);
        return new int[0];
    }
    
    // -- Main
    public static void main(String[] args) {
        int w = 4;
        int h = 3;
        int[] p = new int[] {  
            1, 1, 1, 2,
            2, 1, 1, 1,
            1, 1, 1, 2
        };
        ImageCentralPixels img = new ImageCentralPixels(w, h, p);
        img.centralPixels(1);
    }
}