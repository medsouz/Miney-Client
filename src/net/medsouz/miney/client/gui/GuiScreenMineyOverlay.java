package net.medsouz.miney.client.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.medsouz.miney.client.MineyClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;

public class GuiScreenMineyOverlay extends GuiScreen{
	
	private boolean isScrolling = true;
	private int scroll = -100;
	private boolean isStateScrolling = true;
	private int stateScroll = -100;
	private boolean isStateDown = true;
	private OverlayState currentState;
	private OverlayState nextState;
	
	public GuiScreen parent;
	
	public GuiScreenMineyOverlay(GuiScreen par){
		parent = par;
	}
	
	public void initGui() {
		if(MineyClient.isLoggedIn()){
			setOverlay(new OverlayMainMenu(this));
		}else{
			setOverlay(new OverlayLoggedOut(this));
		}
		isStateDown = true;
	}
	
	private long lastFrame = System.currentTimeMillis();
	
	public void drawScreen(int par1, int par2, float par3){
		long currentTime = System.currentTimeMillis();
		if(currentState != null){
			if(!MineyClient.isLoggedIn() && !(currentState instanceof OverlayLoggedOut) && !(nextState instanceof OverlayLoggedOut)){
				setOverlay(new OverlayLoggedOut(this));
			}
		}
		if(parent != null){
			parent.drawScreen(0, 0, par3);
		}
		if(isScrolling){
			scroll += 0.3 * (currentTime - lastFrame);
			if(scroll > 0){
				scroll = 0;
				isScrolling = false;
			}
			stateScroll = scroll;
		}
		if(isStateScrolling){
			if(!isScrolling){
				if(isStateDown){
					stateScroll += 0.5 * (currentTime - lastFrame);
					if(stateScroll > 0){
						stateScroll = 0;
						isStateScrolling = false;
					}
				}else{
					stateScroll -= 0.5 * (currentTime - lastFrame);
					if(stateScroll < -100){
						stateScroll = -100;
						isStateDown = true;
						isStateScrolling = false;
					}
				}
			}
		}
		drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
		drawOverlayBackground(scroll);
		drawCenteredString(this.fontRenderer, "Miney, The Social Networking Mod by medsouz | Version "+MineyClient.version, width /2, height - 97 - scroll, 16777215);
		if(nextState != null && nextState != currentState && isStateDown){
			isStateScrolling = true;
            if(currentState != null){
            	currentState.deinit();
            }
			currentState = nextState;
			buttonList.clear();
			currentState.init();
		}
		if(currentState != null){
			currentState.drawOverlay(par1, par2, par3, stateScroll);
		}
		super.drawScreen(par1, par2, par3);
		lastFrame = currentTime;
	}
	
	public void drawOverlayBackground(int scroll){
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator tessellator = Tessellator.instance;
        this.mc.renderEngine.bindTexture("/net/medsouz/miney/client/assets/overlaybackground.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 64.0F;
        tessellator.startDrawingQuads();
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
		nextState = o;
		isStateDown = false;
		isStateScrolling = true;
	}
	
	protected void mouseClicked(int par1, int par2, int par3){
		super.mouseClicked(par1, par2, par3);
		if(currentState != null){
			currentState.onMouseClick(par1, par2, par3);
		}
	}
	
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(currentState != null){
			currentState.actionPerformed(par1GuiButton);
		}
	}
	
	public void setWorldAndResolution(Minecraft par1Minecraft, int par2, int par3){
		super.setWorldAndResolution(par1Minecraft, par2, par3);
		if(parent != null){
			parent.setWorldAndResolution(par1Minecraft, par2, par3);
		}
	}
	
	protected void keyTyped(char par1, int par2){
		if (par2 == 1){
            this.mc.displayGuiScreen(parent);
            if(currentState != null){
            	currentState.deinit();
            }
		}
		if(currentState != null){
			currentState.keyTyped(par1, par2);
		}
	}
}
