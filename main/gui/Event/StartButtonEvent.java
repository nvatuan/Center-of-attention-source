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
        String str = obj.taInput.getText();

        String resultString = "";
        try {
            ImageCentralPixels img = ImageCentralPixels.parseImageCP(str);

            int[] result = img.centralPixels(img.queriedColor);
            //
            resultString = "{";
            for (int i = 0; i < result.length; i++) {
                resultString += result[i];
                if (i + 1 < result.length) resultString += " ";
            }
            resultString += "}";
        } catch (NumberFormatException nfex) {
            JOptionPane.showMessageDialog(null, "INPUT contains invalid characters. Please input only numbers", "Error: parsing Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
        } finally {
            obj.taOutput.setText(resultString);
        }
    }
}
