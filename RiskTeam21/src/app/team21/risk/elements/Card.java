
package app.team21.risk.elements;

/**
 * Last Updated on : 06/11/2018, Tuesday
 * This class will handle operations on  cards.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public final class Card{

    private final String type;
    private final Country country;

    /**
     * this setter method assigning the country and the card  type.
     * 
     * @param type card type
     * @param country name of the country
     */
    public Card(String type, Country country) {
        this.type = type;
        this.country = country;
    }

    /**
     * getter method gives the country name and card type.
     * 
     * @return name of country and type
     */
    public String getName() {
        return country.getCountryName() + "," + type;
    }

    /**
     * getter method gives type of the card.
     * 
     * @return string card object
     */
    public String getType() {
        return type;
    }

    /**
     * getter method gives name of the country.
     * 
     * @return string country object
     */
    public Country getCountry() {
        return country;
    }
}

