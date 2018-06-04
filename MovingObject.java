import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovingObject extends GameObject implements ActionListener, KeyListener{
	protected boolean isAlive;
	protected int x = 0, y = 0, velx = 0, vely = 0;

	public MovingObject(int type, int x, int y) {
		super(new Point(x,y), type, true);
		isAlive = true;

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public setLocation(x,y) {
		this.location = new Point(x,y);
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

	public void moveUp() {
		vely = -1;
		velx = 0;
	}

	public void moveDown() {
		vely = 1;
		velx = 0;
	}

	public void moveRight() {
		vely = 0;
		velx = 1;
	}

	public void moveLeft() {
		vely = 0;
		velx = -1;
	}
}