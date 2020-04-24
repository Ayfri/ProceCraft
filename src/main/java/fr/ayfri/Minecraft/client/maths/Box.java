package main.java.fr.ayfri.Minecraft.client.maths;

import main.java.fr.ayfri.Minecraft.utils.maths.BlockPos;

public class Box {
	private final processing.core.PApplet sketch;
	
	public Box(processing.core.PApplet sketch) {
		this.sketch = sketch;
	}
	
	public processing.core.PShape generateBox(processing.core.PImage texture, BlockPos position) {
		processing.core.PShape box = sketch.createShape();
		
		box.translate(position.getX(), position.getY(), position.getZ());
		box.beginShape(sketch.QUADS);
		box.texture(texture);
		
		
		// -Y "top" face
		box.vertex(-1, -1, -1, 0, 0);
		box.vertex(1, -1, -1, 1, 0);
		box.vertex(1, -1, 1, 1, 1);
		box.vertex(-1, -1, 1, 0, 1);
		
		box.tint(150);
		
		// +Y "bottom" face
		box.vertex(-1, 1, 1, 0, 0);
		box.vertex(1, 1, 1, 1, 0);
		box.vertex(1, 1, -1, 1, 1);
		box.vertex(-1, 1, -1, 0, 1);
		
		box.tint(200);
		
		// +Z "front" face
		box.vertex(-1, -1, 1, 0, 0);
		box.vertex(1, -1, 1, 1, 0);
		box.vertex(1, 1, 1, 1, 1);
		box.vertex(-1, 1, 1, 0, 1);
		
		// -Z "back" face
		box.vertex(1, -1, -1, 0, 0);
		box.vertex(-1, -1, -1, 1, 0);
		box.vertex(-1, 1, -1, 1, 1);
		box.vertex(1, 1, -1, 0, 1);
		
		// +X "right" face
		box.vertex(1, -1, 1, 0, 0);
		box.vertex(1, -1, -1, 1, 0);
		box.vertex(1, 1, -1, 1, 1);
		box.vertex(1, 1, 1, 0, 1);
		
		// -X "left" face
		box.vertex(-1, -1, -1, 0, 0);
		box.vertex(-1, -1, 1, 1, 0);
		box.vertex(-1, 1, 1, 1, 1);
		box.vertex(-1, 1, -1, 0, 1);
		
		box.endShape();
		return box;
	}
}
