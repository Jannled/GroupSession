package com.github.jannled.groupsession.packetdealer;

import java.util.ArrayList;

public class PacketSender implements Runnable
{
	private boolean running = true;
	ArrayList<Packet> packetQue = new ArrayList<Packet>();
	
	public PacketSender()
	{
		
	}

	public void addPacket(Packet packet)
	{
		packetQue.add(packet);
	}
	
	@Override
	public void run()
	{
		while(running)
		{
			for(Packet p : packetQue)
			{
				p.send();
			}
		}
	}
	
	public void stop()
	{
		running = false;
	}
}
