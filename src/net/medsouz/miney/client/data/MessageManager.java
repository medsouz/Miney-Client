package net.medsouz.miney.client.data;

import java.util.ArrayList;
import java.util.List;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.common.data.DataType;
import net.medsouz.miney.common.data.Message;
import net.medsouz.miney.common.packet.Packet3RequestData;
import net.medsouz.miney.common.packet.PacketManager;

public class MessageManager {
	private static List<Message> messages = new ArrayList<Message>();
	
	public static void updateFriends(){
		if(MineyClient.isLoggedIn()){
			Packet3RequestData p3 = new Packet3RequestData();
			p3.dataType = DataType.messages;
			PacketManager.sendPacket(p3, MineyClient.connection.getOutputStream());
		}
	}
	
	public static void setMessages(List<Message> f){
		messages = f;
	}
	
	public static List<Message> getMessages(){
		return messages;
	}
}
