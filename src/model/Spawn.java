package model;

import engine.Cmd;

public class Spawn extends Tile {

	public Spawn(String in_source) {
		this.nature = 3;
		this.canWalkOn = true;
		this.canFinishGame = false;
		this.nextlevel = false;
		this.skin = "img/3/spawner.txt";
		this.type = in_source;
		this.state = Cmd.IDLE;
	}
}
