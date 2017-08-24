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
package com.memecraft.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;
	
	public static BufferedImage player, dirt, grass, stone, tree, water;
	
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/test_tile1.png"));
		
		player  = sheet.crop(width * 4, 0, width, height);
		dirt = sheet.crop(width * 3, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, height, width, height);
		tree = sheet.crop(width * 2, height, width, height);
		water = sheet.crop(0, 0, width, height);
	}
	
}
