package com.danielmessias.particleplayground;

import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import java.util.Random;

public class ParticleSystem {
	
	private ArrayList<Particle> particles;
	private ArrayList<Particle> particlesRemove;
	
	//Constants
	protected int windowWidth = ParticleWorld.winWidth;
	protected int windowHeight = ParticleWorld.winHeight;
	protected Random random = new Random();
	
	//Particle System Properties
	private ParticleType particleType = ParticleType.POINT;
	private boolean addOnTick = false;
	private boolean particlesTickRandom = false;
	private int particlesTickMax = 1;
	private int particlesTickMin = 0;
	
	
	public ParticleSystem(){
		particles = new ArrayList<Particle>();
		particlesRemove = new ArrayList<Particle>();
	}
	
	public void init(){
	}
	
	public void update(){
		removeDeadParticles();
		render();
	}
	
	private void render(){
		if(particleType == ParticleType.POINT){
			renderPoint();
		}else if(particleType == ParticleType.LINE){
			renderLine();
		}
	}
	
	private void renderPoint(){
		glBegin(GL_POINTS);
			for(Particle p : particles){
				glVertex2f(p.x,p.y);
			}
		glEnd();
	}
	
	private void renderLine(){
		glColor3f(1,0,1);
		glBegin(GL_LINES);
			for(Particle p : particles){
				p.color.bind();
				glVertex2f(p.prevx,p.prevy);
				glVertex2f(p.x,p.y);
			}
		glEnd();
	}
	
	public void requestNewParticles(int amount) {	
	}
	
	public void requestRemoveParticles(int amount){
		trimToSize(amount);
	}
	
	//Base Class Only
	
	public int getParticlesAmount(){
		return particles.size();
	}
	
	protected void addParticle(Particle p){
		particles.add(p);
	}
	
	protected void removeParticle(Particle p){
		particles.remove(p);
	}
	
	protected void removeParticle(int index){
		particles.remove(index);
	}
	
	protected void trimToSize(int amount){
		particles.retainAll(particles.subList(0,particles.size()-amount));
	}
	
	protected Particle getParticle(int index){
		return particles.get(index);
	}
	
	protected void setAddOnTick(boolean setting){
		addOnTick = setting;
	}
	
	protected boolean addOnTick(){
		return addOnTick;
	}
	
	protected int getAmountToAddOnTick(){
		if(particlesTickRandom){
			return particlesTickMin + random.nextInt(particlesTickMax-particlesTickMin+1);
		}else{
			return particlesTickMax;
		}
	}
	
	protected void setParticlesPerTick(int amount){
		addOnTick = true;
		particlesTickRandom = false;
		particlesTickMax = amount;
	}
	
	protected void setParticlesPerTick(int minimum,int maximum){
		addOnTick = true;
		particlesTickRandom = true;
		particlesTickMax = maximum;
		particlesTickMin = minimum;
	}
	
	protected void particlesUpdate(){
		for(Particle p : particles){
			p.update();
		}
	}
	
	protected void setParticleType(ParticleType type){
		particleType = type;
	}
	
	
	//Practical Functions
	
	private void removeDeadParticles(){
		for(Particle p : particles){
			if(p.killed()){
				particlesRemove.add(p);
			}
		}
		for(Particle pr : particlesRemove) particles.remove(pr);
		particlesRemove.clear();
	}

}
