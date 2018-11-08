package test.team21.mapmodule;

/**
 * @author Yash Sheth
 *
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({

	TestMapTag.class, 
	TestTerritoriesTag.class, 
	TestContinentTag.class, 
	TestBlankFields.class, 
	TestControlValue.class,
	TestNoContinents.class, 
	TestNoCountries.class,  
	TestValidateContinent.class,
	TestValidateCountry.class 	
})

public class MapModuleTestSuite {

}
