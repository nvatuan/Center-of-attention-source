//package main.algo.drawing;

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
    public static HashMap<Integer, Color> convertRange(int[] intArray) {
        // -- Collecting distinct integers and sort them
        List<Integer> list = Arrays.stream(intArray).boxed().distinct().collect(Collectors.toList());
        Collections.sort(list);
        // -- Use hue circle to map integer with colors, more details under this link
        // [https://stackoverflow.com/questions/46928277/trying-to-convert-integer-range-to-rgb-color]
        int n = list.size();


        System.out.println(list);

        return new HashMap<Integer, Color>();
    }

    // This is going to take a day or two if i go on a rampage researching how RGB and HSL work
    // So I decided learning is not appropriate now.
    // With regards to [http://www.camick.com/java/source/HSLColor.java]
    // and their amazing Blog post [https://tips4java.wordpress.com/2009/07/05/hsl-color/]
    // I have taken liberty to adjust the code based on my needs.
    // ---- COPIED CODE STARTS HERE ---------------
    public static Color HueToColor(float h) {
        float s = 1.0f;
        float l = 0.5f;
        float alpha = 1.0f;
		h = h % 3.6f;

		float q = 0;

		if (l < 0.5) q = l * (1 + s);
		else q = (l + s) - (s * l);

		float p = 2 * l - q;

		float r = HueComponentToRGBComponent(p, q, h + (1.0f / 3.0f));
		float g = HueComponentToRGBComponent(p, q, h);
		float b = HueComponentToRGBComponent(p, q, h - (1.0f / 3.0f));

		r = Math.min(r, 1.0f);
		g = Math.min(g, 1.0f);
		b = Math.min(b, 1.0f);

		return new Color(r, g, b, alpha);
	}

	private static float HueComponentToRGBComponent(float p, float q, float h) {
		if (h < 0) h += 1;
		if (h > 1) h -= 1;
		if (6 * h < 1) return p + ((q - p) * 6 * h);
		if (2 * h < 1) return q;
		if (3 * h < 2) return p + ( (q - p) * 6 * ((2.0f / 3.0f) - h) );
   		return p;
    }
    // ---- COPIED CODE ENDS HERE ---------------

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
            panel.setBackground(new Color(1.0f - 1.0f/(f*10), f, 0f));
            System.out.println(new Color(1.0f - 1.0f/(f*10), f, 0f));
            panel.repaint();
        }
    }
}