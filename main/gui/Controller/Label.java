package main.gui.Controller;

import javax.swing.*;
import java.awt.*;

public class Label extends TextArea {
    JLabel lblInput = new JLabel("INPUT");
    JLabel lblOutput = new JLabel("OUTPUT");
    JLabel lblQuestion1 = new JLabel("?");
    JLabel lblQuestion2 = new JLabel("?");

    public Label() {
        super();
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
