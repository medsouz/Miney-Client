package net.medsouz.miney.client.gui;

import net.minecraft.client.gui.GuiButton;

public abstract class OverlayState {
	
	public GuiScreenMineyOverlay overlay;
	
	public OverlayState(GuiScreenMineyOverlay o){
		overlay = o;
	}
	
	public abstract void init();
	
	public abstract void drawOverlay(int mouseX, int mouseY, float par3, int scroll);
	
	public abstract void onMouseClick(int par1, int par2, int par3);
	
	public abstract void actionPerformed(GuiButton button);
}
