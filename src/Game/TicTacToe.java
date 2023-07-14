
package Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TicTacToe extends javax.swing.JFrame implements Runnable{

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    int turn = 1;
    boolean yourTurn = true;
    char matrix[][] = {
        {'1','1','1'},
        {'1','1','1'},
        {'1','1','1'},
    };
    private int mouseX, mouseY;//Di chuyển cửa sổ
    
    public TicTacToe(Socket s) {
        try {
            this.socket = s;
            try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
            initComponents();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return socket;
    }
    
    

    public void setButton(JButton Btn, String path){
         Btn.setText("");
         Btn.setIcon(new ImageIcon(path));
         Btn.setEnabled(false);
    }
    
   public void printMatrix(){
       for (int i = 0; i < 3; i++){
           for (int j = 0; j < 3; j ++)
               System.out.print(matrix[i][j] + "\t");
           System.out.println();
       }
   }
   
   public int checkWin(char key){
       if (matrix[0][0] == key && matrix[1][0] == key && matrix[2][0] == key) return 1;
       if (matrix[0][1] == key && matrix[1][1] == key && matrix[2][1] == key) return 2;
       if (matrix[0][2] == key && matrix[1][2] == key && matrix[2][2] == key) return 3;
       
       if (matrix[0][0] == key && matrix[0][1] == key && matrix[0][2] == key) return 4;
       if (matrix[1][0] == key && matrix[1][1] == key && matrix[1][2] == key) return 5;
       if (matrix[2][0] == key && matrix[2][1] == key && matrix[2][2] == key) return 6;
       
       if (matrix[0][0] == key && matrix[1][1] == key && matrix[2][2] == key) return 7;
       if (matrix[0][2] == key && matrix[1][1] == key && matrix[2][0] == key) return 8;
       
       return 0;
   }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new java.awt.GridLayout(3, 3));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("1");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("2");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("3");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setText("4");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setText("5");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setText("6");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setText("7");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setText("8");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setText("9");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TIC TAC TOE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("1");
            out.flush();
            
                if(jButton1.getText().equals("1")){
                    if (turn == 1) {
                        setButton(jButton1,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix[0][0] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton1,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix[0][0] = 'o';                   
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("2");
            out.flush();
            
                if(jButton2.getText().equals("2")){
                    if (turn == 1) {
                        setButton(jButton2,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix[0][1] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton2,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix[0][1] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("3");
            out.flush();
            
                if(jButton3.getText().equals("3")){
                    if (turn == 1) {
                        setButton(jButton3,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix [0][2] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton3,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix[0][2] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("4");
            out.flush();
            
                if(jButton4.getText().equals("4")){
                    if (turn == 1) {
                        setButton(jButton4,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix [1][0] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton4,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix [1][0] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
             if (yourTurn == true){
            out.writeUTF("5");
            out.flush();
           
                if(jButton5.getText().equals("5")){
                    if (turn == 1) {
                        setButton(jButton5,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix [1][1] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton5,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix [1][1] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("6");
            out.flush();
            
                if(jButton6.getText().equals("6")){
                    if (turn == 1) {
                        setButton(jButton6,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix [1][2] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton6,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix [1][2] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("7");
            out.flush();
            
                if(jButton7.getText().equals("7")){
                    if (turn == 1) {
                        setButton(jButton7,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix [2][0] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton7,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix [2][0] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("8");
            out.flush();
           
                if(jButton8.getText().equals("8")){
                    if (turn == 1) {
                        setButton(jButton8,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix [2][1] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton8,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix [2][1] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            if (yourTurn == true){
            out.writeUTF("9");
            out.flush();
            
                if(jButton9.getText().equals("9")){
                    if (turn == 1) {
                        setButton(jButton9,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = false;
                        turn = 2;
                        matrix [2][2] = 'x';
                        if (checkWin('x') <= 8 && checkWin('x') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                    else {
                        setButton(jButton9,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = false;
                        turn = 1;
                        matrix [2][2] = 'o';
                        if (checkWin('o') <= 8 && checkWin('o') >= 1) {
                            JOptionPane.showMessageDialog(rootPane, "Ban da chien thang");
                            out.writeUTF("lose");
                            out.flush();
                            this.setVisible(false);
                        }
                    }
                
                }
            } else{
                JOptionPane.showMessageDialog(rootPane, "Chưa đến lượt của bạn");
            } 
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int newX = evt.getXOnScreen() - mouseX;
        int newY = evt.getYOnScreen() - mouseY;
        setLocation(newX, newY);
    }//GEN-LAST:event_jPanel2MouseDragged

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
////                new TicTacToe().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true){
            try {
                String mess = in.readUTF();
                System.out.println(mess);
                if (mess.equals("1")) {
                    if (turn == 1){
                        setButton(jButton1,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[0][0] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton1,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[0][0] = '0';
                        turn = 1;
                    }
                } 
                
                if (mess.equals("2")) {
                    if (turn == 1){
                        setButton(jButton2,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[0][1] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton2,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[0][1] = 'o';
                        turn = 1;
                    }
                } 
                
                if (mess.equals("3")) {
                    if (turn == 1){
                        setButton(jButton3,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[0][2] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton3,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[0][2] = 'o';
                        turn = 1;
                    }
                }
                
                if (mess.equals("4")) {
                    if (turn == 1){
                        setButton(jButton4,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[1][0] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton4,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[1][0] = 'o';
                        turn = 1;
                    }
                }
                
                if (mess.equals("5")) {
                    if (turn == 1){
                        setButton(jButton5,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[1][1] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton5,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[1][1] = 'o';
                        turn = 1;
                    }
                }
                
                if (mess.equals("6")) {
                    if (turn == 1){
                        setButton(jButton6,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[1][2] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton6,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[1][2] = 'o';
                        turn = 1;
                    }
                }
                
                if (mess.equals("7")) {
                    if (turn == 1){
                        setButton(jButton7,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[2][0] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton7,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[2][0] = 'o';
                        turn = 1;
                    }
                }
                
                if (mess.equals("8")) {
                    if (turn == 1){
                        setButton(jButton8,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[2][1] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton8,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[2][1] = 'o';
                        turn = 1;
                    }
                }
                
                if (mess.equals("9")) {
                    if (turn == 1){
                        setButton(jButton9,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-close-60.png");
                        yourTurn = true;
                        matrix[2][2] = 'x';
                        turn = 2;
                    } else{
                        setButton(jButton9,"D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-circle-60.png");
                        yourTurn = true;
                        matrix[2][2] = 'o';
                        turn = 1;
                    }
                }
               
                if (mess.equalsIgnoreCase("lose")){
                    JOptionPane.showMessageDialog(rootPane, "Ban da thua cuoc");
                    socket.close();
                    this.setVisible(false);
                }
            } catch (IOException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }
}
