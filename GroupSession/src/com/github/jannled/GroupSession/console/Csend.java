package com.github.jannled.GroupSession.console;

/**
 * @version 0.0.1
 * @author jannled
 * A command to send Messages to a specified target.
 */
public class Csend extends Command
{
	/**
	 * Constructs the command.
	 * @param cm The instance of the CommandManager that will call this command later on
	 */
	public Csend(CommandManager cm) 
	{
		super("send", cm);
	}

	/**
	 * Send a message to the specified target.
	 * @param arg The message to send
	 */
	@Override
	public void execute(String arg) 
	{
		cm.getMain().getUserManager().getUsers().get(0).send(arg.getBytes());
	}
	
}
