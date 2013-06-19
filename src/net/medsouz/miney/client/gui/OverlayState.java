package net.medsouz.miney.client.gui;

import org.lwjgl.opengl.GL11;

import net.medsouz.miney.client.data.TextTools;
import net.medsouz.miney.common.data.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.Tessellator;

public abstract class OverlayState {
	
	public GuiScreenMineyOverlay overlay;
	
	public OverlayState(GuiScreenMineyOverlay o){
		overlay = o;
	}
	
	public abstract void init();
	
	public void deinit(){}
	
	public abstract void drawOverlay(int mouseX, int mouseY, float par3, int scroll);
	
	public void onMouseClick(int par1, int par2, int par3){}
	
	public void actionPerformed(GuiButton button){}
	
	public void keyTyped(char letter, int id){}
	
	public void update(){}
	
	public void drawPlayer(String user, int x, int y, float scale){
		String skinurl = "http://s3.amazonaws.com/MinecraftSkins/"+user+".png";
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ModelBiped biped = new ModelBiped();
		biped.bipedCloak.isHidden = true;
		biped.bipedEars.isHidden = true;
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 100);
		GL11.glScalef(0.5f * scale, 0.5f * scale, 0.5f * scale);
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
	}
	
	public void drawMessageBox(Message m, int x, int y){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(m.isRead()?0x808080:0xFFD700);
        tessellator.addVertexWithUV(x, y - 1, 0.0D, 0.0D, 1);
        tessellator.addVertexWithUV(x + 120, y - 1, 0.0D, 1.0D, 1);
        tessellator.addVertexWithUV(x + 120, y - 1 - 40, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(x, y - 1 - 40, 0.0D, 0.0D, 0.0D);
        tessellator.setColorOpaque_I(0);
        tessellator.addVertexWithUV(x + 1, y - 1 - 1, 0.0D, 0.0D, 1);
        tessellator.addVertexWithUV(x - 1 + 120, y - 1 - 1, 0.0D, 1.0D, 1);
        tessellator.addVertexWithUV(x - 1 + 120, y - 40, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(x + 1, y - 1 + 1 - 40, 0.0D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, TextTools.trimString(m.getTopic(), 115, "..."), x + 2, y - 38, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, (!m.getSender().equals("")?"From ":"")+"\u00A7e"+m.getSender(), x + 2, y - 28, 16777215);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, m.getSentTime(), x + 2, y - 18, 16777215);
	}
}
