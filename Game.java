/* ****************************************************
 * Feel free to modify the code any way you seem fit. *******************
 * I do ask that you add me the original author to the finished project, *
 * this could be in the comments or even a function named after me. *****
 * Author: Charles Eudy *********************************************
 * Date: 8/24/2017      *
 * Language: JAVA      *
 * Version: 0.96        *
 * Thank you and Enjoy! *
 ************************
 */
package com.memecraft.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.memecraft.Display.Display;
import com.memecraft.gfx.Assets;
import com.memecraft.gfx.ImageLoader;
import com.memecraft.gfx.SpriteSheet;
import com.memecraft.states.State;
import com.memecraft.states.GameState;

public class Game implements Runnable{

	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// STATES
	private State gameState;
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	private void init() {
		display = new Display(title, width, height);
		Assets.init();
		
		gameState = new GameState();
		State.setState(gameState);
	}
	
	private void tick() {
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		// set up the buffer strategy
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear the screen
		g.clearRect(0, 0, width, height);
		// Begin drawing to the screen
		
		if(State.getState() != null)
			State.getState().render(g);
		
		// End drawing to the screen
		bs.show();
		g.dispose();
		
	}
	
	public void run() {
		init();
		
		// Makes it so you don't have to click on the window
		// to start moving the player.
		//this.requestFocus();	
		int frames = 60;
		long now;
		long lastTime = System.nanoTime();
		double amountOfTicks = 1000000000 / frames;
		double delta = 0;
		long timer = 0;
		int ticks = 0;
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / amountOfTicks;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
					tick();
					render();
					ticks++;
					delta--;
					}
		
			if(timer > 1000000000) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
