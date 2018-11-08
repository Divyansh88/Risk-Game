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
public class TestRollDice {


    /**
     * This method checks the rolled dice result which should be more than 0 and less than 7
     *
     * @throws Exception it throws if there are any exceptions found
     */
    @Test
    public void testRollDice() throws Exception {
        Integer[] i = Dice.rollDice(3).getDiceResult();
        assertTrue(i[0] >= i[1]);
        assertTrue(i[0] < 7);
        assertTrue(i[0] > 0);
    }

}
