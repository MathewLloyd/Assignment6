import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * \\file GameSelecter.java
 * \author Daniel 709547 A4, Mathew Lloyd A6
 * \date 20/02/2014
 * 
 * \brief Class which handles the choice of starting a new game
 * 
 * This class deals with handling giving the user
 * the option of starting either a new othello or
 * connect 4 game at the start of the application
 * and if they choose to start a new game.
 * --CHANGED 01/05/14 To add the selection for the new game
 */
public class GameSelecter {
	/**
	 * Setter method to set the JButton m_connect4
	 * 
	 * \param newConnect4
	 * \return true on success
	 */
	public boolean setConnect4Button(JButton newConnect4) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: setConnect4Button() BEGIN");
        }
        
		m_connect4 = newConnect4;
		
        if (test || m_test) {
            System.out.println("GameSelecter :: setConnect4Button() END");
        }
		
		return true;
	}
	
	/**
	 * Setter method to set the JButton m_othello
	 * 
	 * \param newOthello
	 * \return true on success
	 */
	public boolean setOthelloButton(JButton newOthello) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: setOthelloButton() BEGIN");
        }
        
		m_othello = newOthello;
		
        if (test || m_test) {
            System.out.println("GameSelecter :: setOthelloButton() END");
        }
		
		return true;
	}
	
	/**
	 * Setter method to set the JButton m_ticTacToe
	 * 
	 * \param newTicTacToe
	 * \return true on success
	 */
	public boolean setTicTacToeButton(JButton newTicTacToe) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: setOthelloButton() BEGIN");
        }
        
		m_ticTacToe = newTicTacToe;
		
        if (test || m_test) {
            System.out.println("GameSelecter :: setOthelloButton() END");
        }
		
		return true;
	}
	
	/**
	 * Setter method to set the data for the JFrame m_choose
	 * 
	 * \param newChoose
	 * \return true on success
	 */
	public boolean setChooseFrame(JFrame newChoose) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: setChooseFrame() BEGIN");
        }
        
		m_choose = newChoose;
		
        if (test || m_test) {
            System.out.println("GameSelecter :: setChooseFrame() END");
        }
		
		return true;
	}
	
	/**
	 * Getter method to return the pointer for the JButton object m_connect4
	 * 
	 * \return JButton m_connect4
	 */
	public JButton getConnect4Button() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: getConnect4Button() BEGIN");
        }
        if (test || m_test) {
            System.out.println("GameSelecter :: getConnect4Button() END");
        }
        
		return m_connect4;
	}
	
	/**
	 * Getter method to return the JButton m_othello
	 * 
	 * \return JButton m_othello
	 */
	public JButton getOthelloButton() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: getOthelloButton() BEGIN");
        }
        if (test || m_test) {
            System.out.println("GameSelecter :: getOthelloButton() END");
        }
        
		return m_othello;
	}
	
	/**
	 * Getter method to return the JButton m_othello
	 * 
	 * \return JButton m_othello
	 */
	public JButton getTicTacToeButton() {
	    boolean test = true;
        if (test || m_test) {
            System.out.println("GameSelecter :: getOthelloButton() BEGIN");
        }
        if (test || m_test) {
            System.out.println("GameSelecter :: getOthelloButton() END");
        }
        
		return m_ticTacToe;
	}
	
	/**
	 * Getter method to return the object, the JFrame m_choose.
	 * 
	 * \return JFrame m_choose
	 */
	public JFrame getChooseFrame() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: getChooseFrame() BEGIN");
        }
        if (test || m_test) {
            System.out.println("GameSelecter :: getChooseFrame() END");
        }
        
		return m_choose;
	}
	
	/**
	* Constructor for the class GameSelecter 
	*/
	public GameSelecter() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("GameSelecter :: GameSelecter() BEGIN");
        }
        
		JFrame newChoose = new JFrame();
		setChooseFrame(newChoose);
		getChooseFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newChoose.setTitle("Select Game");
		
		Container panel = getChooseFrame().getContentPane();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));	
		
		ImageIcon connect4Image = null;
		ImageIcon othelloImage = null;
		ImageIcon tTTImage = null;
		try {
			connect4Image = new ImageIcon(getClass().getResource(
			                                         "/resource/connect4.jpg"));
			othelloImage = new ImageIcon(getClass().getResource(
			                                          "/resource/othello.jpg"));
			tTTImage = new ImageIcon(getClass().getResource(
													"/resource/tictactoe.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		ImageIcon connect4ImageIcon = connect4Image;
		ImageIcon othelloImageIcon = othelloImage;
		ImageIcon ticTacToeImageIcon = tTTImage;
		
		JButton connect4 = new JButton("Connect 4");
		JButton othello = new JButton("Othello");
		JButton ticTacToe = new JButton("Tic Tac Toe");
		
		setConnect4Button(connect4);
		setOthelloButton(othello);
		setTicTacToeButton(ticTacToe);
		
		ChooseEventHandler handler = new ChooseEventHandler();
		
		getConnect4Button().addActionListener(handler);
		getOthelloButton().addActionListener(handler);
		getTicTacToeButton().addActionListener(handler);
		
		panel.add(getConnect4Button());
		panel.add(getOthelloButton());
		panel.add(ticTacToe);
		
		getConnect4Button().setIcon(connect4ImageIcon);
		getOthelloButton().setIcon(othelloImageIcon);
		getTicTacToeButton().setIcon(ticTacToeImageIcon);
		
		getConnect4Button().setHorizontalTextPosition(SwingConstants.LEADING);
		getConnect4Button().setVerticalAlignment(SwingConstants.BOTTOM);
		
		getOthelloButton().setHorizontalTextPosition(SwingConstants.LEADING);
		getOthelloButton().setVerticalAlignment(SwingConstants.BOTTOM);
		
		getTicTacToeButton().setHorizontalTextPosition(SwingConstants.LEADING);
		getTicTacToeButton().setVerticalAlignment(SwingConstants.BOTTOM);

	    getChooseFrame().pack();
		getChooseFrame().setLocationRelativeTo(null);
        getChooseFrame().setVisible(true);
        
        if (test || m_test) {
            System.out.println("GameSelecter :: GameSelecter() END");
        }
	}
	
	class ChooseEventHandler implements ActionListener {

		/**
		*	Deals with actions performed by GUI objects, namely the buttons.
		*
		*	\param event - object which holds the data of the GUI event.
		*/
		public void actionPerformed(ActionEvent event) {
			Game game;
			if (event.getSource() == getOthelloButton()) {
				game = new Othello();
				PlayerSettings playerSettings = new PlayerSettings
						(game, "othello");
			} else if(event.getSource() == getConnect4Button()){
				game = new ConnectFour();
				PlayerSettings playerSettings = new PlayerSettings
						(game, "connect4");
			}else{
				game = new TicTacToe();
				PlayerSettings playerSettings = new PlayerSettings
						(game, "tictactoe");
			}
			
			getChooseFrame().dispose();
		}
	}
	
	/**
	 * MAIN method - this is were the application begins
	 */
	public static void main(String[] args) {
		new GameSelecter();
	}
	
	/** connect four button */
	private JButton m_connect4;
	/** othello button */
	private JButton m_othello;
	/** TicTacToe button */
	private JButton m_ticTacToe;
	/** jframe reference */
	private JFrame m_choose;
	/** test variable */
	private boolean m_test = false;
	
	
}
