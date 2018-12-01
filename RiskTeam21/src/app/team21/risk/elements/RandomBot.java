package app.team21.risk.elements;

import javax.swing.*;
import javax.swing.plaf.basic.BasicIconFactory;

import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

/**
 * Last Updated on: 29/11/2018, Thursday 
 * This class implements the strategy of Random bot player.
 * 
 * @author Yash Sheth
 * @version 3.0.0
 */
public class RandomBot implements PlayerStrategy ,Serializable {
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
     * Overrides the attack phase for Random bot from he PlayerStrategy interface.
     * 
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of GameScreen class
     * @param current_player object of Player class
     * @param map_elements map elements
     */
    @Override
    public void attack(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        country_from = map_elements.getCountry(country1);
        country_to = map_elements.getCountry(country2);
        
        rng = new Random();
        int random_time_attacks = rng.nextInt(9)+1;
        while(random_time_attacks > 0){
            if(checkPlayerTurnCanContinue(country_from,country_to)){
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

                // Defender chooses how many dice to roll after attacker
                if(country_to.getBelongsToPlayer().isBot()){
                    rng = new Random();
                    if (country_to.getCurrentArmiesDeployed() <= 1) {
                        defender_dice = 1;
                    } else {
                        defender_dice = rng.nextInt(1) + 1;
                    }
                }
                else {
                    defender_dice = showDefenderDiceDialogBox(game_view, current_player);
                }

                //Dices are rolled
                attacker_rolls = Dice.rollDice(attacker_dice).getDiceResult();
                defender_rolls = Dice.rollDice(defender_dice).getDiceResult();

                game_view.updateView("\n"+country_from.getBelongsToPlayer().getName()+" (attacker) threw  dice(s) : ");
                for (int attackerRoll : attacker_rolls) {
                    game_view.updateView(" " + attackerRoll + " ");
                }
                game_view.updateView("\n"+country_to.getBelongsToPlayer().getName()+" (defender) threw  dice(s) : ");
                for (int defender_roll : defender_rolls) {
                    game_view.updateView(" " + defender_roll + " ");
                }
                // Rolls arrays have been ordered in descending order. Index 0 = highest pair
                compareDiceResultsAndCalculateLosses();
                game_view.updateView("\n\n<COMBAT REPORT>");

                updateArmiesBasedOnDiceResult(attacker_losses, defender_losses);

                game_view.updateView(country_from.getBelongsToPlayer().getName()+" (attacker) losses : " + attacker_losses + " army.");
                game_view.updateView(country_to.getBelongsToPlayer().getName()+" (defender) losses : " + defender_losses + " army.");
                game_view.updateView(country_from.getBelongsToPlayer().getName()+"'s (attacker) " +country_from.getCountryName() + " has now " + country_from.getCurrentArmiesDeployed());
                game_view.updateView(country_to.getBelongsToPlayer().getName()+"'s (defender)"+ country_to.getCountryName() + " has now " + country_to.getCurrentArmiesDeployed());

                // If defending country loses all armies
                if (country_to.getCurrentArmiesDeployed() < 1) {

                    game_view.updateView(country_from.getBelongsToPlayer().getName() + " has defeated all of " + country_to.getBelongsToPlayer().getName() + "'s armies in " + country2 + " and has occupied the country!");
                    defendingPlayerLostCountry(country_from, country_to, current_player,game_view);
                }

                //If player conquered all the country and have won the game
                if (current_player.assigned_countries.size() == map_elements.getCountries().size()) {
                    current_player.setHasBotWon(true);
                    game_view.updateView("" + current_player.getName() + " has won the game ! Congratulations ! ");
                    current_player.updatePhaseDetails(current_player.getName() + "Won");
                }
                random_time_attacks--;

            }else {
                game_view.updateView("Looks like random player type cannot attack anymore ! ");
                break;
            }
        }

    }
    
    /**
     * Checks if the defending player has lost armies.
     * 
     * @param country_from object of Country class
     * @param country_to object of Country class
     * @param current_player object of Player class
     * @param game_view object of GameScreen class
     */
    private void defendingPlayerLostCountry(Country country_from, Country country_to, Player current_player, GameScreen game_view) {
        // Remove country from defender's list of occupied territories and adds to attacker's list
        country_to.getBelongsToPlayer().assigned_countries.remove(country_to);
        country_from.getBelongsToPlayer().assigned_countries.add(country_to);

        // Check if defender is eliminated from game
        if (country_to.getBelongsToPlayer().getAssignedCountries().size() == 0) {
            current_player.playerEliminated(country_from, country_to, game_view);
        }
        // Set country player to attacker
        country_to.setBelongsToPlayer(country_from.getBelongsToPlayer());
        current_player.updatePhaseDetails("\n"+country_to.getCountryName()+" has been captured ! ");

        //The attacking player must then place a number of armies
        //in the conquered country which is greater or equal than the number of dice that was used in the attack that
        //resulted in conquering the country
        int move_armies = attacker_dice;

        country_from.subtractArmy(move_armies);
        country_to.addArmy(move_armies);
        current_player.setCanGetCard(true);
        //current_player.addObserver(new PlayerView());
        current_player.updateDominationDetails();
    }
    
    /**
     * Updates the armies of the attacker/defender based on the dice roll result.
     * 
     * @param attacker_losses number of armies lost by attacker
     * @param defender_losses number of armies lost by defender
     */
    private void updateArmiesBasedOnDiceResult(int attacker_losses, int defender_losses) {
        country_from.subtractArmy(attacker_losses);
        country_to.subtractArmy(defender_losses);
    }
    
    /**
     * Compares the dice results for attacker and defender and calculates the army loss for them.
     */
    private void compareDiceResultsAndCalculateLosses() {
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
     * Displays the dialogBox for dice roll of defender.
     * 
     * @param game_view object of GameScreen class
     * @param current_player  object of Player class
     * @return JOptionPane message
     */
    private int showDefenderDiceDialogBox(GameScreen game_view, Player current_player) {
        Integer[] select_options = new Integer[getMaxNumberOfDicesForDefender(country_to)];
        for (int i = 0; i < select_options.length; i++) {
            select_options[i] = i + 1;
        }
        current_player.updatePhaseDetails(country_to.getBelongsToPlayer().getName()+" is Defending ");
        return (Integer) JOptionPane.showInputDialog(null,
                country_to.getBelongsToPlayer().getName() + ", you are defending " + country_to.getCountryName() + " from " + country_from.getBelongsToPlayer().getName() + "! How many dice will you roll?",
                "Input", JOptionPane.OK_OPTION, BasicIconFactory.getMenuArrowIcon(), select_options,
                select_options[0]);
    }
    
    /**
     * Number of dice roll for defender depending on the armies.
     * 
     * @param country object of Country class
     * @return number of dices that can be rolled
     */
    private int getMaxNumberOfDicesForDefender(Country country) {
        return country.getCurrentArmiesDeployed() >= 2 ? 2 : 1;
    }
    
    /**
     * Checks if player can still continue to attack depending on the armies left.
     * 
     * @param country_from object of Country class
     * @param country_to object of Country class
     * @return boolean value true or false
     */
    private boolean checkPlayerTurnCanContinue(Country country_from, Country country_to) {
        if(country_from.getCurrentArmiesDeployed() > 1 && !country_to.getBelongsToPlayer().getName().equals(country_from.getBelongsToPlayer().getName())){
            return true;
        }
        return false;
    }
    /**
     * Overrides fortify phase for Random bot from the PlayerStrategy interface.
     * 
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of game_view class
     * @param current_player object of Player class
     * @param map_elements map elements
     */
    @Override
    public void fortify(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        country_from = map_elements.getCountry(country1);
        country_to = map_elements.getCountry(country2);
        rng = new Random();
        // Player inputs how many armies to move from country A to country B
        current_player.updatePhaseDetails("Repaint");
        current_player.updatePhaseDetails("Fortification Phase");


        int armies = rng.nextInt(country_from.getCurrentArmiesDeployed()-1);
        if (armies  == 0) armies = 1;

        country_from.subtractArmy(armies);
        country_to.addArmy(armies);
        current_player.updatePhaseDetails(current_player.getName()+" moved "+armies+" army from "+country_from.getCountryName()+" to " + country_to.getCountryName());
    }
    /**
     * Overrides reinforcement phase for Random bot from the PlayerStrategy interface.
     * 
     * @param country name of country where armies are to be reinforced
     * @param game_view object of GameScreen class
     * @param current_player object of Player class
     * @param map_elements map elements
     */
    @Override
    public void reinforce(String country, GameScreen game_view, Player current_player,MapElements map_elements) {
        country_from = map_elements.getCountry(country);
        int armies=GamePlay.getReinforcementArmies(current_player, map_elements);
        game_view.updateView(current_player.getName() + " gets " + armies + " armies");
        try {
            if (current_player.getReinforceArmies()> 0) {
                current_player.subReinforceArmies(armies);
                country_from.addArmy(armies);
                game_view.updateView(current_player.getName() + " has chosen to reinforce " + country_from.getCountryName() + " with " + armies + " armies.");
                if (current_player.getReinforceArmies() == 0) {
                    game_view.updateView(current_player.getName()+" do not have any armies left to reinforce");
                    game_view.updateView("===Reinforcement phase for Random type player ends===\n");
                    current_player.updatePhaseDetails("\nReinforcement Phase ends");
                }
            }

        } catch (Exception e) {
            game_view.updateView("\nSystem Error or Exception is thrown for reinforce method");
        }
    }
}
