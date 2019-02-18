package ie.swayne.test.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class TestQuestion implements Serializable, Comparable {
	
	protected String question;
	protected String[] answers;
	protected String[] correctAnswers;
	protected Stack<String> selectedAnswers;
	protected String category;
	protected int marks;
	
	public TestQuestion(String rawString) {
		
		String[] data = rawString.split("\"");
		this.category = data[0];
		this.question = data[1];
		this.answers = data[2].split("-");
		this.correctAnswers = data[3].split("-");
		selectedAnswers = new Stack<String>();
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	
	public String[] getAnswers() {
		return this.answers;
	}
	
	public void setCorrectAnswers(String[] correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
	public String[] getCorrectAnswers() {
		return this.correctAnswers;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public Stack<String> getSelectedAnswers() {
		return this.selectedAnswers;
	}
	
	public void pushAnswer(String ans) {
		if(!selectedAnswers.contains(ans))
			selectedAnswers.push(ans);
	}
	
	public void popAnswer(String ans) {
		if(selectedAnswers.contains(ans))
			selectedAnswers.remove(ans);
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public int getMarks() {
		return this.marks;
	}
	
	public String toString() {
		return this.getClass().getName() + "," + this.category + "," + this.question;
	}

	@Override
	public int compareTo(Object obj) {
		return(this.toString().compareTo(obj.toString()));
	}
}
