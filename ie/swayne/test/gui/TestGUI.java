package ie.swayne.test.gui;

import java.awt.event.ActionListener;

import ie.swayne.test.core.Globals;
import ie.swayne.test.gui.components.SButton;

public class TestGUI extends GUI {

	protected SButton nextBtn;
	protected SButton backBtn;
	
	public TestGUI() {
		nextBtn = new SButton("Next", Globals.RC_NEXT);
		backBtn = new SButton("Back", Globals.RC_BACK);
		buttonPanel.add(nextBtn);
		buttonPanel.add(backBtn);
		
		
	}
	
	
	@Override
	public void addListener(ActionListener listen, int RC_CODE) {
		if(RC_CODE == nextBtn.getRC()) {
			nextBtn.addActionListener(listen);
		}
		else if(RC_CODE == backBtn.getRC()) {
			backBtn.addActionListener(listen);
		} else {
			throw new IllegalArgumentException(RC_CODE + " is invalid");
		}
		
	}

}
