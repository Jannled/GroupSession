package com.github.jannled.gsserver.database;

import java.util.UUID;

import com.github.jannled.groupsession.user.User;

public interface Database
{
	/**
	 * Add a user to the database.
	 * @param user The new User to add
	 */
	public void addUser(User user);
	
	/**
	 * Remove a user from the database.
	 * @param user The User to remove
	 */
	public void delUser(User user);
	
	/**
	 * Get a user by the specified ID.
	 * @param id The ID of the user
	 * @return The user with this ID in the database, or null if it doesn't exist
	 */
	public User getUser(UUID id);
	
	/**
	 * Get a user by the specified ID.
	 * @param name The Name of the user
	 * @return The user with this Name in the database, or null if it doesn't exist
	 */
	public User getUser(String name);
	
	/**
	 * Open the database
	 */
	public void open();
	
	/**
	 * Close the database
	 */
	public void close();
}
