package ie.swayne.IQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ie.swayne.IQ.*;
import ie.swayne.IQ.SQL.SQLHelper;
import ie.swayne.IQ.menus.*;
import ie.swayne.IQ.test.*;


public class Main {

	public static void main(String[] args) {
		
		try {
			displayBuild();
		} catch(FileNotFoundException e) {
			File file = new File("logs.txt");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println("Error creating file");
				e1.printStackTrace();
			}
		}
		
		SQLHelper db = new SQLHelper();
		
		db.insertIntoQuestions("maths","56 + 81 = 44 + ?","93-90-89-91-95","A","null", 0);
		db.insertIntoQuestions("maths","44 - ? = 15","26-29-28-39-30","B","null", 0);
		db.insertIntoQuestions("maths","87 - 35 = ?","53-42-51-41-52","E","null", 0);
	    db.insertIntoQuestions("maths","53 - 32 = 25 - ?","3-2-12-14-22","B", "null", 0);
			
		IQTest test = new IQTest(db);
		Frame frame = new Frame(Globals.get("name") + " Aptitude Test");
		
		frame.addMenu("Main", new MainMenu(frame));
		frame.addMenu("Start", new StartMenu(frame));
		frame.addMenu("Test", new TestMenu(frame, test));
		frame.addMenu("Login", new LoginMenu(frame));
		frame.addMenu("Settings", new SettingsMenu(frame, test));
	}
	
	private static void displayBuild() throws FileNotFoundException {
		
		File file = new File("logs.txt");
		
		PrintStream fStream = new PrintStream(new FileOutputStream(file, true));
		//System.setOut(fStream);
		
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-YYYY");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		
        System.out.println("--------------------------");
		System.out.println("" + sdf.format(cal.getTime()) + " " + sdf2.format(cal.getTime()));
		
	}
}
