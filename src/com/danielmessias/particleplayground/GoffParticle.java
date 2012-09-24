package com.danielmessias.particleplayground;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GoffParticle extends Particle {
	private boolean invert = false;
	int velocityX;
	double velocityY;
	float dx = 0;

	public GoffParticle(int xPos, int yPos) {
		super(xPos, yPos);
		dx += (Mouse.getDX());
	}
	
	public void update() {
		
		super.update();
		
		prevx = x;
		prevy = y;
		
		y+=velocityY;
//		x+=velocityX;
		
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
			dx = (float) (-0.9*dx);
		}
		
		dx += (1-random.nextInt(3));
		x+=dx;
		
		if(x < 0 || x > ParticleWorld.winWidth) {
			dx = (float) (-0.8*dx);
		}
		if(dx == 0 && velocityY == 0) {
			killParticle();
		}
	}

}
