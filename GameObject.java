import java.util.Point; //or awt

public class GameObject { //may need to extend JPanel
	protected Point location;
	protected int type;
	//player = 1
	//indestructible wall = 2
	//destructible wall = 3
	//powerup = 4
	//bomb = 5
	//enemy = 6
	protected boolean visible;

	public static GameObject(Point location, int type, boolean visible) {
		location = location;
		type = type;
		visible = visible;
	}
	public static GameObject(int type, int x, int y) {
		location = new Point(x, y);
		type = type;
		visible = true;
	}

	public int getType() {
		return type;
	}

	public Point getLocation() {
		return location;
	}

}