package com.pollmachine.main;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PollJPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private AnswerScreen screen;
	private ArrayList<FixedColorButton> answerBtns;
	
	public PollJPanel(AnswerScreen answerScreen, Poll poll, PollDatabase database) {
		
		super(new GridBagLayout());
		
		this.screen = answerScreen;
		this.answerBtns = new ArrayList<FixedColorButton>();
		
		setBackground(new Color(Integer.parseInt("202020", 16)));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel pollQuestion = new JLabel(poll.getQuestion());
		pollQuestion.setOpaque(false);
		pollQuestion.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.weightx = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 10;
		constraints.ipady = 2;
		constraints.insets = new Insets(5, 10, 5, 10);
		add(pollQuestion, constraints);
		
		int yPos = 1;
		
		for (String answer : poll.getAnswers().keySet()) {
		
			FixedColorButton answerBtn = new FixedColorButton(answer, "grey");
			constraints.gridx = 0;
			constraints.gridy = yPos;
			constraints.gridwidth = 10;
			constraints.ipady = 20;
			constraints.insets = new Insets(5, 10, 0, 10);
			add(answerBtn, constraints);
			answerBtns.add(answerBtn);
			yPos++;
			
			answerBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					screen.getNextAndSkip().changeColour("green");
					screen.getNextAndSkip().setText("Next poll");
					database.addAnswerFromUser(poll, answer);
					
					
					int totalVotes = 0;
					for(String answer:poll.getAnswers().keySet()) {
						totalVotes = totalVotes + poll.getAnswers().get(answer);
					}

					for(FixedColorButton btn:answerBtns) {
						double percent = ((double) poll.getAnswers().get(btn.getText()) / totalVotes) * 100;
						btn.setText("Votes: " + poll.getAnswers().get(btn.getText())+"/"+totalVotes+" - "+percent+"%");
						btn.setEnabled(false);
					}
					screen.getPollsAnswered().setText("Polls answered: "+ database.getUser().getAnsweredPollsAmount());
					
				}
			});
		}
	}

}
