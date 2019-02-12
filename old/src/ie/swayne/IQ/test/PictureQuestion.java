package ie.swayne.IQ.test;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ie.swayne.IQ.*;

public class PictureQuestion extends Question {
	
	private ImageIcon questionPicture;
	private String path;
	
	public PictureQuestion(String rawString) {
		String[] data = rawString.split("\"");
		this.questionNumber = Integer.parseInt(data[0]);
		
		this.path = Globals.get("imgFolder") + this.questionNumber + "q.png";
		this.questionPicture = getScaledImage(new ImageIcon(path), 120, 120);
		this.selectedAnswers = new ArrayList<String>();
	}
	
	public PictureQuestion(Question q) {
		parse(q);
	}
	
	public PictureQuestion(String category, String question, String answers, String correctAnswers, String url, int mark) {
		this.mark = mark;
		this.path = url;
		this.questionPicture = getScaledImage(new ImageIcon(path), 120, 120);
		this.selectedAnswers = new ArrayList<String>();
	}

	public ImageIcon getPicture() {
		return this.questionPicture;
	}
	
	public void parse(Question q) {
		this.questionNumber = q.getQuestionNumber();
		this.category = new String(q.getCategory());
		this.question = new String(q.getQuestion());
		this.answers = q.getAnswers();
		this.path = Globals.get("imgFolder") + this.questionNumber + "q.png";
		this.correctAnswers = q.getCorrectAnswers();
		this.questionPicture = new ImageIcon(path);
	}
	
	public static ImageIcon getScaledImage(ImageIcon img, int w, int h) {
		
		Image temp = img.getImage();
		Image newImg = temp.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);
		return img;
	}
}