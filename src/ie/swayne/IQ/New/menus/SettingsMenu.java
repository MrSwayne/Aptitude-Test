package ie.swayne.IQ.New.menus;

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

import ie.swayne.IQ.New.test.*;
import ie.swayne.IQ.New.panels.SettingsQuestionPanel;
import ie.swayne.IQ.New.Frame;
import ie.swayne.IQ.New.test.IQTest;

public class SettingsMenu extends Menu {

	private IQTest test;
	private String[] options;
	private SettingsQuestionPanel questionPanel;
	private JPanel comboBoxPanel;
	private JPanel controlPanel;
	private ArrayList<SettingsQuestionPanel> dataPanels;
	private int oldInd;
	
	
	public SettingsMenu(Frame frame, IQTest test) {
		super(frame);
		this.test = test;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		test.restart();
		comboBoxPanel = new JPanel();
		controlPanel = new JPanel();
		
		options = new String[test.getNumberOfQuestions()];
		dataPanels = new ArrayList<>();
		
		for(int i = 0;i < test.getNumberOfQuestions() && test.hasNext();i++) {
			Question q = test.getNext();
			
			dataPanels.add(new SettingsQuestionPanel(q));
			options[i] = q.getQuestion();
		}
		
		
		JComboBox<String> questionsBox = new JComboBox<>(options);
		questionPanel = dataPanels.get(questionsBox.getSelectedIndex());
		
		questionsBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {															
				int returnVal = -1;
				
		        JComboBox cb = (JComboBox)e.getSource();
		        int index = cb.getSelectedIndex();
		        
		        
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
		
		
		//comboBoxPanel.setMaximumSize(questionsBox.getPreferredSize());
		
		
		
		JButton backButton = new JButton("back");
		backButton.setActionCommand("Login");
		backButton.addActionListener(new Frame.ButtonClickListener(frame));
		controlPanel.add(backButton);
		
		JButton addButton = new JButton("+");
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("Delete");
		deleteButton.addActionListener(new ButtonClickListener());
			
		
		comboBoxPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteButton.getHeight()));
		
		comboBoxPanel.add(questionsBox);
		comboBoxPanel.add(addButton);
		comboBoxPanel.add(deleteButton);
		
		add(comboBoxPanel);
		add(questionPanel);	
		add(controlPanel);
	}
	
	public void updateQuestionPanel(SettingsQuestionPanel p) {
		remove(questionPanel);
		questionPanel = p;
		add(questionPanel);
		revalidate();
		repaint();
	}
	
	class ButtonClickListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
		}
	}

}
