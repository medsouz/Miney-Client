package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Packet2ConfirmLogin extends Packet{

	public Boolean isGood = false;
	
	public int getID() {
		return 2;
	}

	public String getName() {
		return "Confirm Login";
	}
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeBoolean(isGood);
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
			Boolean good = dis.readBoolean();
			dis.close();
			return good;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
