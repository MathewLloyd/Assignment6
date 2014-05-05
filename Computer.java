import java.awt.Color;


public abstract class Computer extends Player{


	public Computer(Game game) {
		super(game);
	}
	
	/**
	 * The first constructor for setting a type of player of ConnectFourAI,
	 * all data is sent to the super class of Player. Sets the running state of
	 * the thread to be true.
	 * \param game the type of game (ConnectFour only due to PlayerSettings)
	 * \see PlayerSettings.java
	 * \param name the name of the player	 
	 * \param color the piece color of the player
	 */
	public Computer(Game game, String name, Color color) {
		super(game, name, color);
	}

	/**
	 * abstract method that can be implemented 
	 * which gets called whenever it is players turn
	 * \throws InterruptedException 
	 */
	public abstract boolean isYourMove() throws InterruptedException;
	/**
	 * abstract method which is called whenever gamewindow receives a move
	 * \param move that was made
	 * \throws InterruptedException 
	 */
	public abstract boolean sendMove(Coordinate move) throws 
	                                                       InterruptedException;
	
	public abstract boolean sendMove() throws InterruptedException;
	
	public abstract boolean SetRun(boolean run);
}
