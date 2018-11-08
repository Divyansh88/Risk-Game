/**
 * 
 */
package test.team21.elements;

import static org.junit.Assert.*;

import org.junit.Test;

import app.team21.risk.elements.Dice;


/**
 * @author Yash Sheth
 *
 */
public class TestMoreDice {
	
	/**
     * This method checks if the dice result more than three or not
     *
     * @throws Exception it throws if there are any exceptions found
     */
    @Test
    public void testDiceResultMoreThanThree() throws Exception {
        assertFalse(Dice.rollDice(6).isDiceRolled);
    }

}
