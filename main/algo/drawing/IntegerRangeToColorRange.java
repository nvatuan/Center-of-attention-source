package main.algo.drawing;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Scanner;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.List;
import java.util.HashMap;

public class IntegerRangeToColorRange {
    public static final float SATURATE = 1.0f;
    public static final float BRIGHTNESS = 1.0f;
    public static final float HUE_RANGE_START = 0.0f;
    public static final float HUE_RANGE_END   = 1.0f;

    public static HashMap<Integer, Color> getColorMapping(int[] intArray) {
        // -- Collecting distinct integers and sort them
        List<Integer> list = Arrays.stream(intArray).boxed().distinct().collect(Collectors.toList());
        Collections.sort(list);
        // -- Use hue circle to map integer with colors, more details under this link
        // The following code uses the HSB ColorRange to map
        HashMap<Integer, Color> hashMap = new HashMap<Integer, Color>();
        
        int n = list.size();
        for (int i = 0; i < n; i++) {
            float hue = (HUE_RANGE_END - HUE_RANGE_START)/n*i + HUE_RANGE_START;
            hashMap.put(list.get(i), Color.getHSBColor(hue, SATURATE, BRIGHTNESS));
        }

        return hashMap;
    }

    // --- TESTING
    /*
    public static void main(String s[]) {
        IntegerRangeToColorRange.convertRange(new int[] {1 ,1 ,6 ,1 ,2 ,5 ,2 ,1 ,2 ,1 ,5 ,4 ,3 ,2 ,1});
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(200, 200);
        JPanel panel = new JPanel();
        //float col = .45f;
        //panel.setBackground(HueToColor(col));
        //System.out.println(HueToColor(col));
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        while(true) {
            Scanner sc = new Scanner(System.in);
            float f = sc.nextFloat();
            //panel.setBackground(HueToColor(f));
            //System.out.println(HueToColor(f));
            panel.setBackground(Color.getHSBColor(f, 0.7f, 1f));
            System.out.println(Color.getHSBColor(f, 0.7f, 1f));
            panel.repaint();
        }
    }
    */
}