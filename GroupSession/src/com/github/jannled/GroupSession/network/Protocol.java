package com.github.jannled.GroupSession.network;

import java.util.LinkedList;

/**
 * @version 0.0.1
 * @author jannled
 * General class for sending a message to the specified target. 
 * There is one packet Dealer for each protocol (Comslink, Skype, TeamSpeak, etc)
 */
public abstract class Protocol
{
	protected LinkedList<PacketListener> listeners = new LinkedList<PacketListener>();
	private Thread listenerThread;
	private boolean listenerRunning;
	private String name;
	
	public Protocol(String name)
	{
		this.name = name;
		listenerThread = new Thread(new Runnable() {
			@Override
			public void run() 
			{
				listenerRunning = true;
				
				//Wait a short time for the Socket to establish
				try {
					Thread.sleep(60);
					System.out.println("[" + name + "] The protcol is now listening for incoming traffic.");
				} catch (InterruptedException e) {
					System.err.println("[" + name + "] Interrupted while waiting a second for the socket to establish.");
				}
				
				//Start Listening procedure
				while(listenerRunning)
				{
					received(receive());
				}
			}
		});
		listenerThread.start();
	}
	
	/**
	 * Send a packet to the specified user.
	 * @param packet The Packet to send
	 * @param user The User to send the Packet to
	 */
	public abstract void send(Packet packet);
	
	/**
	 * Register a class that should get informed whenever a packet gets recieved.
	 * @param listener The listener class
	 */
	public void registerListener(PacketListener listener)
	{
		listeners.add(listener);
	}
	
	/**
	 * Notify all Listeners about the recieved packet.
	 * @param packet The revieved packet
	 */
	protected void received(Packet packet)
	{
		for(PacketListener l : listeners)
		{
			l.received(packet);
		}
	}
	
	/**
	 * Wait for an incoming packet
	 * @return The recieved Packet
	 */
	protected abstract Packet receive();
	
	/**
	 * Get the name of the protocol.
	 * @return The name of the protocol
	 */
	public String getName()
	{
		return name;
	}
}
