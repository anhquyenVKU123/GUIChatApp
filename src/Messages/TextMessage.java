package Messages;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextMessage {

    public TextMessage() {
        super();
    }
    
    //Hiển thị tin nhắn gửi 
    public void addItemRight(JPanel jPanel, String text) {
        JLabel jLabel1 = new JLabel(text);
        jLabel1.setForeground(Color.black);
        jLabel1.setFont(new Font("Tahoma",Font.PLAIN,18));
        jPanel.add(jLabel1, "wrap, al right");
        jPanel.repaint();
        jPanel.revalidate();
    }
    
    //Hiển thị tin nhắn nhận
    public void addItemLeft(JPanel jPanel, String text){
        JLabel jLabel1 = new JLabel(text);
        jLabel1.setForeground(Color.black);
        jLabel1.setFont(new Font("Tahoma",Font.PLAIN,18));
        jPanel.add(jLabel1, "wrap, al left");
        jPanel.repaint();
        jPanel.revalidate();
    }
}
