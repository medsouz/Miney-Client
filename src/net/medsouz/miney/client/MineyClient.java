package net.medsouz.miney.client;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;

import net.medsouz.miney.client.listeners.OverlayListener;
import net.medsouz.miney.client.networking.MineyConnection;
import net.medsouz.miney.common.packet.PacketManager;
import net.minecraft.client.settings.KeyBinding;

public class MineyClient{

	public static MineyClient instance;
	public static MineyConnection connection;
	public static String version = "v0.3";
	
	public KeyBinding[] overlayKey = {new KeyBinding("Miney Overlay", Keyboard.KEY_P)};
	
	public void init() {
		instance = this;
		KeyBindingRegistry.registerKeyBinding(new OverlayListener(overlayKey, new boolean[] {false}));
		PacketManager.registerPackets();
		connection = new MineyConnection();
		Thread t = new Thread(connection);
		t.start();
	}
	
	public static boolean isLoggedIn(){
		if(connection != null){
			return connection.isLoggedIn();
		}else{
			return false;
		}
	}

}
