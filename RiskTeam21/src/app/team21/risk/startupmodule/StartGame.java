//package app.team21.risk.startupmodule;
//
//import javax.*;
//import javax.swing.*;
///*import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;*/
s//
//import java.awt.*;
//import java.awt.event.*;
//
//public class StartGame {
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("Paths");
//		frame.setSize(400, 400);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		JPanel panel = new JPanel();
//		frame.add(panel);
//
//		JButton play_button = new JButton("Play");
//		play_button.setSize(100, 100);
//		play_button.setVisible(true);
//		JButton map_button = new JButton("Map");
//		map_button.setSize(100, 100);
//		map_button.setVisible(true);
//		JButton quit_button = new JButton("Quit");
//		quit_button.setSize(100, 100);
//		quit_button.setVisible(true);
//		JButton rules_button = new JButton("Rules");
//		rules_button.setSize(100, 100);
//		rules_button.setVisible(true);
//
//		// sets the the vertical alignment
//		play_button.setAlignmentX(play_button.CENTER_ALIGNMENT);
//		map_button.setAlignmentX(map_button.CENTER_ALIGNMENT);
//		quit_button.setAlignmentX(quit_button.CENTER_ALIGNMENT);
//		rules_button.setAlignmentX(rules_button.CENTER_ALIGNMENT);
//
//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // set buttons
//																	// vertically
//
//		// set buttons in center
//		frame.setLayout(new GridBagLayout());
//		frame.add(play_button, new GridBagConstraints());
//		frame.add(map_button, new GridBagConstraints());
//		frame.add(quit_button, new GridBagConstraints());
//		frame.add(rules_button, new GridBagConstraints());
//
//		panel.add(play_button);
//		panel.add(Box.createRigidArea(new Dimension(0, 10))); // add space
//																// between
//																// buttons
//		panel.add(map_button);
//		panel.add(Box.createRigidArea(new Dimension(0, 10)));
//		panel.add(quit_button);
//		panel.add(Box.createRigidArea(new Dimension(0, 10)));
//		panel.add(rules_button);
//
//		rules_button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent a) {
//				try {
//					Desktop.getDesktop().open(new java.io.File("Rules_File/Risk_rules.pdf"));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//	}
//
//}
