package test.team21.risk.elements;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.elements.Country;
import app.team21.risk.elements.Dice;
import app.team21.risk.elements.Player;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.views.GameScreen;


/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for exchange armies.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class TestAttack {
	private MapElements elements;
    private MapLoader loader;
    private GamePlay game_play;
    private GameScreen game_view;
    Player player1,player2,player3; 
    List<Player> player_list;
    String file_path="RiskTeam21/src/app/team21/risk/maps/India.map";
    
    /**
     * This is method initializes important objects and variables used in the test.
     * This method is called before test runs.
     */
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = loader.readMapFile(file_path);
    	
    	player1 = new Player("Player 1",false,"human");
    	player2 = new Player("Player 2",false,"human");
    	
    	game_play = new GamePlay();
    	game_view = new GameScreen();
    	game_view.tournament_mode=true;
    	
    	player_list=new ArrayList<>();
    	player_list.add(player1);
    	player_list.add(player2);
    	game_view.playerContinueButton(elements, player_list, 1);	
    }
    
    /**
     * This method checks exchange of armies.
     */
    @Test
    public void testAttackValidation(){
    	Country attack=player1.getAssignedCountries().get(0);
    	Country defender=null;

    	for(Country c:attack.getNeighbourNodes()){
    		c=elements.getCountry(c.getCountryName());
    		c.setBelongsToPlayer(player2);
    		defender=c;
    	}
    	
    	boolean result=player1.isAttackValid(player1, attack, defender, game_view);
    	assertEquals(true, result);
    }
    

}
