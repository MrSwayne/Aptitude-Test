package ie.swayne.test;

import ie.swayne.test.controllers.LoginController;
import ie.swayne.test.gui.LoginGUI;
import ie.swayne.test.gui.SFrame;

public class Main {
	public static void main(String[] args) {
		SFrame frame = new SFrame("TEST");
		LoginGUI login = new LoginGUI();
		LoginController lc = new LoginController();
		lc.addView(login);
		
		
		SFrame frame2 = new SFrame("test2");
		LoginGUI login2 = new LoginGUI();
		lc.addView(login2);
		frame2.addMenu("login", login2);
		
		frame.addMenu("login", login);
	}
}
