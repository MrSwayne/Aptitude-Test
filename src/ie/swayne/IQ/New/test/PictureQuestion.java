package ie.swayne.IQ.New.test;

import java.awt.Image;
import java.util.ArrayList;
import ie.swayne.IQ.New.*;

import javax.swing.ImageIcon;

public class PictureQuestion extends Question {
	
	private ImageIcon questionPicture;
	private String path;
	
	public PictureQuestion(String rawString) {
		super();
		String[] data = rawString.split("\"");
		this.questionNumber = Integer.parseInt(data[0]);
		
		this.path = Globals.get("imgFolder") + this.questionNumber + "q.png";
		this.questionPicture = new ImageIcon(path);
		this.selectedAnswers = new ArrayList<String>();
	}
	
	public PictureQuestion(Question q) {
		super();
		parse(q);
	}
	
	public ImageIcon getPicture() {
		System.out.println(path);
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
	
	public static ImageIcon getScaledImage(ImageIcon img) {
		int w = 120;
		int h = 120;
		
		Image temp = img.getImage();
		Image newImg = temp.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);
		return img;
	}
}