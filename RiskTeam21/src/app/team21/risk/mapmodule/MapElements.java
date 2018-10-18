package app.team21.risk.mapmodule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.team21.risk.elements.*;
/**
 * @author Yash Sheth
 *
 */
public class MapElements {

	private static MapElements map_elements;
	public HashMap<String, String> map_details=new HashMap<>();
	public List<Continent> continent_list=new ArrayList<>();
	public HashMap<Continent, List<Country>> continent_country_map=new HashMap<>();
	public HashMap<Country, List<Country>> country_neighbour_map=new HashMap<>();
	public List<Player> player_list = new ArrayList<>();
	public boolean is_correct_map = true;
	public String error_message;
	public Player current_player;
	public String phase_details;

	/**
	     * Default Constructor
	     */
	    private MapElements() {
	        //To Prevent Other classes from creating object.
	    }

	/**
	 * This method will return singleton instance for the MapElements class
	 * @param instance creates an instance of MapElements class
	 * @return single map_elements instance
	 */
	public static MapElements getInstance(MapElements instance) {
		if (null == map_elements) {
			if (instance == null)
				map_elements = new MapElements();
			else
				map_elements = instance;
		}
		return map_elements;
	}

	 public static MapElements getNewMapInstance(HashMap<String, String> map_details){
		 map_elements=new MapElements();
		 map_elements.setMapDetail(map_details);
		 return map_elements;
	 }
	/**
	 * This method will assign Map Elements info to the MapElements class
	 * 
	 * @param map_elementsToSet
	 *            map_elements object
	 */
	public static void setInstance(MapElements map_elements_to_set) {
		map_elements = map_elements_to_set;
	}

	/**
	 * This method returns the instance of map_elements
	 * 
	 * @return map_elements object
	 */
	public static MapElements getInstance() {
		return getInstance(null);
	}

	/**
	 * To get the phase details
	 * 
	 * @return phase object
	 */
	public String getPhaseDetails() {
		return phase_details;
	}

	/**
	 * To set the phase details
	 * 
	 * @param phaseDetails
	 *            info about phase
	 */
	public void setPhaseDetails(String phase_details) {
		this.phase_details = phase_details;
	}


	/**
	 * this method verify weather the selected map validate the rules of
	 * CorrectMap constraints
	 *
	 * @return true if the map is correct; otherwise return false
	 */
	public boolean is_correct_map() {
		return is_correct_map;
	}

	/**
	 * setter method checks is the map is correct and then assigns map
	 *
	 * @param is_correct_map
	 *            boolean value
	 */
	public void setCorrectMap(boolean is_correct_map) {
		this.is_correct_map = is_correct_map;
	}

	/**
	 * getter method for players who selected the map to play on
	 *
	 * @return errorMessage message display on the console
	 */
	public String getErrorMessage() {
		return error_message;
	}

	/**
	 * setter method to assign the message when we select a map which doesn't
	 * agree to the rules
	 *
	 * @param errorMessage
	 *            generates a error message
	 */
	public void setErrorMessage(String error_message) {
		this.error_message += "\n"+error_message;

	}

	/**
	 * getter method to get the selected map details
	 *
	 * @return mapDetails Complete details of selected map
	 */
	public HashMap<String, String> getMapDetail() {
		return map_details;
	}

	/**
	 * setter method to assign mapDetails
	 *
	 * @param mapDetail
	 *            map object
	 */
	public void setMapDetail(HashMap<String, String> map_details) {
		this.map_details= map_details;

	}

	/**
	 * getter method to get the informations of neighboring countries
	 *
	 * @return countryAndNeighborsMap list of neighboring countries details
	 */
	public HashMap<Country, List<Country>> getCountryNeighboursMap() {
		return country_neighbour_map;
	}

	/**
	 * setter method to assign countries and its neighbors in the map
	 *
	 * @param countryAndNeighborsMap
	 *            map object
	 */
	public void setCountryNeighboursMap(HashMap<Country, List<Country>> country_neighbour_map) {
		this.country_neighbour_map = country_neighbour_map;
	}

	/**
	 * getter method to get list of continents in the selected map
	 *
	 * @return continentList list of continents in the map
	 */
	public List<Continent> getContinentList() {
		return continent_list;
	}

	/**
	 * setter method to assign continents to the map
	 *
	 * @param continentList
	 *            map object
	 */
	public void setContinentList(List<Continent> continents_list) {
		this.continent_list = continents_list;
	}

	/**
	 * getter method to get list of countries belong to the continent
	 *
	 * @return continentCountryMap map object
	 */
	public HashMap<Continent, List<Country>> getContinentCountryMap() {
		return continent_country_map;
	}

	/**
	 * setter method to assign countries to the continent
	 *
	 * @param continentCountryMap
	 *            map object
	 */
	public void setContinentCountryMap(HashMap<Continent, List<Country>> continent_country_map) {
		this.continent_country_map = continent_country_map;
	}

	/**
	 * getter method to get the list of the players
	 *
	 * @return player_list player details
	 */
	public List<Player> getplayer_list() {
		return player_list;
	}

	/**
	 * setter method to assign players list
	 *
	 * @param player_list
	 *            details of players
	 */
	public void setplayer_list(List<Player> player_list) {
		this.player_list = player_list;
	}

	/**
	 * @param continentName
	 *            Name of the continent.
	 * @return Countries in the continent
	 */
	public List<Country> getCountriesByContinent(String continent_name) {
		List<Country> continent_country_list = new ArrayList<>();
		for (Country c : getCountryNeighboursMap().keySet()) {
			if (c.getBelongsToContinent().equalsIgnoreCase(continent_name)) {
				continent_country_list.add(c);
			}
		}
		return continent_country_list;
	}

	/**
	 * Gets the list of countries in the map
	 *
	 * @return Countries in the map
	 */
	public List<Country> getCountries() {
		List<Country> countries = new ArrayList<>();
		countries.addAll(getCountryNeighboursMap().keySet());
		return countries;
	}
	
	

	/**
	 * To get the currentPlayer Playing in map
	 * 
	 * @return current player name
	 */
	public Player getCurrentPlayer() {
		return current_player;
	}

	/**
	 * To set the current player in map
	 * 
	 * @param currentPlayer
	 *            name of the current player
	 */
	public void setCurrentPlayer(Player current_player) {
		this.current_player = current_player;
	}
	
	public String updateMR(){
		StringBuilder sb=new StringBuilder();
		sb.append("======|HOW THE MAP LOOKS LIKE|======\n");
		for (Continent c : getContinentList()) {
			sb.append("\n\n" + c.getContinentName() + "  " + c.getControlValue() + "\n");
			for (Country c1 : c.getMemberCountriesList())
				sb.append("\n"+c1.getCountryName());
		}
		return	sb.toString();
	}

}
