package ie.swayne.test.gui.components;

import java.awt.Color;

import javax.swing.JLabel;

public class SLabel extends JLabel{

	public SLabel(String string, int center) {
		super(string, center);
		this.setForeground(Color.red);
	}

}
