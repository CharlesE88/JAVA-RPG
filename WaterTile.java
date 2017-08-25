package com.memecraft.tiles;

import com.memecraft.gfx.Assets;

public class WaterTile extends Tile{

	public WaterTile(int id) {
		super(Assets.water, id);
		
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
