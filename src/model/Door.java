package model;

import engine.Cmd;

public class Door extends Tile {
	public Door(String in_source) {
		this.nature = 2;
		this.canWalkOn = true;
		this.canFinishGame = false;
		this.nextlevel = true;
		this.skin = "img/2/door.txt";
		this.type = in_source;
		this.state = Cmd.IDLE;
	}
}
