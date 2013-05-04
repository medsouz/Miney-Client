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
	public void drawOverlay(int mouseX, int mouseY, int scroll) {
		friends.xPosition = (overlay.width - 400) / 2 + 10;
		friends.yPosition = overlay.height - 80 - scroll;
		friends.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
		messages.xPosition = (overlay.width - 400) / 2 + 10;
		messages.yPosition = overlay.height - 55 - scroll;
		messages.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
		settings.xPosition = (overlay.width - 400) / 2 + 10;
		settings.yPosition = overlay.height - 30 - scroll;
		settings.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
	}
	
}
