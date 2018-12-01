package test.team21.risk.gamemodule;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for reinforce armies.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class TestReinforceArmies {

	private MapElements elements;
    private MapLoader loader;
    GamePlay game_play;
    private Player player1,player2,player3,player4,player5;
    String file_path="RiskTeam21/src/app/team21/risk/maps/World.map";
	
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
    	player3 = new Player("Player 3",false,"human");
    	player4 = new Player("Player 4",false,"human");
    	player5 = new Player("Player 4",false,"human");
    	
    	game_play = new GamePlay();
    	
    	List<Player> player_list=new ArrayList<>();
    	player_list.add(player1);
    	player_list.add(player2);
    	
		game_play.distributeCountries(player_list, elements.getCountries());
		game_play.setInitialArmies(player_list);
		game_play.placeInitialArmiesInRR(player_list);
		for(Continent c:elements.getContinentList()){
			if(c.getContinentName().toString().equals("Australia")){
				
				List<Country> new_countries=player1.getAssignedCountries();
				for(Country con:c.getMemberCountriesList()){
					new_countries.add(con);
				}
				player1.setAssignedCountries(new_countries);
			}
		}
    	
    }
    
    /**
     * This method checks reinforcement of armies.
     * 
     * @throws Exception
     */
    @Test
    public void testReinforceArmies() throws Exception {
        int result= game_play.getReinforcementArmies(player1, elements);
        assertEquals(8, result);
    }

}
