package Messages;

import chat_app.Server;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class RecordedMessage {

    private Thread audioThread;
    private long startTime = 0;
    
    public RecordedMessage() {
        super();
    }
    //Trình phát audio
    public void playBackAudio(JPanel jPanel, String path, String postion){
        AudioInputStream audioStream = null;
        try {
            //Bộ đếm thời gian
            JLabel timeLabel = new JLabel("00:00:00");
            Timer timer = new Timer(1000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    timeLabel.setText(getFormattedTime(elapsedTime));
                }

                private String getFormattedTime(long elapsedTime) {
                    int hours = (int) (elapsedTime / (1000 * 60 * 60));
                    int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
                    int seconds = (int) ((elapsedTime / 1000) % 60);
                    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
                }
                
            });
            //File phát audio
            File file = new File(path);
            audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            //Một container để chứa nút Play, nút Stop và JLabel (đếm thời gian)
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1,3,5,5));
            panel.setBackground(Color.white);
            JButton playBtn = new JButton();
            JButton stopBtn = new JButton();
            //Nút play
            playBtn.setIcon(new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-play-30.png"));
            playBtn.setBackground(Color.white);
            playBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            playBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    playAudio(e);
                }

                private void playAudio(ActionEvent e) {
                        clip.setFramePosition(0);
                        clip.start();   
                        startTime = System.currentTimeMillis();
                        timer.start();
                        clip.addLineListener(new LineListener() {
                            @Override
                            public void update(LineEvent event) {
                                if (event.getType() == LineEvent.Type.STOP) {
                                    timer.stop();
                                }
                            }
                        });
                }
                
            });
            //Nút Stop
            stopBtn.setIcon(new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\stopPlayback.png"));
            stopBtn.setBackground(Color.white);
            stopBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            stopBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    stopAudio(e);
                }

                private void stopAudio(ActionEvent e) {
                    clip.stop();
                    timer.stop();
                }
                
            });
            
            //Label thời gian
            timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            //Thêm vào Panel
            panel.add(playBtn);
            panel.add(stopBtn);
            panel.add(timeLabel);
            //Thêm Panel vào khung chat
            jPanel.add(panel,postion);
            jPanel.updateUI();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audioStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void startRecord(JPanel jPanel ,Socket audioClient){
        try {
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
         
            TargetDataLine targetDataLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
            targetDataLine.open();
            File outputFile = new File("E:\\recordedAudio.wav");
            
            JFrame frameRecord = new JFrame();
            frameRecord.setTitle("RECORD");
            frameRecord.setSize(270, 80);
            frameRecord.setLayout(new GridLayout(1,4,5,5));
            
            JLabel timerLabel = new JLabel("00:00:00");
            timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            Timer timer = new Timer(1000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    timerLabel.setText(getFormattedTime(elapsedTime));
                }

                private String getFormattedTime(long elapsedTime) {
                    int hours = (int) (elapsedTime / (1000 * 60 * 60));
                    int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);
                    int seconds = (int) ((elapsedTime / 1000) % 60);
                    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
                }
                
            });
            
            JButton startRecord = new JButton("Start");
            startRecord.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    startRecordActionPerformed(evt);
                }
                
                private void startRecordActionPerformed(ActionEvent evt) { 
                    if (audioThread != null && audioThread.isAlive()) {
                        audioThread.interrupt();
                    }
                    audioThread = new Thread(){
                        public void run(){
                            try {
                                targetDataLine.open(audioFormat);
                                targetDataLine.start();

                                // Create an audio input stream to read from the target data line
                                AudioInputStream inputStream = new AudioInputStream(targetDataLine);

                                // Write the audio data to the file
                                AudioSystem.write(inputStream, AudioFileFormat.Type.WAVE, outputFile);

                                inputStream.close();
                                targetDataLine.stop();
                                targetDataLine.close();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            } catch (LineUnavailableException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }      
                    };
                    audioThread.start();
                    startTime = System.currentTimeMillis();
                    timer.start();
                }
            });
            JButton stopRecord = new JButton();
            stopRecord.setIcon(new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-stop-30.png"));
            stopRecord.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    stopRecordActionPerformed(evt);
                }

                private void stopRecordActionPerformed(ActionEvent evt) {
                    targetDataLine.stop();
                    targetDataLine.close();
                    timer.stop();
                }
            });
            
            JButton sendAudio = new JButton("Send");
            sendAudio.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sendAudioActionPerformed(evt);
                }

                private void sendAudioActionPerformed(ActionEvent evt) {
                    try {
                        frameRecord.setVisible(false);
                        DataOutputStream textOut = new DataOutputStream(audioClient.getOutputStream());
                        //Gui du lieu cho client
                        textOut.writeUTF(outputFile.getAbsolutePath());
                        textOut.flush();
                        playBackAudio(jPanel, outputFile.getAbsolutePath(),"wrap, al right");
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            frameRecord.add(startRecord);
            frameRecord.add(timerLabel);
            frameRecord.add(stopRecord);
            frameRecord.add(sendAudio);
            frameRecord.setVisible(true);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(RecordedMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
