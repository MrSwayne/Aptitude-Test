package ie.swayne.IQ.New.menus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ie.swayne.IQ.New.Globals;
import ie.swayne.IQ.New.Frame;

public class LoginMenu extends Menu {

	private JPanel loginPanel;
	private JPanel controlPanel;
	private static final long serialVersionUID = 1L;
	private String[] options = {"Username", "Password"};
	private JTextField[] fields = new JTextField[options.length];
	static JTextField userField = new JTextField("sam", 10);
	static JPasswordField passwordField = new JPasswordField("1",10);
	private JButton button = new JButton("");
	
	
	public LoginMenu(Frame frame) {
		super(frame);
		loginPanel = new JPanel();
		controlPanel = new JPanel();
		
		JPasswordField field;
		
			
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		JLabel userLabel = new JLabel("Username", JLabel.CENTER);
		fields[0] = userField;
		
		
		JPanel userPanel = new JPanel();
		userPanel.add(userLabel);
		userPanel.add(userField);
		
		
		JLabel passwordLabel = new JLabel("Password", JLabel.CENTER);
		fields[1] = passwordField;
		
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		userPanel.setMaximumSize(userPanel.getPreferredSize());
		passwordPanel.setMaximumSize(passwordPanel.getPreferredSize());
		
		loginPanel.add(userPanel);
		loginPanel.add(passwordPanel);
		
		loginPanel.add(button);
		button.setVisible(false);
	
		JLabel headerLabel = new JLabel("Please input a username and password to continue", JLabel.CENTER);
		
		
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("Main");
		backButton.addActionListener(new Frame.ButtonClickListener(frame));
		controlPanel.add(backButton);
		
		JButton nextButton = new JButton("Next");
		nextButton.setActionCommand("Settings");
		nextButton.addActionListener(new ButtonClickListener());
		controlPanel.add(nextButton);
		
		setLayout(new GridLayout(3,1));
		
		this.add(headerLabel);
		this.add(loginPanel);	
		this.add(controlPanel);
	}
	
	public boolean checkDetails() {
		
		String user = userField.getText().toString();
		char[] password = passwordField.getPassword();
		
		boolean valid = false;
		
		FileReader aFileReader;
		try {
			aFileReader = new FileReader(new File(Globals.get("usersFile")));
			Scanner in = new Scanner(aFileReader);
			
			while(in.hasNext() && !valid) {
				String line = in.nextLine();
				if(!(line.contains("!"))) {
					String[] data = line.split(",");
					if(data[0].equalsIgnoreCase(user) && data[1].equals(new String(password)))
							valid = true;
					else	valid = false;
				}
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	class ButtonClickListener implements ActionListener {
		
		public ButtonClickListener() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if(checkDetails() == true) {
				JButton b = (JButton) e.getSource();
				b.removeActionListener(this);
				b.addActionListener(new Frame.ButtonClickListener(frame));
				b.doClick();
			}
			
		}
	}

}
