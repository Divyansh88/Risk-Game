package app.team21.risk.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicIconFactory;

import app.team21.risk.gamemodule.Deck;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;
import app.team21.risk.views.StartGame;


/**
 * Last Updated on : 06/11/2018, Tuesday 
 * Player class that retrieves and assigns different properties for the Player object.
 * 
 * @author Yash Sheth
 * @version 2.0.0
 */
public class Player extends Observable {
	public String name;
	public String history_text;
	public String status_text;
	public int turn_value;
	public double domination;
	public Player player;

	public int flag = 0;
	public Country country_A;
	public Country country_B;

	public List<Card> cards;

	public List<Country> assigned_countries;

	public int initial_armies;
	public int reinforce_armies;
	public int traded_set = 0;

	public int attacker_losses;
	public int defender_losses;
	public int attacker_dice;
	public int defender_dice;
	public Integer[] attacker_rolls;
	public Integer[] defender_rolls;

	public boolean can_reinforce;
	public boolean can_attack;
	public boolean can_fortify;
	public boolean can_end_turn;
	public boolean can_get_card;
	public boolean can_show_card;

	public String phase_details;
	public String update_message;

	/**
	 * This constructor will assign name to player.
	 * 
	 * @param name name of player
	 */
	public Player(String name) {
		this.name = name;
		cards = new ArrayList<Card>();
		assigned_countries = new ArrayList<Country>();
		can_reinforce = false;
		can_attack = false;
		can_fortify = false;
		can_end_turn = false;
	}

	/**
	 * getter method for turn value of a.
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
	 * getter method for phase details.
	 * 
	 * @return the phase_details
	 */
	public String getPhaseDetails() {
		return phase_details;
	}

	/**
	 * setter method to assign phase details.
	 * 
	 * @param phase_details the phase_details to set
	 */
	public void setPhaseDetails(String phase_details) {
		this.phase_details = phase_details;
	}

	/**
	 * getter method for update message.
	 * 
	 * @return the update_message
	 */
	public String getUpdateMessage() {
		return update_message;
	}

	/**
	 * setter method to assign update message.
	 * 
	 * @param update_message the update_message to set
	 */
	public void setUpdateMessage(String update_message) {
		this.update_message = update_message;
	}

	/**
	 * getter method for domination.
	 * 
	 * @return the domination
	 */
	public double getDomination() {
		return domination;
	}

	/**
	 * setter method to assign domination.
	 * 
	 * @param domination the domination to set
	 */
	public void setDomination(double domination) {
		this.domination = domination;
	}

	/**
	 * getter method to check can get card.
	 * 
	 * @return the can_get_card
	 */
	public boolean isCanGetCard() {
		return can_get_card;
	}

	/**
	 * setter method to assign can get card.
	 * 
	 * @param can_get_card the can_get_card to set
	 */
	public void setCanGetCard(boolean can_get_card) {
		this.can_get_card = can_get_card;
	}

	/**
	 * getter method for list of card.
	 * 
	 * @return the cards
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * setter method to assign list of cards.
	 * 
	 * @param cards the cards to set
	 */
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * getter method for traded set.
	 * 
	 * @return the traded_set
	 */
	public int getTradedSet() {
		return traded_set;
	}

	/**
	 * setter method to assign traded set.
	 * 
	 * @param traded_set the traded_set to set
	 */
	public void setTradedSet(int traded_set) {
		this.traded_set = traded_set;
	}

	/**
	 * getter method to check can show card.
	 * 
	 * @return the can_show_card
	 */
	public boolean isCanShowCard() {
		return can_show_card;
	}

	/**
	 * setter method to assign can show card.
	 * 
	 * @param can_show_card the can_show_card to set
	 */
	public void setCanShowCard(boolean can_show_card) {
		this.can_show_card = can_show_card;
	}

	/**
	 * this method will subtract reinforce armies.
	 * 
	 * @param armies_selected subtract this amount
	 */
	public void subReinforceArmies(int armies_selected) {
		reinforce_armies -= armies_selected;
	}

	/**
	 * this method will start the turn of player.
	 * 
	 * @param player_list list of players
	 * @param map_elements map elements
	 * @param game_view GameScreen object
	 */
	public void startTurn(List<Player> player_list, MapElements map_elements, GameScreen game_view) {
		setCanReinforce(true);
		setCanAttack(false);
		setCanFortify(false);
		setCanEndTurn(false);
		setCanGetCard(false);
		setCanShowCard(true);
		int armies = GamePlay.getReinforcementArmies(this, map_elements);
		this.setReinforceArmies(armies);
		this.setPhaseDetails("Its " + name + "'s turn and Reinforcement phase.");
		this.setUpdateMessage("phase");
		setChanged();
		notifyObservers();
		this.setUpdateMessage("domination");
		setChanged();
		notifyObservers();
		this.setUpdateMessage("cards");
		setChanged();
		notifyObservers();
		game_view.updateStatus("");
		game_view.ReinforcementButton(armies, this, map_elements);
	}

	/**
	 * this method will reinforcement on players.
	 * 
	 * @param armies_selected selected armies
	 * @param map_elements map elements
	 * @param country_name name of country
	 * @param game_view GameScreen object
	 */
	public void playerReinforces(int armies_selected, MapElements map_elements, String country_name, GameScreen game_view) {
		boolean reinforce_successful = false;
		this.setCanShowCard(true);
		Country selected_country = null;
		for (Country c : map_elements.getCountries()) {
			if (c.getCountryName().equals(country_name)) {
				selected_country = c;
				break;
			}
		}

		if (armies_selected <= this.getReinforceArmies()) {
			selected_country.addArmy(armies_selected);
			this.subReinforceArmies(armies_selected);
			reinforce_successful = true;
			history_text = "\n" + name + " reinforces " + selected_country.getCountryName() + " with " + armies_selected
					+ " armies.";
			game_view.updateView(history_text);
		} else {
			status_text = "Not Enough Armies";
			game_view.updateStatus(status_text);
		}

		if (this.getReinforceArmies() > 0 && reinforce_successful) {
			game_view.ReinforcementButton(this.getReinforceArmies(), this, map_elements);
		} else {
			status_text = " Reinforcement Phase Completed";
			this.setCanReinforce(false);
			this.setCanShowCard(false);
			this.setCanAttack(true);
			this.setCanFortify(true);
			this.setCanEndTurn(true);
			this.setPhaseDetails("Its " + name + "'s turn and Attack phase.");
			this.setUpdateMessage("phase");
			setChanged();
			notifyObservers(this);
			game_view.updateStatus(status_text);
			game_view.AttackButton(this, map_elements);
		}
	}

	/**
	 * this method will perform attack on players.
	 * 
	 * @param map_elements
	 * @param country_from
	 * @param country_to
	 * @param game_view
	 * @param mode_string
	 * @param deck
	 */
	public void playerAttacks(MapElements map_elements, Country country_from, Country country_to, GameScreen game_view, String mode_string, Deck deck) {
		int mode;
		if (mode_string.equalsIgnoreCase("ALL OUT ATTACK"))
			mode = 1;
		else
			mode = 0;
		if (can_attack) {
			if (!country_to.belongs_to_player.equals(this)) {
				if (country_from.getCurrentArmiesDeployed() > 1) {

					attacker_losses = 0;
					defender_losses = 0;

					if (mode == 1) {
						attacker_dice = getMaxDiceAttacker(country_from);
						defender_dice = getMaxDiceDefender(country_to);
					} else {
						attacker_dice = showAttackDiceDialog(country_from);
						defender_dice = showDefenceDiceDialog(country_to);
					}

					attacker_rolls = Dice.rollDice(attacker_dice).getDiceResult();
					defender_rolls = Dice.rollDice(defender_dice).getDiceResult();

					StringBuilder sb = new StringBuilder();
					sb.append(country_from.getBelongsToPlayer().getName() + " - Mr.Attacker Rolled ");
					sb.append(System.getProperty("line.separator"));
					for (int a : attacker_rolls) {
						sb.append(a);
						sb.append(System.getProperty("line.separator"));
					}
					sb.append(System.getProperty("line.separator"));
					sb.append(country_to.getBelongsToPlayer().getName() + " - Mr.Defender Rolled ");
					sb.append(System.getProperty("line.separator"));
					for (int a : defender_rolls) {
						sb.append(a);
						sb.append(System.getProperty("line.separator"));
					}

					game_view.updateView(sb.toString());

					calculateLosses();

					country_from.subtractArmy(attacker_losses);
					country_to.subtractArmy(defender_losses);
					game_view.updateView(country_from.getCountryName() + " lost " + attacker_losses + " armies.");
					game_view.updateView(country_to.getCountryName() + " lost " + defender_losses + " armies.");

					if (country_to.getCurrentArmiesDeployed() < 1) {
						game_view.updateView(country_from.getBelongsToPlayer().getName() + " has defeated all of "
								+ country_to.getBelongsToPlayer().getName() + "'s armies in "
								+ country_to.getCountryName() + " and has occupied the country!");
						defenderLostCountry(country_from, country_to, game_view);
						game_view.AttackButton(this, map_elements);
					}
					if (this.getAssignedCountries().size() == game_view.map_elements.getCountries().size()) {
						game_view.updateView("" + this.getName() + " has won the game ! Congratulations ! ");
						game_view.EndTurnButton();
						JOptionPane.showMessageDialog(null, "Congratulations! " + this.getName() + " won the game.");
						StartGame.createStartScreen();
					} else {
						checkCanContinue(game_view);
					}

					if (mode == 1 && !country_to.getBelongsToPlayer().equals(this)
							&& country_from.getCurrentArmiesDeployed() > 1) {
						playerAttacks(map_elements, country_from, country_to, game_view, mode_string, deck);
					}
				} else {
					game_view.updateStatus("Attacking Country must have at least 2 armies deployed.");
				}
			} else {
				game_view.updateStatus("Cannot attack your own country.");
			}
		} else {
			game_view.updateStatus("You cannot attack anymore.");
			status_text = " Attack Phase Completed";
			this.setPhaseDetails(name + " can End the turn now.");
			this.setUpdateMessage("phase");
			setChanged();
			notifyObservers(this);
			game_view.updateStatus(status_text);
			game_view.EndTurnButton();
		}
	}

	/**
	 * this method will perform fortification on players.
	 * 
	 * @param armies number of armies
	 * @param map_elements map elements
	 * @param country_from from country to attack
	 * @param country_to to country to attack
	 * @param game_view GameScreen object
	 */
	public void playerFortifies(int armies, MapElements map_elements, Country country_from, Country country_to, GameScreen game_view) {
		boolean fortify_successful = false;
		List<Country> unwanted = new ArrayList<>();
		if (isFortifyValid(country_from, country_to, unwanted, map_elements)
				&& (country_from.getCurrentArmiesDeployed()) - 1 >= armies) {
			country_from.subtractArmy(armies);
			country_to.addArmy(armies);
			fortify_successful = true;
		} else {
			fortify_successful = false;
		}

		if (fortify_successful) {
			this.setCanFortify(false);
			status_text = " Fortification Phase Completed";
			history_text = "\n" + name + " fortified " + country_to.getCountryName() + " with " + armies
					+ " armies from " + country_from.getCountryName();
			this.setPhaseDetails(name + " can End the turn now.");
			this.setUpdateMessage("phase");
			setChanged();
			notifyObservers(this);
			game_view.updateView(history_text);
			game_view.updateStatus(status_text);
			game_view.EndTurnButton();
		} else {
			status_text = "\nFortify Unsuccessful. Enter valid armies or Select Valid Country to Fortify.";
			game_view.updateStatus(status_text);
			game_view.FortifyButton(this, map_elements);
		}

	}

	/**
	 * This method will calculate who losses armies.
	 */
	public void calculateLosses() {
		// Calculate losses
		if (attacker_rolls[0] > defender_rolls[0]) {
			defender_losses++;
		} else if (attacker_rolls[0] < defender_rolls[0] || Objects.equals(attacker_rolls[0], defender_rolls[0])) {
			attacker_losses++;
		}
		// Index 1 = second highest pair
		if (attacker_dice > 1 && defender_dice > 1) {

			if (attacker_rolls[1] > defender_rolls[1]) {
				defender_losses++;

			} else if (attacker_rolls[1] < defender_rolls[1] || Objects.equals(attacker_rolls[1], defender_rolls[1])) {
				attacker_losses++;
			}
		}
	}

	/**
	 * this method will check fortify is valid or not.
	 * 
	 * @param c1 Country object 1
	 * @param c2 Country object 2
	 * @param unwanted list of countries
	 * @param map_elements map elements
	 * @return boolean boolean value for valid or not
	 */
	public boolean isFortifyValid(Country c1, Country c2, List<Country> unwanted, MapElements map_elements) {
		c1=map_elements.getCountry(c1.getCountryName());
		
		if (c1.getNeighbourNodes().contains(c2) && c2.getNeighbourNodes().contains(c1) && c1.getBelongsToPlayer().equals(c2.getBelongsToPlayer()))
			return true;

		if (unwanted.contains(c1))
			return false;
		
		unwanted.add(c1);

		for (Country c : c1.getNeighbourNodes()) {
			if (!unwanted.contains(c) && isFortifyValid(c, c2, unwanted, map_elements))
				return true;
		}
		return false;

	}

	/**
	 * this method will be called if defender lost country. 
	 * 
	 * @param country_from from country
	 * @param country_to to country
	 * @param game_view GameScreen object
	 */
	public void defenderLostCountry(Country country_from, Country country_to, GameScreen game_view) {
		country_to.getBelongsToPlayer().getAssignedCountries().remove(country_to);
		country_from.getBelongsToPlayer().getAssignedCountries().add(country_to);

		if (country_to.getBelongsToPlayer().getAssignedCountries().size() == 0) {
			playerEliminated(country_from, country_to, game_view);
		}

		country_to.setBelongsToPlayer(country_from.getBelongsToPlayer());
		game_view.updateView("\n" + country_to.getCountryName() + " has been captured ! ");

		int move_armies = 0;

		move_armies = showMoveArmiesDialogBox(country_from, game_view);
		if (move_armies > 0) {
			country_from.subtractArmy(move_armies);
			country_to.addArmy(move_armies);
		}
		game_view.updateView(country_from.getBelongsToPlayer().getName() + " moved " + move_armies + " armies to"
				+ country_to.getCountryName() + "!");
		if (!isCanGetCard())
			can_get_card = true;
		this.setUpdateMessage("domination");
		setChanged();
		notifyObservers();
	}

	/**
	 * this method will eliminate player.
	 * 
	 * @param country_from from country
	 * @param country_to to country
	 * @param game_view GameScreen object
	 */
	public void playerEliminated(Country country_from, Country country_to, GameScreen game_view) {
		game_view.updateView(country_to.getBelongsToPlayer().getName()
				+ " has no countries left, player looses the game and is eliminated");
		game_view.player_list.remove(country_to.getBelongsToPlayer());
	}

	/**
	 * this method will check can continue or not.
	 * 
	 * @param game_view GameScreen object
	 */
	public void checkCanContinue(GameScreen game_view) {
		can_attack = false;
		can_fortify = false;
		for (Country c : this.getAssignedCountries()) {

			if (c.getCurrentArmiesDeployed() > 1) {
				can_attack = true;
				can_fortify = true;
				break;
			}
		}
		if (!can_attack && !can_fortify) {
			status_text = " Attack Phase Completed";
			this.setPhaseDetails(name + " can End the turn now.");
			this.setUpdateMessage("phase");
			setChanged();
			notifyObservers(this);
			game_view.updateStatus(status_text);
			game_view.EndTurnButton();
		}
	}

	/**
	 * this method exchange armies.
	 * 
	 * @return the armies 
	 */
	public int getExchangeArmies() {
		int armies = 0;
		switch (traded_set) {
		case 0:
			armies = 4;
			break;
		case 1:
			armies = 6;
			break;
		case 2:
			armies = 8;
			break;
		case 3:
			armies = 10;
			break;
		case 4:
			armies = 12;
			break;
		case 5:
			armies = 15;
			break;
		default:
			armies = 0;
			break;
		}
		if (traded_set > 5) {
			armies = 15 + 5 * (traded_set - 5);
		}
		return armies;
	}

	/**
	 * this method will show attack dice dialog.
	 * 
	 * @param country Country class object
	 * @return integer number of dices which attacker select to throw
	 */
	public int showAttackDiceDialog(Country country) {

		int dices = getMaxDiceAttacker(country);

		Integer[] choices = new Integer[dices];
		for (int i = 0; i < dices; i++)
			choices[i] = i + 1;

		return (Integer) JOptionPane.showInputDialog(null, "Attacker Select",
				country.getBelongsToPlayer().getName() + "! How many dice will you roll?", JOptionPane.OK_OPTION,
				BasicIconFactory.getMenuArrowIcon(), choices, choices[0]);
	}

	/**
	 * this method will get maximum dice that attacker have.
	 * 
	 * @param country Country class object
	 * @return integer maximum dices attacker have
	 */
	public int getMaxDiceAttacker(Country country) {
		int dices = 1;
		if (country.getCurrentArmiesDeployed() > 3) {
			dices = 3;
		} else if (country.getCurrentArmiesDeployed() == 3) {
			dices = 2;
		}
		return dices;
	}

	/**
	 * this method will show defence dice dialog. 
	 * 
	 * @param country Country class object
	 * @return integer number of dices which attacker select to throw
	 */
	public int showDefenceDiceDialog(Country country) {

		int dices = getMaxDiceDefender(country);

		Integer[] choices = new Integer[dices];
		for (int i = 0; i < dices; i++)
			choices[i] = i + 1;

		return (Integer) JOptionPane.showInputDialog(null, "Defender Select",
				country.getBelongsToPlayer().getName() + "! How many dice will you roll?", JOptionPane.OK_OPTION,
				BasicIconFactory.getMenuArrowIcon(), choices, choices[0]);
	}

	/**
	 * this method will get maximum dice that defender have.
	 * 
	 * @param country Country class object
	 * @return maximum dices defender have
	 */
	public int getMaxDiceDefender(Country country) {
		int dices = country.getCurrentArmiesDeployed() >= 2 ? 2 : 1;

		return dices;
	}

	/**
	 * this method will show move armies dialogbox.
	 * 
	 * @param country Country class object
	 * @param game_view GameScreen object
	 * @return integer number of armies  
	 */
	public int showMoveArmiesDialogBox(Country country, GameScreen game_view) {
		Integer[] choices = new Integer[country.getCurrentArmiesDeployed() - 1];
		for (int i = 0; i < choices.length; i++)
			choices[i] = i + 1;

		return (Integer) JOptionPane.showInputDialog(null, "Select Armies",
				country.getBelongsToPlayer().getName() + "! How many armies will you move?", JOptionPane.OK_OPTION,
				BasicIconFactory.getMenuArrowIcon(), choices, choices[0]);

	}

}