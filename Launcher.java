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

public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("MemeCraft", 640, 360);
		game.start();
	}
}
