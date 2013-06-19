package net.medsouz.miney.client.gui;

import net.medsouz.miney.client.data.FriendManager;
import net.medsouz.miney.client.data.MessageManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class OverlayMainMenu extends OverlayState{
	
	GuiButton friends = new GuiButton(0, 0, 0, 80, 20, "Friends");
	GuiButton messages = new GuiButton(1, 0, 0, 80, 20, "Messages");
	GuiButton settings = new GuiButton(2, 0, 0, 80, 20, "Settings");
	String skinurl = "http://s3.amazonaws.com/MinecraftSkins/"+Minecraft.getMinecraft().session.username+".png";
	
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
	public void drawOverlay(int mouseX, int mouseY, float par3, int scroll) {
		if (overlay.getButtonList().size() < 3) {
			return;
		}
		overlay.getButtonList().get(0).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(0).yPosition = overlay.height - 80 - scroll;
		overlay.getButtonList().get(1).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(1).yPosition = overlay.height - 55 - scroll;
		overlay.getButtonList().get(2).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(2).yPosition = overlay.height - 30 - scroll;
		drawPlayer(Minecraft.getMinecraft().session.username, overlay.width / 2 - 80, overlay.height - scroll - 60, 1f);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7e" + Minecraft.getMinecraft().session.username, overlay.width / 2 - 50, overlay.height - scroll - 80, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7l" + MessageManager.getUnreadMessages() + "\u00A7f unread " + ((MessageManager.getUnreadMessages() == 1) ? "message":"messages"), overlay.width / 2 - 50, overlay.height - scroll - 70, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7l" + FriendManager.getNumberOnline() + "\u00A7f " + ((FriendManager.getNumberOnline() == 1) ? "friend":"friends")+" online", overlay.width / 2 - 50, overlay.height - scroll - 60, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "Latest Message:", overlay.width / 2 - 50, overlay.height - scroll - 50, 16777215);
		drawMessageBox(MessageManager.getLatest(), (overlay.width) / 2 - 50, overlay.height - scroll);
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if(button.id == 0){
			overlay.setOverlay(new OverlayFriendList(overlay));
		}
		if(button.id == 1){
			overlay.setOverlay(new OverlayMessages(overlay));
		}
	}
	
}
