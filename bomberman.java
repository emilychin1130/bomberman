import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Bomberman extends JPanel implements ActionListener, KeyListener {

   static Bomberman person = new Bomberman();
   protected static int size = 650;
   private static JFrame mainFrame;
   private static JFrame playFrame;
   private static JFrame howFrame;
   private static JLabel headerLabel;
   private static JPanel controlPanel;
   Timer t = new Timer(5, this); // necessary for keyboard control of movement
   Timer timer; //for the bomb
   Timer timer2; //for the fire
   int x = 50, y = 50, velx = 0, vely = 0;
   boolean showBomb = false;
   boolean showFire = false;
   static boolean endGame = false;
   Enemy e1 = new Enemy(100,50,person);
   Bomb b;
   Fire fNorth, fSouth, fEast, fWest;

   public Bomberman(){
      t.start();
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      // Pre-load all the sound files
      //SoundEffect.init();
      //SoundEffect.volume = SoundEffect.Volume.MEDIUM;  // un-mute
   }

   public int getx() {
      return x;
   }

   public int gety() {
      return y;
   }

   //draws bomberman player and enemies
   public void paint(Graphics g){
      super.paint(g);
      Toolkit tool = Toolkit.getDefaultToolkit(); 
      Image i = tool.getImage("character.png");  
      g.drawImage(i,x,y,this);
      e1.render(g); 
   }

   //paint indestructible walls
   //paint bomb and fire when player drops bomb
   public void paintComponent(Graphics g){ 
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

      g.setColor(Color.darkGray);//DESTRUCTIBLE WALLS
      g.fillRect(150,50,50,50);
      g.fillRect(100,150,50,50);
      g.fillRect(250,200,50,50);
      g.fillRect(250,250,50,50);
      g.fillRect(50,200,50,50);
      g.fillRect(50,300,50,50);
      g.fillRect(300,50,50,50);
      g.fillRect(250,100,50,50);
      g.fillRect(350,350,50,50);
      g.fillRect(150,250,50,50);
      g.fillRect(300,150,50,50);
      g.fillRect(350,200,50,50);
      g.fillRect(350,300,50,50);
      g.fillRect(400,250,50,50);
      g.fillRect(100,350,50,50);
      g.fillRect(150,400,50,50);
      g.fillRect(550,50,50,50);
      g.fillRect(550,100,50,50);
      g.fillRect(500,150,50,50);
      g.fillRect(200,350,50,50);
      g.fillRect(200,450,50,50);
      g.fillRect(450,250,50,50);
      g.fillRect(550,300,50,50);
      g.fillRect(500,350,50,50);
      g.fillRect(450,400,50,50);
      g.fillRect(300,450,50,50);
      g.fillRect(350,450,50,50);
      g.fillRect(50,550,50,50);
      g.fillRect(50,500,50,50);
      g.fillRect(100,450,50,50);
      g.fillRect(250,500,50,50);
      g.fillRect(550,450,50,50);
      g.fillRect(250,400,50,50);
      g.fillRect(150,550,50,50);
      g.fillRect(300,550,50,50);
      g.fillRect(500,550,50,50);
      g.fillRect(450,500,50,50);
      g.fillRect(400,550,50,50);
      g.setColor(Color.RED);//EXIT
      g.fillRect(550,550,50,50);

      if (showBomb) {
         b.render(g);
      }
      if (showFire) {
         //SoundEffect.EXPLODE.play();
         fNorth.render(g, "North");
         fSouth.render(g, "South");
         fEast.render(g, "East");
         fWest.render(g, "West");
      }
   }

   //character movement
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
         if((y+vely>60 && y+vely< 150)||(y+vely>160 && y+vely < 250 )|| (y+vely>260 && y+vely < 350)||(y+vely>360 && y+vely < 450) || (y+vely>460 && y+vely< 550)||(y+vely>650)){
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

   //character moves according to key press
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
      //triggers method for bomb to show up once space bar pressed
      //sets timer for bomb to explode and show fire after 3 seconds
      //sets timer2 for fire to extinguish after 2 seconds
      if (code == KeyEvent.VK_SPACE){
         b = new Bomb(person);
         fNorth = new Fire(person);
         fSouth = new Fire(person);
         fEast = new Fire(person);
         fWest = new Fire(person);
         showBomb = true;
         timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               showFire = true;
               showBomb = false;
            }    
         });
         timer.start();
         timer.setRepeats(false);
         timer2 = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               showFire = false;
            }    
         });
         timer2.start();
         timer2.setRepeats(false);
      }
   }

   public void keyTyped(KeyEvent e) {}

   //stops character movement when user stops key press
   public void keyReleased(KeyEvent e) {
      velx=0;
      vely=0;
   }

   //sets up the main window that user sees
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

   //directs user to appropriate window based on button click
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

   //sets up the screen for the game
   private static void displayPlay() {
      playFrame = new JFrame("Bomberman");
      playFrame.setSize(size,size);
      playFrame.add(person);
      playFrame.setVisible(true);
      mainFrame.dispose();
      playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      if (endGame) {}
      /*if (showBomb) {
         JLabel test = new JLabel(new ImageIcon("bomb.png"));
         playFrame.add(test);
      }*/
   }

   //displays instructions to play the game
   private static void displayHow() {
      //probably should set like a layout manager or smth. oh boy
      howFrame = new JFrame("How to Play"); //should i make this a global variable like mainFrame?
      howFrame.setSize(size,size);
      //use jtextarea?
      JLabel howLabel = new JLabel("",JLabel.CENTER );
      howLabel.setText("<html>Use arrow keys to move the bomberman.<br>Press spacebar to drop bomb and break through the gray walls.<br>Avoid the enemies to make it to the red exit!</html>");
      howFrame.add(howLabel);
      howFrame.setVisible(true);
   }

}