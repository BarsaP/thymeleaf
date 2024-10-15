package com.practice.model;

public class AnswerResponse {
	private String type;
    private String playerName;
    
    
	public AnswerResponse(String type, String playerName) {
		super();
		this.type = type;
		this.playerName = playerName;
	}
	public AnswerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
