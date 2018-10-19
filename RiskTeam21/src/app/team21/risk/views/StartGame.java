package app.team21.risk.views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Last Updated on: 18-10-2018, Thursday 
 * This class file handles start game screen i.e. home screen.
 * 
 * @author Divyansh
 * @version 1.0.0
 */
public class StartGame {
	private static JFrame main_frame;
	private static JPanel plain_panel;
	
		/**
		 * This is main method.
		 * 
		 * @param args
		 */
		public static void main(String [] args){
			
			createStartScreen();
			
		}
		
		/**
		 * It is main home screen where it ask for play or map or rules or quit.
		 */
		public static void createStartScreen(){
			
			main_frame = new JFrame("Risk");
			JButton play_button,map_button,rules_button,quit_button;
			
			play_button = new JButton("Play");
			play_button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					PlayPath pp = new PlayPath();
					pp.playButton();
					
				}
			});
			
			map_button= new JButton("Map");
			map_button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					MapPath mp = new MapPath();
					mp.mapButton();
					
				}
			});
			
			rules_button = new JButton("Rules");
			rules_button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						Desktop.getDesktop().open(new java.io.File("Rules.pdf"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			});
			
			quit_button = new JButton("Quit");
			quit_button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					close();
					
				}
			});
			
			JPanel start_panel= new JPanel();
			start_panel.setLayout(new BorderLayout());
			start_panel.setLayout(new GridLayout());
			
			start_panel.add(play_button,BorderLayout.CENTER);
			start_panel.add(map_button,BorderLayout.CENTER);
			start_panel.add(rules_button,BorderLayout.CENTER);
			start_panel.add(quit_button,BorderLayout.CENTER);
			
			main_frame.getContentPane().setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			
			main_frame.add(start_panel,gbc);
			main_frame.setVisible(true);
			main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			main_frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		}
		
		/**
		 * This method will return a new panel.
		 * 
		 * @return panel new panel
		 */
		public  JPanel getPanel(){
			
			plain_panel = new JPanel();
			return plain_panel;
			
		}
		
		/**
		 * This method will return a return a same frame.
		 * 
		 * @return frame existing frame
		 */
		public  Frame getFrame(){
			main_frame.getContentPane().removeAll();
			main_frame.repaint();
			return main_frame;
			
		}
		
		/**
		 * closes and disposes main_frame 
		 */
		public static void close(){
			main_frame.dispose();
		}
}
