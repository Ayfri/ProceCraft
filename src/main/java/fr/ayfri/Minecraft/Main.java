package main.java.fr.ayfri.Minecraft;

import main.java.fr.ayfri.Minecraft.utils.Logger;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Main extends PApplet {
	public static Minecraft minecraft;
	
	public static void main(String[] args) {
		Logger.info("Launching Minecraft !", "Loading");
		String[] processingArgs = { "Main" };
		Main main = new Main();
		PApplet.runSketch(processingArgs, main);
	}
	
	public void keyEvent(KeyEvent event) {
		minecraft.getPlayer().keyEvent(event);
	}
	
	public void settings() {
		size(1600, 900, P3D);
		registerMethod("keyEvent", this);
		noSmooth();
		Main.minecraft = new Minecraft(this);
	}
	
	public void setup() {
		Main.minecraft.init();
		textMode(MODEL);
		textureMode(NORMAL);
		noStroke();
		textSize(20f);
		surface.setTitle("Minecraft in Processing");
		hint(ENABLE_TEXTURE_MIPMAPS);
		((processing.opengl.PGraphicsOpenGL) g).textureSampling(3);
		Logger.info("End of Loading.", "Loading");
	}
	
	public void draw() {
		noCursor();
		background(140, 190, 255);
		Main.minecraft.getPlayer().update();
		Main.minecraft.getRenderer().draw();
	}
	
	public void dispose() {
		Logger.fatal("Shutting down program.", "Dispose");
	}
}
