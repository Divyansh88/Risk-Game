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

	static HashMap<String, ArrayList> map_details;
	static ArrayList<String> country_list;
	static HashMap<String,Integer> continent_value; 
	
	/**
	 * Main method. 
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException{
		String file_path = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/World.map";
		readMapFile(file_path);
//		readMap();
	}
		
//	/**
//	 * This method reads text file of map and converts it to Java output
//	 * @throws IOException
//	 */
//	
//	public static void readMap()throws IOException{
//		
//		String file_path = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/India.map";
//		HashMap<String, String> map = new HashMap<String, String>();
//		country_list = new ArrayList<String>();
//		continent_value=new HashMap<String, Integer>();
//		map_details = new HashMap<String, ArrayList>();
//		String line;
//		BufferedReader reader = new BufferedReader(new FileReader(file_path));
//		while ((line = reader.readLine()) != null){
//			String[] MapDetails=line.split("=",2);
//			if (MapDetails.length >= 2){
//				String key = MapDetails[0];
//				String value = MapDetails[1];
//				map.put(key, value);
//				if(key.length()==0 || value.length()==0){
//					System.out.println("Invalid format");
//					System.exit(0);
//					}
//				else{
//					System.out.println(key +" = " + map.get(key));
//				}			
//			}
//			else{
//				String[] headers=line.split("]",2);
//				if (headers.length >= 2){
//					String headerkey = headers[0];
//					if(headerkey.length()==0){
//						System.out.println("Invalid format");
//					}
//					else{
//						System.out.println(headerkey+"]");
//					}
//				}
//				else{
//					String [] country_details = line.split(",");
//					if(country_details.length>=2){
//						String country_name=country_details[0];
//						country_list.add(country_name);
//						ArrayList<String> country_info = new ArrayList<String>();
//						country_info.add(country_details[1]);
//						country_info.add(country_details[2]);
//						country_info.add(country_details[3]);
//						for(int j=4;j<country_details.length;j++){
//							country_info.add(country_details[j]);
//						}
//						map_details.put(country_name, country_info);
//					}
//					else{
//						String blank_line = line;
//						if(blank_line.length()==0){
//							System.out.println(blank_line);
//						}
//						else{
//							System.out.println("Invalid format");
//							System.exit(0);
//						}					
//					}
//				}
//			}
//			
//		}
//		reader.close();
//	}
	
	public static void readMapFile(String file_path) {
		boolean isMAPPresent = false; //to check [MAP] is available in file or not
        boolean isContinentPresent = false;//to check [Continent] is available in file or not
        boolean isTerritoryPresent = false;//to check [Territory] is available in file or not
        try {
            File file = new File(file_path);
            String validationMessage = validateFile(file);
            if (!validationMessage.equalsIgnoreCase("Valid File")) {
				System.out.println("Invalid File");
            }
            String line;
    		BufferedReader reader = new BufferedReader(new FileReader(file_path));
            
            String maps;
            List<Continent> listOfContinents = new ArrayList<>();
        	HashMap<String, String> mapDetail = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[")) {
                    String id = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    
                    // Parsing the [MAP] portion of the map file
                    if (id.equalsIgnoreCase("Map")) {
                    	System.out.println("Map Tag Present");
                        isMAPPresent = true;
                        while ((maps = reader.readLine()) != null && !maps.startsWith("[")) {
                            if (!maps.isEmpty() && maps!=null && !maps.equals("")){
                                String[] mapsEntry = maps.split("=");
                                mapDetail.put(mapsEntry[0], mapsEntry[1]);
                                reader.mark(0);
                            }
                        }
                        reader.reset();
                    }
                    
                    //Parsing the [Continents] portion of the map file
                    if (id.equalsIgnoreCase("Continents")){
                    	System.out.println("Continents Tag Present");
                        isContinentPresent = true;
                        if (isMAPPresent) {
                            listOfContinents = MapLoader.readContinents(reader);
                            System.out.println("Reading of Continents Completed");
                        }
                    }
                    
                    //Parsing the  [Territories] portion of the map file
                    if (id.equalsIgnoreCase("Territories")) {
                    	System.out.println("Territories Tag Present");
                        isTerritoryPresent = true;
                        if (isMAPPresent) {
                            List<Country> countryAndNeighbor = MapLoader.readTerritories(reader);

                            HashMap<Continent, List<Country>> continentCountryMap = getContinentCountryMap(countryAndNeighbor, listOfContinents);
                            List<Continent> updatedcontinentList = new ArrayList<>();// we need to assign number of territories to continent objects
                            System.out.println("Reading of Territories Completed");
                        }
                    }
                }
            }

            if (isMAPPresent && isContinentPresent && isTerritoryPresent) {
                System.out.println("Map Continents and Territories tags are present");
            } else {
                System.out.println("Map or Continents or Territories tags not present");
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
            return "File does not exists";
        }
        String name = file.getName();
        String extension = name.substring(name.lastIndexOf(".") + 1);
        //check if user has selected  file with .map extension
        if (!extension.equalsIgnoreCase("map")) {
            return "Invalid extension of  File Please provide correct file";
        }
        //check if file selected by user is empty or not
        if (file.length() == 0) {
            return "File is empty please select correct file";
        }
        return "Valid File";


    }
    
    /**
     * This method will read [Territories] part of map file
     *
     * @param reader BufferedReader class object as param
     * @return List of Country.Every single object of country will have countryname,continentname,start/end pixels;
     */
    public static List<Country> readTerritories(BufferedReader reader) {
        String Territories;
        List<Country> countryList = new ArrayList<>();
        try {
            while ((Territories = reader.readLine()) != null && !Territories.startsWith("[")) {
                if (!Territories.isEmpty() && Territories!=null && !Territories.equals("")) {
                    String countryName, continentName = null;
                    int startPixel, endPixel = 0;
                    List<Country> neighbourNodes = new ArrayList<>();
                    String[] terrProperties = Territories.split(",");
                    countryName = terrProperties[0];
                    continentName = terrProperties[3];
                    startPixel = Integer.parseInt(terrProperties[1].trim());
                    endPixel = Integer.parseInt(terrProperties[2].trim());
                    Country country = new Country(countryName, startPixel, endPixel, continentName);
                    for (int i = 4; i <= terrProperties.length - 1; i++) {
                        String neighbourCountryName = terrProperties[i];
                        Country neighbour = new Country(neighbourCountryName);
                        neighbourNodes.add(neighbour);
                    }
                    country.setNeighborNodes(neighbourNodes);
                    countryList.add(country);

                }
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();

        }
        System.out.println("Territories on the Map");    
        for(Country c : countryList){
            System.out.println(c.getCountryName()+" of Continent "+c.getBelongsToContinent());
            }
        return countryList;
    }

    /**
     * This method will read the continent part of map file
     *
     * @param reader BufferReader object that read the .map file for continent
     * @return List of Continent. every single object of the list contains continentName and number of countries it hold.
     */
    public static List<Continent> readContinents(BufferedReader reader) {
        String Continents;
        List<Continent> continentsList = new ArrayList<>();
        try {
            while ((Continents = reader.readLine()) != null && !Continents.startsWith("[")) {
                if (!Continents.isEmpty() && Continents!=null && !Continents.equals("")) {
                    Continent continents = new Continent();
                    List<Integer> allContinents = new ArrayList<Integer>();
                    String[] ConProperties = Continents.split("=");
                    continents.setContinentName(ConProperties[0].trim());
                    continents.setControlValue(Integer.parseInt(ConProperties[1].trim()));
                    continentsList.add(continents);
                }
                reader.mark(0);
            }
            reader.reset();
        } catch (NumberFormatException | IOException e) {

            e.printStackTrace();
        }
        System.out.println("Continents on the Map");
        for(Continent c : continentsList){
        System.out.println(c.getContinentName()+" "+c.getControlValue());
        }
        return continentsList;
    }
    
    
    /**
     * This  method will prepare HashMap for continent and country objects
     *
     * @param country list of country that we have got while parsing the  map file
     * @param listOfContinents listOfContinents that we have got while parsing the map file
     * @return HashMap of continent to countries objects
     */
    private static HashMap<Continent, List<Country>> getContinentCountryMap(List<Country> country, List<Continent> listOfContinents) {
        HashMap<Continent, List<Country>> continentCountryMap = new HashMap<>();
        for (Country c : country) {
            List<Country> countryList = new ArrayList<>();
            if (listOfContinents.contains(new Continent(c.getBelongsToContinent()))) {
                if (continentCountryMap.containsKey(new Continent(c.getBelongsToContinent()))) {
                    continentCountryMap.get(new Continent(c.getBelongsToContinent())).add(c);
                } else {
                    int indexOfContinent = listOfContinents.indexOf(new Continent(c.getBelongsToContinent()));
                    countryList.add(c); // Avoid using Array.asList here as it gives fixed size list and will not allow to modify the list
                    continentCountryMap.put(listOfContinents.get(indexOfContinent), countryList);
                }
            }
        }
        return continentCountryMap;
    }

}
