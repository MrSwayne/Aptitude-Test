package ie.swayne.test.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

import ie.swayne.test.gui.GUI;

public abstract class Controller implements ActionListener {
	
	protected LinkedList<GUI> views;
	
	protected Controller() {
		views = new LinkedList<>();
	}
	
	public abstract void addView(GUI view);
	
	public abstract void removeView(GUI view);
	
	public void notifyViews(String message) {
		ListIterator it = views.listIterator();
		while(it.hasNext())
			((GUI)it.next()).setMessage(message);
		System.out.println("Notify: " + message);
	}
	
	/*
	 * 
	 * Not implemented
	 */
	public void notifyView(String message, int i) {
		//ListIterator it = views.listIterator();
		//while(it.hasNext()) {
			//GUI item = ((GUI)it.next());
			//if(item.ID == i)
			//	item.setMessage(message);
		//}
	}

	public abstract void actionPerformed(ActionEvent e);
}