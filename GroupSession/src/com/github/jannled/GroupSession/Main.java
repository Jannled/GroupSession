package com.github.jannled.GroupSession;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import com.github.jannled.GroupSession.console.CommandManager;
import com.github.jannled.GroupSession.gui.WindowManager;
import com.github.jannled.GroupSession.network.Packet;
import com.github.jannled.GroupSession.network.PacketListener;
import com.github.jannled.GroupSession.network.protocols.Comslink;
import com.github.jannled.GroupSession.user.User;
import com.github.jannled.GroupSession.user.UserManager;

/**
 * @version 0.0.1
 * @author jannled
 * Main class that keeps track of every manager.
 */
public class Main implements PacketListener
{
	//All Protocols
	Comslink comslink = new Comslink();
	
	//TODO Debug remove!
	User user;
	
	//All Managers
	UserManager userManager;
	CommandManager commandManager;
	WindowManager windowManager;
	
	public Main()
	{
		//Create User Localhost
		try {
			user = new User(InetAddress.getByName("127.0.0.1"), (short) 2639, "_LOCALHOST_", comslink);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		user.getProtocol().registerListener(this);
		userManager = new UserManager(this);
		userManager.registerUser(user);
		
		commandManager = new CommandManager(this);
	}
	
	public static void main(String args[])
	{
		System.out.println("Starting with arguments: " + Arrays.toString(args));
		new Main();
	}

	@Override
	public void received(Packet packet) 
	{
		System.out.println(new String(packet.getPacket()));
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}
}
