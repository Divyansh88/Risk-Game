package app.team21.risk.mapmodule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;


/**
 * Date Created: 01-10-2018, Friday
 * Last Updated on: 01-10-2018, Saturday
 * This class file allows user to create and edit maps and save according to the format.
 * @author Yash Sheth
 * @version 1
 *
 */
public class MapEditor {
	/**
     * This method will add country in existing Map
     *
     * @param country      the country that you want to add
     * @param gameMap      current map details
     * @param neighbourList List of neighbourCountry
     */
    public void addCountry(Country country, MapElements map_elements, List<Country> neighbour_list) {
        if (country != null && !neighbour_list.isEmpty()) {
        	map_elements.getCountryNeighboursMap().put(country, neighbour_list); // country added to existing game details map
            // Updating neighbour list
            for (Country neighbour : neighbour_list) {
                addNeighbour(neighbour.getCountryName(), map_elements, country);
            }
        }
    }

    /**
     * This method will add neighbour to Country
     *
     * @param countryName     country name where neighbours to be added
     * @param gameMap         current map details
     * @param neighbourCountry neighbour country to be added.
     */
    public void addNeighbour(String country_name, MapElements map_elements, Country neighbourCountry) {
        if (!country_name.isEmpty() && country_name!=null && !country_name.equals("")) {
            Country country = new Country(country_name);
            if (map_elements.getCountryNeighboursMap().containsKey(country)) {
            	map_elements.getCountryNeighboursMap().get(country).add(neighbourCountry);
            }
        }
    }
    
	
    /**
     * @param continent
     * @param map_elements
     */
    public void addContinent(Continent continent, MapElements map_elements) {
        if (continent != null && continent.getControlValue()>=0) {
        	map_elements.getContinentList().add(continent);
        	map_elements.getContinentCountryMap().put(continent, continent.getMemberCountriesList());        	
        }
    }
    
    /**
     * @param continent
     */
    public void validateContinent(Continent continent){
    	//if(continent.getMemberCountriesList().size()>0)
    	
    	
    }
    
/**
     * This method will create .map file based on input provided from user
     *
     * @param graphMap Details provided from the user
     * @param filename Map file name that user wants to give
     */
    
    public void writeMap(MapElements map_elements, String filename) {
        StringBuilder maps = new StringBuilder("[Map]\n");
        for (Map.Entry<String, String> entry : map_elements.getMapDetail().entrySet()) {
            maps.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
        }
        maps.append("\n\n");

        StringBuilder continents = new StringBuilder("[Continents]\n");
        System.out.println("Print Status: this is the size of continents:" + map_elements.getContinentList().size());
        for (Continent continent : map_elements.getContinentList()) {
            continents.append(continent.continentName).append("=").append(continent.controlValue).append("\n");
        }
        continents.append("\n");

        StringBuilder territories = new StringBuilder("[Territories]\n");

        // loop of graphMap
        Iterator<Map.Entry<Country, List<Country>>> it = map_elements.getCountryNeighboursMap().entrySet().iterator();
        int startPixel = 45;
        int endPixel = 60;
        while (it.hasNext()) {
            Map.Entry<Country, List<Country>> pair = it.next();
            // get country object
            Country keyCountry = pair.getKey();
            // get list of the neighbours
            List<Country> neiCountryList = pair.getValue();

            // index of the country from the all countries of all continents
            // list
            startPixel += 10;
            endPixel += 10;
            // get values of each country object
            territories.append(keyCountry.countryName).append(",").append(startPixel).append(",").append(endPixel).append(",").append(keyCountry.getBelongsToContinent());

            // get the index value of the neighbour
            for (Country c : neiCountryList) {
                territories.append(",").append(c.countryName);
            }
            territories.append("\n");
        }

        String result = maps + continents.toString() + territories;

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            out.print(result);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
