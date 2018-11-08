
package app.team21.risk.elements;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * Last Updated on : 08/11/2018, Thursday
 * This class sets and gets the properties of cards.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class Dice {
    int number_of_dice; //number of Dice user wants to roll
    Integer[] dice_result; //the dice result example : first dice roll result will be stored in dice_result[0]
    public boolean is_dice_rolled; // check if dice rolled

    /**
     * Returns result of Dice roll stored into object of values between 1 and 6 representing the outcome of rolling the dice.  
     * The number of values in the array should be between 1 and 3, depending on the number of dice rolled by the player.  
     * The number of dice rolled by the player is specified by the argument number_of_dice.
     *
     * @param number_of_dice number of Dice User wants to roll
     * @return return diceModel object that will have dice roll result
     */
    public static Dice rollDice(int number_of_dice) {
        Dice dicemodel = new Dice();
        //check if we can roll the dice or not
        if (number_of_dice <= 0 || number_of_dice > 3) {
            dicemodel.setDiceRolled(false);
            return dicemodel;
        }
        Integer[] dice_result = new Integer[number_of_dice];
        for (int counter = 0; counter < number_of_dice; counter++) {
            Random random = new Random();
            int result = random.nextInt(5) + 1; //this function will give results between 1-6 including both
            dice_result[counter] = result;
        }
        //Sorting
        Arrays.sort(dice_result,Collections.reverseOrder());
        dicemodel.setDiceRolled(true);
        dicemodel.setDiceResult(dice_result);
        dicemodel.setNumberOfDice(number_of_dice);
        return dicemodel;

    }

    /**
     * getter method checks whether dices is rolled or not.
     * 
     * @return true if dices is rolled ,otherwise false
     */
    public boolean isDiceRolled() {
        return is_dice_rolled;
    }

    /**
     * setter method assigns boolean result of is dice rolled.
     * 
     * @param is_dice_rolled true or false values
     */
    public void setDiceRolled(boolean is_dice_rolled) {
        this.is_dice_rolled = is_dice_rolled;
    }

    /**
     * getter method gives number of dices to be rolled.
     * 
     * @return values of number of dices
     */
    public int getNumberOfDice() {
        return number_of_dice;
    }

    /**
     * setter method assigns number of dices to be rolled.
     * 
     * @param number_of_dice number of dices to be rolled
     */
    public void setNumberOfDice(int number_of_dice) {
        this.number_of_dice = number_of_dice;
    }

    /**
     * getter method gives the result of rolled dices.
     * 
     * @return value of the result
     */
    public Integer[] getDiceResult() {
        return dice_result;
    }


    /**
     * setter method assigns the result of rolled dices.
     * 
     * @param dice_result value of result of rolled dices
     */
    public void setDiceResult(Integer[] dice_result) {
        this.dice_result = dice_result;
    }
}
