package com.danielmessias.particleplayground;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GoffParticle extends Particle {
	private boolean invert = false;
	int velocityX;
	double velocityY;

	public GoffParticle(int xPos, int yPos) {
		super(xPos, yPos);
	}
	
	public void update() {
		
		super.update();
		
		prevx = x;
		prevy = y;
		
		y+=velocityY;
		
//		if(Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
//			invert = !invert;
//		}
//		
//		if(invert) {
//			x = 800 - Mouse.getX();
//			y = Mouse.getY();
//		}else {
//			x = Mouse.getX();
//			y = 600 - Mouse.getY();
//		}
		if(y < ParticleWorld.winHeight && velocityY <= 15) {
			velocityY++;
		}
		
		if(y >= ParticleWorld.winHeight) {
			velocityY = -0.8 * velocityY;
		}
		
		if(SystemGoff.shouldKillParticles){
			killParticle();
		}
	}

}
