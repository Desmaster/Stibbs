package com.github.desmaster.gfx;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.github.desmaster.main.Stibbs;
import com.github.desmaster.physics.Colission;

public class Render {
	
	float angle = 0.0f;
	
	public Render() {
		
	}
	
	public void addColission(Colission col) {
		
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
		GL11.glTranslatef((float) Stibbs.x, (float) Stibbs.y, 0.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(-50, -50);
		GL11.glVertex2f(50, -50);
		GL11.glVertex2f(50, 50);
		GL11.glVertex2f(-50, 50);
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
}
