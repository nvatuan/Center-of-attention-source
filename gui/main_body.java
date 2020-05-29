import ComponentDesign.Button;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_body extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new main_body();
    }
    public main_body()
    {
        int W = 700, H = 650;
        this.setTitle("Demo");
        this.setSize(W,H);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10,15));

        //phần thiết kế menu_bar
        JMenuBar MenuBar = new JMenuBar();
        this.setJMenuBar(MenuBar);

        JMenu Menu = new JMenu("Help");
        JMenuItem Item_AboutUs = new JMenuItem("About us");
        JPanel pnl_inform = new JPanel();
        Item_AboutUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(pnl_inform, "----Create by Anh Tuan va Quang Huy----","About", JOptionPane.PLAIN_MESSAGE);
            }

        });
        Menu.add(Item_AboutUs);
        MenuBar.add(Menu);

        /////////////// 西側 ///////////////
        //phần thiết kế Panel_button
        JPanel pnl_button = new JPanel();
        this.add(BorderLayout.WEST, pnl_button);
        pnl_button.setLayout(new GridLayout(4,1,0,15));
        JButton Start = new JButton("Start");
        Start.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JButton Input = new JButton("Input");
        Input.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JButton SQL = new JButton("Toggle log screen");
        SQL.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JButton History = new JButton("History");
        History.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        pnl_button.add(Start);
        pnl_button.add(Input);
        pnl_button.add(SQL);
        pnl_button.add(History);

        //Xử lý xự kiện cho từng Button
        ///---đầu tiền là Input
        Input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_window popup = new input_window();
                popup.setVisible(true);
            }
        });

        /////////////// 北側 //////////////
        JPanel pn_kita = new JPanel();
        pn_kita.setLayout(new GridLayout(4,1,0,5));

        JTextField jt_input = new JTextField();
        jt_input.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JTextField jt_output = new JTextField();
        jt_output.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        JLabel lb_input = new JLabel("INPUT");
        lb_input.setHorizontalAlignment(JLabel.CENTER);
        lb_input.setBorder(new EmptyBorder(70,70,70,70));
        lb_input.setFont(new Font("Arial", Font.BOLD,30));
        JLabel lb_output = new JLabel("OUTPUT");
        lb_output.setFont(new Font("Arial", Font.BOLD,30));
        lb_output.setHorizontalAlignment(JLabel.CENTER);
        lb_input.setBorder(new EmptyBorder(70,70,70,70));



        pn_kita.add(lb_input);
        pn_kita.add(jt_input);
        pn_kita.add(lb_output);
        pn_kita.add(jt_output);

        this.add(BorderLayout.EAST,pn_kita);


        /////////////// Center //////////////
        JPanel pnl_center = new JPanel();
        pnl_center.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.add(BorderLayout.CENTER, pnl_center);

        /////////////// 東側 //////////////
        JPanel pnl_higashi = new JPanel();
        this.add(BorderLayout.NORTH,pnl_higashi);


        /////////////// 南側 //////////////
        JPanel pnl_minami = new JPanel();
        this.add(BorderLayout.SOUTH,pnl_minami);





        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

    }
}
