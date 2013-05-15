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
	
	public static void updateFriends(){
		if(MineyClient.isLoggedIn()){
			Packet3RequestData p3 = new Packet3RequestData();
			p3.dataType = DataType.friend;
			PacketManager.sendPacket(p3, MineyClient.connection.getOutputStream());
		}
	}
	
	public static void setFriends(List<Friend> f){
		friends = f;
	}
	
	public static List<Friend> getFriends(){
		return friends;
	}
}
