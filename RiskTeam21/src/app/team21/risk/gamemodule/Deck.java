package app.team21.risk.gamemodule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.team21.risk.elements.Card;
import app.team21.risk.elements.Country;


/**
 * Last Updated on : 06/11/2018, Tuesday 
 * Allows the creation of the Risk deck containing the 42 Risk cards.
 * 
 * @author Yash Sheth
 * @version 2.0.0
 */
public class Deck{

    private int i;

    private String input;
    private String name;

    private String[] typesArray;
    public ArrayList<Card> deck;
    private Card drawCard;

   /**
    * Creates all cards, one for each territory. Each card has either a type of Infantry, Cavalry, or Artillery.
    * 
    * @param countries list of country names
    */
    public Deck(List<Country> countries) {

        Collections.shuffle(countries);

        //Types of cards
        typesArray = new String[]{"Infantry", "Cavalry", "Artillery"};

        deck = new ArrayList<Card>();

        for (i = 0; i < countries.size(); i++) {
            // Add new cards to deck
            deck.add(new Card(typesArray[i % 3], countries.get(i)));
           
        }
        Collections.shuffle(deck);
    }

    /**
     * Public default constructor to access other methods.
     */
    public Deck(){
        deck = new ArrayList<Card>();
    }

    /**
     * Removes a card from the deck and return it.
     * 
     * @return card object
     */
    public Card draw() {

        drawCard = deck.get(0);
        deck.remove(0);

        return drawCard;
    }

   /**
    * Add a card to the deck.
    * 
    * @param card name of the card which is to be added to deck
    */
    public void add(Card card) {

        deck.add(card);
    }

   /**
    * Shuffle the deck of cards.
    */
    public void shuffle() {

        Collections.shuffle(deck);
    }
}
