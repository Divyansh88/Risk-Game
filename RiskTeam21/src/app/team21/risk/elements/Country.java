package app.team21.risk.elements;


import java.io.Serializable;
import java.util.List;

import app.team21.risk.elements.Player;

/**
 * Last Updated on : 08/11/2018, Thursday
 * Country Bean class to get and set properties related to country.
 *
 * @author Yash Sheth and Divyansh Thakar
 * @version 2.0.0
 */
public class Country implements Serializable{
	private static final long serial_version_UID = 1L;
	public String country_name;
    public int x_cordinate;
    public int y_cordinate;
    public String belongs_to_continent;// to Represent county belongs to which continent
    public List<Country> neighbour_nodes; // to Represents adjacent country nodes
    public int current_armies_deployed;
    public Player belongs_to_player;


    /**
     * This constructor will create country object based provided parameters.
     *
     * @param country_name name of the country
     * @param x_cordinate For UI purpose start pixel
     * @param y_cordinate For UI purpose end pixel
     * @param continent_name name of the continent
     */
    public Country(String country_name, int x_cordinate, int y_cordinate, String continent_name) {
        this.country_name = country_name;
        this.x_cordinate = x_cordinate;
        this.y_cordinate = y_cordinate;
        this.belongs_to_continent = continent_name;
        this.current_armies_deployed = 0;
    }

    /**
     * This constructor will create country object based on provided player object and current deployed armies.
     *
     * @param belongs_to_player player object
     * @param current_armies_deployed number of armies deployed to the country
     */
    public Country(Player belongs_to_player, int current_armies_deployed) {
        this.belongs_to_player = belongs_to_player;
        this.current_armies_deployed = current_armies_deployed;
    }

    /**
     * This constructor will create country object based on provided country name.
     *
     * @param country_name name of the country
     */
    public Country(String country_name) {
        this.country_name = country_name;
    }

    /**
     * This constructor will create country object based on country name and continent name , for non UI purpose.
     *
     * @param country_name name of the country
     * @param belongs_to_continent name of the continent where country belongs to .
     */
    public Country(String country_name, String belongs_to_continent) {
        this.country_name = country_name;
        this.belongs_to_continent = belongs_to_continent;
    }

    /**
     * Over ride equals method in order to compare compare objects based on country name not country objects.
     */
    @Override
    public boolean equals(Object o) {
        String country_name = ((Country) o).getCountryName();
        return country_name.equals(getCountryName());
    }

    /**
     * Overriding hashCode method.
     */
    @Override
    public int hashCode() {
        return this.country_name.hashCode();
    }

    /**
     * getter method for player who own the country.
     *
     * @return belongs_to_player owner of the country (player object)
     */
    public Player getBelongsToPlayer() {
        return belongs_to_player;
    }

    /**
     * setter method to assign country to player.
     *
     * @param belongs_to_player player object
     */
    public void setBelongsToPlayer(Player belongs_to_player) {
        this.belongs_to_player = belongs_to_player;
    }

    /**
     * getter method give number of current armies deployed.
     * 
     * @return numerator of how many current armies deployed
     */
    public int getCurrentArmiesDeployed() {
        return current_armies_deployed;
    }

    /**
     * setter method to assign value of current armies deployed.
     * 
     * @param current_armies_deployed numerator of how many current armies deployed
     */
    public void setCurrentArmiesDeployed(int current_armies_deployed) {
        this.current_armies_deployed = current_armies_deployed;
    }

    /**
     * getter method gives the name of the country.
     * 
     * @return string country object
     */
    public String getCountryName() {
        return country_name;
    }

    /**
     * setter method assigns country name .
     * 
     * @param country_name name of the country
     */
    public void setCountryName(String country_name) {
        this.country_name = country_name;
    }

    /**
     * getter method gives the starting pixel of the country.
     * 
     * @return value of starting pixel
     */
    public int getx_cordinate() {
        return x_cordinate;
    }

    /**
     * setter method assigns starting pixel to the country.
     * 
     * @param x_cordinate value of pixel
     */
    public void setx_cordinate(int x_cordinate) {
        this.x_cordinate = x_cordinate;
    }

    /**
     * getter method gives the ending pixel of the country.
     * 
     * @return value of ending pixel
     */
    public int gety_cordinate() {
        return y_cordinate;
    }

    /**
     * setter method assigns ending pixel to the country.
     * 
     * @param y_cordinate value of pixel
     */
    public void sety_cordinate(int y_cordinate) {
        this.y_cordinate = y_cordinate;
    }

    /**
     * getter method gives details of country to continent it belongs to.
     * 
     * @return name of the continent the country belongs to
     */
    public String getBelongsToContinent() {
        return belongs_to_continent;
    }

    /**
     * setter method assigns details of country to continent it belongs to.
     * 
     * @param belongs_to_continent country object
     */
    public void setBelongsToContinent(String belongs_to_continent) {
        this.belongs_to_continent = belongs_to_continent;
    }

    /**
     * getter method gives details of neighbour countries.
     * 
     * @return list of neighbour countries
     */
    public List<Country> getNeighbourNodes() {
        return neighbour_nodes;
    }

    /**
     * setter method assigns details of neighbour countries.
     * 
     * @param neighbour_nodes list of neighbour countries
     */
    public void setNeighbourNodes(List<Country> neighbour_nodes) {
        this.neighbour_nodes = neighbour_nodes;
    }

    /**
     * this method assigns number of armies to the country.
     * 
     * @param n value of armies to be added
     */
    public void addArmy(int n) {
        current_armies_deployed += n;
    }

    /**
     * this method assigns number of armies to be removed from the country.
     * 
     * @param n value of the armies to be removed
     */
    public void subtractArmy(int n) {
        current_armies_deployed -= n;
    }

    /**
     * this method assigns player and number of armies to the player.
     * 
     * @param player name of the player
     * @param no_of_army value of the player
     */
    public void setPlayer(Player player, int no_of_army) {
        this.belongs_to_player = player;
        this.current_armies_deployed = no_of_army;
    }

}