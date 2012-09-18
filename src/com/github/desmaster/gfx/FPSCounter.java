package com.github.desmaster.gfx;

import org.lwjgl.opengl.Display;

import com.github.desmaster.main.Stibbs;

public class FPSCounter {

	Stibbs game;

	long lastFrame;
	long lastFPS;
	int fps;

	public FPSCounter(Stibbs game) {
		this.game = game;
		game.getDelta();
		lastFPS = game.getTime();
	}

	public void tick() {
		int delta = game.getDelta();
		if (game.getTime() - lastFPS > 1000) {
			Display.setTitle(game.TITLE + " | FPS: " + fps);
			game.say("fps: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

}
