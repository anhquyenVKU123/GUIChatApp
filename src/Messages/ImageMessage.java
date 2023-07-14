package Messages;

import chat_app.Server;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageMessage {

    public ImageMessage() {
        super();
    }
    
    //Hiển thị hình ảnh gửi
    public void addItemRightImage(JPanel jPanel, ImageIcon icon){
        JLabel imageLabel = new JLabel();
        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Dimension labelSize = new Dimension(450, 450);
        java.awt.Image imageFit = icon.getImage().getScaledInstance(labelSize.width, labelSize.height, java.awt.Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(imageFit));
        jPanel.add(imageLabel, "wrap, al right");
        jPanel.repaint();
        jPanel.revalidate();
    }
    
    //Hiển thị hình ảnh nhận
    public void addItemLeftImage(JPanel jPanel, ImageIcon icon){
        //Tạo label để chứa hình ảnh
        JLabel imageLabel = new JLabel();
        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Dimension labelSize = new Dimension(450, 450);
        java.awt.Image imageFit = icon.getImage().getScaledInstance(labelSize.width, labelSize.height, java.awt.Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(imageFit));
        
        //Tạo button để save hình ảnh
        JButton download = new JButton();
        download.setBackground(new Color(255,255,255));
        download.setCursor(new Cursor (Cursor.HAND_CURSOR));
        ImageIcon iconBtn = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-download-50.png");
        download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadActionPerformed(evt);
            }

            private void downloadActionPerformed(ActionEvent evt) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
                    try {
                        image = new BufferedImage(imageLabel.getWidth(), imageLabel.getHeight(), BufferedImage.TYPE_INT_RGB);
                        imageLabel.paint(image.getGraphics());
                        ImageIO.write(image, "png", file);
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        //Tùy chỉnh size của icon
        Image img = iconBtn.getImage();
        Image newImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon iconDownload = new ImageIcon(newImg);
        download.setIcon(iconDownload);
        
        //Đưa vào khung chat
        JPanel panelImage = new JPanel();
        panelImage.setBackground(Color.white);
        panelImage.add(imageLabel);
        panelImage.add(download);
        jPanel.add(panelImage, "wrap, al left");
        jPanel.repaint();
        jPanel.revalidate();
    }
}
