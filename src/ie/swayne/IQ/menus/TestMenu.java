package ie.swayne.IQ.menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ie.swayne.IQ.Frame;
import ie.swayne.IQ.Globals;
import ie.swayne.IQ.panels.*;
import ie.swayne.IQ.test.*;

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
	private Timer timer = new Timer("MyTimer");
	private Frame frame;
	private JButton nextButton;
	
	private int timeLeft = Integer.parseInt(Globals.get("testTimer"));
	private JPanel timerPanel;

	public TestMenu(Frame frame, IQTest test) {
		super(frame);
		this.test = test;
		this.frame = frame;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		timerPanel = new JPanel();
		questionContainer = new JPanel();
		
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
				
				if(timeLeft == 0 && i == 0)	{ 
					testEndHandler("Time");
				}
				
				if(i == 0)	{
					i = 60;
					timeLeft--;
				}	
			}
		};
		
		timer.scheduleAtFixedRate(timerTask, 30, 1000);
	
		
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("Back");
		backButton.addActionListener(new ButtonClickListener());
		controlPanel.add(backButton);
		
		nextButton = new JButton("Next");
		nextButton.setActionCommand("Next");
		nextButton.addActionListener(new ButtonClickListener());
		controlPanel.add(nextButton);
		
		questionPanel = generatePanel();
		questionContainer.add(questionPanel);
		
		JScrollPane scrollPane = new JScrollPane(questionContainer);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    timerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, timerLabel.getHeight()));
	    scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, timerLabel.getHeight() * 10));
	    
		add(timerPanel);
		add(scrollPane);
		//add(answerPanel);
		add(controlPanel);
	}
	
	protected void testEndHandler(String msg) {
		test.end();
		
		if(msg.equalsIgnoreCase("button")) {
			JOptionPane.showMessageDialog(null, "");
		}

		
		
		if(msg.equalsIgnoreCase("time")) {
			timer.cancel();
			JOptionPane.showConfirmDialog(null, "Unfortunately you ran out of time!");
			frame.setMenu("End");
		}
		
	}

	public void updateQuestionPanel() {
		QuestionPanel temp;
		
		
		
		temp = generatePanel();
		
		if(temp != null) {
			questionContainer.remove(questionPanel);
			questionPanel = temp;
			questionContainer.add(questionPanel);
			this.revalidate();
			this.repaint();
		} else if(temp == null && test.getIndex() > 0) {
			JOptionPane.showMessageDialog(null, "Are you sure you want to finish the test?");
		}
		
		
	}
	
	public QuestionPanel generatePanel() {
		Question q = new Question(null);
		
		
		boolean isLastQuestion = (test.getIndex() == (test.getNumberOfQuestions() - 2));
		if(isLastQuestion) {
			nextButton.setText("Finish");
		} else
			nextButton.setText("next");
		
		if(test.hasNext()) {
			q = test.getNext();
			return new QuestionPanel(q, test.getIndex());
		} else if(test.isFinished()) 
			testEndHandler("button");
		
		return null;
		
	}
	
	class ButtonClickListener implements ActionListener {
		
		public ButtonClickListener() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			switch(command) {
				
				case "Next": {
					test.setForwards(true);
					updateQuestionPanel(); 
				} break;
				
				case "Back": {
					test.setForwards(false);
					updateQuestionPanel();
				} break;
			}
		}
	}

}
