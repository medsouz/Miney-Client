package net.medsouz.miney.client.gui;

import net.medsouz.miney.client.data.MessageManager;
import net.medsouz.miney.common.data.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class OverlayMessages extends OverlayState{

	GuiButton back = new GuiButton(0, 0, 0, 80, 20, "Main Menu");
	GuiButton next = new GuiButton(1, 0, 0, 35, 20, "->");
	GuiButton previous = new GuiButton(2, 0, 0, 35, 20, "<-");
	int offset = 0;
	
	public OverlayMessages(GuiScreenMineyOverlay o) {
		super(o);
		MessageManager.updateMessages();
	}

	@Override
	public void init() {
		overlay.addToButtonList(back);
		overlay.addToButtonList(next);
		overlay.addToButtonList(previous);
	}

	@Override
	public void drawOverlay(int mouseX, int mouseY, float par3, int scroll) {
		overlay.getButtonList().get(0).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(0).yPosition = overlay.height - 30 - scroll;
		overlay.getButtonList().get(1).xPosition = (overlay.width - 400) / 2 + 55;
		overlay.getButtonList().get(1).yPosition = overlay.height - 55 - scroll;
		overlay.getButtonList().get(1).enabled = offset < MessageManager.getMessages().size() - 4;
		overlay.getButtonList().get(2).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(2).yPosition = overlay.height - 55 - scroll;
		overlay.getButtonList().get(2).enabled = offset > 0;
		if(MessageManager.getMessages().size() == 0){
			if(!MessageManager.isWaiting()){
				overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "No new messages for \u00A7e"+Minecraft.getMinecraft().session.username+"\u00A7f.", (overlay.width - 400)/2 + 245, overlay.height - scroll - 50, 16777215);
			}
		}else{
			for(int x = 0; x < 2; x++){
				for(int y = 0; y < 2; y++){
					if(x + y + offset >= MessageManager.getMessages().size()) break;
					Message m = MessageManager.getMessages().get(x + y + offset);
					drawPlayer(m.getSender(), overlay.width/2 - 90 + (x * 150), overlay.height - scroll - 27 - (y * 43), 0.4f);
					drawMessageBox(m, overlay.width/2 - 80 + (x * 150), overlay.height - scroll - (y * 43));
				}
			}
		}
		if(MessageManager.isWaiting()){
			overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Getting \u00A7e"+Minecraft.getMinecraft().session.username+"\u00A7f's messages...", (overlay.width - 400)/2 + 245, overlay.height - scroll - 50, 16777215);
		}
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if(button.id == 0){
			overlay.setOverlay(new OverlayMainMenu(overlay));
		}
		if(button.id == 1){
			offset = offset + 4;
		}
		if(button.id == 2){
			offset = offset - 4;
		}
	}
}
