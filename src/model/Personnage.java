package model;

public class Personnage {
	public int X,Y,Xwt,Xet,Ynt,Yst,Xw,Xe,Yn,Ys,Xwtwest,Yntnorth;
	public String skin;
	
	public Personnage(int X_0,int Y_0) {
		X=X_0;
		Y=Y_0;
		}
	
	public Personnage(int[] spawn) {
		X=spawn[0]*Labyrinthe.Tile_length;
		Y=spawn[1]*Labyrinthe.Tile_length;
		updateHitbox();
	}
	

	// mise � jour de la position des points qui d�finissent la hitbox du pacman, � mettre � jour apr�s chaque d�placement 
		public void updateHitbox() {
			int Tile_length = Labyrinthe.Tile_length;
			int a = (int) (0.355*Tile_length);
			int b = (int) (0.625*Tile_length);
			Xwtwest=(X+a-PacmanGame.game_speed);
			Xwt=Xwtwest/Tile_length;
			Xet=(X+b+PacmanGame.game_speed)/Tile_length;
			Yntnorth=(Y+a-PacmanGame.game_speed);
			Ynt=Yntnorth/Tile_length;
			Yst=(Y+b+PacmanGame.game_speed)/Tile_length;
			Xw=(X+a)/Tile_length;
			Xe=(X+b)/Tile_length;
			Yn=(Y+a)/Tile_length;
			Ys=(Y+b)/Tile_length;
		}
		
	public void moveUP() {
			Y=Y-PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionUP() {
			Y=Y+1;
			updateHitbox();
	}
	
	public void moveDOWN() {
			Y=Y+PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionDOWN() {
		Y=Y-1;
		updateHitbox();
	}
	
	public void moveLEFT() {
			X=X-PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionLEFT() {
		X=X+1;
		updateHitbox();
	}
	
	public void moveRIGHT() {
			X=X+PacmanGame.game_speed;
			updateHitbox();
	}
	
	public void collisionRIGHT() {
		X=X-1;
		updateHitbox();
	}
}
