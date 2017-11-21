import java.awt.event.KeyEvent;
import java.awt.Font;
import java.io.File;

import javax.swing.JOptionPane;

import edu.princeton.cs.introcs.StdDraw;


public class Game
{
	private Grid grid;
	private long msElapsed;
	private int score;
	private int xRes;
	private int yRes;
	private boolean gameOver;
	private int lvl = 500;
	private long highScore;
	//public int numRows;
	//public int numCols;


	public Game(){
		//Sets up the game playing area

		grid = new Grid(5,10, 64, this);
		xRes = grid.getNumCols() * grid.getCellSize();
		yRes = (grid.getNumRows() + 1) * grid.getCellSize();
		StdDraw.setCanvasSize(xRes + 100,yRes + 100);
		StdDraw.setXscale(-100, xRes + 100);
		StdDraw.setYscale(-100, yRes);
	}

	public void reset() {
		// Reset the game to its initial state.

		gameOver = false;
		grid = new Grid(5,10, 64, this);
		msElapsed = 0;
		score = 0;
		grid.reset();
		grid.populateRightEdge();
		grid.draw(0, 10);
		grid.moveAll();
	}

	public void play(){

		while (!isGameOver()){
			//moves all objects of the game

			StdDraw.clear(StdDraw.WHITE);
			StdDraw.picture((xRes + 200)/2 - 100, (yRes + 200)/2 - 100,"GameOver.jpg" ,xRes +200 , yRes +200);
			grid.draw(0, 10);	
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(100, -75, "Score:  " + getScore());
			StdDraw.text(400, -75, "Use the UP and DOWN keys to move and Q tp quit.");
			StdDraw.show(20);

			msElapsed += 20;

			grid.moveAll();

			grid.populateRightEdge();
		}
	}

	public int getScore(){

		
		return score;
	}
	public long getHighScore(){

		if(getScore() >= highScore){

			highScore = getScore();

		}
		return highScore;
	}
	public void adjustScore(int scoreDifference){


		score = score + scoreDifference;
	}

	public long getTimeElapsed() {
		return msElapsed;
	}

	public boolean isGameOver(){
		return gameOver;
	}
	public int getlvl(){

		return lvl;
	}

	public void signalGameOver(){
		gameOver = true;
	}

	public void titleScreen() {
		// Insert code to show the title screen and await a keypress from the player
		// to start the game.

	
		while( !StdDraw.hasNextKeyTyped()){

			StdDraw.setPenColor(StdDraw.WHITE);		
			StdDraw.picture((xRes + 200)/2 - 100, (yRes + 200)/2 - 100, "wallpaper.jpg",xRes +200 , yRes +200);
			StdDraw.text(xRes/2, yRes/2, "Colourfun Bubble");
			StdDraw.text(xRes/2, yRes/4, "To Start Game,");
			StdDraw.text(xRes/2, yRes/8, "Press Any Key!");
			StdDraw.show(20);
		}
	}

	public void gameOverScreen() {
		// Insert code to show the "game over" screen, and await a keypress from the
		// player before the game is restarted.

		StdDraw.clear(StdDraw.BLACK);
		while(!StdDraw.isKeyPressed(KeyEvent.VK_ENTER)){

			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.picture((xRes + 200)/2 - 100, (yRes + 200)/2 - 100, "GameOver.jpg" ,xRes +200 , yRes +200);
			StdDraw.text(xRes - 300, yRes - 100, "GAME OVER!!!");
			StdDraw.text(xRes/2, yRes/2, "Your Highest Score:");
			StdDraw.text(xRes/2, yRes/2.5, " " + getHighScore());
			StdDraw.text(xRes/2, yRes/8, "Your Score: " + getScore());
			StdDraw.text(xRes/2, yRes/16, "Press Enter To Start Again!");
			StdDraw.show(20);
		}
	}


	public static void test(){

		Game game = new Game();

		while (true) {
			game.titleScreen(); 
			game.play();
			game.gameOverScreen();
			game.reset();
		}
	}

	public static void main(String[] args){ 

		test();
	}
}