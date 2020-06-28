package main.gui.Controller;

import javax.swing.*;
import java.awt.Insets;

public class Button extends Label {
    JButton Start = new JButton("Thực thi");
    JButton ProblemPrompt = new JButton("Chi tiết Bài toán");
    JButton History = new JButton("Dữ liệu Database");
    JButton About = new JButton("About us");
    JButton Reformat = new JButton("Reformat");

    JButton InputHelp = new JButton("?");
    JButton OutputHelp = new JButton("?");

    JButton InputPaste = new JButton("Paste from Clipboard");
    JButton OutputCopy = new JButton("Copy to Clipboard");

    public Button() {
        super();
        Start.setFocusPainted(false);
        ProblemPrompt.setFocusPainted(false);
        History.setFocusPainted(false);
        About.setFocusPainted(false);
        Reformat.setFocusPainted(false);
        InputHelp.setFocusPainted(false);
        InputPaste.setFocusPainted(false);
        OutputHelp.setFocusPainted(false);
        OutputCopy.setFocusPainted(false);

        InputHelp.setActionCommand("Input?");
        OutputHelp.setActionCommand("Output?");

        InputPaste.setActionCommand("Paste");
        InputPaste.setMargin(new Insets(0, 0, 0, 0));
        OutputCopy.setActionCommand("Copy");
        OutputCopy.setMargin(new Insets(0, 0, 0, 0));
    }

    void toVien() {
        // -- tô viền button
        Start.setBorder(BorderFactory.createRaisedBevelBorder());
        // Start.setFont(new Font("Courier",Font.BOLD,10));
        ProblemPrompt.setBorder(BorderFactory.createRaisedBevelBorder());
        // Log.setFont(new Font("Courier",Font.BOLD,10));
        History.setBorder(BorderFactory.createRaisedBevelBorder());
        // History.setFont(new Font("Courier",Font.BOLD,10));

        Reformat.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
