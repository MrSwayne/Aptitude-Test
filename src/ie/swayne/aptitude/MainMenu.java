package ie.swayne.IQ;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MainMenu extends JFrame {

	private static final long serialVersionUID = 2327668447005881223L;
	
	protected final static String NAME = "";

	protected static final String TITLE = NAME + " - Aptitude Test";
	protected final int WIDTH;
	protected final int HEIGHT;
	
	protected static ImageIcon logo = new ImageIcon("res/logo.png");

	private JPanel controlPanel;	
	private JLabel headerLabel;
	private JLabel statusLabel;
	
	private JButton startButton;
	private JButton settingsButton;
	
	public static boolean IS_RUNNING = false;
	
	public MainMenu() {
		super(TITLE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int)screenSize.getWidth();
		HEIGHT = (int)screenSize.getHeight();
		setSize(WIDTH / 2, HEIGHT / 2);
		
	
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		initMainLayout();
			
		setVisible(true);	
	}
	
	private void initMainLayout() {	
		controlPanel = new JPanel();
		
		//Add grid layout, top layer consists of the logo (headerLabel), the middle layer is the control panel (buttons which control navigation) and bottom layer is a statusLabel (Can be used to show messages or whatever)	
		setLayout(new GridLayout(3,1));	
		headerLabel = new JLabel(logo, JLabel.CENTER);
		statusLabel = new JLabel("Insert status", JLabel.CENTER);
		
		
		
		

		//Config for the Start and Settings button
		startButton = new JButton("Start");
		settingsButton = new JButton("Settings");	
		
		startButton.setActionCommand("Start");
		settingsButton.setActionCommand("Settings");
		
		startButton.addActionListener(new ButtonClickListener());
		settingsButton.addActionListener(new ButtonClickListener());
		
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(startButton);
		controlPanel.add(settingsButton);
		
		add(headerLabel);
		add(controlPanel);
		add(statusLabel);
	}
	
	private void start() {
		
	}
	
	private void settings() {
		
	}
	
	
	//If start button is clicked, start the test, if settings is clicked, open settings menu
	private class ButtonClickListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			switch(command) {
			
			case "Start": {
				if(!IS_RUNNING) {
					IS_RUNNING = true;
					new StartMenu(TITLE, WIDTH, HEIGHT); 
				}
				
				break;
			}
			case "Settings": new SettingsMenu(); break;
			}
		}
	}
}
