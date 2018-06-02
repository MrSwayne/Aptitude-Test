/**
 * 
 */
package ie.swayne.IQ;


/* TODO
 *  Add weights for each individual question
 */



class Question {

	protected String question;
	protected String[] answers;
	protected String[] correctAnswers;
	protected String category;
	protected int questionNumber;
	protected static int numQuestions;

	public Question(String rawString) {
		String[] data = rawString.split("\"");
		
		this.questionNumber = Integer.parseInt(data[0]);
		this.category = data[1];
		this.question = data[2];
		this.answers = data[3].split("-");
		this.correctAnswers = data[4].split(" ");
	}
	
	protected Question() {
		
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
		return questionNumber;
	}
	
	public String toString() {
		
		String line = "";
		for(int i = 0;i < answers.length;i++) {
			line += answers[i] + " ";
		}
		
		return this.questionNumber + " " + this.getQuestion() + this.getCategory() + " " + line;
	}

}
