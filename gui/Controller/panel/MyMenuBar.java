package main.gui.Controller.panel;

import javax.swing.*;

public interface MyMenuBar {
    public static JMenu menu = new JMenu("Help");
    public static JMenuItem menuItemAboutUs = new JMenuItem("About us");

    JMenuBar add();
}
