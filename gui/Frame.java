package Controller;

import Controller.panel.panel_higashi;
import Controller.panel.panel_nishi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame extends Button  implements thong_so, panel_nishi, panel_higashi {
    public Frame()
    {
        JFrame frame_chinh = new JFrame("Center of Attention");
        frame_chinh.setSize(thong_so.W,thong_so.H);
        frame_chinh.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame_chinh.setVisible(true);
        frame_chinh.setLocationRelativeTo(null);
        frame_chinh.setLayout(new BorderLayout(5,5));

        frame_chinh.add(BorderLayout.WEST,panel_nishi());
        frame_chinh.add(BorderLayout.EAST,panel_higashi());

    }

    @Override
    public JPanel panel_nishi() {
        Button.to_vien();
        JPanel pnl_nishi = new JPanel();
        pnl_nishi.setLayout(new GridLayout(5,1,10,10));
        //cách lề panel khỏi khung Frame
        pnl_nishi.setBorder(new EmptyBorder(15,10,10,0));
        pnl_nishi.add(Start);
        pnl_nishi.add(Input);
        pnl_nishi.add(Toogle);
        pnl_nishi.add(History);
        return pnl_nishi;
    }

    @Override
    public JPanel panel_higashi() {
        Label.can_chinh();
        TextField.to_vien();
        JPanel pnl_higashi = new JPanel();
        pnl_higashi.setLayout(new GridLayout(4,1,10,10));
        //cách lề panel khỏi khung Frame
        pnl_higashi.setBorder(new EmptyBorder(15,0,20,10));
//        pnl_higashi.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        pnl_higashi.add(lb_input);
        pnl_higashi.add(jt_input);
        pnl_higashi.add(lb_output);
        pnl_higashi.add(jt_output);

        return pnl_higashi;
    }
}
