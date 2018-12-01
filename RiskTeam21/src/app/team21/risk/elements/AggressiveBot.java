package app.team21.risk.elements;

import javax.swing.*;
import javax.swing.plaf.basic.BasicIconFactory;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

/**
 * Last Updated on: 29/11/2018, Thursday 
 * This class implements the strategy for aggressive bot player.
 * 
 * @author Yash Sheth 
 * @version 3.0.0
 */
public class AggressiveBot implements PlayerStrategy,Serializable {
	private static final long serial_version_UID = 1L;
	public Country country_from;
    public Country country_to;
    public Dice dice;

    public int attacker_losses;
    public int defender_losses;
    public int attacker_dice;
    public int defender_dice;

    public Integer[] attacker_rolls;
    public Integer[] defender_rolls;

    public Random rng;
    
    /**
     * Overrides attack phase for aggressive bot player from the PlayerStrategy interface.
     * 
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of GameScreen class
     * @param current_player object of the Player class
     * @param map_elements map elements
     */
    @Override
    public void attack(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        country_from = map_elements.getCountry(country1);
        country_to = map_elements.getCountry(country2);
        game_view.updateView("Attack Phase Begins\n"+country1+" is attacking "+country2);
        while (checkBotCanContinue(country_from,country_to)) {

            dice = new Dice();

            // Set default values
            attacker_losses = 0;
            defender_losses = 0;
            attacker_dice = 1;
            defender_dice = 1;

            // Attacker chooses how many dice to roll
            rng = new Random();
            if (country_from.getCurrentArmiesDeployed() <= 3) {
                attacker_dice = 1;
            } else {
                attacker_dice = rng.nextInt(2) + 1;
            }

            try {
                // Defender chooses how many dice to roll after attacker
                if(country_to.getBelongsToPlayer().isBot()){
                    rng = new Random();
                    if (country_to.getCurrentArmiesDeployed() <= 1) {
                        defender_dice = 1;
                    } else {
                        defender_dice = rng.nextInt(current_player.getMaxDiceDefender(country_to)) + 1;
                    }
                }
                else {
                    defender_dice = showDefenderDiceDialogBox(game_view, current_player);
                }
            } catch (Exception e) {
                // Error: defender inputs invalid number of dice
                defender_dice = 1;
                game_view.updateView("By default defender rolls 1 dice !");
            }
            attacker_rolls = Dice.rollDice(attacker_dice).getDiceResult();
            defender_rolls = Dice.rollDice(defender_dice).getDiceResult();

            StringBuilder sb = new StringBuilder();
			sb.append(country_from.getBelongsToPlayer().getName() + " - Mr.Attacker Rolled ");
			sb.append(System.getProperty("line.separator"));
			for (int a : attacker_rolls) {
				sb.append(a);
				sb.append(System.getProperty("line.separator"));
			}
			sb.append(System.getProperty("line.separator"));
			sb.append(country_to.getBelongsToPlayer().getName() + " - Mr.Defender Rolled ");
			sb.append(System.getProperty("line.separator"));
			for (int a : defender_rolls) {
				sb.append(a);
				sb.append(System.getProperty("line.separator"));
			}

			game_view.updateView(sb.toString());
            // Rolls arrays have been ordered in descending order. Index 0 = highest pair
            calculateLosses();
            country_from.subtractArmy(attacker_losses);
            country_to.subtractArmy(defender_losses);

            game_view.updateView(country_from.getCountryName() + " lost " + attacker_losses + " armies.");
			game_view.updateView(country_to.getCountryName() + " lost " + defender_losses + " armies.");

            // If defending country loses all armies
            if (country_to.getCurrentArmiesDeployed() < 1) {
                game_view.updateView(country_from.getBelongsToPlayer().getName() + " has defeated all of " + country_to.getBelongsToPlayer().getName() + "'s armies in " + country2 + " and has occupied the country!");
                defenderLostCountry(country_from, country_to, current_player,game_view);
            }

            //If player conquered all the country and have won the game
            if (current_player.getAssignedCountries().size() == map_elements.getCountries().size()) {
                current_player.has_bot_won = true;
                game_view.updateView("\n" + current_player.getName() + " has won the game ! Congratulations ! ");
                game_view.updateStatus(current_player.getName() + "Won.  ");
            }
            game_view.updateView("");
        }

    }
    
    /**
     * Displays the dialogBox for dice roll of defender.
     * 
     * @param game_view object of GameScreen class
     * @param current_player object of player class
     * @return JOptionPane message
     */
    private int showDefenderDiceDialogBox(GameScreen game_view, Player current_player) {
        Integer[] select_options = new Integer[current_player.getMaxDiceDefender(country_to)];
        for (int i = 0; i < select_options.length; i++) {
            select_options[i] = i + 1;
        }
        game_view.updateStatus(country_to.getBelongsToPlayer().getName()+" is Defending ");
        return (Integer) JOptionPane.showInputDialog(null,
                country_to.getBelongsToPlayer().getName() + ", you are defending " + country_to.getCountryName() + " from " + country_from.getBelongsToPlayer().getName() + "! How many dice will you roll?",
                "Input", JOptionPane.OK_OPTION, BasicIconFactory.getMenuArrowIcon(), select_options,
                select_options[0]);
    }
    
    /**
     * Checks if player can still continue to attack depending on the armies left.
     * 
     * @param country_from object of Country class
     * @param country_to object of Country class
     * @return boolean value true or false
     */
    private boolean checkBotCanContinue(Country country_from, Country country_to) {
    	if(country_from.getCurrentArmiesDeployed() > 1 && !country_to.getBelongsToPlayer().getName().equals(country_from.getBelongsToPlayer().getName())){
    		return true;            
        }
        return false;
    }
    
    /**
     * Checks for result after the attack phase is over.
     * 
     * @param country_from object of Country class
     * @param country_to object of Country class
     * @param current_player object of player class
     */
    private void defenderLostCountry(Country country_from, Country country_to, Player current_player,GameScreen game_view) {

        // Remove country from defender's list of occupied territories and adds to attacker's list
        country_to.getBelongsToPlayer().assigned_countries.remove(country_to);
        country_from.getBelongsToPlayer().assigned_countries.add(country_to);

        // Check if defender is eliminated from game
        if (country_to.getBelongsToPlayer().getAssignedCountries().size() == 0) {
            current_player.playerEliminated(country_from, country_to,game_view);
        }
        // Set country player to attacker
        country_to.setBelongsToPlayer(country_from.getBelongsToPlayer());
        game_view.updateStatus("\n"+country_to.getCountryName()+" has been captured ! ");

        //The attacking player must then place a number of armies
        //in the conquered country which is greater or equal than the number of dice that was used in the attack that
        //resulted in conquering the country
        

        country_from.subtractArmy(1);
        country_to.addArmy(1);
        current_player.setCanGetCard(true);
        //current_player.addObserver(new PlayerView());
        current_player.updateDominationDetails();
    }
    
    /**
     * Compares the dice results for attacker and defender and calculates the army loss for them.
     */
    private void calculateLosses() {
        // Calculate losses
        if (attacker_rolls[0] > defender_rolls[0]) {
            defender_losses++;
        } else if (attacker_rolls[0] < defender_rolls[0] || Objects.equals(attacker_rolls[0], defender_rolls[0])) {
            attacker_losses++;
        }
        // Index 1 = second highest pair
        if (attacker_dice > 1 && defender_dice > 1) {

            if (attacker_rolls[1] > defender_rolls[1]) {
                defender_losses++;

            } else if (attacker_rolls[1] < defender_rolls[1] || Objects.equals(attacker_rolls[1], defender_rolls[1])) {
                attacker_losses++;
            }
        }
    }
    
    
    /**
     * Overrides fortify phase for aggressive bot from the PlayerStrategy Interface.
     * 
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of GameScreen
     * @param current_player object of player class
     * @param map_elements map elements
     */
    @Override
    public void fortify(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {

        country_from = map_elements.getCountry(country1);
        country_to = map_elements.getCountry(country2);

        // Player inputs how many armies to move from country A to country B
        int armies = country_from.getCurrentArmiesDeployed() - 1;
        country_from.subtractArmy(armies);
        country_to.addArmy(armies);
        game_view.updateView(current_player.getName()+" moved "+armies+" army from "+country_from.getCountryName()+" to " + country_to.getCountryName());
    }
    
    /**
     * Overrides reinforcement phase for aggressive bot from the PlayerStrategy Interface.
     * 
     * @param country name of country where armies are to be reinforced
     * @param game_view object of GameScreen class
     * @param current_player object of Player class
     * @param map_elements map elements
     */
    @Override
    public void reinforce(String country, GameScreen game_view, Player current_player,MapElements map_elements) {
        country_from = map_elements.getCountry(country);
        
        game_view.updateView(current_player.getName() + " gets " + current_player.getReinforceArmies() + " armies");
        try {
        	int armies=current_player.getReinforceArmies();
            if (armies > 0) {
                current_player.subReinforceArmies(armies);
                country_from.addArmy(armies);
                game_view.updateView(current_player.getName() + " has chosen to reinforce " + country_from.getCountryName() + " with " + armies + " armies.");
                if (current_player.getReinforceArmies() == 0) {
                    game_view.updateStatus("Reinforcement Phase ends");
                }
            }
        } catch (Exception e) {
            game_view.updateView("System Error or Exception is thrown for reinforce method");
        }
    }
}
