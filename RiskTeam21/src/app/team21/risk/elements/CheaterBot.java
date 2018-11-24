package app.team21.risk.elements;


import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.GameScreen;
/**
 * This class implements the strategy for Cheater bot
 * @author prashantp95
 *
 */
public class CheaterBot implements PlayerStrategy {

    public Country countryA;
    public Country countryB;
    
    /**
     * Overrides the attack phase for Cheater bot
     * from the PlayerStrategy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void attack(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country1);
        countryB = map_elements.getCountry(country2);
        current_player.updatePhaseDetails("Repaint");
        current_player.updatePhaseDetails("==Attack Phase==");
        game_view.updateView("Cheater is attacking : "+country2);
        game_view.updateView(countryA.getBelongsToPlayer().getName() + " has defeated all of " + countryB.getBelongsToPlayer().getName() + "'s armies in " + country2 + " and has occupied the country!");
        defendingPlayerLostCountry(countryA, countryB, current_player,game_view);

        //If player conquered all the country and have won the game
        if (current_player.getAssignedCountries().size() == map_elements.getCountries().size()) {
            current_player.setHasBotWon(true);
            game_view.updateView("" + current_player.getName() + " has won the game ! Congratulations ! ");
            current_player.updatePhaseDetails(current_player.getName() + "Won");
        }
    }
    /**
     * Overrides the fortify phase for Cheater bot
     * from the PlayerStrategy interface
     * @param country1 name of the attacker's country
     * @param country2 name of the defender's country
     * @param game_view object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void fortify(String country1, String country2, GameScreen game_view, Player current_player,MapElements map_elements) {
        countryA = map_elements.getCountry(country1);
        if (countryA != null){
            int armies = countryA.getCurrentArmiesDeployed();
            countryA.addArmy(armies);
            game_view.updateView("Cheater has doubled it's armies on " + country1);
        }
    }
    /**
     * Overrides the reinforce phase for Cheater bot
     * from the PlayerStrategy interface
     * @param country name of the attacker's country
     * @param game_view object of GameView class
     * @param current_player object of Player class
     * 
     */
    @Override
    public void reinforce(String country, GameScreen game_view, Player current_player,MapElements map_elements) throws NullPointerException {
        countryA = map_elements.getCountry(country);
        if (countryA !=null){
            int armies = countryA.getCurrentArmiesDeployed();
            countryA.addArmy(armies);
            game_view.updateView("Cheater has doubled it's armies on " + country);
        }
    }
    /**
     * Checks if the defending player has lost countries.
     * @param countryA object of Country class
     * @param countryB object of Country class
     * @param current_player object of Player class
     * 
     */
    private void defendingPlayerLostCountry(Country countryA, Country countryB, Player current_player,GameScreen game_view) {

        // Remove country from defender's list of occupied territories and adds to attacker's list
        countryB.getBelongsToPlayer().assigned_countries.remove(countryB);
        countryA.getBelongsToPlayer().assigned_countries.add(countryB);

        // Check if defender is eliminated from game
        if (countryB.getBelongsToPlayer().getAssignedCountries().size() == 0) {
            current_player.playerEliminated(countryA, countryB, game_view);
        }
        // Set country player to attacker
        countryB.setBelongsToPlayer(countryA.getBelongsToPlayer());
        current_player.updatePhaseDetails("\n"+countryB.getCountryName()+" has been captured ! ");

        //The attacking player must then place a number of armies
        //in the conquered country which is greater or equal than the number of dice that was used in the attack that
        //resulted in conquering the country
        int moveArmies = 1;

        countryA.subtractArmy(moveArmies);
        countryB.addArmy(moveArmies);
        current_player.setCanGetCard(true);
        //current_player.addObserver(new PlayerView());
        current_player.updateDominationDetails();
    }

}
