package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet0PlayerLogin extends Packet{

	public String username;
	public String sessionID;
	
	@Override
	public int getID() {
		return 0;
	}

	@Override
	public String getName() {
		return "Player Login";
	}

	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeUTF(username);
			dos.writeUTF(sessionID);
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
			String[] loginData = new String[2];
			loginData[0] = dis.readUTF();
			loginData[1] = dis.readUTF();
			dis.close();
			return loginData;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}

}
