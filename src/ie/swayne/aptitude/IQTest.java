package ie.swayne.IQ;

import java.util.*;
import java.io.*;

class IQTest {
	private static ArrayList<Question> questions;
	private ArrayList<String> categories;
	private File questionsFile;
	private File picturesFile;
	private int index;

	public IQTest(File questionsFile, File picturesFile) {
		questions = new ArrayList<Question>();
		this.questionsFile = questionsFile;
		this.picturesFile = picturesFile;
	}

	public void init() {
		System.out.println("Initialising IQTest");
		getQuestions();
	}

	private void getQuestions() {
		try {
			ArrayList<PictureQuestion> pictureQuestions = new ArrayList<PictureQuestion>();

			FileReader 	aFileReader = new FileReader(picturesFile);
			Scanner in = new Scanner(aFileReader);
			
			
			while(in.hasNext()) {
				String line = in.nextLine();
				if(!(line.substring(0,2).equals("!!"))) {
					PictureQuestion question = new PictureQuestion(line);
					pictureQuestions.add(question);
				}
			}
			
			aFileReader = new FileReader(questionsFile);
			in = new Scanner(aFileReader);
			
			while(in.hasNext()) {
				String line = in.nextLine();
				if(!(line.substring(0, 2).equals("!!"))) {
					Question question = new Question(line);										
					questions.add(question);
				}
			}
			
			for(int i = 0;i < pictureQuestions.size();i++) {
				PictureQuestion pictureQuestion = pictureQuestions.get(i);
				
				Question question = questions.get(pictureQuestion.getQuestionNumber() - 1);
				pictureQuestion.parse(question);
				
				questions.set(pictureQuestion.getQuestionNumber() - 1, pictureQuestion);
				//iterateQuestions();
			}	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	private void iterateQuestions() {
		for(int i = 0;i < questions.size();i++) {
			System.out.println(i + ": " + (questions.get(i) instanceof PictureQuestion));
		}
	}
	
	public Question get(int i) {
		i--;
		if(i >= 0 && i < questions.size()) {
			return questions.get(i);
		}
		return null;
	}

	public Question getNext() {
		return questions.get(index++);
	}
	
	public Question getPrevious() {
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
