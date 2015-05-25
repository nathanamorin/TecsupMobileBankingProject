package BankServices.modelo;



public class Transfer {

	private int idQuestion;
	private String question;
	private String answer;
	
	
	public Transfer(int idQuestion, String question, String answer) {
		super();
		this.idQuestion = idQuestion;
		this.question = question;
		this.answer = answer;
	}
	
	
	public Transfer() {
		super();
	}


	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}




}
