package com.github.desmaster.entity;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;

import com.github.desmaster.main.Stibbs;

public class Player {

	public double x = 0.0d;
	public double y = 0.0d;
	
	private Rectangle rect;
	private Rectangle colbox;
	
	private double width;
	private double height;
	
	public Player(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle();
		colbox = new Rectangle();
		rect.setWidth((int) width);
		rect.setHeight((int) height);
		colbox.setWidth((int) (width));
		colbox.setHeight((int) (height));
	}
	
	public void tick() {
		int colsens = (int) (width % (height * width)) / 2;
		rect.setX((int) x);
		rect.setY((int) y);
		colbox.setX((int) x);
		colbox.setY((int) y);
		colbox.setWidth((int) (width + width / colsens));
		Stibbs.say("set width!");
		colbox.setHeight((int) (height + height / colsens));
		Stibbs.say("set height!");
	}
	
	public void render() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight(), 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glPushMatrix();
			GL11.glTranslated(x, y, 0.0f);
			GL11.glBegin(GL11.GL_QUADS); //Begin drawing Quad
			double cwidth = colbox.getWidth();
			double cheight = colbox.getHeight();
			GL11.glVertex2d(-cwidth, -cheight); //Bottom left
			GL11.glVertex2d(cwidth, -cheight); //Bottom right
			GL11.glVertex2d(cwidth, cheight); //Top right
			GL11.glVertex2d(-cwidth, cheight); //Top left
		GL11.glEnd(); //End drawing Quad
			GL11.glBegin(GL11.GL_QUADS); //Begin drawing Quad
				double width = rect.getWidth();
				double height = rect.getHeight();
				GL11.glColor3f(0.2f, 0.2f, 0.2f);
				GL11.glVertex2d(-width, -height); //Bottom left
				GL11.glVertex2d(width, -height); //Bottom right
				GL11.glVertex2d(width, height); //Top right
				GL11.glVertex2d(-width, height); //Top left
			GL11.glEnd(); //End drawing Quad
		GL11.glPopMatrix();
	}
	
}
