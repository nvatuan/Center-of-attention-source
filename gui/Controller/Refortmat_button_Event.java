package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Refortmat_button_Event implements ActionListener {
    public Frame obj;
    public Refortmat_button_Event(Frame obj)
    {
        this.obj = obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        JOptionPane.showMessageDialog(null, obj.ta_input.getText());
        String str = obj.ta_input.getText();
        String[] temp = str.split("\\s");
        String arr = "";
        int n = 0, m = 0, k = 0;
        int count = 0;
        int[] p = null;
        try {
            n = Integer.parseInt(temp[0]); count++;
            m = Integer.parseInt(temp[1]); count++;
            k = Integer.parseInt(temp[2]); count++;
            p = new int[n*m];
            for(int i = 0; i < n*m ; i++)
            {
                p[i] = Integer.parseInt(temp[3+i]);
            }
            for(int i = n*m ; i < temp.length ; i++)
            {
                temp[n*m] = "0";
            }
        }catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null,"Format error in" + count + "line");
        }
        arr = "" + n + " " + m + "\n" + k +"\n";
        for (int c = 0, i = 0; i < n*m; i++) {
            arr += p[i];
            if (c == n-1) {
                arr += "\n";
                c = 0;
            } else {
                arr += " ";
                c++;
            }
        }
        obj.ta_input.setText(arr);
    }
}
