package net.medsouz.miney.common.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.medsouz.miney.common.data.DataType;

public class Packet3RequestData extends Packet{
	
	public DataType dataType = DataType.unknown;
	
	public int getID() {
		return 3;
	}

	public String getName() {
		return "Request Data";
	}
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeInt(dataType.ordinal());
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
			DataType type = DataType.getType(dis.readInt());
			dis.close();
			return type;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
