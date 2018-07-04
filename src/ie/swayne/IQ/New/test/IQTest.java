package ie.swayne.IQ.New.test;

import java.util.*;
import java.io.*;

public class IQTest {
	private ArrayList<Question> questions;
	private ArrayList<String> categories;
	private File questionsFile;
	private File picturesFile;
	private boolean goingForwards;
	private int index;

	public IQTest(File questionsFile, File picturesFile) {
		questions = new ArrayList<Question>();
		categories = new ArrayList<String>();
		this.questionsFile = questionsFile;
		goingForwards = true;
		this.picturesFile = picturesFile;
		init();
	}

	public void init() {
		try {
			ArrayList<PictureQuestion> pictureQuestions = new ArrayList<PictureQuestion>();

			FileReader 	aFileReader = new FileReader(picturesFile);
			Scanner in = new Scanner(aFileReader);
			
			
			while(in.hasNext()) {
				String line = in.nextLine();
				if(!(line.startsWith("!!"))) {
					PictureQuestion question = new PictureQuestion(line);
					pictureQuestions.add(question);
				}
			}
			
			aFileReader = new FileReader(questionsFile);
			in = new Scanner(aFileReader);
			
			while(in.hasNext()) {
				String line = in.nextLine();
				if(!(line.startsWith("!!"))) {
					Question question = new Question(line);										
					questions.add(question);
				}
			}
			
			for(int i = 0;i < pictureQuestions.size();i++) {
				PictureQuestion pictureQuestion = pictureQuestions.get(i);
				
				Question question = questions.get(pictureQuestion.getQuestionNumber() - 1);
				pictureQuestion.parse(question);
				
				questions.set(pictureQuestion.getQuestionNumber() - 1, pictureQuestion);
			}	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public void save() {
		
	}
	
	public void restart() {
		index = 0;
		goingForwards = true;
	}
	
	public void setForwards(boolean b) {
		
		if(b == true && goingForwards == false)
			index++;
		else if(b == false && goingForwards == true)
			index--;

		goingForwards = b;
	}
	
	public void set(int i, Question q) {
		questions.set(i, q);
	}

	public Question get(int i) {
		i--;
		if(i >= 0 && i < questions.size()) {
			return questions.get(i);
		}
		return null;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean isGoingForwards() {
		return goingForwards;
	}

	public Question getNext() {
		goingForwards = true;
		return questions.get(index++);
	}
	
	public Question getPrevious() {
		goingForwards = false;
		return questions.get(--index);
	}
	public boolean hasNext() {
		return index < questions.size();
	}
	
	public boolean hasPrevious() {
		return index > 0;
	}

	public int getNumberOfQuestions() {
		return questions.size();
	}
}
