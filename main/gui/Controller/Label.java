package main.gui.Controller;

import javax.swing.*;
import java.awt.*;

// This class contains all of the JLabel used by the main form
// Inherits every fields from `main.Controller.TextArea`
public class Label extends TextArea {
    // ==== Fields
    JLabel lblInput = new JLabel("INPUT");
    JLabel lblOutput = new JLabel("OUTPUT");
    JLabel lblQuestion1 = new JLabel("?");
    JLabel lblQuestion2 = new JLabel("?");

    // ==== Constructor
    // Default constructor with no args
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
