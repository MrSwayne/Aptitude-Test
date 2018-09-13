package ie.swayne.IQ.test;

import java.util.*;

import ie.swayne.IQ.SQL.SQLHelper;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IQTest {
	
	private SQLHelper helper;
	
	private ArrayList<Question> questions;
	private ArrayList<String> categories;
	private File questionsFile;
	private File picturesFile;
	private boolean goingForwards;
	private int index = -1;
	private int offset = 1;
	private boolean isFinished = false;

	public IQTest(SQLHelper helper) {
		this.helper = helper;
		init();
	}
	
	public SQLHelper getHelper() {
		return helper;
	}
	
	
	public void init()  {
		System.out.println("INIT");
		List<Map<String,String>> resultList = helper.readQuestions();
		questions = new ArrayList<Question>();
		
		for(int i = 0;i < resultList.size();i++) {
			Question q;
			String category = resultList.get(i).get("category");
			String question = resultList.get(i).get("question");
			String answers = resultList.get(i).get("answers");
			String correctAnswers = resultList.get(i).get("correctAnswers");
			String url = resultList.get(i).get("url");
			int mark = Integer.parseInt(resultList.get(i).get("mark"));
			
			if(url.equalsIgnoreCase("null")) {
				q = new Question(category, question, answers, correctAnswers, mark);
			} else {
				q = new PictureQuestion(category, question, answers, correctAnswers, url, mark);
			}
			questions.add(q);
		}
	}
	
	public void save() {
		
	}
	
	public void restart() {
		index = -1;
		goingForwards = true;
		isFinished = false;
	}
	
	public void setForwards(boolean b) {
		if(b) offset = 1;
		else offset = -1;
		
		goingForwards = offset > 0? true: false;
	}
	
	public void set(int i, Question q) {
		questions.set(i, q);
	}
	
	public void remove(Question q) {
		questions.remove(q);
	}
	
	public Question[] getArray() {
		return questions.toArray(new Question[questions.size()]);
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
		if(offset > 0) 	return true;
		else 			return false;
	}

	public Question getNext() {	
		
		index = index + offset;
		
		return questions.get(index);
	}
	
	public boolean hasNext() {
		boolean b;
		if(offset > 0)
			b = (index < questions.size() - 1 && !isFinished);
		else
			b = (index > 0 && !isFinished);	
		return b;
	}
	
	public void end() {
		isFinished = true;
	}
	
	public boolean isFinished() {
		return isFinished;
	}

	public int getNumberOfQuestions() {
		return questions.size();
	}
}
