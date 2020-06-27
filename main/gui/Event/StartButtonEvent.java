package main.gui.Event;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

import main.gui.Controller.Frame;
import main.algo.ImageCentralPixels;

public class StartButtonEvent implements ActionListener {
    public Frame frame;
    public StartButtonEvent(Frame obj) {
        this.frame = obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = frame.taInput.getText();

        String resultString = "";
        try {
            frame.img = ImageCentralPixels.parseImageCP(str);

            int[] result = frame.img.centralPixels(frame.img.queriedColor);
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
            frame.taOutput.setText(resultString);
        }

        frame.canvas.repaint();
    }
}
