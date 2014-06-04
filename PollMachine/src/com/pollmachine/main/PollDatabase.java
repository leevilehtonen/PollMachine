package com.pollmachine.main;

import java.util.ArrayList;
import java.util.Iterator;

public class PollDatabase {

	private ArrayList<Poll> polls;
	private ArrayList<Poll> pollsSkipped;
	private User user;

	public PollDatabase(User user) {
		this.polls = new ArrayList<Poll>();
		this.pollsSkipped = new ArrayList<Poll>();
		this.user = user;
	}

	public Poll getRecent(int i) {
		
		ArrayList<Poll> nonAnsweredPolls = getRemainingPolls();
		
		int get = nonAnsweredPolls.size()- 1 - i;

		if (get < 0) {
			get = 0;
		}
		if(nonAnsweredPolls.size()==0) {
			return null;
		} else {
			return nonAnsweredPolls.get(get);
		}
		
	}

	public void addPoll(Poll poll) {
		if (!polls.contains(poll)) {
			polls.add(poll);
		}
	}

	public void addAnswerFromUser(Poll poll, String answer) {
		for (Poll pollsListed : polls) {
			if (pollsListed.equals(poll)) {
				pollsListed.getAnswers().put(answer,
						pollsListed.getAnswers().get(answer) + 1);
			}
		}
		user.answerPoll(poll);
	}
	public User getUser() {
		return user;
	}
	public ArrayList<Poll> getRemainingPolls() {

		ArrayList<Poll> nonAnsweredPolls = new ArrayList<Poll>(polls);
		
		Iterator<Poll> iteratorPoll = nonAnsweredPolls.iterator();
		
		while(iteratorPoll.hasNext()) {
			
			Poll poll =  (Poll) iteratorPoll.next();
			if(user.getAnsweredPolls().contains(poll)) {
				iteratorPoll.remove();
			}
			if(pollsSkipped.contains(poll)) {
				iteratorPoll.remove();
			}
		}
		return nonAnsweredPolls;
	}

	public void skip(Poll nextPoll) {
		pollsSkipped.add(nextPoll);
		// TODO Auto-generated method stub
		
	}
	

}
