package com.github.jannled.gsserver.database;

import java.io.File;
import java.util.UUID;

import com.github.jannled.groupsession.user.User;
import com.github.jannled.lib.Serializer;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public class Berkely implements Database
{
	private File databaseFile = new File("users.udb");
	
	private com.sleepycat.je.Database db = null;
	private Environment dbEnv = null;
	private EnvironmentConfig envConfig = null;
	private DatabaseConfig dbConf = null;
	
	public Berkely()
	{
		try {
			envConfig = new EnvironmentConfig();
			envConfig.setAllowCreate(true);
			dbEnv = new Environment(databaseFile, envConfig);
			
			dbConf = new DatabaseConfig();
			dbConf.setAllowCreate(true);
			db = dbEnv.openDatabase(null, "Users", dbConf);
		} catch(DatabaseException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user)
	{
		DatabaseEntry key = new DatabaseEntry(Serializer.serialize(user.getId()));
		DatabaseEntry data = new DatabaseEntry(Serializer.serialize(user));
		db.put(null, key, data);
	}

	@Override
	public void delUser(User user)
	{
		
	}

	@Override
	public User getUser(UUID id)
	{
		return null;
	}

	@Override
	public User getUser(String name)
	{
		return null;
	}

	/**
	 * Not used by this database
	 */
	@Override
	public void open()
	{
		
	}

	@Override
	public void close()
	{
		try
		{
			if(db != null)
			{
				db.close();
			}
			if(dbEnv != null)
			{
				dbEnv.close();
			}
		} catch (DatabaseException e)
		{
			e.printStackTrace();
		}
	}

}