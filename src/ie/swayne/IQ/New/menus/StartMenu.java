package ie.swayne.IQ.New.menus;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ie.swayne.IQ.New.Frame;
import ie.swayne.IQ.New.Globals;


public class StartMenu extends Menu {

	
	private JPanel controlPanel;
	private JPanel formPanel;
	
	private JLabel descriptionLabel;
	
	private String[] options = {"Name", "Department"};
	private String[] data = new String[options.length];	
	private JTextField[] fields = new JTextField[data.length];
	
	public StartMenu(Frame frame) {
		super(frame);
		controlPanel = new JPanel();
		formPanel = new JPanel();
		
		controlPanel.setLayout(new FlowLayout());
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		
		setLayout(new GridLayout(4,1));
		
		descriptionLabel = new JLabel(Globals.get("startString"), JLabel.CENTER);
			
		//Add textfields with data specified in options array, for example you might want the name of the person taking the test, so this loop creates the required boxes and will pass the data to save it with the results of the test
		for(int i = 0;i < options.length;i++) {
			JPanel panel = new JPanel();
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabel label = new JLabel(options[i], JLabel.CENTER);
			JTextField field = new JTextField("", 10);
			fields[i] = field;
			field.setMaximumSize(field.getPreferredSize());
			
			panel.add(label);
			panel.add(field);
			
			formPanel.add(panel);
		}
		
		
		//Add next and back buttons so user can flick through the test
		JButton nextButton = new JButton("Next");
		nextButton.setActionCommand("Test");
		nextButton.addActionListener(new ButtonClickListener());
		
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("Main");
		backButton.addActionListener(new Frame.ButtonClickListener(frame));
		
		controlPanel.add(backButton);
		controlPanel.add(nextButton);
		
	
		add(descriptionLabel);
		add(formPanel);
		add(controlPanel);
	} 
	
	private boolean checkDetailsAreEntered() {
		boolean complete = true;
		int i = 0;
		
		//Goes through the panel and checks if all the textfields are filled in, if they are then the test will proceed.
		
		outerLoop:
		for(Component c: formPanel.getComponents())
			if(c instanceof JPanel) 
				for(Component cp: ((JPanel) c).getComponents())
					if(cp instanceof JTextField) {
						data[i] = ((JTextField) cp).getText(); //puts textfield data into array at slot i
						if(data[i].isEmpty()) { //if the box is empty then it breaks out of the loop and doesn't start the test
							complete = false;
							break outerLoop;
						}
						i++;
					}
		 return complete;
	}
	
	public class ButtonClickListener implements ActionListener {
		
		public ButtonClickListener() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			if(checkDetailsAreEntered() == true) {
				JButton button = (JButton) e.getSource();
				button.removeActionListener(this);
				button.addActionListener(new Frame.ButtonClickListener(frame));
				button.doClick();
			}
		}
	}
}
