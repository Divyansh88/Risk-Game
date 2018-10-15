package app.team21.risk.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yash Sheth
 *
 */
public class Player {
    public String name; 
	public int turn_value;
    
	public Player player;
    public Player currentPlayer;

    public Country countryA;
    public Country countryB;

    public ArrayList<String> list;

    public List<Country> assignedCountries;
    
    public int initial_armies;
	public int reinforce_armies;
    
	public List<Player> playerList;


    public Player(String name){
    	this.name=name;
    	this.assignedCountries=new ArrayList<Country>();
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
	 * @return the assignedCountries
	 */
	public List<Country> getAssignedCountries() {
		return assignedCountries;
	}

	/**
	 * @param assignedCountries the assignedCountries to set
	 */
	public void setAssignedCountries(List<Country> assignedCountries) {
		this.assignedCountries = assignedCountries;
	}

	/**
	 * @return the playerList
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * @param playerList the playerList to set
	 */
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
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



}
