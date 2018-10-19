/**
 * 
 */
package app.team21.risk.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * @author Yash Sheth
 *
 */
public class TestNoCountries {

	private MapElements elements;//gamemap
    private MapLoader loader;//mapmodel
    private String filePath;
	
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapElements.getInstance();
    	filePath = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/Testfiles/";	
    }
    
    @Test
    public void testNoCountries() throws Exception {
        elements = loader.readMapFile(filePath + "no_countries.map");
        assertEquals(false, elements.isCorrectMap());
    }

}
