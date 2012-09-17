package com.github.desmaster.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class LWJGLTest {

	private static boolean finished = false;
	private static final int FRAMERATE = 60;
	
	public LWJGLTest() {

		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Display.setTitle("LWJGL Test");
		try {
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
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

	public static void stop() {
		Display.destroy();
		System.exit(0);
	}
	
	private void logic() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			finished = true;
		}
	}

	private void render() {

	}
	
	public static void main(String[] args) {
		LWJGLTest game = new LWJGLTest();
		
	}

}
