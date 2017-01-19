package com.github.jannled.gsserver;

import java.io.File;
import java.util.UUID;

import com.github.jannled.groupsession.packetdealer.Connection;
import com.github.jannled.groupsession.packetdealer.ConnectionManager;
import com.github.jannled.groupsession.user.User;
import com.github.jannled.gsserver.database.Database;
import com.github.jannled.gsserver.database.UserDatabase;
import com.github.jannled.lib.Print;

public class Main
{
	ConnectionManager manager;
	Database database;
	
	public Main()
	{
		manager = new ConnectionManager();
		database = new UserDatabase(new File("/db/users.jdb"), true);
		database.addUser(new User(new Connection("127.0.0.1", (short) 2639), UUID.randomUUID(), "TestUser", "Herobrine"));
		Print.m("Done!");
	}

	public static void main(String[] args)
	{
		new Main();
	}
}