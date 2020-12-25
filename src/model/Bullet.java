package model;

public class Bullet {
	public int X,Y;
	protected String skin;
	private char direction;
	
	public Bullet(Pacman in_shooter) {
		this.X=in_shooter.Xmid;
		this.Y=in_shooter.Ymid;
		this.direction=in_shooter.lastMove;
		this.skin="img/bullet/0/0.png";
	}
	
	public void evolveBullet() {
		switch(direction) {
		case 'L':
			X-=80;
			break;
		case 'R':
			X+=80;
			break;
		case 'U':
			Y-=80;
			break;
		case 'D':
			Y+=80;
			break;
		default:
			break;
		}
			
	}

}
