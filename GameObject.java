import java.util.Point;

public class GameObject {
	protected Point location;
	protected int type;
	protected boolean visible;

	public static GameObject(Point location, int type, boolean visible) {
		location = location;
		type = type;
		visible = visible;
	}

}