import javax.swing.*;
import java.awt.*;

public class input_window extends JFrame {
    public static void main(String[] args) {
        new input_window();
    }
    public input_window()
    {
        this.setTitle("Input");
        this.setSize(400,300);
        this.setLayout(new GridLayout(4,1,4,4));

        //Khai báo component cần thiết
        JLabel lb_row, lb_col, lb_TieuDe;
        JTextField jt_row, jt_col;
        JButton Confirm;
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(3,2,4,4));
        //set layout
        lb_TieuDe = new JLabel("Nhap du lieu dau vao");
        lb_TieuDe.setFont(new Font("Arial", Font.BOLD,30));
        lb_TieuDe.setHorizontalAlignment(JTextField.CENTER);
        this.add(lb_TieuDe);

        this.add(pnl);
        lb_row = new JLabel("Row");
        jt_row = new JTextField();
        pnl.add(lb_row);
        pnl.add(jt_row);

        lb_col = new JLabel("Colunm");
        jt_col = new JTextField();
        pnl.add(lb_col);
        pnl.add(jt_col);

        Confirm = new JButton("Confirm");
        Confirm.setBounds(200,250,100,50);
        pnl.add(Confirm);





        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
