package ie.swayne.IQ.New.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ie.swayne.IQ.New.test.*;
import ie.swayne.IQ.New.panels.*;
import ie.swayne.IQ.New.Frame;
import ie.swayne.IQ.New.test.IQTest;

public class TestMenu extends Menu {
	
	private IQTest test;
	private JPanel listPanel;
	private JPanel controlPanel;
	private JPanel answerPanel;
	private JPanel questionContainer;
	private QuestionPanel questionPanel;
	private String[] answers;
	private Question currentQuestion;
	private JLabel timerLabel;
	
	private int timeLeft = 30;
	private JPanel timerPanel;

	public TestMenu(Frame frame, IQTest test) {
		super(frame);
		this.test = test;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		timerPanel = new JPanel();
		questionContainer = new JPanel();
		questionPanel = generatePanel(true);
		answerPanel = new JPanel();
		controlPanel = new JPanel();
		
		
		timerPanel.add(new JLabel("Time Remaining: "));
		timerLabel = new JLabel("NaN", JLabel.CENTER);
		timerPanel.add(timerLabel);
		
		TimerTask timerTask = new TimerTask() {
			
			private int i = 1;	
			
			private String getFormattedTime(int minutes, int seconds) {
					
				//TODO
				//ADD HOURS
							
				String formattedTime = "hh:mm:ss";
				String split = ":";		
				
				if(seconds < 10) split += 0;		
				
				formattedTime = minutes + split + seconds;
				
				return formattedTime;
			}
			
			@Override
			public void run() {
				String formattedTime = getFormattedTime(timeLeft, --i);
				timerLabel.setText(String.valueOf(formattedTime));
				
				if(i == 0)	{
					i = 60;
					timeLeft--;
				}			
			}
		};
		
		Timer timer = new Timer("MyTimer");
		timer.scheduleAtFixedRate(timerTask, 30, 1000);
	
		
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("Back");
		backButton.addActionListener(new ButtonClickListener());
		controlPanel.add(backButton);
		
		JButton nextButton = new JButton("Next");
		nextButton.setActionCommand("Next");
		nextButton.addActionListener(new ButtonClickListener());
		controlPanel.add(nextButton);
		
		
		questionContainer.add(questionPanel);
		
		JScrollPane scrollPane = new JScrollPane(questionContainer);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
		add(timerPanel);
		add(scrollPane);
		//add(answerPanel);
		add(controlPanel);
	}
	
	public void updateQuestionPanel(boolean forwards) {
		
		System.out.println("updating display");
		questionContainer.remove(questionPanel);
		questionPanel = generatePanel(forwards);
		questionContainer.add(questionPanel);
		this.revalidate();
		this.repaint();
		
		
	}
	
	public QuestionPanel generatePanel(boolean forwards) {
		Question q = null;
		
		System.out.println(forwards + " " + test.hasNext());
		
		if(forwards) {
			if(test.hasNext())
				q = test.getNext();
		} else {
			if(test.hasPrevious())
				q = test.getPrevious();
		}
		
		return new QuestionPanel(q);
		
	}
	
	class ButtonClickListener implements ActionListener {
		
		public ButtonClickListener() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			switch(command) {
				
				case "Next": {
					test.setForwards(true);
					updateQuestionPanel(true); 
				} break;
				
				case "Back": {
					test.setForwards(false);
					updateQuestionPanel(false);
				} break;
			}
		}
	}

}
