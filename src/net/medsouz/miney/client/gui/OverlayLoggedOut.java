package net.medsouz.miney.client.gui;

import java.awt.Desktop;
import java.net.URI;

import net.medsouz.miney.client.MineyClient;
import net.medsouz.miney.client.networking.MineyConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class OverlayLoggedOut extends OverlayState {

	GuiButton login = new GuiButton(0, 0, 0, 200, 20, "Login");
	GuiButton irc = new GuiButton(1, 0, 0, 120, 20, "IRC Channel / Support");
	GuiButton forum = new GuiButton(2, 0, 0, 120, 20, "Minecraft Forum Post");
	boolean supportsURLs = false;
	
	public OverlayLoggedOut(GuiScreenMineyOverlay o) {
		super(o);
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			supportsURLs = true;
		}
		irc.enabled = supportsURLs;
		forum.enabled = supportsURLs;
		overlay.addToButtonList(login);
		overlay.addToButtonList(irc);
		overlay.addToButtonList(forum);
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
		overlay.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "If this does not work please try restarting Minecraft.", overlay.width / 2, overlay.height - 40 - scroll, 16777215);
		overlay.getButtonList().get(1).xPosition = overlay.width / 2 - 130;
		overlay.getButtonList().get(1).yPosition = overlay.height - 30 - scroll;
		overlay.getButtonList().get(2).xPosition = overlay.width / 2 + 10;
		overlay.getButtonList().get(2).yPosition = overlay.height - 30 - scroll;
	}

	@Override
	public void onMouseClick(int par1, int par2, int par3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			MineyClient.connection = new MineyConnection();
			Thread conThread = new Thread(MineyClient.connection);
			conThread.start();
		}
		if (button.id == 1) {
			if (supportsURLs) {
				try {
					Desktop.getDesktop().browse(new URI("http://webchat.esper.net/?channels=Miney"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (button.id == 2) {
			if (supportsURLs) {
				try {
					Desktop.getDesktop().browse(new URI("http://google.com/"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
