package test.team21.gamemodule;

/**
 * @author Yash Sheth
 *
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({

	TestReinforceArmies.class, 
	TestValidMap.class,
	TestDeckCards.class
})

public class GameModuleTestSuite {

}
