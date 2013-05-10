package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet1Disconnect extends Packet{

	public String message;
	
	public int getID() {
		return 1;
	}

	public String getName() {
		return "Disconnect";
	}
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeUTF(message);
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
			String msg = dis.readUTF();
			dis.close();
			return msg;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
