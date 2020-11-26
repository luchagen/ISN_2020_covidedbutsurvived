package model;

public class Wall extends Tile {
	public Wall() {
		this.nature=0;
		this.canWalkOn=false;
		this.canFinishGame=false;
		this.skin="img/small tree.png";
	}
}
