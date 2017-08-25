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

// THIS IS VERSION .1 of the load world file I will make it so that
// the world is randomly generated from a file path in future versions
// or maybe even have it procedurally generated.

package com.memecraft.worlds;

import java.awt.Graphics;

import com.memecraft.tiles.Tile;

public class World {

	private int width, height;
	private int [][] tiles;
	
	public World(String path) {
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		// The array inside the tile class
		Tile t = Tile.tiles[tiles[x][y]];
		// IF the array is past the ammount of tiles in the tile array
		// Then return the dirt tile
		if(t == null) 
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path) {
		width = 5;
		height = 5;
		tiles = new int [width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				tiles[x][y] = 2;
			}
		}
	}
}
