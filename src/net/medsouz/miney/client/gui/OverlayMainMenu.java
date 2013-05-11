package net.medsouz.miney.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class OverlayMainMenu extends OverlayState{
	
	GuiButton friends = new GuiButton(0, 0, 0, 80, 20, "Friends");
	GuiButton messages = new GuiButton(1, 0, 0, 80, 20, "Messages");
	GuiButton settings = new GuiButton(2, 0, 0, 80, 20, "Settings");
	
	public OverlayMainMenu(GuiScreenMineyOverlay o) {
		super(o);
	}

	@Override
	public void init() {
		overlay.addToButtonList(friends);
		overlay.addToButtonList(messages);
		overlay.addToButtonList(settings);
	}

	@Override
	public void drawOverlay(int mouseX, int mouseY, int scroll) {
		if(overlay.getButtonList().size() < 3){
			return;
		}
		overlay.getButtonList().get(0).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(0).yPosition = overlay.height - 80 - scroll;
		overlay.getButtonList().get(1).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(1).yPosition = overlay.height - 55 - scroll;
		overlay.getButtonList().get(2).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(2).yPosition = overlay.height - 30 - scroll;
	}

	@Override
	public void onMouseClick(int par1, int par2, int par3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(GuiButton button) {
		// TODO Auto-generated method stub
		
	}
	
}
