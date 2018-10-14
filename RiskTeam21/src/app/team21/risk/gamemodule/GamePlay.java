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

		for(int i=1;i<6;i++){
			Player p=new Player("Player"+i);
			
			player_list.add(p);
		}
		for(int i=1;i<20;i++){
			Country c=new Country("Country"+1,123,123,"Mega");
			country_list.add(c);
		}
		
		distributeCountries(player_list,country_list);
		
	}
	
	public static void distributeCountries(List<Player> players,List<Country> countries){
		Collections.shuffle(countries);
		System.out.println("Inside if"+countries.size());

		while(countries.size()>0){
			for(Player p:players){
				List<Country> neww=p.getAssignedCountries();
				if(countries.size()>0){
					System.out.println("Inside if");
					Country c=countries.get(0);
					neww.add(c);
					countries.remove(0);
					p.setAssignedCountries(neww);
				}
				else
					break;
			}
		}
	}
}
