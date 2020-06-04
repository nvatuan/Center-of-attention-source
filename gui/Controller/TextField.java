package Controller;

import javax.swing.*;
import java.awt.*;

public class TextField {
    static JTextField tf_input = new JTextField();
    static JTextField tf_output = new JTextField();

    static void to_vien()
    {
//        tf_input.setPreferredSize(new Dimension(200,200));
//        tf_output.setPreferredSize(new Dimension(200,200));

        tf_input.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        tf_output.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

    }
}
