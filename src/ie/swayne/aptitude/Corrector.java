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
	private ArrayList<ArrayList<String>> results;
	private ArrayList<ArrayList<String>> correctAnswers;
	
	
	public Corrector(String[] data, IQTest test) {
		results = new ArrayList<ArrayList<String>>();
		correctAnswers = new ArrayList<ArrayList<String>>();
		this.data = data;
		this.test = test;
	}
	
	public void add(Question question, String[] answers) {
		int questionNumber = question.getQuestionNumber();
		
		if(questionNumber > results.size()) {
			System.out.println("New question found!");
			results.add(new ArrayList<String>());
			correctAnswers.add(new ArrayList<String>());
		}
		
		System.out.println("Adding question " + questionNumber);
		for(int i = 0;i < answers.length;i++)
			results.get(questionNumber - 1).set(i, answers[i]);
		
		for(int i = 0;i < question.getCorrectAnswers().length;i++) 
			correctAnswers.get(questionNumber - 1).set(i, question.getCorrectAnswers()[i]);
	}
	
	//TODO
	private int sum() {
		int sum = 0;
		
		
		
		return sum;
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
