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

	public Socket socket;
	public DataInputStream in;
	public DataOutputStream out;
	public boolean isLoggedIn = false;
	
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
				if(packetId == Packet0PlayerLogin.getID()){
					String[] login = (String[]) PacketManager.readPacket(packetId, data);
					if(login[0].equals(Minecraft.getMinecraft().session.username)){
						//if server responds with the same name then the connection was successful
						isLoggedIn = true;
						//TODO: server should respond with a new token that can be used instead of a sessionid incase Minecraft.net goes down
					}else{
						System.out.println("Login failed");
						break;
					}
				}
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
			}else{
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Connection lost");
		MineyClient.connection = null;
	}

}
