package ie.swayne.IQ.New.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ie.swayne.IQ.New.*;
import ie.swayne.IQ.New.test.PictureQuestion;
import ie.swayne.IQ.New.test.Question;

public class SettingsQuestionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Question question;
	private JTextField questionField;
	private JTextField[] answers;
	private JCheckBox[] boxes;
	private JCheckBox pictureQuestionBox;
	private ImageIcon imagePicture;
	private JLabel pictureLabel;
	
	private JPanel containerPanel;
	private JPanel pictureSettingsPanel;
	
	
	
	private JButton saveButton;
	private boolean savePressed = true;
	
	
	public SettingsQuestionPanel(Question question) {
		super();
		this.question = question;
		
		initDefaultSetup();
		
		this.add(containerPanel);
	}
	
	public void initDefaultSetup() {
		
		containerPanel = new JPanel();
		JPanel answersPanel = new JPanel();
		pictureSettingsPanel = new JPanel();
		JPanel savePanel = new JPanel();
		
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		answers = new JTextField[question.getAnswers().length];
		boxes = new JCheckBox[answers.length];
		
		JPanel questionPanel = new JPanel(new FlowLayout());
		JLabel questionLabel = new JLabel("Question");
		questionField = new JTextField(question.getQuestion(), 22);
		questionField.getDocument().addDocumentListener(new DocEditListener());
		
		questionPanel.add(questionLabel);
		questionPanel.add(questionField);
		
		saveButton = new JButton("Save Changes");
		saveButton.addActionListener(new SaveButtonClickListener());
		savePanel.add(saveButton);
		
		JLabel answerLabel = new JLabel("Answers");
		answersPanel.add(answerLabel);
		for(int i = 0;i < answers.length;i++) {
			answers[i] = new JTextField(question.getAnswers()[i], 8);
			answers[i].getDocument().addDocumentListener(new DocEditListener());
			answersPanel.add(answers[i]);
			boxes[i] = new JCheckBox();
			boxes[i].addActionListener(new EditListener());
			answersPanel.add(boxes[i]);
		}
		
		String[] correctAnswers = question.getCorrectAnswers();
		
		for(int i = 0;i < correctAnswers.length;i++) {
			
			int ind = correctAnswers[i].charAt(0) - 'A';
			
			if(ind < boxes.length)
			boxes[ind].setSelected(true);
		}
		
		JPanel pictureQuestionPanel = new JPanel();
		JLabel pictureQuestionLabel = new JLabel("Is Picture Question?");
		pictureQuestionBox = new JCheckBox();
		pictureQuestionBox.addActionListener(new EditListener());
		pictureQuestionBox.addActionListener(new PictureQuestionBoxClick());
		pictureQuestionPanel.add(pictureQuestionLabel);
		pictureQuestionPanel.add(pictureQuestionBox);
		
		
		JButton button = new JButton("Choose New Image");
		button.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			
			int returnVal = chooser.showOpenDialog(SwingUtilities.getRoot(this));
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				
				try {
					if( ImageIO.read(file) != null) {
						String extension = "png";
						
						int i = file.getName().lastIndexOf('.');
						if(i > 0)
							extension = file.getName().substring(i+1);
						
						String destPath = question.getQuestionNumber() + "q." + extension;
						
						File destination = new File(Globals.get("imgFolder"), destPath);
						
						if(Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING) == null)
							JOptionPane.showMessageDialog(null, "Error moving file, please try again");
						else {
							updateQuestion();
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect file type supplied!");
					}
				} catch(IOException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
			
			});
		
		if(question instanceof PictureQuestion) {	
			PictureQuestion q = (PictureQuestion) question;
			pictureLabel = new JLabel(getScaledImage(q.getPicture()), JLabel.CENTER);
			pictureSettingsPanel.add(pictureLabel);
			
			pictureQuestionBox.setSelected(true);
		}
		
		pictureSettingsPanel.setVisible(pictureQuestionBox.isSelected()? true: false);
		
		pictureSettingsPanel.add(button);
		
		containerPanel.removeAll();
		containerPanel.add(questionPanel);
		containerPanel.add(answersPanel);
		containerPanel.add(pictureQuestionPanel);	
		containerPanel.add(pictureSettingsPanel);
		containerPanel.add(savePanel);
		savePressed = true;
	}
	
	public void reset() {
		this.removeAll();
		initDefaultSetup();
		this.add(containerPanel);
		this.revalidate();
		this.repaint();
	}
	
	private void updateQuestion() {
		if(!(question instanceof PictureQuestion)) {
			question = new PictureQuestion(question);
			
			pictureLabel = new JLabel(PictureQuestion.getScaledImage(((PictureQuestion) question).getPicture()), JLabel.CENTER);
			pictureSettingsPanel.add(pictureLabel);
			this.revalidate();
			this.repaint();
		}
	}
	
	private ImageIcon getScaledImage(ImageIcon img) {
		int w = 120;
		int h = 120;
		
		Image temp = img.getImage();
		Image newImg = temp.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);
		return img;
	}
	
	public void save() {
		savePressed = true;
	}
	
	private void editMade() {
		System.out.println("Edit");
		savePressed = false;
	}
	
	public boolean isEdited() {
		return !savePressed;
	}
	
	private class DocEditListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			editMade();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			editMade();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			editMade();		
		}

	}
	
	private class EditListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			editMade();
		}
	}
	
	private class SaveButtonClickListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			savePressed = true;
			System.out.println("save");
		}
	}

	private class PictureQuestionBoxClick implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox cb = (JCheckBox)e.getSource();
			if(cb.isSelected()) {
				pictureSettingsPanel.setVisible(true);
			} else
				pictureSettingsPanel.setVisible(false);
		}
		
	}

}
