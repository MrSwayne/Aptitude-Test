package ie.swayne.IQ.menus;

import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

import ie.swayne.IQ.Frame;

public class Menu extends JPanel {

	private static final long serialVersionUID = 2327668447005881223L;
	protected Frame frame;
	
	
	protected Menu(Frame frame) {
		
		JButton button = new JButton("Hello");
		this.add(button);
		button.setFont(new Font("Impact", Font.PLAIN, 24));
		
		this.frame = frame;
	}
}
