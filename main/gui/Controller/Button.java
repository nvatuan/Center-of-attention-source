package main.gui.Controller;

import javax.swing.*;
import java.awt.Insets;
import java.awt.Dimension;

// This class inherits all fields in `Label` and adds all `JButton` that
// are used in the program
public class Button extends Label {
    // instances `JButton`
    JButton Start = new JButton("Thực thi");
    JButton ProblemPrompt = new JButton("Chi tiết Bài toán");
    JButton History = new JButton("Dữ liệu Database");
    JButton About = new JButton("About us");

    JButton InputHelp = new JButton("?");
    JButton OutputHelp = new JButton("?");

    JButton InputPaste = new JButton("Paste from Clipboard");
    JButton OutputCopy = new JButton("Copy to Clipboard");

    // Default no args construtor
    public Button() {
        super();
        Start.setFocusPainted(false);
        ProblemPrompt.setFocusPainted(false);
        History.setFocusPainted(false);
        About.setFocusPainted(false);
        InputHelp.setFocusPainted(false);
        InputPaste.setFocusPainted(false);
        OutputHelp.setFocusPainted(false);
        OutputCopy.setFocusPainted(false);

        InputHelp.setActionCommand("Input?");
        OutputHelp.setActionCommand("Output?");

        InputPaste.setMargin(new Insets(0, 0, 0, 0));
        OutputCopy.setMargin(new Insets(0, 0, 0, 0));
        
        InputPaste.setActionCommand("Paste");
        OutputCopy.setActionCommand("Copy");
    }
}
