package main.gui.Controller;

import javax.swing.*;

public class Button extends Label {
    static JButton Start = new JButton("Start");
    static JButton ProblemPrompt = new JButton("Problem Prompt");
    static JButton History = new JButton("History");
    static JButton About = new JButton("About us");
    static JButton Reformat = new JButton("Reformat");

    static JButton InputHelp = new JButton("?");
    static JButton OutputHelp = new JButton("?");

    static JButton InputPaste = new JButton("Paste");
    static JButton OutputCopy = new JButton("Copy");

    static void to_vien() {
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
