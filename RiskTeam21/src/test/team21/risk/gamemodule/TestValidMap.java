package test.team21.risk.gamemodule;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class to check valid map.
 *
 * @author Mahy Salama and Yash Sheth
 * @version 2.0.0
 */
public class TestValidMap {

	private MapElements elements;// gamemap
	private MapLoader loader;// mapmodel
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
	 * This method checks a map file is valid or not.
	 * 
	 * @throws Exception IO exceptions
	 */
	@Test
	public void testValidMap() throws Exception {
		System.out.println("**************************************\n");
		System.out.println("TestValidMap");
		elements = MapLoader.readMapFile(file_path + "valid_file.map");
		boolean result=elements.isCorrectMap();
		assertEquals(true, result);
		
	}

}