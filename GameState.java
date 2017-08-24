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
package com.memecraft.states;

import java.awt.Graphics;

import com.memecraft.gfx.Assets;

public class GameState extends State{


	public GameState() {
		
	}
	
	public void tick() {
		
		
	}


	public void render(Graphics g) {
		g.drawImage(Assets.dirt, 205, 205, null);
		
	}

	
	
}
