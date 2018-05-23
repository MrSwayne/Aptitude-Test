package ie.swayne.IQ;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MainMenu extends JFrame {

	private static final long serialVersionUID = 2327668447005881223L;
	
	protected static final String TITLE = "Aptitude Test";
	protected final int WIDTH;
	protected final int HEIGHT;
	
	protected static ImageIcon logo = new ImageIcon("");
	
	
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
		
		setLayout(new GridLayout(3,1));	
		headerLabel = new JLabel(logo, JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		add(headerLabel);
		add(controlPanel);
		add(statusLabel);
		
		startButton = new JButton("Start");
		settingsButton = new JButton("Settings");
		
		controlPanel.add(startButton);
		controlPanel.add(settingsButton);
		
		startButton.setActionCommand("Start");
		settingsButton.setActionCommand("Settings");
		
		startButton.addActionListener(new ButtonClickListener());
		settingsButton.addActionListener(new ButtonClickListener());
	}
	
	private void start() {
		
	}
	
	private void settings() {
		
	}
	
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
