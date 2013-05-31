package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.medsouz.miney.common.data.Friend;

public class Packet4FriendList extends Packet{
	
	public List<Friend> friends = new ArrayList<Friend>();
	
	public int getID() {
		return 4;
	}

	public String getName() {
		return "Friend List";
	}
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeInt(friends.size());
			for(Friend f : friends){
				dos.writeUTF(f.getUsername());
				dos.writeBoolean(f.isOnline());
				dos.writeUTF(f.getStatus());
				dos.writeBoolean(f.isFriendRequest());
			}
			dos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Override
	public Object readData(byte[] data) {
		try{
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			DataInputStream dis = new DataInputStream(bais);
			List<Friend> f = new ArrayList<Friend>();
			int numFriends = dis.readInt();
			for(int x = 0; x < numFriends; x++){
				Friend friend = new Friend(dis.readUTF(), dis.readBoolean(), dis.readUTF(), dis.readBoolean());
				f.add(friend);
			}
			dis.close();
			return f;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
