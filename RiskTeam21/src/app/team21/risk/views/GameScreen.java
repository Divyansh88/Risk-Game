package app.team21.risk.views;

import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.views.StartGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameScreen {
	JButton btn_reinforcement, btn_attack, btn_fortify, btn_continue_rp, btn_ok_fp, btn_end_turn,btn_endturn_ep;
	JLabel lbl_game_history, lbl_select_army, lbl_select_country, lbl_choose_player, lbl_select_from_country,
			lbl_select_to_country, lbl_game_map;
	JTextField txt_armies;
	JPanel master_panel, game_history_panel, mr_panel, turn_panel, second_master_panel, phase_screen_panel,
			action_panel;
	JPanel reinforcement_panel, attack_panel, fortify_panel, mr_master_panel, status_panel,endturn_panel;
	JTextArea text_area, text_area1;
	JScrollPane scroll_panel, scroll_panel1;
	JComboBox combobox_armies, combobox_country,combobox_country2;
	CardLayout cl_ps = new CardLayout();
	JLabel turn_label = new JLabel();
	JLabel status_label = new JLabel();
	MapElements map_elements;
	List<Player> player_list;
	GamePlay game_play;
	Player current_player;
	int turn_value;
	GameScreen view;
	
	int reinforcement_army;
	/**
	 * It is the main game screen
	 */

	public void playerContinueButton(MapElements map_elements, List<Player> player_list, int turn_value) {

		this.map_elements = map_elements;
		this.player_list = player_list;
		this.turn_value = turn_value;
		game_play = new GamePlay();
		String first_print = game_play.distributeCountries(player_list, map_elements.getCountries());
		game_play.setInitialArmies(player_list);
		String second_print = game_play.placeInitialArmiesInRR(player_list);
		String mr = game_play.updateMR(map_elements);

		JPanel test = new JPanel();
		StartGame sg = new StartGame();
		test = sg.getPanel();
		phase_screen_panel = new JPanel();
		reinforcement_panel = new JPanel();
		attack_panel = new JPanel();
		fortify_panel = new JPanel();
		endturn_panel = new JPanel();
		
		phase_screen_panel.setLayout(cl_ps);
		phase_screen_panel.add(reinforcement_panel, "rp");
		phase_screen_panel.add(attack_panel, "ap");
		phase_screen_panel.add(fortify_panel, "fp");
		phase_screen_panel.add(endturn_panel, "ep");

		master_panel = new JPanel();
		master_panel.setPreferredSize(new Dimension(600, 600));
		master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		game_history_panel = new JPanel();
		game_history_panel.setPreferredSize(new Dimension(400, 600));
		game_history_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		text_area = new JTextArea(35, 34);
		scroll_panel = new JScrollPane(text_area);
		lbl_game_map = new JLabel("Game Map");
		scroll_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_panel.setLocation(0, 0);
		text_area.setText(mr);

		text_area1 = new JTextArea(20, 50);
		text_area1.setEditable(false);
		scroll_panel1 = new JScrollPane(text_area1);
		lbl_game_history = new JLabel("Game Map");
		scroll_panel1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		text_area1.setText(first_print + second_print+"\n\n");

		turn_panel = new JPanel();
		turn_panel.add(turn_label);
		turn_panel.setPreferredSize(new Dimension(600, 30));
		turn_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		second_master_panel = new JPanel();
		second_master_panel.setPreferredSize(new Dimension(600, 150));
		second_master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		status_panel = new JPanel();
		status_panel.add(status_label);
		status_panel.setPreferredSize(new Dimension(600, 30));
		status_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		mr_master_panel = new JPanel();
		mr_master_panel.setPreferredSize(new Dimension(600, 350));
		mr_master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		mr_panel = new JPanel();
		mr_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		mr_panel.add(scroll_panel1);

		phase_screen_panel.setPreferredSize(new Dimension(440, 260));
		phase_screen_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		action_panel = new JPanel();
		action_panel.setPreferredSize(new Dimension(130, 260));
		action_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		second_master_panel.add(phase_screen_panel);
		second_master_panel.add(action_panel);

		btn_reinforcement = new JButton("Reinforcement");
		btn_reinforcement.setSize(100, 100);
		btn_reinforcement.setVisible(true);
		btn_attack = new JButton("Attack");
		btn_attack.setPreferredSize(btn_reinforcement.getPreferredSize());
		btn_attack.setVisible(true);
		btn_fortify = new JButton("Fortify");
		btn_fortify.setPreferredSize(btn_reinforcement.getPreferredSize());
		btn_fortify.setVisible(true);
		btn_end_turn = new JButton("End Turn");
		btn_end_turn.setPreferredSize(btn_reinforcement.getPreferredSize());
		btn_end_turn.setVisible(true);

		test.add(master_panel, BorderLayout.WEST);
		test.add(game_history_panel, BorderLayout.EAST);
		master_panel.add(turn_panel, BorderLayout.PAGE_START);
		master_panel.add(second_master_panel, BorderLayout.NORTH);
		master_panel.add(status_panel, BorderLayout.CENTER);
		mr_master_panel.add(lbl_game_history, BorderLayout.PAGE_START);
		mr_master_panel.add(mr_panel, BorderLayout.PAGE_END);
		master_panel.add(mr_master_panel, BorderLayout.SOUTH);

		second_master_panel.add(phase_screen_panel, BorderLayout.WEST);
		second_master_panel.add(action_panel, BorderLayout.EAST);
		game_history_panel.add(lbl_game_history, BorderLayout.NORTH);
		game_history_panel.add(scroll_panel, BorderLayout.SOUTH);
		action_panel.add(btn_reinforcement);
		action_panel.add(btn_attack);
		action_panel.add(btn_fortify);
		action_panel.add(btn_end_turn);

		btn_reinforcement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current_player.isCanReinforce())
					ReinforcementButton(reinforcement_army,current_player,map_elements);
				else
					
					status_label.setText("\nSorry. You cannot reinforce right now.");
			}
		});

		btn_attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current_player.isCanAttack())
					AttackButton();
				else
					status_label.setText("\nSorry. You cannot attack right now. Feature Coming Soon.");
			}
		});

		btn_fortify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current_player.isCanFortify())
					FortifyButton(current_player,map_elements);
				else
					status_label.setText("\nSorry. You cannot fortify right now.");
			}
		});
		
		btn_end_turn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if can endturn call method
				if(current_player.isCanEndTurn())
					EndTurnButton();
				else
					status_label.setText("\nSorry. You cannot End Turn right now.");
			}
		});
		
		JFrame jf = new JFrame();
		jf = (JFrame) sg.getFrame();
		jf.add(test);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

		current_player = game_play.getCurrentPlayer(player_list, turn_value);
		view=this;
		game_play.startTurn(current_player, player_list, map_elements,view);

	}

	public void ReinforcementButton(int reinforce_armies,Player current_player, MapElements map_elements) {
		this.current_player=current_player;
		System.out.println(current_player.getName().toString());
		turn_label.setText("Its"+current_player.getName()+"'s turn and Reinforcement phase.");
		
		reinforcement_panel.removeAll();
		cl_ps.show(phase_screen_panel, "rp");
		
		
		lbl_select_army = new JLabel("Select number of armies");
		reinforcement_panel.add(lbl_select_army);
		
		combobox_armies = new JComboBox<>();
		for (int i = 1; i <= reinforce_armies; i++) 
			combobox_armies.addItem(i);
		reinforcement_panel.add(combobox_armies);

		lbl_select_country = new JLabel("Select country");
		reinforcement_panel.add(lbl_select_country);
		combobox_country = new JComboBox();
		combobox_country=bindCountryCombobox(combobox_country,current_player);
		reinforcement_panel.add(combobox_country);
		
		btn_continue_rp = new JButton("Continue");
		reinforcement_panel.add(btn_continue_rp);
		reinforcement_panel.revalidate();
		reinforcement_panel.repaint();

		btn_continue_rp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean reinforce_successful=false;
				if(current_player.isCanReinforce()){
					int armies_selected=Integer.valueOf(combobox_armies.getSelectedItem().toString());
					System.out.println("Armies Selected "+armies_selected);
					
					Country selected_country=null;
						// selected items -> tryreinforce
					for(Country c:map_elements.getCountries()){
						if(c.getCountryName().equals(combobox_country.getSelectedItem().toString())){
							selected_country=c;
							break;
						}
					}
					System.out.println("Country Selected "+selected_country.getCountryName());
				
					if(armies_selected<=current_player.getReinforceArmies()){
						selected_country.addArmy(armies_selected);
						current_player.subReinforceArmies(armies_selected);
						reinforce_successful=true;
						text_area1.append("\n"+current_player.getName()+" reinforces "+ selected_country.getCountryName()+" with "+armies_selected+" armies.");
						text_area.setText(game_play.updateMR(map_elements));
					}
					else{
						
						status_label.setText("Not Enough Armies");
					}
				}
				
				if(current_player.getReinforceArmies()>0&&reinforce_successful){
					ReinforcementButton(current_player.getReinforceArmies(),current_player,map_elements);
				}
				else{
					status_label.setText("Reinforcement Phase Completed");
					current_player.setCanReinforce(false);
					current_player.setCanFortify(true);
					current_player.setCanEndTurn(true);
					FortifyButton(current_player,map_elements);
				}
			}
		});
		
		
	}
	

	public void AttackButton() {
		attack_panel.removeAll();
		
		
		cl_ps.show(phase_screen_panel, "ap");
		lbl_choose_player = new JLabel("Coming soon");
		attack_panel.add(lbl_choose_player);
		attack_panel.revalidate();
		attack_panel.repaint();
	}

	public void FortifyButton(Player current_player, MapElements map_elements) {
		fortify_panel.removeAll();
		turn_label.setText("Its"+current_player.getName()+"'s turn and Fortification phase.");
		
		cl_ps.show(phase_screen_panel, "fp");
		
		lbl_select_from_country = new JLabel("Fortify from");
		fortify_panel.add(lbl_select_from_country);
		combobox_country = new JComboBox();
		combobox_country=bindCountryCombobox(combobox_country,current_player);
		fortify_panel.add(combobox_country);
		
		lbl_select_to_country = new JLabel("Fortify to");
		fortify_panel.add(lbl_select_to_country);
		combobox_country2= new JComboBox();
		combobox_country2=bindCountryCombobox(combobox_country2,current_player);
		fortify_panel.add(combobox_country2);
		
		lbl_select_army = new JLabel("Type number of armies you want to send");
		fortify_panel.add(lbl_select_army);
		txt_armies=new JTextField(10);
		txt_armies.setToolTipText("Enter an integer value");
		fortify_panel.add(txt_armies);

		btn_ok_fp = new JButton("Try Fortify");
		fortify_panel.add(btn_ok_fp);
		
		fortify_panel.revalidate();
		fortify_panel.repaint();

		btn_ok_fp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				boolean fortify_successful=false;
				if(current_player.isCanFortify()){
					
					String s=txt_armies.getText().toString().trim();
					
					if(s != null && s.matches("^[0-9]*$")&&!s.equals("")){
					
						int armies=Integer.valueOf(s);
						Country selected_from=null,selected_to=null;
						
						for(Country c:map_elements.getCountries()){
							if(c.getCountryName().equals(combobox_country.getSelectedItem().toString())){
								selected_from=c;
								break;
							}
						}
						
						for(Country c:map_elements.getCountries()){
							if(c.getCountryName().equals(combobox_country2.getSelectedItem().toString())){
								selected_to=c;
								break;
							}
						}
						fortify_successful=GamePlay.tryFortify(current_player, selected_from, selected_to, armies);
						

						if(fortify_successful){
							current_player.setCanFortify(false);
							status_label.setText("\nFortify Successful. You can End your Turn Now.");
							EndTurnButton();
						}
						else{
							status_label.setText("\nFortify Unsuccessful. Enter valid armies or Select Valid Country to Fortify.");
							FortifyButton(current_player, map_elements);
						}
								
					}
					else{
						status_label.setText("\nPlease Enter a valid positive integer.");
					}
				}
				
			}
		});

	}
	
	public void EndTurnButton() {
		endturn_panel.removeAll();
		
		cl_ps.show(phase_screen_panel, "ep");
		
		lbl_choose_player = new JLabel("Click 'End My Turn' Button to end your turn when you feel like ");
		endturn_panel.add(lbl_choose_player);
		
		btn_endturn_ep = new JButton("End My Turn");
		endturn_panel.add(btn_endturn_ep);
		
		endturn_panel.revalidate();
		endturn_panel.repaint();
		
		btn_endturn_ep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turn_value=game_play.endTurn(current_player, player_list);
				current_player=game_play.getCurrentPlayer(player_list, turn_value);
				game_play.startTurn(current_player, player_list, map_elements,view);
			}
		});
		
	}


	public JComboBox bindCountryCombobox(JComboBox combobox_country, Player current_player) {

		for (Country c : current_player.getAssignedCountries()) {
			combobox_country.addItem(c.getCountryName());
		}
		return combobox_country;
	}
}
