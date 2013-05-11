package net.medsouz.miney.common.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PacketManager {
	
	public static List<Class> packets = new ArrayList<Class>();
	
	public static void registerPackets() {
		packets.add(Packet0PlayerLogin.class);
		packets.add(Packet1Disconnect.class);
		packets.add(Packet2ConfirmLogin.class);
	}
	
	public static void sendPacket(Packet p, DataOutputStream out) {
		try{
			out.writeInt(p.getID());
			byte[] data = p.getData();
			out.writeInt(data.length);
 			out.write(data, 0, data.length);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Object readPacket(int id, byte[] data) {
		Object dat = null;
		try{
			Class packClass = packets.get(id);
			Class[] args = {byte[].class};
			Method m = packClass.getMethod("readData", args);
			dat = m.invoke(packClass.newInstance(), new Object[] {data});
		}catch(Exception err){
			err.printStackTrace();
		}
		return dat;
	}
}
