package com.github.desmaster.main;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.github.desmaster.gfx.FPSCounter;
import com.github.desmaster.input.InputHandler;

public class Stibbs {

	public boolean finished = false;
	private static float angle;
	long lastFrame;
	long lastFPS;
	int fps;
	public double x, y;
	private static final int FRAMERATE = 60;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	public static final String TITLE = "LWJGL Test";
	private DisplayMode size;
	private FPSCounter fpsCounter;
	private InputHandler input;

	public Stibbs() {
		size = new DisplayMode(WIDTH, HEIGHT);
		x = size.getWidth() / 2;
		y = size.getHeight() / 2;
		fpsCounter = new FPSCounter(this);
		input = new InputHandler(this);
	}

	public void run() {
		while (!finished) {
			Display.update();
			if (Display.isCloseRequested()) {
				finished = true;
			} else if (Display.isActive()) {
				tick();
				render();
				Display.sync(FRAMERATE);
			}
		}
	}

	public void init(boolean fullscreen) throws Exception {
		Display.setTitle(TITLE);
		Display.setFullscreen(fullscreen);
		Display.setDisplayMode(size);
		Display.setVSyncEnabled(true);
		Display.create();
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private void start() {

		try {
			init(false);
			run();
		} catch (Exception e) {
			e.printStackTrace();
			Sys.alert(TITLE,
					"An error occured while initializing the screen. The game will now exit.");
		} finally {
			stop();
		}
		System.exit(0);
	}

	public static void stop() {
		Display.destroy();
		System.exit(0);
	}
	
	public void say(String s) {
		System.out.println(s);
	}

	public void tick() {
		input.tick();
		renderTicks();
		fpsCounter.tick();
	}
	
	private void renderTicks() {
		angle -= 2.0f % 360;
	}

	private void render() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display
				.getDisplayMode().getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, 0.0f);
		GL11.glRotatef(angle, 0, 0, 1.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(-50, 50);
		GL11.glVertex2i(50, -50);
		GL11.glVertex2i(50, 50);
		GL11.glVertex2i(-50, -50);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public static void main(String[] args) {
		Stibbs game = new Stibbs();
		game.start();
	}

}
