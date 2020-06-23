package Controller;

import javax.swing.*;
import java.awt.*;

public class TextField extends My_Font{
    static JTextArea ta_input = new JTextArea();
    static JTextArea ta_output = new JTextArea();
    static void to_vien() {
//      tf_input.setPreferredSize(new Dimension(200,200));
//      tf_output.setPreferredSize(new Dimension(200,200));
        ta_input.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        ta_input.setPreferredSize(ta_input.getSize());
        ta_input.setFont(My_Font);
        ta_input.setLineWrap(true);

        ta_output.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        ta_output.setPreferredSize(ta_output.getSize());
        ta_output.setFont(My_Font);
        ta_output.setLineWrap(true);
    }
}
