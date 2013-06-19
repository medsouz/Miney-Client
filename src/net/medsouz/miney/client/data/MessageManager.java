package net.medsouz.miney.client.data;

import java.util.ArrayList;
import java.util.List;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.common.data.DataType;
import net.medsouz.miney.common.data.Message;
import net.medsouz.miney.common.packet.Packet3RequestData;
import net.medsouz.miney.common.packet.Packet8SendMessage;
import net.medsouz.miney.common.packet.PacketManager;

public class MessageManager {
	private static List<Message> messages = new ArrayList<Message>();
	private static int unread = 0;
	private static boolean waiting = true;
	
	public static void updateMessages(){
		if(MineyClient.isLoggedIn()){
			waiting = true;
			Packet3RequestData p3 = new Packet3RequestData();
			p3.dataType = DataType.messages;
			if(MineyClient.connection != null){
				PacketManager.sendPacket(p3, MineyClient.connection.getOutputStream());
			}
		}
	}
	
	public static void setMessages(List<Message> f){
		messages = f;
		unread = 0;
		for(Message m : messages){
			if(!m.isRead()){
				unread++;
			}
		}
		waiting = false;
	}
	
	public static List<Message> getMessages(){
		return messages;
	}

	public static Message getLatest() {
		Message latest;
		if(messages.size() == 0){
			latest = new Message("No recent messages", "", "", 0, -1, 0, true);
		}else{
			latest = messages.get(0);
		}
		return latest;
	}

	public static boolean isWaiting() {
		return waiting;
	}
	
	public static int getUnreadMessages(){
		return unread;
	}
	
	public static void sendMessage(String targ, String top, String msg, int pId){
		Packet8SendMessage p8 = new Packet8SendMessage();
		p8.target = targ;
		p8.topic = top;
		p8.message = msg;
		p8.parentId = pId;
		PacketManager.sendPacket(p8, MineyClient.connection.getOutputStream());
	}
}
