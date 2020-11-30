package model;

public class Door extends Tile{
	public Door(String in_source) {
		this.nature=2;
		this.canWalkOn=true;
		this.canFinishGame=false;
		this.skin=super.findSource(in_source);
	}
}
