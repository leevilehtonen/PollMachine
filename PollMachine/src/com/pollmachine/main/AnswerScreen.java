package com.pollmachine.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class AnswerScreen implements Runnable {

	private JFrame frame;
	private PollDatabase database;
	private int count;
	private int skipped;
	private AnswerScreen screen;

	private FixedColorButton nextAndSkip;
	private JLabel pollsAnswered;

	private Poll currentPoll;

	public AnswerScreen(PollDatabase database) {
		this.database = database;
		this.count = 0;
		this.screen = this;

	}

	@Override
	public void run() {

		frame = new JFrame("Answer");
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

		container.setLayout(new BorderLayout());
		container.setBackground(new Color(Integer.parseInt("202020", 16)));

		JPanel scoreAndQuit = new JPanel(
				new FlowLayout(FlowLayout.LEFT, 10, 10));
		scoreAndQuit.setBackground(new Color(Integer.parseInt("202020", 16)));

		pollsAnswered = new JLabel("Polls answered: "
				+ database.getUser().getAnsweredPollsAmount());
		pollsAnswered.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		pollsAnswered.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		scoreAndQuit.add(pollsAnswered);

		JLabel pollsCreated = new JLabel("Polls created: "
				+ database.getUser().getCreatedPolls());
		pollsCreated.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		scoreAndQuit.add(pollsCreated);

		JButton quit = new JButton(new ImageIcon(this.getClass().getResource(
				"/assets/quit.png")));
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setFocusPainted(false);
		quit.setOpaque(false);
		quit.setMaximumSize(new Dimension(10, 10));
		quit.setBorder(BorderFactory.createEmptyBorder(0, 140, 0, 0));
		scoreAndQuit.add(quit);

		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Quit");
				System.exit(0);
			}
		});
		container.add(scoreAndQuit, BorderLayout.NORTH);

		currentPoll = database.getRecent(count);

		if (currentPoll == null) {
			frame.dispose();
			SwingUtilities.invokeLater(new MainMenuScreen(database.getUser()));
			SwingUtilities.invokeLater(new MessageFrame(
					"You have answered to all available polls"));

		} else {
			JPanel pollSwitcher = new JPanel();

			CardLayout cardLayout = new CardLayout();
			pollSwitcher.setLayout(cardLayout);
			pollSwitcher
					.setBackground(new Color(Integer.parseInt("202020", 16)));

			PollJPanel pollJPanel = new PollJPanel(this, currentPoll, database);
			pollSwitcher.add(pollJPanel);

			container.add(pollSwitcher, BorderLayout.CENTER);

			nextAndSkip = new FixedColorButton("Skip poll", "red");
			nextAndSkip.setPreferredSize(new Dimension(380, 80));
			container.add(nextAndSkip, BorderLayout.SOUTH);
			nextAndSkip.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (nextAndSkip.getText().equals("Skip poll")) {
						database.skip(currentPoll);

						count++;
						currentPoll = database.getRecent(count);

						if (currentPoll == null) {
							frame.dispose();
							SwingUtilities.invokeLater(new MainMenuScreen(
									database.getUser()));
							SwingUtilities
									.invokeLater(new MessageFrame(
											"You have answered to all available polls"));

						} else {
							pollSwitcher.removeAll();
							pollSwitcher.add(new PollJPanel(screen, currentPoll,
									database));
							cardLayout.next(pollSwitcher);
							nextAndSkip.changeColour("red");
							nextAndSkip.setText("Skip poll");
							container.revalidate();
							container.repaint();
						}

					} else if (nextAndSkip.getText().equals("Next poll")) {

						count++;
						currentPoll = database.getRecent(count);

						if (currentPoll == null) {
							frame.dispose();
							SwingUtilities.invokeLater(new MainMenuScreen(
									database.getUser()));
							SwingUtilities
									.invokeLater(new MessageFrame(
											"You have answered to all available polls"));

						} else {
							pollSwitcher.removeAll();
							pollSwitcher.add(new PollJPanel(screen, currentPoll,
									database));
							cardLayout.next(pollSwitcher);
							nextAndSkip.changeColour("red");
							nextAndSkip.setText("Skip poll");
							container.revalidate();
							container.repaint();
						}

					}
				}
			});

		}

	}

	public FixedColorButton getNextAndSkip() {
		return nextAndSkip;
	}

	public JLabel getPollsAnswered() {
		return pollsAnswered;
	}

}
