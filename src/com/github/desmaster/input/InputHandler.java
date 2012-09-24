package com.github.desmaster.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.github.desmaster.entity.Player;
import com.github.desmaster.main.Stibbs;

public class InputHandler {

	Stibbs game;
	double movementSpeed = 4.50;
	int gameWidth;
	private Player player;
	
	public static double x = 0.0D;
	public static double y = 0.0D;
	
	public InputHandler(Stibbs game, Player player) {
		this.game = game;
		this.player = player;
		gameWidth = Display.getDisplayMode().getWidth();
	}
	
	public void tick() {
		x = 0.0D;
		y = 0.0D;
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			game.finished = true;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			x -= movementSpeed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			x += movementSpeed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			y += movementSpeed;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			y -= movementSpeed;
		}
		player.x += x;
		player.y += y;
	}
	
}
