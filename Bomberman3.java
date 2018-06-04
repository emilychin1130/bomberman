import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;

public class Bomberman3 extends JPanel implements ActionListener, KeyListener {

   static Bomberman3 person = new Bomberman3();
   protected static int size = 650;
   Timer t = new Timer(5, this);
   int x = 50, y = 50, velx = 0, vely = 0;
   
   boolean dropBomb = false;
   Enemy e1 = new Enemy(100,50,person);
   Bomb b1;
   Fire f1;

   private static JFrame mainFrame;
   private static JFrame playFrame;
   private static JFrame howFrame;
   private static JLabel headerLabel;
   private static JPanel controlPanel;

   public Bomberman3(){
      t.start();
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      // Pre-load all the sound files
      //SoundEffect.init();
      //SoundEffect.volume = SoundEffect.Volume.LOW;  // un-mute
   }

   public int getx() {
      return x;
   }

   public int gety() {
      return y;
   }

   public void paint(Graphics g){
      super.paint(g);
      Toolkit tool = Toolkit.getDefaultToolkit(); 
      Image i = tool.getImage("character.png");  
      g.drawImage(i,x,y,this);
      e1.render(g); 
   }

   public void paintComponent(Graphics g){ //paint indestructible walls
      super.paintComponent(g);
      for(int i = 0; i<600; i+=50){
         g.fillRect(i,0,i+50,50);
      }
      for(int i = 0; i<600; i+=50){
         g.fillRect(i,600,i+50,650);
      }
      for(int i = 0; i<600; i+=50){
         g.fillRect(0,i,50,i+50);
      }
      for(int i = 0; i<600; i+=50){
         g.fillRect(600,i,650,i+50);
      }
      for(int i = 100; i <= 500; i+=100){
         for(int j = 100; j <= 500; j+=100){
            g.fillRect(i, j, 50, 50);
         }
      }

      if (dropBomb) {
         b1.render(g);
         //SoundEffect.EXPLODE.play();
         f1.render(g);
      }
   }

   public void actionPerformed(ActionEvent e) {
      if(x < 50){
         velx=0;
         x = 50;
      }

      if(x > 558){
         velx=0;
         x = 558;
      }

      if(y < 50){
         vely=0;
         y = 50;
      }

      if(y > 558){
         vely=0;
         y = 558;
      }
      if((x+velx>60 && x+velx<150)||(x+velx>160 && x+velx<250 )||(x+velx>260 && x+velx<350)||(x+velx>360 && x+velx<450 )||(x+velx>460 && x+velx<550) || (x+velx>650)){
         if((y+vely>60 && y +vely< 150)||(y+vely>160 && y+vely < 250 )|| (y+vely>260 && y+vely < 350)||(y+vely>360 && y+vely < 450) || (y+vely>460 && y +vely< 550)||(y+vely>650)){
            velx = 0;
            vely = 0;
         }
         else{
            x += velx;
            y += vely;
            repaint();
         }
      }
      else{
         x += velx;
         y += vely;
         repaint();
      }
   }

   public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();

      if (code == KeyEvent.VK_DOWN){
         vely = 1;
         velx = 0;
      }
      if (code == KeyEvent.VK_UP){
         vely = -1;
         velx = 0;
      }
      if (code == KeyEvent.VK_LEFT){
         vely = 0;
         velx = -1;
      }
      if (code == KeyEvent.VK_RIGHT){
         vely = 0;
         velx = 1;
      }
      if (code == KeyEvent.VK_SPACE){
         b1 = new Bomb(person);
         f1 = new Fire(person);
         dropBomb = true;
      }
   }

   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {
      velx=0;
      vely=0;
   }

//could add a "help/how to play" option in like. some menu bar? 
   //like not in the actual jframe but in the very top of the screen where the "file" "edit" etc stuff usually is
   public static void main(String[] args){
      //Bomberman person = new Bomberman();
      //Enemy test = new Enemy(100,50,person);
      //b = new Bomb(person);
      mainFrame = new JFrame("Bomberman");
      mainFrame.setSize(size,size);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);    
  
   //   mainFrame.add(person);

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
      }   
   }

   private static void displayPlay() {
      playFrame = new JFrame("Bomberman");
      playFrame.setSize(size,size);
      playFrame.add(person);
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
      howLabel.setText("<html>Use arrow keys to move the bomberman.<br>Press spacebar to drop bomb.<br>Avoid the enemies to make it to the exit!</html>");
      howFrame.add(howLabel);
      howFrame.setVisible(true);
   }

}