package ie.swayne.test.quiz;

import java.awt.Dimension;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ie.swayne.test.gui.components.SLabel;
import ie.swayne.test.gui.components.SToggleButton;

public class QuestionPanel extends JPanel {
	
	private SLabel questionL;
	private JPanel questionPanel;
	private JPanel answerPanel;
	private SLabel questionPictureLabel;
	private String[] answers;
	private SToggleButton[] buttons;
	private Stack<String> config;
	
	private TestQuestion question = null;
	
	public QuestionPanel() {
	}
	
	public void buildPanel(TestQuestion question) {
		this.question = question;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		questionPanel = new JPanel();
		answerPanel = new JPanel();
		
		questionL = new SLabel(question.getQuestion(), SLabel.CENTER);
		questionPanel.add(questionL);
		
		//TODO
		if(question instanceof PictureTestQuestion) {
			PictureTestQuestion q = (PictureTestQuestion) question;
			questionPictureLabel = new SLabel(q.getPicture(), SLabel.CENTER);
			Dimension dimensions = new Dimension(q.getPicture().getIconWidth(), q.getPicture().getIconHeight());
			questionPictureLabel.setPreferredSize(dimensions);
			questionPanel.add(questionPictureLabel);
		}
		
		answers = new String[question.getAnswers().length];
		buttons = new SToggleButton[question.getAnswers().length];
		
		if(question.getSelectedAnswers().size() != 0) {
			config = question.getSelectedAnswers();
		}
		
		for(int i = 0;i < answers.length;i++) {
			SToggleButton button = new SToggleButton(question.getAnswers()[i]);
			
			char choice = (char) (i + 'A');
			button.setActionCommand(String.valueOf(choice));
			
			if(config.size() > 0)
				if(config.contains(String.valueOf(choice)))
					button.setSelected(true);
			buttons[i] = button;
			
			button.addActionListener(e -> {
				if(button.isSelected())
					question.pushAnswer(button.getActionCommand());
				else
					question.popAnswer(button.getActionCommand());
			});
			answerPanel.add(button);
		}
		
		add(questionPanel);
		add(answerPanel);
	}
}
