package com.github.jannled.groupsession.user;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.github.jannled.groupsession.Main;
import com.github.jannled.groupsession.packetdealer.Connection;

public class User
{
	private final String imagePath = "com.github.jannled.groupsession.assets.Contact.png";
	
	Connection connection;
	UUID uuid;
	String name;
	String displayName;
	BufferedImage icon;
	
	public User(Connection connection, UUID uuid, String name, String displayName)
	{
		this.connection = connection;
		this.uuid = uuid;
		this.name = name;
		this.displayName = displayName;
		
		try
		{
			this.icon = ImageIO.read(Main.class.getResourceAsStream(imagePath));
		} catch (IOException | NullPointerException e)
		{
			this.icon = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public UUID getId()
	{
		return uuid;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
}
