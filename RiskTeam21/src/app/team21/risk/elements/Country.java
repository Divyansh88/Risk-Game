package app.team21.risk.elements;


import java.io.Serializable;
import java.util.List;

import app.team21.risk.elements.Player;

/**
 * Country Bean class to get and set properties related to country
 *
 * @author prashantp95
 */

public class Country{
    public String countryName;
    public int x_cordinate;
    public int y_cordinate;
    public String belongsToContinent;// to Represent county belongs to which continent
    public List<Country> neighbourNodes; // to Represents adjacent country nodes
    public int currentArmiesDeployed;
    public Player belongsToPlayer;


    /**
     * This constructor will create country object based provided parameters
     *
     * @param countryName   name of the country
     * @param x_cordinate    For UI purpose start pixel
     * @param y_cordinate      For UI purpose end pixel
     * @param continentName name of the continent
     */
    public Country(String countryName, int x_cordinate, int y_cordinate, String continentName) {
        this.countryName = countryName;
        this.x_cordinate = x_cordinate;
        this.y_cordinate = y_cordinate;
        this.belongsToContinent = continentName;
    }

    /**
     * This constructor will create country object based on provided player object and current deployed armies
     *
     * @param belongsToPlayer       player object
     * @param currentArmiesDeployed number of armies deployed to the country
     */
    public Country(Player belongsToPlayer, int currentArmiesDeployed) {
        this.belongsToPlayer = belongsToPlayer;
        this.currentArmiesDeployed = currentArmiesDeployed;
    }

    /**
     * This constructor will create country object based on provided country name
     *
     * @param countryName name of the country
     */
    public Country(String countryName) {
        this.countryName = countryName;
    }

    /**
     * This constructor will create country object based on country name and continent name , for non UI purpose
     *
     * @param countryName        Name of the country
     * @param belongsToContinent Name of the continent where country belongs to .
     */
    public Country(String countryName, String belongsToContinent) {
        this.countryName = countryName;
        this.belongsToContinent = belongsToContinent;
    }

    /**
     * Over ride equals method in order to compare compare objects based on country name not country objects
     */
    @Override
    public boolean equals(Object o) {
        String countryName = ((Country) o).getCountryName();
        return countryName.equals(this.getCountryName());
    }

    /**
     * Overriding hashCode method.
     */
    @Override
    public int hashCode() {
        return this.countryName.hashCode();
    }

    /**
     * getter method for player who own the country
     *
     * @return belongsToPlayer owner of the country (player object)
     */
    public Player getBelongsToPlayer() {
        return belongsToPlayer;
    }

    /**
     * setter method to assign country to player
     *
     * @param belongsToPlayer player object
     */
    public void setBelongsToPlayer(Player belongsToPlayer) {
        this.belongsToPlayer = belongsToPlayer;
    }

    /**
     * getter method give number of current armies deployed
     * @return numerator of how many current armies deployed
     */
    public int getCurrentArmiesDeployed() {
        return currentArmiesDeployed;
    }

    /**
     * setter method to assign value of current armies deployed
     * @param currentArmiesDeployed numerator of how many current armies deployed
     */
    public void setCurrentArmiesDeployed(int currentArmiesDeployed) {
        this.currentArmiesDeployed = currentArmiesDeployed;
    }

    /**
     * getter method gives the name of the country
     * @return string country object
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * setter method assigns country name 
     * @param countryName name of the country
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * getter method gives the starting pixel of the country
     * @return value of starting pixel
     */
    public int getx_cordinate() {
        return x_cordinate;
    }

    /**
     * setter method assigns starting pixel to the country
     * @param x_cordinate value of pixel
     */
    public void setx_cordinate(int x_cordinate) {
        this.x_cordinate = x_cordinate;
    }

    /**
     * getter method gives the ending pixel of the country
     * @return value of ending pixel
     */
    public int gety_cordinate() {
        return y_cordinate;
    }

    /**
     * setter method assigns ending pixel to the country
     * @param y_cordinate value of pixel
     */
    public void sety_cordinate(int y_cordinate) {
        this.y_cordinate = y_cordinate;
    }

    /**
     * getter method gives details of country to continent it belongs to
     * @return name of the continent the country belongs to
     */
    public String getBelongsToContinent() {
        return belongsToContinent;
    }

    /**
     * setter method assigns details of country to continent it belongs to
     * @param belongsToContinent country object
     */
    public void setBelongsToContinent(String belongsToContinent) {
        this.belongsToContinent = belongsToContinent;
    }

    /**
     * getter method gives details of neighbour countries 
     * @return list of neighbour countries
     */
    public List<Country> getNeighbourNodes() {
        return neighbourNodes;
    }

    /**
     * setter method assigns details of neighbour countries
     * @param neighbourNodes list of neighbour countries
     */
    public void setNeighbourNodes(List<Country> neighbourNodes) {
        this.neighbourNodes = neighbourNodes;
    }

    /**
     * this method assigns number of armies to the country
     * @param n value of armies to be added
     */
    public void addArmy(int n) {
        currentArmiesDeployed += n;
    }

    /**
     * this method assigns number of armies to be removed from the country
     * @param n value of the armies to be removed
     */
    public void subtractArmy(int n) {
        currentArmiesDeployed -= n;
    }

    /**
     * this method assigns player and number of armies to the player
     * @param player name of the player
     * @param noOfArmy value of the player
     */
    public void setPlayer(Player player, int noOfArmy) {
        this.belongsToPlayer = player;
        this.currentArmiesDeployed = noOfArmy;
    }

}
