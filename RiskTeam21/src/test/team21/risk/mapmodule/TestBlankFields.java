package test.team21.risk.mapmodule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;


/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for blank fields.
 *
 * @author Divyansh Thakar and Yash Sheth
 * @version 2.0.0
 */
public class TestBlankFields {
	
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
     * This method checks blank fields in map file.
     * 
     * @throws Exception IO exceptions
     */
    @Test
    public void testBlankFields() throws Exception {
    	System.out.println("**************************************\n");
		System.out.println("TestBlankFields");
    	elements = loader.readMapFile(file_path + "no_blank_name.map");
        assertEquals(false, elements.isCorrectMap());
    }

    

}
