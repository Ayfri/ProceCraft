package main.java.fr.ayfri.Minecraft.utils.maths;

public class BlockChunkPos extends Vector3i {
	public BlockChunkPos(final int x, final int y, final int z) {
		super(MathsUtils.rangeInt(x, 16), -y, MathsUtils.rangeInt(z, 16));
	}
	
	public BlockPos toBlockPos(ChunkPos chunkPos) {
		return new BlockPos(
			getX() + chunkPos.getX() * 16,
			getY(),
			getZ() + chunkPos.getY() * 16
		);
	}
}
