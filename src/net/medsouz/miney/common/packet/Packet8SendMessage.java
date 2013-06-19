package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.medsouz.miney.common.data.Message;

public class Packet8SendMessage extends Packet{
	public String topic;
	public String message;
	public String target;
	public int parentId = -1;
	
	public int getID() {
		return 8;
	}

	public String getName() {
		return "Send Message";
	}
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeUTF(topic);
			dos.writeUTF(message);
			dos.writeUTF(target);
			dos.writeInt(parentId);
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
			//pretend sender is the receiver here;
			Message m = new Message(dis.readUTF(), dis.readUTF(), dis.readUTF(), -1, -1, dis.readInt(), false);
			dis.close();
			return m;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
