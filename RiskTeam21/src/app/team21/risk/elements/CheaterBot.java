package app.team21.risk.elements;


import java.io.Serializable;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;
/**
 * This class implements the strategy for Cheater bot
 * @author Yash Sheth
 *
 */
public class CheaterBot implements PlayerStrategy,Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Country country_from;
    public Country country_to;
    
    /**
     * Overrides the attack phase for Cheater bot
     * from the PlayerStrategy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void attack(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        country_from = map_elements.getCountry(country1);
        country_to = map_elements.getCountry(country2);
        
        game_view.updateView(country_from.getBelongsToPlayer().getName() + " has defeated all of " + country_to.getBelongsToPlayer().getName() + "'s armies in " + country2 + " and has occupied the country!");
        defendingPlayerLostCountry(country_from, country_to, current_player,game_view);

        //If player conquered all the country and have won the game
        if (current_player.getAssignedCountries().size() == map_elements.getCountries().size()) {
            current_player.setHasBotWon(true);
            game_view.updateView("" + current_player.getName() + " has won the game ! Congratulations ! ");
            current_player.updatePhaseDetails(current_player.getName() + "Won");
        }
    }
    /**
     * Overrides the fortify phase for Cheater bot
     * from the PlayerStrategy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void fortify(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        country_from = map_elements.getCountry(country1);
        if (country_from != null){
            int armies = country_from.getCurrentArmiesDeployed();
            country_from.addArmy(armies);
            game_view.updateView("Cheater has doubled it's armies on " + country1);
        }
    }
    /**
     * Overrides the reinforce phase for Cheater bot
     * from the PlayerStrategy interface
     * @param country name of the attacker's country
     * @param game_view object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void reinforce(String country, GameScreen game_view, Player current_player,MapElements map_elements) throws NullPointerException {
        country_from = map_elements.getCountry(country);
        if (country_from !=null){
            int armies = country_from.getCurrentArmiesDeployed();
            country_from.addArmy(armies);
            game_view.updateView("Cheater has doubled it's armies on " + country);
        }
    }
    /**
     * Checks if the defending player has lost countries.
     * @param country_from object of Country class
     * @param country_to object of Country class
     * @param current_player object of Player class
     * 
     */
    private void defendingPlayerLostCountry(Country country_from, Country country_to, Player current_player,GameScreen game_view) {

        // Remove country from defender's list of occupied territories and adds to attacker's list
        country_to.getBelongsToPlayer().assigned_countries.remove(country_to);
        country_from.getBelongsToPlayer().assigned_countries.add(country_to);

        // Check if defender is eliminated from game
        if (country_to.getBelongsToPlayer().getAssignedCountries().size() == 0) {
            current_player.playerEliminated(country_from, country_to, game_view);
        }
        // Set country player to attacker
        country_to.setBelongsToPlayer(country_from.getBelongsToPlayer());

        //The attacking player must then place a number of armies
        //in the conquered country which is greater or equal than the number of dice that was used in the attack that
        //resulted in conquering the country
        int moveArmies = 1;

        country_from.subtractArmy(moveArmies);
        country_to.addArmy(moveArmies);
        current_player.setCanGetCard(true);
        //current_player.addObserver(new PlayerView());
        current_player.updateDominationDetails();
    }

}
