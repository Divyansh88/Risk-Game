package test.team21.risk.mapmodule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for number of continents.
 *
 * @author Harsh Vaghani and Yash Sheth
 * @version 2.0.0
 */
public class TestNoContinents {

	private MapElements elements;//gamemap
    private MapLoader loader;//mapmodel
    private String file_path;
	
    /**
     * This is method initializes important objects and variables used in the test.
     * This method is called before test runs.
     */
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapElements.getInstance();
    	file_path = "RiskTeam21/src/app/team21/risk/maps/Testfiles/";	
    }
    
    /**
     * This method checks number continent in map file.
     * 
     * @throws Exception IO exceptions
     */
    @Test
    public void testNoContinents() throws Exception {
    	System.out.println("**************************************\n");
		System.out.println("TestNoContinents");
    	elements = loader.readMapFile(file_path + "no_continents.map");
        assertEquals(false, elements.isCorrectMap());
    }

}
