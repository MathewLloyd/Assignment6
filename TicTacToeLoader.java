
public class TicTacToeLoader extends Loader{
    /**
     * Constructor to initiate the loading of the files
     * 
     * \param game - A reference to the Game to be loaded
     */
    public TicTacToeLoader(Game game) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("ConnectFourLoader :: ConnectFourLoader() "
                                                                     + "BEGIN");
        }
        
        setGame(game);
        setPath(TICTACTOE_PATH);
        
        loadAll();
        
        if (test || m_test) {
            System.out.println("ConnectFourLoader :: ConnectFourLoader() END");
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
            System.out.println("ConnectFourLoader :: ConnectFourLoader() "
                                                                     + "BEGIN");
        }
        
        setGame(game);
        setPath(TICTACTOE_TEST_PATH);
        
        if (test || m_test) {
            System.out.println("ConnectFourLoader :: ConnectFourLoader() END");
        }
    }
    
    /** file paths to load files */
    private final String TICTACTOE_PATH = "saveData/tictactoe/";
    /** file path to test loading */
    private final String TICTACTOE_TEST_PATH = "saveDataTest/tictactoe/";
    /** Test variable */
    private boolean m_test = false;
}
