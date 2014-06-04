package com.pollmachine.main;

import java.util.ArrayList;

public class User {
	
	private String name;
	private int createdPolls;
	private ArrayList<Poll> answeredPolls;
	
	
	public User(String name) {
		this.name = name;
		this.answeredPolls = new ArrayList<Poll>();
		this.createdPolls = 0;
	}
	public String getName() {
		return name;
	}
	public void createPoll () {
		createdPolls++;
	}
	public void answerPoll (Poll pool) {
		answeredPolls.add(pool);
	}
	public int getAnsweredPollsAmount() {
		return answeredPolls.size();
	}
	public int getCreatedPolls() {
		return createdPolls;
	}
	public ArrayList<Poll> getAnsweredPolls() {
		return answeredPolls;
	}
}
