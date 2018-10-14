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

import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;

/**
 * @author Yash Sheth
 *
 */
public class GamePlay {

	
	
	public static void main(String[] args) {

		//Remove everything before getSequence once done.
		List<Player> player_list = new ArrayList<Player>();
		List<Country> country_list=new ArrayList<Country>();

		for(int i=1;i<4;i++){
			Player p=new Player("Player "+i);
			
			player_list.add(p);
		}
		for(int i=1;i<50;i++){
			Country c=new Country("Country "+i,123,123,"Mega");
			country_list.add(c);
		}
		
		distributeCountries(player_list,country_list);
		for(Player p:player_list){
			System.out.println(p.getName()+" gets "+getReinforcementArmies(p)+" armies.");
		}
			
		
	}
	
	public static void distributeCountries(List<Player> players,List<Country> countries){
		Collections.shuffle(countries);
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
					//print on HISTORY Screen
					System.out.println(p.getName()+" gets "+c.getCountryName());
					new_list.add(c);
					countries.remove(0);
					p.setAssignedCountries(new_list);
					
				}
				else
					break;
			}
		}

	}
	
	public static int endTurn(Player player,List<Player> player_list){
		int new_turn=player.getTurnValue()+1;
		if(new_turn>player_list.size())
			new_turn=1;
		return new_turn;
	}
	
	public static int getInitialArmies(List<Player> player_list){
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
		
		return armies;
	}
	
	public static int getReinforcementArmies(Player player){
		int armies=0;
		int player_countries=player.getAssignedCountries().size();
		if(player_countries>9)
			armies=player_countries/3;
		else
			armies=3;
		return armies;
	}
	
	public static void tryFortify(Player player, Country country_from, Country country_to){
		
	}
}
