package ie.swayne.IQ;

import javax.swing.ImageIcon;

class PictureQuestion extends Question {
	
	private ImageIcon questionPicture;
	
	public PictureQuestion(String rawString) {
		String[] data = rawString.split("\"");
		this.questionNumber = Integer.parseInt(data[0]);
		this.questionPicture = new ImageIcon(data[1]);
	}
	
	public ImageIcon getPicture() {
		return this.questionPicture;
	}
	
	public void parse(Question q) {
		this.questionNumber = q.getQuestionNumber();
		this.category = new String(q.getCategory());
		this.question = new String(q.getQuestion());
		this.answers = q.getAnswers();
		this.correctAnswers = q.getCorrectAnswers();
	}
}