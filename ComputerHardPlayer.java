import java.awt.Color;
import java.util.*;

/**
 * \\file OthelloAI.Java 
 * \author Mathew Lloyd 711293 A6 
 * \date 01/03/2014
 * 
 * \brief The AI for all games to make a move
 * 
 * The following class sets the AI move for all games of hardest difficulty.
 * Also there are controls of threads in this class for delaying the computer
 * response to add the feeling that the computer is 'thinking' about it's turn.
 * This was created by removing the two original files of OthelloAI and
 * ConnectFourAI as they became irrelevant as could have reuse of methods.
 */

public class ComputerHardPlayer extends Computer {

	/**
	 * The method sets the response time for the AI to respond to the opponents
	 * move.
	 * 
	 * \param responseTime an integer value of which to set the response time to
	 * \return true if the method completes
	 */
	public boolean SetTime(int responseTime) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: SetTime() BEGIN");
		}
		m_time = responseTime;

		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: SetTime() END");
		}

		return true;
	}

	/**
	 * The method returns the response time for the AI. 
	 * \return m_time The response time for the AI
	 */
	public int getTime() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: getTime() BEGIN");
		}
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: getTime() END");
		}
		return m_time;

	}

	/**
	 * The method sets when the thread is to be running, used for thread control
	 * \param run true when game is running, false when not. 
	 * \return true if the method completes
	 */
	public boolean SetRun(boolean run) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: SetRun() BEGIN");
		}
		m_running = run;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: SetRun() END");
		}

		return true;
	}

	/**
	 * The method is getting whether the thread is supposed to be running or not
	 * \return m_running, true when game is running, false when not.
	 */
	public boolean getRun() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: getRun() BEGIN");
		}
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: getRun() END");
		}
		return m_running;
	}

	/**
	 * The first constructor for setting a type of player of ComputerHardPlayer, 
	 * all data is sent to the super class of Player via Computer. Sets the
	 *  running state of the thread to be true. 
	 * \param game the type of game  
	 * \see PlayerSettings.java 
	 * \param name the name of the player 
	 * \param color the piece color of the player
	 */
	public ComputerHardPlayer(Game game, String name, Color color) {
		super(game, name, color);
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: ComputerHardPlayer()"
					+ " BEGIN");
		}
		m_running = true;

		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: ComputerHardPlayer() "
					+ "END");
		}

	}

	/**
	 * The second constructor for setting a type of player, all
	 * data is sent to the super class of Player. Sets the running state of the
	 * thread to be true. 
	 * \param game the type of game  
	 * \see PlayerSettings.java
	 */
	public ComputerHardPlayer(Game game) {
		super(game);
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: ComputerHardPlayer() "
					+ "BEGIN");
		}
		m_running = true;

		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: ComputerHardPlayer() "
					+ "END");
		}
	}

	/**
	 * Loops through each coordinate on the board to check if the coordinate has
	 * a piece located there in the grid or not. Goes from the bottom of the 
	 * grid to be more efficient to find the first available coordinate in
	 * each column
	 * \return a list of available coordinates.
	 */

	private ArrayList<Coordinate> getAvailableMovesConnectFour() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: "
					+ "getAvailableMovesConenctFour() BEGIN");
		}
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		if (getYourTurn()) {
			for (int x = 0; x < getGame().getGrid().getGridWidth(); x++) {
				for (int y = getGame().getGrid().getGridHeight() - 1; y > 
				NEG_ONE; y--) {
					if (getGame().getGrid().getCoordinate(x, y).getValue()
							== Game.PlayerTurn.NONE) {
						Coordinate c1 = new Coordinate(x, y, getGame()
								.getPlayerTurn());
						list.add(c1);
						y = 0;
					}
				}
			}
		}
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: "
					+ "getAvailableMovesConnectFour() END");
		}
		return list;
	}

	/**
	 * Loops through each coordinate on the board to check if the coordinate has
	 * a piece located there in the grid or not. The number of coordinates in
	 * the list will decrease as the number of available moves decreases, will
	 * initially have every board position that does not contain a piece.
	 * \return a list of available coordinates.
	 */
	private ArrayList<Coordinate> getAvailableMoves() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: getAvailableMoves() "
					+ "BEGIN");
		}
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		if (getYourTurn()) {
			for (int y = 0; y < getGame().getGrid().getGridHeight(); y++) {
				for (int x = 0; x < getGame().getGrid().getGridWidth(); x++) {
					Coordinate c1 = new Coordinate(x, y, getGame()
							.getPlayerTurn());

					if (getGame().isValidMove(c1)) {
						list.add(c1);
					}
				}
			}
		}
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: getAvailableMoves() END");
		}
		return list;
	}

	/**
	 * Method which sets the AI position. It gets the list of available comments
	 * from the getAvailableMoves() method. Loops through the list and tries to
	 * find the maximum number of pieces that would be changed and then returns
	 * the maximum turn coordinate as the move to take. Specific to Othello due
	 * to the game logic.
	 * \return Coordinate the Coordinate that would be taken.
	 */
	public Coordinate setAIMoveOthello() throws IndexOutOfBoundsException,
			InterruptedException {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: setAIMoveOthello() "
					+ "BEGIN");
		}
		ArrayList<Coordinate> listTwo = new ArrayList<Coordinate>();
		listTwo = getAvailableMoves();

		int maximum = 0;
		if (!listTwo.isEmpty()) {
			Coordinate takeCoord = listTwo.get(0);
			for (Coordinate coord : listTwo) {
				int move = getGame().moveScore(coord);
				System.out.println("Max: " + maximum + "move: " + move);
				if (move >= maximum) {
					takeCoord = coord;
					maximum = move;
				}
			}
			maximum = 0;

			if (test || m_test) {
				System.out.println("ComputerHardPlayer :: setAIMoveOthello() "
						+ "END");
			}
			return takeCoord;
		} else {
			if (test || m_test) {
				System.out.println("ComputerHardPlayer :: setAIMoveOthello() "
						+ "END");
			}
			return null;
		}

	}
	/**
	 * method that sets the move that the AI will do. The method calls the
	 * getAvailableMoves() method above to get a list of available coordinates
	 * then looks for the move that has the biggest chain of pieces. 
	 * If all positions come out with a zero chain (Initial start of game) then
	 * the position will be picked to a random point.
	 * The game will also check if the opponent has a chain of 3 pieces, if so
	 * it will attempt to block the opponent unless it can win itself.
	 * \return Coordinate to take
	 */

	public Coordinate setAIMove() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: setAIMove() BEGIN");
		}
		ArrayList<Coordinate> listTwo = new ArrayList<Coordinate>();
		listTwo = getAvailableMovesConnectFour();
		Coordinate takeCoord = null;
		int maximum = 0;
		int countCounter = 0;
		if (!listTwo.isEmpty()) {
			takeCoord = listTwo.get(0);
			for (Coordinate coord : listTwo) {
				int move = getGame().moveScore(coord);
				if (move == 0) {
					countCounter++;
				}
				if (move >= maximum) {
					takeCoord = coord;
					maximum = move;
				}
			}
			for (Coordinate coord2 : listTwo) {
				if (getGame().blockOpponentChecker(coord2) == WIN_CHAIN
						&& maximum != WIN_CHAIN) {
					return takeCoord = coord2;

				}
			}

			if (countCounter == listTwo.size()) {
				Random rnd = new Random();
				takeCoord = listTwo.get(rnd.nextInt(listTwo.size()));

			}

			maximum = 0;
		}
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: setAIMove() END");
		}
		return takeCoord;
	}

	/**
	 * method that sets the move that the AI will do. The method calls the
	 * getAvailableMoves() method above to get a list of available coordinates
	 * then looks for the move that has the biggest chain of pieces. 
	 * If all positions come out with a zero chain (Initial start of game) then
	 * the position will be picked to a random point.
	 * The game will also check if the opponent has a chain of 3 pieces, if so
	 * it will attempt to block the opponent unless it can win itself.
	 * Different to the one above as gets a different set of moves.
	 * \return Coordinate to take
	 */
	public Coordinate setAIMoveTicTacToe() throws IndexOutOfBoundsException,
			InterruptedException {
		System.out.println("ITS HERE");
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: setAIMoveTicTacToe() "
					+ "BEGIN");
		}
		ArrayList<Coordinate> listTwo = new ArrayList<Coordinate>();
		listTwo = getAvailableMoves();
		Coordinate takeCoord = null;
		int maximum = 0;
		int countCounter = 0;
		if (!listTwo.isEmpty()) {
			takeCoord = listTwo.get(0);
			for (Coordinate coord : listTwo) {
				int move = getGame().moveScore(coord);
				if (move == 0) {
					countCounter++;
				}
				if (move >= maximum) {
					takeCoord = coord;
					maximum = move;
				}
			}
			for (Coordinate coord2 : listTwo) {
				if (getGame().blockOpponentChecker(coord2) == WIN_CHAIN
						&& maximum != WIN_CHAIN) {
					return takeCoord = coord2;

				}
			}

			if (countCounter == listTwo.size()) {
				Random rnd = new Random();
				takeCoord = listTwo.get(rnd.nextInt(listTwo.size()));

			}

			maximum = 0;
		}
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: setAIMoveTicTacToe() "
					+ "END");
		}
		return takeCoord;
	}

	/**
	 * Method which selects which AI method to do based on the type of game
	 * \return the move that is to be taken
	 */
	private Coordinate gameType() throws IndexOutOfBoundsException,
			InterruptedException {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: gameType() BEGIN");
		}
		Coordinate move = null;
		if (getGame() instanceof Othello) {
			move = setAIMoveOthello();
		} else if (getGame() instanceof ConnectFour) {
			move = setAIMove();
		} else if (getGame() instanceof TicTacToe) {
			move = setAIMoveTicTacToe();
		}
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: gameType() END");
		}
		return move;
	}

	/**
	 * method to make it set the turn of the AI to be it's move, then calls for
	 * the move to be taken \return true if the method completes
	 */
	public boolean isYourMove() throws InterruptedException {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: isYourMove() BEGIN");
		}
		setYourTurn(true);
		if (!(getGame().getPlayer1() instanceof Human || getGame().getPlayer2() 
				instanceof Human)) {
			sendMove();
		}

		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: isYourMove() END");
		}
		return true;
	}

	/**
	 * method that sends the coordinate to take back to the GameWindow class,
	 * Has a thread running so that it can make it 'feel' like the computer is
	 * deciding which piece to take to make the game feel more realistic. Calls
	 * setAIMove to get the available Coordinate. method was created to allow AI
	 * to work. \see GameWindow.java \return true if the method completes
	 */
	public boolean sendMove() throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				if (getRun()) {
					try {
						Coordinate move;
						Thread.sleep(getTime());
						move = gameType();
						if (getYourTurn()) {

							setYourTurn(false);
							getGame().moveMade(move);

						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		return true;
	}

	/**
	 * The default sendMove that was in the Player.java class. Gets the move
	 * that the AI will do from the setAIMove method. Does the same as the
	 * sendMove() method above but is used in case it is called, above method
	 * was created to allow AI to work. \see Player.java \return true if the
	 * method completes
	 */
	public boolean sendMove(Coordinate move) throws InterruptedException {
		boolean test = false;
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: sendMove() BEGIN");
		}
		class MyThread implements Runnable {
			Coordinate m_move;

			public MyThread(Coordinate move) {
				// store parameter for later user
				m_move = move;
			}

			public void run() {
				if (getRun()) {
					try {
						m_move = setAIMove();
					} catch (IndexOutOfBoundsException e) {
						e.printStackTrace();
					}
					if (getYourTurn()) {

						try {
							setYourTurn(false);
							getGame().moveMade(m_move);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		Runnable r = new MyThread(move);
		r.wait(getTime());
		new Thread(r).start();
		if (test || m_test) {
			System.out.println("ComputerHardPlayer :: sendMove() END");
		}
		return true;
	}

	/**
	 * Main to test the creation of games, tests that pieces are actually set.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Game game = new ConnectFour();
		ComputerHardPlayer C4AI;
		ComputerHardPlayer C4AI2;

		C4AI2 = new ComputerHardPlayer(game, "test1", Color.blue);
		C4AI = new ComputerHardPlayer(game, "test2", Color.red);

		if (C4AI.getGame() == game) {
			System.out.println("Set Game Success");
		}
		if (C4AI.getPlayerName() == "test2")
			System.out.println("Name Set");
		if (C4AI.getPlayerColour() == Color.red)
			System.out.println("Color Ok");
		C4AI = new ComputerHardPlayer(game, "test3", Color.yellow);
		C4AI = new ComputerHardPlayer(game);
		if (C4AI.getGame() == game)
			System.out.println("Correct Game Set");

		C4AI.SetTime(GAME_WIDTH);
		if (C4AI.getTime() == GAME_WIDTH)
			System.out.println("Time edited");
		C4AI.SetRun(true);
		if (C4AI.getRun() == true)
			System.out.println("Game Running");

	}

	/** The game board size limits */
	private final static int GAME_WIDTH = 8;
	private final static int GAME_HEIGHT = 8;
	/** The response time value */
	private int m_time = 1500;
	/** The boolean for checking whether a thread should be running */
	private boolean m_running;
	/** To be able to use the value of -1 for the coding conventions */
	private final int NEG_ONE = -1;
	/** The number of pieces in a row before the win */
	private final int WIN_CHAIN = 3;
	/** For testing purposes */
	private boolean m_test = true;
	/** The Game width and height of ConnectFour */

}
