/* ****************************************************
 * Feel free to modify the code any way you seem fit. *******************
 * I do ask that you add me the original author to the finished project, *
 * this could be in the comments or even a function named after me. *****
 * Author: Charles Eudy *********************************************
 * Date: 8/24/2017      *
 * Language: JAVA      *
 * Version: 0.97        *
 * Thank you and Enjoy! *
 ************************
 */
// 0.97 ADDED HANDLER CLASS to STATE CLASS
package com.memecraft.states;

import java.awt.Graphics;

import com.memecraft.main.Game;
import com.memecraft.main.Handler;

public abstract class State {

	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	// CLASS
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
