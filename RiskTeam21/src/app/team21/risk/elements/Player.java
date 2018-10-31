package app.team21.risk.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;

/**
 * Last Updated on : 18/10/2018, Thursday
 * Player class that retrieves and assigns different properties for the Player object.
 * 
 * @author Yash Sheth
 * @version 1.0.0
 *
 */
public class Player extends Observable{
	public String name; 
	public int turn_value;

	public Player player;
	public Player current_player;

	public Country country_A;
	public Country country_B;

	public ArrayList<String> list;

	public List<Country> assigned_countries;

	public int initial_armies;
	public int reinforce_armies;

	public boolean can_reinforce;
	public boolean can_attack;
	public boolean can_fortify;
	public boolean can_end_turn;

	public String phase_details;


	public List<Player> player_list;


	/**
	 * This constructor will assign name to player.
	 * 
	 * @param name name of player
	 */
	public Player(String name){
		this.name=name;
		assigned_countries=new ArrayList<Country>();
		can_reinforce=false;
		can_attack=false;
		can_fortify=false;
		can_end_turn=false;
	}
	/**
	 * getter method for turn value of player.
	 * 
	 * @return the turn_value
	 */
	public int getTurnValue() {
		return turn_value;
	}

	/**
	 * setter method to assign turn value to player.
	 * 
	 * @param turn_value the turn_value to set
	 */
	public void setTurnValue(int turn_value) {
		this.turn_value = turn_value;
	}

	/**
	 * getter method for turn name of player.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter method to assign name to player.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter method for countries.
	 * 
	 * @return the assigned_countries
	 */
	public List<Country> getAssignedCountries() {
		return assigned_countries;
	}

	/**
	 * setter method to assign countries.
	 * 
	 * @param assigned_countries the assignedCountries to set
	 */
	public void setAssignedCountries(List<Country> assigned_countries) {
		this.assigned_countries = assigned_countries;
	} 

	/**
	 * getter method for player list.
	 * 
	 * @return the player_list
	 */
	public List<Player> getPlayerList() {
		return player_list;
	}

	/**
	 * setter method to assign player list.
	 * 
	 * @param player_list the playerList to set
	 */
	public void setPlayerList(List<Player> player_list) {
		this.player_list = player_list;
	}

	/**
	 * getter method for initial armies.
	 * 
	 * @return the intial_armies
	 */
	public int getInitialArmies() {
		return initial_armies;
	}
	/**
	 * setter method to assign initial armies.
	 * 
	 * @param intial_armies the intial_armies to set
	 */
	public void setInitialArmies(int intial_armies) {
		this.initial_armies = intial_armies;
	}
	/**
	 * getter method for reinforce armies.
	 * 
	 * @return the reinforce_armies
	 */
	public int getReinforceArmies() {
		return reinforce_armies;
	}
	/**
	 * setter method to assign reinforce armies.
	 * 
	 * @param reinforce_armies the reinforce_armies to set
	 */
	public void setReinforceArmies(int reinforce_armies) {
		this.reinforce_armies = reinforce_armies;
	}
	/**
	 * getter method to check can reinforce.
	 * 
	 * @return the can_reinforce
	 */
	public boolean isCanReinforce() {
		return can_reinforce;
	}
	/**
	 * setter method to assign can reinforce value.
	 * 
	 * @param can_reinforce the canReinforce to set
	 */
	public void setCanReinforce(boolean can_reinforce) {
		this.can_reinforce = can_reinforce;
	}
	/**
	 * getter method to check can attack.
	 * 
	 * @return the can_attack
	 */
	public boolean isCanAttack() {
		return can_attack;
	}
	/**
	 * setter method to assign can attack value.
	 * 
	 * @param can_attack the can_attack to set
	 */
	public void setCanAttack(boolean can_attack) {
		this.can_attack = can_attack;
	}
	/**
	 * getter method to check can fortify.
	 * 
	 * @return the can_fortify
	 */
	public boolean isCanFortify() {
		return can_fortify;
	}
	/**
	 * setter method to assign can fortify value.
	 * 
	 * @param can_fortify the can_fortify to set
	 */
	public void setCanFortify(boolean can_fortify) {
		this.can_fortify = can_fortify;
	}
	/**
	 * getter method to check can end turn.
	 * 
	 * @return the can_end_turn
	 */
	public boolean isCanEndTurn() {
		return can_end_turn;
	}
	/**
	 * setter method to assign can end turn value.
	 * 
	 * @param can_end_turn the can_end_turn to set
	 */
	public void setCanEndTurn(boolean can_end_turn) {
		this.can_end_turn = can_end_turn;
	}

	/**
	 * @return the phase_details
	 */
	public String getPhaseDetails() {
		return phase_details;
	}
	/**
	 * @param phase_details the phase_details to set
	 */
	public void setPhaseDetails(String phase_details) {
		this.phase_details = phase_details;
	}



	/**
	 * this method will subtract reinforce armies.
	 * 
	 * @param armies_selected subtract this amount
	 */
	public void subReinforceArmies(int armies_selected) {
		reinforce_armies-=armies_selected;
	}


	public void playerReinforces(int armies_selected,MapElements map_elements,String country_name,GameScreen game_view){
		boolean reinforce_successful=false;
//		setPhaseDetails("Its"+name+"'s turn and Reinforcement phase.");
//		setChanged();
//		notifyObservers(this);
		String status_text="";

		Country selected_country=null;
		for(Country c:map_elements.getCountries()){
			if(c.getCountryName().equals(country_name)){
				selected_country=c;
				break;
			}
		}

		if(armies_selected<=current_player.getReinforceArmies()){
			selected_country.addArmy(armies_selected);
			current_player.subReinforceArmies(armies_selected);
			reinforce_successful=true;
			String history_text="\n"+current_player.getName()+" reinforces "+ selected_country.getCountryName()+" with "+armies_selected+" armies.";
			game_view.updateView(history_text);
		}
		else{
			status_text="Not Enough Armies";
			game_view.updateStatus(status_text);
		}

		if(current_player.getReinforceArmies()>0&&reinforce_successful){
			game_view.ReinforcementButton(current_player.getReinforceArmies(),current_player,map_elements);
		}
		else{
			status_text=" Reinforcement Phase Completed";
			current_player.setCanReinforce(false);
			current_player.setCanFortify(true);
			current_player.setCanEndTurn(true);
			
			game_view.updateStatus(status_text);
			game_view.FortifyButton(current_player,map_elements);
		}
	}

	public void playerFortifies(int armies,MapElements map_elements,Country country_from,Country country_to,GameScreen game_view){
		boolean fortify_successful=false;
		setPhaseDetails("Its"+name+"'s turn and Fortification phase.");
		setChanged();
		notifyObservers(this);
		String status_text;

		if(country_from.getNeighbourNodes().contains(country_to)&&country_to.getNeighbourNodes().contains(country_from)&&(country_from.getCurrentArmiesDeployed())-1>=armies){
			country_from.subtractArmy(armies);
			country_to.addArmy(armies);
			fortify_successful=true;
		}
		else{
			fortify_successful=false;
		}

		if(fortify_successful){
			current_player.setCanFortify(false);
			status_text=" Reinforcement Phase Completed";
			String history_text="\n"+current_player.getName()+" fortified "+ country_to.getCountryName()+" with "+armies+" armies from "+country_from.getCountryName();
			game_view.updateView(history_text);
			game_view.updateStatus(status_text);
			game_view.EndTurnButton();
		}
		else{
			status_text="\nFortify Unsuccessful. Enter valid armies or Select Valid Country to Fortify.";
			game_view.updateStatus(status_text);
			game_view.FortifyButton(current_player, map_elements);
		}

	}
}