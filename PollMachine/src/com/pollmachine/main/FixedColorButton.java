package com.pollmachine.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

public class FixedColorButton extends JButton {

	private static final long serialVersionUID = 1L;
	private Color cliked;
	private Color nonCliked;

	public FixedColorButton(String text, String colour) {

		super(text);
		super.setBackground(new Color(Integer.parseInt("525252", 16)));
		super.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		super.setFocusPainted(false);
		super.setBorderPainted(false);
		super.setContentAreaFilled(false);
		super.setFont(new Font("Arial", Font.PLAIN, 12));

		if (colour.equals("blue")) {
			nonCliked = new Color(Integer.parseInt("1f324b", 16));
		} else if (colour.equals("green")) {
			nonCliked = new Color(Integer.parseInt("3e7b6f", 16));
		} else if (colour.equals("yellow")) {
			nonCliked = new Color(Integer.parseInt("e2da95", 16));
		} else if (colour.equals("red")) {
			nonCliked = new Color(Integer.parseInt("d34c4a", 16));
		} else {
			nonCliked = new Color(Integer.parseInt("525252", 16));
		}
		cliked = new Color(Integer.parseInt("aaaaaa", 16));
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(cliked);
		} else {
			g.setColor(nonCliked);
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public void changeColour(String colour) {
		
		if (colour.equals("blue")) {
			nonCliked = new Color(Integer.parseInt("1f324b", 16));
		} else if (colour.equals("green")) {
			nonCliked = new Color(Integer.parseInt("3e7b6f", 16));
		} else if (colour.equals("yellow")) {
			nonCliked = new Color(Integer.parseInt("e2da95", 16));
		} else if (colour.equals("red")) {
			nonCliked = new Color(Integer.parseInt("d34c4a", 16));
		} else {
			nonCliked = new Color(Integer.parseInt("525252", 16));
		}
		cliked = new Color(Integer.parseInt("aaaaaa", 16));
		
	}

}
