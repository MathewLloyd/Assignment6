import java.awt.Color;
import java.io.IOException;
import java.util.*;

/**
 *  \\file	-TicTacToe.Java
 * 	\author	-Mathew Lloyd A6
 * 	\date	-01/05/2014
 * 	
 * 	\brief	Extends Game with the rules of Othello as specified. 
 * 
 * Methods are move so that the AI can know how good it is, and provides a way 
 * to take a move, return a list of changes after a move has been made. 
 * In the case of TicTacToe the game checks in all directions for maximum number
 * of pieces in a row and then also has methods to check validity of a move.
 */

public class TicTacToe extends Game{
	

	/**
	 * Sets the winner of the game.
	 * 
	 * \param player The winner of the game as an enumerator Game.PlayerTurn.
	 */
	public void setWinner(Game.PlayerTurn player) {
		m_Winner = player;
	}

	/**
	 * Return the winning piece coordinates
	 * 
	 * \return the set which stores the winning piece coordinates
	 */
	public Set<Coordinate> getWin() {
		return m_win;
	}

	/**
	 * Returns the winner of the game.
	 * 
	 * \return The winner of the game as a enumerator Game.PlayerTurn.
	 */
	public Game.PlayerTurn getWinner() {
		return m_Winner;
	}

	/**
	 * Empty the set which stores the winning piece coordinates
	 */
	protected void emptyWin() {
		m_win.clear();
	}

	/**
	 * Construtor for a game of ConnectFour
	 */
	public TicTacToe() {
		super(GAME_WIDTH, GAME_HEIGHT);
	}

	/**
	 * Initialise ConnectFour
	 */
	public void start() throws InterruptedException {
		boolean test = false;

		
		setWindow(new GameWindow(this));
		getPlayer1().isYourMove();
		getWindow().displayPlayerTurn(Game.PlayerTurn.PLAYER1);
		if ((getPlayer1() instanceof ComputerHardPlayer || getPlayer1() 
				instanceof  AIEasy) && (getPlayer2() instanceof Human)) {
			getPlayer1().sendMove();
		}
		startTimer();
		
		if (test || m_test) {
			System.out.println("TicTacToe::Start() END");
		}
	}

	/**
	 * Resets the starting pieces. 
	 * \throws InterruptedException
	 */
	public void resetGame() throws InterruptedException {
		boolean test = false;
		if (test || m_test) {
			System.out.println("TicTacToe::resetGame() BEGIN");
		}
		setWinner(Game.PlayerTurn.NONE);
		setPlayerTurn(PlayerTurn.PLAYER1);
		getPlayer1().isYourMove();
		getWindow().displayPlayerTurn(Game.PlayerTurn.PLAYER1);
		if ((getPlayer1() instanceof ComputerHardPlayer || getPlayer1() 
				instanceof  AIEasy) && (getPlayer2() instanceof Human)) {
			getPlayer1().sendMove();
		}
		if (test || m_test) {
			System.out.println("TicTacToe::resetGame() END");
		}
	}
	
	/**
     * resets the game so a game can be loaded from file
     * 
     * \param p - player whos turn it is 
     * \throws InterruptedException
     */
    public void resetGame(Player p ) throws InterruptedException {
        boolean test = false;
        if (test || m_test) {
            System.out.println("TicTacToe::resetGame() BEGIN");
        }
        setWinner(Game.PlayerTurn.NONE);
        if ((p instanceof Human)) {
            //DO NOTHING
        } else {
            p.sendMove();
        }
        if (test || m_test) {
            System.out.println("TicTacToe::resetGame() END");
        }
    }

	/**
	 * If the board is full there are no more valid moves
	 * 
	 * \return true if there is a valid move
	 */
	private boolean validMove() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("TicTacToe::validMove() BEGIN");
		}
		if (getTurnCount() == GAME_WIDTH * GAME_HEIGHT) {
			if (test)
				System.out.println("TicTacToe::validMove() - No more valid "
				                                                     + "moves");
			return false;
		} else {
			if (test || m_test) {
				System.out.println("TicTacToe::validMove() END");
			}
			return true;
		}

	}

	/**
	 * ConnectFour does not allow a move if the mouse is clicked outside the
	 * grid
	 * 
	 * \return true if there is a move available
	 */
	protected boolean isValidMove(Coordinate xy) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("TicTacToe :: isValidMove() BEGIN");
        }
		// Check that the move lies on the board somewhere vaugely sensible
		if (xy.getX() < 0) { throw new IllegalArgumentException("bad x value");}
		if (xy.getY() < 0) { throw new IllegalArgumentException("bad y value");}
		if (xy.getX() >= GAME_WIDTH)  { 
			throw new IllegalArgumentException("bad x value"); 
		}
		if (xy.getY() >= GAME_HEIGHT) {
			throw new IllegalArgumentException("bad y value"); 
		}
		if (xy.getValue() == Game.PlayerTurn.NONE) {
			throw new IllegalArgumentException("bad turn value"); 
		}
		
		// Check that the space is empty. If not, the move cannot be made.
		if (getGrid().getCoordinate(xy.getX(), xy.getY()).getValue() ==
			Game.PlayerTurn.PLAYER1 || 
			getGrid().getCoordinate(xy.getX(), xy.getY()).getValue() ==
			Game.PlayerTurn.PLAYER2) {if (test || m_test) {
	            System.out.println("TicTacToe :: isValidMove() END");
	        } return false; }
		
		if (test || m_test) {
            System.out.println("TicTacToe :: isValidMove() END");
        }
		return true;
	}

	/**
	 * Called whenever a player has made a move. Processes the move and calls
	 * the GUI and storage classes to store the processed move's data.
	 * 
	 * \param move The move which the player has made as a Coordinate class.
	 * 
	 * \throws InterruptedException
	 */
	public void moveMade(Coordinate move) throws InterruptedException {
		boolean test = false;

		if (test || m_test)
			System.out.println("TicTacToe::MoveMade() - BEGIN");
		if (validateMove(move)) {
			if (test || m_test)
				System.out.println("TicTacToe::MoveMade() - Move is valid");
			ArrayList<Coordinate> changes = takeMove(move);
			for (int i = 0; i < changes.size(); i++) {
				getGrid().setCoordinate(changes.get(i));
			}
			getWindow().displayGrid(getGrid());
			getWindow().SetAnimation("null", changes);
			setPlayer1Score(0);
			setPlayer2Score(0);
			for (int i = 0; i < getGrid().getGridWidth(); i++) {
				for (int j = 0; j < getGrid().getGridHeight(); j++) {
					if (getGrid().getCoordinate(i, j).getValue() == 
					                                       PlayerTurn.PLAYER1) {
						setPlayer1Score(getPlayer1Score() + 1);
					} else if (getGrid().getCoordinate(i, j).getValue() == 
					                                       PlayerTurn.PLAYER2) {
						setPlayer2Score(getPlayer2Score() + 1);
					}
				}
			}

			getWindow().updateScore(getPlayer1Score(), getPlayer2Score());
			setPlayerTurn(nextPlayer());
			getWindow().displayPlayerTurn(getPlayerTurn());
			setTurnCount(getTurnCount() + 1);
		}

		if (isOver()) {
			if (test || m_test)
				System.out.println("TicTacToe::MoveMade() - TicTacToe is "
				                                                  + "finished");
			new EndDisplay(this);
			emptyWin();
		} else {
			if (getPlayerTurn() == PlayerTurn.PLAYER1) {
				if (test || m_test)
					System.out
							.println("TicTacToe::MoveMade() - Player1 next");
				getPlayer1().isYourMove();
			} else if (getPlayerTurn() == PlayerTurn.PLAYER2) {
				if (test || m_test)
					System.out
							.println("TicTacToe::MoveMade() - Player2 next");
				getPlayer2().isYourMove();
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::MoveMade() - END");
	}

	/**
	 * Checks if there is a valid move in a column. If there is, the counter is
	 * placed in the lowest available place e.g. on the bottom or on top of
	 * another counter
	 * 
	 * \return a list containing the taken move
	 */
	protected ArrayList<Coordinate> takeMove(Coordinate xy) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::takeMove() - BEGIN");
		Grid grid = getGrid();
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();

		for (int y = GAME_HEIGHT - 1; y >= 0; y--) {
			result.add(xy);
		
		}
		if (test || m_test)
			System.out.println("TicTacToe::takeMove() - END");
		return result;
	}

	/**
	 * Sets the ways in which a game can be ended
	 * 
	 * \return true if the game is over
	 */
	public boolean isOver() {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::isOver() - BEGIN");
		checkWinner();
		Iterator<Coordinate> iterator = m_win.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		if (getWinner() == Game.PlayerTurn.PLAYER1
				                    || getWinner() == Game.PlayerTurn.PLAYER2) {
			if (test || m_test)
				System.out.println("TicTacToe::IsOver() - A Player has won");
			getTimer().setRunning();
			return true;
		} else {
			if (test || m_test)
				System.out.println("TicTacToe::IsOver() - Valid move");
			return (!validMove());
		}
	}

	/**
	 * Sets how the game will check who the winner is
	 */
	private void checkWinner() {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::checkWinner() - BEGIN");
		for (int y = 0; y < GAME_HEIGHT; y++) {
			for (int x = 0; x < GAME_WIDTH; x++) {

				checkRight(x, y);
				checkDown(x, y);
				checkDiagonalUp(x, y);
				checkDiagonalDown(x, y);
			}

		}
		if (test || m_test)
			System.out.println("TicTacToe::checkWinner() - END");
	}

	/**
	 * Checks right from the last counter that has been placed to check if there
	 * are 4 counter of the same colour in a row
	 * 
	 * \param x the x coordinate of the piece that has been placed
	 * \param y the y coordinate of the piece that has been placed
	 */
	private void checkRight(int x, int y) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::checkRight() - BEGIN");
		Grid grid = getGrid();

		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (x < GAME_WIDTH - COUNT3) {
				if ((grid.getCoordinate(x + 1, y).getValue() == Player) && 
				        (grid.getCoordinate(x + COUNT2, y).getValue() == Player)
				        && (grid.getCoordinate(x + COUNT3, y).getValue() == 
				                                                      Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x + 1, y));
					m_win.add(new Coordinate(x + COUNT2, y));
					m_win.add(new Coordinate(x + COUNT3, y));
					setWinner(Player);
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::checkRight() - END");
	}

	/**
	 * Count right pieces from the last counter that has been placed
	 * 
	 * \param xy the piece that has been placed
	 * 
	 * \param Player check if the square is empty
	 * 
	 * \return count number of pieces
	 */
	private int countRight(Coordinate xy, Game.PlayerTurn Player) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::countRight() - BEGIN");
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (x < GAME_WIDTH - 1) {
				loopCount = 1;
			}
			if (x < GAME_WIDTH - COUNT2) {
				loopCount = COUNT2;
			}
			if (x < GAME_WIDTH - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x + z), y)
						.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::countRight() - END");
		return count;
	}

	/**
	 * Count left pieces from the last counter that has been placed
	 * 
	 * \param xy the piece that has been placed
	 * 
	 * \param Player check if the square is empty
	 * 
	 * \return count number of pieces
	 */
	private int countLeft(Coordinate xy, Game.PlayerTurn Player) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::countLeft() - BEGIN");
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (x > 0) {
				loopCount = 1;
			}
			if (x > 1) {
				loopCount = COUNT2;
			}
			if (x > COUNT2) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x - z), y)
						.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::countLeft() - END");
		return count;
	}

	/**
	 * Checks downwards from the last counter that has been placed to see if
	 * there are 4 counter of the same colour in a row
	 * 
	 * \param x the x value to check
	 * 
	 * \param y the y value to check
	 * 
	 */
	private void checkDown(int x, int y) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::checkDown() - BEGIN");
		Grid grid = getGrid();

		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - COUNT3) {
				if ((grid.getCoordinate(x, y + 1).getValue() == Player) && 
				        (grid.getCoordinate(x, y + COUNT2).getValue() == Player)
						&& (grid.getCoordinate(x, y + COUNT3).getValue() == 
						                                              Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x, y + 1));
					m_win.add(new Coordinate(x, y + COUNT2));
					m_win.add(new Coordinate(x, y + COUNT3));
					setWinner(Player);
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::checkDown() - END");
	}

	/**
	 * Count down pieces from the last counter that has been placed
	 * 
	 * \param xy the piece that has been placed
	 * 
	 * \param Player check if the square is empty
	 * 
	 * \return count number of pieces
	 */
	private int countDown(Coordinate xy, Game.PlayerTurn Player) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::countDown() - BEGIN");
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - 1) {
				loopCount = 1;
			}
			if (y < GAME_HEIGHT - COUNT2) {
				loopCount = COUNT2;
			}
			if (y < GAME_HEIGHT - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate(x, (y + z))
						                                            .getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::countDown() - END");
		return count;
	}

	/**
	 * Find the longest chain on the gamebaord and return the length of the
	 * chain
	 * 
	 * \param xy the piece that has been placed
	 * 
	 * \return count the length of the chain
	 * 
	 */
	public int moveScore(Coordinate xy) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::moveScore() - BEGIN");
		int count = 0;
		int countDown = countDown(xy, getPlayerTurn());
		int countDiagDown = countDiagonalDown(xy, getPlayerTurn());
		int countDiagUp = countDiagonalUp(xy, getPlayerTurn());
		int countLeft = countLeft(xy, getPlayerTurn());
		int countRight = countRight(xy, getPlayerTurn());

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(countDown);
		list.add(countDiagDown);
		list.add(countDiagUp);
		list.add(countLeft);
		list.add(countRight);

		int equalsCounter = 0;
		for (Integer listIter : list) {
			if (listIter == count) {
				equalsCounter++;
			} else if (listIter > count) {
				count = listIter;
			}
		}

		if (equalsCounter == list.size()) {
			count = 0;
		}
		if (test || m_test)
			System.out.println("TicTacToe::moveScore() - END");
		return count;

	}

	/**
	 * block the opponent chain if the length of the chain is 3
	 * 
	 * \param xy the piece that has been placed from opponent
	 * 
	 * \return count if the length of the chain is 3
	 */
	public int blockOpponentChecker(Coordinate xy) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::blockOpponentChecker() - BEGIN");
		Game.PlayerTurn Player;

		if (getPlayerTurn() == Game.PlayerTurn.PLAYER1) {
			Player = Game.PlayerTurn.PLAYER2;
		} else {
			Player = Game.PlayerTurn.PLAYER1;
		}

		Coordinate c1 = new Coordinate(xy.getX(), xy.getY());
		int count = 0;
		int countDown = countDown(c1, Player);
		int countDiagDown = countDiagonalDown(c1, Player);
		int countDiagUp = countDiagonalUp(c1, Player);
		int countLeft = countLeft(c1, Player);
		int countRight = countRight(c1, Player);

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(countDown);
		list.add(countDiagDown);
		list.add(countDiagUp);
		list.add(countLeft);
		list.add(countRight);

		for (Integer listIter : list) {
			if (listIter == COUNT3) {
				return COUNT3;
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::blockOpponentChecker() - END");
		return count;
	}

	/**
	 * Count diagonal down pieces from the last counter that has been placed
	 * 
	 * \param xy the piece that has been placed
	 * 
	 * \param Player check if the square is empty
	 * 
	 * \return count number of pieces
	 */
	private int countDiagonalDown(Coordinate xy, Game.PlayerTurn Player) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::countDiagonalDown() - BEGIN");
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - 1 && x < GAME_WIDTH - 1) {
				loopCount = 1;
			}
			if (y < GAME_HEIGHT - COUNT2 && x < GAME_WIDTH - COUNT2) {
				loopCount = COUNT2;
			}
			if (y < GAME_HEIGHT - COUNT3 && x < GAME_WIDTH - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x + z), (y + z))
						.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::countDiagonalDown() - END");
		return count;
	}

	/**
	 * Checks downwards in a diagonal direction from the last counter that has
	 * been placed to check if there are 4 counter of the same colour in a row
	 * 
	 * \param x the x value to check
	 * 
	 * \param y the y value to check
	 */
	private void checkDiagonalDown(int x, int y) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::checkDiagonalDown() - BEGIN");
		Grid grid = getGrid();

		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - COUNT3 && x < GAME_WIDTH - COUNT3) {
				if ((grid.getCoordinate(x + 1, y + 1).getValue() == Player) && 
				       (grid.getCoordinate(x + COUNT2, y + COUNT2).getValue() ==
				       Player) && (grid.getCoordinate(x + COUNT3, y + COUNT3)
						                               .getValue() == Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x + 1, y + 1));
					m_win.add(new Coordinate(x + COUNT2, y + COUNT2));
					m_win.add(new Coordinate(x + COUNT3, y + COUNT3));
					setWinner(Player);
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::checkDiagonalDown() - END");
	}

	/**
	 * Checks upwards in a diagonal direction from the last counter that has
	 * been placed to check if there are 4 counters of the same colour in a row
	 * 
	 * \param x the x value to check \param y the y value to check
	 */
	private void checkDiagonalUp(int x, int y) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::checkDiagonalUp() - BEGIN");
		Grid grid = getGrid();

		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (y > COUNT2 && x < GAME_WIDTH - COUNT3) {
				if ((grid.getCoordinate(x + 1, y - 1).getValue() == Player)
						&& (grid.getCoordinate(x + COUNT2, y - COUNT2)
								.getValue() == Player)
						&& (grid.getCoordinate(x + COUNT3, y - COUNT3)
								.getValue() == Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x + 1, y - 1));
					m_win.add(new Coordinate(x + COUNT2, y - COUNT2));
					m_win.add(new Coordinate(x + COUNT3, y - COUNT3));
					setWinner(Player);
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::checkDiagonalUp() - END");
	}

	/**
	 * Count diagonal up pieces from the last counter that has been placed
	 * 
	 * \param xy the piece that has been placed
	 * 
	 * \param Player check if the square is empty
	 * 
	 * \return count number of pieces
	 * 
	 */
	private int countDiagonalUp(Coordinate xy, Game.PlayerTurn Player) {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::countDiagonalUp() - BEGIN");
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (y > 1 && x < GAME_WIDTH - 1) {
				loopCount = 1;
			}
			if (y > COUNT2 && x < GAME_WIDTH - COUNT2) {
				loopCount = COUNT2;
			}
			if (y > COUNT3 && x < GAME_WIDTH - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x + z), (y - z))
						.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		if (test || m_test)
			System.out.println("TicTacToe::countDiagonalUp() - END");
		return count;
	}

	/**
	 * Pass the turn to next player
	 * 
	 */

	protected PlayerTurn nextPlayer() {
		boolean test = false;
		if (test || m_test)
			System.out.println("TicTacToe::PlayerTurn() - BEGIN");
		if (getPlayerTurn() == Game.PlayerTurn.PLAYER1) {
			if (test || m_test)
				System.out.println("TicTacToe::PlayerTurn() - PLAYER1 turn "
				                                                 + "finished ");
			return Game.PlayerTurn.PLAYER2;
		} else {
			if (test || m_test)
				System.out.println("TicTacToe::PlayerTurn() - PLAYER2 turn "
				                                                 + "finished ");
			return Game.PlayerTurn.PLAYER1;
		}
	}

	/**
	 * calculate the player who has won the game.
	 * 
	 * \return PlayTurn the winner
	 */
	public PlayerTurn isWinner() {
		return getWinner();
	}

	/**
	 * main used to run tests which create player and a game
	 * 
	 * \throws IOException \throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException,
			IOException {
		boolean test = true;
		if (test || m_test) {
			TicTacToe game = new TicTacToe();
			Player player1 = new Human(game);
			Player player2 = new ComputerHardPlayer(game);
			player1.setPlayerName("Gavin");
			player2.setPlayerName("So");
			player1.setPlayerColour(Color.DARK_GRAY);
			player2.setPlayerColour(Color.BLUE);
			game.setPlayer1(player1);
			game.setPlayer2(player2);
			if (player1.getPlayerName() == "Gavin")
				System.out.println("Player Name Added");
			if(player1.getGame() == game)
				System.out.println("Game Added Correctly");
			if(player1.getPlayerColour() == Color.DARK_GRAY)
				System.out.println("Color Added Correctly");
			if (player2.getPlayerName() == "So")
				System.out.println("Player Name Added");
			if(player2.getGame() == game)
				System.out.println("Game Added Correctly");
			if(player2.getPlayerColour() == Color.BLUE)
				System.out.println("Color Added Correctly");
			Coordinate testCooor = new Coordinate(COUNT2, COUNT3,
					                                        PlayerTurn.PLAYER1);
			Coordinate testCooor2 = new Coordinate(COUNT2, COUNT3,
					                                        PlayerTurn.PLAYER2);
			game.start();
			for (int i = 0; i < GAME_HEIGHT; i++) {
				game.moveMade(testCooor);
				System.out.println("Player 1 valid move :"
						                        + game.validateMove(testCooor));
				game.moveMade(testCooor2);
				System.out.println("Player 2 valid move :"
						                       + game.validateMove(testCooor2));
			}
			

		}
	}

	/** Othello gameboard size */
	private final static int GAME_WIDTH  = 8;
	/** Othello gameboard size */
	private final static int GAME_HEIGHT = 8;
	/** starting position */
	private final static int COORD1 = 3;
	/** starting position */
	private final static int COORD2 = 4;
	/** store message  */
	private String m_passmsg = "There are no available moves, the turn has been"
	                                               + " passed to the opponent.";
	/** store winning pieces  */
	private Set<Coordinate> m_win = new HashSet<Coordinate>();
	/** store the pieces that will be flipped   */
	private Set<Coordinate> m_p1 = new HashSet<Coordinate>();
	/** store the pieces that will be flipped   */
	private Set<Coordinate> m_p2 = new HashSet<Coordinate>();
	/** test variable */
    private static boolean m_test = false;
	/** use to count the chain in different direction */
	private final static int COUNT2 = 2;
	/** use to count the chain in different direction */
	private final static int COUNT3 = 3;
	private Game.PlayerTurn m_Winner;
}
