package com.github.jannled.GroupSession.console;

/**
 * @version 0.0.1
 * @author jannled
 * A command to exit the program.
 */
public class Cstop extends Command
{
	/**
	 * Constructs the command.
	 * @param cm The instance of the CommandManager that will call this command later on
	 */
	public Cstop(CommandManager cm) 
	{
		super("stop", cm);
	}

	/**
	 * Stops the client.
	 * @param arg Fired in the void (not used)
	 */
	@Override
	public void execute(String arg) 
	{
		System.out.println("Program stopped via console!");
		System.exit(0);
	}

}
