package com.github.jannled.groupsession.packetdealer;

import java.net.InetAddress;
import java.util.ArrayList;

import com.github.jannled.groupsession.Config;

public class ConnectionManager
{	
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	
	PacketSender packetSender;
	PacketReciever packetReciever;
	
	Thread packetSenderThread;
	Thread packetRecieverThread;
	
	public ConnectionManager()
	{
		startPacketReciever();
		startPacketSender();
	}

	public void addConnection(Connection connection)
	{
		connections.add(connection);
	}
	
	public Connection getConnection(InetAddress adress, int port)
	{
		for(Connection c : connections)
		{
			if(adress.getAddress().equals(c.getAddress()))
			{
				return c;
			}
		}
		return null;
	}
	
	public void startPacketSender()
	{
		packetSender = new PacketSender();
		packetSenderThread = new Thread(packetSender);
		packetSenderThread.start();
	}
	
	public void startPacketReciever()
	{
		packetReciever = new PacketReciever(this, Config.recievePort);
		packetRecieverThread = new Thread(packetReciever);
		packetRecieverThread.start();
	}
	
	public void closeConnections()
	{
		packetReciever.stop();
		packetSender.stop();
	}
	
	public ArrayList<Connection> getConnections()
	{
		return connections;
	}
	
	public PacketSender getPacketSender()
	{
		return packetSender;
	}
	
	public PacketReciever getPacketReciever()
	{
		return packetReciever;
	}
}
