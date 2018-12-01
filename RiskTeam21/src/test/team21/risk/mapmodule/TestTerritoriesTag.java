package test.team21.risk.mapmodule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for territory tag.
 *
 * @author Mahy Salama and Yash Sheth
 * @version 2.0.0
 */
public class TestTerritoriesTag {
	private MapElements elements;//gamemap
    private MapLoader loader;//mapmodel
    private String filePath;
    
    /**
     * This is method initializes important objects and variables used in the test.
     * This method is called before test runs.
     */
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapElements.getInstance();
    	filePath = "RiskTeam21/src/app/team21/risk/maps/Testfiles/";	
    }
    
    /**
     * This method checks territory tag is missing or not.
     * 
     * @throws Exception
     */
    @Test
    public void testTerritoriesTag() throws Exception {
    	System.out.println("**************************************\n");
		System.out.println("TestTerritoriesTag");
    	elements = loader.readMapFile(filePath + "no_territory_tag.map");
        assertEquals(false, elements.isCorrectMap());
    }

}
