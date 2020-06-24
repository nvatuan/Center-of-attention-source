package main.algo;

import main.algo.Image;
import main.algo.PairRowColumn;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.max;

import java.io.Serializable;

public class ImageCentralPixels extends Image implements Serializable {
    public int k;
    // -- constructors
    public ImageCentralPixels() { super(); }
    public ImageCentralPixels(int w, int h) { super(w, h); }
    public ImageCentralPixels(int w, int h, int[] p) { super(w, h, p); }
    public ImageCentralPixels(int w, int h, int[] p, int k) { super(w, h, p); this.k = k; }
    // -- create
    public static ImageCentralPixels parseImageCP(String st) {
        String str = st.trim(); 
        String[] temp = str.split("\\s+");
        String arr = "";
        int n = 0, m = 0, k = 0;
        int count = 0;
        int[] p = null;
        //
        try {
            n = Integer.parseInt(temp[0]); count++;
            m = Integer.parseInt(temp[1]); count++;
            k = Integer.parseInt(temp[2]); count++;
            p = new int[n*m];
            for(int i = 0; i < n*m ; i++)
            {
                p[i] = Integer.parseInt(temp[3+i]);
            }
            for(int i = n*m ; i < temp.length ; i++)
            {
                temp[n*m] = "0";
            }
        } catch (Exception e1) {
            //JOptionPane.showMessageDialog(null,"Format error in" + count + "line");
        }
        return new ImageCentralPixels(n, m, p, k);
    }

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

    private boolean valid(int ih, int iw, int colour) {
        if (ih < 0 || ih == this.height) return false;
        if (iw < 0 || iw == this.width) return false;
        if (this.pixels[this.pairToImageIndex(ih, iw)] != colour) return false;
        return true;
    }

    private void updateBorder(int IH, int IW, int colour, ArrayList<Integer> border, boolean[][] visited) {
        LinkedList<PairRowColumn> Q = new LinkedList<PairRowColumn>();
        Q.addLast(new PairRowColumn(IH, IW));

        while (Q.size() > 0) {
            int ih = Q.peekFirst().getRow();
            int iw = Q.peekFirst().getCol();
            Q.removeFirst();

            if (!valid(ih, iw, colour)) continue;
            if (visited[ih][iw]) continue;
            visited[ih][iw] = true;

            if (this.isBorder(ih, iw, colour)) border.add(this.pairToImageIndex(ih, iw));
            
            Q.addLast(new PairRowColumn(ih + 1, iw));
            Q.addLast(new PairRowColumn(ih - 1, iw));
            Q.addLast(new PairRowColumn(ih, iw + 1));
            Q.addLast(new PairRowColumn(ih, iw - 1));
        }
    }

    // --
    public int[] centralPixels(int colour) {
        ArrayList<Integer> border = new ArrayList<Integer>();
        boolean[][] visited = new boolean[this.height][this.width];

        for (int ih = 0; ih < this.height; ih++)
            for (int iw = 0; iw < this.width; iw++)
                if (this.pixels[this.pairToImageIndex(ih, iw)] == colour)
                    if (visited[ih][iw] == false)
                        this.updateBorder(ih, iw, colour, border, visited);

        int MAX_DEPTH = 0;

        int[] depth_of = new int[this.height * this.width];
        Arrays.fill(depth_of, -1);

        // -- 2nd BFS wave
        LinkedList<PairRowColumn> Q = new LinkedList<PairRowColumn>();
        LinkedList<Integer>       Qd = new LinkedList<Integer>();
        for (Integer i : border) {
            Q.addLast(this.imageIndexToPair(i));
            Qd.addLast(1);
        }
        
        while (Q.size() > 0) {
            int ih = Q.peekFirst().getRow();
            int iw = Q.peekFirst().getCol();
            int depth = Qd.peekFirst();
            Q.removeFirst();
            Qd.removeFirst();

            if (!valid(ih, iw, colour)) continue;
            if (depth_of[this.pairToImageIndex(ih, iw)] != -1) continue;

            MAX_DEPTH = max(MAX_DEPTH, depth);
            depth_of[this.pairToImageIndex(ih, iw)] = depth;
            
            Q.addLast(new PairRowColumn(ih + 1, iw));
            Qd.addLast(depth + 1);
            Q.addLast(new PairRowColumn(ih - 1, iw));
            Qd.addLast(depth + 1);
            Q.addLast(new PairRowColumn(ih, iw + 1));
            Qd.addLast(depth + 1);
            Q.addLast(new PairRowColumn(ih, iw - 1));
            Qd.addLast(depth + 1);
        }
        
        // -- scoop answer
        ArrayList<Integer> answerArrList = new ArrayList<Integer>();
        for (int i = 0; i < this.height * this.width; i++)
            if (depth_of[i] == MAX_DEPTH) answerArrList.add(i);
        
        int[] answer = new int[answerArrList.size()];
        for (int i = 0; i < answerArrList.size(); i++)
            answer[i] = answerArrList.get(i);
        return answer;
    }

    @Override public String toString() {
        String rep = width + " " + height + "\n" + k + "\n";
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