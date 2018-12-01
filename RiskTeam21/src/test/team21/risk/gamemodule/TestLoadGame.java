package test.team21.risk.gamemodule;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.team21.risk.elements.Player;
import app.team21.risk.gamemodule.Deck;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.views.GameScreen;


/**
 * Last Updated on : 06/11/2018, Tuesday
 * This is a test class for deck card.
 *
 * @author Yash Sheth
 * @version 2.0.0
 */
public class TestLoadGame{

	private MapElements elements;
    private MapLoader loader;
    private GamePlay game_play;
    private GameScreen game_view;
    Player player1,player2; 
    String file_path="RiskTeam21/src/app/team21/risk/maps/India.map";
    
    /**
     * This is method initializes important objects and variables used in the test.
     * This method is called before test runs.
     */
    @Before
    public void init() {
    	loader = new MapLoader();
    	elements = MapLoader.readMapFile(file_path);
    	
    	player1 = new Player("KING",false,"human");
    	player2 = new Player("Player 2",false,"human");
    	
    	game_play = new GamePlay();
    	game_view = new GameScreen();
    	game_view.map_elements=elements;
    	List<Player> player_list=new ArrayList<>();
    	player_list.add(player1);
    	player_list.add(player2);
    	
		game_play.distributeCountries(player_list, elements.getCountries());
		game_play.setInitialArmies(player_list);
		game_play.placeInitialArmiesInRR(player_list);
		game_view.setPlayerList(player_list);
    }
    

	/**
     * This method checks whether the deck cards has populated correctly.
	 * @throws Exception 
     */
    @Test
    public void checkSaveFile() throws Exception {
        game_view.saveGame();
    	//game_play.saveGame(elements, game_view);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today;
        String file_name="";
        try {
			today = formatter.parse(formatter.format(new Date()));
			file_name=today.toString().replaceAll("00:00:00"," ").replaceAll("\\s+","").concat("_"+(game_play.save_file_counter-1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        MapElements loaded=GamePlay.loadGame(file_name+".ser");
        
        boolean result=false;
        for(Player p:loaded.getPlayerList()){
        	if(p.getName().equals("KING")){
        		result=true;
        		break;
        	}
        }
        assertEquals(true, result);
        
    }
}
