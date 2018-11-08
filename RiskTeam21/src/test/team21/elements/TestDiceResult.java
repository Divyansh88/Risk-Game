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
public class TestDiceResult {

    /**
     * This method checks whether the dices result
     *
     * @throws Exception it throws if there are any exceptions found
     */
    @Test
    public void testDiceResult() throws Exception {
        assertTrue(Dice.rollDice(3).isDiceRolled);
    }

    

}
