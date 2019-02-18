package ie.swayne.test;

import java.io.File;
import java.io.IOException;

import ie.swayne.test.controllers.ControllerFactory;
import ie.swayne.test.controllers.LoginController;
import ie.swayne.test.core.DataManager;
import ie.swayne.test.core.SettingsManager;
import ie.swayne.test.gui.GUIFactory;
import ie.swayne.test.gui.LoginGUI;
import ie.swayne.test.gui.SFrame;

public class Main {
	public static void main(String[] args) throws IOException {

		SFrame frame = new SFrame("TEST");
		LoginGUI login = (LoginGUI) GUIFactory.get("login");
		LoginController lc = (LoginController) ControllerFactory.get("login");
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
