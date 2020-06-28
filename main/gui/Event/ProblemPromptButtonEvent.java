package main.gui.Event;

import main.gui.Controller.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProblemPromptButtonEvent implements ActionListener {
    public Frame obj;
    public JPanel pnl_ProblemPrompt = new JPanel();
    public ProblemPromptButtonEvent(Frame obj)
    {
        this.obj = obj;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog ProblemPromptFrame = new JDialog();
        ProblemPromptFrame.setTitle("Project's details");
        ProblemPromptFrame.setMinimumSize(new Dimension(550,550));
        ProblemPromptFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ProblemPromptFrame.setLocationRelativeTo(null);

        ProblemPromptFrame.setVisible(true);
    }
}
