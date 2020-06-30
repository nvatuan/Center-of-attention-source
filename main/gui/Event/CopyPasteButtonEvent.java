package main.gui.Event;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

import main.gui.Controller.Frame;

// This class do event handling when the [Copy to Clipboard] and [Paste from Clipboard] is pressed
public class CopyPasteButtonEvent implements ActionListener {
    // ==== Constructor
    // Pass a `main.gui.Controller.Frame` object ref to pass data
    public Frame frame;
    public CopyPasteButtonEvent (Frame source) {
        this.frame = source;
    }

    // ==== Overriden methods
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get clipboard
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String content = null;

        switch (e.getActionCommand()) { // Check whether the button pressed command is Copy/Paste
            case "Copy":
                content = frame.taOutput.getText();
                if (content == "") return; // if there is nothing to copy, return immediately
                // Create a StringSelection object so we can set content to clipboard
                StringSelection selection = new StringSelection(content);
                clip.setContents(selection, selection);
                break;
            case "Paste":
                // Create a transferable data object
                Transferable t = clip.getContents(this);
                if (t == null) return; // the object doesn't exist, return
                try {
                    // Try to paste the data to the textbox
                    // The data may not be text data
                    content = (String) t.getTransferData(DataFlavor.stringFlavor);
                    frame.taInput.setText(content);
                } catch (Exception ex) {
                    System.out.println("Error!! Cannot convert this data to Text.");
                    //ex.printStackTrace();
                }
                break;
        }
    }
}