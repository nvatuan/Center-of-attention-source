package main.gui.Event;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

import main.gui.Controller.Frame;
import main.algo.ImageCentralPixels;

public class StartButtonEvent implements ActionListener {
    public Frame obj;
    public StartButtonEvent(Frame obj) {
        this.obj = obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = obj.ta_input.getText();
        String[] temp = str.split("\\s");
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
            JOptionPane.showMessageDialog(null,"Format error in" + count + "line");
        }
        // --
        ImageCentralPixels img = new ImageCentralPixels(n, m, p);
        int[] result = img.centralPixels(k);
        //
        String tostring = "{";
        for (int i = 0; i < result.length; i++) {
            tostring += result[i];
            if (i + 1 < result.length) tostring += " ";
        }
        tostring += "}";
        obj.ta_output.setText(tostring);
    }
}
