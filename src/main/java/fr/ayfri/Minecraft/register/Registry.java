package main.java.fr.ayfri.Minecraft.register;

import main.java.fr.ayfri.Minecraft.blocks.Block;
import main.java.fr.ayfri.Minecraft.blocks.SimpleBlock;
import main.java.fr.ayfri.Minecraft.client.renderer.Shader;
import main.java.fr.ayfri.Minecraft.utils.Logger;
import processing.core.PImage;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Registry {
	private static final HashMap<String, Block> blocks = new HashMap<>();
	private static final HashMap<String, PImage> textures = new HashMap<>();
	private static final HashMap<String, Shader> shaders = new HashMap<>();
	public static final String ID = "minecraft";
	
	public static void init() throws FileNotFoundException {
		register(new SimpleBlock("void"));
		register(new SimpleBlock("dirt"));
	}
	
	public static void register(Block block) {
		register(block, block.getName());
	}
	
	public static void register(Block block, String name) {
		blocks.put(setFullName(name), block);
		Logger.info("Block " + setFullName(name) + " registered.", "Registering");
	}
	
	public static String setFullName(String name) {
		return ID + ":" + name;
	}
	
	public static void register(PImage texture, String name) {
		textures.put(setFullName(name), texture);
		Logger.info("Texture " + setFullName(name) + " registered.", "Registering");
	}
	
	public static void register(Shader shader) {
		shaders.put(setFullName(shader.getName()), shader);
		Logger.info("Shader " + setFullName(shader.getName()) + " registered.", "Registering");
	}
	
	public static HashMap<String, Block> getBlocks() {
		return blocks;
	}
	
	public static HashMap<String, Shader> getShaders() {
		return shaders;
	}
	
	public static HashMap<String, PImage> getTextures() {
		return textures;
	}
}
