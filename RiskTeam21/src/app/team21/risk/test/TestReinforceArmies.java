/**
 * 
 */
package app.team21.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * @author Yash Sheth
 *
 */
public class TestReinforceArmies {

	private MapElements elements;
    private MapLoader loader;
    private Player player;
    String file_path="C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/World.map";
	
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = loader.readMapFile(file_path);
    	
    	player =new Player("name");
    	List<Country> country_list=new ArrayList();
    	for(int i=0;i<10;i++){
    		country_list.add(new Country("Country"+(i+1)));
    	}
    	player.setAssignedCountries(country_list);
    }
    
    @Test
    public void testBlankField() throws Exception {
        
        assertEquals(false, elements.isCorrectMap());
    }

}
