package com.github.desmaster.gfx;

import org.lwjgl.opengl.Display;

import com.github.desmaster.main.Stibbs;

public class Counter {

	Stibbs game;

	long lastFrame;
	long lastFPS;
	long hours = 0;
	long minutes = 0;
	long seconds = 0;
	int fps;

	public Counter(Stibbs game) {
		this.game = game;
		game.getDelta();
		lastFPS = game.getTime();
	}
	
	public String time() {
		seconds++;
		if(seconds / 2 == 60) {
			minutes++;
			seconds = 0;
			if(minutes == 60) {
				hours++;
				minutes = 0;
			}
		}
		String h;
		String m;
		String s;
		if (seconds / 2 < 10) {
			s = "0" + seconds / 2;
		} else {
			s = "" + seconds / 2;
		}
		if (minutes < 10) {
			m = "0" + minutes;
		} else {
			m = "" + minutes;
		}
		if (hours < 10) {
			h = "0" + hours;
		} else {
			h = "" + hours;
		}
		return h + ":" + m + ":" + s;
	}

	public void tick() {
		if (game.getTime() - lastFPS > 1000) {
			Display.setTitle(Stibbs.TITLE + " | FPS: " + fps + " | Time: " + time());
			time();
			game.say("fps: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

}
