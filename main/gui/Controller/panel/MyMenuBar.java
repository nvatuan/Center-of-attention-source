package main.gui.Controller.panel;

import javax.swing.*;

// Represents method to add `JMenu` to `main.gui.Controller.Frame`
// Although the last product didn't have a `JMenu`
public interface MyMenuBar {
    public static JMenu menu = new JMenu("Help");
    public static JMenuItem menuItemAboutUs = new JMenuItem("About us");

    JMenuBar add();
}
