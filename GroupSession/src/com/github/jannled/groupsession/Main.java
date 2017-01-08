package com.github.jannled.groupsession;

import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.github.jannled.groupsession.packetdealer.Connection;
import com.github.jannled.groupsession.packetdealer.ConnectionManager;
import com.github.jannled.groupsession.packetdealer.Packet;
import com.github.jannled.groupsession.window.Window;

public class Main
{
	Main main;
	Window window;
	ConnectionManager connections;
	
	public Main()
	{
		main = this;
		connections = new ConnectionManager();
		Connection c = null;
		openWindow();
		try
		{
			c = new Connection(InetAddress.getByName("127.0.0.1"), (short) 2369);
			connections.addConnection(c);
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
			return;
		}
		byte[] bytes = "Hallo Welt".getBytes();
		Packet packet = new Packet(c, bytes);
		connections.getPacketSender().addPacket(packet);
	}

	public static void main(String[] args)
	{
		new Main();
	}
	
	public boolean openWindow()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run()
			{
				try
				{
					window = new Window();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
		if(window != null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
