package main.java.fr.ayfri.Minecraft.blocks;

import main.java.fr.ayfri.Minecraft.register.Registry;

public abstract class Block {
	private final String name;
	
	public Block(String name) {
		this.name = name;
	}
	
	public String getFullName() {
		return Registry.setFullName(name);
	}
	
	public String getName() {
		return name;
	}
}
