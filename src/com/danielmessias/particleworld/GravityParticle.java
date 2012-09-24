package com.danielmessias.particleworld;

import org.lwjgl.input.Mouse;

public class GravityParticle extends Particle{
	
	private int mdx;
	private float dx,dy;
	private final float dxmax = 8;
	private final float dymax = 30;
	
	public GravityParticle(){
		super(Mouse.getX(),ParticleWorld.winHeight-Mouse.getY(),ParticleType.LINE);
		dx += (Mouse.getDX()/5);
		dy += Mouse.getDY()/-5;
	}
	
	public void update(){
		super.update();
		
		prevx = x;
		prevy = y;

		dy+=0.5;
		if(dy>dymax) dy = dymax;
		
		dx += (1-random.nextInt(3));
		if(dx>dxmax) dx -= 6;
		if(dx<dxmax*-1) dx += 6;
		
		y+=dy;
		x+=dx;
		
		
		if(y>ParticleWorld.winHeight){
			killParticle = true;
		}
	}
}
