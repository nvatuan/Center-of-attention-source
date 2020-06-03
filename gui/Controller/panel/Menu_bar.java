package Controller.panel;

import javax.swing.*;

public interface Menu_bar {
    public static JMenu Menu = new JMenu("Help");
    public static JMenuItem Item_AboutUs = new JMenuItem("About us");

     JMenuBar add();
}
