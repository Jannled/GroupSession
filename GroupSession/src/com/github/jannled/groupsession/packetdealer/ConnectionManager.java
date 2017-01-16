package com.github.jannled.groupsession.packetdealer;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedList;

import com.github.jannled.groupsession.Config;

public class ConnectionManager
{
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	
	private LinkedList<PacketListener> listener = new LinkedList<PacketListener>();
	
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
	
	public Connection getConnection(InetAddress adress, short port)
	{
		for(Connection c : connections)
		{
			if(adress.equals(c.getAddress()) && port == c.getPort())
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
	
	public void registerPacketListener(PacketListener listener)
	{
		this.listener.add(listener);
	}
	
	public void packetRecieved(Packet packet)
	{
		for(PacketListener l : listener)
		{
			l.recievePacket(packet);
		}
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
