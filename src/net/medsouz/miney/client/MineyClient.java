package net.medsouz.miney.client;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;

import net.medsouz.miney.MineyClientInterface;
import net.medsouz.miney.client.listeners.OverlayListener;
import net.medsouz.miney.client.networking.MineyConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class MineyClient implements MineyClientInterface{

	public static MineyClient instance;

	public static String version = "v0.1";
	
	public KeyBinding[] overlayKey = {new KeyBinding("Miney Overlay", Keyboard.KEY_P)};
	
	@Override
	public void init() {
		instance = this;
		KeyBindingRegistry.registerKeyBinding(new OverlayListener(overlayKey, new boolean[] {false}));
		MineyConnection mcon = new MineyConnection();
		Thread t = new Thread(mcon);
		t.start();
	}

}
