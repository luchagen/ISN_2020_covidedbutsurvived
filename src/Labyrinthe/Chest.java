package Labyrinthe;

import engine.Cmd;

public class Chest extends Tile {
	public Chest(String in_source) {
		this.nature = 5;
		this.canWalkOn = true;
		this.canFinishGame = true;
		this.nextlevel = false;
		this.skin = "img/5/chest.txt";
		this.type = in_source;
		this.state = Cmd.IDLE;
		this.isAnimated=true;
	}

}
