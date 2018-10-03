package app.team21.risk.gamemodule;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameScreen {
	
	JFrame frame = new JFrame("Risk");
	JTextField txt_p1,txt_p2;
	JPanel mainpanel,panel,panel1,panel2,panel3,panel4;
	JButton play_button,map_button,quit_button,rules_button,btn_browse,btn_map_continue,btn_player_conitnue;
	JButton edit_map,create_map;
	JLabel lbl_choose_map,lbl_or,lbl_choose_player,lbl_p1,lbl_p2,lbl_OR;
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
 	    		public void actionPerformed(ActionEvent e) {
 	    		MapEditor();
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
	    	public void actionPerformed(ActionEvent e) {
	    		PlayerConitnueButton();
	    	}
	    });
		
	}
	public void PlayerConitnueButton(){
		
		//code to direct it to gamescreen
		
	}
	
	public void MapEditor(){
		cl.show(mainpanel, "4");
		JPanel mr_panel = new JPanel();
		mr_panel.setPreferredSize(new Dimension(600,600));
		mr_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel buttons = new JPanel();
		buttons.setPreferredSize(new Dimension(200,600));
		buttons.setBorder(BorderFactory.createLineBorder(Color.black));
		panel4.add(mr_panel,BorderLayout.WEST);
		panel4.add(buttons,BorderLayout.EAST);
		
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
					MapEditor();
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
    FlowLayout flowLayout = (FlowLayout) panel1.getLayout();
    flowLayout.setAlignOnBaseline(true);
    
    mainpanel.add(panel1,"1");
    mainpanel.add(panel2,"2");
    mainpanel.add(panel3,"3");
    mainpanel.add(panel4,"4");
    
    
}

	public static void main( String args[]){
		new GameScreen();
	}
}