package com.memecraft.tiles;

import com.memecraft.gfx.Assets;

public class TreeTile extends Tile{

	public TreeTile(int id) {
		super(Assets.tree, id);
		
	}

	public boolean isSolid() {
		return true;
	}
	
}
