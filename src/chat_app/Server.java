
package chat_app;

import Game.ServerGame;
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class Server extends javax.swing.JFrame implements Runnable{
    
    //Khai báo các lớp thực hiện gửi tin nhắn 
    private TextMessage textMess = new TextMessage();       //Tin nhắn văn bản
    private ImageMessage imageMess = new ImageMessage();    //Tin nhắn hình ảnh
    private FileMessage fileMess = new FileMessage();       //Tin nhắn dạng file
    private PlayMedia media;                                //Tin nhắn ghi âm
    private RecordedMessage recorded;                       //Tin nhắn video

    //Khai báo các lớp để nhận tin nhắn từ Client và gửi tin nhắn cho Client
    private Socket textClient = new Socket();   //Tin nhắn từ client
    private Socket imageClient = new Socket();  //Ảnh từ client
    private Socket fileClient = new Socket();   //Nhận file từ client
    private Socket audioClient = new Socket();  //Nhận audio từ client
    private Socket videoClient = new Socket();  //Nhận video từ client
    private Socket gameClient = new Socket();   //Nhận lời phản hồi từ client
    
    //Khai báo các máy chủ nhận tin nhắn từ Client
    private ServerSocket textServer;            //máy chủ nhận tin nhắn
    private ServerSocket imageServer;           //máy chủ nhận hình ảnh
    private ServerSocket fileServer;            //máy chủ nhận file
    private ServerSocket audioServer;           //máy chủ nhận audio
    private ServerSocket videoServer;           //máy chủ nhận video
    private ServerSocket gameServer;            //máy chủ nhận phản hồi chơi game
    
    //Khai báo các biến gửi dữ liệu và nhận dữ liệu
    private DataOutputStream textOut;                   //Lớp gửi tin nhắn
    private DataInputStream textIn, pathFileClient;     //Lớp nhận tin nhắn và đường dẫn file
    private ObjectOutputStream imageOut;                //Lớp gửi hình ảnh  
    private ObjectInputStream imageIn;                  //Lớp nhận hình ảnh
    
    //Khai báo các luồng chạy
    private Thread textINThread;    //Luồng nhận tin nhắn văn bản
    private Thread imageINThread;   //Luồng nhận hình ảnh
    private Thread fileINThread;    //Luồng nhận file
    private Thread audioINThread;   //Luồng nhận ghi âm
    private Thread videoINThread;   //Luồng nhận video
    private Thread gameThread;      //Luồng chạy game
    private Thread t;               //Luồng chạy chương trình chính : Server
    
    
    private int mouseX, mouseY;//Di chuyển cửa sổ
    
    
    
    public Server() {
        initComponents();
        displayMess.setLayout(new MigLayout("fillx","","10[]10[]"));
        t = new Thread(Server.this);
    }
   
    //Lựa chọn item trong ComboBox
    public void selectUser() throws LineUnavailableException{
        String itemSelected = options.getSelectedItem().toString();
        if ( itemSelected.equalsIgnoreCase("images")){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showDialog(this, "Open");
            if(result == JFileChooser.APPROVE_OPTION) {
                try {
                    //Hien thi len man hinh cua server3
                    File file = fileChooser.getSelectedFile();
                    BufferedImage image = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(image);
                    imageMess.addItemRightImage(displayMess, icon);
                
                    //Gui toi client
                    imageOut = new ObjectOutputStream(imageClient.getOutputStream());
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
                    textOut = new DataOutputStream(fileClient.getOutputStream());
                    //Gui du lieu cho client
                    textOut.writeUTF(pathFile);
                    textOut.flush();
                    //Dua len man hinh cua server
                    fileMess.addFileRight(displayMess, selectedFile.getName(),selectedFile.length()/1000);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
            }
        }
        if ( itemSelected.equalsIgnoreCase("record")) {
            recorded = new RecordedMessage();
            recorded.startRecord(displayMess, audioClient);
        }
        
        if(itemSelected.equalsIgnoreCase("video")){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showDialog(this, "Open");
            if(result == JFileChooser.APPROVE_OPTION) {
                try {
                    //Hien thi len man hinh cua server3
                    File file = fileChooser.getSelectedFile();
                    String pathFile = file.getAbsolutePath();
                    textOut = new DataOutputStream(videoClient.getOutputStream());
                    //Gui du lieu cho client
                    textOut.writeUTF(pathFile);
                    textOut.flush();
                    //Hiển thị lên màn hình server
                    media = new PlayMedia();
                    media.displayVideo(displayMess,file,"wrap, al right");
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HeadPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        portTF = new javax.swing.JTextField();
        startBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ChatBody = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mess = new javax.swing.JTextPane();
        options = new javax.swing.JComboBox<>();
        sendPanel = new javax.swing.JPanel();
        sendBtn = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayMess = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        HeadPanel.setBackground(new java.awt.Color(1, 38, 17));
        HeadPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PORT");

        portTF.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N

        startBtn.setBackground(new java.awt.Color(255, 255, 255));
        startBtn.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        startBtn.setText("START");
        startBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startBtnMouseExited(evt);
            }
        });
        startBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBtnActionPerformed(evt);
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

        javax.swing.GroupLayout HeadPanelLayout = new javax.swing.GroupLayout(HeadPanel);
        HeadPanel.setLayout(HeadPanelLayout);
        HeadPanelLayout.setHorizontalGroup(
            HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadPanelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(startBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        HeadPanelLayout.setVerticalGroup(
            HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeadPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(HeadPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(HeadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(portTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ChatBody.setBackground(new java.awt.Color(2, 115, 62));

        dataPanel.setBackground(new java.awt.Color(3, 140, 76));
        dataPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        sendPanel.setBackground(new java.awt.Color(3, 140, 76));
        sendPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sendBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sendBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-send-50.png")); // NOI18N
        sendBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sendPanelLayout = new javax.swing.GroupLayout(sendPanel);
        sendPanel.setLayout(sendPanelLayout);
        sendPanelLayout.setHorizontalGroup(
            sendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sendBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sendPanelLayout.setVerticalGroup(
            sendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addComponent(sendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout ChatBodyLayout = new javax.swing.GroupLayout(ChatBody);
        ChatBody.setLayout(ChatBodyLayout);
        ChatBodyLayout.setHorizontalGroup(
            ChatBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChatBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChatBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ChatBodyLayout.setVerticalGroup(
            ChatBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChatBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titlePanel.setBackground(new java.awt.Color(13, 13, 13));
        titlePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titlePanelMouseDragged(evt);
            }
        });
        titlePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titlePanelMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SERVER");

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

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(minimizeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ChatBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(HeadPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(HeadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ChatBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Nút mở server
    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBtnActionPerformed
        try {
            //Kiểm tra đã nhập port hay  chưa
            if (portTF.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Nhập port cho server","Lỗi",JOptionPane.ERROR_MESSAGE);
            } else{
                try {
                    int myNumber = Integer.parseInt(portTF.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Port không hợp lệ","Lỗi",JOptionPane.ERROR_MESSAGE);
                }
            }
            int port = Integer.parseInt(portTF.getText());
            textServer = new ServerSocket(port);
            imageServer = new ServerSocket(1);
            fileServer = new ServerSocket(2);
            audioServer = new ServerSocket(3);
            videoServer = new ServerSocket(4);
            gameServer =  new ServerSocket(5);
            
            textClient = textServer.accept();
            imageClient = imageServer.accept();
            fileClient = fileServer.accept();
            audioClient = audioServer.accept();
            videoClient = videoServer.accept();
            gameClient = gameServer.accept();
            
            t.start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Port Server này đã tồn tại","Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_startBtnActionPerformed

    private void startBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startBtnMouseEntered
        startBtn.setBackground(new Color(1,38,17));
        startBtn.setForeground(Color.white);
    }//GEN-LAST:event_startBtnMouseEntered

    private void startBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startBtnMouseExited
        startBtn.setBackground(Color.white);
        startBtn.setForeground(Color.black);
    }//GEN-LAST:event_startBtnMouseExited

    //Nút gửi tin nhắn cho client
    private void sendBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendBtnMouseClicked
        try {           
            textOut = new DataOutputStream(textClient.getOutputStream());
            //Gui du lieu cho client
            String text = mess.getText();
            textMess.addItemRight(displayMess, text);
            textOut.writeUTF(text);
            textOut.flush();       
            mess.setText("");
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(rootPane, "Client is disconnected");
        }
    }//GEN-LAST:event_sendBtnMouseClicked

    //Nút chọn chức năng 
    private void optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsActionPerformed
        try {
            selectUser();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optionsActionPerformed

    //Di chuyển khung chat
    private void titlePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlePanelMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_titlePanelMousePressed

    private void titlePanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titlePanelMouseDragged
        int newX = evt.getXOnScreen() - mouseX;
        int newY = evt.getYOnScreen() - mouseY;
        setLocation(newX, newY);
    }//GEN-LAST:event_titlePanelMouseDragged

    //Thiết lập trạng thái ẩn cho khung chat
    private void minimizeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeBtnActionPerformed
        this.setState(ICONIFIED);
    }//GEN-LAST:event_minimizeBtnActionPerformed

    //Thiết lập nút tắt chương trình
    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeBtnActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try {
            textOut = new DataOutputStream(gameClient.getOutputStream());
            textOut.writeUTF("Play Game");
            textOut.flush();
            JOptionPane.showMessageDialog(null, "Đang chờ phản hồi từ Client...", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
            
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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChatBody;
    private javax.swing.JPanel HeadPanel;
    private javax.swing.JButton closeBtn;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JPanel displayMess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane mess;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JComboBox<String> options;
    private javax.swing.JTextField portTF;
    private javax.swing.JLabel sendBtn;
    private javax.swing.JPanel sendPanel;
    private javax.swing.JButton startBtn;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables

    //Viết lại phương thức run();
    @Override
    public void run() {
        //Chạy luồng chờ tin nhắn từ client
        textINThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (textClient != null) JOptionPane.showMessageDialog(null,"Client đã kết nối","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    while (true) {
                        textIn = new DataInputStream(textClient.getInputStream());
                        String text = textIn.readUTF();
                        textMess.addItemLeft(displayMess, text);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Client đã ngắt kết nối","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        textINThread.start();
        
        //Chạy luồng chờ hình ảnh từ client
        imageINThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        imageIn = new ObjectInputStream(imageClient.getInputStream());
                        ImageIcon imageIcon = (ImageIcon) imageIn.readObject();
                        imageMess.addItemLeftImage(displayMess, imageIcon);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        imageINThread.start();
        
        //Chạy luồng chờ file từ client
        fileINThread = new Thread(new Runnable(){
           @Override
           public void run(){
               try{
                   while(true){
                        pathFileClient = new DataInputStream(fileClient.getInputStream());
                        String path = pathFileClient.readUTF();
                        File file = new File(path);
                        //In len man hinh ben trai 
                        fileMess.addFileLeft(displayMess, file.getName(),file.getAbsolutePath(), file.length()/1000);
                   }
               } catch (IOException ex){
                   ex.printStackTrace();
               }
           }
        });
        fileINThread.start();
        
        //Chạy luồng chờ audio từ client
        audioINThread = new Thread(new Runnable(){
           @Override
           public void run(){
               try{
                   while(true){
                        pathFileClient = new DataInputStream(audioClient.getInputStream());
                        String path = pathFileClient.readUTF();
                        //In len man hinh ben trai 
                        recorded = new RecordedMessage();
                        recorded.playBackAudio(displayMess, path, "wrap, al left");
                   }
               } catch (IOException ex){
                   ex.printStackTrace();
               }
           }
        });
        audioINThread.start();
        
        //Chạy luồng chờ file video từ client
        videoINThread = new Thread(new Runnable(){
           @Override
           public void run(){
               try{
                   while(true){
                        pathFileClient = new DataInputStream(videoClient.getInputStream());
                        String path = pathFileClient.readUTF();
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
                        textIn = new DataInputStream(gameClient.getInputStream());
                        String rep = textIn.readUTF();
                        if (rep.equalsIgnoreCase("yes")){
                            ServerGame serverGame = new ServerGame();
                            serverGame.setVisible(true);
                        } 
                        if (rep.equalsIgnoreCase("no")){
                            JOptionPane.showMessageDialog(rootPane, "Client từ chối yêu cầu của bạn");
                        }
                        if (rep.equalsIgnoreCase("play game")){
                            int choice = JOptionPane.showConfirmDialog(rootPane, "Client đang mời bạn chơi game...", "Thông báo", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION){
                                textOut = new DataOutputStream(gameClient.getOutputStream());
                                textOut.writeUTF("yes");
                                textOut.flush();
                                ServerGame serverGame = new ServerGame();
                                serverGame.setVisible(true);
                            } else {
                                textOut = new DataOutputStream(gameClient.getOutputStream());
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
