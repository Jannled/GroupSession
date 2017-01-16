package com.github.jannled.groupsession.packetdealer;

public interface PacketListener
{
	/**
	 * Called whenerver a packet gets recieved
	 * @param packet The packet that got recived
	 */
	public void recievePacket(Packet packet);
}
