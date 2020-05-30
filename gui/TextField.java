package Controller;

import javax.swing.*;
import java.awt.*;

public class TextField {
    static JTextField jt_input = new JTextField();
    static JTextField jt_output = new JTextField();

    static void to_vien()
    {
        jt_input.setPreferredSize(new Dimension(200,200));
        jt_output.setPreferredSize(new Dimension(200,200));

        jt_input.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        jt_output.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

    }
}
