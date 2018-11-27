package app.team21.risk.elements;

import java.io.Serializable;

import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

/**
 * This class implements the strategy for Benevolent bot
 * @author Yash Sheth
 *
 */
public class BenevolentBot implements PlayerStrategy,Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Country country_from;
    public Country country_to;
    
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
        country_from = map_elements.getCountry(country1);
        country_to = map_elements.getCountry(country2);
        // Player inputs how many armies to move from country A to country B
        int armies = country_from.getCurrentArmiesDeployed() - 1;
        country_from.subtractArmy(armies);
        country_to.addArmy(armies);
        gameView.updateView("Benevolent moved "+armies+" army from "+country_from.getCountryName()+" to " + country_to.getCountryName());
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
				country_from = c;
				break;
			}
		}
    	int armies=GamePlay.getReinforcementArmies(current_player, map_elements);
        gameView.updateView(current_player.getName() + " gets " + armies+ " armies");
        
        try {
            if (armies > 0) {   
                current_player.subReinforceArmies(armies);
                country_from.addArmy(armies);
                gameView.updateView(current_player.getName() + " has chosen to reinforce " + country_from.getCountryName() + " with " + armies + " armies.");
                if (current_player.getTotalArmies() == 0) {
                    gameView.updateView("You do not have any armies left to reinforce");
                    current_player.updatePhaseDetails("\nReinforcement Phase ends");
                }
                current_player.updateDominationDetails();
            }

        } catch (Exception e) {
            gameView.updateView("\nSystem Error or Exception is thrown for reinforce method");
        }
    }
}
