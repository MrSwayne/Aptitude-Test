package ie.swayne.IQ.New;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ie.swayne.IQ.New.menus.Menu;

public class Frame extends JFrame {

	private ArrayList<Menu> menus;
	private TreeMap<String, Menu> menuMap;
	private JPanel container;
	private static int WIDTH;
	private static int HEIGHT;
	
	public Frame(String title) {
		super(title);
		menus = new ArrayList<>();
		menuMap = new TreeMap<String, Menu>();
		container = new JPanel();
		
		//Set size of frame equal to half of screen size, and put it in the middle of the screen.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = (int) screenSize.getWidth();
		HEIGHT = (int) screenSize.getHeight();
		setSize(WIDTH / 2, HEIGHT / 2);
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		this.add(container);
	}
	
	public void setMenu(String name) {
		this.remove(container);
		container = menuMap.get(name);
		this.add(container);
		this.revalidate();
		this.repaint();
	}
	
	public void addMenu(String name, Menu m) {	
		boolean first = menuMap.isEmpty();
		menuMap.put(name, m);
		if(first)	setMenu(name);
	}
	
	public static class ButtonClickListener implements ActionListener {
		
		private Frame frame;
		
		public ButtonClickListener(Frame frame) {
			this.frame = frame;
		}
		
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			frame.setMenu(e.getActionCommand());
			
		}
	}
}
