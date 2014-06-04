package com.pollmachine.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CreateScreen implements Runnable {
	
	private PollDatabase database;
	private JFrame frame;
	
	public CreateScreen(PollDatabase database) {
		this.database = database;
	}

	@Override
	public void run() {

		frame = new JFrame("Create");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setShape(new RoundRectangle2D.Double(0, 0, 400, 300, 20, 20));
		frame.setPreferredSize(new Dimension(400, 300));
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		

	}

	private void createComponents(Container container) {
		
		container.setLayout(new GridBagLayout());
		container.setBackground(new Color(Integer.parseInt("202020", 16)));
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel question = new JLabel("Poll question");
		question.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		question.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		container.add(question, BorderLayout.PAGE_START);
		
		JTextField questionField = new JTextField();	
		questionField.setBackground(new Color(Integer.parseInt("525252", 16)));
		questionField.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		questionField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		container.add(questionField, BorderLayout.CENTER);
		
		

		// TODO Auto-generated method stub
		
	}

}
