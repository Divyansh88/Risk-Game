package app.team21.risk.mapmodule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Date Created: 28-09-2018, Friday
 * Last Updated on: 29-09-2018, Saturday
 * This class file handles .txt map file and creates a GUI of connected graph.
 * @author Yash Sheth
 * @author Samip Thakkar
 * @version 1
 *
 */

public class MapLoader {

	static HashMap<String, ArrayList> map_details;
	/**
	 * Main method. 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		readMap();
	}
		
	/**
	 * This method reads text file of map and converts it to Java output
	 * @throws IOException
	 */
	public static void readMap()throws IOException{
		
		String FilePath = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/India.map";
		HashMap<String, String> map = new HashMap<String, String>();
		map_details = new HashMap<String, ArrayList>();
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(FilePath));
		while ((line = reader.readLine()) != null){
			String[] MapDetails=line.split("=",2);
			if (MapDetails.length >= 2){
				String key = MapDetails[0];
				String value = MapDetails[1];
				map.put(key, value);
				if(key.length()==0 || value.length()==0){
					System.out.println("Invalid format");
					System.exit(0);
					}
				else{
				System.out.println(key +" = " + map.get(key));
				}
				
			}
			else{
				String[] headers=line.split("]",2);
				if (headers.length >= 2){
					String headerkey = headers[0];
					if(headerkey.length()==0){
						System.out.println("Invalid format");
					}
					else{
						System.out.println(headerkey+"]");
					}
				}
				else{
					String [] CountryDetails = line.split(",");
					if(CountryDetails.length>=2){
						ArrayList country_details = new ArrayList();
						String Country = CountryDetails[0];
						String X = CountryDetails[1];
						String Y = CountryDetails[2];
						String Continent = CountryDetails[3];
						System.out.println("Country: "+Country);
						System.out.println("X-Coordinate: "+X);
						System.out.println("Y-Coordinate: "+Y);
						System.out.println("Continent: "+Continent);
						for(int j=4,i=1;j<CountryDetails.length;j++){
							String Neighbours = CountryDetails[j];
							System.out.println("Neighbour "+i+":"+ Neighbours);
							i++;
						}
						map_details.put(Country, country_details);
					}
					else{
						String BlankLine = line;
						if(BlankLine.length()==0){
							System.out.println(BlankLine);
						}
						else{
							System.out.println("Invalid format");
							System.exit(0);
						}
						
					}
					
				}
			}
			
		}
		reader.close();
	}
}
