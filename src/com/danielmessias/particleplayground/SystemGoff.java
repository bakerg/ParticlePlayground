package com.danielmessias.particleplayground;

import java.util.Random;

import org.lwjgl.input.Mouse;

public class SystemGoff extends ParticleSystem {
	public static boolean shouldKillParticles = false;
	public void init(){
		setParticleType(ParticleType.LINE);
		setParticlesPerTick(0,10);
	}
	
	public void update(){
		super.update();
		if(addOnTick() && Mouse.isButtonDown(0)){
			requestNewParticles(1);
		}
		particlesUpdate();
		if(super.getParticlesAmount()>2000) {
			shouldKillParticles = true;
		}
		else {
			shouldKillParticles = false;
		}
	}
	
	public void requestNewParticles(int amount) {
		for(int i=0;i<amount;i++){
			addParticle(new GoffParticle(Mouse.getX(),windowHeight - Mouse.getY()));
		}
	}
}
