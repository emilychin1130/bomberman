import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Fire extends JPanel{

	protected Bomberman person;
	final int XVAL;
	final int YVAL;
	private BufferedImage image;

	public Fire(Bomberman person) {
		XVAL = person.getx();
		YVAL = person.gety();
		try {
			image = ImageIO.read(new File("fire.png")); //dimensions: 40x66
		}
		catch (IOException e) {
			//nothing
		}
	}

	public void render(Graphics g, String direction) {
		AffineTransform backup = ((Graphics2D)g).getTransform();
		AffineTransform a = AffineTransform.getRotateInstance(Math.PI, (XVAL*2+40)/2.0, (YVAL*2+66)/2.0);
		//((Graphics2D)g).setTransform(a);
		
		if (direction == "North") {
			g.drawImage(image, XVAL, YVAL-50, this);
		}
		else if (direction == "South") {
			//((Graphics2D)g).setTransform(backup);
			((Graphics2D)g).setTransform(a);
			//((Graphics2D)g).drawImage(image, XVAL, YVAL+100, this);
			g.drawImage(image, XVAL, YVAL-50, this);
		}
		else if (direction == "East") {
			a = AffineTransform.getRotateInstance(Math.PI/2, (XVAL*2+40)/2.0, (YVAL*2+66)/2.0);
			((Graphics2D)g).setTransform(a);
			g.drawImage(image, XVAL+50, YVAL, this);
		}
		else if (direction == "West") {
			a = AffineTransform.getRotateInstance(Math.PI, (XVAL*2+40)/2.0, (YVAL*2+66)/2.0);
			((Graphics2D)g).setTransform(a);
			g.drawImage(image, XVAL-50, YVAL, this);
		}
	}
}