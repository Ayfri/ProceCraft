package main.java.fr.ayfri.Minecraft.utils.maths;

import java.util.Objects;

public class Vector2i {
	private int x;
	private int y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i add(final Vector2i vector) {
		return add(vector.x, vector.y);
	}
	
	public Vector2i add(final int x, final int y) {
		return new Vector2i(this.x + x, this.y + y);
	}
	
	public Vector2i sub(final Vector2i vector) {
		return add(-vector.x, -vector.y);
	}
	
	public Vector2i mul(final int factor) {
		return new Vector2i(this.x * factor, this.y * factor);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Vector2i)) {
			return false;
		}
		final Vector2i vector2i = (Vector2i) o;
		return x == vector2i.x &&
		       y == vector2i.y;
	}
	
	public String toString() {
		return "[X: " + getX() + ", Y: " + getY() + "]";
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
