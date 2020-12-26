package model;

public class Weapon {
	private int x,y;
	private Pacman heros;
	public Weapon(Pacman heros_in) {
		this.x=heros_in.X;
		this.y=heros_in.Y;
		this.heros=heros_in;
	}
	
	public void move() {
		this.x=x;
		
	}

}
