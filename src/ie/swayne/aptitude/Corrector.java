package ie.swayne.IQ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Corrector {

	private int score;
	private String[] data;
	private IQTest test;
	private static ArrayList<ArrayList<String>> results;
	
	public Corrector(String[] data, IQTest test) {
		results = new ArrayList<ArrayList<String>>();
		this.data = data;
		this.test = test;
	}
	
	public void add(Question question, String[] answers) {	
		
	
		
		int questionNumber = question.getQuestionNumber();
		
		
		
		if(questionNumber > results.size()) {
			results.add(new ArrayList<String>());
			for(int i = 0;i < answers.length;i++)
				results.get(questionNumber - 1).add("");
		}	

			for(int i = 0;i < answers.length;i++)
				results.get(questionNumber - 1).set(i, answers[i]);
				
			
			System.out.println(results.get(questionNumber - 1)); 
		
		String[] correctAnswers = question.getCorrectAnswers();
		
		boolean correct = true;
		
		if(answers.length != correctAnswers.length)	correct = false;
		
		for(int i = 0;i < correctAnswers.length && correct;i++) {
			if(answers[i] == null) correct = false;
			if(!(correctAnswers[i].equals(answers[i]))) correct = false;
		}
		
		if(correct)	addToScore(question.getCategory());
	}
	
	private void addToScore(String category) {
		
	}
	
	public void printAll() {
		for(int i = 0;i < results.size();i++) {
			System.out.println((i + 1) + ". " + results.get(i));
		}
	}
	
	public static boolean hasBeenCreatedBefore(Question question) {
		if(question.getQuestionNumber() - 1 < results.size()) 
			return true;
		return false;
	}
	
	public static ArrayList<String> getConfig(Question question) {
		return results.get(question.getQuestionNumber() - 1);
	}

	public boolean writeToFile() {
		return writeToFile(new File("results.txt"));
	}
	
	//TODO
	public boolean writeToFile(File file) {
		try {
			PrintWriter aFileWriter = new PrintWriter(file);		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
}
