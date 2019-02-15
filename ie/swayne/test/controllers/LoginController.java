package ie.swayne.test.controllers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ListIterator;
import java.util.TreeMap;

import ie.swayne.test.core.Globals;
import ie.swayne.test.core.SettingsManager;
import ie.swayne.test.gui.GUI;
import ie.swayne.test.gui.LoginGUI;
import ie.swayne.test.gui.components.SButton;
import ie.swayne.test.sql.Query;
import ie.swayne.test.sql.SQLHelper;

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
		
		if(e.getSource() instanceof SButton) {
			SButton source = (SButton) e.getSource();
			if(checkCredentials(source.getParent().getParent().getParent())) {
				SettingsManager.setSetting("name", SettingsManager.getSetting("name") + "2");
				notifyViews(SettingsManager.getSetting("name"));
			}
		}
		else
			notifyViews("Login failed");
	}
	
	public boolean checkCredentials(Container container) {
		//TODO
		if(container != null) return true;
		
		if(container instanceof LoginGUI) {
			LoginGUI gui = (LoginGUI) container;
			TreeMap<String, String> map = gui.getDetails();
			
			String username = map.get("User");
			String pw = map.get("Pass");
			
			String sql = "SELECT * FROM users WHERE user= " + username + " AND pass = password(" + pw + ");";
			
			SQLHelper db = SQLHelper.getInstance();
			Query query = db.executeQuery(sql);
			System.out.println(query.toString());
			return true;
		}
		System.err.println("INCORRECT GUI PASSED TO CHECK CREDENTIALS");
		return false;
	}

}
