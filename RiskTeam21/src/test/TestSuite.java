package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.team21.risk.elements.*;
import test.team21.risk.mapmodule.*;
import test.team21.risk.gamemodule.*;



@RunWith(Suite.class)

@Suite.SuiteClasses({

	ElementsModuleTestSuite.class, 
	MapModuleTestSuite.class,
	GameModuleTestSuite.class
	
})

/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a main test suit class.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class TestSuite {

}
