package main.gui.Event;

import main.gui.Controller.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUsButtonEvent implements ActionListener {
    public Frame obj;
    public AboutUsButtonEvent(Frame obj)
    {
        this.obj = obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"Made by Anh Tuấn and Quang Huy", "About us", JOptionPane.INFORMATION_MESSAGE);
    }
}
