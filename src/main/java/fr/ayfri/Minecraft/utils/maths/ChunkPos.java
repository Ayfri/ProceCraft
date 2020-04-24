package main.java.fr.ayfri.Minecraft.utils.maths;

import main.java.fr.ayfri.Minecraft.Main;
import main.java.fr.ayfri.Minecraft.world.Chunk;

public class ChunkPos extends Vector2i {
	public ChunkPos(final int x, final int y) {
		super(x, y);
	}
	
	public Chunk getChunkAt() {
		return Main.minecraft.getWorld().getChunks().values().stream().filter(chunk -> chunk.getPosition().equals(this)).findFirst().orElse(null);
	}
}
