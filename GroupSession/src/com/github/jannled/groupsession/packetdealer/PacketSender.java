package com.github.jannled.groupsession.packetdealer;

import java.util.LinkedList;

import com.github.jannled.lib.ThreadUtils;

public class PacketSender implements Runnable
{
	private boolean running = true;
	LinkedList<Packet> packetQue = new LinkedList<Packet>();
	
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
		ThreadUtils.freeze(3000);
		while(running)
		{
			if(!packetQue.isEmpty())
			{
				packetQue.getFirst().send();
				packetQue.removeFirst();
			}
		}
	}
	
	public void stop()
	{
		running = false;
	}
}
