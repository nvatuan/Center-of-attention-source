package main.gui.Event;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

import main.gui.Controller.Frame;

public class AboutUsButtonEvent implements ActionListener {
    public Frame obj;
    public AboutUsButtonEvent(Frame obj) {
        this.obj = obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // -- Java supports HTML rendering so
        // I quickly used an online HTML-editor to design this.
        String message = "" + 
            "<html>" + 
                "<strong>Topic</strong>: Centre of Attention<br>" +
                "<strong>Lớp</strong>: 18TCLC_Nhat<br>" + 
                "<strong>Group</strong>: 9<br>" +
                "<strong>Member</strong>:<br>" +
                "&nbsp;&nbsp;Ng&ocirc; Văn Anh Tuấn" +
                "<ul>" +
                "<li><em>Thiết kế v&agrave; code Thuật to&aacute;n</em></li>" +
                "<li><em>Code c&aacute;c thao t&aacute;c database</em></li>" +
                "</ul>" +
                "&nbsp;&nbsp;Trần Đ&igrave;nh Quang Huy" +
                "<ul>" +
                "<li><em>Thiết kế v&agrave; code Giao diện</em></li>" +
                "<li><em>Xử l&yacute; Event cho c&aacute;c n&uacute;t, text area, menu,...</em></li>" +
                "</ul>" +
            "</html>";

        JOptionPane.showMessageDialog(null, message, "About us", JOptionPane.INFORMATION_MESSAGE);
    }
}
