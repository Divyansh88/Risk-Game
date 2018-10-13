package app.team21.risk.gamemodule;
 
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.*;

public class GameScreen {
	
	JFrame frame = new JFrame("Risk");
	JTextField txt_p1,txt_p2,txt_map_name,txt_author_name;
	JPanel mainpanel,panel,panel1,panel2,panel3,panel4,panel5,panel6;
	JButton play_button,map_button,quit_button,rules_button,btn_browse,btn_map_continue,btn_player_conitnue,btn_selectmap_conitnue,btn_add_continent,btn_add_country,btn_add_neighbour,btn_save;
	JButton edit_map,create_map;
	JLabel lbl_choose_map,lbl_or,lbl_choose_player,lbl_p1,lbl_p2,lbl_OR,lbl_map_name,lbl_author_name;
	JLabel test=new JLabel("test");
	JLabel Test=new JLabel("Test");
	
	private CardLayout cl = new CardLayout();
	private JComboBox combobox_map,combobox_player;
	String [] maps = {"map1","map2","map3"};//temp
	String [] players = {"1","2","3","4","5"};//temp
 	public GameScreen(){
	 
	createScreen();
	
	}
 	
	public void PlayButton(int a){
		 
		cl.show(mainpanel,"1");
 		lbl_choose_map = new JLabel("Choose Map");
 		panel1.add(lbl_choose_map);
 		combobox_map = new JComboBox(maps);
 	    panel1.add(combobox_map);
 	    lbl_or= new JLabel("OR");
 	    panel1.add(lbl_or);
 	    btn_browse = new JButton("Browse");//add open file code to it's click event
 	    panel1.add(btn_browse);
 	    btn_map_continue=new JButton("Continue");
 	    panel1.add(btn_map_continue);
 	    if(a==1){
 	    	btn_map_continue.addActionListener(new ActionListener() {
 	    		public void actionPerformed(ActionEvent e) {
 	    			MapContinueButton();
 	    		}
 	    	});
 	    }
 	    else if(a==0){
 	    	btn_map_continue.addActionListener(new ActionListener() {
 	    	/* (non-Javadoc)
 	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 	    	 * It is a click event which will redirect to select map and edit it
 	    	 */
 	    	public void actionPerformed(ActionEvent e) {
 	    			SelectMap();
 	    		}
 	    	});
 	    }
	 }
	
	public void MapContinueButton(){
		
		cl.show(mainpanel, "2");
		lbl_choose_player = new JLabel("Choose number of Players");
 		panel2.add(lbl_choose_player);
 		combobox_player = new JComboBox(players);
 	    panel2.add(combobox_player);
 	    lbl_p1= new JLabel("Player 1");
 	    panel2.add(lbl_p1);
 	    txt_p1 = new JTextField(15);
	    panel2.add(txt_p1);
 	    lbl_p2= new JLabel("Player 2");
	    panel2.add(lbl_p2);
 	    txt_p2 = new JTextField(15);
 	    panel2.add(txt_p2);
 	    btn_player_conitnue=new JButton("Continue");
 	    panel2.add(btn_player_conitnue);
 	    btn_player_conitnue.addActionListener(new ActionListener() {
	    	/* (non-Javadoc)
	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    	 * It is a click event which will redirect to main game screen
	    	 */
	    	public void actionPerformed(ActionEvent e) {
	    		PlayerConitnueButton();
	    	}
	    });
		
	}
	/**
	 * It is the main game screen 
	 */
	public void PlayerConitnueButton(){
		cl.show(mainpanel, "6");
		JPanel master_panel = new JPanel();
		master_panel.setPreferredSize(new Dimension(600,600));
		master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel game_history_panel = new JPanel();
		game_history_panel.setPreferredSize(new Dimension(400,600));
		game_history_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		JPanel mr_panel = new JPanel();
		mr_panel.setPreferredSize(new Dimension(600,200));
		mr_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel turn_panel = new JPanel();
		turn_panel.setPreferredSize(new Dimension(600,100));
		turn_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel second_master_panel = new JPanel();
		second_master_panel.setPreferredSize(new Dimension(600,280));
		second_master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel phase_screen_panel = new JPanel();
		phase_screen_panel.setPreferredSize(new Dimension(280,280));
		phase_screen_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel action_panel = new JPanel();
		action_panel.setPreferredSize(new Dimension(280,280));
		action_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		second_master_panel.add(phase_screen_panel);
		second_master_panel.add(action_panel);
		
		panel6.add(master_panel,BorderLayout.WEST);
		panel6.add(game_history_panel,BorderLayout.EAST);
		master_panel.add(mr_panel,BorderLayout.NORTH);
		master_panel.add(turn_panel,BorderLayout.CENTER);
		master_panel.add(second_master_panel,BorderLayout.SOUTH);
		second_master_panel.add(phase_screen_panel,BorderLayout.WEST);
		second_master_panel.add(action_panel,BorderLayout.EAST);
	}
	
	
	/**
	 * It will ask the player name and author name 
	 * It doesn't return any value and no parameter is passed 
	 */
	public void SelectMap() {
		cl.show(mainpanel, "5");
 	    lbl_map_name = new JLabel("Enter Map Name");
 	    panel5.add(lbl_map_name);
 	    txt_map_name = new JTextField(15);
	    panel5.add(txt_map_name);
 	    lbl_author_name = new JLabel("Enter Author Name");
	    panel5.add(lbl_author_name);
 	    txt_author_name = new JTextField(15);
 	    panel5.add(txt_author_name);
 	    btn_selectmap_conitnue = new JButton("Continue");
 	    panel5.add(btn_selectmap_conitnue);
 	    btn_selectmap_conitnue.addActionListener(new ActionListener() {
	    	/* (non-Javadoc)
	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    	 * It is a click event which will redirect to add continent, country and neighbour and shows map representation
	    	 */
	    	public void actionPerformed(ActionEvent e) {
	    		SelectMapConitnueButton();
	    	}
	    });
	}
	
	/**
	 * It will show map representation and user can also edit the map
	 */
	public void SelectMapConitnueButton(){
		cl.show(mainpanel, "4");
		JPanel mr_panel = new JPanel();
		mr_panel.setPreferredSize(new Dimension(600,600));
		mr_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel master_panel = new JPanel();
		master_panel.setPreferredSize(new Dimension(600,600));
		master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel buttons_panel = new JPanel();
		buttons_panel.setPreferredSize(new Dimension(600,100));
		buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel cards_panel = new JPanel();
		cards_panel.setPreferredSize(new Dimension(600,380));
		cards_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel save_btn_panel = new JPanel();
		save_btn_panel.setPreferredSize(new Dimension(600,100));
		save_btn_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		master_panel.add(buttons_panel);
		master_panel.add(save_btn_panel);
		master_panel.add(cards_panel);
		
		btn_add_continent = new JButton("Add Continent");
		btn_add_country = new JButton("Add Country");
		btn_add_neighbour = new JButton("Add Neighbour");
		btn_save = new JButton("Save");
		buttons_panel.add(btn_add_continent);
		buttons_panel.add(btn_add_country);
		buttons_panel.add(btn_add_neighbour);	
		save_btn_panel.add(btn_save);
		
		panel4.add(mr_panel,BorderLayout.WEST);
		panel4.add(master_panel,BorderLayout.EAST);
		master_panel.add(buttons_panel,BorderLayout.NORTH);
		master_panel.add(cards_panel,BorderLayout.CENTER);
		master_panel.add(save_btn_panel,BorderLayout.SOUTH);
	}
	
 	public void createScreen(){
	
	
		frame.setSize(400,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		
		mainpanel= new JPanel();
		mainpanel.setLayout(cl);
		panel = new JPanel();
		map_button = new JButton("Map");
		map_button.setSize(100,100);
		map_button.setVisible(true);
		map_button.setAlignmentX(map_button.CENTER_ALIGNMENT);
		
		map_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl.show(mainpanel, "3");
		 	    edit_map = new JButton("Edit an existing Map");
		 	    panel3.add(edit_map);
		 	    
		 	    edit_map.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PlayButton(0);//temp almost only continue button should direct to map editing screen
						
					}
				});
		 	    
		 	    lbl_OR=new JLabel("OR");
		 	    panel3.add(lbl_OR);
		 	    create_map = new JButton("Create a new Map");
		 	    panel3.add(create_map);
		 	    create_map.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//MapEditor();
						SelectMap();
					}
				});
			}
	});
	
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //set buttons vertically
	    //mainpanel.add(panel2,"2");
	    //panel.add(panel3,"3");
	    
	    //frame.getContentPane().add(panel);
	    
	    
	    play_button = new JButton("Play");
	    play_button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		PlayButton(1);
	    		
	    	}
	    });
	    play_button.setSize(100,100);
	    play_button.setVisible(true);
	    
	    //sets the the vertical alignment
	    play_button.setAlignmentX(play_button.CENTER_ALIGNMENT);
	    panel.add(play_button);
	    panel.add(Box.createRigidArea(new Dimension(0,10))); //add space between buttons
	    panel.add(map_button);
	    panel.add(Box.createRigidArea(new Dimension(0,10)));
	    mainpanel.add(panel, "0");
	    
	    quit_button = new JButton("Quit");
	    quit_button.setSize(100,100);
	    quit_button.setVisible(true);
	    panel.add(quit_button);
	    quit_button.setAlignmentX(quit_button.CENTER_ALIGNMENT);
	    
	    quit_button.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    });
	    
	    
	    rules_button = new JButton("Rules");
	    rules_button.setSize(100,100);
	    rules_button.setVisible(true);
	    Component rigidArea = Box.createRigidArea(new Dimension(0,10));
	    panel.add(rigidArea);
	    rules_button.setAlignmentX(rules_button.CENTER_ALIGNMENT);
	    panel.add(rules_button);
	    
	    	rules_button.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent a) {
	    			try {
	    				Desktop.getDesktop().open(new java.io.File("Rules_File/Risk_rules.pdf"));
	    			}
	    			catch(Exception e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	});
   				
    //set buttons in center
    frame.getContentPane().setLayout( new GridBagLayout() );
    GridBagConstraints gbc_mainpanel = new GridBagConstraints();
    gbc_mainpanel.gridx = 7;
    gbc_mainpanel.gridy = 6;
    frame.getContentPane().add(mainpanel, gbc_mainpanel);
    panel1 = new JPanel();
    panel2 =new JPanel();
    panel3=new JPanel();
    panel4=new JPanel(new BorderLayout());
    panel5=new JPanel();
    panel6=new JPanel();
    FlowLayout flowLayout = (FlowLayout) panel1.getLayout();
    flowLayout.setAlignOnBaseline(true);
    
    mainpanel.add(panel1,"1");
    mainpanel.add(panel2,"2");
    mainpanel.add(panel3,"3");
    mainpanel.add(panel4,"4");
    mainpanel.add(panel5,"5");
    mainpanel.add(panel6,"6");
    
    
}
 	public static void main( String args[]){
		new GameScreen();
	}
} 