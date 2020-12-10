package model;

public class Floor extends Tile{
	
	public Floor(String in_source) {
		this.nature=1;
		this.canWalkOn=true;
		this.canFinishGame=false;
		this.nextlevel=false;
		this.skin=super.findSource(in_source);
	}
}
