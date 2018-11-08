package test.team21.risk.elements;

/**
 * @author Yash Sheth
 *
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({

	TestDiceResult.class, 
	TestMoreDice.class,
	TestRollDice.class,
	TestExchangeArmies.class,
	TestMaxDiceAttacker.class,
	TestMaxDiceDefender.class
})

public class ElementsModuleTestSuite {

}
