package test.team21.risk.mapmodule;

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
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for valid continent.
 *
 * @author Harsh Vaghani and Yash Sheth
 * @version 2.0.0
 */
public class TestContinentConnectivity {
	private MapElements elements;
    private MapLoader loader;
    private String file_path1="RiskTeam21/src/app/team21/risk/maps/Testfiles/india.map";
    private String file_path2="RiskTeam21/src/app/team21/risk/maps/INDIA.map";
    private MapEditor editor;
    
    
	/**
     * This method checks continent is valid continent or not.
     */
	@Test
	public void testConnectivity1() {
		loader = new MapLoader();
    	elements = loader.readMapFile(file_path1);	
		System.out.println("**************************************\n");
		System.out.println("TestContinentConnectivity");
		boolean result = elements.isCorrectMap();
    	assertEquals(false, result);
	}

	
	/**
     * This method checks continent is valid continent or not.
     */
	@Test
	public void testConnectivity2() {
		loader = new MapLoader();
    	elements = loader.readMapFile(file_path2);	
		System.out.println("**************************************\n");
		System.out.println("TestContinentConnectivity");
		boolean result = elements.isCorrectMap();
    	assertEquals(true, result);
	}
}
