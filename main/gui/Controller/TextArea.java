package main.gui.Controller;

import javax.swing.*;
import java.awt.*;

import main.gui.Constants.MyFont;

// This class contains every `JTextArea` used by the main form
public class TextArea {
    // ==== Fields
    public JTextArea taInput = new JTextArea();
    public JTextArea taOutput = new JTextArea();

    // ==== Constructor
    // Default no arg constructor
    public TextArea() {
        super();
        taInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        taInput.setPreferredSize(taInput.getSize());
        taInput.setFont(MyFont.Monospaced);
        taInput.setLineWrap(true);

        taOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        taOutput.setPreferredSize(taOutput.getSize());
        taOutput.setFont(MyFont.Monospaced);
        taOutput.setLineWrap(true);
        taOutput.setEditable(false);
    }
}
