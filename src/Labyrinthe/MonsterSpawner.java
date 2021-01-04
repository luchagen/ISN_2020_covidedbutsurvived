package Labyrinthe;

import engine.Cmd;

public class MonsterSpawner extends Tile {

	public MonsterSpawner(String in_source) {
		this.nature = 4;
		this.canWalkOn = true;
		this.canFinishGame = false;
		this.skin = "img/4/spawner.txt";
		this.type = in_source;
		this.state = Cmd.IDLE;
		this.isAnimated=false;
	}
}
