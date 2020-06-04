package Controller;

import Controller.panel.Menu_bar;
import Controller.panel.panel_center;
import Controller.panel.panel_higashi;
import Controller.panel.panel_nishi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame extends Button  implements thong_so, Menu_bar, panel_nishi, panel_higashi, panel_center {
    public Frame()
    {
        JFrame frame_chinh = new JFrame("Center of Attention");
        frame_chinh.setSize(thong_so.W,thong_so.H);
        frame_chinh.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_chinh.setLocationRelativeTo(null);
        frame_chinh.setLayout(new BorderLayout(10,5));

        //add JMenubar vào trong frame
        frame_chinh.setJMenuBar(add());

        frame_chinh.add(BorderLayout.CENTER, panel_center());
        frame_chinh.add(BorderLayout.WEST, panel_nishi());
        frame_chinh.add(BorderLayout.EAST, panel_higashi() );
//        frame_chinh.pack();
        frame_chinh.setVisible(true);
    }

    @Override
    public JPanel panel_nishi() {
        Button.to_vien();
        JPanel pnl_nishi = new JPanel();
        pnl_nishi.setBorder(BorderFactory.createTitledBorder("Function"));
        pnl_nishi.setBackground(Color.LIGHT_GRAY);
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
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1;
        c.weighty = 0;

        c.ipadx = 10;
        c.ipady = 10;
        c.insets = new Insets(10,0,0,0);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;

        pnl_higashi.add(lb_input,c);


        JLabel lb_question1 = new JLabel("?");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnl_higashi.add(lb_question1,c);

        c.insets = new Insets(0,0,150,0);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipadx = 120;
        c.ipady = 100;
        pnl_higashi.add(tf_input,c);


        c.insets = new Insets(0,0,0,0);
        c.ipadx = 10;
        c.ipady = 10;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        pnl_higashi.add(lb_output,c);


        JLabel lb_question2 = new JLabel("?");
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
        c.ipady = 100;
        pnl_higashi.add(tf_output,c);


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
        pnl_center.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnl_center.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
//        pnl_center.setBackground(Color.red);
        ImageIcon image1 = null;
        try{
//            image1 = new ImageIcon(getClass().getResource("../a1.jpg"));
//            JLabel lb_center = new JLabel(image1);
//            lb_center.setOpaque(true);
//            pnl_center.add(lb_center);
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Lỗi chèn ảnh vào panel center");
            JOptionPane.showMessageDialog(null,"" + (image1 == null));
        }
        return pnl_center;
    }
}
