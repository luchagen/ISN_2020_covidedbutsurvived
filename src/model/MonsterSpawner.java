package model;

public class MonsterSpawner extends Tile{
	
	public MonsterSpawner(String in_source) {
		this.nature=4;
		this.canWalkOn=true;
		this.canFinishGame=false;
		this.skin=super.findSource(in_source);
	}
}
