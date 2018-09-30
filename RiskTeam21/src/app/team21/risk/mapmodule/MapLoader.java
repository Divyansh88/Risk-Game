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
	static ArrayList<String> country_list;
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
		
		String file_path = "/Users/samip/Desktop/TestMap.txt";
		HashMap<String, String> map = new HashMap<String, String>();
		country_list = new ArrayList<String>();
		map_details = new HashMap<String, ArrayList>();
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(file_path));
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
					String [] country_details = line.split(",");
					if(country_details.length>=2){
						String country_name=country_details[0];
						country_list.add(country_name);
						ArrayList country_info = new ArrayList();
						country_info.add(country_details[1]);
						country_info.add(country_details[2]);
						country_info.add(country_details[3]);
						for(int j=4;j<country_details.length;j++){
							country_info.add(country_details[j]);
						}
						map_details.put(country_name, country_info);
					}
					else{
						String blank_line = line;
						if(blank_line.length()==0){
							System.out.println(blank_line);
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
