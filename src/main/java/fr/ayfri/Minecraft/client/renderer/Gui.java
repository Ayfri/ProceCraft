package main.java.fr.ayfri.Minecraft.client.renderer;

import main.java.fr.ayfri.Minecraft.utils.MapUtils;

import java.util.HashMap;

public class Gui {
	private final HashMap<String, GuiText> guiTexts = new HashMap<>();
	
	public GuiText getGuiTextFromName(String name) {
		return guiTexts.get(name);
	}
	
	public void draw() {
		for (final GuiText text : guiTexts.values()) {
			text.draw(MapUtils.getKeysByValue(guiTexts, text).toArray()[0].toString());
		}
	}
	
	public void addGuiText(String name, GuiText text) {
		guiTexts.put(name, text);
	}
	
	public void removeGuiText(String name) {
		guiTexts.remove(name);
	}
	
	public HashMap<String, GuiText> getGuiTexts() {
		return guiTexts;
	}
}
