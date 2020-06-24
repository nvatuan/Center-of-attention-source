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
import java.sql.Blob;
import java.sql.PreparedStatement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.algo.ImageCentralPixels;
import main.database.Ultility;

public class HistoryButtonEvent implements ActionListener {
    public main.gui.Controller.Frame obj;
    public JTable History_table = new JTable();
    public JScrollPane History_scroll = new JScrollPane(History_table);
    
    public SqlHandler handler = null;

    public HistoryButtonEvent(main.gui.Controller.Frame frame) {
        this.obj = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        History_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JDialog History_frame = new JDialog();
        History_frame.setTitle("History");
        History_frame.setSize(550,300);
        History_frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        History_frame.setLocationRelativeTo(null);
        History_frame.setLayout(new BorderLayout(10,10));

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
        // -- event
        Clone_Input.addActionListener(new SqlHandler(obj, History_table));
        Delete.addActionListener(new SqlHandler(obj, History_table));
        Save.addActionListener(new SqlHandler(obj, History_table));

        pnl_South.add(Clone_Input);
        pnl_South.add(Delete);
        //pnl_South.add(Export_Data);
        pnl_South.add(Save);
        History_frame.add(pnl_South, BorderLayout.SOUTH);

        History_frame.add(History_scroll,BorderLayout.CENTER);
        History_scroll.setViewportView(History_table);

        handler = new SqlHandler(obj, History_table);
        handler.Show();

        History_frame.setModalityType(ModalityType.APPLICATION_MODAL);
        History_frame.setVisible(true);
    }
}
