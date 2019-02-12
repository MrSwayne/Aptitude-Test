package ie.swayne.test.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ie.swayne.test.controllers.Controller;
import ie.swayne.test.gui.components.SLabel;

public abstract class GUI extends JPanel {

	protected JPanel container;
	protected JPanel buttonPanel;
	protected JPanel contentPanel;
	protected JPanel headerPanel;
	protected JPanel messagePanel;
	
	protected SLabel messageLabel;
	public int ID;
	private static int numGUI = 0;
	
	
	protected GUI(Controller control) {
		this();
		control.addView(this);
	}
	protected GUI() {
		ID = numGUI++;
		container = new JPanel(new GridLayout(4,0));
		headerPanel = new JPanel();
		contentPanel = new JPanel();
		messagePanel = new JPanel();
		buttonPanel = new JPanel();
		
		container.add(headerPanel, 0);
		container.add(messagePanel, 1);
		container.add(contentPanel,2);
		container.add(buttonPanel, 3);
		
		messageLabel = new SLabel("", JLabel.CENTER);
		messagePanel.add(messageLabel);
		
		this.add(container);
	}
	
	public final void show() {
		this.setVisible(this.isVisible()? false:true);
	}

	public final void setMessage(String message) {
		if(message != null)
			messageLabel.setText(message);
	}
	
	public abstract void addListener(ActionListener listen, int RC_CODE);
}