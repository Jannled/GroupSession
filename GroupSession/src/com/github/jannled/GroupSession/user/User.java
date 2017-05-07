package com.github.jannled.GroupSession.user;

import java.net.InetAddress;

import com.github.jannled.GroupSession.network.Packet;
import com.github.jannled.GroupSession.network.Protocol;

/**
 * @version 0.0.1
 * @author jannled
 * A GroupSession user that contains information about his connection and person
 */
public class User 
{
	private InetAddress address;
	private short port;
	private String name;
	private Protocol protocol;
	
	/**
	 * Create a new User.
	 * @param address Address of the User
	 * @param port The Port of the User
	 * @param name The Name of the User
	 * @param protocol The Protocol to use
	 */
	public User(InetAddress address, short port, String name, Protocol protocol)
	{
		this.address = address;
		this.port = port;
		this.name = name;
		this.protocol = protocol;
	}

	/**
	 * Send a packet to the specified user.
	 * @param packet The binary data to send
	 */
	public void send(byte[] packet)
	{
		protocol.send(new Packet(packet, this));
	}
	
	/**
	 * Get the Address of the User.
	 * @return The Address of the User
	 */
	public InetAddress getAddress() {
		return address;
	}

	/**
	 * Set the Adress of the User.
	 * @param address Set the Adress of the User
	 */
	public void setAddress(InetAddress address) {
		this.address = address;
	}

	/**
	 * Get the Port of the User.
	 * @return The Port of the User
	 */
	public short getPort() {
		return port;
	}

	/**
	 * Set the Port of the User.
	 * @param port Set the Port of the User
	 */
	public void setPort(short port) {
		this.port = port;
	}

	/**
	 * Get the Name of the User.
	 * @return The Name of the User
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the Name of the User.
	 * @param name Set the Name of the User.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the Connection Protocol.
	 * @return The Connection Protocol this User uses
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * Set the Connection Protocol.
	 * @param protocol The Connection Protocol this User uses
	 */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
}
