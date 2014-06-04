package com.pollmachine.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class PollMachine implements Runnable {

	private JFrame frame;
	private HashMap<User, String> users;

	public PollMachine() {
	}

	public void run() {

		frame = new JFrame("Welcome");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
	frame.setShape(new RoundRectangle2D.Double(0, 0,
				300, 400, 20, 20));
		frame.setPreferredSize(new Dimension(300, 400));
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		users = new HashMap<User, String>();
		users.put(new User("test"), "test");
	}

	private void createComponents(Container container) {

		container.setLayout(new GridBagLayout());
		container.setBackground(new Color(Integer.parseInt("202020", 16)));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		JButton quit = new JButton(new ImageIcon(this.getClass().getResource(
				"/assets/quit.png")));
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setFocusPainted(false);
		quit.setOpaque(false);
		quit.setMaximumSize(new Dimension(10, 10));
		constraints.insets = new Insets(10, 50, 0, 0);
		constraints.gridx = 9;
		constraints.gridy = 0;
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
		constraints.insets = new Insets(0, 0, 0, 0);
		container.add(logo, constraints);

		JLabel username = new JLabel("Username");
		username.setOpaque(false);
		username.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.weightx = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 5, 5, 5);
		container.add(username, constraints);

		final JTextField usernameField = new JTextField();
		usernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		usernameField.setBackground(new Color(Integer.parseInt("525252", 16)));
		usernameField.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 10;
		constraints.ipady = 10;
		constraints.insets = new Insets(0, 5, 0, 5);
		container.add(usernameField, constraints);

		JLabel password = new JLabel("Password");
		password.setOpaque(false);
		password.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.ipady = 0;
		constraints.insets = new Insets(10, 5, 5, 5);
		container.add(password, constraints);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		passwordField.setBackground(new Color(Integer.parseInt("525252", 16)));
		passwordField.setForeground(new Color(Integer.parseInt("ffffff", 16)));
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 10;
		constraints.ipady = 10;
		constraints.insets = new Insets(0, 5, 5, 5);
		container.add(passwordField, constraints);

		final FixedColorButton loginBtn = new FixedColorButton("Login", "grey");
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 10;
		constraints.ipady = 0;
		constraints.insets = new Insets(30, 5, 5, 5);
		loginBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Login");
				
				boolean correctInfo = false;
				
				for (User user : users.keySet()) {
					if (user.getName().equals(usernameField.getText())) {
						String givenPassword = "";
						for (int i = 0; i < passwordField.getPassword().length; i++) {
							givenPassword = givenPassword
									+ passwordField.getPassword()[i];
						}
						String usersPassword = users.get(user);
						if (givenPassword.equals(usersPassword)) {
							System.out.println("Login completed");
							usernameField.setText("");
							passwordField.setText("");
							MainMenuScreen mainMenuScreen = new MainMenuScreen(user);
							SwingUtilities.invokeLater(mainMenuScreen);
							frame.dispose();
							correctInfo = true;
						} 
					}
				}
				
				if (!correctInfo) {
					SwingUtilities.invokeLater(new MessageFrame("Wrong username or password"));
					usernameField.setText("");
					passwordField.setText("");
				}
			}
		});
		container.add(loginBtn, constraints);

		final FixedColorButton registerBtn = new FixedColorButton("Register", "grey");
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 10;
		constraints.ipady = 0;
		constraints.insets = new Insets(5, 5, 30, 5);
		registerBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Register");

				boolean containsUserNamed = false;

				for (User user : users.keySet()) {
					if (user.getName().equals(usernameField.getText())) {
						containsUserNamed = true;
					}
				}
				if (!containsUserNamed) {
					String givenPassword = "";
					for (int i = 0; i < passwordField.getPassword().length; i++) {
						givenPassword = givenPassword
								+ passwordField.getPassword()[i];
					}
					users.put(new User(usernameField.getText()), givenPassword);
					System.out.println("Register completed");
					usernameField.setText("");
					passwordField.setText("");

					SwingUtilities.invokeLater(new MessageFrame("Registeration completed!"));

				} else {
					usernameField.setForeground(Color.RED);
					passwordField.setText("");
				}
			}
		});
		container.add(registerBtn, constraints);

		if (usernameField.getText().isEmpty()) {
			loginBtn.setEnabled(false);
			registerBtn.setEnabled(false);
		}

		usernameField.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {

				if (usernameField.getText().isEmpty()) {
					usernameField.setForeground(new Color(Integer.parseInt(
							"ffffff", 16)));
					loginBtn.setEnabled(false);
					registerBtn.setEnabled(false);
				} else {
					loginBtn.setEnabled(true);
					registerBtn.setEnabled(true);
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyPressed(KeyEvent arg0) {
			}
		});
	}
}
