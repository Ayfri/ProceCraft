package main.java.fr.ayfri.Minecraft.world;

import main.java.fr.ayfri.Minecraft.Main;
import main.java.fr.ayfri.Minecraft.blocks.Block;
import main.java.fr.ayfri.Minecraft.client.renderer.Cube;
import main.java.fr.ayfri.Minecraft.register.Registry;
import main.java.fr.ayfri.Minecraft.utils.Logger;
import main.java.fr.ayfri.Minecraft.utils.maths.BlockChunkPos;
import main.java.fr.ayfri.Minecraft.utils.maths.ChunkPos;

import java.util.HashMap;

public class Chunk {
	private final HashMap<BlockChunkPos, Cube> cubes;
	private final processing.core.PApplet sketch;
	private final ChunkPos position;
	
	public Chunk(processing.core.PApplet sketch, ChunkPos position) {
		this.sketch = sketch;
		this.position = position;
		cubes = new HashMap<>();
		Logger.log("Chunk at " + position.toString() + " created.", "ChunkLoading");
	}
	
	public void load() {
		cubes.forEach((blockChunkPos, cube) -> {
			cube.update();
			Main.minecraft.getRenderer().getCubes().put(blockChunkPos.toBlockPos(position), cube);
		});
	}
	
	public void unLoad() {
		cubes.forEach((blockChunkPos, cube) -> Main.minecraft.getRenderer().getCubes().remove(blockChunkPos.toBlockPos(position)));
	}
	
	public void generate() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 3; k++) {
					addCube(new BlockChunkPos(i, k, j), Registry.getBlocks().get(Registry.setFullName("dirt")));
				}
			}
		}
	}
	
	public void addCube(BlockChunkPos position, Block block) {
		if (block == null) {
			Logger.warn("A Block that you tried to load isn't in the Registry", "ChunkLoading");
		}
		
		addCube(new Cube(sketch, position, block, this));
	}
	
	public void addCube(Cube cube) {
		cubes.put(cube.getPosition(), cube);
	}
	
	public void removeCube(BlockChunkPos pos) {
		cubes.remove(pos);
	}
	
	public HashMap<BlockChunkPos, Cube> getCubes() {
		return cubes;
	}
	
	public ChunkPos getPosition() {
		return position;
	}
}
