package main.gui.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Blob;
import java.sql.PreparedStatement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.algo.ImageCentralPixels;
import main.database.Util;

public class HistoryButtonEvent implements ActionListener {
    public main.gui.Controller.Frame obj;
    public JTable historyTable = new JTable();
    public JScrollPane historyScroll = new JScrollPane(historyTable);
    
    public SqlHandler handler = null;

    public HistoryButtonEvent(main.gui.Controller.Frame frame) {
        this.obj = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JDialog historyFrame = new JDialog();
        historyFrame.setTitle("History");
        historyFrame.setSize(550,300);
        historyFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        historyFrame.setLocationRelativeTo(null);
        historyFrame.setLayout(new BorderLayout(10,10));

        JPanel pnl_South = new JPanel();
        pnl_South.setLayout(new GridLayout(1,4,10,10));
        pnl_South.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton Clone_Input = new JButton("Clone Input");
        Clone_Input.setActionCommand("Clone");
        JButton Delete = new JButton("Delete");
        Delete.setActionCommand("Delete");
        JButton Export_Data = new JButton("Export Data");
        Export_Data.setActionCommand("Export");
        JButton Save = new JButton("Save Current");
        Save.setActionCommand("Save");

        // -- event handling
        Clone_Input.addActionListener(new SqlHandler(obj, historyTable));
        Delete.addActionListener(new SqlHandler(obj, historyTable));
        Save.addActionListener(new SqlHandler(obj, historyTable));

        pnl_South.add(Clone_Input);
        pnl_South.add(Delete);
        //pnl_South.add(Export_Data);
        pnl_South.add(Save);
        historyFrame.add(pnl_South, BorderLayout.SOUTH);

        historyFrame.add(historyScroll,BorderLayout.CENTER);
        historyScroll.setViewportView(historyTable);

        handler = new SqlHandler(obj, historyTable);
        try {
            handler.Show();

            historyFrame.setModalityType(ModalityType.APPLICATION_MODAL);
            historyFrame.setVisible(true);
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi kết nối đến MySQL database. Thông tin thêm:\n[" + sqlex.getMessage() + "]", "ERROR: Lỗi kết nối đến MySQL DB", JOptionPane.ERROR_MESSAGE);
            historyFrame.dispose();
        } catch (Exception ex) {
            historyFrame.dispose();
        }
    }
}
