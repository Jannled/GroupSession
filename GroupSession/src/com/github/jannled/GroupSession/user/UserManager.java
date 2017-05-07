package com.github.jannled.GroupSession.user;

import java.util.ArrayList;

import com.github.jannled.GroupSession.Main;

public class UserManager 
{	
	Main main;
	ArrayList<User> users = new ArrayList<User>();
	
	public UserManager(Main main)
	{
		this.main = main;
	}
	
	public void registerUser(User user)
	{
		users.add(user);
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
}
