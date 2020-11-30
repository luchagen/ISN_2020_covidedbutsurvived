package model;

public class Chest extends Tile {
	public Chest(String in_source) {
		this.nature=5;
		this.canWalkOn=true;
		this.canFinishGame=true;
		this.skin=super.findSource(in_source);
	}
}
