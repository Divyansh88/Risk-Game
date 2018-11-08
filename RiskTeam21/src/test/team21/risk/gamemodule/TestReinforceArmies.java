/**
 * 
 */
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
 * @author Yash Sheth
 *
 */
public class TestReinforceArmies {

	private MapElements elements;
    private MapLoader loader;
    GamePlay game_play;
    private Player player1,player2;
    String file_path="C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/World.map";
	
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = loader.readMapFile(file_path);
    	
    	player1 = new Player("Player 1");
    	player2 = new Player("Player 2");
    	
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
    
    @Test
    public void testReinforceArmies() throws Exception {
        int result= game_play.getReinforcementArmies(player1, elements);
        assertEquals(8, result);
    }

}
