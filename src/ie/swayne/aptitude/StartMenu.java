package ie.swayne.IQ;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;


class StartMenu extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private String[] options = {"Name", "Department"};
	private String[] data = new String[options.length];	
	private JTextField[] fields = new JTextField[data.length];
	private JLabel headerLabel;
	private JLabel descriptionLabel;
	private JPanel controlPanel;
	private JPanel formPanel;
	
	private String TITLE;
	private int WIDTH;
	private int HEIGHT;
	private String descriptionString = "<html>Welcome to the " + MainMenu.NAME + " Aptitude Test! <br> This test consists of 60 questions. <br> You have 30 minutes to complete this test.<br><br> But first, please fill in the form below.</html>";
	
	public StartMenu(String TITLE, int WIDTH, int HEIGHT) {
		super(TITLE);
		
		this.TITLE = TITLE;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		
		setSize(WIDTH / 2, HEIGHT / 2);
	
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		initLayout();
			
		setVisible(true);	
	}
	
	//Clean inputs
	private String sanitise(String str) {
		str = str.trim().replace("^[a-zA-Z0-9 ]", "");
		return str;
	}
	
	
	//Start the quiz, only happens if all data is present in the textfields and the start button is pressed.
	private void initQuiz() {
		
		//Pass the questions data txt file and the pictures data txt file to the IQTest
		IQTest test = new IQTest(new File("res/questions.txt"), new File("res/pictures.txt"));
		
		//Initialise the test
		test.init();
		
		//clean the inputs and overwrite their place in the data array
		for(int i = 0;i < fields.length;i++) {
			String datum = fields[i].getText();
			datum = sanitise(datum);
			data[i] = datum;
		}
		
		//Start up the question menu
		new QuestionMenu(test, TITLE, WIDTH, HEIGHT, data);
	}
	

	private void initLayout() {
		controlPanel = new JPanel();
		formPanel = new JPanel();
		
		controlPanel.setLayout(new FlowLayout());
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		
		setLayout(new GridLayout(4,1));
		
		headerLabel = new JLabel(MainMenu.logo, JLabel.CENTER);
		descriptionLabel = new JLabel(descriptionString, JLabel.CENTER);
			
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
		JButton backButton = new JButton("Back");
		
		nextButton.setActionCommand("Next");
		backButton.setActionCommand("Back");
		
		nextButton.addActionListener(new ButtonClickListener());
		backButton.addActionListener(new ButtonClickListener());
		
		controlPanel.add(backButton);
		controlPanel.add(nextButton);
		
		
		add(headerLabel);
		add(descriptionLabel);
		add(formPanel);
		add(controlPanel);
	}
	
		private class ButtonClickListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				
				switch(command) {
				
				case "Next": 	{
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
					if(complete) //if all fields are complete, then continue.
						initQuiz();
				}
					break;
				case "Back": {
					StartMenu.this.dispatchEvent(new WindowEvent(StartMenu.this, WindowEvent.WINDOW_CLOSING)); 
					MainMenu.IS_RUNNING = false;
				}
					break;
			}
		}
	}
}
