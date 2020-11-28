package model;

public class Chest extends Tile {
	public Chest(String in_source) {
		this.nature=5;
		this.canWalkOn=false;
		this.canFinishGame=false;
		this.skin=super.findSource(in_source);
	}
}
