package com.github.jannled.groupsession.user;

import java.util.UUID;

import com.github.jannled.groupsession.packetdealer.Connection;

public class Contact extends User
{
	public Contact(Connection connection, UUID uuid, String name, String displayName)
	{
		super(connection, uuid, name, displayName);
	}
}
