import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;

public class Bomberman extends JPanel implements ActionListener, KeyListener {

   protected static int size = 650;
   Timer t = new Timer(5, this);
   int x = 50, y = 50, velx = 0, vely = 0;

   public Bomberman(){
      t.start();
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
    //  Graphics g = null;
     //   for(x = 0; i < 600; i+=50) {
     //     paintComponent(g,x,y);
      //}
   }

   public int getx() {
      return x;
   }

   public int gety() {
      return y;
   }
/*
   public void paintComponent(Graphics g, int x, int y) {
      super.paintComponent(g);
      g.setColor(Color.darkGray);
      g.fillRect()
   }
*/
   public void paint(Graphics g){
      super.paint(g);
      Toolkit t=Toolkit.getDefaultToolkit();  
      Image i=t.getImage("character.png");  
      g.drawImage(i,x,y,this);  
      //super.paintComponent(g);
     // g.setColor(Color.darkGray);
     // g.fillRect(0,0,100,100);

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
   }

   public void paintBomb(Graphics g, int a, int b){
      super.paint(g);
      g.setColor(Color.BLACK);
      g.fillOval(x,y,50,50);
   }

   public void paintFire(Graphics g, int a, int b){
      super.paint(g);
      g.setColor(Color.RED);
      g.fillRect(x,y,30,30);
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
         displayFire();
         //System.out.println("space bar pressed");
      }
   }

   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {
      velx=0;
      vely=0;
   }

   public void displayFire() {

   }

//could add a "help/how to play" option in like. some menu bar? 
   //like not in the actual jframe but in the very top of the screen where the "file" "edit" etc stuff usually is
   public static void main(String[] args){
      Bomberman person = new Bomberman();  
      JFrame mainFrame = new JFrame("Bomberman");
      mainFrame.setSize(size,size);
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
   //   JPanel controlPanel = new JPanel();
   //   controlPanel.setLayout(new FlowLayout());
   //   mainFrame.add(controlPanel);
  
      mainFrame.add(person);
      mainFrame.setVisible(true);       
   }

}