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
 * Date Created: 28-09-2018, Friday
 * Last Updated on: 29-09-2018, Saturday
 * This class file handles .txt map file and creates a GUI of connected graph.
 * @author Yash Sheth
 * @author Samip Thakkar
 * @version 1
 *
 */

public class MapLoader{

	static HashMap<String, String> map_details;
	static List<Continent> continent_list;
	static List<Country> country_list;
	static HashMap<Continent, List<Country>> continent_country_map;
	
	/**
	 * Main method. 
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException{
		String file_path = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/World.map";
		readMapFile(file_path);
	}
		

	public static void readMapFile(String file_path) {
		
		boolean is_map_present = false; //to check [MAP] is available in file or not
        boolean is_continent_resent = false;//to check [Continent] is available in file or not
        boolean is_territory_present = false;//to check [Territory] is available in file or not
        
        try {
            File file = new File(file_path);
            String validationMessage = validateFile(file);
            if (!validationMessage.equalsIgnoreCase("Valid File")) {
				System.out.println("Invalid File");
            }
            
            String line;
    		BufferedReader reader = new BufferedReader(new FileReader(file_path));
                        
            continent_list = new ArrayList<>();
            country_list = new ArrayList<>();
        	map_details = new HashMap<String,String>();
        	continent_country_map = new HashMap<Continent, List<Country>>();
        	
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[")) {
                    String id = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    
                    // Parsing the [MAP] portion of the map file
                    if (id.equalsIgnoreCase("Map")) {
                    	System.out.println("Map Tag Present");
                    	String maps;
                        is_map_present = true;
                        while ((maps = reader.readLine()) != null && !maps.startsWith("[")) {
                            if (!maps.isEmpty() && maps!=null && !maps.equals("")){
                                String[] mapsEntry = maps.split("=");
                                map_details.put(mapsEntry[0], mapsEntry[1]);
                                reader.mark(0);
                            }
                        }
                        reader.reset();
                    }
                    
                    //Parsing the [Continents] portion of the map file
                    if (id.equalsIgnoreCase("Continents")){
                    	System.out.println("Continents Tag Present");
                        is_continent_resent = true;
                        if (is_map_present) {
                            readContinents(reader);
                            System.out.println("Reading of Continents Completed");
                        }
                    }
                    
                    //Parsing the  [Territories] portion of the map file
                    if (id.equalsIgnoreCase("Territories")) {
                    	System.out.println("Territories Tag Present");
                        is_territory_present = true;
                        if (is_map_present) {
                            readTerritories(reader);
                            getContinentCountryMap(country_list, continent_list);
                            System.out.println("Reading of Territories Completed");
                        }
                    }
                }
            }

            if (is_map_present && is_continent_resent && is_territory_present) {
                System.out.println("Map Continents and Territories tags are present");
            } else {
                System.out.println("Invalid Map File.\nMap or Continents or Territories tags not present");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }	
    /**
     * This method will perform validation of provided input file
     *
     * @param file File class object is passed where input file is selected by user and is check for validation
     * @return error/success Message
     */
    public static String validateFile(File file) {

        //check if file is present or not
        if (!file.exists()) {
            return "File does not exist.";
        }
        String name = file.getName();
        String extension = name.substring(name.lastIndexOf(".") + 1);
        //check if user has selected  file with .map extension
        if (!extension.equalsIgnoreCase("map")) {
            return "Invalid extension of File. Please provide the correct file.";
        }
        //check if file selected by user is empty or not
        if (file.length() == 0) {
            return "File is empty please select correct file.";
        }
        return "Valid File";


    }
    
    /**
     * This method will read [Territories] part of map file
     *
     * @param reader BufferedReader class object as param
     */
    public static void readTerritories(BufferedReader reader) {
        String Territories;

        try {
            while ((Territories = reader.readLine()) != null && !Territories.startsWith("[")) {
                if (!Territories.isEmpty() && Territories!=null && !Territories.equals("")) {
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
                    country.setNeighborNodes(neighbour_nodes);
                    country_list.add(country);

                }
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();

        }
        System.out.println("Territories on the Map");    
        for(Country c : country_list){
            System.out.println(c.getCountryName()+" of Continent "+c.getBelongsToContinent());
            }
    }

    /**
     * This method will read the continent part of map file
     *
     * @param reader BufferReader object that read the .map file for continent
     */
    public static void readContinents(BufferedReader reader) {
        String Continents;
        try {
            while ((Continents = reader.readLine()) != null && !Continents.startsWith("[")) {
                if (!Continents.isEmpty() && Continents!=null && !Continents.equals("")) {
                    Continent continents = new Continent();
                    List<Integer> allContinents = new ArrayList<Integer>();
                    String[] ConProperties = Continents.split("=");
                    continents.setContinentName(ConProperties[0].trim());
                    continents.setControlValue(Integer.parseInt(ConProperties[1].trim()));
                    continent_list.add(continents);
                }
                reader.mark(0);
            }
            reader.reset();
        } catch (NumberFormatException | IOException e) {

            e.printStackTrace();
        }
        System.out.println("Continents on the Map");
        for(Continent c : continent_list){
        System.out.println(c.getContinentName()+" "+c.getControlValue()+" "+c.getNumberOfTerritories());
        }
    }
    
    
    /**
     * This  method will prepare HashMap for continent and country objects
     *
     * @param country list of country that we have got while parsing the  map file
     * @param continent_list continent_list that we have got while parsing the map file
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
                    country_list.add(c); // Avoid using Array.asList here as it gives fixed size list and will not allow to modify the list
                    continent_country_map.put(continent_list.get(index_continent), country_list);
                }
            }
        }
    }
}
