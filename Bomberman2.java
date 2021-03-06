import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;

public class Bomberman2 extends JPanel {
   private JFrame mainFrame;
   private JFrame playFrame;
   private JFrame howFrame;
   private JFrame gameFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   protected static int size = 650;

//  private JLabel test;

   public Bomberman2(){
      prepareGUI();
   }

//sets up the main screen
   private void prepareGUI(){
      mainFrame = new JFrame("Bomberman");
      mainFrame.setSize(size,size);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
   //   statusLabel = new JLabel("",JLabel.CENTER);        
    //  statusLabel.setSize(350,100);
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
   //   mainFrame.add(statusLabel);
      mainFrame.setVisible(true);


//judy is very sad
/*
      BufferedImage myImage = null;
      try {
         myImage = ImageIO.read(new File("trees.jpg"));
      } catch (IOException e) {
      }
      mainFrame.setContentPane(new ImagePanel(myImage));
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
         if (command.equals("Play")) {
            displayPlay();
         } 
         else if (command.equals("How to Play")) {
            //statusLabel.setText("How to Play Button clicked.");
            displayHow();
         }
         else if (command.equals("Start")) {
            displayGame();
         }    
      }   
   }

//displays the screen to select theme and then start game
   private void displayPlay() {
      playFrame = new JFrame("Start Screen");
      playFrame.setSize(size,size);
      JPanel playPanel = new JPanel();
      playFrame.add(playPanel);
      //JLabel themeLabel = new JLabel("",JLabel.CENTER );
      //themeLabel.setText("Choose a theme:");
      //playFrame.add(themeLabel);
      JButton startButton = new JButton("Start");
      startButton.setActionCommand("Start");
      startButton.addActionListener(new ButtonClickListener());
      playPanel.add(startButton);
   //   playFrame.getContentPane().add(playPanel,"CENTER");
      startButton.setOpaque(true);
      playFrame.setVisible(true);
      mainFrame.dispose();
      playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

//displays instructions to play the game
   private void displayHow() {
      //probably should set like a layout manager or smth. oh boy
      howFrame = new JFrame("How to Play"); //should i make this a global variable like mainFrame?
      howFrame.setSize(size,size);
      //use jtextarea?
      JLabel howLabel = new JLabel("",JLabel.CENTER );
      howLabel.setText("<html>Use arrow keys to move the bomberman.<br>Press spacebar to drop bomb.<br>Avoid the enemies to make it to the exit!</html>");
      howFrame.add(howLabel);
      howFrame.setVisible(true);
   }

   private void displayGame() {
      gameFrame = new JFrame("Bomberman");
      gameFrame.setSize(size,size);
      playFrame.dispose();
      //paint the indestructible walls.
   //   paintNDWalls()



      gameFrame.setVisible(true);
      gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void paintNDWalls(Graphics g){
      super.paintComponent(g);
      g.setColor(Color.darkGray);
      g.fillRect(0,0,100,100);

/*      for (int i = 0; i < 600; i+=50){
         super.paintComponent(g);
         g.setColor(Color.darkGray);
         g.fillRect(i,0,i+50,50);
      }
      for (int i = 0; i < 600; i+=50){
         super.paintComponent(g);
         g.setColor(Color.darkGray);
         g.fillRect(i,600,i+50,650);
      }
      for (int i = 0; i < 600; i+=50){
         super.paintComponent(g);
         g.setColor(Color.darkGray);
         g.fillRect(0,i,50,i+50);
      }
      for (int i = 0; i < 600; i+=50){
         super.paintComponent(g);
      g.setColor(Color.darkGray);
      g.fillRect(600,i,650,i+50);
      } */
   }

   public static void main(String[] args){
      Bomberman2 bomberman = new Bomberman2();  
      bomberman.showEvent();       
   }

}