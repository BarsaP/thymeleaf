package com.practice.model;

import java.util.List;

public class Question {
	private String question;
    private String correctAnswer;
    private List<String> options;
    
	
	public Question(String question, String correctAnswer, List<String> options) {
		super();
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.options = options;
	}
    
	public String getQuestion() {
		return question;
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

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
	
}
