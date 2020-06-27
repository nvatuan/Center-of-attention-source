package main.gui.Controller;

import javax.swing.*;
import java.awt.*;

public class Label extends TextArea {
    static JLabel lblInput = new JLabel("INPUT");
    static JLabel lblOutput = new JLabel("OUTPUT");
    static JLabel lblQuestion1 = new JLabel("?");
    static JLabel lblQuestion2 = new JLabel("?");

    static void can_chinh()
    {
        lblInput.setFont(new Font("Arial", Font.BOLD,15));
        lblInput.setHorizontalAlignment(JLabel.CENTER);

        lblOutput.setFont(new Font("Arial", Font.BOLD,15));
        lblOutput.setHorizontalAlignment(JLabel.CENTER);

        lblQuestion1.setFont(new Font("Arial", Font.BOLD,20));
        lblQuestion1.setToolTipText("Nhập INPUT");

        lblQuestion2.setFont(new Font("Arial", Font.BOLD,20));
        lblQuestion2.setToolTipText("Nhập OUTPUT");
    }
}
