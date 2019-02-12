package ie.swayne.test.gui.components;

import javax.swing.JButton;

public class SButton extends JButton {
	
	private int RC_CODE;
	
	public SButton(String s, int RC) {
		super(s);
		this.RC_CODE = RC;
	}
	
	public int getRC() {
		return this.RC_CODE;
	}
}
