package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet7MessageRead extends Packet{

	public int messageID;
	
	public int getID() {
		return 7;
	}

	public String getName() {
		return "Message Read";
	}
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeInt(messageID);
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
			int id = dis.readInt();
			dis.close();
			return id;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
