package Messages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileMessage {

    public FileMessage() {
        super();
    }
    
    //Hiển thị file gửi
    public void addFileRight(JPanel jPanel,String text, float size){
        //Tạo label để chứa icon
        JLabel fileLabel = new JLabel();
        ImageIcon imageIcon;
        if (text.contains(".docx")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-microsoft-word-25.png");
        else
            if(text.contains(".xlsx")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-microsoft-excel-25.png");
            else 
                if (text.contains(".pptx")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-microsoft-powerpoint-25.png");
                else 
                    if (text.contains(".txt")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-txt-25.png");
                    else
                        if (text.contains(".pdf")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-pdf-25.png");
                        else imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-file-25.png");
        fileLabel.setIcon(imageIcon);
        fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //Tạo label để chứa tên file
        JLabel nameFile = new JLabel(text);
        nameFile.setForeground(Color.black);
        nameFile.setFont(new Font("Arial",Font.BOLD,14));
        nameFile.setHorizontalAlignment(SwingConstants.CENTER);
        //Tạo Label để chứa kích thước file
        JLabel sizeFile = new JLabel("Size: "+String.valueOf(size) + "KB");
        sizeFile.setForeground(Color.white);
        sizeFile.setFont(new Font("Arial",Font.BOLD,14));
        //Tạo Panel để chứa icon và tên file
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(250,50));
        panel.add(fileLabel,BorderLayout.WEST);
        panel.add(nameFile,BorderLayout.CENTER);
        panel.add(sizeFile,BorderLayout.SOUTH);
        panel.setBackground(new Color(204,192,152));
        
        jPanel.add(panel, "wrap, al right");
        jPanel.repaint();
        jPanel.revalidate();
    }
    
    //Hiển thị file nhận
    public void addFileLeft(JPanel jPanel, String text, String path, float size){
        //Tạo label để chứa icon
        JLabel fileLabel = new JLabel();
        ImageIcon imageIcon;
        if (text.contains(".docx")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-microsoft-word-25.png");
        else
            if(text.contains(".xlsx")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-microsoft-excel-25.png");
            else 
                if (text.contains(".pptx")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-microsoft-powerpoint-25.png");
                else 
                    if (text.contains(".txt")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-txt-25.png");
                    else
                        if (text.contains(".pdf")) imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-pdf-25.png");
                        else imageIcon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-file-25.png");
        fileLabel.setIcon(imageIcon);
         //Tạo label để chứa tên file
        JLabel nameFile = new JLabel(text);
        nameFile.setForeground(Color.black);
        nameFile.setFont(new Font("Arial",Font.BOLD,14));
        nameFile.setHorizontalAlignment(SwingConstants.CENTER);
        //Tạo Label để chứa kích thước file
        JLabel sizeFile = new JLabel("Size: "+String.valueOf(size) + "KB");
        sizeFile.setForeground(Color.white);
        sizeFile.setFont(new Font("Arial",Font.BOLD,14));
        //Tạo nút để save file
        JButton saveFile = new JButton();
        saveFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon icon = new ImageIcon("D:\\Netbeans\\Chat_App\\src\\Icons\\icons8-download-25.png");
        saveFile.setIcon(icon);
        saveFile.setBackground(Color.white);
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }

            private void saveFileActionPerformed(ActionEvent evt) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter textFilter = new FileNameExtensionFilter("*.txt", ".txt");
                FileNameExtensionFilter docxFilter = new FileNameExtensionFilter("*.docx", ".docx");
                FileNameExtensionFilter xlsxFilter = new FileNameExtensionFilter("*.xlsx", ".xlsx");
                FileNameExtensionFilter pptxFilter = new FileNameExtensionFilter("*.pptx", ".pptx");
                FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("*.pdf", ".pdf");
                
                fileChooser.setFileFilter(textFilter);
                fileChooser.setFileFilter(docxFilter);
                fileChooser.setFileFilter(xlsxFilter);
                fileChooser.setFileFilter(pptxFilter);
                fileChooser.setFileFilter(pdfFilter);
                
                File file = new File(path);
                Path sourcepath =  file.toPath();
                fileChooser.setDialogTitle("Save As");
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File destinationFile = fileChooser.getSelectedFile();
                    if (text.endsWith(".docx")){
                        destinationFile = new File(destinationFile.getParentFile(), destinationFile.getName() + ".docx");
                    } else
                        if (text.endsWith(".xlsx")){
                            destinationFile = new File(destinationFile.getParentFile(), destinationFile.getName() + ".xlsx");
                        } else
                            if (text.endsWith(".txt")){
                                destinationFile = new File(destinationFile.getParentFile(), destinationFile.getName() + ".txt");
                            } else
                                if (text.endsWith(".pptx")){
                                    destinationFile = new File(destinationFile.getParentFile(), destinationFile.getName() + ".pptx");
                                } else 
                                    if (text.endsWith(".pdf")){
                                        destinationFile = new File(destinationFile.getParentFile(), destinationFile.getName() + ".pdf");
                                    }
                                    
                    Path destinationPath = destinationFile.toPath();
                    try{
                        Files.copy(sourcepath,destinationPath);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        //Tạo Panel để chứa icon và tên file
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(250,50));
        panel.add(fileLabel,BorderLayout.WEST);
        panel.add(nameFile,BorderLayout.CENTER);
        panel.add(saveFile,BorderLayout.EAST);
        panel.add(sizeFile,BorderLayout.SOUTH);
        panel.setBackground(new Color(204,192,152));
        
        jPanel.add(panel, "wrap, al left");
        jPanel.repaint();
        jPanel.revalidate();
        
    }
}
