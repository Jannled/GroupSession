package com.github.jannled.groupsession.packetdealer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import com.github.jannled.groupsession.Config;
import com.github.jannled.lib.Print;

public class Packet
{
	Connection connection;
	
	/** */
	private DatagramPacket packet;
	
	/** 1 Byte Mime type and resend. The first four bits are mirrored in the second four bits */
	private byte meta = 0x00000000;
	
	/** 4 Byte Index of the message, gets increased by one , if one number gets skipped it requests the packet again */
	private int index;
	
	/** 8 Byte Checksum used to make sure the message got transmitted probably */
	private long checksum;
	
	/** The transmitted data*/
	byte[] data;
	
	/**
	 * An outgoing packet. Need to be send manually by calling <code>send()</code>
	 * @param connection Where the packet should go
	 * @param data The data the packet should contain
	 * @param index The index of the message, every message has its own index to enable the reciever to re request the packet
	 */
	public Packet(Connection connection, byte[] data)
	{
		this.connection = connection;
		this.packet = null;
		this.data = data;
		this.index = connection.getOutgoingIndex();
		checksum = calculateChecksum(data);
	}
	
	/**
	 * An incoming packet
	 * @param connection Where the packet came from
	 * @param data In the data the header should already be included
	 */
	public Packet(Connection connection, DatagramPacket packet)
	{
		this.connection = connection;
		this.packet = packet;
		recieve(connection, packet.getData());
	}
	
	private void recieve(Connection connection, byte[] packetBytes)
	{
		data = new byte[Config.packetSize];
		
		meta = packetBytes[0];
		
		index = ByteBuffer.wrap(packetBytes, 1, 4).getInt();
		
		checksum = ByteBuffer.wrap(packetBytes, 5, 12).getLong();
		
		int pos = 0;
		for(int i=Config.headerSize; i<packetBytes.length; i++)
		{
			data[pos] = packetBytes[i-1];
			pos++;
		}
	}
	
	public void send()
	{
		byte[] packetBytes = new byte[Config.actualSize];
		
		packetBytes[0] = meta;
		int offset = 0;
		
		for(int i = 0; i < 4; ++i) 
		{
			offset++;
			packetBytes[offset] = (byte) (index >> (4 - i - 1 << 3));
		}
		
		
		for(int i = 0; i < 8; ++i) 
		{
			offset++;
			packetBytes[offset] = (byte) (checksum >> (8 - i - 1 << 3));
		}
		
		for(int i=0; i<data.length; i++)
		{
			packetBytes[offset + i] = data[i];
		}
		
		try
		{
			DatagramPacket packet = new DatagramPacket(packetBytes, packetBytes.length, connection.getAddress(), connection.getPort());
			connection.getSocket().send(packet);
		} catch (IOException e)
		{
			Print.e("IOException while creating packets!");
			e.printStackTrace();
		}
	}
	
	public long calculateChecksum(byte[] input)
	{
		Checksum checksum = new CRC32();
		checksum.update(input, 0, input.length);
		return checksum.getValue();
	}

	public int getIndex()
	{
		return index;
	}

	public long getChecksum()
	{
		return checksum;
	}

	public byte[] getBytes()
	{
		return data;
	}
	
	public DatagramPacket getPacket()
	{
		return packet;
	}
	
	public Connection getConnection()
	{
		return connection;
	}
}
