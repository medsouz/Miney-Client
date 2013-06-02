package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.medsouz.miney.common.data.FriendRequest;
import net.medsouz.miney.common.data.Message;
import net.medsouz.miney.common.data.MessageType;

public class Packet6Messages extends Packet{
	
	public List<Message> messages = new ArrayList<Message>();
	
	public int getID() {
		return 6;
	}

	public String getName() {
		return "Message List";
	}
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeInt(messages.size());
			for(Message m : messages){
				if(m instanceof FriendRequest){
					dos.writeInt(MessageType.friendRequest.ordinal());
					dos.writeUTF(m.getSender());
					dos.writeLong(m.getSentTimeLong());
					dos.writeBoolean(m.isRead());
				}else{
					dos.writeInt(MessageType.message.ordinal());
					dos.writeUTF(m.getTopic());
					dos.writeUTF(m.getMessage());
					dos.writeUTF(m.getSender());
					dos.writeLong(m.getSentTimeLong());
					dos.writeInt(m.getMessageID());
					dos.writeInt(m.getParentID());
					dos.writeBoolean(m.isRead());
				}
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
			List<Message> m = new ArrayList<Message>();
			int numMessages = dis.readInt();
			for(int x = 0; x < numMessages; x++){
				if(dis.readInt() == MessageType.friendRequest.ordinal()){
					m.add(new FriendRequest(dis.readUTF(), dis.readLong(), dis.readBoolean()));
				}else{
					m.add(new Message(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readLong(), dis.readInt(), dis.readInt(), dis.readBoolean()));
				}
			}
			dis.close();
			return m;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
