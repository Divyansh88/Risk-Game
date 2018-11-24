package app.team21.risk.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.team21.risk.mapmodule.MapElements;

/**
 * @author Divyansh
 *
 */
public class TournamentPath {
	
	JPanel tournament_master_panel,player_maps_master_panel,player_type_panel,maps_panel,number_of_turn_panel;
	JButton btn_map1,btn_map2,btn_map3,btn_map4,btn_map5,btn_start_game,btn_back,btn_home;
	JLabel lbl_number_of_turns,lbl_number_of_games,lbl_tournament_mode;
	JTextField tf_player1,tf_player2,tf_player3,tf_player4,tf_turn_value;
	JComboBox cb_player1,cb_player2,cb_player3,cb_player4,cb_games;
	String[] types = {"Human","Aggressive","Benevolent","Random","Cheater"};
	String[] games = {"1","2","3","4","5"};
	String browse_file_path;
	String file_path = "src/app/team21/risk/maps/";
	MapElements map_elements;

	public void tournamentButton() {
		JPanel test = new JPanel();
		StartGame sg= new StartGame(); 

		test=sg.getPanel();
		
		tournament_master_panel = new JPanel();
		tournament_master_panel.setPreferredSize(new Dimension(600, 300));
		tournament_master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		player_maps_master_panel = new JPanel();
		player_maps_master_panel.setPreferredSize(new Dimension(600, 200));
		player_maps_master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		player_type_panel = new JPanel();
		player_type_panel.setPreferredSize(new Dimension(400, 190));
		player_type_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		maps_panel = new JPanel();
		maps_panel.setPreferredSize(new Dimension(180, 190));
		maps_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		player_maps_master_panel.add(player_type_panel, BorderLayout.WEST);
		player_maps_master_panel.add(maps_panel, BorderLayout.EAST);
		
		number_of_turn_panel = new JPanel();
		number_of_turn_panel.setPreferredSize(new Dimension(600, 60));
		number_of_turn_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		lbl_tournament_mode = new JLabel("Tournament Mode");
		tournament_master_panel.add(lbl_tournament_mode, BorderLayout.PAGE_START);
		tournament_master_panel.add(player_maps_master_panel, BorderLayout.NORTH);
		tournament_master_panel.add(number_of_turn_panel, BorderLayout.SOUTH);
		
		tf_player1 = new JTextField("Player1 Name");
		tf_player2 = new JTextField("Player2 Name");
		tf_player3 = new JTextField("Player3 Name");
		tf_player4 = new JTextField("Player4 Name");
		
		cb_player1 = new JComboBox(types);
		cb_player2 = new JComboBox(types);
		cb_player3 = new JComboBox(types);
		cb_player4 = new JComboBox(types);
		
		player_type_panel.add(tf_player1);
		player_type_panel.add(cb_player1);
		player_type_panel.add(tf_player2);
		player_type_panel.add(cb_player2);
		player_type_panel.add(tf_player3);
		player_type_panel.add(cb_player3);
		player_type_panel.add(tf_player4);
		player_type_panel.add(cb_player4);
		
		btn_map1 = new JButton("Map 1");
		btn_map1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			  	 
	  	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // For Directory
	  	        FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP file", "map");
	  	        fileChooser.setFileFilter(filter);
	  	        fileChooser.setAcceptAllFileFilterUsed(false);
	  	 
	  	        int bopen = fileChooser.showOpenDialog(null); //open the dialog box
	  	        browse_file_path=fileChooser.getSelectedFile().toString();
	  	        System.out.println(browse_file_path);
			}
		});
		btn_map2 = new JButton("Map 2");
		btn_map2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			  	 
	  	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // For Directory
	  	        FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP file", "map");
	  	        fileChooser.setFileFilter(filter);
	  	        fileChooser.setAcceptAllFileFilterUsed(false);
	  	 
	  	        int bopen = fileChooser.showOpenDialog(null); //open the dialog box
	  	        browse_file_path=fileChooser.getSelectedFile().toString();
	  	        System.out.println(browse_file_path);
			}
		});
		btn_map3 = new JButton("Map 3");
		btn_map3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			  	 
	  	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // For Directory
	  	        FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP file", "map");
	  	        fileChooser.setFileFilter(filter);
	  	        fileChooser.setAcceptAllFileFilterUsed(false);
	  	 
	  	        int bopen = fileChooser.showOpenDialog(null); //open the dialog box
	  	        browse_file_path=fileChooser.getSelectedFile().toString();
	  	        System.out.println(browse_file_path);
			}
		});
		btn_map4 = new JButton("Map 4");
		btn_map4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			  	 
	  	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // For Directory
	  	        FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP file", "map");
	  	        fileChooser.setFileFilter(filter);
	  	        fileChooser.setAcceptAllFileFilterUsed(false);
	  	 
	  	        int bopen = fileChooser.showOpenDialog(null); //open the dialog box
	  	        browse_file_path=fileChooser.getSelectedFile().toString();
	  	        System.out.println(browse_file_path);
			}
		});
		btn_map5 = new JButton("Map 5");
		btn_map5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			  	 
	  	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // For Directory
	  	        FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP file", "map");
	  	        fileChooser.setFileFilter(filter);
	  	        fileChooser.setAcceptAllFileFilterUsed(false);
	  	 
	  	        int bopen = fileChooser.showOpenDialog(null); //open the dialog box
	  	        browse_file_path=fileChooser.getSelectedFile().toString();
	  	        System.out.println(browse_file_path);
			}
		});
		
		maps_panel.add(btn_map1);
		maps_panel.add(btn_map2);
		maps_panel.add(btn_map3);
		maps_panel.add(btn_map4);
		maps_panel.add(btn_map5);
		
		lbl_number_of_turns = new JLabel("Enter number of turns");
		tf_turn_value = new JTextField(10);
		lbl_number_of_games = new JLabel("Select number of games");
		cb_games = new JComboBox(games);
		btn_start_game = new JButton("Start game");
		
		number_of_turn_panel.add(lbl_number_of_turns);
		number_of_turn_panel.add(tf_turn_value);
		number_of_turn_panel.add(lbl_number_of_games);
		number_of_turn_panel.add(cb_games);
		number_of_turn_panel.add(btn_start_game);
		
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);	
		jf.add(tournament_master_panel);
		btn_back = new JButton("Back");
		btn_home = new JButton("Home");
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
}
