package net.medsouz.miney.client.gui;

import org.lwjgl.input.Keyboard;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.common.packet.Packet5AddFriend;
import net.medsouz.miney.common.packet.PacketManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class OverlayAddFriend extends OverlayState{

	GuiButton add = new GuiButton(0, 0, 0, 80, 20, "Add");
	GuiButton back = new GuiButton(1, 0, 0, 80, 20, "Friends");
	GuiButton share = new GuiButton(2, 0, 0, 80, 20, "Share Miney");
	GuiTextField friendName = new GuiTextField(Minecraft.getMinecraft().fontRenderer, 0, 0, 200, 20);
	
	public OverlayAddFriend(GuiScreenMineyOverlay o) {
		super(o);
	}

	@Override
	public void init() {
		overlay.addToButtonList(add);
		overlay.addToButtonList(back);
		overlay.addToButtonList(share);
		friendName.setMaxStringLength(16);
		Keyboard.enableRepeatEvents(true);
	}

	@Override
	public void drawOverlay(int mouseX, int mouseY, float par3, int scroll) {
		overlay.getButtonList().get(0).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(0).yPosition = overlay.height - 55 - scroll;
		overlay.getButtonList().get(0).enabled = friendName.getText().length() >= 2;
		overlay.getButtonList().get(1).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(1).yPosition = overlay.height - 30 - scroll;
		overlay.getButtonList().get(2).xPosition = (overlay.width - 400) / 2 + 10;
		overlay.getButtonList().get(2).yPosition = overlay.height - 80 - scroll;
		friendName.setYPos(overlay.height - 55 - scroll);
		friendName.setXPos((overlay.width - 400)/2 + 145);
		friendName.drawTextBox();
		Minecraft.getMinecraft().fontRenderer.drawString("Friend Name:", (overlay.width - 400)/2 + 145, overlay.height - 65 - scroll, 16777215);
	}

	@Override
	public void onMouseClick(int par1, int par2, int par3) {
		friendName.mouseClicked(par1, par2, par3);
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if(button.id == 0){
			Packet5AddFriend p5 = new Packet5AddFriend();
			friendName.removeRegex("[^A-Za-z0-9_]");
			p5.friendName = friendName.getText();
			PacketManager.sendPacket(p5, MineyClient.connection.getOutputStream());
			overlay.setOverlay(new OverlayFriendList(overlay));
		}
		if(button.id == 1){
			overlay.setOverlay(new OverlayFriendList(overlay));
		}
		if(button.id == 2){
			//TODO: OverlayState for sharing Miney on sites like Twitter and Facebook
		}
	}

	@Override
	public void keyTyped(char letter, int id) {
		friendName.textboxKeyTyped(letter, id);
		friendName.removeRegex("[^A-Za-z0-9_]");
	}

	@Override
	public void deinit() {
		Keyboard.enableRepeatEvents(false);
	}

}
