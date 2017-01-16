package com.github.jannled.groupsession.user;

import java.util.UUID;

import com.github.jannled.groupsession.packetdealer.Connection;

public class Contact extends User
{
	private static final long serialVersionUID = -4346095669976557771L;

	public Contact(Connection connection, UUID uuid, String name, String displayName)
	{
		super(connection, uuid, name, displayName);
	}
}
