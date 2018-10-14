package app.team21.risk.views;
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
import java.util.List;

public class MapPath {
	
	JButton edit_map,create_map,btn_selectmap_conitnue,btn_add_continent,btn_add_country,btn_add_neighbour,btn_save;
	JLabel lbl_or,lbl_map_name,lbl_author_name,lbl_alert;
	JTextField txt_map_name,txt_author_name;
	JPanel mr_panel,master_panel,buttons_panel,cards_panel,save_btn_panel,card_continent,card_country,card_neighbour;
	private CardLayout cl = new CardLayout();
	MapLoader map_loader=new MapLoader();
	String browse_file_path;
	String file_path="C:/Users/yashe/OneDrive/Documents/GitHub/RiskTeam21/RiskTeam21/src/app/team21/risk/maps/";
	MapElements map_elements;
	JComboBox maps = new JComboBox();

	
	public void mapButton(){
		
		JPanel test = new JPanel();
		StartGame sg =new StartGame();
		test=sg.getPanel();
		
		
		edit_map = new JButton("Edit an existing Map");
		test.add(edit_map);
 	    edit_map.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playButton();//temp almost only continue button should direct to map editing screen
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
	
	/**
	 * It will show map representation and user can also edit the map
	 */
	public void selectMapConitnueButton(){
		JPanel test = new JPanel();
		StartGame sg =new StartGame();
		test=sg.getPanel();
		
		
		
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
		btn_add_continent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addContinent();
				//cl.show(cards_panel, "card_continent");
			}
		});
		btn_add_country = new JButton("Add Country");
		btn_add_neighbour = new JButton("Add Neighbour");
		btn_save = new JButton("Save");
		buttons_panel.add(btn_add_continent);
		buttons_panel.add(btn_add_country);
		buttons_panel.add(btn_add_neighbour);	
		save_btn_panel.add(btn_save);
		
		test.add(mr_panel,BorderLayout.WEST);
		test.add(master_panel,BorderLayout.EAST);
		master_panel.add(buttons_panel,BorderLayout.NORTH);
		master_panel.add(cards_panel,BorderLayout.CENTER);
		master_panel.add(save_btn_panel,BorderLayout.SOUTH);
		
		cards_panel = new JPanel();
		cards_panel.setLayout(cl);
		card_continent = new JPanel();
		card_country = new JPanel();
		card_neighbour = new JPanel();
		
		cards_panel.add(card_continent,"card_continent");
		cards_panel.add(card_country,"card_country");
		cards_panel.add(card_country,"card_country");
		
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	}
	
	void addContinent() {
		
		cl.show(cards_panel, "card_continent");
		card_continent.add(lbl_or);
		
		
	}
	
}
