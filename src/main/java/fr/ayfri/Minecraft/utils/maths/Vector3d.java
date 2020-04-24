package main.java.fr.ayfri.Minecraft.utils.maths;

public class Vector3d {
	private double x;
	private double y;
	private double z;
	
	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3d add(final Vector3d vector) {
		return add(vector.x, vector.y, vector.z);
	}
	
	public Vector3d add(final double x, final double y, final double z) {
		return new Vector3d(this.x + x, this.y + y, this.z + z);
	}
	
	public Vector3d sub(final Vector3d vector) {
		return add(-vector.x, -vector.y, -vector.z);
	}
	
	public Vector3d mul(final double factor) {
		return new Vector3d(this.x * factor, this.y * factor, this.z * factor);
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
}