package gui.Controller;

import javax.swing.*;
import java.awt.*;

public class Label extends TextField {
    static JLabel lb_input = new JLabel("INPUT");
    static JLabel lb_output = new JLabel("OUTPUT");
    static JLabel lb_question1 = new JLabel("?");
    static JLabel lb_question2 = new JLabel("?");

    static void can_chinh()
    {
        lb_input.setFont(new Font("Arial", Font.BOLD,15));
        lb_input.setHorizontalAlignment(JLabel.CENTER);

        lb_output.setFont(new Font("Arial", Font.BOLD,15));
        lb_output.setHorizontalAlignment(JLabel.CENTER);

        lb_question1.setFont(new Font("Arial", Font.BOLD,20));
        lb_question1.setToolTipText("Nhập INPUT");

        lb_question2.setFont(new Font("Arial", Font.BOLD,20));
        lb_question2.setToolTipText("Nhập OUTPUT");
    }
}
