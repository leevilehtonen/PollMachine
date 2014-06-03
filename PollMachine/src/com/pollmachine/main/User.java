package com.pollmachine.main;

public class User {
	
	private String name;
	private int answeredPolls;
	private int createdPolls;
	
	public User(String name) {
		this.name = name;
		this.answeredPolls = 0;
		this.createdPolls = 0;
	}
	public String getName() {
		return name;
	}
	public void createPoll () {
		createdPolls++;
	}
	public void answerPoll () {
		answeredPolls++;
	}
	public int getAnsweredPolls() {
		return answeredPolls;
	}
	public int getCreatedPolls() {
		return createdPolls;
	}
}
