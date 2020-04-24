package main.java.fr.ayfri.Minecraft.client.renderer;

import static processing.core.PConstants.DISABLE_DEPTH_TEST;
import static processing.core.PConstants.ENABLE_DEPTH_TEST;

public class GuiText {
	private final processing.core.PApplet sketch;
	private String text;
	private String name;
	private int x;
	private int y;
	
	public GuiText(processing.core.PApplet sketch, int x, int y) {
		this(sketch, "none", x, y);
	}
	
	public GuiText(processing.core.PApplet sketch, Object text, int x, int y) {
		this.sketch = sketch;
		this.text = text.toString();
		this.x = x;
		this.y = y;
	}
	
	public void draw(String name) {
		draw(name, this.text);
	}
	
	public void draw(String name, Object text) {
		sketch.hint(DISABLE_DEPTH_TEST);
		sketch.pushMatrix();
		sketch.resetMatrix();
		sketch.text(name + " : " + text.toString(), x, y, -500);
		sketch.popMatrix();
		sketch.hint(ENABLE_DEPTH_TEST);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(final Object text) {
		this.text = text.toString();
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(final int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(final int y) {
		this.y = y;
	}
}
