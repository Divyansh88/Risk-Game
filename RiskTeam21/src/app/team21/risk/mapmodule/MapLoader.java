package app.team21.risk.mapmodule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;

/**
 * Date Created: 28-09-2018, Friday Last Updated on: 29-09-2018, Saturday This
 * class file handles .txt map file and creates a GUI of connected graph.
 * 
 * @author Yash Sheth
 * @version 1
 *
 */

public class MapLoader {

	static HashMap<String, String> map_details;
	static List<Continent> continent_list;
	static List<Country> country_list;
	static HashMap<Continent, List<Country>> continent_country_map;
	static HashMap<Country, List<Country>> country_neighbour_map;



	/**
	 * this map helps you to read th map file
	 * @param file_path 
	 * @return MapElements      
	 */
	public static MapElements readMapFile(String file_path) {

		boolean is_map_present = false; // to check [MAP] is available in file
										// or not
		boolean is_continent_present = false;// to check [Continent] is available
											// in file or not
		boolean is_territory_present = false;// to check [Territory] is
												// available in file or not
		MapElements map_elements = MapElements.getInstance();

		try {
			File file = new File(file_path);
			String validationMessage = validateFile(file);
			if (!validationMessage.equalsIgnoreCase("Valid File")) {
				System.out.println("Invalid File");
				map_elements.setCorrectMap(false);
				map_elements.setErrorMessage(validationMessage);
				return map_elements;

			}

			String line;
			BufferedReader reader = new BufferedReader(new FileReader(file_path));

			continent_list = new ArrayList<>();
			country_list = new ArrayList<>();
			map_details = new HashMap<String, String>();
			continent_country_map = new HashMap<Continent, List<Country>>();
			country_neighbour_map = new HashMap<Country, List<Country>>();

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("[")) {
					String id = line.substring(line.indexOf("[") + 1, line.indexOf("]"));

					// Parsing the [MAP] portion of the map file
					if (id.equalsIgnoreCase("Map")) {
						System.out.println("Map Tag Present");
						String maps;
						is_map_present = true;
						while ((maps = reader.readLine()) != null && !maps.startsWith("[")) {
							if (!maps.isEmpty() && maps != null && !maps.equals("")) {
								String[] mapsEntry = maps.split("=");
								map_details.put(mapsEntry[0], mapsEntry[1]);
								reader.mark(0);
							}
						}
						reader.reset();
						map_elements.setMapDetail(map_details);
					}

					// Parsing the [Continents] portion of the map file
					if (id.equalsIgnoreCase("Continents")&&map_elements.is_correct_map()) {
						System.out.println("Continents Tag Present");
						is_continent_present = true;
						if (is_map_present) {
							map_elements=readContinents(reader,map_elements);
							if(map_elements.is_correct_map()){
								map_elements.setContinentList(continent_list);
								System.out.println("Reading of Continents Completed");
							}
							else{
								map_elements.setCorrectMap(false);
								map_elements.setErrorMessage("Invalid Properties in Continent");
							}
						}
					}

					// Parsing the [Territories] portion of the map file
					if (id.equalsIgnoreCase("Territories")) {
						System.out.println("Territories Tag Present");
						is_territory_present = true;
						List<Continent> new_continent_list = new ArrayList<>();
						if (is_map_present) {
							readTerritories(reader);
							getContinentCountryMap(country_list, continent_list);
							for (Country c : country_list) {
								country_neighbour_map.put(c, c.getNeighbourNodes());
							}
							for (Continent continent : map_elements.getInstance().getContinentList()) {
								continent.setMemberCountriesList(
								continent_country_map.get(new Continent(continent.getContinentName())));
								new_continent_list.add(continent);
							}
							map_elements.setContinentCountryMap(continent_country_map);
							map_elements.setCountryNeighboursMap(country_neighbour_map);
							map_elements.setContinentCountryMap(continent_country_map);
							map_elements.setContinentList(new_continent_list);
							System.out.println("Reading of Territories Completed");
						}
					}
				}
			}

			if (is_map_present && is_continent_present && is_territory_present&&map_elements.is_correct_map()) {
				System.out.println("Valid File.\nMap Continents and Territories tags are present");

			} else {
				System.out.println("Invalid Map File.\nMap or Continents or Territories tags not present");
				map_elements.setCorrectMap(false);
				map_elements.setErrorMessage("Map or Continents or Territories tags not present");
				//System.out.println(map_elements.getErrorMessage());
				return map_elements;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		checkControlValue(map_elements);
		return map_elements;
	}

	/**
	 * This method will perform validation of provided input file
	 *
	 * @param file
	 *            File class object is passed where input file is selected by
	 *            user and is check for validation
	 * @return error/success Message
	 */
	public static String validateFile(File file) {

		// check if file is present or not
		if (!file.exists()) {
			return "File does not exist.";
		}
		String name = file.getName();
		String extension = name.substring(name.lastIndexOf(".") + 1);
		// check if user has selected file with .map extension
		if (!extension.equalsIgnoreCase("map")) {
			return "Invalid extension of File. Please provide the correct file.";
		}
		// check if file selected by user is empty or not
		if (file.length() == 0) {
			return "File is empty please select correct file.";
		}
		return "Valid File";

	}

	/**
	 * This method will read [Territories] part of map file
	 *
	 * @param reader
	 *            BufferedReader class object as param
	 */
	public static void readTerritories(BufferedReader reader) {
		String Territories;

		try {
			while ((Territories = reader.readLine()) != null && !Territories.startsWith("[")) {
				if (!Territories.isEmpty() && Territories != null && !Territories.equals("")) {
					String country_name, continent_name = null;
					int x_cordinate, y_cordinate = 0;
					List<Country> neighbour_nodes = new ArrayList<>();
					String[] terrProperties = Territories.split(",");
					country_name = terrProperties[0];
					continent_name = terrProperties[3];
					x_cordinate = Integer.parseInt(terrProperties[1].trim());
					y_cordinate = Integer.parseInt(terrProperties[2].trim());
					Country country = new Country(country_name, x_cordinate, y_cordinate, continent_name);
					for (int i = 4; i <= terrProperties.length - 1; i++) {
						String neighbour_country_name = terrProperties[i];
						Country neighbour = new Country(neighbour_country_name);
						neighbour_nodes.add(neighbour);
					}

					country.setNeighbourNodes(neighbour_nodes);
					country_list.add(country);

				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * This method will read the continent part of map file
	 *
	 * @param reader
	 *            BufferReader object that read the .map file for continent
	 */
	public static MapElements readContinents(BufferedReader reader,MapElements map_elements) throws IOException{
		String line;
		try {
			while ((line = reader.readLine()) != null && !line.startsWith("[")) {
				if (!line.isEmpty() && line != null && !line.equals("")&&line.contains("=")) {
					Continent continents = new Continent();
					String[] ConProperties = line.split("=");
					if(!ConProperties[0].equals("")&&!ConProperties[1].equals("")&&!ConProperties[0].isEmpty()&&!ConProperties[1].isEmpty()){
		
						continents.setContinentName(ConProperties[0].trim());
						continents.setControlValue(Integer.parseInt(ConProperties[1].trim()));
						continent_list.add(continents);	
					}
					else{
						map_elements.setCorrectMap(false);
						break;
					}
				}
				reader.mark(0);

			}
			reader.reset();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		return map_elements;
	}

	/**
	 * This method will prepare HashMap for continent and country objects
	 *
	 * @param country
	 *            list of country that we have got while parsing the map file
	 * @param continent_list
	 *            continent_list that we have got while parsing the map file
	 * @return HashMap of continent to countries objects
	 */
	private static void getContinentCountryMap(List<Country> country, List<Continent> continent_list) {
		for (Country c : country) {
			List<Country> country_list = new ArrayList<>();
			if (continent_list.contains(new Continent(c.getBelongsToContinent()))) {
				if (continent_country_map.containsKey(new Continent(c.getBelongsToContinent()))) {
					continent_country_map.get(new Continent(c.getBelongsToContinent())).add(c);
				} else {
					int index_continent = continent_list.indexOf(new Continent(c.getBelongsToContinent()));
					country_list.add(c); // Avoid using Array.asList here as it
											// gives fixed size list and will
											// not allow to modify the list
					continent_country_map.put(continent_list.get(index_continent), country_list);
				}
			}
		}
	}
	
	public static void checkControlValue(MapElements map_elements){
		for(Continent continent: map_elements.getContinentList()) {
        	if(continent.getControlValue()<=0) {
        		
        		map_elements.setCorrectMap(false);
                
        		map_elements.setErrorMessage("There is invalid  control value of the continent-->"+continent.getContinentName());
                break;
        	}
        }
	}
}