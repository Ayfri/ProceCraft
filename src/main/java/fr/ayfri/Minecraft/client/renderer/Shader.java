package main.java.fr.ayfri.Minecraft.client.renderer;

import processing.opengl.PShader;

public class Shader {
	private final processing.core.PApplet sketch;
	private final processing.opengl.PShader shader;
	private final String name;
	private final ShaderType type;
	
	public Shader(processing.core.PApplet sketch, processing.opengl.PShader shader, String name, ShaderType type) {
		this.sketch = sketch;
		this.shader = shader;
		this.name = name;
		this.type = type;
	}
	
	public void process() {
		if (type.getType() == 0) {
			sketch.shader(shader);
		} else {
			sketch.shader(shader, type.getType());
		}
	}
	
	public void bind() {
		shader.bind();
	}
	
	public void unbind() {
		shader.unbind();
	}
	
	public String getName() {
		return name;
	}
	
	public PShader getShader() {
		return shader;
	}
	
	public ShaderType getType() {
		return type;
	}
}
