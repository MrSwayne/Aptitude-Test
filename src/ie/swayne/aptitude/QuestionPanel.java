package ie.swayne.IQ;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class QuestionPanel extends JPanel {

	private JLabel questionLabel;
	private JLabel questionPictureLabel;
	private JPanel questionPanel;
	private JPanel answerPanel;
	
	
	public QuestionPanel(Question question) {
		super(new GridLayout(3,1));
		System.out.println(question.toString());
		
		setSize(1280,720);
		
		answerPanel = new JPanel();
	
		questionPanel = new JPanel();
		
		questionLabel = new JLabel(question.getQuestion(), JLabel.CENTER);
		add(questionLabel);
		
		if(question instanceof PictureQuestion) {
			PictureQuestion pictureQuestion = (PictureQuestion) question;
			System.out.println(pictureQuestion.getPicture().toString());
			questionPictureLabel = new JLabel(pictureQuestion.getPicture(), JLabel.CENTER);
			Dimension dimensions = new Dimension(pictureQuestion.getPicture().getIconWidth() * 2,pictureQuestion.getPicture().getIconHeight() * 2);
			questionPictureLabel.setPreferredSize(dimensions);
			add(questionPictureLabel);
		}	
		
		if(question.getCorrectAnswers().length == 1)
			for(int i = 0;i < question.getAnswers().length;i++)
				answerPanel.add(new JButton(question.getAnswers()[i]));
		else
			for(int i = 0;i < question.getCorrectAnswers().length;i++) {
				JComboBox comboBox = new JComboBox(question.getAnswers());
				comboBox.setSelectedIndex(0);
				answerPanel.add(comboBox);
			}
		
		add(answerPanel);
	}
}
