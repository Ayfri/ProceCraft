package main.java.fr.ayfri.Minecraft.utils;

import main.java.fr.ayfri.Minecraft.client.renderer.Shader;
import main.java.fr.ayfri.Minecraft.client.renderer.ShaderType;
import main.java.fr.ayfri.Minecraft.register.Registry;
import processing.core.PApplet;
import processing.opengl.PShader;

import java.io.File;
import java.io.FileNotFoundException;

public class ShaderManager {
	private final PApplet sketch;
	
	public ShaderManager(final PApplet sketch) {this.sketch = sketch;}
	
	public void init() throws FileNotFoundException {
		try {
			final Shader basic = new Shader(sketch, getShader("fragment", "vertex"), "basic", ShaderType.BASIC);
			
			Registry.register(basic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PShader getShader(String fragName, String vertexName) throws FileNotFoundException {
		String fragPath = "src\\main\\resources\\shaders\\" + fragName + ".glsl";
		String vertexPath = "src\\main\\resources\\shaders\\" + vertexName + ".glsl";
		File fragFile = new File(fragPath);
		File vertexFile = new File(vertexPath);
		
		if (fragFile.exists()) {
			if (vertexFile.exists()) {
				Logger.info("Fragment Shader " + fragFile.getAbsolutePath() + " found.", "ShaderMapping");
				Logger.info("Vertex Shader " + vertexFile.getAbsolutePath() + " found.", "ShaderMapping");
				return sketch.loadShader(fragPath, vertexPath);
			} else {
				throw new FileNotFoundException("File " + vertexFile.getAbsolutePath() + " not found.");
			}
		} else {
			throw new FileNotFoundException("File " + fragFile.getAbsolutePath() + " not found.");
		}
	}
	
	public PShader getShader(String fragName) throws FileNotFoundException {
		String path = "src\\main\\resources\\shaders\\" + fragName + ".glsl";
		File file = new File(path);
		
		if (file.exists()) {
			Logger.info("Fragment Shader " + file.getAbsolutePath() + " found.", "ShaderMapping");
			return sketch.loadShader(path);
		} else {
			throw new FileNotFoundException("File " + file.getAbsolutePath() + " not found.");
		}
	}
}
