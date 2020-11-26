package model;

public class Spawn extends Tile{
	
	public Spawn(String in_source) {
		this.nature=3;
		this.canWalkOn=true;
		this.canFinishGame=false;
		this.skin=super.findSource(in_source);
	}
}
