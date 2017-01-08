package com.github.jannled.groupsession.packetdealer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.github.jannled.groupsession.Config;
import com.github.jannled.lib.Print;

public class PacketReciever implements Runnable
{
	private ConnectionManager connections;
	private DatagramSocket reciever;
	
	private boolean running;
	
	public PacketReciever(ConnectionManager connections, short port)
	{
		this.connections = connections;
		try
		{
			reciever = new DatagramSocket(port);
		} catch (SocketException e)
		{
			Print.e("Failed to create reciever socket for port " + port + "!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		while(running)
		{
			DatagramPacket datagramm = new DatagramPacket(new byte[Config.actualSize], Config.actualSize);
			try
			{
				reciever.receive(datagramm);
			} catch (IOException e)
			{
				Print.e("IOException while recieving packet " + datagramm.getAddress().getHostAddress() + ":" + datagramm.getPort());
				e.printStackTrace();
				continue;
			}
			Packet packet = new Packet(connections.getConnection(datagramm.getAddress(), datagramm.getPort()), datagramm.getData());
			Print.m("Packet: " + packet.getPacket().getAddress() + ": " + packet.getBytes());
		}
	}
	
	public void stop()
	{
		running = false;
	}
}
