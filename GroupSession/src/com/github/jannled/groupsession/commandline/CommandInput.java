package com.github.jannled.groupsession.commandline;

import java.util.Scanner;

import com.github.jannled.lib.Print;

public class CommandInput implements Runnable
{
	Thread thread;
	private Scanner scanner;
	private boolean running;
	
	Command[] allCommands = {new CommandSend()};
	
	public CommandInput()
	{
		scanner = new Scanner(System.in);
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run()
	{
		while(running)
		{
			String line = scanner.nextLine();
			Command.runCommand(line);
			Print.m("Running " + line);
		}
	}
	
	public void stop()
	{
		running = false;
	}
}
