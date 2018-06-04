import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;


public class Person extends JPanel{

//	private Board board;
	private BufferedImage image;

	public Person() {
		try {
			image = ImageIO.read(new File("character.jpg"));
		}
		catch (IOException e) {
			//nothing
		}
	}

	public void render(Graphics g) {
		g.drawImage(image, 300, 300, this);
	}



}