import java.awt.Color;
import java.util.ArrayList;
/**
 *  \\file	-Computer.Java
 * 	\author	-Mathew Lloyd 711293 - A6
 * 	\date	-02/05/2014
 * 	
 * 	\brief	Abstract class for class hierarchy structure for the two computer 
 * players
 * 
 * This class is tested by the two classes for the computer players
 */

public abstract class Computer extends Player{

	/**
	 * Constructor of Player, receives the type of game 
	 * \param game the type of game to be played
	 */
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
	/**
	 * method called when it receives a move
	 *\throws InterruptedException
	 */
	public abstract boolean sendMove() throws InterruptedException;
	
	/**
	 * The method sets when the thread is to be running, used for thread control
	 * \param run true when game is running, false when not.
	 * \return true if the method completes
	 */
	public abstract boolean SetRun(boolean run);
	
	/**
	 * The method sets the response time for the AI to respond to the opponents
	 * move.
	 * 
	 * \param responseTime an integer value of which to set the response time to
	 * \return true if the method completes
	 */
	public abstract boolean SetTime(int responseTime);
	
	
	/**
	 * The method returns the response time for the AI.
	 * \return m_time The response time for the AI
	 */
	public abstract int getTime();
	
	/**
	 * The method is getting whether the thread is supposed to be running or not
	 * \return m_running, true when game is running, false when not.
	 */
	public abstract boolean getRun();
	
	/**Method which sets the AI position. It gets the list of available comments
	 * from the getAvailableMoves() method. Loops through the list and tries
	 * to find the maximum number of pieces that would be changed and then 
	 * returns the maximum turn coordinate as the move to take.
	 * \return Coordinate the Coordinate that would be taken.*/
	public abstract Coordinate setAIMove();
	
	
}
