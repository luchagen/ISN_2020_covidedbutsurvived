package Labyrinthe;

import engine.Cmd;

public class Floor extends Tile {

	public Floor(String in_source) {
		this.nature = 1;
		this.canWalkOn = true;
		this.canFinishGame = false;
		this.nextlevel = false;
		this.skin = "img/1/floor.txt";
		this.type = in_source;
		this.state = Cmd.IDLE;
		this.isAnimated=false;
	}
}
