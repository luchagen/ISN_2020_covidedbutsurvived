package model;

public class Pacman {
	public int X,Y,Xwt,Xet,Ynt,Yst,Xw,Xe,Yn,Ys,Xwtwest,Yntnorth;
	public String skin;
	
	public Pacman(int X_0,int Y_0) {
		X=X_0;
		Y=Y_0;
		}
	public Pacman(int[] spawn) {
		X=spawn[0]*Labyrinthe.Tile_length;
		Y=spawn[1]*Labyrinthe.Tile_length;
		this.skin= "img/hero.png";
		updateHitbox();
	}
	public void updateHitbox() {
		Xwtwest=(X+10-PacmanGame.game_speed);
		Xwt=Xwtwest/Labyrinthe.Tile_length;
		Xet=(X+30+PacmanGame.game_speed)/Labyrinthe.Tile_length;
		Yntnorth=(Y+10-PacmanGame.game_speed);
		Ynt=Yntnorth/Labyrinthe.Tile_length;
		Yst=(Y+30+PacmanGame.game_speed)/Labyrinthe.Tile_length;
		Xw=(X+10)/Labyrinthe.Tile_length;
		Xe=(X+30)/Labyrinthe.Tile_length;
		Yn=(Y+10)/Labyrinthe.Tile_length;
		Ys=(Y+30)/Labyrinthe.Tile_length;
	}
	public void moveUP() {
			Y=Y-PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
	}
	
	public void collisionUP() {
			Y=Y+1;
			updateHitbox();
			System.out.println("*BUMP*");
	}
	
	public void moveDOWN() {
			Y=Y+PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
		}
	
	public void collisionDOWN() {
		Y=Y-1;
		updateHitbox();
		System.out.println("*BUMP*");
	}
	
	public void moveLEFT() {
			X=X-PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
	}
	
	public void collisionLEFT() {
		X=X+1;
		updateHitbox();
		System.out.println("*BUMP*");
	}
	
	public void moveRIGHT() {
			X=X+PacmanGame.game_speed;
			updateHitbox();
			System.out.println("("+X+","+Y+")");
	}
	
	public void collisionRIGHT() {
		X=X-1;
		updateHitbox();
		System.out.println("*BUMP*");
	}
}
