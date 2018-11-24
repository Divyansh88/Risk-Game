package app.team21.risk.elements;

import javax.swing.*;
import javax.swing.plaf.basic.BasicIconFactory;

import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

import java.util.Objects;
import java.util.Random;
/**
 * This class implements the strategy of Random bot player
 * @author mudraparikh
 *
 */
public class RandomBot implements PlayerStrategy {

    public Country countryA;
    public Country countryB;

    public Dice dice;

    public int attackerLosses;
    public int defenderLosses;
    public int attackerDice;
    public int defenderDice;

    public Integer[] attackerRolls;
    public Integer[] defenderRolls;

    public Random rng;
    
    /**
     * Overrides the attack phase for Random bot
     * from he PlayerStrategy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param gameView object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void attack(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country1);
        countryB = map_elements.getCountry(country2);
        game_view.updateView("\n===Attack phase for random player type begins===");
        current_player.updatePhaseDetails("Repaint");
        current_player.updatePhaseDetails("===Attack Phase==");
        rng = new Random();
        int randomTimeAttacks = rng.nextInt(9)+1;
        while(randomTimeAttacks > 0){
            if(checkPlayerTurnCanContinue(countryA,countryB)){
                dice = new Dice();

                // Set default values
                attackerLosses = 0;
                defenderLosses = 0;
                attackerDice = 1;
                defenderDice = 1;

                // Attacker chooses how many dice to roll
                rng = new Random();
                if (countryA.getCurrentArmiesDeployed() <= 3) {
                    attackerDice = 1;
                } else {
                    attackerDice = rng.nextInt(2) + 1;
                }

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

                //Dices are rolled
                attackerRolls = Dice.rollDice(attackerDice).getDiceResult();
                defenderRolls = Dice.rollDice(defenderDice).getDiceResult();

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

                updateArmiesBasedOnDiceResult(attackerLosses, defenderLosses);

                game_view.updateView(countryA.getBelongsToPlayer().getName()+" (attacker) losses : " + attackerLosses + " army.");
                game_view.updateView(countryB.getBelongsToPlayer().getName()+" (defender) losses : " + defenderLosses + " army.");
                game_view.updateView(countryA.getBelongsToPlayer().getName()+"'s (attacker) " +countryA.getCountryName() + " has now " + countryA.getCurrentArmiesDeployed());
                game_view.updateView(countryB.getBelongsToPlayer().getName()+"'s (defender)"+ countryB.getCountryName() + " has now " + countryB.getCurrentArmiesDeployed());
                game_view.updateView("\n\n");
                current_player.updatePhaseDetails("<Based On Dice Results> \n");
                current_player.updatePhaseDetails("Attacker Losses : " + attackerLosses + " army." + "\n" + "Defender Losses : " + defenderLosses + " army.");

                // If defending country loses all armies
                if (countryB.getCurrentArmiesDeployed() < 1) {

                    game_view.updateView(countryA.getBelongsToPlayer().getName() + " has defeated all of " + countryB.getBelongsToPlayer().getName() + "'s armies in " + country2 + " and has occupied the country!");
                    defendingPlayerLostCountry(countryA, countryB, current_player,game_view);
                }

                //If player conquered all the country and have won the game
                if (current_player.assigned_countries.size() == map_elements.getCountries().size()) {
                    current_player.setHasBotWon(true);
                    game_view.updateView("" + current_player.getName() + " has won the game ! Congratulations ! ");
                    current_player.updatePhaseDetails(current_player.getName() + "Won");
                }
                randomTimeAttacks--;

            }else {
                game_view.updateView("Looks like random player type cannot attack anymore ! ");
                break;
            }
        }

    }
    /**
     * Checks if the defending player has lost armies
     * @param countryA object of Country class
     * @param countryB object of Country class
     * @param current_player object of Player class
     * 
     */
    private void defendingPlayerLostCountry(Country countryA, Country countryB, Player current_player, GameScreen game_view) {
        // Remove country from defender's list of occupied territories and adds to attacker's list
        countryB.getBelongsToPlayer().assigned_countries.remove(countryB);
        countryA.getBelongsToPlayer().assigned_countries.add(countryB);

        // Check if defender is eliminated from game
        if (countryB.getBelongsToPlayer().getAssignedCountries().size() == 0) {
            current_player.playerEliminated(countryA, countryB, game_view);
        }
        // Set country player to attacker
        countryB.setBelongsToPlayer(countryA.getBelongsToPlayer());
        current_player.updatePhaseDetails("\n"+countryB.getCountryName()+" has been captured ! ");

        //The attacking player must then place a number of armies
        //in the conquered country which is greater or equal than the number of dice that was used in the attack that
        //resulted in conquering the country
        int moveArmies = attackerDice;

        countryA.subtractArmy(moveArmies);
        countryB.addArmy(moveArmies);
        current_player.setCanGetCard(true);
        //current_player.addObserver(new PlayerView());
        current_player.updateDominationDetails();
    }
    /**
     * Updates the armies of the attacker/defender based on the dice roll result
     * @param attackerLosses number of armies lost by attacker
     * @param defenderLosses number of armies lost by defender
     * 
     */
    private void updateArmiesBasedOnDiceResult(int attackerLosses, int defenderLosses) {
        countryA.subtractArmy(attackerLosses);
        countryB.subtractArmy(defenderLosses);
    }
    /**
     * Compares the dice results for attacker and defender
     * and calculates the army loss for them
     * 
     */
    private void compareDiceResultsAndCalculateLosses() {
        // Calculate losses
        if (attackerRolls[0] > defenderRolls[0]) {
            defenderLosses++;
        } else if (attackerRolls[0] < defenderRolls[0] || Objects.equals(attackerRolls[0], defenderRolls[0])) {
            attackerLosses++;
        }
        // Index 1 = second highest pair
        if (attackerDice > 1 && defenderDice > 1) {

            if (attackerRolls[1] > defenderRolls[1]) {
                defenderLosses++;

            } else if (attackerRolls[1] < defenderRolls[1] || Objects.equals(attackerRolls[1], defenderRolls[1])) {
                attackerLosses++;
            }
        }
    }
    /**
     * Displays the dialogBox for dice roll of defender
     * @param gameView object of GameView class
     * @param current_player  object of Player class
     * @return JOptionPane message
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
     */
    private int getMaxNumberOfDicesForDefender(Country country) {
        return country.getCurrentArmiesDeployed() >= 2 ? 2 : 1;
    }
    /**
     * Checks if player can still continue to attack depending on the armies left
     * @param countryA object of Country class
     * @param countryB object of Country class
     * @return boolean value true or false
     */
    private boolean checkPlayerTurnCanContinue(Country countryA, Country countryB) {
        if(countryA.getCurrentArmiesDeployed() > 1 && !countryB.getBelongsToPlayer().getName().equals(countryA.getBelongsToPlayer().getName())){
            return true;
        }
        return false;
    }
    /**
     * Overrides fortify phase for Random bot
     * from the PlayerStrategy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param gameView object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void fortify(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country1);
        countryB = map_elements.getCountry(country2);
        rng = new Random();
        // Player inputs how many armies to move from country A to country B
        current_player.updatePhaseDetails("Repaint");
        current_player.updatePhaseDetails("===Fortification phase===");


        int armies = rng.nextInt(countryA.getCurrentArmiesDeployed()-1);
        if (armies  == 0) armies = 1;

        countryA.subtractArmy(armies);
        countryB.addArmy(armies);
        current_player.updatePhaseDetails(current_player.getName()+" moved "+armies+" army from "+countryA.getCountryName()+" to " + countryB.getCountryName());
        current_player.updatePhaseDetails("===Fortification ends===");
    }
    /**
     * Overrides reinforcement phase for Random bot
     * from the PlayerStrategy interface
     * @param country name of country where armies are to be reinforced
     * @param gameView object of GameView class
     * @param current_player object of Player class
     */
    @Override
    public void reinforce(String country, GameScreen game_view, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country);
        int armies=GamePlay.getReinforcementArmies(current_player, map_elements);
        game_view.updateView("\n===Reinforcement phase for Random type player begins===");
        game_view.updateView(current_player.getName() + " gets " + armies + " armies");
        try {
            if (current_player.getReinforceArmies()> 0) {
                current_player.subReinforceArmies(armies);
                countryA.addArmy(armies);
                game_view.updateView(current_player.getName() + " has chosen to reinforce " + countryA.getCountryName() + " with " + armies + " armies.");
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
