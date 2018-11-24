package app.team21.risk.elements;

import javax.swing.*;
import javax.swing.plaf.basic.BasicIconFactory;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

import java.util.Objects;
import java.util.Random;

/**
 * This class implements the strategy for aggressive bot player
 * @author Yash Sheth
 *
 */
public class AggressiveBot implements PlayerStrategy {
    public Country countryA;
    public Country countryB;
    public Dice dice;

    public int attacker_losses;
    public int defender_losses;
    public int attacker_dice;
    public int defenderDice;

    public Integer[] attackerRolls;
    public Integer[] defenderRolls;

    public Random rng;
    
    /**
     * Overrides attack phase for aggressive bot player
     * from the PlayerStrategy interface
     * 
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param gameView object of GameView class
     * @param current_player object of the Player class
     * 
     */
    @Override
    public void attack(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country1);
        countryB = map_elements.getCountry(country2);
        game_view.updateView("\n===Attack phase for aggressive player type begins===");
        current_player.updatePhaseDetails("Repaint");
        current_player.updatePhaseDetails("==Attack Phase==");
        while (checkPlayerTurnCanContinue(countryA,countryB)) {

            dice = new Dice();

            // Set default values
            attacker_losses = 0;
            defender_losses = 0;
            attacker_dice = 1;
            defenderDice = 1;

            // Attacker chooses how many dice to roll
            rng = new Random();
            if (countryA.getCurrentArmiesDeployed() <= 3) {
                attacker_dice = 1;
            } else {
                attacker_dice = rng.nextInt(2) + 1;
            }

            try {
                // Defender chooses how many dice to roll after attacker
                if(countryB.getBelongsToPlayer().isBot()){
                    rng = new Random();
                    if (countryB.getCurrentArmiesDeployed() <= 1) {
                        defenderDice = 1;
                    } else {
                        defenderDice = rng.nextInt(1) + 1;
                    }
                }
                else {
                    defenderDice = showDefenderDiceDialogBox(game_view, current_player);
                }
            } catch (Exception e) {
                // Error: defender inputs invalid number of dice
                defenderDice = 1;
                game_view.updateView("By default defender rolls 1 dice !");
            }
            attackerRolls = Dice.rollDice(attacker_dice).getDiceResult();
            defenderRolls = Dice.rollDice(defenderDice).getDiceResult();

            System.out.println("\n"+countryA.getBelongsToPlayer().getName()+" (attacker) threw  dice(s) : ");
            game_view.updateView("\n"+countryA.getBelongsToPlayer().getName()+" (attacker) threw  dice(s) : ");
            for (int attackerRoll : attackerRolls) {
                game_view.updateView(" " + attackerRoll + " ");
            }
            game_view.updateView("\n"+countryB.getBelongsToPlayer().getName()+" (defender) threw  dice(s) : ");
            for (int defenderRoll : defenderRolls) {
                game_view.updateView(" " + defenderRoll + " ");
            }
            // Rolls arrays have been ordered in descending order. Index 0 = highest pair
            compareDiceResultsAndCalculateLosses();
            game_view.updateView("\n\n<COMBAT REPORT>");
            System.out.println("before combat report");
            updateArmiesBasedOnDiceResult(attacker_losses, defender_losses);

            game_view.updateView(countryA.getBelongsToPlayer().getName()+" (attacker) losses : " + attacker_losses + " army.");
            game_view.updateView(countryB.getBelongsToPlayer().getName()+" (defender) losses : " + defender_losses + " army.");
            game_view.updateView(countryA.getBelongsToPlayer().getName()+"'s (attacker) " +countryA.getCountryName() + " has now " + countryA.getCurrentArmiesDeployed());
            game_view.updateView(countryB.getBelongsToPlayer().getName()+"'s (defender)"+ countryB.getCountryName() + " has now " + countryB.getCurrentArmiesDeployed());
            game_view.updateView("\n\n");
            current_player.updatePhaseDetails("<Based On Dice Results> \n");
            current_player.updatePhaseDetails("Attacker Losses : " + attacker_losses + " army." + "\n" + "Defender Losses : " + defender_losses + " army.");

            // If defending country loses all armies
            if (countryB.getCurrentArmiesDeployed() < 1) {
                game_view.updateView(countryA.getBelongsToPlayer().getName() + " has defeated all of " + countryB.getBelongsToPlayer().getName() + "'s armies in " + country2 + " and has occupied the country!");
                defendingPlayerLostCountry(countryA, countryB, current_player,game_view);
            }

            //If player conquered all the country and have won the game
            if (current_player.getAssignedCountries().size() == map_elements.getCountries().size()) {
                current_player.has_bot_won = true;
                game_view.updateView("\n" + current_player.getName() + " has won the game ! Congratulations ! ");
                current_player.updatePhaseDetails(current_player.getName() + "Won.  ");
            }
            game_view.updateView("");
        }

    }
    
    /**
     * Displays the dialogBox for dice roll of defender
     * @param gameView object of GameView class
     * @param current_player object of player class
     * @return JOptionPane message
     * 
     */
    private int showDefenderDiceDialogBox(GameScreen game_view, Player current_player) {
        Integer[] selectOptions = new Integer[getMaxNumberOfDicesForDefender(countryB)];
        for (int i = 0; i < selectOptions.length; i++) {
            selectOptions[i] = i + 1;
        }
        current_player.updatePhaseDetails(countryB.getBelongsToPlayer().getName()+" is Defending ");
        return (Integer) JOptionPane.showInputDialog(null,
                countryB.getBelongsToPlayer().getName() + ", you are defending " + countryB.getCountryName() + " from " + countryA.getBelongsToPlayer().getName() + "! How many dice will you roll?",
                "Input", JOptionPane.OK_OPTION, BasicIconFactory.getMenuArrowIcon(), selectOptions,
                selectOptions[0]);
    }
    
    /**
     * Number of dice roll for defender depending on the armies
     * @param country object of Country class
     * @return number of dices that can be rolled
     * 
     */
    private int getMaxNumberOfDicesForDefender(Country country) {
        return country.getCurrentArmiesDeployed() >= 2 ? 2 : 1;
    }
    
    /**
     * Checks if player can still continue to attack depending on the armies left
     * @param countryA object of country class
     * @param countryB object of country class
     * @return boolean value true or false
     * 
     */
    private boolean checkPlayerTurnCanContinue(Country countryA, Country countryB) {
       System.out.println("INSIDE checkplayercancontinue");
    	if(countryA.getCurrentArmiesDeployed() > 1 && !countryB.getBelongsToPlayer().getName().equals(countryA.getBelongsToPlayer().getName())){
            System.out.println("TRUE");
    		return true;
            
        }
        return false;
    }
    
    /**
     * Checks for result after the attack phase is over
     * @param countryA object of country class
     * @param countryB object of country class
     * @param current_player object of player class
     * 
     */
    private void defendingPlayerLostCountry(Country countryA, Country countryB, Player current_player,GameScreen game_view) {

        // Remove country from defender's list of occupied territories and adds to attacker's list
        countryB.getBelongsToPlayer().assigned_countries.remove(countryB);
        countryA.getBelongsToPlayer().assigned_countries.add(countryB);

        // Check if defender is eliminated from game
        if (countryB.getBelongsToPlayer().getAssignedCountries().size() == 0) {
            current_player.playerEliminated(countryA, countryB,game_view);
        }
        // Set country player to attacker
        countryB.setBelongsToPlayer(countryA.getBelongsToPlayer());
        current_player.updatePhaseDetails("\n"+countryB.getCountryName()+" has been captured ! ");

        //The attacking player must then place a number of armies
        //in the conquered country which is greater or equal than the number of dice that was used in the attack that
        //resulted in conquering the country
        

        countryA.subtractArmy(1);
        countryB.addArmy(1);
        current_player.setCanGetCard(true);
        //current_player.addObserver(new PlayerView());
        current_player.updateDominationDetails();
    }
    
    /**
     * Compares the dice results for attacker and defender
     * and calculates the army loss for them
     * 
     */
    private void compareDiceResultsAndCalculateLosses() {
        // Calculate losses
        if (attackerRolls[0] > defenderRolls[0]) {
            defender_losses++;
        } else if (attackerRolls[0] < defenderRolls[0] || Objects.equals(attackerRolls[0], defenderRolls[0])) {
            attacker_losses++;
        }
        // Index 1 = second highest pair
        if (attacker_dice > 1 && defenderDice > 1) {

            if (attackerRolls[1] > defenderRolls[1]) {
                defender_losses++;

            } else if (attackerRolls[1] < defenderRolls[1] || Objects.equals(attackerRolls[1], defenderRolls[1])) {
                attacker_losses++;
            }
        }
    }
    
    /**
     * Update armies of the players based on the dice results
     * @param attacker_losses number of armies lost by attacker
     * @param defender_losses number of armies lost by defender
     * 
     */
    private void updateArmiesBasedOnDiceResult(int attacker_losses, int defender_losses) {
        countryA.subtractArmy(attacker_losses);
        countryB.subtractArmy(defender_losses);
    }
    
    /**
     * Overrides fortify phase for aggressive bot
     * from the PlayerStrategy Interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param gameView object of GameView class
     * @param current_player object of player class
     * 
     */
    @Override
    public void fortify(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {

        countryA = map_elements.getCountry(country1);
        countryB = map_elements.getCountry(country2);

        // Player inputs how many armies to move from country A to country B
        current_player.updatePhaseDetails("Repaint");
        current_player.updatePhaseDetails("===Fortification phase===");

        int armies = countryA.getCurrentArmiesDeployed() - 1;
        countryA.subtractArmy(armies);
        countryB.addArmy(armies);
        current_player.updatePhaseDetails(current_player.getName()+" moved "+armies+" army from "+countryA.getCountryName()+" to " + countryB.getCountryName());
        game_view.updateView(current_player.getName()+" moved "+armies+" army from "+countryA.getCountryName()+" to " + countryB.getCountryName());
        current_player.updatePhaseDetails("===Fortification ends===");
    }
    
    /**
     * Overrides reinforcement phase for aggressive bot
     * from the PlaerStrategy Interface
     * @param country name of country where armies are to be reinforced
     * @param gameView object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void reinforce(String country, GameScreen game_view, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country);
        System.out.println("COUNTRYA"+countryA.getCurrentArmiesDeployed()+" "+countryA.getBelongsToPlayer().getName());
        game_view.updateView("\n===Reinforcement phase for Aggressive type player begins===");
        game_view.updateView(current_player.getName() + " gets " + current_player.getReinforceArmies() + " armies");
        try {
        	int armies=current_player.getReinforceArmies();
            if (armies > 0) {
                current_player.subReinforceArmies(armies);
                countryA.addArmy(armies);
                game_view.updateView(current_player.getName() + " has chosen to reinforce " + countryA.getCountryName() + " with " + armies + " armies.");
                if (current_player.getReinforceArmies() == 0) {
                    game_view.updateView("===Reinforcement phase for Aggressive type player ends===\n");
                    current_player.updatePhaseDetails("Reinforcement Phase ends");
                }
            }
        } catch (Exception e) {
            game_view.updateView("System Error or Exception is thrown for reinforce method");
        }
    }
}
