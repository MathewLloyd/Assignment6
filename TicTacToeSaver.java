/**
 * \\file -ConnectFourSaver.java 
 * \author - Mathew Lloyd A6
 * \date -2nd May 14
 * 
 * \see FileManager.java
 * \see Saver.java
 * 
 * \brief Class to set file paths for the Saving of TicTacToe
 * 
 * This Class extends the Saver class to and controls the path of the save files
 */
public class TicTacToeSaver  extends Saver{
    /**
     * Constructor to initiate the saving of the files
     * 
     * \param g - A reference to the Game to be saved
     */
    public TicTacToeSaver(Game g) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("TicTacToeSaver :: TicTacToeSaver() BEGIN");
        }
        
        setGame(g);
        setPath(TICTACTOE_PATH);
        createDirs();
        
        if (test || m_test) {
            System.out.println("TicTacToeSaver :: TicTacToeSaver() END");
        }
    }
    
    /**
     * Test constructor
     */
    public TicTacToeSaver() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("TicTacToeSaver :: TicTacToeSaver() BEGIN");
        }
        
        setPath(TICTACTOE_TEST_PATH);
        createDirs();
        
        if (test || m_test) {
            System.out.println("TicTacToeSaver :: TicTacToeSaver() END");
        }
    }
    	
        /** file paths to load files */
        private final String TICTACTOE_PATH = "saveData/TicTacToe/";
        /** file path to test loading */
        private final String TICTACTOE_TEST_PATH = "saveDataTest/TicTacToe/";
        /** Test variable */
        private boolean m_test = false;


}
