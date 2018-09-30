package app.team21.risk.startupmodule;

import java.io.File;
import javax.*;
import javax.swing.*;
import java.awt.*;


public class GameModule {
	
	static File folder = new File("C:/Users/ADMIN/git/Risk/RiskTeam21/Maps");
	static File[] listOfFiles = folder.listFiles();
	
    
    
	
	public static void main(String[] args) {
		 
	    
	    dropDownList();
	    
	    
	    //String[] fileNames;// = listOfFiles;
		JComboBox messageList = new JComboBox(listOfFiles);
		
	 
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        System.out.println("File " + listOfFiles[i].getName());
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
	    }
	  }
	
	
	public static void dropDownList() {
    	JFrame frame = new JFrame("Select map & Players");
		frame.setSize(200,200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		JComboBox list = new JComboBox(listOfFiles);
		System.out.println(list);
		
		JPanel panel = new JPanel();
		
		JLabel selectMapLabel = new JLabel("Select Map");
		panel.add(selectMapLabel);
		panel.add(list);
		frame.getContentPane().add(panel);
//		
//		messageList.setSelectedIndex(0);
//		messageList.addActionListener(this);
//		add(messageList);
//		add(labelText);
    }

}
