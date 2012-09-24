package com.danielmessias.particleworld;


public class MouseSeeker extends Particle{
	
	
	private float dx,dy;
	private final float dmax = 6;
	private final int randFlux = 4;
	
	public MouseSeeker(int x, int y){
		super(x,y,ParticleType.LINE);
	}
	
	public void update(){
		super.update();
		
		prevx = x;
		prevy = y;
		
		float xDist = (mx-x);
		float yDist = (my-y);
				
		dx += (xDist/Math.abs(yDist));
		dy += (yDist/Math.abs(xDist));
		
		
		float dxmax = Math.abs(dmax*(xDist/yDist))+10;
		float dymax = Math.abs(dmax*(yDist/xDist))+10;

		if(dx>dxmax) dx = dxmax;
		if(dx<dxmax*-1) dx = dxmax*-1;
		if(dy>dymax) dy = dymax;
		if(dy<dymax*-1) dy = dymax*-1;
		
		
		x += dx;
		y += dy;
		
	}
	
	private int getFlux(){
		return randFlux - random.nextInt(randFlux*2);
	}
}
