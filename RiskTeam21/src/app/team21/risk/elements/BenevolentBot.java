package app.team21.risk.elements;

import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

/**
 * This class implements the strategy for Benevolent bot
 * @author Akshay Shah
 *
 */
public class BenevolentBot implements PlayerStrategy {

    public Country countryA;
    public Country countryB;
    
    /**
     * Overrides the attack phase for benevolent bot
     * from the PlayerStartegy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param gameView object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void attack(String country1, String country2, GameScreen gameView, Player current_player,MapElements map_elements) {
        gameView.updateView("Skipping Attack Phase as player is benevolent.");
        gameView.updateView(current_player.getName()+" is too afraid to attack ! Skipping the attack phase.");
    }
    /**
     * Overrides the fortify phase for benevolent bot
     * from the PlayerStrategy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param gameView object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void fortify(String country1, String country2, GameScreen gameView, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country1);
        countryB = map_elements.getCountry(country2);

        // Player inputs how many armies to move from country A to country B
        current_player.updatePhaseDetails("Repaint");
        current_player.updatePhaseDetails("===Fortification phase===");

        int armies = countryA.getCurrentArmiesDeployed() - 1;

        countryA.subtractArmy(armies);
        countryB.addArmy(armies);
        gameView.updateView("Benevolent moved "+armies+" army from "+countryA.getCountryName()+" to " + countryB.getCountryName());
        current_player.updatePhaseDetails("You moved "+armies+" army from "+countryA.getCountryName()+" to " + countryB.getCountryName());
        current_player.updatePhaseDetails("===Fortification ends===");

    }
    /**
     * Overrides the reinforcement phase for benevolent bot
     * from the PlayerStrategy interface
     * @param country name of the the player's country
     * @param gameView object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void reinforce(String country_name, GameScreen gameView, Player current_player,MapElements map_elements) {
		
    	for (Country c : map_elements.getCountries()) {
			if (c.getCountryName().equals(country_name)) {
				countryA = c;
				break;
			}
		}
    	int armies=GamePlay.getReinforcementArmies(current_player, map_elements);
        gameView.updateView("\n===Reinforcement phase for Benevolent type player begins===");
        gameView.updateView(current_player.getName() + " gets " + armies+ " armies");
        
        try {
            if (armies > 0) {   
                current_player.subReinforceArmies(armies);
                countryA.addArmy(armies);
                gameView.updateView(current_player.getName() + " has chosen to reinforce " + countryA.getCountryName() + " with " + armies + " armies.");
                if (current_player.getTotalArmies() == 0) {
                    gameView.updateView("You do not have any armies left to reinforce");
                    gameView.updateView("===Reinforcement phase for Benevolent type player ends===\n");
                    current_player.updatePhaseDetails("\nReinforcement Phase ends");
                }
                current_player.updateDominationDetails();
            }

        } catch (Exception e) {
            gameView.updateView("\nSystem Error or Exception is thrown for reinforce method");
        }
    }
}
