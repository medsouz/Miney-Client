package net.medsouz.miney.client.gui;

import org.lwjgl.opengl.GL11;

import net.medsouz.miney.client.data.FriendManager;
import net.medsouz.miney.client.data.MessageManager;
import net.medsouz.miney.common.data.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.Tessellator;

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
		ModelBiped biped = new ModelBiped();
		biped.bipedCloak.isHidden = true;
		biped.bipedEars.isHidden = true;
		GL11.glPushMatrix();
		GL11.glTranslatef(overlay.width / 2 - 80, overlay.height - scroll - 60, 100);
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		GL11.glRotatef(-20, 1, 0, 0);
		GL11.glRotatef(205, 0, 1, 0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(32826);
		Minecraft.getMinecraft().renderEngine.obtainImageData(skinurl, new ImageBufferDownload());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,(Minecraft.getMinecraft().renderEngine.getTextureForDownloadableImage(skinurl, "/mob/char.png")));
		for (int i = 0; i < biped.boxList.size(); i++) {
			((ModelRenderer) (biped.boxList.get(i))).render(5);
		}
		GL11.glDisable(32826);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glPopMatrix();
		Minecraft.getMinecraft().renderEngine.resetBoundTexture();
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7e" + Minecraft.getMinecraft().session.username, overlay.width / 2 - 50, overlay.height - scroll - 80, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7l" + MessageManager.getMessages().size() + "\u00A7f unread messages", overlay.width / 2 - 50, overlay.height - scroll - 70, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7l" + FriendManager.getFriends().size() + "\u00A7f friends online", overlay.width / 2 - 50, overlay.height - scroll - 60, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "Latest Message:", overlay.width / 2 - 50, overlay.height - scroll - 50, 16777215);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(8421504);
        double center = (overlay.width) / 2 - 50;
        tessellator.addVertexWithUV(center, overlay.height - 1 - scroll, 0.0D, 0.0D, 1);
        tessellator.addVertexWithUV(center + 120, overlay.height - 1 - scroll, 0.0D, 1.0D, 1);
        tessellator.addVertexWithUV(center + 120, overlay.height - 1 - 40 - scroll, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(center, overlay.height - 1 - 40 - scroll, 0.0D, 0.0D, 0.0D);
        tessellator.setColorOpaque_I(0);
        tessellator.addVertexWithUV(center + 1, overlay.height - 1 - 1 - scroll, 0.0D, 0.0D, 1);
        tessellator.addVertexWithUV(center - 1 + 120, overlay.height - 1 - 1 - scroll, 0.0D, 1.0D, 1);
        tessellator.addVertexWithUV(center - 1 + 120, overlay.height - 1 + 1 - 40 - scroll, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(center + 1, overlay.height - 1 + 1 - 40 - scroll, 0.0D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		Message latest = MessageManager.getLatest();
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, latest.getTopic(), overlay.width / 2 - 47, overlay.height - scroll - 38, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7e"+latest.getSender(), overlay.width / 2 - 47, overlay.height - scroll - 28, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, latest.getSentTime(), overlay.width / 2 - 47, overlay.height - scroll - 18, 16777215);
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
