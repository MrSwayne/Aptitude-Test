package ie.swayne.test.controllers;

import java.awt.event.ActionEvent;
import java.util.ListIterator;

import ie.swayne.test.core.Globals;
import ie.swayne.test.gui.GUI;

public class LoginController extends Controller {

	@Override
	public void addView(GUI view) {
		this.views.add(view);
		view.addListener(this, Globals.RC_LOGIN);
	}

	@Override
	public void removeView(GUI view) {
		this.removeView(view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		notifyViews("Checking login");
		if(checkCredentials()) {
			notifyViews("Login succesful");
		}
		else
			notifyViews("Login failed");
	}
	
	public boolean checkCredentials() {
		return true;
	}

}
