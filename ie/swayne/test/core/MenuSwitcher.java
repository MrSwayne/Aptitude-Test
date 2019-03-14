package ie.swayne.test.core;

import ie.swayne.test.gui.GUI;
import ie.swayne.test.gui.SFrame;

public final class MenuSwitcher {

	private static SFrame instance = null;
	
	
	private MenuSwitcher() {
	}
	
	
	public static void setFrame(SFrame ins) {
		instance = ins;
	}
	
	public static void changeMenu(String name) {
		if(instance == null)
			throw new IllegalStateException("NO FRAME SETUP");
		else {
			instance.setMenu(name);
		}
	}
	
	public static void addMenu(String name, GUI item) {
		if(instance == null)
			throw new IllegalStateException("NO FRAME SETUP");
		else {
			instance.addMenu(name, item);
		}
	}
}
