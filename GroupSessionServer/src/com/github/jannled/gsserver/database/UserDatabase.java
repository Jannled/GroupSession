package com.github.jannled.gsserver.database;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.github.jannled.groupsession.user.User;
import com.github.jannled.lib.Print;

public class UserDatabase implements Database
{
	File dbFile;
	ZipFile zip;
	
	/**
	 * Create conection to an existing database file or create a new one.
	 * @param dbFile The file path of the database to load or create
	 * @param create Allow to create a new Database file, if not existing
	 */
	public UserDatabase(File dbFile, boolean create)
	{
		this.dbFile = dbFile;
		if(create && (!exists()))
		{
			try
			{
				dbFile.createNewFile();
			} catch (IOException e)
			{
				Print.e("Failed to create database file " + dbFile.getAbsolutePath());
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void addUser(User user)
	{
		
	}

	@Override
	public void delUser(User user)
	{
		
	}

	@Override
	public User getUser(UUID id)
	{
		ZipEntry entrie = zip.getEntry("/u/" + id.toString());
		entrie.getName();
		return null;
	}

	@Override
	public User getUser(String name)
	{
		return null;
	}

	@Override
	public void open()
	{
		
	}

	@Override
	public void close()
	{
		
	}
	
	public boolean exists()
	{
		return dbFile.exists();
	}
}
