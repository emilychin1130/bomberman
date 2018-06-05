import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;


public class Bomb extends JPanel{

	protected Bomberman person;
	final int XVAL;
	final int YVAL;
	private BufferedImage image;

	//constructs Bomb object, reads bomb file
	public Bomb(Bomberman person) {
		XVAL = person.getx();
		YVAL = person.gety();
		try {
			image = ImageIO.read(new File("bomb.png"));
		}
		catch (IOException e) {
			//nothing
		}
	}

	//draws bomb image
	public void render(Graphics g) {
		g.drawImage(image, XVAL, YVAL, this);
/*		try {
    		Thread.sleep(3000);
		} 		
		catch(InterruptedException e) {}
		g.clearRect(XVAL, YVAL, image.getWidth(null), image.getHeight(null));
		*/
	}
}