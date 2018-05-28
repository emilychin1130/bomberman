import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;

public class Bomberman {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   protected static int size = 700;

//  private JLabel test;

   public Bomberman(){
      prepareGUI();
   }

   public static void main(String[] args){
      Bomberman Bomberman = new Bomberman();  
      Bomberman.showEvent();       
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Bomberman");
      mainFrame.setSize(size,size);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        
      statusLabel.setSize(350,100);
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);


   //   mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

//judy is very sad
/*
      BufferedImage myImage = null;
      try {
         myImage = ImageIO.read(new File("tress.jpg"));
      } catch (IOException e) {
      }
      JFrame myJFrame = new JFrame("Image pane");
      myJFrame.setContentPane(new ImagePanel(myImage));
*/
/*
      try {
         test = new JLabel(new ImageIcon(ImageIO.read(new File("trees.jpg"))));
      }
      catch (IOException e) {
         e.printStackTrace();
      }
      test.setLayout(new FlowLayout());

      mainFrame.setContentPane(test);
   
      mainFrame.pack();
      mainFrame.setVisible(true); 
*/
/*
      try {
         mainFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("trees.jpg")))));
      } 
      catch (IOException e) {
         e.printStackTrace();
      }

      mainFrame.pack();
      mainFrame.setVisible(true); 
*/

   }


   private void showEvent(){
      headerLabel.setText("Welcome to Bomberman Game!"); 

      JButton playButton = new JButton("Play");
      JButton howToPlayButton = new JButton("How to Play");

      playButton.setActionCommand("Play");
      howToPlayButton.setActionCommand("How to Play");
     
      playButton.addActionListener(new ButtonClickListener()); 
      howToPlayButton.addActionListener(new ButtonClickListener()); 
     
      controlPanel.add(playButton);
      controlPanel.add(howToPlayButton);
      
      mainFrame.setVisible(true);
      playButton.setOpaque(true);  
      howToPlayButton.setOpaque(true); 
   }

   private class ButtonClickListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();  
         
         if( command.equals( "Play" ))  {
            statusLabel.setText("Play Button clicked.");
         } else if ( command.equals( "How to Play" ) )  {
            //statusLabel.setText("How to Play Button clicked.");
            displayHow();
         //   mainFrame.setVisible(false);  
            //but not good. um. bc that closes the mainframe.
         }   	
      }		
   }

   private void displayHow() {
      JFrame how = new JFrame("How to Play");
      how.setSize(size,size);
      how.setVisible(true);


   }
}