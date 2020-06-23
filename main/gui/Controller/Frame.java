package main.gui.Controller;

import main.gui.Controller.panel.Menu_bar;
import main.gui.Controller.panel.panel_center;
import main.gui.Controller.panel.panel_higashi;
import main.gui.Controller.panel.panel_nishi;

import javax.swing.*;
import java.awt.*;

import main.gui.Constants.MyHeightWidth;
import main.gui.Event.HistoryButtonEvent;
import main.gui.Event.ReformatButtonEvent;

public class Frame extends Button implements Menu_bar, panel_nishi, panel_higashi, panel_center {
    public static JFrame frame_chinh = new JFrame("Center of Attention");
    public Frame()
    {
        frame_chinh.setSize(MyHeightWidth.W,MyHeightWidth.H);
        frame_chinh.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_chinh.setLocationRelativeTo(null);
        frame_chinh.setLayout(new BorderLayout(10,5));
        
        // -- add JMenubar vào trong frame
        frame_chinh.setJMenuBar(add());

        frame_chinh.add(BorderLayout.CENTER, panel_center());
        frame_chinh.add(BorderLayout.WEST, panel_nishi());
        frame_chinh.add(BorderLayout.EAST, panel_higashi() );
//      frame_chinh.pack();
        frame_chinh.setVisible(true);
    }

    @Override
    public JPanel panel_nishi() {
        Button.to_vien();
        JPanel pnl_nishi = new JPanel();
        pnl_nishi.setBorder(BorderFactory.createTitledBorder("Function"));
        pnl_nishi.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 15;
        c.ipadx = 30;
        c.insets = new Insets(20,10,10,10);
        //
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        pnl_nishi.add(Start,c);
        //
        c.gridx = 0;
        c.gridy = 1;
        pnl_nishi.add(Log,c);
        //
        c.gridx = 0;
        c.gridy = 2;
        pnl_nishi.add(History,c);
        HistoryButtonEvent obj = new HistoryButtonEvent(this);
        History.addActionListener(obj);
        //
        JButton About = new JButton("About us");
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 0;
        c.gridy = 3;
        pnl_nishi.add(About,c);

        return pnl_nishi;
    }

    @Override
    public JPanel panel_higashi() {
        Label.can_chinh();
        TextField.to_vien();
        JPanel pnl_higashi = new JPanel();
        pnl_higashi.setLayout(new GridBagLayout());
        pnl_higashi.setBorder(BorderFactory.createTitledBorder("Input & Output"));

        GridBagConstraints c = new GridBagConstraints();

        c.ipadx = 10;
        c.ipady = 10;
        c.insets = new Insets(10,10,0,10);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;

        pnl_higashi.add(lb_input,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnl_higashi.add(lb_question1,c);

        c.insets = new Insets(0,10,120,10);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipadx = 120;
        c.ipady = 130;
        pnl_higashi.add(ta_input,c);

        c.insets = new Insets(0,10,10,10);
        c.ipadx = 10;
        c.ipady = 10;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnl_higashi.add(lb_output,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnl_higashi.add(lb_question2,c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipadx = 120;
        c.ipady = 130;
        pnl_higashi.add(ta_output,c);

        c.insets = new Insets(5,10,10,10);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        ReformatButtonEvent RF_Event = new ReformatButtonEvent(this);
        Reformat.addActionListener(RF_Event);
        pnl_higashi.add(Reformat, c);

        return pnl_higashi;
    }

    @Override
    public JMenuBar add() {
        JMenuBar Menu_bar = new JMenuBar();
        Menu_bar.add(Menu);
        Menu.add(Item_AboutUs);
        return Menu_bar;
    }

    @Override
    public JPanel panel_center() {
        JPanel pnl_center = new JPanel();
        JPanel pnl_center_temp = new JPanel();
        pnl_center_temp.setLayout(new BorderLayout());
        pnl_center_temp.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        pnl_center.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        pnl_center_temp.add(pnl_center,BorderLayout.CENTER);
//        pnl_center.setBackground(Color.red);
//        ImageIcon image1 = null;
//        try{
//            image1 = new ImageIcon(getClass().getResource("../a1.jpg"));
//            JLabel lb_center = new JLabel(image1);
//            lb_center.setOpaque(true);
//            pnl_center.add(lb_center);
//        }catch (Exception e)
//        {
//            JOptionPane.showMessageDialog(null,"Lỗi chèn ảnh vào panel center");
//            JOptionPane.showMessageDialog(null,"" + (image1 == null));
//        }
        return pnl_center_temp;
    }
}
