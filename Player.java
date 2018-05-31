public class Player extends MovingObject{
	protected int bombLength;
	protected boolean canMoveThroughWall;

	public Player {
		super(new Point(1,1), 1, true);
		bombLength = 1;
		canMoveThroughWall = false;
	}

	public Point getLocation() {
		return location;
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