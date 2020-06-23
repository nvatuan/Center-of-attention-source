package main.gui.Controller;

// import from standard java
import javax.swing.*;
import java.awt.*;

// import from my packages
import main.gui.Constants.MyFont;

public class TextField {
    public static JTextArea ta_input = new JTextArea();
    public static JTextArea ta_output = new JTextArea();
    static void to_vien() {
//      tf_input.setPreferredSize(new Dimension(200,200));
//      tf_output.setPreferredSize(new Dimension(200,200));
        ta_input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        ta_input.setPreferredSize(ta_input.getSize());
        ta_input.setFont(MyFont.Monospaced);
        ta_input.setLineWrap(true);

        ta_output.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        ta_output.setPreferredSize(ta_output.getSize());
        ta_output.setFont(MyFont.Monospaced);
        ta_output.setLineWrap(true);
    }
}
