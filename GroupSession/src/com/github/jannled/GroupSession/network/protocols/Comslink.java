package com.github.jannled.GroupSession.network.protocols;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.github.jannled.GroupSession.network.Packet;
import com.github.jannled.GroupSession.network.Protocol;

/**
 * @version 0.0.1
 * @author jannled
 * A communication protocol created by Jannled
 */
public class Comslink extends Protocol 
{
	private DatagramSocket socket;
	
	private int packetSize = 4096;
	
	public Comslink()
	{
		super("Comslink");
		try 
		{
			socket = new DatagramSocket(2639);
		}
		catch (SocketException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void send(Packet packet)
	{
		DatagramPacket p = new DatagramPacket(packet.getPacket(), packet.getPacket().length, packet.getUser().getAddress(), packet.getUser().getPort());
		try 
		{
			socket.send(p);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	protected Packet receive() 
	{
		byte[] data = new byte[packetSize];
		DatagramPacket p = new DatagramPacket(data, data.length);
		try 
		{
			socket.receive(p);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.err.println("[Comslink] NullPointerException on packet recieve.");
		}
		return new Packet(p.getData(), null);
	}
	
}
