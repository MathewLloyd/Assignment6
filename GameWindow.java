import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
/**
 * \\file GameWindow.java
 * \author Gavin Tsang 658679 A4, Chun Kit So A5
 * \date 27/02/2014
 * 
 * \brief This class is used to initialise the main GUI window.
 * 
 * The main GUI window contains all the graphical elements and user interfaces.
 * Also provides methods to manipulate the elements within the window such as
 * displaying error messages or updating the grid and labels.
 * --CHANGED 01/05/14 to reflect the new classes for hard AI & adding the new 
 * files for saving and loading of the new game
 */
public class GameWindow extends JFrame {
	
	/**
	 * Returns the main game class which this window is taking commands from.
	 * 
	 * \return m_gameControl - the pointer towards the game class that is 
	 * currently being used.
	 */
	public Game getGame() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: getGame() BEGIN");
        }
		if (test || m_test) {
            System.out.println("GameWindow :: getGame() END");
        }
		return m_gameControl;
	}
	
	/**
	 * Sets the game class with which this window is to take and send commands
	 * from.
	 * 
	 * \param game The game class which should currently be used.
	 * \return Returns TRUE if successful.
	 */
	private boolean setGame(Game game) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: setGame() BEGIN");
        }
		m_gameControl = game;
		if (test || m_test) {
            System.out.println("GameWindow :: setGame() END");
        }
		return true;
	}
	
	/**
	 * Returns the drawing class which controls all the graphical systems in the
	 * window.
	 * 
	 * \return m_drawingControl - a pointer towards the drawing class.
	 */
	public Drawing getDrawing() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: getDrawing() BEGIN");
        }
		if (test || m_test) {
            System.out.println("GameWindow :: getDrawing() END");
        }
		return m_drawingControl;
	}
	
	/**
	* Method responsible for setting AI move to Drawing class
	* \param move - coordinate of AI move
	*/
	public void SetAImove(Coordinate move){
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: SetAImove() BEGIN");
        }
		getDrawing().SetAImove(move);
		if (test || m_test) {
            System.out.println("GameWindow :: SetAImove() END");
        }
	}
	
	/**
	* Method responsible for passing animation data to Drawing class
	* \param type - type of animation that is either flip or fall
	* \param changes - the list stores the pieces which need the animation
	*/
	public void SetAnimation(String type, ArrayList<Coordinate> changes){
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: SetAnimation() BEGIN");
        }
		getDrawing().SetAnimation(type, changes);
		if (test || m_test) {
            System.out.println("GameWindow :: SetAnimation() END");
        }
	}
	
	/**
	 * Method to set the game is not over after the game restarts
	 * \param isOver - the boolean true means the game is over
	 */
	public void SetOver(boolean isOver){
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: SetOver() BEGIN");
        }
		getDrawing().SetOver(isOver);
		if (test || m_test) {
            System.out.println("GameWindow :: SetOver() END");
        }
	}		
	
	
	/**
	 * Sets the drawing class which controls all the graphical systems in the
	 * window.
	 * 
	 * \param drawing The instance of the drawing class which is to be used.
	 * 
	 * \return TRUE if successful.
	 */
	private boolean setDrawing(Drawing drawing) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: SetDrawing() BEGIN");
        }
		m_drawingControl = drawing;
		if (test || m_test) {
            System.out.println("GameWindow :: SetDrawing() END");
        }
		return true;
	}
	
	/**
	 * Returns the control class which processes the users inputs commands.
	 * 
	 * \return m_controlsControl -Returns a pointer towards the control class 
	 * being used.
	 */
	public Controls getControls() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: getControls() BEGIN");
        }
		if (test || m_test) {
            System.out.println("GameWindow :: getControls() END");
        }
		return m_controlsControl;
	}
	
	/**
	 * Sets the control class which is used to take in and process the user's
	 * comands on the main GameWindow.
	 * 
	 * \param controls The instance of the controls class which is to be used.
	 * 
	 * \return TRUE if successful.
	 */
	private boolean setControls(Controls controls) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: setControls() BEGIN");
        }
		m_controlsControl = controls;
		if (test || m_test) {
            System.out.println("GameWindow :: setControls() END");
        }
		return true;
	}
	
	/**
	 * Constructor creates the window, initialises subControl classes Controls
	 * and Drawing and draws on the component panels provided by the
	 * aforementioned classes.
	 * 
	 * \param game Pointer to indicate which game process is currently using
	 * this Gamewindow.
	 */
	public GameWindow(Game game) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: GameWindow() BEGIN");
        }
		boolean m_Trace = true;
		
		if(m_Trace) System.out.println
			("GameWindow::GameWindow() - window initializing");
		if(m_Trace) System.out.println
			("GameWindow::GameWindow() - Linked game is " + game.getClass());
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		
		setGame(game);
		setDrawing(new Drawing(m_gameControl));
		setControls(new Controls(this));
		
		setLayout(layout);
		
		JComponent grid = getDrawing().getGridPanel();
		
		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(grid, c);
		add(grid);
		
		c.gridx = 1;
		layout.setConstraints(getDrawing().getSideBarPanel(), c);
		add(getDrawing().getSideBarPanel());
			  
		m_menubar = new JMenuBar();
		add(m_menubar);
		m_newGame = new JMenuItem("New Game");
		m_menubar.add(m_newGame);
		m_resetGame = new JMenuItem("Restart Game");
		m_menubar.add(m_resetGame);
        m_save = new JMenuItem("Save");
		m_menubar.add(m_save);
		m_load = new JMenuItem("Load");
		m_menubar.add(m_load);
		m_exit = new JMenuItem("Exit");
		m_menubar.add(m_exit);
		setJMenuBar(m_menubar);
		
		Handler handler = new Handler();
		
		m_newGame.addActionListener(handler);
	    m_resetGame.addActionListener(handler);
		m_save.addActionListener(handler);
        m_load.addActionListener(handler);
        m_exit.addActionListener(handler);


		setTitle("Boardgame");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
        setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		if(m_Trace) { System.out.println
			("GameWindow::GameWindow() - window initialized");
		}
		if (test || m_test) {
            System.out.println("GameWindow :: GameWindow() END");
        }
	}
	
	/**
	 * Called whenever the player's turn changes.
	 * 
	 * \param player indicates which player's turn it is as the Enumerator
	 * "PlayerTurn".
	 * 
	 * \return Returns TRUE if successful.
	 */
	public boolean displayPlayerTurn(Game.PlayerTurn player) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: displayPlayerTurn() BEGIN");
        }
		if(player == Game.PlayerTurn.PLAYER1){
			getDrawing().setPlayerTurn(player, 
				!(m_gameControl.getPlayer1() instanceof Human));
		} else {
			getDrawing().setPlayerTurn(player, 
				!(m_gameControl.getPlayer2() instanceof Human));
		}
		if (test || m_test) {
            System.out.println("GameWindow :: displayPlayerTurn() END");
        }
		return true;
	}

	/**
	 * Called whenever the displayed grid needs to be updated.
	 * 
	 * \param grid The grid class which is to be used to update the GUI.
	 * 
	 * \return Returns TRUE if successful.
	 */
	public boolean displayGrid(Grid grid) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: displayGrid() BEGIN");
        }
		getDrawing().setGrid(grid, m_gameControl);
		if (test || m_test) {
            System.out.println("GameWindow :: displayGrid() END");
        }
		return true;
	}
	
	/**
	 * Called to pass the invalid coordinate to gameboard graphics.
	 * 
	 * \param move -  the position of invalid move
	 * \return Returns TRUE if successful.
	 */
	public boolean displayInvalidMove(Coordinate move) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: displayInvalidMove() BEGIN");
        }
		getDrawing().getGridPanel().SetValid(false, move);
		getDrawing().getGridPanel().repaint();
		if (test || m_test) {
            System.out.println("GameWindow :: displayInvalidMove() END");
        }
		return true;
	}
	
	/**
	 * Called to pass whether the move is valid to the gameboard graphics.
	 * 
	 * \param valid -  boolean whether the move is valid or not
	 * \return Returns TRUE if successful.
	 */
	public boolean displayInvalidMove(boolean valid) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: displayInvalidMove(boolean) "
                                                                     + "BEGIN");
        }
		getDrawing().getGridPanel().SetValid(true);
		if (test || m_test) {
            System.out.println("GameWindow :: displayInvalidMove(boolean) END");
        }
		return true;
	}

	public void Displaymessage(String msg) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: Displaymessage() BEGIN");
        }
		getDrawing().Message(msg);
		if (test || m_test) {
            System.out.println("GameWindow :: Displaymessage() END");
        }
	}

	/**
	 * Called to update the player's scores displayed on screen.
	 * 
	 * \param player1Score The new score to be displayed for player 1.
	 * 
	 * \param player2Score The new score to be displayed for player 2.
	 * 
	 * \return Returns TRUE if successful.
	 */
	public boolean updateScore(int player1Score, int player2Score) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: updateScore() BEGIN");
        }
		getDrawing().setPlayer1Score(player1Score);
		getDrawing().setPlayer2Score(player2Score);
		if (test || m_test) {
            System.out.println("GameWindow :: updateScore() END");
        }
		return true;
	}
	
	/**
	 * Called whenever the user has made a move on the grid UI and passes
	 * it onto the correct Player class who's turn it currently is.
	 * 
	 * \param move The move that the user has made as a Coordinate.
	 * 
	 * \return Returns TRUE if successful.
	 * \throws InterruptedException 
	 */
	public boolean moveMade(Coordinate move) throws InterruptedException {
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: moveMade() BEGIN");
        }
		//System.out.println(getGame().getPlayerTurn());
		if (getGame().getPlayerTurn() == Game.PlayerTurn.PLAYER1) {
			move.setValue(Game.PlayerTurn.PLAYER1);
			getGame().getPlayer1().sendMove(move);
			if (getGame().getPlayer2() instanceof ComputerHardPlayer
				|| getGame().getPlayer2() instanceof AIEasy) {
				getGame().getPlayer2().sendMove();
			}
		} else {
			move.setValue(Game.PlayerTurn.PLAYER2);
			getGame().getPlayer2().sendMove(move);
			if (getGame().getPlayer1() instanceof ComputerHardPlayer
					|| getGame().getPlayer1() instanceof AIEasy) {
				getGame().getPlayer1().sendMove();

			}
		}
		if (test || m_test) {
            System.out.println("GameWindow :: moveMade() END");
        }
		return true;
	}
	
	/**
	 * Called when the user clicks the restart game button on the menu bar
	 * Stop all the threads and recreate the current game
	 * \returns true on success
	 */
	private boolean restart(){
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: restart() BEGIN");
        }
		Game m_game;
		Player m_player1;
		Player m_player2;
		if(m_gameControl instanceof Othello){
			m_game = new Othello();
		} else if(m_gameControl instanceof ConnectFour){
			m_game = new ConnectFour();
		}else{
			m_game = new TicTacToe();
		}
    	
		getDrawing().getGridPanel().SetRun(false);
		if(m_gameControl.getPlayer1() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer1())).SetRun(false);
			m_player1 = new ComputerHardPlayer(m_game,m_gameControl.
					getPlayer1().getPlayerName(),m_gameControl.getPlayer1()
					.getPlayerColour());
		} else if(m_gameControl.getPlayer1() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer1())).SetRun(false);
			m_player1 = new ComputerHardPlayer(m_game,m_gameControl.
					getPlayer1().getPlayerName(),m_gameControl.getPlayer1()
					.getPlayerColour());
		} else if(m_gameControl.getPlayer1() instanceof AIEasy){
			((AIEasy)(m_gameControl.getPlayer1())).SetRun(false);
			m_player1 = new AIEasy(m_game,m_gameControl.getPlayer1().
			      getPlayerName(),m_gameControl.getPlayer1().getPlayerColour());
		} else {
			m_player1 = new Human(m_game,m_gameControl.getPlayer1().
			      getPlayerName(),m_gameControl.getPlayer1().getPlayerColour());
		}
		if(m_gameControl.getPlayer2() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer2())).SetRun(false);
			m_player2 = new ComputerHardPlayer(m_game,m_gameControl
					.getPlayer2().getPlayerName(),m_gameControl.getPlayer2()
					.getPlayerColour());
		} else if(m_gameControl.getPlayer2() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer2())).SetRun(false);
			m_player2 = new ComputerHardPlayer(m_game,m_gameControl.
					getPlayer2().getPlayerName(),m_gameControl.getPlayer2()
					.getPlayerColour());
		} else if(m_gameControl.getPlayer2() instanceof AIEasy){
			((AIEasy)(m_gameControl.getPlayer2())).SetRun(false);
			m_player2 = new AIEasy(m_game,m_gameControl.getPlayer2().
			      getPlayerName(),m_gameControl.getPlayer2().getPlayerColour());
		} else {
			m_player2 = new Human(m_game,m_gameControl.getPlayer2().
			      getPlayerName(),m_gameControl.getPlayer2().getPlayerColour());
		}		      		
		getGame().getTimer().setRunning();
		m_game.setPlayer1(m_player1);
		m_game.setPlayer2(m_player2);				
		try {
			m_game.start();
		dispose();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (test || m_test) {
            System.out.println("GameWindow :: restart() END");
        }
		return true;
	}
	
	/**
	 * Called when the user clicks the new game button on the menu bar
	 * Stop all the threads and recreate the game chooser window
	 * \return true on success
	 */
	private boolean returnMainWindow(){
		boolean test = false;
		if (test || m_test) {
            System.out.println("GameWindow :: returnMainWindow() BEGIN");
        }
		getDrawing().getGridPanel().SetRun(false);
		if(m_gameControl.getPlayer1() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer1())).SetRun(false);
		} else if(m_gameControl.getPlayer1() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer1())).SetRun(false);
		} else if(m_gameControl.getPlayer1() instanceof AIEasy){
			((AIEasy)(m_gameControl.getPlayer1())).SetRun(false);
		}
		if(m_gameControl.getPlayer2() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer2())).SetRun(false);
		} else if(m_gameControl.getPlayer2() instanceof ComputerHardPlayer){
			((ComputerHardPlayer)(m_gameControl.getPlayer2())).SetRun(false);
		} else if(m_gameControl.getPlayer2() instanceof AIEasy){
			((AIEasy)(m_gameControl.getPlayer2())).SetRun(false);
		}	
		getGame().getTimer().setRunning();
        dispose();
        new GameSelecter();
		if (test || m_test) {
            System.out.println("GameWindow :: returnMainWindow() END");
        }
        return true;
	}
	
	private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
    		boolean test = false;
    		if (test || m_test) {
                System.out.println("GameWindow-Handler :: actionPerformed() "
                                                                     + "BEGIN");
            }
            if (e.getSource() == m_newGame) {
				returnMainWindow();
            }
            
            if (e.getSource() == m_resetGame) {
            	restart();
            }
            
           if (e.getSource() == m_save) {
				//System.out.println("Save");
				Saver saver;
				if (getGame().isOver()) {
                    Displaymessage(m_gameOverSave);
				} else {
    				if ((getGame().getPlayerTurn() == Game.PlayerTurn.PLAYER1
    						&& getGame().getPlayer1() instanceof Human)
    						|| (getGame().getPlayerTurn() == 
    						                             Game.PlayerTurn.PLAYER2
    						&& getGame().getPlayer2() instanceof Human)
    						|| (!(getGame().getPlayer1() instanceof Human)
    						&& !(getGame().getPlayer2() instanceof Human))) {
    					if (getGame() instanceof Othello) {
    						saver = new OthelloSaver(getGame());
    					} else if (getGame() instanceof ConnectFour){
    						saver = new ConnectFourSaver(getGame());
    					}else{
    						saver = new TicTacToeSaver(getGame());
    					}
    
    					saver.saveGrid(getGame().getGrid().toString());
    					saver.savePlayer1(getGame().getPlayer1().toString());
    					saver.savePlayer2(getGame().getPlayer2().toString());
    					saver.saveTimer(getGame().getTimer().toString());
    					Displaymessage(m_saveMessage);
    				} else {
    					Displaymessage(m_aiTurnsave);
    				}
				}
			}
            
            if (e.getSource() == m_load) {
                Game gameToLoad;
                Loader loader;
                
                if (getGame() instanceof Othello) {
                    gameToLoad = new Othello();
                    loader = new OthelloLoader(gameToLoad);
                } else if(getGame() instanceof ConnectFour){
                    gameToLoad = new ConnectFour();
                    loader = new ConnectFourLoader(gameToLoad);
                }else{
                	gameToLoad = new TicTacToe();
                	loader = new TicTacToeLoader(gameToLoad);
                }
                
                if (checkValid(loader, gameToLoad)) {
                    //DO NOTHING
                } else {
                    returnMainWindow();
                }
            }
            
            if (e.getSource() == m_exit) {
                System.exit(0);
            }
            
    		if (test || m_test) {
                System.out.println("GameWindow-Handler :: actionPerformed() "
                                                                       + "END");
            }
        }
        
        /**
         * checks whether the load is valid, if so it loads the game
         * 
         * \param l - reference to the loader object
         * \param g - reference to new game object
         */
        private boolean checkValid(Loader l, Game g) {
            boolean test = false;
            if (test || m_test) {
                System.out.println("GameWindow-Handler :: checkValid() BEGIN");
            }
            getGame().getTimer().setRunning();
            getDrawing().getGridPanel().SetRun(false);
            getGame().getPlayer1().SetRun(false);
            getGame().getPlayer2().SetRun(false);

            
            if(m_gameControl.getPlayer1() instanceof ComputerHardPlayer){
                ((ComputerHardPlayer)(m_gameControl.getPlayer1()))
                .SetRun(false);
            } else if(m_gameControl.getPlayer1() instanceof ComputerHardPlayer){
                ((ComputerHardPlayer)(m_gameControl.getPlayer1()))
                .SetRun(false);
            } else if(m_gameControl.getPlayer1() instanceof AIEasy){
                ((AIEasy)(m_gameControl.getPlayer1())).SetRun(false);
            }
            
            if (l.getValid()) {
                g.getGrid().setGrid(l.getGridArray());
                g.setPlayer1(l.getPlayer1());
                g.setPlayer2(l.getPlayer2());

                try {
                    g.resumeGame();
                    g.startTimer(l.getTimer());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                dispose();

                if (test || m_test) {
                    System.out.println("GameWindow :: checkValid() END");
                }
                
                return true;  
            }else {
                JOptionPane.showMessageDialog(null, "ERROR Loading File",
                "Load ERROR",JOptionPane.ERROR_MESSAGE);
                
                if (test || m_test) {
                    System.out.println("Handler :: checkValid() END");
                }
                
                return false;
            }
        }
	}
	
	/**
	 * Test method.
	 * 
	 * \param args
	 * \throws InterruptedException 
	 */
	public static void main(String args[]) throws InterruptedException {
		ConnectFour game = new ConnectFour();
		Player player1 = new AIEasy(game);
		Player player2 = new AIEasy(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.RED);
		player2.setPlayerColour(Color.YELLOW);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		game.getWindow().Displaymessage("HELLO");
		game.getWindow().restart();
	}
	
	/** Strings for save game for message box */
	private String m_saveMessage = "Game saved!";
	private String m_aiTurnsave = "Can't save game while AI's trun!";
	private String m_gameOverSave = "Can't save when game is over!";
	/** reference to current game object*/
	private Game m_gameControl;
	/** reference to drawing object */
	private Drawing m_drawingControl;
	/** reference to Controls object */
	private Controls m_controlsControl;
	/** menu bar */
	private JMenuBar m_menubar;
	/** menu items */
	private JMenuItem m_exit;
	private JMenuItem m_save;
	private JMenuItem m_load;
	private JMenuItem m_newGame;
	private JMenuItem m_resetGame;
	/** test variables */
	private boolean m_test = false;
}
