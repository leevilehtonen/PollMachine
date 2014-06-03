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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.sun.awt.AWTUtilities;

public class MessageFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private String message;
	
	
	public MessageFrame(String message) {
		super("Completed");
		this.message = message;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0, 0,
				250, 150, 10, 10));
		setPreferredSize(new Dimension(250, 100));
		createComponents(this.getContentPane());
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void createComponents(Container container) {
		
		container.setLayout(new GridBagLayout());
		container.setBackground(new Color(Integer.parseInt("1a1a1a", 16)));
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel message= new JLabel(this.message);
		message.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.weightx = 0.5;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(10, 10, 10, 10);
		container.add(message, constraints);
		
		FixedColorButton confirm = new FixedColorButton("OK", "grey");
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.ipady = 5;
		constraints.insets = new Insets(10, 10, 10, 10);
		container.add(confirm, constraints);
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
	}

	
	
}
