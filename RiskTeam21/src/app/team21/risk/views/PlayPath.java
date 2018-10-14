package app.team21.risk.views;
import app.team21.risk.views.StartGame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayPath extends StartGame{
	
	public void playButton(){
		
		System.out.println("play button clicked");
		JPanel test = new JPanel();
		JLabel lbl = new JLabel("Test Success");

		StartGame sg =new StartGame();
		test=sg.getPanel();
		test.add(lbl);
		JFrame jf = new JFrame();
		jf=(JFrame) sg.getFrame();
		jf.add(test);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		
	}

}


