import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;



//prob shouldn't use this anymore bc i made everything static :)



public class Bombermantest extends JPanel{
   private static JFrame mainFrame;
   private static JFrame playFrame;
   private static JFrame howFrame;
   private static JFrame gameFrame;
   private static JLabel headerLabel;
//   private JLabel statusLabel;
   private static JPanel controlPanel;
   protected static int size = 700;

//  private JLabel test;
   private Person person = new Person();

   public Bombermantest(){
      prepareGUI();
   }

   public static void prepareGUI() {
      mainFrame = new JFrame("Bombermantest");
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
      headerLabel.setText("Welcome to Bombermantest Game!"); 

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

   public static void main(String[] args){
      Bombermantest bombermantest = new Bombermantest();  
      //Bombermantest.showEvent();      
   }

   private static class ButtonClickListener implements ActionListener{
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
   private static void displayPlay() {
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
   private static void displayHow() {
      //probably should set like a layout manager or smth. oh boy
      howFrame = new JFrame("How to Play"); //should i make this a global variable like mainFrame?
      howFrame.setSize(size,size);
      //use jtextarea?
      JLabel howLabel = new JLabel("",JLabel.CENTER );
      howLabel.setText("<html>Use arrow keys to move the Bombermantest.<br>Press spacebar to drop bomb.<br>Avoid the enemies to make it to the exit!</html>");
      howFrame.add(howLabel);
      howFrame.setVisible(true);
   }

   private static void displayGame() {
      gameFrame = new JFrame("Bombermantest");
      gameFrame.setSize(size,size);
      gameFrame.setVisible(true);
      playFrame.dispose();
      gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.RED);
      g.fillRect(0,0,50,30);
   //   person.render(g);
   }

}