package ie.swayne.IQ;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class QuestionMenu extends JFrame {


	
	private IQTest test;
	
	private boolean finished = false;
	
	private JPanel listPanel;
	private JPanel controlPanel;
	private JPanel answerPanel;
	private QuestionPanel questionPanel;
	private String[] answers;
	private Question currentQuestion;
	
	private Corrector corrector;
	
	
	public QuestionMenu(IQTest test, String TITLE, int WIDTH, int HEIGHT, String[] data) {
		super(TITLE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		//The corrector corrects the test
		corrector = new Corrector(data, test);
		
		answerPanel = new JPanel();
		controlPanel = new JPanel();
		
		this.test = test;
		setSize(WIDTH /2, HEIGHT /2);
	
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		setLocationRelativeTo(null);
		
		//Passing true means that it's the first question to be created
		generatePanel(true);
		
		
		//Setting up standard next and back buttons
		JButton nextButton = new JButton("Next");
		JButton backButton = new JButton("Back");
		
		nextButton.setActionCommand("Next");
		backButton.setActionCommand("Back");
		
		nextButton.addActionListener(e -> {
			
			if(finished) {
				
				corrector.add(currentQuestion, questionPanel.getAnswers());
				destroy();													 //If it's the last question of the test, destroy the window
				corrector.printAll();
			}
		
			corrector.add(currentQuestion, questionPanel.getAnswers());		//Add the current question to be corrected
			
			
			
			if(!test.isGoingForwards()) test.setForwards(true);		//Pressing next means the test is going forward
			
			generatePanel(false);									//Generate the next panel, false indicating it's not the first panel
			
			if(!test.hasNext()) {
				nextButton.setText("Finish"); 						//Minor change to let the user know the test is finished
				finished = true;
			}
		});
		
		backButton.addActionListener(e -> {	
			
			corrector.add(currentQuestion, questionPanel.getAnswers()); 	//Correct the current question
			if(test.isGoingForwards()) test.setForwards(false);				//If you press the back button, the test is now going backwards
			generatePanel(false);
		});
		
		controlPanel.add(backButton);
		controlPanel.add(nextButton);
		
		add(answerPanel);
		add(controlPanel);	
		setVisible(true);
	}
	
	private void generatePanel(boolean isFirstPanel) {
		
		
		//If test is going forwards, generate the next panel
		if(test.isGoingForwards()) {
			if(test.hasNext())		
				 currentQuestion = test.getNext();
		}
		
		//If test is going backwards, generate the previous panel
		else {
			if(test.hasPrevious())		
				currentQuestion = test.getPrevious();	
		}
		
		
		
		//If the test is still ongoing, remove the current panel and add the next/previous panel
		if(test.hasNext() || test.hasPrevious()) {
			
			
			//Otherwise it will shit itself trying to remove a panel that doesn't exist
			if(!isFirstPanel)	remove(questionPanel);
			
			
			questionPanel = new QuestionPanel(currentQuestion);
			add(questionPanel,0,0);									//Add the question to the top of the panel
			QuestionMenu.this.revalidate();							//Update the display to the user
		}
	}

	
	//When the test is over, close the menu
	private void destroy() {
		
		MainMenu.IS_RUNNING = false;
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
	
