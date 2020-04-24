package main.java.fr.ayfri.Minecraft.client.renderer;

import main.java.fr.ayfri.Minecraft.Main;
import main.java.fr.ayfri.Minecraft.register.Registry;
import main.java.fr.ayfri.Minecraft.utils.Logger;
import main.java.fr.ayfri.Minecraft.utils.maths.BlockPos;
import processing.core.PApplet;

import java.util.HashMap;

public class GameRenderer {
	private final processing.core.PApplet sketch;
	private final HashMap<BlockPos, Cube> cubes;
	private final Gui mainGui;
	
	public GameRenderer(processing.core.PApplet sketch) {
		this.sketch = sketch;
		cubes = new HashMap<>();
		mainGui = new Gui();
		Logger.log("GameRenderer instance created.", "Loading");
	}
	
	public void init() {
		Main.minecraft.getWorld().getChunks().forEach((chunkPos, chunk) -> chunk.load());
		
		mainGui.addGuiText("x", new GuiText(sketch, -510, -270));
		mainGui.addGuiText("y", new GuiText(sketch, -510, -250));
		mainGui.addGuiText("z", new GuiText(sketch, -510, -230));
		mainGui.addGuiText("cubes", new GuiText(sketch, -510, -200));
		mainGui.addGuiText("chunks", new GuiText(sketch, -380, -200));
		mainGui.addGuiText("fps", new GuiText(sketch, -510, -180));
	}
	
	public void draw() {
		mainGui.getGuiTextFromName("x").setText(Main.minecraft.getPlayer().getPosition().x);
		mainGui.getGuiTextFromName("y").setText(Main.minecraft.getPlayer().getPosition().y);
		mainGui.getGuiTextFromName("z").setText(Main.minecraft.getPlayer().getPosition().z);
		mainGui.getGuiTextFromName("cubes").setText(Main.minecraft.getRenderer().getCubes().size());
		mainGui.getGuiTextFromName("chunks").setText(Main.minecraft.getWorld().getChunks().size());
		mainGui.getGuiTextFromName("fps").setText(sketch.frameRate);
		
		if (Main.minecraft.getPlayer().isShadering()) {
			Registry.getShaders().forEach((name, shader) -> shader.process());
		} else {
			sketch.resetShader();
		}
		
		cubes.values().forEach(Cube::draw);
		mainGui.draw();
	}
	
	public HashMap<BlockPos, Cube> getCubes() {
		return cubes;
	}
	
	public Gui getMainGui() {
		return mainGui;
	}
	
	public PApplet getSketch() {
		return sketch;
	}
}
