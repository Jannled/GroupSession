package com.github.jannled.GroupSession.console;

/**
 * @version 0.0.1
 * @author jannled
 * Parent class for every console command
 */
public abstract class Command 
{
	private String name;
	protected CommandManager cm;
	
	/**
	 * Constructs a new command.
	 * @param name The name of the command used in the console
	 * @param cm The instance of the CommandManager
	 */
	public Command(String name, CommandManager cm)
	{
		this.name = name;
		this.cm = cm;
	}
	
	/**
	 * Get the name used by the console to determine the command class.
	 * @return The name of the command
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Called when the apropiate command was typed in the console.
	 * @param arg The arguments used
	 */
	public abstract void execute(String arg);
}
