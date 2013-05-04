package net.medsouz.miney.client.gui;

public abstract class OverlayState {
	
	public GuiScreenMineyOverlay overlay;
	
	public OverlayState(GuiScreenMineyOverlay o){
		overlay = o;
	}
	
	public abstract void drawOverlay(int mouseX, int mouseY, int scroll);
}
