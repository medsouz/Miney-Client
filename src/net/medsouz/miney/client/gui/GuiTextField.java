package net.medsouz.miney.client.gui;

import java.lang.reflect.Field;

import net.minecraft.client.gui.FontRenderer;

public class GuiTextField extends net.minecraft.client.gui.GuiTextField{
	
	public GuiTextField(FontRenderer par1FontRenderer, int par2, int par3, int par4, int par5) {
		super(par1FontRenderer, par2, par3, par4, par5);
	}
	
	public void setXPos(int x){
		try {
			Field xPos = net.minecraft.client.gui.GuiTextField.class.getDeclaredField("xPos");
			xPos.setAccessible(true);
			xPos.set(this, x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setYPos(int y){
		try {
			Field yPos = net.minecraft.client.gui.GuiTextField.class.getDeclaredField("yPos");
			yPos.setAccessible(true);
			yPos.set(this, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setTextFinal(String txt){
		try {
			Field text = net.minecraft.client.gui.GuiTextField.class.getDeclaredField("text");
			text.setAccessible(true);
			text.set(this, txt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeRegex(String regexString){
		String newText = getText().replaceAll(regexString, "");
		if(getCursorPosition() > newText.length()){
			setCursorPosition(newText.length());
		}
		setTextFinal(newText);
	}
}
