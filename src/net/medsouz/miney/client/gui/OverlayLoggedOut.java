package net.medsouz.miney.client.gui;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.client.networking.MineyConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class OverlayLoggedOut extends OverlayState {

	GuiButton login = new GuiButton(0, 0, 0, 200, 20, "Login");
	
	public OverlayLoggedOut(GuiScreenMineyOverlay o) {
		super(o);
		overlay.addToButtonList(login);
	}

	@Override
	public void drawOverlay(int mouseX, int mouseY, int scroll) {
		if(MineyClient.isLoggedIn()){
			overlay.setOverlay(new OverlayMainMenu(overlay));
		}
		overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "You are not connected to Miney!", overlay.width / 2, overlay.height - 50 - 30 - scroll, 16777215);
		overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "You can try to reconnect as \u00A7e"+Minecraft.getMinecraft().session.username+"\u00A7f by clicking login.", overlay.width / 2, overlay.height - 50 - 20 - scroll, 16777215);
		overlay.getButtonList().get(0).xPosition = overlay.width / 2 - 100;
		overlay.getButtonList().get(0).yPosition = overlay.height - 50 - 10 - scroll;
		overlay.getButtonList().get(0).enabled = (MineyClient.connection == null);
		//login.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
		overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "If this does not work please try restarting Minecraft.", overlay.width / 2, overlay.height - 40 - scroll, 16777215);
	}

	@Override
	public void onMouseClick(int par1, int par2, int par3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if(button.id == 0){
			MineyClient.connection = new MineyConnection();
			Thread conThread = new Thread(MineyClient.connection);
			conThread.start();
		}
	}

}
