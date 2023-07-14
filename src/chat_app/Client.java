
package chat_app;

import Game.ClientGame;
import Game.ClientGame;
import Game.TicTacToe;
import Messages.FileMessage;
import Messages.ImageMessage;
import Messages.TextMessage;
import Messages.PlayMedia;
import Messages.RecordedMessage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;

public class Client extends javax.swing.JFrame implements Runnable{

    //Khai báo các lớp thực hiện gửi tin nhắn 
    private TextMessage textMess = new TextMessage();
    private ImageMessage imageMess = new ImageMessage();
    private FileMessage fileMess = new FileMessage();
    private PlayMedia media;
    private RecordedMessage recorded;
    
    //Khai báo các biến để kết nối tới Server
    private Socket textServer;      //Kết nối server nhận văn bản
    private Socket imageServer;     //Kết nối server nhận hình ảnh
    private Socket fileServer;      //Kết nối server nhận file
    private Socket audioServer;     //Kết nối server nhận file ghi âm
    private Socket videoServer;     //Kết nối server nhận video
    private Socket gameServer;      //Kết nối server chơi game
    
    //Khai báo các biến nhận và gửi dữ liệu cho Server
    private DataInputStream textIn, pathFileServer;  //Biến nhận dữ liệu văn bản và đường dẫn File
    private ObjectInputStream imageIn;               //Biến nhận dữ liệu hình ảnh
    private DataOutputStream textOut;                //Biến gửi dữ liệu văn bản
    private ObjectOutputStream imageOut;             //Biến gửi dư liệu hình ảnh
    
    //Khai báo các luồng nhận tin nhắn từ Server
    private Thread textThread;      //Luồng nhận văn bản
    private Thread imageThread;     //Luồng nhận hình ảnh
    private Thread fileThread;      //Luồng nhận file
    private Thread audioINThread;   //Luồng nhận ghi âm
    private Thread videoINThread;   //Luồng nhận video
    private Thread gameThread;      //Luồng nhận yêu cầu chơi game
    private Thread t;               //Luồng chạy chương trình chính: Client
            
    private int mouseX,mouseY;//Di chuyển cửa sổ
    
    public Client() {
        initComponents();
        displayMess.setLayout(new MigLayout("fillx","","10[400]10[400]"));
        t = new Thread(Client.this); 
    }
    
    //Lựa chọn item trong ComboBox
    public void selectUser() throws LineUnavailableException{
        String itemSelected = options.getSelectedItem().toString();
        if ( itemSelected.equalsIgnoreCase("images")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showDialog(this, "Open");
            if(result == JFileChooser.APPROVE_OPTION) {
                try {
                    //Hien thi len man hinh cua client
                    File file = fileChooser.getSelectedFile();
                    BufferedImage image = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(image);
                    imageMess.addItemRightImage(displayMess, icon);
                
                    //Gui toi server
                    imageOut = new ObjectOutputStream(imageServer.getOutputStream());
                    imageOut.writeObject(icon);
                    imageOut.flush();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Hình ảnh không hợp lệ","Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if ( itemSelected.equalsIgnoreCase("files")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = fileChooser.getSelectedFile();
                    String pathFile = selectedFile.getAbsolutePath();
                    textOut = new DataOutputStream(fileServer.getOutputStream());
                    //Gui du lieu cho server
                    textOut.writeUTF(pathFile);
                    textOut.flush();
                    //Dua len man hinh cua client
                    fileMess.addFileRight(displayMess, selectedFile.getName(),selectedFile.length()/1000);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
            }
        }
        if ( itemSelected.equalsIgnoreCase("record")){
            recorded = new RecordedMessage();
            recorded.startRecord(displayMess, audioServer);
        }
        
        if ( itemSelected.equalsIgnoreCase("video")){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    String pathFile = file.getAbsolutePath();
                    textOut = new DataOutputStream(videoServer.getOutputStream());
                    //Gui du lieu cho server
                    textOut.writeUTF(pathFile);
                    textOut.flush();
                    //Dua len man hinh cua client
                    media = new PlayMedia();
                    media.displayVideo(displayMess, file, "wrap, right");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        portTF = new javax.swing.JTextField();
        connectBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mess = new javax.swing.JTextPane();
        options = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        sendBtn = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayMess = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(1, 38, 17));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PORT");

        portTF.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        connectBtn.setBackground(new java.awt.Color(255, 255, 255));
        connectBtn.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        connectBtn.setText("CONNECT");
        connectBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        connectBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                connectBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                connectBtnMouseExited(evt);
            }
        });
        connectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectBtnActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-tic-tac-toe-40 (1).png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(connectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(connectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(2, 115, 62));

        jPanel4.setBackground(new java.awt.Color(3, 140, 76));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        mess.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(mess);

        options.setBackground(new java.awt.Color(255, 255, 255));
        options.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FILES", "IMAGES", "RECORD", "VIDEO" }));
        options.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(3, 140, 76));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sendBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-send-50.png")); // NOI18N
        sendBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sendBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(options))
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        displayMess.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout displayMessLayout = new javax.swing.GroupLayout(displayMess);
        displayMess.setLayout(displayMessLayout);
        displayMessLayout.setHorizontalGroup(
            displayMessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 802, Short.MAX_VALUE)
        );
        displayMessLayout.setVerticalGroup(
            displayMessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(displayMess);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(13, 13, 13));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLIENT");

        minimizeBtn.setBackground(new java.awt.Color(255, 255, 255));
        minimizeBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-minimize-30.png")); // NOI18N
        minimizeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeBtnActionPerformed(evt);
            }
        });

        closeBtn.setBackground(new java.awt.Color(255, 255, 255));
        closeBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-30.png")); // NOI18N
        closeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(minimizeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Nút kết nối tới Server
    private void connectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectBtnActionPerformed
        try {
            //Kiểm tra đã nhập port hay  chưa
            if (portTF.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Nhập port để kết nối đến Server","Lỗi",JOptionPane.ERROR_MESSAGE);
            } else{
                try {
                    int myNumber = Integer.parseInt(portTF.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Port không hợp lệ","Lỗi",JOptionPane.ERROR_MESSAGE);
                }
            }
            int port = Integer.parseInt(portTF.getText());
            textServer = new Socket("localhost",port);
            imageServer = new Socket("localhost",1);
            fileServer = new Socket("localhost",2);
            audioServer = new Socket("localhost",3);
            videoServer = new Socket("localhost",4);
            gameServer = new Socket("localhost",5);
            
            t.start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Server này không tồn tại","Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_connectBtnActionPerformed

    private void connectBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectBtnMouseEntered
        connectBtn.setBackground(new Color(1,38,17));
        connectBtn.setForeground(Color.white);
    }//GEN-LAST:event_connectBtnMouseEntered

    private void connectBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectBtnMouseExited
        connectBtn.setBackground(Color.white);
        connectBtn.setForeground(Color.black);
    }//GEN-LAST:event_connectBtnMouseExited

    //Nút gửi tin nhắn cho Server
    private void sendBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendBtnMouseClicked
        try {
            textOut = new DataOutputStream(textServer.getOutputStream());
            String text = mess.getText();
            textMess.addItemRight(displayMess, text);
            textOut.writeUTF(text);
            textOut.flush();
            mess.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendBtnMouseClicked

    //Nút chọn loại tin nhắn của người dùng
    private void optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsActionPerformed
        try {
            selectUser();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optionsActionPerformed

    //Nút ẩn chương trình
    private void minimizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeBtnActionPerformed
        this.setState(ICONIFIED);
    }//GEN-LAST:event_minimizeBtnActionPerformed

    //Nút đóng chương trình
    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeBtnActionPerformed

    //Di chuyển khung chương trình
    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        int newX = evt.getXOnScreen() - mouseX;
        int newY = evt.getYOnScreen() - mouseY;
        setLocation(newX, newY);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try {
            textOut = new DataOutputStream(gameServer.getOutputStream());
            textOut.writeUTF("Play Game");
            textOut.flush();
            JOptionPane.showMessageDialog(null, "Đang chờ phản hồi từ Server...", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton connectBtn;
    private javax.swing.JPanel displayMess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane mess;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JComboBox<String> options;
    private javax.swing.JTextField portTF;
    private javax.swing.JLabel sendBtn;
    // End of variables declaration//GEN-END:variables


    @Override
    public void run() {
        //Chờ nhận tin nhắn từ server
        textThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (textServer != null) JOptionPane.showMessageDialog(null,"Kết nối thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    while (true) {
                        textIn = new DataInputStream(textServer.getInputStream());
                        String text = textIn.readUTF();
                        textMess.addItemLeft(displayMess,text);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Server đã ngắt kết nối","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        textThread.start();
        
        //Chờ hình ảnh từ Server
        imageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        imageIn = new ObjectInputStream(imageServer.getInputStream());
                        ImageIcon imageIcon = (ImageIcon) imageIn.readObject();
                        imageMess.addItemLeftImage(displayMess, imageIcon);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        imageThread.start();
        
        //Chờ file từ Server
        fileThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        pathFileServer = new DataInputStream(fileServer.getInputStream());
                        String path = pathFileServer.readUTF();
                        File file = new File(path);
                        //In len man hinh ben trai 
                        fileMess.addFileLeft(displayMess, file.getName(),file.getAbsolutePath(), file.length()/1000);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fileThread.start();
        
        //Chạy luồng chờ audio từ server
        audioINThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        pathFileServer = new DataInputStream(audioServer.getInputStream());
                        String path = pathFileServer.readUTF();
                        recorded = new RecordedMessage();
                        recorded.playBackAudio(displayMess, path, "wrap, al left");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        audioINThread.start();
        
        //Chờ video từ Server
        videoINThread = new Thread(new Runnable(){
           @Override
           public void run(){
               try{
                   while(true){
                        pathFileServer = new DataInputStream(videoServer.getInputStream());
                        String path = pathFileServer.readUTF();
                        File file = new File(path);
                        //In len man hinh ben trai 
                        media = new PlayMedia();
                        media.displayVideo(displayMess, file, "wrap, al left");
                   }
               } catch (IOException ex){
                   ex.printStackTrace();
               }
           }
        });
        videoINThread.start();
        
        //Chạy luồng chờ phản hồi chơi game từ Client
        gameThread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    while(true){
                        textIn = new DataInputStream(gameServer.getInputStream());
                        String rep = textIn.readUTF();
                        if (rep.equalsIgnoreCase("yes")){
                            ClientGame clientGame = new ClientGame();
                            clientGame.setVisible(true);                           
                        }
                        if (rep.equalsIgnoreCase("no")){
                            JOptionPane.showMessageDialog(rootPane, "Server từ chối yêu cầu của bạn");
                        }
                        if (rep.equalsIgnoreCase("play game")){
                            int choice = JOptionPane.showConfirmDialog(rootPane, "Server đang mời bạn chơi game...", "Thông báo", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION){
                                textOut = new DataOutputStream(gameServer.getOutputStream());
                                textOut.writeUTF("yes");
                                textOut.flush();
                                ClientGame clientGame = new ClientGame();
                                clientGame.setVisible(true);
                            } else {
                                textOut = new DataOutputStream(gameServer.getOutputStream());
                                textOut.writeUTF("no");
                                textOut.flush();
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        gameThread.start();
    }
}
