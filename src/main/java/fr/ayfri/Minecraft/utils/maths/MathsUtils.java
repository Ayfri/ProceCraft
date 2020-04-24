package main.java.fr.ayfri.Minecraft.utils.maths;

public class MathsUtils {
	public static int rangeInt(int number, int max) {
		return rangeInt(number, 0, max);
	}
	
	public static int rangeInt(int number, int min, int max) {
		while (Math.abs(number) > max) {
			number -= max;
		}
		while (Math.abs(number) < min) {
			number += max;
		}
		
		return number;
	}
	
	// proche
	public static boolean isNear(int base, int test) {
		return isNear(base, test, 1);
	}
	
	
	// proche
	public static boolean isNear(int base, int test, int limit) {
		final int value = base - test;
		return Math.abs(value) <= limit;
	}
}
