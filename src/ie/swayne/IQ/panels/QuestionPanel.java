package ie.swayne.IQ.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import ie.swayne.IQ.test.*;


public class QuestionPanel extends JPanel {

	
	private JPanel containerPanel = new JPanel();
	private JLabel questionLabel;
	private JLabel questionPictureLabel;
	private JPanel questionPanel;
	private JPanel answerPanel;
	private String[] answers;
	private JToggleButton[] buttons;
	private ArrayList<String> config;
	
	private Question question;
	
	
	public QuestionPanel(Question question, int number) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		this.question = question;
		question.setCreated(true);
		
		answerPanel = new JPanel();
		questionPanel = new JPanel();
		
		
		//Create a label with the question number and the question and add it to the question panel
		questionLabel = new JLabel((number + 1) + ". " + question.getQuestion(), JLabel.CENTER);
		questionPanel.add(questionLabel);
		
		
		//If it's a picture question, then encapsulate the question and picture inside a questionPanel
		if(question instanceof PictureQuestion) {
			PictureQuestion pictureQuestion = (PictureQuestion) question;
			questionPictureLabel = new JLabel(pictureQuestion.getPicture(), JLabel.CENTER);
			Dimension dimensions = new Dimension(pictureQuestion.getPicture().getIconWidth(),pictureQuestion.getPicture().getIconHeight());
			questionPictureLabel.setPreferredSize(dimensions);
			questionPanel.add(questionPictureLabel);
		}	
		
		
		answers = new String[question.getAnswers().length];
		buttons = new JToggleButton[question.getAnswers().length];
		
		
		
		//If the user backtracks i.e if the question has been created before, then get the config of the answers already selected
		
		
		if (question.isCreated()) {	
			config = question.getConfig();
		}
			//generate buttons for the answers
		for(int i = 0;i < answers.length;i++) {
			JToggleButton button = new JToggleButton(question.getAnswers()[i]);
			
			
			//Questions are labeled A,B,C,D.. etc
			char choice = (char) (i + 'A');
			button.setActionCommand(String.valueOf(choice));
			
			//If the config has button A selected, then when generating the panel again, it should select question A
			if(config.size() > 0)
				if(config.contains(String.valueOf(choice))) {
					button.setSelected(true);
				}
			
			buttons[i] = button;
			
			//Keep track of how many buttons are pressed 
			button.addActionListener(e -> {
				if(button.isSelected())
					question.add(button.getActionCommand());


				else
					question.remove(button.getActionCommand());
			});
			answerPanel.add(button);
		}		
		
		add(questionPanel);
		add(answerPanel);
	}
}
