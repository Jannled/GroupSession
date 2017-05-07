package com.github.jannled.GroupSession.network;

import com.github.jannled.GroupSession.user.User;

public class Packet 
{
	private byte[] packet;
	private User user;
	
	public Packet(byte[] packet, User user)
	{
		this.packet = packet;
		this.user = user;
	}

	public byte[] getPacket() {
		return packet;
	}

	public User getUser() {
		return user;
	}
}
