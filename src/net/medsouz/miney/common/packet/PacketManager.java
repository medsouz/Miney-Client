package net.medsouz.miney.common.packet;

import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PacketManager {
	
	@SuppressWarnings("rawtypes")
	public static List<Class> packets = new ArrayList<Class>();
	
	public static void registerPackets() {
		packets.add(Packet0PlayerLogin.class);
		packets.add(Packet1Disconnect.class);
		packets.add(Packet2ConfirmLogin.class);
		packets.add(Packet3RequestData.class);
		packets.add(Packet4FriendList.class);
		packets.add(Packet5AddFriend.class);
		packets.add(Packet6Messages.class);
		packets.add(Packet7MessageRead.class);
		packets.add(Packet8SendMessage.class);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getPacketName(int id) {
		String name = "Unknown";
		try{
			Class packClass = packets.get(id);
			Method m = packClass.getMethod("getName");
			name = (String) m.invoke(packClass.newInstance());
		}catch(Exception err){
			err.printStackTrace();
		}
		return name;
	}
}
