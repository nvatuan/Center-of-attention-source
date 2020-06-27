package main.gui.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.gui.Controller.panel.*;
import main.gui.Controller.Canvas;
import main.gui.Constants.MyHeightWidth;
import main.gui.Event.*;
import main.algo.ImageCentralPixels;

public class Frame extends Button implements MyMenuBar, PanelNishi, PanelHigashi, PanelCenter {
    public JFrame mainFrame = new JFrame("Center of Attention");
    public ImageCentralPixels img = null;
    public Canvas canvas = null;

    public Frame() {
        mainFrame.setSize(MyHeightWidth.W, MyHeightWidth.H);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout(10, 5));

        // -- add JMenubar vào trong frame
        mainFrame.setJMenuBar(add());

        mainFrame.add(BorderLayout.CENTER, panelCenter());
        mainFrame.add(BorderLayout.WEST, panelNishi());
        mainFrame.add(BorderLayout.EAST, panelHigashi());
        // mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public JPanel panelNishi() {
        Button.to_vien();
        JPanel pnlNishi = new JPanel();
        pnlNishi.setBorder(BorderFactory.createTitledBorder("Function"));
        pnlNishi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 15;
        c.ipadx = 30;
        c.insets = new Insets(20, 10, 10, 10);
        //
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnlNishi.add(Start, c);
        StartButtonEvent sbe = new StartButtonEvent(this);
        Start.addActionListener(sbe);
        //
        c.gridx = 0;
        c.gridy = 1;
        pnlNishi.add(ProblemPrompt, c);
        ProblemPromptButtonEvent objProblemPrompt = new ProblemPromptButtonEvent(this);
        ProblemPrompt.addActionListener(objProblemPrompt);
        //
        c.gridx = 0;
        c.gridy = 2;
        pnlNishi.add(History, c);
        HistoryButtonEvent objHistory = new HistoryButtonEvent(this);
        History.addActionListener(objHistory);
        //
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 0;
        c.gridy = 3;
        pnlNishi.add(About, c);
        AboutUsButtonEvent objAbout = new AboutUsButtonEvent(this);
        About.addActionListener(objAbout);

        return pnlNishi;
    }

    @Override
    public JPanel panelHigashi() {
        Label.can_chinh();
        TextArea.to_vien();
        JPanel pnlHigashi = new JPanel();
        pnlHigashi.setLayout(new GridBagLayout());
        pnlHigashi.setBorder(BorderFactory.createTitledBorder("Input & Output"));

        GridBagConstraints c = new GridBagConstraints();

        c.ipadx = 10;
        c.ipady = 10;
        c.insets = new Insets(10, 10, 0, 10);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;

        pnlHigashi.add(lblInput, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnlHigashi.add(lblQuestion1, c);

        c.insets = new Insets(0, 10, 120, 10);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipadx = 120;
        c.ipady = 130;
        pnlHigashi.add(taInput, c);

        c.insets = new Insets(0, 10, 10, 10);
        c.ipadx = 10;
        c.ipady = 10;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnlHigashi.add(lblOutput, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnlHigashi.add(lblQuestion2, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipadx = 120;
        c.ipady = 130;
        pnlHigashi.add(taOutput, c);

        c.insets = new Insets(5, 10, 10, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        ReformatButtonEvent RF_Event = new ReformatButtonEvent(this);
        Reformat.addActionListener(RF_Event);
        pnlHigashi.add(Reformat, c);

        return pnlHigashi;
    }

    @Override
    public JMenuBar add() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menu.add(menuItemAboutUs);
        return menuBar;
    }

    @Override
    public JPanel panelCenter() {
        JPanel canvasContainer = new JPanel();
        canvasContainer.setLayout(new BorderLayout());
        canvasContainer.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        canvasContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        canvas = new Canvas(this);
        canvasContainer.add(canvas, BorderLayout.CENTER);
        canvasContainer.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = canvasContainer.getWidth();
                int h = canvasContainer.getHeight();
                int edge = Math.min(w, h);
                canvas.setPreferredSize(new Dimension(edge, edge));
                canvas.repaint();
                canvasContainer.revalidate();
            }
        });
        return canvasContainer;
    }
}
