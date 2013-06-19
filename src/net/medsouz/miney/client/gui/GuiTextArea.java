package net.medsouz.miney.client.gui;

import net.minecraft.client.gui.Gui;

public class GuiTextArea extends Gui{
	
	private String text;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	public GuiTextArea(String txt, int x, int y, int w, int h){
		setText(txt);
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}
	
	public void render(){
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
