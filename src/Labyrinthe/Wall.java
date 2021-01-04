package Labyrinthe;

import engine.Cmd;

public class Wall extends Tile {
	public Wall(String in_source) {
		this.nature = 0;
		this.canWalkOn = false;
		this.canFinishGame = false;
		this.nextlevel = false;
		this.skin = "img/0/wall.txt";
		this.type = in_source;
		this.state = Cmd.IDLE;
		this.isAnimated=false;
	}
}
