package net.medsouz.miney.client.listeners;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.medsouz.miney.client.gui.GuiScreenMineyOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class OverlayListener extends KeyHandler{

	public OverlayListener(KeyBinding[] keyBindings, boolean[] repeatings) {
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel() {
		return "Miney Input Listener";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if(tickEnd){
			if(!(Minecraft.getMinecraft().currentScreen instanceof GuiScreenMineyOverlay) && !Keyboard.areRepeatEventsEnabled()){
				Minecraft.getMinecraft().displayGuiScreen(new GuiScreenMineyOverlay(Minecraft.getMinecraft().currentScreen));
			}
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}
}
