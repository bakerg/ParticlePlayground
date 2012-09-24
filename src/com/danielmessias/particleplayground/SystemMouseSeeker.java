package com.danielmessias.particleplayground;

public class SystemMouseSeeker extends ParticleSystem{
	public SystemMouseSeeker(){
		super();
	}
	
	public void init(){
		setParticleType(ParticleType.LINE);
		
		for(int i=0;i<200;i++){
			addParticle(new MouseSeeker(random.nextInt(windowWidth),random.nextInt(windowHeight)));
		}
		
	}
	
	public void update(){
		super.update();
		particlesUpdate();
	}
	
	public void requestNewParticles(int amount) {
		for(int i=0;i<amount;i++){
			Particle p = new MouseSeeker(random.nextInt(windowWidth),random.nextInt(windowHeight));
			addParticle(p);
		}
	}
	
	public void requestRemoveParticles(int amount){
		trimToSize(amount);
	}
}
