package com.github.desmaster.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.github.desmaster.main.Stibbs;

public class InputHandler {

	Stibbs game;
	double movementSpeed = 2.75;
	int gameWidth;
	
	public InputHandler(Stibbs game) {
		this.game = game;
		gameWidth = Display.getDisplayMode().getWidth();
	}
	
	public void tick() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			game.finished = true;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			game.x -= movementSpeed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			game.x += movementSpeed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			game.y += movementSpeed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			game.y -= movementSpeed;
		}
		
	}
	
}
