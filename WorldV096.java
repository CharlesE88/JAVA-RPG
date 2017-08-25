* ****************************************************
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
// MAKE YOUR CLASS NAME World not World096 
// THIS IS VERSION 0.96 of the load world file I will make it so that
// the world is randomly generated from a file path in future versions
// or maybe even have it procedurally generated.

package com.memecraft.worlds;

import java.awt.Graphics;

import com.memecraft.tiles.Tile;
import com.memecraft.utils.Utilities;

public class World {

	private int width, height;
	private int spawnX, spawnY;
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
		String file = Utilities.loadFileAsString(path);
		// This will take every number and put it into its own string
		// for the string array
		String[] tokens = file.split("\\s+");
		// Now set the width and the height of the world
		// from the 1st index of the text file
		width = Utilities.parseInt(tokens[0]);
		height = Utilities.parseInt(tokens[1]);
		// Now set the player spawn point from the 2nd index of the 
		// text file
		spawnX = Utilities.parseInt(tokens[2]);
		spawnY = Utilities.parseInt(tokens[3]);
		// Now take the tile numbers listed in the text file in the
		// 3rd index and add them to the tiles Array
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				// convert the multidimensional array x,y into the proper position
				// in the tokens array. ADD 4 for the first 4 elements in the
				// world file variables, CHANGE THIS IF YOU ADD MORE ELEMENTS.
				tiles[x][y] = Utilities.parseInt(tokens[(x + y * width) + 4]);
			}
		}
		
	}
}
