package com.github.jannled.GroupSession.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.jannled.GroupSession.Main;

public class WindowManager 
{
	Main main;
	
	JFrame window = new JFrame("GroupSession");
	JPanel pnlRoot = new JPanel();
	
	public WindowManager(Main main)
	{
		this.main = main;
		
		window.getContentPane().add(pnlRoot);
		window.setVisible(true);
	}
}
