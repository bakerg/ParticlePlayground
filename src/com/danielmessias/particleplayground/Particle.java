package com.danielmessias.particleplayground;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Particle {
	
	private boolean dead;
		
	protected float prevx,prevy;
	protected float x,y;
	protected float mx,my;
	
	
	private int pointSize;
	public Color color;
	
	protected Random random;
	
	public Particle(int xPos, int yPos){
		x = xPos;
		y = yPos;
		standardSetup();
	}
	
	private void standardSetup(){
		random = new Random();
		color = Color.green;
		pointSize = 2;
		dead = false;
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

	}
	
	//Base Class Only
	protected void killParticle(){
		dead = true;
	}
	
	public boolean killed(){
		return dead;
	}
	
}
