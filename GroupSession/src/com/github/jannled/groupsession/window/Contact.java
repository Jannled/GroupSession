package com.github.jannled.groupsession.window;

import com.github.jannled.groupsession.packetdealer.Connection;

public class Contact
{
	String displayname;
	Connection connection;
	
	public Contact(String displayname, Connection connection)
	{
		this.displayname = displayname;
		this.connection = connection;
	}
	
	public Connection getConnection()
	{
		return connection;
	}

	public String getDisplayname()
	{
		return displayname;
	}
}
