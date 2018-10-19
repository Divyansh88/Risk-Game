/**
 * 
 */
package app.team21.risk.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.mapmodule.MapEditor;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * @author Harsh Vaghani and Yash Sheth
 *
 */
public class TestValidateCountry {
	private MapElements elements;
    private MapLoader loader;
    private String file_path="C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/World.map";    
    private MapEditor editor;
    
	@Before
    public void init() {
    	loader = new MapLoader();
    	elements = loader.readMapFile(file_path);	
    	Country new_country=new Country("Country 1");
    	editor = new MapEditor();
    	HashMap<Country, List<Country>> new_country_neighbour_map=elements.getCountryNeighboursMap();
		List<Country> new_neighbour_list=new ArrayList<>();
		new_country.setNeighbourNodes(new_neighbour_list);
		new_country_neighbour_map.put(new_country, new_neighbour_list);
		elements.setCountryNeighboursMap(new_country_neighbour_map);
    }
	
	@Test
	public void test() {
		boolean result = editor.validateCountry(elements);
    	assertEquals(false, result);
	}

}
