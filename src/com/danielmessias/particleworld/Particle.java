package com.danielmessias.particleworld;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Particle {
	
	public boolean killParticle;
	
	private ParticleType type;
	
	protected float prevx,prevy;
	protected float x,y;
	protected float mx,my;
	
	
	private int pointSize;
	private Color color;
	
	protected Random random;
	
	public Particle(int xPos, int yPos, ParticleType ptype){
		x = xPos;
		y = yPos;
		type = ptype;
		standardSetup();
	}
	
	private void standardSetup(){
		random = new Random();
		color = Color.green;
		pointSize = 2;
		killParticle = false;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void setPointSize(int ps){
		pointSize = ps;
	}
	
	
	public void update(){
		mx = Mouse.getX();
		my = ParticleWorld.winHeight-Mouse.getY();
	}
	
	public void render(){
		glPointSize(pointSize);
		color.bind();
		if(type == ParticleType.LINE){
			glBegin(GL_LINES);
				glVertex2f(prevx,prevy);
				glVertex2f(x,y);
			glEnd();
		}else if(type == ParticleType.POINT){
			glBegin(GL_POINTS);
			glVertex2f(x,y);
		glEnd();
		}
	}
	
}
