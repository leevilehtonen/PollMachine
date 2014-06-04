package com.pollmachine.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class MainMenuScreen implements Runnable {

	private JFrame frame;

	private User user;

	private PollDatabase database;

	public MainMenuScreen(User user) {
		this.user = user;
		this.database = new PollDatabase(user);
		
		Poll testPoll = new Poll();
		testPoll.setQuestion("What is this test question?");
		testPoll.addAnswer("Whats wafwfawfthis?");
		testPoll.addAnswer("Hahahafwafawfha");
		testPoll.addAnswer("Hahahaha");
		testPoll.addAnswer("Maybe something useful");
		this.database.addPoll(testPoll);
		
		Poll testPoll2 = new Poll();
		testPoll2.setQuestion("Who is this?");
		testPoll2.addAnswer("Hahahafwafawfha");
		testPoll2.addAnswer("Maybe something useful");
		testPoll2.addAnswer("Maybe something not so useful");
		testPoll2.addAnswer("e<gsge<gWhats wafwfawfthis?");
		testPoll2.addAnswer("HWFfWA fahahafwafawfha");
		testPoll2.addAnswer("Whats wafwfawfthis?");
		testPoll2.addAnswer("HahahafwawfAFfawfha");
		testPoll2.addAnswer("HahawFWFwFahaha");
		this.database.addPoll(testPoll2);
		
		Poll testPoll3 = new Poll();
		testPoll3.setQuestion("What to do now?");
		testPoll3.addAnswer("Hahahafwafawfha");
		testPoll3.addAnswer("Hahahaha");
		this.database.addPoll(testPoll3);

	}

	@Override
	public void run() {

		frame = new JFrame("Main menu");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setShape(new RoundRectangle2D.Double(0, 0, 400, 600, 20, 20));
		frame.setPreferredSize(new Dimension(400, 600));
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	private void createComponents(Container container) {

		container.setLayout(new GridBagLayout());
		container.setBackground(new Color(Integer.parseInt("202020", 16)));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		JLabel pollsAnswered = new JLabel("Polls answered: "
				+ user.getAnsweredPollsAmount());
		pollsAnswered.setOpaque(false);
		pollsAnswered.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.ipady = 0;
		constraints.insets = new Insets(0, 10, 40, 10);
		container.add(pollsAnswered, constraints);

		JLabel pollsCreated = new JLabel("Polls created: "
				+ user.getCreatedPolls());
		pollsCreated.setOpaque(false);
		pollsCreated.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.ipady = 0;
		constraints.insets = new Insets(0, 10, 40, 10);
		container.add(pollsCreated, constraints);

		JButton quit = new JButton(new ImageIcon(this.getClass().getResource(
				"/assets/quit.png")));
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setFocusPainted(false);
		quit.setOpaque(false);
		quit.setMaximumSize(new Dimension(10, 10));
		constraints.insets = new Insets(0, 140, 40, 0);
		constraints.gridx = 9;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		container.add(quit, constraints);

		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Quit");
				System.exit(0);
			}
		});
		JLabel logo = new JLabel(new ImageIcon(this.getClass().getResource(
				"/assets/logo.png")));
		constraints.weightx = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 10;
		constraints.insets = new Insets(0, 0, 30, 0);
		container.add(logo, constraints);

		FixedColorButton answer = new FixedColorButton("Answer recent polls",
				"blue");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 10;
		constraints.ipady = 50;
		constraints.insets = new Insets(5, 5, 0, 5);
		container.add(answer, constraints);
		answer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(new AnswerScreen(database));
				frame.dispose(); 
				
			}
				
		});

		FixedColorButton create = new FixedColorButton("Create a new poll",
				"green");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 10;
		constraints.ipady = 50;
		constraints.insets = new Insets(5, 5, 0, 5);
		container.add(create, constraints);
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new CreateScreen(database));
				frame.dispose();
				
			}
		});

		FixedColorButton search = new FixedColorButton("Search a poll",
				"yellow");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 10;
		constraints.ipady = 50;
		constraints.insets = new Insets(5, 5, 0, 5);
		container.add(search, constraints);

		FixedColorButton logout = new FixedColorButton("Exit", "red");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 10;
		constraints.ipady = 50;
		constraints.insets = new Insets(5, 5, 40, 5);
		container.add(logout, constraints);

	}

}
