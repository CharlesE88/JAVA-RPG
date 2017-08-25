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
// 0.97 ADDED HANDLER CLASS to the Game Class
package com.memecraft.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.memecraft.Display.Display;
import com.memecraft.gfx.Assets;
import com.memecraft.gfx.GameCamera;
import com.memecraft.gfx.ImageLoader;
import com.memecraft.gfx.SpriteSheet;
import com.memecraft.input.KeyManager;
import com.memecraft.states.State;
import com.memecraft.states.GameState;
import com.memecraft.states.MenuState;

public class Game implements Runnable{

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// STATES
	private State gameState;
	private State menuState;
	
	// INPUT
	private KeyManager keyManager;
	
	// CAMERA
	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	private void init() {
		// Display
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		// Camera
		gameCamera = new GameCamera(this, 0, 0);
		// Handler
		handler = new Handler(this);
		// States
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(gameState);
	}
	
	private void tick() {
		keyManager.tick();
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
	// Return the keyManager object so other classes can use them
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	// Get the width of the window
	public int getWidth() {
		return width;
	}
	// Get the height of the window
	public int getHeight() {
		return height;
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
