package model;

import engine.Cmd;

public class ItemSpawn extends Tile {

	public ItemSpawn(String in_source) {
		this.nature = 7;
		this.canWalkOn = true;
		this.canFinishGame = false;
		this.nextlevel = false;
		this.skin = "img/7/itemspawner.txt";
		this.type = in_source;
		this.state = Cmd.IDLE;
	}
}
