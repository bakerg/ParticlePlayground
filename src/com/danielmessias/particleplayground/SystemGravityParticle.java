package com.danielmessias.particleplayground;

public class SystemGravityParticle extends ParticleSystem{
	public SystemGravityParticle() {
		super();
	}
	
	public void init(){
		setParticleType(ParticleType.LINE);
		setParticlesPerTick(0,10);
	}
	
	public void update(){
		super.update();
		if(addOnTick()){
			requestNewParticles(getAmountToAddOnTick());
		}
		particlesUpdate();
	}
	
	public void requestNewParticles(int amount) {
		for(int i=0;i<amount;i++){
			addParticle(new GravityParticle());
		}
	}
	
}
