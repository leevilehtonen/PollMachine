package com.pollmachine.main;

import java.util.HashMap;

public class Poll {
	
	private String question;
	private HashMap<String, Integer> answers;
	
	public Poll() {
		answers = new HashMap<String, Integer>();
	}
	public void setQuestion (String question) {
		this.question = question;
	}
	public void addAnswer (String answer) {
		answers.put(answer, 0);
	}
	public HashMap<String, Integer> getAnswers() {
		return answers;
	}
	public String getQuestion() {
		return question;
	}
	@Override
	public boolean equals(Object obj) {
		
		Poll objectToCOmpare = (Poll) obj;
		if(objectToCOmpare==null) {
			return false;
		}
		if(objectToCOmpare.getQuestion().equals(this.question)) {
			return true;
		} else {
			return super.equals(obj);
		}
		
	}
	@Override
	public int hashCode() {
		return this.question.hashCode() + this.getAnswers().size();
	}
}
