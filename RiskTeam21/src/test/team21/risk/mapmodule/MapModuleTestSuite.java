package test.team21.risk.mapmodule;

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
	TestValidateCountry.class,
	TestContinentConnectivity.class
})

/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test suit class for mapmodule.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class MapModuleTestSuite {

}
