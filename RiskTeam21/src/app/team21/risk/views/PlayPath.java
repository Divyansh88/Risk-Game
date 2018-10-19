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
import javax.swing.filechooser.FileNameExtensionFilter;

public class PlayPath extends StartGame{
	JPanel choose_player = new JPanel();
	JComboBox maps = new JComboBox();
	MapLoader map_loader=new MapLoader();
	String browse_file_path;
	String file_path="C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/";
	MapElements map_elements;
	JButton btn_back = new JButton("Back"); 
	JButton btn_home = new JButton("Home");
	JComboBox players;
	
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
	  	        	String short_name=browse_file_path.substring(browse_file_path.lastIndexOf("\\") + 1);
	  	        	maps.addItem(short_name);
	  	      		}
			}
		});
		
		map_selected= new JButton("Continue");
		map_selected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected_map = maps.getSelectedItem().toString();
				map_elements=map_loader.readMapFile(file_path+selected_map);
				if(map_elements.isCorrectMap()){
					selectPlayers();
					cl.show(main_panel, "choose_player");
				}
				else{
					System.out.println("Could not load. Invalid Map File.");
				}
			}
		});
		
		
		StartGame sg =new StartGame();
		choose_map=sg.getPanel();
		
		choose_map.add(select_map);
		fillCombobox();
		choose_map.add(maps);
		choose_map.add(lbl_or);
		choose_map.add(browse_map);
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
				sg.createStartScreen();
				
			}
		});
		jf.add(btn_home);
		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.createStartScreen();
				
			}
		});
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		
	}
	
	public void fillCombobox(){
		
		File dir = new File("C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/");
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
	
	public void selectPlayers(){
		JPanel test = new JPanel();
		StartGame sg= new StartGame();
		
		test=sg.getPanel();
		
		JLabel choose_number_of_player = new JLabel("Choose number of players :");
		JButton lets_go = new JButton("Let's go");
		
		players = new JComboBox<Integer>();
		int no_countries = map_elements.getCountries().size();
		if(no_countries==2){
			players.addItem("2");
		}
		else if(no_countries==3){
			players.addItem("2");
			players.addItem("3");
		}
		else if(no_countries==4){
			players.addItem("2");
			players.addItem("3");
			players.addItem("4");
		}
		else {
			players.addItem("2");
			players.addItem("3");
			players.addItem("4");
			players.addItem("5");
		}
		
		
		lets_go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Player> player_list = new ArrayList<Player>();
				for(int i=1;i<=Integer.valueOf(players.getSelectedItem().toString());i++){
					Player p= new Player("Player "+i);
					player_list.add(p);
				}
				
				GameScreen gs = new GameScreen();
				gs.playerContinueButton(map_elements,player_list,1);
				
			}
		});
		
		test.add(choose_number_of_player);
		test.add(players);
		test.add(lets_go);
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playButton();
				
			}
		});
		jf.add(btn_home);
		btn_home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sg.createStartScreen();
				
			}
		});
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

	}
}


