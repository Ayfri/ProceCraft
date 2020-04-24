package main.java.fr.ayfri.Minecraft.client.renderer;

import processing.core.PApplet;

public enum ShaderType {
	TRIANGLES(PApplet.TRIANGLES),
	POINTS(PApplet.POINTS),
	LINES(PApplet.LINES),
	BASIC(0);
	
	private final int type;
	
	ShaderType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
}
