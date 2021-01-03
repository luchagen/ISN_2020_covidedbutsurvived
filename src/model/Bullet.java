package model;

public class Bullet {
	protected int X,Y,Xtile,Ytile;
	protected String skin;
	protected char direction;
	protected boolean isTerminal;
	protected Hitbox bullethitbox;
	
	public Bullet(Pacman in_shooter) {
		this.X=in_shooter.X;
		this.Xtile=in_shooter.X/Labyrinthe.Tile_length;
		this.Y=in_shooter.Y;
		this.Ytile=in_shooter.Y/Labyrinthe.Tile_length;
		this.direction=in_shooter.lastMove;
		this.skin="img/bullet/0/0.png";
		this.isTerminal=false;
		this.bullethitbox = new Hitbox(this.X, this.Y,10,10);
	}
	public void evolveBullet() {
		switch(direction) {
		case 'L':
			X-=2*PacmanGame.game_speed;
			this.Xtile=this.X/Labyrinthe.Tile_length;
			break;
		case 'R':
			X+=2*PacmanGame.game_speed;
			this.Xtile=this.X/Labyrinthe.Tile_length;
			break;
		case 'U':
			Y-=2*PacmanGame.game_speed;
			this.Ytile=this.Y/Labyrinthe.Tile_length;
			break;
		case 'D':
			Y+=2*PacmanGame.game_speed;
			this.Ytile=this.Y/Labyrinthe.Tile_length;
			break;
		default:
			break;
		}
		this.bullethitbox.updateHitbox(this.X, this.Y);
			
	}

}
