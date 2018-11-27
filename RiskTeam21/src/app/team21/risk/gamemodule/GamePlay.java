package app.team21.risk.gamemodule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.views.GameScreen;


/**
 * Last Updated on : 08/11/2018, Thursday 
 * GamePlay class that contains all the game play logic.
 * 
 * @author Yash Sheth and Samip Thakkar
 * @version 2.0.0
 */
public class GamePlay extends Observable implements Serializable{
	
	static Player current_player;
	static int player_count;
	static int save_file_counter=1;
	
	static StringBuilder tb = new StringBuilder();
	
	
	/**
	 * this method will distribute countries to players.
	 * 
	 * @param players list of players
	 * @param countries list of countries
	 * @return string value
	 */
	public static String distributeCountries(List<Player> players,List<Country> countries){
		tb.append("-----USER STARTS PUTTING ARMIES-----\n\n");
		player_count=players.size();
		Collections.shuffle(countries);
		StringBuilder sb=new StringBuilder();
		
		int turn_value=0;
		for(Player p:players){
			turn_value++;
			p.setTurnValue(turn_value);
		}
		
		while(countries.size()>0){
			for(Player p:players){
				List<Country> new_list=p.getAssignedCountries();
				if(countries.size()>0){					
					Country c=countries.get(0);
					c.setBelongsToPlayer(p);
					c.addArmy(1);
					sb.append(p.getName()+" gets "+c.getCountryName()+".\n");
					tb.append(p.getName()+" puts 1 army on "+c.getCountryName()+".\n");
					new_list.add(c);
					countries.remove(0);
					p.setAssignedCountries(new_list);
				}
				else{
					break;
				}
			}
		}
		sb.append("-----COUNTRIES ASSIGNED-----\n\n");
		return sb.toString();
	}
	
	/**
	 * this method will get current player.
	 * 
	 * @param player_list list of players 
	 * @param turn_value turn value
	 * @return current player
	 */
	public static Player getCurrentPlayer(List<Player> player_list,int turn_value){
		for(Player p:player_list){
			if(turn_value==p.getTurnValue()){
				current_player=p;
				break;
			}
		}
		return current_player;
	}
	
	/**
	 * this method will set end turn.
	 * 
	 * @param player  current player
	 * @param player_list list of players
	 * @return new turn
	 */
	public static int endTurn(Player player,List<Player> player_list){
		int new_turn=player.getTurnValue()+1;
		int max_turn_value=0;
		for(Player p:player_list){
			if(p.getTurnValue()>max_turn_value)
				max_turn_value=p.getTurnValue();
		}
		if(new_turn>max_turn_value){
			new_turn=1;
		}
		boolean player_flag=true;
		while(player_flag){
			for(Player p:player_list){
				if(p.getTurnValue()==new_turn){
					player_flag=false;
					break;
				}
			}
			if(player_flag){
				new_turn+=1;
			}
			if(new_turn>max_turn_value){
				new_turn=1;
			}
		}
		return new_turn;
	}
	
	/**
	 * this method will set initial armies.
	 * 
	 * @param player_list list of player
	 */
	public static void setInitialArmies(List<Player> player_list){
		int armies = 0;
		int num_of_players = player_list.size();
		
		switch (num_of_players){
		case 2:
			armies=40;
			break;
		case 3:
			armies=35;
			break;
		case 4:
			armies=30;
			break;
		case 5:
			armies=25;
			break;
		case 6:
			armies=20;
			break;
		}
		
		for(Player p:player_list){
			p.setInitialArmies(armies);
		}
		
		for(Player p:player_list){
			int deployed_armies=armies-p.getAssignedCountries().size();
			p.setInitialArmies(deployed_armies);			
		}
	}
	
    /**
     * this method will add initial armies to the country of the player in round robin fashion.
     * 
     * @param player_list list of players
     * @return string value
     */
    public static String placeInitialArmiesInRR(List<Player> player_list) {
        int j = 0;
        int players_left_for_assign = player_list.size();
        while (players_left_for_assign > 0){
            if (player_list.get(j % player_count).getInitialArmies() > 0) {
                List<Country> player_countryList = player_list.get(j % player_count).getAssignedCountries();
                Country randomCountry = player_countryList.get(new Random().nextInt(player_countryList.size()));
                randomCountry.addArmy(1);
                player_list.get(j % player_count).setInitialArmies(player_list.get(j % player_count).getInitialArmies()- 1);
                tb.append(player_list.get(j % player_count).getName() + " put 1 army on "+ randomCountry.getCountryName()+".\n");
            } 
            else{
                players_left_for_assign--;
            }
            j++;
        }
        tb.append("\n\n==Allocating armies as well as country is done===");
        return tb.toString();
    }

	/**
	 * this method will update map representation.
	 * 
	 * @param map_elements map elements
	 * @return string value
	 */
	public static String updateMR(MapElements map_elements){
		StringBuilder sb=new StringBuilder();
		sb.append("======|CAPTURE THEM ALL|======\n");
		for (Continent c : map_elements.getContinentList()) {
			sb.append("\n\n" + c.getContinentName() + "  " + c.getControlValue() + "\n");
			for (Country c1 : c.getMemberCountriesList()){			
				sb.append("\n"+c1.getCountryName() + " - " +c1.getCurrentArmiesDeployed()+ " - " + c1.getBelongsToPlayer().getName());
			}		
		}
		return	sb.toString();
	}
	
	
	/**
	 * this method used to get reinforcement armies.
	 * 
	 * @param player player
	 * @param map map 
	 * @return integer armies
	 */
	public static int getReinforcementArmies(Player player,MapElements map){
		int armies=0;
		int player_countries=player.getAssignedCountries().size();
		if(player_countries>9){
			armies=player_countries/3;
		}
		else{
			armies=3;
		}
		//check if user holds any continents
		for(Continent c:map.getContinentList()){	
			List<Country> co=c.getMemberCountriesList();
			boolean continent_control=true;
			for(Country check:co){
				if(check.getBelongsToPlayer()==player){
					continue;
				}
				else{
					continent_control=false;
					break;
				}
			}
			if(continent_control){
				armies+=c.getControlValue();
			}
		}
		return armies;
	}	
	
	/**
     * This method will save the game .
     * @param gameMap MapElements object at the point of saving the game
     * @return true if function able to save the game else false
     */
    public static boolean saveGame(MapElements gameMap,GameScreen game_view) {
    	
    	FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today;
        String fileName="";
		try {
			today = formatter.parse(formatter.format(new Date()));
			fileName=today.toString().replaceAll("00:00:00"," ").replaceAll("\\s+","").concat("_"+String.valueOf(save_file_counter++));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
       
		try {
            fout = new FileOutputStream(fileName+".ser");
			oos = new ObjectOutputStream(fout);
			game_view.updateStatus("Saved Game to "+fileName+".ser file");
			oos.writeObject(gameMap);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
     return true;
    }
    /**
     * This method will load the saved game
     * @param fileName fileName that user gave while saving the map 
     * @return true if function able to save the game else false
     */
    public static MapElements loadGame(String fileName) {
    	FileInputStream fin = null;
		ObjectInputStream ois = null;
        MapElements gameMap = null;
		try {
			fin = new FileInputStream(fileName);
			ois = new ObjectInputStream(fin);
			gameMap = (MapElements) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
           if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return gameMap;

    }

}