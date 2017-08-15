package com.github.jannled.GroupSession.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.jannled.GroupSession.Main;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class WindowManager 
{
	Main main;
	
	JFrame window = new JFrame("GroupSession");
	JPanel pnlRoot = new JPanel();
	
	public WindowManager(Main main)
	{
		this.main = main;
		
		window.getContentPane().add(pnlRoot);
		pnlRoot.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		pnlRoot.add(splitPane);
		window.setVisible(true);
	}
}
