package main.gui.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.gui.Controller.panel.*;
import main.gui.Controller.Canvas;
import main.gui.Constants.MyHeightWidth;
import main.gui.Event.*;

import main.algo.ImageCentralPixels;

// This class is the ultimate class, where the magic happens,
// In other words, when the program runs, this class gets called first
public class Frame extends Button implements MyMenuBar, PanelNishi, PanelHigashi, PanelCenter {
    // ==== Fields
    // `mainFrame` is the main interface allowing user to interact with every components so far
    public JFrame mainFrame = new JFrame("Center of Attention");
    // `img` is the `main.algo.Image` object
    public ImageCentralPixels img = null;
    // `canvas` is the image displayer in the middle
    public Canvas canvas = null;

    // ==== Constructor
    // Default no args constructor
    public Frame() {
        super();
        mainFrame.setSize(MyHeightWidth.W, MyHeightWidth.H);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout(10, 5));

        // Decided not to use JMenuBar
        // mainFrame.setJMenuBar(add());

        mainFrame.add(BorderLayout.CENTER, panelCenter());
        mainFrame.add(BorderLayout.WEST, panelNishi());
        mainFrame.add(BorderLayout.EAST, panelHigashi());
        mainFrame.setMinimumSize(new Dimension(700, 450));
        mainFrame.setVisible(true);
    }
    
    // ==== Override methods
    // The left panel, Function buttons and such,...
    @Override
    public JPanel panelNishi() {
        JPanel pnlNishi = new JPanel();
        pnlNishi.setBorder(BorderFactory.createTitledBorder("Function"));
        pnlNishi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 15;
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnlNishi.add(ProblemPrompt, c);
        ProblemPromptButtonEvent objProblemPrompt = new ProblemPromptButtonEvent(this);
        ProblemPrompt.addActionListener(objProblemPrompt);

        c.gridx = 0;
        c.gridy = 1;
        pnlNishi.add(Start, c);
        StartButtonEvent sbe = new StartButtonEvent(this);
        Start.addActionListener(sbe);

        c.gridx = 0;
        c.gridy = 2;
        pnlNishi.add(History, c);
        HistoryButtonEvent objHistory = new HistoryButtonEvent(this);
        History.addActionListener(objHistory);

        c.weighty = 0.5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 0;
        c.gridy = 3;
        pnlNishi.add(About, c);
        AboutUsButtonEvent objAbout = new AboutUsButtonEvent(this);
        About.addActionListener(objAbout);

        pnlNishi.setPreferredSize(new Dimension(150, 0));
        return pnlNishi;
    }

    // The right panel, INPUT OUTPUT boxes,...
    @Override
    public JPanel panelHigashi() {
        JPanel pnlHigashi = new JPanel();
        pnlHigashi.setLayout(new BoxLayout(pnlHigashi, BoxLayout.Y_AXIS));
        pnlHigashi.setBorder(BorderFactory.createTitledBorder("Input & Output"));
        pnlHigashi.setPreferredSize(new Dimension(150, 0));

        JPanel container = null;
        JPanel subContainer = null;

        // -------------- INPUT section -----------------
        container = new JPanel(new BorderLayout(3, 3));
        container.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        subContainer = new JPanel();
        subContainer.setLayout(new BoxLayout(subContainer, BoxLayout.X_AXIS));
        
        subContainer.add(lblInput);
        subContainer.add(Box.createRigidArea(new Dimension(5, 0)));
        subContainer.add(Box.createHorizontalGlue());
        subContainer.add(InputHelp);

        container.add(subContainer, BorderLayout.NORTH);
        container.add(taInput, BorderLayout.CENTER);
        container.add(InputPaste, BorderLayout.SOUTH);

        // -- add to parent container
        pnlHigashi.add(container);
        // -- add filler
        pnlHigashi.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlHigashi.add(Box.createVerticalGlue());
        // --

        // -------------- OUTPUT section -----------------
        container = new JPanel(new BorderLayout(3, 3));
        container.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        subContainer = new JPanel();
        subContainer.setLayout(new BoxLayout(subContainer, BoxLayout.X_AXIS));
        
        subContainer.add(lblOutput);
        subContainer.add(Box.createRigidArea(new Dimension(5, 0)));
        subContainer.add(Box.createHorizontalGlue());
        subContainer.add(OutputHelp);

        container.add(subContainer, BorderLayout.NORTH);
        container.add(taOutput, BorderLayout.CENTER);
        container.add(OutputCopy, BorderLayout.SOUTH);

        pnlHigashi.add(container);
        // ----------------------------------------------

        // -- add EventHandler to buttons
        CopyPasteButtonEvent cpeHandler = new CopyPasteButtonEvent(this);
        InputPaste.addActionListener(cpeHandler);
        OutputCopy.addActionListener(cpeHandler);
        
        InputOutputHelpEvent iohHandler = new InputOutputHelpEvent();
        InputHelp.addActionListener(iohHandler);
        OutputHelp.addActionListener(iohHandler);

        //ReformatButtonEvent RF_Event = new ReformatButtonEvent(this);
        //Reformat.addActionListener(RF_Event);
        //pnlHigashi.add(Reformat, c);

        return pnlHigashi;
    }

    // Menubar method, not used
    @Override
    public JMenuBar add() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menu.add(menuItemAboutUs);
        return menuBar;
    }

    // The middle panel, contains canvas
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
