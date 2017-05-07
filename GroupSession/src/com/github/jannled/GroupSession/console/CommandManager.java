package com.github.jannled.GroupSession.console;

import java.util.LinkedList;
import java.util.Scanner;

import com.github.jannled.GroupSession.Main;

/**
 * @version 0.0.1
 * @author jannled
 * Receives the console input and passes it to the apropoiate command classes.
 */
public class CommandManager 
{
	private Main main;
	private LinkedList<Command> commands = new LinkedList<Command>();
	
	private Thread thread = new Thread();
	private boolean abort;
	
	/**
	 * Creates and starts the CommandManager
	 * @param main The Main instance
	 */
	public CommandManager(Main main)
	{
		this.main = main;
		thread = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				register();
				System.out.println("Started console Listener");
				listen();
			}
		});
		thread.start();
	}
	
	/**
	 * Every commands gets registered here.
	 */
	public void register()
	{
		commands.add(new Cstop(this));
		commands.add(new Csend(this));
	}
	
	/**
	 * Wait for console input and pass it to the target command.
	 */
	public void listen()
	{
		Scanner scanner = new Scanner(System.in);
		while(!abort)
		{
			System.out.println("Awaiting commands: ");
			String input = scanner.nextLine();
			
			String[] text = input.split(" ", 2);
			for(Command c : commands)
			{
				if(c.getName().equalsIgnoreCase(text[0]))
				{
					if(text.length > 1)
						c.execute(text[1]);
					else
						c.execute("");
				}
			}
		}
		scanner.close();
		System.exit(0);
	}
	
	/**
	 * Get the Main instance for the commands to get access to certain features Main provides.
	 * @return The Main instance
	 */
	Main getMain()
	{
		return main;
	}
}
