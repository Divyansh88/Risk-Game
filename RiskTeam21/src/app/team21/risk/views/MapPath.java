package app.team21.risk.views;
import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;
import app.team21.risk.gamemodule.GamePlay;
import app.team21.risk.mapmodule.MapEditor;
import app.team21.risk.mapmodule.MapElements;
import app.team21.risk.mapmodule.MapLoader;
import app.team21.risk.views.StartGame;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Last Updated on: 18-10-2018, Thursday 
 * This class file handles all map related screens.
 * 
 * @author Divyansh
 * @version 1.0.0
 */
public class MapPath {
	
	JButton edit_map,create_map,btn_selectmap_conitnue,btn_add_continent,btn_add_country,btn_add_neighbour,btn_save,btn_create_continent,btn_ok;
	JLabel lbl_or,lbl_map_name,lbl_author_name,lbl_alert,lbl_continent_name,lbl_control_value,lbl_state,lbl_country_name,lbl_select_neighbour,lbl_select_continent,lbl_select_country,lbl_game_map;
	JTextField txt_map_name,txt_author_name,txt_control_value,txt_continent_name,txt_country_name;
	JPanel mr_panel,master_panel,buttons_panel,cards_panel,save_btn_panel,card_continent_panel,card_country_panel,card_neighbour_panel,status_panel;
	CardLayout cl = new CardLayout();
	JList<String> combobox_neighbour;
	JTextArea text_area_mr;
	JScrollPane scroll_panel_mr,scroll_pane_neighbours;
	JComboBox combobox_continents,combobox_countries,combobox_neighbours;
	String browse_file_path;
	String file_path = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/";
	MapLoader map_loader = new MapLoader();
	MapElements map_elements;
	JButton btn_back = new JButton("Back"); 
	JButton btn_home = new JButton("Home");
	String short_name,file_name="DEFAULT";
	JComboBox maps = new JComboBox<>();
	
	/**
	 * This method will ask for create a new map or edit an existing map.
	 */
	public void mapButton(){
		
		JPanel test = new JPanel();
		StartGame sg =new StartGame();
		test=sg.getPanel();
		
		edit_map = new JButton("Edit an existing Map");
		test.add(edit_map);
 	    edit_map.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playButton();
			}
		});
 	    
 	    lbl_or=new JLabel("OR");
	    test.add(lbl_or);
	    create_map = new JButton("Create a new Map");
	    test.add(create_map);
	    create_map.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMap();
			}
		});
	    
	    
	    JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
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
	
	
	/**
	 * This method asks for map name and author name of a new map.
	 */
	public void selectMap() {
		
		JPanel test = new JPanel();
		StartGame sg = new StartGame();
		test=sg.getPanel();
		
 	    lbl_map_name = new JLabel("Enter Map Name");
 	    test.add(lbl_map_name);
 	    txt_map_name = new JTextField(15);
	    test.add(txt_map_name);
 	    lbl_author_name = new JLabel("Enter Author Name");
	    test.add(lbl_author_name);
 	    txt_author_name = new JTextField(15);
 	    test.add(txt_author_name);
 	    
 	    btn_selectmap_conitnue = new JButton("Continue");
	    test.add(btn_selectmap_conitnue);
 	    
	    lbl_alert = new JLabel("");
	    test.add(lbl_alert);
 	    btn_selectmap_conitnue.addActionListener(new ActionListener() {
	    	/* (non-Javadoc)
	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    	 * It is a click event which will redirect to add continent, country and neighbour and shows map representation
	    	 */
 	    	
	    	public void actionPerformed(ActionEvent e) {
	    		//SelectMapConitnueButton();

	 	    	if ((txt_map_name.getText().equals("")) || (txt_author_name.getText().equals(""))){
	 	 	    	lbl_alert.setText("PLEASE ENTER TEXT");
	 	 	    }
	 	 	    else {
	 	 	    	HashMap<String, String> map_details=new HashMap<>();
	 	 	    	map_details.put("name", txt_map_name.getText());
	 	 	    	map_details.put("author", txt_author_name.getText());
	 	 	    	map_elements=MapElements.getNewMapInstance(map_details);
	 	 	    	file_name=txt_map_name.getText()+".map";
	 	 	    	selectMapConitnueButton();
	 	 	    }
	    	}
	    });
 	    
 	    JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				mapButton();
				
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
	
	/**
	 * It will show map representation and user can also edit the map.
	 */
	public void selectMapConitnueButton(){
		JPanel test = new JPanel();
		StartGame sg =new StartGame();
		test=sg.getPanel();
		
		lbl_state = new JLabel();
		
		cards_panel = new JPanel();
		card_continent_panel = new JPanel();
		card_country_panel = new JPanel();
		card_neighbour_panel = new JPanel();
		cards_panel.setLayout(cl);
		cards_panel.add(card_continent_panel,"card_continent");
		cards_panel.add(card_country_panel,"card_country");
		cards_panel.add(card_neighbour_panel,"card_neighbour");
		
		mr_panel = new JPanel();
		mr_panel.setPreferredSize(new Dimension(600,600));
		mr_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		lbl_game_map = new JLabel("Map Representation");

		text_area_mr=new JTextArea(35,52);
		text_area_mr.setEditable(false);
		scroll_panel_mr=new JScrollPane(text_area_mr);
		scroll_panel_mr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		text_area_mr.setText(map_elements.updateMR());
		
		master_panel = new JPanel();
		master_panel.setPreferredSize(new Dimension(600,600));
		master_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		buttons_panel = new JPanel();
		buttons_panel.setPreferredSize(new Dimension(600,90));
		buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		cards_panel.setPreferredSize(new Dimension(600,380));
		cards_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		save_btn_panel = new JPanel();
		save_btn_panel.setPreferredSize(new Dimension(600,50));
		save_btn_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		status_panel = new JPanel();
		status_panel.setPreferredSize(new Dimension(600,50));
		status_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		btn_add_continent = new JButton("Add Continent");
		btn_add_country = new JButton("Add Country");
		btn_add_neighbour = new JButton("Add Neighbour");
		buttons_panel.add(btn_add_continent);
		buttons_panel.add(btn_add_country);
		buttons_panel.add(btn_add_neighbour);	
		status_panel.add(lbl_state);
		
		btn_add_continent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				addContinent();

			}
		});
		
		btn_add_country.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(map_elements.getContinentList().size()>0)
					addCountry();				
				else
					lbl_state.setText("Create a continent first.");
			}
		});
		
		btn_add_neighbour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(map_elements.getCountries().size()>1)
					addNeighbour();
				else
					lbl_state.setText("Create more countries.");
			}
		});
		btn_save = new JButton("Save");
		
		save_btn_panel.add(btn_save);
		
		btn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MapEditor me=new MapEditor();
				if(map_elements.getContinentList().size()>0&&map_elements.getCountries().size()>0){
					me.writeMap(map_elements,file_path+file_name);
					lbl_state.setText("Map Saved");
				}
				else{
					lbl_state.setText("Create more elements in the map");
				}
			}
		});
		mr_panel.add(lbl_game_map,BorderLayout.NORTH);
		mr_panel.add(scroll_panel_mr,BorderLayout.CENTER);
		mr_panel.add(status_panel,BorderLayout.SOUTH);
		
		master_panel.add(buttons_panel,BorderLayout.NORTH);
		master_panel.add(cards_panel,BorderLayout.CENTER);
		
		master_panel.add(status_panel,BorderLayout.SOUTH);
		master_panel.add(save_btn_panel,BorderLayout.PAGE_END);
		test.add(mr_panel,BorderLayout.WEST);
		test.add(master_panel,BorderLayout.EAST);		
		
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMap();
				
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
	
	/**
	 * This method adds continents into map.
	 */
	void addContinent() {
		
		card_continent_panel.removeAll();
		cl.show(cards_panel, "card_continent");
		lbl_continent_name = new JLabel("Continent name");
		card_continent_panel.add(lbl_continent_name);
		txt_continent_name = new JTextField(15);
		txt_continent_name.setToolTipText("Minimum 1 character");
		card_continent_panel.add(txt_continent_name);

 		lbl_control_value = new JLabel("Continent control value");
 		card_continent_panel.add(lbl_control_value);
 		txt_control_value = new JTextField(15);
 		txt_control_value.setToolTipText("Please enter integer value greater than 0.");
		card_continent_panel.add(txt_control_value);

 		btn_create_continent = new JButton("Create Continent");
 		card_continent_panel.add(btn_create_continent); 
 		btn_create_continent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String continent_name=txt_continent_name.getText().toString().trim();
				String s=txt_control_value.getText().toString().trim();
				if(s != null && s.matches("^[0-9]*$")&&continent_name.length()>0){
					int control_value=Integer.valueOf(s);
					if(control_value>0){
						Continent new_continent=new Continent(continent_name,control_value);
						List<Continent> new_list=map_elements.getContinentList();
						new_list.add(new_continent);
						HashMap<Continent, List<Country>> new_continent_country_map=map_elements.getContinentCountryMap();
						new_continent_country_map.put(new_continent, new ArrayList<>());
						map_elements.setContinentCountryMap(new_continent_country_map);
						map_elements.setContinentList(new_list);
						text_area_mr.setText(map_elements.updateMR());
						lbl_state.setText("Continent Added Successfully");
						addCountry();
					}
					else{
						lbl_state.setText("Control Value must be greater than 0.");
					}
				}
				else{
					lbl_state.setText("Enter numerical value greater than 0.");
				}
			}
		});
 		card_continent_panel.revalidate();
 		card_continent_panel.repaint();
	}
	
	/**
	 * This will add countries into map.
	 */
	void addCountry() {
		
		card_country_panel.removeAll();
		cl.show(cards_panel, "card_country");
		
		lbl_country_name = new JLabel("Country name");
		card_country_panel.add(lbl_country_name);
		txt_country_name = new JTextField(15);
		card_country_panel.add(txt_country_name);
		
		lbl_select_continent = new JLabel("Select continent");
		card_country_panel.add(lbl_select_continent);
 		combobox_continents= new JComboBox();
		combobox_continents = bindContinentCombobox(combobox_continents,map_elements);
 		card_country_panel.add(combobox_continents);
 		
// 		lbl_select_neighbour = new JLabel("Select neighbour to add to this country");
// 		card_country_panel.add(lbl_select_neighbour);
 		
// 		DefaultListModel<String> listModel=getNeighboursModel();
// 		combobox_neighbour=new JList(listModel);
// 		combobox_neighbour.setFixedCellHeight(20);
//		combobox_neighbour.setFixedCellWidth(100);
//		combobox_neighbour.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		combobox_neighbour.setVisibleRowCount(7);
// 		scroll_pane_neighbours=new JScrollPane(combobox_neighbour);
//		scroll_pane_neighbours.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
// 		card_country_panel.add(scroll_pane_neighbours);
	
 		
// 		combobox_neighbours = new JComboBox();
// 		combobox_neighbours = bindCountries(combobox_neighbours, map_elements);
//		card_country_panel.add(combobox_neighbours);
 		
 		btn_add_country = new JButton("Add country");
 		card_country_panel.add(btn_add_country); 
 		
 		btn_add_country.addActionListener(new ActionListener() {
 			
			@Override
			public void actionPerformed(ActionEvent e) {
				String country_name=txt_country_name.getText().toString().trim();
				if(country_name!=null && country_name.length()>0){
					Continent selected_continent=null;
//					Country selected_neighbour=null;//
					for(Continent c:map_elements.getContinentList()){
						if(c.getContinentName().equals(combobox_continents.getSelectedItem().toString())){
							selected_continent=c;
							break;
						}
					}
//					for(Country c:map_elements.getCountries()){//
//						if(c.getCountryName().equals(combobox_neighbours.getSelectedItem().toString())){//
//							selected_neighbour=c;//
//							break;//
//						}//
//					}//
					Country new_country=new Country(country_name,selected_continent.getContinentName());
					
					
					HashMap<Country, List<Country>> new_country_neighbour_map=map_elements.getCountryNeighboursMap();
//					List<Country> new_neighbour_list=new_country_neighbour_map.get(selected_neighbour);//
					
//					new_neighbour_list.add(new_country);//
//					selected_neighbour.setNeighbourNodes(new_neighbour_list);//
//					new_country_neighbour_map.put(selected_neighbour, new_neighbour_list);//
					
					List<Country> new_neighbour_list=new ArrayList<>();
//					new_neighbour_list.add(selected_neighbour);//
					new_country.setNeighbourNodes(new_neighbour_list);
					new_country_neighbour_map.put(new_country, new_neighbour_list);
					
					HashMap<Continent, List<Country>> new_continent_country_map=map_elements.getContinentCountryMap();
					List<Country> new_list=selected_continent.getMemberCountriesList();
					new_list.add(new_country);
					new_continent_country_map.put(selected_continent, new_list);
					
					selected_continent.setMemberCountriesList(new_list);
					map_elements.setContinentCountryMap(new_continent_country_map);
					map_elements.setCountryNeighboursMap(new_country_neighbour_map);
					text_area_mr.setText(map_elements.updateMR());
					lbl_state.setText("Country Added Successfully");
					if(map_elements.getCountries().size()>1)
						addNeighbour(); 
					
				}
				else{
					lbl_state.setText("Enter a valid country name");
				}
				
			}
		});
 		card_country_panel.revalidate();
 		card_country_panel.repaint();
	}
	
	/**
	 * This method will bind country combobox.
	 * 
	 * @param combobox_countries combobox of country
	 * @param map_elements elements of map
	 * @return combobox combobox of country
	 */
	private JComboBox bindCountries(JComboBox combobox_countries,MapElements map_elements) {
		for(Country c:map_elements.getCountries()){
			combobox_countries.addItem(c.getCountryName().toString().trim());
		}
		return combobox_countries;
	}
	
	/**
	 * This method will get neighbours attached to the particular country.
	 * 
	 * @return list of neighbours
	 */
	private DefaultListModel<String> getNeighboursModel() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(Country c:map_elements.getCountries()){
			listModel.addElement(c.getCountryName());
		}		
		return listModel;
	}
	
	/**
	 * This method will bind continent combobox.
	 * 
	 * @param combobox_continent combobox of continent
	 * @param map_elements elements of map
	 * @return combobox combobox of continent
	 */
	public JComboBox bindContinentCombobox(JComboBox combobox_continent,MapElements map_elements) {
		for (Continent c : map_elements.getContinentList()) {
			combobox_continent.addItem(c.getContinentName().toString().trim());
		}
		return combobox_continent;
	}

	/**
	 * This method will add neighbours to a particular country.
	 */
	void addNeighbour() {
		
		card_neighbour_panel.removeAll();
		cl.show(cards_panel, "card_neighbour");

 		lbl_select_country = new JLabel("Select country");;
 		card_neighbour_panel.add(lbl_select_country);
 		combobox_countries = new JComboBox();
 		combobox_countries = bindCountries(combobox_countries, map_elements);
 		card_neighbour_panel.add(combobox_countries);
 		lbl_select_neighbour = new JLabel("Select neighbour");
 		card_neighbour_panel.add(lbl_select_neighbour);
 		combobox_neighbours = new JComboBox();
 		combobox_neighbours = bindCountries(combobox_neighbours, map_elements);
 		card_neighbour_panel.add(combobox_neighbours);
 		
 		btn_ok = new JButton("Add Neighbour");
 		card_neighbour_panel.add(btn_ok); 
 		btn_ok.addActionListener(new ActionListener() {
 			
			@Override
			public void actionPerformed(ActionEvent e) {
				String country=combobox_countries.getSelectedItem().toString().trim();
				String neighbour=combobox_neighbours.getSelectedItem().toString().trim();
				if(!neighbour.equals(country)){
					Country selected_country=null,selected_neighbour = null;
					for(Country c:map_elements.getCountries()){
						if(c.getCountryName().equals(country)){
							selected_country=c;
							break;
						}
					}
					for(Country c:map_elements.getCountries()){
						if(c.getCountryName().equals(neighbour)){
							selected_neighbour=c;
							break;
						}
					}
					System.out.println();
					if(!selected_country.getNeighbourNodes().contains(selected_neighbour)){
						HashMap<Country, List<Country>> new_country_neighbour_map=map_elements.getCountryNeighboursMap();
						
						List<Country> new_neighbour_list=new_country_neighbour_map.get(selected_neighbour);
						new_neighbour_list.add(selected_country);
						selected_neighbour.setNeighbourNodes(new_neighbour_list);
						new_country_neighbour_map.put(selected_neighbour, new_neighbour_list);
						
						new_neighbour_list=new_country_neighbour_map.get(selected_country);
						new_neighbour_list.add(selected_neighbour);
						selected_country.setNeighbourNodes(new_neighbour_list);
						new_country_neighbour_map.put(selected_country, new_neighbour_list);
						
						map_elements.setCountryNeighboursMap(new_country_neighbour_map);
						
						lbl_state.setText("NEIGHBOURS ADDED");
					}
					else{
						lbl_state.setText("They both are already neighbours");
					}
					
					
					
				}
				else{
					lbl_state.setText("Same countries cannot be selected. Choose 2 different countries.");
				}
				
			}
		});
 		card_neighbour_panel.revalidate();
 		card_neighbour_panel.repaint();
	}
	
	/**
	 * This will ask for select from existing maps or browse other maps to play the game.
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
				JFileChooser fileChooser = new JFileChooser();
			  	 
	  	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // For Directory
	  	        FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP file", "map");
	  	        fileChooser.setFileFilter(filter);
	  	        fileChooser.setAcceptAllFileFilterUsed(false);
	  	 
	  	        int bopen = fileChooser.showOpenDialog(null); //open the dialog box
	  	        
	  	      if (bopen == JFileChooser.APPROVE_OPTION) {
	  	        	browse_file_path=fileChooser.getSelectedFile().toString();
	  	        	map_elements=map_loader.readMapFile(browse_file_path);
	  	        	if(map_elements.isCorrectMap()){
	  	        		selectMapConitnueButton();
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
				file_name=selected_map;
				if(selected_map.equals(short_name))
					map_elements=map_loader.readMapFile(browse_file_path);
				else
					map_elements=map_loader.readMapFile(file_path+selected_map);
				
				if(map_elements.isCorrectMap()){
					
					selectMapConitnueButton();
				}
				else{
					//lbl set incorrect map. Could not Load. 
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
		//main_panel.add(choose_player, "choose_player");
		cl.show(main_panel, "choose_map");
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(main_panel);
		jf.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mapButton();
				
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
	
	/**
	 * This method will fill comboboxes.
	 */
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

}
