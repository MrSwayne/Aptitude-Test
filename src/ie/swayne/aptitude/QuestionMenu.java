package ie.swayne.IQ;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class QuestionMenu extends JFrame {


	
	private IQTest test;
	
	
	private JPanel listPanel;
	private JPanel controlPanel;
	private JPanel answerPanel;
	private QuestionPanel questionPanel;
	
	
	public QuestionMenu(IQTest test, String TITLE, int WIDTH, int HEIGHT) {
		super(TITLE);
		//listPanel = new JPanel();
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		answerPanel = new JPanel();
		controlPanel = new JPanel();
		
		
		this.test = test;
		setSize(WIDTH /2, HEIGHT /2);
	
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		

		Question q = test.getNext();
		questionPanel = new QuestionPanel(q);
		questionPanel.setVisible(true);
	
		
		JButton nextButton = new JButton("Next");
		JButton backButton = new JButton("Back");
		
		nextButton.setActionCommand("Next");
		backButton.setActionCommand("Back");
		
		nextButton.addActionListener(e -> {
			
			if(test.hasNext()) {
				Question question = test.getNext();
				
				remove(questionPanel);
				questionPanel = new QuestionPanel(question);
				add(questionPanel,0,0);
				QuestionMenu.this.revalidate();
				
			}
		});
		
		
		backButton.addActionListener(e -> {
			
			if(test.hasPrevious()) {
				Question question = test.getPrevious();
				
				remove(questionPanel);
				questionPanel = new QuestionPanel(question);
				add(questionPanel,0,0);
				QuestionMenu.this.revalidate();
			}
			
		});
		
		controlPanel.add(backButton);
		controlPanel.add(nextButton);
		
		add(questionPanel);
		add(answerPanel);
		add(controlPanel);

		setVisible(true);	
	}
}
	
