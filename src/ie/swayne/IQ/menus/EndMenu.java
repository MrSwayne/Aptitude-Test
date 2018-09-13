package ie.swayne.IQ.menus;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ie.swayne.IQ.Frame;

public class EndMenu extends Menu {

	private JLabel label;
	private JPanel controlPanel;
	
	public EndMenu(Frame frame) {
		super(frame);
		
		
		label = new JLabel("You completed the test!");
		
		
		
		
		this.add(label);
		
	}

}
