package main.algo;

// This class is simply a pair of integers
// Represents XY-coordinates
public class PairRowColumn {
    int r;
    int c;
    // -- constructors
    public PairRowColumn() { r = c = 0; }
    public PairRowColumn(int rr, int cc) { r = rr; c = cc; }
    // -- getters
    public int getRow() { return this.r; }
    public int getCol() { return this.c; }
    // -- toString()
    @Override
    public String toString() {
        String rep = "";
        rep += "Row = " + this.r + "; Col = " + this.c;
        return rep;
    }
}