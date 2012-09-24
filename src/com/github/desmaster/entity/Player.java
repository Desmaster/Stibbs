package com.github.desmaster.entity;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.github.desmaster.input.InputHandler;

public class Player {

	public double x = 0.0d;
	public double y = 0.0d;
	
	public Player(double x, double y, InputHandler input) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		
	}
	
	public void render() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display
				.getDisplayMode().getHeight(), 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glColor3f(0.7f, 0.4f, 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, 0.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(-50, -50);
		GL11.glVertex2f(50, -50);
		GL11.glVertex2f(50, 50);
		GL11.glVertex2f(-50, 50);
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
}
