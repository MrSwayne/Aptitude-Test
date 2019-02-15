package ie.swayne.test.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ie.swayne.test.core.MenuSwitcher;

public class SFrame extends JFrame {
	
	private TreeMap<String, GUI> menus;
	public static int WIDTH;
	public static int HEIGHT;
	
	private GUI container = null;
	
	
	public SFrame(String title) {
		super(title);
		MenuSwitcher.setFrame(this);
		menus = new TreeMap<>();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		setSize(WIDTH / 2, HEIGHT / 2);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Are you sure you want to quit?
		/*this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
				if(i == 0)
					System.exit(0);
			}
			
			
		});*/
		
	}

	public void addMenu(String name, GUI menu) {	
		if(menu != null && name != null &! name.isEmpty()) 	menus.put(name, menu);
		if(menus.size() == 1)	                           	setMenu(name);
	}
	
	public void setMenu(String name) {
		if(container != null) 
			this.remove(container);
		
		container = menus.get(name);
		
		if(container != null) {
			this.add(container);
			this.pack();
			this.revalidate();
			this.repaint();
		}
		else
			throw new IllegalStateException("JFrame cannot set menu " + name);
	}
	
}
