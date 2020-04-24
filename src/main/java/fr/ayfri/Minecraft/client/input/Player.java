package main.java.fr.ayfri.Minecraft.client.input;

import main.java.fr.ayfri.Minecraft.Main;
import main.java.fr.ayfri.Minecraft.client.renderer.Cube;
import main.java.fr.ayfri.Minecraft.utils.Logger;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

import static java.awt.event.KeyEvent.*;

public class Player {
	private final PApplet sketch;
	private final PVector up;
	private final PVector position;
	private final PVector gravity;
	private final HashMap<Integer, Boolean> keys;
	private final PVector velocity;
	private Robot robot;
	private PVector right;
	private PVector forward;
	private PVector target;
	private Point prevMouse;
	private boolean shadering;
	public final float sensitivity;
	public final float friction;
	public boolean grounded;
	public float speed;
	public float pan;
	public float tilt;
	
	public Player(PApplet sketch) {
		this(sketch, 0.01f, 1000f);
	}
	
	public Player(PApplet sketch, float near, float far) {
		this.sketch = sketch;
		
		try {
			robot = new Robot();
			robot.setAutoDelay(0);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		sensitivity = 1.6f;
		position = new PVector(0f, 0f, 0f);
		up = new PVector(0f, 1f, 0f);
		right = new PVector(1f, 0f, 0f);
		forward = new PVector(0f, 0f, 1f);
		velocity = new PVector(0f, 0f, 0f);
		pan = 0f;
		tilt = 0f;
		friction = 0.88f;
		keys = new HashMap<>();
		
		sketch.perspective(PConstants.PI / 3f, (float) sketch.width / (float) sketch.height, near, far);
		gravity = new PVector(0f, 0.00f, 0f);
		Logger.log("Player created.", "Loading");
	}
	
	public void update() {
		velocity.add(gravity);
		/*
		if(grounded && keys.containsKey(VK_SPACE)) {
			grounded = false;
			velocity.y += 0.2f;
		}*/
		
		final Point mouse = MouseInfo.getPointerInfo().getLocation();
		if (prevMouse == null) {
			prevMouse = new Point(mouse.x, mouse.y);
		}
		
		int w = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		int h = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
		
		if (mouse.x < 1 && (mouse.x - prevMouse.x) < 0) {
			robot.mouseMove(w - 2, mouse.y);
			mouse.x = w - 2;
			prevMouse.x = w - 2;
		}
		
		if (mouse.x > w - 2 && (mouse.x - prevMouse.x) > 0) {
			robot.mouseMove(2, mouse.y);
			mouse.x = 2;
			prevMouse.x = 2;
		}
		
		if (mouse.y < 1 && (mouse.y - prevMouse.y) < 0) {
			robot.mouseMove(mouse.x, h - 2);
			mouse.y = h - 2;
			prevMouse.y = h - 2;
		}
		
		if (mouse.y > h - 1 && (mouse.y - prevMouse.y) > 0) {
			robot.mouseMove(mouse.x, 2);
			mouse.y = 2;
			prevMouse.y = 2;
		}
		
		pan += PApplet.map(mouse.x - prevMouse.x, 0, sketch.width, 0, PConstants.TWO_PI) * sensitivity;
		tilt += PApplet.map(mouse.y - prevMouse.y, 0, sketch.height, 0, PConstants.PI) * sensitivity;
		tilt = clamp(tilt);
		
		if (tilt == PConstants.HALF_PI) {
			tilt += 0.001f;
		}
		
		forward = new PVector(PApplet.cos(pan), PApplet.tan(tilt), PApplet.sin(pan));
		forward.normalize();
		right = new PVector(PApplet.cos(pan - PConstants.HALF_PI), 0, PApplet.sin(pan - PConstants.HALF_PI));
		
		target = PVector.add(position, forward);
		prevMouse = new Point(mouse.x, mouse.y);
		
		if (keys.containsKey(VK_CONTROL) && keys.get(VK_CONTROL)) {
			speed = 0.04f;
		} else {
			speed = 0.03f;
		}
		
		applyDirection(VK_A, right, speed);
		applyDirection(VK_D, right, -speed);
		applyDirection(VK_W, new PVector(PApplet.cos(pan), 0, PApplet.sin(pan)), speed);
		applyDirection(VK_S, new PVector(PApplet.cos(pan), 0, PApplet.sin(pan)), -speed);
		applyDirection(VK_SPACE, up, -speed);
		applyDirection(VK_SHIFT, up, speed);
		
		if (gravity.y != 0) {
			velocity.x *= friction;
			velocity.z *= friction;
		} else {
			velocity.mult(friction);
		}
		position.add(velocity);
		final PVector center = PVector.add(position, forward);
		sketch.camera(position.x, position.y, position.z, center.x, center.y, center.z, up.x, up.y, up.z);
	}
	
	private float clamp(float x) {
		if (x > 1.5629815f) {
			return 1.5629815f;
		}
		return Math.max(x, -1.5629815f);
	}
	
	private void applyDirection(int key, PVector vector, float speed) {
		//todo : collisions
		Collection<Cube> tiles = Main.minecraft.getRenderer().getCubes().values();
		/*for (final Cube tile : tiles) {
			if (tile.getPosition().getY() + tile.size / 2f == position.y ||
			    tile.getPosition().getY() - tile.size / 2f == position.y ||
			    tile.getPosition().getX() + tile.size / 2f == position.x ||
			    tile.getPosition().getX() - tile.size / 2f == position.x ||
			    tile.getPosition().getZ() + tile.size / 2f == position.z ||
			    tile.getPosition().getZ() - tile.size / 2f == position.z
			) {
				velocity.mult(0);
				break;
			}
		}*/
		
		if (keys.containsKey(key) && keys.get(key)) {
			velocity.add(PVector.mult(vector, speed));
		}
	}
	
	public void keyEvent(KeyEvent event) {
		int key = event.getKeyCode();
		
		switch (event.getAction()) {
			case KeyEvent.PRESS:
				keys.put(key, true);
				break;
			case KeyEvent.RELEASE:
				keys.put(key, false);
				break;
		}
		
		if (keys.containsKey(VK_E) && keys.get(VK_E)) {
			this.shadering = !shadering;
		}
	}
	
	public PVector getForward() {
		return forward;
	}
	
	public PVector getPosition() {
		return position;
	}
	
	public PVector getRight() {
		return right;
	}
	
	public PVector getTarget() {
		return target;
	}
	
	public PVector getUp() {
		return up;
	}
	
	public PVector getVelocity() {
		return velocity;
	}
	
	public boolean isShadering() {
		return shadering;
	}
}