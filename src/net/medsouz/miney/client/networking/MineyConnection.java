package net.medsouz.miney.client.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import net.medsouz.miney.common.packet.Packet0PlayerLogin;
import net.medsouz.miney.common.packet.PacketManager;
import net.minecraft.client.Minecraft;

public class MineyConnection implements Runnable{

	public Socket socket;
	public DataInputStream in;
	public DataOutputStream out;
	
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
				System.out.println("Reading packet id "+packetId);
				int len = in.readInt();
				byte[] data = new byte[len];
				in.read(data, 0, len);
				System.out.println("Packet length: "+data.length);
			}
			in.close();
			out.close();
		} catch (SocketException e) {
			if(e.getMessage().equals("Connection reset")){
				try{
					System.out.println("Connection reset, closing streams");
					in.close();
					out.close();
				}catch(Exception er){
					er.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
