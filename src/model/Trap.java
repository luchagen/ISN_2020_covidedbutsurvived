package model;

public class Trap extends Tile{
	public Trap(String in_source) {
		this.nature=6;
		this.canFinishGame=false;
		this.canWalkOn=true;
		this.nextlevel=false;
		this.skin=super.findSource("100");
		this.trapSkin=in_source;
		this.isTrapOpen=false;
		
		switch(in_source.charAt(1)) {
		case '0':
			this.trapType="hole";
			this.damage=1;
			break;
		case '1':
			this.trapType="flood";
			this.damage=1;
			break;
		case '2':
			this.trapType="lava";
			this.damage=2;
			break;
		case '3':
			this.trapType="thunder";
			this.damage=2;
			break;
		default:
			this.trapType="hole";
			this.damage=1;
			break;
		}
	}
}
