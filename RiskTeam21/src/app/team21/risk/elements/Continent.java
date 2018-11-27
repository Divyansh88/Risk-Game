package app.team21.risk.elements;

import app.team21.risk.elements.Country;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Last Updated on : 06/11/2018, Tuesday
 * Continent class that retrieves and assigns different properties for the continent object.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class Continent implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String continent_name;
    public List<Country> member_countries_list = new ArrayList<Country>();
    public int control_value;

    /**
     * This constructor will create continent object based provided continent name and control value.
     *
     * @param continent_name name of the continent
     * @param control_value control value
     */
    public Continent(String continent_name,int control_value) {
        this.continent_name = continent_name;
        this.control_value = control_value;
    }
    /**
     * This constructor will create continent object based provided continent name.
     *
     * @param continent_name name of the continent
     */
    public Continent(String continent_name) {
        this.continent_name = continent_name;
        this.control_value = control_value;
    }

    /**
     * default constructor.
     */
    public Continent() {

    }

    /**
     * Over ride equals method in order to compare compare objects based on continent name not continent objects.
     */
    @Override
    public boolean equals(Object continent_object) {
        String continent_name = ((Continent) continent_object).getContinentName();
        return continent_name.equals(this.getContinentName());
    }

    /**
     * Overriding hashCode method.
     */
    @Override
    public int hashCode() {
        return this.continent_name.hashCode();
    }

    /**
     * This method will return continent name.
     *
     * @return name of the continent (String format)
     */
    public String getContinentName() {
        return continent_name;
    }

    /**
     * setter method assigns the continent name.
     *
     * @param continent_name continent object
     */
    public void setContinentName(String continent_name) {
        this.continent_name = continent_name;
    }

    /**
     * getter method to get the list of the countries belong to that particular continent.
     *
     * @return member_countries_list list of countries
     */
    public List<Country> getMemberCountriesList() {
        return member_countries_list;
    }

    /**
     * setter method assigns countries list to the player.
     *
     * @param member_countries_list individual player countries list
     */
    public void setMemberCountriesList(List<Country> member_countries_list) {
        this.member_countries_list = member_countries_list;
    }

   
    /**
     * getter method gives the control value.
     *
     * @return value of control value
     */
    public int getControlValue() {
        return control_value;
    }

    /**
     * setter method assigns control value.
     *
     * @param control_value player object
     */
    public void setControlValue(int control_value) {
        this.control_value = control_value;
    }

}