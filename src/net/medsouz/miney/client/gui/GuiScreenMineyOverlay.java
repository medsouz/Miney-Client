package net.medsouz.miney.client.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.medsouz.miney.client.MineyClient;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;

public class GuiScreenMineyOverlay extends GuiScreen{
	
	private boolean isScrolling = true;
	private int scroll = -100;
	private OverlayState currentState;
	
	public GuiScreen parent;
	
	public GuiScreenMineyOverlay(GuiScreen par){
		parent = par;
	}
	
	public void initGui() {
		if(MineyClient.isLoggedIn()){
			currentState = new OverlayMainMenu(this);
		}else{
			currentState = new OverlayLoggedOut(this);
		}
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
			if(scroll == 0){
				isScrolling = false;
			}
		}
		drawCenteredString(this.fontRenderer, "Miney, The Social Networking Mod by medsouz | Version "+MineyClient.version, width /2, height - 97 - scroll, 16777215);
		currentState.drawOverlay(par1, par2, scroll);
		super.drawScreen(par1, par2, par3);
	}
	
	public void drawOverlayBackground(int scroll){
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator tessellator = Tessellator.instance;
        this.mc.renderEngine.bindTexture("/net/medsouz/miney/client/assets/overlaybackground.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 64.0F;
        tessellator.startDrawingQuads();
        //tessellator.setColorOpaque_I(4210752);
        /*tessellator.addVertexWithUV(0.0D, (double)this.height, 0.0D, 0.0D, (double)((float)this.height / f));
        tessellator.addVertexWithUV((double)width, (double)this.height, 0.0D, (double)((float)width / f), (double)((float)this.height / f));
        tessellator.addVertexWithUV((double)width, 0.0D, 0.0D, (double)((float)width / f), 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);*/
        //12
        //34
        double center = (width - 400) / 2;
        tessellator.addVertexWithUV(center, height - scroll, 0.0D, 0.0D, 1);//1
        tessellator.addVertexWithUV(center + 400, height - scroll, 0.0D, (double)((float)400 / f), 1);//2
        tessellator.addVertexWithUV(center + 400, height - 100 - scroll, 0.0D, (double)((float)400 / f), 0.0D);//4
        tessellator.addVertexWithUV(center, height - 100 - scroll, 0.0D, 0.0D, 0.0D);//3
        tessellator.draw();
	}
	
	public void addToButtonList(GuiButton b){
		buttonList.add(b);
	}
	
	public List<GuiButton> getButtonList(){
		return buttonList;
	}
	
	public void setOverlay(OverlayState o){
		buttonList.clear();
		currentState = o;
	}
	
	protected void mouseClicked(int par1, int par2, int par3){
		super.mouseClicked(par1, par2, par3);
		if(currentState != null){
			currentState.onMouseClick(par1, par2, par3);
		}
	}
	
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(currentState != null){
			if(!MineyClient.isLoggedIn() && !(currentState instanceof OverlayLoggedOut)){
				setOverlay(new OverlayLoggedOut(this));//if you try to perform an action while logged out (due to connection loss or something) it will boot you to the login screen
				return;
			}
			currentState.actionPerformed(par1GuiButton);
		}
	}
}
