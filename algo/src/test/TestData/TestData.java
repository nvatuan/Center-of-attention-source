package test;

import main.ImageCentralPixels;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileReader;
import java.io.BufferedReader;

import java.util.ArrayList;

public class TestData {
    public ImageCentralPixels img = new ImageCentralPixels();
    public ArrayList<Integer> subtestInput = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> subtestJuryans = new ArrayList<ArrayList<Integer>>();
    
    // -- constructor
    public TestData() { }
    public TestData(TestData td) {
        this.img = td.img;
        this.subtestInput = td.subtestInput;
        this.subtestJuryans = td.subtestJuryans;
    }
    public TestData(File fi, File fo) throws IOException, NumberFormatException {
        subtestInput = new ArrayList<Integer>();
        subtestJuryans = new ArrayList<ArrayList<Integer>>();

        FileReader fr = new FileReader(fi); 
        BufferedReader br = new BufferedReader(fr);

        // -- Parsing File in
        String line;
        String[] splittedLine;
        int h, w;
        int[] p;
    
        line = br.readLine();
        line = line.trim();
        splittedLine = line.split(" ");

        w = Integer.parseInt(splittedLine[0]);
        h = Integer.parseInt(splittedLine[1]);

        line = br.readLine();
        line = line.trim();
        splittedLine = line.split(" ");

        p = new int[h*w];
        for (int i = 0; i < h*w; i++) {
            p[i] = Integer.parseInt(splittedLine[i]);
        }

        // --
        this.img = new ImageCentralPixels(w, h, p);
        // --
        int t;
        line = br.readLine();
        line = line.trim();
        t = Integer.parseInt(line);

        for (int i = 0; i < t; i++) {
            line = br.readLine();
            subtestInput.add(Integer.parseInt(line));
        }

        br.close();
        fr.close();
        // --------------------------------------------------------
        // -- Parsing File out
        fr = new FileReader(fo);
        br = new BufferedReader(fr);
        for (int i = 0; i < t; i++) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            line = br.readLine();

            if (line == null) {
            } else {
                line = line.trim();

                splittedLine = line.split(" ");
                for (String st : splittedLine)
                    if (st.compareTo("") != 0) tmp.add(Integer.parseInt(st));
            }

            subtestJuryans.add(tmp);
        }
        br.close();
        fr.close();
    }

    // -- functions

    // -- toString()
    @Override
    public String toString() {
        String rep = "";
        rep += ("TestData = \n");
        rep += (this.img.toString());

        rep += ("> SubtestCount = " + subtestInput.size() + "\n");
        rep += ("> SubtestInput = {\n");
        for (Integer I : subtestInput)
            rep += (I + " ");
        rep += ("\n}\n");

        rep += ("> SubtestJuryans = {\n");
        for (ArrayList<Integer> AI : subtestJuryans) {
            rep += ("{");
            for (Integer I : AI) rep += (" " + I + " ");
            rep += ("}\n");
        }
        rep += ("}\n");
        //
        return rep;
    }
}