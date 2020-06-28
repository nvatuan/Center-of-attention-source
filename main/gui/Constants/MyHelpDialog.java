package main.gui.Constants;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import main.gui.Constants.MyFont;

public class MyHelpDialog {
    private static JDialog constructNewDialog(String title) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(400, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        return dialog;
    }

    public static JDialog getHelpInput() {
        JDialog dialog = constructNewDialog("Help: Định dạng INPUT");
        dialog.setLayout(new BorderLayout());

        JPanel container = new JPanel(new BorderLayout());
        JTextArea taDesc = new JTextArea("" +
            "Dữ liệu vào lần lượt sẽ là ba số nguyên Width, Height, Color và ngay sau là một danh sách các số nguyên gồm Width*Height phần tử.\nWidth, Height phải là số nguyên dương." + "\n" +
            "\nMỗi số cách nhau bằng ký tự khoảng trống hoặc là ký tự xuống dòng. Các ký tự khác sẽ khiến thông báo lỗi xảy ra và Chương trình sẽ không thực thi."
        );
        taDesc.setFont(MyFont.Verdana);
        taDesc.setEditable(false);
        taDesc.setBackground(UIManager.getColor("Panel.background"));
        taDesc.setWrapStyleWord(true);
        taDesc.setLineWrap(true);
        taDesc.setHighlighter(null);
        
        JTextArea tfExample = new JTextArea();
        tfExample.setEditable(false);
        tfExample.setText("3 3 1 2 1 2 1 1 1 2 1 2");
        tfExample.setFont(new Font(MyFont.Monospaced.getFamily(), Font.PLAIN, 12));
        tfExample.setBorder(BorderFactory.createTitledBorder("Ví dụ"));

        container.add(taDesc, BorderLayout.CENTER);
        container.add(tfExample, BorderLayout.SOUTH);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --
        JButton bttCopy = new JButton("Copy to Clipboard");
        bttCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection selection = new StringSelection(tfExample.getText());
                clip.setContents(selection, selection);
            }
        });
        bttCopy.setFocusable(false);

        dialog.add(container, BorderLayout.CENTER);
        dialog.add(bttCopy, BorderLayout.SOUTH);
        dialog.setResizable(false);
        return dialog;
    }    

    public static JDialog getHelpOutput() {
        JDialog dialog = constructNewDialog("Help: Định dạng OUTPUT");
        dialog.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JTextArea taDesc = new JTextArea("" +
            "Dữ liệu ra là một danh sách các số nguyên theo thứ tự tăng dần,\n" +
            "có giá trị từ 0 cho đến Width * Height - 1.\n" +
            "Chúng tượng trưng cho chỉ số index trong mảng màu P[] mà có giá trị là Color và có độ_sâu là tối đa, như Bài toán mô tả.\n"
        );
        taDesc.setFont(MyFont.Verdana);
        taDesc.setEditable(false);
        taDesc.setBackground(UIManager.getColor("Panel.background"));
        taDesc.setWrapStyleWord(true);
        taDesc.setLineWrap(true);
        taDesc.setHighlighter(null);

        JTextArea taPairToIndex = new JTextArea();
        taPairToIndex.setEditable(false);
        taPairToIndex.setBorder(BorderFactory.createTitledBorder("Cách để chuyển từ tọa độ X, Y sang index"));
        taPairToIndex.setText("index = X * Width + Y");
        taPairToIndex.setFont(new Font(MyFont.Monospaced.getFamily(), Font.PLAIN, 12));
        taPairToIndex.setPreferredSize(new Dimension(0, 40));
        

        JTextArea taIndexToPair = new JTextArea();
        taIndexToPair.setEditable(false);
        taIndexToPair.setBorder(BorderFactory.createTitledBorder("Cách để chuyển từ index sang tọa độ X, Y"));
        taIndexToPair.setText("X = index / Width (chia lấy phần nguyên)\nY = index % Width (chia lấy phần dư)");
        taIndexToPair.setFont(new Font(MyFont.Monospaced.getFamily(), Font.PLAIN, 12));
        taIndexToPair.setPreferredSize(new Dimension(0, 55));

        container.add(taDesc);
        container.add(Box.createRigidArea(new Dimension(0, 5)));
        container.add(taPairToIndex);
        container.add(Box.createRigidArea(new Dimension(0, 5)));
        container.add(taIndexToPair);

        dialog.add(container, BorderLayout.CENTER);
        dialog.setResizable(false);
        return dialog;
    }
}