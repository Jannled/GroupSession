package com.github.jannled.groupsession;

public class Config
{
	public static short recievePort = 2369;
	public static int packetSize = 4096;
	public static int headerSize = 13;
	public static int actualSize = packetSize + headerSize;
	
	private Config()
	{
		// TODO Auto-generated constructor stub
	}

}
