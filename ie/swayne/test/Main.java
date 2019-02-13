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

		frame.addMenu("login", login);
		
		
		//Code to make sure the program exits gracefully
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				//TODO
				System.out.println("Shutdown hook is running");
			}
		});
		
	}
}
