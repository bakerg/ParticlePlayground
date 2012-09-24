package com.danielmessias.particleplayground;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class ParticleWorld {	
	
	//Current Particle System
	ParticleSystem particleSystem;
	
	public Random random;
	public Class particleClass;
	public boolean addOnTick;
	public boolean randomOnTick;
	public int tickRate;
	
	public static int winWidth,winHeight;
	
	private long lastFPS;
	private int fps;
	
	public ParticleWorld(){
	}
	
	public static void main(String args[]){
		ParticleWorld pw = new ParticleWorld();
			pw.start();
	}
	
	public void start(){
		createDisplay();
		lastFPS = getTime();

		particleSystem = CurrentSystem.currentSystem;
		particleSystem.init();
		
		loop();
	}
	
	private void createDisplay(){
		
		winWidth = 800;
		winHeight = 600;
		
		try {
			Display.setDisplayMode(new DisplayMode(winWidth,winHeight));
			Display.setTitle("Particle World, FPS: ");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		glEnable(GL_LINE_SMOOTH);
		glEnable( GL_BLEND ); 
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);  
			
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, winWidth, winHeight, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle("Particle World, FPS: " + fps +" Particles: "+particleSystem.getParticlesAmount()); 
	        fps = 0;
	        lastFPS += 1000; 
	    }
	    fps++;
	}
	
	private void loop(){
		while(!Display.isCloseRequested()){
			run();
			updateFPS();
			Display.update();
			Display.sync(60);
		}
	}
	
	private void drawBackground(){
		glColor3f(0,0,0);
		glBegin(GL_QUADS);
			glVertex2f(0,0);
			glVertex2f(800,0);
			glVertex2f(800,600);
			glVertex2f(0,600);
		glEnd();
	}
	
	private void addParticles(int amount){
		particleSystem.requestNewParticles(amount);
	}
	
	private void removeParticles(int amount){
		particleSystem.requestRemoveParticles(amount);
	}
	
	private void run(){	
		keyboardHandler();
		drawBackground();
		particleSystem.update();
		
	}
	
	private void openConsole(){
		String inp = JOptionPane.showInputDialog("Enter a command");
		if(inp.startsWith("add:")){
			int a = Integer.parseInt(inp.substring(inp.indexOf(":")+1));
			addParticles(a);
		}else if(inp.startsWith("remove:")){
			int a = Integer.parseInt(inp.substring(inp.indexOf(":")+1));
			removeParticles(a);
		}
	}
	
	private void keyboardHandler(){
		while(Keyboard.next()){
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
				openConsole();
			}
		}
	}
}
