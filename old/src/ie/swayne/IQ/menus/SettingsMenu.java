package ie.swayne.IQ.menus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ie.swayne.IQ.Frame;
import ie.swayne.IQ.panels.SettingsQuestionPanel;
import ie.swayne.IQ.test.*;

public class SettingsMenu extends Menu {

	private IQTest test;
	private Question[] options;
	private JComboBox<Question> questionsBox;
	private SettingsQuestionPanel questionPanel;
	private JPanel questionContainer;
	private JPanel comboBoxPanel;
	private JPanel controlPanel;
	private ArrayList<SettingsQuestionPanel> dataPanels;
	private int oldInd;
	private int index;
	private boolean changeMade = false;
	
	
	
	public SettingsMenu(Frame frame, IQTest test) {
		super(frame);
		this.test = test;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		test.restart();
		
		questionContainer = new JPanel();
		comboBoxPanel = new JPanel();
		controlPanel = new JPanel();
		
		options = test.getArray();
		dataPanels = new ArrayList<>();
		
		for(int i = 0;test.hasNext();i++) {
			Question q = test.getNext();
			System.out.println(q.getQuestion());
			
			dataPanels.add(new SettingsQuestionPanel(q));
		}	test.restart();
		
		
		questionsBox = new JComboBox<>(options);
		
		questionPanel = dataPanels.get(questionsBox.getSelectedIndex());
		
		questionsBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {															
				int returnVal = -1;
				
		        JComboBox<Question> cb = (JComboBox<Question>)e.getSource();
		        index = cb.getSelectedIndex();
		        
		        updateQuestionPanel(dataPanels.get(index));
		        
		        
		        String[] opts = {"Save And Exit", "Continue Without Saving", "Cancel"};
		        if(dataPanels.get(oldInd).isEdited()) {
		        	returnVal = JOptionPane.showOptionDialog(null, 
		        			"Warning: You have changes that need to be saved, any unsaved changes will be lost",
		        			"Unsaved Changes",
		        			JOptionPane.YES_NO_OPTION,
		        			JOptionPane.QUESTION_MESSAGE,
		        			null,
		        			opts,
		        			opts[0]
		        			);
		        	
		        	if(returnVal == 1)	{			        		
		        		dataPanels.get(oldInd).reset();
		        		dataPanels.get(index);	
		        	} else if(returnVal == 0) 
		        		dataPanels.get(index).save();
		        	else {
		        		cb.setSelectedIndex(oldInd);
		        		dataPanels.get(oldInd);
		        	}
		        } else dataPanels.get(index);
		        
		        oldInd = index;
			}
		});

		
		
		
		JButton backButton = new JButton("back");
		backButton.setActionCommand("Login");
		backButton.addActionListener(new ButtonClickListener());
		controlPanel.add(backButton);
		
		
		questionsBox.setMaximumSize(backButton.getPreferredSize());
		
		JButton addButton = new JButton("+");
		addButton.setActionCommand("Add");
		addButton.addActionListener(new ButtonClickListener());
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("Delete");
		deleteButton.addActionListener(new ButtonClickListener());
			
		
		comboBoxPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteButton.getHeight()));
		
		comboBoxPanel.add(questionsBox);
		comboBoxPanel.add(addButton);
		comboBoxPanel.add(deleteButton);
		questionContainer.add(questionPanel);
		
		
		add(comboBoxPanel);
		add(questionContainer);	
		add(controlPanel);
	}
	
	public void updateQuestionPanel(SettingsQuestionPanel p) {
		questionContainer.remove(questionPanel);
		questionPanel = p;
		questionContainer.add(questionPanel);
		revalidate();
		repaint();
	}
	
	public void addNewQuestion() {
		int ind = questionsBox.getItemCount();
		Question q = new Question(null);
		questionsBox.addItem(q);
		dataPanels.add(new SettingsQuestionPanel(q));
		questionsBox.setSelectedIndex(ind);
		changeMade = true;
	}
	
	public void deleteCurrentQuestion() {
		String[] opts = {"Yes", "No"};
		
    	int returnVal = JOptionPane.showOptionDialog(null, 
    			"Are you sure you want to delete this question? This CANNOT BE UNDONE",
    			"Are you Sure?",
    			JOptionPane.YES_NO_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,
    			opts,
    			opts[0]
    			);
    	
    	if(returnVal == 0) {
    		changeMade = true;
    		questionsBox.removeItemAt(questionsBox.getSelectedIndex());
    		dataPanels.remove(questionsBox.getSelectedIndex());
    		this.revalidate();
    		this.repaint();
    	}
	}
	
	public void backButtonHandler() {
		JButton button = new JButton();
		button.addActionListener(new Frame.ButtonClickListener(frame));
		button.setActionCommand("Main");
		button.doClick();
	}
	
	class ButtonClickListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			
			switch(command) {
			
			case "Login": backButtonHandler(); break;
			case "Add": addNewQuestion(); break;
			case "Delete": deleteCurrentQuestion(); break;
			}
		}
	}

}
