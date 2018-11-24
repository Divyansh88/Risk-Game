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
    public int defender_dice;

    public Integer[] attacker_rolls;
    public Integer[] defender_rolls;

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
        while (checkBotCanContinue(countryA,countryB)) {

            dice = new Dice();

            // Set default values
            attacker_losses = 0;
            defender_losses = 0;
            attacker_dice = 1;
            defender_dice = 1;

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
                        defender_dice = 1;
                    } else {
                        defender_dice = rng.nextInt(1) + 1;
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
			sb.append(countryA.getBelongsToPlayer().getName() + " - Mr.Attacker Rolled ");
			sb.append(System.getProperty("line.separator"));
			for (int a : attacker_rolls) {
				sb.append(a);
				sb.append(System.getProperty("line.separator"));
			}
			sb.append(System.getProperty("line.separator"));
			sb.append(countryB.getBelongsToPlayer().getName() + " - Mr.Defender Rolled ");
			sb.append(System.getProperty("line.separator"));
			for (int a : defender_rolls) {
				sb.append(a);
				sb.append(System.getProperty("line.separator"));
			}

			game_view.updateView(sb.toString());
            // Rolls arrays have been ordered in descending order. Index 0 = highest pair
            calculateLosses();
            countryA.subtractArmy(attacker_losses);
            countryB.subtractArmy(defender_losses);

            game_view.updateView(countryA.getCountryName() + " lost " + attacker_losses + " armies.");
			game_view.updateView(countryB.getCountryName() + " lost " + defender_losses + " armies.");

            // If defending country loses all armies
            if (countryB.getCurrentArmiesDeployed() < 1) {
                game_view.updateView(countryA.getBelongsToPlayer().getName() + " has defeated all of " + countryB.getBelongsToPlayer().getName() + "'s armies in " + country2 + " and has occupied the country!");
                defenderLostCountry(countryA, countryB, current_player,game_view);
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
        Integer[] selectOptions = new Integer[current_player.getMaxDiceDefender(countryB)];
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
     * Checks if player can still continue to attack depending on the armies left
     * @param countryA object of country class
     * @param countryB object of country class
     * @return boolean value true or false
     * 
     */
    private boolean checkBotCanContinue(Country countryA, Country countryB) {
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
    private void defenderLostCountry(Country countryA, Country countryB, Player current_player,GameScreen game_view) {

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
