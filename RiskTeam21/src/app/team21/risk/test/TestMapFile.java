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
 * @author Divyansh
 *
 */
public class TestMapFile {
	
	private MapElements elements;//gamemap
    private MapLoader loader;//mapmodel
    private String filePath;
	
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapElements.getInstance();
    	filePath = "C:\\Users\\ADMIN\\git\\RiskTeam21\\RiskTeam21\\src\\app\\team21\\risk\\maps\\Testfiles";	
    }
    
    @Test
    public void testValidMap() throws Exception {
        elements = loader.readMapFile(filePath + "no_map_tag.map");
        assertEquals(false, elements.is_correct_map());
    }
    @Test
    public void testValidTerritory() throws Exception {
        elements = loader.readMapFile(filePath + "no_territory_tag.map");
        assertEquals(false, elements.is_correct_map());
    }
    @Test
    public void testValidCountry() throws Exception {
        elements = loader.readMapFile(filePath + "no_country_tag.map");
        assertEquals(false, elements.is_correct_map());
    }
    @Test
    public void testValidControlValue() throws Exception {
        elements = loader.readMapFile(filePath + "test_control_values.map");
        assertEquals(false, elements.is_correct_map());
    }
    @Test
    public void testValidName() throws Exception {
        elements = loader.readMapFile(filePath + "no_blank_name.map");
        assertEquals(false, elements.is_correct_map());
    }
    @Test
    public void testValidFile() throws Exception {
        elements = loader.readMapFile(filePath + "valid_file.map");
        assertEquals(true, elements.is_correct_map());
    }

    

}
