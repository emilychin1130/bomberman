import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Point; 
import javax.swing.*;
import java.awt.*;

public class Enemy extends JPanel implements ActionListener{
	protected boolean isAlive;
	protected int x = 0, y = 0, velx = 0, vely = 0;
	protected Bomberman person;

	public Enemy(int x, int y, Bomberman person) {
		this.x = x;
		this.y = y;
		this.person = person;
	//	addKeyListener(this);
	//	setFocusable(true);
	//	setFocusTraversalKeysEnabled(false);
	}

	public Point getLocation() {
		return new Point(x,y);
	}

	public void setLocation(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public void actionPerformed(ActionEvent e) {
		if(x < 0) {
			velx=0;
			x = 0;
		}

		if(x > 650) {
			velx=0;
			x = 650;
		}

		if(y < 0) {
			vely=0;
			y = 0;
		}

		if(y > 650) {
			vely=0;
			y = 650;
		}

		//indestructible
		if (x%2 == 0 && y%2 == 0) {
			velx = 0;
			vely = 0;
		}

		x += velx;
		y += vely;
		repaint();
	}

	public void moveUp(int a) {
		vely = -1*a;
		velx = 0;
	}

	public void moveDown(int a) {
		vely = a;
		velx = 0;
	}

	public void moveRight(int a) {
		vely = 0;
		velx = a;
	}

	public void moveLeft(int a) {
		vely = 0;
		velx = -1*a;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillRect(x,y,50,50);
	}

	public void move() {
		boolean moveX = (Math.abs(this.x - person.getx()) > Math.abs(this.y - person.gety()));
		if(moveX){
			if(this.x > person.getx()){
				int x = (int) Math.random()*2 + 1;
				moveLeft(x);
			}
			else{
				int x = (int) Math.random()*2 + 1;
				moveRight(x);
			}
		}
		else{
			if(this.y > person.gety()){
				int x = (int) Math.random()*2 + 1;
				moveUp(x);
			}
			else{
				int x = (int) Math.random()*2 + 1;
				moveDown(x);
			}
		}

		if (person.getx() == this.x && person.gety() == this.y) {
			//player dies
		}
	}


}