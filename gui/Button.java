package Controller;

import javax.swing.*;
import java.awt.*;

public class Button extends Label {
        static JButton Start = new JButton("Start");
        static JButton Input = new JButton("Input");
        static JButton Toogle = new JButton("Toggle log screen");
        static JButton History = new JButton("History");

     static void to_vien()
    {
        //tô viền button
        Start.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        Input.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        Toogle.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        History.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }

}
