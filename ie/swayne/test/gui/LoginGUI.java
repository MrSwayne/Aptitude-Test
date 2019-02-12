package ie.swayne.test.gui;

import java.awt.event.ActionListener;

import ie.swayne.test.controllers.Controller;
import ie.swayne.test.controllers.LoginController;
import ie.swayne.test.core.Globals;
import ie.swayne.test.gui.components.SButton;

public class LoginGUI extends GUI {
	
	SButton loginBtn = new SButton("Login", Globals.RC_LOGIN);
	
	public LoginGUI(Controller control) {
		super(control);
	}
	
	public LoginGUI() {
		
		buttonPanel.add(loginBtn);
	}

	@Override
	public void addListener(ActionListener listen, int RC_CODE) {
		if(RC_CODE == loginBtn.getRC()) {
			loginBtn.addActionListener(listen);
		}
		
	}
}
