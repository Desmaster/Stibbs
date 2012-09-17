package com.github.desmaster.main;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class LWJGLTest {

	private static boolean finished = false;
	private static float angle;
	private static final int FRAMERATE = 60;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String TITLE = "LWJGL Test";
	private static DisplayMode size;

	public LWJGLTest() {
		size = new DisplayMode(WIDTH, HEIGHT);
	}

	public void run() {
		while (!finished) {
			Display.update();
			if (Display.isCloseRequested()) {
				finished = true;
			} else if (Display.isActive()) {
				logic();
				render();
				Display.sync(FRAMERATE);
			}
		}
	}

	public static void init(boolean fullscreen) throws Exception {
		Display.setTitle(TITLE);
		Display.setFullscreen(fullscreen);
		Display.setDisplayMode(size);
		Display.setVSyncEnabled(true);
		Display.create();
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

	private void logic() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			finished = true;
		}
		angle += 2.0f % 360;
	}

	private void render() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display
				.getDisplayMode().getHeight(), -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glPushMatrix();
		GL11.glTranslatef(Display.getDisplayMode().getWidth() / 2, Display
				.getDisplayMode().getHeight() / 2, 0.0f);
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
		LWJGLTest game = new LWJGLTest();
		game.start();
	}

}
