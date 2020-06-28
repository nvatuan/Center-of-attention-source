package main.gui.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.gui.Controller.Frame;
import main.gui.Constants.MyFont;

public class ProblemPromptButtonEvent implements ActionListener {
    public Frame obj;
    public JPanel pnl_ProblemPrompt = new JPanel();
    public ProblemPromptButtonEvent(Frame obj)
    {
        this.obj = obj;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog ProblemPromptFrame = new JDialog();
        ProblemPromptFrame.setTitle("Problem's details");
        ProblemPromptFrame.setMinimumSize(new Dimension(600, 500));
        ProblemPromptFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ProblemPromptFrame.setLocationRelativeTo(null);
        ProblemPromptFrame.setLayout(new BorderLayout());

        JTextPane tpPrompt = new JTextPane();
        tpPrompt.setContentType("text/html");
        tpPrompt.setEditable(false);
        tpPrompt.setBackground(UIManager.getColor("Panel.background"));

        // -- problem prompt crafting
        String content = "" +
        "<h1 style=\"text-align: center;\"><strong>CENTRE OF ATTENTION</strong></h1>" +
        "<h2 style=\"text-align: justify;\"><strong>INPUT:</strong></h2>" +
        "<p style=\"text-align: justify;\">Lần lượt l&agrave;, ba số <em>nguy&ecirc;n</em> <strong><em>H, W, K</em></strong> v&agrave; một mảng số nguy&ecirc;n c&oacute; <strong><em>H*W</em> </strong>phần tử l&agrave; <strong><em>P[]</em></strong>.<strong><em> H, W</em></strong> l&agrave; số dương.</p>" +
        "<br><h2 style=\"text-align: justify;\"><strong>OUTPUT:</strong></h2>" +
        "<p style=\"text-align: justify;\">Một mảng số nguy&ecirc;n <strong><em>A[]</em></strong></p>" +
        "<br><h2 style=\"text-align: justify;\"><strong>Y&Ecirc;U CẦU B&Agrave;I TO&Aacute;N:</strong></h2>" +
        "<p style=\"text-align: justify;\">Cho một bức ảnh được biểu diễn bởi mảng <strong><em>P[]</em></strong>, mỗi pixel m&agrave;u của ảnh n&agrave;y l&agrave; một phần tử trong mảng <strong><em>P[]</em></strong>, tương ứng từ tr&aacute;i qua phải rồi từ tr&ecirc;n xuống dưới. Nếu hai pixel c&oacute; m&agrave;u giống nhau th&igrave; sẽ c&oacute; gi&aacute; trị số nguy&ecirc;n trong mảng <em><strong>P[]</strong></em> giống nhau.</p>" +
        "<p style=\"text-align: justify;\">Bức ảnh sẽ c&oacute; số pixel l&agrave; <em><strong>H*W</strong></em>, với <em><strong>W</strong></em> l&agrave; số pixel theo chiều ngang của ảnh v&agrave; <em><strong>H</strong></em> l&agrave; số pixel theo chiều dọc của ảnh.</p>" +
        "<p style=\"text-align: justify;\">Định nghĩa <em>độ_s&acirc;u</em> của một pixel đang x&eacute;t l&agrave; số bước đi (l&ecirc;n, xuống, tr&aacute;i, phải) &iacute;t nhất sao cho đi được đến một &ocirc; pixel kh&aacute;c kh&aacute;c m&agrave;u với &ocirc; pixel đang x&eacute;t hoặc l&agrave; đi ra khỏi bi&ecirc;n của ảnh.<br />H&atilde;y t&igrave;m c&aacute;c chỉ số (<em>index</em>) pixel trong mảng <strong><em>P[]</em></strong> sao cho ch&uacute;ng c&oacute; m&agrave;u l&agrave; <em><strong>K</strong></em> v&agrave; c&oacute; <em>độ_s&acirc;u</em> l&agrave; tối đa.</p>";

        try {
            String promptExample = "" +
            "<br><h2 style=\"text-align: justify;\"><strong>V&Iacute; DỤ:</strong></h2>" +
            "<p style=\"text-align: justify;\">Với v&iacute; dụ b&ecirc;n dưới, với m&agrave;u <strong><em>K</em></strong> l&agrave; m&agrave;u đỏ th&igrave; <em>độ_s&acirc;u</em> tối đa sẽ l&agrave; 3, v&agrave; chỉ c&oacute; một &ocirc; đ&oacute; l&agrave; (3, 2), v&agrave; n&oacute; c&oacute; chỉ số index l&agrave; (3 * W + 2) = 32.</p>" +
            "<p style=\"text-align: justify;\">Với m&agrave;u&nbsp;<em><strong>K</strong></em> l&agrave; m&agrave;u xanh dương th&igrave; độ_s&acirc;u tối đa sẽ l&agrave; 2, tuy nhi&ecirc;n c&oacute; nhiều hơn một &ocirc; c&oacute; độ_s&acirc;u l&agrave; 2.</p>" +
            "<p style=\"text-align: justify;\">Với m&agrave;u<strong>&nbsp;<em>K</em></strong> l&agrave; t&iacute;m hoặc xanh l&aacute; th&igrave; độ_s&acirc;u tối đa sẽ l&agrave; 1, v&agrave; đ&aacute;p &aacute;n sẽ l&agrave; index của tất cả những &ocirc; c&ugrave;ng m&agrave;u.</p>" +
            "<p style=\"text-align: justify;\">&nbsp;</p>";
            
            String imageExample = "<div style=\"text-align: center\"><img src=\"" + getClass().getResource("../../img/problemExample.png").toString()  + "\" /></div>";
            content += promptExample + imageExample;
        } catch (Exception ex) {
            System.out.println("@ProblemPromptButtonEvent: Error!! Cannot read example image file.");
        }
        tpPrompt.setText("<html><body>" + content + "</body></html>");
        tpPrompt.setCaretPosition(0);
        // -- ended Problem prompt crafting..

        JScrollPane jscroll = new JScrollPane(tpPrompt);

        ProblemPromptFrame.add(jscroll, BorderLayout.CENTER);
        ProblemPromptFrame.setModalityType(ModalityType.APPLICATION_MODAL);
        ProblemPromptFrame.setVisible(true);
    }
}
