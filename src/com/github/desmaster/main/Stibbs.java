package com.github.desmaster.main;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.github.desmaster.entity.Player;
import com.github.desmaster.gfx.Counter;
import com.github.desmaster.gfx.Render;
import com.github.desmaster.input.InputHandler;

public class Stibbs {

	public boolean finished = false;
	long lastFrame;
	long lastFPS;
	int fps;
	public static double x;
	public static double y;
	private static final int FRAMERATE = 60;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	public static final String TITLE = "LWJGL Test";
	private DisplayMode size;
	private Counter counter;
	private InputHandler input;
	private Render render;
	private Player player;

	public Stibbs() {
		size = new DisplayMode(WIDTH, HEIGHT);
		player = new Player((double) size.getHeight() / 2, (double)size.getHeight() / 2, 50, 50);
		counter = new Counter(this);
		input = new InputHandler(this, player);
		render = new Render();
	}

	public void run() {
		while (!finished) {
			Display.update();
			if (Display.isCloseRequested()) {
				finished = true;
			} else {
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
	
	public static void say(String s) {
		System.out.println(s);
	}

	public void tick() {
		input.tick();
		player.tick();
		render.tick();
		counter.tick();
	}

	private void render() {
		player.render();
	}

	public static void main(String[] args) {
		Stibbs game = new Stibbs();
		game.start();
	}

}
