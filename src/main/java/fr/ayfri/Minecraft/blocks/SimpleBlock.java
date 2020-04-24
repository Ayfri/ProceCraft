package main.java.fr.ayfri.Minecraft.blocks;

import main.java.fr.ayfri.Minecraft.Main;
import main.java.fr.ayfri.Minecraft.register.Registry;

import java.io.FileNotFoundException;

public class SimpleBlock extends Block {
	public SimpleBlock(String name) throws FileNotFoundException {
		super(name);
		Registry.register(Main.minecraft.getTextureManager().getImage(name), name);
	}
}
