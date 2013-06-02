package net.medsouz.miney.client.data;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

public class TextTools {
	//Taken from SocialMiner's source code. I borrowed this from some part of Minecraft but I have no idea where.
	public static List<String> wordWrap(String text, int width){
		String[] var7 = text.split("\n");
		List<String> wrappedMessage = new ArrayList<String>();

		if (var7.length > 1) {
			for (int var14 = 0; var14 < var7.length; ++var14) {
				wrappedMessage.add(0, var7[var14]);
			}
		} else {
			String[] var8 = text.split(" ");
			int var9 = 0;
			String var10 = "";
			while (var9 < var8.length) {
				String var11;

				for (var11 = var10 + var8[var9++] + " "; var9 < var8.length && Minecraft.getMinecraft().fontRenderer.getStringWidth(var11 + var8[var9]) < width; var11 = var11 + var8[var9++] + " ") {
					;
				}
				int var12;

				for (; Minecraft.getMinecraft().fontRenderer.getStringWidth(var11) > width; var11 = var10 + var11.substring(var12)) {
					for (var12 = 0; Minecraft.getMinecraft().fontRenderer.getStringWidth(var11.substring(0, var12 + 1)) <= width; ++var12) {

					}
					if (var11.substring(0, var12).trim().length() > 0) {
						String var13 = var11.substring(0, var12);

						if (var13.lastIndexOf("\u00a7") >= 0) {
							var10 = "\u00a7" + var13.charAt(var13.lastIndexOf("\u00a7") + 1);
						}
						wrappedMessage.add(var13);
					}
				}
				if (Minecraft.getMinecraft().fontRenderer.getStringWidth(var11.trim()) > 0) {
					if (var11.lastIndexOf("\u00a7") >= 0) {
						var10 = "\u00a7" + var11.charAt(var11.lastIndexOf("\u00a7") + 1);
					}
					wrappedMessage.add(var11);
				}
			}
		}
		return wrappedMessage;
	}
	
	public static String trimString(String text, int width){
		return trimString(text, width, "");
	}
	
	public static String trimString(String text, int width, String append){
		char[] t = text.toCharArray();
		String out = "";
		int pointer = 0;
		while(Minecraft.getMinecraft().fontRenderer.getStringWidth(out+append) < width && pointer < t.length){
			out = out + t[pointer++];
		}
		if(pointer >= t.length){
			return out;
		}else{
			return out+append;
		}
	}
}
