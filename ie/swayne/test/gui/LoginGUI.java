package ie.swayne.test.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ie.swayne.test.controllers.Controller;
import ie.swayne.test.controllers.LoginController;
import ie.swayne.test.core.Globals;
import ie.swayne.test.gui.components.SButton;
import ie.swayne.test.gui.components.SHintField;
import ie.swayne.test.gui.components.SLabel;

public final class LoginGUI extends GUI {
	
	private SButton loginBtn = new SButton("Login", Globals.RC_LOGIN);
	private SLabel pl, ul;
	private JTextField pf, uf;
	
	public LoginGUI(Controller control) {
		super(control);
	}
	
	public LoginGUI() {
		contentPanel.setLayout(new GridLayout(2,0));
		JPanel userPanel = new JPanel();
		ul = new SLabel("User", SLabel.CENTER);
		uf = new SHintField("Enter Username");
		userPanel.add(ul);
		userPanel.add(uf);
		
		JPanel passwordPanel = new JPanel();
	    pl = new SLabel("Pass", SLabel.CENTER);
		pf = new JPasswordField(10);
		passwordPanel.add(pl, 0);
		passwordPanel.add(pf, 1);
		
		contentPanel.add(userPanel);
		contentPanel.add(passwordPanel);
		buttonPanel.add(loginBtn);
	}
	
	public TreeMap<String, String> getDetails() {
		TreeMap<String, String> map = new TreeMap<String, String>();
		
		
		
		//Fairly unsafe as password becomes an immutable string so the password will stay in memory
		//Until it is garbage collected
		//This system doesn't have strong security requirements so I wouldn't worry about it
		map.put(ul.getText(), uf.getText());
		map.put(pl.getText(), String.copyValueOf(((JPasswordField) pf).getPassword()));
		
		return map;
	}

	@Override
	public void addListener(ActionListener listen, int RC_CODE) {
		if(RC_CODE == loginBtn.getRC()) {
			loginBtn.addActionListener(listen);
		}
		
	}
}
