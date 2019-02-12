package ie.swayne.IQ.menus;


import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ie.swayne.IQ.*;

public class MainMenu extends Menu {

	private JPanel settingsPanel;
	private JPanel controlPanel;
	private JLabel logo;
	private JButton startButton;
	private JButton settingsButton;
	
	public MainMenu(Frame frame) {
		super(frame);
		logo = new JLabel(new ImageIcon(Globals.get("imgFolder") + Globals.get("logo")), JLabel.CENTER);
		
		settingsPanel = new JPanel();
		controlPanel = new JPanel();
		
		startButton = new JButton("Start");
		startButton.setActionCommand("Start");
		startButton.addActionListener(new Frame.ButtonClickListener(frame));
		
		settingsButton = new JButton("Settings");
		settingsButton.setActionCommand("Login");
		settingsButton.addActionListener(new Frame.ButtonClickListener(frame));
		
		controlPanel.add(startButton);
		controlPanel.add(settingsButton);
		
		this.add(logo);
		this.add(controlPanel);
	}

}
