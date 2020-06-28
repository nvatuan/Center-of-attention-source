package main.gui.Event;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

import main.gui.Constants.MyHelpDialog;

public class InputOutputHelpEvent implements ActionListener {
    public InputOutputHelpEvent() {}
    //
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dial = null;
        switch (e.getActionCommand()) {
            case "Input?":
                dial = MyHelpDialog.getHelpInput();
                break;
            case "Output?":
                dial = MyHelpDialog.getHelpOutput();
                break;
            default:
                return;
        }
        if (dial != null) dial.setVisible(true);
    }
}
