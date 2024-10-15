package com.practice.model;

public class Question {
	private String question;
    private String correctAnswer;
	public String getQuestion() {
		return question;
	}
	
	public Question(String question, String correctAnswer) {
		super();
		this.question = question;
		this.correctAnswer = correctAnswer;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
