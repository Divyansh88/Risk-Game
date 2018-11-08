package test;

/**
 * @author Yash Sheth
 *
 */

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

public class TestSuite {

}
