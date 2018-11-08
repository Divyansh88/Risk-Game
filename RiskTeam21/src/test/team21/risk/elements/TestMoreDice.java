package test.team21.risk.elements;
import static org.junit.Assert.*;

import org.junit.Test;

import app.team21.risk.elements.Dice;


/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for more dice.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class TestMoreDice {
	
	/**
     * This method checks if the dice result more than three or not
     *
     * @throws Exception it throws if there are any exceptions found
     */
    @Test
    public void testDiceResultMoreThanThree() throws Exception {
        assertFalse(Dice.rollDice(6).is_dice_rolled);
    }

}
