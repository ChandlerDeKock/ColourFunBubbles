import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Grid {
	private static final String Grid = null;

	// Add instance variables.
	GameObject[][] grid;
	int GridCellSize = 0;
	int NumRows = 0;
	int NumCols = 0;
	public Game game = null;

	public Grid(int numRows, int numCols, int gridCellSize, Game game) {
		// Initialize a net grid with the given dimensions (rows by columns).
		// Each cell
		// in the grid should be draw with width and height of gridCellSize on
		// the
		// screen. Calls reset() to reset the state of the grid to what it
		// should be at
		// the start of the game.
		grid = new GameObject[numRows][numCols];
		GridCellSize = gridCellSize;
		NumCols = numCols;
		NumRows = numRows;
		Player p = new Player();
		Location player = new Location(2, 0);
		this.game = game;
		p.init(game, this, player);

		setObjectAt(player, p);

	}

	public void reset() {
		// Set up an initial grid of game objects. This could be just an empty
		// grid, but
		// this depends on how you want your game to start off initially.
		// This can be called from outside to reset the board at the start of
		// play after
		// a game over condition has been reached.
		// Remember to include a Player object on this new grid, otherwise you
		// won't
		// have anything to control in the game!

		Player p = new Player();
		Location player = new Location(2, 0);
		p.init(game, this, player);

		setObjectAt(player, p);
	}

	public void populateRightEdge() {
		// Place new objects at the rightmost edge of the grid. This function
		// depends
		// entirely on you, but make sure that this results in interesting
		// gameplay.
		
		if(game.getTimeElapsed() % game.getlvl() == 0){
			for (int i = 0; i < NumRows; i++) {
				double lvl = 0;
				if(game.getScore() % 10 == 0){
					
					lvl = lvl + 0.01;
				}

				double generator = Math.random();

				if ((generator < 0.05 + lvl) && (generator > 0)) {

					setObjectAt(new Location(i, NumCols - 1), new GoodThing());

				}
				if ((generator >= 0.9 - lvl) && (generator <= 1)) {

					setObjectAt(new Location(i, NumCols - 1), new BadThing());

				}
			}
		}
	}

	public int getNumRows() {
		// Return the number of rows in the grid.
		return NumRows;
	}

	public int getNumCols() {
		// Return the number of columns in the grid.
		return NumCols;
	}

	public GameObject getObjectAt(Location loc) {
		// Return the object at location loc.

		int Row = loc.getRow();
		int Col = loc.getCol();
		return grid[Row][Col];
	}

	public void setObjectAt(Location loc, GameObject obj) {
		// Set the object at location loc.

		int Row = loc.getRow();
		int Col = loc.getCol();

		grid[Row][Col] = obj;
		grid[Row][Col].init(game, this, loc);
	}

	public int getCellSize() {
		// Return the cell size of the grid.
		return GridCellSize;
	}

	public void moveAll() {
		// Ask each object in the grid what its new location should be. Make a
		// new array
		// representing the objects in the grid at their new locations. If two
		// objects
		// ask to be moved to the same location, resolve this by calling the
		// collision
		// method on one of them and use the result of that in the new grid's
		// corresponding location.
		GameObject[][] grid1 = new GameObject[NumRows][NumCols];

		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < NumCols; j++) {
				
				if (grid[i][j] instanceof Player) {

					Location move = grid[i][j].move();
					grid1[move.getRow()][move.getCol()] = grid[i][j];

					if (grid[move.getRow()][move.getCol()] instanceof GoodThing) {

						grid1[move.getRow()][move.getCol()] = grid[i][j].collision(grid[move.getRow()][move.getCol()]);

						if (grid[move.getRow()][move.getCol()] instanceof BadThing) {

							grid1[move.getRow()][move.getCol()] = grid[i][j].collision(grid[move.getRow()][move.getCol()]);
						}
					}
				}
			}
		}
		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < NumCols; j++) {

				if (grid[i][j] != null && !(grid[i][j] instanceof Player)) {

					Location move = grid[i][j].move();
					if (move.getCol() == -1) {
						
						continue;
						
					} else {
					
						if(grid1[move.getRow()][move.getCol()] == null){
							
								grid1[move.getRow()][move.getCol()] = grid[i][j];
						}

						else  {
							
							grid1[move.getRow()][move.getCol()] = grid[i][j].collision(grid1[move.getRow()][move.getCol()]);

						}
					}
				}
				/*if (grid[i][j] instanceof BadThing) {

					Location move = grid[i][j].move();
					if (move.getCol() == -1) {
						continue;
						// grid1[move.getRow()][0] = null;
					} else {

						grid1[move.getRow()][move.getCol()] = grid[i][j];

						if (grid[move.getRow()][move.getCol()] instanceof Player) {

							grid1[move.getRow()][move.getCol()] = grid[i][j]
									.collision(grid[move.getRow()][move
									                               .getCol()]);
						}
					}
				}*/
			}
		}

		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < NumCols; j++) {

				grid[i][j] = grid1[i][j];

			}
		}
	}

	public void draw(int atX, int atY) {
		// Draw every object in the grid, going through every row and column.
		// The origin
		// of the grid should be at drawAtX and drawAtY. You must delegate each
		// object's
		// painting the object itself.
		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < NumCols; j++) {

				StdDraw.setPenColor(StdDraw.WHITE);

				if (grid[i][j] != null) {

					grid[i][j].draw(j, i, GridCellSize);

				}
			}
		}
	}

	public boolean isValid(Location loc) {
		// Returns true if the location Loc is on the grid, otherwise it returns
		// false.
		if (loc.getRow() < 0 || loc.getCol() < 0) {

			return false;
		}
		if (loc.getRow() > (NumRows - 1) || loc.getCol() > (NumRows - 1)) {

			return false;
		}

		return true;
	}
}