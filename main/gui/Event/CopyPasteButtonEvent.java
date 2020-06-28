package main.gui.Event;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

import main.gui.Controller.Frame;

public class CopyPasteButtonEvent implements ActionListener {
    public Frame frame;
    public CopyPasteButtonEvent (Frame source) {
        this.frame = source;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String content = null;
        switch (e.getActionCommand()) {
            case "Copy":
                content = frame.taOutput.getText();
                if (content == "") return;
                StringSelection selection = new StringSelection(content);
                clip.setContents(selection, selection);
                break;
            case "Paste":
                Transferable t = clip.getContents(this);
                if (t == null) return;
                try {
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