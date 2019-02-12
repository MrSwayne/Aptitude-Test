/**
 * 
 */
package ie.swayne.IQ.test;

import java.util.ArrayList;

/* TODO
 *  Add weights for each individual question
 */



public class Question {

	protected static ArrayList<Question> questions = new ArrayList<Question>();
	protected String question;
	protected String[] answers;
	protected String[] correctAnswers;
	protected ArrayList<String> selectedAnswers;
	protected String category;
	protected int questionNumber;
	protected static int numQuestions;
	protected boolean isCreated = false;
	protected int mark;

	public Question(String rawString) {
		
		
		if(rawString != null) {
		
			String[] data = rawString.split("\"");
			
			this.questionNumber = Integer.parseInt(data[0]);
			this.category = data[1];
			this.question = data[2];
			this.answers = data[3].split("-");
			this.correctAnswers = data[4].split(" ");
			selectedAnswers = new ArrayList<String>();
			questions.add(this);
		} else {
			this.questionNumber = -1;
			category = "NULL";
			question = "NULL";
			answers = new String[4];
			correctAnswers = new String[2];
			selectedAnswers = new ArrayList<String>();
			
			
			for(int i = 0;i < answers.length;i++)
				answers[i] = "NULL";
			for(int i = 0;i < correctAnswers.length;i++)
				correctAnswers[i] = "NULL";
			
			questions.add(this);
		}
	}
	
	protected Question() {
		questions.add(this);
	}
	
	public Question(String category, String question, String answers, String correctAnswers, int mark) {
		this.category = category;
		this.question = question;
		this.answers = answers.split("-");
		this.correctAnswers = correctAnswers.split(" ");
		this.mark = mark;
		selectedAnswers = new ArrayList<String>();
		questions.add(this);
	}
	
	public void update(String category, String question, String answers, String correctAnswers, int mark) {
		this.category = category;
		this.question = question;
		this.answers = answers.split("-");
		this.correctAnswers = correctAnswers.split(" ");
		this.mark = mark;
	}

	public void setQuestionNumber(int i) {
		this.questionNumber = i;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String[] getAnswers() {
		return answers;
	}
	
	public String[] getCorrectAnswers() {
		return correctAnswers;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getQuestionNumber() {
		return -1;
	}
	
	public void setCreated(boolean b) {
		isCreated = b;
	}
	
	public boolean isCreated() {
		return isCreated;
	}
	
	public void add(String ans) {
		
		if(!(selectedAnswers.contains(ans)))
			selectedAnswers.add(ans);
	}
	
	public void remove(String ans) {
		if(selectedAnswers.contains(ans))
			selectedAnswers.remove(ans);
	}
	
	public int getMark() {
		return mark;
	}
	
	
	public ArrayList<String> getConfig() {
		return selectedAnswers;
	}
	
	public String toString() {
		
		String line = "";
		for(int i = 0;i < answers.length;i++) {
			line += answers[i] + " ";
		}
		
		String line2 = "";
		for(int i = 0;i < selectedAnswers.size();i++) {
			line2 += selectedAnswers.get(i) + ",";
		}
		
		return this.getQuestion();
	}

}
