package app.team21.risk.gamemodule;
 
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameScreen {
	
	JFrame frame = new JFrame("Risk");
	JTextField txt_p1,txt_p2,txt_p3,txt_p4,txt_p5,txt_map_name,txt_author_name;
	JPanel mainpanel,panel,panel1,panel2,panel3,panel4,panel5,panel6,phase_screen_panel,reinforcement_panel,attack_panel,fortify_panel,player_panel,player_name_panel,player_conitnue_panel,player_select_panel,main_player_panel,master_panel,game_history_panel  ;
	JPanel mr_panel,turn_panel,second_master_panel,action_panel,buttons_panel,cards_panel,save_btn_panel;
	JButton play_button,map_button,quit_button,rules_button,btn_browse,btn_map_continue,btn_player_conitnue,btn_selectmap_conitnue,btn_add_continent,btn_add_country,btn_add_neighbour,btn_save,btn_continue_rp;
	JButton edit_map,create_map,btn_ok_fp,btn_player_ok,btn_reinforcement,btn_attack,btn_fortify;
	JLabel lbl_choose_map,lbl_or,lbl_choose_player,lbl_p1,lbl_p2,lbl_p3,lbl_p4,lbl_p5,lbl_OR,lbl_map_name,lbl_author_name,lbl_game_history,lbl_select_army,lbl_select_country;
	JLabel lbl_select_from_country,lbl_select_to_country;
	JTextArea text_area;
	JScrollPane scroll_panel;
	static List<JLabel> listOfLabels = new ArrayList<JLabel>();
    static List<JTextField> listOfTextFields = new ArrayList<JTextField>();
	
	private CardLayout cl = new CardLayout();
	private CardLayout cl_ps = new CardLayout();
	private CardLayout cl_p = new CardLayout();
	
	JComboBox combobox_map,combobox_player,combobox_armies,combobox_country;
	String [] maps = {"map1","map2","map3"};//temp
	Integer [] armies = new Integer[100];
	Integer [] players = {2,3,4,5};//temp
	String [] country = {"India","USA","China"};
	int reinforcement_army = 4;//temp
	int number_of_player;
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
		main_player_panel = new JPanel();
		main_player_panel.setPreferredSize(new Dimension(600, 600));
		main_player_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		player_panel = new JPanel();
		player_panel.setPreferredSize(new Dimension(600,100));
		player_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		player_name_panel.setPreferredSize(new Dimension(600,380));
		player_name_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		player_conitnue_panel = new JPanel();
		player_conitnue_panel.setPreferredSize(new Dimension(600,100));
		player_conitnue_panel.setBorder(BorderFactory.createLineBorder(Color.black));		
		lbl_choose_player = new JLabel("Choose number of Players");
		player_panel.add(lbl_choose_player);
 		combobox_player = new JComboBox(players);
 		player_panel.add(combobox_player);
 	    
 	    btn_player_ok=new JButton("Ok");
 	    player_panel.add(btn_player_ok);
 	    
 	    btn_player_ok.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		PlayerSelectButton();
	    	}
	    });
		
		btn_player_conitnue = new JButton("Continue");
		player_conitnue_panel.add(btn_player_conitnue);
		btn_player_conitnue.addActionListener(new ActionListener() {
	    	/* (non-Javadoc)
	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    	 * It is a click event which will redirect to main game screen
	    	 */
	    	public void actionPerformed(ActionEvent e) {
	    		PlayerConitnueButton();
	    	}
	    });
		
		panel2.add(main_player_panel);
		main_player_panel.add(player_panel,BorderLayout.NORTH);
		main_player_panel.add(player_name_panel,BorderLayout.CENTER);
		main_player_panel.add(player_conitnue_panel,BorderLayout.SOUTH);		
	}
	
	public void PlayerSelectButton() {
		System.out.println("hi");
		
		JLabel lbl_choose = new JLabel("Coming soon");
		player_select_panel.add(lbl_choose);
		cl_p.show(player_name_panel, "p");
		System.out.println("bye");
	}
	
	/**
	 * It is the main game screen 
	 */
	public void PlayerConitnueButton(){
		cl.show(mainpanel, "6");
		master_panel = new JPanel();
		master_panel.setPreferredSize(new Dimension(600,600));
		master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		game_history_panel = new JPanel();
		game_history_panel.setPreferredSize(new Dimension(400,600));
		game_history_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		text_area = new JTextArea(35,34);	
		scroll_panel = new JScrollPane(text_area);
		lbl_game_history = new JLabel("Game History");
		scroll_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		mr_panel = new JPanel();
		mr_panel.setPreferredSize(new Dimension(600,200));
		mr_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		turn_panel = new JPanel();
		turn_panel.setPreferredSize(new Dimension(600,100));
		turn_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		second_master_panel = new JPanel();
		second_master_panel.setPreferredSize(new Dimension(600,280));
		second_master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		phase_screen_panel.setPreferredSize(new Dimension(440,260));
		phase_screen_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		action_panel = new JPanel();
		action_panel.setPreferredSize(new Dimension(130,260));
		action_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		second_master_panel.add(phase_screen_panel);
		second_master_panel.add(action_panel);
		
		btn_reinforcement = new JButton("Reinforcement");
		btn_reinforcement.setSize(100,100);
		btn_reinforcement.setVisible(true);
		btn_attack = new JButton("Attack");
		btn_attack.setPreferredSize(btn_reinforcement.getPreferredSize());
		btn_attack.setVisible(true);
		btn_fortify = new JButton("Fortify");
		btn_fortify.setPreferredSize(btn_reinforcement.getPreferredSize());
		btn_fortify.setVisible(true);
		
		
		
		/*btn_reinforcement.setAlignmentX(btn_reinforcement.CENTER_ALIGNMENT);
		btn_attack.setAlignmentX(btn_attack.CENTER_ALIGNMENT);
		btn_fortify.setAlignmentX(btn_fortify.CENTER_ALIGNMENT);
		
		action_panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		action_panel.setLayout( new GridBagLayout() ); 
		action_panel.add(btn_reinforcement, new GridBagConstraints());
		action_panel.add(btn_attack, new GridBagConstraints());
		action_panel.add(btn_fortify, new GridBagConstraints());*/
		
		
		
		panel6.add(master_panel,BorderLayout.WEST);
		panel6.add(game_history_panel,BorderLayout.EAST);
		master_panel.add(mr_panel,BorderLayout.NORTH);
		master_panel.add(turn_panel,BorderLayout.CENTER);
		master_panel.add(second_master_panel,BorderLayout.SOUTH);
		second_master_panel.add(phase_screen_panel,BorderLayout.WEST);
		second_master_panel.add(action_panel,BorderLayout.EAST);
		game_history_panel.add(lbl_game_history,BorderLayout.NORTH);
		game_history_panel.add(scroll_panel,BorderLayout.SOUTH);
		action_panel.add(btn_reinforcement);
		action_panel.add(btn_attack);
		action_panel.add(btn_fortify);
		
		btn_reinforcement.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ReinforcementButton(reinforcement_army);
	    	}
	    });
		
		btn_attack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		AttackButton();
	    	}
	    });
		
		btn_fortify.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		FortifyButton();
	    	}
	    });
	}
	
	
	public void ReinforcementButton(int ps) {
		cl_ps.show(phase_screen_panel,"rp");
		for(int i=0;i<4;i++) {
			armies[i]=i+1;
			//System.out.println("armies " + armies[i]);
		}
		lbl_select_army = new JLabel("Select number of armies");
		reinforcement_panel.add(lbl_select_army);
 		combobox_armies = new JComboBox<>(armies);
 		reinforcement_panel.add(combobox_armies);
 		lbl_select_country = new JLabel("Select country");
 		reinforcement_panel.add(lbl_select_country);
 		combobox_country = new JComboBox(country);
 		reinforcement_panel.add(combobox_country);
 		btn_continue_rp = new JButton("Continue");
 		reinforcement_panel.add(btn_continue_rp); 
	}
	
	public void AttackButton() {
		cl_ps.show(phase_screen_panel, "ap");
		lbl_choose_player = new JLabel("Coming soon");
		attack_panel.add(lbl_choose_player);
	}
	
	public void FortifyButton() {
		cl_ps.show(phase_screen_panel,"fp");
		for(int i=0;i<4;i++) {
			armies[i]=i+1;
			//System.out.println("armies " + armies[i]);
		}
		lbl_select_from_country = new JLabel("Fortify from");
		fortify_panel.add(lbl_select_from_country);
		combobox_country = new JComboBox(country);
		fortify_panel.add(combobox_country);
		lbl_select_army = new JLabel("Select number of armies");
		fortify_panel.add(lbl_select_army);
 		combobox_armies = new JComboBox<>(armies);
 		fortify_panel.add(combobox_armies);
 		lbl_select_to_country = new JLabel("Fortify to");
 		fortify_panel.add(lbl_select_to_country);
 		combobox_country = new JComboBox(country);
 		fortify_panel.add(combobox_country);
 		btn_ok_fp = new JButton("Ok");
 		fortify_panel.add(btn_ok_fp); 
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
		mr_panel = new JPanel();
		mr_panel.setPreferredSize(new Dimension(600,600));
		mr_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		master_panel = new JPanel();
		master_panel.setPreferredSize(new Dimension(600,600));
		master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		buttons_panel = new JPanel();
		buttons_panel.setPreferredSize(new Dimension(600,100));
		buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		cards_panel = new JPanel();
		cards_panel.setPreferredSize(new Dimension(600,380));
		cards_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		save_btn_panel = new JPanel();
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
		
		phase_screen_panel = new JPanel();
		phase_screen_panel.setLayout(cl_ps);
		player_name_panel = new JPanel();
		player_name_panel.setLayout(cl_p);
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
						SelectMap();
					}
				});
			}
	});
	
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //set buttons vertically	    
	    
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
    reinforcement_panel = new JPanel();
    attack_panel = new JPanel();
    fortify_panel = new JPanel();
    player_select_panel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) panel1.getLayout();
    flowLayout.setAlignOnBaseline(true);
    
    mainpanel.add(panel1,"1");
    mainpanel.add(panel2,"2");
    mainpanel.add(panel3,"3");
    mainpanel.add(panel4,"4");
    mainpanel.add(panel5,"5");
    mainpanel.add(panel6,"6");
    phase_screen_panel.add(reinforcement_panel,"rp");
    phase_screen_panel.add(attack_panel,"ap");
    phase_screen_panel.add(fortify_panel,"fp");
    player_name_panel.add(player_select_panel,"p");
    
}
 	public static void main( String args[]){
		new GameScreen();
	}
} 