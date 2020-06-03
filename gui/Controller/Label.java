package Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Label extends TextField{
    static JLabel lb_input = new JLabel("INPUT");
    static JLabel lb_output = new JLabel("OUTPUT");


    static void can_chinh()
    {
        lb_input.setFont(new Font("Arial", Font.BOLD,20));
        lb_input.setHorizontalAlignment(JLabel.CENTER);

        lb_output.setFont(new Font("Arial", Font.BOLD,20));
        lb_output.setHorizontalAlignment(JLabel.CENTER);

    }



}
