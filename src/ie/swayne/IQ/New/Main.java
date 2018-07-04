package ie.swayne.IQ.New;

import java.io.File;

import ie.swayne.IQ.New.*;
import ie.swayne.IQ.New.test.*;
import ie.swayne.IQ.New.*;
import ie.swayne.IQ.New.menus.*;


public class Main {

	public static void main(String[] args) {
			
		IQTest test = new IQTest(new File(Globals.get("questionsFile")), new File(Globals.get("picturesFile")));
		Frame frame = new Frame(Globals.get("name") + " Aptitude Test");
		
		frame.addMenu("Main", new MainMenu(frame));
		frame.addMenu("Start", new StartMenu(frame));
		frame.addMenu("Test", new TestMenu(frame, test));
		frame.addMenu("Login", new LoginMenu(frame));
		frame.addMenu("Settings", new SettingsMenu(frame, test));
	}

}
