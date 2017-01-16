package com.github.jannled.groupsession.commandline;

import java.util.ArrayList;

public abstract class Command
{
	private static ArrayList<Command> commands = new ArrayList<Command>();
	
	String label;
	
	
	public Command(String label)
	{
		this.label = label;
		commands.add(this);
	}

	public boolean isCommand(String s)
	{
		if(s.equalsIgnoreCase(label))
			return true;
		else
			return false;
	}
	
	public abstract boolean run(String[] params);
	
	/**
	 * 
	 * @param s A String containing the command label
	 */
	public static void runCommand(String s)
	{
		String[] split = s.split(" ");
		String[] params = new String[split.length-1];
		System.arraycopy(split, 1, params, 0, split.length-1);
		for(Command c : commands)
		{
			if(c.isCommand(split[0]))
			{
				c.run(params);
			}
		}
	}
}
