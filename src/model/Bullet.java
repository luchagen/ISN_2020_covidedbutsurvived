package model;

public class Bullet {
	protected int X,Y,Xtile,Ytile;
	protected String skin;
	protected char direction;
	protected boolean isTerminal;
	
	public Bullet(Pacman in_shooter) {
		this.X=in_shooter.X;
		this.Xtile=in_shooter.X/Labyrinthe.Tile_length;
		this.Y=in_shooter.Y;
		this.Ytile=in_shooter.Y/Labyrinthe.Tile_length;
		this.direction=in_shooter.lastMove;
		this.skin="img/bullet/0/0.png";
		this.isTerminal=false;
	}
	public void evolveBullet() {
		switch(direction) {
		case 'L':
			X-=Labyrinthe.Tile_length;
			Xtile-=1;
			break;
		case 'R':
			X+=Labyrinthe.Tile_length;
			Xtile+=1;
			break;
		case 'U':
			Y-=Labyrinthe.Tile_length;
			Ytile-=1;
			break;
		case 'D':
			Y+=Labyrinthe.Tile_length;
			Ytile+=1;
			break;
		default:
			break;
		}
			
	}

}
