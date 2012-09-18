package com.github.desmaster.input;

import org.lwjgl.input.Keyboard;

import com.github.desmaster.main.Stibbs;

public class InputHandler {

	Stibbs game;
	
	public InputHandler(Stibbs game) {
		this.game = game;
	}
	
	public void tick() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			game.finished = true;
		}
	}
	
}
