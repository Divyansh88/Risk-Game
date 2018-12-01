package app.team21.risk.elements;

import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

/**
 * Last Updated on: 29/11/2018, Thursday 
 * This is an interface of player's attack,reinforcement,fortify method.
 * 
 * @author Yash Sheth
 * @version 3.0.0
 */
public interface PlayerStrategy {

	/**
	 * Attack Method for the player. Based on Player type , attack method will be executed according in respective java class..
	 * 
	 * @param country1 Country from where you want to attack 
	 * @param country2 Enemy Country
	 * @param game_view current GameScreen Object
	 * @param current_player object of Player class
	 * @param map_elements map elements
	 */
	public void attack(String country1, String country2, GameScreen game_view, Player current_player, MapElements map_elements);

	/**
	 * Fortify Method for the player. Based on Player type , fortify method will be executed according in respective java class.
	 *  
	 * @param country1 country from where you want to move army
	 * @param country2 country where you want to move army
	 * @param game_view current GameScreen Object
	 * @param current_player object of Player class
	 * @param map_elements map elements
	 */
	public void fortify(String country1, String country2, GameScreen game_view, Player current_player, MapElements map_elements);

	/**
	 * Reinforce Method for the player. Based on Player type , Reinforce method will be executed according in respective java class.
	 *  
	 * @param country country where you want to reinforce Army
	 * @param game_view current GameScreen Object
	 * @param current_player object of Player class
	 * @param map_elements map elements
	 */
	public void reinforce(String country, GameScreen game_view, Player current_player, MapElements map_elements);
}
