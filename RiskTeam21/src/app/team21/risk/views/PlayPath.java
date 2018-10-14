package app.team21.risk.views;
import app.team21.risk.elements.Player;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayPath extends StartGame{
	JPanel choose_player = new JPanel();
	JComboBox maps = new JComboBox();
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
				// Add vaghani's code for browse here
				
			}
		});
		
		map_selected= new JButton("Continue");
		map_selected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectPlayers();
				cl.show(main_panel, "choose_player");
				
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
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		
	}
	
	public void fillCombobox(){
		
		File dir = new File("/Users/samip/git/RiskTeam/RiskTeam21/src/app/team21/risk/maps");
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
		
		JLabel choose_number_of_player = new JLabel("Choose number of players :");
		JButton lets_go = new JButton("Let's go");
		
		JComboBox players = new JComboBox<Integer>();
		int no_countries = 4;
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
		else if(no_countries==5){
			players.addItem("2");
			players.addItem("3");
			players.addItem("4");
			players.addItem("4");
		}
		
		lets_go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Player> player_list = new ArrayList<Player>();
				for(int i=1;i<4;i++){
					Player p= new Player("Player "+i);
					
					player_list.add(p);
				}
				
				GameScreen gs = new GameScreen();
				gs.playerContinueButton();
				
			}
		});
		
		
		choose_player.add(choose_number_of_player);
		choose_player.add(players);
		choose_player.add(lets_go);
	}

}


