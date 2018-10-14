package app.team21.risk.views;
import app.team21.risk.elements.Player;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.StartGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameScreen {
	JButton btn_reinforcement,btn_attack,btn_fortify,btn_continue_rp,btn_ok_fp;
	JLabel lbl_game_history,lbl_select_army,lbl_select_country,lbl_choose_player,lbl_select_from_country,lbl_select_to_country;
	JTextField txt_map_name,txt_author_name;
	JPanel master_panel,game_history_panel,mr_panel,turn_panel,second_master_panel,phase_screen_panel,action_panel;
	JPanel reinforcement_panel,attack_panel,fortify_panel;
	JTextArea text_area;
	JScrollPane scroll_panel;
	JComboBox combobox_armies,combobox_country;
	CardLayout cl_ps = new CardLayout();
	
	
	int reinforcement_army = 4;//temp
	Integer [] players = {2,3,4,5};//temp
	String [] country = {"India","USA","China"};
	Integer [] armies = new Integer[100];
	
	/**
	 * It is the main game screen 
	 */
	public void playerContinueButton(MapElements map_elements,List<Player> player_list){

		
		JPanel test = new JPanel();
		StartGame sg =new StartGame();
		test=sg.getPanel();
		phase_screen_panel = new JPanel();
		reinforcement_panel = new JPanel();
		attack_panel = new JPanel();
		fortify_panel = new JPanel();
		phase_screen_panel.setLayout(cl_ps);
	    phase_screen_panel.add(reinforcement_panel,"rp");
	    phase_screen_panel.add(attack_panel,"ap");
	    phase_screen_panel.add(fortify_panel,"fp");
		
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
		
		
		
		test.add(master_panel,BorderLayout.WEST);
		test.add(game_history_panel,BorderLayout.EAST);
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
		
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
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
}
