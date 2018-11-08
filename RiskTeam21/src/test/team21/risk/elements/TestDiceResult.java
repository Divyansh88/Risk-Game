package test.team21.risk.elements;
import static org.junit.Assert.*;

import org.junit.Test;

import app.team21.risk.elements.Dice;


/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for dice result.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class TestDiceResult {

    /**
     * This method checks whether the dices result.
     *
     * @throws Exception it throws if there are any exceptions found
     */
    @Test
    public void testDiceResult() throws Exception {
        assertTrue(Dice.rollDice(3).is_dice_rolled);
    }

    

}
