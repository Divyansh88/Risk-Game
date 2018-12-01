package test.team21.risk.gamemodule;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.elements.Player;
import app.team21.risk.gamemodule.Deck;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.views.GameScreen;


/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for deck card.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class TestDeckCards{

	private MapElements elements;
    private MapLoader loader;
    private GamePlay game_play;
    private GameScreen game_view;
    Player player1,player2; 
    String file_path="RiskTeam21/src/app/team21/risk/maps/India.map";
    
    /**
     * This is method initializes important objects and variables used in the test.
     * This method is called before test runs.
     */
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapLoader.readMapFile(file_path);
    	
    	player1 = new Player("Player 1",false,"human");
    	player2 = new Player("Player 2",false,"human");
    	
    	game_play = new GamePlay();
    	game_view = new GameScreen();
    	game_view.map_elements=elements;
    	List<Player> player_list=new ArrayList<>();
    	player_list.add(player1);
    	player_list.add(player2);
    	
		game_play.distributeCountries(player_list, elements.getCountries());
		game_play.setInitialArmies(player_list);
		game_play.placeInitialArmiesInRR(player_list);
		
    }
    

	/**
     * This method checks whether the deck cards has populated correctly.
     */
    @Test
    public void checkDeckCardsPopulated() {
        Deck deck = new Deck(elements.getCountries());
        assertEquals(elements.getCountries().size(), deck.deck.size());
    }
}
