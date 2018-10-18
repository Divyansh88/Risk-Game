package app.team21.risk.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Last Updated on : 18/10/2018, Thursday
 * Player class that retrieves and assigns different properties for the Player object.
 * 
 * @author Yash Sheth
 * @version 1.0.0
 *
 */
public class Player {
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
    
    public int phase;
	
    public List<Player> player_list;


    public Player(String name){
    	this.name=name;
    	assigned_countries=new ArrayList<Country>();
    	can_reinforce=false;
    	can_attack=false;
    	can_fortify=false;
    	can_end_turn=false;
    }
    /**
	 * @return the turn_value
	 */
	public int getTurnValue() {
		return turn_value;
	}

	/**
	 * @param turn_value the turn_value to set
	 */
	public void setTurnValue(int turn_value) {
		this.turn_value = turn_value;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the assigned_countries
	 */
	public List<Country> getAssignedCountries() {
		return assigned_countries;
	}

	/**
	 * @param assigned_countries the assignedCountries to set
	 */
	public void setAssignedCountries(List<Country> assigned_countries) {
		this.assigned_countries = assigned_countries;
	}

	/**
	 * @return the player_list
	 */
	public List<Player> getPlayerList() {
		return player_list;
	}

	/**
	 * @param playerList the playerList to set
	 */
	public void setPlayerList(List<Player> player_list) {
		this.player_list = player_list;
	}

    /**
	 * @return the intial_armies
	 */
	public int getInitialArmies() {
		return initial_armies;
	}
	/**
	 * @param intial_armies the intial_armies to set
	 */
	public void setInitialArmies(int intial_armies) {
		this.initial_armies = intial_armies;
	}
	/**
	 * @return the reinforce_armies
	 */
	public int getReinforceArmies() {
		return reinforce_armies;
	}
	/**
	 * @param reinforce_armies the reinforce_armies to set
	 */
	public void setReinforceArmies(int reinforce_armies) {
		this.reinforce_armies = reinforce_armies;
	}
	/**
	 * @return the can_reinforce
	 */
	public boolean isCanReinforce() {
		return can_reinforce;
	}
	/**
	 * @param can_reinforce the canReinforce to set
	 */
	public void setCanReinforce(boolean can_reinforce) {
		this.can_reinforce = can_reinforce;
	}
	/**
	 * @return the can_attack
	 */
	public boolean isCanAttack() {
		return can_attack;
	}
	/**
	 * @param can_attack the can_attack to set
	 */
	public void setCanAttack(boolean can_attack) {
		this.can_attack = can_attack;
	}
	/**
	 * @return the can_fortify
	 */
	public boolean isCanFortify() {
		return can_fortify;
	}
	/**
	 * @param can_fortify the can_fortify to set
	 */
	public void setCanFortify(boolean can_fortify) {
		this.can_fortify = can_fortify;
	}
	/**
	 * @return the can_end_turn
	 */
	public boolean isCanEndTurn() {
		return can_end_turn;
	}
	/**
	 * @param can_end_turn the can_end_turn to set
	 */
	public void setCanEndTurn(boolean can_end_turn) {
		this.can_end_turn = can_end_turn;
	}
	/**
	 * @return the phase
	 */
	public int getPhase() {
		return phase;
	}
	/**
	 * @param phase the phase to set
	 */
	public void setPhase(int phase) {
		this.phase = phase;
	}
	/**
	 * @param armies_selected
	 */
	public void subReinforceArmies(int armies_selected) {
		reinforce_armies-=armies_selected;
	}
	
}
