package main.gui.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryButtonEvent implements ActionListener {
    public main.gui.Controller.Frame obj;
    public JTable History_table = new JTable();
    public JScrollPane History_Scroll = new JScrollPane(History_table);

    public HistoryButtonEvent(main.gui.Controller.Frame frame) {
        this.obj = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        JButton Delete = new JButton("Delete");
        JButton Export_Data = new JButton("Export Data");
        JButton Save = new JButton("Save Database");
        pnl_South.add(Clone_Input);
        pnl_South.add(Delete);
        pnl_South.add(Export_Data);
        pnl_South.add(Save);
        History_frame.add(pnl_South, BorderLayout.SOUTH);

        History_frame.add(History_Scroll,BorderLayout.CENTER);
        History_Scroll.setViewportView(History_table);

        History_frame.setModalityType(ModalityType.APPLICATION_MODAL);
        History_frame.setVisible(true);
    }
}
