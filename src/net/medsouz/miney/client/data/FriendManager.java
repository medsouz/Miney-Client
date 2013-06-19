package net.medsouz.miney.client.data;

import java.util.ArrayList;
import java.util.List;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.common.data.DataType;
import net.medsouz.miney.common.data.Friend;
import net.medsouz.miney.common.packet.Packet3RequestData;
import net.medsouz.miney.common.packet.PacketManager;

public class FriendManager {
	private static List<Friend> friends = new ArrayList<Friend>();
	private static int numOnline = 0;
	private static boolean waiting = false;
	
	public static void updateFriends(){
		if(MineyClient.isLoggedIn()){
			waiting = true;
			Packet3RequestData p3 = new Packet3RequestData();
			p3.dataType = DataType.friend;
			if(MineyClient.connection != null){
				PacketManager.sendPacket(p3, MineyClient.connection.getOutputStream());
			}
		}
	}
	
	public static void setFriends(List<Friend> f){
		friends = f;
		numOnline = 0;
		for(Friend fr: friends){
			if(fr.isOnline()){
				numOnline++;
			}
		}
		waiting = false;
	}
	
	public static List<Friend> getFriends(){
		return friends;
	}
	
	public static int getNumberOnline(){
		return numOnline;
	}
	
	public static boolean isWaiting(){
		return waiting;
	}
}
