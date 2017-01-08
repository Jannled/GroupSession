package com.github.jannled.groupsession.window;

import javax.swing.JFrame;

public class Window
{

	private JFrame window;

	/**
	 * Create the application.
	 */
	public Window()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		window = new JFrame();
		window.setBounds(100, 100, 450, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
