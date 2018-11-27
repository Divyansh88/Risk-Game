package app.team21.risk.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicIconFactory;

import app.team21.risk.gamemodule.Deck;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;
import app.team21.risk.views.StartGame;

/**
 * Last Updated on : 06/11/2018, Tuesday Player class that retrieves and assigns
 * different properties for the Player object.
 * 
 * @author Yash Sheth
 * @version 2.0.0
 */
public class Player extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String history_text;
	public String status_text;
	public int turn_value;
	public double domination;

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
	public boolean has_player_won;

	public boolean is_bot;
	public String type;
	public boolean has_bot_won;

	public PlayerStrategy strategy;

	public boolean is_tournament_mode;
	public int turns=100000;
	
	public String phase_details;
	public String update_message;
	public boolean load_game=false;
	/**
	 * This constructor will assign name to player.
	 * 
	 * @param name
	 *            name of player
	 */
	public Player(String name, boolean is_bot, String type) {
		this.name = name;
		cards = new ArrayList<Card>();
		assigned_countries = new ArrayList<Country>();
		can_reinforce = false;
		can_attack = false;
		can_fortify = false;
		can_end_turn = false;
		this.is_bot = is_bot;
		this.type = type;
	}

	public void resetData(int turns){
		assigned_countries = new ArrayList<Country>();
		can_reinforce = false;
		can_attack = false;
		can_fortify = false;
		can_end_turn = false;
		this.turns=turns;
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
	 * @param turn_value
	 *            the turn_value to set
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
	 * @param name
	 *            the name to set
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
	 * @param assigned_countries
	 *            the assignedCountries to set
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
	 * @param intial_armies
	 *            the intial_armies to set
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
	 * @param reinforce_armies
	 *            the reinforce_armies to set
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
	 * @param can_reinforce
	 *            the canReinforce to set
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
	 * @param can_attack
	 *            the can_attack to set
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
	 * @param can_fortify
	 *            the can_fortify to set
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
	 * @param can_end_turn
	 *            the can_end_turn to set
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
	 * @param phase_details
	 *            the phase_details to set
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
	 * @param update_message
	 *            the update_message to set
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
	 * @param domination
	 *            the domination to set
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
	 * @param can_get_card
	 *            the can_get_card to set
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
	 * @param cards
	 *            the cards to set
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
	 * @param traded_set
	 *            the traded_set to set
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
	 * @param can_show_card
	 *            the can_show_card to set
	 */
	public void setCanShowCard(boolean can_show_card) {
		this.can_show_card = can_show_card;
	}

	/**
	 * @return the has_player_won
	 */
	public boolean HasPlayerWon() {
		return has_player_won;
	}

	/**
	 * @param has_player_won
	 *            the has_player_won to set
	 */
	public void setpPlayerWon(boolean has_player_won) {
		this.has_player_won = has_player_won;
	}

	/**
	 * @return the is_bot
	 */
	public boolean isBot() {
		return is_bot;
	}

	/**
	 * @param is_bot
	 *            the is_bot to set
	 */
	public void setIsBot(boolean is_bot) {
		this.is_bot = is_bot;
	}

	/**
	 * @return the bot_type
	 */
	public String getBotType() {
		return type;
	}

	/**
	 * @param bot_type
	 *            the bot_type to set
	 */
	public void setBotType(String type) {
		this.type = type;
	}

	/**
	 * @return the strategy
	 */
	public PlayerStrategy getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy
	 *            the strategy to set
	 */
	public void setStrategy(PlayerStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the has_bot_won
	 */
	public boolean hasBotWon() {
		return has_bot_won;
	}

	/**
	 * @param has_bot_won
	 *            the has_bot_won to set
	 */
	public void setHasBotWon(boolean has_bot_won) {
		this.has_bot_won = has_bot_won;
	}

	/**
	 * @return the is_tournament_mode
	 */
	public boolean isTournamentMode() {
		return is_tournament_mode;
	}

	/**
	 * @param is_tournament_mode
	 *            the is_tournament_mode to set
	 */
	public void setTournamentMode(boolean is_tournament_mode) {
		this.is_tournament_mode = is_tournament_mode;
	}

	/**
	 * this method will subtract reinforce armies.
	 * 
	 * @param armies_selected
	 *            subtract this amount
	 */
	public void subReinforceArmies(int armies_selected) {
		reinforce_armies -= armies_selected;
	}

	/**
	 * this method will start the turn of player.
	 * 
	 * @param player_list
	 *            list of players
	 * @param map_elements
	 *            map elements
	 * @param game_view
	 *            GameScreen object
	 */
	public void startTurn(Player current_player, List<Player> player_list, MapElements map_elements,
			GameScreen game_view) {
		game_view.updateView("It is " + name + "'s turn.\n**************************");
		if (!is_bot&&!load_game) {
			setCanReinforce(true);
			setCanAttack(false);
			setCanFortify(false);
			setCanEndTurn(false);
			setCanGetCard(false);
			setCanShowCard(true);
			int armies = GamePlay.getReinforcementArmies(this, map_elements);
			this.setReinforceArmies(armies);

			// this.setPhaseDetails("Its " + name + "'s turn and Reinforcement
			// phase.");
			// this.setUpdateMessage("phase");
			// setChanged();
			// notifyObservers();
			// refactored
			updatePhaseDetails("Its " + name + "'s turn and Reinforcement phase.");
			// this.setUpdateMessage("domination");
			// setChanged();
			// notifyObservers();
			// refactored
			updateDominationDetails();
			this.setUpdateMessage("cards");
			setChanged();
			notifyObservers();
			game_view.updateStatus("");
			game_view.updateView(
					"\nREINFORCEMENT PHASE \n-----------------------------------------------------------------------");
			game_view.updateView(name+" gets "+armies+" to reinforce.");
			game_view.ReinforcementButton(armies, this, map_elements);
		} else if(is_bot&&turns>0){
			game_view.updateView(name+" is a BOT who is "+type);
			turnOfBot(current_player, map_elements, game_view);
		}else if(is_bot&&turns==0){
			setCanReinforce(false);
		}
		else if(!is_bot&&load_game){
			if(isCanReinforce()){
				updatePhaseDetails("Its " + name + "'s turn and Reinforcement phase.");
				updateDominationDetails();
				this.setUpdateMessage("cards");
				setChanged();
				notifyObservers();
				game_view.updateStatus("");
				game_view.updateView(
						"\nREINFORCEMENT PHASE \n-----------------------------------------------------------------------");
				load_game=false;
				game_view.ReinforcementButton(getReinforceArmies(), this, map_elements);
			}
			else if(isCanAttack()){
				setPhaseDetails("Its " + name + "'s turn and Attack phase.");
				setUpdateMessage("phase");
				setChanged();
				notifyObservers(this);
				game_view.updateStatus("Reinforcement Phase Completed");
				game_view.updateView(
						"\nATTACK PHASE \n-----------------------------------------------------------------------");
				load_game=false;
				game_view.AttackButton(this, map_elements);
				
			}
			else if(!isCanAttack()&&isCanFortify()){
				setPhaseDetails(name + " can Fortify now.");
				setUpdateMessage("phase");
				setChanged();
				notifyObservers(this);
				game_view.updateStatus("Attack Phase Completed");
				game_view.updateView(
						"\nFORTIFICATION PHASE \n-----------------------------------------------------------------------");
				load_game=false;
				game_view.FortifyButton(this, map_elements);
			}
			else if(!isCanAttack()&&isCanFortify()&&isCanEndTurn()){
				setPhaseDetails(name + " can End the turn now.");
				setUpdateMessage("phase");
				setChanged();
				notifyObservers(this);
				load_game=false;
				game_view.EndTurnButton();
			}
		}
	}

	/**
	 * this method will reinforcement on players.
	 * 
	 * @param armies_selected
	 *            selected armies
	 * @param map_elements
	 *            map elements
	 * @param country_name
	 *            name of country
	 * @param game_view
	 *            GameScreen object
	 */
	public void playerReinforces(int armies_selected, MapElements map_elements, String country_name,
			GameScreen game_view) {

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
			subReinforceArmies(armies_selected);
			reinforce_successful = true;
			updateDominationDetails();
			game_view.updateView(name + " reinforces " + selected_country.getCountryName() + " with " + armies_selected
					+ " armies.");
		} else {
			game_view.updateStatus("Not Enough Armies");
		}

		if (this.getReinforceArmies() > 0 && reinforce_successful) {
			game_view.ReinforcementButton(this.getReinforceArmies(), this, map_elements);
		} else {
			setCanReinforce(false);
			setCanShowCard(false);
			setCanAttack(true);
			setCanFortify(true);
			setCanEndTurn(true);
			setPhaseDetails("Its " + name + "'s turn and Attack phase.");
			setUpdateMessage("phase");
			setChanged();
			notifyObservers(this);
			game_view.updateStatus("Reinforcement Phase Completed");
			game_view.updateView(
					"\nATTACK PHASE \n-----------------------------------------------------------------------");
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
	public void playerAttacks(MapElements map_elements, Country country_from, Country country_to, GameScreen game_view,
			String mode_string, Deck deck) {
		int mode;
		if (mode_string.equalsIgnoreCase("ALL OUT ATTACK")) {
			mode = 1;
		} else {
			mode = 0;
		}
		if (can_attack) {
			if (!country_to.belongs_to_player.equals(this)) {
				if (country_from.getCurrentArmiesDeployed() > 1) {

					game_view.updateView(name+" attacks "+country_to.getCountryName());
					attacker_losses = 0;
					defender_losses = 0;

					if (mode == 1) {
						attacker_dice = getMaxDiceAttacker(country_from);
						defender_dice = getMaxDiceDefender(country_to);
					}

					if (mode != 1 && country_to.getBelongsToPlayer().isBot()) {
						attacker_dice = showAttackDiceDialog(country_from);
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
					game_view.updateView(country_from.getCountryName() + " lost " + attacker_losses + " armies. "+country_from.getCurrentArmiesDeployed()+" armies remains.");
					game_view.updateView(country_to.getCountryName() + " lost " + defender_losses + " armies."+country_to.getCurrentArmiesDeployed()+" armies remains.");

					if (country_to.getCurrentArmiesDeployed() < 1) {
						game_view.updateView(country_from.getBelongsToPlayer().getName() + " has defeated all of "
								+ country_to.getBelongsToPlayer().getName() + "'s armies in "
								+ country_to.getCountryName() + " and has occupied the country!");
						defenderLostCountry(country_from, country_to, game_view);
						game_view.AttackButton(this, map_elements);
					}
					if (getAssignedCountries().size() == game_view.map_elements.getCountries().size()) {
						setCanAttack(false);
						setCanReinforce(false);
						setCanFortify(false);
						setCanEndTurn(false);
						game_view.updateView("" + this.getName() + " has won the game ! Congratulations ! ");
						// game_view.EndTurnButton();
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

			setPhaseDetails(name + " can Fortify now.");
			setUpdateMessage("phase");
			setChanged();
			notifyObservers(this);
			game_view.updateStatus("Attack Phase Completed");
			game_view.updateView(
					"\nFORTIFICATION PHASE \n-----------------------------------------------------------------------");
			game_view.FortifyButton(this, map_elements);
			;
		}
	}

	/**
	 * this method will perform fortification on players.
	 * 
	 * @param armies
	 *            number of armies
	 * @param map_elements
	 *            map elements
	 * @param country_from
	 *            from country to attack
	 * @param country_to
	 *            to country to attack
	 * @param game_view
	 *            GameScreen object
	 */
	public void playerFortifies(int armies, MapElements map_elements, Country country_from, Country country_to,
			GameScreen game_view) {
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
			setCanAttack(false);
			setCanFortify(false);
			setPhaseDetails(name + " can End the turn now.");
			setUpdateMessage("phase");
			setChanged();
			notifyObservers(this);
			game_view.updateView("\n" + name + " fortified " + country_to.getCountryName() + " with " + armies
					+ " armies from " + country_from.getCountryName());
			game_view.updateStatus("Fortification Phase Completed");
			game_view.EndTurnButton();
		} else {
			game_view.updateStatus("Fortify Unsuccessful. Enter valid armies or Select Valid Country to Fortify.");
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
	 * @param c1
	 *            Country object 1
	 * @param c2
	 *            Country object 2
	 * @param unwanted
	 *            list of countries
	 * @param map_elements
	 *            map elements
	 * @return boolean boolean value for valid or not
	 */
	public boolean isFortifyValid(Country c1, Country c2, List<Country> unwanted, MapElements map_elements) {
		c1 = map_elements.getCountry(c1.getCountryName());

		if (c1.getNeighbourNodes().contains(c2) && c2.getNeighbourNodes().contains(c1)
				&& c1.getBelongsToPlayer().equals(c2.getBelongsToPlayer())) {
			return true;
		}

		if (unwanted.contains(c1)) {
			return false;
		}

		unwanted.add(c1);

		for (Country c : c1.getNeighbourNodes()) {
			if (!unwanted.contains(c) && isFortifyValid(c, c2, unwanted, map_elements)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * this method will be called if defender lost country.
	 * 
	 * @param country_from
	 *            from country
	 * @param country_to
	 *            to country
	 * @param game_view
	 *            GameScreen object
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
		if (!isCanGetCard()) {
			can_get_card = true;
		}
		this.setUpdateMessage("domination");
		setChanged();
		notifyObservers();
	}

	/**
	 * this method will eliminate player.
	 * 
	 * @param country_from
	 *            from country
	 * @param country_to
	 *            to country
	 * @param game_view
	 *            GameScreen object
	 */
	public void playerEliminated(Country country_from, Country country_to, GameScreen game_view) {
		game_view.updateView(country_to.getBelongsToPlayer().getName()
				+ " has no countries left, player looses the game and is eliminated");
		game_view.player_list.remove(country_to.getBelongsToPlayer());
	}

	/**
	 * this method will check can continue or not.
	 * 
	 * @param game_view
	 *            GameScreen object
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
			setPhaseDetails(name + " can End the turn now.");
			setUpdateMessage("phase");
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
	 * This method will check if attacker can attack to selected defender's
	 * country
	 * 
	 * @param currentPlayer
	 *            attacker
	 * @param countryA
	 *            attacker's country
	 * @param countryB
	 *            defender's country
	 * @return true if attacker can attack else false
	 */
	protected boolean isAttackValid(Player current_player, Country countryA, Country countryB, GameScreen game_view) {
		if (countryA.getCurrentArmiesDeployed() > 1 && !current_player.hasBotWon()) {
			// Check if at-least 2 armies are there on the attacking country.
			if (!current_player.getName().equals(countryB.getBelongsToPlayer().getName())
					&& current_player.getName().equals(countryA.getBelongsToPlayer().getName())) {
				// Check if another country is occupied by an opponent and not
				// by the currentPlayer.

			} else {
				if (!current_player.is_tournament_mode) {
					game_view.updateView("You cannot attack your own country.");
				}
			}
		} else {
			if (!has_bot_won) {
				game_view.updateView("You must have more than 1 army on " + countryA.getCountryName()
						+ " if you wish to attack from it.");
			}

		}
		return false;
	}

	/**
	 * this method will show attack dice dialog.
	 * 
	 * @param country
	 *            Country class object
	 * @return integer number of dices which attacker select to throw
	 */
	public int showAttackDiceDialog(Country country) {

		int dices = getMaxDiceAttacker(country);

		Integer[] choices = new Integer[dices];
		for (int i = 0; i < dices; i++) {
			choices[i] = i + 1;
		}

		return (Integer) JOptionPane.showInputDialog(null, "Attacker Select",
				country.getBelongsToPlayer().getName() + "! How many dice will you roll?", JOptionPane.OK_OPTION,
				BasicIconFactory.getMenuArrowIcon(), choices, choices[0]);
	}

	/**
	 * this method will get maximum dice that attacker have.
	 * 
	 * @param country
	 *            Country class object
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
	 * @param country
	 *            Country class object
	 * @return integer number of dices which attacker select to throw
	 */
	public int showDefenceDiceDialog(Country country) {

		int dices = getMaxDiceDefender(country);

		Integer[] choices = new Integer[dices];
		for (int i = 0; i < dices; i++) {
			choices[i] = i + 1;
		}

		return (Integer) JOptionPane.showInputDialog(null, "Defender Select",
				country.getBelongsToPlayer().getName() + "! How many dice will you roll?", JOptionPane.OK_OPTION,
				BasicIconFactory.getMenuArrowIcon(), choices, choices[0]);
	}

	/**
	 * this method will get maximum dice that defender have.
	 * 
	 * @param country
	 *            Country class object
	 * @return maximum dices defender have
	 */
	public int getMaxDiceDefender(Country country) {
		int dices = country.getCurrentArmiesDeployed() >= 2 ? 2 : 1;

		return dices;
	}

	/**
	 * this method will show move armies dialogbox.
	 * 
	 * @param country
	 *            Country class object
	 * @param game_view
	 *            GameScreen object
	 * @return integer number of armies
	 */
	public int showMoveArmiesDialogBox(Country country, GameScreen game_view) {
		Integer[] choices = new Integer[country.getCurrentArmiesDeployed() - 1];
		for (int i = attacker_dice; i < choices.length; i++) {
			choices[i] = i + 1;
		}

		return (Integer) JOptionPane.showInputDialog(null, "Select Armies",
				country.getBelongsToPlayer().getName() + "! How many armies will you move?", JOptionPane.OK_OPTION,
				BasicIconFactory.getMenuArrowIcon(), choices, choices[0]);

	}

	public int getTotalArmies() {
		int total_armies = 0;
		for (Country c : getAssignedCountries()) {
			total_armies += c.getCurrentArmiesDeployed();
		}
		return total_armies;
	}

	public void updatePhaseDetails(String messageToUpdate) {
		setPhaseDetails(messageToUpdate);
		setUpdateMessage("phase");
		setChanged();
		notifyObservers(this);
	}

	public void updateDominationDetails() {
		setUpdateMessage("domination");
		setChanged();
		notifyObservers(this);
	}

	private void turnOfBot(Player current_player, MapElements map_elements, GameScreen game_view) {
		setCanReinforce(true);
		switch (getBotType()) {
		case "aggressive":
			aggressiveBotTurn(current_player, map_elements, game_view);
			break;
		case "benevolent":
			benevolentBotTurn(current_player, map_elements, game_view);
			break;
		case "random":
			randomBotTurn(current_player, map_elements, game_view);
			break;
		case "cheater":
			cheaterBotTurn(current_player, map_elements, game_view);
			break;
		default:
			break;
		}
		if (!has_bot_won) {
			turns--;
			game_view.view_visibility = false;
			game_view.updateView("\n" + getName() + " ended the turn.\n**************************");
			List<Player> player_list = game_view.getPlayerList();
			int new_turn_value = GamePlay.endTurn(current_player, player_list);
			current_player = GamePlay.getCurrentPlayer(player_list, new_turn_value);
			current_player.addObserver(game_view.view);
			current_player.startTurn(current_player, player_list, map_elements, game_view.view);
		} else {
			setCanAttack(false);
			setCanReinforce(false);
			setCanFortify(false);
			setCanEndTurn(false);
			game_view.result=name+" "+type;
			game_view.updateView("" + getName() + " has won the game ! Congratulations ! ");
			game_view.updateStatus("" + getName() + " has won the game ! Congratulations ! ");
		}
	}

	public void executeAttack(String country1, String country2, GameScreen game_view, Player model,
			MapElements map_elements) {
		this.strategy.attack(country1, country2, game_view, model, map_elements);
	}

	/**
	 * This method will execute the reinforce method from the PlayerStrategy
	 * interface
	 * 
	 * @param country
	 *            name of the country where the armies need to reinforced
	 * @param game_view
	 *            object of GameView class
	 * @param model
	 *            object of Player class
	 */
	public void executeReinforce(String country, GameScreen game_view, Player model, MapElements map_elements) {
		setReinforceArmies(GamePlay.getReinforcementArmies(this, map_elements));
		this.strategy.reinforce(country, game_view, model, map_elements);
	}

	/**
	 * This method will execute the fortification method from the PlayerStrategy
	 * interface
	 * 
	 * @param country1
	 *            name of the attacker's country
	 * @param country2
	 *            name of the defender's country
	 * @param game_view
	 *            object of GameView class
	 * @param model
	 *            object of Player class
	 */
	public void executeFortification(String country1, String country2, GameScreen game_view, Player model,
			MapElements map_elements) {
		this.strategy.fortify(country1, country2, game_view, model, map_elements);
	}

	/**
	 * Sets the strategy to cheater bot and update its logger
	 */
	private void cheaterBotTurn(Player current_player, MapElements map_elements, GameScreen game_view) {
		this.setStrategy(new CheaterBot());

		if (getAssignedCountries() != null) {

			// reinforce for bot
			game_view.updateView("\nREINFORCEMENT PHASE \n-----------------------------------------------------------------------");
			List<Country> cheaterCountries = new ArrayList<>();
			for (Country country : getAssignedCountries()) {
				executeReinforce(country.getCountryName(), game_view, this, map_elements);
				cheaterCountries.add(country);
			}


			// attack phase for bot
			game_view.updateView("\nATTACK PHASE \n-----------------------------------------------------------------------");
			attackPhase: for (Country attackingCountry : cheaterCountries) {
				game_view.updateView("Cheater is attacking with " + attackingCountry.getCountryName() + " country...");
				List<Country> neighbors = attackingCountry.getNeighbourNodes();
				for (Country neighbor : neighbors) {
					Country defenderCountry = map_elements.getCountry(neighbor.getCountryName());
					if (isAttackValidForCheater(current_player, attackingCountry, defenderCountry)) {
						executeAttack(attackingCountry.getCountryName(), neighbor.getCountryName(), game_view, this,
								map_elements);
						if (has_bot_won) {
							break attackPhase;
						}
						break;
					}
				}
			}

			// fortification phase for bot
			game_view.updateView("\nFORTIFICATION PHASE \n-----------------------------------------------------------------------");
			List<Country> priorityCountries = new ArrayList<>();
			List<Country> eligibleFortificationCountry = getAssignedCountries();
			for (Country country : eligibleFortificationCountry) {
				List<Country> neighbors = country.getNeighbourNodes();
				for (Country neighbor : neighbors) {
					Country c = map_elements.getCountry(neighbor.getCountryName());
					if (c != null && !c.getBelongsToPlayer().getName().equalsIgnoreCase(getName())) {
						priorityCountries.add(country);
						break;
					}
				}
			}
			if (priorityCountries.size() > 0) {
				for (Country country : priorityCountries) {
					executeFortification(country.getCountryName(), null, game_view, this, map_elements);
				}
			}
		}
	}

	/**
	 * This method will check if attacker can attack to selected defender's
	 * country
	 * 
	 * @param currentPlayer
	 *            attacker
	 * @param countryA
	 *            attacker's country
	 * @param countryB
	 *            defender's country
	 * @return true if attacker can attack else false
	 */
	protected boolean isAttackValidForCheater(Player currentPlayer, Country countryA, Country countryB) {
		if (countryA.getCurrentArmiesDeployed() > 1) {
			// Check if at-least 2 armies are there on the attacking country.
			if (!currentPlayer.getName().equals(countryB.getBelongsToPlayer().getName())
					&& currentPlayer.getName().equals(countryA.getBelongsToPlayer().getName())) {
				// Check if another country is occupied by an opponent and not
				// by the currentPlayer.
				if (countryA.getNeighbourNodes().contains(countryB)
						&& countryB.getNeighbourNodes().contains(countryA)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Sets the strategy to random bot and update its logger
	 */
	private void randomBotTurn(Player current_player, MapElements map_elements, GameScreen game_view) {
		this.setStrategy(new RandomBot());
		Random rng = new Random();
		Country randomCountry = getAssignedCountries().get(rng.nextInt(getAssignedCountries().size()));

		if (randomCountry != null) {

			// reinforce phase for bot
			game_view.updateView("\nREINFORCEMENT PHASE \n-----------------------------------------------------------------------");
			executeReinforce(randomCountry.getCountryName(), game_view, this, map_elements);

			// attack phase for bot
			game_view.updateView("\nATTACK PHASE \n-----------------------------------------------------------------------");
			List<Country> neighbors = randomCountry.getNeighbourNodes();
			for (Country neighbor : neighbors) {
				Country defenderCountry = map_elements.getCountry(neighbor.getCountryName());
				if (isAttackValid(current_player, randomCountry, defenderCountry, game_view)) {
					executeAttack(randomCountry.getCountryName(), neighbor.getCountryName(), game_view, this,
							map_elements);
					break;
				}
			}

			// fortification phase for bot
			// AI fortify
			game_view.updateView("\nFORTIFICATION PHASE \n-----------------------------------------------------------------------");
			List<Country> priorityTargets = new ArrayList<>();

			for (Country country : getAssignedCountries()) {
				for (Country neighbor : country.getNeighbourNodes()) {
					// Checks if country has both adjacent friendly and enemy
					// countries
					Country n = map_elements.getCountry(neighbor.getCountryName());
					if (n != null) {
						if (n.getBelongsToPlayer().getName().equals(getName())) {
							priorityTargets.add(country);
						}
					}
				}
			}

			if (priorityTargets.size() > 0) {
				List<Country> priorityCountries = new ArrayList<Country>();
				int r1 = rng.nextInt(priorityTargets.size());

				for (int i = 0; i < priorityTargets.get(r1).getNeighbourNodes().size(); i++) {
					Country c = map_elements
							.getCountry(priorityTargets.get(r1).getNeighbourNodes().get(i).getCountryName());

					if (c != null && c.getBelongsToPlayer().equals(this) && c.getCurrentArmiesDeployed() > 1) {
						priorityCountries.add(c);
					}
				}
				if (priorityCountries.size() > 0) {
					int r2 = rng.nextInt(priorityCountries.size());
					game_view.updateView(" Fortifying...");
					executeFortification(priorityCountries.get(r2).getCountryName(),
							priorityTargets.get(r1).getCountryName(), game_view, this, map_elements);
					game_view.updateView("Successful fortify");
				} else {
					game_view.updateView(priorityTargets.get(r1).getCountryName()
							+ " does not have any valid neighbours from which it can fortify ! ");
				}
			} else {
				game_view.updateView("The player doesn't have any valid countries to fortify !");
			}
		}

	}

	/**
	 * Sets the strategy to benevolent bot and update its logger
	 */
	private void benevolentBotTurn(Player current_player, MapElements map_elements, GameScreen game_view) {
		this.setStrategy(new BenevolentBot());
		Random rng = new Random();
		Country weakest_country = getWeakestCountry();

		// reinforce phase for bot
		game_view.updateView("\nREINFORCEMENT PHASE \n-----------------------------------------------------------------------");
		if (weakest_country != null) {
			executeReinforce(weakest_country.getCountryName(), game_view, this, map_elements);

			// attack phase for bot
			game_view.updateView("\nATTACK PHASE \n-----------------------------------------------------------------------");
			executeAttack(null, null, game_view, this, map_elements);

			// fortification phase for bot
			// AI fortify
			game_view.updateView("\nFORTIFICATION PHASE \n-----------------------------------------------------------------------");
			List<Country> priorityTargets = new ArrayList<>();

			for (Country country : getAssignedCountries()) {
				for (Country neighbor : country.getNeighbourNodes()) {
					// Checks if country has both adjacent friendly and enemy
					// countries
					Country n = map_elements.getCountry(neighbor.getCountryName());
					if (n != null) {
						if (n.getBelongsToPlayer().getName().equals(getName())) {
							priorityTargets.add(country);
						}
					}
				}

			}

			if (priorityTargets.size() > 0) {
				List<Country> priorityCountries = new ArrayList<Country>();
				int r1 = rng.nextInt(priorityTargets.size());

				for (int i = 0; i < priorityTargets.get(r1).getNeighbourNodes().size(); i++) {
					Country c = map_elements
							.getCountry(priorityTargets.get(r1).getNeighbourNodes().get(i).getCountryName());

					if (c != null && c.getBelongsToPlayer().equals(this) && c.getCurrentArmiesDeployed() > 1) {
						priorityCountries.add(c);
					}
				}
				if (priorityCountries.size() > 0) {
					int r2 = rng.nextInt(priorityCountries.size());
					game_view.updateView(" Fortifying...");
					executeFortification(priorityCountries.get(r2).getCountryName(),
							priorityTargets.get(r1).getCountryName(), game_view, this, map_elements);
					game_view.updateView("Successful fortify");
				} else {
					game_view.updateView(priorityTargets.get(r1).getCountryName()
							+ " does not have any valid neighbours from which it can fortify ! ");
				}
			} else {
				game_view.updateView("The player doesn't have any valid countries to fortify !");
			}
		}
	}

	/**
	 * Sets the strategy to aggressive bot and update its logger
	 */
	private void aggressiveBotTurn(Player current_player, MapElements map_elements, GameScreen game_view) {
		this.setStrategy(new AggressiveBot());
		Random rng = new Random();
		Country strongest_country = getStrongestCountry();

		if (strongest_country != null) {

			// reinforce phase for bot
			game_view.updateView("\nREINFORCEMENT PHASE \n-----------------------------------------------------------------------");
			executeReinforce(strongest_country.getCountryName(), game_view, this, map_elements);

			// attack phase for bot
			game_view.updateView("\nATTACK PHASE \n-----------------------------------------------------------------------");
			List<Country> neighbors = strongest_country.getNeighbourNodes();
			for (Country neighbor : neighbors) {
				Country defender_country = map_elements.getCountry(neighbor.getCountryName());
				if (!defender_country.belongs_to_player.equals(this)
						&& strongest_country.getCurrentArmiesDeployed() > 1) {
					executeAttack(strongest_country.getCountryName(), neighbor.getCountryName(), game_view, this,map_elements);
					break;
				}
			}

			// fortification phase for bot
			// AI fortify
			game_view.updateView("\nFORTIFICATION PHASE \n-----------------------------------------------------------------------");
			List<Country> priorityTargets = new ArrayList<>();

			for (Country country : getAssignedCountries()) {

				for (Country neighbor : country.getNeighbourNodes()) {
					// Checks if country has both adjacent friendly and enemy
					// countries
					Country n = map_elements.getCountry(neighbor.getCountryName());
					if (n != null) {
						if (n.getBelongsToPlayer().getName().equals(getName())) {
							priorityTargets.add(country);
						}
					}
				}
			}

			if (priorityTargets.size() > 0) {
				List<Country> priorityCountries = new ArrayList<Country>();
				int r1 = rng.nextInt(priorityTargets.size());

				for (int i = 0; i < priorityTargets.get(r1).getNeighbourNodes().size(); i++) {
					Country c = map_elements
							.getCountry(priorityTargets.get(r1).getNeighbourNodes().get(i).getCountryName());

					if (c != null && c.getBelongsToPlayer().equals(this) && c.getCurrentArmiesDeployed() > 1) {
						priorityCountries.add(c);
					}
				}
				if (priorityCountries.size() > 0) {
					int r2 = rng.nextInt(priorityCountries.size());
					game_view.updateView("Fortifying...");
					executeFortification(priorityCountries.get(r2).getCountryName(),
							priorityTargets.get(r1).getCountryName(), game_view, this, map_elements);
					game_view.updateView("Successful fortify");
				} else {
					game_view.updateView(priorityTargets.get(r1).getCountryName()
							+ " does not have any valid neighbours from which it can fortify ! ");
				}
			} else {
				game_view.updateView("The player doesn't have any valid countries to fortify !");
			}
		}
	}

	/**
	 * This method will return strongest country of player
	 * 
	 * @param player
	 *            player object
	 * @return strongest country
	 */
	public Country getStrongestCountry() {
		List<Country> player_countries = getAssignedCountries();
		Country strongest_country = null;
		int max_army = 1;
		if (player_countries.size() == 1) {
			return player_countries.get(0);
		}
		for (Country country : player_countries) {
			int army = country.getCurrentArmiesDeployed();
			if (army > max_army) {
				max_army = army;
				strongest_country = country;
			}
		}
		if (strongest_country == null) {
			strongest_country = getAssignedCountries().get(0);
		}
		return strongest_country;
	}

	/**
	 * This method will return weakest country of player
	 * 
	 * @param player
	 *            player object
	 * @return strongest country
	 */
	public Country getWeakestCountry() {
		List<Country> player_countries = getAssignedCountries();
		Country weakest_country = null;
		int min_army = 200;
		for (Country country : player_countries) {
			int army = country.getCurrentArmiesDeployed();
			if (army < min_army) {
				min_army = army;
				weakest_country = country;
			}
		}
		if (weakest_country == null) {
			weakest_country = getAssignedCountries().get(0);
		}
		return weakest_country;
	}

}