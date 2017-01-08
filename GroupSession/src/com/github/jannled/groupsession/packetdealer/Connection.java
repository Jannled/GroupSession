package com.github.jannled.groupsession.packetdealer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.github.jannled.lib.NetUtils;
import com.github.jannled.lib.Print;

public class Connection
{
	DatagramSocket socket;
	
	InetAddress adress;
	short port;
	
	int incomingIndex = 0;
	int outgoingIndex = 0;
	
	public Connection()
	{
		try
		{
			socket = new DatagramSocket();
			setConnection("127.0.0.1", (short) 2369);
		} catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection(InetAddress adress, short port)
	{
		try
		{
			socket = new DatagramSocket();
			setConnection(adress, port);
		} catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendPacket(Packet packet)
	{
		DatagramPacket datagramm = new DatagramPacket(packet.getBytes(), packet.getBytes().length);
		try
		{
			socket.send(datagramm);
			
		} catch (IOException e)
		{
			Print.e("IOException while sending packet to " + toString());
			e.printStackTrace();
		}
	}

	public void setConnection(InetAddress adress, short port)
	{
		this.adress = adress;
		this.port = port;
	}
	
	public void setConnection(String ip, short port)
	{
		try
		{
			this.adress = InetAddress.getByName(ip);
			this.port = port; 
		} catch (UnknownHostException e)
		{
			Print.e("UnknownHostException while parsing adress " + ip);
			e.printStackTrace();
		}
	}
	
	public int getPing()
	{
		return (int) NetUtils.ping(getAddress());
	}

	public InetAddress getAddress()
	{
		return adress;
	}

	public short getPort()
	{
		return port;
	}
	
	public int getIncomingIndex()
	{
		incomingIndex++;
		return incomingIndex;
	}
	
	public int getOutgoingIndex()
	{
		outgoingIndex++;
		return outgoingIndex;
	}
	
	public DatagramSocket getSocket()
	{
		return socket;
	}
	
	@Override
	public String toString()
	{
		return getAddress().getHostAddress() + ":" + getPort();
	}
}