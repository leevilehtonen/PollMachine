package com.pollmachine.main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class FixedColorButton extends JButton{
	
	public FixedColorButton(String text) {
		super(text);
		super.setBackground(new Color(Integer.parseInt("525252", 16)));
		super.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		super.setFocusPainted(false);
		super.setBorderPainted(false);
		super.setContentAreaFilled(false);
		
		
		
		
	}
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
            g.setColor(new Color(Integer.parseInt("aaaaaa", 16)));
        } else if (getModel().isRollover()) {
        	 g.setColor(new Color(Integer.parseInt("525252", 16)));
        } else {
            g.setColor(new Color(Integer.parseInt("525252", 16)));
        }
		g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
	}

	
}
