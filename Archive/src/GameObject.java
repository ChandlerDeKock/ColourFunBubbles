public interface GameObject {
  // Interface that each object in the game that can occupy a cell within
  // the grid must implement.
  
  public void init(Game game, Grid grid, Location location);
  // Initialize the object with the game it is inside, the grid it is inside, and its
  // initial location in that grid.
  
  public GameObject collision(GameObject otherObject);
  // Handle the collision of this object with a another object in the grid. The object
  // returned by this method should be the new object to occupy the cell within the grid.
  
  public Location move();
  // Move the object. This may be under keyboard control (for a user), or it could be
  // automatic (for enemies or powerups). Really sophisticated games could add players
  // controlled over the network (for multiplayer games).
  
  public void draw(int centerX, int centerY, int cellSize);  
  // Draw the object to the screen at position centerX, centerY with width/height of
  // cellSize.
}