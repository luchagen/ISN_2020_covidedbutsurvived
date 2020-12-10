package model;

public class Wall extends Tile {
	public Wall(String in_source) {
		this.nature=0;
		this.canWalkOn=false;
		this.canFinishGame=false;
		this.nextlevel=false;
		this.skin=super.findSource(in_source);
	}
}
