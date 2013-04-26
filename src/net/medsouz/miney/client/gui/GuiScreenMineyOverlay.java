package net.medsouz.miney.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;

public class GuiScreenMineyOverlay extends GuiScreen{
	
	private boolean isScrolling = true;
	private int scroll = 0;
	
	public GuiScreen parent;
	
	public GuiScreenMineyOverlay(GuiScreen par){
		parent = par;
	}
	
	public void drawScreen(int par1, int par2, float par3){
		if(parent != null){
			parent.drawScreen(par1, par2, par3);
		}
		//TODO: Nice gradient fade and background sliding in when menu is first open
		drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
		drawOverlayBackground(scroll);
		if(isScrolling){
			scroll += 2;//TODO: apply delta to smooth on computers with inconsistent framerates
			if(scroll == 200){
				isScrolling = false;
			}
		}else{
			drawCenteredString(this.fontRenderer, "Miney", 100, 15, 16777215);
		}
	}
	
	public void drawOverlayBackground(int width){
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator tessellator = Tessellator.instance;
        this.mc.renderEngine.bindTexture("/gui/background.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(4210752);
        tessellator.addVertexWithUV(0.0D, (double)this.height, 0.0D, 0.0D, (double)((float)this.height / f));
        tessellator.addVertexWithUV((double)width, (double)this.height, 0.0D, (double)((float)width / f), (double)((float)this.height / f));
        tessellator.addVertexWithUV((double)width, 0.0D, 0.0D, (double)((float)width / f), 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
	}
}
