package app.team21.risk.views;
import app.team21.risk.elements.Player;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.views.StartGame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Last Updated on: 26/11/2018, Monday 
 * This class file handles play path screen.
 * 
 * @author Yash Sheth and Samip Thakkar
 * @version 3.0.0
 */
public class PlayPath extends StartGame{
	JPanel choose_player = new JPanel();
	JComboBox maps = new JComboBox();
	MapLoader map_loader=new MapLoader();
	String browse_file_path;
	String file_path="RiskTeam21/src/app/team21/risk/maps/";
	MapElements map_elements;
	JButton btn_back = new JButton("Back"); 
	JButton btn_home = new JButton("Home");
	JLabel lbl_map_status;
	JComboBox players;
	JTextField tf_player1,tf_player2,tf_player3,tf_player4,tf_player5;
	JComboBox cb_player1,cb_player2,cb_player3,cb_player4,cb_player5;
	String[] types = {"Human","Aggressive","Benevolent","Random","Cheater"};
	
	/**
	 * This method will ask for select from existing maps or browse other maps to play the game.
	 */
	public void playButton(){
		JButton map_selected, browse_map;
		JPanel main_panel,choose_map;
		main_panel= new JPanel();
	    choose_map = new JPanel();
		
		
		JLabel select_map,lbl_or;
		CardLayout cl = new CardLayout();
		main_panel.setLayout(cl);
	
		select_map = new JLabel("Select Map");
		lbl_or = new JLabel("OR");
		
		browse_map = new JButton("Browse");
		browse_map.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser file_chooser = new JFileChooser();
			  	 
	  	        file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // For Directory
	  	        FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP file", "map");
	  	        file_chooser.setFileFilter(filter);
	  	        file_chooser.setAcceptAllFileFilterUsed(false);
	  	 
	  	        int bopen = file_chooser.showOpenDialog(null); //open the dialog box
	  	        if (bopen == JFileChooser.APPROVE_OPTION) {
	  	        	browse_file_path=file_chooser.getSelectedFile().toString();
//	  	        	map_elements=new MapElements();
//	  	        	map_loader.readMapFile(browse_file_path);
	  	        	if(MapLoader.readMapFile(browse_file_path).isCorrectMap()){
	  	        		map_elements=MapLoader.readMapFile(browse_file_path);
	  	        		selectPlayers();
						cl.show(main_panel, "choose_player");
					}
					else{
						//Label dialog for incorrect map file
					}
	  	      	}
			}
		});
		
		map_selected= new JButton("Continue");
		map_selected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected_map = maps.getSelectedItem().toString();
				if(MapLoader.readMapFile(file_path+selected_map).isCorrectMap()){
					map_elements=MapLoader.readMapFile(file_path+selected_map);
					selectPlayers();
					cl.show(main_panel, "choose_player");
				}
				else{
					
				}
			}
		});
		
		
		StartGame sg =new StartGame();
		choose_map=sg.getPanel();
		lbl_map_status = new JLabel("");
		
		choose_map.add(select_map);
		fillCombobox();
		choose_map.add(maps);
		choose_map.add(lbl_or);
		choose_map.add(browse_map);
		choose_map.add(lbl_map_status);
		choose_map.add(map_selected);
		
		
		main_panel.add(choose_map,"choose_map");
		main_panel.add(choose_player, "choose_player");
		cl.show(main_panel, "choose_map");
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(main_panel);
		jf.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.close();
				sg.createStartScreen();
				
			}
		});
		jf.add(btn_home);
		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.close();
				sg.createStartScreen();
				
			}
		});
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
	
	/**
	 * This method will fill comboboxes.
	 */
	public void fillCombobox(){
		File dir = new File("RiskTeam21/src/app/team21/risk/maps/");
        FilenameFilter filter = new FilenameFilter()
        {
        	public boolean accept(File dir, String name)
            {
                return name.endsWith(".map");
            }
        };
        String[] var = dir.list(filter);
        
        if (var == null)
        {
       		System.out.println("JCOMBO BOX Directory is INCORRECT or does not exist!");
        }
        else
        {
            for (int i=0; i<var.length; i++) //adding to combobox
            {
                String filename = var[i];
                maps.addItem(filename);
            }
        }
	}
	
	/**
	 * This method will ask for number of players.
	 */
	public void selectPlayers(){
		JPanel test = new JPanel();
		StartGame sg= new StartGame();
		
		test=sg.getPanel();
		
		JLabel choose_number_of_player = new JLabel("Choose number of players :");
		JButton ok = new JButton("Ok");
		
		players = new JComboBox<Integer>();
		int no_countries = map_elements.getCountries().size();
		switch (no_countries) {
		case 2:
			players.addItem("2");
			break;
		case 3:
			players.addItem("2");
			players.addItem("3");
			break;
		case 4:
			players.addItem("2");
			players.addItem("3");
			players.addItem("4");
			break;
		default:
			players.addItem("2");
			players.addItem("3");
			players.addItem("4");
			players.addItem("5");
			break;
		}
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				playerTypes();
				
			}
		});
//		choose_player.add(choose_number_of_player);
//		choose_player.add(players);
//		choose_player.add(lets_go);
		test.add(choose_number_of_player);
		test.add(players);
		test.add(ok);
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.close();
				playButton();
				
			}
		});
		jf.add(btn_home);
		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.close();
				sg.createStartScreen();
				
			}
		});
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
	
	/**
	 * This method will ask to select player type.
	 */
	public void playerTypes() {
		JPanel test = new JPanel();
		StartGame sg= new StartGame();
		
		test=sg.getPanel();
		
		tf_player1 = new JTextField("Player1 Name");
		tf_player2 = new JTextField("Player2 Name");
		tf_player3 = new JTextField("Player3 Name");
		tf_player4 = new JTextField("Player4 Name");
		tf_player5 = new JTextField("Player5 Name");
		
		cb_player1 = new JComboBox(types);
		cb_player2 = new JComboBox(types);
		cb_player3 = new JComboBox(types);
		cb_player4 = new JComboBox(types);
		cb_player5 = new JComboBox(types);
		
		int no_players = Integer.valueOf(players.getSelectedItem().toString());
		System.out.println("items"+no_players);
		switch (no_players) {
		case 2:
			test.add(tf_player1);
			test.add(cb_player1);
			test.add(tf_player2);
			test.add(cb_player2);
			break;
		case 3:
			test.add(tf_player1);
			test.add(cb_player1);
			test.add(tf_player2);
			test.add(cb_player2);
			test.add(tf_player3);
			test.add(cb_player3);
			break;
		case 4:
			test.add(tf_player1);
			test.add(cb_player1);
			test.add(tf_player2);
			test.add(cb_player2);
			test.add(tf_player3);
			test.add(cb_player3);
			test.add(tf_player4);
			test.add(cb_player4);
			break;
		case 5:
			test.add(tf_player1);
			test.add(cb_player1);
			test.add(tf_player2);
			test.add(cb_player2);
			test.add(tf_player3);
			test.add(cb_player3);
			test.add(tf_player4);
			test.add(cb_player4);
			test.add(tf_player5);
			test.add(cb_player5);
			break;
		default:
			System.out.println("ERROR");
			break;
		}
		
		JButton lets_go = new JButton("Let's go");
		
		lets_go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Player> player_list = new ArrayList<Player>();
				
				if(no_players==2) {
					String type=cb_player1.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player1.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player1.getText(),true,type.toLowerCase());
						player_list.add(p);
					}	
					type=cb_player2.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player2.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player2.getText(),true,type.toLowerCase());
						player_list.add(p);
					}	
				}
				else if(no_players==3) {
					String type=cb_player1.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player1.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player1.getText(),true,type.toLowerCase());
						player_list.add(p);
					}	
					type=cb_player2.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player2.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player2.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
					type=cb_player3.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player3.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player3.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
				}
				else if(no_players==4) {
					String type=cb_player1.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player1.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player1.getText(),true,type.toLowerCase());
						player_list.add(p);
					}	
					type=cb_player2.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player2.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player2.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
					type=cb_player3.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player3.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player3.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
					type=cb_player4.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player4.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player4.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
				}
				else if(no_players==5) {
					String type=cb_player1.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player1.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player1.getText(),true,type.toLowerCase());
						player_list.add(p);
					}	
					type=cb_player2.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player2.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player2.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
					type=cb_player3.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player3.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player3.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
					type=cb_player4.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player4.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player4.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
					type=cb_player5.getSelectedItem().toString();
					if(type.equals("Human")){
						Player p=new Player(tf_player5.getText(),false,"Human");
						player_list.add(p);
					}else{
						Player p=new Player(tf_player5.getText(),true,type.toLowerCase());
						player_list.add(p);
					}
				}
				else{
					System.out.println("ERROR");
				}
				GameScreen gs = new GameScreen();
				gs.playerContinueButton(map_elements,player_list,1);
				
			}
		});
		
		test.add(lets_go);
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.close();
				selectPlayers();
				
			}
		});
		jf.add(btn_home);
		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.close();
				sg.createStartScreen();
				
			}
		});
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
}


