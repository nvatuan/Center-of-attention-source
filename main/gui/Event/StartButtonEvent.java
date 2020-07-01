package main.gui.Event;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

import main.gui.Controller.Frame;
import main.algo.ImageCentralPixels;

// This class handles event when START Button is pressed
public class StartButtonEvent implements ActionListener {
    // ==== Construstor
    // Pass a `main.gui.Controller.Frame` object to access data
    public Frame frame;
    public StartButtonEvent(Frame source) {
        this.frame = source;
    }

    // ==== Overriden methods
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = frame.taInput.getText();

        String resultString = "";
        try {
            frame.img = ImageCentralPixels.parseImageCP(str);

            // -- reformat the input
            String reformat = "";
            reformat += frame.img.getWidth() + " " + frame.img.getHeight() + "\n" + frame.img.queriedColor + "\n";
            for (int i = 0; i < frame.img.getHeight(); i++)
                for (int j = 0; j < frame.img.getWidth(); j++) {
                    reformat += frame.img.getPixels()[frame.img.pairToImageIndex(i, j)];
                    reformat += (j + 1 == frame.img.getWidth() ? '\n' : ' ');
                }
            frame.taInput.setText(reformat);
            // 

            int[] result = frame.img.centralPixels(frame.img.queriedColor);
            //
            resultString = "{";
            for (int i = 0; i < result.length; i++) {
                resultString += result[i];
                if (i + 1 < result.length) resultString += " ";
            }
            resultString += "}";

        } catch (NumberFormatException nfex) { // Non-number characters detected exception
            JOptionPane.showMessageDialog(null, "Dữ liệu vào chỉ chấp nhận các số nguyên, các ký tự khoảng trống và các ký tự xuống dòng.", "ERROR: Ký tự không hợp lệ trong INPUT", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException iaex) { // Non-integer detected
            JOptionPane.showMessageDialog(null, "Hai số nguyên đầu (Width và Height) phải là số nguyên dương.", "ERROR: Giá trị không hợp lệ trong INPUT", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) { // Unexpeceted exception
            ex.printStackTrace();
        } finally { // Write text to OUTPUT text area
            frame.taOutput.setText(resultString);
        }
        // Don't `repaint()` when failed to parse input
        frame.canvas.repaint();
    }
}
