import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bomberman2 {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public Bomberman2(){
      prepareGUI();
   }

   public static void main(String[] args){
      Bomberman2 Bomberman2 = new Bomberman2();  
      Bomberman2.showEventDemo();       
   }

   private void prepareGUI(){
      mainFrame = new JFrame("Bomberman");
      mainFrame.setSize(700,700);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        
      statusLabel.setSize(350,100);
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      final Image image = requestImage();    
    //  controlPanel = new JPanel();

      JPanel controlPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
      controlPanel.setLayout(new FlowLayout());


      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);

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



   private Image requestImage() {
        Image image = null;

        try {
    //        image = ImageIO.read(new URL("http://www.johnlennon.com/wp-content/themes/jl/images/home-gallery/2.jpg"));
            image = ImageIO.read(new File("trees.jpg"));      
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }




   private void showEventDemo(){
      System.out.println("hi");

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
            statusLabel.setText("How to Play Button clicked."); 
         }   	
      }		
   }
}