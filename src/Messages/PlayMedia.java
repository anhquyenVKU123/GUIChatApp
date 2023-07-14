package Messages;

import chat_app.Server;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayMedia extends JFrame{
    private int mouseX,mouseY;
    private int count = 1;//Đếm số lần nhấp chuột của nút play-pause
    private PlayMedia media;
    
    public PlayMedia() {    
        super();
    }
    
    public Parent convert(MediaView mediaView) {
        Pane container = new Pane();
        container.getChildren().add(mediaView);
        MediaPlayer mediaPlayer = mediaView.getMediaPlayer();
        mediaView.fitWidthProperty().bind(container.widthProperty());
        mediaView.fitHeightProperty().bind(container.heightProperty());
        container.setPrefSize(mediaPlayer.getMedia().getWidth(), mediaPlayer.getMedia().getHeight());
        return container;
    }
    public void playMedia(String pathVideo) {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        minimizeBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        panelBtn = new javax.swing.JPanel();
        stopBtn = new javax.swing.JButton();
        replayBtn = new javax.swing.JButton();
        playpauseBtn = new javax.swing.JButton();
        panelVideo = new javax.swing.JPanel();
        fxPanel = new JFXPanel();
        
        Media media = new Media(new File(pathVideo).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(false);
        mediaPlayer.setOnReady(() -> {
            Dimension dimension = new Dimension((int) mediaPlayer.getMedia().getWidth(), (int) mediaPlayer.getMedia().getHeight());
            fxPanel.setPreferredSize(dimension);
            pack();
        });
        BorderPane borderPane = new BorderPane(mediaView);
        borderPane.setAlignment(mediaView, Pos.CENTER);
        Scene scene = new Scene(borderPane);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1300,800));
        setUndecorated(true);
        
        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }

            private void jPanel1MouseDragged(MouseEvent evt) {
                int newX = evt.getXOnScreen() - mouseX;
                int newY = evt.getYOnScreen() - mouseY;
                setLocation(newX, newY);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }

            private void jPanel1MousePressed(MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VIDEO");

        minimizeBtn.setBackground(new java.awt.Color(255, 255, 255));
        minimizeBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-minimize-30.png")); // NOI18N
        minimizeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeBtnActionPerformed(evt);
            }

            private void minimizeBtnActionPerformed(ActionEvent evt) {
                setState(ICONIFIED);
            }
        });

        closeBtn.setBackground(new java.awt.Color(255, 255, 255));
        closeBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-30.png")); // NOI18N
        closeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }

            private void closeBtnActionPerformed(ActionEvent evt) {
                mediaPlayer.stop();
                setVisible(false);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimizeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(minimizeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtn.setBackground(new java.awt.Color(255, 255, 255));

        stopBtn.setBackground(new java.awt.Color(255, 255, 255));
        stopBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-stop-35.png")); // NOI18N
        stopBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBtnActionPerformed(evt);
            }

            private void stopBtnActionPerformed(ActionEvent evt) {
               mediaPlayer.stop();
               playpauseBtn.setIcon(new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-play-35 (1).png"));
            }
        });

        replayBtn.setBackground(new java.awt.Color(255, 255, 255));
        replayBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-replay-35 (1).png")); // NOI18N
        replayBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        replayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replayBtnActionPerformed(evt);
            }

            private void replayBtnActionPerformed(ActionEvent evt) {
                mediaPlayer.stop();
                mediaPlayer.seek(mediaPlayer.getStartTime());
                mediaPlayer.play();
                playpauseBtn.setIcon(new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-pause-35 (1).png"));
            }
        });

        playpauseBtn.setBackground(new java.awt.Color(255, 255, 255));
        playpauseBtn.setIcon(new javax.swing.ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-play-35 (1).png")); // NOI18N
        playpauseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playpauseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playpauseBtnMouseClicked(evt);
            }

            private void playpauseBtnMouseClicked(MouseEvent evt) {
                fxPanel.setScene(scene);
                count++;
                if (count%2 == 0) {
                    playpauseBtn.setIcon(new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-pause-35 (1).png"));
                    mediaPlayer.play();
                }
                else {
                    playpauseBtn.setIcon(new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-play-35 (1).png"));
                    mediaPlayer.pause();
                }
            }
        });
        playpauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playpauseBtnActionPerformed(evt);
            }

            private void playpauseBtnActionPerformed(ActionEvent evt) {
            }
        });

        panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBtn.add(replayBtn);
        panelBtn.add(playpauseBtn);
        panelBtn.add(stopBtn);

        fxPanel.setBackground(new java.awt.Color(204, 204, 204));
        
        javax.swing.GroupLayout panelVideoLayout = new javax.swing.GroupLayout(fxPanel);
        fxPanel.setLayout(panelVideoLayout);
        panelVideoLayout.setHorizontalGroup(
            panelVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelVideoLayout.setVerticalGroup(
            panelVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(fxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setUI();
        setVisible(true);
    }
    
    public void setUI() {
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
    }
    
    //Hiển thị thông tin video
    public void displayVideo(JPanel jPanel, File file, String postion){
        //Tạo label để chứa icon
        JLabel fileLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-video-35.png");
        fileLabel.setIcon(imageIcon);
        fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                media = new PlayMedia();
                media.playMedia(file.getAbsolutePath());
            }           
        });
        //Tạo label để chứa tên file
        JLabel name = new JLabel(file.getName());
        name.setForeground(Color.black);
        name.setFont(new Font("Arial",Font.BOLD,14));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        //Tạo Label để chứa kích thước file
        JLabel sizeFile = new JLabel("Size: "+String.valueOf(file.length()/1000) + "KB");
        sizeFile.setForeground(Color.white);
        sizeFile.setFont(new Font("Arial",Font.BOLD,14));
        //Tạo Panel để chứa icon và tên file
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(250,50));
        panel.add(fileLabel,BorderLayout.WEST);
        panel.add(name,BorderLayout.CENTER);
        panel.add(sizeFile,BorderLayout.SOUTH);
        panel.setBackground(new Color(204,192,152));
        
        jPanel.add(panel, postion);
        jPanel.repaint();
        jPanel.revalidate();
    }
    
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton minimizeBtn;
    private javax.swing.JPanel panelBtn;
    private javax.swing.JPanel panelVideo;
    private javax.swing.JButton playpauseBtn;
    private javax.swing.JButton replayBtn;
    private javax.swing.JButton stopBtn;
    private JFXPanel fxPanel;
}
