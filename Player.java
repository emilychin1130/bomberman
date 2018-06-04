public class Player extends MovingObject{
	protected int bombLength;
	protected boolean canMoveThroughWall;

	public Player() {
		super(new Point(1,1), 1, true);
		bombLength = 1;
		canMoveThroughWall = false;
	}

	public Point getLocation() {
		return location;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(x,y,50,50);
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_DOWN){
			moveDown();
		}
		if (code == KeyEvent.VK_UP){
			moveUp();
		}
		if (code == KeyEvent.VK_LEFT){
			moveLeft();
		}
		if (code == KeyEvent.VK_RIGHT){
			moveRight();
		}
	}


	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {
		velx=0;
		vely=0;
	}


	public static explode(int x, int y) {
		if (board[x][y].getType() == 1) {
			board[x][y].isAlive = false;
		}
		if (board[x][y].getType() == 3) {
			board[x][y] = null;
		}
		if (board[x][y].getType() == 4) {
			board[x][y] = null;
		}
	}

	public dropBomb() {
		//image at player.getLocation changes to image of bomb

		GameObject[][] board = Board.getboard();
		explode(getLocation().x(), getLocation().y());
		if (getLocation().x() - 1 > 0) {
			explode(getLocation().x() - 1, getLocation().y());
		}
		if (getLocation().y() - 1 > 0) {
			explode(getLocation().x(), getLocation().y() - 1);
		}
		if (getLocation().x() + 1 < 12) {
			explode(getLocation().x() + 1, getLocation().y());
		}
		if (getLocation().y() + 1 < 12) {
			explode(getLocation().x(), getLocation().y() + 1);
		}
	}
}