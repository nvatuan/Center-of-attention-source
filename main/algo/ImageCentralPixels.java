package main.algo;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;
import static java.lang.Math.max;

import main.algo.Image;
import main.algo.PairRowColumn;

// This class inherits from `main.algo.Image`, inherits all of it data fields
// and methods. In addition to that, it implements the algorithm finding central
// pixels. The algorithm works in O(width*height)
public class ImageCentralPixels extends Image implements Serializable {
    // ==== Fields
    // -- Although this field is not necessary, because the algorithm function
    // already took an integer as args, but this field helps reduce the amount
    // of code to pass data between gui.Controller
    // --
    // Represents the color is being queried
    public int queriedColor;
    
    // Represents the depth of pixels, will be populated when the algorithm
    // function successfully ran.
    private int[] depthOf = null;
    public int[] getDepthOf() { return depthOf; } 

    // ==== constructors
    // Constructors correspoding to its superclass
    public ImageCentralPixels() { super(); }
    public ImageCentralPixels(int w, int h) { super(w, h); }
    public ImageCentralPixels(int w, int h, int[] p) { super(w, h, p); }
    // Constructor that takes an extra integer as color being queried
    public ImageCentralPixels(int w, int h, int[] p, int k) { super(w, h, p); this.queriedColor = k; }
    
    // ==== Parser
    // This function tried to create an `ImageCentralPixels` from a `String`
    // The Format of the `String` is the exact same in its `toString()` method
    // First three integers are `width`, `height`, `queriedColor`
    // Then an array of integers `pixels[]`
    public static ImageCentralPixels parseImageCP(String st) throws NumberFormatException, IllegalArgumentException, Exception {
        String stringHolder = st.trim(); 
        String[] parts = stringHolder.split("\\s+");
        int h = 0, w = 0, k = 0;
        int[] p = null;
        //
        h = Integer.parseInt(parts[0]); 
        w = Integer.parseInt(parts[1]);
        if (h <= 0 || w <= 0) throw new IllegalArgumentException();
        
        k = Integer.parseInt(parts[2]); 
        p = new int[h*w];
        for(int i = 0; i < h*w ; i++) {
            if (i + 3 < parts.length) p[i] = Integer.parseInt(parts[3+i]);
            else p[i] = 0;
        }
        return new ImageCentralPixels(h, w, p, k);
    }

    // ==== Algorithm methods related
    // This function returns `true` if a tile `ih`, `iw` is consider to be a border tile
    // A tile is border when (vertically, horizontally) next to it is another tile with
    // another color or that tile is on the edge of an image.
    private boolean isBorder(int ih, int iw, int colour) {
        boolean flag = false;
        for (int dh = -1; dh <= 1; dh += 2)
            flag |= (
                (ih + dh < 0 || ih + dh == this.height) ||
                (this.pixels[this.pairToImageIndex(ih + dh, iw)] != colour)
            );

        for (int dw = -1; dw <= 1; dw += 2)
            flag |= (
                (iw + dw < 0 || iw + dw == this.width) ||
                (this.pixels[this.pairToImageIndex(ih, iw + dw)] != colour)
            );
        return flag;
    }

    // This function returns `true` if coordiate `ih`, `iw` are valid coordinates
    // Valid coordinates are coordinates that has its height component `ih` in range [0, height)
    // and its width component `iw` in range [0, width) and if that tile has color = `colour`
    private boolean valid(int ih, int iw, int colour) {
        if (ih < 0 || ih == this.height) return false;
        if (iw < 0 || iw == this.width) return false;
        if (this.pixels[this.pairToImageIndex(ih, iw)] != colour) return false;
        return true;
    }

    // This function takes coordinates of a tile , a colour, a reference to list of tiles that is 
    // considered border, a 2D boolean array which check for visited coordinates
    // This function first will discover the connected area of tiles that share the same color
    // as input tile, then add if not already existing coordinates of tiles that lie on the border of 
    // that area to the referenced list.
    // The list contains only tiles that have the same colour.
    // Arguments: int, int (coordinates of tile that will be explored from), int (color of interest),
    // ArrayList<Integer> (ref'd list of border, coordinates in 1D-index form), boolean[][] (check if a tile is already checked)
    private void updateBorder(int IH, int IW, int colour, ArrayList<Integer> border, boolean[][] visited) {
        LinkedList<PairRowColumn> queue = new LinkedList<PairRowColumn>();
        queue.addLast(new PairRowColumn(IH, IW));

        while (queue.size() > 0) {
            int ih = queue.peekFirst().getRow();
            int iw = queue.peekFirst().getCol();
            queue.removeFirst();

            if (!valid(ih, iw, colour)) continue;
            if (visited[ih][iw]) continue;
            visited[ih][iw] = true;

            if (this.isBorder(ih, iw, colour)) border.add(this.pairToImageIndex(ih, iw));
            
            queue.addLast(new PairRowColumn(ih + 1, iw));
            queue.addLast(new PairRowColumn(ih - 1, iw));
            queue.addLast(new PairRowColumn(ih, iw + 1));
            queue.addLast(new PairRowColumn(ih, iw - 1));
        }
    }

    // Algorithm function
    // Argument: an integer represents the colour of interest
    // Return: a list of integer represents a list of index that has color = `colour` and depth equals to max_depth
    public int[] centralPixels(int colour) {
        // -- 1st BFS: Get list of tiles that lie on the border of areas of coloured 'colour`
        ArrayList<Integer> border = new ArrayList<Integer>();
        boolean[][] visited = new boolean[this.height][this.width];

        for (int ih = 0; ih < this.height; ih++)
            for (int iw = 0; iw < this.width; iw++)
                if (this.pixels[this.pairToImageIndex(ih, iw)] == colour)
                    if (visited[ih][iw] == false)
                        this.updateBorder(ih, iw, colour, border, visited);

        // -- 2nd BFS wave
        int maxDepth = 0;

        depthOf = new int[this.height * this.width];
        Arrays.fill(depthOf, -1);

        LinkedList<PairRowColumn> queue      = new LinkedList<PairRowColumn>();
        LinkedList<Integer>       queueDepth = new LinkedList<Integer>();
        for (Integer i : border) {
            queue.addLast(this.imageIndexToPair(i));
            queueDepth.addLast(1);
        }
        
        while (queue.size() > 0) {
            int ih = queue.peekFirst().getRow();
            int iw = queue.peekFirst().getCol();
            int depth = queueDepth.peekFirst();
            queue.removeFirst();
            queueDepth.removeFirst();

            if (!valid(ih, iw, colour)) continue;
            if (depthOf[this.pairToImageIndex(ih, iw)] != -1) continue;

            maxDepth = max(maxDepth, depth);
            depthOf[this.pairToImageIndex(ih, iw)] = depth;
            
            queue.addLast(new PairRowColumn(ih + 1, iw));
            queueDepth.addLast(depth + 1);
            queue.addLast(new PairRowColumn(ih - 1, iw));
            queueDepth.addLast(depth + 1);
            queue.addLast(new PairRowColumn(ih, iw + 1));
            queueDepth.addLast(depth + 1);
            queue.addLast(new PairRowColumn(ih, iw - 1));
            queueDepth.addLast(depth + 1);
        }
        
        // -- scoop answer
        ArrayList<Integer> answerArrList = new ArrayList<Integer>();
        for (int i = 0; i < this.height * this.width; i++)
            if (depthOf[i] == maxDepth) answerArrList.add(i);
        
        int[] answer = new int[answerArrList.size()];
        for (int i = 0; i < answerArrList.size(); i++)
            answer[i] = answerArrList.get(i);
        return answer;
    }

    // ==== Overriden `toString()` method
    @Override public String toString() {
        // rep: representation string
        String rep = width + " " + height + "\n" + queriedColor + "\n";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rep += pixels[i*width + j];
                if (j + 1 < width) rep += " ";
            }
            rep += "\n";
        }
        return rep;
    }
}