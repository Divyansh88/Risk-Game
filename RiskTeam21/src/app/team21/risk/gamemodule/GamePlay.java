/**
 * 
 */
package app.team21.risk.gamemodule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;

/**
 * @author Yash Sheth
 *
 */
public class GamePlay {
	
	static Player current_player;
	static int playerCount;
	static StringBuilder tb=new StringBuilder();
	
	
	public static String distributeCountries(List<Player> players,List<Country> countries){
		tb.append("-----USER STARTS PUTTING ARMIES-----\n\n");
		playerCount=players.size();
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
				else
					break;
			}
		}
		sb.append("-----COUNTRIES ASSIGNED-----\n\n");
		return sb.toString();

	}
	
	public Player getCurrentPlayer(List<Player> player_list,int turn_value){
		for(Player p:player_list){
			if(turn_value==p.getTurnValue()){
				current_player=p;
				break;
			}
		}
		return current_player;
	}
	
	public static int endTurn(Player player,List<Player> player_list){
		int new_turn=player.getTurnValue()+1;
		if(new_turn>player_list.size())
			new_turn=1;
		return new_turn;
	}
	
	public static void setInitialArmies(List<Player> player_list){
		int armies=0;
		int num_of_players=player_list.size();
		
		if(num_of_players==2)
			armies=40;
		else if(num_of_players==3)
			armies=35;
		else if(num_of_players==4)
			armies=30;
		else if(num_of_players==5)
			armies=25;
		else if(num_of_players==6)
			armies=20;
		
		for(Player p:player_list){
			p.setInitialArmies(armies);
			System.out.println(p.getName()+" has "+p.getInitialArmies());
		}
		
		for(Player p:player_list){
			int deployed_armies=armies-p.getAssignedCountries().size();
			p.setInitialArmies(deployed_armies);
			System.out.println(p.getName()+" has "+p.getInitialArmies());
		}
	}
	
	
	 /**
     * This method will add initial armies to the country of the player in round robin fashion
     */
    public String placeInitialArmiesInRR(List<Player> player_list) {
        int j = 0;
        int playersLeftForAssign = player_list.size();
        while (playersLeftForAssign > 0) {
        	
            if (player_list.get(j % playerCount).getInitialArmies() > 0) {
            	System.out.println(""+player_list.get(j % playerCount).getInitialArmies()+"  -" );
                List<Country> playerCountryList = player_list.get(j % playerCount).getAssignedCountries();
                Country randomCountry = playerCountryList.get(new Random().nextInt(playerCountryList.size()));
                System.out.println("-- "+randomCountry.getCountryName());
                randomCountry.addArmy(1);
                player_list.get(j % playerCount).setInitialArmies(player_list.get(j % playerCount).getInitialArmies()- 1);
                tb.append(player_list.get(j % playerCount).getName() + " put 1 army on "+ randomCountry.getCountryName()+".\n");
            } else {
                playersLeftForAssign--;
            }
            j++;
        }
        tb.append("\n\n==Allocating armies as well as country is done===");
        return tb.toString();
    }

	public static String updateMR(MapElements map_elements){
		StringBuilder sb=new StringBuilder();
		sb.append("======|CAPTURE THEM ALL|======\n");
		for (Continent c : map_elements.getContinentList()) {
			sb.append("\n\n" + c.getContinentName() + "  " + c.getControlValue() + "\n");
			for (Country c1 : c.getMemberCountriesList())
				sb.append("\n"+c1.getCountryName() + " - " +c1.getCurrentArmiesDeployed()+ " - " + c1.getBelongsToPlayer().getName());
		}
		return	sb.toString();
	}
	
	
	public static int getReinforcementArmies(Player player,MapElements map){
		int armies=0;
		int player_countries=player.getAssignedCountries().size();
		if(player_countries>9)
			armies=player_countries/3;
		else
			armies=3;
		
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
			if(continent_control)
				armies+=c.getControlValue();
		}
		return armies;
	}
	
	public static void tryFortify(Player player, Country country_from, Country country_to){
		
	}
}
