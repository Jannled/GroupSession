package com.github.jannled.groupsession.commandline;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.github.jannled.groupsession.Config;
import com.github.jannled.groupsession.Main;
import com.github.jannled.groupsession.packetdealer.Connection;
import com.github.jannled.groupsession.packetdealer.Packet;

public class CommandSend extends Command
{
	public CommandSend()
	{
		super("send");
	}

	@Override
	public boolean run(String[] params)
	{
		try {
			if(params.length > 1)
			{
				String[] s = params[0].split(":");
				InetAddress adress = InetAddress.getByName("127.0.0.1");
				short port = Config.recievePort;
				
				if(s.length == 2)
				{
					adress = InetAddress.getByName(s[0]);
					port = Short.parseShort(s[1]);
				}
				else if(s.length == 1)
				{
					adress = InetAddress.getByName(s[0]);
				}
				
				String output = "";
				Connection target = Main.main.getConnectionManager().getConnection(adress, port);
				for(int i=1; i<params.length-1; i++)
				{
					output += params[i];
				}
				Packet packet = new Packet(target, output.getBytes());
				Main.main.getConnectionManager().getPacketSender().addPacket(packet);
			}
		} catch(UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}

}
