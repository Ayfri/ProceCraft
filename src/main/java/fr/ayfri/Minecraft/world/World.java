package main.java.fr.ayfri.Minecraft.world;

import main.java.fr.ayfri.Minecraft.utils.Logger;
import main.java.fr.ayfri.Minecraft.utils.maths.ChunkPos;
import processing.core.PApplet;

import java.util.HashMap;

public class World {
	private final processing.core.PApplet sketch;
	private final HashMap<ChunkPos, Chunk> chunks;
	
	public World(processing.core.PApplet sketch) {
		this.sketch = sketch;
		chunks = new HashMap<>();
		Logger.log("World created.", "Loading");
	}
	
	public void init() {
		Chunk firstChunk = new Chunk(sketch, new ChunkPos(0, 0));
		firstChunk.generate();
		chunks.put(firstChunk.getPosition(), firstChunk);
	}
	
	public HashMap<ChunkPos, Chunk> getChunks() {
		return chunks;
	}
	
	public PApplet getSketch() {
		return sketch;
	}
}
