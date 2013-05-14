package net.medsouz.miney.client.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.common.packet.Packet0PlayerLogin;
import net.medsouz.miney.common.packet.PacketManager;
import net.minecraft.client.Minecraft;

public class MineyConnection implements Runnable{

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private boolean isLoggedIn = false;
	private static String reason = "";
	
	@Override
	public void run() {
		try {
			socket = new Socket("localhost", 90);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			//Compose login packet
			Packet0PlayerLogin p0 = new Packet0PlayerLogin();
			p0.username = Minecraft.getMinecraft().session.username;
			p0.sessionID = Minecraft.getMinecraft().session.sessionId;
			PacketManager.sendPacket(p0, out);
			int packetId;
			while((packetId = in.readInt()) != -1){
				int len = in.readInt();
				byte[] data = new byte[len];
				in.read(data, 0, len);
				if(packetId == 0){
					System.out.println("You're not supposed to have this packet!");
					setReason("Recieved illegal packet");
					break
				}
				if(packetId == 1){
					String msg = (String)PacketManager.readPacket(packetId, data);
					System.out.println("Disconnected with message: "+msg);
					setReason(msg);
					break;
				}
				if(packetId == 2){
					boolean good = (Boolean)PacketManager.readPacket(packetId, data);
					if(good){
						isLoggedIn = true;
					}else{
						System.out.println("Login packet returned false for unknown reason");
						setReason("Unknown login failure");
						break;
					}
				}
			}
			in.close();
			out.close();
		} catch (SocketException e) {
			setReason("Connection Lost");
			if(e.getMessage().equals("Connection reset")){
				try{
					System.out.println("Connection reset, closing streams");
					in.close();
					out.close();
				}catch(Exception er){
					er.printStackTrace();
				}
			}else{
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Connection lost");
		MineyClient.connection = null;
	}
	
	public static String getReason(){
		return reason;
	}

	public static void setReason(String r){
		reason = r;
	}
	
	public boolean isLoggedIn(){
		return isLoggedIn;
	}
}
