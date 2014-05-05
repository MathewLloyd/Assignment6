/**
 * \\file -OthelloLoader.java 
 * \author - Mathew Lloyd A6
 * \date -2nd May 2014
 * 
 * \see FileManager.java
 * \see Loader.java
 * 
 * \brief Class to set file paths for the Loading of TicTacToe
 * 
 * This Class extends the Loader class to and controls the path of the save file
 */
public class TicTacToeLoader extends Loader{
    /**
     * Constructor to initiate the loading of the files
     * 
     * \param game - A reference to the Game to be loaded
     */
    public TicTacToeLoader(Game game) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("TicTacToeLoader :: TicTacToeLoader() "
                                                                     + "BEGIN");
        }
        
        setGame(game);
        setPath(TICTACTOE_PATH);
        
        loadAll();
        
        if (test || m_test) {
            System.out.println("TicTacToeLoader :: TicTacToeLoader() END");
        }
    }
    
    /**
     * Test constructor
     * 
     * \param game - reference to the game to be loaded
     * \param s - used to overload the constructor
     */
    public TicTacToeLoader(Game game, String s) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("TicTacToeLoader :: TicTacToeLoader() "
                                                                     + "BEGIN");
        }
        
        setGame(game);
        setPath(TICTACTOE_TEST_PATH);
        
        if (test || m_test) {
            System.out.println("TicTacToeLoader :: TicTacToeLoader() END");
        }
    }
    
    /** file paths to load files */
    private final String TICTACTOE_PATH = "saveData/tictactoe/";
    /** file path to test loading */
    private final String TICTACTOE_TEST_PATH = "saveDataTest/tictactoe/";
    /** Test variable */
    private boolean m_test = false;
}
