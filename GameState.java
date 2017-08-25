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
// 0.97 ADDED HANDLER CLASS to GAMESTATE CLASS
package com.memecraft.states;

import java.awt.Graphics;

import com.memecraft.entities.creatures.Player;
import com.memecraft.gfx.Assets;
import com.memecraft.main.Game;
import com.memecraft.main.Handler;
import com.memecraft.tiles.Tile;
import com.memecraft.worlds.World;

public class GameState extends State{

	private Player player;
	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		player = new Player(handler, 100, 100);
	}
	
	public void tick() {
		world.tick();
		player.tick();
	}


	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}
}
