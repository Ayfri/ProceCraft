package main.java.fr.ayfri.Minecraft.client.renderer;

import main.java.fr.ayfri.Minecraft.blocks.Block;
import main.java.fr.ayfri.Minecraft.client.maths.Box;
import main.java.fr.ayfri.Minecraft.register.Registry;
import main.java.fr.ayfri.Minecraft.utils.Logger;
import main.java.fr.ayfri.Minecraft.utils.maths.BlockChunkPos;
import main.java.fr.ayfri.Minecraft.utils.maths.MathsUtils;
import main.java.fr.ayfri.Minecraft.world.Chunk;
import processing.core.PImage;

import java.util.stream.Stream;

public class Cube {
	private final processing.core.PApplet sketch;
	private final BlockChunkPos position;
	private final Block block;
	private final processing.core.PImage texture;
	private final Box box;
	private final Chunk chunk;
	
	private boolean isRendered;
	private boolean isVisible;
	
	public Cube(processing.core.PApplet sketch, BlockChunkPos pos, Block block, Chunk chunk) {
		this.sketch = sketch;
		this.block = block;
		this.chunk = chunk;
		position = pos;
		box = new Box(sketch);
		isRendered = false;
		isVisible = false;
		
		if (block == null) {
			texture = Registry.getTextures().get(Registry.setFullName("void"));
		} else {
			texture = Registry.getTextures().get(Registry.setFullName(block.getName()));
		}
		
		texture.loadPixels();
	}
	
	public void update() {
		Stream<Cube> cubes = chunk.getCubes().values().stream();
		cubes = cubes.filter(cube -> MathsUtils.isNear(position.getX(), cube.position.getX()) &&
		                             MathsUtils.isNear(position.getY(), cube.position.getY()) &&
		                             MathsUtils.isNear(position.getZ(), cube.position.getZ())
		);
		isVisible = cubes.count() < 6;
	}
	
	public void draw() {
		if (isRendered || !isVisible) {
			return;
		}
		
		if (texture == null) {
			Logger.error("Texture of block " + block.getFullName() + " is null", "CubeRenderer");
		}
		
		sketch.pushMatrix();
		sketch.translate(position.getX(), position.getY(), position.getZ());
		sketch.shape(box.generateBox(texture, position.toBlockPos(chunk.getPosition())));
		sketch.popMatrix();
	}
	
	public Block getBlock() {
		return block;
	}
	
	public Chunk getChunk() {
		return chunk;
	}
	
	public BlockChunkPos getPosition() {
		return position;
	}
	
	public PImage getTexture() {
		return texture;
	}
	
	public boolean isRendered() {
		return isRendered;
	}
	
	public void setRendered(final boolean rendered) {
		isRendered = rendered;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(final boolean visible) {
		isVisible = visible;
	}
}
