package app.team21.risk.views;
import app.team21.risk.elements.Continent;
import app.team21.risk.elements.Country;
import app.team21.risk.elements.Player;
import app.team21.risk.gamemodule.GamePlay;
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

public class MapPath {
	
	JButton edit_map,create_map,btn_selectmap_conitnue,btn_add_continent,btn_add_country,btn_add_neighbour,btn_save,btn_create_continent,btn_ok;
	JLabel lbl_or,lbl_map_name,lbl_author_name,lbl_alert,lbl_continent_name,lbl_control_value,lbl_state,lbl_country_name,lbl_select_neighbour,lbl_select_continent,lbl_select_country,lbl_game_map;
	JTextField txt_map_name,txt_author_name,txt_control_value,txt_continent_name,txt_country_name;
	JPanel mr_panel,master_panel,buttons_panel,cards_panel,save_btn_panel,card_continent_panel,card_country_panel,card_neighbour_panel,status_panel;
	CardLayout cl = new CardLayout();
	JList<String> combobox_neighbour;
	JTextArea text_area_mr;
	JScrollPane scroll_panel_mr,scroll_pane_neighbours;
	JComboBox combobox_continents,combobox_countries;
	String browse_file_path;
	String file_path = "C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/";
	MapLoader map_loader = new MapLoader();
	MapElements map_elements;
	JComboBox maps = new JComboBox<>();
	
	Integer [] values = {2,3,4,5};//temp
	String [] countries = {"India","USA","China"};
	String [] neighbours = {"a","b","c"};
	String [] continents = {"A","B","C"};
	
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
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
	}
	
	
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

	 	    	if ((txt_map_name.getText().equals("")) && (txt_author_name.getText().equals(""))){
	 	 	    	lbl_alert.setText("PLEASE ENTER TEXT");
	 	 	    }
	 	 	    else {
	 	 	    	selectMapConitnueButton();
	 	 	    }
	    	}
	    });
 	    
 	    JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
	
	/**
	 * It will show map representation and user can also edit the map
	 * @param map_elements 
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

		text_area_mr=new JTextArea(36,52);
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
				lbl_state.setText("Adding continent");
				addContinent();

			}
		});
		
		btn_add_country.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lbl_state.setText("Adding Country");
				addCountry();				
			}
		});
		
		btn_add_neighbour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lbl_state.setText("Adding Neighbour");
				addNeighbour();
			}
		});
		btn_save = new JButton("Save");
		
		save_btn_panel.add(btn_save);
		
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
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
	
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
						addCountry();
					}
				}
			}
		});
 		card_continent_panel.revalidate();
 		card_continent_panel.repaint();
	}
	
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
 		
 		lbl_select_neighbour = new JLabel("Select neighbour to add to this country");
 		card_country_panel.add(lbl_select_neighbour);
 		
 		DefaultListModel<String> listModel=getNeighboursModel();
 		combobox_neighbour=new JList(listModel);
 		combobox_neighbour=bindNeighbours(combobox_neighbour,map_elements);
 		combobox_neighbour.setFixedCellHeight(20);
		combobox_neighbour.setFixedCellWidth(100);
		combobox_neighbour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		combobox_neighbour.setVisibleRowCount(10);
 		scroll_pane_neighbours=new JScrollPane(combobox_neighbour);
		scroll_pane_neighbours.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 		card_country_panel.add(scroll_pane_neighbours);
 		
 		btn_add_country = new JButton("Add country");
 		card_country_panel.add(btn_add_country); 
 		
 		btn_add_country.addActionListener(new ActionListener() {
 			
			@Override
			public void actionPerformed(ActionEvent e) {
				String country_name=txt_country_name.getText().toString().trim();
				if(country_name!=null && country_name.length()>0){
					Continent selected_continent=null;
					Country selected_neighbour;
					for(Continent c:map_elements.getContinentList()){
						if(c.getContinentName().equals(combobox_continents.getSelectedItem().toString())){
							selected_continent=c;
							break;
						}
					}
					for(Country c:map_elements.getCountries()){
						if(c.getCountryName().equals(combobox_neighbour.getSelectedValue().toString())){
							selected_neighbour=c;
							break;
						}
					}
					Country new_country=new Country(country_name,selected_continent.getContinentName());
					
					List<Country> new_neighbour_list=new ArrayList<>();
					HashMap<Country, List<Country>> new_country_neighbour_map=map_elements.getCountryNeighboursMap();
					new_country_neighbour_map.put(new_country, new_neighbour_list);
					
					HashMap<Continent, List<Country>> new_continent_country_map=map_elements.getContinentCountryMap();
					List<Country> new_list=map_elements.getCountriesByContinent(selected_continent.getContinentName());
					//new_list.add(new_country);
					new_continent_country_map.put(selected_continent, new_list);
					
					selected_continent.setMemberCountriesList(new_list);
					map_elements.setContinentCountryMap(new_continent_country_map);
					map_elements.setCountryNeighboursMap(new_country_neighbour_map);
					text_area_mr.setText(map_elements.updateMR());
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
	 * @param combobox_neighbour
	 * @return
	 */
	private JList<String> bindNeighbours(JList<String> combobox_neighbour,MapElements map_elements) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(Country c:map_elements.getCountries()){
			listModel.addElement(c.getCountryName());
		}
		combobox_neighbour=new JList<>(listModel);
		
		return combobox_neighbour;
	}
	
	private DefaultListModel<String> getNeighboursModel() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(Country c:map_elements.getCountries()){
			listModel.addElement(c.getCountryName());
		}		
		return listModel;
	}
	
	public JComboBox bindContinentCombobox(JComboBox combobox_continent,MapElements map_elements) {

		for (Continent c : map_elements.getContinentList()) {
			//System.out.println(c.getContinentName().toString().trim());
			combobox_continent.addItem(c.getContinentName().toString().trim());
		}
		return combobox_continent;
	}



	void addNeighbour() {
		
		card_neighbour_panel.removeAll();
		cl.show(cards_panel, "card_neighbour");
		lbl_select_continent = new JLabel("Select continent");
		card_neighbour_panel.add(lbl_select_continent);
 		combobox_continents = new JComboBox(continents);
 		card_neighbour_panel.add(combobox_continents);
 		lbl_select_country = new JLabel("Select country");;
 		card_neighbour_panel.add(lbl_select_country);
 		combobox_countries = new JComboBox(countries);
 		card_neighbour_panel.add(combobox_countries);
 		lbl_select_neighbour = new JLabel("Select neighbour");
// 		card_neighbour_panel.add(lbl_select_neighbour);
// 		combobox_neighbour = new JComboBox(neighbours);
// 		card_neighbour_panel.add(combobox_neighbour);
 		
 		btn_ok = new JButton("Ok");
 		card_neighbour_panel.add(btn_ok); 
 		btn_ok.addActionListener(new ActionListener() {
 			
			@Override
			public void actionPerformed(ActionEvent e) {
				lbl_state.setText("Neighbour Added");
				
			}
		});
 		card_neighbour_panel.revalidate();
 		card_neighbour_panel.repaint();
	}

	
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
				if(map_elements.is_correct_map()){
					selectMapConitnueButton();
				}
				else{
					//Label dialog for incorrect map file
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

}
