package main.java.fr.ayfri.Minecraft.utils.maths;

import main.java.fr.ayfri.Minecraft.world.Chunk;

public class BlockPos extends Vector3i {
	
	public BlockPos(BlockPos pos) {
		this(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public BlockPos(final int x, final int y, final int z) {
		super(x, y, z);
	}
	
	public Chunk getChunkAt() {
		final ChunkPos chunkPos = new ChunkPos(
			getX() == 0 ? 0 : getX() / 16,
			getZ() == 0 ? 0 : getZ() / 16
		);
		return chunkPos.getChunkAt();
	}
}
