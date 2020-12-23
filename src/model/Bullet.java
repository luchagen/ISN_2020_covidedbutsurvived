package model;

public class Bullet {
	private int X,Y;
	private String skin;
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
			X-=1;
			break;
		case 'R':
			X+=1;
			break;
		case 'U':
			Y-=1;
			break;
		case 'D':
			Y+=1;
			break;
		default:
			break;
		}
			
	}

}
