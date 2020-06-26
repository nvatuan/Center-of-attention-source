package main.gui.Controller;

// import from standard java
import javax.swing.*;
import java.awt.*;

// import from my packages
import main.gui.Constants.MyFont;

public class TextField {
    public static JTextArea taInput = new JTextArea();
    public static JTextArea taOutput = new JTextArea();
    static void to_vien() {
//      tf_input.setPreferredSize(new Dimension(200,200));
//      tf_output.setPreferredSize(new Dimension(200,200));
        taInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        taInput.setPreferredSize(taInput.getSize());
        taInput.setFont(MyFont.Monospaced);
        taInput.setLineWrap(true);

        taOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        taOutput.setPreferredSize(taOutput.getSize());
        taOutput.setFont(MyFont.Monospaced);
        taOutput.setLineWrap(true);
    }
}
