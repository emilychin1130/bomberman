public class MovingObject extends GameObject {
	protected boolean isAlive;

	public MovingObject {
		super(Point location, int type, boolean visible);
		isAlive = true;
	}

	public setLocation(x,y) {
		this.location = new Point(x,y);
	}

	//moving in all directions, check if places can be moved to

	// public moveUp {
	// 	board.moveUp(this);
	// }
	// public moveDown {
	// 	board.moveDown(this);
	// }
	// public moveRight {
	// 	board.moveRight(this);
	// }
	// public moveLeft {
	// 	board.moveLeft(this);
	// }
}