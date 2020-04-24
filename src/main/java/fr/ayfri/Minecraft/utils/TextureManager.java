package main.java.fr.ayfri.Minecraft.utils;

import java.io.File;
import java.io.FileNotFoundException;

public class TextureManager {
	
	private final processing.core.PApplet sketch;
	
	public TextureManager(processing.core.PApplet sketch) {
		this.sketch = sketch;
	}
	
	public processing.core.PImage getImage(String name) throws FileNotFoundException {
		String path = "src\\main\\resources\\blocks\\textures\\" + name + ".png";
		File file = new File(path);
		
		if (file.exists()) {
			Logger.info("Texture " + file.getAbsolutePath() + " found.", "TextureMapping");
			return sketch.loadImage(path);
		} else {
			throw new FileNotFoundException("File " + file.getAbsolutePath() + " not found.");
		}
	}
}
