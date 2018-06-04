public class Enemy extends MovingObject{
	protected Player target;

	public Enemy(int x, int y, Player player) {
		super(6,11,11);
		this.target = player;
	}

	public Point getLocation() {
		return location;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillRect(x,y,50,50);
	}

	public void move() {
		boolean moveX = (Math.abs(this.x() - target.x()) > Math.abs(this.y() - target.y()));
		if(moveX){
			if(this.x() > target.x()){
				super.moveLeft();
			}
			else{
				super.moveRight();
			}
		}
		else{
			if(this.y() > target.y()){
				super.moveUp();
			}
			else{
				super.moveDown();
			}
		}

		if (target.getLocation().equals(this.getLocation())) {
			//player dies
		}
	}


}