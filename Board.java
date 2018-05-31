import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Board{
  protected static final int width = 13;
  protected static final int height = 13;
  protected GameObject[][] board;
  protected static final int numWalls = 98;
  protected ArrayList<GameObject> walls;
  protected Player player;
  protected Enemy[] enemies = [4];
  protected Powerup[] powerups = [3];

  public Board(){
    board = new GameObject[width][height];
    walls = new ArrayList<GameObject>;
    for(int i = 0; i < height; i+12){
      for(int j = 0; j <width; j++){
        board[j][i] = new GameObject(2, j, i);
        walls.add(new GameObject(2, j, i));
      }
    }
    for(int i = 0; i < width; i+12){
      for(int j = 0; j < height; j++){
        board[i][j] = new GameObject(2,i,j);
        walls.add(new GameObject(2, i, j))
      }
    }
    for(int i = 2; i < width; i + 2){
      for(int j = 2; j < height; j + 2){
        board[i][j] = new GameObject(2, i, j);
        walls.add(new GameObject(2, i, j));
      }
    }
    player.move(1,1);
    board[1][1] = player;
  }

  public Point newEmpty(){
    boolean available = true;
    int x;
    int y;
    random a = new Random();
    do{
      x = a.nextInt(width);
      y = a.nextInt(height);
      if(board[x][y] == null{
        available = true;
      }
      else{
        available = false;
      }
    }
    while(!available);
    return new Point(x,y);
  }

  public boolean inBound(int x, int y){
    if(x>=width || y >= height || x < 0 || y < 0){
      retur false;
    }
    else{
      return true;
    }
  }

  public boolean isEmpty(int x, int y){
    if(board[x][y]== null){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean isEmpty(){
    isEmpty(this.getLocation().x, this.getLocation().y);
  }

  public boolean isNDWalls(int x, int y){ //check to see if location(x,y) has indistructable walls
    if(board[x][y].getType() == 2){
      return true;
    }
    else{
      return false;
    }
  }

  public void setDWalls(){ //randomly sets the location of destructible walls
    Point emptyGrid;
    GameObject dwall;
    while(walls.size() < numWalls){
      emptyGrid = newEmpty();
      dwall = new GameObject(3, (int)emptyGrid.getX(), (int)emptyGrid.getY());
      walls.add(dwall);
      board[(int)emptyGrid.getX][(int)emptyGrid.getY()] = dwall;
    }
  }

  public void setEnemies(){
    Point emptyGrid;
    for(int i = 0; i < 4; i++){
      emptyGrid = newEmpty();
      enemies[i] = new Enemy(6, (int)emptyGrid.getX(), (int)emptyGrid.getY());
      board[(int)emptyGrid.getX][(int)emptyGrid.getY()] = enemies[i];
    }
  }

  public void setPowerups(){
    fo
  }

  public static void explode(int x, int y) {
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

  public void dropBomb() {
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
