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
	int incomingIndex = 0;
	int outgoingIndex = 0;
	
	public Connection()
	{
		try
		{
			setConnection(new DatagramSocket());
		} catch (SocketException e)
		{
			Print.m("NOTE: Standard Socket used for connection");
			e.printStackTrace();
		}
	}
	
	public Connection(InetAddress adress, short port)
	{
		setConnection(adress, port);
	}
	
	public Connection(DatagramSocket socket)
	{
		this.socket = socket;
	}
	
	public void sendPacket(Packet packet)
	{
		DatagramPacket datagramm = new DatagramPacket(packet.getBytes(), packet.getBytes().length);
		try
		{
			socket.send(datagramm);
			
		} catch (IOException e)
		{
			Print.e("IOException while sending packet to " + socket.getInetAddress() + ":" + socket.getPort());
			e.printStackTrace();
		}
	}

	public void setConnection(InetAddress adress, short port)
	{
		try
		{
			socket = new DatagramSocket(port, adress);
		} catch (SocketException e)
		{
			Print.e("SocketException while creating socket " + adress.getHostAddress() + ":" + port);
			e.printStackTrace();
		}
	}
	
	public void setConnection(String ip, short port)
	{
		try
		{
			socket = new DatagramSocket(port, InetAddress.getByName(ip));
		} catch (UnknownHostException e)
		{
			Print.e("UnknownHostException while parsing adress " + ip);
			e.printStackTrace();
		} catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	public void setConnection(DatagramSocket socket)
	{
		this.socket = socket;
	}
	
	public int getPing()
	{
		return (int) NetUtils.ping(getAddress());
	}

	public InetAddress getAddress()
	{
		return socket.getInetAddress();
	}

	public short getPort()
	{
		return (short) socket.getPort();
	}

	public DatagramSocket getSocket()
	{
		return socket;
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
	
	@Override
	public String toString()
	{
		return getAddress().getHostAddress() + ":" + getPort();
	}
}
