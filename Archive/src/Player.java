import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Player implements GameObject {

	private Game game = null;
	private Grid grid = null;
	private Location location = null;

	public void init(Game game, Grid grid, Location location) {
		// Initialize the player object with a reference to the game, grid and the 
		// player's starting location.
		this.game = game;
		this.grid = grid;
		this.location = location;

	}

	public GameObject collision(GameObject otherObject) {
		// Handle a player object's collision with another object. Return the object that
		// remains if this collision occurs. Note that you can use the "instanceof" keyword
		// to test whether otherObject is of a given type.
		if(otherObject instanceof GoodThing){

			game.adjustScore(1);
			return this;
		}

		if(otherObject instanceof BadThing){

			game.signalGameOver();
			game.gameOverScreen();
			return otherObject;
		}
		else{

			return this;
		}
	}

	public Location move() {
		// Calculate the player's new location. Here, you should handle
		// keyboard input from the player. It is suggested that you update
		// the player's location every 100ms.
		int Row = location.getRow();
		int Col = location.getCol();
		Location move = new Location(Row, Col);

		if(game.getTimeElapsed() % 100 == 0){
			if(StdDraw.isKeyPressed(KeyEvent.VK_UP) ){

				if(Row == 4){

					move = new Location (Row, Col);
				}
				else{
					move = new Location(Row + 1, Col);
				}

			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){

				if(Row == 0){

					move = new Location (Row, Col);
				}
				else{
					move = new Location(Row - 1, Col);
				}
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
				
				game.signalGameOver();
			}
		}
		location = move;
		//draw(move.getRow(), move.getCol(), grid.getCellSize());
		return move;
	}

	public void draw(int centerX  , int centerY, int cellSize) {
		// Draw the player at location x and y, with width/height of cellSize.
		// You can use user.gif as a test image for this.


		StdDraw.picture(centerX * cellSize, centerY * cellSize, "Purple.png", 64, 50);

	}
}