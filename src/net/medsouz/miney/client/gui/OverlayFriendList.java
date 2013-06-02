package net.medsouz.miney.client.gui;

import net.medsouz.miney.client.data.FriendManager;
import net.medsouz.miney.common.data.Friend;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class OverlayFriendList extends OverlayState{
	GuiButton next = new GuiButton(0, 0, 0, 35, 20, "->");
	GuiButton previous = new GuiButton(1, 0, 0, 35, 20, "<-");
	GuiButton back = new GuiButton(2, 0, 0, 80, 20, "Main Menu");
	GuiButton add = new GuiButton(3, 0, 0 , 80, 20, "Add Friend");
	int offset = 0;
	
	public OverlayFriendList(GuiScreenMineyOverlay o) {
		super(o);
		FriendManager.updateFriends();
	}

	@Override
	public void init() {
		overlay.addToButtonList(next);
		overlay.addToButtonList(previous);
		overlay.addToButtonList(back);
		overlay.addToButtonList(add);
	}

	@Override
	public void drawOverlay(int mouseX, int mouseY, float par3, int scroll) {
		if (overlay.getButtonList().size() < 3) {
			return;
		}
		overlay.getButtonList().get(0).xPosition = (overlay.width - 400) / 2 + 55;
		overlay.getButtonList().get(0).yPosition = overlay.height - 55 - scroll;
		overlay.getButtonList().get(0).enabled = offset < FriendManager.getFriends().size() - 4;
		overlay.getButtonList().get(1).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(1).yPosition = overlay.height - 55 - scroll;
		overlay.getButtonList().get(1).enabled = offset > 0;
		overlay.getButtonList().get(2).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(2).yPosition = overlay.height - 30 - scroll;
		overlay.getButtonList().get(3).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(3).yPosition = overlay.height - 80 - scroll;
		if(!(FriendManager.getFriends().size() == 0)){
			for(int x = 0; x < 4; x++){
				if(x + offset >= FriendManager.getFriends().size()) break;
				drawFriend(FriendManager.getFriends().get(x + offset), overlay.width / 2 - 75 + (x * 75), overlay.height - scroll - 60);
			}
		}else{
			if(!FriendManager.isWaiting()){
				overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "I am terribly sorry \u00A7e"+Minecraft.getMinecraft().session.username, (overlay.width - 400)/2 + 245, overlay.height - scroll - 60, 16777215);
				overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "It appears you have no friends added yet.", (overlay.width - 400)/2 + 245, overlay.height - scroll - 50, 16777215);
				overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "To add a friend press the \"Add Friend\" button on the left.", (overlay.width - 400)/2 + 245, overlay.height - scroll - 40, 16777215);
			}
		}
		if(FriendManager.isWaiting()){
			overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Getting \u00A7e"+Minecraft.getMinecraft().session.username+"\u00A7f's friends...", (overlay.width - 400)/2 + 245, overlay.height - scroll - 50, 16777215);
		}
	}

	@Override
	public void onMouseClick(int par1, int par2, int par3) {
		for(int x = 0; x < 4; x++){
			if(par1 > overlay.width / 2 - 75 + (x * 75) - 30 && par1 < overlay.width / 2 - 75 + (x * 75) + 30 && par2 > overlay.height - 90 && par2 < overlay.height && !(x + offset >= FriendManager.getFriends().size())){
				System.out.println(x);
			}
		}
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if(button.id == 0){
			offset = offset + 4;
		}
		if(button.id == 1){
			offset = offset - 4;
		}
		if(button.id == 2){
			overlay.setOverlay(new OverlayMainMenu(overlay));
		}
		if(button.id == 3){
			overlay.setOverlay(new OverlayAddFriend(overlay));
		}
	}

	@Override
	public void keyTyped(char letter, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deinit() {
		// TODO Auto-generated method stub
		
	}
	
	public void drawFriend(Friend user, int x, int y){
		drawPlayer(user.getUsername(), x, y, 1f);
		overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "\u00A7e"+user.getUsername(), x, y + 30, 16777215);
		overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, (user.isOnline())?"Online":"Offline", x, y + 40, 16777215);
		overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, user.getStatus(), x, y + 50, 16777215);
	}

}
