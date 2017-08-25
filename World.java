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

// THIS IS VERSION 0.97 of the load world file I will make it so that
// the world is randomly generated from a file path in future versions
// or maybe even have it procedurally generated.
// 0.96 ADDED TEXT FILE LOADING to the World class
// 0.97 ADDED GARBAGE COLLECTION to the render method
// 0.97 ADDED HANDLER CLASS to the World class
package com.memecraft.worlds;

import java.awt.Graphics;

import com.memecraft.main.Game;
import com.memecraft.main.Handler;
import com.memecraft.tiles.Tile;
import com.memecraft.utils.Utilities;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int [][] tiles;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		// Delete tiles that are out of the camera's view left side and top side
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		// Delete tiles that are out of the camera's view right side and bottom side
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		// The array inside the tile class
		Tile t = Tile.tiles[tiles[x][y]];
		// IF the array is past the amount of tiles in the tile array
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
