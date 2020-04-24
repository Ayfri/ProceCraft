package main.java.fr.ayfri.Minecraft;

import main.java.fr.ayfri.Minecraft.client.input.Player;
import main.java.fr.ayfri.Minecraft.client.renderer.GameRenderer;
import main.java.fr.ayfri.Minecraft.register.Registry;
import main.java.fr.ayfri.Minecraft.utils.Logger;
import main.java.fr.ayfri.Minecraft.utils.ShaderManager;
import main.java.fr.ayfri.Minecraft.utils.TextureManager;
import main.java.fr.ayfri.Minecraft.world.World;
import processing.core.PApplet;

import java.io.FileNotFoundException;

public class Minecraft {
	private final processing.core.PApplet sketch;
	private final GameRenderer renderer;
	private final TextureManager textureManager;
	private final ShaderManager shaderManager;
	private final World world;
	private Player player;
	
	public Minecraft(PApplet sketch) {
		this.sketch = sketch;
		this.world = new World(sketch);
		this.textureManager = new TextureManager(sketch);
		this.shaderManager = new ShaderManager(sketch);
		this.renderer = new GameRenderer(sketch);
		Logger.log("Minecraft instance created.", "Loading");
	}
	
	public void init() {
		try {
			shaderManager.init();
			Registry.init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		player = new Player(sketch);
		world.init();
		renderer.init();
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public GameRenderer getRenderer() {
		return renderer;
	}
	
	public ShaderManager getShaderManager() {
		return shaderManager;
	}
	
	public TextureManager getTextureManager() {
		return textureManager;
	}
	
	public World getWorld() {
		return world;
	}
}
