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
import java.util.Observable;
import java.util.Observer;

/**
 * Last Updated on: 18-10-2018, Thursday This class file handles main game
 * screen.
 * 
 * @author Yash Sheth and Divyansh Thakar and Samip Thakkar
 * @version 1.0.0
 */

public class GameScreen implements Observer{
	JButton btn_reinforcement, btn_attack, btn_fortify, btn_continue_rp, btn_ok_fp, btn_end_turn, btn_endturn_ep;
	JLabel lbl_game_history, lbl_select_army, lbl_select_country, lbl_choose_player, lbl_select_from_country,
	lbl_select_to_country, lbl_game_map, lbl_domination_panel, lbl_map_finder;
	JTextField txt_armies;
	JPanel master_panel, game_history_panel, mr_panel, turn_panel, second_master_panel, phase_screen_panel,
	action_panel;
	JPanel reinforcement_panel, attack_panel, fortify_panel, mr_master_panel, status_panel, endturn_panel,
	domination_master_panel, domination_panel, map_finder_panel, select_country_panel, result_panel;
	JTextArea text_area_game_map, text_area_game_history, text_area_domination, text_area_result,
	text_area_select_country;
	JScrollPane scroll_panel, scroll_panel1, scroll_panel2;
	JComboBox combobox_armies, combobox_country, combobox_country2;
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

	/**
	 * This method will create view of main game screen and updates the value of
	 * different components.
	 * 
	 * @param map_elements
	 *            elements of map
	 * @param player_list
	 *            list of players
	 * @param turn_value
	 *            turn value
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
		domination_master_panel = new JPanel();
		domination_master_panel.setPreferredSize(new Dimension(300, 600));
		domination_master_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		domination_panel = new JPanel();
		domination_panel.setPreferredSize(new Dimension(300, 210));
		domination_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		map_finder_panel = new JPanel();
		map_finder_panel.setPreferredSize(new Dimension(300, 380));
		map_finder_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		lbl_map_finder = new JLabel("Map Finder");
		select_country_panel = new JPanel();
		select_country_panel.setPreferredSize(new Dimension(300, 190));
		select_country_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		result_panel = new JPanel();
		result_panel.setPreferredSize(new Dimension(300, 190));
		result_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		lbl_select_country = new JLabel("Select Country");
		text_area_select_country = new JTextArea(10, 26);
		// text_area_select_country.setEditable(false);
		text_area_result = new JTextArea(10, 26);
		// text_area_result.setEditable(false);
		select_country_panel.add(lbl_select_country);
		select_country_panel.add(text_area_select_country);
		result_panel.add(text_area_result);

		map_finder_panel.add(lbl_map_finder, BorderLayout.NORTH);
		map_finder_panel.add(select_country_panel, BorderLayout.CENTER);
		map_finder_panel.add(result_panel, BorderLayout.SOUTH);

		text_area_domination = new JTextArea(11, 25);
		text_area_domination.setEditable(false);
		scroll_panel2 = new JScrollPane(text_area_domination);
		lbl_domination_panel = new JLabel("Domination");
		scroll_panel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		domination_panel.add(lbl_domination_panel, BorderLayout.NORTH);
		domination_panel.add(scroll_panel2, BorderLayout.SOUTH);

		domination_master_panel.add(domination_panel, BorderLayout.NORTH);
		domination_master_panel.add(map_finder_panel, BorderLayout.SOUTH);

		text_area_game_map = new JTextArea(35, 34);
		text_area_game_map.setEditable(false);
		scroll_panel = new JScrollPane(text_area_game_map);
		lbl_game_map = new JLabel("Game Map");
		scroll_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_panel.setLocation(0, 0);
		text_area_game_map.setText(mr);

		text_area_game_history = new JTextArea(20, 50);
		text_area_game_history.setEditable(false);
		scroll_panel1 = new JScrollPane(text_area_game_history);
		lbl_game_history = new JLabel("Game History");
		scroll_panel1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		text_area_game_history.setText(first_print + second_print + "\n\n");

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
				if (current_player.isCanReinforce())
					ReinforcementButton(reinforcement_army, current_player, map_elements);
				else

					status_label.setText("\nSorry. You cannot reinforce right now.");
			}
		});

		btn_attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current_player.isCanAttack())
					AttackButton();
				else
					status_label.setText("\nSorry. You cannot attack right now. Feature Coming Soon.");
			}
		});

		btn_fortify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current_player.isCanFortify())
					FortifyButton(current_player, map_elements);
				else
					status_label.setText("\nSorry. You cannot fortify right now.");
			}
		});

		btn_end_turn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if can endturn call method
				if (current_player.isCanEndTurn())
					EndTurnButton();
				else
					status_label.setText("\nSorry. You cannot End Turn right now.");
			}
		});

		JFrame jf = new JFrame();
		jf = (JFrame) sg.getFrame();
		jf.add(test);
		jf.add(domination_master_panel);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

		current_player = game_play.getCurrentPlayer(player_list, turn_value);
		current_player.addObserver(this);
		view = this;
		current_player.startTurn(player_list, map_elements, view);
	}

	/**
	 * This method view reinforcement screen in game screen.
	 * 
	 * @param reinforce_armies
	 *            total reinforce armies
	 * @param current_player
	 *            current player
	 * @param map_elements
	 *            elements of map
	 */
	public void ReinforcementButton(int reinforce_armies, Player current_player, MapElements map_elements) {
		this.current_player = current_player;
		
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
		combobox_country = bindCountryCombobox(combobox_country, current_player);
		reinforcement_panel.add(combobox_country);

		btn_continue_rp = new JButton("Continue");
		reinforcement_panel.add(btn_continue_rp);
		reinforcement_panel.revalidate();
		reinforcement_panel.repaint();

		GameScreen game_view = this;
		btn_continue_rp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int armies_selected = Integer.valueOf(combobox_armies.getSelectedItem().toString());
				String country_name = combobox_country.getSelectedItem().toString();
				//current_player.current_player = current_player;
				current_player.playerReinforces(armies_selected, map_elements, country_name, game_view);
			}
		});
	}

	/**
	 * This method view attack screen in game screen. (Coming soon)
	 */
	public void AttackButton() {
		attack_panel.removeAll();

		cl_ps.show(phase_screen_panel, "ap");
		lbl_choose_player = new JLabel("Coming soon");
		attack_panel.add(lbl_choose_player);
		attack_panel.revalidate();
		attack_panel.repaint();
	}

	/**
	 * This method view fortify screen in game screen.
	 * 
	 * @param current_player current player
	 * @param map_elements elements
	 */
	public void FortifyButton(Player current_player, MapElements map_elements) {
		fortify_panel.removeAll();
		
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

		GameScreen game_view=this;
		btn_ok_fp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


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
					
					current_player.playerFortifies(armies, map_elements, selected_from,selected_to, game_view);
				}
				else{
					status_label.setText("\nPlease Enter a valid positive integer.");
				}
			}

		});

	}

	/**
	 * This method will end the turn of a player.
	 */
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
				updateView(current_player.getName()+" ended the turn./n*************************");
				turn_value = GamePlay.endTurn(current_player, player_list);
				current_player = game_play.getCurrentPlayer(player_list, turn_value);
				current_player.addObserver(view);
				current_player.startTurn(player_list, map_elements, view);
			}
		});

	}

	/**
	 * This method will bind country combobox.
	 * 
	 * @param combobox_country
	 *            combobox of country
	 * @param current_player
	 *            current player
	 * @return combobox combobox of country
	 */
	public JComboBox<Object> bindCountryCombobox(JComboBox<Object> combobox_country, Player current_player) {

		for (Country c : current_player.getAssignedCountries()) {
			combobox_country.addItem(c.getCountryName());
		}
		return combobox_country;
	}

	public void updateView(String history_text) {
		text_area_game_history.append(history_text);
		text_area_game_map.setText(GamePlay.updateMR(map_elements));
	}

	public void updateStatus(String status_text) {
		status_label.setText(status_text);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Player player=(Player) arg0;
		turn_label.setText(player.getPhaseDetails());	
	}
}