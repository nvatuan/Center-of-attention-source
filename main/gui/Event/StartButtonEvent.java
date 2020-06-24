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
        ImageCentralPixels img = ImageCentralPixels.parseImageCP(str);
        int[] result = img.centralPixels(img.k);
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
