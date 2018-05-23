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
	private String[] answers = new String[options.length];	
	private JTextField[] fields = new JTextField[answers.length];
	private JLabel headerLabel;
	private JLabel descriptionLabel;
	private JPanel controlPanel;
	private JPanel formPanel;
	
	private String TITLE;
	private int WIDTH;
	private int HEIGHT;
	private String descriptionString = "<html>Welcome to the Aptitude Test! <br> This test consists of 60 questions. <br> You have 30 minutes to complete this test.<br><br> But first, please fill in the form below.</html>";
	
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
	
	private void initQuiz() {
		IQTest test = new IQTest(new File("res/questions.txt"), new File("res/pictures.txt"));
		test.init();
		new QuestionMenu(test, TITLE, WIDTH, HEIGHT);
		
		for(int i = 0;i < fields.length;i++)
			answers[i] = fields[i].getText();
		
		for(int i = 0;i < answers.length;i++)
			System.out.println(answers[i]);
	}
	

	private void initLayout() {
		

		
		setLayout(new GridLayout(4,1));
		
		controlPanel = new JPanel();	
		controlPanel.setLayout(new FlowLayout());
		
		
		descriptionLabel = new JLabel(descriptionString, JLabel.CENTER);
		headerLabel = new JLabel("", JLabel.CENTER);
		
		formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		
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
					
					outerLoop:
					for(Component c: formPanel.getComponents())
						if(c instanceof JPanel) 
							for(Component cp: ((JPanel) c).getComponents())
								if(cp instanceof JTextField) {
									answers[i] = ((JTextField) cp).getText();
									if(answers[i].isEmpty()) {
										complete = false;
										break outerLoop;
									}
									i++;
								}
					if(complete)
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
