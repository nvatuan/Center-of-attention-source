package gui.Controller;

import javax.swing.*;

public class Button extends Label {
    static JButton Start = new JButton("Start");
    static JButton Log = new JButton("Log screen");
    static JButton History = new JButton("History");
    static JButton Reformat = new JButton("Reformat");
    static void to_vien() {
        // -- tô viền button
        Start.setBorder(BorderFactory.createRaisedBevelBorder());
//      Start.setFont(new Font("Courier",Font.BOLD,10));
        Log.setBorder(BorderFactory.createRaisedBevelBorder());
//      Log.setFont(new Font("Courier",Font.BOLD,10));
        History.setBorder(BorderFactory.createRaisedBevelBorder());
//      History.setFont(new Font("Courier",Font.BOLD,10));

        Reformat.setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
