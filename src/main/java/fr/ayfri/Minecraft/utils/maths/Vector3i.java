package main.java.fr.ayfri.Minecraft.utils.maths;

import java.util.Objects;

public class Vector3i {
	private int x;
	private int y;
	private int z;
	
	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3i add(final Vector3i vector) {
		return add(vector.x, vector.y, vector.z);
	}
	
	public Vector3i add(final int x, final int y, final int z) {
		return new Vector3i(this.x + x, this.y + y, this.z + z);
	}
	
	public Vector3i sub(final Vector3i vector) {
		return add(-vector.x, -vector.y, -vector.z);
	}
	
	public Vector3d mul(final int factor) {
		return new Vector3d(this.x * factor, this.y * factor, this.z * factor);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Vector3i)) {
			return false;
		}
		final Vector3i vector3i = (Vector3i) o;
		return x == vector3i.x &&
		       y == vector3i.y &&
		       z == vector3i.z;
	}
	
	public String toString() {
		return "[X: " + getX() + ", Y: " + getY() + ", Z: " + getZ() + "]";
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
	
	public int getZ() {
		return z;
	}
	
	public void setZ(int z) {
		this.z = z;
	}
}