package com.github.jannled.gsserver;

import com.github.jannled.groupsession.packetdealer.ConnectionManager;
import com.github.jannled.gsserver.database.Berkely;
import com.github.jannled.gsserver.database.Database;

public class Main
{
	ConnectionManager manager;
	Database database;
	
	public Main()
	{
		manager = new ConnectionManager();
		database = new Berkely();
	}

	public static void main(String[] args)
	{
		new Main();
	}
}